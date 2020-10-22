-- INITIALIZE A DATABASE --
DELETE FROM hopes.hospitals_services;
DELETE FROM hopes.sections_roles;
DELETE FROM hopes.users_roles;
DELETE FROM hopes.roles;
DELETE FROM hopes.hospitals;
DELETE FROM hopes.indications;
DELETE FROM hopes.services_pathologies;
DELETE FROM hopes.pathologies;
DELETE FROM hopes.users;

-- Configuration Initial - Hospitals
INSERT INTO hopes.hospitals (hos_id, hos_name, hos_cie) VALUES (nextval('hopes.hospitals_hos_id_seq'), 'Hopes - Servicios de Salud', 'CIE9');
INSERT INTO hopes.hospitals (hos_id, hos_name, hos_cie) VALUES (nextval('hopes.hospitals_hos_id_seq'), 'Hospital Perpetuo Confinamiento', 'CIE10');

-- Configuration Initial - Pathologies
INSERT INTO hopes.pathologies (pth_id, pth_name, pth_description) VALUES (nextval('hopes.pathologies_pth_id_seq'), 'Dermatología', 'Patologia Dermatología');
INSERT INTO hopes.pathologies (pth_id, pth_name, pth_description) VALUES (nextval('hopes.pathologies_pth_id_seq'), 'Reumatología', 'Patologia Reumatología');
INSERT INTO hopes.pathologies (pth_id, pth_name, pth_description) VALUES (nextval('hopes.pathologies_pth_id_seq'), 'VIH', 'Patologia VIH');

-- Configuration Initial - Users
-- admin/password
INSERT INTO hopes.users (usr_id, usr_username, usr_password, usr_email, date_created, date_updated, usr_name, usr_surname, usr_phone, usr_dni)
VALUES (nextval('hopes.users_usr_id_seq'),
        'admin',
        '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.',
        'jordi.ripoll@plexus.es',
         current_timestamp,
         current_timestamp,
          'admin',
          'admin',
          'no',
          '43000000A');
