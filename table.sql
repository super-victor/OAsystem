drop table if exists log;
drop table if exists Message;
drop table if exists DocumentAnnex;
drop table if exists DocumentAcess;
drop table if exists RoleMenu;
drop table if exists Menu;
drop table if exists Meeting;
drop table if exists EmployeeRole;
drop table if exists Role;
drop table if exists SendFile;
drop table if exists Equipment;
drop table if exists MeetingRoom;
drop table if exists EquipmentClassify;
drop table if exists Message;
drop table if exists EmployeeTodo;
drop table if exists Todo;
drop table if exists Card;
drop table if exists CardHolder;
drop table if exists CardHolderClassfy;
drop table if exists EmployeeSchedule;
drop table if exists Schedule;
drop table if exists Employee;
drop table if exists Department;


-- 部门表
create table Department (
    departmentId int auto_increment comment '部门id',
    name varchar(50) not null comment '部门名称',
    phone char(11) not null comment '部门电话',
    createTime timestamp default current_timestamp comment '字段创建时间',
    updateTime timestamp on update current_timestamp comment '字段修改时间',
    constraint pk_Department_departmentId primary key(departmentId),
    constraint unique_Department_name unique key(name)
);


-- 职工表
create table Employee (
    employeeId int auto_increment comment '职工id',
    username varchar(20) not null comment '登录用户名',
    name varchar(20) not null comment '职工姓名',
    password varchar(20) not null comment '职工密码',
    phone char(11) comment '职工电话',
    email varchar(20) comment '职工邮箱',
    birthday timestamp comment '职工生日',
    idCard char(18) not null comment '职工身份证号码',
    sex char(2) comment '职工性别',
    entryTime timestamp default current_timestamp not null comment '职工入职时间',
    departmentName varchar(50) not null comment '部门名称',
    position varchar(10) not null comment '职工职位',
    homeAddress varchar(50) comment '职工家庭地址',
    isAccountLocked int comment '账户是否被锁',
    passwordChangeDate timestamp default current_timestamp comment'密码最后修改时间',
    createTime timestamp default current_timestamp comment '字段创建时间',
    updateTime timestamp on update current_timestamp comment '字段修改时间',
    constraint pk_Employee_employeeId primary key(employeeId),
    constraint unique_Employee_username unique key(username),
    constraint fk_Employee_departmentName foreign key(departmentName) references Department(name)
);


-- 日程表
create table Schedule (
      scheduleId int auto_increment comment '日程id',
      isCompany int not null comment '是否为公司日程',
      startTime timestamp not null comment '日程开始时间',
      endTime timestamp not null comment '日程结束时间',
      leader int not null comment '负责人的id',
      location varchar(50) not null comment '日程地点',
      content text not null comment '日程内容',
      remark text comment '日程备注',
      type int comment '日程类型',
      createTime timestamp default current_timestamp comment '字段创建时间',
      updateTime timestamp on update current_timestamp comment '字段修改时间',
      constraint pk_Schedule_scheduleId primary key(scheduleId)
);


-- 职工日程对应表
create table EmployeeSchedule(
    employeeScheduleId int auto_increment comment '职工和日程对应id',
    scheduleId int not null comment '日程id',
    employeeId int not null comment '职工id',
    createTime timestamp default current_timestamp comment '字段创建时间',
    updateTime timestamp on update current_timestamp comment '字段修改时间',
    constraint pk_EmployeeSchedule_employeeScheduleId primary key(employeeScheduleId),
    constraint fk_EmployeeSchedule_scheduleId foreign key(scheduleId) references Schedule(scheduleId),
    constraint fk_EmployeeSchedule_employeeId foreign key(employeeId) references Employee(employeeId),
    constraint unique_EmployeeSchedule_scheduleId_employeeId unique key(scheduleId,employeeId)
);


-- 名片夹表
create table CardHolder(
    cardHolderId int not null auto_increment comment '名片夹id',
    name varchar(50) not null comment '名片夹分类名称',
    ownerId int not null comment '名片夹拥有者id',
    createTime timestamp default current_timestamp comment '字段创建时间',
    updateTime timestamp on update current_timestamp comment '字段修改时间',
    constraint pk_CardHolder_cardHolderId primary key(cardHolderId),
    constraint fk_CardHolder_ownerId foreign key(ownerId) references Employee(employeeId)
);



