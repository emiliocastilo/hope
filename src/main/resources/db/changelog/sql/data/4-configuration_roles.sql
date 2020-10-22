
----------------------------------------------------------------- ROLES -------------------------------------------------------------------------------
INSERT INTO hopes.roles VALUES (nextval('hopes.roles_rol_id_seq'),
'Administrador',
'Rol administrador. Tiene el máximo nivel de acceso a la aplicación',
'ROLE_ADMIN',
(SELECT min(hos_id) FROM hopes.hospitals),
(SELECT min(hss_srv_id) FROM hopes.hospitals_services WHERE hss_hos_id = (SELECT min(hos_id) FROM hopes.hospitals)),
(SELECT min(srp_pth_id) FROM hopes.services_pathologies WHERE srp_srv_id = (SELECT min(hss_srv_id) FROM hopes.hospitals_services WHERE hss_hos_id = (SELECT min(hos_id) FROM hopes.hospitals))));
INSERT INTO hopes.roles VALUES (nextval('hopes.roles_rol_id_seq'),'Gestor CAU', 'Rol gestor del CAU. Permite la gestión de las credenciales de acceso de los diferentes usuarios de la aplicación, recuperación y asignación de perfiles');
INSERT INTO hopes.roles VALUES (nextval('hopes.roles_rol_id_seq'),'Gestor', 'Rol gestor. Personaliza las características de los formularios, encuestas, tramtamientos, medicamentos, etc a la aplicación');
INSERT INTO hopes.roles VALUES (nextval('hopes.roles_rol_id_seq'),'Farmacéutico', 'Rol farmacéutico. Posee acceso al cuadro de mandos, calendario, listado de pacientes. Posibilidad de personalización de formularios relativos a su alcance');
INSERT INTO hopes.roles VALUES (nextval('hopes.roles_rol_id_seq'),'Médico Dermatología', 'Rol médico de la patología dermatología');
INSERT INTO hopes.roles VALUES (nextval('hopes.roles_rol_id_seq'),'Médico VIH', 'Rol médico de la patología VIH');
INSERT INTO hopes.roles VALUES (nextval('hopes.roles_rol_id_seq'),'Médica Reumatología', 'Rol médico de la patología reumatología');
INSERT INTO hopes.roles VALUES (nextval('hopes.roles_rol_id_seq'),'Enfermero', 'Rol enfermero. Posee acceso al cuadro de mandos, calendario, listado de pacientes. Posibilidad de personalización de formularios relativos a su alcance');
INSERT INTO hopes.roles VALUES (nextval('hopes.roles_rol_id_seq'),'Paciente', 'Rol gestor. Posee acceso a la información que los roles de seguimiento le concedan, ademas de a encuestas, recordatorios de consulta');
----------------------------------------------------------------- ROLES -------------------------------------------------------------------------------
----------------------------------------------------------------- USERS ROLES -------------------------------------------------------------------------
INSERT INTO hopes.users_roles (uro_id, uro_user_id, uro_rol_id)
VALUES (nextval('hopes.users_roles_uro_id_seq'),
        (select usr_id from hopes.users where usr_username ='admin' LIMIT 1),
        (select rol_id from hopes.roles where rol_code ='ROLE_ADMIN' LIMIT 1));
----------------------------------------------------------------- USERS ROLES -------------------------------------------------------------------------
