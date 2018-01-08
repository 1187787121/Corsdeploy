/**
 * Title: BlockGenSeqService.java
 * File Description: 分段获取数据库数据生成序号类
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 1/12/2015
 */

package com.wk.cd.common.cm.service;
import com.wk.cd.common.cm.dao.CmSeqDaoService;
import com.wk.cd.common.cm.info.CmSeqInfo;
import com.wk.cd.enu.SEQ_TYPE;
import com.wk.cd.exc.DataErrorException;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;


/**
 * Class Description:分段获取数据库数据生成序号类
 * @author lixl
 */
class BlockSeqObjService {
	private CmSeqDaoService csDaos;
	private long STEPNUM;
	private JaDate lawkdt;
	private long lasq;
	private long bksq;
	private SEQ_TYPE sqmd;
	private static final Log logger = LogFactory.getLog();
	
	BlockSeqObjService(long step, CmSeqDaoService csDaos){
		this.STEPNUM= step;
		this.csDaos = csDaos;
	}

	synchronized long getNextSeqForBlock(String seq_name, JaDate cur_date)
	{
		long seq = 0L;
		// 第一次获取
		if (lawkdt == null)
			transGet(seq_name, cur_date);

		if(sqmd == SEQ_TYPE.DAILY) 
		{
			if(cur_date.isBefor(lawkdt)) 
			{
				if(!cur_date.addDay(1).equals(lawkdt))
				{
					throw new DataErrorException().
						addScene("INPUT", "只支持取上日序号: "+cur_date+"--"+lawkdt);
				}
				logger.debug("befor cur date=[{}] -- last date=[{}]", cur_date, lawkdt);
				seq = lasq + 1;
				if (seq % STEPNUM == 0)
					transGet(seq_name, cur_date);
				else
					lasq++;
				logger.debug("BlockSeqObjService lasq=[{}]--seq=[{}]", lasq, seq);
			}
			else if(cur_date.isAfter(lawkdt))
			{
				logger.debug("after cur date=[{}] -- last date=[{}]", cur_date, lawkdt);
				transGet(seq_name, cur_date);
				seq = ++bksq;
				logger.debug("BlockSeqObjService bksq=[{}]--seq=[{}]", bksq, seq);
			}
			else
			{
				seq = bksq + 1;
				if (seq % STEPNUM == 0)
					transGet(seq_name, cur_date);
				else
					bksq++;
			}
		}
		else if(sqmd == SEQ_TYPE.PERMANENT)
		{
			logger.debug("seq type =[{}]", SEQ_TYPE.PERMANENT);
			seq = bksq + 1;
			if (seq % STEPNUM == 0)
				transGet(seq_name, cur_date);
			else
				bksq++;
		}
		return seq;
		
	}

	private void transGet(String seq_name, JaDate cur_date){
		CmSeqInfo info = csDaos.getSeqByKeyForNewTrans(seq_name, cur_date, STEPNUM);
		lawkdt = info.getLmod_bk_date();
		lasq = info.getLs_bk_seq();
		bksq = info.getCur_bk_seq();
		sqmd = info.getSeq_type();
		logger.debug("tranGet bkseq=[{}]--lasq=[{}]--STEPNUM=[{}]", bksq, lasq, STEPNUM);
	}

}

