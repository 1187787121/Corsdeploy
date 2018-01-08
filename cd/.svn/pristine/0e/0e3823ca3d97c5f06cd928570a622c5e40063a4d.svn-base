/**
 * Title: ReadStringService.java
 * File Description: 文件读写服务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年2月29日
 */
package com.wk.cd.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wk.cd.exc.FileNotExistException;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.Base64;
import com.wk.util.FileUtil;
import com.wk.util.StringUtil;

/**
 * Class Description: 文件读写服务
 * @author Xul
 */
public class FileStringService {
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 读取文件服务
	 * @param full_path
	 * @param encoding
	 * @return 字符串列表：第一个为文件内容，第二个为系统类型
	 */
	public List<String> readFileToString(String full_path, String encoding) {
		logger.info("***********readFileToString begin***********");
		// 获得配置文件
		File file = new File(full_path);
		// 按行读取配置文件，并存入字符串数组
		List<String> list = new ArrayList<String>();
		byte[] files = FileUtil.readFileImage(file);
		String str = "";
		String system = "";
		try {
			str = new String(files, StringUtil.isEmpty(encoding) ? "UTF-8" : encoding);
			// 根据文件中是否有\r判断文件所在系统
			if (str.contains("\r")) {
				system = "DOS";
			} else {
				system = "UNIX";
			}
			// //根据标志判断是否去掉文件中的\r
			// if(rflag){
			// str = str.replaceAll("\r", "");
			// }
			byte[] b = str.getBytes("UTF-8");
			str = new String(Base64.encode(b), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new com.wk.cd.exc.UnsupportedEncodingException();
		}
		list.add(str);
		list.add(system);
		logger.info("***********readFileToString end***********");
		return list;
	}

	/**
	 * Description: 读取文件服务
	 * @param full_path
	 * @param encoding
	 * @return 字符串列表：第一个为文件内容，第二个为系统类型
	 */
	public String readFileToStr(String full_path, String encoding) {
		logger.info("***********readFileToString begin***********");
		// 获得配置文件
		File file = new File(full_path);
		// 按行读取配置文件，并存入字符串数组
		byte[] files = FileUtil.readFileImage(file);
		String str = "";
		try {
			str = new String(files, StringUtil.isEmpty(encoding) ? "UTF-8" : encoding);
		} catch (UnsupportedEncodingException e) {
			throw new com.wk.cd.exc.UnsupportedEncodingException();
		}
		logger.info("***********readFileToString end***********");
		return str;
	}

	public void writeFile(String config_file, String full_path, String encoding, String system) {
		logger.info("***********writeFile begin***********");
		// 获得转化标志
		boolean rflag = "DOS".equals(StringUtil.isEmpty(system) ? "DOS" : system);
		// 新建输出文件
		File file = new File(full_path);
		OutputStreamWriter out = null;
		try {
			FileOutputStream os = new FileOutputStream(file);
			out = new OutputStreamWriter(os);
			String final_str = "";
			// 若字符串不为空
			if (!Assert.isEmpty(config_file)) {
				// 根据标志判断是否去掉文件中的\r
				if (rflag) {
					config_file = config_file.replaceAll("\r", "");
				}
				byte[] b = Base64.decode(config_file);
				final_str = new String(b, "UTF-8");
			}
			// 将字符串按传入字符集转为byte数组
			byte[] b_ = final_str.getBytes(StringUtil.isEmpty(encoding) ? "UTF-8" : encoding);
			// 将byte数组写入文件
			FileUtil.writeFileImage(b_, file);
			out.flush();
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			throw new com.wk.cd.exc.UnsupportedEncodingException();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FileNotExistException().addScene("FILE", file);
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				throw new FileNotExistException().addScene("FILE", file);
			}
		}
		logger.info("***********writeFile end***********");
		return;
	}

