package com.wk.cd.module1.dao;

import java.util.List;

import com.wk.cd.enu.TEMPLATE_FORMATE;
import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.cd.module1.info.MoTemplateInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

abstract class MoTemplateDao extends EntityDao<MoTemplateInfo> {
	
	@SqlParam(sql = "select ltrim(rtrim(TEMPLATE_NAME)) from mo_template ORDER BY CRT_BK_DATE DESC,CRT_BK_TIME DESC")
	abstract List<String> queryAllTemplateName();

	@SqlParam(condition = "template_name=:template_name")
	abstract int countByTemplateName(String paramString);

	@SqlParam(updateSet = { "TP_CLASS_NAME,SCRIPT_FILE_PATH" }, condition = "PK")
	abstract int updateTemplate(String paramString1, String paramString2,
			String paramString3);

	@SqlParam(sql = "select * from MO_TEMPLATE order by :order_col_name :order_type", dynamic = true)
	abstract List<MoTemplateInfo> pageAllTemplate(String paramString1,
			String paramString2, int paramInt1, int paramInt2);

	@SqlParam(condition = "1 = 1")
	abstract int countAllTemplate();

	@SqlParam(condition = "SCRIPT_FILE_PATH=:script_file_path")
	abstract int countByScriptFilePath(String paramString);

	@SqlParam(sql = "select ltrim(rtrim(TEMPLATE_NAME)) from mo_template where TEMPLATE_TYPE=:template_type")
	abstract List<String> queryTemplateNameByType(
			TEMPLATE_TYPE paramTEMPLATE_TYPE);

	@SqlParam(sql = "select ltrim(rtrim(TEMPLATE_NAME)) from mo_template where TEMPLATE_FORMATE=:template_formate and TEMPLATE_TYPE=:template_type")
	abstract List<String> queryTemplateNameByFormate(
			TEMPLATE_FORMATE paramTEMPLATE_FORMATE,
			TEMPLATE_TYPE paramTEMPLATE_TYPE);

	@SqlParam(updateSet = { "REF_MODULE_ID", "TEMPLATE_FORMATE" }, condition = "PK")
	abstract int updateTmplateInfoByKey(String paramString1,
			TEMPLATE_FORMATE paramTEMPLATE_FORMATE, String paramString2);

	@SqlParam(updateSet = { "SCRIPT_FILE_PATH" }, condition = "PK")
	abstract int updateTemplateScriptPath(String paramString1,
			String paramString2);

	@SqlParam(querySet = { "REF_MODULE_ID" }, condition = "PK")
	abstract String getRefModuleIdByName(String paramString);
}