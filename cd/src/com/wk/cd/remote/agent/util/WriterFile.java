package com.wk.cd.remote.agent.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * 创建并存储文件 Class Description:
 * 
 * @author 12049
 */
public class WriterFile {

	private static final Log logger = LogFactory.getLog();
	public String filepath = null;
	public String filename = null;
	public String fileContent = null;
	public Boolean success;

	// 创建文件路径
	public void SetFilePath(String s) {
		filepath = s;
		try {
			File path = new File(filepath);
			path.mkdirs();

		} catch (Exception ex) {

		}
	}

	// 获取文件路径
	public String GetFilepath() {
		return filepath;
	}

	// 设置文件名称
	public void SetFileName(String strname) {
		filename = strname;
	}

	// 获取文件名称
	public String GetFileName() {
		return filename;
	}

	// 是否成功
	public Boolean IsSuccess() {
		return success;
	}

	// 设置文件内容
	public void SetFileContent(String strContent, String encoding) {
		String lineSeparator = System.getProperty("line.separator", "/n"); 
		
		if(filename.endsWith("bat")){
			fileContent = dealStrForBat(strContent.replace("\n", lineSeparator));
		}else{
			fileContent = strContent.replace("\n", lineSeparator);
		}
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			File file = new File(filepath, filename);
			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos, encoding);
			bw = new BufferedWriter(osw);
			bw.write(fileContent, 0, fileContent.length());
			success = true;
		} catch (Exception ex) {
			success = false;
		}finally{
			try {
				if(bw != null){
					bw.close();
				}
				if(osw != null){
					osw.close();
				}
				if(fos != null){
					fos.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	
	public String dealStrForBat(String content){
		if(content.contains("&&")){
			return content;
		}
		
		String lineSeparator = System.getProperty("line.separator", "/n"); 
		String[] cmds = content.split(lineSeparator);
		for (int i = 0; i < cmds.length; i++){
			String str = cmds[i];
			
			if(str.endsWith(".exe") || str.endsWith(".bat") || str.endsWith(".so") || str.endsWith(".dll")){
				if(i == cmds.length - 1){
					cmds[i] = str;
				}else{
					cmds[i] = str + "&& ";
				}
				
			}else{
				cmds[i] = str + lineSeparator;
			}
		}
		
		StringBuffer sb = new StringBuffer();
		for (String str : cmds){
			sb.append(str);
		}
		return sb.toString();
	}
}
