--liquibase formatted sql

--changeset alexander_ermakov:1
CREATE TABLE IF NOT EXISTS payment_type
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(255) NOT NULL
);

--changeset alexander_ermakov:2
CREATE TABLE IF NOT EXISTS currency
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(128) NOT NULL
);

--changeset alexander_ermakov:3
CREATE TABLE IF NOT EXISTS status
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(64)
);

--changeset alexander_ermakov:4
CREATE TABLE IF NOT EXISTS users
(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--changeset alexander_ermakov:5
CREATE TABLE IF NOT EXISTS account
(
    id BIGSERIAL PRIMARY KEY ,
    user_id BIGINT REFERENCES users(id),
    currency_id BIGINT REFERENCES currency(id),
    balance NUMERIC NOT NULL CHECK ( balance >= 0.0 ),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--changeset alexander_ermakov:6
CREATE TABLE IF NOT EXISTS payment_method
(
    id BIGSERIAL PRIMARY KEY ,
    type_id BIGINT REFERENCES payment_type(id),
    details VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--changeset alexander_ermakov:7
CREATE TABLE IF NOT EXISTS transaction
(
    id BIGSERIAL PRIMARY KEY ,
    account_receiver BIGINT REFERENCES account(id),
    account_sender BIGINT REFERENCES account(id),
    payment_method_id BIGINT REFERENCES payment_method(id),
    currency_id BIGINT REFERENCES currency(id),
    status_id BIGINT REFERENCES status(id),
    amount NUMERIC NOT NULL CHECK ( amount >= 0.0 ),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--changeset alexander_ermakov:8
CREATE TABLE IF NOT EXISTS transaction_log
(
    id BIGSERIAL PRIMARY KEY ,
    transaction_id BIGINT REFERENCES transaction(id),
    message TEXT NOT NULL ,
    logged_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);