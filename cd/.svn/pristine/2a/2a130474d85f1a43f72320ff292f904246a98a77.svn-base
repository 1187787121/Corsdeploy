package com.wk.cd.module1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wk.cd.module1.impl.DefaultEnv;
import com.wk.cd.module1.info.Param;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.module1.Env;
import com.wk.cd.module1.ParamUtil;
import com.wk.test.TestCase;

/**
 * Created by ½ªÖ¾¸Õ on 2016/11/5.
 */
public class TestParamUtil  extends TestCase {

    public void testGetParamInCode() {
        String code = "cd ${dir}";
        List<String> params = ParamUtil.getParamInCode(code);
        assertEquals(1, params.size());
        assertEquals("dir", params.get(0));

        code = "cp ${dir1} ${dir2}";
        params = ParamUtil.getParamInCode(code);
        assertEquals(2, params.size());
        assertEquals("dir1", params.get(0));
        assertEquals("dir2", params.get(1));
    }

    public void testGetParamsByNames() {
        DefaultEnv env = new DefaultEnv();
        ParamInfo info = new ParamInfo();
        info.setParam_name("dir1");
        Param param = new Param(info, "aaa");
        env.setParam(param.getName(), param);
        info = new ParamInfo();
        info.setParam_name("dir2");
        param = new Param(info, "bbb");
        env.setParam(param.getName(), param);
        info = new ParamInfo();
        info.setParam_name("dir3");
        param = new Param(info, "ccc");
        env.setParam(param.getName(), param);

        String code = "cp ${dir1} ${dir2} ${dir3}";
        List<String> param_names = ParamUtil.getParamInCode(code);
        List<Param> params = ParamUtil.getParamsByNames(param_names, env);
        assertEquals(3, params.size());
        assertEquals("dir1", params.get(0).getName());
        assertEquals("aaa", params.get(0).getSingleValue());
        assertEquals("dir2", params.get(1).getName());
        assertEquals("bbb", params.get(1).getSingleValue());
        assertEquals("dir3", params.get(2).getName());
        assertEquals("ccc", params.get(2).getSingleValue());
    }

    public void testDescartesWithGroup() {
        DefaultEnv env = new DefaultEnv();
        ParamInfo info = new ParamInfo();
        info.setParam_group("g");
        info.setParam_name("g.dir1");
        Param param = new Param(info, new String[]{"aaa", "bbb", "ccc"});
        env.setParam(param.getName(), param);
        info = new ParamInfo();
        info.setParam_name("dir2");
        param = new Param(info, new String[]{"111", "222", "333"});
        env.setParam(param.getName(), param);
        info = new ParamInfo();
        info.setParam_group("g");
        info.setParam_name("g.dir3");
        param = new Param(info, new String[]{"x", "y", "z"});
        env.setParam(param.getName(), param);

        String code = "cp ${g.dir1} ${dir2} ${g.dir3}";
        List<String> param_names = ParamUtil.getParamInCode(code);
        List<Param> params = ParamUtil.getParamsByNames(param_names, env);

        List<Map<String,String>> paramvals = ParamUtil.descartes(params);
        assertEquals(9, paramvals.size());
        Map<String,String> v = paramvals.get(0);
        assertEquals(3, v.size());
        assertEquals("aaa", v.get("g.dir1"));
        assertEquals("111", v.get("dir2"));
        assertEquals("x", v.get("g.dir3"));
        v = paramvals.get(1);
        assertEquals(3, v.size());
        assertEquals("aaa", v.get("g.dir1"));
        assertEquals("222", v.get("dir2"));
        assertEquals("x", v.get("g.dir3"));
        v = paramvals.get(2);
        assertEquals(3, v.size());
        assertEquals("aaa", v.get("g.dir1"));
        assertEquals("333", v.get("dir2"));
        assertEquals("x", v.get("g.dir3"));
        v = paramvals.get(3);
        assertEquals(3, v.size());
        assertEquals("bbb", v.get("g.dir1"));
        assertEquals("111", v.get("dir2"));
        assertEquals("y", v.get("g.dir3"));
        v = paramvals.get(4);
        assertEquals(3, v.size());
        assertEquals("bbb", v.get("g.dir1"));
        assertEquals("222", v.get("dir2"));
        assertEquals("y", v.get("g.dir3"));
        v = paramvals.get(5);
        assertEquals(3, v.size());
        assertEquals("bbb", v.get("g.dir1"));
        assertEquals("333", v.get("dir2"));
        assertEquals("y", v.get("g.dir3"));
        v = paramvals.get(6);
        assertEquals(3, v.size());
        assertEquals("ccc", v.get("g.dir1"));
        assertEquals("111", v.get("dir2"));
        assertEquals("z", v.get("g.dir3"));
        v = paramvals.get(7);
        assertEquals(3, v.size());
        assertEquals("ccc", v.get("g.dir1"));
        assertEquals("222", v.get("dir2"));
        assertEquals("z", v.get("g.dir3"));
        v = paramvals.get(8);
        assertEquals(3, v.size());
        assertEquals("ccc", v.get("g.dir1"));
        assertEquals("333", v.get("dir2"));
        assertEquals("z", v.get("g.dir3"));

        String[] values = ParamUtil.resolveParam(code.split("\n"), env);
        assertEquals("cp aaa 111 x", values[0]);
        assertEquals("cp aaa 222 x", values[1]);
        assertEquals("cp aaa 333 x", values[2]);
        assertEquals("cp bbb 111 y", values[3]);
        assertEquals("cp bbb 222 y", values[4]);
        assertEquals("cp bbb 333 y", values[5]);
        assertEquals("cp ccc 111 z", values[6]);
        assertEquals("cp ccc 222 z", values[7]);
        assertEquals("cp ccc 333 z", values[8]);
    }

