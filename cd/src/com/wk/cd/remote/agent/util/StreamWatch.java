package com.wk.cd.remote.agent.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StreamWatch extends Thread{
	InputStream is;
	String type;
	List<String> output = new ArrayList<String>();
	boolean debug = false;
	InputStreamReader isr;
	BufferedReader br;
	
	public StreamWatch(InputStream is, String type) {
		this(is, type, false);
	}

	StreamWatch(InputStream is, String type, boolean debug) {
		this.is = is;
		this.type = type;
		this.debug = debug;
	}

	public void run() {
		try {
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				output.add(line + "\n");
				if (debug)
					System.out.println(type + ">" + line);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}finally{
			try {
				br.close();
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public List<String> getOutput() {
		return output;
	}
}
