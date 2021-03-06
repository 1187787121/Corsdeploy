/**
 * Title: PLTFTP.java
 * File Description: 平台FTP功能（APACHE)类
 * @copyright 2015 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 10/20/2015
 */

package com.wk.cd.remote.fp.service;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.exc.CorsManagerSystemErrorException;
import com.wk.cd.exc.DirDeleteFailException;
import com.wk.cd.exc.FileDeleteFailException;
import com.wk.cd.remote.fp.bean.CommandValueBean;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Class Description:平台FTP功能（APACHE)类
 * @author lixl
 */
public class PLTFTP {
	private static final int CONNECT_TIME_OUT = 5000;
	private static final int DATA_TIME_OUT = 300000;
	private String ENCODING = "GBK";
	private static final Log logger = LogFactory.getLog();

	FTPClient fc;
	String ip;
	int port;
	String user;
	String passwd;
	boolean isUTF8 = false;
	boolean isPasv = true;
	int dtimeout = 300000;
	
	PLTFTP(String ip, int port, String user, String passwd, String ftp_encoding,int dtimeout){
		this.fc = new FTPClient();
		this.ip = ip;
		this.port = port;
		this.user = user;
		this.passwd = passwd;
		if(!Assert.isEmpty(ftp_encoding)) {
			this.isUTF8 = "UTF-8".equalsIgnoreCase(ftp_encoding);
		}
		if(dtimeout > 0) {
			this.dtimeout = dtimeout * 1000;
		}
	}

	public FTPClient getFTPClient(){
		return this.fc;
	}

