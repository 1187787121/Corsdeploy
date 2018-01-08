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
'渠道定义表';

comment on column CH_CHANNEL.CHANNEL_CODE is
'渠道编码';

comment on column CH_CHANNEL.CHANNEL_CN_NAME is
'渠道中文名称';

comment on column CH_CHANNEL.CHANNEL_TYPE is
'渠道类型';

comment on column CH_CHANNEL.CHANNEL_BK_DESC is
'渠道描述';

comment on column CH_CHANNEL.BACKUP_FLD is
'备用字段';

comment on column CH_CHANNEL.RCD_STATE is
'记录状态';

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
'渠道服务组权限配置表';

comment on column CH_CHANNEL_SRVG_PRIV.CHANNEL_CODE is
'渠道编码';

comment on column CH_CHANNEL_SRVG_PRIV.SRVG_CODE is
'服务组编码';

comment on column CH_CHANNEL_SRVG_PRIV.BACKUP_FLD is
'备用字段';

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
'渠道服务权限配置表';

comment on column CH_CHANNEL_SRV_PRIV.CHANNEL_CODE is
'渠道编码';

comment on column CH_CHANNEL_SRV_PRIV.SRV_NAME is
'服务名';

comment on column CH_CHANNEL_SRV_PRIV.AF_FLAG is
'允许禁止标志';

comment on column CH_CHANNEL_SRV_PRIV.BACKUP_FLD is
'备用字段';

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
'序号表';

comment on column CM_SEQ.SEQ_NAME is
'序号名称';

comment on column CM_SEQ.CUR_BK_SEQ is
'当前序号';

comment on column CM_SEQ.SEQ_FLD_LENGTH is
'序号长度';

comment on column CM_SEQ.SEQ_TYPE is
'序号种类';

comment on column CM_SEQ.LMOD_BK_DATE is
'上次修改日期';

comment on column CM_SEQ.LS_BK_SEQ is
'上日序号';

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
'数据字典信息表';

comment on column DC_DICT.DOMAIN_NAME is
'域民称';

comment on column DC_DICT.DOMAIN_CN_NAME is
'域中文名称';

comment on column DC_DICT.FLD_TYPE is
'类型';

comment on column DC_DICT.FLD_LENGTH is
'总长度';

comment on column DC_DICT.FLD_SCALE is
'小数位';

comment on column DC_DICT.ENU_YN_FLAG is
'是否枚举';

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
'数据字典枚举表';

comment on column DC_DICT_ENU.DOMAIN_NAME is
'域名称';

comment on column DC_DICT_ENU.ENU_VALUE is
'选项值';

comment on column DC_DICT_ENU.ENU_CODE is
'选项代码';

comment on column DC_DICT_ENU.ENU_BK_EXPL is
'选项说明';

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
'部门表';

comment on column DP_DEPT.DEPT_ID is
'部门编码';

comment on column DP_DEPT.DEPT_CN_NAME is
'部门名称';

comment on column DP_DEPT.DEPT_FULL_CNAME is
'部门全称';

comment on column DP_DEPT.DEPT_TYPE is
'部门类型';

comment on column DP_DEPT.DEPT_LEVEL is
'部门级别';

comment on column DP_DEPT.BRANCH_NO is
'机构号';

comment on column DP_DEPT.SUPER_DEPT_ID is
'上级部门编码';

comment on column DP_DEPT.BACKUP_FLD is
'备用字段';

comment on column DP_DEPT.CRT_BK_DATE is
'创建日期';

comment on column DP_DEPT.CRT_BK_TIME is
'创建时间';

comment on column DP_DEPT.CRT_USER_ID is
'创建用户';

comment on column DP_DEPT.MODIFY_BK_DATE is
'修改日期';

comment on column DP_DEPT.MODIFY_BK_TIME is
'修改时间';

comment on column DP_DEPT.MODIFY_USER_ID is
'修改用户';

comment on column DP_DEPT.DEL_BK_DATE is
'删除日期';

comment on column DP_DEPT.DEL_BK_TIME is
'删除时间';

comment on column DP_DEPT.DEL_USER_ID is
'删除用户';

comment on column DP_DEPT.RCD_STATE is
'记录状态';

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
'部门SQL字段操作权限表';

comment on column DP_DEPT_COL_PRIV.DEPT_ID is
'部门编码';

comment on column DP_DEPT_COL_PRIV.SOC_NAME is
'数据源名称';

comment on column DP_DEPT_COL_PRIV.TBL_NAME is
'表名';

comment on column DP_DEPT_COL_PRIV.COL_NAME is
'字段名称';

