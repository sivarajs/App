drop table if exists core_client;
drop table if exists core_address;
drop table if exists core_city;
drop table if exists core_city_area;
drop table if exists core_country;
drop table if exists core_state;
drop table if exists core_app_entity_sequence;
drop table if exists core_app_entity_state;
drop table if exists core_app_hierarchical_entity;
drop table if exists comm_alert;
drop table if exists comm_email;
drop table if exists comm_send_email;
drop table if exists comm_send_s_m_s;
drop table if exists core_entity_feature;
drop table if exists core_entity_feature_value;
drop table if exists core_property;
drop table if exists core_property_group;
drop table if exists core_schedule;
drop table if exists core_schedule_trigger;
drop table if exists core_sequence_id;
drop table if exists bus_enterprise;
drop table if exists bus_enterprise_address;
drop table if exists fin_payment_method;
drop table if exists fin_tax;
drop table if exists fin_tax_category;
drop table if exists mar_sales_offer;
drop table if exists mar_sales_order_complimentary_item;
drop table if exists bp_business_partner;
drop table if exists bp_business_partner_address;
drop table if exists bp_business_partner_group;
drop table if exists mdm_product;
drop table if exists mdm_product_category;
drop table if exists mdm_product_category_feature;
drop table if exists mdm_product_feature;
drop table if exists mdm_product_feature_value;
drop table if exists mdm_product_line_item;
drop table if exists sales_sales_invoice;
drop table if exists sales_sales_order;
drop table if exists sales_sales_order_delivery;
drop table if exists sales_sales_order_line_item;
drop table if exists store_shopping_cart;
drop table if exists store_shopping_cart_line_item;
drop table if exists subs_subscription;
drop table if exists subs_subscription_frequency;
drop table if exists subs_subscription_line_item;

create table core_client (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    name varchar(100) not null,
    email varchar(100) not null,
    phone varchar(100) not null,
    fax varchar(100),
    tin varchar(100) not null,
    CONSTRAINT uniqueKey_0_name UNIQUE (name)
) ENGINE=InnoDB;

create table core_address (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    address varchar(100) not null,
    landmark varchar(100),
    area_id bigint,
    city_id bigint not null,
    state_id bigint not null,
    country_id bigint not null,
    pin_code int not null,
    latlng varchar(100)
) ENGINE=InnoDB;

create table core_city (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    country_id bigint not null,
    state_id bigint not null
) ENGINE=InnoDB;

create table core_city_area (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    city_id bigint not null
) ENGINE=InnoDB;

create table core_country (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    code varchar(10) not null
) ENGINE=InnoDB;

create table core_state (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    country_id bigint not null,
    code varchar(10) not null
) ENGINE=InnoDB;

create table core_app_entity_sequence (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    entity_id bigint,
    name varchar(100) not null,
    value bigint not null,
    CONSTRAINT uniqueKey_1_name UNIQUE (name)
) ENGINE=InnoDB;

create table core_app_entity_state (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    entity varchar(100) not null,
    code int not null,
    status varchar(100) not null,
    CONSTRAINT uniqueKey_2_entity_code UNIQUE (entity,code)
) ENGINE=InnoDB;

create table core_app_hierarchical_entity (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    qualified_name varchar(100),
    category varchar(100),
    parent_id bigint,
    type varchar(100) not null,
    kind varchar(100) not null,
    action varchar(100),
    CONSTRAINT uniqueKey_3_parentId_name UNIQUE (parent_id,name)
) ENGINE=InnoDB;

create table comm_alert (
    id bigint not null primary key,
    sender varchar(100) not null,
    receiver varchar(100) not null,
    category varchar(100) not null,
    type varchar(100) not null,
    description varchar(100) not null,
    target varchar(100) not null,
    received_on timestamp not null
) ENGINE=InnoDB;

create table comm_email (
    id bigint not null primary key,
    sender varchar(100) not null,
    receiver varchar(100) not null,
    message varchar(1000) not null,
    subject varchar(200) not null,
    received_on timestamp not null,
    state int not null
) ENGINE=InnoDB;

create table comm_send_email (
    id bigint not null primary key auto_increment,
    tos varchar(500) not null,
    ccs varchar(500),
    bccs varchar(500),
    subject varchar(100) not null,
    message text not null,
    content_type varchar(100) not null,
    sent_on timestamp not null,
    reference varchar(100),
    delivered_on timestamp null,
    state varchar(100) not null
) ENGINE=InnoDB;

create table comm_send_s_m_s (
    id bigint not null primary key auto_increment,
    number varchar(100) not null,
    message varchar(256) not null,
    sent_on timestamp not null,
    reference varchar(100) not null,
    delivered_on timestamp null,
    state varchar(100) not null
) ENGINE=InnoDB;

create table core_entity_feature (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    type_id bigint not null,
    owner_id bigint not null,
    owner_type varchar(100) not null,
    sort_order int default 0,
    values_id bigint
) ENGINE=InnoDB;

