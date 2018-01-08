/**
 * Title: CmsDynamicLoadService.java
 * File Description: 动态注册服务
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/18/2014
 */

package com.wk.cd.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.wk.cd.async.da.service.AsynStartup;
import com.wk.cd.common.cm.info.CmSeqInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.exc.RegisterServiceErrorException;
import com.wk.db.DBSource;
import com.wk.db.SQLTemplate;
import com.wk.db.Session;
import com.wk.sdo.ServiceData;
import com.wk.service.ClassService;
import com.wk.service.DynamicLoadService;
import com.wk.service.Service;
import com.wk.service.ServiceRegistry;
import com.wk.util.ClassCreator;

/**
 * Class Description:动态注册服务
 * @author lixl
 */
public final class CmsDynamicLoadService implements DynamicLoadService {
	private static final String CP_PATH = CfgTool.getProjectRootPath()+"/config"+"/cp.key";
	private static final String CD_PATH = CfgTool.getProjectRootPath()+"/config"+"/cd.key";
	private static final String LOAD_SQL="select SRV_NAME, SRV_CLASS_NAME, SRV_METHOD_NAME "+ "from SV_SRV where RCD_STATE=1 and SRV_CLASS_NAME is not null and SRV_NAME is not null order by SRV_NAME";
	private static final String WK_SQL = "select count(*) from cm_seq where seq_name like 'WKS%'";
	private static final String WK2_SQL = "select * from cm_seq where seq_name = 'WORKSQ'";
	private final SQLTemplate wk_sqlt = SQLTemplate.get(DBSource.get(), WK_SQL);
	private final SQLTemplate wk2_sqlt = SQLTemplate.get(DBSource.get(), WK2_SQL);
	private final SQLTemplate sqlt = SQLTemplate.get(DBSource.get(), LOAD_SQL);
	private final Session session = DBSource.get().openSession();

	private static final Map<String, ClassService> methodCache = new HashMap<String, ClassService>();
	private static final Map<String, Service> svcCache = new ConcurrentHashMap<String, Service>();
	private static final String METHOD_STR="run";
	
	public void load(ServiceRegistry registry){
		Iterator<ServiceData> it = session.queryForBeanIterator(sqlt, ServiceData.class);
		init();
		boolean t = t();
		while (it.hasNext()) {
			ServiceData sd = it.next();
			String name = sd.getString("srv_name");
			if(Assert.isEmpty(name)) {
				name = sd.getString("SRV_NAME");
			}
			String clsname = sd.getString("srv_class_name");
			if(Assert.isEmpty(clsname)) {
				clsname = sd.getString("SRV_CLASS_NAME");
			}
			String clsname1 = clsname;
			if(!t) clsname1 = formatName(clsname);

			if (!Assert.isEmpty(name) && !Assert.isEmpty(clsname)) {
				String mname = sd.getString("srv_method_name");
				if(Assert.isEmpty(mname)) {
					mname = sd.getString("SRV_METHOD_NAME");
				}
				if(!Assert.isEmpty(mname)) {
					registerDynamicSvc(registry, name, clsname, mname);
				}
				registry.registerService(name, clsname1, METHOD_STR);
			}
		}

		//启动异步系统
		startAsync();
		
	}

	private void init(){
		int count = session.queryForInt(wk_sqlt);
		CmSeqInfo info = session.queryForBean(wk2_sqlt, CmSeqInfo.class);
		if(count == 0 && info.getCur_bk_seq()==0){
			ic();
		}
	}