-- 名片表
create table Card(
    cardId int not null auto_increment comment '名片id',
    ownerId int not null comment '名片拥有着id',
    cardHolderId int not null comment '名片夹id',
    name varchar(20) not null comment '姓名',
    phone char(11) not null comment '电话',
    email varchar(20) comment '邮箱',
    address varchar(50) not null comment '联系地址',
    company varchar(20) not null  comment '公司',
    department varchar(20) comment '部门',
    position varchar(20) comment '职位',
    createTime timestamp default current_timestamp comment '字段创建时间',
    updateTime timestamp on update current_timestamp comment '字段修改时间',
    constraint pk_Card_cardId primary key(cardId),
    constraint fk_Card_ownerId foreign key(ownerId) references Employee(employeeId),
    constraint fk_Card_cardHolderId foreign key(cardHolderId) references CardHolder(cardHolderId)
);



-- 待办事项表
create table Todo (
    todoId int  not null auto_increment comment '待办事项id',
    name varchar(50) not null comment '待办事项名称',
    content text comment'待办事项内容',
    beginTime timestamp not null comment '开始日期时间',
    deadline timestamp not null comment '截止日期时间',
    isUrgent int not null comment '是否紧急',
    isFinish int not null comment '是否已完成',
    remark varchar(50) comment '备注',
    createTime timestamp default current_timestamp comment '字段创建时间',
    updateTime timestamp on update current_timestamp comment '字段更新时间',
    constraint pk_Todo_todoId primary key(todoId)
);


-- 职工待办事项对应表
create table EmployeeTodo (
    employeeTodoId int not null auto_increment comment '职工和待办事项对应id',
    employeeId int not null comment '职工id',
    todoId int not null comment '待办事项id',
    createTime timestamp default current_timestamp comment '字段创建时间',
    updateTime timestamp on update current_timestamp comment '字段更新时间',
    constraint pk_EmployeeTodo_employeeTodoId primary key(employeeTodoId),
    constraint fk_EmployeeTodo_employeeId foreign key(employeeId) references Employee(employeeId),
    constraint fk_EmployeeTodo_todoId foreign key(todoId) references Todo(todoId),
    constraint unique_EmployeeTodo_employeeId_todoId unique key(employeeId,todoId)
);

-- 设备分类表
create table EquipmentClassify (
    equipmentClassifyId int not null auto_increment comment '设备分类id',
    name varchar(20) not null comment '设备分类名称',
    createTime timestamp default current_timestamp comment '字段创建时间',
    updateTime timestamp on update current_timestamp comment '字段更新时间',
    constraint pk_EquipmentClassify_equipmentClassifyId primary key(equipmentClassifyId)
);


-- 会议室表
create table MeetingRoom (
    meetingRoomId int not null auto_increment comment '会议室id',
    name varchar(20) not null comment '会议室名称',
    place varchar(20) comment '位置',
    isOccapy int not null comment '是否占用',
    maxperson int not null comment '最大容纳人数',
    createTime timestamp default current_timestamp comment '字段创建时间',
    updateTime timestamp on update current_timestamp comment '字段更新时间',
    constraint pk_MeetingRoom_meetingRoomId primary key(meetingRoomId)
);


-- 设备表
create table Equipment (
    equipmentId int not null auto_increment comment '设备id',
    equipmentClassifyId int not null comment '设备分类id',
    meetingRoomId int not null comment '会议室id',
    name varchar(20) not null comment '设备名称',
    isMaintain int not null comment '是否维护',
    remark varchar(50) comment '备注',
    num int not null comment '会议室设备数量',
    createTime timestamp default current_timestamp comment '字段创建时间',
    updateTime timestamp on update current_timestamp comment '字段更新时间',
    constraint pk_Equipment_equipmentId primary key(equipmentId),
    constraint fk_Equipment_equipmentClassifyId foreign key(equipmentClassifyId) references EquipmentClassify(equipmentClassifyId),
    constraint fk_Equipment_meetingRoomId foreign key(meetingRoomId) references MeetingRoom(meetingRoomId)
);


-- 发文表
create table SendFile(
    sendFileId int not null auto_increment comment '发文id',
    type varchar(50) comment '发文类别',
    subject varchar(50) comment '主题词',
    isPublic int comment '是否公有',
    title varchar(20) comment '标题',
    content text comment '正文',
    urgent varchar(20) comment '发文缓急',
    remark text comment '备注',
    senderId int not null comment '发件人ID',
    censorId int comment '审稿人ID',
    status int not null comment '状态 -3空白文件 -1草稿箱 -2审查不通过 0待审查 1通过审查(发布)',
    cersorOpinion varchar(50) comment '审稿人意见',
    createTime timestamp default current_timestamp comment '创建时间',
    updateTime timestamp on update current_timestamp comment '修改时间',
    constraint pk_SendFile_sendfileId primary key (sendFileId),
    constraint fk_SendFile_senderId foreign key (senderId) references Employee(employeeId),
    constraint fk_SendFile_censorId foreign key (censorId) references Employee(employeeId)
);

