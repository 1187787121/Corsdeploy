drop view if exists VI_SYS_ENV_SERVER;

drop table if exists CH_CHANNEL;

drop table if exists CH_CHANNEL_SRVG_PRIV;

drop table if exists CH_CHANNEL_SRV_PRIV;

drop table if exists CM_SEQ;

drop table if exists DC_DICT;

drop table if exists DC_DICT_ENU;

drop table if exists DP_DEPT;

drop table if exists DP_DEPT_COL_PRIV;

drop table if exists DP_DEPT_OPT_PRIV;

drop table if exists DP_DEPT_RS_PRIV;

drop table if exists DP_DEPT_SOC_PRIV;

drop table if exists DP_DEPT_SQL_PRIV;

drop table if exists DT_SOURCE;

drop table if exists LG_LOG_DOWN;

drop table if exists LG_LOG_LABEL;

drop table if exists LG_LOG_MF;

drop table if exists MG_MSG;

drop table if exists MG_MSG_USER;

drop table if exists RS_OPT;

drop table if exists RS_RES;

drop table if exists RT_SVC_EXE;

drop table if exists SV_REMOTE_SRV;

drop table if exists SV_SRV;

drop table if exists SV_SRVG;

drop table if exists SV_SRV_AUTH;

drop table if exists SV_SRV_CHECK;

drop table if exists SV_SRV_SOC;

drop table if exists TM_TERM;

drop table if exists US_DEPT_ROLE;

drop table if exists US_ROLE;

drop table if exists US_ROLE_COL_PRIV;

drop table if exists US_ROLE_OPT_PRIV;

drop table if exists US_ROLE_RS_PRIV;

drop table if exists US_ROLE_SOC_PRIV;

drop table if exists US_ROLE_SQL_PRIV;

drop table if exists US_USER;

drop table if exists US_USER_COL_PRIV;

drop table if exists US_USER_CONTACT;

drop table if exists US_USER_OPT_PRIV;

drop table if exists US_USER_ROLE;

drop table if exists US_USER_RS_PRIV;

drop table if exists US_USER_SOC_PRIV;

drop table if exists US_USER_SQL_PRIV;

drop table if exists US_USER_TERM;

drop table if exists WK_DEAL_DETAIL;

drop table if exists WK_DEAL_STATE;

drop table if exists WK_DETAIL_REGISTER;

drop table if exists WK_WORK_PROCESS;

/*==============================================================*/
/* Table: CH_CHANNEL                                            */
/*==============================================================*/
create table CH_CHANNEL
(
   CHANNEL_CODE         char(2) not null comment '��������',
   CHANNEL_CN_NAME      varchar(50) comment '������������',
   CHANNEL_TYPE         int not null default 0 comment '��������',
   CHANNEL_BK_DESC      varchar(100) comment '��������',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   RCD_STATE            int not null default 0 comment '��¼״̬',
   primary key (CHANNEL_CODE)
);

/*==============================================================*/
/* Table: CH_CHANNEL_SRVG_PRIV                                  */
/*==============================================================*/
create table CH_CHANNEL_SRVG_PRIV
(
   CHANNEL_CODE         char(2) not null comment '��������',
   SRVG_CODE            char(2) not null comment '���������',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (CHANNEL_CODE, SRVG_CODE)
);

/*==============================================================*/
/* Table: CH_CHANNEL_SRV_PRIV                                   */
/*==============================================================*/
create table CH_CHANNEL_SRV_PRIV
(
   CHANNEL_CODE         char(2) not null comment '��������',
   SRV_NAME             varchar(50) not null comment '������',
   AF_FLAG              int not null default 0 comment '������ֹ��־',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (CHANNEL_CODE, SRV_NAME)
);

/*==============================================================*/
/* Table: CM_SEQ                                                */
/*==============================================================*/
create table CM_SEQ
(
   SEQ_NAME             varchar(6) not null comment '�������',
   CUR_BK_SEQ           bigint not null default 0 comment '��ǰ���',
   SEQ_FLD_LENGTH       int not null default 0 comment '��ų���',
   SEQ_TYPE             int not null default 0 comment '�������',
   LMOD_BK_DATE         date comment '�ϴ��޸�����',
   LS_BK_SEQ            bigint not null default 0 comment '�������',
   primary key (SEQ_NAME)
);

/*==============================================================*/
/* Table: DC_DICT                                               */
/*==============================================================*/
create table DC_DICT
(
   DOMAIN_NAME          varchar(20) not null comment '�����',
   DOMAIN_CN_NAME       varchar(50) comment '����������',
   FLD_TYPE             varchar(20) comment '����',
   FLD_LENGTH           int not null default 0 comment '�ܳ���',
   FLD_SCALE            int not null default 0 comment 'С��λ',
   ENU_YN_FLAG          int not null default 0 comment '�Ƿ�ö��',
   primary key (DOMAIN_NAME)
);

/*==============================================================*/
/* Table: DC_DICT_ENU                                           */
/*==============================================================*/
create table DC_DICT_ENU
(
   DOMAIN_NAME          varchar(20) not null comment '������',
   ENU_VALUE            int not null default 0 comment 'ѡ��ֵ',
   ENU_CODE             varchar(50) comment 'ѡ�����',
   ENU_BK_EXPL          varchar(500) comment 'ѡ��˵��',
   primary key (DOMAIN_NAME, ENU_VALUE)
);

/*==============================================================*/
/* Table: DP_DEPT                                               */
/*==============================================================*/
create table DP_DEPT
(
   DEPT_ID              char(6) not null comment '���ű���',
   DEPT_CN_NAME         varchar(50) comment '��������',
   DEPT_FULL_CNAME      varchar(50) comment '����ȫ��',
   DEPT_TYPE            int not null default 0 comment '��������',
   DEPT_LEVEL           int not null default 0 comment '���ż���',
   BRANCH_NO            varchar(10) comment '������',
   SUPER_DEPT_ID        char(6) comment '�ϼ����ű���',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   CRT_BK_DATE          date comment '��������',
   CRT_BK_TIME          time comment '����ʱ��',
   CRT_USER_ID          varchar(20) comment '�����û�',
   MODIFY_BK_DATE       date comment '�޸�����',
   MODIFY_BK_TIME       time comment '�޸�ʱ��',
   MODIFY_USER_ID       varchar(20) comment '�޸��û�',
   DEL_BK_DATE          date comment 'ɾ������',
   DEL_BK_TIME          time comment 'ɾ��ʱ��',
   DEL_USER_ID          varchar(20) comment 'ɾ���û�',
   RCD_STATE            int not null default 0 comment '��¼״̬',
   primary key (DEPT_ID)
);

/*==============================================================*/
/* Table: DP_DEPT_COL_PRIV                                      */
/*==============================================================*/
create table DP_DEPT_COL_PRIV
(
   DEPT_ID              char(6) not null comment '���ű���',
   SOC_NAME             varchar(20) not null comment '����Դ����',
   TBL_NAME             varchar(50) not null comment '����',
   COL_NAME             varchar(50) not null comment '�ֶ�����',
   INS_PRIV_FLAG        int not null default 0 comment 'INSERTȨ��',
   DEL_PRIV_FLAG        int not null default 0 comment 'DELETEȨ��',
   UPD_PRIV_FLAG        int not null default 0 comment 'UPDATEȨ��',
   SEL_PRIV_FLAG        int not null default 0 comment 'SELECTȨ��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (DEPT_ID, SOC_NAME, TBL_NAME, COL_NAME)
);

/*==============================================================*/
/* Table: DP_DEPT_OPT_PRIV                                      */
/*==============================================================*/
create table DP_DEPT_OPT_PRIV
(
   OPT_CODE             varchar(12) not null comment '��������',
   DEPT_ID              char(6) not null comment '���ű���',
   PRIV_FLAG            int not null default 0 comment 'Ȩ��',
   primary key (OPT_CODE, DEPT_ID)
);

/*==============================================================*/
/* Table: DP_DEPT_RS_PRIV                                       */
/*==============================================================*/
create table DP_DEPT_RS_PRIV
(
   DEPT_ID              char(6) not null comment '���ű���',
   RS_CODE              varchar(10) not null comment '��Դ����',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (DEPT_ID, RS_CODE)
);

/*==============================================================*/
/* Table: DP_DEPT_SOC_PRIV                                      */
/*==============================================================*/
create table DP_DEPT_SOC_PRIV
(
   DEPT_ID              char(6) not null comment '���ű���',
   SOC_NAME             varchar(20) not null comment '����Դ����',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (DEPT_ID, SOC_NAME)
);