comment on column DP_DEPT_COL_PRIV.INS_PRIV_FLAG is
'INSERT权限';

comment on column DP_DEPT_COL_PRIV.DEL_PRIV_FLAG is
'DELETE权限';

comment on column DP_DEPT_COL_PRIV.UPD_PRIV_FLAG is
'UPDATE权限';

comment on column DP_DEPT_COL_PRIV.SEL_PRIV_FLAG is
'SELECT权限';

comment on column DP_DEPT_COL_PRIV.BACKUP_FLD is
'备用字段';

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
'部门操作权限配置表';

comment on column DP_DEPT_OPT_PRIV.OPT_CODE is
'操作编码';

comment on column DP_DEPT_OPT_PRIV.DEPT_ID is
'部门编码';

comment on column DP_DEPT_OPT_PRIV.PRIV_FLAG is
'权限';

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
'部门资源权限表';

comment on column DP_DEPT_RS_PRIV.DEPT_ID is
'部门编码';

comment on column DP_DEPT_RS_PRIV.RS_CODE is
'资源编码';

comment on column DP_DEPT_RS_PRIV.BACKUP_FLD is
'备用字段';

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
'部门数据源权限表';

comment on column DP_DEPT_SOC_PRIV.DEPT_ID is
'部门编码';

comment on column DP_DEPT_SOC_PRIV.SOC_NAME is
'数据源名称';

comment on column DP_DEPT_SOC_PRIV.BACKUP_FLD is
'备用字段';

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
'部门SQL操作权限表';

comment on column DP_DEPT_SQL_PRIV.DEPT_ID is
'部门编码';

comment on column DP_DEPT_SQL_PRIV.SOC_NAME is
'数据源名称';

comment on column DP_DEPT_SQL_PRIV.TBL_NAME is
'表名';

comment on column DP_DEPT_SQL_PRIV.INS_PRIV_FLAG is
'INSERT权限';

comment on column DP_DEPT_SQL_PRIV.DEL_PRIV_FLAG is
'DELETE权限';

comment on column DP_DEPT_SQL_PRIV.UPD_PRIV_FLAG is
'UPDATE权限';

comment on column DP_DEPT_SQL_PRIV.SEL_PRIV_FLAG is
'SELECT权限';

comment on column DP_DEPT_SQL_PRIV.BACKUP_FLD is
'备用字段';

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
'数据源信息表';

comment on column DT_SOURCE.SOC_NAME is
'数据源名称';

comment on column DT_SOURCE.SOC_IP is
'IP地址';

comment on column DT_SOURCE.SOC_PORT is
'端口号';

comment on column DT_SOURCE.PROTOCOL_TYPE is
'协议类型';

comment on column DT_SOURCE.REMOTE_UNAME is
'服务器登陆用户名';

comment on column DT_SOURCE.REMOTE_PASSWD is
'服务器登陆密码';

comment on column DT_SOURCE.KEY_REMOTE_PASSWD is
'密钥';

comment on column DT_SOURCE.BK_TIMEOUT is
'超时时间';

comment on column DT_SOURCE.JDBC_DRV is
'Jdbc_driver';

comment on column DT_SOURCE.JDBC_URL is
'Jdbc_url';

comment on column DT_SOURCE.JDBC_SCHEMA is
'Jdbc_schema';

comment on column DT_SOURCE.CVT_TYPE is
'转码方式';

comment on column DT_SOURCE.FTP_LOCAL_SCRIPT is
'上下传脚本';

comment on column DT_SOURCE.CVT_LOCAL_SCRIPT is
'转码脚本';

comment on column DT_SOURCE.SOC_DOMAIN is
'数据源域名';

comment on column DT_SOURCE.SOC_BK_DESC is
'数据源描述';

comment on column DT_SOURCE.SOC_PARAMS is
'数据源参数';

comment on column DT_SOURCE.USER_ROOT_PATH is
'用户根路径';

comment on column DT_SOURCE.BACKUP_FLD is
'备用字段';

comment on column DT_SOURCE.RCD_STATE is
'记录状态';

comment on column DT_SOURCE.ENVIRONMENT_VARIABLES is
'环境参数';

comment on column DT_SOURCE.ENCODING_TYPE is
'编码格式';

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
'日期下载信息表';

comment on column LG_LOG_DOWN.LOG_ROOT_PATH is
'日志文件路径';

comment on column LG_LOG_DOWN.LOG_FILE_NAME is
'日志文件名称';

comment on column LG_LOG_DOWN.START_BK_DATE is
'日志起始日期';

comment on column LG_LOG_DOWN.END_BK_DATE is
'日志截止日期';

comment on column LG_LOG_DOWN.USER_ID is
'用户名';

