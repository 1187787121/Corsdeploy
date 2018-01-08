drop view VI_SYS_ENV_SERVER;

drop table BUILD_CONFIGFILE cascade constraints;

drop table BUILD_SCRIPT cascade constraints;

drop table BUILD_STEP cascade constraints;

drop table CE_ENVIRONMENT cascade constraints;

drop table CE_ENVIRONMENT_SERVER cascade constraints;

drop table CE_PROJECT cascade constraints;

drop table CE_SERVER cascade constraints;

drop table CE_SERVER_DS cascade constraints;

drop table CE_SYSTEM cascade constraints;

drop table CE_SYSTEM_CFG cascade constraints;

drop table CE_SYSTEM_TEMPLATE cascade constraints;

drop table DP_DEPT_ENV_PRIV cascade constraints;

drop table ENV_BUILD_TASK cascade constraints;

drop table ENV_CONFIGFILE_MOD cascade constraints;

drop table ENV_TAG_STORAGE cascade constraints;

drop table ENV_TASK cascade constraints;

drop table INSTANCE_EXE cascade constraints;

drop table MO_COMPONENT cascade constraints;

drop table MO_MODULE_QUOTE cascade constraints;

drop table MO_TEMPLATE cascade constraints;

drop table PG_INTE_STEP cascade constraints;

drop table PG_PROGRAM cascade constraints;

drop table PG_RELE cascade constraints;

drop table PG_RELE_STEP cascade constraints;

drop table US_ROLE_ENV_PRIV cascade constraints;

drop table US_USER_ENV_PRIV cascade constraints;

drop table UU_FILELIST cascade constraints;

drop table UU_PARAM cascade constraints;

drop table UU_SOC cascade constraints;

/*==============================================================*/
/* Table: BUILD_CONFIGFILE                                      */
/*==============================================================*/
create table BUILD_CONFIGFILE  (
   FILE_WORK_SEQ        VARCHAR2(17)                    not null,
   WORK_ID              VARCHAR2(14),
   CFG_TYPE             INTEGER                        default 0,
   SERVER_NAME          VARCHAR2(20),
   SERVER_IP            VARCHAR2(20),
   FOPT_TYPE            INTEGER                        default 0,
   FILE_BK_PATH         VARCHAR2(200),
   FILE_BK_FNAME        VARCHAR2(50),
   FILE_BK_CSUM         VARCHAR2(20),
   DIR_YN_FLAG          INTEGER                        default 0,
   OPT_STATUS           INTEGER                        default 0,
   MODIFY_USER_ID       VARCHAR2(20),
   MODIFY_BK_DATE       DATE,
   MODIFY_BK_TIME       DATE,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_BUILD_CONFIGFILE primary key (FILE_WORK_SEQ)
);

comment on table BUILD_CONFIGFILE is
'�������������ļ������';

comment on column BUILD_CONFIGFILE.FILE_WORK_SEQ is
'�ļ������ˮ��';

comment on column BUILD_CONFIGFILE.WORK_ID is
'������';

comment on column BUILD_CONFIGFILE.CFG_TYPE is
'��������';

comment on column BUILD_CONFIGFILE.SERVER_NAME is
'��������';

comment on column BUILD_CONFIGFILE.SERVER_IP is
'������IP';

comment on column BUILD_CONFIGFILE.FOPT_TYPE is
'��������';

comment on column BUILD_CONFIGFILE.FILE_BK_PATH is
'�ļ�·��';

comment on column BUILD_CONFIGFILE.FILE_BK_FNAME is
'�ļ���';

comment on column BUILD_CONFIGFILE.FILE_BK_CSUM is
'�ļ�CSUM';

comment on column BUILD_CONFIGFILE.DIR_YN_FLAG is
'�Ƿ�Ŀ¼��־';

comment on column BUILD_CONFIGFILE.OPT_STATUS is
'����״̬';

comment on column BUILD_CONFIGFILE.MODIFY_USER_ID is
'�޸���';

comment on column BUILD_CONFIGFILE.MODIFY_BK_DATE is
'�޸�����';

comment on column BUILD_CONFIGFILE.MODIFY_BK_TIME is
'�޸�ʱ��';

comment on column BUILD_CONFIGFILE.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: BUILD_SCRIPT                                          */
/*==============================================================*/
create table BUILD_SCRIPT  (
   WORK_ID              VARCHAR2(14)                    not null,
   SCRIPT_TYPE          INTEGER                        default 0 not null,
   SCIRPT_BK_SEQ        INTEGER                        default 0 not null,
   SCRIPT_METHOD        INTEGER                        default 0,
   MODULE_ID            VARCHAR2(20),
   MODULE_CN_NAME       VARCHAR2(50),
   SOC_UUID             CHAR(32),
   MODULE_PARAM_UUID    CHAR(32),
   INSTANCE_ID          VARCHAR2(50),
   SCRIPT_TEXT          VARCHAR2(1000),
   EXE_STATUS           INTEGER                        default 0,
   EXE_RESULT           INTEGER                        default 0,
   EXELOG_BK_PATH       VARCHAR2(200),
   EXE_USER_ID          VARCHAR2(20),
   START_BK_TM          TIMESTAMP,
   END_BK_TM            TIMESTAMP,
   TIME_USED            INTEGER                        default 0,
   IMPL_TYPE            INTEGER                        default 0,
   constraint PK_BUILD_SCRIPT primary key (WORK_ID, SCRIPT_TYPE, SCIRPT_BK_SEQ)
);

comment on table BUILD_SCRIPT is
'�����ű���Ϣ��';

comment on column BUILD_SCRIPT.WORK_ID is
'������';

comment on column BUILD_SCRIPT.SCRIPT_TYPE is
'�ű�����';

comment on column BUILD_SCRIPT.SCIRPT_BK_SEQ is
'�ű����';

comment on column BUILD_SCRIPT.SCRIPT_METHOD is
'�ű���ʽ';

comment on column BUILD_SCRIPT.MODULE_ID is
'���ID';

comment on column BUILD_SCRIPT.MODULE_CN_NAME is
'���������';

comment on column BUILD_SCRIPT.SOC_UUID is
'����ԴUUID';

comment on column BUILD_SCRIPT.MODULE_PARAM_UUID is
'���������UUID';

comment on column BUILD_SCRIPT.INSTANCE_ID is
'ʵ��ID';

comment on column BUILD_SCRIPT.SCRIPT_TEXT is
'�ű�����';

comment on column BUILD_SCRIPT.EXE_STATUS is
'ִ��״̬';

comment on column BUILD_SCRIPT.EXE_RESULT is
'ִ�н��';

comment on column BUILD_SCRIPT.EXELOG_BK_PATH is
'ִ����־';

comment on column BUILD_SCRIPT.EXE_USER_ID is
'ִ����';

