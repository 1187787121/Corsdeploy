package com.wk.cd.common.cm.dao;

import com.wk.cd.common.cm.info.CmSeqInfo;
import com.wk.cd.enu.SEQ_TYPE;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.db.DBService;
import com.wk.db.NewTransaction;
import com.wk.db.Session;
import com.wk.lang.BaseException;
import com.wk.lang.Inject;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import java.util.List;

@DBService
public class CmSeqDaoService {

	@Inject
	private CmSeqDao dao;

	public CmSeqInfo getInfoByKey(CmSeqInfo info) {
		return dao.get(info);
	}

	public CmSeqInfo getInfoByKey(String seq_name) {
		return dao.get(seq_name);
	}

	public CmSeqInfo getInfoByKeyForUpdate(CmSeqInfo info) {
		CmSeqInfo rs = dao.getForUpdate(info);
		if (rs == null) {
			throw new RecordNotFoundException().addScene("TABLE", "ÐòºÅ±í")
					.addScene("FIELD", info.getSeq_name());
		}
		return rs;
	}

	public int insertInfo(CmSeqInfo info) {
		return dao.insert(info);
	}

	@NewTransaction
	public int insertInfoForNewTran(CmSeqInfo info) {
		return dao.insert(info);
	}

	public int insertListInfo(List<CmSeqInfo> infos) {
		return dao.insert(infos);
	}

	public JaDateTime getCurrentDateTime() {
		return dao.getSession().getDBDateTime();
	}

	@NewTransaction
	public CmSeqInfo getSeqByKeyForNewTrans(String seq_name, JaDate cur_date,
			long step) {
		return getSeqByKey(seq_name, cur_date, step);
	}

	public CmSeqInfo getSeqByKey(String seq_name, JaDate cur_date, long step) {
		CmSeqInfo rs_info = new CmSeqInfo();
		rs_info.setSeq_name(seq_name);
		CmSeqInfo info = getInfoByKeyForUpdate(rs_info);

		rs_info = info.clone();
		if (info.getSeq_type() == SEQ_TYPE.PERMANENT) {
			info.setCur_bk_seq(info.getCur_bk_seq() + step);
			updateCurSeqByKey(info);
		} else if (info.getSeq_type() == SEQ_TYPE.DAILY) {
			if (cur_date.isBefor(info.getLmod_bk_date())) {
				info.setLs_bk_seq(info.getLs_bk_seq() + step);
				updateLsSeqByKey(info);
			} else if (cur_date.isAfter(info.getLmod_bk_date())) {
				info.setLmod_bk_date(cur_date);
				info.setLs_bk_seq(info.getCur_bk_seq() + step);
				info.setCur_bk_seq(step);
				updateLmodBkdateByKey(info);

				rs_info = info.clone();
				rs_info.setCur_bk_seq(0L);
			} else {
				info.setCur_bk_seq(info.getCur_bk_seq() + step);
				updateCurSeqByKey(info);
			}
		}
		return rs_info;
	}

	public CmSeqInfo getInfoByKeyNoCache(String seq_name) {
		return dao.getInfoByKeyNoCache(seq_name);
	}

	public int updateLmodBkdateByKey(CmSeqInfo info) {
		return dao.updateLmodBkdateByKey(info);
	}

	public int updateCurSeqByKey(CmSeqInfo info) {
		return dao.updateCurSeqByKey(info);
	}

	public int updateLsSeqByKey(CmSeqInfo info) {
		return dao.updateLsSeqByKey(info);
	}

	public Session getSession() {
		return dao.getSession();
	}

	@NewTransaction
	public int updateSeqBeyName(long cur_bk_seq, long ls_bk_seq, String seq_name) {
		return dao.updateSeqBeyName(cur_bk_seq, ls_bk_seq, seq_name);
	}

	public List<String> querySeqName(String seq_name) {
		return dao.querySeqName(seq_name);
	}
}