	public void writeFile(String[] config_file, String full_path) {
		logger.info("***********writeFile begin***********");
		// 新建输出文件
		File file = new File(full_path);
		OutputStreamWriter out = null;
		try {
			FileOutputStream os = new FileOutputStream(file);
			out = new OutputStreamWriter(os);
			// 将字符串数组按行写入文件
			for (String str : config_file) {
				FileUtil.writeln(out, str);
			}
			out.flush();
		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw new FileNotExistException().addScene("FILE", file);
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				throw new FileNotExistException().addScene("FILE", file);
			}
		}
		logger.info("***********writeFile end***********");
		return;
	}

	/**
	 * Description:
	 * @param file_content
	 * @return
	 */
	public String encryStrBase64(String file_content) {
		if (!Assert.isEmpty(file_content)) {
			try {
				byte[] b = file_content.getBytes("UTF-8");
				return new String(Base64.encode(b), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new com.wk.cd.exc.UnsupportedEncodingException();
			}
		}
		return "";
	}

	/**
	 * Description:
	 * @param file_content
	 * @param keyString
	 * @param keyNum
	 * @return
	 */
	public List<String> dealConfigFileStr(String file_content) {
		List<String> ct_list = new ArrayList<String>();
		if (!Assert.isEmpty(file_content)) {
			ct_list = splitStr(file_content);
			logger.debug("截取之后的日志文件：[{}]", ct_list.toString());
		}
		return ct_list;
	}

	/**
	 * Description:
	 * @param file_content
	 * @return
	 */
	private List<String> splitStr(String file_content) {
		String[] str = file_content.split("--");
		return Arrays.asList(str);
	}

	private List<String> splitStrWithR(String file_content) {
		String[] str = file_content.split("\n");
		return Arrays.asList(str);
	}

	/**
	 * Description: 得到文本中字体的重复率，取值以文本长度的行为标准默认大于50%为高
	 * @param file_content
	 * @param keyString
	 * @return
	 */
	private boolean repetitionRate(List<String> str_splitr, String keyString) {
		boolean mark_do = false;
		int mark = 0;
		int size = str_splitr.size();
		int low_line = size / 2;
		if(size>1000){
		    low_line = size / 10;
		}
		for (int i = 0; i < size; i++) {
			if (str_splitr.get(i).contains(keyString)) {
				mark++;
			}
		}
		if (mark >= low_line) {
			mark_do = true;
		}
		return mark_do;
	}

	/**
	 * Description: 解析节点Log文件
	 * @param file_content
	 * @param keyString
	 * @param keyNum
	 * @return
	 */
	public List<String> dealConfigNodeStr(String file_content, String keyString, int keyNum) {
		 
		List<String> log_list = new ArrayList<String>();
		if (!Assert.isEmpty(file_content)) {
			List<String> str_splitr = new ArrayList<String>(splitStrWithR(file_content));
			int size = str_splitr.size();
			String lastString = str_splitr.get(size - 1);
			if (keyNum == 0) {
				for (int i = 0; i < size; i++) {
					if (str_splitr.get(i).contains(keyString)) {
						log_list.add(str_splitr.get(i));
					}
				}
			} else {
				if (repetitionRate(str_splitr, keyString)) {
					/*int offset = keyNum * 2 + 1;
					int range = size / offset;
					int end_mark = 0;
					for (int i = 0; i < range; i++) {
						end_mark = offset * (i + 1) + 1;
						if (end_mark > size) {
							end_mark = size;
						}
						log_list.add(combineNodeConfig(str_splitr.subList(i * offset, end_mark), ""));
					}
					if (end_mark < size) {
						log_list.add(combineNodeConfig(str_splitr.subList(end_mark, size), lastString));
					}*/
					int offset = keyNum * 2 + 1;
					if(offset>size){
						log_list.add(combineNodeConfig(str_splitr,""));
					}else{
						int range = size / offset;
						for (int i = 0; i < range; i++) {
							log_list.add(combineNodeConfig(str_splitr.subList(0, offset), ""));
							str_splitr.subList(0, offset).clear();
						}
						if(!Assert.isEmpty(str_splitr)){
							log_list.add(combineNodeConfig(str_splitr,""));
						}
					}
				} else {
					for (int i = 0; i < size; i++) {
						if (str_splitr.get(i).contains(keyString)) {
							List<String> subList = null;
							int fromIndex = i - keyNum;
							int toIndex = i + keyNum;
							if (fromIndex < 0) {
								fromIndex = 0;
							}
							if (toIndex > size - 1) {
								toIndex = size - 1;
								subList = new ArrayList<String>(str_splitr.subList(fromIndex, toIndex));
								log_list.add(combineNodeConfig(subList, lastString));
								subList.clear();
							} else {
								subList = new ArrayList<String>(str_splitr.subList(fromIndex, toIndex));
								log_list.add(combineNodeConfig(subList, str_splitr.get(toIndex)));
								subList.clear();
							}
						}
					}
				}
			}
		}
		return log_list;
	}

	private String combineNodeConfig(List<String> strings2, String lastString) {
		StringBuffer sBuffer = new StringBuffer(1000);
		if (!Assert.isEmpty(strings2)) {
			for (String string : strings2) {
				sBuffer.append(string + "\n");
			}
			sBuffer.append(lastString);
		}
		return sBuffer.toString();
	}
}