/*==============================================================*/
/* Table: DP_DEPT_SQL_PRIV                                      */
/*==============================================================*/
create table DP_DEPT_SQL_PRIV
(
   DEPT_ID              char(6) not null comment '���ű���',
   SOC_NAME             varchar(20) not null comment '����Դ����',
   TBL_NAME             varchar(50) not null comment '����',
   INS_PRIV_FLAG        int not null default 0 comment 'INSERTȨ��',
   DEL_PRIV_FLAG        int not null default 0 comment 'DELETEȨ��',
   UPD_PRIV_FLAG        int not null default 0 comment 'UPDATEȨ��',
   SEL_PRIV_FLAG        int not null default 0 comment 'SELECTȨ��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (DEPT_ID, SOC_NAME, TBL_NAME)
);

/*==============================================================*/
/* Table: DT_SOURCE                                             */
/*==============================================================*/
create table DT_SOURCE
(
   SOC_NAME             varchar(20) not null comment '����Դ����',
   SOC_IP               varchar(20) comment 'IP��ַ',
   SOC_PORT             int not null default 0 comment '�˿ں�',
   PROTOCOL_TYPE        int not null default 0 comment 'Э������',
   REMOTE_UNAME         varchar(20) comment '��������½�û���',
   REMOTE_PASSWD        varchar(32) comment '��������½����',
   KEY_REMOTE_PASSWD    varchar(32) not null comment '��Կ',
   BK_TIMEOUT           bigint not null default 0 comment '��ʱʱ��',
   JDBC_DRV             varchar(50) comment 'Jdbc_driver',
   JDBC_URL             varchar(100) comment 'Jdbc_url',
   JDBC_SCHEMA          varchar(50) comment 'Jdbc_schema',
   CVT_TYPE             int not null default 0 comment 'ת�뷽ʽ',
   FTP_LOCAL_SCRIPT     varchar(50) comment '���´��ű�',
   CVT_LOCAL_SCRIPT     varchar(50) comment 'ת��ű�',
   SOC_DOMAIN           varchar(20) comment '����Դ����',
   SOC_BK_DESC          varchar(100) comment '����Դ����',
   SOC_PARAMS           varchar(500) comment '����Դ����',
   USER_ROOT_PATH       varchar(200) comment '�û���·��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   RCD_STATE            int not null default 0 comment '��¼״̬',
   ENVIRONMENT_VARIABLES varchar(500) comment '��������',
   ENCODING_TYPE        varchar(20) comment '�����ʽ',
   primary key (SOC_NAME)
);

/*==============================================================*/
/* Table: LG_LOG_DOWN                                           */
/*==============================================================*/
create table LG_LOG_DOWN
(
   LOG_ROOT_PATH        varchar(200) not null comment '��־�ļ�·��',
   LOG_FILE_NAME        varchar(50) not null comment '��־�ļ�����',
   START_BK_DATE        date comment '��־��ʼ����',
   END_BK_DATE          date comment '��־��ֹ����',
   USER_ID              varchar(20) comment '�û���',
   CRT_BK_DATE          date comment '������־����',
   CRT_BK_TIME          time comment '������־ʱ��',
   BACKUP_FLD           varchar(50) comment '����',
   primary key (LOG_ROOT_PATH, LOG_FILE_NAME)
);

/*==============================================================*/
/* Table: LG_LOG_LABEL                                          */
/*==============================================================*/
create table LG_LOG_LABEL
(
   WORK_SEQ             varchar(17) not null comment '������ˮ��',
   USER_ID              varchar(20) not null comment '�û���',
   LOG_LABEL            int not null default 0 comment '��־���',
   LABEL_BK_DATE        date comment '�������',
   LABEL_BK_TIME        time comment '���ʱ��',
   BACKUP_FLD           varchar(50) comment '����',
   primary key (WORK_SEQ, USER_ID)
);

/*==============================================================*/
/* Table: LG_LOG_MF                                             */
/*==============================================================*/
create table LG_LOG_MF
(
   WORK_SEQ             varchar(17) not null comment '������ˮ��',
   ORG_CHANNEL_CODE     char(2) comment '��������',
   ORG_TERM_NO          varchar(40) comment '�����ն�',
   ORG_WORK_CODE        varchar(10) comment '�����������',
   ORG_SRV_NAME         varchar(50) comment '���������',
   ORG_SRV_BK_DESC      varchar(100) comment '�����������',
   ORG_RS_CODE          varchar(10) comment '������Դ����',
   ORG_ARY_SOCNAME      varchar(100) comment '��������Դ����',
   PEND_WORK_SEQ        varchar(17) comment '��������ˮ��',
   PEND_WORK_CODE       varchar(10) comment '�������������',
   PEND_SRV_NAME        varchar(50) comment '��������������',
   PEND_RS_CODE         varchar(10) comment '��������Դ����',
   PEND_ARY_SOCNAME     varchar(100) comment '����������Դ����',
   PENDWK_BK_EXPL       varchar(500) comment '����������˵��',
   PBL_CODE             varchar(20) comment '���ⵥ����',
   SR_YN_FLAG           int not null default 0 comment '�ɹ���׼',
   CRT_USER_ID          varchar(20) comment '�����û�',
   CRT_USER_CN_NAME     varchar(50) comment '�û�������',
   CRT_DEPT_ID          char(6) comment '��������',
   CRT_DEPT_CN_NAME     varchar(50) comment '����������',
   CRT_BK_DATE          date comment '��������',
   CRT_BK_TIME          time comment '����ʱ��',
   LOG_TXT              varchar(1000) comment '��־����',
   LOG_LEVEL            int not null default 0 comment '��־����',
   BACKUP_FLD           varchar(50) comment '����',
   primary key (WORK_SEQ)
);

/*==============================================================*/
/* Table: MG_MSG                                                */
/*==============================================================*/
create table MG_MSG
(
   WORK_SEQ             varchar(17) not null comment '��Ϣ��ˮ��',
   MSG_TITLE            varchar(200) comment '��Ϣ����',
   MSG_TEXT             varchar(1000) comment '��Ϣ����',
   MSG_TYPE             int not null default 0 comment '��Ϣ����',
   FILE_PATH            varchar(200) comment '�ļ�·��',
   FIRST_BK_FNAME       varchar(50) comment '�ļ���1',
   SECD_BK_FNAME        varchar(50) comment '�ļ���2',
   THIRD_BK_FNAME       varchar(50) comment '�ļ���3',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   CRT_USER_ID          varchar(20) comment '�����û�',
   CRT_BK_DATE          date comment '��������',
   CRT_BK_TIME          time comment '����ʱ��',
   DEL_YN_FLAG          int not null default 0 comment 'ɾ��״̬',
   RCD_STATE            int not null default 0 comment '��¼״̬',
   primary key (WORK_SEQ)
);

/*==============================================================*/
/* Table: MG_MSG_USER                                           */
/*==============================================================*/
create table MG_MSG_USER
(
   WORK_SEQ             varchar(17) not null comment '��Ϣ��ˮ��',
   RC_USER_ID           varchar(20) not null comment '�����û�',
   RC_FLAG              int not null default 0 comment '����״̬',
   ATTENT_YN_FLAG       int not null default 0 comment '��ע״̬',
   RC_BK_DATE           date comment '��������',
   RC_BK_TIME           time comment '����ʱ��',
   RCD_STATE            int not null default 0 comment '��¼״̬',
   primary key (WORK_SEQ, RC_USER_ID)
);

/*==============================================================*/
/* Table: RS_OPT                                                */
/*==============================================================*/
create table RS_OPT
(
   OPT_CODE             varchar(12) not null comment '��������',
   BL_RS_CODE           varchar(10) comment '������Դ����',
   OPT_BK_SEQ           bigint not null default 0 comment '�������',
   OPT_NAME             varchar(50) comment '������',
   OPT_BK_EXPL          varchar(500) comment '����˵��',
   DIS_EXPRESS          varchar(200) comment '���ñ���ʽ',
   CRT_USER_ID          varchar(20) comment '�����û�',
   CRT_BK_DATE          date comment '��������',
   CRT_BK_TIME          time comment '����ʱ��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   RCD_STATE            int not null default 0 comment '��¼״̬',
   primary key (OPT_CODE)
);

/*==============================================================*/
/* Table: RS_RES                                                */
/*==============================================================*/
create table RS_RES
(
   RS_CODE              varchar(10) not null comment '��Դ����',
   SUPER_RS_CODE        varchar(10) comment '�ϼ���Դ����',
   BL_RS_CODE           varchar(10) comment '����һ����Դ����',
   RS_FUN_TYPE          int not null default 0 comment '��Դ����',
   RS_CN_NAME           varchar(50) comment '��Դ����',
   RS_BK_DESC           varchar(100) comment '��Դ����',
   RS_URL               varchar(200) comment '��Դ��ַ',
   RSIM_URL             varchar(200) comment '��Դͼ���ַ',
   RS_LEVEL             int not null default 0 comment '��Դ����',
   GHEIGHT_BK_PIXEL     varchar(6) comment '�߶�',
   PWIDTH_BK_PIXEL      varchar(6) comment '����',
   TITLE_ABLE           int not null default 0 comment '�Ƿ��б���',
   PUBLIC_YN_FLAG       int not null default 0 comment '�Ƿ񹫿�',
   OPEN_TYPE            int not null default 0 comment '��������',
   SORT_KEY             int not null default 0 comment '�����',
   RCD_STATE            int not null default 0 comment '��¼״̬',
   primary key (RS_CODE)
);

