--liquibase formatted sql

--changeset alexander_ermakov:1
ALTER TABLE users
ADD COLUMN client_id BIGINT;