-- 发文附件
create table DocumentAnnex(
    documnetAnnexId int not null auto_increment comment '文档附件id',
    sendFileId int not null comment '发文id',
    annexUrl varchar(50) not null comment '附件url',
    createTime timestamp default current_timestamp comment '创建时间',
    updateTime timestamp on update current_timestamp comment '修改时间',
    constraint pk_DocumnetAnnex_documnetAnnexId primary key (documnetAnnexId),
    constraint fk_DocumnetAnnex_sendfileId foreign key (sendFileId) references SendFile(sendFileId),
    constraint unique_DocumnetAnnex_annexUrl unique key(annexUrl)
);

-- 已发布发文查看权限控制表
create table DocumentAcess(
    documentAcessId int not null auto_increment comment '文档权限id',
    sendFileId int not null comment '已发布发文id',
    employeeId int not null comment '可查看职工id',
    createTime timestamp default current_timestamp comment '创建时间',
    updateTime timestamp on update current_timestamp comment '修改时间',
    constraint pk_DocumentAcess_documentAcessId primary key (documentAcessId),
    constraint fk_DocumentAcess_sendfileId foreign key (sendFileId) references SendFile(sendFileId),
    constraint unique_DocumentAcess_sendFileId_employeeId unique key(sendFileId,employeeId)
);


-- 角色表
create table Role(
    roleId int not null auto_increment comment '角色ID',
    isAble int not null default 1 comment '角色是否可用',
    name varchar(20) not null  comment '角色名称',
    createTime timestamp default current_timestamp comment '创建时间',
    updateTime timestamp on update current_timestamp comment '修改时间',
    constraint pk_Role_roleId primary key (roleId),
    constraint unique_Role_name unique key(name)
 );


-- 职工角色对应表
create table EmployeeRole(
    employeeRoleId int not null auto_increment comment '职工角色ID',
    employeeId int not null comment '职工ID',
    roleId int not null comment '角色ID',
    createTime timestamp default current_timestamp comment '创建时间',
    updateTime timestamp on update current_timestamp comment '修改时间',
    constraint pk_EmployeeRole_emplyeeRoleId primary key (employeeRoleId),
    constraint fk_EmployeeRole_employeeId foreign key (employeeId) references Employee(employeeId),
    constraint fk_EmployeeRole_roleId foreign key (roleId) references Role(roleId),
    constraint unique_EmployeeRole_employeeId_roleId unique key(employeeId,roleId)
);

-- 菜单表
create table Menu(
    menuId int not null auto_increment comment '菜单id',
    name varchar(50) not null comment '菜单名称',
    url varchar(50) not null comment 'url接口地址',
    code varchar(50) not null comment '菜单码',
    createTime timestamp default current_timestamp comment '创建时间',
    updateTime timestamp on update current_timestamp comment '修改时间',
    constraint pk_Menu_menuId primary key (menuId),
    constraint unique_Menu_name unique key (name),
    constraint unique_Menu_url unique key (url),
    constraint unique_Menu_code unique key (code)
);

-- 角色菜单对应表
create table RoleMenu (
    roleMenuId int not null auto_increment comment '菜单角色对应id',
    menuId int not null comment '菜单id',
    roleId int not null comment '角色id',
    createTime timestamp default current_timestamp comment '创建时间',
    updateTime timestamp on update current_timestamp comment '修改时间',
    constraint pk_RoleMenu_roleMenuId primary key (roleMenuId),
    constraint fk_RoleMenu_menuId foreign key (menuId) references Menu(menuId),
    constraint fk_RoleMenu_roleId foreign key (roleId) references Role(roleId),
    constraint unique_RoleMenu_menuId_roleId unique key(menuId,roleId)
);

