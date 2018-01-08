package com.wk.cd.module1.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wk.cd.module1.Module;
import com.wk.cd.module1.ModuleInterceptor;
import com.wk.cd.module1.MultiResult;
import com.wk.cd.module1.Process;
import com.wk.cd.module1.ProcessContext;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.entity.Instance;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.module1.impl.StepEnabled;
import com.wk.cd.remote.bean.AsyncMsgBean;
import com.wk.lang.SystemException;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * 流程缺省实现 按阶段和步骤顺序执行，提供拦截器功能扩展
 */
public class DefaultProcess
		implements Process {

	private static final Log logger = LogFactory.getLog();

	protected final List<Module> modules = new ArrayList<Module>();

	protected final List<ModuleInterceptor> interceptors = new ArrayList<ModuleInterceptor>();

	protected final Instance inst_info;

	protected final ProcessContext ctx;

	public DefaultProcess(Instance inst_info) {
		this.inst_info = inst_info;
		this.ctx = new ProcessContext(inst_info);
	}

	public void addModule(Module module) {
		modules.add(module);
	}

	public void addInterceptor(ModuleInterceptor interceptor) {
		if (interceptor != null) {
			interceptors.add(interceptor);
		}
	}

	public void removeInterceptor(ModuleInterceptor interceptor) {
		for (Iterator<ModuleInterceptor> it = interceptors.iterator(); it.hasNext();) {
			ModuleInterceptor i = it.next();
			if (i == interceptor) {
				it.remove();
			}
		}
	}

	/**
	 * @see com.wk.cd.module1.Process#getCtx()
	 */
	public ProcessContext getCtx() {
		return ctx;
	}

	/**
	 * @see com.wk.cd.module1.Process#run(int)
	 */
	public Result run(int stage) {
		Module module = modules.get(stage);
		if (module.isStepEnabled()) {
			MultiResult result = new MultiResult();
			for (int i = 0; i < ((StepEnabled) module).getStepCount(); i++) {
				Result step_result = stepInto(stage, i);
				result.addResult(step_result);
				if (CMD_STATUS.SUCCEED != result.getStatus()) {
					break;
				}
			}
			return result;
		} else {
			before(stage, -1);

			module.setCtx(ctx);
			Result result = module.run();

			after(result, false, stage, -1);
			return result;
		}
	}

	/**
	 * Description: 阶段执行 这个阶段执行是把所有的脚本信息存放到.sh文件中， 发送到远程机器上 然后执行文件，
	 * 执行过程中记录这个sh文件pid便于关闭，执行结束后删除sh文件
	 * @param stage 注意 这个stage是从0开始的
	 * @return
	 */
	@Override
	public Result runStage(String remote_relative_dir,int stage, boolean skip_flag) {
		
		Module module = modules.get(stage);
		beforeRunStage(stage);
		Result result = null;
		if (skip_flag) {
			logger.debug("current execute skip [{}]",stage+1);
			result = new Result(CMD_STATUS.SKIP, "执行跳过", System.currentTimeMillis());
		} else {
			logger.debug("current execute stage[{}] begin with 1",stage+1);
			result = module.runModule(remote_relative_dir);
			logger.debug("current execute result[{}],msg[{}]",result.getStatus(),result.getMsg());
		}
		afterRunStage(result, stage);
		return result;
	}

	private boolean isLastStage(int stage) {
		return stage == modules.size() - 1;
	}

	/**
	 * @see com.wk.cd.module1.Process#stepInto(int, int)
	 */
	public Result stepInto(int stage, int step) {
		before(stage, step);
		Module module = modules.get(stage);
		module.setCtx(ctx);
		Result result = module.stepinto(step);
		result.setPhase_no(stage);
		result.setStep_no(step);
		after(result, module.isLastStep(step), stage, step);

		return result;
	}

	/**
	 * Description:
	 * @param stage
	 */
	@Override
	public Result interactRun(String remote_relative_dir,int stage) {
		Module module = modules.get(stage);
		//需要在此处加上日志的写入
		beforeRunStage(stage);
		IMPL_TYPE type = module.getImplType();
		if (!(type == IMPL_TYPE.PYTHON2 || type == IMPL_TYPE.PYTHON3
				||type == IMPL_TYPE.SHELL )) {
			Throwable t = new Throwable(type.getCname() +"不支持交互执行");
			Result result = new Result(t,System.currentTimeMillis());
			return result;
		}
		logger.debug("send interact cmds start");
		module.interactRun(remote_relative_dir);
		logger.debug("send interact cmds success");
		Result result = new Result();
		result.setInteract_flag(true);
		return result;

	}

	/**
	 * 交互式中间发送命命令
	 * Description:
	 * @param stage
	 * @param cmd
	 */
	@Override
	public void sendInteractCmd(int stage, String cmd, boolean sensitive_flag) {
		Module module = modules.get(stage);
		module.sendInteractCmd(cmd, sensitive_flag);

		//执行日志密文
		if(sensitive_flag){
			ctx.sendInteractCmd(PhaseParam.PARAM_CIPHERTEXT);
		}else{
			ctx.sendInteractCmd(cmd);
		}
		
		for (ModuleInterceptor inter : interceptors) {
			inter.sendInteractCmd(ctx);
		}
	}

	/**
	 * 交互式获取执行信息
	 * Description:
	 * @param stage
	 * @return
	 */
	@Override
	public AsyncMsgBean getInteractMsg(int stage) {
		Module module = modules.get(stage);
		AsyncMsgBean msg =  module.getInteractMsg();
		
		if (msg.isEnd_flag()) {
			Result result = new Result(CMD_STATUS.SUCCEED, msg.getMsg(), System.currentTimeMillis());
			afterRunStage(result, stage);
		}else{
			if(!Assert.isEmpty(msg) && !Assert.isEmpty(msg.getMsg())){
				Result result = new Result();
				result.setMsg(msg.getMsg());
				ctx.addResult(result);
				for (ModuleInterceptor inter : interceptors) {
					inter.getInteractMsg(ctx);
				}
			}
		}
		
		logger.debug("Default Process AsyncMsgBean[{}]",msg);
		return msg;
	}

	@Override
	public Result skip(int stage) {
		long start_time = System.currentTimeMillis();
		before(stage, -1);

		for (ModuleInterceptor inter : interceptors) {
			inter.skip(ctx);
		}

		Result result = new Result(CMD_STATUS.SKIP, "执行跳过", start_time);
		result.setPhase_no(stage);
		after(result, false, stage, -1);
		return result;
	}

	@Override
	public Result skipStep(int stage, int step) {
		long start_time = System.currentTimeMillis();
		before(stage, step);

		for (ModuleInterceptor inter : interceptors) {
			inter.skip(ctx);
		}
		Module module = modules.get(stage);
		Result result = new Result(CMD_STATUS.SKIP, "执行跳过", start_time);
		result.setPhase_no(stage);
		result.setStep_no(step);
		after(result, module.isLastStep(step), stage, step);
		return result;
	}

	private void beforeRunStage(int stage) {
		logger.debug("curren: {} - {}", ctx.getCurrentStage(), ctx.getCurrentStep());
		logger.debug("run before: {} - {}", stage);
		ctx.setCurrentStage(stage);

		checkStage(stage);
		if (stage == 0) {
			logger.debug("before start");
			for (ModuleInterceptor inter : interceptors) {
				inter.beforeStart(ctx);
			}
		}
		logger.debug("before run");
		for (ModuleInterceptor inter : interceptors) {
			inter.beforeRun(ctx);
		}

	}

	private void afterRunStage(Result result, int stage) {
		logger.debug("curren: {} - {}", ctx.getCurrentStage());
		checkStage(stage);
		ctx.addResult(result);
		logger.debug("result: {} - {}", result.getStatus(), result.getMsg());
		logger.debug("after run");
		for (ModuleInterceptor inter : interceptors) {
			inter.afterRun(ctx);
		}
		if (isLastStage(stage)) {
			reachEnd();
		}
		logger.debug("run after: {} ", stage);
	}

	private void before(int stage, int step) {
		logger.debug("curren: {} - {}", ctx.getCurrentStage(), ctx.getCurrentStep());
		logger.debug("run before: {} - {}", stage, step);
		ctx.setCurrentStage(stage);
		ctx.setCurrentStep(step);

		checkStageStep(stage, step);

		if (step < 0) {
			if (stage == 0) {
				logger.debug("before start");
				for (ModuleInterceptor inter : interceptors) {
					inter.beforeStart(ctx);
				}
			}
			logger.debug("before run");
			for (ModuleInterceptor inter : interceptors) {
				inter.beforeRun(ctx);
			}
		} else {
			if (step == 0) {
				if (stage == 0) {
					logger.debug("before start");
					for (ModuleInterceptor inter : interceptors) {
						inter.beforeStart(ctx);
					}
				}
				logger.debug("before run");
				for (ModuleInterceptor inter : interceptors) {
					inter.beforeRun(ctx);
				}
			}
			logger.debug("before step into");
			for (ModuleInterceptor inter : interceptors) {
				inter.beforeStepinto(ctx);
			}
		}
	}

	private void after(Result result, boolean is_last_step, int stage, int step) {
		logger.debug("curren: {} - {}", ctx.getCurrentStage(), ctx.getCurrentStep());

		checkStageStep(stage, step);

		ctx.addResult(result);

		logger.debug("result: {} - {}", result.getStatus(), result.getMsg());

		if (step < 0) {
			logger.debug("after run");
			for (ModuleInterceptor inter : interceptors) {
				inter.afterRun(ctx);
			}
			if (isLastStage(stage)) {
				reachEnd();
			}
		} else {
			logger.debug("after step into");
			for (ModuleInterceptor inter : interceptors) {
				inter.afterStepinto(ctx);
			}
			if (is_last_step) {
				logger.debug("after run");
				for (ModuleInterceptor inter : interceptors) {
					inter.afterRun(ctx);
				}
				if (isLastStage(stage)) {
					reachEnd();
				}
			}
		}
		logger.debug("run after: {} - {}", stage, step);
	}

	private void reachEnd() {
		logger.debug("after end");
		for (ModuleInterceptor inter : interceptors) {
			inter.afterEnd(ctx);
		}
		ctx.clean();
	}

	private void checkStageStep(int stage, int step) {
		if (stage < 0 || stage >= modules.size()) {
			throw new SystemException("SYS_MODULE_INVALID_STAGE").addScene("Stage", stage);
		}
		if (step >= 0) {
			Module module = modules.get(stage);
			if (step < 0 || step >= ((StepEnabled) module).getStepCount()) {
				throw new SystemException("SYS_MODULE_INVALID_STEP").addScene("Stage", stage)
						.addScene("Step", step);
			}
		}
	}

	private void checkStage(int stage) {
		if (stage < 0 || stage >= modules.size()) {
			throw new SystemException("SYS_MODULE_INVALID_STAGE").addScene("Stage", stage);
		}
	}

	/**
	 * Description:
	 */
	@Override
	public Result close(int stage) {
		Result result = null;
		if (!Assert.isEmpty(modules) && modules.size() != 0) {
			Module module = modules.get(stage);
			module.setCtx(ctx);
			module.sessionClose();
			result = new Result(CMD_STATUS.STOP, "执行停止", System.currentTimeMillis());
		}
		return result;

	}

}
