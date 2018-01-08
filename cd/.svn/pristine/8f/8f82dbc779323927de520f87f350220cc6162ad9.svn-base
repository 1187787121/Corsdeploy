/**
 * Title: ActionRootOutputBean.java
 * File Description:  服务输出接口基类
 * @copyright 2014
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/7/2014
 */

package com.wk.cd.bean;

import com.wk.cd.enu.APROV_TYPE;
import com.wk.cd.enu.SVDEAL_TYPE;
import com.wk.sdo.ServiceData;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description:服务输出接口基类
 * @author lixl
 */
public class ActionRootOutputBean extends RequestRootOutputBean{
	private static final long serialVersionUID = 9066144690361105669L;

	/**
     * 深度拷贝 构造函数
     * @param bean 
     */
	public ActionRootOutputBean(ActionRootOutputBean bean){
		super(bean);
		this.dtbs_bk_date = bean.getDtbs_bk_date();
		this.dtbs_bk_time = bean.getDtbs_bk_time();
		this.work_seq = bean.getWork_seq();
	}
		
	/**
	 * 构造函数
	 */
	public ActionRootOutputBean(){
	}

   /**
    * 系统流水号
    */
    private String work_seq;
    
    public static final String WORK_SEQCN = "系统流水号";
    
    /**
     * 待处理流水号
     */
    private String pend_work_seq;
    
    public static final String PEND_WORK_SEQCN = "待处理流水号";

    /**
     * 数据库日期
     */
    private JaDate dtbs_bk_date;
    
    public static final String DTBS_BK_DATECN = "数据库日期";

    /**
    * 数据库时间
    */
    private JaTime dtbs_bk_time;
    
    public static final String DTBS_BK_TIMECN = "数据库时间";
	
	/**
	 * 待处理服务名
	 */
	private String pend_srv_name;
	
	public static final String PEND_SRV_NAMECN="待处理服务名";
	
	/**
	 * 授权序号
	 */
	private Integer auth_seq;
	
	public static final String AUTH_SEQCN = "授权序号";
	
	/**
	 * 授权部门角色编号
	 */
	private String auth_dprl_code;
	
	public static final String AUTH_DPRL_CODECN = "授权部门角色编号";
	
	/**
	 * 授权部门角色名称
	 */
	private String authdprl_bk_expl;
	
	public static final String AUTHDPRL_BK_EXPLCN = "授权部门角色名称";
	
	/**
	 * 服务处理类型
	 */
	private SVDEAL_TYPE svdeal_type;
	
	public static final String svdeal_typecn = "服务处理类型";
	
	/**
	 *待处理任务说明
	 */
	private String pendwk_bk_expl;

	public static final String PENDWK_BK_EXPLCN = "待处理任务说明";
	
	/**
	 *审批展示类型
	 */
	private APROV_TYPE aprov_type;

	public static final String APROV_TYPECN = "审批展示类型";
	
	/**
	 * 附加数据
	 */
	private ServiceData req_data;
	
	public static final String REQ_DATACN = "附加数据";
	
	/**
	 * @return work_seq 系统流水号
	 */
	public String getWork_seq() {
		return this.work_seq;
	}

	/**
	 * @param workSeq 系统流水号
	 */
	public void setWork_seq(String workSeq) {
		this.work_seq = workSeq;
	}
	
	/**
	 * 待处理流水号
	 * @return
	 */
	public String getPend_work_seq(){
		return this.pend_work_seq;
	}
	
	/**
	 * 待处理流水号
	 * @param pend_work_seq 
	 */
	public void setPend_work_seq(String pend_work_seq){
		this.pend_work_seq = pend_work_seq;
	}
	
	/**
	 * 授权关键字
	 */
	private String auth_key;
	
	public static final String AUTH_KEYCN = "授权关键字";

	/**
	 * @return dtbs_bk_date 数据库日期
	 */
	public JaDate getDtbs_bk_date() {
		return this.dtbs_bk_date;
	}

	/**
	 * @param dtbsBkDate 数据库日期
	 */
	public void setDtbs_bk_date(JaDate dtbsBkDate) {
		this.dtbs_bk_date = dtbsBkDate;
	}

	/**
	 * @return dtbs_bk_time 数据库时间
	 */
	public JaTime getDtbs_bk_time() {
		return this.dtbs_bk_time;
	}

	/**
	 * @param dtbsBkTime 数据库时间
	 */
	public void setDtbs_bk_time(JaTime dtbsBkTime) {
		this.dtbs_bk_time = dtbsBkTime;
	}

	/**
	 * @return auth_seq 授权序号
	 */
	public Integer getAuth_seq() {
		return this.auth_seq;
	}

	/**
	 * @param auth_seq 授权序号
	 */
	public void setAuth_seq(Integer auth_seq) {
		this.auth_seq = auth_seq;
	}

	/**
	 * @return auth_dprl_code 授权部门角色编号
	 */
	public String getAuth_dprl_code() {
		return this.auth_dprl_code;
	}

	/**
	 * @param auth_dprl_code 授权部门角色编号
	 */
	public void setAuth_dprl_code(String auth_dprl_code) {
		this.auth_dprl_code = auth_dprl_code;
	}

	/**
	 * @return authdprl_bk_expl 授权部门角色名称
	 */
	public String getAuthdprl_bk_expl() {
		return this.authdprl_bk_expl;
	}

	/**
	 * @param authdprl_bk_expl 授权部门角色名称
	 */
	public void setAuthdprl_bk_expl(String authdprl_bk_expl) {
		this.authdprl_bk_expl = authdprl_bk_expl;
	}
	

	/**
	 * @return pend_srv_name 待处理服务名
	 */
	public String getPend_srv_name() {
		return this.pend_srv_name;
	}

	/**
	 * @param pend_srv_name 待处理服务名
	 */
	public void setPend_srv_name(String pend_srv_name) {
		this.pend_srv_name = pend_srv_name;
	}

	/**
	 * @return svdeal_type 服务处理类型
	 */
	public SVDEAL_TYPE getSvdeal_type() {
		return svdeal_type;
	}

	/**
	 * @param svdeal_type 服务处理类型
	 */
	public void setSvdeal_type(SVDEAL_TYPE svdeal_type) {
		this.svdeal_type = svdeal_type;
	}
	
	/**
	 * @return pendwk_bk_expl 待处理任务说明
	 */
	public String getPendwk_bk_expl() {
		return pendwk_bk_expl;
	}

	/**
	 * @param pendwk_bk_expl 待处理任务说明
	 */
	public void setPendwk_bk_expl(String pendwk_bk_expl) {
		this.pendwk_bk_expl = pendwk_bk_expl;
	}
	
	/**
	 * @return aprov_type 审批展示类型
	 */
	public APROV_TYPE getAprov_type() {
		return aprov_type;
	}

	/**
	 * @param aprov_type 审批展示类型
	 */
	public void setAprov_type(APROV_TYPE aprov_type) {
		this.aprov_type = aprov_type;
	}
	
	/**
	 * @return auth_key 授权关键字
	 */
	public String getAuth_key() {
		return auth_key;
	}

	/**
	 * @param auth_key 授权关键字
	 */
	public void setAuth_key(String auth_key) {
		this.auth_key = auth_key;
	}
	
	/**
	 * @return req_data 附加数据
	 */
	public ServiceData getReq_data() {
		return req_data;
	}

	/**
	 * @param req_data 附加数据
	 */
	public void setReq_data(ServiceData req_data) {
		this.req_data = req_data;
	}

	@Override
	public ActionRootOutputBean clone() {
		return (ActionRootOutputBean) super.clone();
	}
}
