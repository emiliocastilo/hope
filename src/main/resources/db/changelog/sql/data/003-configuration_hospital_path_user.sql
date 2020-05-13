-- INITIALIZE A DATABASE --

-- Configuration Initial - Hospitals
INSERT INTO hopes.hospitals (hos_id, hos_name) VALUES (nextval('hopes.hospitals_hos_id_seq'), 'Hopes - Servicios de Salud');
INSERT INTO hopes.hospitals (hos_id, hos_name) VALUES (nextval('hopes.hospitals_hos_id_seq'), 'Hospital Perpetuo Confinamiento');

-- Configuration Initial - Pathologies
INSERT INTO hopes.pathologies (pth_id, pth_name, pth_description) VALUES (nextval('hopes.pathologies_pth_id_seq'), 'Dermatología', 'Patologia Dermatología');
INSERT INTO hopes.pathologies (pth_id, pth_name, pth_description) VALUES (nextval('hopes.pathologies_pth_id_seq'), 'Reumatología', 'Patologia Reumatología');
INSERT INTO hopes.pathologies (pth_id, pth_name, pth_description) VALUES (nextval('hopes.pathologies_pth_id_seq'), 'VIH', 'Patologia VIH');

-- Configuration Initial - Users
-- admin/password
INSERT INTO hopes.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, date_created, date_updated)
VALUES (nextval('hopes.users_usr_id_seq'),
        'admin',
        '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.',
        'jordi.ripoll@plexus.es',
        (select hos_id from hopes.hospitals where hos_name = 'Hopes - Servicios de Salud'),
         current_timestamp,
         current_timestamp );

INSERT INTO hopes.users_roles (uro_id, uro_user_id, uro_rol_id)
VALUES (nextval('hopes.users_roles_uro_id_seq'),
        (select usr_id from hopes.users where usr_name ='admin' LIMIT 1),
        (select rol_id from hopes.roles where rol_name ='Administrador' LIMIT 1));