comment on column BUILD_SCRIPT.START_BK_TM is
'ִ�п�ʼʱ��';

comment on column BUILD_SCRIPT.END_BK_TM is
'ִ�н���ʱ��';

comment on column BUILD_SCRIPT.TIME_USED is
'��ʱ';

comment on column BUILD_SCRIPT.IMPL_TYPE is
'���ִ������';

/*==============================================================*/
/* Table: BUILD_STEP                                            */
/*==============================================================*/
create table BUILD_STEP  (
   WORK_ID              VARCHAR2(14)                    not null,
   TEMPLATE_NAME        VARCHAR2(50)                    not null,
   PHASE_ID             INTEGER                        default 0 not null,
   PHASE_BK_DESC        VARCHAR2(100),
   SOC_UUID             CHAR(32),
   GEN_YN_FLAG          INTEGER                        default 0,
   IMPL_TYPE            INTEGER                        default 0,
   constraint PK_BUILD_STEP primary key (WORK_ID, TEMPLATE_NAME, PHASE_ID)
);

comment on table BUILD_STEP is
'�����׶α�';

comment on column BUILD_STEP.WORK_ID is
'������';

comment on column BUILD_STEP.TEMPLATE_NAME is
'ģ����';

comment on column BUILD_STEP.PHASE_ID is
'�׶α��';

comment on column BUILD_STEP.PHASE_BK_DESC is
'�׶�����';

comment on column BUILD_STEP.SOC_UUID is
'����ԴUUID';

comment on column BUILD_STEP.GEN_YN_FLAG is
'�Ƿ�����ʵ��';

comment on column BUILD_STEP.IMPL_TYPE is
'���ִ������';

/*==============================================================*/
/* Table: CE_ENVIRONMENT                                        */
/*==============================================================*/
create table CE_ENVIRONMENT  (
   ENV_NAME             VARCHAR2(20)                    not null,
   ENV_CN_NAME          VARCHAR2(50),
   ENV_BK_DESC          VARCHAR2(200),
   ENV_TYPE             INTEGER                        default 0 not null,
   SYS_NAME             VARCHAR2(20),
   ELE_TYPE             VARCHAR2(20),
   DT_RANGE             INTEGER                        default 0,
   CREATE_BK_DATE       DATE,
   CREATE_BK_TIME       DATE,
   CREATE_USER_ID       VARCHAR2(20),
   MODIFY_BK_DATE       DATE,
   MODIFY_BK_TIME       DATE,
   MODIFY_USER_ID       VARCHAR2(20),
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_CE_ENVIRONMENT primary key (ENV_NAME)
);

comment on table CE_ENVIRONMENT is
'������';

comment on column CE_ENVIRONMENT.ENV_NAME is
'��������';

comment on column CE_ENVIRONMENT.ENV_CN_NAME is
'�������';

comment on column CE_ENVIRONMENT.ENV_BK_DESC is
'��������';

comment on column CE_ENVIRONMENT.ENV_TYPE is
'��������';

comment on column CE_ENVIRONMENT.SYS_NAME is
'Ӧ��ϵͳ';

comment on column CE_ENVIRONMENT.ELE_TYPE is
'����Ҫ��';

comment on column CE_ENVIRONMENT.DT_RANGE is
'���ݷ�Χ';

comment on column CE_ENVIRONMENT.CREATE_BK_DATE is
'��������';

comment on column CE_ENVIRONMENT.CREATE_BK_TIME is
'����ʱ��';

comment on column CE_ENVIRONMENT.CREATE_USER_ID is
'�����û�';

comment on column CE_ENVIRONMENT.MODIFY_BK_DATE is
'�޸�����';

comment on column CE_ENVIRONMENT.MODIFY_BK_TIME is
'�޸�ʱ��';

comment on column CE_ENVIRONMENT.MODIFY_USER_ID is
'�޸��û�';

comment on column CE_ENVIRONMENT.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: CE_ENVIRONMENT_SERVER                                 */
/*==============================================================*/
create table CE_ENVIRONMENT_SERVER  (
   ENV_NAME             VARCHAR2(20)                    not null,
   SERVER_TYPE          INTEGER                        default 0 not null,
   SERVER_NAME          VARCHAR2(20)                    not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_CE_ENVIRONMENT_SERVER primary key (ENV_NAME, SERVER_TYPE, SERVER_NAME)
);

comment on table CE_ENVIRONMENT_SERVER is
'������������';

comment on column CE_ENVIRONMENT_SERVER.ENV_NAME is
'��������';

comment on column CE_ENVIRONMENT_SERVER.SERVER_TYPE is
'����������';

comment on column CE_ENVIRONMENT_SERVER.SERVER_NAME is
'����������';

comment on column CE_ENVIRONMENT_SERVER.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: CE_PROJECT                                            */
/*==============================================================*/
create table CE_PROJECT  (
   PROJECT_NAME         VARCHAR2(100)                   not null,
   PROJECT_SHORT_NAME   VARCHAR2(20),
   PROJECT_BK_DESC      VARCHAR2(100),
   SYS_NAME             VARCHAR2(20),
   CREATE_BK_DATE       DATE,
   CREATE_BK_TIME       DATE,
   CREATE_USER_ID       VARCHAR2(20),
   MODIFY_BK_DATE       DATE,
   MODIFY_BK_TIME       DATE,
   MODIFY_USER_ID       VARCHAR2(20),
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_CE_PROJECT primary key (PROJECT_NAME)
);

comment on table CE_PROJECT is
'��Ŀ��';

comment on column CE_PROJECT.PROJECT_NAME is
'��Ŀ���';

comment on column CE_PROJECT.PROJECT_SHORT_NAME is
'��Ŀ���';

comment on column CE_PROJECT.PROJECT_BK_DESC is
'��Ŀ����';

comment on column CE_PROJECT.SYS_NAME is
'Ӧ��ϵͳ';

comment on column CE_PROJECT.CREATE_BK_DATE is
'��������';

comment on column CE_PROJECT.CREATE_BK_TIME is
'����ʱ��';

comment on column CE_PROJECT.CREATE_USER_ID is
'�����û�';

comment on column CE_PROJECT.MODIFY_BK_DATE is
'�޸�����';

comment on column CE_PROJECT.MODIFY_BK_TIME is
'�޸�ʱ��';

comment on column CE_PROJECT.MODIFY_USER_ID is
'�޸��û�';

