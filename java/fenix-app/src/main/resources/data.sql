INSERT INTO fire_departments (id, rev, name, enabled) VALUES (1, 1, 'Test Fire Department', true);
INSERT INTO users (id, rev, first_name, last_name, encrypted_password, username, password_exp_date, account_exp_date, locked, fire_department_id) VALUES (1, 1, 'Joe', 'Cool', '$2a$04$1msDGF6OLTquNVDDA4FWPegdZrzLMzycrOkTdvmKn3OVFNlfw8q8u', 'joecool', null, null, false, 1);