	public boolean connect(){
		boolean isconn = false;
		
		try{
			FTPClientConfig cfg = new FTPClientConfig(FTPClientConfig.SYST_L8);
			cfg.setServerLanguageCode("zh");
			fc.configure(cfg);
			fc.setConnectTimeout(CONNECT_TIME_OUT);
			fc.setDataTimeout(dtimeout);
			fc.connect(ip, port);
			logger.debug("connect repley code=[{}]", fc.getReplyCode());
			if(!FTPReply.isPositiveCompletion(fc.getReplyCode())) {
				fc.disconnect();
			}
			isconn = fc.login(user, passwd);
			logger.debug("login code=[{}]", fc.getReplyCode());
			fc.setFileType(FTPClient.BINARY_FILE_TYPE);
			logger.debug("setFileType code=[{}]", fc.getReplyCode());
			
			fc.sendCommand("SYST");
			logger.debug("SYST code=[{}]", fc.getReplyString());
			fc.sendCommand("FEAT");
			logger.debug("SYST code=[{}]", fc.getReplyString());
			if(isUTF8) {
				ENCODING = "UTF-8";
				FTPReply.isPositiveCompletion(fc.sendCommand("OPTS UTF8", "ON"));
				logger.debug("OPTS UTF8 ON code=[{}]", fc.getReplyCode());
			}
			fc.setControlEncoding(ENCODING);
			logger.debug("setControlEncoding code=[{}]", fc.getReplyCode());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		return isconn;
	}

	public void disconnect(){
		try{
			if(fc.isConnected()) {
				fc.logout();
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw new CorsManagerSystemErrorException("CMS_LOGOUT_ERROR").
				addScene("E", e.getMessage());
		}finally{
			try{
				fc.disconnect();
			}catch(Exception e){
				logger.error(e.getMessage(), e);
				throw new CorsManagerSystemErrorException("CMS_DISCONNECT_ERROR").
					addScene("E", e.getMessage());
			}
		}
	}
	
	/**
	 * cms_list中cmd是表示FTP协议中的原始命令 
	 * 比如OPT UTF8 ON操作中OPT UTF8是命令ON必须是value值 
	 * @param cmd_lst 
	 */
	public void sendCommand(List<CommandValueBean> cmd_lst){
		try{
			int s = cmd_lst.size();
			for(int i=0; i<s; i++) {
				CommandValueBean b = cmd_lst.get(i);
				if(Assert.isEmpty(b.getValue())) {
					fc.sendCommand(b.getCmd());
				}else{
					fc.sendCommand(b.getCmd(), b.getValue());
				}
			}
		}catch(IOException e){
			logger.error(e.getMessage(), e);
			throw new CorsManagerSystemErrorException("CMS_EXE_FTPCOMMAND_ERROR").
				addScene("E", e.getMessage());
		}
	}
	
	public String getReplyString(){
		return fc.getReplyString();
	}

	boolean chdir(String dir){
		boolean isuc = false;
		try{
			isuc=fc.changeWorkingDirectory(dir);
			logger.info("返回状态=[{}],切换工作目录[{}]",isuc,dir);
		}catch(IOException e){
			logger.error(e.getMessage(), e);
		}
		return isuc;
	}
	
	public boolean isExistDir(String dir){
        String wd = null;
        try {
            // 记录当前工作目录，结束创建时回到记录的工作目录
            wd = fc.printWorkingDirectory();
            return chdir(dir);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return false;
        } finally {
            if (Assert.notEmpty(wd)) {
                try {
                    // 回到初始工作目录
                    fc.changeWorkingDirectory(wd);
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
	}
	
	public boolean mkdir(String dir){
        if(Assert.isEmpty(dir)) {
            return true;
        }
        String wd = null;
        try {
            // 记录当前工作目录，结束创建时回到记录的工作目录
            wd = fc.printWorkingDirectory();
            // 目标目录，转换编码，解决中文路径问题
            dir = getISOFname(dir);
            //尝试切入目录
            if(fc.changeWorkingDirectory(dir)) {
                return true;
            }
            String[] arr =  dir.split("/");
            // 如果是绝对路径
            if (dir.startsWith("/")) {
                fc.changeWorkingDirectory("/");
            }
            //循环生成子目录
            for(String s : arr){
                if (Assert.notEmpty(s)) {
                    //尝试切入目录
                    if(fc.changeWorkingDirectory(s)) {
                        continue;
                    } else if(!fc.makeDirectory(s)){
                        logger.info("[失败]ftp创建目录："+s);
                        return false;
                    }
                    fc.changeWorkingDirectory(s);
                    logger.info("[成功]创建ftp目录："+s);
                }
            }
            return true;
        } catch (UnsupportedEncodingException e) {
            logger.info("[失败]ftp创建目录："+dir);
            logger.error(e.getMessage(),e);
        } catch (IOException e) {
            logger.info("[失败]ftp创建目录："+dir);
            logger.error(e.getMessage(),e);
        } finally {
            if (Assert.notEmpty(wd)) {
                try {
                    // 回到初始工作目录
                    fc.changeWorkingDirectory(wd);
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
        return false;
    }
    
	FTPFile getFTPFile(String rfname){
		FTPFile f = null;
		try{
			String rfname_c = getISOFname(rfname);
			FTPFile[] fs = fc.listFiles(rfname_c);
			for(FTPFile file : fs) {
				String uname = getU8FnFromG(file.getName());
				String urfname = getU8FnFromG(file.getName());
				logger.debug("FTPFile=[{}]--rfname=[{}]--fname=[{}]", rfname_c, rfname, file.getName()); 
				if(uname.equals(urfname)) {
					f = file;
					break;
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw new CorsManagerSystemErrorException("CMS_GET_FTPFILE_ERROR").
				addScene("E", e.getMessage());
			}
			return f;
	}
	
	InputStream getFTPFileInputStream(String rfname){
		try{
			String rfname_c = getISOFname(rfname);
			InputStream fin = fc.retrieveFileStream(rfname_c);
			return fin;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw new CorsManagerSystemErrorException("CMS_GET_INPUTSTREAM_ERROR").
			addScene("E", e.getMessage());
		}
	}
	
	OutputStream getFTPFileOutputStream(String rfname){
		try{
			String rfname_c = getISOFname(rfname);
				logger.debug("rfname_c=[{}]--rfname=[{}]", rfname_c, rfname);
				return fc.storeFileStream(rfname_c);
			}catch(Exception e){
				logger.error(e.getMessage(), e);
				throw new CorsManagerSystemErrorException("CMS_GET_FTP_FILEOUTPUT_ERROR").
					addScene("E", e.getMessage());
		}
	}

	void pwd(){
		try{
			fc.pwd();
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw new CorsManagerSystemErrorException("CMS_PWD_ERROR").
				addScene("E", e.getMessage());
		}
	}

	void setCEncoding(String encoding){
		fc.setControlEncoding(encoding);
	}

	boolean isComplete(){
		try{
			return fc.completePendingCommand();
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		return false;
	}


	void setPassiveModel(){
		if(isPasv) {
			fc.enterLocalPassiveMode();
		}
	}

	FTPFile[] listFiles(){
		try{
			return fc.listFiles();
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw new CorsManagerSystemErrorException("CMS_LIST_FILES_ERROR").
				addScene("E", e.getMessage());
		}
	}

	String getISOFname(String rfname_c){
		try{
			if(isUTF8) {
				return new String(rfname_c.getBytes("UTF-8"),"ISO-8859-1");
			}else{
				return new String(rfname_c.getBytes("GBK"),"ISO-8859-1");
			}
		}catch(UnsupportedEncodingException e){
			logger.error(e.getMessage(), e);
			throw new CorsManagerSystemErrorException("CMS_GETISOFANEM_ERROR").
				addScene("E", e.getMessage());
		}
	}

	String getU8FnFromG(String rfname_c){
		return StringUtil.toU8(rfname_c);
	}
	
	//删除远程文件
	boolean deleteFile(String pathname){
		try {
			return fc.deleteFile(pathname);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
			throw new FileDeleteFailException().addScene("FILE", pathname);
		}
	}
	
	// 删除远程目录
	boolean deleteDir(String pathname) {
		try {
			FTPFile[] files = fc.listFiles(pathname);
			for (FTPFile f : files) {
				if (f.isDirectory()) {
					deleteDir(pathname + "/" + f.getName());
					fc.removeDirectory(pathname);
				} else {
					deleteFile(pathname + "/" + f.getName());
				}
			}
			fc.removeDirectory(pathname);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new DirDeleteFailException().addScene("DIR", pathname);
		}
		return true;
	}
}

