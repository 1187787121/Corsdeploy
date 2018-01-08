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
   CHANNEL_CODE         char(2) not null comment '渠道编码',
   CHANNEL_CN_NAME      varchar(50) comment '渠道中文名称',
   CHANNEL_TYPE         int not null default 0 comment '渠道类型',
   CHANNEL_BK_DESC      varchar(100) comment '渠道描述',
   BACKUP_FLD           varchar(50) comment '备用字段',
   RCD_STATE            int not null default 0 comment '记录状态',
   primary key (CHANNEL_CODE)
);

/*==============================================================*/
/* Table: CH_CHANNEL_SRVG_PRIV                                  */
/*==============================================================*/
create table CH_CHANNEL_SRVG_PRIV
(
   CHANNEL_CODE         char(2) not null comment '渠道编码',
   SRVG_CODE            char(2) not null comment '服务组编码',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (CHANNEL_CODE, SRVG_CODE)
);

/*==============================================================*/
/* Table: CH_CHANNEL_SRV_PRIV                                   */
/*==============================================================*/
create table CH_CHANNEL_SRV_PRIV
(
   CHANNEL_CODE         char(2) not null comment '渠道编码',
   SRV_NAME             varchar(50) not null comment '服务名',
   AF_FLAG              int not null default 0 comment '允许禁止标志',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (CHANNEL_CODE, SRV_NAME)
);

/*==============================================================*/
/* Table: CM_SEQ                                                */
/*==============================================================*/
create table CM_SEQ
(
   SEQ_NAME             varchar(6) not null comment '序号名称',
   CUR_BK_SEQ           bigint not null default 0 comment '当前序号',
   SEQ_FLD_LENGTH       int not null default 0 comment '序号长度',
   SEQ_TYPE             int not null default 0 comment '序号种类',
   LMOD_BK_DATE         date comment '上次修改日期',
   LS_BK_SEQ            bigint not null default 0 comment '上日序号',
   primary key (SEQ_NAME)
);

/*==============================================================*/
/* Table: DC_DICT                                               */
/*==============================================================*/
create table DC_DICT
(
   DOMAIN_NAME          varchar(20) not null comment '域民称',
   DOMAIN_CN_NAME       varchar(50) comment '域中文名称',
   FLD_TYPE             varchar(20) comment '类型',
   FLD_LENGTH           int not null default 0 comment '总长度',
   FLD_SCALE            int not null default 0 comment '小数位',
   ENU_YN_FLAG          int not null default 0 comment '是否枚举',
   primary key (DOMAIN_NAME)
);

/*==============================================================*/
/* Table: DC_DICT_ENU                                           */
/*==============================================================*/
create table DC_DICT_ENU
(
   DOMAIN_NAME          varchar(20) not null comment '域名称',
   ENU_VALUE            int not null default 0 comment '选项值',
   ENU_CODE             varchar(50) comment '选项代码',
   ENU_BK_EXPL          varchar(500) comment '选项说明',
   primary key (DOMAIN_NAME, ENU_VALUE)
);

/*==============================================================*/
/* Table: DP_DEPT                                               */
/*==============================================================*/
create table DP_DEPT
(
   DEPT_ID              char(6) not null comment '部门编码',
   DEPT_CN_NAME         varchar(50) comment '部门名称',
   DEPT_FULL_CNAME      varchar(50) comment '部门全称',
   DEPT_TYPE            int not null default 0 comment '部门类型',
   DEPT_LEVEL           int not null default 0 comment '部门级别',
   BRANCH_NO            varchar(10) comment '机构号',
   SUPER_DEPT_ID        char(6) comment '上级部门编码',
   BACKUP_FLD           varchar(50) comment '备用字段',
   CRT_BK_DATE          date comment '创建日期',
   CRT_BK_TIME          time comment '创建时间',
   CRT_USER_ID          varchar(20) comment '创建用户',
   MODIFY_BK_DATE       date comment '修改日期',
   MODIFY_BK_TIME       time comment '修改时间',
   MODIFY_USER_ID       varchar(20) comment '修改用户',
   DEL_BK_DATE          date comment '删除日期',
   DEL_BK_TIME          time comment '删除时间',
   DEL_USER_ID          varchar(20) comment '删除用户',
   RCD_STATE            int not null default 0 comment '记录状态',
   primary key (DEPT_ID)
);

/*==============================================================*/
/* Table: DP_DEPT_COL_PRIV                                      */
/*==============================================================*/
create table DP_DEPT_COL_PRIV
(
   DEPT_ID              char(6) not null comment '部门编码',
   SOC_NAME             varchar(20) not null comment '数据源名称',
   TBL_NAME             varchar(50) not null comment '表名',
   COL_NAME             varchar(50) not null comment '字段名称',
   INS_PRIV_FLAG        int not null default 0 comment 'INSERT权限',
   DEL_PRIV_FLAG        int not null default 0 comment 'DELETE权限',
   UPD_PRIV_FLAG        int not null default 0 comment 'UPDATE权限',
   SEL_PRIV_FLAG        int not null default 0 comment 'SELECT权限',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (DEPT_ID, SOC_NAME, TBL_NAME, COL_NAME)
);

/*==============================================================*/
/* Table: DP_DEPT_OPT_PRIV                                      */
/*==============================================================*/
create table DP_DEPT_OPT_PRIV
(
   OPT_CODE             varchar(12) not null comment '操作编码',
   DEPT_ID              char(6) not null comment '部门编码',
   PRIV_FLAG            int not null default 0 comment '权限',
   primary key (OPT_CODE, DEPT_ID)
);

/*==============================================================*/
/* Table: DP_DEPT_RS_PRIV                                       */
/*==============================================================*/
create table DP_DEPT_RS_PRIV
(
   DEPT_ID              char(6) not null comment '部门编码',
   RS_CODE              varchar(10) not null comment '资源编码',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (DEPT_ID, RS_CODE)
);

/*==============================================================*/
/* Table: DP_DEPT_SOC_PRIV                                      */
/*==============================================================*/
create table DP_DEPT_SOC_PRIV
(
   DEPT_ID              char(6) not null comment '部门编码',
   SOC_NAME             varchar(20) not null comment '数据源名称',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (DEPT_ID, SOC_NAME)
);

/*==============================================================*/
/* Table: DP_DEPT_SQL_PRIV                                      */
/*==============================================================*/
create table DP_DEPT_SQL_PRIV
(
   DEPT_ID              char(6) not null comment '部门编码',
   SOC_NAME             varchar(20) not null comment '数据源名称',
   TBL_NAME             varchar(50) not null comment '表名',
   INS_PRIV_FLAG        int not null default 0 comment 'INSERT权限',
   DEL_PRIV_FLAG        int not null default 0 comment 'DELETE权限',
   UPD_PRIV_FLAG        int not null default 0 comment 'UPDATE权限',
   SEL_PRIV_FLAG        int not null default 0 comment 'SELECT权限',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (DEPT_ID, SOC_NAME, TBL_NAME)
);

/*==============================================================*/
/* Table: DT_SOURCE                                             */
/*==============================================================*/
create table DT_SOURCE
(
   SOC_NAME             varchar(20) not null comment '数据源名称',
   SOC_IP               varchar(20) comment 'IP地址',
   SOC_PORT             int not null default 0 comment '端口号',
   PROTOCOL_TYPE        int not null default 0 comment '协议类型',
   REMOTE_UNAME         varchar(20) comment '服务器登陆用户名',
   REMOTE_PASSWD        varchar(32) comment '服务器登陆密码',
   KEY_REMOTE_PASSWD    varchar(32) not null comment '密钥',
   BK_TIMEOUT           bigint not null default 0 comment '超时时间',
   JDBC_DRV             varchar(50) comment 'Jdbc_driver',
   JDBC_URL             varchar(100) comment 'Jdbc_url',
   JDBC_SCHEMA          varchar(50) comment 'Jdbc_schema',
   CVT_TYPE             int not null default 0 comment '转码方式',
   FTP_LOCAL_SCRIPT     varchar(50) comment '上下传脚本',
   CVT_LOCAL_SCRIPT     varchar(50) comment '转码脚本',
   SOC_DOMAIN           varchar(20) comment '数据源域名',
   SOC_BK_DESC          varchar(100) comment '数据源描述',
   SOC_PARAMS           varchar(500) comment '数据源参数',
   USER_ROOT_PATH       varchar(200) comment '用户根路径',
   BACKUP_FLD           varchar(50) comment '备用字段',
   RCD_STATE            int not null default 0 comment '记录状态',
   ENVIRONMENT_VARIABLES varchar(500) comment '环境参数',
   ENCODING_TYPE        varchar(20) comment '编码格式',
   primary key (SOC_NAME)
);

