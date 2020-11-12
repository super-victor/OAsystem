drop table if exists Meeting;
drop table if exists EmployeeRole;
drop table if exists Role;
drop table if exists ReceiveFile;
drop table if exists SendFile;
drop table if exists Equipment;
drop table if exists MeetingRoom;
drop table if exists EquipmentClassify;
drop table if exists Message;
drop table if exists EmployeeTodo;
drop table if exists Todo;
drop table if exists CardHolder;
drop table if exists CardHolderClassfy;
drop table if exists EmployeeSchedule;
drop table if exists Schedule;
drop table if exists Employee;
drop table if exists Department;


-- 部门表
create table Department (
    departmentId int auto_increment comment '部门id',
    name varchar(10) not null comment '部门名称',
    phone char(11) not null comment '部门电话',
    createTime timestamp default current_timestamp comment '字段创建时间',
    updateTime timestamp on update current_timestamp comment '字段修改时间',
    constraint pk_Department_departmentId primary key(departmentId)
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
    entryTime timestamp not null comment '职工入职时间',
    departmentId int not null comment '职工所在部门id',
    position varchar(10) not null comment '职工职位',
    homeAddress varchar(20) comment '职工家庭地址',
    createTime timestamp default current_timestamp comment '字段创建时间',
    updateTime timestamp on update current_timestamp comment '字段修改时间',
    constraint pk_Employee_employeeId primary key(employeeId),
    constraint unique_Employee_username unique key(username),
    constraint fk_Employee_departmentId foreign key(departmentId) references Department(departmentId)
);


