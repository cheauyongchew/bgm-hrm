DROP DATABASE beans;
CREATE DATABASE IF NOT EXISTS beans;

DROP USER 'beans'@'localhost';
DROP USER 'beans'@'127.0.0.1';

CREATE USER 'beans'@'localhost' IDENTIFIED BY '1700819819';
CREATE USER 'beans'@'127.0.0.1' IDENTIFIED BY '1700819819';

USE beans;

CREATE TABLE IF NOT EXISTS EmployeeGrade (
    id INT(10) NOT NULL AUTO_INCREMENT,
    name VARCHAR(20),
    description VARCHAR(50),
    createdBy VARCHAR(70),
    creationTime TIMESTAMP NULL,
    lastModifiedBy VARCHAR(70),
    lastModifiedTime TIMESTAMP NULL,
    isDeleted CHAR(1),
    PRIMARY KEY (id)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS EmployeeType (
    id INT(10) NOT NULL AUTO_INCREMENT,
    name VARCHAR(20),
    description VARCHAR(50),
    createdBy VARCHAR(70),
    creationTime TIMESTAMP NULL,
    lastModifiedBy VARCHAR(70),
    lastModifiedTime TIMESTAMP NULL,
    isDeleted CHAR(1),
    PRIMARY KEY (id)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS Department (
    id INT(10) NOT NULL AUTO_INCREMENT,
    name VARCHAR(20),
    description VARCHAR(50),
    createdBy VARCHAR(70),
    creationTime TIMESTAMP NULL,
    lastModifiedBy VARCHAR(70),
    lastModifiedTime TIMESTAMP NULL,

    isDeleted CHAR(1),
    PRIMARY KEY (id)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS Users (
  id INT(10) NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled TINYINT(1) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS Role (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isDeleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS UserToRole (
  userId int(10) NOT NULL,
  userRoleId int(10) NOT NULL,
  FOREIGN KEY (userId) REFERENCES Users(id),
  FOREIGN KEY (userRoleId) REFERENCES Role(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS LeaveType (
	id INT(10) NOT NULL AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	description VARCHAR(50),
	employeeTypeId INT,
	entitlement DOUBLE(4,2),
	isAccountable TINYINT(1) DEFAULT 1 NOT NULL,
	isDeleted TINYINT(1) DEFAULT 0 NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (employeeTypeId)
    	REFERENCES EmployeeType(id)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS SystemAuditTrail (
    id INT(10) NOT NULL AUTO_INCREMENT,
    actionDate DATETIME,
    level VARCHAR(10),
    activity VARCHAR(15),
    description VARCHAR(250),
    actorUserId INT(10),
    actorUsername VARCHAR(50),
    isDeleted TINYINT(1),
    PRIMARY KEY (id)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS Employee (
    id INT(10) NOT NULL AUTO_INCREMENT,
    employeeNumber VARCHAR(10),
    name VARCHAR(50),
    position VARCHAR(30),
    idNumber VARCHAR(20),
    passportNumber VARCHAR(20),
    gender VARCHAR(3),
    religion VARCHAR(20),
    maritalStatus VARCHAR(10),
    workEmailAddress VARCHAR(50),
    personalEmailAddress VARCHAR(50),
    officePhone VARCHAR(20),
    personalPhone VARCHAR(20),
    nationality VARCHAR(40),
    userId INT(10),
    employeeGradeId INT(10),
    departmentId INT(10),
    employeeTypeId INT(10),
    joinDate DATE,
    resignationDate DATE,
    isDeleted TINYINT(1),
    isResigned TINYINT(1),
    PRIMARY KEY (id),
    FOREIGN KEY (userId) 
  		REFERENCES Users(id),
    FOREIGN KEY (employeeGradeId)
    	REFERENCES EmployeeGrade(id),
    FOREIGN KEY (departmentId)
    	REFERENCES Department(id),
    FOREIGN KEY (employeeTypeId)
    	REFERENCES EmployeeType(id)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS Address (
    id INT(10) NOT NULL AUTO_INCREMENT,
    employeeId INT(10) NOT NULL,
    addressType VARCHAR(20),
    line1 VARCHAR(100),
    line2 VARCHAR(100),
    line3 VARCHAR(100),
    city VARCHAR(40),
    state VARCHAR(40),
    country VARCHAR(40),
    postcode VARCHAR(10),
    isDeleted TINYINT(1),
    PRIMARY KEY (id),
    FOREIGN KEY (employeeId)
    	REFERENCES Employee(id)
    	ON DELETE CASCADE
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS YearlyEntitlement (
    id INT(10) NOT NULL AUTO_INCREMENT,
    employeeId INT(10) NOT NULL,
    leaveTypeId INT(10) NOT NULL,
    entitlement DOUBLE(4,2),
    leaveBalance DOUBLE(4,2),
    isDeleted TINYINT(1),
    PRIMARY KEY (id),
    FOREIGN KEY (leaveTypeId)
    	REFERENCES LeaveType(id),
    FOREIGN KEY (employeeId)
    	REFERENCES Employee(id)
    	ON DELETE CASCADE
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS LeaveTransaction (
    id INT(10) NOT NULL AUTO_INCREMENT,
    applicationDate DATE,
    startDateTime TIMESTAMP,
    endDateTime TIMESTAMP,
    numberOfHours DOUBLE(4,2),
    numberOfDays DOUBLE(4,2),
    reason VARCHAR(100),
    leaveTypeId INT(10),
    employeeId INT(10),
    taskId BIGINT(20),
    isDeleted TINYINT(1),
    PRIMARY KEY (id),
    FOREIGN KEY (leaveTypeId)
    	REFERENCES LeaveType(id),
    FOREIGN KEY (employeeId)
    	REFERENCES Employee(id)
    	ON DELETE CASCADE
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS LeaveAttachment (
    id INT(10) NOT NULL AUTO_INCREMENT,
    leaveTransactionId INT(10),
    description VARCHAR(50),
    fileLocation VARCHAR(150),
    isDeleted TINYINT(1),
    PRIMARY KEY (id),
    FOREIGN KEY (leaveTransactionId)
    	REFERENCES LeaveTransaction(id)
    	ON DELETE CASCADE
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS LeaveApplicationComment (
    id INT(10) NOT NULL AUTO_INCREMENT,
    leaveTransactionId INT(10),
    comment VARCHAR(150) NOT NULL,
    createdBy VARCHAR(70),
    creationTime TIMESTAMP NULL,
    lastModifiedBy VARCHAR(70),
    lastModifiedTime TIMESTAMP NULL,
    isDeleted TINYINT(1),
    PRIMARY KEY (id),
    FOREIGN KEY (leaveTransactionId)
    	REFERENCES LeaveTransaction(id)
    	ON DELETE CASCADE
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS AccessRights (
  id int(10) NOT NULL AUTO_INCREMENT,
  accessRights VARCHAR(50) NOT NULL,
  description VARCHAR(150),
  isDeleted TINYINT(1),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS RoleToAccessRights (
  roleId int(10) NOT NULL,
  accessRightsId int(10) NOT NULL,
  FOREIGN KEY (roleId) REFERENCES Role(id),
  FOREIGN KEY (accessRightsId) REFERENCES AccessRights(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS UserToAccessRights (
  id int(10) NOT NULL AUTO_INCREMENT,
  userId int(10) NOT NULL ,
  accessRightsId int(10) NOT NULL,
  enabled TINYINT(1) NOT NULL,
  isDeleted TINYINT(1) NOT NULL,
  FOREIGN KEY (userId) REFERENCES Users(id),
  FOREIGN KEY (accessRightsId) REFERENCES AccessRights(id),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

GRANT ALL PRIVILEGES ON beans.* TO 'beans'@'localhost';
GRANT ALL PRIVILEGES ON beans.* TO 'beans'@'127.0.0.1';


INSERT INTO EmployeeGrade(name, description,creationTime,createdBy, isDeleted) VALUES('JNR1', 'Junior 1',CURRENT_TIMESTAMP,'admin', 0);
INSERT INTO EmployeeGrade(name, description,creationTime,createdBy, isDeleted) VALUES('JNR2', 'Junior 2',CURRENT_TIMESTAMP,'admin', 0);
INSERT INTO EmployeeGrade(name, description,creationTime,createdBy, isDeleted) VALUES('JNR3', 'Junior 3',CURRENT_TIMESTAMP,'admin', 0);
INSERT INTO EmployeeGrade(name, description,creationTime,createdBy, isDeleted) VALUES('SNR1', 'Senior 1',CURRENT_TIMESTAMP,'admin', 0);
INSERT INTO EmployeeGrade(name, description,creationTime,createdBy, isDeleted) VALUES('SNR2', 'Senior 2',CURRENT_TIMESTAMP,'admin', 0);
INSERT INTO EmployeeGrade(name, description,creationTime,createdBy, isDeleted) VALUES('SNR3', 'Senior 3',CURRENT_TIMESTAMP,'admin', 0);

INSERT INTO EmployeeType(name, description,creationTime,createdBy, isDeleted) VALUES('INT', 'Internship',CURRENT_TIMESTAMP,'admin', 0);
INSERT INTO EmployeeType(name, description,creationTime,createdBy, isDeleted) VALUES('CONT', 'Contracted',CURRENT_TIMESTAMP,'admin', 0);
INSERT INTO EmployeeType(name, description,creationTime,createdBy, isDeleted) VALUES('PERM', 'Permanent',CURRENT_TIMESTAMP,'admin', 0);

INSERT INTO Department(name, description,creationTime,createdBy, isDeleted) VALUES('SOA', 'SOA team',CURRENT_TIMESTAMP,'admin', 0);
INSERT INTO Department(name, description,creationTime,createdBy, isDeleted) VALUES('JAVA', 'Java team',CURRENT_TIMESTAMP,'admin', 0);
INSERT INTO Department(name, description,creationTime,createdBy, isDeleted) VALUES('PM', 'Project management team',CURRENT_TIMESTAMP,'admin', 0);
INSERT INTO Department(name, description,creationTime,createdBy, isDeleted) VALUES('HRA', 'HR & Admin team',CURRENT_TIMESTAMP,'admin', 0);
INSERT INTO Department(name, description,creationTime,createdBy, isDeleted) VALUES('MGMT', 'Management team',CURRENT_TIMESTAMP,'admin', 0);

INSERT INTO LeaveType(name, description, employeeTypeId, entitlement, isAccountable, isDeleted) VALUES ('Annual', 'Annual leave', (SELECT id from EmployeeType WHERE name = 'INT'), 0.0, 1, 0); 
INSERT INTO LeaveType(name, description, employeeTypeId, entitlement, isAccountable, isDeleted) VALUES ('Annual', 'Annual leave', (SELECT id from EmployeeType WHERE name = 'CONT'), 12.0, 0, 0);
INSERT INTO LeaveType(name, description, employeeTypeId, entitlement, isAccountable, isDeleted) VALUES ('Annual', 'Annual leave', (SELECT id from EmployeeType WHERE name = 'PERM'), 16.0, 0, 0);
INSERT INTO LeaveType(name, description, employeeTypeId, entitlement, isAccountable, isDeleted) VALUES ('Sick', 'Sick leave', (SELECT id from EmployeeType WHERE name = 'INT'), 0.0, 1, 0); 
INSERT INTO LeaveType(name, description, employeeTypeId, entitlement, isAccountable, isDeleted) VALUES ('Sick', 'Sick leave', (SELECT id from EmployeeType WHERE name = 'CONT'), 12.0, 0, 0);
INSERT INTO LeaveType(name, description, employeeTypeId, entitlement, isAccountable, isDeleted) VALUES ('Sick', 'Sick leave', (SELECT id from EmployeeType WHERE name = 'PERM'), 30.0, 0, 0);
INSERT INTO LeaveType(name, description, employeeTypeId, entitlement, isAccountable, isDeleted) VALUES ('Compassionate', 'Compassionate leave', (SELECT id from EmployeeType WHERE name = 'PERM'), 3.0, 0, 0);
INSERT INTO LeaveType(name, description, employeeTypeId, entitlement, isAccountable, isDeleted) VALUES ('Marriage', 'Marriage leave', (SELECT id from EmployeeType WHERE name = 'PERM'), 3.0, 0, 0);
INSERT INTO LeaveType(name, description, employeeTypeId, entitlement, isAccountable, isDeleted) VALUES ('Maternity', 'Maternity leave', (SELECT id from EmployeeType WHERE name = 'PERM'), 60.0, 0, 0);
INSERT INTO LeaveType(name, description, employeeTypeId, entitlement, isAccountable, isDeleted) VALUES ('Paternity', 'Paternity leave', (SELECT id from EmployeeType WHERE name = 'PERM'), 3.0, 0, 0);

INSERT INTO Users(id, username, password, enabled) VALUES ('1', 'johndoe', '$2a$10$qzEqlpoVOYv4yT/pAqq7L.Y8PX.DLbtfWuPDvenJfcfhJd4I2wvZy', '1');
INSERT INTO Users(id, username, password, enabled) VALUES ('2', 'jennifer', '$2a$10$qzEqlpoVOYv4yT/pAqq7L.Y8PX.DLbtfWuPDvenJfcfhJd4I2wvZy', '1');
INSERT INTO Users(id, username, password, enabled) VALUES ('3', 'ruby', '$2a$10$qzEqlpoVOYv4yT/pAqq7L.Y8PX.DLbtfWuPDvenJfcfhJd4I2wvZy', '1');
INSERT INTO Users(id, username, password, enabled) VALUES ('4', 'admin', '$2a$10$qzEqlpoVOYv4yT/pAqq7L.Y8PX.DLbtfWuPDvenJfcfhJd4I2wvZy', '1');
INSERT INTO Users(id, username, password, enabled) VALUES ('5', 'larry', '$2a$10$qzEqlpoVOYv4yT/pAqq7L.Y8PX.DLbtfWuPDvenJfcfhJd4I2wvZy', '1');
INSERT INTO Users(id, username, password, enabled) VALUES ('6', 'joe', '$2a$10$qzEqlpoVOYv4yT/pAqq7L.Y8PX.DLbtfWuPDvenJfcfhJd4I2wvZy', '1');

INSERT INTO Role(id, role, description, isDeleted) VALUES ('1', 'ROLE_USER', 'Normal User', 0);
INSERT INTO Role(id, role, description, isDeleted) VALUES ('2', 'ROLE_EMPLOYEE', 'Employee', 0);
INSERT INTO Role(id, role, description, isDeleted) VALUES ('3', 'ROLE_HRJR', 'HR Junior Users', 0);
INSERT INTO Role(id, role, description, isDeleted) VALUES ('4', 'ROLE_HRSR', 'HR Senior Users', 0);
INSERT INTO Role(id, role, description, isDeleted) VALUES ('5', 'ROLE_ADMIN', 'Administrator', 0);
INSERT INTO Role(id, role, description, isDeleted) VALUES ('6', 'ROLE_HR', 'General HR Users', 0);
INSERT INTO Role(id, role, description, isDeleted) VALUES ('7', 'ROLE_TEAMLEAD', 'Team Lead Users', 0);
INSERT INTO Role(id, role, description, isDeleted) VALUES ('8', 'ROLE_OPERDIR', 'Operation Director Users', 0);

INSERT INTO UserToRole(userId, userRoleId) VALUES ('1', '1');
INSERT INTO UserToRole(userId, userRoleId) VALUES ('1', '2');
INSERT INTO UserToRole(userId, userRoleId) VALUES ('2', '1');
INSERT INTO UserToRole(userId, userRoleId) VALUES ('2', '2');
INSERT INTO UserToRole(userId, userRoleId) VALUES ('2', '3');
INSERT INTO UserToRole(userId, userRoleId) VALUES ('2', '6');
INSERT INTO UserToRole(userId, userRoleId) VALUES ('3', '1');
INSERT INTO UserToRole(userId, userRoleId) VALUES ('3', '2');
INSERT INTO UserToRole(userId, userRoleId) VALUES ('3', '4');
INSERT INTO UserToRole(userId, userRoleId) VALUES ('3', '6');
INSERT INTO UserToRole(userId, userRoleId) VALUES ('4', '1');
INSERT INTO UserToRole(userId, userRoleId) VALUES ('4', '5');
INSERT INTO UserToRole(userId, userRoleId) VALUES ('5', '1');
INSERT INTO UserToRole(userId, userRoleId) VALUES ('5', '2');
INSERT INTO UserToRole(userId, userRoleId) VALUES ('5', '7');
INSERT INTO UserToRole(userId, userRoleId) VALUES ('6', '1');
INSERT INTO UserToRole(userId, userRoleId) VALUES ('6', '2');
INSERT INTO UserToRole(userId, userRoleId) VALUES ('6', '8');

INSERT INTO AccessRights(id, accessRights, description, isDeleted) VALUES ('1', 'ViewEmployees', 'View Access to Employees.', 0);
INSERT INTO AccessRights(id, accessRights, description, isDeleted) VALUES ('2', 'AddEmployee', 'Access to Add Employee.', 0);
INSERT INTO AccessRights(id, accessRights, description, isDeleted) VALUES ('3', 'EditEmployee', 'Access to Edit Employee.', 0);
INSERT INTO AccessRights(id, accessRights, description, isDeleted) VALUES ('4', 'AdminFunctions', 'General Admin Functions', 0);
INSERT INTO AccessRights(id, accessRights, description, isDeleted) VALUES ('5', 'ViewMyProfile', 'View Access to MyProfile', 0);
INSERT INTO AccessRights(id, accessRights, description, isDeleted) VALUES ('6', 'DeleteEmployee', 'Access to Delete Employee', 0);
INSERT INTO AccessRights(id, accessRights, description, isDeleted) VALUES ('7', 'ApproveEmployeeRegistration', 'Access to Employee Registration Approval', 0);
INSERT INTO AccessRights(id, accessRights, description, isDeleted) VALUES ('8', 'ApplyLeave', 'Access to Leave Form', 0);


INSERT INTO RoleToAccessRights(roleId, accessRightsId) VALUES ('2', '5');
INSERT INTO RoleToAccessRights(roleId, accessRightsId) VALUES ('2', '8');
INSERT INTO RoleToAccessRights(roleId, accessRightsId) VALUES ('3', '1');
INSERT INTO RoleToAccessRights(roleId, accessRightsId) VALUES ('3', '7');
INSERT INTO RoleToAccessRights(roleId, accessRightsId) VALUES ('4', '1');
INSERT INTO RoleToAccessRights(roleId, accessRightsId) VALUES ('4', '2');
INSERT INTO RoleToAccessRights(roleId, accessRightsId) VALUES ('4', '3');
INSERT INTO RoleToAccessRights(roleId, accessRightsId) VALUES ('4', '6');
INSERT INTO RoleToAccessRights(roleId, accessRightsId) VALUES ('5', '4');
INSERT INTO RoleToAccessRights(roleId, accessRightsId) VALUES ('6', '7');
INSERT INTO RoleToAccessRights(roleId, accessRightsId) VALUES ('7', '8');

INSERT INTO Employee(id, name, employeenumber, position, employeeGradeId, employeeTypeId, departmentId, userId, isDeleted, isResigned) values(1, 'John Doe', '1', 'Software Engineer', 1, 1, 1, 1, 0, 0);
INSERT INTO Employee(id, name, employeenumber, position, employeeGradeId, employeeTypeId, departmentId, userId, isDeleted, isResigned) values(2, 'Jennifer', '2', 'HR Executive', 1, 1, 1, 2, 0, 0);
INSERT INTO Employee(id, name, employeenumber, position, employeeGradeId, employeeTypeId, departmentId, userId, isDeleted, isResigned) values(3, 'Ruby', '3', 'HR Manager', 1, 1, 1, 3, 0, 0);
INSERT INTO Employee(id, name, employeenumber, position, employeeGradeId, employeeTypeId, departmentId, userId, isDeleted, isResigned) values(4, 'Larry', '4', 'Team Lead', 1, 1, 1, 5, 0, 0);
INSERT INTO Employee(id, name, employeenumber, position, employeeGradeId, employeeTypeId, departmentId, userId, isDeleted, isResigned) values(5, 'Joe', '5', 'Operation Director', 1, 1, 1, 6, 0, 0);

INSERT INTO UserToAccessRights(id, userId, accessRightsId, enabled, isDeleted) values('1', '1', '1', '1', 0);

INSERT INTO YearlyEntitlement(id, employeeId, leaveTypeId, entitlement, leaveBalance, isDeleted) VALUES (1, 1, 1, 12, 12, 0);
INSERT INTO YearlyEntitlement(id, employeeId, leaveTypeId, entitlement, leaveBalance, isDeleted) VALUES (2, 4, 1, 12, 12, 0);