comment on column LG_LOG_DOWN.CRT_BK_DATE is
'生成日志日期';

comment on column LG_LOG_DOWN.CRT_BK_TIME is
'生成日志时间';

comment on column LG_LOG_DOWN.BACKUP_FLD is
'备用';

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
'日志标记级别表';

comment on column LG_LOG_LABEL.WORK_SEQ is
'任务流水号';

comment on column LG_LOG_LABEL.USER_ID is
'用户名';

comment on column LG_LOG_LABEL.LOG_LABEL is
'日志标记';

comment on column LG_LOG_LABEL.LABEL_BK_DATE is
'标记日期';

comment on column LG_LOG_LABEL.LABEL_BK_TIME is
'标记时间';

comment on column LG_LOG_LABEL.BACKUP_FLD is
'备用';

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
'任务日志流水表';

comment on column LG_LOG_MF.WORK_SEQ is
'任务流水号';

comment on column LG_LOG_MF.ORG_CHANNEL_CODE is
'发起渠道';

comment on column LG_LOG_MF.ORG_TERM_NO is
'发起终端';

comment on column LG_LOG_MF.ORG_WORK_CODE is
'发起任务编码';

comment on column LG_LOG_MF.ORG_SRV_NAME is
'发起服务名';

comment on column LG_LOG_MF.ORG_SRV_BK_DESC is
'发起服务描述';

comment on column LG_LOG_MF.ORG_RS_CODE is
'发起资源编码';

comment on column LG_LOG_MF.ORG_ARY_SOCNAME is
'发起数据源数组';

comment on column LG_LOG_MF.PEND_WORK_SEQ is
'待处理流水号';

comment on column LG_LOG_MF.PEND_WORK_CODE is
'待处理任务编码';

comment on column LG_LOG_MF.PEND_SRV_NAME is
'待处理服务名称';

comment on column LG_LOG_MF.PEND_RS_CODE is
'待处理资源编码';

comment on column LG_LOG_MF.PEND_ARY_SOCNAME is
'待处理数据源数组';

comment on column LG_LOG_MF.PENDWK_BK_EXPL is
'待处理任务说明';

comment on column LG_LOG_MF.PBL_CODE is
'问题单编码';

comment on column LG_LOG_MF.SR_YN_FLAG is
'成功标准';

comment on column LG_LOG_MF.CRT_USER_ID is
'创建用户';

comment on column LG_LOG_MF.CRT_USER_CN_NAME is
'用户中文名';

comment on column LG_LOG_MF.CRT_DEPT_ID is
'创建部门';

comment on column LG_LOG_MF.CRT_DEPT_CN_NAME is
'部门中文名';

comment on column LG_LOG_MF.CRT_BK_DATE is
'创建日期';

comment on column LG_LOG_MF.CRT_BK_TIME is
'创建时间';

comment on column LG_LOG_MF.LOG_TXT is
'日志内容';

comment on column LG_LOG_MF.LOG_LEVEL is
'日志级别';

comment on column LG_LOG_MF.BACKUP_FLD is
'备用';

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
'消息信息表';

comment on column MG_MSG.WORK_SEQ is
'消息流水号';

comment on column MG_MSG.MSG_TITLE is
'消息标题';

comment on column MG_MSG.MSG_TEXT is
'消息正文';

comment on column MG_MSG.MSG_TYPE is
'消息类型';

comment on column MG_MSG.FILE_PATH is
'文件路径';

comment on column MG_MSG.FIRST_BK_FNAME is
'文件名1';

comment on column MG_MSG.SECD_BK_FNAME is
'文件名2';

comment on column MG_MSG.THIRD_BK_FNAME is
'文件名3';

comment on column MG_MSG.BACKUP_FLD is
'备用字段';

comment on column MG_MSG.CRT_USER_ID is
'创建用户';

comment on column MG_MSG.CRT_BK_DATE is
'创建日期';

comment on column MG_MSG.CRT_BK_TIME is
'创建时间';

comment on column MG_MSG.DEL_YN_FLAG is
'删除状态';

comment on column MG_MSG.RCD_STATE is
'记录状态';

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
'消息用户接收表';

comment on column MG_MSG_USER.WORK_SEQ is
'消息流水号';

comment on column MG_MSG_USER.RC_USER_ID is
'接收用户';

comment on column MG_MSG_USER.RC_FLAG is
'接收状态';

comment on column MG_MSG_USER.ATTENT_YN_FLAG is
'关注状态';

comment on column MG_MSG_USER.RC_BK_DATE is
'接收日期';

comment on column MG_MSG_USER.RC_BK_TIME is
'接收时间';

