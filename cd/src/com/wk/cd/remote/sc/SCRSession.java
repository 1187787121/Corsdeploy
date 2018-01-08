package com.wk.cd.remote.sc;

/**
 * Created by ��־�� on 2016/11/12.
 */
public interface SCRSession {

    /**
     * checkout�汾������
     * @param remote_path Զ�̰汾·����������url
     */
    String co(String remote_path);
    
    /**
     * checkout�汾������
     * @param remote_path Զ�̰汾·����������url
     * @param local_path ������û���·�������·��
     */
    String co(String remote_path, String local_path);
    
    /**
     * checkout�汾��Ŀ¼������
     * @param remote_path Զ�̰汾·����������url
     * @param local_path ������û���·�������·��
     * @return
     */
    String co_empty(String remote_path, String local_path);
    
    /**
     * export�汾������
     * @param remote_path Զ�̰汾·����������url
     * @param local_path ������û���·�������·��
     * @return
     */
    String export(String remote_path, String local_path);
    
    /**
     * ��ѯĿ¼���ļ��б�
     * @param remote_path Զ�̰汾·����������url
     * @return
     */
    String ls(String remote_path);
    
    /**
     * ����tag����chunk·����tag
     * @param chunk ��ǰ�汾·����������url
     * @param tag tag·����������url
     * @param desc tag��������
     */
    String tag(String chunk, String tag, String desc);

    /**
     * ����֧�ϲ�����ǰ�汾�����ύ
     * @param branch ��֧
     * @param desc �ύ����
     * @param commit �Ƿ��ύ
     */
    String merge(String branch, String desc, boolean commit);

    /**
     * �Ӱ汾�����
     */
    String update();

    /**
     * ��ӱ���ļ�
     * @param file
     * @return
     */
    String add(String file);

    /**
     * ��ӱ���ļ��б�
     * @param files
     * @return
     */
    String add(String[] files);

    /**
     * �Ӱ汾��ɾ���ļ�
     * @param file
     * @return
     */
    String rm(String file);

    /**
     * �Ӱ汾��ɾ���ļ��б�
     * @param files
     * @return
     */
    String rm(String[] files);

    /**
     * �ύ������汾��
     * @param desc �������
     */
    String ci(String desc);

    /**
     * ͨ��SVN����Դִ������shell����
     * @param cmd
     * @return
     */
    String execCmd(String cmd);

    /**
     * �Ͽ�����
     */
    void close();
}
