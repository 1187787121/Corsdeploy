/**
 * Title: SvSrvgDaoService.java
 * File Description: 服务组定义表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-10-10
 */
package com.wk.cd.system.sv.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.sv.info.SvSrvgInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:服务组定义表
 * @author AutoGen
 */
public class SvSrvgDaoService {
	@Inject private SvSrvgDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param SvSrvgInfo info
	 * @return SvSrvgInfo
	 */
	public SvSrvgInfo getInfoByKey(SvSrvgInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param SvSrvgInfo info
	 * @return SvSrvgInfo
	 */
	public SvSrvgInfo getInfoByKeyForUpdate(SvSrvgInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param SvSrvgInfo info
	 * @return int
	 */
	public int insertInfo(SvSrvgInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<SvSrvgInfo>
	 * @return int
	 */
	public int insertListInfo(List<SvSrvgInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 分页查询服务组信息
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	public List<SvSrvgInfo> pageSrvg(int start_recd, int limit_recd) {
		return dao.pageSrvg(start_recd,limit_recd);
	}

	/** 
	 * Description: 查询服务组信息总数
	 * @return 
	 */
	public int countSrvg() {
		return dao.countSrvg();
	}

	/** 
	 * Description: 检查服务组编码不存在
	 * @param srvg_code 
	 */
	public void checkSrvgCodeNoExist(String srvg_code) {
		int count=dao.countByCode(srvg_code);
		if(count>0){
			throw new RecordAlreadyExistException().addScene("TABLE", SvSrvgInfo.TABLECN).addScene("FIELD", srvg_code);
		}
		
	}

	/** 
	 * Description: 检查服务组名称不存在
	 * @param srvg_cn_name 
	 */
	public void checkSrvgCnNameNoExist(String srvg_cn_name) {
		int count=dao.countBySrvgCnName(srvg_cn_name);
		if(count>0){
			throw new RecordAlreadyExistException().addScene("TABLE", SvSrvgInfo.TABLECN).addScene("FIELD", srvg_cn_name);
		}
	}

	/** 
	 * Description: 检查服务组编码存在
	 * @param srvg_code 
	 */
	public void checkSrvgCodeExist(String srvg_code) {
		int count=dao.countBySrvgCode(srvg_code);
		if(count==0){
			throw new RecordNotFoundException().addScene("TABLE", SvSrvgInfo.TABLECN).addScene("FIELD", srvg_code);
		}		
	}

	/** 
	 * Description: 修改服务组信息
	 * @param info 
	 */
	public void updateSrvg(SvSrvgInfo info) {
		dao.update(info);
	}

	/** 
	 * Description: 删除服务组信息
	 * @param srvg_code 
	 */
	public void deleteSrvg(String srvg_code) {
		dao.deleteSrvg(srvg_code);
	}

	/** 
	 * Description: 查询所有服务组信息
	 * @return 
	 */
	public List<SvSrvgInfo> listAllSrvg() {
		List<SvSrvgInfo> srvg_list=new ArrayList<SvSrvgInfo>();
		DBIterator<SvSrvgInfo> iterator=dao.iteratorAllSrvg();
		try {
			while (iterator.hasNext()) {
				srvg_list.add(iterator.next());
			}
		} finally {
			iterator.close();
		}
		return srvg_list;
	}

}