    public void testTwoGroups() {
        DefaultEnv env = new DefaultEnv();
        ParamInfo info = new ParamInfo();
        info.setParam_group("g1");
        info.setParam_name("g1.dir1");
        Param param = new Param(info, new String[]{"aaa", "bbb", "ccc"});
        env.setParam(param.getName(), param);
        info = new ParamInfo();
        info.setParam_name("dir2");
        param = new Param(info, new String[]{"111", "222", "333"});
        env.setParam(param.getName(), param);
        info = new ParamInfo();
        info.setParam_group("g1");
        info.setParam_name("g1.dir3");
        param = new Param(info, new String[]{"x", "y", "z"});
        env.setParam(param.getName(), param);
        info = new ParamInfo();
        info.setParam_group("g2");
        info.setParam_name("g2.dir4");
        param = new Param(info, new String[]{"!!!", "###"});
        env.setParam(param.getName(), param);
        info = new ParamInfo();
        info.setParam_group("g2");
        info.setParam_name("g2.dir5");
        param = new Param(info, new String[]{"***", "~~~"});
        env.setParam(param.getName(), param);

        String code = "cp ${g1.dir1} ${dir2} ${g1.dir3} ${g2.dir4} ${g2.dir5}";
        String[] values = ParamUtil.resolveParam(code.split("\n"), env);
        assertEquals(18, values.length);
        assertEquals("cp aaa 111 x !!! ***", values[0]);
        assertEquals("cp aaa 222 x !!! ***", values[1]);
        assertEquals("cp aaa 333 x !!! ***", values[2]);
        assertEquals("cp bbb 111 y !!! ***", values[3]);
        assertEquals("cp bbb 222 y !!! ***", values[4]);
        assertEquals("cp bbb 333 y !!! ***", values[5]);
        assertEquals("cp ccc 111 z !!! ***", values[6]);
        assertEquals("cp ccc 222 z !!! ***", values[7]);
        assertEquals("cp ccc 333 z !!! ***", values[8]);
        assertEquals("cp aaa 111 x ### ~~~", values[9]);
        assertEquals("cp aaa 222 x ### ~~~", values[10]);
        assertEquals("cp aaa 333 x ### ~~~", values[11]);
        assertEquals("cp bbb 111 y ### ~~~", values[12]);
        assertEquals("cp bbb 222 y ### ~~~", values[13]);
        assertEquals("cp bbb 333 y ### ~~~", values[14]);
        assertEquals("cp ccc 111 z ### ~~~", values[15]);
        assertEquals("cp ccc 222 z ### ~~~", values[16]);
        assertEquals("cp ccc 333 z ### ~~~", values[17]);
    }

