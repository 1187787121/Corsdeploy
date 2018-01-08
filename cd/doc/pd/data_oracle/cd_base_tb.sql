drop table CH_CHANNEL cascade constraints;

drop table CH_CHANNEL_SRVG_PRIV cascade constraints;

drop table CH_CHANNEL_SRV_PRIV cascade constraints;

drop table CM_SEQ cascade constraints;

drop table DC_DICT cascade constraints;

drop table DC_DICT_ENU cascade constraints;

drop table DP_DEPT cascade constraints;

drop table DP_DEPT_COL_PRIV cascade constraints;

drop table DP_DEPT_OPT_PRIV cascade constraints;

drop table DP_DEPT_RS_PRIV cascade constraints;

drop table DP_DEPT_SOC_PRIV cascade constraints;

drop table DP_DEPT_SQL_PRIV cascade constraints;

drop table DT_SOURCE cascade constraints;

drop table LG_LOG_DOWN cascade constraints;

drop table LG_LOG_LABEL cascade constraints;

drop index LG_LOG_MF_I3;

drop index LG_LOG_MF_I2;

drop index LG_LOG_MF_I1;

drop table LG_LOG_MF cascade constraints;

drop table MG_MSG cascade constraints;

drop table MG_MSG_USER cascade constraints;

drop table RS_OPT cascade constraints;

drop table RS_RES cascade constraints;

drop table RT_SVC_EXE cascade constraints;

drop table SV_REMOTE_SRV cascade constraints;

drop table SV_SRV cascade constraints;

drop table SV_SRVG cascade constraints;

drop table SV_SRV_AUTH cascade constraints;

drop table SV_SRV_CHECK cascade constraints;

drop table SV_SRV_SOC cascade constraints;

drop table TM_TERM cascade constraints;

drop table US_DEPT_ROLE cascade constraints;

drop table US_ROLE cascade constraints;

drop table US_ROLE_COL_PRIV cascade constraints;

drop table US_ROLE_OPT_PRIV cascade constraints;

drop table US_ROLE_RS_PRIV cascade constraints;

drop table US_ROLE_SOC_PRIV cascade constraints;

drop table US_ROLE_SQL_PRIV cascade constraints;

drop table US_USER cascade constraints;

drop table US_USER_COL_PRIV cascade constraints;

drop table US_USER_CONTACT cascade constraints;

drop table US_USER_OPT_PRIV cascade constraints;

drop table US_USER_ROLE cascade constraints;

drop table US_USER_RS_PRIV cascade constraints;

drop table US_USER_SOC_PRIV cascade constraints;

drop table US_USER_SQL_PRIV cascade constraints;

drop table US_USER_TERM cascade constraints;

drop table WK_DEAL_DETAIL cascade constraints;

drop index WK_DEAL_STATE_I3;

drop index WK_DEAL_STATE_I2;

drop index WK_DEAL_STATE_I1;

drop table WK_DEAL_STATE cascade constraints;

drop table WK_DETAIL_REGISTER cascade constraints;

drop table WK_WORK_PROCESS cascade constraints;

/*==============================================================*/
/* Table: CH_CHANNEL                                            */
/*==============================================================*/
create table CH_CHANNEL  (
   CHANNEL_CODE         CHAR(2)                         not null,
   CHANNEL_CN_NAME      VARCHAR2(50),
   CHANNEL_TYPE         INTEGER                        default 0 not null,
   CHANNEL_BK_DESC      VARCHAR2(100),
   BACKUP_FLD           VARCHAR2(50),
   RCD_STATE            INTEGER                        default 0 not null,
   constraint PK_CH_CHANNEL primary key (CHANNEL_CODE)
);

comment on table CH_CHANNEL is
'���������';

comment on column CH_CHANNEL.CHANNEL_CODE is
'��������';

comment on column CH_CHANNEL.CHANNEL_CN_NAME is
'������������';

comment on column CH_CHANNEL.CHANNEL_TYPE is
'��������';

comment on column CH_CHANNEL.CHANNEL_BK_DESC is
'��������';

comment on column CH_CHANNEL.BACKUP_FLD is
'�����ֶ�';

comment on column CH_CHANNEL.RCD_STATE is
'��¼״̬';

/*==============================================================*/
/* Table: CH_CHANNEL_SRVG_PRIV                                  */
/*==============================================================*/
create table CH_CHANNEL_SRVG_PRIV  (
   CHANNEL_CODE         CHAR(2)                         not null,
   SRVG_CODE            CHAR(2)                         not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_CH_CHANNEL_SRVG_PRIV primary key (CHANNEL_CODE, SRVG_CODE)
);

comment on table CH_CHANNEL_SRVG_PRIV is
'����������Ȩ�����ñ�';

comment on column CH_CHANNEL_SRVG_PRIV.CHANNEL_CODE is
'��������';

comment on column CH_CHANNEL_SRVG_PRIV.SRVG_CODE is
'���������';

comment on column CH_CHANNEL_SRVG_PRIV.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: CH_CHANNEL_SRV_PRIV                                   */
/*==============================================================*/
create table CH_CHANNEL_SRV_PRIV  (
   CHANNEL_CODE         CHAR(2)                         not null,
   SRV_NAME             VARCHAR2(50)                    not null,
   AF_FLAG              INTEGER                        default 0 not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_CH_CHANNEL_SRV_PRIV primary key (CHANNEL_CODE, SRV_NAME)
);

comment on table CH_CHANNEL_SRV_PRIV is
'��������Ȩ�����ñ�';

comment on column CH_CHANNEL_SRV_PRIV.CHANNEL_CODE is
'��������';

comment on column CH_CHANNEL_SRV_PRIV.SRV_NAME is
'������';

comment on column CH_CHANNEL_SRV_PRIV.AF_FLAG is
'�����ֹ��־';

comment on column CH_CHANNEL_SRV_PRIV.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: CM_SEQ                                                */
/*==============================================================*/
create table CM_SEQ  (
   SEQ_NAME             VARCHAR2(6)                     not null,
   CUR_BK_SEQ           INTEGER                        default 0 not null,
   SEQ_FLD_LENGTH       INTEGER                        default 0 not null,
   SEQ_TYPE             INTEGER                        default 0 not null,
   LMOD_BK_DATE         DATE,
   LS_BK_SEQ            INTEGER                        default 0 not null,
   constraint PK_CM_SEQ primary key (SEQ_NAME)
);

comment on table CM_SEQ is
'��ű�';

comment on column CM_SEQ.SEQ_NAME is
'�������';

comment on column CM_SEQ.CUR_BK_SEQ is
'��ǰ���';

comment on column CM_SEQ.SEQ_FLD_LENGTH is
'��ų���';

comment on column CM_SEQ.SEQ_TYPE is
'�������';

comment on column CM_SEQ.LMOD_BK_DATE is
'�ϴ��޸�����';

comment on column CM_SEQ.LS_BK_SEQ is
'�������';

/*==============================================================*/
/* Table: DC_DICT                                               */
/*==============================================================*/
create table DC_DICT  (
   DOMAIN_NAME          VARCHAR2(20)                    not null,
   DOMAIN_CN_NAME       VARCHAR2(50),
   FLD_TYPE             VARCHAR2(20),
   FLD_LENGTH           INTEGER                        default 0 not null,
   FLD_SCALE            INTEGER                        default 0 not null,
   ENU_YN_FLAG          INTEGER                        default 0 not null,
   constraint PK_DC_DICT primary key (DOMAIN_NAME)
);

comment on table DC_DICT is
'�����ֵ���Ϣ��';

comment on column DC_DICT.DOMAIN_NAME is
'�����';

comment on column DC_DICT.DOMAIN_CN_NAME is
'����������';

comment on column DC_DICT.FLD_TYPE is
'����';

comment on column DC_DICT.FLD_LENGTH is
'�ܳ���';

comment on column DC_DICT.FLD_SCALE is
'С��λ';

comment on column DC_DICT.ENU_YN_FLAG is
'�Ƿ�ö��';

/*==============================================================*/
/* Table: DC_DICT_ENU                                           */
/*==============================================================*/
create table DC_DICT_ENU  (
   DOMAIN_NAME          VARCHAR2(20)                    not null,
   ENU_VALUE            INTEGER                        default 0 not null,
   ENU_CODE             VARCHAR2(50),
   ENU_BK_EXPL          VARCHAR2(500),
   constraint PK_DC_DICT_ENU primary key (DOMAIN_NAME, ENU_VALUE)
);

comment on table DC_DICT_ENU is
'�����ֵ�ö�ٱ�';

comment on column DC_DICT_ENU.DOMAIN_NAME is
'������';

comment on column DC_DICT_ENU.ENU_VALUE is
'ѡ��ֵ';

comment on column DC_DICT_ENU.ENU_CODE is
'ѡ�����';

comment on column DC_DICT_ENU.ENU_BK_EXPL is
'ѡ��˵��';

/*==============================================================*/
/* Table: DP_DEPT                                               */
/*==============================================================*/
create table DP_DEPT  (
   DEPT_ID              CHAR(6)                         not null,
   DEPT_CN_NAME         VARCHAR2(50),
   DEPT_FULL_CNAME      VARCHAR2(50),
   DEPT_TYPE            INTEGER                        default 0 not null,
   DEPT_LEVEL           INTEGER                        default 0 not null,
   BRANCH_NO            VARCHAR2(10),
   SUPER_DEPT_ID        CHAR(6),
   BACKUP_FLD           VARCHAR2(50),
   CRT_BK_DATE          DATE,
   CRT_BK_TIME          DATE,
   CRT_USER_ID          VARCHAR2(20),
   MODIFY_BK_DATE       DATE,
   MODIFY_BK_TIME       DATE,
   MODIFY_USER_ID       VARCHAR2(20),
   DEL_BK_DATE          DATE,
   DEL_BK_TIME          DATE,
   DEL_USER_ID          VARCHAR2(20),
   RCD_STATE            INTEGER                        default 0 not null,
   constraint PK_DP_DEPT primary key (DEPT_ID)
);

