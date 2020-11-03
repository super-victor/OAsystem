/*创建部门表*/
create table Department(
    departmentId int primary key auto_increment,
    name varchar(10),
    manager varchar(10),
    phone char(11),
    createTime TIMESTAMP,
    updateTime TIMESTAMP
);
/*创建职工表*/
create table Employee(
                         employeeId int primary key auto_increment,
                         name varchar(20),
                         password varchar(20),
                         phone char(11),
                         email varchar(20),
                         birth timestamp,
                         idCard char(18),
                         sex char(2),
                         employmentDate timestamp,
                         departmentId int,
                         position varchar(10),
                         homeLocation varchar(20),
                         createTime timestamp,
                         updateTime timestamp
);
/*创建日程表*/
create table Schedule(
                         scheduleId int primary key auto_increment,
                         time timestamp,
                         location varchar(50),
                         work text,
                         comment text,
                         type int,
                         createTime timestamp,
                         updateTime timestamp
);
/*创建职工日程对应表*/
create table EmployeeSchedule(
                                 employeeScheduleId int primary key auto_increment,
                                 scheduleId int,
                                 employeeId int,
                                 createTime timestamp,
                                 updateTime timestamp
);