package com.wk.cd.module1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.module1.entity.Instance;
import com.wk.cd.module1.entity.InstancePhase;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.module1.entity.Script;
import com.wk.cd.module1.ModuleSession;
import com.wk.cd.module1.ProcessManager;
import com.wk.cd.module1.Result;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * 运行上下文
 *
 * 包括：运行环境，运行中的执行结果，当前的运行状态
 */
public class ProcessContext {
 
	private final List<Result> results = new ArrayList<Result>();

	private static final Set<String> sess_ids = new HashSet<String>();

	private volatile int currentStage = -1;

	private volatile int currentStep = -1;

	private volatile Result last_result = null;

	private final Instance instance_info;

	private static Log logger = LogFactory.getLog();
	
	public ProcessContext(Instance instance_info) {
		if(!Assert.isEmpty(instance_info)){
			this.instance_info = copyInstance(instance_info);
		}else{
			this.instance_info = instance_info;
		}
	}

	public void bindSession(ModuleSession sess) {
		sess.setRealDisconnect(false);
		sess_ids.add(sess.getKey());
		ProcessManager.instance.registerModuleSession(sess);
	}

	public void clean() {
		ProcessManager.instance.closeSessByIds(sess_ids);
		if (instance_info != null) {
			ProcessManager.instance.removeProcessInstance(instance_info.getInstance_id());
		}
	}

	public int getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(int currentStage) {
		this.currentStage = currentStage;
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}

	public Instance getInstance_info() {
		return instance_info;
	}

	public Result getLastResult() {
		return last_result;
	}
	
	public List<Result> getResults() {
		return results;
	}

	public InstancePhase getCurrentModuleInfo() {
		List<InstancePhase> list = instance_info.getPhase();
		if(currentStage > list.size()-1){
			throw new ArrayIndexOutOfBoundsException("该项目已经执行到末尾，不能再继续执行");
		}
		InstancePhase mi = list.get(currentStage);
		
		return mi;
	}
	
	public String getCurrentCmd() {
		InstancePhase mi = getCurrentModuleInfo();
		String [] cmds = mi.getScript().getCmds();
		if(currentStep > cmds.length-1){
			throw new ArrayIndexOutOfBoundsException("该阶段已经执行到末尾，不能再继续执行");
		}
		return cmds[currentStep];
	}
	
	public int getCurrentCmdCount() {
		InstancePhase mi = getCurrentModuleInfo();
		String [] cmds = mi.getScript().getCmds();
		return cmds.length;
	}

	public void addResult(Result result) {
		results.add(result);
		last_result = result;
	}
	
	/**
	 * 交互式发送的命令
	 */
	private String cmd;
	public void sendInteractCmd(String cmd){
		this.cmd = cmd;
	}
	
	public String getInteractCmd(){
		return this.cmd;
	}
	
	/**
	 * 拷贝一份带有密文的instance，不然在ProcessManager中替换密文时会连这里的instance中的密文也一起替换
	 * Description: 
	 * @param instance
	 * @return
	 */
	public Instance copyInstance(Instance instance){
		Instance inst = new Instance(instance.getInstance_id());
		inst.setParam_list(instance.getParam_list());
		List<InstancePhase> new_inst_phase_list = new ArrayList<InstancePhase>();
		List<InstancePhase> inst_phase_list = instance.getPhase();
		if(!Assert.isEmpty(inst_phase_list)){
			for(InstancePhase info : inst_phase_list){
				InstancePhase instancePhase = new InstancePhase();
				instancePhase.setImpl_type(info.getImpl_type());
				instancePhase.setModule_source_info(info.getModule_source_info());
				instancePhase.setOriginal_phase_id(info.getOriginal_phase_id());
				instancePhase.setPhase_name(info.getPhase_name());
				instancePhase.setPhase_no(info.getPhase_no());
				Script script = new Script();
				script.setScript_type(info.getScript().getScript_type());
				String[] cmds =  info.getScript().getCmds();
				String[] new_cmds = null;
				
				if(!Assert.isEmpty(cmds)){
					new_cmds = new String[cmds.length];
					for(int i = 0; i < cmds.length; i++){
						new_cmds[i] = cmds[i];
					}
				}
				
				/**
				 * 将每条命令中的密文替换成“******”
				 */
				if(!Assert.isEmpty(inst.getParam_list())){
					for(PhaseParam paramInfo : inst.getParam_list()){
						if(paramInfo.getSensitive_flag() && !Assert.isEmpty(paramInfo.getParam_value())){
							String[] param_array = paramInfo.getParam_value().trim().split(PhaseParam.PARAM_SLIP);
							for(String param_str : param_array){
								if(!Assert.isEmpty(new_cmds)){
									for(int i = 0; i < new_cmds.length; i++){
										new_cmds[i] = new_cmds[i].replace(DESUtil.encrypt(param_str), PhaseParam.PARAM_CIPHERTEXT);
									}
								}
							}
							
						}
					}
				}
				
				script.setCmds(new_cmds);
				instancePhase.setScript(script);
				instancePhase.setSrv_soc(info.getSrv_soc());
				instancePhase.setInteractor_flag(info.isInteractor_flag());
				instancePhase.setParallel_flag(info.isParallel_flag());
				new_inst_phase_list.add(instancePhase);
			}
		}
		inst.addPhases(new_inst_phase_list);
		
		return inst;
	}
}
 
