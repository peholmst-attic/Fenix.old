CREATE TABLE contacts (
  id                 BIGINT       NOT NULL AUTO_INCREMENT,
  rev                BIGINT       NOT NULL,
  cell_phone         VARCHAR(255) NOT NULL,
  email              VARCHAR(255) NOT NULL,
  first_name         VARCHAR(255) NOT NULL,
  last_name          VARCHAR(255) NOT NULL,
  single_name        VARCHAR(255) NOT NULL,
  fire_department_id BIGINT       NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (fire_department_id) REFERENCES fire_departments (id)
)
  ENGINE InnoDB;

CREATE TABLE contact_communication_methods (
  contact_id BIGINT      NOT NULL,
  method     VARCHAR(20) NOT NULL,
  PRIMARY KEY (contact_id, method),
  FOREIGN KEY (contact_id) REFERENCES contacts (id)
)
  ENGINE InnoDB;

CREATE TABLE contact_groups (
  id                 BIGINT       NOT NULL AUTO_INCREMENT,
  rev                BIGINT       NOT NULL,
  group_name         VARCHAR(255) NOT NULL,
  fire_department_id BIGINT       NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (fire_department_id) REFERENCES fire_departments (id)
)
  ENGINE InnoDB;

CREATE TABLE contact_group_members (
  group_id  BIGINT NOT NULL,
  member_id BIGINT NOT NULL,
  PRIMARY KEY (group_id, member_id),
  FOREIGN KEY (group_id) REFERENCES contact_groups (id),
  FOREIGN KEY (member_id) REFERENCES contacts (id)
)
  ENGINE InnoDB;

CREATE TABLE message_receipts (
  id                 BIGINT       NOT NULL AUTO_INCREMENT,
  rev                BIGINT       NOT NULL,
  fire_department_id BIGINT       NOT NULL,
  ts_utc             TIMESTAMP    NOT NULL,
  msg_excerpt        VARCHAR(200) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (fire_department_id) REFERENCES fire_departments (id)

)
  ENGINE InnoDB;

CREATE TABLE message_receipt_status (
  id              BIGINT      NOT NULL AUTO_INCREMENT,
  rev             BIGINT      NOT NULL,
  receipt_id      BIGINT      NOT NULL,
  com_method      VARCHAR(20) NOT NULL,
  code            VARCHAR(20) NOT NULL,
  additional_info VARCHAR(255),
  PRIMARY KEY (id),
  UNIQUE (receipt_id, com_method),
  FOREIGN KEY (receipt_id) REFERENCES message_receipts (id)
)
  ENGINE InnoDB;


CREATE TABLE message_receipt_recipients (
  status_id BIGINT       NOT NULL,
  recipient VARCHAR(255) NOT NULL,
  PRIMARY KEY (status_id, recipient),
  FOREIGN KEY (status_id) REFERENCES message_receipt_status (id)
)
  ENGINE InnoDB;
