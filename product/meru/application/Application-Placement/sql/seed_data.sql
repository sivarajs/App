
-- delete from sec_resource_security;

-- insert into sec_resource_security(id, resource_pattern, resource_type, access_type, is_protected) values(1, '/i2par/admin+.*', 'URI', NULL, 'Y');
-- insert into sec_resource_security(id, resource_pattern, resource_type, access_type, is_protected) values(2, '/i2par/campus+.*', 'URI', NULL, 'Y');
-- insert into sec_resource_security(id, resource_pattern, resource_type, access_type, is_protected) values(3, '/i2par/employer+.*', 'URI', NULL, 'Y');
-- insert into sec_resource_security(id, resource_pattern, resource_type, access_type, is_protected) values(4, '/i2par/student+.*', 'URI', NULL, 'Y');

-- delete from sec_resource_permission;

-- insert into sec_resource_permission(id, resource_security_id, role_id) values(1, 1, 1);
-- insert into sec_resource_permission(id, resource_security_id, role_id) values(2, 2, 4);
-- insert into sec_resource_permission(id, resource_security_id, role_id) values(3, 3, 7);
-- insert into sec_resource_permission(id, resource_security_id, role_id) values(4, 4, 6);

delete from core_property;

insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(-1, 100,-1, NULL, 'app.config', 'price-format', 'string',  '#.##');

insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(1, 100,-1, 'admin', 'campus-placement', 'max-offers', 'int',  '2');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(2, 100,-1, 'admin', 'campus-placement', 'registration-clause', 'string', 'A student can appear for placements till he gets #{No of Offers} Offer This registration is valid between #{Placement Period}');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(3, 100,-1, 'admin', 'campus-placement', 'registration-notification', 'string', 'Registration closes on #{Due date} Time #{Due time}');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(4, 100,-1, 'admin', 'campus-placement', 'registration-id-format', 'string', '#{Campus.documentPrefix}RG#{Number}');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(5, 100,-1, 'admin', 'campus-placement', 'rfp-id-format', 'string', '#{Campus.documentPrefix}RF#{Number}');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(6, 100,-1, 'admin', 'campus-placement', 'rfp-response-id-format', 'string', '#{Campus.documentPrefix}RR#{Number}');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(7, 100,-1, 'admin', 'campus-placement', 'schedule-id-format', 'string', '#{Campus.documentPrefix}SH#{Number}');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(8, 100,-1, 'admin', 'campus-placement', 'document-id-length', 'int', '10');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(9, 100,-1, 'admin', 'campus-placement', 'employer-rfp-response-id-length', 'int', '4');


insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(51, 100,-1, 'admin', 'mail', 'smtp.host', 'string', 'smtpout.asia.secureserver.net');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(52, 100,-1, 'admin', 'mail', 'smtp.port', 'int', '465');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(53, 100,-1, 'admin', 'mail', 'smtp.auth', 'boolean', 'true');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(54, 100,-1, 'admin', 'mail', 'smtp.connectiontimeout', 'int', '60000');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(55, 100,-1, 'admin', 'mail', 'smtp.timeout', 'int', '60000');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(56, 100,-1, 'admin', 'mail', 'smtp.starttls.enable', 'boolean', 'true');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(57, 100,-1, 'admin', 'mail', 'smtp.socketFactory.port', 'int', '465');

insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(58, 100,-1, 'admin', 'mail', 'user.name', 'string', 'I2Par Admin');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(59, 100,-1, 'admin', 'mail', 'user.email', 'string', 'admin@i2par.com');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(60, 100,-1, 'admin', 'mail', 'user.password', 'string', 'admin123');

delete from core_property_group;

insert into core_property_group(id, app_id, client_id, name, value) values(1, 100, -1, 'institute-type', 'Autonomous-Private/Autonomous-Public');
insert into core_property_group(id, app_id, client_id, name, value) values(2, 100, -1, 'institute-type', 'Government Aid');
insert into core_property_group(id, app_id, client_id, name, value) values(3, 100, -1, 'institute-type', 'Private');
insert into core_property_group(id, app_id, client_id, name, value) values(4, 100, -1, 'institute-type', 'Public');