comment on column MG_MSG_USER.RCD_STATE is
'记录状态';

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
'资源操作定义表';

comment on column RS_OPT.OPT_CODE is
'操作编码';

comment on column RS_OPT.BL_RS_CODE is
'所属资源编码';

comment on column RS_OPT.OPT_BK_SEQ is
'操作序号';

comment on column RS_OPT.OPT_NAME is
'操作名';

comment on column RS_OPT.OPT_BK_EXPL is
'操作说明';

comment on column RS_OPT.DIS_EXPRESS is
'禁用表达式';

comment on column RS_OPT.CRT_USER_ID is
'创建用户';

comment on column RS_OPT.CRT_BK_DATE is
'创建日期';

comment on column RS_OPT.CRT_BK_TIME is
'创建时间';

comment on column RS_OPT.BACKUP_FLD is
'备用字段';

comment on column RS_OPT.RCD_STATE is
'记录状态';

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
'资源配置表';

comment on column RS_RES.RS_CODE is
'资源编码';

comment on column RS_RES.SUPER_RS_CODE is
'上级资源编码';

comment on column RS_RES.BL_RS_CODE is
'所属一级资源编码';

comment on column RS_RES.RS_FUN_TYPE is
'资源类型';

comment on column RS_RES.RS_CN_NAME is
'资源名称';

comment on column RS_RES.RS_BK_DESC is
'资源描述';

comment on column RS_RES.RS_URL is
'资源地址';

comment on column RS_RES.RSIM_URL is
'资源图标地址';

comment on column RS_RES.RS_LEVEL is
'资源级别';

comment on column RS_RES.GHEIGHT_BK_PIXEL is
'高度';

comment on column RS_RES.PWIDTH_BK_PIXEL is
'宽度';

comment on column RS_RES.TITLE_ABLE is
'是否有标题';

comment on column RS_RES.PUBLIC_YN_FLAG is
'是否公开';

comment on column RS_RES.OPEN_TYPE is
'开放类型';

comment on column RS_RES.SORT_KEY is
'排序键';

comment on column RS_RES.RCD_STATE is
'记录状态';

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
'远程服务执行信息表';

comment on column RT_SVC_EXE.WORK_SEQ is
'任务流水号';

comment on column RT_SVC_EXE.SOC_NAME is
'数据源名称';

comment on column RT_SVC_EXE.START_BK_DATE is
'开始日期';

comment on column RT_SVC_EXE.START_BK_TIME is
'开始时间';

comment on column RT_SVC_EXE.END_BK_DATE is
'结束日期';

comment on column RT_SVC_EXE.END_BK_TIME is
'结束时间';

comment on column RT_SVC_EXE.CUR_PROC_STATE is
'当前状态';

comment on column RT_SVC_EXE.PROC_NAME is
'进程名称';

comment on column RT_SVC_EXE.PROC_NUM is
'进程号';

comment on column RT_SVC_EXE.BL_USER_ID is
'进程所属用户';

comment on column RT_SVC_EXE.RUN_CMD_STR is
'执行程序';

comment on column RT_SVC_EXE.ORG_USER_ID is
'提交用户';

comment on column RT_SVC_EXE.RS_BK_TEXT is
'运行结果';

comment on column RT_SVC_EXE.ONE_BACKUP_FLD is
'备用字段1';

comment on column RT_SVC_EXE.TWO_BACKUP_FLD is
'备用字段2';

comment on column RT_SVC_EXE.THREE_BACKUP_FLD is
'备用字段3';

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
'远程服务调用配置表';

comment on column SV_REMOTE_SRV.SRV_NAME is
'服务名称';

comment on column SV_REMOTE_SRV.DEAL_SEQ is
'处理序号';

comment on column SV_REMOTE_SRV.REMOTE_SRV_NAME is
'目标主机名称';

comment on column SV_REMOTE_SRV.SRV_TYPE is
'目标主机服务类型';

comment on column SV_REMOTE_SRV.SRV_ROOT_PATH is
'服务路径';

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
'服务配置表';

comment on column SV_SRV.SRV_NAME is
'服务名称';

comment on column SV_SRV.SUP_SRVG_CODE is
'所属服务组编码';

comment on column SV_SRV.SRV_BK_DESC is
'服务描述';

comment on column SV_SRV.SRV_FUN_TYPE is
'服务类型';

comment on column SV_SRV.SRV_CLASS_NAME is
'服务类名';

comment on column SV_SRV.SRV_METHOD_NAME is
'服务方法名';

comment on column SV_SRV.CHECK_FLAG is
'是否复核';

comment on column SV_SRV.AUTH_FLAG is
'是否授权';

