CREATE DATABASE /*!32312 IF NOT EXISTS*/`ihr` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ihr`;


/*Table structure for table `client` */

DROP TABLE IF EXISTS client;

CREATE TABLE client (
  client_id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(100) NOT NULL,
  Address varchar(250) NOT NULL,
  effective_date date NOT NULL,
  Type char(1) DEFAULT NULL,
  term_date date DEFAULT NULL,
  Creation_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  Last_update_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (client_id)
);

/*Data for the table `client` */

INSERT INTO client (Name,Address,effective_date,Type,term_date,Creation_ts,Last_update_ts) VALUES 
('Experis','7272 Wisconsin Ave, Bethesda, MD 20814','2015-01-01','D',NULL,'2016-06-17 00:13:24.000','2016-06-17 00:13:24.000');

/*Table structure for table `health_carrier` */

DROP TABLE IF EXISTS health_carrier;

CREATE TABLE health_carrier (
  carrier_id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  Address varchar(250) NOT NULL,
  Start_date date NOT NULL,
  End_date date DEFAULT NULL,
  Creation_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  Last_update_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (carrier_id)
);

/*Data for the table `health_carrier` */

INSERT INTO health_carrier (name,Address,Start_date,End_date,Creation_ts,Last_update_ts) VALUES 
('Aetna','151 Farmington Avenue Hartford, CT 06156','2015-01-01',NULL,'2016-06-16 23:46:12.000','2016-06-16 23:46:12.000');

/*Table structure for table `plan_option` */

DROP TABLE IF EXISTS plan_option;

CREATE TABLE plan_option (
  Plan_opt_id int(11) NOT NULL AUTO_INCREMENT,
  Option_code char(10) DEFAULT NULL,
  Name char(10) NOT NULL,
  Description char(10) DEFAULT NULL,
  Creation_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  Last_update_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (Plan_opt_id)
);

/*Data for the table `plan_option` */

/*Table structure for table `project` */

DROP TABLE IF EXISTS project;

CREATE TABLE project (
  project_id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(100) DEFAULT NULL,
  Prj_code char(10) DEFAULT NULL,
  Start_Date date NOT NULL,
  End_Date date DEFAULT NULL,
  Creation_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  Last_update_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (project_id)
);

/*Data for the table `project` */

INSERT INTO project (Name,Prj_code,Start_Date,End_Date,Creation_ts,Last_update_ts) VALUES 
('iHR','IHR','2015-01-01',NULL,'2016-06-16 23:47:06.000','2016-06-16 23:47:06.000');

/*Table structure for table `role` */

DROP TABLE IF EXISTS role;

CREATE TABLE role (
  role_id int(11) NOT NULL AUTO_INCREMENT,
  Role_name varchar(100) NOT NULL,
  Creation_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  Last_update_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (role_id)
);

/*Data for the table `role` */

INSERT INTO role (Role_name,Creation_ts,Last_update_ts) VALUES 
('ADMIN','2016-07-01 12:39:30.000','2016-07-01 12:39:30.000'),
('EMPLOYEE','2016-07-01 12:39:53.000','2016-07-01 12:39:53.000');

/*Table structure for table `IMMIGRATION_TYPE` */

DROP TABLE IF EXISTS IMMIGRATION_TYPE;
 
CREATE TABLE IMMIGRATION_TYPE (
	immi_type_id int(11) NOT NULL,
	immi_type_nm varchar(100) NOT NULL,
	description varchar(200) NOT NULL,
	country varchar(100) NOT NULL,
	PRIMARY KEY (immi_type_id)
);

/*Data for the table `IMMIGRATION_TYPE` */

insert into IMMIGRATION_TYPE (immi_type_id, immi_type_nm, description, country) 
values (1,'H1B','Work Visa on employment','USA'),(2,'EAD','Work authorization against document','USA'),
(3,'H4-EAD','Work authorization against H1','USA'),(4,'GC','Green Card','USA'),
(5,'CT','Citizen of the country','USA'),(6,'H4','Dependent on H1','USA');






/*Table structure for table `employee` */

DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  employee_id int(11) NOT NULL AUTO_INCREMENT,
  First_name varchar(60) NOT NULL,
  Last_name varchar(60) NOT NULL,
  Middle_name varchar(60) DEFAULT NULL,
  Email varchar(100) NOT NULL,
  Home_phone mediumtext,
  Mobile_phone mediumtext NOT NULL,
  Work_phone mediumtext,
  SSN varchar(11) DEFAULT NULL,
  Birth_date date DEFAULT NULL,
  Start_date date NOT NULL,
  End_date date DEFAULT NULL,
  Base_salary_amt decimal(9,2) NOT NULL,
  Bonus_amt decimal(9,2) DEFAULT NULL,
  Designation varchar(20) NOT NULL,
  Creation_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  Last_update_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  GENDER varchar(5) DEFAULT NULL,
  PRIMARY KEY (employee_id)
);

/*Data for the table `employee` */

INSERT INTO employee (First_name,Last_name,Middle_name,Email,Home_phone,Mobile_phone,Work_phone,SSN,Birth_date,Start_date,End_date,Base_salary_amt,Bonus_amt,Designation,Creation_ts,Last_update_ts,GENDER) VALUES 
('Purna','Rachakonda',NULL,'tarunistream@gmail.com',NULL,'1231231234',NULL,'1234','2000-01-01','2015-01-01',NULL,1000.00,NULL,'HR','2016-07-05 22:21:37.000','2016-07-05 22:21:37.000','Male'),
('Vivek','Soni',NULL,'empistream@gmail.com',NULL,'1231231234',NULL,'1234','2000-01-01','2015-01-01',NULL,1000.00,NULL,'Programmer Analyst','2016-07-05 22:21:37.000','2016-07-05 22:21:37.000','Male');

/*Table structure for table `address` */

DROP TABLE IF EXISTS address;

CREATE TABLE address (
  Address_id int(11) NOT NULL AUTO_INCREMENT,
  Street1 varchar(100) NOT NULL,
  Street2 varchar(100) DEFAULT NULL,
  City varchar(40) NOT NULL,
  State char(2) NOT NULL,
  Zipcode char(15) NOT NULL,
  Country varchar(100) NOT NULL DEFAULT 'USA',
  Effective_date date NOT NULL,
  Term_date date DEFAULT NULL,
  Creation_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  Last_update_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  employee_id int(11) NOT NULL,
  PRIMARY KEY (Address_id),
  KEY employee_id (employee_id),
  CONSTRAINT address_ibfk_1 FOREIGN KEY (employee_id) REFERENCES employee (employee_id)
);

/*Data for the table `address` */

insert  into address (Street1, Street2, City, State, Zipcode, Country, Effective_date, Term_date, Creation_ts, Last_update_ts, employee_id) values 
('fee','ergre','herndon','va','20171','fairfax','2015-01-01','2015-02-02','2015-03-03 00:00:00','2015-03-03 00:00:00',(select employee_id from employee where email = 'empistream@gmail.com'));

/*Table structure for table `emp_client_as` */

DROP TABLE IF EXISTS emp_client_as;

CREATE TABLE emp_client_as (
  Emp_client_id int(11) NOT NULL AUTO_INCREMENT,
  Emp_id int(11) NOT NULL,
  Client_id int(11) NOT NULL,
  Start_date date NOT NULL,
  End_date date DEFAULT NULL,
  Creation_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  Last_update_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (Emp_client_id),
  KEY Emp_id (Emp_id),
  KEY Client_id (Client_id),
  CONSTRAINT emp_client_as_ibfk_1 FOREIGN KEY (Emp_id) REFERENCES employee (employee_id),
  CONSTRAINT emp_client_as_ibfk_2 FOREIGN KEY (Client_id) REFERENCES client (client_id)
);

/*Data for the table emp_client_as */

insert  into emp_client_as (Emp_id, Client_id, Start_date, End_date, Creation_ts, Last_update_ts) values 
((select employee_id from employee where email = 'empistream@gmail.com'), (select client_id from client where name = 'Experis'),'2015-02-02','2018-02-02','2015-02-02 00:00:00','2015-02-02 00:00:00');

/*Table structure for table `emp_health_plan` */

DROP TABLE IF EXISTS emp_health_plan;

CREATE TABLE emp_health_plan (
  emp_plan_id int(11) NOT NULL AUTO_INCREMENT,
  Plan_type varchar(20) NOT NULL,
  Carrier_id int(11) NOT NULL,
  Num_covd_members smallint(40) NOT NULL,
  Effective_date date NOT NULL,
  Term_date date DEFAULT NULL,
  Creation_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  Last_update_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  employee_id int(11) NOT NULL,
  PRIMARY KEY (emp_plan_id),
  KEY employee_id (employee_id),
  KEY Carrier_id (Carrier_id),
  CONSTRAINT emp_health_plan_ibfk_1 FOREIGN KEY (employee_id) REFERENCES employee (employee_id),
  CONSTRAINT emp_health_plan_ibfk_2 FOREIGN KEY (Carrier_id) REFERENCES health_carrier (carrier_id)
);

/*Data for the table `emp_health_plan` */

insert  into emp_health_plan(Plan_type,Carrier_id,Num_covd_members,Effective_date,Term_date,Creation_ts,Last_update_ts,employee_id) values 
('ppo',(select client_id from client where name = 'Experis'),4,'2015-02-02','2016-02-02','2016-02-02 00:00:00','2016-02-02 00:00:00',(select employee_id from employee where email = 'empistream@gmail.com'));

/*Table structure for table `emp_immigration_dtl` */

DROP TABLE IF EXISTS emp_immigration_dtl;

CREATE TABLE emp_immigration_dtl (
  Emp_imm_dtl_id int(11) NOT NULL AUTO_INCREMENT,
  Immi_type_id int(11) NOT NULL,
  start_date date NOT NULL,
  end_date date NOT NULL,
  Creation_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  Last_update_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  employee_id int(11) NOT NULL,
  PRIMARY KEY (Emp_imm_dtl_id),
  KEY employee_id (employee_id),
  CONSTRAINT emp_immigration_dtl_ibfk_1 FOREIGN KEY (employee_id) REFERENCES employee (employee_id),
  CONSTRAINT emp_immigration_dtl_ibfk_2 FOREIGN KEY (Immi_type_id) REFERENCES IMMIGRATION_TYPE (Immi_type_id)
);
/*Data for the table `emp_immigration_dtl` */

insert  into emp_immigration_dtl(Immi_type_Id,start_date,end_date,Creation_ts,Last_update_ts,employee_id) values 
((select Immi_type_id from immigration_type where immi_type_nm = 'H1B'),'2015-02-02','2018-02-02','2016-02-02 00:00:00','2016-02-02 00:00:00',(select employee_id from employee where email = 'empistream@gmail.com'));

/*Table structure for table `health_plan_dtl` */

DROP TABLE IF EXISTS health_plan_dtl;

CREATE TABLE health_plan_dtl (
  Plan_dtl_id int(11) NOT NULL AUTO_INCREMENT,
  Plan_type char(10) NOT NULL,
  Coverage_type char(10) DEFAULT NULL,
  Option_code char(10) DEFAULT NULL,
  plan_renew_mo char(2) NOT NULL,
  Montly_prem_amt decimal(9,2) NOT NULL,
  Creation_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  Last_update_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  Plan_opt_id int(11) NOT NULL,
  PRIMARY KEY (Plan_dtl_id),
  KEY Plan_opt_id (Plan_opt_id),
  CONSTRAINT health_plan_dtl_ibfk_1 FOREIGN KEY (Plan_opt_id) REFERENCES plan_option (Plan_opt_id)
);

/*Data for the table `health_plan_dtl` */

/*Table structure for table `project_task` */

DROP TABLE IF EXISTS project_task;

CREATE TABLE project_task (
  task_id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(100) DEFAULT NULL,
  Start_Date date NOT NULL,
  End_Date date DEFAULT NULL,
  Creation_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  Last_update_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  project_id int(11) NOT NULL,
  PRIMARY KEY (task_id),
  KEY project_id (project_id),
  CONSTRAINT project_task_ibfk_1 FOREIGN KEY (project_id) REFERENCES project (project_id)
);

/*Data for the table `project_task` */

/*Table structure for table `time_sheet` */

DROP TABLE IF EXISTS time_sheet;

CREATE TABLE time_sheet (
  Time_sheet_id int(11) NOT NULL AUTO_INCREMENT,
  Time_sheet_date date DEFAULT NULL,
  Billed_Hours int(11) NOT NULL,
  Non_Billed_Hours int(11) DEFAULT NULL,
  Note varchar(100) DEFAULT NULL,
  Employee_id int(11) DEFAULT NULL,
  Status char(2) DEFAULT NULL,
  project_id int(11) NOT NULL,
  Creation_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  Last_update_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  Description varchar(250) DEFAULT NULL,
  PRIMARY KEY (Time_sheet_id),
  KEY Approver_id (Employee_id),
  KEY project_id (project_id),
  CONSTRAINT time_sheet_ibfk_1 FOREIGN KEY (Employee_id) REFERENCES employee (employee_id),
  CONSTRAINT time_sheet_ibfk_2 FOREIGN KEY (project_id) REFERENCES project (project_id)
);

/*Data for the table `time_sheet` */

INSERT INTO time_sheet (Time_sheet_date,Billed_Hours,Non_Billed_Hours,Note,Employee_id,Status,project_id,Creation_ts,Last_update_ts,Description) VALUES 
('2016-01-01',8,1,'note',(select employee_id from employee where email = 'empistream@gmail.com'),'NA',(select project_id from project where prj_code='IHR'),'2016-07-05 22:23:37.000','2016-07-05 22:23:37.000','Worked on Client Masters module');

/*Table structure for table `time_sheet_task_as` */

DROP TABLE IF EXISTS time_sheet_task_as;

CREATE TABLE time_sheet_task_as (
  Timest_task_id int(11) NOT NULL AUTO_INCREMENT,
  task_id int(11) NOT NULL,
  time_sheet_id int(11) NOT NULL,
  Creation_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  Last_update_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (Timest_task_id),
  KEY task_id (task_id),
  KEY time_sheet_id (time_sheet_id),
  CONSTRAINT time_sheet_task_as_ibfk_1 FOREIGN KEY (task_id) REFERENCES project_task (task_id),
  CONSTRAINT time_sheet_task_as_ibfk_2 FOREIGN KEY (time_sheet_id) REFERENCES time_sheet (Time_sheet_id)
);

/*Data for the table `time_sheet_task_as` */

/*Table structure for table `timesheet_detail` */

DROP TABLE IF EXISTS timesheet_detail;

CREATE TABLE timesheet_detail (
  Timest_detail_id int(11) NOT NULL AUTO_INCREMENT,
  task_id int(11) NOT NULL,
  hours int(11) NOT NULL,
  Name varchar(100) DEFAULT NULL,
  date date NOT NULL,
  time_sheet_id int(11) NOT NULL,
  Note varchar(255) DEFAULT NULL,
  PRIMARY KEY (Timest_detail_id),
  KEY task_id_ibfk_1 (task_id),
  CONSTRAINT task_id_ibfk_1 FOREIGN KEY (task_id) REFERENCES project_task (task_id)
);

/*Data for the table `timesheet_detail` */

/*Table structure for table `user_account` */

DROP TABLE IF EXISTS user_account;

CREATE TABLE user_account (
  User_id int(11) NOT NULL AUTO_INCREMENT,
  Username varchar(100) NOT NULL,
  Role_id int(11) NOT NULL,
  Emp_id int(11) DEFAULT NULL,
  Client_id int(11) DEFAULT NULL,
  Creation_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  Last_update_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (User_id),
  KEY Emp_id (Emp_id),
  KEY Role_id (Role_id),
  CONSTRAINT user_account_ibfk_1 FOREIGN KEY (Emp_id) REFERENCES employee (employee_id),
  CONSTRAINT user_account_ibfk_2 FOREIGN KEY (Role_id) REFERENCES role (role_id)
);

/*Data for the table `user_account` */

INSERT INTO user_account (Username,Role_id,Emp_id,Client_id,Creation_ts,Last_update_ts) VALUES 
('tarunistream@gmail.com',(select role_id from role where role_name = 'ADMIN'),(select employee_id from employee where email = 'tarunistream@gmail.com'),1,'2016-06-30 00:00:00.000','2016-07-01 12:43:02.000')
,('empistream@gmail.com',(select role_id from role where role_name = 'EMPLOYEE'),(select employee_id from employee where email = 'empistream@gmail.com'),1,'2016-06-30 00:00:00.000','2016-07-01 12:44:15.000');

/*Table structure for table `EMP_DEPENDENT` */

DROP TABLE IF EXISTS EMP_DEPENDENT;

CREATE TABLE EMP_DEPENDENT (
  emp_dependent_id int(11) NOT NULL AUTO_INCREMENT,
  First_name varchar(60) NOT NULL,
  Last_name varchar(60) NOT NULL,
  Middle_name varchar(60) DEFAULT NULL,
  Email varchar(100) NOT NULL,
  Home_phone mediumtext,
  Mobile_phone mediumtext NOT NULL,
  Work_phone mediumtext,
  SSN varchar(11) DEFAULT NULL,
  Birth_date date DEFAULT NULL,
  Creation_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  Last_update_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  GENDER varchar(5) DEFAULT NULL,
  employee_id int(11) not null,
  PRIMARY KEY (emp_dependent_id),
  CONSTRAINT emp_dependent_ibfk_1 FOREIGN KEY (employee_id) REFERENCES employee (employee_id)
);

/*Data for the table EMP_DEPENDENT */

insert  into EMP_DEPENDENT(First_name,Last_name,Middle_name,Email,Home_phone,Mobile_phone,Work_phone,SSN,Birth_date,Creation_ts,Last_update_ts,GENDER, employee_id) values 
('Praveen','Akuthoti','','pravakhi@gmail.com',NULL,'9999999999',NULL,'****1111','1981-01-01','2016-05-21 15:40:20','2016-06-09 21:54:25','Male', (select employee_id from employee where email = 'empistream@gmail.com'));

/*Table structure for table `FOLDER` */

DROP TABLE IF EXISTS FOLDER;

CREATE TABLE FOLDER (
	FOLDER_ID int(11) NOT NULL AUTO_INCREMENT,
	FOLDER_NAME varchar(100) NOT NULL,
	PARENT_FOLDER_ID int(11),
	IMMI_TYPE_ID int(11),
	CREATION_TS timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 	LAST_UPDATE_TS timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  	CREATED_BY int(11) NOT NULL, 
  	UPDATED_BY int(11),
  	PRIMARY KEY (FOLDER_ID),
  	CONSTRAINT FOLDER_ibfk_1 FOREIGN KEY (CREATED_BY) REFERENCES employee (employee_id),
  	CONSTRAINT FOLDER_ibfk_2 FOREIGN KEY (UPDATED_BY) REFERENCES employee (employee_id)
);

/*Data for the table FOLDER */
  
insert into FOLDER (FOLDER_NAME, PARENT_FOLDER_ID, IMMI_TYPE_ID, CREATED_BY, UPDATED_BY) VALUES 
('I-797',NULL,(select immi_type_id from immigration_type where immi_type_nm = 'H1B'),(select employee_id from employee where email = 'tarunistream@gmail.com'),NULL),
('Offer Letter',NULL,(select immi_type_id from immigration_type where immi_type_nm = 'H1B'),(select employee_id from employee where email = 'tarunistream@gmail.com'),NULL),
('Employment Letter',NULL,(select immi_type_id from immigration_type where immi_type_nm = 'H1B'),(select employee_id from employee where email = 'tarunistream@gmail.com'),NULL),
('Resume',NULL,(select immi_type_id from immigration_type where immi_type_nm = 'H1B'),(select employee_id from employee where email = 'tarunistream@gmail.com'),NULL),
('I-140',NULL,(select immi_type_id from immigration_type where immi_type_nm = 'H1B'),(select employee_id from employee where email = 'tarunistream@gmail.com'),NULL),
('I-94',NULL,(select immi_type_id from immigration_type where immi_type_nm = 'H1B'),(select employee_id from employee where email = 'tarunistream@gmail.com'),NULL);

/*Table structure for table `DOCUMENT` */

DROP TABLE IF EXISTS DOCUMENT;

CREATE TABLE DOCUMENT (
	DOCUMENT_ID  int(11) NOT NULL AUTO_INCREMENT,
	DOCUMENT_UUID varchar(50) NOT NULL UNIQUE,
	DOCUMENT_NAME varchar(100) NOT NULL,
	DOCUMENT_TYPE VARCHAR(20),
	FOLDER_ID int(11),
	EMPLOYEE_ID INT(11),
	Creation_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 	Last_update_ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  	CREATED_BY  int(11) not null,
  	UPDATED_BY int(11) not null,
    PRIMARY KEY (DOCUMENT_ID),
  	CONSTRAINT DOCUMENT_ibfk_1 FOREIGN KEY (CREATED_BY) REFERENCES employee (employee_id),
  	CONSTRAINT DOCUMENT_ibfk_2 FOREIGN KEY (UPDATED_BY) REFERENCES employee (employee_id),
  	CONSTRAINT DOCUMENT_ibfk_3 FOREIGN KEY (EMPLOYEE_ID) REFERENCES employee (employee_id)
);

commit;