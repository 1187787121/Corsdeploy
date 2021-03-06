/**
 * Title: GenerateExcelListService.java
 * File Description: 生成清单Excel文件
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017年2月14日
 */
package com.wk.cd.build.ea.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.dao.EnvTagStorageDaoService;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.PgInteStepDaoService;
import com.wk.cd.build.ea.dao.UuFilelistDaoService;
import com.wk.cd.build.ea.info.EnvTagStorageInfo;
import com.wk.cd.build.ea.info.PgInteStepInfo;
import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.cd.build.exc.ExcelListGenerateException;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.STEP_TYPE;
import com.wk.cd.enu.TASK_ALL_TYPE;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 生成清单Excel文件
 * @author Xul
 */
public class GenerateExcelListService {

	@Inject
	private PgInteStepDaoService pgInteStepDaoService;
	@Inject
	private EnvTaskDaoService envTaskDaoService;
	@Inject
	private EnvTagStorageDaoService envTagStorageDaoService;
	@Inject
	private UuFilelistDaoService uuFilelistDaoService;
	private static final String VER_NUM = "${ver_num}";
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 根据任务编号生成Excel清单文件
	 * @param work_id
	 */
	public void generateExcelList(String work_id, TASK_ALL_TYPE type, String path) {
		// 若为集成任务
		if (TASK_ALL_TYPE.INTEGRATION.equals(type)) {
			// 获取入库步骤的清单UUID
			String prog_id = envTaskDaoService.getInfoByKey(work_id).getProg_id();
			if (!Assert.isEmpty(prog_id)) {
				List<PgInteStepInfo> step_list = pgInteStepDaoService.getInfoByType(prog_id, STEP_TYPE.STORAGE);
				if (!Assert.isEmpty(step_list)) {
					String list_uuid = step_list.get(0).getStorage_list_uuid();
					if (!Assert.isEmpty(list_uuid)) {
						generateLists(list_uuid, path);
					}
				}
			}
			// 若为入库任务
		} else if (TASK_ALL_TYPE.STORAGE.equals(type)) {
			// 获取入库清单UUID
			EnvTagStorageInfo storage_info = envTagStorageDaoService.getInfoByKey2(work_id);
			String list_uuid = storage_info.getStorage_list_uuid();
			if (!Assert.isEmpty(list_uuid)) {
				generateLists(list_uuid, path);
			}
		}
	}

	/**
	 * Description: 根据任务编号生成Excel清单文件(重载方法)
	 * @param work_id
	 */
	public void generateExcelList(String prog_id, String path, String vercode_ver_num) {
		if (!Assert.isEmpty(prog_id)) {
			List<PgInteStepInfo> step_list = pgInteStepDaoService.getInfoByType(prog_id, STEP_TYPE.STORAGE);
			if (!Assert.isEmpty(step_list)) {
				String list_uuid = step_list.get(0).getStorage_list_uuid();
				if (!Assert.isEmpty(list_uuid)) {
					generateList(list_uuid, path, vercode_ver_num);
				}
			} else {
				throw new ExcelListGenerateException().addScene("REASON", "集成方案下无入库步骤");
			}
		}
	}