create table core_entity_feature_value (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    entity_feature_id bigint not null,
    row_attribute_name varchar(100),
    column_attribute_name varchar(100),
    value varchar(100),
    sort_order int default 0
) ENGINE=InnoDB;

create table core_property (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    type varchar(100) not null,
    owner_id bigint,
    category varchar(100),
    prefix varchar(100),
    value varchar(2000) not null,
    CONSTRAINT uniqueKey_4_ownerId_category_prefix_name UNIQUE (owner_id,category,prefix,name)
) ENGINE=InnoDB;

create table core_property_group (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    value varchar(100) not null,
    CONSTRAINT uniqueKey_5_name_value UNIQUE (name,value)
) ENGINE=InnoDB;

create table core_schedule (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    action_class varchar(100) not null,
    type varchar(100) not null,
    start_time timestamp null,
    end_time timestamp null,
    trigger_on_start varchar(1),
    schedule_trigger_id bigint not null,
    CONSTRAINT uniqueKey_6_appId_name UNIQUE (app_id,name)
) ENGINE=InnoDB;

create table core_schedule_trigger (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    frequency int,
    at_time varchar(100),
    day varchar(100)
) ENGINE=InnoDB;

create table core_sequence_id (
    id bigint not null primary key auto_increment,
    entity_id bigint not null,
    name varchar(100) not null,
    value bigint not null,
    CONSTRAINT uniqueKey_7_name UNIQUE (name)
) ENGINE=InnoDB;

create table bus_enterprise (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    email varchar(100) not null,
    phone varchar(100) not null,
    fax varchar(100),
    tin varchar(100) not null,
    CONSTRAINT uniqueKey_8_name UNIQUE (name)
) ENGINE=InnoDB;

create table bus_enterprise_address (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    enterprise_id bigint not null,
    address_id bigint not null,
    is_primary varchar(1),
    CONSTRAINT uniqueKey_9_enterpriseId_address UNIQUE (enterprise_id,address_id)
) ENGINE=InnoDB;

create table fin_payment_method (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    uid varchar(100) not null,
    CONSTRAINT uniqueKey_10_name UNIQUE (name)
) ENGINE=InnoDB;

create table fin_tax (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    tax_category_id bigint not null,
    rate float not null,
    CONSTRAINT uniqueKey_11_name UNIQUE (name)
) ENGINE=InnoDB;

create table fin_tax_category (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    state_id bigint not null,
    CONSTRAINT uniqueKey_12_name_state UNIQUE (name,state_id)
) ENGINE=InnoDB;

create table mar_sales_offer (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    title varchar(100) not null,
    start_time date not null,
    end_time date not null,
    product_category_id bigint,
    minimum_purchase bigint,
    customer_types varchar(100),
    discount float not null,
    discount_type_id bigint not null,
    sort_order int,
    is_complimentary_or varchar(1),
    complimentary_items_id bigint,
    CONSTRAINT uniqueKey_13_title UNIQUE (title)
) ENGINE=InnoDB;

create table mar_sales_order_complimentary_item (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    sales_offer_id bigint not null,
    product_line_item_id bigint not null,
    mrp float not null,
    CONSTRAINT uniqueKey_14_salesOfferId_productLineItem UNIQUE (sales_offer_id,product_line_item_id)
) ENGINE=InnoDB;

create table bp_business_partner (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100),
    group_id bigint,
    email varchar(100),
    mobile varchar(100),
    alt_mobile varchar(100),
    landline varchar(100),
    address_id bigint,
    user_id bigint not null,
    CONSTRAINT uniqueKey_15_userId UNIQUE (user_id)
) ENGINE=InnoDB;

create table bp_business_partner_address (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    business_partner_id bigint not null,
    address_id bigint not null,
    is_primary varchar(1),
    CONSTRAINT uniqueKey_16_businessPartnerId_address UNIQUE (business_partner_id,address_id)
) ENGINE=InnoDB;

create table bp_business_partner_group (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    discount float not null,
    CONSTRAINT uniqueKey_17_name UNIQUE (name)
) ENGINE=InnoDB;

create table mdm_product (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    uid varchar(100),
    brand varchar(100),
    product_category_id bigint not null,
    short_description varchar(100),
    description text,
    rating int,
    is_active varchar(1) not null default 'Y',
    tags varchar(100),
    tn_image varchar(100),
    tax_id bigint,
    CONSTRAINT uniqueKey_18_productCategory_name_brand UNIQUE (product_category_id,name,brand)
) ENGINE=InnoDB;

create table mdm_product_category (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    qualified_name varchar(100),
    category varchar(100),
    parent_id bigint,
    type varchar(100) not null,
    kind varchar(100) not null,
    action varchar(100),
    sort_order int default 0,
    is_active varchar(1) not null default 'Y',
    tags varchar(100),
    tax_id bigint,
    CONSTRAINT uniqueKey_19_parentId_name UNIQUE (parent_id,name),
    CONSTRAINT uniqueKey_20_qualifiedName UNIQUE (qualified_name)
) ENGINE=InnoDB;