comment on column SV_SRV.SOC_FLAG is
'是否定义数据源';

comment on column SV_SRV.SALLOW_FLAG is
'服务允许标志';

comment on column SV_SRV.LOG_LEVEL is
'日志级别';

comment on column SV_SRV.RCD_STATE is
'记录状态';

comment on column SV_SRV.APROV_TYPE is
'审批展示类型';

comment on column SV_SRV.CUSTOM_RS_CODE is
'定制页面资源编码';

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
'服务组定义表';

comment on column SV_SRVG.SRVG_CODE is
'服务组编码';

comment on column SV_SRVG.SRVG_CN_NAME is
'服务组名称';

comment on column SV_SRVG.SRVG_BK_DESC is
'服务组描述';

comment on column SV_SRVG.SRVG_FUN_TYPE is
'服务组类型';

comment on column SV_SRVG.BACKUP_FLD is
'备用字段';

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
'服务授权定义表';

comment on column SV_SRV_AUTH.AUTH_DEPT_ID is
'配置部门编码';

comment on column SV_SRV_AUTH.SRV_NAME is
'服务名称';

comment on column SV_SRV_AUTH.AUTH_SEQ is
'授权序号';

comment on column SV_SRV_AUTH.AUTH_TYPE is
'授权类型';

comment on column SV_SRV_AUTH.AUTH_APROV_CATEGORY is
'审批类别';

comment on column SV_SRV_AUTH.AUTH_DPRL_CODE is
'授权部门角色';

comment on column SV_SRV_AUTH.ROLE_CN_NAME is
'角色描述';

comment on column SV_SRV_AUTH.SUPER_FLAG is
'是否上级配置';

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
'服务复核定义表';

comment on column SV_SRV_CHECK.CHECK_DEPT_ID is
'配置部门编码';

comment on column SV_SRV_CHECK.SRV_NAME is
'服务名称';

comment on column SV_SRV_CHECK.CHECK_SEQ is
'复核序号';

comment on column SV_SRV_CHECK.CHK_APROV_CATEGORY is
'审批类别';

comment on column SV_SRV_CHECK.CHK_DPRL_CODE is
'复核部门角色';

comment on column SV_SRV_CHECK.ROLE_CN_NAME is
'角色描述';

comment on column SV_SRV_CHECK.SUPER_FLAG is
'是否上级配置';

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
'服务数据源配置表';

comment on column SV_SRV_SOC.SRV_NAME is
'服务名称';

comment on column SV_SRV_SOC.SOC_NAME is
'数据源名称';

comment on column SV_SRV_SOC.SOC_SEQ is
'数据源顺序号';

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
'终端配置表';

comment on column TM_TERM.TERM_NO is
'终端号';

comment on column TM_TERM.TERM_TYPE is
'终端类型';

comment on column TM_TERM.TERM_BK_DESC is
'终端说明';

comment on column TM_TERM.CRT_USER_ID is
'创建用户';

comment on column TM_TERM.CRT_DEPT_ID is
'创建部门';

comment on column TM_TERM.CRT_BK_DATE is
'创建日期';

comment on column TM_TERM.CRT_BK_TIME is
'创建时间';

comment on column TM_TERM.BACKUP_FLD is
'备用';

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
'部门角色关联表';

comment on column US_DEPT_ROLE.DPRL_CODE is
'部门角色编码';

comment on column US_DEPT_ROLE.DEPT_ID is
'部门编码';

comment on column US_DEPT_ROLE.ROLE_CODE is
'角色编码';

comment on column US_DEPT_ROLE.BK_EXPL is
'部门角色说明';

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
'角色信表';

comment on column US_ROLE.ROLE_CODE is
'角色编码';

comment on column US_ROLE.ROLE_CN_NAME is
'角色名称';

comment on column US_ROLE.ROLE_TYPE is
'角色类型';

comment on column US_ROLE.ROLE_BK_DESC is
'角色说明';

comment on column US_ROLE.BACKUP_FLD is
'备用字段';

comment on column US_ROLE.CRT_BK_DATE is
'创建日期';

comment on column US_ROLE.CRT_BK_TIME is
'创建时间';

comment on column US_ROLE.CRT_USER_ID is
'创建用户';

comment on column US_ROLE.MODIFY_BK_DATE is
'修改日期';

comment on column US_ROLE.MODIFY_BK_TIME is
'修改时间';

comment on column US_ROLE.MODIFY_USER_ID is
'修改用户';

comment on column US_ROLE.DEL_BK_DATE is
'删除日期';

comment on column US_ROLE.DEL_BK_TIME is
'删除时间';

