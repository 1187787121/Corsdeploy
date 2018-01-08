package com.wk.cd.module1.impl;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.remote.bean.AsyncMsgBean;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.remote.sh.service.AbstractRCallService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * 配置文件 Module实现 Class Description:
 * 
 * @author 12049
 */
public class Config extends MultiStepModule {

	protected static final Log logger = LogFactory.getLog();

	private ModuleSourceInfo ftp_dt_info;

	private ModuleSourceInfo shell_dt_info;

	protected ConfigSession config_sess;

	protected boolean connected;

	public Config(String[] cmds) {
		super(cmds);
	}

	public Config(String[] cmds, List<ModuleSourceInfo> module_soc_list) {
		super(cmds);
		if(Assert.notEmpty(module_soc_list)){
			this.ftp_dt_info = module_soc_list.get(0);
			this.shell_dt_info = module_soc_list.get(1);
		}
	}

	public boolean isConnected() {
		return connected;
	}

	@Override
	public Result runModule(String remote_relative_dir) {

		Result result = null;
		CMD_STATUS status = null;
		StringBuffer msg = new StringBuffer();
		StringBuffer error_msg = new StringBuffer();
		if(Assert.notEmpty(cmds)){
			for (String cmd : cmds) {
				result = uploadCfgFile(cmd);
				status = result.getStatus();
				if (status != CMD_STATUS.SUCCEED) {
					logger.debug("ftp execute error out");
					msg.append(result.getMsg() + "\n");
					error_msg.append(result.getError_msg());
					break;
				}
				
				result = checkCsumCfgFile(cmd, remote_relative_dir);
				
				if (status != CMD_STATUS.SUCCEED) {
					logger.debug("shell execute error out");
					msg.append(result.getMsg() + "\n");
					error_msg.append(result.getError_msg());
					break;
				}
			}

			return result;
		}else{
			long start_time = System.currentTimeMillis();
			return new Result(CMD_STATUS.SUCCEED, "执行成功", start_time);
		}
		
	}

	/**
	 * 上传配置文件 Description:
	 * 
	 * @return
	 */
	private Result uploadCfgFile(String cmd) {
		logger.info("uploadCfgFile cmd = [{}]", cmd);
		String[] cmd_args = cmd.split(";");
		String file = "put " + cmd_args[0] + " " + cmd_args[1];
		String file_csum = "put " + cmd_args[0] + ".csum " + cmd_args[1]
				+ ".csum";
		String[] ftp_cmds = { file, file_csum };

		long start_time = System.currentTimeMillis();
		StringBuffer msg = new StringBuffer();
		StringBuffer error_msg = new StringBuffer();
		CMD_STATUS status = null;
		for (int i = 0; i < ftp_cmds.length; i++) {

			Result result = ftpStep(ftp_cmds[i]);
			status = result.getStatus();
			if (status != CMD_STATUS.SUCCEED) {
				logger.debug("ftp execute error out");
				msg.append(result.getMsg() + "\n");
				error_msg.append(result.getError_msg());
				break;
			}
			if (!Assert.isEmpty(result.getMsg())) {
				msg.append(result.getMsg() + "\n");
			}

		}
		Result result = new Result(status, msg.toString(), start_time);
		result.setError_msg(error_msg.toString());
		return result;
	}

	public Result ftpStep(String cmd) {
		long start_time = System.currentTimeMillis();
		DtSourceInfo dsi = ftp_dt_info.getDt_source_info();
		if (config_sess == null) {
			try {
				config_sess = new ConfigSession(step_count, ftp_dt_info, IMPL_TYPE.FTP, null);
				config_sess.connect();
			} catch (Throwable t) {
				logger.error("连接数据源[{}]异常", dsi.getSoc_name(), t);
				return new Result(t, start_time);
			}

			if (ctx != null) {
				ctx.bindSession(config_sess);
			}
		}

		logger.debug("ftp send cmd [{}]", cmd);
		ShExecRsBean reply = config_sess.sendCmd(cmd);
		CMD_STATUS status = reply.getIs_succ() ? CMD_STATUS.SUCCEED
				: CMD_STATUS.ERROR;
		String msg = reply.getIs_succ() ? reply.getRs_msg() : reply
				.getErr_msg();
		logger.debug("命令执行结果信息：{}", msg);
		logger.debug("命令输出：{}\n{}", reply.getIs_succ(), msg);

		return new Result(status, msg, start_time);
	}

	/**
	 * 校验配置文件和备份配置文件 Description:
	 * 
	 * @return
	 */
	private Result checkCsumCfgFile(String cmd, String remote_relative_dir) {
		logger.info("checkCsumCfgFile cmd = [{}]", cmd);
		
		String[] cmd_args = cmd.split(";");
		String[] check_csum = new String[4];
		check_csum[0] = "a=$a`cat " + cmd_args[1] + ".csum`";
		check_csum[1] = "b=$b`cksum " + cmd_args[1] + " | awk '{print $1$2}'`";
		check_csum[2] = "if [ \"$a\" == \"$b\" ]; then echo \"upload success\"; else echo \"upload failed\"; fi";
		check_csum[3] = "rm " + cmd_args[1] + ".csum";
		

		Shell module = new Shell(shell_dt_info, check_csum);
		Result result = module.runModule(remote_relative_dir);
		return result;
	}

