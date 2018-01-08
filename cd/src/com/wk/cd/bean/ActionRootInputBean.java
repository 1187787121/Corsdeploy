/**
 * Title: ActionRootInputBean.java
 * File Description:  服务输入接口基类
 * @copyright 2014
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/7/2014
 */
package com.wk.cd.bean;

import com.wk.cd.common.util.BeanTool;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.sdo.ServiceData;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description:服务输入接口基类
 * @author lixl
 */
public class ActionRootInputBean extends RequestRootInputBean {
	private static final long serialVersionUID = -8027912942525222143L;

	/**
     * 深度拷贝 构造函数
     * @param bean 
     */
    public ActionRootInputBean(ActionRootInputBean bean){
    	super(bean);
    	this.dtbs_bk_date = bean.getDtbs_bk_date();
    	this.dtbs_bk_time = bean.getDtbs_bk_time();
    	this.orglst_soc_name = bean.getOrglst_soc_name();
    	this.pendlst_soc_name = bean.getPendlst_soc_name();
    	this.org_dept_id = bean.getOrg_dept_id();
    	this.org_rs_code = bean.getOrg_rs_code();
    	this.org_srv_name = bean.getOrg_srv_name();
    	this.org_term_no = bean.getOrg_term_no();
    	this.org_user_id = bean.getOrg_user_id();
    	this.org_work_code = bean.getOrg_work_code();
    	this.pbl_code = bean.getPbl_code();
    	this.pend_rs_code = bean.getPend_rs_code();
    	this.pend_srv_name = bean.getPend_srv_name();
    	this.pend_work_code = bean.getPend_work_code();
    	this.pend_work_seq = bean.getPend_work_seq();
    	this.work_seq = bean.getWork_seq();
    	this.br_sbsbno = bean.getBr_sbsbno();
    	this.br_sbsbtp = bean.getBr_sbsbtp();
    	this.tl_tltelr = bean.getTl_tltelr();
    	this.bk_desc = bean.getBk_desc();
    	this.apply_bk_expl = bean.getApply_bk_expl();
    	this.apply_html = bean.getApply_html();
    	this.req_data = bean.getReq_data();
    }

    /**
     * 构造函数
     */
    public ActionRootInputBean(){
    }

	/**
	 * 提交类型
	 */
	private transient SUBMIT_TYPE submit_type;

	public static final String SUBMIT_TYPECN = "提交类型";

    /**
     * 发起用户
     */ 
    private transient String org_user_id;
    
    public static final String ORG_USER_IDCN = "发起用户";

	/**
	 * 发起用户姓名
	 */
	private transient String orguser_cn_name;

	public static final String ORGUSER_CN_NAMECN = "发起用户姓名";

	/**
	 * 代理用户
	 */
	private transient String proxy_user_id;

	public static final String PROXY_USER_IDCN = "代理用户";

    
    /**
     * 发起终端
     */ 
    private transient String org_term_no;
 
	public static final String ORG_TERM_NOCN = "发起终端";

    /**
    * 发起部门
    */
    private transient String org_dept_id;
    
    public static final String ORG_DEPT_IDCN = "发起部门";

	/**
	 * 发起部门简称
	 */
	private transient String orgdept_cn_name;

	public static final String ORGDEPT_CN_NAMECN = "发起部门简称";

    /**
    * 系统流水号
    */
    private transient String work_seq;
    
    public static final String WORK_SEQCN = "系统流水号";

    /**
     * 数据库日期
     */
    private transient JaDate dtbs_bk_date;
    
    public static final String DTBS_BK_DATECN = "数据库日期";

    /**
    * 数据库时间
    */
    private transient JaTime dtbs_bk_time;
    
    public static final String DTBS_BK_TIMECN = "数据库时间";

	/**
	* 问题单编号
	*/
	private String pbl_code;

	public static final String PBL_CODECN = "问题单编号";

	/**
	 * 发起任务编码
	 */
	private String org_work_code;