/*==============================================================*/
/* Table: RT_SVC_EXE                                            */
/*==============================================================*/
create table RT_SVC_EXE
(
   WORK_SEQ             varchar(17) not null comment '������ˮ��',
   SOC_NAME             varchar(20) comment '����Դ����',
   START_BK_DATE        date comment '��ʼ����',
   START_BK_TIME        time comment '��ʼʱ��',
   END_BK_DATE          date comment '��������',
   END_BK_TIME          time comment '����ʱ��',
   CUR_PROC_STATE       int not null default 0 comment '��ǰ״̬',
   PROC_NAME            varchar(20) comment '��������',
   PROC_NUM             varchar(10) comment '���̺�',
   BL_USER_ID           varchar(20) comment '���������û�',
   RUN_CMD_STR          varchar(500) comment 'ִ�г���',
   ORG_USER_ID          varchar(20) comment '�ύ�û�',
   RS_BK_TEXT           varchar(1000) comment '���н��',
   ONE_BACKUP_FLD       varchar(50) comment '�����ֶ�1',
   TWO_BACKUP_FLD       varchar(50) comment '�����ֶ�2',
   THREE_BACKUP_FLD     varchar(50) comment '�����ֶ�3',
   primary key (WORK_SEQ)
);

/*==============================================================*/
/* Table: SV_REMOTE_SRV                                         */
/*==============================================================*/
create table SV_REMOTE_SRV
(
   SRV_NAME             varchar(50) not null comment '��������',
   DEAL_SEQ             int not null default 0 comment '�������',
   REMOTE_SRV_NAME      varchar(50) comment 'Ŀ����������',
   SRV_TYPE             varchar(10) comment 'Ŀ��������������',
   SRV_ROOT_PATH        varchar(200) comment '����·��',
   primary key (SRV_NAME, DEAL_SEQ)
);

/*==============================================================*/
/* Table: SV_SRV                                                */
/*==============================================================*/
create table SV_SRV
(
   SRV_NAME             varchar(50) not null comment '��������',
   SUP_SRVG_CODE        char(2) comment '�������������',
   SRV_BK_DESC          varchar(100) comment '��������',
   SRV_FUN_TYPE         int not null default 0 comment '��������',
   SRV_CLASS_NAME       varchar(100) comment '��������',
   SRV_METHOD_NAME      varchar(50) comment '���񷽷���',
   CHECK_FLAG           int not null default 0 comment '�Ƿ񸴺�',
   AUTH_FLAG            int not null default 0 comment '�Ƿ���Ȩ',
   SOC_FLAG             int not null default 0 comment '�Ƿ�������Դ',
   SALLOW_FLAG          int not null default 0 comment '����������־',
   LOG_LEVEL            int not null default 0 comment '��־����',
   RCD_STATE            int not null default 0 comment '��¼״̬',
   APROV_TYPE           int not null default 0 comment '����չʾ����',
   CUSTOM_RS_CODE       varchar(10) comment '����ҳ����Դ����',
   primary key (SRV_NAME)
);

/*==============================================================*/
/* Table: SV_SRVG                                               */
/*==============================================================*/
create table SV_SRVG
(
   SRVG_CODE            char(2) not null comment '���������',
   SRVG_CN_NAME         varchar(50) comment '����������',
   SRVG_BK_DESC         varchar(100) comment '����������',
   SRVG_FUN_TYPE        int not null default 0 comment '����������',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (SRVG_CODE)
);

/*==============================================================*/
/* Table: SV_SRV_AUTH                                           */
/*==============================================================*/
create table SV_SRV_AUTH
(
   AUTH_DEPT_ID         char(6) not null comment '���ò��ű���',
   SRV_NAME             varchar(50) not null comment '��������',
   AUTH_SEQ             int not null default 0 comment '��Ȩ���',
   AUTH_TYPE            int not null default 0 comment '��Ȩ����',
   AUTH_APROV_CATEGORY  int not null default 0 comment '�������',
   AUTH_DPRL_CODE       char(8) comment '��Ȩ���Ž�ɫ',
   ROLE_CN_NAME         varchar(50) comment '��ɫ����',
   SUPER_FLAG           int not null default 0 comment '�Ƿ��ϼ�����',
   primary key (AUTH_DEPT_ID, SRV_NAME, AUTH_SEQ)
);

/*==============================================================*/
/* Table: SV_SRV_CHECK                                          */
/*==============================================================*/
create table SV_SRV_CHECK
(
   CHECK_DEPT_ID        char(6) not null comment '���ò��ű���',
   SRV_NAME             varchar(50) not null comment '��������',
   CHECK_SEQ            int not null default 0 comment '�������',
   CHK_APROV_CATEGORY   int not null default 0 comment '�������',
   CHK_DPRL_CODE        char(8) comment '���˲��Ž�ɫ',
   ROLE_CN_NAME         varchar(50) comment '��ɫ����',
   SUPER_FLAG           int not null default 0 comment '�Ƿ��ϼ�����',
   primary key (CHECK_DEPT_ID, SRV_NAME, CHECK_SEQ)
);

/*==============================================================*/
/* Table: SV_SRV_SOC                                            */
/*==============================================================*/
create table SV_SRV_SOC
(
   SRV_NAME             varchar(50) not null comment '��������',
   SOC_NAME             varchar(20) not null comment '����Դ����',
   SOC_SEQ              int not null default 0 comment '����Դ˳���',
   primary key (SRV_NAME, SOC_NAME)
);

/*==============================================================*/
/* Table: TM_TERM                                               */
/*==============================================================*/
create table TM_TERM
(
   TERM_NO              varchar(40) not null comment '�ն˺�',
   TERM_TYPE            int not null default 0 comment '�ն�����',
   TERM_BK_DESC         varchar(100) comment '�ն�˵��',
   CRT_USER_ID          varchar(20) comment '�����û�',
   CRT_DEPT_ID          char(6) comment '��������',
   CRT_BK_DATE          date comment '��������',
   CRT_BK_TIME          time comment '����ʱ��',
   BACKUP_FLD           varchar(50) comment '����',
   primary key (TERM_NO)
);

/*==============================================================*/
/* Table: US_DEPT_ROLE                                          */
/*==============================================================*/
create table US_DEPT_ROLE
(
   DPRL_CODE            char(8) not null comment '���Ž�ɫ����',
   DEPT_ID              char(6) comment '���ű���',
   ROLE_CODE            char(2) comment '��ɫ����',
   BK_EXPL              varchar(500) comment '���Ž�ɫ˵��',
   primary key (DPRL_CODE)
);

/*==============================================================*/
/* Table: US_ROLE                                               */
/*==============================================================*/
create table US_ROLE
(
   ROLE_CODE            char(2) not null comment '��ɫ����',
   ROLE_CN_NAME         varchar(50) comment '��ɫ����',
   ROLE_TYPE            int not null default 0 comment '��ɫ����',
   ROLE_BK_DESC         varchar(100) comment '��ɫ˵��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   CRT_BK_DATE          date comment '��������',
   CRT_BK_TIME          time comment '����ʱ��',
   CRT_USER_ID          varchar(20) comment '�����û�',
   MODIFY_BK_DATE       date comment '�޸�����',
   MODIFY_BK_TIME       time comment '�޸�ʱ��',
   MODIFY_USER_ID       varchar(20) comment '�޸��û�',
   DEL_BK_DATE          date comment 'ɾ������',
   DEL_BK_TIME          time comment 'ɾ��ʱ��',
   DEL_USER_ID          varchar(20) comment 'ɾ���û�',
   RCD_STATE            int not null default 0 comment '��¼״̬',
   primary key (ROLE_CODE)
);

/*==============================================================*/
/* Table: US_ROLE_COL_PRIV                                      */
/*==============================================================*/
create table US_ROLE_COL_PRIV
(
   DPRL_CODE            char(8) not null comment '���Ž�ɫ����',
   SOC_NAME             varchar(20) not null comment '����Դ����',
   TBL_NAME             varchar(50) not null comment '����',
   COL_NAME             varchar(50) not null comment '�ֶ�����',
   INS_PRIV_FLAG        int not null default 0 comment 'InsertȨ��',
   DEL_PRIV_FLAG        int not null default 0 comment 'DeleteȨ��',
   UPD_PRIV_FLAG        int not null default 0 comment 'UpdateȨ��',
   SEL_PRIV_FLAG        int not null default 0 comment 'SelectȨ��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (DPRL_CODE, SOC_NAME, TBL_NAME, COL_NAME)
);