	/**
	 * Description: 根据任务编号生成Excel清单文件(重载方法)
	 */
	public void generateExcelList(List<TargetPackageBean> tar_package_list, String path) {
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		@SuppressWarnings("resource") XSSFWorkbook wb = new XSSFWorkbook();

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		XSSFSheet sheet = wb.createSheet("文件名列表");

		// 创建字体样式
		XSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setBoldweight((short) 80);
		font.setFontHeight((short) 220);

		// 创建单元格样式
		XSSFCellStyle style = wb.createCellStyle();
		style.setFont(font);// 设置字体

		// 创建Excel的sheet的一行
		XSSFRow row = sheet.createRow(0);
		row.setHeight((short) 300);// 设定行的高度
		createCell(row, 0, style, "文件名");
		createCell(row, 1, style, "别名");
		createCell(row, 2, style, "是否打包文件");
		createCell(row, 3, style, "是否校验");

		// 获取包名，遍历生成EXCEL
		if (!Assert.isEmpty(tar_package_list)) {
			for (int i = 1; i <= tar_package_list.size(); i++) {
				/**
				 * 生成包名列表
				 */
				String package_name = tar_package_list.get(i - 1).getPackage_name();
				String package_name2 = package_name;
				XSSFRow package_row = sheet.createRow(i);
				package_row.setHeight((short) 300);// 设定行的高度
				createCell(package_row, 0, style, package_name);
				// 若包名大于26位，则采用别名（截取前26位加上一个列号）
				if (package_name.length() >= 26) {
					package_name2 = package_name.substring(0, 26) + i;
					createCell(package_row, 1, style, package_name2);
				}
				createCell(package_row, 2, style, "y");
				createCell(package_row, 3, style, "y");

				/**
				 * 生成每个包内文件列表
				 */
				logger.debug("####生成包[{}]Sheet表格", package_name2);
				XSSFSheet package_sheet = wb.createSheet(package_name2);
				if (!Assert.isEmpty(tar_package_list.get(i - 1).getFile_list())) {
					for (int j = 0; j < tar_package_list.get(i - 1).getFile_list().size(); j++) {
						XSSFRow sheet_row = package_sheet.createRow(j);
						sheet_row.setHeight((short) 300);// 设定行的高度
						String tag_path = tar_package_list.get(i - 1).getFile_list().get(j).getFile_path();
						if (!tag_path.endsWith("/")) {
							tag_path = tag_path + "/";
						}
						String tag_value = tar_package_list.get(i - 1).getFile_list().get(j).getFile_name();
						String cell_value = new String(tag_path + tag_value);
						logger.debug("包清单内容:[{}]", cell_value);
						createCell(sheet_row, 0, style, cell_value);
					}
				}
				package_sheet.autoSizeColumn((short) 0);
			}

			// 自适应列宽
			sheet.autoSizeColumn((short) 0);
			sheet.autoSizeColumn((short) 1);
			sheet.autoSizeColumn((short) 2);
			sheet.autoSizeColumn((short) 3);

		}

		// 输出文件
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(path);
			wb.write(os);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ExcelListGenerateException().addScene("REASON", path);
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * Description: （独立）入库清单生成2
	 * @param root_path 
	 */
	public void generateStorgExcel(List<TargetPackageBean> tar_package_list, String path, String root_path) {
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		@SuppressWarnings("resource") XSSFWorkbook wb = new XSSFWorkbook();

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		XSSFSheet sheet = wb.createSheet("文件名列表");

		// 创建字体样式
		XSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setBoldweight((short) 80);
		font.setFontHeight((short) 220);

		// 创建单元格样式
		XSSFCellStyle style = wb.createCellStyle();
		style.setFont(font);// 设置字体

		// 创建Excel的sheet的一行
		XSSFRow row = sheet.createRow(0);
		row.setHeight((short) 300);// 设定行的高度
		createCell(row, 0, style, "文件名");
		createCell(row, 1, style, "别名");
		createCell(row, 2, style, "是否打包文件");
		createCell(row, 3, style, "是否校验");

		// 获取包名，遍历生成EXCEL
		if (!Assert.isEmpty(tar_package_list)) {
			for (int i = 1; i <= tar_package_list.size(); i++) {
				/**
				 * 生成包名列表
				 */
				String package_name = tar_package_list.get(i - 1).getPackage_name();
				String package_name2 = package_name;
				XSSFRow package_row = sheet.createRow(i);
				package_row.setHeight((short) 300);// 设定行的高度
				createCell(package_row, 0, style, package_name);
				// 若包名大于26位，则采用别名（截取前26位加上一个列号）
				if (package_name.length() >= 26) {
					package_name2 = package_name.substring(0, 26) + i;
					createCell(package_row, 1, style, package_name2);
				}
				createCell(package_row, 2, style, "y");
				createCell(package_row, 3, style, "y");

				TargetPackageBean pkbean = tar_package_list.get(i - 1);
				//String root_path = formatDirA(pkbean.getStorage_bk_path());
				List<UuFilelistInfo> file_list = pkbean.getFile_list();
				/**
				 * 生成每个包内文件列表
				 */
				logger.debug("####生成包[{}]Sheet表格", package_name2);
				XSSFSheet package_sheet = wb.createSheet(package_name2);
				if (!Assert.isEmpty(file_list)) {
					for (int j = 0; j < file_list.size(); j++) {
						XSSFRow sheet_row = package_sheet.createRow(j);
						sheet_row.setHeight((short) 300);// 设定行的高度
						// 路径
						String file_path = formatDirA(file_list.get(j).getFile_path());
						// 文件名
						String file_name = file_list.get(j).getFile_name();
						// 命令信息
						logger.debug("###文件路径：[{}]", file_path);
						String tar_file_path = file_path.replace(root_path, "");
						String cell_value = new String(tar_file_path + file_name);
						logger.debug("包清单内容:[{}]", cell_value);
						createCell(sheet_row, 0, style, cell_value);
					}
				}
				package_sheet.autoSizeColumn((short) 0);
			}

			// 自适应列宽
			sheet.autoSizeColumn((short) 0);
			sheet.autoSizeColumn((short) 1);
			sheet.autoSizeColumn((short) 2);
			sheet.autoSizeColumn((short) 3);

		}

		// 输出文件
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(path);
			wb.write(os);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ExcelListGenerateException().addScene("REASON", path);
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * Description: 格式目录(加/)
	 * @param file_path
	 * @return
	 */
	public String formatDirA(String file_path) {
		if (!Assert.isEmpty(file_path)) {
			if ('/' != (file_path.charAt(0))) {
				file_path = "/" + file_path;
			}
			if (file_path.length() > 0 && '/' != file_path.charAt(file_path.length() - 1)) {
				file_path = file_path + "/";
			}
		}
		return file_path;
	}

	/**
	 * Description: 生成Excel清单文件（集成）
	 * @param list_uuid
	 */
	public void generateList(String list_uuid, String path, String vercode_ver_num) {
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		@SuppressWarnings("resource") XSSFWorkbook wb = new XSSFWorkbook();

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		XSSFSheet sheet = wb.createSheet("文件名列表");

		// 创建字体样式
		XSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setBoldweight((short) 80);
		font.setFontHeight((short) 220);

		// 创建单元格样式
		XSSFCellStyle style = wb.createCellStyle();
		style.setFont(font);// 设置字体

		// 创建Excel的sheet的一行
		XSSFRow row = sheet.createRow(0);
		row.setHeight((short) 300);// 设定行的高度
		createCell(row, 0, style, "文件名                      ");
		createCell(row, 1, style, "别名                          ");
		createCell(row, 2, style, "是否打包文件           ");
		createCell(row, 3, style, "是否校验                   ");

		// 获取包名，遍历生成EXCEL
		List<String> package_list = uuFilelistDaoService.queryPacList(list_uuid);
		logger.debug("包列表:[{}]", package_list);
		if (!Assert.isEmpty(package_list)) {
			for (int i = 1; i <= package_list.size(); i++) {
				/**
				 * 生成包名列表
				 */
				String package_name = package_list.get(i - 1);
				String package_name2 = package_name;
				XSSFRow package_row = sheet.createRow(i);
				package_row.setHeight((short) 300);// 设定行的高度
				createCell(package_row, 0, style, package_name);
				// 若包名大于26位，则采用别名（截取前26位加上一个列号）
				if (package_name.length() >= 26) {
					package_name2 = package_name.substring(0, 26) + i;
					createCell(package_row, 1, style, package_name2);
				}
				createCell(package_row, 2, style, "y");
				createCell(package_row, 3, style, "y");

				/**
				 * 生成每个包内文件列表
				 */
				logger.debug("生成sheet:[{}]", package_name2);
				XSSFSheet package_sheet = wb.createSheet(package_name2);
				List<UuFilelistInfo> file_list = uuFilelistDaoService.queryFileByPac(list_uuid, package_name);
				if (!Assert.isEmpty(file_list)) {
					for (int j = 0; j < file_list.size(); j++) {
						XSSFRow sheet_row = package_sheet.createRow(j);
						sheet_row.setHeight((short) 300);// 设定行的高度
						String file_path = file_list.get(j).getFile_path().replace(VER_NUM, vercode_ver_num);
						logger.debug("######1:PATH:[{}]", file_path);
						if (!file_path.endsWith("/")) {
							file_path = file_path + "/";
						}
						logger.debug("######2:PATH[{}]", file_path);
						String file_name = file_list.get(j).getFile_name();
						createCell(sheet_row, 0, style, file_path + file_name + "                ");
					}
				}
				package_sheet.autoSizeColumn((short) 0);
			}

			// 自适应列宽
			sheet.autoSizeColumn((short) 0);
			sheet.autoSizeColumn((short) 1);
			sheet.autoSizeColumn((short) 2);
			sheet.autoSizeColumn((short) 3);

		}

		// 输出文件
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(path);
			wb.write(os);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if (!Assert.isEmpty(os))
					os.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * Description: 生成Excel清单文件（集成）
	 * @param list_uuid
	 */
	public void generateLists(String list_uuid, String path) {
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		@SuppressWarnings("resource") XSSFWorkbook wb = new XSSFWorkbook();

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		XSSFSheet sheet = wb.createSheet("文件名列表");

		// 创建字体样式
		XSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setBoldweight((short) 80);
		font.setFontHeight((short) 220);

		// 创建单元格样式
		XSSFCellStyle style = wb.createCellStyle();
		style.setFont(font);// 设置字体

		// 创建Excel的sheet的一行
		XSSFRow row = sheet.createRow(0);
		row.setHeight((short) 300);// 设定行的高度
		createCell(row, 0, style, "文件名                      ");
		createCell(row, 1, style, "别名                          ");
		createCell(row, 2, style, "是否打包文件           ");
		createCell(row, 3, style, "是否校验                   ");

		// 获取包名，遍历生成EXCEL
		List<String> package_list = uuFilelistDaoService.queryPacList(list_uuid);
		logger.debug("包列表:[{}]", package_list);
		if (!Assert.isEmpty(package_list)) {
			for (int i = 1; i <= package_list.size(); i++) {
				/**
				 * 生成包名列表
				 */
				String package_name = package_list.get(i - 1);
				String package_name2 = package_name;
				XSSFRow package_row = sheet.createRow(i);
				package_row.setHeight((short) 300);// 设定行的高度
				createCell(package_row, 0, style, package_name);
				// 若包名大于26位，则采用别名（截取前26位加上一个列号）
				if (package_name.length() >= 26) {
					package_name2 = package_name.substring(0, 26) + i;
					createCell(package_row, 1, style, package_name2);
				}
				createCell(package_row, 2, style, "y");
				createCell(package_row, 3, style, "y");

				/**
				 * 生成每个包内文件列表
				 */
				logger.debug("生成sheet:[{}]", package_name2);
				XSSFSheet package_sheet = wb.createSheet(package_name2);
				List<UuFilelistInfo> file_list = uuFilelistDaoService.queryFileByPac(list_uuid, package_name);
				if (!Assert.isEmpty(file_list)) {
					for (int j = 0; j < file_list.size(); j++) {
						XSSFRow sheet_row = package_sheet.createRow(j);
						sheet_row.setHeight((short) 300);// 设定行的高度
						String file_path = file_list.get(j).getFile_path();
						logger.debug("######1:PATH:[{}]", file_path);
						if (!file_path.endsWith("/")) {
							file_path = file_path + "/";
						}
						logger.debug("######2:PATH[{}]", file_path);
						String file_name = file_list.get(j).getFile_name();
						createCell(sheet_row, 0, style, file_path + file_name + "                ");
					}
				}
				package_sheet.autoSizeColumn((short) 0);
			}

			// 自适应列宽
			sheet.autoSizeColumn((short) 0);
			sheet.autoSizeColumn((short) 1);
			sheet.autoSizeColumn((short) 2);
			sheet.autoSizeColumn((short) 3);

		}

		// 输出文件
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(path);
			wb.write(os);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if (!Assert.isEmpty(os))
					os.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * 创建一个Excel的单元格
	 */
	private void createCell(XSSFRow row, int cellNum, XSSFCellStyle style, String value) {
		XSSFCell cell = row.createCell(cellNum);
		cell.setCellStyle(style);
		cell.setCellValue(value);
	}
}