/*==============================================================*/
/* Table: LG_LOG_DOWN                                           */
/*==============================================================*/
create table LG_LOG_DOWN
(
   LOG_ROOT_PATH        varchar(200) not null comment '日志文件路径',
   LOG_FILE_NAME        varchar(50) not null comment '日志文件名称',
   START_BK_DATE        date comment '日志起始日期',
   END_BK_DATE          date comment '日志截止日期',
   USER_ID              varchar(20) comment '用户名',
   CRT_BK_DATE          date comment '生成日志日期',
   CRT_BK_TIME          time comment '生成日志时间',
   BACKUP_FLD           varchar(50) comment '备用',
   primary key (LOG_ROOT_PATH, LOG_FILE_NAME)
);

/*==============================================================*/
/* Table: LG_LOG_LABEL                                          */
/*==============================================================*/
create table LG_LOG_LABEL
(
   WORK_SEQ             varchar(17) not null comment '任务流水号',
   USER_ID              varchar(20) not null comment '用户名',
   LOG_LABEL            int not null default 0 comment '日志标记',
   LABEL_BK_DATE        date comment '标记日期',
   LABEL_BK_TIME        time comment '标记时间',
   BACKUP_FLD           varchar(50) comment '备用',
   primary key (WORK_SEQ, USER_ID)
);

/*==============================================================*/
/* Table: LG_LOG_MF                                             */
/*==============================================================*/
create table LG_LOG_MF
(
   WORK_SEQ             varchar(17) not null comment '任务流水号',
   ORG_CHANNEL_CODE     char(2) comment '发起渠道',
   ORG_TERM_NO          varchar(40) comment '发起终端',
   ORG_WORK_CODE        varchar(10) comment '发起任务编码',
   ORG_SRV_NAME         varchar(50) comment '发起服务名',
   ORG_SRV_BK_DESC      varchar(100) comment '发起服务描述',
   ORG_RS_CODE          varchar(10) comment '发起资源编码',
   ORG_ARY_SOCNAME      varchar(100) comment '发起数据源数组',
   PEND_WORK_SEQ        varchar(17) comment '待处理流水号',
   PEND_WORK_CODE       varchar(10) comment '待处理任务编码',
   PEND_SRV_NAME        varchar(50) comment '待处理服务名称',
   PEND_RS_CODE         varchar(10) comment '待处理资源编码',
   PEND_ARY_SOCNAME     varchar(100) comment '待处理数据源数组',
   PENDWK_BK_EXPL       varchar(500) comment '待处理任务说明',
   PBL_CODE             varchar(20) comment '问题单编码',
   SR_YN_FLAG           int not null default 0 comment '成功标准',
   CRT_USER_ID          varchar(20) comment '创建用户',
   CRT_USER_CN_NAME     varchar(50) comment '用户中文名',
   CRT_DEPT_ID          char(6) comment '创建部门',
   CRT_DEPT_CN_NAME     varchar(50) comment '部门中文名',
   CRT_BK_DATE          date comment '创建日期',
   CRT_BK_TIME          time comment '创建时间',
   LOG_TXT              varchar(1000) comment '日志内容',
   LOG_LEVEL            int not null default 0 comment '日志级别',
   BACKUP_FLD           varchar(50) comment '备用',
   primary key (WORK_SEQ)
);

/*==============================================================*/
/* Table: MG_MSG                                                */
/*==============================================================*/
create table MG_MSG
(
   WORK_SEQ             varchar(17) not null comment '消息流水号',
   MSG_TITLE            varchar(200) comment '消息标题',
   MSG_TEXT             varchar(1000) comment '消息正文',
   MSG_TYPE             int not null default 0 comment '消息类型',
   FILE_PATH            varchar(200) comment '文件路径',
   FIRST_BK_FNAME       varchar(50) comment '文件名1',
   SECD_BK_FNAME        varchar(50) comment '文件名2',
   THIRD_BK_FNAME       varchar(50) comment '文件名3',
   BACKUP_FLD           varchar(50) comment '备用字段',
   CRT_USER_ID          varchar(20) comment '创建用户',
   CRT_BK_DATE          date comment '创建日期',
   CRT_BK_TIME          time comment '创建时间',
   DEL_YN_FLAG          int not null default 0 comment '删除状态',
   RCD_STATE            int not null default 0 comment '记录状态',
   primary key (WORK_SEQ)
);

/*==============================================================*/
/* Table: MG_MSG_USER                                           */
/*==============================================================*/
create table MG_MSG_USER
(
   WORK_SEQ             varchar(17) not null comment '消息流水号',
   RC_USER_ID           varchar(20) not null comment '接收用户',
   RC_FLAG              int not null default 0 comment '接收状态',
   ATTENT_YN_FLAG       int not null default 0 comment '关注状态',
   RC_BK_DATE           date comment '接收日期',
   RC_BK_TIME           time comment '接收时间',
   RCD_STATE            int not null default 0 comment '记录状态',
   primary key (WORK_SEQ, RC_USER_ID)
);

/*==============================================================*/
/* Table: RS_OPT                                                */
/*==============================================================*/
create table RS_OPT
(
   OPT_CODE             varchar(12) not null comment '操作编码',
   BL_RS_CODE           varchar(10) comment '所属资源编码',
   OPT_BK_SEQ           bigint not null default 0 comment '操作序号',
   OPT_NAME             varchar(50) comment '操作名',
   OPT_BK_EXPL          varchar(500) comment '操作说明',
   DIS_EXPRESS          varchar(200) comment '禁用表达式',
   CRT_USER_ID          varchar(20) comment '创建用户',
   CRT_BK_DATE          date comment '创建日期',
   CRT_BK_TIME          time comment '创建时间',
   BACKUP_FLD           varchar(50) comment '备用字段',
   RCD_STATE            int not null default 0 comment '记录状态',
   primary key (OPT_CODE)
);

/*==============================================================*/
/* Table: RS_RES                                                */
/*==============================================================*/
create table RS_RES
(
   RS_CODE              varchar(10) not null comment '资源编码',
   SUPER_RS_CODE        varchar(10) comment '上级资源编码',
   BL_RS_CODE           varchar(10) comment '所属一级资源编码',
   RS_FUN_TYPE          int not null default 0 comment '资源类型',
   RS_CN_NAME           varchar(50) comment '资源名称',
   RS_BK_DESC           varchar(100) comment '资源描述',
   RS_URL               varchar(200) comment '资源地址',
   RSIM_URL             varchar(200) comment '资源图标地址',
   RS_LEVEL             int not null default 0 comment '资源级别',
   GHEIGHT_BK_PIXEL     varchar(6) comment '高度',
   PWIDTH_BK_PIXEL      varchar(6) comment '宽度',
   TITLE_ABLE           int not null default 0 comment '是否有标题',
   PUBLIC_YN_FLAG       int not null default 0 comment '是否公开',
   OPEN_TYPE            int not null default 0 comment '开放类型',
   SORT_KEY             int not null default 0 comment '排序键',
   RCD_STATE            int not null default 0 comment '记录状态',
   primary key (RS_CODE)
);