	public static final String ORG_WORK_CODECN = "发起任务编码";

	/**
	 * 发起服务名称
	 */
	private String org_srv_name;

	public static final String ORG_SRV_NAMECN = "发起服务名称";

	/**
	 * 发起资源编码
	 */
	private String org_rs_code;

	public static final String ORG_RS_CODECN = "发起资源编码";

	/**
	 * 发起数据源列表
	 */
	private String[] orglst_soc_name;

	public static final String ORGLST_SOC_NAMECN = "发起数据源列表";

    /**
     * 待处理任务流水号
     */
    private String pend_work_seq;
    
    public static final String PEND_WORK_SEQCN = "待处理任务流水号";

    /**
     * 待处理任务编码
     */
    private String pend_work_code;
    
    public static final String PEND_WORK_CODECN = "待处理任务编码";

    /**
     * 待处理服务名称
     */
    private String pend_srv_name;
    
    public static final String PEND_SRV_NAMECN = "待处理服务名称";
    
    /**
     * 待处理资源编码
     */
    private String pend_rs_code;
    
    public static final String PEND_RS_CODECN = "待处理资源编码";

    /**
     * 待处理数据源名称
     */
    private String[] pendlst_soc_name;
 
	public static final String PENDLST_SOC_NAMECN = "待处理数据源名称";

	/**
	 * 待处理任务说明
	 */
	private String pendwk_bk_expl;

	public static final String PENDWK_BK_EXPLCN = "待处理任务说明";
	
	/**
	 * 柜员号
	 */
	private String tl_tltelr;
	
	public static final String TL_TLTELRCN = "柜员号";
	
	/**
	 * 机构号
	 */
	private String br_sbsbno;
	
	public static final String BR_SBSBNOCN = "机构号";
	
	/**
	 * 网点类型
	 */
	private String br_sbsbtp;
	
	public static final String BR_SBSBTPCN = "网点类型";

	/**
	 * 客户端IP
	 */
	private String remote_ip;

	public static final String REMOTE_IPCN = "客户端IP";

	/**
	 * 服务端IP
	 */
	private String server_name;

	public static final String SERVER_NAMECN = "服务端IP";

	/**
	 * 服务端端口
	 */
	private int server_port;

	public static final String SERVER_PORTCN = "服务端端口";
	
	/**
	 * 任务申请说明
	 */
	private String apply_bk_expl;
	
	public static final String APPLY_BK_EXPLCN = "任务申请说明";
	
	/**
	 *申请页面代码
	 */
	private String apply_html;

	public static final String APPLY_HTMLCN = "申请页面代码";
	
	/**
	 * 备用
	 */
	private String bk_desc;

	public static final String BK_DESCCN = "备用";
	
	/**
	 * 附加数据
	 */
	private ServiceData req_data;
	
	public static final String REQ_DATACN = "附加数据";

	/**
	 * @return SUBMIT_TYPE 提交类型
	 */
	public SUBMIT_TYPE getSubmit_type(){
		return this.submit_type;
	}

	/**
	 * @param submit_type 提交类型
	 */
	public void setSubmit_type(SUBMIT_TYPE submit_type){
		this.submit_type = submit_type;
	}

	/**
	 * @return org_work_code 发起任务编码
	 */
	public String getOrg_work_code() {
		return this.org_work_code;
	}

	/**
	 * @param orgWorkCode 发起任务编码
	 */
	public void setOrg_work_code(String orgWorkCode) {
		this.org_work_code = orgWorkCode;
	}

	/**
	 * @return org_srv_name 发起服务名称
	 */
	public String getOrg_srv_name() {
		return this.org_srv_name;
	}

	/**
	 * @param orgSrvName 发起服务名称
	 */
	public void setOrg_srv_name(String orgSrvName) {
		this.org_srv_name = orgSrvName;
	}

	/**
	 * @return org_rs_code 发起资源编码
	 */
	public String getOrg_rs_code() {
		return this.org_rs_code;
	}

