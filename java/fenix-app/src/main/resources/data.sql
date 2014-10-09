INSERT INTO fire_departments (id, rev, name, enabled) VALUES (1, 1, 'Test Fire Department', true);
INSERT INTO users (id, rev, first_name, last_name, encrypted_password, username, password_exp_date, account_exp_date, locked, fire_department_id) VALUES (1, 1, 'Joe', 'Cool', '$2a$04$1msDGF6OLTquNVDDA4FWPegdZrzLMzycrOkTdvmKn3OVFNlfw8q8u', 'joecool', null, null, false, 1);

INSERT INTO contacts (id, rev, fire_department_id, first_name, last_name, single_name, cell_phone, email) VALUES (1, 1, 1, 'Joe', 'Cool', '', '', '');
INSERT INTO contacts (id, rev, fire_department_id, first_name, last_name, single_name, cell_phone, email) VALUES (2, 1, 1, 'Maxwell', 'Smart', '', '', '');
INSERT INTO contacts (id, rev, fire_department_id, first_name, last_name, single_name, cell_phone, email) VALUES (3, 1, 1, 'Alice', 'Smith', '', '', '');
INSERT INTO contacts (id, rev, fire_department_id, first_name, last_name, single_name, cell_phone, email) VALUES (4, 1, 1, 'Donald', 'Duck', '', '', '');
INSERT INTO contacts (id, rev, fire_department_id, first_name, last_name, single_name, cell_phone, email) VALUES (5, 1, 1, 'Mickey', 'Mouse', '', '', '');