comment on column CE_PROJECT.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: CE_SERVER                                             */
/*==============================================================*/
create table CE_SERVER  (
   SERVER_NAME          VARCHAR2(20)                    not null,
   SERVER_CN_NAME       VARCHAR2(50),
   SERVER_DESC          VARCHAR2(200),
   SERVER_IP            VARCHAR2(20),
   SERVER_OS            INTEGER                        default 0 not null,
   OS_SBK_VER           VARCHAR2(20),
   SERVER_DB            VARCHAR2(200),
   SERVER_MID_WARE      VARCHAR2(20),
   CREATE_BK_DATE       DATE,
   CREATE_BK_TIME       DATE,
   CREATE_USER_ID       VARCHAR2(20),
   MODIFY_BK_DATE       DATE,
   MODIFY_BK_TIME       DATE,
   MODIFY_USER_ID       VARCHAR2(20),
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_CE_SERVER primary key (SERVER_NAME)
);

comment on table CE_SERVER is
'��������';

comment on column CE_SERVER.SERVER_NAME is
'����������';

comment on column CE_SERVER.SERVER_CN_NAME is
'���������';

comment on column CE_SERVER.SERVER_DESC is
'����������';

comment on column CE_SERVER.SERVER_IP is
'��������ַ';

comment on column CE_SERVER.SERVER_OS is
'����ϵͳ';

comment on column CE_SERVER.OS_SBK_VER is
'����ϵͳ�汾';

comment on column CE_SERVER.SERVER_DB is
'���ݿ�����';

comment on column CE_SERVER.SERVER_MID_WARE is
'�м��';

comment on column CE_SERVER.CREATE_BK_DATE is
'��������';

comment on column CE_SERVER.CREATE_BK_TIME is
'����ʱ��';

comment on column CE_SERVER.CREATE_USER_ID is
'�����û�';

comment on column CE_SERVER.MODIFY_BK_DATE is
'�޸�����';

comment on column CE_SERVER.MODIFY_BK_TIME is
'�޸�ʱ��';

comment on column CE_SERVER.MODIFY_USER_ID is
'�޸��û�';

comment on column CE_SERVER.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: CE_SERVER_DS                                          */
/*==============================================================*/
create table CE_SERVER_DS  (
   SERVER_NAME          VARCHAR2(20)                    not null,
   SOC_NAME             VARCHAR2(20)                    not null,
   APPLY_TYPE           VARCHAR2(20),
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_CE_SERVER_DS primary key (SERVER_NAME, SOC_NAME)
);

comment on table CE_SERVER_DS is
'����������Դ��';

comment on column CE_SERVER_DS.SERVER_NAME is
'����������';

comment on column CE_SERVER_DS.SOC_NAME is
'����Դ����';

comment on column CE_SERVER_DS.APPLY_TYPE is
'����Դ��;';

comment on column CE_SERVER_DS.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: CE_SYSTEM                                             */
/*==============================================================*/
create table CE_SYSTEM  (
   SYS_NAME             VARCHAR2(20)                        not null,
   SYS_CN_NAME          VARCHAR2(50),
   SYS_TYPE             INTEGER,
   SYS_BK_DESC          VARCHAR2(200),
   CREATE_BK_DATE       DATE,
   CREATE_BK_TIME       DATE,
   CREATE_USER_ID       VARCHAR2(20),
   MODIFY_BK_DATE       DATE,
   MODIFY_BK_TIME       DATE,
   MODIFY_USER_ID       VARCHAR2(20),
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_CE_SYSTEM primary key (SYS_NAME)
);

comment on table CE_SYSTEM is
'Ӧ��ϵͳ��';

comment on column CE_SYSTEM.SYS_NAME is
'Ӧ��ϵͳ����';

comment on column CE_SYSTEM.SYS_CN_NAME is
'Ӧ��ϵͳ���';

comment on column CE_SYSTEM.SYS_TYPE is
'Ӧ��ϵͳ����';

comment on column CE_SYSTEM.SYS_BK_DESC is
'Ӧ��ϵͳ����';

comment on column CE_SYSTEM.CREATE_BK_DATE is
'��������';

comment on column CE_SYSTEM.CREATE_BK_TIME is
'����ʱ��';

comment on column CE_SYSTEM.CREATE_USER_ID is
'�����û�';

comment on column CE_SYSTEM.MODIFY_BK_DATE is
'�޸�����';

comment on column CE_SYSTEM.MODIFY_BK_TIME is
'�޸�ʱ��';

comment on column CE_SYSTEM.MODIFY_USER_ID is
'�޸��û�';

comment on column CE_SYSTEM.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: CE_SYSTEM_CFG                                         */
/*==============================================================*/
create table CE_SYSTEM_CFG  (
   SYS_NAME             VARCHAR2(20)                        not null,
   CFG_BK_FNAME         VARCHAR2(50)                    not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_CE_SYSTEM_CFG primary key (SYS_NAME, CFG_BK_FNAME)
);

comment on table CE_SYSTEM_CFG is
'Ӧ��ϵͳ�����ļ���';

comment on column CE_SYSTEM_CFG.SYS_NAME is
'Ӧ��ϵͳ����';

comment on column CE_SYSTEM_CFG.CFG_BK_FNAME is
'�ļ���';

comment on column CE_SYSTEM_CFG.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: CE_SYSTEM_TEMPLATE                                    */
/*==============================================================*/
create table CE_SYSTEM_TEMPLATE  (
   SYS_NAME             VARCHAR2(20)                        not null,
   TEMPLATE_TYPE        INTEGER                         not null,
   TEMPLATE_NAME        VARCHAR2(50)                    not null,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_CE_SYSTEM_TEMPLATE primary key (SYS_NAME, TEMPLATE_TYPE, TEMPLATE_NAME)
);

comment on table CE_SYSTEM_TEMPLATE is
'Ӧ��ϵͳģ���';

comment on column CE_SYSTEM_TEMPLATE.SYS_NAME is
'Ӧ��ϵͳ����';

comment on column CE_SYSTEM_TEMPLATE.TEMPLATE_TYPE is
'ģ������';

comment on column CE_SYSTEM_TEMPLATE.TEMPLATE_NAME is
'ģ������';

comment on column CE_SYSTEM_TEMPLATE.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: DP_DEPT_ENV_PRIV                                      */
/*==============================================================*/
create table DP_DEPT_ENV_PRIV  (
   DEPT_ID              CHAR(6)                         not null,
   ENV_NAME             VARCHAR2(20)                    not null,
   SYS_NAME             VARCHAR2(20),
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_DP_DEPT_ENV_PRIV primary key (DEPT_ID, ENV_NAME)
);

comment on table DP_DEPT_ENV_PRIV is
'����Ӧ�û���Ȩ�ޱ�';

comment on column DP_DEPT_ENV_PRIV.DEPT_ID is
'���ű���';

comment on column DP_DEPT_ENV_PRIV.ENV_NAME is
'��������';

comment on column DP_DEPT_ENV_PRIV.SYS_NAME is
'Ӧ��ϵͳ';