insert into core_property_group(id, app_id, client_id, name, value) values(11, 100, -1, 'degree-type', 'Single Degree');
insert into core_property_group(id, app_id, client_id, name, value) values(12, 100, -1, 'degree-type', 'Double Degree');
insert into core_property_group(id, app_id, client_id, name, value) values(13, 100, -1, 'degree-type', 'Integrated First Degree');
insert into core_property_group(id, app_id, client_id, name, value) values(14, 100, -1, 'degree-type', 'Masters');
insert into core_property_group(id, app_id, client_id, name, value) values(15, 100, -1, 'degree-type', 'Doctorate');

insert into core_property_group(id, app_id, client_id, name, value) values(21, 100, -1, 'education-pattern', 'Period');
insert into core_property_group(id, app_id, client_id, name, value) values(22, 100, -1, 'education-pattern', 'Semester');
insert into core_property_group(id, app_id, client_id, name, value) values(23, 100, -1, 'education-pattern', 'Yearly');

insert into core_property_group(id, app_id, client_id, name, value) values(31, 100, -1, 'education-type', 'On Campus-Full time');
insert into core_property_group(id, app_id, client_id, name, value) values(32, 100, -1, 'education-type', 'On Campus-Part time');
insert into core_property_group(id, app_id, client_id, name, value) values(33, 100, -1, 'education-type', 'Distance Learning');

insert into core_property_group(id, app_id, client_id, name, value) values(41, 100, -1, 'selection-mode', 'Academic Merit');
insert into core_property_group(id, app_id, client_id, name, value) values(42, 100, -1, 'selection-mode', 'Entrance Test');
insert into core_property_group(id, app_id, client_id, name, value) values(43, 100, -1, 'selection-mode', 'Both');


insert into core_property_group(id, app_id, client_id, name, value) values(51, 100, -1, 'evaluation-type', 'CGPA');
insert into core_property_group(id, app_id, client_id, name, value) values(52, 100, -1, 'evaluation-type', 'Percentage');

insert into core_property_group(id, app_id, client_id, name, value) values(61, 100, -1, 'placement-category', 'On-Campus');
insert into core_property_group(id, app_id, client_id, name, value) values(62, 100, -1, 'placement-category', 'Off-Campus');

insert into core_property_group(id, app_id, client_id, name, value) values(71, 100, -1, 'placement-orientation', 'Inland');
insert into core_property_group(id, app_id, client_id, name, value) values(72, 100, -1, 'placement-orientation', 'Non-Inland');

insert into core_property_group(id, app_id, client_id, name, value) values(81, 100, -1, 'internship-type', 'Collaboration Progarm');
insert into core_property_group(id, app_id, client_id, name, value) values(82, 100, -1, 'internship-type', 'Individual Based');

insert into core_property_group(id, app_id, client_id, name, value) values(91, 100, -1, 'time-unit', 'hours');
insert into core_property_group(id, app_id, client_id, name, value) values(92, 100, -1, 'time-unit', 'days');
insert into core_property_group(id, app_id, client_id, name, value) values(93, 100, -1, 'time-unit', 'months');
insert into core_property_group(id, app_id, client_id, name, value) values(94, 100, -1, 'time-unit', 'years');

insert into core_property_group(id, app_id, client_id, name, value) values(111, 100, -1, 'dual-degree-type', 'First');
insert into core_property_group(id, app_id, client_id, name, value) values(112, 100, -1, 'dual-degree-type', 'Second');

insert into core_property_group(id, app_id, client_id, name, value) values(121, 100, -1, 'preferred-placement', 'Discipline Centric');
insert into core_property_group(id, app_id, client_id, name, value) values(122, 100, -1, 'preferred-placement', 'Open Discipline');


insert into core_property_group(id, app_id, client_id, name, value) values(301, 100, -1, 'campus-staff-type', 'Faculty');
insert into core_property_group(id, app_id, client_id, name, value) values(302, 100, -1, 'campus-staff-type', 'Student');
insert into core_property_group(id, app_id, client_id, name, value) values(303, 100, -1, 'campus-staff-type', 'Others');

