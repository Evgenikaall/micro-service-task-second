CREATE SEQUENCE message_schedule_seq
START 1
INCREMENT 1;

CREATE TABLE message_schedule (
    message_schedule_id BIGINT DEFAULT nextval('message_schedule_seq') NOT NULL PRIMARY KEY,
    message_schedule_date DATE NOT NULL,
    message_schedule_message VARCHAR(255) DEFAULT 'EMPTY MESSAGE BODY'
);