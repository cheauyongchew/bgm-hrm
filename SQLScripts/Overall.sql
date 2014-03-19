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
    isDeleted CHAR(1),
    PRIMARY KEY (id)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS EmployeeType (
    id INT(10) NOT NULL AUTO_INCREMENT,
    name VARCHAR(20),
    description VARCHAR(50),
    isDeleted CHAR(1),
    PRIMARY KEY (id)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS Department (
    id INT(10) NOT NULL AUTO_INCREMENT,
    name VARCHAR(20),
    description VARCHAR(50),
    isDeleted CHAR(1),
    PRIMARY KEY (id)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS Users (
  id INT(10) NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
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
  `userId` int(10) NOT NULL,
  `userRoleId` int(10) NOT NULL,
  PRIMARY KEY (`userId`,`userRoleId`),
  KEY `FK_userRoleId` (`userRoleId`),
  CONSTRAINT `FK_userId` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_userRoleId` FOREIGN KEY (`userRoleId`) REFERENCES `role` (`id`)
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
    actionDate DATE,
    description VARCHAR(50),
    actorUserId INT(10),
    isDeleted CHAR(1),
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
    isDeleted CHAR(1),
    isResigned CHAR(1),
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
    isDeleted CHAR(1),
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
    isDeleted CHAR(1),
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
    isApproved CHAR(1),
    isCancelled CHAR(1),
    isDeleted CHAR(1),
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
    isDeleted CHAR(1),
    PRIMARY KEY (id),
    FOREIGN KEY (leaveTransactionId)
    	REFERENCES LeaveTransaction(id)
    	ON DELETE CASCADE
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

GRANT ALL PRIVILEGES ON beans.* TO 'beans'@'localhost';
GRANT ALL PRIVILEGES ON beans.* TO 'beans'@'127.0.0.1';


INSERT INTO EmployeeGrade(name, description, isDeleted) VALUES('JNR1', 'Junior 1', 0);
INSERT INTO EmployeeGrade(name, description, isDeleted) VALUES('JNR2', 'Junior 2', 0);
INSERT INTO EmployeeGrade(name, description, isDeleted) VALUES('JNR3', 'Junior 3', 0);
INSERT INTO EmployeeGrade(name, description, isDeleted) VALUES('SNR1', 'Senior 1', 0);
INSERT INTO EmployeeGrade(name, description, isDeleted) VALUES('SNR2', 'Senior 2', 0);
INSERT INTO EmployeeGrade(name, description, isDeleted) VALUES('SNR3', 'Senior 3', 0);

INSERT INTO EmployeeType(name, description, isDeleted) VALUES('INT', 'Internship', 0);
INSERT INTO EmployeeType(name, description, isDeleted) VALUES('CONT', 'Contracted', 0);
INSERT INTO EmployeeType(name, description, isDeleted) VALUES('PERM', 'Permanent', 0);

INSERT INTO Department(name, description, isDeleted) VALUES('SOA', 'SOA team', 0);
INSERT INTO Department(name, description, isDeleted) VALUES('JAVA', 'Java team', 0);
INSERT INTO Department(name, description, isDeleted) VALUES('PM', 'Project management team', 0);
INSERT INTO Department(name, description, isDeleted) VALUES('HRA', 'HR & Admin team', 0);
INSERT INTO Department(name, description, isDeleted) VALUES('MGMT', 'Management team', 0);

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

INSERT INTO Users(id, username, password, enabled) VALUES ('1', 'test1', 'test1', '1');

INSERT INTO Role(id, role, description, isDeleted) VALUES ('1', 'ROLE_USER', 'Normal User', 0);

INSERT INTO UserToRole(userId, userRoleId) VALUES ('1', '1');
 