insert into core_property_group(id, app_id, client_id, name, value) values(311, 100, -1, 'campus-contact-type', 'Placement Officer');
insert into core_property_group(id, app_id, client_id, name, value) values(312, 100, -1, 'campus-contact-type', 'Placement Assistant');

insert into core_property_group(id, app_id, client_id, name, value) values(351, 100, -1, 'degree', 'BE');
insert into core_property_group(id, app_id, client_id, name, value) values(352, 100, -1, 'degree', 'MCA');
insert into core_property_group(id, app_id, client_id, name, value) values(353, 100, -1, 'degree', 'ME');

insert into core_property_group(id, app_id, client_id, name, value) values(371, 100, -1, 'degree-discipline', 'Computer Science');
insert into core_property_group(id, app_id, client_id, name, value) values(372, 100, -1, 'degree-discipline', 'Electrical & Electronics');


insert into core_property_group(id, app_id, client_id, name, value) values(401, 100, -1, 'employer-contact-type', 'HR');
insert into core_property_group(id, app_id, client_id, name, value) values(402, 100, -1, 'employer-contact-type', 'Placement Coordinator');

insert into core_property_group(id, app_id, client_id, name, value) values(411, 100, -1, 'selection-type', 'Final Offer');
insert into core_property_group(id, app_id, client_id, name, value) values(412, 100, -1, 'selection-type', 'Shortlisting');

insert into core_property_group(id, app_id, client_id, name, value) values(541, 100, -1, 'placement-hierarchy-state', 'New');
insert into core_property_group(id, app_id, client_id, name, value) values(542, 100, -1, 'placement-hierarchy-state', 'Active');
insert into core_property_group(id, app_id, client_id, name, value) values(543, 100, -1, 'placement-hierarchy-state', 'Expired');

insert into core_property_group(id, app_id, client_id, name, value) values(501, 100, -1, 'registration-state', 'New');
insert into core_property_group(id, app_id, client_id, name, value) values(502, 100, -1, 'registration-state', 'Rolled Out');
insert into core_property_group(id, app_id, client_id, name, value) values(503, 100, -1, 'registration-state', 'Suspended');
insert into core_property_group(id, app_id, client_id, name, value) values(504, 100, -1, 'registration-state', 'Extended');
insert into core_property_group(id, app_id, client_id, name, value) values(505, 100, -1, 'registration-state', 'Closed');
insert into core_property_group(id, app_id, client_id, name, value) values(506, 100, -1, 'registration-state', 'Expired');

insert into core_property_group(id, app_id, client_id, name, value) values(551, 100, -1, 'student-registration-state', 'New');
insert into core_property_group(id, app_id, client_id, name, value) values(552, 100, -1, 'student-registration-state', 'Viewed');
insert into core_property_group(id, app_id, client_id, name, value) values(553, 100, -1, 'student-registration-state', 'Blocked');
insert into core_property_group(id, app_id, client_id, name, value) values(554, 100, -1, 'student-registration-state', 'Extended');
insert into core_property_group(id, app_id, client_id, name, value) values(555, 100, -1, 'student-registration-state', 'Open');
insert into core_property_group(id, app_id, client_id, name, value) values(556, 100, -1, 'student-registration-state', 'Registered');
insert into core_property_group(id, app_id, client_id, name, value) values(557, 100, -1, 'student-registration-state', 'Rejected');
insert into core_property_group(id, app_id, client_id, name, value) values(558, 100, -1, 'student-registration-state', 'Not Eligible');


insert into core_property_group(id, app_id, client_id, name, value) values(601, 100, -1, 'rfp-state', 'New');
insert into core_property_group(id, app_id, client_id, name, value) values(602, 100, -1, 'rfp-state', 'Dispatched');
insert into core_property_group(id, app_id, client_id, name, value) values(603, 100, -1, 'rfp-state', 'Closed');
insert into core_property_group(id, app_id, client_id, name, value) values(604, 100, -1, 'rfp-state', 'Expired');