/*==============================================================*/
/* Table: RT_SVC_EXE                                            */
/*==============================================================*/
create table RT_SVC_EXE
(
   WORK_SEQ             varchar(17) not null comment '任务流水号',
   SOC_NAME             varchar(20) comment '数据源名称',
   START_BK_DATE        date comment '开始日期',
   START_BK_TIME        time comment '开始时间',
   END_BK_DATE          date comment '结束日期',
   END_BK_TIME          time comment '结束时间',
   CUR_PROC_STATE       int not null default 0 comment '当前状态',
   PROC_NAME            varchar(20) comment '进程名称',
   PROC_NUM             varchar(10) comment '进程号',
   BL_USER_ID           varchar(20) comment '进程所属用户',
   RUN_CMD_STR          varchar(500) comment '执行程序',
   ORG_USER_ID          varchar(20) comment '提交用户',
   RS_BK_TEXT           varchar(1000) comment '运行结果',
   ONE_BACKUP_FLD       varchar(50) comment '备用字段1',
   TWO_BACKUP_FLD       varchar(50) comment '备用字段2',
   THREE_BACKUP_FLD     varchar(50) comment '备用字段3',
   primary key (WORK_SEQ)
);

/*==============================================================*/
/* Table: SV_REMOTE_SRV                                         */
/*==============================================================*/
create table SV_REMOTE_SRV
(
   SRV_NAME             varchar(50) not null comment '服务名称',
   DEAL_SEQ             int not null default 0 comment '处理序号',
   REMOTE_SRV_NAME      varchar(50) comment '目标主机名称',
   SRV_TYPE             varchar(10) comment '目标主机服务类型',
   SRV_ROOT_PATH        varchar(200) comment '服务路径',
   primary key (SRV_NAME, DEAL_SEQ)
);

/*==============================================================*/
/* Table: SV_SRV                                                */
/*==============================================================*/
create table SV_SRV
(
   SRV_NAME             varchar(50) not null comment '服务名称',
   SUP_SRVG_CODE        char(2) comment '所属服务组编码',
   SRV_BK_DESC          varchar(100) comment '服务描述',
   SRV_FUN_TYPE         int not null default 0 comment '服务类型',
   SRV_CLASS_NAME       varchar(100) comment '服务类名',
   SRV_METHOD_NAME      varchar(50) comment '服务方法名',
   CHECK_FLAG           int not null default 0 comment '是否复核',
   AUTH_FLAG            int not null default 0 comment '是否授权',
   SOC_FLAG             int not null default 0 comment '是否定义数据源',
   SALLOW_FLAG          int not null default 0 comment '服务允许标志',
   LOG_LEVEL            int not null default 0 comment '日志级别',
   RCD_STATE            int not null default 0 comment '记录状态',
   APROV_TYPE           int not null default 0 comment '审批展示类型',
   CUSTOM_RS_CODE       varchar(10) comment '定制页面资源编码',
   primary key (SRV_NAME)
);

/*==============================================================*/
/* Table: SV_SRVG                                               */
/*==============================================================*/
create table SV_SRVG
(
   SRVG_CODE            char(2) not null comment '服务组编码',
   SRVG_CN_NAME         varchar(50) comment '服务组名称',
   SRVG_BK_DESC         varchar(100) comment '服务组描述',
   SRVG_FUN_TYPE        int not null default 0 comment '服务组类型',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (SRVG_CODE)
);

/*==============================================================*/
/* Table: SV_SRV_AUTH                                           */
/*==============================================================*/
create table SV_SRV_AUTH
(
   AUTH_DEPT_ID         char(6) not null comment '配置部门编码',
   SRV_NAME             varchar(50) not null comment '服务名称',
   AUTH_SEQ             int not null default 0 comment '授权序号',
   AUTH_TYPE            int not null default 0 comment '授权类型',
   AUTH_APROV_CATEGORY  int not null default 0 comment '审批类别',
   AUTH_DPRL_CODE       char(8) comment '授权部门角色',
   ROLE_CN_NAME         varchar(50) comment '角色描述',
   SUPER_FLAG           int not null default 0 comment '是否上级配置',
   primary key (AUTH_DEPT_ID, SRV_NAME, AUTH_SEQ)
);

/*==============================================================*/
/* Table: SV_SRV_CHECK                                          */
/*==============================================================*/
create table SV_SRV_CHECK
(
   CHECK_DEPT_ID        char(6) not null comment '配置部门编码',
   SRV_NAME             varchar(50) not null comment '服务名称',
   CHECK_SEQ            int not null default 0 comment '复核序号',
   CHK_APROV_CATEGORY   int not null default 0 comment '审批类别',
   CHK_DPRL_CODE        char(8) comment '复核部门角色',
   ROLE_CN_NAME         varchar(50) comment '角色描述',
   SUPER_FLAG           int not null default 0 comment '是否上级配置',
   primary key (CHECK_DEPT_ID, SRV_NAME, CHECK_SEQ)
);

/*==============================================================*/
/* Table: SV_SRV_SOC                                            */
/*==============================================================*/
create table SV_SRV_SOC
(
   SRV_NAME             varchar(50) not null comment '服务名称',
   SOC_NAME             varchar(20) not null comment '数据源名称',
   SOC_SEQ              int not null default 0 comment '数据源顺序号',
   primary key (SRV_NAME, SOC_NAME)
);

/*==============================================================*/
/* Table: TM_TERM                                               */
/*==============================================================*/
create table TM_TERM
(
   TERM_NO              varchar(40) not null comment '终端号',
   TERM_TYPE            int not null default 0 comment '终端类型',
   TERM_BK_DESC         varchar(100) comment '终端说明',
   CRT_USER_ID          varchar(20) comment '创建用户',
   CRT_DEPT_ID          char(6) comment '创建部门',
   CRT_BK_DATE          date comment '创建日期',
   CRT_BK_TIME          time comment '创建时间',
   BACKUP_FLD           varchar(50) comment '备用',
   primary key (TERM_NO)
);

/*==============================================================*/
/* Table: US_DEPT_ROLE                                          */
/*==============================================================*/
create table US_DEPT_ROLE
(
   DPRL_CODE            char(8) not null comment '部门角色编码',
   DEPT_ID              char(6) comment '部门编码',
   ROLE_CODE            char(2) comment '角色编码',
   BK_EXPL              varchar(500) comment '部门角色说明',
   primary key (DPRL_CODE)
);

/*==============================================================*/
/* Table: US_ROLE                                               */
/*==============================================================*/
create table US_ROLE
(
   ROLE_CODE            char(2) not null comment '角色编码',
   ROLE_CN_NAME         varchar(50) comment '角色名称',
   ROLE_TYPE            int not null default 0 comment '角色类型',
   ROLE_BK_DESC         varchar(100) comment '角色说明',
   BACKUP_FLD           varchar(50) comment '备用字段',
   CRT_BK_DATE          date comment '创建日期',
   CRT_BK_TIME          time comment '创建时间',
   CRT_USER_ID          varchar(20) comment '创建用户',
   MODIFY_BK_DATE       date comment '修改日期',
   MODIFY_BK_TIME       time comment '修改时间',
   MODIFY_USER_ID       varchar(20) comment '修改用户',
   DEL_BK_DATE          date comment '删除日期',
   DEL_BK_TIME          time comment '删除时间',
   DEL_USER_ID          varchar(20) comment '删除用户',
   RCD_STATE            int not null default 0 comment '记录状态',
   primary key (ROLE_CODE)
);

/*==============================================================*/
/* Table: US_ROLE_COL_PRIV                                      */
/*==============================================================*/
create table US_ROLE_COL_PRIV
(
   DPRL_CODE            char(8) not null comment '部门角色编码',
   SOC_NAME             varchar(20) not null comment '数据源名称',
   TBL_NAME             varchar(50) not null comment '表名',
   COL_NAME             varchar(50) not null comment '字段名称',
   INS_PRIV_FLAG        int not null default 0 comment 'Insert权限',
   DEL_PRIV_FLAG        int not null default 0 comment 'Delete权限',
   UPD_PRIV_FLAG        int not null default 0 comment 'Update权限',
   SEL_PRIV_FLAG        int not null default 0 comment 'Select权限',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (DPRL_CODE, SOC_NAME, TBL_NAME, COL_NAME)
);

/*==============================================================*/
/* Table: US_ROLE_OPT_PRIV                                      */
/*==============================================================*/
create table US_ROLE_OPT_PRIV
(
   OPT_CODE             varchar(12) not null comment '操作编码',
   DPRL_CODE            char(8) not null comment '部门角色编码',
   PRIV_FLAG            int not null default 0 comment '权限',
   primary key (OPT_CODE, DPRL_CODE)
);

