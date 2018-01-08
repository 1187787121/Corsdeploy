/**
 * Title: PhasePublishBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016��11��17��
 */
package com.wk.cd.build.ea.bean;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.module1.bean.PhaseTestBean;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 
 * @author Zhangj
 */
public class PhasePublishBean {
	
	private static final Log logger = LogFactory.getLog();
	/**
	 * �׶κ�
	 */
	private int phase_no; 

	public static final String PHASE_NOCN="�׶κ�";
	
	/**
	 * �׶���
	 */
   	private String cn_name; 
   	
   	public static final String CN_NAMECN = "�׶���";
   	
   	/**
   	 * �Ƿ����ɱ�־
   	 */
   	private YN_FLAG gen_flag;
   	
   	public static final String GEN_FLAGCN = "�Ƿ����ɱ�ʾ";
   	
   	/**
   	 * ʵ�����
   	 */
   	private IMPL_TYPE impl_type; 
   	
   	public static final String TYPECN = "ʵ�����";
   	
   	/**
   	 * �����б�
   	 */
   	private String[] cmds;
   	
   	public static final String CMDSCN="�����б�";
   	
   	/**
   	 * ������ip������Դ
   	 */
	private	SrvSocBean[]srv_soc;
	
	public static final String SRV_SOCCN="������ip������Դ";

	/**
	 * @return phase_no
	 */
	public int getPhase_no() {
		return phase_no;
	}

	/**
	 * @param phase_no
	 */
	public void setPhase_no(int phase_no) {
		this.phase_no = phase_no;
	}

	/**
	 * @return cn_name
	 */
	public String getCn_name() {
		return cn_name;
	}

	/**
	 * @param cn_name
	 */
	public void setCn_name(String cn_name) {
		this.cn_name = cn_name;
	}
	/**
	 * @return gen_flag
	 */
	public YN_FLAG getGen_flag() {
		return this.gen_flag;
	}

	/**
	 * @param gen_flag
	 */
	public void setGen_flag(YN_FLAG gen_flag) {
		this.gen_flag = gen_flag;
	}

	/**
	 * @return impl_type
	 */
	public IMPL_TYPE getImpl_type() {
		return impl_type;
	}

	/**
	 * @param impl_type
	 */
	public void setImpl_type(IMPL_TYPE impl_type) {
		this.impl_type = impl_type;
	}

	/**
	 * @return cmds
	 */
	public String[] getCmds() {
		return cmds;
	}

	/**
	 * @param cmds
	 */
	public void setCmds(String[] cmds) {
		this.cmds = cmds;
	}

	/**
	 * @return srv_soc
	 */
	public SrvSocBean[] getSrv_soc() {
		return srv_soc;
	}

	/**
	 * @param srv_soc
	 */
	public void setSrv_soc(SrvSocBean[] srv_soc) {
		this.srv_soc = srv_soc;
	}
	
	public static PhaseTestBean copyToTest(PhasePublishBean bean){
		PhaseTestBean ptb = new PhaseTestBean ();
		ptb.setCmds(bean.getCmds());
		ptb.setCn_name(bean.getCn_name());
		ptb.setGen_flag(bean.getGen_flag());
		ptb.setImpl_type(bean.getImpl_type());
		ptb.setSrv_soc(bean.getSrv_soc());
		logger.debug("��ǰʵ������[{}]",ptb.getImpl_type());
		return ptb;
		
	}
	
	public static List<PhaseTestBean> copyList(List<PhasePublishBean> beans){
		logger.debug("PhasePublishBean _ copy_list begin");
		List<PhaseTestBean> list = new ArrayList<PhaseTestBean>();
		for(PhasePublishBean bean : beans){
			list.add(copyToTest(bean));
		}
		logger.debug("PhasePublishBean _ copy_list end");
		return list;
	}
	
}