insert into core_property_group(id, app_id, client_id, name, value) values(651, 100, -1, 'rfp-response-state', 'New');
insert into core_property_group(id, app_id, client_id, name, value) values(652, 100, -1, 'rfp-response-state', 'Accepted');
insert into core_property_group(id, app_id, client_id, name, value) values(653, 100, -1, 'rfp-response-state', 'Rejected');

insert into core_property_group(id, app_id, client_id, name, value) values(701, 100, -1, 'placement-schedule-state', 'New');
insert into core_property_group(id, app_id, client_id, name, value) values(702, 100, -1, 'placement-schedule-state', 'Dispatched');
insert into core_property_group(id, app_id, client_id, name, value) values(703, 100, -1, 'placement-schedule-state', 'Closed');
insert into core_property_group(id, app_id, client_id, name, value) values(704, 100, -1, 'placement-schedule-state', 'Aborted');
insert into core_property_group(id, app_id, client_id, name, value) values(705, 100, -1, 'placement-schedule-state', 'Expired');

insert into core_property_group(id, app_id, client_id, name, value) values(751, 100, -1, 'interview-type', 'PPT');
insert into core_property_group(id, app_id, client_id, name, value) values(752, 100, -1, 'interview-type', 'Interview/Test');
insert into core_property_group(id, app_id, client_id, name, value) values(753, 100, -1, 'interview-type', 'Shortlisting/Briefing');
insert into core_property_group(id, app_id, client_id, name, value) values(754, 100, -1, 'interview-type', 'Fitment');

insert into core_property_group(id, app_id, client_id, name, value) values(811, 100, -1, 'employer-schedule-state', 'New');
insert into core_property_group(id, app_id, client_id, name, value) values(812, 100, -1, 'employer-schedule-state', 'In Process');
insert into core_property_group(id, app_id, client_id, name, value) values(813, 100, -1, 'employer-schedule-state', 'Published To Campus');
insert into core_property_group(id, app_id, client_id, name, value) values(814, 100, -1, 'employer-schedule-state', 'Published To Students');

insert into core_property_group(id, app_id, client_id, name, value) values(821, 100, -1, 'employer-interview-state', 'New');
insert into core_property_group(id, app_id, client_id, name, value) values(822, 100, -1, 'employer-interview-state', 'Released');
insert into core_property_group(id, app_id, client_id, name, value) values(823, 100, -1, 'employer-interview-state', 'Accepted');
insert into core_property_group(id, app_id, client_id, name, value) values(824, 100, -1, 'employer-interview-state', 'Rejected');
insert into core_property_group(id, app_id, client_id, name, value) values(825, 100, -1, 'employer-interview-state', 'In Process');

insert into core_property_group(id, app_id, client_id, name, value) values(1500, 100, -1, 'student-interview-result', 'New');
insert into core_property_group(id, app_id, client_id, name, value) values(1501, 100, -1, 'student-interview-result', 'Selected');
insert into core_property_group(id, app_id, client_id, name, value) values(1502, 100, -1, 'student-interview-result', 'Rejected');
insert into core_property_group(id, app_id, client_id, name, value) values(1503, 100, -1, 'student-interview-result', 'OnHold');

insert into core_property_group(id, app_id, client_id, name, value) values(771, 100, -1, 'sex', 'Male');
insert into core_property_group(id, app_id, client_id, name, value) values(772, 100, -1, 'sex', 'Female');



insert into core_property_group(id, app_id, client_id, name, value) values(1001, 100, -1, 'employer-state', 'Temporary');
insert into core_property_group(id, app_id, client_id, name, value) values(1002, 100, -1, 'employer-state', 'Standard');
insert into core_property_group(id, app_id, client_id, name, value) values(1003, 100, -1, 'employer-state', 'Active');


delete from core_app_hierarchical_entity;

insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind) values(1,100,-1,NULL,'app-setup-menu','General Setup','module');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind) values(2,100,-1,NULL,'app-setup-menu','Security','module');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind) values(3,100,-1,NULL,'app-setup-menu','Administration Data','module');

insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(21,100,-1,1,'app-setup-menu','Placement','entity','admin/config/Placement.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(22,100,-1,1,'app-setup-menu','Email','entity','admin/config/Email.xhtml');

insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(31,100,-1,2,'app-setup-menu','Role','entity','admin/security/Role.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(32,100,-1,2,'app-setup-menu','User','entity','admin/security/User.xhtml');

insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(35,100,-1,3,'app-setup-menu','University','entity','admin/campus/University.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(36,100,-1,3,'app-setup-menu','Campus','entity','admin/campus/Campus.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(37,100,-1,3,'app-setup-menu','Employer','entity','admin/employer/Employer.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(38,100,-1,3,'app-setup-menu','Institute Type','entity','admin/campus/InstituteType.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(39,100,-1,3,'app-setup-menu','Degree Type','entity','admin/campus/DegreeType.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(40,100,-1,3,'app-setup-menu','Degree','entity','admin/campus/Degree.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(41,100,-1,3,'app-setup-menu','Discipline','entity','admin/campus/Discipline.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(42,100,-1,3,'app-setup-menu','Education Type','entity','admin/campus/EducationType.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(43,100,-1,3,'app-setup-menu','Selection Mode','entity','admin/campus/SelectionMode.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(44,100,-1,3,'app-setup-menu','Evaluation Type','entity','admin/campus/EvaluationType.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(45,100,-1,3,'app-setup-menu','Education Pattern','entity','admin/campus/EducationPattern.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(46,100,-1,3,'app-setup-menu','Placement Category','entity','admin/campus/PlacementCategory.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(47,100,-1,3,'app-setup-menu','Placement Orientation','entity','admin/campus/PlacementOrientation.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(48,100,-1,3,'app-setup-menu','Internship Type','entity','admin/campus/InternshipType.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(49,100,-1,3,'app-setup-menu','Time Unit','entity','admin/campus/TimeUnit.xhtml');


insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind) values(101,100,-1,NULL,'app-module-menu','Master Data Management','module');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind) values(102,100,-1,NULL,'app-module-menu','Campus Recruitment','module');


insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(121,100,-1,101,'app-module-menu','Campus','entity','admin/campus/Campus.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(122,100,-1,101,'app-module-menu','Employer','entity','admin/employer/Employer.xhtml');

insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(151,100,-1,102,'app-module-menu','Placement','entity','campus/registration/Placement.xhtml');




insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind) values(201, 100,-1, 'campus-admin', NULL, 'app-setup-menu','General Setup','module');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind) values(202, 100,-1, 'campus-admin', NULL, 'app-setup-menu','Administration Data','module');

insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(211, 100,-1, 'campus-admin',201,'app-setup-menu','Placement','entity','campus/config/Placement.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(212, 100,-1, 'campus-admin',201,'app-setup-menu','Email','entity','campus/config/Email.xhtml');

insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(221, 100,-1, 'campus-admin',202,'app-setup-menu','Campus','entity','campus/setup/Campus.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(222, 100,-1, 'campus-admin',202,'app-setup-menu','User','entity','campus/setup/User.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(223, 100,-1, 'campus-admin',202,'app-setup-menu','Course Curriculum','entity','campus/setup/Course.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(224, 100,-1, 'campus-admin',202,'app-setup-menu','Campus Program','entity','campus/setup/CampusProgram.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(225, 100,-1, 'campus-admin',202,'app-setup-menu','Venue','entity','campus/setup/CampusVenue.xhtml');

insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind) values(501, 100,-1, 'campus-admin',  NULL,'app-module-menu','Master Data Management','module');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind) values(502, 100,-1, 'campus-admin', NULL,'app-module-menu','Placement Administration','module');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind) values(503, 100,-1, 'campus-admin', NULL,'app-module-menu','Placement Management','module');

insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(516, 100,-1, 'campus-admin',501,'app-module-menu','Employer','entity','campus/employer/Employer.xhtml');

insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(531, 100,-1, 'campus-admin',502,'app-module-menu','Placement Hierarchy','entity','campus/placement/Placement.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(532, 100,-1, 'campus-admin',502,'app-module-menu','Registration','entity','campus/placement/Registration.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(533, 100,-1, 'campus-admin',502,'app-module-menu','Registration Review','entity','campus/placement/RegistrationReview.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(534, 100,-1, 'campus-admin',502,'app-module-menu','RFP','entity','campus/placement/rfp/RFP.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(535, 100,-1, 'campus-admin',502,'app-module-menu','RFP Response','entity','campus/placement/rfp/RFPResponse.xhtml');

insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(601, 100,-1, 'campus-admin',503,'app-module-menu','Placement Schedule','entity','campus/placement/schedule/PlacementSchedule.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(602, 100,-1, 'campus-admin',503,'app-module-menu','Placement Event','entity','campus/placement/schedule/PlacementEvent.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(603, 100,-1, 'campus-admin',503,'app-module-menu','Interview Run','entity','campus/placement/interview/InterviewRun.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(604, 100,-1, 'campus-admin',503,'app-module-menu','Interview Result','entity','campus/placement/interview/InterviewResult.xhtml');

insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind) values(1001, 100,-1, 'employer',  NULL,'app-module-menu','Master Data Management','module');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind) values(1002, 100,-1, 'employer', NULL,'app-module-menu','Campus Placement','module');

insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(1051, 100,-1, 'employer',1001,'app-module-menu','Employer','entity','employer/Employer.xhtml');

insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(1071, 100,-1, 'employer',1002,'app-module-menu','RFP','entity','employer/placement/RFPResponse.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(1072, 100,-1, 'employer',1002,'app-module-menu','Placement Schedule','entity','employer/placement/PlacementSchedule.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(1073, 100,-1, 'employer',1002,'app-module-menu','Interview Run','entity','employer/placement/InterviewRun.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(1074, 100,-1, 'employer',1002,'app-module-menu','Interview Result','entity','employer/placement/InterviewResult.xhtml');


insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind) values(1501, 100,-1, 'student', NULL,'app-module-menu','Campus Placement','module');

insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(1511, 100,-1, 'student',  1501,'app-module-menu','Registration','entity','student/Registration.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, category, parent_id, type, name, kind, action) values(1512, 100,-1, 'student',  1501,'app-module-menu','Interview Run','entity','student/InterviewRun.xhtml');




delete from core_country;
insert into core_country(id, app_id, client_id, name, code) values(1, 0, -1, 'India', 'in');

delete from core_state;
insert into core_state(id, app_id, client_id, name, country_id, code) values(18, 0, -1, 'Karnataka', 1, 'KA');
insert into core_state(id, app_id, client_id, name, country_id, code) values(19, 0, -1, 'Rajasthan', 1, 'RJ');

delete from core_city;
insert into core_city(id, app_id, client_id, name, country_id, state_id) values(1, 0, -1, 'Bangalore', 1, 18);
insert into core_city(id, app_id, client_id, name, country_id, state_id) values(10, 0, -1, 'Pilani', 1, 18);

delete from core_city_area;
insert into core_city_area(id, app_id, client_id, name, city_id) values(1, 0, -1, 'BTM Layout 1st Stage', 1);



delete from core_schedule;
insert into core_schedule(id, app_id, client_id, name, action_class, type, start_time, end_time, trigger_on_start, schedule_trigger_id) values(1, 100,-1, 'Email Dispatch', 'meru.app.service.schedule.job.EmailScheduleJob', 'second', NULL, NULL, 'Y', 1);
insert into core_schedule(id, app_id, client_id, name, action_class, type, start_time, end_time, trigger_on_start, schedule_trigger_id) values(3, 100,-1, 'Placement State Update', 'i2par.placement.schedule.job.PlacementScheduleJob', 'day', NULL, NULL, 'Y', 3);

delete from core_schedule_trigger;
insert into core_schedule_trigger(id, app_id, client_id, frequency) values(1, 100,-1, 3);
insert into core_schedule_trigger(id, app_id, client_id, at_time) values(3, 100,-1, '0 59 23 * * ?');