comment on column DP_DEPT_ENV_PRIV.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: ENV_BUILD_TASK                                        */
/*==============================================================*/
create table ENV_BUILD_TASK  (
   WORK_ID              VARCHAR2(14)                    not null,
   TEMPLATE_NAME        VARCHAR2(50),
   TEMPLATE_PARAM_UUID  CHAR(32),
   VER_SOC_UUID         CHAR(32),
   EXELOG_BK_PATH       VARCHAR2(200),
   BUILD_STEP_ID        INTEGER                        default 0,
   TASK_STATUS          INTEGER                        default 0,
   EXE_RESULT           INTEGER                        default 0,
   EXE_USER_ID          VARCHAR2(20),
   START_BK_TM          TIMESTAMP,
   END_BK_TM            TIMESTAMP,
   constraint PK_ENV_BUILD_TASK primary key (WORK_ID)
);

comment on table ENV_BUILD_TASK is
'����������չ��';

comment on column ENV_BUILD_TASK.WORK_ID is
'������';

comment on column ENV_BUILD_TASK.TEMPLATE_NAME is
'ģ����';

comment on column ENV_BUILD_TASK.TEMPLATE_PARAM_UUID is
'ģ�����UUID';

comment on column ENV_BUILD_TASK.VER_SOC_UUID is
'�汾������ԴUUID';

comment on column ENV_BUILD_TASK.EXELOG_BK_PATH is
'������־';

comment on column ENV_BUILD_TASK.BUILD_STEP_ID is
'����������';

comment on column ENV_BUILD_TASK.TASK_STATUS is
'����״̬';

comment on column ENV_BUILD_TASK.EXE_RESULT is
'ִ�н��';

comment on column ENV_BUILD_TASK.EXE_USER_ID is
'ִ����';

comment on column ENV_BUILD_TASK.START_BK_TM is
'ִ�п�ʼʱ��';

comment on column ENV_BUILD_TASK.END_BK_TM is
'ִ�н���ʱ��';

/*==============================================================*/
/* Table: ENV_CONFIGFILE_MOD                                    */
/*==============================================================*/
create table ENV_CONFIGFILE_MOD  (
   FILE_WORK_SEQ        VARCHAR2(17)                    not null,
   BATCH_NO             VARCHAR2(12),
   ENV_NAME             VARCHAR2(20),
   SERVER_NAME          VARCHAR2(20),
   SERVER_IP            VARCHAR2(20)                    not null,
   FOPT_TYPE            INTEGER                        default 0 not null,
   FILE_BK_PATH         VARCHAR2(200),
   FILE_BK_FNAME        VARCHAR2(50),
   FILE_BK_CSUM         VARCHAR2(20),
   DIR_YN_FLAG          INTEGER                        default 0 not null,
   OPT_STATUS           INTEGER                        default 0 not null,
   MODIFY_USER_ID       VARCHAR2(20),
   MODIFY_BK_DATE       DATE,
   MODIFY_BK_TIME       DATE,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_ENV_CONFIGFILE_MOD primary key (FILE_WORK_SEQ)
);

comment on table ENV_CONFIGFILE_MOD is
'���������ļ������';

comment on column ENV_CONFIGFILE_MOD.FILE_WORK_SEQ is
'�ļ������ˮ��';

comment on column ENV_CONFIGFILE_MOD.BATCH_NO is
'���κ�';

comment on column ENV_CONFIGFILE_MOD.ENV_NAME is
'��������';

comment on column ENV_CONFIGFILE_MOD.SERVER_NAME is
'��������';

comment on column ENV_CONFIGFILE_MOD.SERVER_IP is
'������IP';

comment on column ENV_CONFIGFILE_MOD.FOPT_TYPE is
'��������';

comment on column ENV_CONFIGFILE_MOD.FILE_BK_PATH is
'�ļ�·��';

comment on column ENV_CONFIGFILE_MOD.FILE_BK_FNAME is
'�ļ���';

comment on column ENV_CONFIGFILE_MOD.FILE_BK_CSUM is
'�ļ�CSUM';

comment on column ENV_CONFIGFILE_MOD.DIR_YN_FLAG is
'�Ƿ�Ŀ¼��־';

comment on column ENV_CONFIGFILE_MOD.OPT_STATUS is
'����״̬';

comment on column ENV_CONFIGFILE_MOD.MODIFY_USER_ID is
'�޸���';

comment on column ENV_CONFIGFILE_MOD.MODIFY_BK_DATE is
'�޸�����';

comment on column ENV_CONFIGFILE_MOD.MODIFY_BK_TIME is
'�޸�ʱ��';

comment on column ENV_CONFIGFILE_MOD.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: ENV_TAG_STORAGE                                       */
/*==============================================================*/
create table ENV_TAG_STORAGE  (
   STORAGE_ID           VARCHAR2(14)                    not null,
   STORAGE_BK_DESC      VARCHAR2(200),
   ENV_NAME             VARCHAR2(20),
   PROJECT_NAME         VARCHAR2(100),
   INSTANCE_ID          VARCHAR2(50),
   EXE_SOC_UUID         CHAR(32),
   TAR_VERSOC_UUID      CHAR(32),
   STORAGE_LIST_UUID    CHAR(32),
   INTE_VER_NUM         VARCHAR2(50),
   TAR_VER_NUM          VARCHAR2(50),
   STORAGE_STATUS       INTEGER                        default 0,
   CRT_USER_ID          VARCHAR2(20),
   STORAGE_RESULT       INTEGER                        default 0,
   CRT_BK_DATE          DATE,
   CRT_BK_TIME          DATE,
   STORAGE_USER_ID      VARCHAR2(20),
   START_BK_TM          TIMESTAMP,
   END_BK_TM            TIMESTAMP,
   LOG_BK_PATH          VARCHAR2(200),
   TIME_USED            INTEGER                        default 0,
   constraint PK_ENV_TAG_STORAGE primary key (STORAGE_ID)
);

comment on table ENV_TAG_STORAGE is
'Ŀ������';

comment on column ENV_TAG_STORAGE.STORAGE_ID is
'�����';

comment on column ENV_TAG_STORAGE.STORAGE_BK_DESC is
'�������';

comment on column ENV_TAG_STORAGE.ENV_NAME is
'��������';

comment on column ENV_TAG_STORAGE.PROJECT_NAME is
'��Ŀ���';

comment on column ENV_TAG_STORAGE.INSTANCE_ID is
'ʵ��ID';

comment on column ENV_TAG_STORAGE.EXE_SOC_UUID is
'ִ������ԴUUID';

comment on column ENV_TAG_STORAGE.TAR_VERSOC_UUID is
'Ŀ��汾����ԴUUID';

comment on column ENV_TAG_STORAGE.STORAGE_LIST_UUID is
'����嵥UUID';

