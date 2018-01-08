/**
 * Title: CMSGBKConvert.java
 * File Description: GBK文件转换工具类
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 12/11/2014
 */

package com.wk.cd.common.util;
import java.io.File;

import com.wk.cd.exc.CorsManagerSystemErrorException;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.encoding.GBKConvert;

/**
 * Class Description:GBK文件转换工具类
 * @author lixl
 */
public class CMSGBKConvert {
	private static Log logger = LogFactory.getLog();

	/**
	 * 根据field文件进行EBCDIC -> GBK
	 * @author lixl (2014-12-12)
	 * @param src EBCDIC文件
	 * @param dst GBK文件
	 * @param ffile field配置文件
	 * @param hasSep 是否需要添加分隔符
	 * @param sep 分隔符号
	 */
	public static void EBC2GBK(File src, File dst, File ffile, boolean hasSep, char sep){
		try{
			if(!dst.exists()) {
				FileTool.createFile(dst);
			}
			logger.debug("src=[{}] dst=[{}] fld=[{}]", 
						 src.getAbsoluteFile(), dst.getAbsoluteFile(), ffile.getAbsoluteFile());
			logger.debug("has_sep[{}] sep=[{}]", hasSep, sep);
			GBKConvert.as400EBC2GBK(src, dst, ffile, hasSep, sep);
		}catch(Exception e){
			logger.error(e.toString(), e);
			throw new CorsManagerSystemErrorException("CMS_ENCODING_CONVERT_ERROR").addScene("E", src.getName());
		}
	}

	/**
	 * 根据field文件进行EBCDIC -> GBK，添加"，"分隔符
	 * @author lixl (2014-12-12)
	 * @param src EBCDIC文件
	 * @param dst GBK文件
	 * @param ffile field配置文件
	 */
	public static void EBC2GBKDefSep(File src, File dst, File ffile){
		EBC2GBK(src, dst, ffile, true, ',');
	}

	/**
	 * 根据field文件进行GBK -> EBCDIC
	 * @author lixl (2014-12-12)
	 * @param src GBK文件
	 * @param dst EBCDIC文件
	 * @param ffile field配置文件
	 * @param hasSep 是否添加分隔符
	 * @param sep 分隔符号
	 */
	public static void GBK2EBC(File src, File dst, File ffile, boolean hasSep, char sep){
		try{
			if(!dst.exists()) {
				FileTool.createFile(dst);
			}
			logger.debug("src=[{}] dst=[{}] fld=[{}] ", src.getAbsoluteFile(), 
						 dst.getAbsoluteFile(), ffile.getAbsoluteFile());
			logger.debug("has_sep[{}] sep=[{}]", hasSep, sep);
			GBKConvert.GBK2EBCbyline(src, dst, ffile, hasSep, String.valueOf(sep));
		}catch(Exception e){
			logger.error(e.toString(), e);
			throw new CorsManagerSystemErrorException("CSM_ENCODING_CONVERT_ERROR").addScene("E", src.getName());
		}
	}

	/**
	 * 根据field文件进行GBK -> EBCDIC转码
	 * @author lixl (2014-12-12)
	 * @param src GBK文件
	 * @param dst EBCDIC文件
	 * @param ffile field配置文件
	 */
	public static void GBK2EBCDefSep(File src, File dst, File ffile){
		GBK2EBC(src, dst, ffile, true, ',');
	}

}

