package com.wk.cd.remote.agent.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * 读取进程中数据源 Class Description:
 * 
 * @author 12049
 */
public class StreamReader extends Thread {

	private static final Log logger = LogFactory.getLog();

	private StringBuffer output = new StringBuffer();

	private InputStream is;

	private InputStreamReader isr;

	private BufferedReader br;
	
	private Process proc;

	private int exitStatus = -1;
	
	public StreamReader(InputStream is, Process proc) {
		this.is = is;
		this.proc = proc;
	}

	@Override
	public void run() {
		try {
			if(AgentHelperUtil.isWindowsOS()){
				isr = new InputStreamReader(is, "GBK");
			}else{
				isr = new InputStreamReader(is, "UTF-8");
			}
			
			br = new BufferedReader(isr);
			int count = 0;

			while (( count = br.read()) != -1) {
				output.append((char) count);
				logger.debug("output = {}", output.toString() + output.toString().length());
			}
			
			if(proc != null){
				exitStatus = proc.waitFor();
			}
		} catch (IOException ioe) {
			logger.error(ioe.toString(), ioe);
		} catch (InterruptedException e) {
			logger.error(e.toString(), e);
		}
	}

	public StringBuffer getOutput() {
		logger.debug("output2 = {}", output.toString());
		return output;
	}

	public void setOutput(StringBuffer output) {
		this.output = output;
	}

	/**
	 * @return proc
	 */
	public Process getProc() {
		return proc;
	}

	/**
	 * @param proc
	 */
	public void setProc(Process proc) {
		this.proc = proc;
	}
	
	public int getExitStatus(){
		return exitStatus;
	}
}