comment on table DP_DEPT is
'���ű�';

comment on column DP_DEPT.DEPT_ID is
'���ű���';

comment on column DP_DEPT.DEPT_CN_NAME is
'��������';

comment on column DP_DEPT.DEPT_FULL_CNAME is
'����ȫ��';

comment on column DP_DEPT.DEPT_TYPE is
'��������';

comment on column DP_DEPT.DEPT_LEVEL is
'���ż���';

comment on column DP_DEPT.BRANCH_NO is
'������';

comment on column DP_DEPT.SUPER_DEPT_ID is
'�ϼ����ű���';

comment on column DP_DEPT.BACKUP_FLD is
'�����ֶ�';

comment on column DP_DEPT.CRT_BK_DATE is
'��������';

comment on column DP_DEPT.CRT_BK_TIME is
'����ʱ��';

comment on column DP_DEPT.CRT_USER_ID is
'�����û�';

comment on column DP_DEPT.MODIFY_BK_DATE is
'�޸�����';

comment on column DP_DEPT.MODIFY_BK_TIME is
'�޸�ʱ��';

comment on column DP_DEPT.MODIFY_USER_ID is
'�޸��û�';

comment on column DP_DEPT.DEL_BK_DATE is
'ɾ������';

comment on column DP_DEPT.DEL_BK_TIME is
'ɾ��ʱ��';

comment on column DP_DEPT.DEL_USER_ID is
'ɾ���û�';

comment on column DP_DEPT.RCD_STATE is
'��¼״̬';

/*==============================================================*/
/* Table: DP_DEPT_COL_PRIV                                      */
/*==============================================================*/
create table DP_DEPT_COL_PRIV  (
   DEPT_ID              CHAR(6)                         not null,
   SOC_NAME             VARCHAR2(20)                    not null,
   TBL_NAME             VARCHAR2(50)                    not null,
   COL_NAME             VARCHAR2(50)                    not null,
   INS_PRIV_FLAG        INTEGER                        default 0 not null,
   DEL_PRIV_FLAG        INTEGER                        default 0 not null,
   UPD_PRIV_FLAG        INTEGER                        default 0 not null,
   SEL_PRIV_FLAG        INTEGER                        default 0 not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_DP_DEPT_COL_PRIV primary key (DEPT_ID, SOC_NAME, TBL_NAME, COL_NAME)
);

comment on table DP_DEPT_COL_PRIV is
'����SQL�ֶβ���Ȩ�ޱ�';

comment on column DP_DEPT_COL_PRIV.DEPT_ID is
'���ű���';

comment on column DP_DEPT_COL_PRIV.SOC_NAME is
'����Դ����';

comment on column DP_DEPT_COL_PRIV.TBL_NAME is
'����';

comment on column DP_DEPT_COL_PRIV.COL_NAME is
'�ֶ�����';

comment on column DP_DEPT_COL_PRIV.INS_PRIV_FLAG is
'INSERTȨ��';

comment on column DP_DEPT_COL_PRIV.DEL_PRIV_FLAG is
'DELETEȨ��';

comment on column DP_DEPT_COL_PRIV.UPD_PRIV_FLAG is
'UPDATEȨ��';

comment on column DP_DEPT_COL_PRIV.SEL_PRIV_FLAG is
'SELECTȨ��';

comment on column DP_DEPT_COL_PRIV.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: DP_DEPT_OPT_PRIV                                      */
/*==============================================================*/
create table DP_DEPT_OPT_PRIV  (
   OPT_CODE             VARCHAR2(12)                    not null,
   DEPT_ID              CHAR(6)                         not null,
   PRIV_FLAG            INTEGER                        default 0 not null,
   constraint PK_DP_DEPT_OPT_PRIV primary key (OPT_CODE, DEPT_ID)
);

comment on table DP_DEPT_OPT_PRIV is
'���Ų���Ȩ�����ñ�';

comment on column DP_DEPT_OPT_PRIV.OPT_CODE is
'��������';

comment on column DP_DEPT_OPT_PRIV.DEPT_ID is
'���ű���';

comment on column DP_DEPT_OPT_PRIV.PRIV_FLAG is
'Ȩ��';

/*==============================================================*/
/* Table: DP_DEPT_RS_PRIV                                       */
/*==============================================================*/
create table DP_DEPT_RS_PRIV  (
   DEPT_ID              CHAR(6)                         not null,
   RS_CODE              VARCHAR2(10)                    not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_DP_DEPT_RS_PRIV primary key (DEPT_ID, RS_CODE)
);

comment on table DP_DEPT_RS_PRIV is
'������ԴȨ�ޱ�';

comment on column DP_DEPT_RS_PRIV.DEPT_ID is
'���ű���';

comment on column DP_DEPT_RS_PRIV.RS_CODE is
'��Դ����';

comment on column DP_DEPT_RS_PRIV.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: DP_DEPT_SOC_PRIV                                      */
/*==============================================================*/
create table DP_DEPT_SOC_PRIV  (
   DEPT_ID              CHAR(6)                         not null,
   SOC_NAME             VARCHAR2(20)                    not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_DP_DEPT_SOC_PRIV primary key (DEPT_ID, SOC_NAME)
);

comment on table DP_DEPT_SOC_PRIV is
'��������ԴȨ�ޱ�';

comment on column DP_DEPT_SOC_PRIV.DEPT_ID is
'���ű���';

comment on column DP_DEPT_SOC_PRIV.SOC_NAME is
'����Դ����';

comment on column DP_DEPT_SOC_PRIV.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: DP_DEPT_SQL_PRIV                                      */
/*==============================================================*/
create table DP_DEPT_SQL_PRIV  (
   DEPT_ID              CHAR(6)                         not null,
   SOC_NAME             VARCHAR2(20)                    not null,
   TBL_NAME             VARCHAR2(50)                    not null,
   INS_PRIV_FLAG        INTEGER                        default 0 not null,
   DEL_PRIV_FLAG        INTEGER                        default 0 not null,
   UPD_PRIV_FLAG        INTEGER                        default 0 not null,
   SEL_PRIV_FLAG        INTEGER                        default 0 not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_DP_DEPT_SQL_PRIV primary key (DEPT_ID, SOC_NAME, TBL_NAME)
);

comment on table DP_DEPT_SQL_PRIV is
'����SQL����Ȩ�ޱ�';

comment on column DP_DEPT_SQL_PRIV.DEPT_ID is
'���ű���';

comment on column DP_DEPT_SQL_PRIV.SOC_NAME is
'����Դ����';

comment on column DP_DEPT_SQL_PRIV.TBL_NAME is
'����';

comment on column DP_DEPT_SQL_PRIV.INS_PRIV_FLAG is
'INSERTȨ��';

comment on column DP_DEPT_SQL_PRIV.DEL_PRIV_FLAG is
'DELETEȨ��';

comment on column DP_DEPT_SQL_PRIV.UPD_PRIV_FLAG is
'UPDATEȨ��';

comment on column DP_DEPT_SQL_PRIV.SEL_PRIV_FLAG is
'SELECTȨ��';

comment on column DP_DEPT_SQL_PRIV.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: DT_SOURCE                                             */
/*==============================================================*/
create table DT_SOURCE  (
   SOC_NAME             VARCHAR2(20)                    not null,
   SOC_IP               VARCHAR2(20),
   SOC_PORT             INTEGER                        default 0 not null,
   PROTOCOL_TYPE        INTEGER                        default 0 not null,
   REMOTE_UNAME         VARCHAR2(20),
   REMOTE_PASSWD        VARCHAR2(32),
   KEY_REMOTE_PASSWD    VARCHAR2(32)                    not null,
   BK_TIMEOUT           INTEGER                        default 0 not null,
   JDBC_DRV             VARCHAR2(50),
   JDBC_URL             VARCHAR2(100),
   JDBC_SCHEMA          VARCHAR2(50),
   CVT_TYPE             INTEGER                        default 0 not null,
   FTP_LOCAL_SCRIPT     VARCHAR2(50),
   CVT_LOCAL_SCRIPT     VARCHAR2(50),
   SOC_DOMAIN           VARCHAR2(20),
   SOC_BK_DESC          VARCHAR2(100),
   SOC_PARAMS           VARCHAR2(500),
   USER_ROOT_PATH       VARCHAR2(200),
   BACKUP_FLD           VARCHAR2(50),
   RCD_STATE            INTEGER                        default 0 not null,
   ENVIRONMENT_VARIABLES VARCHAR2(500),
   ENCODING_TYPE        VARCHAR2(20),
   constraint PK_DT_SOURCE primary key (SOC_NAME)
);

comment on table DT_SOURCE is
'����Դ��Ϣ��';

comment on column DT_SOURCE.SOC_NAME is
'����Դ����';

comment on column DT_SOURCE.SOC_IP is
'IP��ַ';

comment on column DT_SOURCE.SOC_PORT is
'�˿ں�';

comment on column DT_SOURCE.PROTOCOL_TYPE is
'Э������';

comment on column DT_SOURCE.REMOTE_UNAME is
'��������½�û���';

comment on column DT_SOURCE.REMOTE_PASSWD is
'��������½����';

comment on column DT_SOURCE.KEY_REMOTE_PASSWD is
'��Կ';

comment on column DT_SOURCE.BK_TIMEOUT is
'��ʱʱ��';

