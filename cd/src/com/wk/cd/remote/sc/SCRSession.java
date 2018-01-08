package com.wk.cd.remote.sc;

/**
 * Created by 姜志刚 on 2016/11/12.
 */
public interface SCRSession {

    /**
     * checkout版本到本地
     * @param remote_path 远程版本路径，不包括url
     */
    String co(String remote_path);
    
    /**
     * checkout版本到本地
     * @param remote_path 远程版本路径，不包括url
     * @param local_path 相对于用户根路径的相对路径
     */
    String co(String remote_path, String local_path);
    
    /**
     * checkout版本空目录到本地
     * @param remote_path 远程版本路径，不包括url
     * @param local_path 相对于用户根路径的相对路径
     * @return
     */
    String co_empty(String remote_path, String local_path);
    
    /**
     * export版本到本地
     * @param remote_path 远程版本路径，不包括url
     * @param local_path 相对于用户根路径的相对路径
     * @return
     */
    String export(String remote_path, String local_path);
    
    /**
     * 查询目录下文件列表
     * @param remote_path 远程版本路径，不包括url
     * @return
     */
    String ls(String remote_path);
    
    /**
     * 创建tag，从chunk路径到tag
     * @param chunk 当前版本路径，不包括url
     * @param tag tag路径，不包括url
     * @param desc tag创建描述
     */
    String tag(String chunk, String tag, String desc);

    /**
     * 将分支合并到当前版本，并提交
     * @param branch 分支
     * @param desc 提交描述
     * @param commit 是否提交
     */
    String merge(String branch, String desc, boolean commit);

    /**
     * 从版本库更新
     */
    String update();

    /**
     * 添加变更文件
     * @param file
     * @return
     */
    String add(String file);

    /**
     * 添加变更文件列表
     * @param files
     * @return
     */
    String add(String[] files);

    /**
     * 从版本库删除文件
     * @param file
     * @return
     */
    String rm(String file);

    /**
     * 从版本库删除文件列表
     * @param files
     * @return
     */
    String rm(String[] files);

    /**
     * 提交变更到版本库
     * @param desc 变更描述
     */
    String ci(String desc);

    /**
     * 通过SVN数据源执行其他shell命令
     * @param cmd
     * @return
     */
    String execCmd(String cmd);

    /**
     * 断开连接
     */
    void close();
}
