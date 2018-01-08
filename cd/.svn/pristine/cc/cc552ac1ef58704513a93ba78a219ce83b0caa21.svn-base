package com.wk.cd.remote.sh.service;

import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.remote.bean.AsyncMsgBean;

import java.io.IOException;

public abstract interface RSession {
	public abstract void send(String paramString) throws IOException;

	public abstract String getReplyUntilPrompt(String paramString)
			throws IOException;

	public abstract String getReplyUntilPrompt(String[] paramArrayOfString)
			throws IOException;

	public abstract int getExitStatus() throws IOException;

	public abstract ShExecRsBean sendCmd(String paramString);

	void asyncRunStage(String[] cmds,boolean is_read, boolean sensitive_flag) throws IOException;
	
	AsyncMsgBean asyncRunMsg();
	public abstract void disconnect();
}