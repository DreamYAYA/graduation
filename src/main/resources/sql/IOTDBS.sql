/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/12/4 14:20:21                           */
/*==============================================================*/


drop table if exists DEVICE;

drop table if exists DEVICECON;

drop table if exists DEVICE_ALERT;

drop table if exists DEVICE_INTERFACE;

drop table if exists DEVICE_TIMER;

drop table if exists USER;

drop table if exists USER_INFO;

/*==============================================================*/
/* Table: DEVICE                                                */
/*==============================================================*/
create table DEVICE
(
   DEVICE_ID            varchar(40) not null,
   USER_ID              varchar(40),
   DEVICE_NAME          varchar(40),
   APIKEY               varchar(20),
   DEVICE_ISPUBLIC      varchar(30),
   DEVICE_ISONLINE      int,
   DEVICE_TIME          int,
   DEVICE_STATUE        int,
   CRATE_BY             varchar(40),
   CREAT_TIME           datetime,
   UPDATE_BY            varchar(40),
   UPDATE_TIME          datetime,
   primary key (DEVICE_ID)
);

/*==============================================================*/
/* Table: DEVICECON                                             */
/*==============================================================*/
create table DEVICECON
(
   DEVICECON_ID         varchar(40) not null,
   TODEVICE_ID          varchar(40),
   DEVICEDON_MESSAGE    varchar(20),
   CREATE_BY            varchar(40),
   CRAETE_TIME          datetime,
   UPDATE_BY            varchar(40),
   UPDATE_TIME          datetime,
   primary key (DEVICECON_ID)
);

/*==============================================================*/
/* Table: DEVICE_ALERT                                          */
/*==============================================================*/
create table DEVICE_ALERT
(
   DEVICE_ALERT_ID      varchar(40) not null,
   DEVICE_ALERT_NAME    varchar(20),
   DEVICE_ALERT_STATUS  varchar(40),
   DEVICE_INTERFACE_ID  varchar(40),
   DEVICE_ALERT_VAL     varchar(20),
   DEVICE_ALERT_SENDMETHOD int,
   CREATE_BY            varchar(40),
   CREATE_TIME          datetime,
   UPDATE_BY            varchar(40),
   UPDATE_TIME          datetime,
   primary key (DEVICE_ALERT_ID)
);

alter table DEVICE_ALERT comment '设置数据的报警条件及设备定时控制';

/*==============================================================*/
/* Table: DEVICE_INTERFACE                                      */
/*==============================================================*/
create table DEVICE_INTERFACE
(
   DEVICE_INTERFACE_ID  varchar(40) not null,
   DEV_DEVICE_ID       varchar(40),
   DEVICE_ID            varchar(40),
   DEVICE_INTERFACE_NAME varchar(20),
   DEVICE_INTERFACE_TYPE int,
   DEVICE_UNIT          varchar(10),
   DEVICE_INTEFACE_DES  varchar(80),
   DEVICE_INTERFACE_STATUS char(10),
   CREATE_BY            varchar(40),
   CREATE_TIME          datetime,
   UPDATE_BY            varchar(40),
    UPDATE_TIMER         datetime,
   primary key (DEVICE_INTERFACE_ID)
);

alter table DEVICE_INTERFACE comment '设备的数据接口用于设备的数据的上传，';

/*==============================================================*/
/* Table: DEVICE_TIMER                                          */
/*==============================================================*/
create table DEVICE_TIMER
(
   DEVICE_TIMER_ID      varchar(40) not null,
   DEV_DEVICE_ID        varchar(40),
   DEVICE_ID            varchar(40),
   DEVICE_TIMER_NAME    varchar(20),
   DEVICE_TIMER_TODE    varchar(40),
   DEVICE_TIEMR_WEEK    int,
   DEVICE_TIEMR_HOUR    int,
   DEVICE_TIMER_MINER   int,
   DEVICE_TIMER_MES     varchar(20),
   DEVICE_TIMER_STATUS  int,
   CREATE_BY            varchar(40),
   CREATE_TIME          datetime,
   UPDATE_BY            varchar(40),
   UPDATE_TIMER         datetime,
   primary key (DEVICE_TIMER_ID)
);

alter table DEVICE_TIMER comment '给设备定时的发送相关的指令';

/*==============================================================*/
/* Table: USER                                                  */
/*==============================================================*/
create table USER
(
   USER_ID              varchar(40) not null,
   DEP_ID               varchar(40),
   USER_PASSWORD        varchar(64),
   USER_NAME            varchar(40),
   CREATE_BY            varchar(40),
   CREATE_TIME          datetime,
   UPDATE_BY            varchar(40),
   UPDATE_TIME          datetime,
   primary key (USER_ID)
);

/*==============================================================*/
/* Table: USER_INFO                                             */
/*==============================================================*/
create table USER_INFO
(
   USER_ID              varchar(40) not null,
   USER_TEL             varchar(16),
   USER_INFO_MAIL       varchar(40),
   CREATE_BY            varchar(40),
   CREATE_TIME          datetime,
   UPDATE_BY            varchar(40),
   UPDATE_TIME          datetime,
   primary key (USER_ID)
);

alter table DEVICE add constraint FK_Reference_2 foreign key (USER_ID)
      references USER (USER_ID) on delete restrict on update restrict;

alter table DEVICE_ALERT add constraint FK_Reference_4 foreign key (DEVICE_INTERFACE_ID)
      references DEVICE_INTERFACE (DEVICE_INTERFACE_ID) on delete restrict on update restrict;

alter table DEVICE_INTERFACE add constraint FK_Reference_3 foreign key (DEV_DEVICE_ID)
      references DEVICE (DEVICE_ID) on delete restrict on update restrict;

alter table DEVICE_TIMER add constraint FK_Reference_5 foreign key (DEV_DEVICE_ID)
      references DEVICE (DEVICE_ID) on delete restrict on update restrict;

alter table USER_INFO add constraint FK_Reference_1 foreign key (USER_ID)
      references USER (USER_ID) on delete restrict on update restrict;

