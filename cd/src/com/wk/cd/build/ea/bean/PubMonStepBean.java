/**
 * Title: PubMonStepBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016Äê11ÔÂ23ÈÕ
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;

/**
 * Class Description: 
 * @author chiss
 */
public class PubMonStepBean {
	/**
	 * ½×¶Î±àºÅ
	 */
	private int phase_id;
	
	public static final String PHASE_IDCN = "½×¶Î±àºÅ";
	
	/**
	 * ½×¶ÎÃèÊö
	 */
    private String phase_bk_desc;
    
    public static final String PHASE_BK_DESCCN = "½×¶ÎÃèÊö";
    
	/**
	 * Ö´ÐÐ×´Ì¬
	 */
	private EXE_STATUS exe_status;

	public static final String EXE_STATUSCN = "Ö´ÐÐ×´Ì¬";
	
	/**
	 * Ö´ÐÐ½á¹û
	 */
	private EXE_RESULT task_exe_result;

	public static final String TASK_EXE_RESULTCN = "Ö´ÐÐ½á¹û";

	/**
	 * @return phase_id ½×¶Î±àºÅ
	 */
	public int getPhase_id() {
		return phase_id;
	}

	/**
	 * @param phase_id ½×¶Î±àºÅ
	 */
	public void setPhase_id(int phase_id) {
		this.phase_id = phase_id;
	}

	/**
	 * @return phase_bk_desc ½×¶ÎÃèÊö
	 */
	public String getPhase_bk_desc() {
		return phase_bk_desc;
	}

	/**
	 * @param phase_bk_desc ½×¶ÎÃèÊö
	 */
	public void setPhase_bk_desc(String phase_bk_desc) {
		this.phase_bk_desc = phase_bk_desc;
	}

	/**
	 * @return exe_status Ö´ÐÐ×´Ì¬
	 */
	public EXE_STATUS getExe_status() {
		return exe_status;
	}

	/**
	 * @param exe_status Ö´ÐÐ×´Ì¬
	 */
	public void setExe_status(EXE_STATUS exe_status) {
		this.exe_status = exe_status;
	}

	/**
	 * @return task_exe_result Ö´ÐÐ½á¹û
	 */
	public EXE_RESULT getTask_exe_result() {
		return task_exe_result;
	}

	/**
	 * @param task_exe_result Ö´ÐÐ½á¹û
	 */
	public void setTask_exe_result(EXE_RESULT task_exe_result) {
		this.task_exe_result = task_exe_result;
	}
}