	/**
	 * @param orgRsCode 发起资源编码
	 */
	public void setOrg_rs_code(String orgRsCode) {
		this.org_rs_code = orgRsCode;
	}

	/**
	 * @return lstorg_soc_name 发起数据源列表
	 */
	public String[] getOrglst_soc_name() {
		return this.orglst_soc_name;
	}

	/**
	 * @param lstorgSocName 发起数据源列表
	 */
	public void setOrglst_soc_name(String[] orgLstSocName) {
		this.orglst_soc_name = orgLstSocName;
	}

	/**
	 * @return org_user_id 发起用户
	 */
	public String getOrg_user_id() {
		return this.org_user_id;
	}

	/**
	 * @param orgUserId 发起用户
	 */
	public void setOrg_user_id(String orgUserId) {
		this.org_user_id = orgUserId;
	}

	/**
	 * @return String 代理用户
	 */
	public String getProxy_user_id(){
		return this.proxy_user_id;
	}

	/**
	 * @param proxy_user_id 代理用户
	 */
	public void setProxy_user_id(String proxy_user_id){
		this.proxy_user_id = proxy_user_id;
	}

	/**
	 * @return org_term_no 发起终端
	 */
	public String getOrg_term_no() {
		return this.org_term_no;
	}

	/**
	 * @param orgTermNo 发起终端
	 */
	public void setOrg_term_no(String orgTermNo) {
		this.org_term_no = orgTermNo;
	}

	/**
	 * @return org_dept_id 发起部门
	 */
	public String getOrg_dept_id() {
		return this.org_dept_id;
	}

	/**
	 * @param orgDeptId 发起部门
	 */
	public void setOrg_dept_id(String orgDeptId) {
		this.org_dept_id = orgDeptId;
	}

	/**
	 * @return pbl_code 问题单编码
	 */
	public String getPbl_code() {
		return this.pbl_code;
	}

	/**
	 * @param pblCode 问题单编码
	 */
	public void setPbl_code(String pblCode) {
		this.pbl_code = pblCode;
	}

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
	 * @return pend_work_seq 待处理流水
	 */
	public String getPend_work_seq() {
		return this.pend_work_seq;
	}

	/**
	 * @param pendWorkSeq 待处理流水
	 */
	public void setPend_work_seq(String pendWorkSeq) {
		this.pend_work_seq = pendWorkSeq;
	}

	/**
	 * @return pend_work_code 待处理任务编码
	 */
	public String getPend_work_code() {
		return this.pend_work_code;
	}

	/**
	 * @param pendWorkCode 待处理任务编码
	 */
	public void setPend_work_code(String pendWorkCode) {
		this.pend_work_code = pendWorkCode;
	}

	/**
	 * @return pend_srv_name 待处理服务名称
	 */
	public String getPend_srv_name() {
		return this.pend_srv_name;
	}

	/**
	 * @param pendSrvName 待处理服务名称
	 */
	public void setPend_srv_name(String pendSrvName) {
		this.pend_srv_name = pendSrvName;
	}

	/**
	 * @return pend_rs_code 待处理资源编码
	 */
	public String getPend_rs_code() {
		return this.pend_rs_code;
	}

	/**
	 * @param pendRsCode 待处理资源编码
	 */
	public void setPend_rs_code(String pendRsCode) {
		this.pend_rs_code = pendRsCode;
	}

	/**
	 * @param pendlstSocName 待处理数据源列表
	 */
	public void setPendlst_soc_name(String[] pendLstSocName) {
		this.pendlst_soc_name = pendLstSocName;
	}

	/**
	 * @return pendlst_soc_name 待处理数据源列表
	 */
	public String[] getPendlst_soc_name() {
		return this.pendlst_soc_name;
	}

	/**
	 * @param pendwk_bk_expl 待处理任务说明
	 */
	public void setPendwk_bk_expl(String pendwk_bk_expl){
		this.pendwk_bk_expl = pendwk_bk_expl;
	}