create table mdm_product_category_feature (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    category_id bigint not null,
    name varchar(100) not null,
    type_id bigint not null,
    attributes varchar(2000),
    sort_order int not null default 0,
    CONSTRAINT uniqueKey_21_name UNIQUE (name)
) ENGINE=InnoDB;

create table mdm_product_feature (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    product_id bigint not null,
    product_category_feature_id bigint,
    name varchar(100) not null,
    type_id bigint not null,
    attributes varchar(2000),
    sort_order int not null default 0,
    CONSTRAINT uniqueKey_22_name UNIQUE (name)
) ENGINE=InnoDB;

create table mdm_product_feature_value (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    owner_id bigint not null,
    owner_type varchar(100) not null,
    row_attribute_name varchar(100),
    column_attribute_name varchar(100),
    value varchar(100),
    sort_order int default 0
) ENGINE=InnoDB;

create table mdm_product_line_item (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    uid varchar(100),
    product_id bigint not null,
    code varchar(10) not null,
    search_terms varchar(5000) not null,
    description varchar(5000),
    category varchar(100) not null,
    quantity int not null,
    net_quantity float,
    unit_of_measure_id bigint not null,
    package_type_id bigint,
    mrp float not null,
    price float not null,
    savings float,
    discount float,
    discount_type_id bigint,
    is_default varchar(1),
    sort_order int,
    is_active varchar(1) not null default 'Y',
    in_stock varchar(1) not null default 'Y',
    is_offer varchar(1) default 'N',
    tags varchar(100),
    tn_image varchar(100),
    CONSTRAINT uniqueKey_23_code UNIQUE (code),
    CONSTRAINT uniqueKey_24_product_quantity_unitOfMeasure_packageType UNIQUE (product_id,quantity,unit_of_measure_id,package_type_id)
) ENGINE=InnoDB;

create table sales_sales_invoice (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    number varchar(100) not null,
    sales_order_id bigint not null,
    notes varchar(100),
    CONSTRAINT uniqueKey_25_number UNIQUE (number)
) ENGINE=InnoDB;

create table sales_sales_order (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    order_id varchar(100),
    transaction_id varchar(100),
    business_partner_id bigint not null,
    session_id varchar(100) not null,
    placed_on timestamp not null,
    state_id bigint not null,
    payment_method_id bigint not null,
    amount float not null,
    notes varchar(100),
    flexi_items varchar(100),
    delivery_id bigint,
    CONSTRAINT uniqueKey_26_orderId UNIQUE (order_id)
) ENGINE=InnoDB;

create table sales_sales_order_delivery (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    address_id bigint not null,
    date date not null,
    code varchar(100)
) ENGINE=InnoDB;

create table sales_sales_order_line_item (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    sales_order_id bigint not null,
    product_line_item_id bigint not null,
    quantity int not null,
    net_quantity float,
    total_price float not null,
    unit_mrp float not null,
    unit_price float not null,
    discount float,
    discount_type_id bigint,
    tax_rate float not null default 0,
    notes varchar(100),
    CONSTRAINT uniqueKey_27_salesOrderId_productLineItem UNIQUE (sales_order_id,product_line_item_id)
) ENGINE=InnoDB;

create table store_shopping_cart (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    business_partner_id bigint,
    session_id varchar(100) not null,
    created_time timestamp not null,
    items_id bigint,
    CONSTRAINT uniqueKey_28_businessPartner UNIQUE (business_partner_id)
) ENGINE=InnoDB;

create table store_shopping_cart_line_item (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    shopping_cart_id bigint not null,
    product_line_item_id bigint not null,
    quantity int not null,
    total_price float not null,
    CONSTRAINT uniqueKey_29_shoppingCartId_productLineItem UNIQUE (shopping_cart_id,product_line_item_id)
) ENGINE=InnoDB;

create table subs_subscription (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    business_partner_id bigint not null,
    frequency_id bigint not null,
    items_id bigint,
    notes varchar(100),
    CONSTRAINT uniqueKey_30_businessPartner_frequency UNIQUE (business_partner_id,frequency_id)
) ENGINE=InnoDB;

create table subs_subscription_frequency (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    frequency_id bigint not null,
    info varchar(100),
    CONSTRAINT uniqueKey_31_frequency UNIQUE (frequency_id)
) ENGINE=InnoDB;

create table subs_subscription_line_item (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    subscription_id bigint not null,
    product_line_item_id bigint not null,
    quantity int not null,
    CONSTRAINT uniqueKey_32_subscriptionId_productLineItem UNIQUE (subscription_id,product_line_item_id)
) ENGINE=InnoDB;
