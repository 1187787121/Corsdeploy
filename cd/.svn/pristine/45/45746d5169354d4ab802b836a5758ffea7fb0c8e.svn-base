/**
 * Title: TpComponentDaoService.java
 * File Description: 组件信息表
 *
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-18
 */
package com.wk.cd.module1.dao;

import java.util.List;

import com.wk.cd.module1.info.MoComponentQuoteInfo;
import com.wk.lang.Inject;

/**
 * Class description:组件信息表
 * @author AutoGen
 */
public class MoComponentQuoteDaoService {
    @Inject
    private MoComponentQuoteDao dao;

    /**
     * 根据主键查询一条记录
     * @param  info
     * @return TpComponentInfo
     */
    public MoComponentQuoteInfo getInfoByKey(MoComponentQuoteInfo info) {
        return dao.get(info);
    }

    /**
     * 根据主键查询一条记录并对记录加锁
     * @param  info
     * @return TpComponentInfo
     */
    public MoComponentQuoteInfo getInfoByKeyForUpdate(MoComponentQuoteInfo info) {
        return dao.getForUpdate(info);
    }

    /**
     * 插入一条记录
     * @param  info
     * @return int
     */
    public int insertInfo(MoComponentQuoteInfo info) {
        return dao.insert(info);
    }

    /**
     * 插入多条记录
     * @param infos
     * @return int
     */
    public int insertListInfo(List<MoComponentQuoteInfo> infos) {
        return dao.insert(infos);
    }

}