comment on column ENV_TAG_STORAGE.INTE_VER_NUM is
'���ɰ汾��';

comment on column ENV_TAG_STORAGE.TAR_VER_NUM is
'Ŀ��汾��';

comment on column ENV_TAG_STORAGE.STORAGE_STATUS is
'���״̬';

comment on column ENV_TAG_STORAGE.CRT_USER_ID is
'������';

comment on column ENV_TAG_STORAGE.STORAGE_RESULT is
'�����';

comment on column ENV_TAG_STORAGE.CRT_BK_DATE is
'��������';

comment on column ENV_TAG_STORAGE.CRT_BK_TIME is
'����ʱ��';

comment on column ENV_TAG_STORAGE.STORAGE_USER_ID is
'�����';

comment on column ENV_TAG_STORAGE.START_BK_TM is
'��⿪ʼʱ��';

comment on column ENV_TAG_STORAGE.END_BK_TM is
'������ʱ��';

comment on column ENV_TAG_STORAGE.LOG_BK_PATH is
'�����־ȫ·��';

comment on column ENV_TAG_STORAGE.TIME_USED is
'��ʱ';

/*==============================================================*/
/* Table: ENV_TASK                                              */
/*==============================================================*/
create table ENV_TASK  (
   WORK_ID              VARCHAR2(14)                    not null,
   TASK_TYPE            INTEGER                        default 0,
   TASK_BK_DESC         VARCHAR2(200),
   ROL_WORK_ID          VARCHAR2(14),
   ENV_NAME             VARCHAR2(20),
   PROJECT_NAME         VARCHAR2(100),
   PROG_ID              VARCHAR2(14),
   INSTANCE_ID          VARCHAR2(50),
   EXE_METHOD           INTEGER                        default 0,
   TAGPAC_LIST_UUID     CHAR(32),
   PUB_LIST_UUID        CHAR(32),
   CODE_VER_NUM         VARCHAR2(50),
   TARGET_VER_NUM       VARCHAR2(50),
   VERCODE_VER_NUM      VARCHAR2(50),
   VERTARGET_VER_NUM    VARCHAR2(50),
   TASK_STATUS          INTEGER                        default 0,
   EXE_RESULT           INTEGER                        default 0,
   EXELOG_BK_PATH       VARCHAR2(200),
   CRT_USER_ID          VARCHAR2(20),
   CRT_BK_DATE          DATE,
   CRT_BK_TIME          DATE,
   EXE_USER_ID          VARCHAR2(20),
   START_BK_TM          TIMESTAMP,
   END_BK_TM            TIMESTAMP,
   TIME_USED            INTEGER                        default 0 not null,
   constraint PK_ENV_TASK primary key (WORK_ID)
);

comment on table ENV_TASK is
'���������';

comment on column ENV_TASK.WORK_ID is
'������';

comment on column ENV_TASK.TASK_TYPE is
'��������';

comment on column ENV_TASK.TASK_BK_DESC is
'��������';

comment on column ENV_TASK.ROL_WORK_ID is
'����������';

comment on column ENV_TASK.ENV_NAME is
'��������';

comment on column ENV_TASK.PROJECT_NAME is
'������Ŀ';

comment on column ENV_TASK.PROG_ID is
'�������';

comment on column ENV_TASK.INSTANCE_ID is
'ʵ��ID';

comment on column ENV_TASK.EXE_METHOD is
'ִ�ж���';

comment on column ENV_TASK.TAGPAC_LIST_UUID is
'����Ŀ����嵥UUID';

comment on column ENV_TASK.PUB_LIST_UUID is
'�����嵥UUID';

comment on column ENV_TASK.CODE_VER_NUM is
'Դ��汾���汾��';

comment on column ENV_TASK.TARGET_VER_NUM is
'Ŀ��汾���汾��';

comment on column ENV_TASK.VERCODE_VER_NUM is
'�汾����Դ��汾��';

comment on column ENV_TASK.VERTARGET_VER_NUM is
'�汾����Ŀ��汾��';

comment on column ENV_TASK.TASK_STATUS is
'����״̬';

comment on column ENV_TASK.EXE_RESULT is
'ִ�н��';

comment on column ENV_TASK.EXELOG_BK_PATH is
'ִ����־';

comment on column ENV_TASK.CRT_USER_ID is
'������';

comment on column ENV_TASK.CRT_BK_DATE is
'��������';

comment on column ENV_TASK.CRT_BK_TIME is
'����ʱ��';

comment on column ENV_TASK.EXE_USER_ID is
'ִ����';

comment on column ENV_TASK.START_BK_TM is
'ִ�п�ʼʱ��';

comment on column ENV_TASK.END_BK_TM is
'ִ�н���ʱ��';

comment on column ENV_TASK.TIME_USED is
'��ʱ';

/*==============================================================*/
/* Table: INSTANCE_EXE                                          */
/*==============================================================*/
create table INSTANCE_EXE  (
   INSTANCE_ID          VARCHAR2(50)                    not null,
   INST_BK_NO           INTEGER                        default 0 not null,
   TPL_BK_NO            INTEGER                        default 0,
   STEP_BK_DESC         VARCHAR2(100),
   SERVER_NAME          VARCHAR2(20),
   SOC_NAME             VARCHAR2(20),
   EXE_STATUS           INTEGER                        default 0,
   EXE_RESULT           INTEGER                        default 0,
   EXEC_TEXT            VARCHAR2(500),
   START_BK_TM          TIMESTAMP,
   END_BK_TM            TIMESTAMP,
   TIME_USED            INTEGER                        default 0,
   constraint PK_INSTANCE_EXE primary key (INSTANCE_ID, INST_BK_NO)
);

comment on table INSTANCE_EXE is
'ʵ��ִ����Ϣ��';

comment on column INSTANCE_EXE.INSTANCE_ID is
'ִ��ʵ��ID';

comment on column INSTANCE_EXE.INST_BK_NO is
'ʵ���׶κ�';

comment on column INSTANCE_EXE.TPL_BK_NO is
'ģ������׶κ�';

comment on column INSTANCE_EXE.STEP_BK_DESC is
'�׶�����';

comment on column INSTANCE_EXE.SERVER_NAME is
'ִ�з�������';

comment on column INSTANCE_EXE.SOC_NAME is
'ִ������Դ��';

comment on column INSTANCE_EXE.EXE_STATUS is
'ִ��״̬';

comment on column INSTANCE_EXE.EXE_RESULT is
'ִ�н��';

comment on column INSTANCE_EXE.EXEC_TEXT is
'ִ����Ϣ';

comment on column INSTANCE_EXE.START_BK_TM is
'ִ�п�ʼʱ��';

comment on column INSTANCE_EXE.END_BK_TM is
'ִ�н���ʱ��';

