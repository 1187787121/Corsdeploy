/**
 * Title: TestViewInteAction.java
 * File Description: ���Լ�������鿴����
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��22��
 */
package com.wk.cd.test.build.ea.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.ViewInteAction;
import com.wk.cd.build.ea.bean.DownFileBean;
import com.wk.cd.build.ea.bean.InteMonStepBean;
import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.bean.ViewInteInputBean;
import com.wk.cd.build.ea.bean.ViewInteOutputBean;
import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.cd.enu.TASK_ALL_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;
import com.wk.util.JaDate;

/**
 * Class Description: ���Լ�������鿴����
 * @author Xul
 */
public class TestViewInteAction extends TestCase{
	
	private DBSource db = DBSource.get();
	private Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private ViewInteAction sc = inject.getBean(ViewInteAction.class);
	
	@Override
	protected void setUp()
		throws Exception {
		if (session == null) {
			session = db.openSession();
			session.beginTransaction();
		}
	}

	@Override
	protected void setUpOnce()
		throws Exception {
		session = db.openSession();
		session.beginTransaction();
	}

	@Override
	protected void tearDown()
		throws Exception {
		session.commitAndResume();
	}

	@Override
	protected void tearDownOnce()
		throws Exception {
		session.commit();
	}
	
	/**
	 * Description:���Բ鿴������������
	 */
	public void testQueryInteDetail(){
		ViewInteInputBean input = new ViewInteInputBean();
		input.setWork_id("TK201701090005");
		ViewInteOutputBean output = sc.queryInteDetail(input);
		System.out.println(output.getEnv_name());
		System.out.println(output.getTask_bk_desc());
		System.out.println(output.getProject_name());
		System.out.println(output.getProg_id());
//		System.out.println(output.getCode_ver_num());
		System.out.println(output.getTask_status());
		System.out.println(output.getExe_result());
	}
	
	/**
	 * Description:�������ִ�н���
	 */
	public void testMonitorInte(){
		ViewInteInputBean input = new ViewInteInputBean();
		input.setWork_id("TK201701090005");
		ViewInteOutputBean output = sc.monitorInte(input);
		System.out.println(output.getStart_bk_tm());
		System.out.println(output.getEnd_bk_tm());
		System.out.println(output.getTask_status());
		System.out.println(output.getExe_result());
		for(InteMonStepBean bean : output.getInte_step_list()){
			System.out.println(bean.getStep_id());
			System.out.println(bean.getStep_expl());
			System.out.println(bean.getExe_status());
			System.out.println(bean.getTask_exe_result());
		}
		System.out.println(output.getAll_steps());
		System.out.println(output.getExe_step());
	}
	
	/**
	 * Description:��������ID��ȡ�������ļ��б�
	 */
	public void testQueryDownFileList(){
		ViewInteInputBean input = new ViewInteInputBean();
		input.setWork_id("TK201701070005");
		ViewInteOutputBean output = sc.queryDownFileList(input);
		for(DownFileBean bean : output.getDown_file_list()){
			System.out.println(bean.getFile_name());
			System.out.println(bean.getFile_path());
			System.out.println(bean.getFile_type());
		}
	}
	
	/**
	 * Description:����Ŀ���
	 */
	public void testGetTargetPackage(){
		ViewInteInputBean input = new ViewInteInputBean();
		input.setDtbs_bk_date(JaDate.today());
		input.setWork_id("TK201701060004");
		input.setTask_all_type(TASK_ALL_TYPE.INTEGRATION);
		input.setPac_list(new String[]{"corslares.tar"});
		ViewInteOutputBean output = sc.getTargetPackage(input);
		for(String str : output.getFull_path_list()){
			System.out.println(str);
		}
	}
	
	/**
	 * Description:��ȡ�嵥��Ͷ����
	 */
	public void testGetListAndPac(){
		ViewInteInputBean input = new ViewInteInputBean();
//		input.setWork_id("111");
		input.setEnv_name("����");
		input.setProg_id("PG201702150002");
		input.setVercode_ver_num("�汾��");
		ViewInteOutputBean output = sc.getListAndPac(input);
		System.out.println("------------------Ͷ����------------------");
		for(TargetPackageBean bean : output.getPac_list()){
			System.out.println("------------------��------------------");
			System.out.println(bean.getPackage_name());
			System.out.println(bean.getDownload_path());
//			for(UuFilelistInfo info : bean.getFile_list()){
//				System.out.println("------------------�ļ�------------------");
//				System.out.println(info.getFile_name());
//				System.out.println(info.getFile_path());
//			}
		}
		System.out.println("------------------�嵥------------------");
		for(TargetPackageBean bean : output.getList_list()){
			System.out.println(bean.getPackage_name());
			System.out.println(bean.getDownload_path());
		}
	}
	
	/**
	 * Description:��ȡ�汾���б�
	 */
	public void testQueryVersionList(){
		ViewInteInputBean input = new ViewInteInputBean();
		input.setProg_id("PG201709250001");
		ViewInteOutputBean output = sc.queryVersionList(input);
		for(String str : output.getVersion_list()){
			System.out.println(str);
		}
	}
}