comment on column DT_SOURCE.JDBC_DRV is
'Jdbc_driver';

comment on column DT_SOURCE.JDBC_URL is
'Jdbc_url';

comment on column DT_SOURCE.JDBC_SCHEMA is
'Jdbc_schema';

comment on column DT_SOURCE.CVT_TYPE is
'ת�뷽ʽ';

comment on column DT_SOURCE.FTP_LOCAL_SCRIPT is
'���´��ű�';

comment on column DT_SOURCE.CVT_LOCAL_SCRIPT is
'ת��ű�';

comment on column DT_SOURCE.SOC_DOMAIN is
'����Դ����';

comment on column DT_SOURCE.SOC_BK_DESC is
'����Դ����';

comment on column DT_SOURCE.SOC_PARAMS is
'����Դ����';

comment on column DT_SOURCE.USER_ROOT_PATH is
'�û���·��';

comment on column DT_SOURCE.BACKUP_FLD is
'�����ֶ�';

comment on column DT_SOURCE.RCD_STATE is
'��¼״̬';

comment on column DT_SOURCE.ENVIRONMENT_VARIABLES is
'��������';

comment on column DT_SOURCE.ENCODING_TYPE is
'�����ʽ';

/*==============================================================*/
/* Table: LG_LOG_DOWN                                           */
/*==============================================================*/
create table LG_LOG_DOWN  (
   LOG_ROOT_PATH        VARCHAR2(200)                   not null,
   LOG_FILE_NAME        VARCHAR2(50)                    not null,
   START_BK_DATE        DATE,
   END_BK_DATE          DATE,
   USER_ID              VARCHAR2(20),
   CRT_BK_DATE          DATE,
   CRT_BK_TIME          DATE,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_LG_LOG_DOWN primary key (LOG_ROOT_PATH, LOG_FILE_NAME)
);

comment on table LG_LOG_DOWN is
'����������Ϣ��';

comment on column LG_LOG_DOWN.LOG_ROOT_PATH is
'��־�ļ�·��';

comment on column LG_LOG_DOWN.LOG_FILE_NAME is
'��־�ļ�����';

comment on column LG_LOG_DOWN.START_BK_DATE is
'��־��ʼ����';

comment on column LG_LOG_DOWN.END_BK_DATE is
'��־��ֹ����';

comment on column LG_LOG_DOWN.USER_ID is
'�û���';

comment on column LG_LOG_DOWN.CRT_BK_DATE is
'������־����';

comment on column LG_LOG_DOWN.CRT_BK_TIME is
'������־ʱ��';

comment on column LG_LOG_DOWN.BACKUP_FLD is
'����';

/*==============================================================*/
/* Table: LG_LOG_LABEL                                          */
/*==============================================================*/
create table LG_LOG_LABEL  (
   WORK_SEQ             VARCHAR2(17)                    not null,
   USER_ID              VARCHAR2(20)                    not null,
   LOG_LABEL            INTEGER                        default 0 not null,
   LABEL_BK_DATE        DATE,
   LABEL_BK_TIME        DATE,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_LG_LOG_LABEL primary key (WORK_SEQ, USER_ID)
);

comment on table LG_LOG_LABEL is
'��־��Ǽ����';

comment on column LG_LOG_LABEL.WORK_SEQ is
'������ˮ��';

comment on column LG_LOG_LABEL.USER_ID is
'�û���';

comment on column LG_LOG_LABEL.LOG_LABEL is
'��־���';

comment on column LG_LOG_LABEL.LABEL_BK_DATE is
'�������';

comment on column LG_LOG_LABEL.LABEL_BK_TIME is
'���ʱ��';

comment on column LG_LOG_LABEL.BACKUP_FLD is
'����';

/*==============================================================*/
/* Table: LG_LOG_MF                                             */
/*==============================================================*/
create table LG_LOG_MF  (
   WORK_SEQ             VARCHAR2(17)                    not null,
   ORG_CHANNEL_CODE     CHAR(2),
   ORG_TERM_NO          VARCHAR2(40),
   ORG_WORK_CODE        VARCHAR2(10),
   ORG_SRV_NAME         VARCHAR2(50),
   ORG_SRV_BK_DESC      VARCHAR2(100),
   ORG_RS_CODE          VARCHAR2(10),
   ORG_ARY_SOCNAME      VARCHAR2(100),
   PEND_WORK_SEQ        VARCHAR2(17),
   PEND_WORK_CODE       VARCHAR2(10),
   PEND_SRV_NAME        VARCHAR2(50),
   PEND_RS_CODE         VARCHAR2(10),
   PEND_ARY_SOCNAME     VARCHAR2(100),
   PENDWK_BK_EXPL       VARCHAR2(500),
   PBL_CODE             VARCHAR2(20),
   SR_YN_FLAG           INTEGER                        default 0 not null,
   CRT_USER_ID          VARCHAR2(20),
   CRT_USER_CN_NAME     VARCHAR2(50),
   CRT_DEPT_ID          CHAR(6),
   CRT_DEPT_CN_NAME     VARCHAR2(50),
   CRT_BK_DATE          DATE,
   CRT_BK_TIME          DATE,
   LOG_TXT              VARCHAR2(1000),
   LOG_LEVEL            INTEGER                        default 0 not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_LG_LOG_MF primary key (WORK_SEQ)
);

comment on table LG_LOG_MF is
'������־��ˮ��';

comment on column LG_LOG_MF.WORK_SEQ is
'������ˮ��';

comment on column LG_LOG_MF.ORG_CHANNEL_CODE is
'��������';

comment on column LG_LOG_MF.ORG_TERM_NO is
'�����ն�';

comment on column LG_LOG_MF.ORG_WORK_CODE is
'�����������';

comment on column LG_LOG_MF.ORG_SRV_NAME is
'���������';

comment on column LG_LOG_MF.ORG_SRV_BK_DESC is
'�����������';

comment on column LG_LOG_MF.ORG_RS_CODE is
'������Դ����';

comment on column LG_LOG_MF.ORG_ARY_SOCNAME is
'��������Դ����';

comment on column LG_LOG_MF.PEND_WORK_SEQ is
'��������ˮ��';

comment on column LG_LOG_MF.PEND_WORK_CODE is
'�������������';

comment on column LG_LOG_MF.PEND_SRV_NAME is
'�������������';

comment on column LG_LOG_MF.PEND_RS_CODE is
'��������Դ����';

comment on column LG_LOG_MF.PEND_ARY_SOCNAME is
'����������Դ����';

comment on column LG_LOG_MF.PENDWK_BK_EXPL is
'����������˵��';

comment on column LG_LOG_MF.PBL_CODE is
'���ⵥ����';

comment on column LG_LOG_MF.SR_YN_FLAG is
'�ɹ���׼';

comment on column LG_LOG_MF.CRT_USER_ID is
'�����û�';

comment on column LG_LOG_MF.CRT_USER_CN_NAME is
'�û�������';

comment on column LG_LOG_MF.CRT_DEPT_ID is
'��������';

comment on column LG_LOG_MF.CRT_DEPT_CN_NAME is
'����������';

comment on column LG_LOG_MF.CRT_BK_DATE is
'��������';

comment on column LG_LOG_MF.CRT_BK_TIME is
'����ʱ��';

comment on column LG_LOG_MF.LOG_TXT is
'��־����';

comment on column LG_LOG_MF.LOG_LEVEL is
'��־����';

comment on column LG_LOG_MF.BACKUP_FLD is
'����';

/*==============================================================*/
/* Index: LG_LOG_MF_I1                                          */
/*==============================================================*/
create index LG_LOG_MF_I1 on LG_LOG_MF (
   LOG_LEVEL ASC
);

/*==============================================================*/
/* Index: LG_LOG_MF_I2                                          */
/*==============================================================*/
create index LG_LOG_MF_I2 on LG_LOG_MF (
   CRT_BK_DATE ASC
);

/*==============================================================*/
/* Index: LG_LOG_MF_I3                                          */
/*==============================================================*/
create index LG_LOG_MF_I3 on LG_LOG_MF (
   CRT_USER_ID ASC
);

/*==============================================================*/
/* Table: MG_MSG                                                */
/*==============================================================*/
create table MG_MSG  (
   WORK_SEQ             VARCHAR2(17)                    not null,
   MSG_TITLE            VARCHAR2(200),
   MSG_TEXT             VARCHAR2(1000),
   MSG_TYPE             INTEGER                        default 0 not null,
   FILE_PATH            VARCHAR2(200),
   FIRST_BK_FNAME       VARCHAR2(50),
   SECD_BK_FNAME        VARCHAR2(50),
   THIRD_BK_FNAME       VARCHAR2(50),
   BACKUP_FLD           VARCHAR2(50),
   CRT_USER_ID          VARCHAR2(20),
   CRT_BK_DATE          DATE,
   CRT_BK_TIME          DATE,
   DEL_YN_FLAG          INTEGER                        default 0 not null,
   RCD_STATE            INTEGER                        default 0 not null,
   constraint PK_MG_MSG primary key (WORK_SEQ)
);

comment on table MG_MSG is
'��Ϣ��Ϣ��';

comment on column MG_MSG.WORK_SEQ is
'��Ϣ��ˮ��';

comment on column MG_MSG.MSG_TITLE is
'��Ϣ����';

comment on column MG_MSG.MSG_TEXT is
'��Ϣ����';

comment on column MG_MSG.MSG_TYPE is
'��Ϣ����';

comment on column MG_MSG.FILE_PATH is
'�ļ�·��';

comment on column MG_MSG.FIRST_BK_FNAME is
'�ļ���1';

comment on column MG_MSG.SECD_BK_FNAME is
'�ļ���2';