comment on column US_ROLE.DEL_USER_ID is
'删除用户';

comment on column US_ROLE.RCD_STATE is
'记录状态';

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
'部门角色SQL字段操作权限表';

comment on column US_ROLE_COL_PRIV.DPRL_CODE is
'部门角色编码';

comment on column US_ROLE_COL_PRIV.SOC_NAME is
'数据源名称';

comment on column US_ROLE_COL_PRIV.TBL_NAME is
'表名';

comment on column US_ROLE_COL_PRIV.COL_NAME is
'字段名称';

comment on column US_ROLE_COL_PRIV.INS_PRIV_FLAG is
'Insert权限';

comment on column US_ROLE_COL_PRIV.DEL_PRIV_FLAG is
'Delete权限';

comment on column US_ROLE_COL_PRIV.UPD_PRIV_FLAG is
'Update权限';

comment on column US_ROLE_COL_PRIV.SEL_PRIV_FLAG is
'Select权限';

comment on column US_ROLE_COL_PRIV.BACKUP_FLD is
'备用字段';

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
'部门角色操作权限配置表';

comment on column US_ROLE_OPT_PRIV.OPT_CODE is
'操作编码';

comment on column US_ROLE_OPT_PRIV.DPRL_CODE is
'部门角色编码';

comment on column US_ROLE_OPT_PRIV.PRIV_FLAG is
'权限';

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
'部门角色资源权限表';

comment on column US_ROLE_RS_PRIV.DPRL_CODE is
'部门角色编码';

comment on column US_ROLE_RS_PRIV.RS_CODE is
'资源编码';

comment on column US_ROLE_RS_PRIV.BACKUP_FLD is
'备用字段';

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
'部门角色数据源权限表';

comment on column US_ROLE_SOC_PRIV.DPRL_CODE is
'部门角色编码';

comment on column US_ROLE_SOC_PRIV.SOC_NAME is
'数据源名称';

comment on column US_ROLE_SOC_PRIV.BACKUP_FLD is
'备用字段';

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
'部门角色SQL操作权限表';

comment on column US_ROLE_SQL_PRIV.DPRL_CODE is
'部门角色编码';

comment on column US_ROLE_SQL_PRIV.SOC_NAME is
'数据源名称';

comment on column US_ROLE_SQL_PRIV.TBL_NAME is
'表名';

comment on column US_ROLE_SQL_PRIV.INS_PRIV_FLAG is
'Insert权限';

comment on column US_ROLE_SQL_PRIV.DEL_PRIV_FLAG is
'Delete权限';

comment on column US_ROLE_SQL_PRIV.UPD_PRIV_FLAG is
'Update权限';

comment on column US_ROLE_SQL_PRIV.SEL_PRIV_FLAG is
'Select权限';

comment on column US_ROLE_SQL_PRIV.BACKUP_FLD is
'备用字段';

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
'用户表';

comment on column US_USER.USER_ID is
'用户名';

comment on column US_USER.USER_PASSWD is
'用户密码';

comment on column US_USER.PWDEXP_BK_DATE is
'密码到期日';

comment on column US_USER.USER_CN_NAME is
'用户姓名';

comment on column US_USER.EMAIL_ADD is
'邮箱';

comment on column US_USER.PHONE_NO is
'电话号码';

comment on column US_USER.TELLER_NO is
'柜员号';

comment on column US_USER.LOGIN_BK_NUM is
'用户登录数量';

comment on column US_USER.BL_DEPT_ID is
'所属部门号';

comment on column US_USER.USER_TYPE is
'用户类型';

comment on column US_USER.FIRST_DEPT_ID is
'兼职部门1';

comment on column US_USER.SECD_DEPT_ID is
'兼职部门2';

comment on column US_USER.THIRD_DEPT_ID is
'兼职部门3';

comment on column US_USER.BACKUP_FLD is
'备用字段';

comment on column US_USER.CRT_BK_DATE is
'创建日期';

comment on column US_USER.CRT_BK_TIME is
'创建时间';

comment on column US_USER.CRT_USER_ID is
'创建用户';

comment on column US_USER.MODIFY_BK_DATE is
'修改日期';

comment on column US_USER.MODIFY_BK_TIME is
'修改时间';

comment on column US_USER.MODIFY_USER_ID is
'修改用户';

comment on column US_USER.DEL_BK_DATE is
'删除日期';

comment on column US_USER.DEL_BK_TIME is
'删除时间';

comment on column US_USER.DEL_USER_ID is
'删除用户';

comment on column US_USER.RCD_STATE is
'记录状态';

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
'用户SQL字段操作权限表';

comment on column US_USER_COL_PRIV.USER_ID is
'用户名';

