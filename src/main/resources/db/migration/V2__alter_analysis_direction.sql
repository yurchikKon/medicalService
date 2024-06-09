create type notification_enum as enum ('planed', 'done');
ALTER TABLE appointment_doctor
ADD notification notification_enum;