/*==============================================================*/
/* Table: US_ROLE_OPT_PRIV                                      */
/*==============================================================*/
create table US_ROLE_OPT_PRIV
(
   OPT_CODE             varchar(12) not null comment '��������',
   DPRL_CODE            char(8) not null comment '���Ž�ɫ����',
   PRIV_FLAG            int not null default 0 comment 'Ȩ��',
   primary key (OPT_CODE, DPRL_CODE)
);

/*==============================================================*/
/* Table: US_ROLE_RS_PRIV                                       */
/*==============================================================*/
create table US_ROLE_RS_PRIV
(
   DPRL_CODE            char(8) not null comment '���Ž�ɫ����',
   RS_CODE              varchar(10) not null comment '��Դ����',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (DPRL_CODE, RS_CODE)
);

/*==============================================================*/
/* Table: US_ROLE_SOC_PRIV                                      */
/*==============================================================*/
create table US_ROLE_SOC_PRIV
(
   DPRL_CODE            char(8) not null comment '���Ž�ɫ����',
   SOC_NAME             varchar(20) not null comment '����Դ����',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (DPRL_CODE, SOC_NAME)
);

/*==============================================================*/
/* Table: US_ROLE_SQL_PRIV                                      */
/*==============================================================*/
create table US_ROLE_SQL_PRIV
(
   DPRL_CODE            char(8) not null comment '���Ž�ɫ����',
   SOC_NAME             varchar(20) not null comment '����Դ����',
   TBL_NAME             varchar(50) not null comment '����',
   INS_PRIV_FLAG        int not null default 0 comment 'InsertȨ��',
   DEL_PRIV_FLAG        int not null default 0 comment 'DeleteȨ��',
   UPD_PRIV_FLAG        int not null default 0 comment 'UpdateȨ��',
   SEL_PRIV_FLAG        int not null default 0 comment 'SelectȨ��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (DPRL_CODE, SOC_NAME, TBL_NAME)
);

/*==============================================================*/
/* Table: US_USER                                               */
/*==============================================================*/
create table US_USER
(
   USER_ID              varchar(20) not null comment '�û���',
   USER_PASSWD          varchar(32) comment '�û�����',
   PWDEXP_BK_DATE       date comment '���뵽����',
   USER_CN_NAME         varchar(50) comment '�û�����',
   EMAIL_ADD            varchar(50) comment '����',
   PHONE_NO             varchar(12) comment '�绰����',
   TELLER_NO            varchar(6) comment '��Ա��',
   LOGIN_BK_NUM         int not null default 0 comment '�û���¼����',
   BL_DEPT_ID           char(6) comment '�������ź�',
   USER_TYPE            int not null default 0 comment '�û�����',
   FIRST_DEPT_ID        char(6) comment '��ְ����1',
   SECD_DEPT_ID         char(6) comment '��ְ����2',
   THIRD_DEPT_ID        char(6) comment '��ְ����3',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   CRT_BK_DATE          date comment '��������',
   CRT_BK_TIME          time comment '����ʱ��',
   CRT_USER_ID          varchar(20) comment '�����û�',
   MODIFY_BK_DATE       date comment '�޸�����',
   MODIFY_BK_TIME       time comment '�޸�ʱ��',
   MODIFY_USER_ID       varchar(20) comment '�޸��û�',
   DEL_BK_DATE          date comment 'ɾ������',
   DEL_BK_TIME          time comment 'ɾ��ʱ��',
   DEL_USER_ID          varchar(20) comment 'ɾ���û�',
   RCD_STATE            int not null default 0 comment '��¼״̬',
   primary key (USER_ID)
);

/*==============================================================*/
/* Table: US_USER_COL_PRIV                                      */
/*==============================================================*/
create table US_USER_COL_PRIV
(
   USER_ID              varchar(20) not null comment '�û���',
   SOC_NAME             varchar(20) not null comment '����Դ����',
   TBL_NAME             varchar(50) not null comment '����',
   COL_NAME             varchar(50) not null comment '�ֶ�����',
   PRIV_TYPE            int not null default 0 comment '��Դ����',
   INS_PRIV_FLAG        int not null default 0 comment 'InsertȨ��',
   DEL_PRIV_FLAG        int not null default 0 comment 'DeleteȨ��',
   UPD_PRIV_FLAG        int not null default 0 comment 'UpdateȨ��',
   SEL_PRIV_FLAG        int not null default 0 comment 'SelectȨ��',
   AF_FLAG              int not null default 0 comment '������ֹ��־λ',
   TEMPSTART_BK_DATETIME timestamp comment '��ʱȨ�޿�ʼʱ��',
   TEMPEND_BK_DATETIME  timestamp comment '��ʱȨ�޽���ʱ��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (USER_ID, SOC_NAME, TBL_NAME, COL_NAME, PRIV_TYPE)
);

/*==============================================================*/
/* Table: US_USER_CONTACT                                       */
/*==============================================================*/
create table US_USER_CONTACT
(
   USER_ID              varchar(20) not null comment '�û�ID',
   CONTACT_USER_ID      varchar(20) not null comment '�û�������ϵ��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (USER_ID, CONTACT_USER_ID)
);

/*==============================================================*/
/* Table: US_USER_OPT_PRIV                                      */
/*==============================================================*/
create table US_USER_OPT_PRIV
(
   OPT_CODE             varchar(12) not null comment '��������',
   USER_ID              varchar(20) not null comment '�û���',
   PRIV_FLAG            int not null default 0 comment 'Ȩ��',
   primary key (OPT_CODE, USER_ID)
);

/*==============================================================*/
/* Table: US_USER_ROLE                                          */
/*==============================================================*/
create table US_USER_ROLE
(
   USER_ID              varchar(20) not null comment '�û���',
   DPRL_CODE            char(8) not null comment '���Ž�ɫ����',
   USER_BK_WEIGHT       int not null default 0 comment '�û�Ȩ��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (USER_ID, DPRL_CODE)
);

/*==============================================================*/
/* Table: US_USER_RS_PRIV                                       */
/*==============================================================*/
create table US_USER_RS_PRIV
(
   USER_ID              varchar(20) not null comment '�û���',
   RS_CODE              varchar(10) not null comment '��Դ����',
   PRIV_TYPE            int not null default 0 comment 'Ȩ������',
   AF_FLAG              int not null default 0 comment '������ֹ��־',
   TEMPSTART_BK_DATETIME timestamp comment '��ʱȨ�޿�ʼʱ��',
   TEMPEND_BK_DATETIME  timestamp comment '��ʱȨ�޽���ʱ��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (USER_ID, RS_CODE, PRIV_TYPE)
);

/*==============================================================*/
/* Table: US_USER_SOC_PRIV                                      */
/*==============================================================*/
create table US_USER_SOC_PRIV
(
   USER_ID              varchar(20) not null comment '�û���',
   SOC_NAME             varchar(20) not null comment '����Դ����',
   PRIV_TYPE            int not null default 0 comment '��Դ����',
   AF_FLAG              int not null default 0 comment '������ֹ��־',
   TEMPSTART_BK_DATETIME timestamp comment '��ʱȨ�޿�ʼʱ��',
   TEMPEND_BK_DATETIME  timestamp comment '��ʱȨ�޽���ʱ��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (USER_ID, SOC_NAME, PRIV_TYPE)
);

/*==============================================================*/
/* Table: US_USER_SQL_PRIV                                      */
/*==============================================================*/
create table US_USER_SQL_PRIV
(
   USER_ID              varchar(20) not null comment '�û���',
   SOC_NAME             varchar(20) not null comment '����Դ����',
   TBL_NAME             varchar(50) not null comment '����',
   PRIV_TYPE            int not null default 0 comment '��Դ����',
   INS_PRIV_FLAG        int not null default 0 comment 'InsertȨ��',
   DEL_PRIV_FLAG        int not null default 0 comment 'DELETEȨ��',
   UPD_PRIV_FLAG        int not null default 0 comment 'UpdateȨ��',
   SEL_PRIV_FLAG        int not null default 0 comment 'SelectȨ��',
   AF_FLAG              int not null default 0 comment '������ֹ��־',
   TEMPSTART_BK_DATETIME timestamp comment '��ʱȨ�޿�ʼʱ��',
   TEMPEND_BK_DATETIME  timestamp comment '��ʱȨ�޽���ʱ��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (USER_ID, SOC_NAME, TBL_NAME, PRIV_TYPE)
);

