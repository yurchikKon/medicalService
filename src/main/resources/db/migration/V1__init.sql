create type gender_enum as enum ('male', 'female');
create type role_enum as enum ('doctor', 'patient', 'assistant', 'manager');
create type day_of_week_enum as enum ('monday', 'tuesday', 'wednesday', 'thursday','friday');

create table if not exists specialization_list
(
    id   bigserial primary key,
    name varchar(255) UNIQUE NOT NULL
);

create table if not exists usr
(
    id           bigserial primary key,
    first_name   varchar(30)  NOT NULL,
    last_name    varchar(50)  NOT NULL,
    role         role_enum    NOT NULL,
    phone_number varchar(20)  NOT NULL,
    address      text,
    login        varchar(255) NOT NULL UNIQUE,
    password     varchar(255) NOT NULL,
    email        varchar(255) NOT NULL UNIQUE
);

create table if not exists doctor
(
    id                bigserial primary key references usr (id),
    specialization_id bigint,
    rate              numeric(3, 2),
    foreign key (specialization_id) references specialization_list (id)
);


create table if not exists patient
(
    id         bigserial primary key references usr (id),
    gender     gender_enum,
    birth_date DATE
);

create table if not exists doctor_timetable
(
    id          bigserial primary key,
    doctor_id   bigint,
    time_start  time             NOT NULL,
    time_end    time             NOT NULL,
    room_number int              NOT NULL,
    day_of_week day_of_week_enum NOT NULL,
    foreign key (doctor_id) references usr (id)
);

create type appointment_status_enum as enum ('scheduled', 'complete', 'canceled');

create table if not exists appointment_doctor
(
    id        bigserial primary key,
    doctor_id bigint,
    user_id   bigint,
    date_time timestamp NOT NULL ,
    status    appointment_status_enum NOT NULL ,
    foreign key (doctor_id) references usr (id),
    foreign key (user_id) references usr (id)
);

create table if not exists diagnosis_list
(
    id   bigserial primary key,
    name varchar(255) NOT NULL UNIQUE
);

create table if not exists diagnosis
(
    id                    bigserial primary key,
    appointment_doctor_id bigint,
    diagnosis_list_id     bigint,
    foreign key (appointment_doctor_id) references appointment_doctor (id),
    foreign key (diagnosis_list_id) references diagnosis_list (id)
);

create table if not exists medical_receipt
(
    id                    bigserial primary key,
    appointment_doctor_id bigint,
    name                  varchar(255),
    date_end              date,
    foreign key (appointment_doctor_id) references appointment_doctor (id)
);

create table if not exists appointment_review
(
    id                    bigserial primary key,
    appointment_doctor_id bigint,
    mark                  numeric(3, 2),
    foreign key (appointment_doctor_id) references appointment_doctor (id)
);

create table if not exists special_doctor_direction
(
    id                    bigserial primary key,
    appointment_doctor_id bigint,
    specialization_id     bigint,
    foreign key (appointment_doctor_id) references appointment_doctor (id),
    foreign key (specialization_id) references specialization_list (id)
);

create table if not exists medical_service
(
    id   bigserial primary key,
    name varchar(255) NOT NULL UNIQUE,
    cost int
);

create table if not exists appointment_service
(
    id                    bigserial primary key,
    medical_service_id    bigint,
    appointment_doctor_id bigint,
    foreign key (medical_service_id) references medical_service (id),
    foreign key (appointment_doctor_id) references appointment_doctor (id)
);

create table if not exists analysis_list
(
    id   bigserial primary key,
    name varchar(255) NOT NULL UNIQUE,
    cost int
);

create type analysis_status_enum as enum ('valid', 'invalid');

create type analysis_usage_enum as enum ('used', 'unused');

create table if not exists analysis_direction
(
    id                    bigserial primary key,
    analysis_id           bigint,
    appointment_doctor_id bigint,
    status                appointment_status_enum,
    usage                 analysis_usage_enum,
    result                varchar(255),
    foreign key (appointment_doctor_id) references appointment_doctor (id),
    foreign key (analysis_id) references analysis_list (id)
);

create table if not exists doctor_remark
(
    id                    bigserial primary key,
    appointment_doctor_id bigint,
    remark                text,
    foreign key (appointment_doctor_id) references appointment_doctor (id)
);

create type pay_receipt_status_enum as enum ('pending', 'complete', 'failed');
create type payment_method_enum as enum ('card', 'insurance');
create type payment_type_enum as enum ('service', 'analysis');

create table if not exists pay_receipt
(
    id                    bigserial primary key,
    appointment_doctor_id bigint,
    value                 int,
    status                pay_receipt_status_enum,
    payment_method        payment_method_enum,
    type                  payment_type_enum,
    foreign key (appointment_doctor_id) references appointment_doctor (id)
);

