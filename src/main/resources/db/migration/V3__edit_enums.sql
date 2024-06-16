ALTER TABLE patient
    DROP COLUMN gender;
DROP TYPE gender_enum;
create type gender_enum as enum ('MALE', 'FEMALE');
ALTER TABLE patient
    ADD gender gender_enum;

ALTER TABLE users
    DROP COLUMN role;
DROP TYPE role_enum;
create type role_enum as enum ('DOCTOR', 'PATIENT', 'ASSISTANT', 'MANAGER');
ALTER TABLE users
    ADD role role_enum;

ALTER TABLE doctor_timetable
    DROP COLUMN day_of_week;
DROP TYPE day_of_week_enum;
create type day_of_week_enum as enum ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY','FRIDAY');
ALTER TABLE doctor_timetable
    ADD day_of_week day_of_week_enum;

ALTER TABLE appointment_doctor
    DROP COLUMN status;
DROP TYPE appointment_status_enum;
create type appointment_status_enum as enum ('SCHEDULED', 'COMPLETED', 'CANCELED');
ALTER TABLE appointment_doctor
    ADD status appointment_status_enum;

ALTER TABLE analysis_direction
    DROP COLUMN status;
DROP TYPE analysis_status_enum;
create type analysis_status_enum as enum ('VALID', 'INVALID');
ALTER TABLE analysis_direction
    ADD status analysis_status_enum;

ALTER TABLE analysis_direction
    DROP COLUMN usage;
DROP TYPE analysis_usage_enum;
create type analysis_usage_enum as enum ('USED', 'UNUSED');
ALTER TABLE analysis_direction
    ADD usage analysis_usage_enum;

ALTER TABLE appointment_doctor
    DROP COLUMN notification;
DROP TYPE notification_enum;
create type notification_enum as enum ('PLANED', 'DONE');
ALTER TABLE appointment_doctor
    ADD notification notification_enum;

ALTER TABLE pay_receipt
    DROP COLUMN status;
DROP TYPE pay_receipt_status_enum;
create type pay_receipt_status_enum as enum ('PENDING', 'COMPLETED', 'FAILED');
ALTER TABLE pay_receipt
    ADD status pay_receipt_status_enum;

ALTER TABLE pay_receipt
    DROP COLUMN payment_method;
DROP TYPE payment_method_enum;
create type payment_method_enum as enum ('CARD', 'INSURANCE');
ALTER TABLE pay_receipt
    ADD payment_method payment_method_enum;

ALTER TABLE pay_receipt
    DROP COLUMN type;
DROP TYPE payment_type_enum;
create type payment_type_enum as enum ('SERVICE', 'ANALYSIS');
ALTER TABLE pay_receipt
    ADD type payment_type_enum;