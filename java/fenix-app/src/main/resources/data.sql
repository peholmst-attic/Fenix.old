INSERT INTO fire_departments (id, rev, name) VALUES (1, 1, 'Test Fire Department');
INSERT INTO users (id, rev, first_name, last_name, encrypted_password, username, expired, locked, password_expired, enabled, fire_department_id) VALUES (1, 1, 'Joe', 'Cool', '$2a$04$1msDGF6OLTquNVDDA4FWPegdZrzLMzycrOkTdvmKn3OVFNlfw8q8u', 'joecool', false, false, false, true, 1);
