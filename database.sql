CREATE TABLE user_test_table (
	user_id 		BIGSERIAL 	PRIMARY KEY,
    order_id 		BIGINT 		NOT NULL,
    partner_id 		BIGINT 		NOT NULL,
    name 			VARCHAR(20) NOT NULL,
    UNIQUE(order_id,partner_id)
);

CREATE TABLE user_payload (
	user_id 		BIGINT 		PRIMARY KEY,
	payload 		XML 		NOT NULL,
	CONSTRAINT "user_test_table_user_id" FOREIGN KEY ("user_id")
    	REFERENCES user_test_table ("user_id") MATCH SIMPLE
    	ON UPDATE NO ACTION ON DELETE NO ACTION
);

--CREATE TABLE users_x_partners_test_table (
--    user_id bigint,
--    partner_id bigint,
--    PRIMARY KEY (user_id, partner_id)
--);

--CREATE TABLE user_test_table_composite_pk (
--    order_id bigint NOT NULL,
--    partner_id bigint NOT NULL,
--    name VARCHAR(20) NOT NULL,
--    payload XML NOT NULL
--    PRIMARY KEY (order_id, partner_id)
--);
