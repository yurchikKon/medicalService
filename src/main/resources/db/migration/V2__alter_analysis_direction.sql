create type notification_enum as enum ('planed', 'done');

ALTER TABLE appointment_doctor
ADD notification notification_enum;

ALTER TABLE analysis_direction
DROP COLUMN status;

ALTER TABLE analysis_direction
ADD COLUMN status  analysis_status_enum;