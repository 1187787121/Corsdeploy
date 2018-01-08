/**
 * Title: TmTermDaoService.java
 * File Description: 终端配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-1-5
 */
package com.wk.cd.system.tm.dao;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.TERM_TYPE;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.tm.info.TmTermInfo;
import com.wk.lang.Inject;

/**
 * Class description:终端配置表
 * @author AutoGen
 */
public class TmTermDaoService {
	private static final String WDC = "*";
	private static final String APP_TERM_NO = "android_id";
	@Inject
	private TmTermDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param TmTermInfo info
	 * @return TmTermInfo
	 */
	public TmTermInfo getInfoByKey(TmTermInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param TmTermInfo info
	 * @return TmTermInfo
	 */
	public TmTermInfo getInfoByKeyForUpdate(TmTermInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param TmTermInfo info
	 * @return int
	 */
	public int insertInfo(TmTermInfo info) {
		return dao.insert(info);
	}

	/**
	 * 修改一条记录
	 * @param info
	 * @return
	 */
	public int updateInfo(TmTermInfo info) {
		return dao.update(info);
	}

	/**
	 * 插入多条记录
	 * @param List<TmTermInfo>
	 * @return int
	 */
	public int insertListInfo(List<TmTermInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 删除一条记录
	 * @param info
	 * @return
	 */
	public int deleteInfo(TmTermInfo info) {
		if (Assert.isEmpty(dao.get(info))) {
			throw new RecordNotFoundException().addScene("TABLE", TmTermInfo.TABLECN).addScene("FIELD", info.getTerm_no());
		}
		return dao.delete(info);
	}

	/**
	 * 枷锁读一条记录，如果没有找到记录把报错
	 * @param info 终端号
	 * @return
	 */
	public TmTermInfo getTermInfoForUpdate(TmTermInfo info) {
		TmTermInfo info1 = dao.getForUpdate(info);
		if (Assert.isEmpty(info1)) {
			throw new RecordNotFoundException().addScene("TABLE", TmTermInfo.TABLECN).addScene("FIELD", info.getTerm_no());
		}
		return info1;
	}

	/**
	 * 分页查询终端配置
	 * @param term_no 终端号
	 * @param term_type 终端类型
	 * @param term_bk_desc 终端描述
	 * @param start_recd 起始条数
	 * @param limit_recd 查询条数
	 * @return 终端列表信息
	 */
	public List<TmTermInfo> pageInfo(String term_no, TERM_TYPE term_type, String term_bk_desc, int start_recd, int limit_recd) {
		return dao.pageInfo(term_no, term_type, term_bk_desc, start_recd, limit_recd);
	}

	/**
	 * Description: 满足条件的终端信息条数
	 * @param term_no
	 * @param term_type
	 * @param term_bk_desc
	 * @return
	 */
	public int countInfo(String term_no, TERM_TYPE term_type, String term_bk_desc) {
		return dao.countInfo(term_no, term_type, term_bk_desc);
	}

	/**
	 * 查询终端详细信息
	 * @param info 终端号
	 * @return 详细信息
	 */
	public TmTermInfo getInfoByTermNo(TmTermInfo info) {
		TmTermInfo info1 = dao.get(info);
		if (Assert.isEmpty(info1)) {
			throw new RecordNotFoundException().addScene("TABLE", TmTermInfo.TABLECN).addScene("FIELD", info.getTerm_no());
		}
		return info1;
	}

	/**
	 * 检查终端
	 * @param term_no 终端号 以后的终端类型不同！！存入的不一定是ip地址，则此时程序断不正确
	 * 如果不满足IP段管理，可以是全包含，修改了判断规则2017-07-28
	 */
	/*
	 * public void checkTermTypeN(String term_no){
	 * Assert.assertNotEmpty(term_no, "终端号");
	 * 
	 * DBIterator<TmTermInfo> its_tm = dao.iteratorTmAllTerm(); try{ boolean
	 * hasc= false; while(its_tm.hasNext()) { String tm_no =
	 * its_tm.next().getTerm_no(); if(hasContain(tm_no,term_no)){ hasc = true;
	 * break; } String[] cf = tm_no.split("\\."); String[] tf =
	 * term_no.split("\\."); if(cf.length != 4){ continue; } if(cf.length != 4
	 * || tf.length != 4) { String err = tm_no; if(cf.length != 4) { err =
	 * "终端表[" + tm_no + "]"; } throw new DataErrorException().addScene("INPUT",
	 * err); } boolean ok = true; for(int i = 0; i < tf.length; i++) { ok = ok
	 * && hasContain(cf[i], tf[i]); if(!ok) { break; } } if(ok) { hasc = true;
	 * break; } } if(!hasc) { throw new
	 * IllegalTermLoginException().addScene("TERM_NO", term_no); } }finally{
	 * its_tm.close(); } }
	 */

	/**
	 * 检查终端
	 * @param term_no 终端号 以后的终端类型不同！！存入的不一定是ip地址，则此时程序断不正确
	 */
	public void checkTermTypeN(String term_no) {
		Assert.assertNotEmpty(term_no, "终端号");
		if(APP_TERM_NO.equals(term_no)){
			return;
		}
		String[] tf = term_no.split("\\.");
		if (tf.length != 4) {
			String err = term_no;
			if (tf.length != 4) {
				err = "终端表[" + term_no + "]";
			}
			throw new DataErrorException().addScene("INPUT", err);
		}
	}

	private boolean hasContain(String fs, String fd) {
		boolean b = fs.equals(fd) || fs.equals(WDC);
		return b;
	}

	/**
	 * Description: 检查终端存在
	 * @param term_no
	 */
	public void checkTermExist(String term_no) {
		int count = dao.countByTermNo(term_no);
		if (count == 0) {
			throw new RecordNotFoundException().addScene("TABLE", TmTermInfo.TABLECN).addScene("FIELD", term_no);
		}
	}

	/**
	 * Description: 终端号是否存在
	 * @param term_no
	 * @return
	 */
	public boolean isTermExist(String term_no) {
		int count = dao.countByTermNo(term_no);
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

}