comment on column MG_MSG.THIRD_BK_FNAME is
'�ļ���3';

comment on column MG_MSG.BACKUP_FLD is
'�����ֶ�';

comment on column MG_MSG.CRT_USER_ID is
'�����û�';

comment on column MG_MSG.CRT_BK_DATE is
'��������';

comment on column MG_MSG.CRT_BK_TIME is
'����ʱ��';

comment on column MG_MSG.DEL_YN_FLAG is
'ɾ��״̬';

comment on column MG_MSG.RCD_STATE is
'��¼״̬';

/*==============================================================*/
/* Table: MG_MSG_USER                                           */
/*==============================================================*/
create table MG_MSG_USER  (
   WORK_SEQ             VARCHAR2(17)                    not null,
   RC_USER_ID           VARCHAR2(20)                    not null,
   RC_FLAG              INTEGER                        default 0 not null,
   ATTENT_YN_FLAG       INTEGER                        default 0 not null,
   RC_BK_DATE           DATE,
   RC_BK_TIME           DATE,
   RCD_STATE            INTEGER                        default 0 not null,
   constraint PK_MG_MSG_USER primary key (WORK_SEQ, RC_USER_ID)
);

comment on table MG_MSG_USER is
'��Ϣ�û����ձ�';

comment on column MG_MSG_USER.WORK_SEQ is
'��Ϣ��ˮ��';

comment on column MG_MSG_USER.RC_USER_ID is
'�����û�';

comment on column MG_MSG_USER.RC_FLAG is
'����״̬';

comment on column MG_MSG_USER.ATTENT_YN_FLAG is
'��ע״̬';

comment on column MG_MSG_USER.RC_BK_DATE is
'��������';

comment on column MG_MSG_USER.RC_BK_TIME is
'����ʱ��';

comment on column MG_MSG_USER.RCD_STATE is
'��¼״̬';

/*==============================================================*/
/* Table: RS_OPT                                                */
/*==============================================================*/
create table RS_OPT  (
   OPT_CODE             VARCHAR2(12)                    not null,
   BL_RS_CODE           VARCHAR2(10),
   OPT_BK_SEQ           INTEGER                        default 0 not null,
   OPT_NAME             VARCHAR2(50),
   OPT_BK_EXPL          VARCHAR2(500),
   DIS_EXPRESS          VARCHAR2(200),
   CRT_USER_ID          VARCHAR2(20),
   CRT_BK_DATE          DATE,
   CRT_BK_TIME          DATE,
   BACKUP_FLD           VARCHAR2(50),
   RCD_STATE            INTEGER                        default 0 not null,
   constraint PK_RS_OPT primary key (OPT_CODE)
);

comment on table RS_OPT is
'��Դ���������';

comment on column RS_OPT.OPT_CODE is
'��������';

comment on column RS_OPT.BL_RS_CODE is
'������Դ����';

comment on column RS_OPT.OPT_BK_SEQ is
'�������';

comment on column RS_OPT.OPT_NAME is
'������';

comment on column RS_OPT.OPT_BK_EXPL is
'����˵��';

comment on column RS_OPT.DIS_EXPRESS is
'���ñ��ʽ';

comment on column RS_OPT.CRT_USER_ID is
'�����û�';

comment on column RS_OPT.CRT_BK_DATE is
'��������';

comment on column RS_OPT.CRT_BK_TIME is
'����ʱ��';

comment on column RS_OPT.BACKUP_FLD is
'�����ֶ�';

comment on column RS_OPT.RCD_STATE is
'��¼״̬';

/*==============================================================*/
/* Table: RS_RES                                                */
/*==============================================================*/
create table RS_RES  (
   RS_CODE              VARCHAR2(10)                    not null,
   SUPER_RS_CODE        VARCHAR2(10),
   BL_RS_CODE           VARCHAR2(10),
   RS_FUN_TYPE          INTEGER                        default 0 not null,
   RS_CN_NAME           VARCHAR2(50),
   RS_BK_DESC           VARCHAR2(100),
   RS_URL               VARCHAR2(200),
   RSIM_URL             VARCHAR2(200),
   RS_LEVEL             INTEGER                        default 0 not null,
   GHEIGHT_BK_PIXEL     VARCHAR2(6),
   PWIDTH_BK_PIXEL      VARCHAR2(6),
   TITLE_ABLE           INTEGER                        default 0 not null,
   PUBLIC_YN_FLAG       INTEGER                        default 0 not null,
   OPEN_TYPE            INTEGER                        default 0 not null,
   SORT_KEY             INTEGER                        default 0 not null,
   RCD_STATE            INTEGER                        default 0 not null,
   constraint PK_RS_RES primary key (RS_CODE)
);

comment on table RS_RES is
'��Դ���ñ�';

comment on column RS_RES.RS_CODE is
'��Դ����';

comment on column RS_RES.SUPER_RS_CODE is
'�ϼ���Դ����';

comment on column RS_RES.BL_RS_CODE is
'����һ����Դ����';

comment on column RS_RES.RS_FUN_TYPE is
'��Դ����';

comment on column RS_RES.RS_CN_NAME is
'��Դ����';

comment on column RS_RES.RS_BK_DESC is
'��Դ����';

comment on column RS_RES.RS_URL is
'��Դ��ַ';

comment on column RS_RES.RSIM_URL is
'��Դͼ���ַ';

comment on column RS_RES.RS_LEVEL is
'��Դ����';

comment on column RS_RES.GHEIGHT_BK_PIXEL is
'�߶�';

comment on column RS_RES.PWIDTH_BK_PIXEL is
'���';

comment on column RS_RES.TITLE_ABLE is
'�Ƿ��б���';

comment on column RS_RES.PUBLIC_YN_FLAG is
'�Ƿ񹫿�';

comment on column RS_RES.OPEN_TYPE is
'��������';

comment on column RS_RES.SORT_KEY is
'�����';

comment on column RS_RES.RCD_STATE is
'��¼״̬';

/*==============================================================*/
/* Table: RT_SVC_EXE                                            */
/*==============================================================*/
create table RT_SVC_EXE  (
   WORK_SEQ             VARCHAR2(17)                    not null,
   SOC_NAME             VARCHAR2(20),
   START_BK_DATE        DATE,
   START_BK_TIME        DATE,
   END_BK_DATE          DATE,
   END_BK_TIME          DATE,
   CUR_PROC_STATE       INTEGER                        default 0 not null,
   PROC_NAME            VARCHAR2(20),
   PROC_NUM             VARCHAR2(10),
   BL_USER_ID           VARCHAR2(20),
   RUN_CMD_STR          VARCHAR2(500),
   ORG_USER_ID          VARCHAR2(20),
   RS_BK_TEXT           VARCHAR2(1000),
   ONE_BACKUP_FLD       VARCHAR2(50),
   TWO_BACKUP_FLD       VARCHAR2(50),
   THREE_BACKUP_FLD     VARCHAR2(50),
   constraint PK_RT_SVC_EXE primary key (WORK_SEQ)
);

comment on table RT_SVC_EXE is
'Զ�̷���ִ����Ϣ��';

comment on column RT_SVC_EXE.WORK_SEQ is
'������ˮ��';

comment on column RT_SVC_EXE.SOC_NAME is
'����Դ����';

comment on column RT_SVC_EXE.START_BK_DATE is
'��ʼ����';

comment on column RT_SVC_EXE.START_BK_TIME is
'��ʼʱ��';

comment on column RT_SVC_EXE.END_BK_DATE is
'��������';

comment on column RT_SVC_EXE.END_BK_TIME is
'����ʱ��';

comment on column RT_SVC_EXE.CUR_PROC_STATE is
'��ǰ״̬';

comment on column RT_SVC_EXE.PROC_NAME is
'��������';

comment on column RT_SVC_EXE.PROC_NUM is
'���̺�';

comment on column RT_SVC_EXE.BL_USER_ID is
'���������û�';

comment on column RT_SVC_EXE.RUN_CMD_STR is
'ִ�г���';

comment on column RT_SVC_EXE.ORG_USER_ID is
'�ύ�û�';

comment on column RT_SVC_EXE.RS_BK_TEXT is
'���н��';

comment on column RT_SVC_EXE.ONE_BACKUP_FLD is
'�����ֶ�1';

comment on column RT_SVC_EXE.TWO_BACKUP_FLD is
'�����ֶ�2';

comment on column RT_SVC_EXE.THREE_BACKUP_FLD is
'�����ֶ�3';

/*==============================================================*/
/* Table: SV_REMOTE_SRV                                         */
/*==============================================================*/
create table SV_REMOTE_SRV  (
   SRV_NAME             VARCHAR2(50)                    not null,
   DEAL_SEQ             INTEGER                        default 0 not null,
   REMOTE_SRV_NAME      VARCHAR2(50),
   SRV_TYPE             VARCHAR2(10),
   SRV_ROOT_PATH        VARCHAR2(200),
   constraint PK_SV_REMOTE_SRV primary key (SRV_NAME, DEAL_SEQ)
);

comment on table SV_REMOTE_SRV is
'Զ�̷���������ñ�';

comment on column SV_REMOTE_SRV.SRV_NAME is
'��������';

comment on column SV_REMOTE_SRV.DEAL_SEQ is
'�������';

comment on column SV_REMOTE_SRV.REMOTE_SRV_NAME is
'Ŀ����������';

comment on column SV_REMOTE_SRV.SRV_TYPE is
'Ŀ��������������';

comment on column SV_REMOTE_SRV.SRV_ROOT_PATH is
'����·��';

