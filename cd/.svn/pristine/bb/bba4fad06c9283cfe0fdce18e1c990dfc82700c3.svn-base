/**
 * Title: BuildStepDaoService.java
 * File Description: 构建阶段表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */
package com.wk.cd.build.ea.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.info.BuildStepInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:构建阶段表
 * @author AutoGen
 */
public class BuildStepDaoService {
	@Inject private BuildStepDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param BuildStepInfo info
	 * @return BuildStepInfo
	 */
	public BuildStepInfo getInfoByKey(String work_id, String template_name, int phase_id) {
		return dao.get(work_id, template_name, phase_id);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param BuildStepInfo info
	 * @return BuildStepInfo
	 */
	public BuildStepInfo getInfoByKeyForUpdate(BuildStepInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param BuildStepInfo info
	 * @return int
	 */
	public int insertInfo(BuildStepInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<BuildStepInfo>
	 * @return int
	 */
	public int insertListInfo(List<BuildStepInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 根据任务编号和模板名查询多条记录
	 * @param work_id
	 * @param template_name 
	 */
	public List<BuildStepInfo> queryListInfoByIdAndTp(String work_id, String template_name) {
		List<BuildStepInfo> list = new ArrayList<BuildStepInfo>();
        DBIterator<BuildStepInfo> iterator = dao.queryListInfoByIdAndTp(work_id, template_name);
        try {
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        } finally {
            iterator.close();
        }
        return list;
	}

	/** 
	 * Description: 根据主键删除一个阶段
	 * @param work_id
	 * @param template_name 
	 */
	public int deleteListByIdAndTp(String work_id, String template_name, int phase_id) {
		return dao.delete(work_id, template_name, phase_id);
	}
	
	/** 
	 * Description: 根据任务编号和模板名删除多条记录
	 * @param work_id
	 * @param template_name 
	 */
	public int deleteListByIdAndTp(String work_id, String template_name) {
		return dao.deleteListByIdAndTp(work_id, template_name);
	}

}