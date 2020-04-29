-- INITIALIZE A DATABASE --
INSERT INTO hopes.hospitals (hos_id, hos_name) VALUES (nextval('hopes.hospitals_hos_id_seq'), 'Hospital Perpetuo Confinamiento');

-- admin/password
INSERT INTO hopes.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, date_created, date_updated)
VALUES (1, 'admin', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'jordi.ripoll@plexus.es', 1, current_timestamp, current_timestamp );

-- 1 role
INSERT INTO hopes.users_roles (uro_id, uro_user_id, uro_rol_id) VALUES (nextval('hopes.users_roles_uro_id_seq'), 1, 1);

-- doctor/password
INSERT INTO hopes.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, date_created, date_updated)
VALUES (2, 'doctor', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'angel.broncanorodriguez@plexus.es', 1, current_timestamp, current_timestamp );

-- farmaceutico/password
INSERT INTO hopes.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, date_created, date_updated)
VALUES (3, 'farmaceutico', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'victor.gomeztejada@plexus.es', 1, current_timestamp, current_timestamp );

-- 2 roles
INSERT INTO hopes.users_ROLES (uro_id, uro_user_id, uro_rol_id) VALUES (nextval('hopes.users_roles_uro_id_seq'), 2, 1);
INSERT INTO hopes.users_ROLES (uro_id, uro_user_id, uro_rol_id) VALUES (nextval('hopes.users_roles_uro_id_seq'), 2, 7);