/*==============================================================*/
/* Table: US_USER_TERM                                          */
/*==============================================================*/
create table US_USER_TERM
(
   USER_ID              varchar(20) not null comment '�û���',
   TERM_NO              varchar(40) not null comment '�ն˺�',
   CHANNEL_CODE         char(2) comment '��������',
   DEPT_ID              char(6) comment '���ű���',
   USER_CN_NAME         varchar(50) comment '�û�����',
   DEPT_CN_NAME         varchar(50) comment '��������',
   USE_STATE            int not null default 0 comment '����״̬',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (USER_ID, TERM_NO)
);

/*==============================================================*/
/* Table: WK_DEAL_DETAIL                                        */
/*==============================================================*/
create table WK_DEAL_DETAIL
(
   PEND_WORK_SEQ        varchar(17) not null comment '������������ˮ��',
   DEAL_SEQ             int not null default 0 comment '�������',
   DEAL_TYPE            int not null default 0 comment '������ʽ',
   DEAL_RES             int not null default 0 comment '�������',
   DEAL_USER_ID         varchar(20) comment '������Ա',
   DEAL_BK_DATE         date comment '��������',
   DEAL_BK_TIME         time comment '����ʱ��',
   DEAL_BK_DESC         varchar(100) comment '����˵��',
   primary key (PEND_WORK_SEQ, DEAL_SEQ)
);

/*==============================================================*/
/* Table: WK_DEAL_STATE                                         */
/*==============================================================*/
create table WK_DEAL_STATE
(
   PEND_WORK_SEQ        varchar(17) not null comment '������������ˮ��',
   SUBMIT_WORK_SEQ      varchar(17) comment '�ύ������ˮ��',
   PEND_WORK_CODE       varchar(10) comment '�������������',
   PEND_SRV_NAME        varchar(50) comment '��������������',
   PEND_RS_CODE         varchar(10) comment '��������Դ����',
   PEND_ARY_SOCNAME     varchar(100) comment '����������Դ����',
   PENDWK_BK_EXPL       varchar(500) comment '����������˵��',
   PEND_DEAL_SEQ        int not null default 0 comment '���������',
   PEND_USER_ID         varchar(20) comment '�������û�',
   PEND_USER_CN_NAME    varchar(50) comment '�������û�������',
   PBL_CODE             varchar(20) comment '���ⵥ����',
   PROXY_USER_ID        varchar(20) comment '�����û�',
   CRT_USER_ID          varchar(20) not null comment '�����û�',
   CRT_USER_CN_NAME     varchar(50) comment '�û�������',
   CRT_DEPT_ID          char(6) not null comment '��������',
   CRT_DEPT_CN_NAME     varchar(50) comment '����������',
   CRT_BK_DATE          date comment '��������',
   CRT_BK_TIME          time comment '����ʱ��',
   WORKFLOW_STATE       int not null default 0 comment '����״̬',
   BACKUP_FLD           varchar(50) comment '����',
   RCD_STATE            int not null default 0 comment '��¼״̬',
   APPLY_BK_EXPL        varchar(500) comment '��������˵��',
   primary key (PEND_WORK_SEQ)
);

/*==============================================================*/
/* Table: WK_DETAIL_REGISTER                                    */
/*==============================================================*/
create table WK_DETAIL_REGISTER
(
   PEND_WORK_SEQ        varchar(17) not null comment '������������ˮ��',
   INTE_DETAIL          BINARY(1048576) comment '�ӿ���ϸ��Ϣ',
   APROV_TYPE           int not null default 0 comment '����չʾ����',
   CUSTOM_RS_CODE       varchar(10) comment '����ҳ����Դ����',
   APPLY_HTML           BINARY(1048576) comment '����ҳ�����',
   primary key (PEND_WORK_SEQ)
);

/*==============================================================*/
/* Table: WK_WORK_PROCESS                                       */
/*==============================================================*/
create table WK_WORK_PROCESS
(
   PEND_WORK_SEQ        varchar(17) not null comment '������������ˮ��',
   DEAL_SEQ             int not null default 0 comment '�������',
   PEND_USER_ID         varchar(20) comment '������',
   PEND_USER_CN_NAME    varchar(50) comment '������������',
   PEND_TYPE            int not null default 0 comment '������ʽ',
   primary key (PEND_WORK_SEQ, DEAL_SEQ)
);

drop view if exists VI_SYS_ENV_SERVER;

drop table if exists BUILD_CONFIGFILE;

drop table if exists BUILD_SCRIPT;

drop table if exists BUILD_STEP;

drop table if exists CE_ENVIRONMENT;

drop table if exists CE_ENVIRONMENT_SERVER;

drop table if exists CE_PROJECT;

drop table if exists CE_SERVER;

drop table if exists CE_SERVER_DS;

drop table if exists CE_SYSTEM;

drop table if exists CE_SYSTEM_CFG;

drop table if exists CE_SYSTEM_TEMPLATE;

drop table if exists DP_DEPT_ENV_PRIV;

drop table if exists ENV_BUILD_TASK;

drop table if exists ENV_CONFIGFILE_MOD;

drop table if exists ENV_TAG_STORAGE;

drop table if exists ENV_TASK;

drop table if exists INSTANCE_EXE;

drop table if exists MO_MODULE;

drop table if exists MO_MODULE_QUOTE;

drop table if exists MO_COMPONENT;

drop table if exists MO_TEMPLATE;

drop table if exists PG_INTE_STEP;

drop table if exists PG_PROGRAM;

drop table if exists PG_RELE;

drop table if exists PG_RELE_STEP;

drop table if exists US_ROLE_ENV_PRIV;

drop table if exists US_USER_ENV_PRIV;

drop table if exists UU_FILELIST;

drop table if exists UU_PARAM;

drop table if exists UU_SOC;

/*==============================================================*/
/* Table: BUILD_CONFIGFILE                                      */
/*==============================================================*/
create table BUILD_CONFIGFILE
(
   FILE_WORK_SEQ        varchar(17) not null comment '�ļ������ˮ��',
   WORK_ID              varchar(14) comment '������',
   CFG_TYPE             int default 0 comment '��������',
   SERVER_NAME          varchar(20) comment '��������',
   SERVER_IP            varchar(20) comment '������IP',
   FOPT_TYPE            int default 0 comment '��������',
   FILE_BK_PATH         varchar(200) comment '�ļ�·��',
   FILE_BK_FNAME        varchar(50) comment '�ļ���',
   FILE_BK_CSUM         varchar(20) comment '�ļ�CSUM',
   DIR_YN_FLAG          int default 0 comment '�Ƿ�Ŀ¼��־',
   OPT_STATUS           int default 0 comment '����״̬',
   MODIFY_USER_ID       varchar(20) comment '�޸���',
   MODIFY_BK_DATE       date comment '�޸�����',
   MODIFY_BK_TIME       time comment '�޸�ʱ��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (FILE_WORK_SEQ)
);

alter table BUILD_CONFIGFILE comment '�������������ļ������';

/*==============================================================*/
/* Table: BUILD_SCRIPT                                          */
/*==============================================================*/
create table BUILD_SCRIPT
(
   WORK_ID              varchar(14) not null comment '������',
   SCRIPT_TYPE          int not null default 0 comment '�ű�����',
   SCIRPT_BK_SEQ        bigint not null default 0 comment '�ű����',
   SCRIPT_METHOD        int default 0 comment '�ű���ʽ',
   MODULE_ID            varchar(20) comment '���ID',
   MODULE_CN_NAME       varchar(50) comment '���������',
   SOC_UUID             char(32) comment '����ԴUUID',
   MODULE_PARAM_UUID    char(32) comment '���������UUID',
   INSTANCE_ID          varchar(50) comment 'ʵ��ID',
   SCRIPT_TEXT          varchar(1000) comment '�ű�����',
   EXE_STATUS           int default 0 comment 'ִ��״̬',
   EXE_RESULT           int default 0 comment 'ִ�н��',
   EXELOG_BK_PATH       varchar(200) comment 'ִ����־',
   EXE_USER_ID          varchar(20) comment 'ִ����',
   START_BK_TM          timestamp comment 'ִ�п�ʼʱ��',
   END_BK_TM            timestamp comment 'ִ�н���ʱ��',
   TIME_USED            int default 0 comment '��ʱ',
   IMPL_TYPE            int default 0 comment '���ִ������',
   primary key (WORK_ID, SCRIPT_TYPE, SCIRPT_BK_SEQ)
);

alter table BUILD_SCRIPT comment '�����ű���Ϣ��';

/*==============================================================*/
/* Table: BUILD_STEP                                            */
/*==============================================================*/
create table BUILD_STEP
(
   WORK_ID              varchar(14) not null comment '������',
   TEMPLATE_NAME        varchar(50) not null comment 'ģ����',
   PHASE_ID             int not null default 0 comment '�׶α��',
   PHASE_BK_DESC        varchar(100) comment '�׶�����',
   SOC_UUID             char(32) comment '����ԴUUID',
   GEN_YN_FLAG          int default 0 comment '�Ƿ�����ʵ��',
   IMPL_TYPE            int default 0 comment '���ִ������',
   primary key (WORK_ID, TEMPLATE_NAME, PHASE_ID)
);