/*==============================================================*/
/* Table: US_ROLE_RS_PRIV                                       */
/*==============================================================*/
create table US_ROLE_RS_PRIV
(
   DPRL_CODE            char(8) not null comment '部门角色编码',
   RS_CODE              varchar(10) not null comment '资源编码',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (DPRL_CODE, RS_CODE)
);

/*==============================================================*/
/* Table: US_ROLE_SOC_PRIV                                      */
/*==============================================================*/
create table US_ROLE_SOC_PRIV
(
   DPRL_CODE            char(8) not null comment '部门角色编码',
   SOC_NAME             varchar(20) not null comment '数据源名称',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (DPRL_CODE, SOC_NAME)
);

/*==============================================================*/
/* Table: US_ROLE_SQL_PRIV                                      */
/*==============================================================*/
create table US_ROLE_SQL_PRIV
(
   DPRL_CODE            char(8) not null comment '部门角色编码',
   SOC_NAME             varchar(20) not null comment '数据源名称',
   TBL_NAME             varchar(50) not null comment '表名',
   INS_PRIV_FLAG        int not null default 0 comment 'Insert权限',
   DEL_PRIV_FLAG        int not null default 0 comment 'Delete权限',
   UPD_PRIV_FLAG        int not null default 0 comment 'Update权限',
   SEL_PRIV_FLAG        int not null default 0 comment 'Select权限',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (DPRL_CODE, SOC_NAME, TBL_NAME)
);

/*==============================================================*/
/* Table: US_USER                                               */
/*==============================================================*/
create table US_USER
(
   USER_ID              varchar(20) not null comment '用户名',
   USER_PASSWD          varchar(32) comment '用户密码',
   PWDEXP_BK_DATE       date comment '密码到期日',
   USER_CN_NAME         varchar(50) comment '用户姓名',
   EMAIL_ADD            varchar(50) comment '邮箱',
   PHONE_NO             varchar(12) comment '电话号码',
   TELLER_NO            varchar(6) comment '柜员号',
   LOGIN_BK_NUM         int not null default 0 comment '用户登录数量',
   BL_DEPT_ID           char(6) comment '所属部门号',
   USER_TYPE            int not null default 0 comment '用户类型',
   FIRST_DEPT_ID        char(6) comment '兼职部门1',
   SECD_DEPT_ID         char(6) comment '兼职部门2',
   THIRD_DEPT_ID        char(6) comment '兼职部门3',
   BACKUP_FLD           varchar(50) comment '备用字段',
   CRT_BK_DATE          date comment '创建日期',
   CRT_BK_TIME          time comment '创建时间',
   CRT_USER_ID          varchar(20) comment '创建用户',
   MODIFY_BK_DATE       date comment '修改日期',
   MODIFY_BK_TIME       time comment '修改时间',
   MODIFY_USER_ID       varchar(20) comment '修改用户',
   DEL_BK_DATE          date comment '删除日期',
   DEL_BK_TIME          time comment '删除时间',
   DEL_USER_ID          varchar(20) comment '删除用户',
   RCD_STATE            int not null default 0 comment '记录状态',
   primary key (USER_ID)
);

/*==============================================================*/
/* Table: US_USER_COL_PRIV                                      */
/*==============================================================*/
create table US_USER_COL_PRIV
(
   USER_ID              varchar(20) not null comment '用户名',
   SOC_NAME             varchar(20) not null comment '数据源名称',
   TBL_NAME             varchar(50) not null comment '表名',
   COL_NAME             varchar(50) not null comment '字段名称',
   PRIV_TYPE            int not null default 0 comment '资源类型',
   INS_PRIV_FLAG        int not null default 0 comment 'Insert权限',
   DEL_PRIV_FLAG        int not null default 0 comment 'Delete权限',
   UPD_PRIV_FLAG        int not null default 0 comment 'Update权限',
   SEL_PRIV_FLAG        int not null default 0 comment 'Select权限',
   AF_FLAG              int not null default 0 comment '允许禁止标志位',
   TEMPSTART_BK_DATETIME timestamp comment '临时权限开始时间',
   TEMPEND_BK_DATETIME  timestamp comment '临时权限结束时间',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (USER_ID, SOC_NAME, TBL_NAME, COL_NAME, PRIV_TYPE)
);

/*==============================================================*/
/* Table: US_USER_CONTACT                                       */
/*==============================================================*/
create table US_USER_CONTACT
(
   USER_ID              varchar(20) not null comment '用户ID',
   CONTACT_USER_ID      varchar(20) not null comment '用户常用联系人',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (USER_ID, CONTACT_USER_ID)
);

/*==============================================================*/
/* Table: US_USER_OPT_PRIV                                      */
/*==============================================================*/
create table US_USER_OPT_PRIV
(
   OPT_CODE             varchar(12) not null comment '操作编码',
   USER_ID              varchar(20) not null comment '用户名',
   PRIV_FLAG            int not null default 0 comment '权限',
   primary key (OPT_CODE, USER_ID)
);

/*==============================================================*/
/* Table: US_USER_ROLE                                          */
/*==============================================================*/
create table US_USER_ROLE
(
   USER_ID              varchar(20) not null comment '用户名',
   DPRL_CODE            char(8) not null comment '部门角色编码',
   USER_BK_WEIGHT       int not null default 0 comment '用户权重',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (USER_ID, DPRL_CODE)
);

/*==============================================================*/
/* Table: US_USER_RS_PRIV                                       */
/*==============================================================*/
create table US_USER_RS_PRIV
(
   USER_ID              varchar(20) not null comment '用户名',
   RS_CODE              varchar(10) not null comment '资源编码',
   PRIV_TYPE            int not null default 0 comment '权限类型',
   AF_FLAG              int not null default 0 comment '允许禁止标志',
   TEMPSTART_BK_DATETIME timestamp comment '临时权限开始时间',
   TEMPEND_BK_DATETIME  timestamp comment '临时权限结束时间',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (USER_ID, RS_CODE, PRIV_TYPE)
);

/*==============================================================*/
/* Table: US_USER_SOC_PRIV                                      */
/*==============================================================*/
create table US_USER_SOC_PRIV
(
   USER_ID              varchar(20) not null comment '用户名',
   SOC_NAME             varchar(20) not null comment '数据源名称',
   PRIV_TYPE            int not null default 0 comment '资源类型',
   AF_FLAG              int not null default 0 comment '允许禁止标志',
   TEMPSTART_BK_DATETIME timestamp comment '临时权限开始时间',
   TEMPEND_BK_DATETIME  timestamp comment '临时权限结束时间',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (USER_ID, SOC_NAME, PRIV_TYPE)
);

/*==============================================================*/
/* Table: US_USER_SQL_PRIV                                      */
/*==============================================================*/
create table US_USER_SQL_PRIV
(
   USER_ID              varchar(20) not null comment '用户名',
   SOC_NAME             varchar(20) not null comment '数据源名称',
   TBL_NAME             varchar(50) not null comment '表名',
   PRIV_TYPE            int not null default 0 comment '资源类型',
   INS_PRIV_FLAG        int not null default 0 comment 'Insert权限',
   DEL_PRIV_FLAG        int not null default 0 comment 'DELETE权限',
   UPD_PRIV_FLAG        int not null default 0 comment 'Update权限',
   SEL_PRIV_FLAG        int not null default 0 comment 'Select权限',
   AF_FLAG              int not null default 0 comment '允许禁止标志',
   TEMPSTART_BK_DATETIME timestamp comment '临时权限开始时间',
   TEMPEND_BK_DATETIME  timestamp comment '临时权限结束时间',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (USER_ID, SOC_NAME, TBL_NAME, PRIV_TYPE)
);

/*==============================================================*/
/* Table: US_USER_TERM                                          */
/*==============================================================*/
create table US_USER_TERM
(
   USER_ID              varchar(20) not null comment '用户名',
   TERM_NO              varchar(40) not null comment '终端号',
   CHANNEL_CODE         char(2) comment '接入渠道',
   DEPT_ID              char(6) comment '部门编码',
   USER_CN_NAME         varchar(50) comment '用户姓名',
   DEPT_CN_NAME         varchar(50) comment '部门名称',
   USE_STATE            int not null default 0 comment '启用状态',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (USER_ID, TERM_NO)
);