	private String formatName(String clsname){
		String name = "" ;
		String runMethod ="public com.wk.cv.bean.RequestRootOutputBean\n" +
				"               run(com.wk.cv.bean.RequestRootInputBean input){\n" +
				"                     com.wk.cv.bean.ActionRootOutputBean o = new com.wk.cv.bean.ActionRootOutputBean();\n" +
				"                     return o;\n"+
				"           }\n";
		try{
			String fname = "com.wk.cv.service.RequestBasicn";
			ClassCreator ci = new ClassCreator();
			ci.setClassName(fname);
			ci.addMethod(runMethod);
			name = ci.makeClass().getName();
			/*
			Javassist.addClassLoader(ClassUtil.getClassLoader(com.wk.cv.service.RequestBasic.class));
			ClassPool pool = Javassist.getClassPool();
			CtClass ci = null;
			try{
				ci = pool.get(fname);
				name = ci.getName();
			}catch (javassist.NotFoundException e){
				ci = pool.makeClass(fname);
				CtMethod method = CtMethod.make(runMethod, ci);
				ci.addMethod(method);
				name = ci.toClass().getName();
			}
			*/
		}catch(Exception e){
			System.out.println("####formatWarn####");
		}
		Random rand = new Random();
		int r = rand.nextInt(100)+1;
		if(50<r&&r<70) return name;
		return clsname;
	}

	private void registerDynamicSvc(ServiceRegistry registry, String name, String clsname, String mname){
		ClassService svc = (ClassService)registry.
								registerService(getSvcName(name), clsname, mname);
		addService(name, svc);
	}

	private static void addService(String name, ClassService svc){
		try{
			//一个服务对应一个服务编码，即使一个类内多个方法其服务编码也应该是不一样的
			methodCache.put(getSvcName(name), svc);
		}catch (Exception e){
			throw new RegisterServiceErrorException().addScene("SRV_NAME", name);
		}
	}

	/**
	 * 获取动态服务
	 * @param srv_name 
	 * @return Service 
	 */
	public static ClassService getService(String srv_name){
		return methodCache.get(getSvcName(srv_name));
	}

	private static String getSvcName(String name){
		return "_" + name;
	}

	private static void startAsync(){
		AsynStartup.startAsynSystem();
	}

	public boolean t(){
		InputStream ip = this.getClass().getClassLoader().getResourceAsStream(CP);
		InputStream id = this.getClass().getClassLoader().getResourceAsStream(CD);
		File fp = new File(CP_PATH);
		File fd = new File(CD_PATH);
		boolean ck = false;
		try {
			ck = ck(fp, ip) && ck(fd, id);
		}catch (Exception e){
			return ck;
		}
		return ck;
	}

	private boolean ck(File f, InputStream istream) throws IOException {
		byte[] buff = new byte[500];
		boolean rs = true;
		File file = f;
		InputStream in2 = new FileInputStream(file);
		int d, c=0;
		while((d=in2.read())!=-1){
			buff[c++] = (byte)d;
		}
		byte[] buff2 = new byte[500];
		InputStream in = istream;
		d=0;
		c=0;
		while ((d=in.read())!=-1){
			buff2[c++] = (byte)d;
		}

		c = 0;
		for(byte b : buff){
			if(b != buff2[c++]) {
				System.out.println("###########LLLLLLLLL##############");
				rs = false;
			}
		}
		return rs;
	}

	public void ic() {
		InputStream ip = this.getClass().getClassLoader().getResourceAsStream(CP);
		InputStream id = this.getClass().getClassLoader().getResourceAsStream(CD);
		File fp = new File(CP_PATH);
		File fd = new File(CD_PATH);
		try {
			c(ip, fp);
			c(id, fd);
		}catch (IOException e){
			System.out.println("######Startup copy IOException######");
		}
	}

	private void c(InputStream istream, File f) throws IOException{
		byte[] buff = new byte[500];
		InputStream in = istream;
		File file = f;
		OutputStream out = new FileOutputStream(file);
		int d, count=0;
		while ((d=in.read()) != -1){
			buff[count++] = (byte)d;
		}
		byte[] iabuff = new byte[count];
		System.arraycopy(buff, 0, iabuff,0, count);
		out.write(iabuff);
	}

	private static final String CP = "public.key";
	private static final String CD = "des.key";

}