comment on column INSTANCE_EXE.TIME_USED is
'��ʱ';

/*==============================================================*/
/* Table: MO_COMPONENT                                             */
/*==============================================================*/
create table MO_COMPONENT  (
   COMPONENT_ID         VARCHAR2(20)                    not null,
   COMPONENT_CN_NAME    VARCHAR2(50),
   COMPONENT_BK_DESC    VARCHAR2(500),
   MODULE_TYPE          INTEGER                        default 0,
   COMPONENT_PURPOSES   VARCHAR2(10),
   COMPONENT_SOURCE     INTEGER,
   IMPL_TYPE            INTEGER                        default 0,
   LANGUAGE_VERSION     VARCHAR2(50),
   PUBLISH_STATE        INTEGER                        default 0,
   CRT_USER_ID          VARCHAR2(20),
   CRT_BK_DATE          DATE,
   CRT_BK_TIME          DATE,
   MODIFY_USER_ID       VARCHAR2(20),
   MODIFY_BK_DATE       DATE,
   MODIFY_BK_TIME       DATE,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_MO_COMPONENT primary key (COMPONENT_ID)
);

comment on table MO_COMPONENT is
'�����Ϣ��';

comment on column MO_COMPONENT.COMPONENT_ID is
'���ID';

comment on column MO_COMPONENT.COMPONENT_CN_NAME is
'���������';

comment on column MO_COMPONENT.COMPONENT_BK_DESC is
'�������';

comment on column MO_COMPONENT.MODULE_TYPE is
'�������';

comment on column MO_COMPONENT.COMPONENT_PURPOSES is
'�����;';

comment on column MO_COMPONENT.COMPONENT_SOURCE is
'�ű���Դ';

comment on column MO_COMPONENT.IMPL_TYPE is
'���ʵ������';

comment on column MO_COMPONENT.LANGUAGE_VERSION is
'���԰汾';

comment on column MO_COMPONENT.PUBLISH_STATE is
'����״̬';

comment on column MO_COMPONENT.CRT_USER_ID is
'������';

comment on column MO_COMPONENT.CRT_BK_DATE is
'��������';

comment on column MO_COMPONENT.CRT_BK_TIME is
'����ʱ��';

comment on column MO_COMPONENT.MODIFY_USER_ID is
'�޸���';

comment on column MO_COMPONENT.MODIFY_BK_DATE is
'�޸�����';

comment on column MO_COMPONENT.MODIFY_BK_TIME is
'�޸�ʱ��';

comment on column MO_COMPONENT.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: MO_MODULE_QUOTE                                       */
/*==============================================================*/
create table MO_MODULE_QUOTE  (
   MODULE_ID            VARCHAR2(20)                    not null,
   QUOTE_MODULE_ID      VARCHAR2(20)                    not null,
   QUOTE_MODULE_TYPE    INTEGER                        default 0,
   constraint PK_MO_MODULE_QUOTE primary key (MODULE_ID, QUOTE_MODULE_ID)
);

comment on table MO_MODULE_QUOTE is
'������ñ�';

comment on column MO_MODULE_QUOTE.MODULE_ID is
'���ID';

comment on column MO_MODULE_QUOTE.QUOTE_MODULE_ID is
'�������ID';

comment on column MO_MODULE_QUOTE.QUOTE_MODULE_TYPE is
'�����������';

/*==============================================================*/
/* Table: MO_TEMPLATE                                           */
/*==============================================================*/
create table MO_TEMPLATE  (
   TEMPLATE_NAME        VARCHAR2(50)                    not null,
   TEMPLATE_FORMATE     INTEGER                        default 0 not null,
   TEMPLATE_CN_NAME     VARCHAR2(50),
   TP_CLASS_NAME        VARCHAR2(100),
   SCRIPT_FILE_PATH     VARCHAR2(200),
   REF_MODULE_ID        VARCHAR2(20),
   TEMPLATE_TYPE        INTEGER,
   OPERATING_SYSTEM     VARCHAR2(20),
   PUBLISH_STATE        INTEGER                        default 0 not null,
   TEMPLATE_BK_DESC     VARCHAR2(100),
   CRT_BK_DATE          DATE,
   CRT_BK_TIME          DATE,
   CRT_USER_ID          VARCHAR2(20),
   MODIFY_BK_DATE       DATE,
   MODIFY_BK_TIME       DATE,
   MODIFY_USER_ID       VARCHAR2(20),
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_MO_TEMPLATE primary key (TEMPLATE_NAME)
);

comment on table MO_TEMPLATE is
'Ͷ��ģ���';

comment on column MO_TEMPLATE.TEMPLATE_NAME is
'ģ������';

comment on column MO_TEMPLATE.TEMPLATE_FORMATE is
'ģ���ʽ';

comment on column MO_TEMPLATE.TEMPLATE_CN_NAME is
'ģ��������';

comment on column MO_TEMPLATE.TP_CLASS_NAME is
'ģ������';

comment on column MO_TEMPLATE.SCRIPT_FILE_PATH is
'ģ��·��';

comment on column MO_TEMPLATE.REF_MODULE_ID is
'����ID';

comment on column MO_TEMPLATE.TEMPLATE_TYPE is
'ģ������';

comment on column MO_TEMPLATE.OPERATING_SYSTEM is
'����ϵͳ';

comment on column MO_TEMPLATE.PUBLISH_STATE is
'����״̬';

comment on column MO_TEMPLATE.TEMPLATE_BK_DESC is
'ģ������';

comment on column MO_TEMPLATE.CRT_BK_DATE is
'��������';

comment on column MO_TEMPLATE.CRT_BK_TIME is
'����ʱ��';

comment on column MO_TEMPLATE.CRT_USER_ID is
'�����û�';

comment on column MO_TEMPLATE.MODIFY_BK_DATE is
'�޸�����';

comment on column MO_TEMPLATE.MODIFY_BK_TIME is
'�޸�ʱ��';

comment on column MO_TEMPLATE.MODIFY_USER_ID is
'�޸��û�';

comment on column MO_TEMPLATE.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: PG_INTE_STEP                                          */
/*==============================================================*/
create table PG_INTE_STEP  (
   PROG_ID              VARCHAR2(14)                    not null,
   STEP_ID              INTEGER                        default 0 not null,
   STEP_EXPL            VARCHAR2(40),
   STEP_TYPE            INTEGER                        default 0,
   SOC_UUID             CHAR(32),
   STEP_BK_SCRIPT       VARCHAR2(1000),
   COMPILE_TYPE         INTEGER                        default 0,
   COMPLIE_BK_PATH      VARCHAR2(200),
   ENV_VARIABLE         VARCHAR2(500),
   COMPILE_PARAM        VARCHAR2(200),
   STORAGE_LIST_UUID    CHAR(32),
   STORAGE_BK_PATH      VARCHAR2(200),
   constraint PK_PG_INTE_STEP primary key (PROG_ID, STEP_ID)
);