-- 日程表
create table Schedule (
    scheduleId int auto_increment comment '日程id',
    startTime timestamp not null comment '日程开始时间',
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


-- 名片夹分类表
create table CardHolderClassfy(
    cardHolderClassfyId int not null auto_increment comment '名片夹分类id',
    name varchar(20) not null comment '名片夹分类名称',
    ownerId int not null comment '名片夹拥有者id',
    createTime timestamp default current_timestamp comment '字段创建时间',
    updateTime timestamp on update current_timestamp comment '字段修改时间',
    constraint pk_CardHolderClassfy_cardHolderClassfyId primary key(cardHolderClassfyId),
    constraint fk_CardHolderClassfy_ownerId foreign key(ownerId) references Employee(employeeId)
);


-- 名片夹表
create table CardHolder(
    cardHolderId int not null auto_increment comment '名片id',
    ownerId int not null comment '拥有者id',
    ownedId int not null comment '名片被拥有者id',
    cardHolderClassfyId int not null comment '名片夹分类id',
    createTime timestamp default current_timestamp comment '字段创建时间',
    updateTime timestamp on update current_timestamp comment '字段修改时间',
    constraint pk_cardHolder_cardHolderId primary key(cardHolderId),
    constraint fk_cardHolder_ownerId foreign key(ownerId) references Employee(employeeId),
    constraint fk_cardHolder_ownedId foreign key(ownedId) references Employee(employeeId),
    constraint fk_cardHolder_cardHolderClassfyId foreign key(cardHolderClassfyId) references CardHolderClassfy(cardHolderClassfyId)
);


-- 待办事项表
create table Todo (
    todoId int  not null auto_increment comment '待办事项id',
    name varchar(20) not null comment '待办事项名称',
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
    employeeTodoId int not null comment '职工和待办事项对应id',
    employeeId int not null comment '职工id',
    todoId int not null comment '待办事项id',
    createTime timestamp default current_timestamp comment '字段创建时间',
    updateTime timestamp on update current_timestamp comment '字段更新时间',
    constraint pk_EmployeeTodo_employeeTodoId primary key(employeeTodoId),
    constraint fk_EmployeeTodo_employeeId foreign key(employeeId) references Employee(employeeId),
    constraint fk_EmployeeTodo_todoId foreign key(todoId) references Todo(todoId),
    constraint unique_EmployeeTodo_employeeId_todoId unique key(employeeId,todoId)
);


-- 留言表
create table Message (
    messageId int not null comment '留言id',
    senderId int not null comment '发送者id',
    receiverId int not null comment '接收者id',
    content text not null comment '留言内容',
    isRead int not null comment '是否已读',
    createTime timestamp default current_timestamp comment '字段创建时间',
    updateTime timestamp on update current_timestamp comment '字段更新时间',
    constraint pk_Message_messageId primary key(messageId),
    constraint fk_Message_senderId foreign key(senderId) references Employee(employeeId),
    constraint fk_Message_receiverId foreign key(receiverId) references Employee(employeeId)
);


-- 设备分类表
create table EquipmentClassify (
    equipmentClassifyId int not null comment '设备分类id',
    name varchar(20) not null comment '设备分类名称',
    createTime timestamp default current_timestamp comment '字段创建时间',
    updateTime timestamp on update current_timestamp comment '字段更新时间',
    constraint pk_EquipmentClassify_equipmentClassifyId primary key(equipmentClassifyId)
);


-- 会议室表
create table MeetingRoom (
    meetingRoomId int not null comment '会议室id',
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
    equipmentId int not null comment '设备id',
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
    sendfileId int not null auto_increment comment '发文id',
    type int not null comment '发文类别',
    title varchar(20) not null comment '标题',
    annexUrl varchar(20) comment '附件URL',
    context text not null comment '正文',
    sendPsrsonNum int not null comment '分发人数',
    isUergent int not null comment '发文缓急',
    remark text comment '备注',
    senderId int not null comment '发件人ID',
    censorId int comment '审查人ID',
    isCensored int comment '是否通过审查 0未审查，1审查通过 -1审查未通过',
    createTime timestamp default current_timestamp comment '创建时间',
    updateTime timestamp on update current_timestamp comment '修改时间',
    constraint pk_SendFile_sendfileId primary key (sendfileid),
    constraint fk_SendFile_senderId foreign key (senderId) references Employee(employeeId),
    constraint fk_SendFile_censorId foreign key (censorId) references Employee(employeeId)
);


-- 收文表
create table ReceiveFile(
    receiveId int not null comment '收文ID',
    sendFileId int not null comment '发文ID',
    receiverId int not null comment '收件人ID',
    isRecceived int not null comment '是否收到',
    createTime timestamp default current_timestamp comment '创建时间',
    updateTime timestamp on update current_timestamp comment '修改时间',
    constraint pk_ReceiveFile_receiveId primary key (receiveId),
    constraint fk_ReceiveFile_sendFileId foreign key (sendFileId) references SendFile(sendfileId),
    constraint fk_ReceiveFile_receiverId foreign key (receiverId) references Employee(employeeId)
);


-- 角色表
create table Role(
    roleId int not null comment '角色ID',
    name varchar(10) not null  comment '角色名称',
    createTime timestamp default current_timestamp comment '创建时间',
    updateTime timestamp on update current_timestamp comment '修改时间',
    constraint pk_Role_roleId primary key (roleId)
 );


-- 职工角色对应表
create table EmployeeRole(
    employeeRoleId int not null comment '职工角色ID',
    employeeId int not null comment '职工ID',
    roleId int not null comment '角色ID',
    createTime timestamp default current_timestamp comment '创建时间',
    updateTime timestamp on update current_timestamp comment '修改时间',
    constraint pk_EmployeeRole_emplyeeRoleId primary key (employeeRoleId),
    constraint fk_EmployeeRole_employeeId foreign key (employeeId) references Employee(employeeId),
    constraint fk_EmployeeRole_roleId foreign key (roleId) references Role(roleId),
    constraint unique_EmployeeRole_employeeId_roleId unique key(employeeId,roleId)
);


-- 会议表
create table Meeting(
    meetingId int not null comment '会议ID',
    meetingRoomId int not null comment '会议室ID',
    status int not null comment '会议状态',
    employeeId int not null comment '主会人ID',
    record text comment '会议记录',
    remark text comment '备注',
    name varchar(20) not null comment '会议名称',
    appoinmentStatus int not null comment '会议预约状态',
    createTime timestamp default current_timestamp comment '创建时间',
    updateTime timestamp on update current_timestamp comment '修改时间',
    startTime timestamp not null comment '会议开始时间',
    endTime timestamp not null  comment '会议结束时间',
    peopleNum int not null comment '会议人数',
    constraint pk_Meeting_meetingId primary key (meetingId),
    constraint fk_Meeting_employeeId foreign key (employeeId) references Employee(employeeId),
    constraint fk_Meeting_meetingRoomId foreign key (meetingRoomId) references MeetingRoom(meetingRoomId)
);