/*==============================================================*/
/* Table: SV_SRV                                                */
/*==============================================================*/
create table SV_SRV  (
   SRV_NAME             VARCHAR2(50)                    not null,
   SUP_SRVG_CODE        CHAR(2),
   SRV_BK_DESC          VARCHAR2(100),
   SRV_FUN_TYPE         INTEGER                        default 0 not null,
   SRV_CLASS_NAME       VARCHAR2(100),
   SRV_METHOD_NAME      VARCHAR2(50),
   CHECK_FLAG           INTEGER                        default 0 not null,
   AUTH_FLAG            INTEGER                        default 0 not null,
   SOC_FLAG             INTEGER                        default 0 not null,
   SALLOW_FLAG          INTEGER                        default 0 not null,
   LOG_LEVEL            INTEGER                        default 0 not null,
   RCD_STATE            INTEGER                        default 0 not null,
   APROV_TYPE           INTEGER                        default 0 not null,
   CUSTOM_RS_CODE       VARCHAR2(10),
   constraint PK_SV_SRV primary key (SRV_NAME)
);

comment on table SV_SRV is
'�������ñ�';

comment on column SV_SRV.SRV_NAME is
'��������';

comment on column SV_SRV.SUP_SRVG_CODE is
'�������������';

comment on column SV_SRV.SRV_BK_DESC is
'��������';

comment on column SV_SRV.SRV_FUN_TYPE is
'��������';

comment on column SV_SRV.SRV_CLASS_NAME is
'��������';

comment on column SV_SRV.SRV_METHOD_NAME is
'���񷽷���';

comment on column SV_SRV.CHECK_FLAG is
'�Ƿ񸴺�';

comment on column SV_SRV.AUTH_FLAG is
'�Ƿ���Ȩ';

comment on column SV_SRV.SOC_FLAG is
'�Ƿ�������Դ';

comment on column SV_SRV.SALLOW_FLAG is
'���������־';

comment on column SV_SRV.LOG_LEVEL is
'��־����';

comment on column SV_SRV.RCD_STATE is
'��¼״̬';

comment on column SV_SRV.APROV_TYPE is
'����չʾ����';

comment on column SV_SRV.CUSTOM_RS_CODE is
'����ҳ����Դ����';

/*==============================================================*/
/* Table: SV_SRVG                                               */
/*==============================================================*/
create table SV_SRVG  (
   SRVG_CODE            CHAR(2)                         not null,
   SRVG_CN_NAME         VARCHAR2(50),
   SRVG_BK_DESC         VARCHAR2(100),
   SRVG_FUN_TYPE        INTEGER                        default 0 not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_SV_SRVG primary key (SRVG_CODE)
);

comment on table SV_SRVG is
'�����鶨���';

comment on column SV_SRVG.SRVG_CODE is
'���������';

comment on column SV_SRVG.SRVG_CN_NAME is
'����������';

comment on column SV_SRVG.SRVG_BK_DESC is
'����������';

comment on column SV_SRVG.SRVG_FUN_TYPE is
'����������';

comment on column SV_SRVG.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: SV_SRV_AUTH                                           */
/*==============================================================*/
create table SV_SRV_AUTH  (
   AUTH_DEPT_ID         CHAR(6)                         not null,
   SRV_NAME             VARCHAR2(50)                    not null,
   AUTH_SEQ             INTEGER                        default 0 not null,
   AUTH_TYPE            INTEGER                        default 0 not null,
   AUTH_APROV_CATEGORY  INTEGER                        default 0 not null,
   AUTH_DPRL_CODE       CHAR(8),
   ROLE_CN_NAME         VARCHAR2(50),
   SUPER_FLAG           INTEGER                        default 0 not null,
   constraint PK_SV_SRV_AUTH primary key (AUTH_DEPT_ID, SRV_NAME, AUTH_SEQ)
);

comment on table SV_SRV_AUTH is
'������Ȩ�����';

comment on column SV_SRV_AUTH.AUTH_DEPT_ID is
'���ò��ű���';

comment on column SV_SRV_AUTH.SRV_NAME is
'��������';

comment on column SV_SRV_AUTH.AUTH_SEQ is
'��Ȩ���';

comment on column SV_SRV_AUTH.AUTH_TYPE is
'��Ȩ����';

comment on column SV_SRV_AUTH.AUTH_APROV_CATEGORY is
'�������';

comment on column SV_SRV_AUTH.AUTH_DPRL_CODE is
'��Ȩ���Ž�ɫ';

comment on column SV_SRV_AUTH.ROLE_CN_NAME is
'��ɫ����';

comment on column SV_SRV_AUTH.SUPER_FLAG is
'�Ƿ��ϼ�����';

/*==============================================================*/
/* Table: SV_SRV_CHECK                                          */
/*==============================================================*/
create table SV_SRV_CHECK  (
   CHECK_DEPT_ID        CHAR(6)                         not null,
   SRV_NAME             VARCHAR2(50)                    not null,
   CHECK_SEQ            INTEGER                        default 0 not null,
   CHK_APROV_CATEGORY   INTEGER                        default 0 not null,
   CHK_DPRL_CODE        CHAR(8),
   ROLE_CN_NAME         VARCHAR2(50),
   SUPER_FLAG           INTEGER                        default 0 not null,
   constraint PK_SV_SRV_CHECK primary key (CHECK_DEPT_ID, SRV_NAME, CHECK_SEQ)
);

comment on table SV_SRV_CHECK is
'���񸴺˶����';

comment on column SV_SRV_CHECK.CHECK_DEPT_ID is
'���ò��ű���';

comment on column SV_SRV_CHECK.SRV_NAME is
'��������';

comment on column SV_SRV_CHECK.CHECK_SEQ is
'�������';

comment on column SV_SRV_CHECK.CHK_APROV_CATEGORY is
'�������';

comment on column SV_SRV_CHECK.CHK_DPRL_CODE is
'���˲��Ž�ɫ';

comment on column SV_SRV_CHECK.ROLE_CN_NAME is
'��ɫ����';

comment on column SV_SRV_CHECK.SUPER_FLAG is
'�Ƿ��ϼ�����';

/*==============================================================*/
/* Table: SV_SRV_SOC                                            */
/*==============================================================*/
create table SV_SRV_SOC  (
   SRV_NAME             VARCHAR2(50)                    not null,
   SOC_NAME             VARCHAR2(20)                    not null,
   SOC_SEQ              INTEGER                        default 0 not null,
   constraint PK_SV_SRV_SOC primary key (SRV_NAME, SOC_NAME)
);

comment on table SV_SRV_SOC is
'��������Դ���ñ�';

comment on column SV_SRV_SOC.SRV_NAME is
'��������';

comment on column SV_SRV_SOC.SOC_NAME is
'����Դ����';

comment on column SV_SRV_SOC.SOC_SEQ is
'����Դ˳���';

/*==============================================================*/
/* Table: TM_TERM                                               */
/*==============================================================*/
create table TM_TERM  (
   TERM_NO              VARCHAR2(40)                    not null,
   TERM_TYPE            INTEGER                        default 0 not null,
   TERM_BK_DESC         VARCHAR2(100),
   CRT_USER_ID          VARCHAR2(20),
   CRT_DEPT_ID          CHAR(6),
   CRT_BK_DATE          DATE,
   CRT_BK_TIME          DATE,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_TM_TERM primary key (TERM_NO)
);

comment on table TM_TERM is
'�ն����ñ�';

comment on column TM_TERM.TERM_NO is
'�ն˺�';

comment on column TM_TERM.TERM_TYPE is
'�ն�����';

comment on column TM_TERM.TERM_BK_DESC is
'�ն�˵��';

comment on column TM_TERM.CRT_USER_ID is
'�����û�';

comment on column TM_TERM.CRT_DEPT_ID is
'��������';

comment on column TM_TERM.CRT_BK_DATE is
'��������';

comment on column TM_TERM.CRT_BK_TIME is
'����ʱ��';

comment on column TM_TERM.BACKUP_FLD is
'����';

/*==============================================================*/
/* Table: US_DEPT_ROLE                                          */
/*==============================================================*/
create table US_DEPT_ROLE  (
   DPRL_CODE            CHAR(8)                         not null,
   DEPT_ID              CHAR(6),
   ROLE_CODE            CHAR(2),
   BK_EXPL              VARCHAR2(500),
   constraint PK_US_DEPT_ROLE primary key (DPRL_CODE)
);

comment on table US_DEPT_ROLE is
'���Ž�ɫ������';

comment on column US_DEPT_ROLE.DPRL_CODE is
'���Ž�ɫ����';

comment on column US_DEPT_ROLE.DEPT_ID is
'���ű���';

comment on column US_DEPT_ROLE.ROLE_CODE is
'��ɫ����';

comment on column US_DEPT_ROLE.BK_EXPL is
'���Ž�ɫ˵��';

/*==============================================================*/
/* Table: US_ROLE                                               */
/*==============================================================*/
create table US_ROLE  (
   ROLE_CODE            CHAR(2)                         not null,
   ROLE_CN_NAME         VARCHAR2(50),
   ROLE_TYPE            INTEGER                        default 0 not null,
   ROLE_BK_DESC         VARCHAR2(100),
   BACKUP_FLD           VARCHAR2(50),
   CRT_BK_DATE          DATE,
   CRT_BK_TIME          DATE,
   CRT_USER_ID          VARCHAR2(20),
   MODIFY_BK_DATE       DATE,
   MODIFY_BK_TIME       DATE,
   MODIFY_USER_ID       VARCHAR2(20),
   DEL_BK_DATE          DATE,
   DEL_BK_TIME          DATE,
   DEL_USER_ID          VARCHAR2(20),
   RCD_STATE            INTEGER                        default 0 not null,
   constraint PK_US_ROLE primary key (ROLE_CODE)
);

