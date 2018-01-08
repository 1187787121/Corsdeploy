/**
 * Title: ScriptInstanceGenerator.java
 * File Description: ģ��ʵ��������
 * @copyright: 2016
 * @company: CORSWORK
 * @author: "Zhangj"
 * @version: 1.0
 * @date: 2016��6��8��
 */
package com.wk.cd.module1.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wk.cd.module1.bean.ParamInCmdBean;
import com.wk.cd.module1.exc.CmdParamIsNotExistException;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.common.util.Assert;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:ģ��ʵ��������
 * @author "Zhangj"
 */
public class XmlSystemParamUtils {
	private static final Log logger = LogFactory.getLog();
	private static final Pattern CONFIG_PARAM_PATTERN = Pattern.compile("\\$\\{\\s*node_config_(local|file)\\s*\\}");

	public static List<ParamInfo> getFixedParam() {
		List<ParamInfo> list = new ArrayList<ParamInfo>();
		ParamInfo param_bean = new ParamInfo();
		param_bean.setParam_name("update_local_dir");
		param_bean.setParam_value("module/testExecute/");
		list.add(param_bean);
		return list;
	}

	/**
	 * Description: �滻�������õ���ϵͳ������������ֵ�����õ���ϵͳ���� �滻Ϊϵͳ������ֵ
	 * @param params
	 * @param system_param ��Ҫ�滻��ϵͳ���������û��ϵͳ��������null
	 * @return
	 */
	public static List<ParamInfo> replaceParamValue(List<ParamInfo> params, List<ParamInfo> system_param) {
		logger.debug("replaceParamValue begine");
		if (Assert.isEmpty(params) || Assert.isEmpty(system_param)) {
			logger.debug("no param");
			return params;
		}
		List<ParamInfo> list = new ArrayList<ParamInfo>();
		for (ParamInfo info : params) {
			String value = info.getParam_value();
			ParamInfo pi = ParamInfo.copy(info);
			pi.setParam_value(XmlSystemParamUtils.replaceValue(value, system_param));
			list.add(pi);
		}
		logger.debug("replaceParamValue end");
		return list;
	}

