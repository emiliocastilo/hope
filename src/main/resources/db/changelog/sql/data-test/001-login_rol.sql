----------------------------------------------------------------- ROLES -------------------------------------------------------------------------------
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Administrador', 'Rol administrador. Tiene le máximo nivel de acceso a la aplicación');
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Gestor CAU', 'Rol gestor del CAU. Permite la gestión de las credenciales de acceso de los diferentes usuarios de la aplicación, recuperación y asignación de perfiles');
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Jefe de Servicio Dermatología', 'Rol Jefe de servicio Dermatología. Personaliza las características de los formularios, encuestas, tramtamientos, medicamentos, etc a la aplicación');
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Farmacéutico', 'Rol farmacéutico. Posee acceso al cuadro de mandos, calendario, listado de pacientes. Posibilidad de personalización de formularios relativos a su alcance');
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Médico Dermatología', 'Rol médico de la patología dermatología');
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Médico VIH', 'Rol médico de la patología VIH');
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Médica Reumatología', 'Rol médico de la patología reumatología');
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Enfermero', 'Rol enfermero. Posee acceso al cuadro de mandos, calendario, listado de pacientes. Posibilidad de personalización de formularios relativos a su alcance');
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Paciente', 'Rol gestor. Posee acceso a la información que los roles de seguimiento le concedan, ademas de a encuestas, recordatorios de consulta');
----------------------------------------------------------------- ROLES -------------------------------------------------------------------------------

-- Test Configuration - Users/Roles
-- Configuration Initial - Users
-- admin/password
INSERT INTO hopes.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, date_created, date_updated)
VALUES (nextval('hopes.users_usr_id_seq'),
        'admin',
        '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.',
        'juan.martinez@plexus.es',
        (select hos_id from hopes.hospitals where hos_name = 'Hopes - Servicios de Salud'),
        current_timestamp,
        current_timestamp );

INSERT INTO hopes.users_roles (uro_id, uro_user_id, uro_rol_id)
VALUES (nextval('hopes.users_roles_uro_id_seq'),
        (select usr_id from hopes.users where usr_name ='admin' LIMIT 1),
        (select rol_id from hopes.roles where rol_name ='Administrador' LIMIT 1));

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
        (select rol_id from hopes.roles where rol_name ='Jefe de Servicio Dermatología' LIMIT 1));

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