alter table BUILD_STEP comment '�����׶α�';

/*==============================================================*/
/* Table: CE_ENVIRONMENT                                        */
/*==============================================================*/
create table CE_ENVIRONMENT
(
   ENV_NAME             varchar(20) not null comment '��������',
   ENV_CN_NAME          varchar(50) comment '�������',
   ENV_BK_DESC          varchar(200) comment '��������',
   ENV_TYPE             int not null default 0 comment '��������',
   SYS_NAME             char(20) comment 'Ӧ��ϵͳ',
   ELE_TYPE             varchar(20) comment '����Ҫ��',
   DT_RANGE             int default 0 comment '���ݷ�Χ',
   CREATE_BK_DATE       date comment '��������',
   CREATE_BK_TIME       time comment '����ʱ��',
   CREATE_USER_ID       varchar(20) comment '�����û�',
   MODIFY_BK_DATE       date comment '�޸�����',
   MODIFY_BK_TIME       time comment '�޸�ʱ��',
   MODIFY_USER_ID       varchar(20) comment '�޸��û�',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (ENV_NAME)
);

alter table CE_ENVIRONMENT comment '������';

/*==============================================================*/
/* Table: CE_ENVIRONMENT_SERVER                                 */
/*==============================================================*/
create table CE_ENVIRONMENT_SERVER
(
   ENV_NAME             varchar(20) not null comment '��������',
   SERVER_TYPE          int not null default 0 comment '����������',
   SERVER_NAME          varchar(20) not null comment '����������',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (ENV_NAME, SERVER_TYPE, SERVER_NAME)
);

alter table CE_ENVIRONMENT_SERVER comment '������������';

/*==============================================================*/
/* Table: CE_PROJECT                                            */
/*==============================================================*/
create table CE_PROJECT
(
   PROJECT_NAME         varchar(100) not null comment '��Ŀ���',
   PROJECT_SHORT_NAME   varchar(20) comment '��Ŀ���',
   PROJECT_BK_DESC      varchar(100) comment '��Ŀ����',
   SYS_NAME             char(20) comment 'Ӧ��ϵͳ',
   CREATE_BK_DATE       date comment '��������',
   CREATE_BK_TIME       time comment '����ʱ��',
   CREATE_USER_ID       varchar(20) comment '�����û�',
   MODIFY_BK_DATE       date comment '�޸�����',
   MODIFY_BK_TIME       time comment '�޸�ʱ��',
   MODIFY_USER_ID       varchar(20) comment '�޸��û�',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (PROJECT_NAME)
);

alter table CE_PROJECT comment '��Ŀ��';

/*==============================================================*/
/* Table: CE_SERVER                                             */
/*==============================================================*/
create table CE_SERVER
(
   SERVER_NAME          varchar(20) not null comment '����������',
   SERVER_CN_NAME       varchar(50) comment '���������',
   SERVER_DESC          varchar(200) comment '����������',
   SERVER_IP            varchar(20) comment '��������ַ',
   SERVER_OS            int not null default 0 comment '����ϵͳ',
   OS_SBK_VER           varchar(20) comment '����ϵͳ�汾',
   SERVER_DB            varchar(200) comment '���ݿ�����',
   SERVER_MID_WARE      varchar(20) comment '�м��',
   CREATE_BK_DATE       date comment '��������',
   CREATE_BK_TIME       time comment '����ʱ��',
   CREATE_USER_ID       varchar(20) comment '�����û�',
   MODIFY_BK_DATE       date comment '�޸�����',
   MODIFY_BK_TIME       time comment '�޸�ʱ��',
   MODIFY_USER_ID       varchar(20) comment '�޸��û�',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (SERVER_NAME)
);

alter table CE_SERVER comment '��������';

/*==============================================================*/
/* Table: CE_SERVER_DS                                          */
/*==============================================================*/
create table CE_SERVER_DS
(
   SERVER_NAME          varchar(20) not null comment '����������',
   SOC_NAME             varchar(20) not null comment '����Դ����',
   APPLY_TYPE           varchar(20) comment '����Դ��;',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (SERVER_NAME, SOC_NAME)
);

alter table CE_SERVER_DS comment '����������Դ��';

/*==============================================================*/
/* Table: CE_SYSTEM                                             */
/*==============================================================*/
create table CE_SYSTEM
(
   SYS_NAME             char(20) not null comment 'Ӧ��ϵͳ����',
   SYS_CN_NAME          varchar(50) comment 'Ӧ��ϵͳ���',
   SYS_TYPE             int comment 'Ӧ��ϵͳ����',
   SYS_BK_DESC          varchar(200) comment 'Ӧ��ϵͳ����',
   CREATE_BK_DATE       date comment '��������',
   CREATE_BK_TIME       time comment '����ʱ��',
   CREATE_USER_ID       varchar(20) comment '�����û�',
   MODIFY_BK_DATE       date comment '�޸�����',
   MODIFY_BK_TIME       time comment '�޸�ʱ��',
   MODIFY_USER_ID       varchar(20) comment '�޸��û�',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (SYS_NAME)
);

alter table CE_SYSTEM comment 'Ӧ��ϵͳ��';

/*==============================================================*/
/* Table: CE_SYSTEM_CFG                                         */
/*==============================================================*/
create table CE_SYSTEM_CFG
(
   SYS_NAME             char(20) not null comment 'Ӧ��ϵͳ����',
   CFG_BK_FNAME         varchar(50) not null comment '�ļ���',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (SYS_NAME, CFG_BK_FNAME)
);

alter table CE_SYSTEM_CFG comment 'Ӧ��ϵͳ�����ļ���';

/*==============================================================*/
/* Table: CE_SYSTEM_TEMPLATE                                    */
/*==============================================================*/
create table CE_SYSTEM_TEMPLATE
(
   SYS_NAME             char(20) not null comment 'Ӧ��ϵͳ����',
   TEMPLATE_TYPE        int not null comment 'ģ������',
   TEMPLATE_NAME        varchar(50) not null comment 'ģ������',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (SYS_NAME, TEMPLATE_TYPE, TEMPLATE_NAME)
);

alter table CE_SYSTEM_TEMPLATE comment 'Ӧ��ϵͳģ���';

/*==============================================================*/
/* Table: DP_DEPT_ENV_PRIV                                      */
/*==============================================================*/
create table DP_DEPT_ENV_PRIV
(
   DEPT_ID              char(6) not null comment '���ű���',
   ENV_NAME             varchar(20) not null comment '��������',
   SYS_NAME             char(20) comment 'Ӧ��ϵͳ',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (DEPT_ID, ENV_NAME)
);

alter table DP_DEPT_ENV_PRIV comment '����Ӧ�û���Ȩ�ޱ�';

/*==============================================================*/
/* Table: ENV_BUILD_TASK                                        */
/*==============================================================*/
create table ENV_BUILD_TASK
(
   WORK_ID              varchar(14) not null comment '������',
   TEMPLATE_NAME        varchar(50) comment 'ģ����',
   TEMPLATE_PARAM_UUID  char(32) comment 'ģ�����UUID',
   VER_SOC_UUID         char(32) comment '�汾������ԴUUID',
   EXELOG_BK_PATH       varchar(200) comment '������־',
   BUILD_STEP_ID        int default 0 comment '����������',
   TASK_STATUS          int default 0 comment '����״̬',
   EXE_RESULT           int default 0 comment 'ִ�н��',
   EXE_USER_ID          varchar(20) comment 'ִ����',
   START_BK_TM          timestamp comment 'ִ�п�ʼʱ��',
   END_BK_TM            timestamp comment 'ִ�н���ʱ��',
   primary key (WORK_ID)
);

alter table ENV_BUILD_TASK comment '����������չ��';

/*==============================================================*/
/* Table: ENV_CONFIGFILE_MOD                                    */
/*==============================================================*/
create table ENV_CONFIGFILE_MOD
(
   FILE_WORK_SEQ        varchar(17) not null comment '�ļ������ˮ��',
   BATCH_NO             varchar(12) comment '���κ�',
   ENV_NAME             varchar(20) comment '��������',
   SERVER_NAME          varchar(20) comment '��������',
   SERVER_IP            varchar(20) not null comment '������IP',
   FOPT_TYPE            int not null default 0 comment '��������',
   FILE_BK_PATH         varchar(200) comment '�ļ�·��',
   FILE_BK_FNAME        varchar(50) comment '�ļ���',
   FILE_BK_CSUM         varchar(20) comment '�ļ�CSUM',
   DIR_YN_FLAG          int not null default 0 comment '�Ƿ�Ŀ¼��־',
   OPT_STATUS           int not null default 0 comment '����״̬',
   MODIFY_USER_ID       varchar(20) comment '�޸���',
   MODIFY_BK_DATE       date comment '�޸�����',
   MODIFY_BK_TIME       time comment '�޸�ʱ��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (FILE_WORK_SEQ)
);