comment on column US_USER_COL_PRIV.SOC_NAME is
'数据源名称';

comment on column US_USER_COL_PRIV.TBL_NAME is
'表名';

comment on column US_USER_COL_PRIV.COL_NAME is
'字段名称';

comment on column US_USER_COL_PRIV.PRIV_TYPE is
'资源类型';

comment on column US_USER_COL_PRIV.INS_PRIV_FLAG is
'Insert权限';

comment on column US_USER_COL_PRIV.DEL_PRIV_FLAG is
'Delete权限';

comment on column US_USER_COL_PRIV.UPD_PRIV_FLAG is
'Update权限';

comment on column US_USER_COL_PRIV.SEL_PRIV_FLAG is
'Select权限';

comment on column US_USER_COL_PRIV.AF_FLAG is
'允许禁止标志位';

comment on column US_USER_COL_PRIV.TEMPSTART_BK_DATETIME is
'临时权限开始时间';

comment on column US_USER_COL_PRIV.TEMPEND_BK_DATETIME is
'临时权限结束时间';

comment on column US_USER_COL_PRIV.BACKUP_FLD is
'备用字段';

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
'用户常用联系人关联表';

comment on column US_USER_CONTACT.USER_ID is
'用户ID';

comment on column US_USER_CONTACT.CONTACT_USER_ID is
'用户常用联系人';

comment on column US_USER_CONTACT.BACKUP_FLD is
'备用字段';

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
'用户操作权限配置表';

comment on column US_USER_OPT_PRIV.OPT_CODE is
'操作编码';

comment on column US_USER_OPT_PRIV.USER_ID is
'用户名';

comment on column US_USER_OPT_PRIV.PRIV_FLAG is
'权限';

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
'用户角色关联表';

comment on column US_USER_ROLE.USER_ID is
'用户名';

comment on column US_USER_ROLE.DPRL_CODE is
'部门角色编码';

comment on column US_USER_ROLE.USER_BK_WEIGHT is
'用户权重';

comment on column US_USER_ROLE.BACKUP_FLD is
'备用字段';

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
'用户资源权限表';

comment on column US_USER_RS_PRIV.USER_ID is
'用户名';

comment on column US_USER_RS_PRIV.RS_CODE is
'资源编码';

comment on column US_USER_RS_PRIV.PRIV_TYPE is
'权限类型';

comment on column US_USER_RS_PRIV.AF_FLAG is
'允许禁止标志';

comment on column US_USER_RS_PRIV.TEMPSTART_BK_DATETIME is
'临时权限开始时间';

comment on column US_USER_RS_PRIV.TEMPEND_BK_DATETIME is
'临时权限结束时间';

comment on column US_USER_RS_PRIV.BACKUP_FLD is
'备用字段';

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
'用户数据源权限表';

comment on column US_USER_SOC_PRIV.USER_ID is
'用户名';

comment on column US_USER_SOC_PRIV.SOC_NAME is
'数据源名称';

comment on column US_USER_SOC_PRIV.PRIV_TYPE is
'资源类型';

comment on column US_USER_SOC_PRIV.AF_FLAG is
'允许禁止标志';

comment on column US_USER_SOC_PRIV.TEMPSTART_BK_DATETIME is
'临时权限开始时间';

comment on column US_USER_SOC_PRIV.TEMPEND_BK_DATETIME is
'临时权限结束时间';

comment on column US_USER_SOC_PRIV.BACKUP_FLD is
'备用字段';

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
'用户SQL操作权限表';

comment on column US_USER_SQL_PRIV.USER_ID is
'用户名';

comment on column US_USER_SQL_PRIV.SOC_NAME is
'数据源名称';

comment on column US_USER_SQL_PRIV.TBL_NAME is
'表名';

comment on column US_USER_SQL_PRIV.PRIV_TYPE is
'资源类型';

comment on column US_USER_SQL_PRIV.INS_PRIV_FLAG is
'Insert权限';

comment on column US_USER_SQL_PRIV.DEL_PRIV_FLAG is
'DELETE权限';

comment on column US_USER_SQL_PRIV.UPD_PRIV_FLAG is
'Update权限';

comment on column US_USER_SQL_PRIV.SEL_PRIV_FLAG is
'Select权限';

comment on column US_USER_SQL_PRIV.AF_FLAG is
'允许禁止标志';

comment on column US_USER_SQL_PRIV.TEMPSTART_BK_DATETIME is
'临时权限开始时间';

comment on column US_USER_SQL_PRIV.TEMPEND_BK_DATETIME is
'临时权限结束时间';

