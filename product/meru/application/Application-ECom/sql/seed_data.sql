delete from core_property;

insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(-1, 200,-1, NULL, 'app.config', 'price-format', 'string',  '#.##');

insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(1, 200,-1, 'admin', 'campus-placement', 'max-offers', 'int',  '2');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(2, 200,-1, 'admin', 'campus-placement', 'registration-clause', 'string', 'A student can appear for placements till he gets #{No of Offers} Offer This registration is valid between #{Placement Period}');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(3, 200,-1, 'admin', 'campus-placement', 'registration-notification', 'string', 'Registration closes on #{Due date} Time #{Due time}');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(4, 200,-1, 'admin', 'campus-placement', 'registration-id-format', 'string', '#{Campus.documentPrefix}RG#{Number}');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(5, 200,-1, 'admin', 'campus-placement', 'rfp-id-format', 'string', '#{Campus.documentPrefix}RF#{Number}');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(6, 200,-1, 'admin', 'campus-placement', 'rfp-response-id-format', 'string', '#{Campus.documentPrefix}RR#{Number}');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(7, 200,-1, 'admin', 'campus-placement', 'schedule-id-format', 'string', '#{Campus.documentPrefix}SH#{Number}');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(8, 200,-1, 'admin', 'campus-placement', 'document-id-length', 'int', '10');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(9, 200,-1, 'admin', 'campus-placement', 'employer-rfp-response-id-length', 'int', '4');


insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(51, 200,-1, 'admin', 'mail', 'smtp.host', 'string', 'smtpout.asia.secureserver.net');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(52, 200,-1, 'admin', 'mail', 'smtp.port', 'int', '465');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(53, 200,-1, 'admin', 'mail', 'smtp.auth', 'boolean', 'true');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(54, 200,-1, 'admin', 'mail', 'smtp.connectiontimeout', 'int', '60000');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(55, 200,-1, 'admin', 'mail', 'smtp.timeout', 'int', '60000');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(56, 200,-1, 'admin', 'mail', 'smtp.starttls.enable', 'boolean', 'true');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(57, 200,-1, 'admin', 'mail', 'smtp.socketFactory.port', 'int', '465');

insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(58, 200,-1, 'admin', 'mail', 'user.name', 'string', 'I2Par Admin');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(59, 200,-1, 'admin', 'mail', 'user.email', 'string', 'admin@i2par.com');
insert into core_property(id, app_id, client_id, category, prefix, name, type, value) values(60, 200,-1, 'admin', 'mail', 'user.password', 'string', 'admin123');


delete from core_property_group;
insert into core_property_group(id, app_id, client_id, name, value) values(1, 200,-1, 'attribute-feature-type', 'Text');
insert into core_property_group(id, app_id, client_id, name, value) values(2, 200,-1, 'attribute-feature-type', 'List');
insert into core_property_group(id, app_id, client_id, name, value) values(3, 200,-1, 'attribute-feature-type', 'Map');
insert into core_property_group(id, app_id, client_id, name, value) values(4, 200,-1, 'attribute-feature-type', 'Table');

insert into core_property_group(id, app_id, client_id, name, value) values(11, 200,-1, 'unit-of-measure', 'gm');
insert into core_property_group(id, app_id, client_id, name, value) values(12, 200,-1, 'unit-of-measure', 'pc');

insert into core_property_group(id, app_id, client_id, name, value) values(31, 200,-1, 'package-type', 'Pouch');
insert into core_property_group(id, app_id, client_id, name, value) values(32, 200,-1, 'package-type', 'Bottle');
insert into core_property_group(id, app_id, client_id, name, value) values(33, 200,-1, 'package-type', 'Jar');
insert into core_property_group(id, app_id, client_id, name, value) values(34, 200,-1, 'package-type', 'Tin');
insert into core_property_group(id, app_id, client_id, name, value) values(35, 200,-1, 'package-type', 'Pack');
insert into core_property_group(id, app_id, client_id, name, value) values(36, 200,-1, 'package-type', 'Can');

insert into core_property_group(id, app_id, client_id, name, value) values(71, 200,-1, 'discount-type', '%');


insert into core_property_group(id, app_id, client_id, name, value) values(81, 200,-1, 'subscription-frequency', 'Daily');
insert into core_property_group(id, app_id, client_id, name, value) values(82, 200,-1, 'subscription-frequency', 'Weekly');
insert into core_property_group(id, app_id, client_id, name, value) values(83, 200,-1, 'subscription-frequency', 'Bi-Weekly');
insert into core_property_group(id, app_id, client_id, name, value) values(84, 200,-1, 'subscription-frequency', 'Monthly');


delete from core_app_hierarchical_entity;

insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind) values(1, 200, -1, NULL,'app-setup-menu','General Setup','module');
-- insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind) values(2,1, NULL,'app-setup-menu','Security','module');

insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(21, 200, -1,1,'app-setup-menu','App Configuration','entity','app/setup/AppConfig.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(22, 200, -1,1,'app-setup-menu','Email','entity','app/setup/Email.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(23, 200, -1,1,'app-setup-menu','SMS','entity','app/setup/SMS.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(24, 200, -1,1,'app-setup-menu','Payment Gateway','entity','app/setup/PaymentGateway.xhtml');


-- insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(31,1, 2,'app-setup-menu','Role','entity','app/security/Role.xhtml');
-- insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(32,1, 2,'app-setup-menu','User','entity','app/security/User.xhtml');


insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind) values(101,200, -1, NULL,'app-module-menu','Master Data Management','module');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind) values(102,200, -1, NULL,'app-module-menu','Financial Management','module');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind) values(103,200, -1, NULL,'app-module-menu','Inventory Management','module');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind) values(104,200, -1, NULL,'app-module-menu','Sales Management','module');

insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(121,200, -1, 101,'app-module-menu','Product Category','entity','app/module/mdm/catalog/ProductCategory.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(122,200, -1, 101,'app-module-menu','Product','entity','app/module/mdm/catalog/Product.xhtml');

insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(401,200, -1, 104,'app-module-menu','Sales Order','entity','app/module/sales/SalesOrder.xhtml');
insert into core_app_hierarchical_entity(id, app_id, client_id, parent_id, type, name, kind, action) values(402,200, -1, 104,'app-module-menu','Sales Invoice','entity','app/module/sales/SalesInvoice.xhtml');

delete from core_app_entity_sequence;
insert into core_app_entity_sequence(id, app_id, client_id, name, value) values(1, 200, -1, 'SalesOrder.Id',1);
insert into core_app_entity_sequence(id, app_id, client_id, name, value) values(2, 200, -1, 'InvoiceOrder.Id',1);
insert into core_app_entity_sequence(id, app_id, client_id, name, value) values(3, 200, -1, 'PaymentGateway.Id',1);


delete from core_app_entity_state;

insert into core_app_entity_state(id, app_id, client_id, entity, code, status) values(1, 200, -1, "SalesOrder", 1, "New");
insert into core_app_entity_state(id, app_id, client_id, entity, code, status) values(2, 200, -1, "SalesOrder", 2, "Pending Payment");
insert into core_app_entity_state(id, app_id, client_id, entity, code, status) values(3, 200, -1, "SalesOrder", 3, "Customer Submitted");
insert into core_app_entity_state(id, app_id, client_id, entity, code, status) values(4, 200, -1, "SalesOrder", 4, "In-Process");
insert into core_app_entity_state(id, app_id, client_id, entity, code, status) values(5, 200, -1, "SalesOrder", 5, "Pending Fulfilment");
insert into core_app_entity_state(id, app_id, client_id, entity, code, status) values(6, 200, -1, "SalesOrder", 6, "Shipped");
insert into core_app_entity_state(id, app_id, client_id, entity, code, status) values(7, 200, -1, "SalesOrder", 7, "Delivered");
insert into core_app_entity_state(id, app_id, client_id, entity, code, status) values(8, 200, -1, "SalesOrder", 8, "Completed");
insert into core_app_entity_state(id, app_id, client_id, entity, code, status) values(9, 200, -1, "SalesOrder", 9, "Customer Cancelled");
insert into core_app_entity_state(id, app_id, client_id, entity, code, status) values(10, 200, -1, "SalesOrder", 10, "Cancelled");

delete from core_country;
insert into core_country(id, app_id, client_id, name, code) values(1, 200, -1, 'India', 'in');

delete from core_state;
insert into core_state(id, app_id, client_id, name, country_id, code) values(18, 200, -1, 'Karnataka', 1, 'KA');

delete from core_city;
insert into core_city(id, app_id, client_id, name, country_id, state_id) values(1, 200, -1, 'Bangalore', 1, 18);

delete from core_city_area;
insert into core_city_area(id, app_id, client_id, name, city_id) values(1, 200, -1, 'BTM Layout 1st Stage', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(2, 200, -1, 'BTM Layout 2nd Stage', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(3, 200, -1, 'Madiwala', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(4, 200, -1, 'N.S.Palya', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(5, 200, -1, 'Jaya Nagar 1st Block', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(6, 200, -1, 'Jaya Nagar 2nd Block', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(7, 200, -1, 'Jaya Nagar 3rd Block', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(8, 200, -1, 'Jaya Nagar 4th Block', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(9, 200, -1, 'Jaya Nagar 5th Block', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(10, 200, -1, 'Jaya Nagar 6th Block', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(11, 200, -1, 'Jaya Nagar 7th Block', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(12, 200, -1, 'Jaya Nagar 8th Block', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(13, 200, -1, 'Jaya Nagar 9th Block', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(14, 200, -1, 'JP Nagar 1st Phase', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(15, 200, -1, 'JP Nagar 2nd Phase', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(16, 200, -1, 'JP Nagar 3rd Phase', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(17, 200, -1, 'JP Nagar 4th Phase', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(18, 200, -1, 'JP Nagar 5th Phase', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(19, 200, -1, 'Bilekahalli', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(20, 200, -1, 'Vijaya Bank Layout', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(21, 200, -1, 'Chikka Audugoi', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(22, 200, -1, 'Tavarekere', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(23, 200, -1, 'Bellandur', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(24, 200, -1, 'Basavanagudi', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(25, 200, -1, 'Arekere Mico Layout', 1);
insert into core_city_area(id, app_id, client_id, name, city_id) values(26, 200, -1, 'Kalena Agrahara', 1);


delete from fin_tax_category;
insert into fin_tax_category(id, app_id, client_id, name, state_id) values(1, 200, -1, 'Karnataka VAT', 18);

delete from fin_tax;
insert into fin_tax(id, app_id, client_id, name, tax_category_id, rate) values(1, 200, -1, 'Karnataka VAT - Nil', 1, 0);
insert into fin_tax(id, app_id, client_id, name, tax_category_id, rate) values(2, 200, -1, 'Karnataka VAT - 1%', 1, 1);
insert into fin_tax(id, app_id, client_id, name, tax_category_id, rate) values(3, 200, -1, 'Karnataka VAT - 2%', 1, 2);
insert into fin_tax(id, app_id, client_id, name, tax_category_id, rate) values(4, 200, -1, 'Karnataka VAT - 5.5%', 1,  5.5);
insert into fin_tax(id, app_id, client_id, name, tax_category_id, rate) values(5, 200, -1, 'Karnataka VAT - 14.5%', 1, 14.5);
insert into fin_tax(id, app_id, client_id, name, tax_category_id, rate) values(6, 200, -1, 'Karnataka VAT - 20%', 1, 20);


delete from fin_payment_method;
insert into fin_payment_method(id, app_id, client_id, name, uid) values (1, 200, -1, 'Cash/Card On Delivery', 'COD');
insert into fin_payment_method(id, app_id, client_id, name, uid) values (2, 200, -1, 'Debit/Credit Card', 'Online');
insert into fin_payment_method(id, app_id, client_id, name, uid) values (3, 200, -1, 'Net Banking', 'Online');


delete from mdm_product_category;
insert into mdm_product_category(id, app_id, client_id, parent_id, name, type, kind, qualified_name, sort_order, is_active, tax_id) values (1, 200, -1, NULL, 'Fruits & Vegetables', 'category', 'module', '/Fruits & Vegetables', 0, 'Y', 1);
insert into mdm_product_category(id, app_id, client_id, parent_id, name, type, kind, qualified_name, sort_order, is_active, tax_id) values (2, 200, -1, NULL, 'Pure Edible Oil', 'category', 'module', '/Pure Edible Oil', 0, 'Y', 1);
insert into mdm_product_category(id, app_id, client_id, parent_id, name, type, kind, qualified_name, sort_order, is_active, tax_id) values (3, 200, -1, NULL, 'Dry Fruits', 'category', 'module', '/Dry Fruits', 0, 'Y', 1);

-- Fruits & Vegetables
insert into mdm_product_category(id, app_id, client_id, parent_id, name, type, kind, qualified_name, sort_order, is_active, tax_id) values (101, 200, -1, 1, 'Fruits', 'category', 'module', '/Fruits & Vegetables/Fruits', 0, 'Y', 1);
insert into mdm_product_category(id, app_id, client_id, parent_id, name, type, kind, qualified_name, sort_order, is_active, tax_id) values (102, 200, -1, 1, 'Vegetables', 'category', 'module', '/Fruits & Vegetables/Vegetables', 0, 'Y', 1);


insert into mdm_product_category(id, app_id, client_id, parent_id, name, type, kind, qualified_name, sort_order, is_active, tax_id) values (1011, 200, -1, 101, 'Apple', 'category', 'module', '/Fruits & Vegetables/Fruits/Apple', 0, 'Y', 1);
insert into mdm_product_category(id, app_id, client_id, parent_id, name, type, kind, qualified_name, sort_order, is_active, tax_id) values (1012, 200, -1, 101, 'Orange', 'category', 'module', '/Fruits & Vegetables/Fruits/Orange', 0, 'Y', 1);

insert into mdm_product_category(id, app_id, client_id, parent_id, name, type, kind, qualified_name, sort_order, is_active, tax_id) values (1021, 200, -1, 102, 'Onion, Tomato & Potato', 'category', 'module', '/Fruits & Vegetables/Vegetables/Onion', 0, 'Y', 1);
