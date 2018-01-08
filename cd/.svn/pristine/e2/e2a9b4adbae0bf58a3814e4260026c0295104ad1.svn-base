/**
 *  Title: CheckFileService.java
 * File Description:校验上线清单和上线包是否匹配
 * @copyright: 2015
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2015年12月1日
 */
package com.wk.cd.build.ea.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

import com.wk.cd.build.ea.bean.FileDetailBean;
import com.wk.cd.build.ea.bean.UpdateListBean;
import com.wk.cd.build.exc.AddPathIsNotFullPathException;
import com.wk.cd.build.exc.TpTemplateTarListDifferentException;
import com.wk.cd.build.exc.TpTemplateTarListExcludeException;
import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.ExcelFileIOException;
import com.wk.cd.module1.exc.FileListBooleanException;
import com.wk.cd.module1.exc.FileListTitleException;
import com.wk.cd.module1.exc.TarExtractFailedExcepitionException;
import com.wk.cd.module1.exc.TarExtractFailedForFileNotFoundExcepitionException;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 校验上线清单和上线包是否匹配
 * @author "Zhangj"
 */
public class FileListService {
	private static final Log logger = LogFactory.getLog();

	private final String SPECIAL_FILE_SHEET = "文件名列表";

	private final String FILE_NAME = "文件名";

	private final String ALIAS_FILE_NAME = "别名";

	private final String IS_TAR = "是否打包文件";

	private final String IS_CHECK = "是否校验";

	private final String SINGLE_FILE_TABLE = "单个文件列表";

	private final String DELETE_FILE_LIST = "删除列表";

	private final String NODE_TYPE = "节点类别";

	private final String DELETE_FILE_PATH = "文件全路径";

	/**
	 * 文件名和文件详细实例，用于校验当中是否要解压的判断
	 */
	private final Map<String, UpdateListBean> map_bean = new HashMap<String, UpdateListBean>();

	// public CheckFileService(String business_sys_name, String project_name) {
	// Controller.getInstance().getInjector().inject(this);
	// }

	/**
	 * Description: tar清单路径是以List传入
	 * @param updateListFile
	 * @param updateTarFiles
	 */
	public void checkFileList(String file_list_path, List<String> updateTarFiles) {
		// 获取清单存放目录
		String[] update = updateTarFiles.toArray(new String[updateTarFiles
				.size()]);
		checkFileList(file_list_path, update);
	}

	/**
	 * Description: tar清单路径是以String数组类型传入
	 * @param updateListFile
	 * @param updateTarFiles
	 */
	public void checkFileList(String updateListFile, String[] updateTarFiles) {
		logger.debug("比较[{}]Excel清单文件和[{}]生产包列表是否一致", updateListFile,
				Arrays.toString(updateTarFiles));
		Map<String, List<String>> listFileMap = readFileList(updateListFile);
		Map<String, List<String>> tarFileMap = decompressFiles(updateTarFiles,
				true);
		logger.debug("文件清单[{}]", listFileMap.toString());
		logger.debug("tar包的清单[{}]", tarFileMap.toString());
		checkFileListMap(listFileMap, tarFileMap);
	}

