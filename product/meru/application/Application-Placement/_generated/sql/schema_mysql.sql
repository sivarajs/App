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
drop table if exists cp_campus;
drop table if exists cp_campus_contact;
drop table if exists cp_campus_program;
drop table if exists cp_campus_venue;
drop table if exists cp_course;
drop table if exists cp_campus_employer;
drop table if exists cp_campus_employer_contact;
drop table if exists cp_course_placement_item;
drop table if exists cp_employer_interview;
drop table if exists cp_interview_round;
drop table if exists cp_student_interview;
drop table if exists cp_student_interview_result;
drop table if exists cp_placement;
drop table if exists cp_placement_registration;
drop table if exists cp_rfp;
drop table if exists cp_rfp_response;
drop table if exists cp_rfp_response_item;
drop table if exists cp_employer_schedule;
drop table if exists cp_placement_schedule;
drop table if exists st_award;
drop table if exists st_education;
drop table if exists st_elective;
drop table if exists st_offer;
drop table if exists st_student;
drop table if exists st_student_registration;
drop table if exists cp_university;
drop table if exists cp_employer;
drop table if exists cp_employer_contact;

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

create table cp_campus (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    alias varchar(100),
    code varchar(100) not null,
    website varchar(100),
    university_id bigint not null,
    estabilished_on date,
    affiliated_from date,
    student_count bigint,
    selection_mode_id bigint,
    institute_type_id bigint,
    evaluation_type_id bigint,
    education_pattern_id bigint,
    placement_orientation_id bigint,
    internship_duration int,
    internship_unit_id bigint,
    internship_type_id bigint,
    address_id bigint,
    CONSTRAINT uniqueKey_8_code UNIQUE (code)
) ENGINE=InnoDB;

create table cp_campus_contact (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    campus_id bigint not null,
    staff_type_id bigint not null,
    email varchar(100) not null,
    phone varchar(100) not null,
    role_id bigint not null,
    role varchar(100)
) ENGINE=InnoDB;

create table cp_campus_program (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    campus_id bigint not null,
    program_id bigint not null,
    CONSTRAINT uniqueKey_9_campusId_program UNIQUE (campus_id,program_id)
) ENGINE=InnoDB;

create table cp_campus_venue (
    id bigint not null primary key auto_increment,
    campus_id bigint not null,
    name varchar(100) not null,
    CONSTRAINT uniqueKey_10_campusId_name UNIQUE (campus_id,name)
) ENGINE=InnoDB;

create table cp_course (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    campus_id bigint not null,
    degree_id bigint not null,
    discipline_id bigint not null,
    education_type_id bigint not null,
    duration int not null,
    time_unit_id bigint not null,
    CONSTRAINT uniqueKey_11_campusId_degree_discipline UNIQUE (campus_id,degree_id,discipline_id)
) ENGINE=InnoDB;

create table cp_campus_employer (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    campus_id bigint not null,
    name varchar(100) not null,
    search_term varchar(100) not null,
    is_domain_specific varchar(1),
    industry_type varchar(100),
    address_id bigint not null
) ENGINE=InnoDB;

create table cp_campus_employer_contact (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    campus_employer_id bigint not null,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    email varchar(100) not null,
    phone varchar(100) not null,
    type_id bigint not null
) ENGINE=InnoDB;

create table cp_course_placement_item (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    owner_id bigint not null,
    type varchar(100) not null,
    course_id bigint not null,
    student_count int not null,
    expected_pay float not null,
    offers_made int
) ENGINE=InnoDB;

create table cp_employer_interview (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    employer_schedule_id bigint not null,
    interview_type_id bigint not null,
    description varchar(100) not null,
    date date not null,
    time varchar(100) not null,
    duration int not null,
    buffer_time int,
    campus_venue_id bigint,
    temp_venue varchar(100),
    state_id bigint not null
) ENGINE=InnoDB;

create table cp_interview_round (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    rfp_response_id bigint not null,
    interview_type_id bigint not null,
    description varchar(100) not null,
    expected_date date not null,
    duration int not null
) ENGINE=InnoDB;

create table cp_student_interview (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    campus_id bigint not null,
    employer_id bigint not null,
    employer_schedule_id bigint not null,
    student_id bigint not null,
    course_id bigint not null,
    selection_state_id bigint,
    acceptance_state_id bigint,
    acceptance_due_date date,
    remarks varchar(100),
    pay float
) ENGINE=InnoDB;

create table cp_student_interview_result (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    student_interview_id bigint not null,
    employer_interview_id bigint not null,
    selection_state_id bigint,
    evaluated_by varchar(100),
    remarks varchar(100)
) ENGINE=InnoDB;