comment on table US_ROLE is
'��ɫ�ű�';

comment on column US_ROLE.ROLE_CODE is
'��ɫ����';

comment on column US_ROLE.ROLE_CN_NAME is
'��ɫ����';

comment on column US_ROLE.ROLE_TYPE is
'��ɫ����';

comment on column US_ROLE.ROLE_BK_DESC is
'��ɫ˵��';

comment on column US_ROLE.BACKUP_FLD is
'�����ֶ�';

comment on column US_ROLE.CRT_BK_DATE is
'��������';

comment on column US_ROLE.CRT_BK_TIME is
'����ʱ��';

comment on column US_ROLE.CRT_USER_ID is
'�����û�';

comment on column US_ROLE.MODIFY_BK_DATE is
'�޸�����';

comment on column US_ROLE.MODIFY_BK_TIME is
'�޸�ʱ��';

comment on column US_ROLE.MODIFY_USER_ID is
'�޸��û�';

comment on column US_ROLE.DEL_BK_DATE is
'ɾ������';

comment on column US_ROLE.DEL_BK_TIME is
'ɾ��ʱ��';

comment on column US_ROLE.DEL_USER_ID is
'ɾ���û�';

comment on column US_ROLE.RCD_STATE is
'��¼״̬';

/*==============================================================*/
/* Table: US_ROLE_COL_PRIV                                      */
/*==============================================================*/
create table US_ROLE_COL_PRIV  (
   DPRL_CODE            CHAR(8)                         not null,
   SOC_NAME             VARCHAR2(20)                    not null,
   TBL_NAME             VARCHAR2(50)                    not null,
   COL_NAME             VARCHAR2(50)                    not null,
   INS_PRIV_FLAG        INTEGER                        default 0 not null,
   DEL_PRIV_FLAG        INTEGER                        default 0 not null,
   UPD_PRIV_FLAG        INTEGER                        default 0 not null,
   SEL_PRIV_FLAG        INTEGER                        default 0 not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_US_ROLE_COL_PRIV primary key (DPRL_CODE, SOC_NAME, TBL_NAME, COL_NAME)
);

comment on table US_ROLE_COL_PRIV is
'���Ž�ɫSQL�ֶβ���Ȩ�ޱ�';

comment on column US_ROLE_COL_PRIV.DPRL_CODE is
'���Ž�ɫ����';

comment on column US_ROLE_COL_PRIV.SOC_NAME is
'����Դ����';

comment on column US_ROLE_COL_PRIV.TBL_NAME is
'����';

comment on column US_ROLE_COL_PRIV.COL_NAME is
'�ֶ�����';

comment on column US_ROLE_COL_PRIV.INS_PRIV_FLAG is
'InsertȨ��';

comment on column US_ROLE_COL_PRIV.DEL_PRIV_FLAG is
'DeleteȨ��';

comment on column US_ROLE_COL_PRIV.UPD_PRIV_FLAG is
'UpdateȨ��';

comment on column US_ROLE_COL_PRIV.SEL_PRIV_FLAG is
'SelectȨ��';

comment on column US_ROLE_COL_PRIV.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: US_ROLE_OPT_PRIV                                      */
/*==============================================================*/
create table US_ROLE_OPT_PRIV  (
   OPT_CODE             VARCHAR2(12)                    not null,
   DPRL_CODE            CHAR(8)                         not null,
   PRIV_FLAG            INTEGER                        default 0 not null,
   constraint PK_US_ROLE_OPT_PRIV primary key (OPT_CODE, DPRL_CODE)
);

comment on table US_ROLE_OPT_PRIV is
'���Ž�ɫ����Ȩ�����ñ�';

comment on column US_ROLE_OPT_PRIV.OPT_CODE is
'��������';

comment on column US_ROLE_OPT_PRIV.DPRL_CODE is
'���Ž�ɫ����';

comment on column US_ROLE_OPT_PRIV.PRIV_FLAG is
'Ȩ��';

/*==============================================================*/
/* Table: US_ROLE_RS_PRIV                                       */
/*==============================================================*/
create table US_ROLE_RS_PRIV  (
   DPRL_CODE            CHAR(8)                         not null,
   RS_CODE              VARCHAR2(10)                    not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_US_ROLE_RS_PRIV primary key (DPRL_CODE, RS_CODE)
);

comment on table US_ROLE_RS_PRIV is
'���Ž�ɫ��ԴȨ�ޱ�';

comment on column US_ROLE_RS_PRIV.DPRL_CODE is
'���Ž�ɫ����';

comment on column US_ROLE_RS_PRIV.RS_CODE is
'��Դ����';

comment on column US_ROLE_RS_PRIV.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: US_ROLE_SOC_PRIV                                      */
/*==============================================================*/
create table US_ROLE_SOC_PRIV  (
   DPRL_CODE            CHAR(8)                         not null,
   SOC_NAME             VARCHAR2(20)                    not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_US_ROLE_SOC_PRIV primary key (DPRL_CODE, SOC_NAME)
);

comment on table US_ROLE_SOC_PRIV is
'���Ž�ɫ����ԴȨ�ޱ�';

comment on column US_ROLE_SOC_PRIV.DPRL_CODE is
'���Ž�ɫ����';

comment on column US_ROLE_SOC_PRIV.SOC_NAME is
'����Դ����';

comment on column US_ROLE_SOC_PRIV.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: US_ROLE_SQL_PRIV                                      */
/*==============================================================*/
create table US_ROLE_SQL_PRIV  (
   DPRL_CODE            CHAR(8)                         not null,
   SOC_NAME             VARCHAR2(20)                    not null,
   TBL_NAME             VARCHAR2(50)                    not null,
   INS_PRIV_FLAG        INTEGER                        default 0 not null,
   DEL_PRIV_FLAG        INTEGER                        default 0 not null,
   UPD_PRIV_FLAG        INTEGER                        default 0 not null,
   SEL_PRIV_FLAG        INTEGER                        default 0 not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_US_ROLE_SQL_PRIV primary key (DPRL_CODE, SOC_NAME, TBL_NAME)
);

comment on table US_ROLE_SQL_PRIV is
'���Ž�ɫSQL����Ȩ�ޱ�';

comment on column US_ROLE_SQL_PRIV.DPRL_CODE is
'���Ž�ɫ����';

comment on column US_ROLE_SQL_PRIV.SOC_NAME is
'����Դ����';

comment on column US_ROLE_SQL_PRIV.TBL_NAME is
'����';

comment on column US_ROLE_SQL_PRIV.INS_PRIV_FLAG is
'InsertȨ��';

comment on column US_ROLE_SQL_PRIV.DEL_PRIV_FLAG is
'DeleteȨ��';

comment on column US_ROLE_SQL_PRIV.UPD_PRIV_FLAG is
'UpdateȨ��';

comment on column US_ROLE_SQL_PRIV.SEL_PRIV_FLAG is
'SelectȨ��';

comment on column US_ROLE_SQL_PRIV.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: US_USER                                               */
/*==============================================================*/
create table US_USER  (
   USER_ID              VARCHAR2(20)                    not null,
   USER_PASSWD          VARCHAR2(32),
   PWDEXP_BK_DATE       DATE,
   USER_CN_NAME         VARCHAR2(50),
   EMAIL_ADD            VARCHAR2(50),
   PHONE_NO             VARCHAR2(12),
   TELLER_NO            VARCHAR2(6),
   LOGIN_BK_NUM         INTEGER                        default 0 not null,
   BL_DEPT_ID           CHAR(6),
   USER_TYPE            INTEGER                        default 0 not null,
   FIRST_DEPT_ID        CHAR(6),
   SECD_DEPT_ID         CHAR(6),
   THIRD_DEPT_ID        CHAR(6),
   BACKUP_FLD           VARCHAR2(50),
   CRT_BK_DATE          DATE,
   CRT_BK_TIME          DATE,
   CRT_USER_ID          VARCHAR2(20),
   MODIFY_BK_DATE       DATE,
   MODIFY_BK_TIME       DATE,
   MODIFY_USER_ID       VARCHAR2(20),
   DEL_BK_DATE          DATE,
   DEL_BK_TIME          DATE,
   DEL_USER_ID          VARCHAR2(20),
   RCD_STATE            INTEGER                        default 0 not null,
   constraint PK_US_USER primary key (USER_ID)
);

comment on table US_USER is
'�û���';

comment on column US_USER.USER_ID is
'�û���';

comment on column US_USER.USER_PASSWD is
'�û�����';

comment on column US_USER.PWDEXP_BK_DATE is
'���뵽����';

comment on column US_USER.USER_CN_NAME is
'�û�����';

comment on column US_USER.EMAIL_ADD is
'����';

comment on column US_USER.PHONE_NO is
'�绰����';

comment on column US_USER.TELLER_NO is
'��Ա��';

comment on column US_USER.LOGIN_BK_NUM is
'�û���¼����';

comment on column US_USER.BL_DEPT_ID is
'�������ź�';

comment on column US_USER.USER_TYPE is
'�û�����';

comment on column US_USER.FIRST_DEPT_ID is
'��ְ����1';

comment on column US_USER.SECD_DEPT_ID is
'��ְ����2';

comment on column US_USER.THIRD_DEPT_ID is
'��ְ����3';

comment on column US_USER.BACKUP_FLD is
'�����ֶ�';

comment on column US_USER.CRT_BK_DATE is
'��������';

comment on column US_USER.CRT_BK_TIME is
'����ʱ��';

comment on column US_USER.CRT_USER_ID is
'�����û�';

comment on column US_USER.MODIFY_BK_DATE is
'�޸�����';

comment on column US_USER.MODIFY_BK_TIME is
'�޸�ʱ��';

