create type gender_enum as enum ('male', 'female');
create type role_enum as enum ('doctor', 'patient', 'assistant', 'manager');
create type day_of_week_enum as enum ('monday', 'tuesday', 'wednesday', 'thursday','friday');

create table if not exists specialization_list
(
    id   bigserial primary key,
    name varchar(255) UNIQUE NOT NULL
);

create table if not exists users
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
    id                bigserial primary key references users (id),
    specialization_id bigint,
    rate              numeric(3, 2),
    foreign key (specialization_id) references specialization_list (id)
);


create table if not exists patient
(
    id         bigserial primary key references users (id),
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
    foreign key (doctor_id) references users (id)
);

create type appointment_status_enum as enum ('scheduled', 'completed', 'canceled');

create table if not exists appointment_doctor
(
    id        bigserial primary key,
    doctor_id bigint,
    user_id   bigint,
    date_time timestamp               NOT NULL,
    status    appointment_status_enum NOT NULL,
    foreign key (doctor_id) references users (id),
    foreign key (user_id) references users (id)
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

create table if not exists preparation_list(
    id bigserial primary key,
    name varchar(255)
);

create table if not exists medical_receipt
(
    id                    bigserial primary key,
    appointment_doctor_id bigint,
    preparation_id        bigint,
    date_end              date,
    foreign key (appointment_doctor_id) references appointment_doctor (id),
    foreign key (preparation_id) references preparation_list(id)
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
    cost decimal(10,2)
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
    cost decimal(10,2)
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
    value                 decimal(10,2),
    status                pay_receipt_status_enum,
    payment_method        payment_method_enum,
    type                  payment_type_enum,
    foreign key (appointment_doctor_id) references appointment_doctor (id)
);

insert into specialization_list(name)
values ('therapist'),
       ('traumatologist'),
       ('orthopedist'),
       ('ophthalmologist');

insert into users(first_name, last_name, role, phone_number, address, login, password, email)
values ('ivan', 'ivanov', 'manager', '88888888888', 'Moscow', 'ivanov', '$2a$10$YONPknUyc.NcFBTHtyG4IOEGREocQv8AKAM5j2gAPtWdEBOC/ay0u', 'ivanov@mail.ru'),
       ('petr', 'petrov', 'doctor', '88888888888', 'Moscow', 'petrov', '$2a$10$YONPknUyc.NcFBTHtyG4IOEGREocQv8AKAM5j2gAPtWdEBOC/ay0u', 'petrov@mail.ru');

insert into doctor(id, specialization_id)
values (2, 1);

insert into doctor_timetable(doctor_id, time_start, time_end, room_number, day_of_week)
values (2, '08:00:00', '14:00:00', 1, 'monday'),
       (2, '08:00:00', '14:00:00', 1, 'tuesday'),
       (2, '08:00:00', '14:00:00', 1, 'wednesday'),
       (2, '08:00:00', '14:00:00', 1, 'thursday'),
       (2, '08:00:00', '14:00:00', 1, 'friday');

insert into analysis_list(name, cost)
values ('blood', 1000.00),
       ('fluorography', 1500.00);

insert into medical_service(name, cost)
values ('inspection', 2000.00),
       ('massage', 3000.00),
       ('electrophoresis', 1000.00),
       ('oxygen_cocktail', 500.00);

insert into preparation_list(name)
values ('nurafen'),
       ('oksikontin'),
       ('nazalvan'),
       ('sprei');

insert into diagnosis_list(name)
values ('flu'),
       ('runny_nose'),
       ('fracture'),
       ('bruis'),
       ('back_pain'),
       ('head_pain');