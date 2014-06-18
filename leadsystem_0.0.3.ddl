--DROP TABLE lead_data;
--DROP TABLE lead;
--DROP TABLE leads_x_partners;

CREATE TABLE lead
(
  "order_id" bigint PRIMARY KEY,
  "campaign_id" bigint NOT NULL,
  "status" varchar(40) NOT NULL,
  "creation_date" timestamp NOT NULL,
  "modification_date" timestamp,
  "export_date" timestamp
);

CREATE TABLE lead_data
(
  "order_id" bigint PRIMARY KEY,
  "payload" xml NOT NULL,
  CONSTRAINT "lead_order_id" FOREIGN KEY ("order_id")
      REFERENCES lead ("order_id") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE leads_x_partners (
    "order_id" bigint,
    "partner_id" bigint,
    PRIMARY KEY (order_id, partner_id)
);

CREATE INDEX lead_status_index ON lead (status);
