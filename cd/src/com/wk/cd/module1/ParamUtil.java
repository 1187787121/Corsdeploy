package com.wk.cd.module1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wk.cd.module1.exc.CmdParamIsNotExistException;
import com.wk.cd.module1.info.Param;
import com.wk.cd.module1.Env;

/**
 * Created by ��־�� on 2016/11/5.
 */
public final class ParamUtil {

    public static String[] resolveParam(String[] cmds, Env env) {
        return resolveParam(-1, cmds, env);
    }

    public static String[] resolveParam(int stage, String[] cmds, Env env) {
        final List<String> codes = new ArrayList<String>();
        for (String cmd : cmds) {
            final List<String> param_names = getParamInCode(cmd);
            if (param_names.size() > 0) {
                final List<Param> params = getParamsByNames(stage, param_names, env);
                final List<Map<String,String>> param_values = descartes(params);
                for (Map<String,String> values : param_values) {
                    codes.add(replaceCmd(cmd, values));
                }
            } else {
                codes.add(cmd);
            }
        }
        return codes.toArray(new String[codes.size()]);
    }

    /**
     * Description: �滻��������
     * @param cmd
     * @param param_values
     * @return
     */
    public static String replaceCmd(String cmd, Map<String, String> param_values) {
        String cmd_temp = cmd;
        for (Map.Entry<String,String> entry : param_values.entrySet()) {
            String var = entry.getKey();
            String match = "\\$\\{\\s*" + var + "\\s*\\}";
            Pattern var_regex = Pattern.compile(match);
            Matcher m = var_regex.matcher(cmd_temp);
            if (m.find()) {
                cmd_temp = m.replaceAll(entry.getValue());
            }
        }
        return cmd_temp;

    }

    /**
     * Description: �������� ���еѿ����˻�
     * @param params
     * @return
     */
    public static List<Map<String,String>> descartes(List<Param> params) {
        List<Map<String, String>> results = new ArrayList<Map<String, String>>();

        //�ȴ����������
        Set<String> groups = findGroups(params);
        for (String group : groups) {
            results = descartesOne(results, toGroupListMap(findGroupParams(group, params)));
        }
        // ���ɵѿ�������ÿ������
        for (int i=0; i<params.size(); i++) {
            Param p = params.get(i);
            if (p.hasGroup()) {
                continue;
            }
            results = descartesOne(results, params.get(i));
        }
        return results;
    }

    public static List<Map<String,String>> descartesOne(List<Map<String,String>> a, Param b) {
        return descartesOne(a, toListMap(b.getName(), b.getListValue()));
    }

    private static Set<String> findGroups(List<Param> params) {
        Set<String> names = new HashSet<String>();
        for (int i=0; i<params.size(); i++) {
            Param p = params.get(i);
            if (p.hasGroup()) {
                names.add(p.getInfo().getParam_group());
            }
        }
        return names;
    }

    private static List<Param> findGroupParams(String group, List<Param> params) {
        List<Param> gps = new ArrayList<Param>();
        for (int i=0; i<params.size(); i++) {
            Param p = params.get(i);
            if (group.equals(p.getInfo().getParam_group())) {
                gps.add(p);
            }
        }
        return gps;
    }

    private static List<Map<String,String>> toGroupListMap(List<Param> params) {
        checkGroupParamLength(params);

        int length = params.get(0).size();

        final List<Map<String, String>> results = new ArrayList<Map<String, String>>();
        for (int i = 0; i < length; i++) {
            Map<String, String> m = new HashMap<String, String>();
            for (int j = 0; j < params.size(); j++) {
                Param p = params.get(j);
                m.put(p.getName(), p.getListValue()[i]);
            }
            results.add(m);
        }
        return results;
    }

    private static List<Map<String, String>> toListMap(String name, String[] lv) {
        final List<Map<String, String>> results = new ArrayList<Map<String, String>>();
        for (int i = 0; i < lv.length; i++) {
            Map<String, String> m = new HashMap<String, String>();
            m.put(name, lv[i]);
            results.add(m);
        }
        return results;
    }

    public static List<Map<String,String>> descartesOne(List<Map<String,String>> a, List<Map<String,String>> b) {
        if (a == null || a.size() <= 0) {
            return b;
        }

        final List<Map<String, String>> results = new ArrayList<Map<String, String>>();
        for (int i = 0; i < a.size(); i++) {
            Map<String, String> row = a.get(i);
            for (int j = 0; j < b.size(); j++) {
                Map<String, String> m = b.get(j);
                Map<String, String> rownew = new HashMap<String, String>();
                rownew.putAll(row);
                rownew.putAll(m);
                results.add(rownew);
            }
        }
        return results;
    }

    /**
     * TODO: ����������ȱ���һ��
     * @param params
     */
    public static void checkGroupParamLength(List<Param> params) {
    }

    public static List<Param> getParamsByNames(List<String> param_names, Env env) {
        return getParamsByNames(-1, param_names, env);
    }

    public static List<Param> getParamsByNames(int stage, List<String> param_names, Env env) {
        List<Param> param_list = new ArrayList<Param>();
        // ������������Ҫʹ�õ��Ĳ���
        for (String name : param_names) {
            Param param = env.getParam(stage, name);
            param = param == null ? env.getParam(name): param;
            if (param == null) {
                throw new CmdParamIsNotExistException().addScene("PARAM", name);
            }
            param_list.add(param);
        }
        return param_list;
    }

    public static String resolveGroup(String name) {
        int idx = name.lastIndexOf('.');
        return idx>0 ? name.substring(0, idx) : "";
    }

    /**
     * Description: ��ȡ�����в����б�
     * @param code
     * @return
     */
    public static List<String> getParamInCode(String code) {
        List<String> param_list = new ArrayList<String>();
        // �������ʽ ƥ�䡰${ was.was }�������������пո���п���
        String match_group = "\\$\\{\\s*\\w+\\.\\w+\\s*\\}";
        Pattern regex_group = Pattern.compile(match_group);
        Matcher m_group = regex_group.matcher(code);
        // ��ȡ������ƥ���������ʽ�Ĳ������뵽�б���
        while (true) {
            if (!m_group.find()) {
                break;
            }
            String param_group = m_group.group().substring(2,
                    m_group.group().length() - 1);
            if (!param_list.contains(param_group)) {
                param_list.add(param_group.trim());
            }

        }
        // �������ʽ ƥ�䡰${ was }�������������пո���п���
        String match_single = "\\$\\{\\s*\\w+\\s*\\}";
        Pattern regex_single = Pattern.compile(match_single);
        Matcher m_single = regex_single.matcher(code);
        // ��ȡ������ƥ���������ʽ�Ĳ������뵽�б���
        while (true) {
            if (!m_single.find()) {
                break;
            }
            String param_single = m_single.group().substring(2,
                    m_single.group().length() - 1);
            if (!param_list.contains(param_single)) {
                param_list.add(param_single.trim());
            }

        }
        return param_list;
    }
}