comment on table PG_INTE_STEP is
'���ɷ��������';

comment on column PG_INTE_STEP.PROG_ID is
'�������';

comment on column PG_INTE_STEP.STEP_ID is
'������';

comment on column PG_INTE_STEP.STEP_EXPL is
'����˵��';

comment on column PG_INTE_STEP.STEP_TYPE is
'��������';

comment on column PG_INTE_STEP.SOC_UUID is
'����ԴUUID';

comment on column PG_INTE_STEP.STEP_BK_SCRIPT is
'�ű�';

comment on column PG_INTE_STEP.COMPILE_TYPE is
'��������';

comment on column PG_INTE_STEP.COMPLIE_BK_PATH is
'����·��';

comment on column PG_INTE_STEP.ENV_VARIABLE is
'��������';

comment on column PG_INTE_STEP.COMPILE_PARAM is
'�������';

comment on column PG_INTE_STEP.STORAGE_LIST_UUID is
'����嵥UUID';

comment on column PG_INTE_STEP.STORAGE_BK_PATH is
'�����·��';

/*==============================================================*/
/* Table: PG_PROGRAM                                            */
/*==============================================================*/
create table PG_PROGRAM  (
   PROG_ID              VARCHAR2(14)                    not null,
   PROG_NAME            VARCHAR2(50),
   ENV_NAME             VARCHAR2(20),
   PROG_BK_DESC         VARCHAR2(100),
   PROG_TYPE            INTEGER                        default 0 not null,
   IS_PUBLISH           INTEGER                        default 0 not null,
   BACKUP_FLD           VARCHAR2(50),
   CRT_USER_ID          VARCHAR2(20),
   CRT_BK_DATE          DATE,
   CRT_BK_TIME          DATE,
   constraint PK_PG_PROGRAM primary key (PROG_ID)
);

comment on table PG_PROGRAM is
'����������';

comment on column PG_PROGRAM.PROG_ID is
'�������';

comment on column PG_PROGRAM.PROG_NAME is
'��������';

comment on column PG_PROGRAM.ENV_NAME is
'��������';

comment on column PG_PROGRAM.PROG_BK_DESC is
'��������';

comment on column PG_PROGRAM.PROG_TYPE is
'��������';

comment on column PG_PROGRAM.IS_PUBLISH is
'�Ƿ񷢲�';

comment on column PG_PROGRAM.BACKUP_FLD is
'�����ֶ�';

comment on column PG_PROGRAM.CRT_USER_ID is
'������';

comment on column PG_PROGRAM.CRT_BK_DATE is
'��������';

comment on column PG_PROGRAM.CRT_BK_TIME is
'����ʱ��';

/*==============================================================*/
/* Table: PG_RELE                                               */
/*==============================================================*/
create table PG_RELE  (
   PROG_ID              VARCHAR2(14)                    not null,
   PUB_TEMPLATE_NAME    VARCHAR2(50),
   PUBL_PARAM_UUID      CHAR(32),
   ROL_TEMPLATE_NAME    VARCHAR2(50),
   ROLL_PARAM_UUID      CHAR(32),
   VER_SOC_UUID         CHAR(32),
   constraint PK_PG_RELE primary key (PROG_ID)
);

comment on table PG_RELE is
'����������չ��';

comment on column PG_RELE.PROG_ID is
'�������';

comment on column PG_RELE.PUB_TEMPLATE_NAME is
'����ģ��';

comment on column PG_RELE.PUBL_PARAM_UUID is
'����ģ�����UUID';

comment on column PG_RELE.ROL_TEMPLATE_NAME is
'����ģ��';

comment on column PG_RELE.ROLL_PARAM_UUID is
'����ģ�����UUID';

comment on column PG_RELE.VER_SOC_UUID is
'�汾������ԴUUID';

/*==============================================================*/
/* Table: PG_RELE_STEP                                          */
/*==============================================================*/
create table PG_RELE_STEP  (
   PROG_ID              VARCHAR2(14)                    not null,
   TEMPLATE_NAME        VARCHAR2(50)                    not null,
   PHASE_ID             INTEGER                        default 0 not null,
   PHASE_BK_DESC        VARCHAR2(100),
   SOC_UUID             CHAR(32)                        not null,
   GEN_YN_FLAG          INTEGER                        default 0 not null,
   IMPL_TYPE            INTEGER                        default 0 not null,
   constraint PK_PG_RELE_STEP primary key (PROG_ID, TEMPLATE_NAME, PHASE_ID)
);

comment on table PG_RELE_STEP is
'���������׶α�';

comment on column PG_RELE_STEP.PROG_ID is
'�������';

comment on column PG_RELE_STEP.TEMPLATE_NAME is
'ģ����';

comment on column PG_RELE_STEP.PHASE_ID is
'�׶α��';

comment on column PG_RELE_STEP.PHASE_BK_DESC is
'�׶�����';

comment on column PG_RELE_STEP.SOC_UUID is
'����ԴUUID';

comment on column PG_RELE_STEP.GEN_YN_FLAG is
'�Ƿ�����ʵ��';

comment on column PG_RELE_STEP.IMPL_TYPE is
'ʵ������';

/*==============================================================*/
/* Table: US_ROLE_ENV_PRIV                                      */
/*==============================================================*/
create table US_ROLE_ENV_PRIV  (
   DPRL_CODE            CHAR(8)                         not null,
   ENV_NAME             VARCHAR2(20)                    not null,
   SYS_NAME             VARCHAR2(20),
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_US_ROLE_ENV_PRIV primary key (DPRL_CODE, ENV_NAME)
);

comment on table US_ROLE_ENV_PRIV is
'���Ž�ɫӦ�û���Ȩ�ޱ�';

comment on column US_ROLE_ENV_PRIV.DPRL_CODE is
'���Ž�ɫ����';

comment on column US_ROLE_ENV_PRIV.ENV_NAME is
'��������';

comment on column US_ROLE_ENV_PRIV.SYS_NAME is
'Ӧ��ϵͳ';

comment on column US_ROLE_ENV_PRIV.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: US_USER_ENV_PRIV                                      */
/*==============================================================*/
create table US_USER_ENV_PRIV  (
   USER_ID              VARCHAR2(20)                    not null,
   ENV_NAME             VARCHAR2(20)                    not null,
   PRIV_TYPE            INTEGER                        default 0 not null,
   SYS_NAME             VARCHAR2(20),
   AF_FLAG              INTEGER                        default 0 not null,
   TEMPSTART_BK_DATETIME TIMESTAMP,
   TEMPEND_BK_DATETIME  TIMESTAMP,
   BACKUP_FLD           VARCHAR2(50),
   constraint PK_US_USER_ENV_PRIV primary key (USER_ID, ENV_NAME, PRIV_TYPE)
);