comment on column US_USER_SQL_PRIV.BACKUP_FLD is
'备用字段';

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
'用户接入终端配置表';

comment on column US_USER_TERM.USER_ID is
'用户名';

comment on column US_USER_TERM.TERM_NO is
'终端号';

comment on column US_USER_TERM.CHANNEL_CODE is
'接入渠道';

comment on column US_USER_TERM.DEPT_ID is
'部门编码';

comment on column US_USER_TERM.USER_CN_NAME is
'用户姓名';

comment on column US_USER_TERM.DEPT_CN_NAME is
'部门名称';

comment on column US_USER_TERM.USE_STATE is
'启用状态';

comment on column US_USER_TERM.BACKUP_FLD is
'备用字段';

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
'任务处理明细';

comment on column WK_DEAL_DETAIL.PEND_WORK_SEQ is
'待处理任务流水号';

comment on column WK_DEAL_DETAIL.DEAL_SEQ is
'处理序号';

comment on column WK_DEAL_DETAIL.DEAL_TYPE is
'处理方式';

comment on column WK_DEAL_DETAIL.DEAL_RES is
'处理结果';

comment on column WK_DEAL_DETAIL.DEAL_USER_ID is
'处理人员';

comment on column WK_DEAL_DETAIL.DEAL_BK_DATE is
'处理日期';

comment on column WK_DEAL_DETAIL.DEAL_BK_TIME is
'处理时间';

comment on column WK_DEAL_DETAIL.DEAL_BK_DESC is
'处理说明';

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
'任务处理状态表';

comment on column WK_DEAL_STATE.PEND_WORK_SEQ is
'待处理任务流水号';

comment on column WK_DEAL_STATE.SUBMIT_WORK_SEQ is
'提交任务流水号';

comment on column WK_DEAL_STATE.PEND_WORK_CODE is
'待处理任务编码';

comment on column WK_DEAL_STATE.PEND_SRV_NAME is
'待处理服务名称';

comment on column WK_DEAL_STATE.PEND_RS_CODE is
'待处理资源编码';

comment on column WK_DEAL_STATE.PEND_ARY_SOCNAME is
'待处理数据源数组';

comment on column WK_DEAL_STATE.PENDWK_BK_EXPL is
'待处理任务说明';

comment on column WK_DEAL_STATE.PEND_DEAL_SEQ is
'待处理序号';

comment on column WK_DEAL_STATE.PEND_USER_ID is
'待处理用户';

comment on column WK_DEAL_STATE.PEND_USER_CN_NAME is
'待处理用户中文名';

comment on column WK_DEAL_STATE.PBL_CODE is
'问题单编码';

comment on column WK_DEAL_STATE.PROXY_USER_ID is
'代理用户';

comment on column WK_DEAL_STATE.CRT_USER_ID is
'创建用户';

comment on column WK_DEAL_STATE.CRT_USER_CN_NAME is
'用户中文名';

comment on column WK_DEAL_STATE.CRT_DEPT_ID is
'创建部门';

comment on column WK_DEAL_STATE.CRT_DEPT_CN_NAME is
'部门中文名';

comment on column WK_DEAL_STATE.CRT_BK_DATE is
'创建日期';

comment on column WK_DEAL_STATE.CRT_BK_TIME is
'创建时间';

comment on column WK_DEAL_STATE.WORKFLOW_STATE is
'任务状态';

comment on column WK_DEAL_STATE.BACKUP_FLD is
'备用';

comment on column WK_DEAL_STATE.RCD_STATE is
'记录状态';

comment on column WK_DEAL_STATE.APPLY_BK_EXPL is
'任务申请说明';

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
'待处理任务明细登记表';

comment on column WK_DETAIL_REGISTER.PEND_WORK_SEQ is
'待处理任务流水号';

comment on column WK_DETAIL_REGISTER.INTE_DETAIL is
'接口明细信息';

comment on column WK_DETAIL_REGISTER.APROV_TYPE is
'审批展示类型';

comment on column WK_DETAIL_REGISTER.CUSTOM_RS_CODE is
'定制页面资源编码';

comment on column WK_DETAIL_REGISTER.APPLY_HTML is
'申请页面代码';

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
'任务审批流程表';

comment on column WK_WORK_PROCESS.PEND_WORK_SEQ is
'待处理任务流水号';

comment on column WK_WORK_PROCESS.DEAL_SEQ is
'处理序号';

comment on column WK_WORK_PROCESS.PEND_USER_ID is
'审批人';

comment on column WK_WORK_PROCESS.PEND_USER_CN_NAME is
'审批人中文名';

comment on column WK_WORK_PROCESS.PEND_TYPE is
'审批方式';
