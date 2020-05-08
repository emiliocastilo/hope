-- Test Configuration - Users/Roles

-- doctor/password
INSERT INTO hopes.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, date_created, date_updated)
VALUES (nextval('hopes.users_usr_id_seq'),
        'doctor',
        '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.',
        'angel.broncanorodriguez@plexus.es',
        (select hos_id from hopes.hospitals where hos_name = 'Hopes - Servicios de Salud'),
         current_timestamp,
         current_timestamp );

INSERT INTO hopes.users_roles (uro_id, uro_user_id, uro_rol_id)
VALUES (nextval('hopes.users_roles_uro_id_seq'),
        (select usr_id from hopes.users where usr_name ='doctor' LIMIT 1),
        (select rol_id from hopes.roles where rol_name ='Gestor' LIMIT 1));

INSERT INTO hopes.users_roles (uro_id, uro_user_id, uro_rol_id)
VALUES (nextval('hopes.users_roles_uro_id_seq'),
        (select usr_id from hopes.users where usr_name ='doctor' LIMIT 1),
        (select rol_id from hopes.roles where rol_name ='Médico Dermatología' LIMIT 1));

-- farmaceutico/password
INSERT INTO hopes.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, date_created, date_updated)
VALUES (nextval('hopes.users_usr_id_seq'),
        'farmaceutico',
        '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.',
        'victor.gomeztejada@plexus.es',
        (select hos_id from hopes.hospitals where hos_name = 'Hopes - Servicios de Salud'),
         current_timestamp,
         current_timestamp );

INSERT INTO hopes.users_roles (uro_id, uro_user_id, uro_rol_id)
VALUES (nextval('hopes.users_roles_uro_id_seq'),
        (select usr_id from hopes.users where usr_name ='farmaceutico' LIMIT 1),
        (select rol_id from hopes.roles where rol_name ='Farmacéutico' LIMIT 1));
