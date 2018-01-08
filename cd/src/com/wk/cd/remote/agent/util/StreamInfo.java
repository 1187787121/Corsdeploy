package com.wk.cd.remote.agent.util;

import java.io.InputStream;
import java.io.OutputStream;

public class StreamInfo {

	private InputStream is;
	
	private InputStream eis;
	
	private OutputStream os;

	private StreamReader srin;
	
	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	public InputStream getEis() {
		return eis;
	}

	public void setEis(InputStream eis) {
		this.eis = eis;
	}

	public OutputStream getOs() {
		return os;
	}

	public void setOs(OutputStream os) {
		this.os = os;
	}

	public StreamReader getSrin() {
		return srin;
	}

	public void setSrin(StreamReader srin) {
		this.srin = srin;
	}
	
}