	public static List<ParamInfo> splitMultiParams(List<ParamInfo> params) {
		List<ParamInfo> result = new ArrayList<ParamInfo>();
		if (!Assert.isEmpty(params)) {
			for (ParamInfo param : params) {
				String value = param.getParam_value();
				if (value.contains(";")) {
					String[] values = value.split(";");
					int index = 1;
					for (String str : values) {
						ParamInfo p = ParamInfo.copy(param);
						p.setParam_value(str);
						p.setIndex(index++);
						result.add(p);
					}
				} else {
					result.add(param);
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		List<ParamInfo> params = new ArrayList<ParamInfo>();
		List<ParamInfo> system_param = new ArrayList<ParamInfo>();
		ParamInfo info = new ParamInfo();
		info.setParam_value("value${task_no}ddd");
		info.setParam_group("group");
		info.setParam_name("name");
		params.add(info);

		ParamInfo info1 = new ParamInfo();
		info1.setParam_value("kskskskks");
		info1.setParam_name("task_no");
		system_param.add(info1);

		List<ParamInfo> list = replaceParamValue(params, system_param);
		for (ParamInfo i : list) {
			System.out.println(i.getParam_group());
			System.out.println(i.getParam_name());
			System.out.println(i.getParam_value());
		}

	}

	/**
	 * Description: ���ɵ��������ʵ���������滻���������еĲ���
	 * @param cmd
	 * @param time_out
	 * @param param_group_list
	 * @return
	 */
	private static String replaceValue(String value, List<ParamInfo> params) {
		List<String> param_in_value = getSysParamInValue(value);
		// ���������û����Ҫ�滻�Ĳ��� ��ֱ�ӽ��������ͳ�ʱʱ�޷���
		if (Assert.isEmpty(param_in_value)) {
			return value;
		}
		if (Assert.isEmpty(params)) {
			throw new CmdParamIsNotExistException().addScene("PARAM", value);
		}
		// ��ȡ���������õĲ��� ������Щ����������Ϸ���
		List<List<ParamInCmdBean>> params_needs = getUsefulParam(param_in_value, params);
		// �滻�����еĲ���
		List<String> result_cmd = replaceParamInCmd(value, params_needs);
		StringBuffer sb = new StringBuffer();
		for (String v : result_cmd) {
			sb.append(v + "-,-");
		}
		return sb.toString();
	}

	/**
	 * Description: ��ȡ�����в����б�
	 * @param cmd
	 * @return
	 */
	private static List<String> getSysParamInValue(String value) {
		List<String> param_list = new ArrayList<String>();
		// �������ʽ ƥ�䡰${ was.was }�������������пո���п���
		String match_group = "\\$\\{\\s*\\w+\\.\\w+\\s*\\}";
		Pattern regex_group = Pattern.compile(match_group);
		Matcher m_group = regex_group.matcher(value);
		// ��ȡ������ƥ���������ʽ�Ĳ������뵽�б���
		while (true) {
			if (!m_group.find()) {
				break;
			}
			String param_group = m_group.group().substring(2, m_group.group().length() - 1);
			if (!param_list.contains(param_group)) {
				param_list.add(param_group.trim());
			}
		}
		// �������ʽ ƥ�䡰${ was }�������������пո���п���
		String match_single = "\\$\\{\\s*\\w+\\s*\\}";
		Pattern regex_single = Pattern.compile(match_single);
		Matcher m_single = regex_single.matcher(value);
		// ��ȡ������ƥ���������ʽ�Ĳ������뵽�б���
		while (true) {
			if (!m_single.find()) {
				break;
			}
			String param_single = m_single.group().substring(2, m_single.group().length() - 1);
			if (!param_list.contains(param_single)) {
				param_list.add(param_single.trim());
			}
		}
		return param_list;
	}

	/**
	 * Description: ��ȡ�������������õĲ���������ϴ���
	 * @param param_in_cmd
	 * @param params
	 * @param cmd
	 * @return
	 */
	private static List<List<ParamInCmdBean>> getUsefulParam(List<String> param_in_cmd, List<ParamInfo> params) {
		List<List<ParamInCmdBean>> list_father = new ArrayList<List<ParamInCmdBean>>();
		// ������������Ҫʹ�õ��Ĳ���
		List<ParamInCmdBean> list_cmd = ParamInCmdBean.coypList(params);
		for (String param : param_in_cmd) {
			List<ParamInCmdBean> params_return = new ArrayList<ParamInCmdBean>();
			for (ParamInCmdBean bean : list_cmd) {
				String param_name = bean.getParam_name();
				String group = bean.getGroup();
				if (Assert.isEmpty(group)) {
					if (param.equals(param_name)) {
						params_return.add(bean);
					}
				} else {
					if (param.equals(group + "." + param_name)) {
						params_return.add(bean);
					}
				}

			}
			if (Assert.isEmpty(params_return)) {
				throw new CmdParamIsNotExistException().addScene("PARAM", param);
			}
			list_father.add(params_return);

		}
		return paramFilter(descartes(list_father));
	}

	/**
	 * Description: �滻�����еĲ���
	 * @param cmd
	 * @param params
	 * @return
	 */
	private static List<String> replaceParamInCmd(String value, List<List<ParamInCmdBean>> params) {
		List<String> result_cmd = new ArrayList<String>();
		if (Assert.isEmpty(params)) {
			result_cmd.add(value);
			return result_cmd;
		}
		for (List<ParamInCmdBean> list : params) {
			String merged_cmd = replaceCmd(list, value);

			result_cmd.add(merged_cmd);
		}

		return result_cmd;

	}

	/**
	 * Description: �������� ���еѿ����˻�
	 * @param crossArgs
	 * @return
	 */
	private static List<List<ParamInCmdBean>> descartes(List<List<ParamInCmdBean>> params) {

		// ������ѿ���������
		int rows = params.size() > 0 ? 1 : 0;

		for (List<ParamInCmdBean> data : params) {
			rows *= data.size();
		}
		// �ѿ�����������¼
		int[] record = new int[params.size()];
		List<List<ParamInCmdBean>> results = new ArrayList<List<ParamInCmdBean>>();
		// �����ѿ�����
		for (int i = 0; i < rows; i++) {
			List<ParamInCmdBean> row = new ArrayList<ParamInCmdBean>();
			// ���ɵѿ�������ÿ������
			for (int index = 0; index < record.length; index++) {
				row.add(params.get(index).get(record[index]));
			}
			results.add(row);
			descartesRecord(params, record, params.size() - 1);
		}
		return results;
	}

	private static void descartesRecord(List<List<ParamInCmdBean>> sourceArgs, int[] record, int level) {
		record[level] = record[level] + 1;
		if (record[level] >= sourceArgs.get(level).size() && level > 0) {
			record[level] = 0;
			descartesRecord(sourceArgs, record, level - 1);
		}
	}

	private static List<List<ParamInCmdBean>> paramFilter(List<List<ParamInCmdBean>> params) {
		List<List<ParamInCmdBean>> results = new ArrayList<List<ParamInCmdBean>>();
		for (List<ParamInCmdBean> list : params) {
			if (checkParam(list)) {
				results.add(list);
			}
		}
		return results;
	}

	/**
	 * Description: У���������б��� ���гɶԵĲ��� �Ƿ����һ��
	 * @param param
	 * @return
	 */
	private static boolean checkParam(List<ParamInCmdBean> param) {
		List<List<ParamInCmdBean>> list_father = splitByInstacneParam(param);
		for (List<ParamInCmdBean> list : list_father) {
			if (!check(list)) {
				return false;
			}
		}
		return true;

	}

	/**
	 * Description: У���������Ҫ�Ƿ�һ��
	 * @param param
	 * @return
	 */
	private static boolean check(List<ParamInCmdBean> param) {
		int index = param.get(0).getIndex();
		for (ParamInCmdBean info : param) {
			if (info.getIndex() != index) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Description: �Ѳ������� instance_param_name����
	 * @param param_group_list
	 * @return
	 */
	private static List<List<ParamInCmdBean>> splitByInstacneParam(List<ParamInCmdBean> param_group_list) {
		List<String> param_list = getInstance(param_group_list);
		List<List<ParamInCmdBean>> list_father = new ArrayList<List<ParamInCmdBean>>();
		for (String param_name : param_list) {
			List<ParamInCmdBean> list_child = new ArrayList<ParamInCmdBean>();
			for (ParamInCmdBean info : param_group_list) {
				if (info.getParam_name().equals(param_name)) {
					list_child.add(info);
				}
			}
			list_father.add(list_child);
		}
		return list_father;

	}

	/**
	 * Description: ��ȡʵ�������� ����ȥ���ظ���
	 * @param param_group_list
	 * @return
	 */
	private static List<String> getInstance(List<ParamInCmdBean> param_group_list) {
		List<String> param_list = new ArrayList<String>();
		for (ParamInCmdBean info : param_group_list) {
			String instance_param_name = info.getParam_name();
			if (!param_list.contains(instance_param_name)) {
				param_list.add(instance_param_name);
			}
		}
		return param_list;

	}

	/**
	 * Description: �滻��������
	 * @param list
	 * @param cmd
	 * @return
	 */
	private static String replaceCmd(List<ParamInCmdBean> list, String cmd) {

		String cmd_temp = cmd;
		for (ParamInCmdBean info : list) {
			String group = info.getGroup();
			String match = "";
			if (Assert.isEmpty(group)) {
				match = "\\$\\{\\s*" + info.getParam_name() + "\\s*\\}";
			} else {
				match = "\\$\\{\\s*" + info.getGroup() + "." + info.getParam_name() + "\\s*\\}";
			}
			Pattern var_regex = Pattern.compile(match);
			Matcher m = var_regex.matcher(cmd_temp);
			if (m.find()) {
				cmd_temp = m.replaceAll(info.getParam_value());
			}
		}
		return cmd_temp;

	}
	
	public static boolean judgeConfigParam(String[] cmds) {
		for (String cmd : cmds) {
			Matcher matcher = CONFIG_PARAM_PATTERN.matcher(cmd);
			if (matcher.find()) {
				return true;
			}
		}
		return false;
	}

}