comment on table US_USER_ENV_PRIV is
'�û�Ӧ�û���Ȩ�ޱ�';

comment on column US_USER_ENV_PRIV.USER_ID is
'�û���';

comment on column US_USER_ENV_PRIV.ENV_NAME is
'��������';

comment on column US_USER_ENV_PRIV.PRIV_TYPE is
'��Դ����';

comment on column US_USER_ENV_PRIV.SYS_NAME is
'Ӧ��ϵͳ';

comment on column US_USER_ENV_PRIV.AF_FLAG is
'�����ֹ��־';

comment on column US_USER_ENV_PRIV.TEMPSTART_BK_DATETIME is
'��ʱȨ�޿�ʼʱ��';

comment on column US_USER_ENV_PRIV.TEMPEND_BK_DATETIME is
'��ʱȨ�޽���ʱ��';

comment on column US_USER_ENV_PRIV.BACKUP_FLD is
'�����ֶ�';

/*==============================================================*/
/* Table: UU_FILELIST                                           */
/*==============================================================*/
create table UU_FILELIST  (
   LIST_UUID            CHAR(32)                        not null,
   FILE_WORK_SEQ        VARCHAR2(17)                    not null,
   FILE_PATH            VARCHAR2(200),
   FILE_NAME            VARCHAR2(50),
   FILE_TYPE            VARCHAR2(10),
   FILE_SIZE            INTEGER                        default 0,
   PACKAGE_NAME         VARCHAR2(50),
   SERVER_NAME          VARCHAR2(20),
   STORAGE_BK_PATH      VARCHAR2(200),
   TARGET_RELATIVE_PATH VARCHAR2(200),
   constraint PK_UU_FILELIST primary key (LIST_UUID, FILE_WORK_SEQ)
);

comment on table UU_FILELIST is
'�ļ��嵥��';

comment on column UU_FILELIST.LIST_UUID is
'�嵥UUID';

comment on column UU_FILELIST.FILE_WORK_SEQ is
'�ļ���ˮ��';

comment on column UU_FILELIST.FILE_PATH is
'�ļ�Ŀ¼';

comment on column UU_FILELIST.FILE_NAME is
'�ļ���';

comment on column UU_FILELIST.FILE_TYPE is
'�ļ�����';

comment on column UU_FILELIST.FILE_SIZE is
'�ļ�����';

comment on column UU_FILELIST.PACKAGE_NAME is
'��������';

comment on column UU_FILELIST.SERVER_NAME is
'������������';

comment on column UU_FILELIST.STORAGE_BK_PATH is
'�����·��';

comment on column UU_FILELIST.TARGET_RELATIVE_PATH is
'Ŀ������·��';

/*==============================================================*/
/* Table: UU_PARAM                                              */
/*==============================================================*/
create table UU_PARAM  (
   PARAM_UUID           CHAR(32)                        not null,
   PARAM_NAME           VARCHAR2(50)                    not null,
   PARAM_TYPE           INTEGER,
   PARAM_VALUE          VARCHAR2(500),
   PARAM_CN_NAME        VARCHAR2(50),
   PARAM_BK_DESC        VARCHAR2(100),
   PARAM_MODIFY_FLAG    INTEGER                        default 0,
   BACKUP_FLD           VARCHAR2(50),
   PARAM_GROUP          VARCHAR2(20),
   PHASE_NO             INTEGER                        default 0,
   constraint PK_UU_PARAM primary key (PARAM_UUID, PARAM_NAME)
);

comment on table UU_PARAM is
'ģ�����������';

comment on column UU_PARAM.PARAM_UUID is
'����UUID';

comment on column UU_PARAM.PARAM_NAME is
'������';

comment on column UU_PARAM.PARAM_TYPE is
'��������';

comment on column UU_PARAM.PARAM_VALUE is
'����ֵ';

comment on column UU_PARAM.PARAM_CN_NAME is
'����������';

comment on column UU_PARAM.PARAM_BK_DESC is
'��������';

comment on column UU_PARAM.PARAM_MODIFY_FLAG is
'�Ƿ���޸�';

comment on column UU_PARAM.BACKUP_FLD is
'�����ֶ�';

comment on column UU_PARAM.PARAM_GROUP is
'��������';

comment on column UU_PARAM.PHASE_NO is
'�׶κ�';

/*==============================================================*/
/* Table: UU_SOC                                                */
/*==============================================================*/
create table UU_SOC  (
   SOC_UUID             CHAR(32)                        not null,
   SOC_BK_SEQ           INTEGER                        default 0 not null,
   EXE_SERVER_NAME      VARCHAR2(20),
   EXE_SOC_NAME         VARCHAR2(20),
   VER_SERVER_NAME      VARCHAR2(20),
   VER_SOC_NAME         VARCHAR2(20),
   CODE_BK_DIR          VARCHAR2(100),
   constraint PK_UU_SOC primary key (SOC_UUID, SOC_BK_SEQ)
);

comment on table UU_SOC is
'����Դ������';

comment on column UU_SOC.SOC_UUID is
'����ԴUUID';

comment on column UU_SOC.SOC_BK_SEQ is
'����Դ���';

comment on column UU_SOC.EXE_SERVER_NAME is
'ִ�з�������';

comment on column UU_SOC.EXE_SOC_NAME is
'ִ������Դ��';

comment on column UU_SOC.VER_SERVER_NAME is
'�汾��������';

comment on column UU_SOC.VER_SOC_NAME is
'�汾����Դ��';

comment on column UU_SOC.CODE_BK_DIR is
'Դ��汾Ŀ¼';

/*==============================================================*/
/* View: VI_SYS_ENV_SERVER                                      */
/*==============================================================*/
create or replace view VI_SYS_ENV_SERVER as
select sys.SYS_NAME,sys.SYS_CN_NAME,sys.SYS_BK_DESC,sys.SYS_TYPE,en.ENV_NAME,en.ENV_CN_NAME,en.ENV_BK_DESC,en.ENV_TYPE,en.DT_RANGE,se.SERVER_NAME,se.SERVER_CN_NAME,SERVER_DESC,se.SERVER_IP,se.SERVER_OS,se.OS_SBK_VER,se.SERVER_DB,se.SERVER_MID_WARE from ce_system sys left join ce_environment en on sys.SYS_NAME=en.SYS_NAME LEFT JOIN ce_environment_server es on en.ENV_NAME=es.ENV_NAME left JOIN ce_server se on es.SERVER_NAME=se.SERVER_NAME 
with read only;

 comment on table VI_SYS_ENV_SERVER is
'Ӧ�û�����������ͼ';



