/**
 * @copyright 2014
 * @company CORSWORK
 * @version 1.0
 */

package com.wk.cd.remote.agent.bean;

import java.util.List;

import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.system.dt.info.DtSourceInfo;

/**
 * Class Description:
 * Created by lixl on 16-8-11.
 */
public class ShellBean {
	
    private String shell;

    private String rs_flag;

    private String result;

    private String id;

    private String digest;   //摘要
    
    private long pid;       //进程号
    
    private int exitStatus;   //进程结束状态
    
    private boolean isStepSupport;          //是否支持单步执行
    
    private int type;       //1.正常的命令  2。交互式
    
    private String remote_temp_path;          //目标机器临时文件目录
    
    private long timeout;            //任务超时时间
    
    private long beginTime;           //任务启动执行时间
    
    /**
     * ftp传输相关
     */
    
    private String local_dir;  //本地路径
    
    private String remote_dir;  //远程路径
    
    private String local_file_name;     //文件名
    
    private String remote_file_name;
    
    private String file_content;   //文件内容
    
    private IMPL_TYPE impl_type;  //执行类别
    
    private long file_total_size;       //文件总的大小
    
    private int cur_num;     //文件传输序号
    
    private int total_num;    //文件传输总次数
    
    private long slice_size;        // 分片大小 
    
    private List<String> file_list;       //某个目录下所有文件路径
    
    /**
     * JDBC相关
     */
    private DtSourceInfo dt_source_info;
    
    private int start_num;
    
    private int offset;
    
    private int stepCount;
    
    /**
     * 监控节点信息
     */
    private AgentNodeMsgBean nodeMsgBean;
    
    public String getShell() {
        return shell;
    }

    public void setShell(String shell) {
        this.shell = shell;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRs_flag() {
        return rs_flag;
    }

    public void setRs_flag(String rs_flag) {
        this.rs_flag = rs_flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public int getExitStatus() {
		return exitStatus;
	}

	public void setExitStatus(int exitStatus) {
		this.exitStatus = exitStatus;
	}

	public boolean isStepSupport() {
		return isStepSupport;
	}

	public void setStepSupport(boolean isStepSupport) {
		this.isStepSupport = isStepSupport;
	}

	public String getLocal_dir() {
		return local_dir;
	}

	public void setLocal_dir(String local_dir) {
		this.local_dir = local_dir;
	}

	public String getRemote_dir() {
		return remote_dir;
	}

	public void setRemote_dir(String remote_dir) {
		this.remote_dir = remote_dir;
	}

	public String getLocal_file_name() {
		return local_file_name;
	}

	public void setLocal_file_name(String local_file_name) {
		this.local_file_name = local_file_name;
	}

	public String getRemote_file_name() {
		return remote_file_name;
	}

	public void setRemote_file_name(String remote_file_name) {
		this.remote_file_name = remote_file_name;
	}

	public String getFile_content() {
		return file_content;
	}

	public void setFile_content(String file_content) {
		this.file_content = file_content;
	}

	public IMPL_TYPE getImpl_type() {
		return impl_type;
	}

	public void setImpl_type(IMPL_TYPE impl_type) {
		this.impl_type = impl_type;
	}

	public long getSlice_size() {
		return slice_size;
	}

	public void setSlice_size(long slice_size) {
		this.slice_size = slice_size;
	}

	public long getFile_total_size() {
		return file_total_size;
	}

	public void setFile_total_size(long file_total_size) {
		this.file_total_size = file_total_size;
	}
	
	public List<String> getFile_list() {
		return file_list;
	}

	public void setFile_list(List<String> file_list) {
		this.file_list = file_list;
	}

	/**
	 * @return 文件当前传输序号
	 */
	public int getCur_num() {
		return cur_num;
	}

	/**
	 * @param 文件当前传输序号
	 */
	public void setCur_num(int cur_num) {
		this.cur_num = cur_num;
	}

	public int getTotal_num() {
		return total_num;
	}

	public void setTotal_num(int total_num) {
		this.total_num = total_num;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public DtSourceInfo getDt_source_info() {
		return dt_source_info;
	}

	public void setDt_source_info(DtSourceInfo dt_source_info) {
		this.dt_source_info = dt_source_info;
	}

	public int getStart_num() {
		return start_num;
	}

	public void setStart_num(int start_num) {
		this.start_num = start_num;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return stepCount
	 */
	public int getStepCount() {
		return stepCount;
	}

	/**
	 * @param stepCount
	 */
	public void setStepCount(int stepCount) {
		this.stepCount = stepCount;
	}
	
	/**
	 * @return nodeMsgBean
	 */
	public AgentNodeMsgBean getNodeMsgBean() {
		return nodeMsgBean;
	}

	/**
	 * @param nodeMsgBean
	 */
	public void setNodeMsgBean(AgentNodeMsgBean nodeMsgBean) {
		this.nodeMsgBean = nodeMsgBean;
	}
	
	public String getRemote_temp_path() {
		return remote_temp_path;
	}

	public void setRemote_temp_path(String remote_temp_path) {
		this.remote_temp_path = remote_temp_path;
	}
	
	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	@Override
	public String toString() {
		return "ShellBean [shell=" + shell + ", rs_flag=" + rs_flag
				+ ", result=" + result + ", id=" + id + ", digest=" + digest
				+ ", pid=" + pid + ", exitStatus=" + exitStatus
				+ ", isStepSupport=" + isStepSupport + ", type=" + type
				+ ", local_dir=" + local_dir + ", remote_dir=" + remote_dir
				+ ", local_file_name=" + local_file_name
				+ ", remote_file_name=" + remote_file_name + ", file_content="
				+ file_content + ", impl_type=" + impl_type
				+ ", file_total_size=" + file_total_size + ", cur_num="
				+ cur_num + ", total_num=" + total_num + ", slice_size="
				+ slice_size + ", file_list=" + file_list + ", dt_source_info="
				+ dt_source_info 
				+ ", start_num=" + start_num + ", offset=" + offset
				+ ", stepCount=" + stepCount + ", nodeMsgBean=" + nodeMsgBean
				+ "]";
	}
	
}