-- 会议表
create table Meeting(
    meetingId int not null auto_increment comment '会议ID',
    meetingRoomId int not null comment '会议室ID',
    status int not null comment '会议状态',
    employeeId int not null comment '主会人ID',
    record text comment '会议记录',
    remark text comment '备注',
    name varchar(50) not null comment '会议名称',
    appoinmentStatus int not null comment '会议预约状态 0预约中 -1 预约失败 1预约成功 2会议完成',
    createTime timestamp default current_timestamp comment '创建时间',
    updateTime timestamp on update current_timestamp comment '修改时间',
    startTime timestamp not null comment '会议开始时间',
    endTime timestamp not null  comment '会议结束时间',
    peopleNum int not null comment '会议人数',
    constraint pk_Meeting_meetingId primary key (meetingId),
    constraint fk_Meeting_employeeId foreign key (employeeId) references Employee(employeeId),
    constraint fk_Meeting_meetingRoomId foreign key (meetingRoomId) references MeetingRoom(meetingRoomId)
);

-- 消息表
create table Message (
    messageId int not null auto_increment comment '消息id',
    employeeId int not null comment '员工id',
    type int not null comment '消息种类',
    title varchar(30) not null comment '消息头',
    content varchar(50) not null comment '消息内容',
    sendTime timestamp not null default current_timestamp comment '发送时间',
    isRead int not null comment '消息是否已读',
    constraint pk_Message_messageId primary key (messageId),
    constraint fk_Message_employeeId foreign key (employeeId) references Employee(employeeId)
);


CREATE table log(
    logId int not null auto_increment,
    level varchar(45) not null,
    type char(20) not null,
    content text,
    createTime timestamp default current_timestamp comment '创建时间',
    updateTime timestamp on update current_timestamp comment '修改时间',
    constraint pk_Log_LogId primary key (logId)
);

-- 数据
insert into department (name, phone) values ('后勤部', '10001');
insert into department (name, phone) values ('财务部', '10002');

insert into employee (username, name, password, phone, email, idCard, sex, departmentName, position, homeAddress, isAccountLocked) values ('123456', '小花', '123456', '10086', '2238192070@qq.com', '510100000000000001', 'f', '财务部', '普通员工', '四川师范大学', 0);
insert into employee (username, name, password, phone, email, idCard, sex, departmentName, position, homeAddress, isAccountLocked) values ('pickmiu', '小明', '123456', '10086', '2238192070@qq.com', '510100000000000000', 'm', '后勤部', '普通员工', '四川师范大学', 0);
insert into employee (username, name, password, phone, email, idCard, sex, departmentName, position, homeAddress, isAccountLocked) values ('zhangsan','张三','666666','12345678910','968561352@qq.com','510100000000000002','f','财务部','普通用户','四川师范大学',0);
insert into employee (username, name, password, phone, email, idCard, sex, departmentName, position, homeAddress, isAccountLocked) values ('lisi','李四','121212','12345678910','115645284@qq.com','510100000000000003','f','后勤部','管理员','四川师范大学',0);
insert into employee (username, name, password, phone, email, idCard, sex, departmentName, position, homeAddress, isAccountLocked) values ('wanghong','王红','123123','12345678910','12456385@qq.com','510100000000000004','m','后勤部','普通员工','四川师范大学',0);

insert into cardholder (name, ownerId) values ('默认名片夹', 1);
insert into cardholder (name, ownerId) values ('默认名片夹', 2);
insert into cardholder (name, ownerId) values ('默认名片夹', 3);
insert into cardholder (name, ownerId) values ('默认名片夹', 4);
insert into cardholder (name, ownerId) values ('默认名片夹', 5);
insert into cardholder (name, ownerId) values ('后端', 2);

insert into card (ownerId, cardHolderId, name, phone, email, address, company, department, position) values (2, 2, '张三', '1122334455', '11223344@qq.com', '四川师范大学', '腾讯', '人事部', '普通员工');
insert into card (ownerId, cardHolderId, name, phone, email, address, company, department, position) values (2, 2, '张三', '1122334455', '11223344@qq.com', '四川师范大学', '腾讯', '人事部', '普通员工');
insert into card (ownerId, cardHolderId, name, phone, email, address, company, department, position) values (4, 5, '张一', '13348943243', 'Yu@163.com', '成龙', 'Sicnu', '人事部', '职员');

INSERT INTO `meetingroom` VALUES ('1', '101会议室', '3楼', '1', '100', '2020-11-13 15:35:27', '2020-11-13 22:48:31');
INSERT INTO `meetingroom` VALUES ('2', '201会议室', '2楼', '0', '100', '2020-11-13 15:56:09', '2020-11-13 22:55:56');
INSERT INTO `meetingroom` VALUES ('3', '301会议室', '3楼', '0', '100', '2020-11-13 15:57:40', null);