create table cp_placement (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    campus_id bigint not null,
    year int not null,
    academic_start date not null,
    academic_end date not null,
    rollout_start date not null,
    rollout_end date not null,
    commencement date not null,
    joining_period date not null,
    state_id bigint not null
) ENGINE=InnoDB;

create table cp_placement_registration (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    document_id varchar(100) not null,
    campus_id bigint not null,
    placement_id bigint not null,
    state_id bigint not null,
    due_date date not null,
    max_offers int not null,
    student_cgpa varchar(1) not null,
    placement_category_id bigint not null,
    clause_template varchar(500) not null,
    clause varchar(500) not null,
    notification_template varchar(500) not null,
    notification varchar(500) not null,
    UNIQUE KEY (document_id)
) ENGINE=InnoDB;

create table cp_rfp (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    document_id varchar(100) not null,
    campus_id bigint not null,
    placement_registration_id bigint,
    placement_id bigint not null,
    mode varchar(100) not null,
    due_date date not null,
    expected_pay float not null,
    total_students int,
    participated_students int,
    preferred_placement_id bigint,
    placement_category_id bigint not null,
    resume_visible varchar(1),
    state_id bigint not null,
    response_seq int,
    course_placement_items_id bigint,
    UNIQUE KEY (document_id)
) ENGINE=InnoDB;

create table cp_rfp_response (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    campus_id bigint not null,
    employer_id bigint not null,
    rfp_id bigint not null,
    preferred_placement_id bigint,
    selection_type_id bigint,
    acceptable_pay float,
    responded_on date,
    state_id bigint not null,
    rfp_response_items_id bigint
) ENGINE=InnoDB;

create table cp_rfp_response_item (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    rfp_response_id bigint not null,
    course_placement_item_id bigint not null,
    is_eligible varchar(1),
    accepted_pay float
) ENGINE=InnoDB;

create table cp_employer_schedule (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    campus_id bigint not null,
    employer_id bigint not null,
    placement_schedule_id bigint not null,
    rfp_response_id bigint not null,
    employer_interview_id bigint,
    state_id bigint not null,
    dispatch_state_id bigint,
    accept_due_date date
) ENGINE=InnoDB;

create table cp_placement_schedule (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    document_id varchar(100) not null,
    campus_id bigint not null,
    rfp_id bigint,
    placement_registration_id bigint not null,
    mode varchar(100) not null,
    state_id bigint not null,
    due_date date not null,
    UNIQUE KEY (document_id)
) ENGINE=InnoDB;

create table st_award (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    student_id bigint not null,
    year int not null,
    detail varchar(100) not null
) ENGINE=InnoDB;

create table st_education (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    student_id bigint not null,
    degree_id bigint not null,
    year int not null,
    board varchar(100) not null,
    institute varchar(100) not null,
    percentage int not null
) ENGINE=InnoDB;

create table st_elective (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    student_id bigint not null,
    course_id bigint,
    subject varchar(100) not null
) ENGINE=InnoDB;

create table st_offer (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    student_id bigint not null,
    employer varchar(100) not null,
    date date not null,
    pay float not null
) ENGINE=InnoDB;

create table st_student (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    code varchar(100) not null,
    campus_id bigint not null,
    email varchar(100) not null,
    ipar_id varchar(100),
    course1_id bigint,
    course2_id bigint,
    score float,
    birth_date date,
    sex_id bigint,
    weight int,
    height int,
    internship_duration int,
    internship_unit_id bigint,
    internship_company varchar(100),
    internship_details varchar(100),
    student_registration_id bigint,
    educations_id bigint,
    awards_id bigint,
    electives_id bigint,
    offers_id bigint
) ENGINE=InnoDB;

create table st_student_registration (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    placement_registration_id bigint not null,
    placement_degree_id bigint,
    max_offers int,
    actual_offers int,
    joining_period date,
    evaluation_history varchar(2000),
    review varchar(100),
    state_id bigint,
    prev_state_id bigint,
    state_info varchar(100)
) ENGINE=InnoDB;

create table cp_university (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    address_id bigint not null,
    CONSTRAINT uniqueKey_12_name UNIQUE (name)
) ENGINE=InnoDB;

create table cp_employer (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    name varchar(100) not null,
    code varchar(100) not null,
    domain varchar(100),
    industry_type varchar(100),
    address_id bigint,
    state_id bigint not null,
    CONSTRAINT uniqueKey_13_code UNIQUE (code)
) ENGINE=InnoDB;

create table cp_employer_contact (
    id bigint not null primary key auto_increment,
    app_id bigint not null,
    client_id bigint not null,
    employer_id bigint not null,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    email varchar(100) not null,
    phone varchar(100) not null,
    type_id bigint not null
) ENGINE=InnoDB;