comment on column US_USER.MODIFY_USER_ID is
'�޸��û�';

comment on column US_USER.DEL_BK_DATE is
'ɾ������';

comment on column US_USER.DEL_BK_TIME is
'ɾ��ʱ��';

comment on column US_USER.DEL_USER_ID is
'ɾ���û�';

comment on column US_USER.RCD_STATE is
'��¼״̬';

/*==============================================================*/
/* Table: US_USER_COL_PRIV                                      */
/*==============================================================*/
create table US_USER_COL_PRIV  (
   USER_ID              VARCHAR2(20)                    not null,
   SOC_NAME             VARCHAR2(20)                    not null,
   TBL_NAME             VARCHAR2(50)                    not null,
   COL_NAME             VARCHAR2(50)                    not null,
   PRIV_TYPE            INTEGER                        default 0 not null,
   INS_PRIV_FLAG        INTEGER                        default 0 not null,
   DEL_PRIV_FLAG        INTEGER                        default 0 not null,
   UPD_PRIV_FLAG        INTEGER                        default 0 not null,
   SEL_PRIV_FLAG        INTEGER                        default 0 not null,
   AF_FLAG              INTEGER                        default 0 not null,
   TEMPSTART_BK_DATETIME TIMESTAMP,
   TEMPEND_BK_DATETIME  TIMESTAMP,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_US_USER_COL_PRIV primary key (USER_ID, SOC_NAME, TBL_NAME, COL_NAME, PRIV_TYPE)
);

comment on table US_USER_COL_PRIV is
'�û�SQL�ֶβ���Ȩ�ޱ�';

comment on column US_USER_COL_PRIV.USER_ID is
'�û���';

comment on column US_USER_COL_PRIV.SOC_NAME is
'����Դ����';

comment on column US_USER_COL_PRIV.TBL_NAME is
'����';

comment on column US_USER_COL_PRIV.COL_NAME is
'�ֶ�����';

comment on column US_USER_COL_PRIV.PRIV_TYPE is
'��Դ����';

comment on column US_USER_COL_PRIV.INS_PRIV_FLAG is
'InsertȨ��';

comment on column US_USER_COL_PRIV.DEL_PRIV_FLAG is
'DeleteȨ��';

comment on column US_USER_COL_PRIV.UPD_PRIV_FLAG is
'UpdateȨ��';

comment on column US_USER_COL_PRIV.SEL_PRIV_FLAG is
'SelectȨ��';

comment on column US_USER_COL_PRIV.AF_FLAG is
'�����ֹ��־λ';

comment on column US_USER_COL_PRIV.TEMPSTART_BK_DATETIME is
'��ʱȨ�޿�ʼʱ��';

comment on column US_USER_COL_PRIV.TEMPEND_BK_DATETIME is
'��ʱȨ�޽���ʱ��';

comment on column US_USER_COL_PRIV.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: US_USER_CONTACT                                       */
/*==============================================================*/
create table US_USER_CONTACT  (
   USER_ID              VARCHAR2(20)                    not null,
   CONTACT_USER_ID      VARCHAR2(20)                    not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_US_USER_CONTACT primary key (USER_ID, CONTACT_USER_ID)
);

comment on table US_USER_CONTACT is
'�û�������ϵ�˹�����';

comment on column US_USER_CONTACT.USER_ID is
'�û�ID';

comment on column US_USER_CONTACT.CONTACT_USER_ID is
'�û�������ϵ��';

comment on column US_USER_CONTACT.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: US_USER_OPT_PRIV                                      */
/*==============================================================*/
create table US_USER_OPT_PRIV  (
   OPT_CODE             VARCHAR2(12)                    not null,
   USER_ID              VARCHAR2(20)                    not null,
   PRIV_FLAG            INTEGER                        default 0 not null,
   constraint PK_US_USER_OPT_PRIV primary key (OPT_CODE, USER_ID)
);

comment on table US_USER_OPT_PRIV is
'�û�����Ȩ�����ñ�';

comment on column US_USER_OPT_PRIV.OPT_CODE is
'��������';

comment on column US_USER_OPT_PRIV.USER_ID is
'�û���';

comment on column US_USER_OPT_PRIV.PRIV_FLAG is
'Ȩ��';

/*==============================================================*/
/* Table: US_USER_ROLE                                          */
/*==============================================================*/
create table US_USER_ROLE  (
   USER_ID              VARCHAR2(20)                    not null,
   DPRL_CODE            CHAR(8)                         not null,
   USER_BK_WEIGHT       INTEGER                        default 0 not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_US_USER_ROLE primary key (USER_ID, DPRL_CODE)
);

comment on table US_USER_ROLE is
'�û���ɫ������';

comment on column US_USER_ROLE.USER_ID is
'�û���';

comment on column US_USER_ROLE.DPRL_CODE is
'���Ž�ɫ����';

comment on column US_USER_ROLE.USER_BK_WEIGHT is
'�û�Ȩ��';

comment on column US_USER_ROLE.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: US_USER_RS_PRIV                                       */
/*==============================================================*/
create table US_USER_RS_PRIV  (
   USER_ID              VARCHAR2(20)                    not null,
   RS_CODE              VARCHAR2(10)                    not null,
   PRIV_TYPE            INTEGER                        default 0 not null,
   AF_FLAG              INTEGER                        default 0 not null,
   TEMPSTART_BK_DATETIME TIMESTAMP,
   TEMPEND_BK_DATETIME  TIMESTAMP,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_US_USER_RS_PRIV primary key (USER_ID, RS_CODE, PRIV_TYPE)
);

comment on table US_USER_RS_PRIV is
'�û���ԴȨ�ޱ�';

comment on column US_USER_RS_PRIV.USER_ID is
'�û���';

comment on column US_USER_RS_PRIV.RS_CODE is
'��Դ����';

comment on column US_USER_RS_PRIV.PRIV_TYPE is
'Ȩ������';

comment on column US_USER_RS_PRIV.AF_FLAG is
'�����ֹ��־';

comment on column US_USER_RS_PRIV.TEMPSTART_BK_DATETIME is
'��ʱȨ�޿�ʼʱ��';

comment on column US_USER_RS_PRIV.TEMPEND_BK_DATETIME is
'��ʱȨ�޽���ʱ��';

comment on column US_USER_RS_PRIV.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: US_USER_SOC_PRIV                                      */
/*==============================================================*/
create table US_USER_SOC_PRIV  (
   USER_ID              VARCHAR2(20)                    not null,
   SOC_NAME             VARCHAR2(20)                    not null,
   PRIV_TYPE            INTEGER                        default 0 not null,
   AF_FLAG              INTEGER                        default 0 not null,
   TEMPSTART_BK_DATETIME TIMESTAMP,
   TEMPEND_BK_DATETIME  TIMESTAMP,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_US_USER_SOC_PRIV primary key (USER_ID, SOC_NAME, PRIV_TYPE)
);

comment on table US_USER_SOC_PRIV is
'�û�����ԴȨ�ޱ�';

comment on column US_USER_SOC_PRIV.USER_ID is
'�û���';

comment on column US_USER_SOC_PRIV.SOC_NAME is
'����Դ����';

comment on column US_USER_SOC_PRIV.PRIV_TYPE is
'��Դ����';

comment on column US_USER_SOC_PRIV.AF_FLAG is
'�����ֹ��־';

comment on column US_USER_SOC_PRIV.TEMPSTART_BK_DATETIME is
'��ʱȨ�޿�ʼʱ��';

comment on column US_USER_SOC_PRIV.TEMPEND_BK_DATETIME is
'��ʱȨ�޽���ʱ��';

comment on column US_USER_SOC_PRIV.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: US_USER_SQL_PRIV                                      */
/*==============================================================*/
create table US_USER_SQL_PRIV  (
   USER_ID              VARCHAR2(20)                    not null,
   SOC_NAME             VARCHAR2(20)                    not null,
   TBL_NAME             VARCHAR2(50)                    not null,
   PRIV_TYPE            INTEGER                        default 0 not null,
   INS_PRIV_FLAG        INTEGER                        default 0 not null,
   DEL_PRIV_FLAG        INTEGER                        default 0 not null,
   UPD_PRIV_FLAG        INTEGER                        default 0 not null,
   SEL_PRIV_FLAG        INTEGER                        default 0 not null,
   AF_FLAG              INTEGER                        default 0 not null,
   TEMPSTART_BK_DATETIME TIMESTAMP,
   TEMPEND_BK_DATETIME  TIMESTAMP,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_US_USER_SQL_PRIV primary key (USER_ID, SOC_NAME, TBL_NAME, PRIV_TYPE)
);

comment on table US_USER_SQL_PRIV is
'�û�SQL����Ȩ�ޱ�';

comment on column US_USER_SQL_PRIV.USER_ID is
'�û���';

comment on column US_USER_SQL_PRIV.SOC_NAME is
'����Դ����';

comment on column US_USER_SQL_PRIV.TBL_NAME is
'����';

comment on column US_USER_SQL_PRIV.PRIV_TYPE is
'��Դ����';

comment on column US_USER_SQL_PRIV.INS_PRIV_FLAG is
'InsertȨ��';

comment on column US_USER_SQL_PRIV.DEL_PRIV_FLAG is
'DELETEȨ��';

comment on column US_USER_SQL_PRIV.UPD_PRIV_FLAG is
'UpdateȨ��';

comment on column US_USER_SQL_PRIV.SEL_PRIV_FLAG is
'SelectȨ��';

comment on column US_USER_SQL_PRIV.AF_FLAG is
'�����ֹ��־';

