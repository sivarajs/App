delete from sec_secured_application;
delete from sec_role;
delete from sec_user;
delete from core_property;

-- Account
insert into sec_secured_application (id, name, uid, root) values(-1, "Account", "account", '/account');
insert into sec_role (id, app_id, client_id, name, home) values(-1, -1, -1, 'admin','app');
insert into sec_user (id, app_id, client_id, name, password, primary_role_id,state) values(1, -1, -1, 'admin', '547f28502d56778be1ad038c941b1917', -1, 'A');

-- I2par
insert into sec_secured_application (id, name, uid, root) values(100, "i2par", "i2par", '/i2par');
insert into sec_role (id, app_id, client_id, name, home) values(101, 100, -1, 'Super User','admin/');
insert into sec_role (id, app_id, client_id, name, home) values(102, 100, -1, 'System Admin','sys/');

insert into sec_role (id, app_id, client_id, name, home) values(111, 100, -1, 'Campus Placement Officer','campus/');
insert into sec_role (id, app_id, client_id, name, home) values(112, 100, -1, 'Campus Placement Assistant','campusa/');
insert into sec_role (id, app_id, client_id, name, home) values(113, 100, -1, 'Student','student/');

insert into sec_role (id, app_id, client_id, name, home) values(121, 100, -1, 'Employer Admin','employer/');
insert into sec_role (id, app_id, client_id, name, home) values(122, 100, -1, 'HR PO','employer/');
insert into sec_role (id, app_id, client_id, name, home) values(123, 100, -1, 'HR PA','employer/');
insert into sec_role (id, app_id, client_id, name, home) values(124, 100, -1, 'Interviewer','employer/');

insert into sec_user (id, app_id, client_id, name, password, primary_role_id,state) values(101, 100, -1, 'super', 'd32517be09c2c14f2e3e158d20162573', 101, 'A');

-- ecom
insert into sec_secured_application (id, name, uid, root) values(200, "ns", "ns", '/ns');
insert into sec_role (id, app_id, client_id, name, home) values(201, 200, -1, 'admin','app/');
insert into sec_role (id, app_id, client_id, name, home) values(202, 200, -1, 'customer','customer/');



insert into core_property(id, app_id, client_id, prefix, name, type, value) values(51, -1, -1, 'mail', 'smtp.host', 'string', 'smtpout.asia.secureserver.net');
-- insert into core_property(id, app_id,  client_id, prefix, name, type, value) values(51, -1, 0, 'admin', 'mail', 'smtp.host', 'string', 'smtp.gmail.com');
insert into core_property(id, app_id, client_id, prefix, name, type, value) values(52, -1, -1, 'mail', 'smtp.port', 'int', '465');
insert into core_property(id, app_id, client_id, prefix, name, type, value) values(53, -1, -1, 'mail', 'smtp.auth', 'boolean', 'true');
insert into core_property(id, app_id, client_id, prefix, name, type, value) values(54, -1, -1, 'mail', 'smtp.connectiontimeout', 'int', '60000');
insert into core_property(id, app_id, client_id, prefix, name, type, value) values(55, -1, -1, 'mail', 'smtp.timeout', 'int', '60000');
insert into core_property(id, app_id, client_id, prefix, name, type, value) values(56, -1, -1, 'mail', 'smtp.starttls.enable', 'boolean', 'true');
insert into core_property(id, app_id, client_id, prefix, name, type, value) values(57, -1, -1, 'mail', 'smtp.socketFactory.port', 'int', '465');

insert into core_property(id, app_id, client_id, prefix, name, type, value) values(58, -1, -1, 'mail', 'user.name', 'string', 'I2Par Admin');
insert into core_property(id, app_id, client_id, prefix, name, type, value) values(59, -1, -1, 'mail', 'user.email', 'string', 'admin@i2par.com');
insert into core_property(id, app_id, client_id, prefix, name, type, value) values(60, -1, -1, 'mail', 'user.password', 'string', 'admin123');


delete from core_app_hierarchical_entity;

insert into core_app_hierarchical_entity(id, parent_id,  app_id, client_id, type, name, kind) values(1,NULL, -1, -1, 'app-setup-menu','General','module');
insert into core_app_hierarchical_entity(id, parent_id,  app_id, client_id, type, name, kind) values(2,NULL, -1, -1, 'app-setup-menu','Property Group','module');

insert into core_app_hierarchical_entity(id, parent_id,  app_id, client_id, type, name, kind, action) values(11,1, -1, -1, 'app-setup-menu','Email','entity','app/setup/config/Email.xhtml');


delete from core_schedule;
insert into core_schedule(id, app_id, client_id, name, action_class, type, start_time, end_time, trigger_on_start, schedule_trigger_id) values(1, 100,-1, 'Email Dispatch', 'meru.app.service.schedule.job.EmailScheduleJob', 'second', NULL, NULL, 'Y', 1);

delete from core_schedule_trigger;
insert into core_schedule_trigger(id, app_id, client_id, frequency) values(1, 100,-1, 3);