	/**
	 * 备份文件
	 * Description: 
	 * @param cmd
	 * @param remote_relative_dir
	 * @return
	 */
	private Result backupFile(String cmd, String remote_relative_dir){
		logger.info("backupFile cmd = [{}]", cmd);
		String backup_dir = "";
		if(Assert.notEmpty(CfgTool.getProjectPropterty("cms.config.backup.dir"))){
			backup_dir = getFilePath(CfgTool.getProjectPropterty("cms.config.backup.dir"));
		}else{
			backup_dir = "./backup";
		}
		
		String[] cmd_args = cmd.split(";");
		String[] check_csum = new String[4];
		check_csum[0] = "export dt=`date '+%Y%m%d-%H%M%S'`";
		check_csum[1] = "export tf="+ backup_dir + "/" + getFileName(cmd_args[1]) +"_config.tar";
		check_csum[2] = "if [ -f \"$tf\" ]; then tf="+backup_dir+"/"+getFileName(cmd_args[1])+"_config_$dt.tar; fi";
		check_csum[3] = "if [ -f " + cmd_args[1]
				+ " ]; then if [ ! -f \"$tf\" ]; then tar -cf $tf "
				+ cmd_args[1] + "; else tar -rf $tf " + cmd_args[1]
				+ ";fi; fi;";
		
		Shell module = new Shell(shell_dt_info, check_csum);
		Result result = module.runModule(remote_relative_dir);
		return result;
	}
	public Result shellModule(String[] cmds, String remote_relative_dir){
		long start_time = System.currentTimeMillis();
		DtSourceInfo dsi = this.shell_dt_info.getDt_source_info();
		if (config_sess == null) {
			try {
				config_sess = new ConfigSession(step_count, this.shell_dt_info, IMPL_TYPE.SHELL, remote_relative_dir);
				config_sess.connect();
				
			} catch (Throwable t) {
				logger.error("连接数据源[{}]异常", dsi.getSoc_name(), t);
				return new Result(t,start_time);
			}

			if (ctx != null) {
				ctx.bindSession(config_sess);
			}
			connected = true;
		}
		try {
			ShExecRsBean reply = config_sess.sendCmds(cmds, remote_relative_dir);
			CMD_STATUS status = reply.getIs_succ() ? CMD_STATUS.SUCCEED : CMD_STATUS.ERROR;
			String msg = reply.getRs_msg();
			if(!Assert.isEmpty(msg) && msg.endsWith(AbstractRCallService.CLOSE_MSG)){
				status = CMD_STATUS.STOP;
				Result result = new Result(status, msg,start_time);
				result.setError_msg(reply.getErr_msg());
				return result;
			}
			logger.debug("命令执行结果信息：{}", msg);
			logger.debug("命令输出：{}\n{}", reply.getIs_succ(), msg);
			
			Result result = new Result(status, msg,start_time);
			result.setError_msg(reply.getErr_msg());
			return result;
		} catch (Throwable t) {
			logger.error("执行[{}]异常", t);
			return new Result(t,start_time);
		}finally{
			if(this.config_sess != null){
				this.config_sess.disconnect();
			}
			connected = false;
			this.config_sess = null;
		}
	}
	
	

	@Override
	public void interactRun(String remote_relative_dir) {

	}

	@Override
	public void sendInteractCmd(String cmd, boolean sensitive_flag) {

	}

	@Override
	public AsyncMsgBean getInteractMsg() {
		return null;
	}

	@Override
	public IMPL_TYPE getImplType() {
		return null;
	}

	@Override
	public void sessionClose() {
		 if (this.config_sess != null) {
	            this.config_sess.forceDisconnect();
	     }
	}

	@Override
	public Result stepinto(int step) {

		return null;
	}

	public String getFileName(String filePath){
		//带后缀的文件名
		String _file = FileTool.getFileName(filePath);
		//去掉文件名后缀
		int index = _file.indexOf(".");
		if (index > 0) {
			return _file.substring(0, index);
		} else {
			return _file;
		}
	}
	
	public static String getFilePath(String filePath){
		StringBuffer sb = new StringBuffer();
		String _filePath = FileTool.filePathCvt(filePath);
		int index = _filePath.indexOf("./");
		if(index < 0){
			sb.append("./").append(_filePath);
		}else{
			sb.append(_filePath);
		}
		
		if(sb.toString().endsWith("/")){
			return sb.toString().substring(0, sb.toString().length() - 1);
		}
		
		return sb.toString();
	}
}
