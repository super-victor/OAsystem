/*创建部门表*/
create table Department(
    departmentId int primary key auto_increment,
    name varchar(10),
    manager varchar(10),
    phone char(11),
    createTime TIMESTAMP,
    updateTime TIMESTAMP
)