INSERT INTO `meeting` VALUES ('1', '1', '1', '1', null, null, '产品设计', '1', '2020-11-13 19:18:30', '2020-11-13 22:42:09', '2020-11-06 19:18:14', '2020-11-25 19:18:20', '20');
INSERT INTO `meeting` VALUES ('2', '2', '0', '1', null, null, '部门会议', '1', '2020-11-13 19:55:49', '2020-11-13 20:02:44', '2020-11-19 19:55:02', '2020-11-27 19:55:06', '10');

INSERT INTO `equipmentclassify` VALUES ('1', '显示器', '2020-11-15 22:15:39', null);
INSERT INTO `equipmentclassify` VALUES ('2', '电脑', '2020-11-15 22:20:17', null);

INSERT INTO `equipment` VALUES ('1', '1', '1', '机器', '0', null, '20', '2020-11-15 22:36:04', '2020-11-15 23:05:24');

insert into schedule (isCompany, startTime, endTime, leader, location, content, remark, type) values (0, '2020-11-27 11:03:22', '2020-11-27 21:00:22', 2, '实验室', '我的日程', '完成日程接口', 4);
insert into schedule (isCompany, startTime, endTime, leader, location, content, remark, type) values (1, '2020-11-29 16:38:01', '2020-11-30 04:38:01', 4, '公司', '协同办公平台', '完成项目50%功能', 4);

insert into employeeschedule(scheduleId, employeeId) values (1, 2);

insert into role (name) values ('超级管理员');
insert into role (name) values ('管理员');
insert into role (name) values ('审稿人');

insert into employeerole (employeeId, roleId) values (1,1);
insert into employeerole (employeeId, roleId) values (2,2);
insert into employeerole (employeeId, roleId) values (3,3);

insert into menu (name, url, code) values ('获取个人资料', 'GET /selfprofile', '0001');
insert into menu (name, url, code) values ('修改个人资料', 'PUT /selfprofile', '0002');

insert into menu (name, url, code) values ('获取职工所拥有的名片夹', 'GET /CardHolder', '0003');
insert into menu (name, url, code) values ('通过名片夹id删除名片夹', 'DELETE /CardHolder', '0004');
insert into menu (name, url, code) values ('增加名片夹', 'POST /CardHolder', '0005');
insert into menu (name, url, code) values ('修改名片夹名称', 'PUT /CardHolder', '0006');
insert into menu (name, url, code) values ('直接手动添加名片', 'POST /Card', '0007');
insert into menu (name, url, code) values ('修改名片相关内容', 'PUT /Card', '0008');
insert into menu (name, url, code) values ('分享名片', 'POST /shareCard', '0009');
insert into menu (name, url, code) values ('通过名片id删除名片', 'DELETE /Card', '000A');
insert into menu (name, url, code) values ('通过名片夹id查找名片', 'GET /Card', '000B');