	/**
	 * @return pendwk_bk_expl 待处理任务说明
	 */
	public String getPendwk_bk_expl(){
		return this.pendwk_bk_expl;
	}
	
	/**
	 * @return tl_tltelr 柜员号
	 */
	public String getTl_tltelr() {
		return this.tl_tltelr;
	}

	/**
	 * @param br_sbsbtp 柜员号
	 */
	public void setTl_tltelr(String br_sbsbtp) {
		this.tl_tltelr = br_sbsbtp;
	}

	/**
	 * @return br_sbsbno 机构号
	 */
	public String getBr_sbsbno() {
		return this.br_sbsbno;
	}

	/**
	 * @param brSbsbno 机构号
	 */
	public void setBr_sbsbno(String br_sbsbtp) {
		this.br_sbsbno = br_sbsbtp;
	}

	/**
	 * @return br_sbsbtp 网点类型
	 */
	public String getBr_sbsbtp() {
		return this.br_sbsbtp;
	}

	/**
	 * @param brSbsbtp 网点类型
	 */
	public void setBr_sbsbtp(String br_sbsbtp) {
		this.br_sbsbtp = br_sbsbtp;
	}

	/**
	 * @return String 客户端IP
	 */
	public String getRemote_ip(){
		return this.remote_ip;
	}

	/**
	 * @param remote_ip 客户端IP
	 */
	public void setRemote_ip(String remote_ip){
		this.remote_ip = remote_ip;
	}

	/**
	 * @return String 服务端IP
	 */
	public String getServer_name(){
		return this.server_name;
	}

	/**
	 * @param server_name 服务端IP
	 */
	public void setServer_name(String server_name){
		this.server_name = server_name;
	}

	/**
	 * @return int 服务端端口
	 */
	public int getServer_port(){
		return this.server_port;
	}

	/**
	 * @param server_port 服务端端口
	 */
	public void setServer_port(int server_port){
		this.server_port = server_port;
	}
	
	/**
	 * @return bk_desc 备用
	 */
	public String getBk_desc() {
		return this.bk_desc;
	}

	/**
	 * @param bk_desc 备用
	 */
	public void setBk_desc(String bk_desc) {
		this.bk_desc = bk_desc;
	}
	
	/**
	 * @return apply_bk_expl 任务申请说明
	 */
	public String getApply_bk_expl() {
		return apply_bk_expl;
	}

	/**
	 * @param apply_bk_expl 任务申请说明
	 */
	public void setApply_bk_expl(String apply_bk_expl) {
		this.apply_bk_expl = apply_bk_expl;
	}

	/**
	 * @return String 发起部门简称
	 */
	public String getOrgdept_cn_name(){
		return this.orgdept_cn_name;
	}

	/**
	 * @param orgdept_cn_name 发起部门简称
	 */
	public void setOrgdept_cn_name(String orgdept_cn_name){
		this.orgdept_cn_name = orgdept_cn_name;
	}

	/**
	 * @return String 发起用户姓名
	 */
	public String getOrguser_cn_name(){
		return this.orguser_cn_name;
	}

	/**
	 * @param orguser_cn_name 发起用户姓名
	 */
	public void setOrguser_cn_name(String orguser_cn_name){
		this.orguser_cn_name = orguser_cn_name;
	}
	
	/**
	 * @return apply_html 申请页面代码
	 */
	public String getApply_html() {
		return apply_html;
	}

	/**
	 * @param apply_html 申请页面代码
	 */
	public void setApply_html(String apply_html) {
		this.apply_html = apply_html;
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
	public ActionRootInputBean clone(){
		return (ActionRootInputBean) super.clone();
	}

	/**
	 * 如果有多个对象，则应该使用多个对象使用”+“连接的方式，
	 * 例如BeanTool.obj2JsonStr(info1)+","+BeanTool.obj2JsonStr(info2)
	 * @return String 转换Json字符串
	 */
	public String toString(){
	    return BeanTool.beanToJsonStr(this);
	}
}