alter table ENV_CONFIGFILE_MOD comment '���������ļ������';

/*==============================================================*/
/* Table: ENV_TAG_STORAGE                                       */
/*==============================================================*/
create table ENV_TAG_STORAGE
(
   STORAGE_ID           varchar(14) not null comment '�����',
   STORAGE_BK_DESC      varchar(200) comment '�������',
   ENV_NAME             varchar(20) comment '��������',
   PROJECT_NAME         varchar(100) comment '��Ŀ���',
   INSTANCE_ID          varchar(50) comment 'ʵ��ID',
   EXE_SOC_UUID         char(32) comment 'ִ������ԴUUID',
   TAR_VERSOC_UUID      char(32) comment 'Ŀ��汾����ԴUUID',
   STORAGE_LIST_UUID    char(32) comment '����嵥UUID',
   INTE_VER_NUM         varchar(50) comment '���ɰ汾��',
   TAR_VER_NUM          varchar(50) comment 'Ŀ��汾��',
   STORAGE_STATUS       int default 0 comment '���״̬',
   CRT_USER_ID          varchar(20) comment '������',
   STORAGE_RESULT       int default 0 comment '�����',
   CRT_BK_DATE          date comment '��������',
   CRT_BK_TIME          time comment '����ʱ��',
   STORAGE_USER_ID      varchar(20) comment '�����',
   START_BK_TM          timestamp comment '��⿪ʼʱ��',
   END_BK_TM            timestamp comment '������ʱ��',
   LOG_BK_PATH          varchar(200) comment '�����־ȫ·��',
   TIME_USED            int default 0 comment '��ʱ',
   primary key (STORAGE_ID)
);

alter table ENV_TAG_STORAGE comment 'Ŀ������';

/*==============================================================*/
/* Table: ENV_TASK                                              */
/*==============================================================*/
create table ENV_TASK
(
   WORK_ID              varchar(14) not null comment '������',
   TASK_TYPE            int default 0 comment '��������',
   TASK_BK_DESC         varchar(200) comment '��������',
   ROL_WORK_ID          varchar(14) comment '����������',
   ENV_NAME             varchar(20) comment '��������',
   PROJECT_NAME         varchar(100) comment '������Ŀ',
   PROG_ID              varchar(14) comment '�������',
   INSTANCE_ID          varchar(50) comment 'ʵ��ID',
   EXE_METHOD           int default 0 comment 'ִ�ж���',
   TAGPAC_LIST_UUID     char(32) comment '����Ŀ����嵥UUID',
   PUB_LIST_UUID        char(32) comment '�����嵥UUID',
   CODE_VER_NUM         varchar(50) comment 'Դ��汾���汾��',
   TARGET_VER_NUM       varchar(50) comment 'Ŀ��汾���汾��',
   VERCODE_VER_NUM      varchar(50) comment '�汾����Դ��汾��',
   VERTARGET_VER_NUM    varchar(50) comment '�汾����Ŀ��汾��',
   TASK_STATUS          int default 0 comment '����״̬',
   EXE_RESULT           int default 0 comment 'ִ�н��',
   EXELOG_BK_PATH       varchar(200) comment 'ִ����־',
   CRT_USER_ID          varchar(20) comment '������',
   CRT_BK_DATE          date comment '��������',
   CRT_BK_TIME          time comment '����ʱ��',
   EXE_USER_ID          varchar(20) comment 'ִ����',
   START_BK_TM          timestamp comment 'ִ�п�ʼʱ��',
   END_BK_TM            timestamp comment 'ִ�н���ʱ��',
   TIME_USED            int not null default 0 comment '��ʱ',
   primary key (WORK_ID)
);

alter table ENV_TASK comment '���������';

/*==============================================================*/
/* Table: INSTANCE_EXE                                          */
/*==============================================================*/
create table INSTANCE_EXE
(
   INSTANCE_ID          varchar(50) not null comment 'ִ��ʵ��ID',
   INST_BK_NO           int not null default 0 comment 'ʵ���׶κ�',
   TPL_BK_NO            int default 0 comment 'ģ������׶κ�',
   STEP_BK_DESC         varchar(100) comment '�׶�����',
   SERVER_NAME          varchar(20) comment 'ִ�з�������',
   SOC_NAME             varchar(20) comment 'ִ������Դ��',
   EXE_STATUS           int default 0 comment 'ִ��״̬',
   EXE_RESULT           int default 0 comment 'ִ�н��',
   EXEC_TEXT            varchar(500) comment 'ִ����Ϣ',
   START_BK_TM          timestamp comment 'ִ�п�ʼʱ��',
   END_BK_TM            timestamp comment 'ִ�н���ʱ��',
   TIME_USED            int default 0 comment '��ʱ',
   primary key (INSTANCE_ID, INST_BK_NO)
);

alter table INSTANCE_EXE comment 'ʵ��ִ����Ϣ��';

/*==============================================================*/
/* Table: MO_MODULE                                             */
/*==============================================================*/
create table MO_MODULE
(
   MODULE_ID            varchar(20) not null comment '���ID',
   PUBLISH_STATE        int default 0 comment '����״̬',
   CRT_USER_ID          varchar(20) comment '������',
   CRT_BK_DATE          date comment '��������',
   CRT_BK_TIME          time comment '����ʱ��',
   MODIFY_USER_ID       varchar(20) comment '�޸���',
   MODIFY_BK_DATE       date comment '�޸�����',
   MODIFY_BK_TIME       time comment '�޸�ʱ��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   IMPL_TYPE            int default 0 comment '���ʵ������',
   SCRIPT_SOURCE        int comment '�ű���Դ',
   MODULE_PURPOSE       int comment '�����;',
   MODULE_TYPE          int not null default 0 comment '�������',
   MODULE_BK_DESC       varchar(500) comment '�������',
   MODULE_CN_NAME       varchar(50) comment '���������',
   primary key (MODULE_ID)
);

alter table MO_MODULE comment '�����Ϣ��';

/*==============================================================*/
/* Table: MO_MODULE_QUOTE                                       */
/*==============================================================*/
create table MO_MODULE_QUOTE
(
   MODULE_ID            varchar(20) not null comment '���ID',
   QUOTE_MODULE_ID      varchar(20) not null comment '�������ID',
   QUOTE_MODULE_TYPE    int default 0 comment '�����������',
   primary key (MODULE_ID, QUOTE_MODULE_ID)
);

alter table MO_MODULE_QUOTE comment '������ñ�';

/*==============================================================*/
/* Table: MO_COMPONENT                                          */
/*==============================================================*/
create table MO_COMPONENT
(
   COMPONENT_ID         varchar(20) not null comment '���ID',
   COMPONENT_CN_NAME    varchar(50) comment '���������',
   COMPONENT_BK_DESC    varchar(500) comment '�������',
   MODULE_TYPE          int default 0 comment '�������',
   COMPONENT_PURPOSES   varchar(10) comment '�����;',
   COMPONENT_SOURCE     int comment '�ű���Դ',
   IMPL_TYPE            int default 0 comment '���ʵ������',
   LANGUAGE_VERSION     varchar(50) comment '���԰汾',
   PUBLISH_STATE        int default 0 comment '����״̬',
   CRT_USER_ID          varchar(20) comment '������',
   CRT_BK_DATE          date comment '��������',
   CRT_BK_TIME          time comment '����ʱ��',
   MODIFY_USER_ID       varchar(20) comment '�޸���',
   MODIFY_BK_DATE       date comment '�޸�����',
   MODIFY_BK_TIME       time comment '�޸�ʱ��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (COMPONENT_ID)
);

alter table MO_COMPONENT comment '�����Ϣ��';

/*==============================================================*/
/* Table: MO_TEMPLATE                                           */
/*==============================================================*/
create table MO_TEMPLATE
(
   TEMPLATE_NAME        varchar(50) not null comment 'ģ������',
   TEMPLATE_FORMATE     int not null default 0 comment 'ģ���ʽ',
   TEMPLATE_CN_NAME     varchar(50) comment 'ģ��������',
   TP_CLASS_NAME        varchar(100) comment 'ģ������',
   SCRIPT_FILE_PATH     varchar(200) comment 'ģ��·��',
   REF_MODULE_ID        varchar(20) comment '����ID',
   TEMPLATE_TYPE        int comment 'ģ������',
   OPERATING_SYSTEM     varchar(20) comment '����ϵͳ',
   PUBLISH_STATE        int not null default 0 comment '����״̬',
   TEMPLATE_BK_DESC     varchar(100) comment 'ģ������',
   CRT_BK_DATE          date comment '��������',
   CRT_BK_TIME          time comment '����ʱ��',
   CRT_USER_ID          varchar(20) comment '�����û�',
   MODIFY_BK_DATE       date comment '�޸�����',
   MODIFY_BK_TIME       time comment '�޸�ʱ��',
   MODIFY_USER_ID       varchar(20) comment '�޸��û�',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (TEMPLATE_NAME)
);

