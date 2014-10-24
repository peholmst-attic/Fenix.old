CREATE TABLE fire_departments (
  id      BIGINT       NOT NULL AUTO_INCREMENT,
  rev     BIGINT       NOT NULL,
  enabled BOOLEAN      NOT NULL,
  name    VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (name)
)
  ENGINE InnoDB;

CREATE TABLE users (
  id                 BIGINT       NOT NULL AUTO_INCREMENT,
  rev                BIGINT       NOT NULL,
  account_exp_date   DATE,
  encrypted_password VARCHAR(255) NOT NULL,
  first_name         VARCHAR(255) NOT NULL,
  last_name          VARCHAR(255) NOT NULL,
  locked             BOOLEAN      NOT NULL,
  password_exp_date  DATE,
  username           VARCHAR(255) NOT NULL,
  fire_department_id BIGINT,
  PRIMARY KEY (id),
  UNIQUE (username),
  FOREIGN KEY (fire_department_id) REFERENCES fire_departments (id)
)
  ENGINE InnoDB;

CREATE TABLE user_authorities (
  user_id   BIGINT       NOT NULL,
  authority VARCHAR(255) NOT NULL,
  PRIMARY KEY (user_id, authority),
  FOREIGN KEY (user_id) REFERENCES users (id)
)
  ENGINE InnoDB;

CREATE TABLE sms_properties (
  id                 BIGINT       NOT NULL AUTO_INCREMENT,
  rev                BIGINT       NOT NULL,
  originator         VARCHAR(255) NOT NULL,
  password           VARCHAR(255) NOT NULL,
  user_key           VARCHAR(255) NOT NULL,
  fire_department_id BIGINT       NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (fire_department_id) REFERENCES fire_departments (id),
  UNIQUE (fire_department_id)
)
  ENGINE InnoDB;

CREATE TABLE email_properties (
  id                 BIGINT       NOT NULL AUTO_INCREMENT,
  rev                BIGINT       NOT NULL,
  sender_name        VARCHAR(255) NOT NULL,
  sender_address     VARCHAR(255) NOT NULL,
  fire_department_id BIGINT       NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (fire_department_id) REFERENCES fire_departments (id),
  UNIQUE (fire_department_id)
)
  ENGINE InnoDB;
