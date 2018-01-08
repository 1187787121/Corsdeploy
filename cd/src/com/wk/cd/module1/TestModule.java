package com.wk.cd.module1;

/**
 * Created by 姜志刚 on 2016/11/2.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.module1.entity.Instance;
import com.wk.cd.module1.entity.InstancePhase;
import com.wk.cd.module1.impl.DefaultEnv;
import com.wk.cd.module1.impl.DefaultProcess;
import com.wk.cd.module1.impl.FTP;
import com.wk.cd.module1.impl.ModuleBase;
import com.wk.cd.module1.impl.SVN;
import com.wk.cd.module1.impl.Shell;
import com.wk.cd.module1.impl.UriModule;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.info.Param;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.remote.agent.service.AgentRSession;
import com.wk.cd.remote.agent.service.CheckAgentExistService;
import com.wk.cd.remote.agent.util.AgentCommonTool;
import com.wk.cd.remote.bean.AsyncMsgBean;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.sdo.ServiceData;
import com.wk.test.TestCase;
import com.wk.util.JSON;

public class TestModule
		extends TestCase {

	public void testSuccess() {
		DtSourceInfo info = createDtInfo(PROTOCOL_TYPE.SSH, "10.1.1.220", 22, "front", "front");
		info.setUser_root_path("/home/front");
		ModuleSourceInfo msi = new ModuleSourceInfo(info.getProtocol_type(), info);
		String cmd = "ls -l\nuname -a";
		Shell script = new Shell(msi, cmd);
		Result result = script.run();
		System.out.println(result);
		assertEquals(CMD_STATUS.SUCCEED, result.getStatus());
		assertTrue(!script.isConnected());
	}

	public void testStep() {
		DtSourceInfo info = createDtInfo(PROTOCOL_TYPE.SSH, "10.1.1.220", 22, "front", "front");

		ModuleSourceInfo msi = new ModuleSourceInfo(info.getProtocol_type(), info);
		String cmd = "ls -l\nuname -a";
		Shell script = new Shell(msi, cmd);

		Result result = script.stepinto(0);
		assertEquals(CMD_STATUS.SUCCEED, result.getStatus());
		assertTrue(script.isConnected());

		result = script.stepinto(1);
		assertEquals(CMD_STATUS.SUCCEED, result.getStatus());
		assertTrue(!script.isConnected());
	}

	public void testRunModule() {
		DtSourceInfo info = createDtInfo(PROTOCOL_TYPE.SSH, "10.1.1.227", 22, "sample", "sample");
		info.setUser_root_path("/home/sample");
		ModuleSourceInfo msi = new ModuleSourceInfo(info.getProtocol_type(), info);
		String[] cmds = { "systemctl status httpd" };
		Shell script = new Shell(msi, cmds);

		Result result = script.runModule(null);
		System.out.println(result);

	}

	public void testError() {
		DtSourceInfo info = createDtInfo(PROTOCOL_TYPE.SSH, "10.1.1.220", 22, "front", "front");

		ModuleSourceInfo msi = new ModuleSourceInfo(info.getProtocol_type(), info);
		String cmd = "xxx";
		Shell script = new Shell(msi, cmd);
		Result result = script.run();
		System.out.println(result);
		assertEquals(CMD_STATUS.ERROR, result.getStatus());
		assertTrue(!script.isConnected());
	}

	private class XModule
			extends ModuleBase {

		private String msg;

		XModule(String msg) {
			this.msg = msg;
		}

		@Override
		public Result run() {
			System.out.println(msg);
			return new Result(CMD_STATUS.SUCCEED, msg, System.currentTimeMillis());
		}

		/**
		 * Description:
		 */
		@Override
		public void sessionClose() {
			// TODO Auto-generated method stub

		}

		/**
		 * Description:
		 */
		@Override
		public void interactRun(String remote_relative_dir) {
			// TODO Auto-generated method stub

		}

		/**
		 * Description:
		 * @param cmd
		 */
		@Override
		public void sendInteractCmd(String cmd, boolean sensitive_flag) {
			// TODO Auto-generated method stub

		}

		/**
		 * Description:
		 * @return
		 */
		@Override
		public AsyncMsgBean getInteractMsg() {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * Description:
		 * @return
		 */
		@Override
		public Result runModule(String remote_relative_dir) {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * Description:
		 * @return
		 */
		@Override
		public IMPL_TYPE getImplType() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public void testProcess() {
		DtSourceInfo info = createDtInfo(PROTOCOL_TYPE.SSH, "10.1.1.220", 22, "front", "front");
		DefaultProcess process = new DefaultProcess(null);
		ModuleSourceInfo msi = new ModuleSourceInfo(info.getProtocol_type(), info);
		process.addModule(new Shell(msi, "ls -l\nuname -a"));
		process.addModule(new Shell(msi, "xxx"));
		process.addModule(new XModule("run run run"));

		Result result = process.run(0);
		System.out.println(result);
		assertEquals(CMD_STATUS.SUCCEED, result.getStatus());

		result = process.run(1);
		System.out.println(result);
		assertEquals(CMD_STATUS.ERROR, result.getStatus());

		result = process.run(2);
		System.out.println(result);
		assertEquals(CMD_STATUS.SUCCEED, result.getStatus());
	}

	public void testInstance() {
		DtSourceInfo info = createDtInfo(PROTOCOL_TYPE.SSH, "10.1.1.220", 22, "front", "front");
		DtSourceInfo info2 = createDtInfo(PROTOCOL_TYPE.TELNET, "10.1.1.228", 23, "front", "front");

		ModuleSourceInfo minfo = new ModuleSourceInfo(PROTOCOL_TYPE.SSH, info);

		ModuleSourceInfo minfo1 = new ModuleSourceInfo(PROTOCOL_TYPE.TELNET, info2);

		Instance inst = new Instance("112233");
		InstancePhase mdl = new InstancePhase();
		mdl.addCmds("ls -l".split("\n"));
		mdl.setImpl_type(IMPL_TYPE.SHELL);
		mdl.setModule_source_info(minfo);
		inst.addPhase(mdl);

		mdl = new InstancePhase();
		mdl.setImpl_type(IMPL_TYPE.SHELL);
		mdl.addCmds("uname -a".split("\n"));
		mdl.setModule_source_info(minfo1);

		inst.addPhase(mdl);

		Process proc = ProcessManager.instance.buildProcess(inst);
		Result result = proc.run(0);
		System.out.println(result);
		assertEquals(CMD_STATUS.SUCCEED, result.getStatus());

		result = proc.run(1);
		System.out.println(result);
		assertEquals(CMD_STATUS.SUCCEED, result.getStatus());
	}

	public void testUrlModule()
		throws URISyntaxException, UnsupportedEncodingException {
		String cmd = "ls%20-l%0auname%20-a";
		Result result = UriModule.invoke("ssh://front@10.1.1.220:22?password=front&cmd=" + cmd);
		System.out.println(result);
		assertEquals(CMD_STATUS.SUCCEED, result.getStatus());
	}

	public void testSVN() {
		final int port = 22;
		final PROTOCOL_TYPE prot_type = PROTOCOL_TYPE.SVN;
		final String user = "monitor";
		final String pass = "monitor";
		final int timeout = 240;
		final String local_root = "/home/monitor";
		final String url = "svn://10.1.1.240/";
		final String remote_path = "/CorsManager/bank_kk/cd_xp_test";
		final String sc_user = "jiangzg";
		final String sc_pass = "jiangzg";
		Result result = null;
		try {
			DtSourceInfo info = new DtSourceInfo();
			info.setProtocol_type(PROTOCOL_TYPE.SSH);
			info.setSoc_name("22ssh");
			info.setSoc_ip("10.1.1.228");
			info.setSoc_port(port);
			info.setRemote_uname(user);
			info.setUser_root_path(local_root);
			info.setBk_timeout(timeout);
			String pass_key = "111111111111";
			info.setKey_remote_passwd(DESUtil.docryptAllowReverse(true, null, pass_key).trim());
			info.setRemote_passwd(DESUtil.docryptAllowReverse(true, pass_key, pass).trim());
			String p = DESUtil.docryptAllowReverse(true, pass_key, sc_pass).trim();

			ModuleSourceInfo minfo = new ModuleSourceInfo(prot_type, info);

			ServiceData data = new ServiceData();
			data.putString("url", url);
			data.putString("user", sc_user);
			data.putString("password", p);
			System.out.println(p);
			minfo.setData(data);
		/*	String[] cmds = new String[] { "pwd", "ls -l", "add /home/monitor/svntest/d.txt",
					"rm /home/monitor/svntest/a.txt", "ci \"svn commit test\"" };*/
			String[] cmds = new String[] {"co /CorsManager/bank_kk/cd_xp_test /home/monitor/svntest"};
			SVN svn = new SVN(minfo, cmds);
			result = svn.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);
		//assertEquals(CMD_STATUS.SUCCEED, result.getStatus());
	}

	private Env buildEnv() {
		DefaultEnv env = new DefaultEnv();
		ParamInfo info = new ParamInfo();
		info.setParam_name("dir");
		Param param = new Param(info, new String[] { "aaa", "bbb", "ccc" });
		env.setParam(param.getName(), param);
		return env;
	}

	private static DtSourceInfo createDtInfo(PROTOCOL_TYPE proto_type, String ip, int port, String user,
			String password) {
		DtSourceInfo info = new DtSourceInfo();
		info.setProtocol_type(proto_type);
		info.setSoc_name(proto_type.getName() + "_" + ip + "_" + user);
		info.setSoc_ip(ip);
		info.setSoc_port(port);
		info.setRemote_uname(user);
		String pass = "111111111111";
		info.setKey_remote_passwd(DESUtil.docryptAllowReverse(true, null, pass).trim());
		info.setRemote_passwd(DESUtil.docryptAllowReverse(true, pass, password).trim());
		return info;
	}

	public void testAgent() {
		DtSourceInfo info = createDtInfo(PROTOCOL_TYPE.AGENT, "10.1.1.85", AgentCommonTool.getPort(), "sample", "sample");

		ModuleSourceInfo msi = new ModuleSourceInfo(info.getProtocol_type(), info);
		// String cmd = "set l=100100\necho %l%";
		// String cmd = "cd F:\\workspace-android\ncd
		// F:\\workspace-android\\Demo\nls";
		// String cmd ="md F:\\workspace-android-studio\\Demo";
		// String cmd = "xcopy F:\\workspace-android\\Alipay
		// F:\\workspace-android-studio\\Demo /E";
		// String cmd = "if [ -d 'ZJ_AGENT' ]; then echo 'Directory ZJ_AGENT
		// already exists !'; else mkdir -p ZJ_AGENT; fi;";
		// String cmd = "pwd";
		// String cmd = "cd Agent\ncd Agent\\bin\nls";
		// String cmd = "nohup ./.collect/zj_qi/coCPU.sh
		// TA201706290001/0/COMP201704270463 TA201706290001/0/COMP201704270463
		// &";
		// String cmd = "wmic nic list brief";
		// String cmd = "ifstat 1 1 | tail -1";
		String cmd = "pwd";
		// String cmd = "while read line; do if [ -f $line ];then backup
		// $line;fi;done < ./upload/agent01/agent01.list";
		Shell script = new Shell(msi, cmd);
		Result result = script.runModule("common/");
		System.out.println(result);
		assertEquals(CMD_STATUS.SUCCEED, result.getStatus());
		assertTrue(!script.isConnected());
	}

	public void testCheck() {
		boolean result = CheckAgentExistService.checkAgentExist("10.1.1.227", AgentCommonTool.getPort());
		System.out.println(result);
	}

	public void testJDBC() {
		DtSourceInfo info = new DtSourceInfo();
		info.setSoc_name("232DB2");
		info.setSoc_ip("10.1.1.232");
		info.setSoc_port(50000);
		info.setProtocol_type(PROTOCOL_TYPE.JDBC);
		info.setRemote_uname("db2inst1");
		info.setRemote_passwd("09ceb3d451c15fe3f3bec332a41aba27");
		info.setKey_remote_passwd("a8cbfc6852835d24dcb9d17047cb04cf");
		info.setBk_timeout(0);
		info.setJdbc_drv("com.ibm.db2.jcc.DB2Driver");
		info.setJdbc_url("jdbc:db2://10.1.1.232:50000/cldb");

		AgentRSession session = new AgentRSession("10.1.1.227", 39999, IMPL_TYPE.SQL, 1, 1, false, null,null, 0, 0);
		ShExecRsBean bean = session.sendCmd("select * from BS_NODE");
		// ShExecRsBean bean = session.sendCmd("update BS_NODE set NODE_CN_NAME
		// = '1011232touble_sys' where NODE_NAME = '1011232touble_sys'");

		ServiceData data = JSON.toServiceData(bean.getRs_msg());
		System.out.println(data);
		// ShExecRsBean bean = session.sendCmd("update BS_NODE set NODE_CN_NAME
		// = '1011220test_SYS' where NODE_NAME = '1011220test_SYS'");
	}

	public void testFtp() {
		DtSourceInfo info = createDtInfo(PROTOCOL_TYPE.PLT_FTP, "10.1.1.220", 21, "front", "front");
		info.setUser_root_path("/home/front");
		ModuleSourceInfo msi = new ModuleSourceInfo(info.getProtocol_type(), info);
		FTP script = new FTP(msi, new String[] { "bin" });
		Result result = script.run();
		System.out.println(result);
	}

	public void testStopAgent() {
		boolean flag = AgentCommonTool.startOrStopWorkProc("10.1.1.220", 38888, 2);
		System.out.println(flag);
	}

	Timer timer = null;
	TimerTask task = null;

	public void test666() {
		/**
		 * 交互式Agent
		 */
		final AgentRSession ss = new AgentRSession("10.1.1.227", 39999, IMPL_TYPE.SHELL, 40, 2, true, "test/");
		ss.sendCmd("echo \"请输入要查找的路径\"");
		ss.sendCmd("read path");
		ss.sendCmd("cd $path");
		ss.sendCmd("echo \"请输入要查找的文件名\"");
		ss.sendCmd("read file");
		ss.sendCmd("if [ ! -e $file ]; then");
		ss.sendCmd("echo \"文件不存在，是否创建文件？输入y创建，n不创建并退出script\"");
		ss.sendCmd("read yn");
		ss.sendCmd("if [ \"$yn\" = \"y\" ] || [ \"$yn\" = \"Y\" ]; then");
		ss.sendCmd("touch $file");
		ss.sendCmd("echo \"文件已创建，退出script\"");
		ss.sendCmd("exit 1");
		ss.sendCmd("else");
		ss.sendCmd("echo \"已退出script\"");
		ss.sendCmd("fi");
		ss.sendCmd("elif [ -e $file ] && [ -f $file ]; then");
		ss.sendCmd("echo \"该文件已存在，是否删除重建？输入y删除重建，输入n则退出script\"");
		ss.sendCmd("read yn");
		ss.sendCmd("if [ \"$yn\" = \"y\" ] || [ \"$yn\" = \"Y\" ]; then");
		ss.sendCmd("rm -rf $file");
		ss.sendCmd("touch $file");
		ss.sendCmd("echo \"旧文件已删除，新文件已创建，已退出script\"");
		ss.sendCmd("exit 1");
		ss.sendCmd("else");
		ss.sendCmd("echo \"已退出script\"");
		ss.sendCmd("fi");
		ss.sendCmd("elif [ -e $file ] && [ -d $file ]; then");
		ss.sendCmd("echo \"该目录已存在，是否删除该目录，并重新建立一个目录？输入y删除重建，输入n则退出script\"");
		ss.sendCmd("read yn");
		ss.sendCmd("if [ \"$yn\" = \"y\" ] || [ \"$yn\" = \"Y\" ]; then");
		ss.sendCmd("rm -rf $file");
		ss.sendCmd("mkdir $file");
		ss.sendCmd("echo \"旧目录已删除，新目录已创建，已退出script\"");
		ss.sendCmd("exit 1");
		ss.sendCmd("else");
		ss.sendCmd("echo \"已退出script\"");
		ss.sendCmd("fi");
		ss.sendCmd("else");
		ss.sendCmd("echo What is wrong???");
		ss.sendCmd("fi");
		/**
		 * 定时任务获取数据流数据
		 */
		task = new TimerTask() {
			@Override
			public void run() {
				String rs = ss.asyncRunMsg().getMsg();

				if (!Assert.isEmpty(rs) && rs.length() > 0)
					System.out.println(rs.trim());
			}
		};
		timer = new Timer();
		long delay = 0;
		long intevalPeriod = 3 * 1000;
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);

		while (true) {
			try {
				BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
				String line = buf.readLine();
				OnStopAccessToken();

				String[] cmds = { line };
				ss.asyncRunStage(cmds, false, false);

				task = new TimerTask() {
					@Override
					public void run() {
						AsyncMsgBean bean = ss.asyncRunMsg();
						String rs = bean.getMsg();
						boolean exitStatus = bean.isEnd_flag();

						if (!Assert.isEmpty(rs) && rs.length() > 0)
							System.out.println(rs.trim());

						if (exitStatus) {
							System.out.println("stream closed!!!");
							OnStopAccessToken();
						}
					}
				};
				timer = new Timer();
				timer.scheduleAtFixedRate(task, delay, intevalPeriod);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 关闭定时更新AccessToken
	 */
	public void OnStopAccessToken() {
		if (task != null) {
			task.cancel();
		}

		if (timer != null) {
			timer.cancel();
		}
	}

	
	
	public void test666111(){
		AgentRSession session = new AgentRSession("10.1.1.85", 39999, IMPL_TYPE.SHELL, 5, AgentRSession.SYNCHRO_TYPE, true, "test/");
		String[] cmds = new String[5];
		cmds[0]="for /l %%i in (1,1,60) do (";
		cmds[1]="	call echo DATE: %%DATE%% %%TIME%% %%i >> F:/zenmeban2.txt";
		cmds[2]="	ping 1.1.1.1 -n 1 -w 5000 > nul";
		cmds[3]=")";
		cmds[4]="echo F:/zenmeban1.txt";
		ShExecRsBean bean = session.sendCmds(cmds);
		System.out.println(bean);
	}
	
	
	public void test888(){
		
		AgentRSession session = new AgentRSession("10.1.1.227", 39999, IMPL_TYPE.SHELL, 4, AgentRSession.SYNCHRO_TYPE, true, "test/");
		String[] cmds = new String[4];
		cmds[0] = "for (( i=1; i<=100; i++ ));do";
		cmds[1] = "	echo $i";
		cmds[2] = "	sleep 5";
		cmds[3] = "done;";
		ShExecRsBean bean = session.sendCmds(cmds);
		System.out.println(bean);
	}
	
	public void test777(){
		AgentRSession session = new AgentRSession("10.1.1.85", 39999, IMPL_TYPE.SHELL, 1, AgentRSession.SYNCHRO_TYPE, true, "test/");
		String[] cmds = new String[2];
		cmds[0] = "sleep 5";
		cmds[1] = "echo end";
		ShExecRsBean bean = session.sendCmds(cmds);
		System.out.println(bean);
	}
	
	public void test999(){
		
		boolean flag = CheckAgentExistService.checkAgentExist("10.1.1.220", 39999);
		if(flag){
			test777();
		}
//		CheckAgentExistService.checkAgentExist("10.1.1.85", 39999);
//		AgentRSession session = new AgentRSession("10.1.1.85", 39999, IMPL_TYPE.SHELL, 1);
//		
//		session.sendCmd("pwd");
	}
	
	public void test9999(){
		AgentRSession session = new AgentRSession("192.168.240.1", 39999, IMPL_TYPE.SHELL, 1, AgentRSession.SYNCHRO_TYPE, true, "test/");
		String[] cmds = new	String[2];
		cmds[0] = "cd /d E:/网易云音乐/CloudMusic";
		cmds[1] = "start cloudmusic.exe";
//		ShExecRsBean bean = session.sendCmd("start C:/Users/12049/Desktop/test.cmd");
		ShExecRsBean bean = session.sendCmds(cmds);
		System.out.println(bean);
		
	}
	
	public void test1000(){
		
		AgentRSession session = new AgentRSession("10.1.1.85", 39999, IMPL_TYPE.SHELL, 2, AgentRSession.SYNCHRO_TYPE, true, "test/");
		String[] cmds = new String[7];
		
//		cmds[0] = "echo begin";
//		cmds[1] = "cd /d E:/网易云音乐/CloudMusic";
//		cmds[2] = "start cloudmusic.exe";
		
		cmds[0] = "setlocal enabledelayedexpansion";
		cmds[1] = "set a=";
		cmds[2] = "cd /d f:/";
		cmds[3] = "for /f %%i in ('call echoTest.bat') do ( set a=!a! %%i)";
		cmds[4] = "for %%i in ( \"%a%\" ) do ( echo %%i echo %a% )";
		cmds[5] = "echo %str%";
		cmds[6] = "echo %var%";
//		cmds[0] = "cd /d f:/";
//		cmds[1] = "call echoTest.bat";
//		cmds[2] = "echo %1%~1";
//		cmds[1] = "for /f %%i in ('call echoTest.bat') do ( set a=%%i && echo %a% )";
//		cmds[0] = "cd /d E:/网易云音乐/CloudMusic";
////		cmds[1] = "start cloudmusic.exe";
//		cmds[1] = "echo yaeryou";
//		cmds[2] = "echo haha";
//		cmds[3] = "start cloudmusic.exe";
		
		
//		cmds[0] = "for /l %%i in (1,1,5) do (";
//		cmds[1] = "		call echo DATE: %%DATE%% %%TIME%% %%i >> D:/hulc.txt";
//		cmds[2] = "		ping 1.1.1.1 -n 1 -w 5000 > nul";
//		cmds[3] = "	)";
//		cmds[4] = "	echo end";
		
		ShExecRsBean bean = session.sendCmds(cmds);
		System.out.println(bean);
	}
	
	
	public void test11111(){
		/**
		 * ftp下载
		 */
//		AgentRSession ss = new AgentRSession("10.1.1.227", 39999, IMPL_TYPE.FTP, 0);
//		ss.sendCmd("cd /home/sample/upload/agent123");
//		// ss.sendCmd("cd D:/");
//		ss.sendCmd("lcd F:/logs1/");
//		ShExecRsBean bean = ss.sendCmd("get target.tar");
//		System.out.println("******ftp下载**************");
//		System.out.println(bean);
//		System.out.println("**************************");
		
//		AgentFTPService.download("10.1.1.85", 39999, "F:/logs1/", "E:/网易云音乐/CloudMusic/cloudmusic.exe");
//		AgentRSession session = new AgentRSession("10.1.1.227", 39999, IMPL_TYPE.SHELL, 1);
//		String[] cmds = new String[1];
////		cmds[0] = "export LANG=\"zh_CN.UTF-8\"";
//		cmds[0] = "systeminfo";
//		String msg = session.sendCmds(cmds).getRs_msg();
//		System.out.println(msg);
		
		
		
		AgentRSession session = new AgentRSession("10.1.1.85", 39999, IMPL_TYPE.SHELL, 1, AgentRSession.SYNCHRO_TYPE, true, "test/");
		String msg = session.sendCmd("echo hello").getRs_msg();
		System.out.println(msg);
	}
	
//	public void test0000(){
//		PjStepCmdInfo info = new ExecuteAServcie().getCurrentPhase("test_SYS","testhulc2",PROG_TYPE.PUBLISH);
//		System.out.println(info);
//	}
//	
}