    public void testDescartes() {
        Env env = buildEnv();

        String code = "cp ${dir1} ${dir2} ${dir3}";
        List<String> param_names = ParamUtil.getParamInCode(code);
        List<Param> params = ParamUtil.getParamsByNames(param_names, env);

        List<Map<String,String>> paramvals = ParamUtil.descartes(params);
        assertEquals(27, paramvals.size());
        Map<String,String> v = paramvals.get(0);
        assertEquals(3, v.size());
        assertEquals("aaa", v.get("dir1"));
        assertEquals("111", v.get("dir2"));
        assertEquals("x", v.get("dir3"));
        v = paramvals.get(1);
        assertEquals(3, v.size());
        assertEquals("aaa", v.get("dir1"));
        assertEquals("111", v.get("dir2"));
        assertEquals("y", v.get("dir3"));
        v = paramvals.get(2);
        assertEquals(3, v.size());
        assertEquals("aaa", v.get("dir1"));
        assertEquals("111", v.get("dir2"));
        assertEquals("z", v.get("dir3"));
        v = paramvals.get(3);
        assertEquals(3, v.size());
        assertEquals("aaa", v.get("dir1"));
        assertEquals("222", v.get("dir2"));
        assertEquals("x", v.get("dir3"));
        v = paramvals.get(4);
        assertEquals(3, v.size());
        assertEquals("aaa", v.get("dir1"));
        assertEquals("222", v.get("dir2"));
        assertEquals("y", v.get("dir3"));
        v = paramvals.get(5);
        assertEquals(3, v.size());
        assertEquals("aaa", v.get("dir1"));
        assertEquals("222", v.get("dir2"));
        assertEquals("z", v.get("dir3"));
        v = paramvals.get(6);
        assertEquals(3, v.size());
        assertEquals("aaa", v.get("dir1"));
        assertEquals("333", v.get("dir2"));
        assertEquals("x", v.get("dir3"));
        v = paramvals.get(7);
        assertEquals(3, v.size());
        assertEquals("aaa", v.get("dir1"));
        assertEquals("333", v.get("dir2"));
        assertEquals("y", v.get("dir3"));
        v = paramvals.get(8);
        assertEquals(3, v.size());
        assertEquals("aaa", v.get("dir1"));
        assertEquals("333", v.get("dir2"));
        assertEquals("z", v.get("dir3"));
    }

    public void testReplaceParams() {
        Map<String,String> values = new HashMap<String, String>();
        values.put("dir1", "111");
        values.put("dir2", "222");
        values.put("dir3", "333");
        String code = "cp ${dir1} ${dir2} ${dir3}";
        assertEquals("cp 111 222 333", ParamUtil.replaceCmd(code, values));
    }

    public void testResolveParams() {
        Env env = buildEnv();
        String code = "cp ${dir1} ${dir2} ${dir3}";
        String[] values = ParamUtil.resolveParam(code.split("\n"), env);
        assertEquals("cp aaa 111 x", values[0]);
        assertEquals("cp aaa 111 y", values[1]);
        assertEquals("cp aaa 111 z", values[2]);
        assertEquals("cp aaa 222 x", values[3]);
        assertEquals("cp aaa 222 y", values[4]);
        assertEquals("cp aaa 222 z", values[5]);
        assertEquals("cp aaa 333 x", values[6]);
        assertEquals("cp aaa 333 y", values[7]);
        assertEquals("cp aaa 333 z", values[8]);
        assertEquals("cp bbb 111 x", values[9]);
        assertEquals("cp bbb 111 y", values[10]);
        assertEquals("cp bbb 111 z", values[11]);
        assertEquals("cp bbb 222 x", values[12]);
        assertEquals("cp bbb 222 y", values[13]);
        assertEquals("cp bbb 222 z", values[14]);
        assertEquals("cp bbb 333 x", values[15]);
        assertEquals("cp bbb 333 y", values[16]);
        assertEquals("cp bbb 333 z", values[17]);
    }

