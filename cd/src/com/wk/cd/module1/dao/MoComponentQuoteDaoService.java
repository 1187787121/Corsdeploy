/**
 * Title: TpComponentDaoService.java
 * File Description: �����Ϣ��
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
 * Class description:�����Ϣ��
 * @author AutoGen
 */
public class MoComponentQuoteDaoService {
    @Inject
    private MoComponentQuoteDao dao;

    /**
     * ����������ѯһ����¼
     * @param  info
     * @return TpComponentInfo
     */
    public MoComponentQuoteInfo getInfoByKey(MoComponentQuoteInfo info) {
        return dao.get(info);
    }

    /**
     * ����������ѯһ����¼���Լ�¼����
     * @param  info
     * @return TpComponentInfo
     */
    public MoComponentQuoteInfo getInfoByKeyForUpdate(MoComponentQuoteInfo info) {
        return dao.getForUpdate(info);
    }

    /**
     * ����һ����¼
     * @param  info
     * @return int
     */
    public int insertInfo(MoComponentQuoteInfo info) {
        return dao.insert(info);
    }

    /**
     * ���������¼
     * @param infos
     * @return int
     */
    public int insertListInfo(List<MoComponentQuoteInfo> infos) {
        return dao.insert(infos);
    }

}