insert into menu (name, url, code) values ('获取所有员工的通讯录','GET /addressbook','000E');
insert into menu (name, url, code) values ('获取所有部门的部门名称','GET /getAllDepartmentName','000F');
insert into menu (name, url, code) values ('分页获取员工通讯录','GET /addressbookbypage','000G');
insert into menu (name, url, code) values ('按条件查询员工通讯录','GET /getBypage/condition','000H');
insert into menu (name, url, code) values ('添加设备分类','POST /addEquipmentClassify','000I');
insert into menu (name, url, code) values ('修改设备分类信息','PUT /updateEquipmentClassify','000J');
insert into menu (name, url, code) values ('获取所有设备分类信息','GET /getAllEquipmentClassify','000K');
insert into menu (name, url, code) values ('删除设备分类','POST /deleteEquipmentClassify','000L');
insert into menu (name, url, code) values ('添加设备','POST /addEquipment','000M');
insert into menu (name, url, code) values ('修改设备','PUT /updateEquipment','000N');
insert into menu (name, url, code) values ('删除设备','DELETE /deleteEuipment','000P');
insert into menu (name, url, code) values ('获取所有的设备信息','GET /getallEquipment','000Q');
insert into menu (name, url, code) values ('查找设备信息','GET /getEquipmentById','000R');
insert into menu (name, url, code) values ('获取所有的会议室信息','GET /getAllMeetingRoom','000S');
insert into menu (name, url, code) values ('添加会议室','POST /addMeetingRoom','000T');
insert into menu (name, url, code) values ('删除会议室','DELETE /deleteMeetingRoom','000V');
insert into menu (name, url, code) values ('查找会议室','GET /getMeetingRoomById','000W');
insert into menu (name, url ,code) values ('添加公司日程','POST /CompanySchedule','000X');
insert into menu (name, url ,code) values ('修改公司日程信息','PUT /CompanySchedule','000Y');
insert into menu (name, url ,code) values ('删除公司日程','DELETE /CompanySchedule','000Z');
insert into menu (name, url ,code) values ('添加个人日程','POST /SelfSchedule','0011');
insert into menu (name, url ,code) values ('修改个人日程信息','PUT /SelfSchedule','0012');
insert into menu (name, url ,code) values ('删除个人日程','DELETE /SelfSchedule','0013');
insert into menu (name, url ,code) values ('审稿人获取自己未审稿','GET /UnCheckDocument','0014');
insert into menu (name, url ,code) values ('审稿人审稿','PUT /CheckDocument','0015');
insert into menu (name, url ,code) values ('获取该员工一段时间的个人日程','GET /findSelfSchedule','0016');
insert into menu (name, url ,code) values ('获取该员工一段时间的公司日程','GET /findCompanySchedule','0017');
insert into menu (name, url ,code) values ('按条件获取设备信息','GET /getEquipmentByCondition','0018');
insert into menu (name, url ,code) values ('按楼层获取会议室信息','GET /getAllMeetingRoomByStorey','0019');
insert into menu (name, url ,code) values ('获取所有的会议','GET /getAllMeetings','0020');
insert into menu (name, url ,code) values ('获取当前的所有会议','GET /getCurrentAllMeeting','0021');
insert into menu (name, url ,code) values ('查询会议','GET /getMeetingById','0022');
insert into menu (name, url ,code) values ('审批会议','PUT /approveMeeting','0023');
insert into menu (name, url ,code) values ('获取未被审批的会议','GET /getNotApprovedMeeting','0024');
insert into menu (name, url ,code) values ('预约会议','POST /addOrderMeeting','0025');
insert into menu (name, url ,code) values ('获取某一天某一个会议室的会议预约','POST /getAllMeetingTimeByRoomAndTime','0026');
insert into menu (name, url ,code) values ('删除未审批的预约会议','DELETE /cancleApproveMeeting','0027');
insert into menu (name, url ,code) values ('修改未审批的预约会议','PUT /updateOrderMeeting','0028');



insert into rolemenu (roleId, menuId) VALUES (3, 35);
insert into rolemenu (roleId, menuId) VALUES (3, 36);

insert into message (employeeId, type, title, content, sendTime, isRead) values (1, 1, '发文审核', '标题为xxx的发文审核已通过', '2020-11-25 22:38:12', 0);
insert into message (employeeId, type, title, content, sendTime, isRead) values (2, 2, '公司日程', '您收到了一个关于公司日程的日程', '2020-11-27 21:45:44', 0);
insert into message (employeeId, type, title, content, sendTime, isRead) values (3, 2, '公司日程', '您收到了一个关于公司日程的日程', '2020-11-27 21:45:44', 0);
insert into message (employeeId, type, title, content, sendTime, isRead) values (4, 2, '公司日程', '您收到了一个关于公司日程的日程', '2020-11-27 21:45:44', 0);
insert into message (employeeId, type, title, content, sendTime, isRead) values (2, 2, '公司日程', '有关''公司日程''内容的公司日程已经结束', '2020-11-29 16:35:25', 0);
insert into message (employeeId, type, title, content, sendTime, isRead) values (3, 2, '公司日程', '有关''公司日程''内容的公司日程已经结束', '2020-11-29 16:35:25', 0);
insert into message (employeeId, type, title, content, sendTime, isRead) values (4, 2, '公司日程', '有关''公司日程''内容的公司日程已经结束', '2020-11-29 16:35:25', 0);
insert into message (employeeId, type, title, content, sendTime, isRead) values (2, 2, '公司日程', '您收到了一个关于公司日程的日程', '2020-11-29 16:39:43', 0);
insert into message (employeeId, type, title, content, sendTime, isRead) values (3, 2, '公司日程', '您收到了一个关于公司日程的日程', '2020-11-29 16:39:43', 0);
insert into message (employeeId, type, title, content, sendTime, isRead) values (4, 2, '公司日程', '您收到了一个关于公司日程的日程', '2020-11-29 16:39:43', 0);