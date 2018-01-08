package com.wk.cd.remote.agent.bean;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.wk.util.JaDate;

/**
 * Created by 12049 on 2017/6/20.
 *
 * 环境变量和子进程工作路径
 */
public class EnvDirInfo {

    private Map<String, String> env;

    private File file_dir;
    
    private JaDate date = JaDate.valueOf(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));

    private byte[] content_bytes;
    
    private String local_dir;
    
    private String remote_dir;

	public byte[] getContent_bytes() {
		return content_bytes;
	}

	public void setContent_bytes(byte[] content_bytes) {
		this.content_bytes = content_bytes;
	}

	public Map<String, String> getEnv() {
        return env;
    }

    public void setEnv(Map<String, String> env) {
        this.env = env;
    }

	public File getFile_dir() {
		return file_dir;
	}

	public void setFile_dir(File file_dir) {
		this.file_dir = file_dir;
	}

	public JaDate getDate() {
		return date;
	}

	public void setDate(JaDate date) {
		this.date = date;
	}

	public String getLocal_dir() {
		return local_dir;
	}

	public void setLocal_dir(String local_dir) {
		this.local_dir = local_dir;
	}

	public String getRemote_dir() {
		return remote_dir;
	}

	public void setRemote_dir(String remote_dir) {
		this.remote_dir = remote_dir;
	}
}
