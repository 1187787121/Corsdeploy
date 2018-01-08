/**
 * Title: DcDictDaoService.java
 * File Description: 数据字典信息表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.dc.dao;

import java.util.List;

import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.dc.info.DcDictInfo;
import com.wk.lang.Inject;

/**
 * Class description:数据字典信息表
 * @author AutoGen
 */
/**
 * Class Description:
 * @author HT
 */
public class DcDictDaoService {
	@Inject
	private DcDictDao dao;

	private static final String TD = DcDictInfo.TABLECN;

	/**
	 * 根据主键查询一条记录
	 * @param DcDictInfo info
	 * @return DcDictInfo
	 */
	public DcDictInfo getInfoByKey(DcDictInfo info) {
		DcDictInfo ninfo = dao.get(info);
		if (ninfo == null) {
			throw new RecordNotFoundException().addScene("TABLE", TD).addScene(
					"FIELD", info.getDomain_cn_name()+"("+info.getDomain_name()+")");
		}
		return ninfo;
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param DcDictInfo info
	 * @return DcDictInfo
	 */
	public DcDictInfo getInfoByKeyForUpdate(DcDictInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param DcDictInfo info
	 * @return int
	 */
	public int insertInfo(DcDictInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<DcDictInfo>
	 * @return int
	 */
	public int insertListInfo(List<DcDictInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 根据关键词到domain_name和domian_cn_name中查询结果集
	 * @param keyword 关键词	
	 * @param start_recd 起始记录数
	 * @param limit_recd 查询条数
	 * @return
	 */
	public List<DcDictInfo> pageDict(String keyword, int start_recd,
			int limit_recd) {
		return dao.pageDict(keyword, keyword, start_recd, limit_recd);
	}

	/**
	 * Description: 根据domain_name 获取数据字典详细信息
	 * @param domain_name 域名称
	 * @return
	 */
	public DcDictInfo getDictInfo(String domain_name) {
		return dao.get(domain_name);
	}

	/**
	 * Description: 修改数据字典信息
	 * @param dictInfo
	 * @return
	 */
	public int updateDict(DcDictInfo dictInfo) {
		return dao.update(dictInfo);
	}

	/**
	 * Description: 物理删除数据字典信息
	 * @param domain_name 域名称
	 */
	public int deleteDict(String domain_name) {
		return dao.delete(domain_name);
	}

	/**
	 * 检查DC_DICT表中是否存在domian_name记录，若存在抛“已存在记录”异常。
	 * @param domain_name 域名称
	 * @return
	 */
	public void checkNotExistDomainName(String domain_name) {
		if (dao.countByDomainName(domain_name) > 0) {
			throw new RecordAlreadyExistException()
					.addScene("TABLE", TD)
					.addScene("FIELD", domain_name);
		}
	}

	/**
	 * 检查DC_DICT表中是否存在domian_name记录，若不存在抛“不存在记录”异常。
	 * @param domain_name 域名称
	 * @return
	 */
	public void checkExistDomainName(String domain_name) {
		if (dao.countByDomainName(domain_name) == 0) {
			throw new RecordNotFoundException().addScene("TABLE", TD)
					.addScene("FIELD", domain_name);
		}
	}

	/**
	 * 检查DC_DICT表中是否存在domian_cn_name记录，若存在抛“已存在记录”异常。
	 * @param domain_cn_name 域中文名称
	 * @return
	 */
	public void checkNotExistDomainCnName(String domain_cn_name) {
		if (dao.countByDomainCnName(domain_cn_name) > 0) {
			throw new RecordAlreadyExistException()
					.addScene("TABLE", TD).addScene("FIELD",
							domain_cn_name);
		}
	}

	/**
	 * 检查DC_DICT表中是否存在domian_cn_name记录，若不存在抛“不存在记录”异常。
	 * @param domain_cn_name 域中文名称
	 * @return
	 */
	public void checkExistDomainCnName(String domain_cn_name) {
		if (dao.countByDomainCnName(domain_cn_name) == 0) {
			throw new RecordNotFoundException().addScene("TABLE", TD)
					.addScene("FIELD", domain_cn_name);
		}
	}

	/**
	 * Description: 获取查询总条数
	 * @param keyword 关键词
	 * @return
	 */
	public int getCount(String keyword) {
		return dao.getCount(keyword, keyword);
	}
	
}