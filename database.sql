CREATE TABLE user_test_table (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    payload XML NOT NULL
);

CREATE TABLE users_x_partners_test_table (
    user_id bigint,
    partner_id bigint,
    PRIMARY KEY (user_id, partner_id)
);