alter table MO_TEMPLATE comment 'Ͷ��ģ���';

/*==============================================================*/
/* Table: PG_INTE_STEP                                          */
/*==============================================================*/
create table PG_INTE_STEP
(
   PROG_ID              varchar(14) not null comment '�������',
   STEP_ID              int not null default 0 comment '������',
   STEP_EXPL            varchar(40) comment '����˵��',
   STEP_TYPE            int default 0 comment '��������',
   SOC_UUID             char(32) comment '����ԴUUID',
   STEP_BK_SCRIPT       varchar(1000) comment '�ű�',
   COMPILE_TYPE         int default 0 comment '��������',
   COMPLIE_BK_PATH      varchar(200) comment '����·��',
   ENV_VARIABLE         varchar(500) comment '��������',
   COMPILE_PARAM        varchar(200) comment '�������',
   STORAGE_LIST_UUID    char(32) comment '����嵥UUID',
   STORAGE_BK_PATH      varchar(200) comment '�����·��',
   primary key (PROG_ID, STEP_ID)
);

alter table PG_INTE_STEP comment '���ɷ��������';

/*==============================================================*/
/* Table: PG_PROGRAM                                            */
/*==============================================================*/
create table PG_PROGRAM
(
   PROG_ID              varchar(14) not null comment '�������',
   PROG_NAME            varchar(50) comment '��������',
   ENV_NAME             varchar(20) comment '��������',
   PROG_BK_DESC         varchar(100) comment '��������',
   PROG_TYPE            int not null default 0 comment '��������',
   IS_PUBLISH           int not null default 0 comment '�Ƿ񷢲�',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   CRT_USER_ID          varchar(20) comment '������',
   CRT_BK_DATE          date comment '��������',
   CRT_BK_TIME          time comment '����ʱ��',
   primary key (PROG_ID)
);

alter table PG_PROGRAM comment '����������';

/*==============================================================*/
/* Table: PG_RELE                                               */
/*==============================================================*/
create table PG_RELE
(
   PROG_ID              varchar(14) not null comment '�������',
   PUB_TEMPLATE_NAME    varchar(50) comment '����ģ��',
   PUBL_PARAM_UUID      char(32) comment '����ģ�����UUID',
   ROL_TEMPLATE_NAME    varchar(50) comment '����ģ��',
   ROLL_PARAM_UUID      char(32) comment '����ģ�����UUID',
   VER_SOC_UUID         char(32) comment '�汾������ԴUUID',
   primary key (PROG_ID)
);

alter table PG_RELE comment '����������չ��';

/*==============================================================*/
/* Table: PG_RELE_STEP                                          */
/*==============================================================*/
create table PG_RELE_STEP
(
   PROG_ID              varchar(14) not null comment '�������',
   TEMPLATE_NAME        varchar(50) not null comment 'ģ����',
   PHASE_ID             int not null default 0 comment '�׶α��',
   PHASE_BK_DESC        varchar(100) comment '�׶�����',
   SOC_UUID             char(32) not null comment '����ԴUUID',
   GEN_YN_FLAG          int not null default 0 comment '�Ƿ�����ʵ��',
   IMPL_TYPE            int not null default 0 comment 'ʵ������',
   primary key (PROG_ID, TEMPLATE_NAME, PHASE_ID)
);

alter table PG_RELE_STEP comment '���������׶α�';

/*==============================================================*/
/* Table: US_ROLE_ENV_PRIV                                      */
/*==============================================================*/
create table US_ROLE_ENV_PRIV
(
   DPRL_CODE            char(8) not null comment '���Ž�ɫ����',
   ENV_NAME             varchar(20) not null comment '��������',
   SYS_NAME             char(20) comment 'Ӧ��ϵͳ',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (DPRL_CODE, ENV_NAME)
);

alter table US_ROLE_ENV_PRIV comment '���Ž�ɫӦ�û���Ȩ�ޱ�';

/*==============================================================*/
/* Table: US_USER_ENV_PRIV                                      */
/*==============================================================*/
create table US_USER_ENV_PRIV
(
   USER_ID              varchar(20) not null comment '�û���',
   ENV_NAME             varchar(20) not null comment '��������',
   PRIV_TYPE            int not null default 0 comment '��Դ����',
   SYS_NAME             char(20) comment 'Ӧ��ϵͳ',
   AF_FLAG              int not null default 0 comment '������ֹ��־',
   TEMPSTART_BK_DATETIME timestamp comment '��ʱȨ�޿�ʼʱ��',
   TEMPEND_BK_DATETIME  timestamp comment '��ʱȨ�޽���ʱ��',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   primary key (USER_ID, ENV_NAME, PRIV_TYPE)
);

alter table US_USER_ENV_PRIV comment '�û�Ӧ�û���Ȩ�ޱ�';

/*==============================================================*/
/* Table: UU_FILELIST                                           */
/*==============================================================*/
create table UU_FILELIST
(
   LIST_UUID            char(32) not null comment '�嵥UUID',
   FILE_WORK_SEQ        varchar(17) not null comment '�ļ���ˮ��',
   FILE_PATH            varchar(200) comment '�ļ�Ŀ¼',
   FILE_NAME            varchar(50) comment '�ļ���',
   FILE_TYPE            varchar(10) comment '�ļ�����',
   FILE_SIZE            int default 0 comment '�ļ���С',
   PACKAGE_NAME         varchar(50) comment '��������',
   SERVER_NAME          varchar(20) comment '������������',
   STORAGE_BK_PATH      varchar(200) comment '�����·��',
   TARGET_RELATIVE_PATH varchar(200) comment 'Ŀ������·��',
   primary key (LIST_UUID, FILE_WORK_SEQ)
);

alter table UU_FILELIST comment '�ļ��嵥��';

/*==============================================================*/
/* Table: UU_PARAM                                              */
/*==============================================================*/
create table UU_PARAM
(
   PARAM_UUID           char(32) not null comment '����UUID',
   PARAM_NAME           varchar(50) not null comment '������',
   PARAM_TYPE           int comment '��������',
   PARAM_VALUE          varchar(500) comment '����ֵ',
   PARAM_CN_NAME        varchar(50) comment '����������',
   PARAM_BK_DESC        varchar(100) comment '��������',
   PARAM_MODIFY_FLAG    int default 0 comment '�Ƿ���޸�',
   BACKUP_FLD           varchar(50) comment '�����ֶ�',
   PARAM_GROUP          varchar(20) comment '��������',
   PHASE_NO             int default 0 comment '�׶κ�',
   primary key (PARAM_UUID, PARAM_NAME)
);

alter table UU_PARAM comment 'ģ�����������';

/*==============================================================*/
/* Table: UU_SOC                                                */
/*==============================================================*/
create table UU_SOC
(
   SOC_UUID             char(32) not null comment '����ԴUUID',
   SOC_BK_SEQ           bigint not null default 0 comment '����Դ���',
   EXE_SERVER_NAME      varchar(20) comment 'ִ�з�������',
   EXE_SOC_NAME         varchar(20) comment 'ִ������Դ��',
   VER_SERVER_NAME      varchar(20) comment '�汾��������',
   VER_SOC_NAME         varchar(20) comment '�汾����Դ��',
   CODE_BK_DIR          varchar(100) comment 'Դ��汾Ŀ¼',
   primary key (SOC_UUID, SOC_BK_SEQ)
);

alter table UU_SOC comment '����Դ������';

create view VI_SYS_ENV_SERVER as select sys.SYS_NAME,sys.SYS_CN_NAME,sys.SYS_BK_DESC,sys.SYS_TYPE,en.ENV_NAME,en.ENV_CN_NAME,en.ENV_BK_DESC,en.ENV_TYPE,en.DT_RANGE,se.SERVER_NAME,se.SERVER_CN_NAME,SERVER_DESC,se.SERVER_IP,se.SERVER_OS,se.OS_SBK_VER,se.SERVER_DB,se.SERVER_MID_WARE from ce_system sys left join ce_environment en on sys.SYS_NAME=en.SYS_NAME LEFT JOIN ce_environment_server es on en.ENV_NAME=es.ENV_NAME left JOIN ce_server se on es.SERVER_NAME=se.SERVER_NAME;