    private Env buildEnv() {
        DefaultEnv env = new DefaultEnv();
        ParamInfo info = new ParamInfo();
        info.setParam_name("dir1");
        Param param = new Param(info, new String[]{"aaa", "bbb", "ccc"});
        env.setParam(param.getName(), param);
        info = new ParamInfo();
        info.setParam_name("dir2");
        param = new Param(info, new String[]{"111", "222", "333"});
        env.setParam(param.getName(), param);
        info = new ParamInfo();
        info.setParam_name("dir3");
        param = new Param(info, new String[]{"x", "y", "z"});
        env.setParam(param.getName(), param);
        return env;
    }

    public void testRefParams() {
        List<ParamInfo> infos = new ArrayList<ParamInfo>();
        ParamInfo info1 = new ParamInfo();
        info1.setParam_name("dir1");
        ParamInfo info2 = new ParamInfo();
        info2.setParam_name("dir2");
        ParamInfo info3 = new ParamInfo();
        info3.setParam_name("dir1");
        info3.setPhase_no(0);
        info3.setRef("dir2");
        ParamInfo info4 = new ParamInfo();
        info4.setParam_group("g1");
        info4.setParam_name("g1.dir3");
        ParamInfo info5 = new ParamInfo();
        info5.setParam_group("g1");
        info5.setParam_name("g1.dir4");
        ParamInfo info6 = new ParamInfo();
        info6.setParam_group("g1");
        info6.setParam_name("g1.dir5");
        ParamInfo info7 = new ParamInfo();
        info7.setParam_group("g1");
        info7.setParam_name("g1.dir6");
        ParamInfo info8 = new ParamInfo();
        info8.setParam_group("g1");
        info8.setParam_name("g1.dir3");
        info8.setPhase_no(0);
        info8.setRef("dir5");
        ParamInfo info9 = new ParamInfo();
        info9.setParam_group("g1");
        info9.setParam_name("g1.dir4");
        info9.setPhase_no(0);
        info9.setRef("dir6");
        infos.add(info1);
        infos.add(info2);
        infos.add(info3);
        infos.add(info4);
        infos.add(info5);
        infos.add(info6);
        infos.add(info7);
        infos.add(info8);
        infos.add(info9);

        DefaultEnv env = new DefaultEnv();
        Param param1 = new Param(info1, new String[]{"aaa", "bbb", "ccc"});
        Param param2 = new Param(info2, new String[]{"111", "222", "333"});
        Param param3 = new Param(info4, new String[]{"!!!", "###"});
        Param param4 = new Param(info5, new String[]{"999", "888"});
        Param param5 = new Param(info6, new String[]{"@@@", "^^^"});
        Param param6 = new Param(info7, new String[]{"666", "777"});
        env.setParam(param1.getName(), param1);
        env.setParam(param2.getName(), param2);
        env.setParam(param3.getName(), param3);
        env.setParam(param4.getName(), param4);
        env.setParam(param5.getName(), param5);
        env.setParam(param6.getName(), param6);
        env.setParamInfos(infos.toArray(new ParamInfo[infos.size()]));

        String code = "cp ${dir1}\ncd ${dir2}";
        String[] values = ParamUtil.resolveParam(0, code.split("\n"), env);
        assertEquals("cp 111", values[0]);
        assertEquals("cp 222", values[1]);
        assertEquals("cp 333", values[2]);
        assertEquals("cd 111", values[3]);
        assertEquals("cd 222", values[4]);
        assertEquals("cd 333", values[5]);
        values = ParamUtil.resolveParam(1, code.split("\n"), env);
        assertEquals("cp aaa", values[0]);
        assertEquals("cp bbb", values[1]);
        assertEquals("cp ccc", values[2]);
        assertEquals("cd 111", values[3]);
        assertEquals("cd 222", values[4]);
        assertEquals("cd 333", values[5]);

        code = "cp ${g1.dir3} ${g1.dir4}";
        values = ParamUtil.resolveParam(0, code.split("\n"), env);
        assertEquals("cp @@@ 666", values[0]);
        assertEquals("cp ^^^ 777", values[1]);
        values = ParamUtil.resolveParam(1, code.split("\n"), env);
        assertEquals("cp !!! 999", values[0]);
        assertEquals("cp ### 888", values[1]);
    }
}
