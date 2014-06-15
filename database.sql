CREATE TABLE user_test_table (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    payload XML NOT NULL
);