comment on column US_USER_SQL_PRIV.TEMPSTART_BK_DATETIME is
'��ʱȨ�޿�ʼʱ��';

comment on column US_USER_SQL_PRIV.TEMPEND_BK_DATETIME is
'��ʱȨ�޽���ʱ��';

comment on column US_USER_SQL_PRIV.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: US_USER_TERM                                          */
/*==============================================================*/
create table US_USER_TERM  (
   USER_ID              VARCHAR2(20)                    not null,
   TERM_NO              VARCHAR2(40)                    not null,
   CHANNEL_CODE         CHAR(2),
   DEPT_ID              CHAR(6),
   USER_CN_NAME         VARCHAR2(50),
   DEPT_CN_NAME         VARCHAR2(50),
   USE_STATE            INTEGER                        default 0 not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_US_USER_TERM primary key (USER_ID, TERM_NO)
);

comment on table US_USER_TERM is
'�û������ն����ñ�';

comment on column US_USER_TERM.USER_ID is
'�û���';

comment on column US_USER_TERM.TERM_NO is
'�ն˺�';

comment on column US_USER_TERM.CHANNEL_CODE is
'��������';

comment on column US_USER_TERM.DEPT_ID is
'���ű���';

comment on column US_USER_TERM.USER_CN_NAME is
'�û�����';

comment on column US_USER_TERM.DEPT_CN_NAME is
'��������';

comment on column US_USER_TERM.USE_STATE is
'����״̬';

comment on column US_USER_TERM.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: WK_DEAL_DETAIL                                        */
/*==============================================================*/
create table WK_DEAL_DETAIL  (
   PEND_WORK_SEQ        VARCHAR2(17)                    not null,
   DEAL_SEQ             INTEGER                        default 0 not null,
   DEAL_TYPE            INTEGER                        default 0 not null,
   DEAL_RES             INTEGER                        default 0 not null,
   DEAL_USER_ID         VARCHAR2(20),
   DEAL_BK_DATE         DATE,
   DEAL_BK_TIME         DATE,
   DEAL_BK_DESC         VARCHAR2(100),
   constraint PK_WK_DEAL_DETAIL primary key (PEND_WORK_SEQ, DEAL_SEQ)
);

comment on table WK_DEAL_DETAIL is
'��������ϸ';

comment on column WK_DEAL_DETAIL.PEND_WORK_SEQ is
'������������ˮ��';

comment on column WK_DEAL_DETAIL.DEAL_SEQ is
'�������';

comment on column WK_DEAL_DETAIL.DEAL_TYPE is
'����ʽ';

comment on column WK_DEAL_DETAIL.DEAL_RES is
'������';

comment on column WK_DEAL_DETAIL.DEAL_USER_ID is
'������Ա';

comment on column WK_DEAL_DETAIL.DEAL_BK_DATE is
'��������';

comment on column WK_DEAL_DETAIL.DEAL_BK_TIME is
'����ʱ��';

comment on column WK_DEAL_DETAIL.DEAL_BK_DESC is
'����˵��';

/*==============================================================*/
/* Table: WK_DEAL_STATE                                         */
/*==============================================================*/
create table WK_DEAL_STATE  (
   PEND_WORK_SEQ        VARCHAR2(17)                    not null,
   SUBMIT_WORK_SEQ      VARCHAR2(17),
   PEND_WORK_CODE       VARCHAR2(10),
   PEND_SRV_NAME        VARCHAR2(50),
   PEND_RS_CODE         VARCHAR2(10),
   PEND_ARY_SOCNAME     VARCHAR2(100),
   PENDWK_BK_EXPL       VARCHAR2(500),
   PEND_DEAL_SEQ        INTEGER                        default 0 not null,
   PEND_USER_ID         VARCHAR2(20),
   PEND_USER_CN_NAME    VARCHAR2(50),
   PBL_CODE             VARCHAR2(20),
   PROXY_USER_ID        VARCHAR2(20),
   CRT_USER_ID          VARCHAR2(20)                    not null,
   CRT_USER_CN_NAME     VARCHAR2(50),
   CRT_DEPT_ID          CHAR(6)                         not null,
   CRT_DEPT_CN_NAME     VARCHAR2(50),
   CRT_BK_DATE          DATE,
   CRT_BK_TIME          DATE,
   WORKFLOW_STATE       INTEGER                        default 0 not null,
   BACKUP_FLD           VARCHAR2(50),
   RCD_STATE            INTEGER                        default 0 not null,
   APPLY_BK_EXPL        VARCHAR2(500),
   constraint PK_WK_DEAL_STATE primary key (PEND_WORK_SEQ)
);

comment on table WK_DEAL_STATE is
'������״̬��';

comment on column WK_DEAL_STATE.PEND_WORK_SEQ is
'������������ˮ��';

comment on column WK_DEAL_STATE.SUBMIT_WORK_SEQ is
'�ύ������ˮ��';

comment on column WK_DEAL_STATE.PEND_WORK_CODE is
'�������������';

comment on column WK_DEAL_STATE.PEND_SRV_NAME is
'�������������';

comment on column WK_DEAL_STATE.PEND_RS_CODE is
'��������Դ����';

comment on column WK_DEAL_STATE.PEND_ARY_SOCNAME is
'����������Դ����';

comment on column WK_DEAL_STATE.PENDWK_BK_EXPL is
'����������˵��';

comment on column WK_DEAL_STATE.PEND_DEAL_SEQ is
'���������';

comment on column WK_DEAL_STATE.PEND_USER_ID is
'�������û�';

comment on column WK_DEAL_STATE.PEND_USER_CN_NAME is
'�������û�������';

comment on column WK_DEAL_STATE.PBL_CODE is
'���ⵥ����';

comment on column WK_DEAL_STATE.PROXY_USER_ID is
'�����û�';

comment on column WK_DEAL_STATE.CRT_USER_ID is
'�����û�';

comment on column WK_DEAL_STATE.CRT_USER_CN_NAME is
'�û�������';

comment on column WK_DEAL_STATE.CRT_DEPT_ID is
'��������';

comment on column WK_DEAL_STATE.CRT_DEPT_CN_NAME is
'����������';

comment on column WK_DEAL_STATE.CRT_BK_DATE is
'��������';

comment on column WK_DEAL_STATE.CRT_BK_TIME is
'����ʱ��';

comment on column WK_DEAL_STATE.WORKFLOW_STATE is
'����״̬';

comment on column WK_DEAL_STATE.BACKUP_FLD is
'����';

comment on column WK_DEAL_STATE.RCD_STATE is
'��¼״̬';

comment on column WK_DEAL_STATE.APPLY_BK_EXPL is
'��������˵��';

/*==============================================================*/
/* Index: WK_DEAL_STATE_I1                                      */
/*==============================================================*/
create index WK_DEAL_STATE_I1 on WK_DEAL_STATE (
   CRT_USER_ID ASC,
   PROXY_USER_ID ASC,
   PEND_SRV_NAME ASC,
   PEND_DEAL_SEQ ASC
);

/*==============================================================*/
/* Index: WK_DEAL_STATE_I2                                      */
/*==============================================================*/
create index WK_DEAL_STATE_I2 on WK_DEAL_STATE (
   PEND_SRV_NAME ASC
);

/*==============================================================*/
/* Index: WK_DEAL_STATE_I3                                      */
/*==============================================================*/
create index WK_DEAL_STATE_I3 on WK_DEAL_STATE (
   PEND_USER_ID ASC,
   WORKFLOW_STATE ASC
);

/*==============================================================*/
/* Table: WK_DETAIL_REGISTER                                    */
/*==============================================================*/
create table WK_DETAIL_REGISTER  (
   PEND_WORK_SEQ        VARCHAR2(17)                    not null,
   INTE_DETAIL          BLOB,
   APROV_TYPE           INTEGER                        default 0 not null,
   CUSTOM_RS_CODE       VARCHAR2(10),
   APPLY_HTML           BLOB,
   constraint PK_WK_DETAIL_REGISTER primary key (PEND_WORK_SEQ)
);

comment on table WK_DETAIL_REGISTER is
'������������ϸ�ǼǱ�';

comment on column WK_DETAIL_REGISTER.PEND_WORK_SEQ is
'������������ˮ��';

comment on column WK_DETAIL_REGISTER.INTE_DETAIL is
'�ӿ���ϸ��Ϣ';

comment on column WK_DETAIL_REGISTER.APROV_TYPE is
'����չʾ����';

comment on column WK_DETAIL_REGISTER.CUSTOM_RS_CODE is
'����ҳ����Դ����';

comment on column WK_DETAIL_REGISTER.APPLY_HTML is
'����ҳ�����';

/*==============================================================*/
/* Table: WK_WORK_PROCESS                                       */
/*==============================================================*/
create table WK_WORK_PROCESS  (
   PEND_WORK_SEQ        VARCHAR2(17)                    not null,
   DEAL_SEQ             INTEGER                        default 0 not null,
   PEND_USER_ID         VARCHAR2(20),
   PEND_USER_CN_NAME    VARCHAR2(50),
   PEND_TYPE            INTEGER                        default 0 not null,
   constraint PK_WK_WORK_PROCESS primary key (PEND_WORK_SEQ, DEAL_SEQ)
);

comment on table WK_WORK_PROCESS is
'�����������̱�';

comment on column WK_WORK_PROCESS.PEND_WORK_SEQ is
'������������ˮ��';

comment on column WK_WORK_PROCESS.DEAL_SEQ is
'�������';

comment on column WK_WORK_PROCESS.PEND_USER_ID is
'������';

comment on column WK_WORK_PROCESS.PEND_USER_CN_NAME is
'������������';

comment on column WK_WORK_PROCESS.PEND_TYPE is
'������ʽ';