/*==============================================================*/
/* Table: WK_DEAL_DETAIL                                        */
/*==============================================================*/
create table WK_DEAL_DETAIL
(
   PEND_WORK_SEQ        varchar(17) not null comment '待处理任务流水号',
   DEAL_SEQ             int not null default 0 comment '处理序号',
   DEAL_TYPE            int not null default 0 comment '处理方式',
   DEAL_RES             int not null default 0 comment '处理结果',
   DEAL_USER_ID         varchar(20) comment '处理人员',
   DEAL_BK_DATE         date comment '处理日期',
   DEAL_BK_TIME         time comment '处理时间',
   DEAL_BK_DESC         varchar(100) comment '处理说明',
   primary key (PEND_WORK_SEQ, DEAL_SEQ)
);

/*==============================================================*/
/* Table: WK_DEAL_STATE                                         */
/*==============================================================*/
create table WK_DEAL_STATE
(
   PEND_WORK_SEQ        varchar(17) not null comment '待处理任务流水号',
   SUBMIT_WORK_SEQ      varchar(17) comment '提交任务流水号',
   PEND_WORK_CODE       varchar(10) comment '待处理任务编码',
   PEND_SRV_NAME        varchar(50) comment '待处理服务名称',
   PEND_RS_CODE         varchar(10) comment '待处理资源编码',
   PEND_ARY_SOCNAME     varchar(100) comment '待处理数据源数组',
   PENDWK_BK_EXPL       varchar(500) comment '待处理任务说明',
   PEND_DEAL_SEQ        int not null default 0 comment '待处理序号',
   PEND_USER_ID         varchar(20) comment '待处理用户',
   PEND_USER_CN_NAME    varchar(50) comment '待处理用户中文名',
   PBL_CODE             varchar(20) comment '问题单编码',
   PROXY_USER_ID        varchar(20) comment '代理用户',
   CRT_USER_ID          varchar(20) not null comment '创建用户',
   CRT_USER_CN_NAME     varchar(50) comment '用户中文名',
   CRT_DEPT_ID          char(6) not null comment '创建部门',
   CRT_DEPT_CN_NAME     varchar(50) comment '部门中文名',
   CRT_BK_DATE          date comment '创建日期',
   CRT_BK_TIME          time comment '创建时间',
   WORKFLOW_STATE       int not null default 0 comment '任务状态',
   BACKUP_FLD           varchar(50) comment '备用',
   RCD_STATE            int not null default 0 comment '记录状态',
   APPLY_BK_EXPL        varchar(500) comment '任务申请说明',
   primary key (PEND_WORK_SEQ)
);

/*==============================================================*/
/* Table: WK_DETAIL_REGISTER                                    */
/*==============================================================*/
create table WK_DETAIL_REGISTER
(
   PEND_WORK_SEQ        varchar(17) not null comment '待处理任务流水号',
   INTE_DETAIL          BINARY(1048576) comment '接口明细信息',
   APROV_TYPE           int not null default 0 comment '审批展示类型',
   CUSTOM_RS_CODE       varchar(10) comment '定制页面资源编码',
   APPLY_HTML           BINARY(1048576) comment '申请页面代码',
   primary key (PEND_WORK_SEQ)
);

/*==============================================================*/
/* Table: WK_WORK_PROCESS                                       */
/*==============================================================*/
create table WK_WORK_PROCESS
(
   PEND_WORK_SEQ        varchar(17) not null comment '待处理任务流水号',
   DEAL_SEQ             int not null default 0 comment '处理序号',
   PEND_USER_ID         varchar(20) comment '审批人',
   PEND_USER_CN_NAME    varchar(50) comment '审批人中文名',
   PEND_TYPE            int not null default 0 comment '审批方式',
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
   FILE_WORK_SEQ        varchar(17) not null comment '文件变更流水号',
   WORK_ID              varchar(14) comment '任务编号',
   CFG_TYPE             int default 0 comment '配置类型',
   SERVER_NAME          varchar(20) comment '服务器名',
   SERVER_IP            varchar(20) comment '服务器IP',
   FOPT_TYPE            int default 0 comment '操作类型',
   FILE_BK_PATH         varchar(200) comment '文件路径',
   FILE_BK_FNAME        varchar(50) comment '文件名',
   FILE_BK_CSUM         varchar(20) comment '文件CSUM',
   DIR_YN_FLAG          int default 0 comment '是否目录标志',
   OPT_STATUS           int default 0 comment '操作状态',
   MODIFY_USER_ID       varchar(20) comment '修改人',
   MODIFY_BK_DATE       date comment '修改日期',
   MODIFY_BK_TIME       time comment '修改时间',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (FILE_WORK_SEQ)
);

alter table BUILD_CONFIGFILE comment '构建任务配置文件变更表';

/*==============================================================*/
/* Table: BUILD_SCRIPT                                          */
/*==============================================================*/
create table BUILD_SCRIPT
(
   WORK_ID              varchar(14) not null comment '任务编号',
   SCRIPT_TYPE          int not null default 0 comment '脚本类型',
   SCIRPT_BK_SEQ        bigint not null default 0 comment '脚本序号',
   SCRIPT_METHOD        int default 0 comment '脚本方式',
   MODULE_ID            varchar(20) comment '组件ID',
   MODULE_CN_NAME       varchar(50) comment '组件中文名',
   SOC_UUID             char(32) comment '数据源UUID',
   MODULE_PARAM_UUID    char(32) comment '组件参数表UUID',
   INSTANCE_ID          varchar(50) comment '实例ID',
   SCRIPT_TEXT          varchar(1000) comment '脚本命令',
   EXE_STATUS           int default 0 comment '执行状态',
   EXE_RESULT           int default 0 comment '执行结果',
   EXELOG_BK_PATH       varchar(200) comment '执行日志',
   EXE_USER_ID          varchar(20) comment '执行人',
   START_BK_TM          timestamp comment '执行开始时间',
   END_BK_TM            timestamp comment '执行结束时间',
   TIME_USED            int default 0 comment '耗时',
   IMPL_TYPE            int default 0 comment '组件执行类型',
   primary key (WORK_ID, SCRIPT_TYPE, SCIRPT_BK_SEQ)
);

alter table BUILD_SCRIPT comment '构建脚本信息表';

/*==============================================================*/
/* Table: BUILD_STEP                                            */
/*==============================================================*/
create table BUILD_STEP
(
   WORK_ID              varchar(14) not null comment '任务编号',
   TEMPLATE_NAME        varchar(50) not null comment '模板名',
   PHASE_ID             int not null default 0 comment '阶段编号',
   PHASE_BK_DESC        varchar(100) comment '阶段描述',
   SOC_UUID             char(32) comment '数据源UUID',
   GEN_YN_FLAG          int default 0 comment '是否生成实例',
   IMPL_TYPE            int default 0 comment '组件执行类型',
   primary key (WORK_ID, TEMPLATE_NAME, PHASE_ID)
);

alter table BUILD_STEP comment '构建阶段表';

/*==============================================================*/
/* Table: CE_ENVIRONMENT                                        */
/*==============================================================*/
create table CE_ENVIRONMENT
(
   ENV_NAME             varchar(20) not null comment '环境名称',
   ENV_CN_NAME          varchar(50) comment '环境简称',
   ENV_BK_DESC          varchar(200) comment '环境描述',
   ENV_TYPE             int not null default 0 comment '环境类型',
   SYS_NAME             char(20) comment '应用系统',
   ELE_TYPE             varchar(20) comment '构成要素',
   DT_RANGE             int default 0 comment '数据范围',
   CREATE_BK_DATE       date comment '创建日期',
   CREATE_BK_TIME       time comment '创建时间',
   CREATE_USER_ID       varchar(20) comment '创建用户',
   MODIFY_BK_DATE       date comment '修改日期',
   MODIFY_BK_TIME       time comment '修改时间',
   MODIFY_USER_ID       varchar(20) comment '修改用户',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (ENV_NAME)
);

