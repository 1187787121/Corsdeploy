package com.wk.cd.module1;

import java.util.List;
import java.util.Map;

import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.info.Param;
import com.wk.cd.module1.info.ParamInfo;

/**
 * 流程运行环境
 *
 * 包括：运行参数和运行数据源
 *
 * Created by 姜志刚 on 2016/11/5.
 */
public interface Env {

    /**
     * 设置参数定义信息
     * @param infos
     */
    void setParamInfos(ParamInfo[] infos);
    /**
     * 获取参数列表
     */
    Map<String, Param> getParams();

    /**
     * 获取阶段引用参数
     * @param stage 阶段号
     * @param name 参数名
     * @return
     */
    Param getParam(int stage, String name);
    /**
     * 获取参数
     * @param name 参数名
     * @return
     */
    Param getParam(String name);
    /**
     * 设置参数
     * @param name 参数名
     * @param param 参数
     */
    void setParam(String name, Param param);
    /**
     * 设置阶段对应的数据源
     * @param stage 阶段号
     * @param soc_info 数据源Info
     */
    void addStageNode(int stage, ModuleSourceInfo soc_info);
    /**
     * 获取阶段对应的数据源列表
     * @param stage 阶段号
     * @return
     */
    List<ModuleSourceInfo> getStageNodes(int stage);
}