	/**
	 * Description: 获取需要删除的文件列表
	 * @param list_file_path
	 * @return
	 */
	@SuppressWarnings("resource")
	public Map<String,String> getDeleteFileList(String list_file_path) {
		Workbook workbook = getWorkBookByExcelPath(list_file_path);
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			Sheet sheet = workbook.getSheetAt(i);
			String sheet_name = sheet.getSheetName().trim();
			if (sheet_name.equals(DELETE_FILE_LIST)) {
				return getDeleteFileList(sheet);
			}
		}
		return null;
	}

	/**
	 * Description:读取文件列表清单的Excel文件
	 * @param updateListFile
	 * @return 包名为key，对应包名下的文件列表为value
	 */
	@SuppressWarnings("resource")
	public Map<String, List<String>> readFileList(String update_list_file) {
		Workbook workbook = getWorkBookByExcelPath(update_list_file);
		return readExcelFileList(workbook);
	}

	/**
	 * Description: 将传入的tar文件进行解压
	 * @param updateTarFiles
	 * @param flag 如果flag为true表示解压后文件文件删除
	 * @return
	 */
	private Map<String, List<String>> decompressFiles(String[] updateTarFiles,
			boolean flag) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> single_file = new ArrayList<String>();
		for (String file_full_path : updateTarFiles) {
			checkFullPath(file_full_path);
			String file_name = getFileName(file_full_path);
			UpdateListBean bean = map_bean.get(file_name);
			if (Assert.isEmpty(bean)) {
				throw new TpTemplateTarListExcludeException().addScene("PARM",
						file_name);
			}
			if (bean.isIs_check() && bean.isIs_tar()) {
				if (file_full_path.contains(".")) {
					String suffix = file_full_path.substring(
							file_full_path.lastIndexOf(".") + 1,
							file_full_path.length());
					if (suffix.equalsIgnoreCase("tar")) {
						map.putAll(decompressTar(file_full_path, "./", flag));
					} else if (suffix.equalsIgnoreCase("gzip")) {
						map.putAll(decompressGz(file_full_path, flag));
					}
				}
			} else if (!bean.isIs_tar()) {
				single_file.add(file_name);
			} else if (bean.isIs_tar() && !bean.isIs_check()) {
				map.put(file_name, null);
			}
		}
		if (!Assert.isEmpty(single_file)) {
			map.put(SINGLE_FILE_TABLE, single_file);
		}

		return map;

	}

	/**
	 * Description: 解压Gzip文件
	 * @param filePath
	 * @return
	 * @throws IOException
	 */

	@SuppressWarnings("resource")
	private Map<String, List<String>> decompressGz(String file_path,
			boolean flag) {
		logger.debug("开始解压Gzip文件[{}]", file_path);
		InputStream is = null;
		OutputStream os = null;
		GZIPInputStream gis = null;
		String fileName = null;
		try {
			File file = new File(file_path);
			if (!file.exists()) {
				throw new FileNotFoundException();
			}
			is = new FileInputStream(file_path);
			fileName = file.getName();
			os = new FileOutputStream(fileName);
			gis = new GZIPInputStream(is);
			int count;
			byte data[] = new byte[1024];
			while ((count = gis.read(data, 0, 1024)) != -1) {
				os.write(data, 0, count);
			}
			logger.debug("解压Gzip文件[{}]结束", file_path);
			return decompressTar(fileName, "./", flag);

		} catch (FileNotFoundException e) {
			throw new TarExtractFailedForFileNotFoundExcepitionException()
					.addScene("FILE", file_path);
		} catch (Exception e) {
			throw new TarExtractFailedExcepitionException().addScene("FILE",
					file_path);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (gis != null) {
					gis.close();
				}
				if (os != null) {
					os.close();
				}
				if (flag) {
					File fileDelete = new File(fileName);
					fileDelete.delete();
				}

			} catch (Exception e) {
			}
		}
	}

	/**
	 * Description: 将生成的tar文件解归档
	 * @param filename
	 * @param descDir
	 */
	@SuppressWarnings("resource")
	private Map<String, List<String>> decompressTar(String file_path,
			String descDir, boolean flag) {
		logger.debug("开始解压Tar文件[{}]", file_path);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> list = new ArrayList<String>();
		TarInputStream tar = null;
		try {
			File fileGetName = new File(file_path);
			if (!fileGetName.exists()) {
				throw new FileNotFoundException();
			}
			String fileName = fileGetName.getName();
			// 打开.tar文件
			tar = new TarInputStream(new FileInputStream(file_path));
			TarEntry entry = null;
			// 遍历tar文件
			while ((entry = tar.getNextEntry()) != null) {

				String entryName = entry.getName();

				if (entry.isDirectory()) {
					String outPath = (descDir + entryName).replaceAll("\\*",
							"/");
					File file = new File(outPath.substring(0,
							outPath.lastIndexOf('/')));
					if (!file.exists()) {
						continue;
					}
				} else {
					list.add(entryName);
				}

			}
			map.put(fileName, list);
			logger.debug("解压Tar文件[{}]结束", file_path);
			return map;

		} catch (FileNotFoundException e) {
			throw new TarExtractFailedForFileNotFoundExcepitionException()
					.addScene("FILE", file_path);
		} catch (IOException e) {
			throw new TarExtractFailedExcepitionException().addScene("FILE",
					file_path);
		} finally {
			try {
				File file = new File("demo.tar");
				if (flag) {
					file.delete();
				}

				if (tar != null) {
					tar.close();
				}

			} catch (Exception e) {
			}
		}

	}

	/**
	 * Description: 检查传入的两个Map是否一致
	 * @param map1
	 * @param map2
	 */
	private void checkFileListMap(Map<String, List<String>> map1,
			Map<String, List<String>> map2) {
		logger.debug("####开始比对上线文件清单和上线压缩包#####");
		if (map1.size() != map2.size()) {
			throw new TpTemplateTarListDifferentException();
		}
//		List<String> listValue1 = new ArrayList<String>();
//		List<String> listValue2 = new ArrayList<String>();
//		for (Map.Entry<String, List<String>> m : map1.entrySet()) {
//			String key = m.getKey();
//			if (map2.containsKey(key)) {
//				listValue1 = map1.get(key);
//				listValue2 = map2.get(key);
//				if (Assert.isEmpty(listValue1) && Assert.isEmpty(listValue2)) {
//					continue;
//				} else if (Assert.isEmpty(listValue1)
//						|| Assert.isEmpty(listValue2)) {
//					throw new TpTemplateFileListDifferentException().addScene(
//							"PARM", key);
//				}
//				if (listValue1.size() == listValue2.size()) {
//					Set<String> set1 = new HashSet<String>();
//					Set<String> set2 = new HashSet<String>();
//					set1.addAll(listValue1);
//					set2.addAll(listValue2);
//					if (!set1.equals(set2)) {
//						throw new TpTemplateFileListDifferentException()
//								.addScene("PARM", key);
//					}
//				} else {
//					throw new TpTemplateFileListDifferentException().addScene(
//							"PARM", key);
//				}
//			} else {
//				throw new TpTemplateTarListExcludeException().addScene("PARM",
//						key);
//			}
//
//		}
//		logger.debug("####比对上线文件清单和上线压缩包成功#####");
	}

	private Map<String, List<String>> readExcelFileList(Workbook workbook) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<UpdateListBean> list = getFileNameList(workbook);
		List<String> single_file = new ArrayList<String>();
		for (UpdateListBean bean : list) {
			boolean is_tar = bean.isIs_tar();
			boolean is_check = bean.isIs_check();
			String file_name = bean.getFile_name();
			// 是tar包同时需要校验压缩包内文件列表
			if (is_tar && is_check) {
				List<String> file_name_list = new ArrayList<String>();
				List<FileDetailBean> file_detail_bean = bean
						.getFile_detail_bean();
				for (FileDetailBean bean1 : file_detail_bean) {
					if (!bean1.isDir_flag()) {
						file_name_list.add(bean1.getFile_child_name());
					}

				}
				map.put(file_name, file_name_list);
			} else if (is_tar) {
				map.put(file_name, null);
			} else if (!is_tar) {
				single_file.add(file_name);
			}
		}
		if (!Assert.isEmpty(single_file)) {
			map.put(SINGLE_FILE_TABLE, single_file);
		}

		return map;
	}

	/**
	 * Description: 解压文件
	 * @param filename tar包
	 * @param directory
	 */
	@SuppressWarnings("resource")
	public void untar(String filename, String directory) {
		OutputStream out = null;
		try {
			TarInputStream in = new TarInputStream(new FileInputStream(
					new File(filename)));
			TarEntry entry = null;
			while ((entry = in.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				File outfile = new File(directory + "/" + entry.getName());
				new File(outfile.getParent()).mkdirs();
				out = new BufferedOutputStream(new FileOutputStream(outfile));
				int x = 0;
				while ((x = in.read()) != -1) {
					out.write(x);
				}
				out.close();
			}
			in.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	private void checkFullPath(String file_full_path) {
		if (file_full_path.length() == 0 || file_full_path.trim().equals("")) {
			throw new AddPathIsNotFullPathException().addScene("DIR",
					file_full_path);
		}
		if (file_full_path.endsWith("/") || file_full_path.endsWith(".")) {
			throw new AddPathIsNotFullPathException().addScene("DIR",
					file_full_path);
		}
		// checkPureDirectory(root_path);
		String file_name = getFileName(file_full_path);
		if (file_name.length() == 0 || file_name.equals("")) {
			throw new AddPathIsNotFullPathException().addScene("DIR",
					file_full_path);
		}
		// if (!root_part.contains(".")) {
		// throw new AddPathIsNotFullPathException()
		// .addScene("DIR", file_full_path);
		// }
	}

	private String getFileName(String file_path) {
		if (file_path.contains("/")) {
			String file_name = file_path
					.substring(file_path.lastIndexOf("/") + 1);
			return file_name;
		}
		return null;
	}

	/**
	 * Description:
	 * @param workbook
	 * @return
	 */
	private List<UpdateListBean> getFileNameList(Workbook workbook) {
		List<UpdateListBean> list = new ArrayList<UpdateListBean>();

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			Sheet sheet = workbook.getSheetAt(i);
			String sheet_name = sheet.getSheetName().trim();
			if (sheet_name.equals(SPECIAL_FILE_SHEET)) {
				return getFileNameList(sheet, sheet_name, workbook);
			}

		}
		return list;

	}

	/**
	 * Description: 获取文件名列表
	 * @param sheet
	 * @param sheet_name
	 * @param workbook
	 * @return
	 */
	private List<UpdateListBean> getFileNameList(Sheet sheet,
			String sheet_name, Workbook workbook) {
		List<UpdateListBean> list = new ArrayList<UpdateListBean>();
		Row row_title = sheet.getRow(0);
		if (row_title != null) {
			Cell file_name = row_title.getCell(0);
			Cell alias_file_name = row_title.getCell(1);
			Cell is_tar = row_title.getCell(2);
			Cell is_check = row_title.getCell(3);
			if (file_name == null || alias_file_name == null || is_tar == null
					|| is_check == null) {
				throw new FileListTitleException().addScene("MSG", "存在为空的情况");
			}
			logger.debug("#####清单CELL:[{}]",file_name);
			if (!file_name.toString().trim().equals(FILE_NAME)) {
				throw new FileListTitleException().addScene("MSG", FILE_NAME
						+ "不正确");
			}
			if (!alias_file_name.toString().trim().equals(ALIAS_FILE_NAME)) {
				throw new FileListTitleException().addScene("MSG",
						ALIAS_FILE_NAME + "不正确");
			}
			if (!is_tar.toString().trim().equals(IS_TAR)) {
				throw new FileListTitleException().addScene("MSG", IS_TAR
						+ "不正确");
			}
			if (!is_check.toString().trim().equals(IS_CHECK)) {
				throw new FileListTitleException().addScene("MSG", IS_CHECK
						+ "不正确");
			}

		}
		for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);
			if (row != null) {
				Cell cell_file_name = row.getCell(0);
				Cell cell_alias_name = row.getCell(1);
				Cell cell_is_tar = row.getCell(2);
				Cell cell_is_check = row.getCell(3);
				if (cell_file_name == null
						|| "".equals(cell_file_name.toString().trim())) {
					break;// 当清单表中出现有空白行，则接下来的数据不再读取
				}
				String file_name = cell_file_name.toString();
				String alias_file_name = cell_alias_name == null ? ""
						: cell_alias_name.toString().trim();
				boolean is_tar = cell_is_tar == null ? true
						: getBooleanFromString(cell_is_tar.toString());
				boolean is_check = cell_is_check == null ? true
						: getBooleanFromString(cell_is_check.toString());
				UpdateListBean bean = new UpdateListBean(file_name,
						alias_file_name, is_tar, is_check);
				String name = Assert.isEmpty(alias_file_name) ? file_name
						: alias_file_name;
				if (is_tar) {
					bean.setFile_detail_bean(getFileDetail(name, workbook));

				}
				list.add(bean);
				map_bean.put(file_name, bean);

			}
		}

		return list;

	}

	/**
	 * Description: 获取需删除文件列表 其中类型为 “node_type|full_path”
	 * @param sheet
	 * @return
	 */
	private Map<String,String> getDeleteFileList(Sheet sheet) {
		Map<String,String> map  = new HashMap<String,String>();
	
		Row row_title = sheet.getRow(0);
		// 校验表头是否合法
		if (row_title != null) {
			Cell node_type = row_title.getCell(0);
			Cell full_path = row_title.getCell(1);
			if (node_type == null || full_path == null) {
				throw new FileListTitleException().addScene("MSG", "存在为空的情况");
			}
			if (!node_type.toString().equals(NODE_TYPE)) {
				throw new FileListTitleException().addScene("MSG", NODE_TYPE
						+ "不正确");
			}
			if (!full_path.toString().equals(DELETE_FILE_PATH)) {
				throw new FileListTitleException().addScene("MSG",
						DELETE_FILE_PATH + "不正确");
			}
		}
		//获取表中的节点和路径列表
		for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);
			if (row != null) {
				Cell cell_node_type = row.getCell(0);
				Cell cell_full_path = row.getCell(1);
				if (cell_node_type == null
						|| "".equals(cell_node_type.toString().trim())
						|| cell_full_path == null
						|| "".equals(cell_full_path.toString().trim())) {
					break;// 当清单表中出现有空白行，则接下来的数据不再读取
				}
				String node_type = cell_node_type.toString();
				String full_path = cell_full_path.toString();
				
				if(map.containsKey(node_type)){
					String add = map.get(node_type)+"\n"+full_path;
					map.put(node_type, add);
				}else{
					map.put(node_type, full_path);
				}

			}
		}
		return map;

	}

	/**
	 * Description: 根据文件名找到 Excel中对应的tar包的table页
	 * @param file_name
	 * @param workbook
	 * @return
	 */
	private List<FileDetailBean> getFileDetail(String file_name,
			Workbook workbook) {
		List<FileDetailBean> list = new ArrayList<FileDetailBean>();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			Sheet sheet = workbook.getSheetAt(i);
			String sheet_name = sheet.getSheetName().trim();
			if (sheet_name.equals(file_name)) {
				return getFileDetail(sheet, sheet_name);
			}

		}
		return list;

	}

	/**
	 * Description:获取文件名列表中 tar包文件中文件的详细信息
	 * @param sheet
	 * @param sheet_name
	 * @return
	 */
	private List<FileDetailBean> getFileDetail(Sheet sheet, String sheet_name) {
		List<FileDetailBean> list = new ArrayList<FileDetailBean>();
		for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);
			if (row != null) {
				Cell msg = row.getCell(0);
				Cell file_dir = row.getCell(1);
				if (msg == null || "".equals(msg.toString().trim())) {
					break;// 当清单表中出现有空白行，则接下来的数据不再读取
				} else if(msg.toString().endsWith("/")){
					continue;
				}else {
					boolean dir_flag = false;
					if (file_dir == null
							|| "".equals(file_dir.toString().trim())) {
						dir_flag = false;
					} else {
						file_dir.setCellType(Cell.CELL_TYPE_STRING);
						dir_flag = file_dir.toString().equalsIgnoreCase("d");
						// 如果是目录暂时不做判断
						continue;
					}

					msg.setCellType(Cell.CELL_TYPE_STRING);
					FileDetailBean bean = new FileDetailBean(msg.toString(),
							dir_flag);
					list.add(bean);
				}
			}
		}
		return list;

	}

	/**
	 * Description: 根据传入的字符串判断其布尔值
	 * @param check
	 * @return
	 */
	private boolean getBooleanFromString(String check) {
		String[] true_flag = { "T", "TRUE", "Y", "YES", "是" };
		String[] false_flag = { "F", "FALSE", "N", "NO", "否" };
		if (Assert.isEmpty(check)) {
			return true;
		}
		for (String f : false_flag) {
			if (f.equalsIgnoreCase(check)) {
				return false;
			}
		}
		for (String t : true_flag) {
			if (t.equalsIgnoreCase(check)) {
				return true;
			}
		}
		// 清单文件：文件名列表中存在字符串[CHECK]不能判断其Boolean值
		throw new FileListBooleanException().addScene("CHECK", check);
	}

	/**
	 * Description: 根据Excel文件的路径地址 获取Workbook对象
	 * @param updateListFile
	 * @return
	 */
	@SuppressWarnings("resource")
	private Workbook getWorkBookByExcelPath(String update_list_file) {
		InputStream is;
		try {
			is = new FileInputStream(update_list_file);
		} catch (Exception e) {
			// 不是Excel或者文件路径为空 都会抛出“不是Excel文件”异常
			logger.error(e.toString(), e);
			throw new ExcelFileIOException().addScene("FILE", update_list_file)
					.addScene("PARM", "文件列表路径不正确或者文件列表不存在");
		}
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(is);
			// return readExcelFileList(workbook, updateListFile);
			return workbook;
		} catch (EncryptedDocumentException e) {
			logger.error(e.toString(), e);
			throw new ExcelFileIOException().addScene("FILE", update_list_file)
					.addScene("PARM", "Excel文件被加密");
		} catch (IllegalArgumentException e) {
			logger.error(e.toString(), e);
			throw new ExcelFileIOException().addScene("FILE", update_list_file)
					.addScene("PARM", "不是Excel文件或伪装的Excel文件");
		} catch (InvalidFormatException e) {
			logger.error(e.toString(), e);
			throw new ExcelFileIOException().addScene("FILE", update_list_file)
					.addScene("PARM", "不是Excel文件或伪装的Excel文件");
		} catch (IOException e) {
			logger.error(e.toString(), e);
			throw new ExcelFileIOException().addScene("FILE", update_list_file)
					.addScene("PARM", "路径错误或者文件其他错误");
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
				if (is != null) {
					is.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