alter table CE_ENVIRONMENT comment '环境表';

/*==============================================================*/
/* Table: CE_ENVIRONMENT_SERVER                                 */
/*==============================================================*/
create table CE_ENVIRONMENT_SERVER
(
   ENV_NAME             varchar(20) not null comment '环境名称',
   SERVER_TYPE          int not null default 0 comment '服务器类型',
   SERVER_NAME          varchar(20) not null comment '服务器名称',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (ENV_NAME, SERVER_TYPE, SERVER_NAME)
);

alter table CE_ENVIRONMENT_SERVER comment '环境服务器表';

/*==============================================================*/
/* Table: CE_PROJECT                                            */
/*==============================================================*/
create table CE_PROJECT
(
   PROJECT_NAME         varchar(100) not null comment '项目编号',
   PROJECT_SHORT_NAME   varchar(20) comment '项目简称',
   PROJECT_BK_DESC      varchar(100) comment '项目描述',
   SYS_NAME             char(20) comment '应用系统',
   CREATE_BK_DATE       date comment '创建日期',
   CREATE_BK_TIME       time comment '创建时间',
   CREATE_USER_ID       varchar(20) comment '创建用户',
   MODIFY_BK_DATE       date comment '修改日期',
   MODIFY_BK_TIME       time comment '修改时间',
   MODIFY_USER_ID       varchar(20) comment '修改用户',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (PROJECT_NAME)
);

alter table CE_PROJECT comment '项目表';

/*==============================================================*/
/* Table: CE_SERVER                                             */
/*==============================================================*/
create table CE_SERVER
(
   SERVER_NAME          varchar(20) not null comment '服务器名称',
   SERVER_CN_NAME       varchar(50) comment '服务器简称',
   SERVER_DESC          varchar(200) comment '服务器描述',
   SERVER_IP            varchar(20) comment '服务器地址',
   SERVER_OS            int not null default 0 comment '操作系统',
   OS_SBK_VER           varchar(20) comment '操作系统版本',
   SERVER_DB            varchar(200) comment '数据库类型',
   SERVER_MID_WARE      varchar(20) comment '中间件',
   CREATE_BK_DATE       date comment '创建日期',
   CREATE_BK_TIME       time comment '创建时间',
   CREATE_USER_ID       varchar(20) comment '创建用户',
   MODIFY_BK_DATE       date comment '修改日期',
   MODIFY_BK_TIME       time comment '修改时间',
   MODIFY_USER_ID       varchar(20) comment '修改用户',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (SERVER_NAME)
);

alter table CE_SERVER comment '服务器表';

/*==============================================================*/
/* Table: CE_SERVER_DS                                          */
/*==============================================================*/
create table CE_SERVER_DS
(
   SERVER_NAME          varchar(20) not null comment '服务器名称',
   SOC_NAME             varchar(20) not null comment '数据源名称',
   APPLY_TYPE           varchar(20) comment '数据源用途',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (SERVER_NAME, SOC_NAME)
);

alter table CE_SERVER_DS comment '服务器数据源表';

/*==============================================================*/
/* Table: CE_SYSTEM                                             */
/*==============================================================*/
create table CE_SYSTEM
(
   SYS_NAME             char(20) not null comment '应用系统名称',
   SYS_CN_NAME          varchar(50) comment '应用系统简称',
   SYS_TYPE             int comment '应用系统类型',
   SYS_BK_DESC          varchar(200) comment '应用系统描述',
   CREATE_BK_DATE       date comment '创建日期',
   CREATE_BK_TIME       time comment '创建时间',
   CREATE_USER_ID       varchar(20) comment '创建用户',
   MODIFY_BK_DATE       date comment '修改日期',
   MODIFY_BK_TIME       time comment '修改时间',
   MODIFY_USER_ID       varchar(20) comment '修改用户',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (SYS_NAME)
);

alter table CE_SYSTEM comment '应用系统表';

/*==============================================================*/
/* Table: CE_SYSTEM_CFG                                         */
/*==============================================================*/
create table CE_SYSTEM_CFG
(
   SYS_NAME             char(20) not null comment '应用系统名称',
   CFG_BK_FNAME         varchar(50) not null comment '文件名',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (SYS_NAME, CFG_BK_FNAME)
);

alter table CE_SYSTEM_CFG comment '应用系统配置文件表';

/*==============================================================*/
/* Table: CE_SYSTEM_TEMPLATE                                    */
/*==============================================================*/
create table CE_SYSTEM_TEMPLATE
(
   SYS_NAME             char(20) not null comment '应用系统名称',
   TEMPLATE_TYPE        int not null comment '模板类型',
   TEMPLATE_NAME        varchar(50) not null comment '模板名称',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (SYS_NAME, TEMPLATE_TYPE, TEMPLATE_NAME)
);

alter table CE_SYSTEM_TEMPLATE comment '应用系统模板表';

/*==============================================================*/
/* Table: DP_DEPT_ENV_PRIV                                      */
/*==============================================================*/
create table DP_DEPT_ENV_PRIV
(
   DEPT_ID              char(6) not null comment '部门编码',
   ENV_NAME             varchar(20) not null comment '环境名称',
   SYS_NAME             char(20) comment '应用系统',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (DEPT_ID, ENV_NAME)
);

alter table DP_DEPT_ENV_PRIV comment '部门应用环境权限表';

/*==============================================================*/
/* Table: ENV_BUILD_TASK                                        */
/*==============================================================*/
create table ENV_BUILD_TASK
(
   WORK_ID              varchar(14) not null comment '任务编号',
   TEMPLATE_NAME        varchar(50) comment '模板名',
   TEMPLATE_PARAM_UUID  char(32) comment '模板参数UUID',
   VER_SOC_UUID         char(32) comment '版本机数据源UUID',
   EXELOG_BK_PATH       varchar(200) comment '构建日志',
   BUILD_STEP_ID        int default 0 comment '构建步骤数',
   TASK_STATUS          int default 0 comment '任务状态',
   EXE_RESULT           int default 0 comment '执行结果',
   EXE_USER_ID          varchar(20) comment '执行人',
   START_BK_TM          timestamp comment '执行开始时间',
   END_BK_TM            timestamp comment '执行结束时间',
   primary key (WORK_ID)
);

alter table ENV_BUILD_TASK comment '构建任务扩展表';

/*==============================================================*/
/* Table: ENV_CONFIGFILE_MOD                                    */
/*==============================================================*/
create table ENV_CONFIGFILE_MOD
(
   FILE_WORK_SEQ        varchar(17) not null comment '文件变更流水号',
   BATCH_NO             varchar(12) comment '批次号',
   ENV_NAME             varchar(20) comment '环境名称',
   SERVER_NAME          varchar(20) comment '服务器名',
   SERVER_IP            varchar(20) not null comment '服务器IP',
   FOPT_TYPE            int not null default 0 comment '操作类型',
   FILE_BK_PATH         varchar(200) comment '文件路径',
   FILE_BK_FNAME        varchar(50) comment '文件名',
   FILE_BK_CSUM         varchar(20) comment '文件CSUM',
   DIR_YN_FLAG          int not null default 0 comment '是否目录标志',
   OPT_STATUS           int not null default 0 comment '操作状态',
   MODIFY_USER_ID       varchar(20) comment '修改人',
   MODIFY_BK_DATE       date comment '修改日期',
   MODIFY_BK_TIME       time comment '修改时间',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (FILE_WORK_SEQ)
);

alter table ENV_CONFIGFILE_MOD comment '环境配置文件变更表';

/*==============================================================*/
/* Table: ENV_TAG_STORAGE                                       */
/*==============================================================*/
create table ENV_TAG_STORAGE
(
   STORAGE_ID           varchar(14) not null comment '入库编号',
   STORAGE_BK_DESC      varchar(200) comment '入库描述',
   ENV_NAME             varchar(20) comment '环境名称',
   PROJECT_NAME         varchar(100) comment '项目编号',
   INSTANCE_ID          varchar(50) comment '实例ID',
   EXE_SOC_UUID         char(32) comment '执行数据源UUID',
   TAR_VERSOC_UUID      char(32) comment '目标版本数据源UUID',
   STORAGE_LIST_UUID    char(32) comment '入库清单UUID',
   INTE_VER_NUM         varchar(50) comment '集成版本号',
   TAR_VER_NUM          varchar(50) comment '目标版本号',
   STORAGE_STATUS       int default 0 comment '入库状态',
   CRT_USER_ID          varchar(20) comment '创建人',
   STORAGE_RESULT       int default 0 comment '入库结果',
   CRT_BK_DATE          date comment '创建日期',
   CRT_BK_TIME          time comment '创建时间',
   STORAGE_USER_ID      varchar(20) comment '入库人',
   START_BK_TM          timestamp comment '入库开始时间',
   END_BK_TM            timestamp comment '入库结束时间',
   LOG_BK_PATH          varchar(200) comment '入库日志全路径',
   TIME_USED            int default 0 comment '耗时',
   primary key (STORAGE_ID)
);

alter table ENV_TAG_STORAGE comment '目标入库表';

/*==============================================================*/
/* Table: ENV_TASK                                              */
/*==============================================================*/
create table ENV_TASK
(
   WORK_ID              varchar(14) not null comment '任务编号',
   TASK_TYPE            int default 0 comment '任务类型',
   TASK_BK_DESC         varchar(200) comment '任务描述',
   ROL_WORK_ID          varchar(14) comment '回退任务编号',
   ENV_NAME             varchar(20) comment '所属环境',
   PROJECT_NAME         varchar(100) comment '所属项目',
   PROG_ID              varchar(14) comment '方案编号',
   INSTANCE_ID          varchar(50) comment '实例ID',
   EXE_METHOD           int default 0 comment '执行动作',
   TAGPAC_LIST_UUID     char(32) comment '集成目标包清单UUID',
   PUB_LIST_UUID        char(32) comment '发布清单UUID',
   CODE_VER_NUM         varchar(50) comment '源码版本机版本号',
   TARGET_VER_NUM       varchar(50) comment '目标版本机版本号',
   VERCODE_VER_NUM      varchar(50) comment '版本环境源码版本号',
   VERTARGET_VER_NUM    varchar(50) comment '版本环境目标版本号',
   TASK_STATUS          int default 0 comment '任务状态',
   EXE_RESULT           int default 0 comment '执行结果',
   EXELOG_BK_PATH       varchar(200) comment '执行日志',
   CRT_USER_ID          varchar(20) comment '创建人',
   CRT_BK_DATE          date comment '创建日期',
   CRT_BK_TIME          time comment '创建时间',
   EXE_USER_ID          varchar(20) comment '执行人',
   START_BK_TM          timestamp comment '执行开始时间',
   END_BK_TM            timestamp comment '执行结束时间',
   TIME_USED            int not null default 0 comment '耗时',
   primary key (WORK_ID)
);

alter table ENV_TASK comment '环境任务表';

/*==============================================================*/
/* Table: INSTANCE_EXE                                          */
/*==============================================================*/
create table INSTANCE_EXE
(
   INSTANCE_ID          varchar(50) not null comment '执行实例ID',
   INST_BK_NO           int not null default 0 comment '实例阶段号',
   TPL_BK_NO            int default 0 comment '模板组件阶段号',
   STEP_BK_DESC         varchar(100) comment '阶段描述',
   SERVER_NAME          varchar(20) comment '执行服务器名',
   SOC_NAME             varchar(20) comment '执行数据源名',
   EXE_STATUS           int default 0 comment '执行状态',
   EXE_RESULT           int default 0 comment '执行结果',
   EXEC_TEXT            varchar(500) comment '执行信息',
   START_BK_TM          timestamp comment '执行开始时间',
   END_BK_TM            timestamp comment '执行结束时间',
   TIME_USED            int default 0 comment '耗时',
   primary key (INSTANCE_ID, INST_BK_NO)
);

alter table INSTANCE_EXE comment '实例执行信息表';

/*==============================================================*/
/* Table: MO_MODULE                                             */
/*==============================================================*/
create table MO_MODULE
(
   MODULE_ID            varchar(20) not null comment '组件ID',
   PUBLISH_STATE        int default 0 comment '发布状态',
   CRT_USER_ID          varchar(20) comment '创建人',
   CRT_BK_DATE          date comment '创建日期',
   CRT_BK_TIME          time comment '创建时间',
   MODIFY_USER_ID       varchar(20) comment '修改人',
   MODIFY_BK_DATE       date comment '修改日期',
   MODIFY_BK_TIME       time comment '修改时间',
   BACKUP_FLD           varchar(50) comment '备用字段',
   IMPL_TYPE            int default 0 comment '组件实现类型',
   SCRIPT_SOURCE        int comment '脚本来源',
   MODULE_PURPOSE       int comment '组件用途',
   MODULE_TYPE          int not null default 0 comment '组件类型',
   MODULE_BK_DESC       varchar(500) comment '组件描述',
   MODULE_CN_NAME       varchar(50) comment '组件中文名',
   primary key (MODULE_ID)
);

alter table MO_MODULE comment '组件信息表';

/*==============================================================*/
/* Table: MO_MODULE_QUOTE                                       */
/*==============================================================*/
create table MO_MODULE_QUOTE
(
   MODULE_ID            varchar(20) not null comment '组件ID',
   QUOTE_MODULE_ID      varchar(20) not null comment '引用组件ID',
   QUOTE_MODULE_TYPE    int default 0 comment '引用组件类型',
   primary key (MODULE_ID, QUOTE_MODULE_ID)
);

alter table MO_MODULE_QUOTE comment '组件引用表';

/*==============================================================*/
/* Table: MO_COMPONENT                                          */
/*==============================================================*/
create table MO_COMPONENT
(
   COMPONENT_ID         varchar(20) not null comment '组件ID',
   COMPONENT_CN_NAME    varchar(50) comment '组件中文名',
   COMPONENT_BK_DESC    varchar(500) comment '组件描述',
   MODULE_TYPE          int default 0 comment '组件类型',
   COMPONENT_PURPOSES   varchar(10) comment '组件用途',
   COMPONENT_SOURCE     int comment '脚本来源',
   IMPL_TYPE            int default 0 comment '组件实现类型',
   LANGUAGE_VERSION     varchar(50) comment '语言版本',
   PUBLISH_STATE        int default 0 comment '发布状态',
   CRT_USER_ID          varchar(20) comment '创建人',
   CRT_BK_DATE          date comment '创建日期',
   CRT_BK_TIME          time comment '创建时间',
   MODIFY_USER_ID       varchar(20) comment '修改人',
   MODIFY_BK_DATE       date comment '修改日期',
   MODIFY_BK_TIME       time comment '修改时间',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (COMPONENT_ID)
);

alter table MO_COMPONENT comment '组件信息表';

/*==============================================================*/
/* Table: MO_TEMPLATE                                           */
/*==============================================================*/
create table MO_TEMPLATE
(
   TEMPLATE_NAME        varchar(50) not null comment '模版名称',
   TEMPLATE_FORMATE     int not null default 0 comment '模板格式',
   TEMPLATE_CN_NAME     varchar(50) comment '模版中文名',
   TP_CLASS_NAME        varchar(100) comment '模板类名',
   SCRIPT_FILE_PATH     varchar(200) comment '模版路径',
   REF_MODULE_ID        varchar(20) comment '引用ID',
   TEMPLATE_TYPE        int comment '模版类型',
   OPERATING_SYSTEM     varchar(20) comment '操作系统',
   PUBLISH_STATE        int not null default 0 comment '发布状态',
   TEMPLATE_BK_DESC     varchar(100) comment '模版描述',
   CRT_BK_DATE          date comment '创建日期',
   CRT_BK_TIME          time comment '创建时间',
   CRT_USER_ID          varchar(20) comment '创建用户',
   MODIFY_BK_DATE       date comment '修改日期',
   MODIFY_BK_TIME       time comment '修改时间',
   MODIFY_USER_ID       varchar(20) comment '修改用户',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (TEMPLATE_NAME)
);

alter table MO_TEMPLATE comment '投产模版表';

/*==============================================================*/
/* Table: PG_INTE_STEP                                          */
/*==============================================================*/
create table PG_INTE_STEP
(
   PROG_ID              varchar(14) not null comment '方案编号',
   STEP_ID              int not null default 0 comment '步骤编号',
   STEP_EXPL            varchar(40) comment '步骤说明',
   STEP_TYPE            int default 0 comment '步骤类型',
   SOC_UUID             char(32) comment '数据源UUID',
   STEP_BK_SCRIPT       varchar(1000) comment '脚本',
   COMPILE_TYPE         int default 0 comment '编译类型',
   COMPLIE_BK_PATH      varchar(200) comment '编译路径',
   ENV_VARIABLE         varchar(500) comment '环境变量',
   COMPILE_PARAM        varchar(200) comment '编译参数',
   STORAGE_LIST_UUID    char(32) comment '入库清单UUID',
   STORAGE_BK_PATH      varchar(200) comment '打包根路径',
   primary key (PROG_ID, STEP_ID)
);

alter table PG_INTE_STEP comment '集成方案步骤表';

/*==============================================================*/
/* Table: PG_PROGRAM                                            */
/*==============================================================*/
create table PG_PROGRAM
(
   PROG_ID              varchar(14) not null comment '方案编号',
   PROG_NAME            varchar(50) comment '方案名称',
   ENV_NAME             varchar(20) comment '所属环境',
   PROG_BK_DESC         varchar(100) comment '方案描述',
   PROG_TYPE            int not null default 0 comment '方案类型',
   IS_PUBLISH           int not null default 0 comment '是否发布',
   BACKUP_FLD           varchar(50) comment '备用字段',
   CRT_USER_ID          varchar(20) comment '创建人',
   CRT_BK_DATE          date comment '创建日期',
   CRT_BK_TIME          time comment '创建时间',
   primary key (PROG_ID)
);

alter table PG_PROGRAM comment '环境方案表';

/*==============================================================*/
/* Table: PG_RELE                                               */
/*==============================================================*/
create table PG_RELE
(
   PROG_ID              varchar(14) not null comment '方案编号',
   PUB_TEMPLATE_NAME    varchar(50) comment '发布模板',
   PUBL_PARAM_UUID      char(32) comment '发布模板参数UUID',
   ROL_TEMPLATE_NAME    varchar(50) comment '回退模板',
   ROLL_PARAM_UUID      char(32) comment '回退模板参数UUID',
   VER_SOC_UUID         char(32) comment '版本机数据源UUID',
   primary key (PROG_ID)
);

alter table PG_RELE comment '发布方案扩展表';

/*==============================================================*/
/* Table: PG_RELE_STEP                                          */
/*==============================================================*/
create table PG_RELE_STEP
(
   PROG_ID              varchar(14) not null comment '方案编号',
   TEMPLATE_NAME        varchar(50) not null comment '模板名',
   PHASE_ID             int not null default 0 comment '阶段编号',
   PHASE_BK_DESC        varchar(100) comment '阶段描述',
   SOC_UUID             char(32) not null comment '数据源UUID',
   GEN_YN_FLAG          int not null default 0 comment '是否生成实例',
   IMPL_TYPE            int not null default 0 comment '实现类型',
   primary key (PROG_ID, TEMPLATE_NAME, PHASE_ID)
);

alter table PG_RELE_STEP comment '发布方案阶段表';

/*==============================================================*/
/* Table: US_ROLE_ENV_PRIV                                      */
/*==============================================================*/
create table US_ROLE_ENV_PRIV
(
   DPRL_CODE            char(8) not null comment '部门角色编码',
   ENV_NAME             varchar(20) not null comment '环境名称',
   SYS_NAME             char(20) comment '应用系统',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (DPRL_CODE, ENV_NAME)
);

alter table US_ROLE_ENV_PRIV comment '部门角色应用环境权限表';

/*==============================================================*/
/* Table: US_USER_ENV_PRIV                                      */
/*==============================================================*/
create table US_USER_ENV_PRIV
(
   USER_ID              varchar(20) not null comment '用户名',
   ENV_NAME             varchar(20) not null comment '环境名称',
   PRIV_TYPE            int not null default 0 comment '资源类型',
   SYS_NAME             char(20) comment '应用系统',
   AF_FLAG              int not null default 0 comment '允许禁止标志',
   TEMPSTART_BK_DATETIME timestamp comment '临时权限开始时间',
   TEMPEND_BK_DATETIME  timestamp comment '临时权限结束时间',
   BACKUP_FLD           varchar(50) comment '备用字段',
   primary key (USER_ID, ENV_NAME, PRIV_TYPE)
);

alter table US_USER_ENV_PRIV comment '用户应用环境权限表';

/*==============================================================*/
/* Table: UU_FILELIST                                           */
/*==============================================================*/
create table UU_FILELIST
(
   LIST_UUID            char(32) not null comment '清单UUID',
   FILE_WORK_SEQ        varchar(17) not null comment '文件流水号',
   FILE_PATH            varchar(200) comment '文件目录',
   FILE_NAME            varchar(50) comment '文件名',
   FILE_TYPE            varchar(10) comment '文件类型',
   FILE_SIZE            int default 0 comment '文件大小',
   PACKAGE_NAME         varchar(50) comment '所属包名',
   SERVER_NAME          varchar(20) comment '所属服务器名',
   STORAGE_BK_PATH      varchar(200) comment '打包根路径',
   TARGET_RELATIVE_PATH varchar(200) comment '目标包相对路径',
   primary key (LIST_UUID, FILE_WORK_SEQ)
);

alter table UU_FILELIST comment '文件清单表';

/*==============================================================*/
/* Table: UU_PARAM                                              */
/*==============================================================*/
create table UU_PARAM
(
   PARAM_UUID           char(32) not null comment '参数UUID',
   PARAM_NAME           varchar(50) not null comment '参数名',
   PARAM_TYPE           int comment '参数类型',
   PARAM_VALUE          varchar(500) comment '参数值',
   PARAM_CN_NAME        varchar(50) comment '参数中文名',
   PARAM_BK_DESC        varchar(100) comment '参数描述',
   PARAM_MODIFY_FLAG    int default 0 comment '是否可修改',
   BACKUP_FLD           varchar(50) comment '备用字段',
   PARAM_GROUP          varchar(20) comment '参数分组',
   PHASE_NO             int default 0 comment '阶段号',
   primary key (PARAM_UUID, PARAM_NAME)
);

alter table UU_PARAM comment '模板组件参数表';

/*==============================================================*/
/* Table: UU_SOC                                                */
/*==============================================================*/
create table UU_SOC
(
   SOC_UUID             char(32) not null comment '数据源UUID',
   SOC_BK_SEQ           bigint not null default 0 comment '数据源序号',
   EXE_SERVER_NAME      varchar(20) comment '执行服务器名',
   EXE_SOC_NAME         varchar(20) comment '执行数据源名',
   VER_SERVER_NAME      varchar(20) comment '版本服务器名',
   VER_SOC_NAME         varchar(20) comment '版本数据源名',
   CODE_BK_DIR          varchar(100) comment '源码版本目录',
   primary key (SOC_UUID, SOC_BK_SEQ)
);

alter table UU_SOC comment '数据源关联表';

create view VI_SYS_ENV_SERVER as select sys.SYS_NAME,sys.SYS_CN_NAME,sys.SYS_BK_DESC,sys.SYS_TYPE,en.ENV_NAME,en.ENV_CN_NAME,en.ENV_BK_DESC,en.ENV_TYPE,en.DT_RANGE,se.SERVER_NAME,se.SERVER_CN_NAME,SERVER_DESC,se.SERVER_IP,se.SERVER_OS,se.OS_SBK_VER,se.SERVER_DB,se.SERVER_MID_WARE from ce_system sys left join ce_environment en on sys.SYS_NAME=en.SYS_NAME LEFT JOIN ce_environment_server es on en.ENV_NAME=es.ENV_NAME left JOIN ce_server se on es.SERVER_NAME=se.SERVER_NAME;