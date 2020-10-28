DELETE FROM hopes.sections_roles;
DELETE FROM hopes.sections;
----------------------- Configuration Menú  ----------------------------------------
--##################### Section: Hopes
INSERT INTO hopes.sections(
	sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Hopes',
        'Home',
        true,
        true,
        1,
        null,
        'assets/img/modules/hopes-launch.png',
        '/hopes');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Médica Reumatología' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Médico VIH' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor CAU' LIMIT 1));

-- Role_Pharmacist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));


--##################### Section: Administración
INSERT INTO hopes.sections(
	sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Administración',
        'Sección que contiene las gestiones del usuario logado',
        true,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1),
        'assets/img/modules/administracion.png',
        '/hopes/management');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Médica Reumatología' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Médico VIH' LIMIT 1));

-- Role_Pharmacist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

--##################### Section: Cuadro de Mando
INSERT INTO hopes.sections(
	sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Cuadro de Mando',
        'Sección que contiene el Cuadro de Mando de la patología',
        true,
        true,
		(SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1),
        'assets/img/modules/cuadro-de-mando.png',
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Cuadro de Mando' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Cuadro de Mando' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Cuadro de Mando' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Médica Reumatología' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Cuadro de Mando' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Médico VIH' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Cuadro de Mando' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

--##################### Section: Paciente
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Paciente',
        'Sección que contiene el Menú completo tras la seleccion de un Paciente',
        true,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1),
        'assets/img/modules/planes-atencion.png',
        '/hopes/pathology/patients');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Calendario
INSERT INTO hopes.sections(
	sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Calendario',
        'Sección que contiene el Calendario de citas de la patología',
        true,
        false,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1),
        'assets/img/modules/calendario.png',
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Calendario' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Calendario' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Calendario' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Médica Reumatología' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Calendario' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Médico VIH' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Calendario' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));


--##################### Section: Alertas
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Alertas',
        'Sección que contiene las Alertas de la patología',
        true,
        false,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1),
        'assets/img/modules/alertas.png',
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Alertas' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Alertas' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Alertas' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Médica Reumatología' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Alertas' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Médico VIH' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Alertas' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

--##################### Section: Administración 	Secciones
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Secciones',
        'Sección que contiene la gestión de secciones de la aplicación',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
        'assets/img/modules/alertas.png',
        '/hopes/management/sections');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Secciones' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

--##################### Section: Administración 	Gestión de Usuarios
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Gestión de Usuarios',
        'Sección que contiene la gestión de los usuarios de la aplicación',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
        null,
        '/hopes/management/users');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Gestión de Usuarios' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

--##################### Section: Management - Gestión de Pacientes
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Gestión de Pacientes',
        'Sección que contiene la Gestión de Pacientes',
        true,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
        'assets/img/modules/planes-atencion.png',
        '/hopes/management/patients');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Gestión de Pacientes' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Gestión de Pacientes' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Role_Medic
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Gestión de Pacientes' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

--##################### Section: Administración 	Gestión de Roles
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Gestión de Roles',
        'Sección que contiene la gestión de los roles de la aplicación',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
        null,
        '/hopes/management/roles');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Gestión de Roles' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

--##################### Section: Administración 	Dispensaciones
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Dispensaciones',
        'Sección que contiene la carga de dispensaciones',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
        'assets/img/modules/dispensaciones.png',
        '/hopes/management/dispensations');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Dispensaciones' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Dispensaciones' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

--##################### Section: Administración 	Gestión de Medicamentos
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Gestión de Medicamentos',
        'Sección que contiene la Gestión de Medicamentos',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
        'assets/img/modules/medicamentos.png',
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Gestión de Medicamentos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Gestión de Medicamentos' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

-- Section: Administración 	Informes
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Informes',
        'Sección que contiene la configuración de los Informes',
        false,
        false,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Informes' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

--##################### Section: Soporte
INSERT INTO hopes.sections(
	sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Soporte',
         'Sección que contiene la configuración de las opciones de soporte',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Hopes' LIMIT 1),
        null,
        '/hopes/contact');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Soporte' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Manager_CAU
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Soporte' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Gestor CAU' LIMIT 1));


--##################### Section: Cuadro de Mando - Información Diagnóstico
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Información Diagnóstico',
        'Sección que contiene la información de diagnóstico de la patologia',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Cuadro de Mando' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Cuadro de Mando' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));


--##################### Section: Cuadro de Mando - Información Tratamientos
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Información de Tratamientos',
        'Sección que contiene la información de los tratamientos de la patologia',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Cuadro de Mando' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Cuadro de Mando' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Cuadro de Mando - Resultados en Salud
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Información Resultados en Salud',
        'Sección que contiene los Resultados en salud de la patologia',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Cuadro de Mando' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Cuadro de Mando' LIMIT 1),
        null,
        '/hopes/dashboard/health-outcomes');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Información Resultados en Salud' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Manager_CAU
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Información Resultados en Salud' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Cuadro de Mando - Información Pacientes/Dosis
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Información Pacientes/Dosis',
        'Sección que contiene la Información de Pacientes/Dosis de la patologia',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Cuadro de Mando' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Cuadro de Mando' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Información Pacientes/Dosis' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Información Pacientes/Dosis' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Cuadro de Mando - Información Farmaeconómicos
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Información Farmaeconómicos',
        'Sección que contiene la Información Farmaeconómica de la patologia',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Cuadro de Mando' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Cuadro de Mando' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Información Farmaeconómicos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Información Farmaeconómicos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Cuadro de Mando Paciente - Cuadro de Mando Paciente
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Cuadro de Mando Paciente',
        'Sección que contiene la Cuadro de Mando Paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '/hopes/pathology/patients/dashboard');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Cuadro de Mando Paciente' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Cuadro de Mando Paciente' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Paciente - Datos del paciente
INSERT into hopes.sections (
       sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal)
VALUES (
       nextval('hopes.sections_sec_id_seq'),
       'Datos del paciente', 'Sección que contiene los datos generales del paciente',
       true,
       (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
       (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
       '',
       '#',
       false);

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Datos del paciente - Datos personales
INSERT into hopes.sections (
       sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal)
VALUES (
       nextval('hopes.sections_sec_id_seq'),
       'Datos personales', 'Datos personales del paciente',
       true,
       (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1)), null), 1)) ,
       (select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1),
       '',
       '/hopes/pathology/patients/personal-information',
       false);

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Datos personales' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Datos personales' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Datos personales' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Datos del paciente - Datos sociodemográficos
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Datos sociodemográficos',
        'Sección que contiene los datos sociodemograficos de un paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1),
        null,
        '/hopes/pathology/patients/sociodemographic-data');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Datos sociodemográficos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Datos sociodemográficos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Datos del paciente - Datos de ensayos clínicos
INSERT into hopes.sections (
       sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal)
VALUES (
       nextval('hopes.sections_sec_id_seq'),
       'Datos de ensayos clínicos', 'Datos sobre ensayos clínicos',
       true,
       (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1)), null), 1)) ,
       (select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1),
       '',
       '/hopes/pathology/patients/work-groups',
       false);

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Datos de ensayos clínicos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Datos de ensayos clínicos' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Datos de ensayos clínicos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Datos del paciente - Evaluación del estado físico
INSERT into hopes.sections (
       sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal)
VALUES (
       nextval('hopes.sections_sec_id_seq'),
       'Evaluación del estado físico', 'Evaluación del estado físico',
       true,
       (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1)), null), 1)) ,
       (select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1),
       '',
       '/hopes/pathology/patients/physical-condition',
       false);

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Evaluación del estado físico' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Evaluación del estado físico' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Evaluación del estado físico' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Datos del paciente - Antecedentes Familiares
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Antecedentes Familiares',
        'Sección que contiene los antecedentes del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1),
        null,
        '/hopes/pathology/patients/family-history');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Antecedentes Familiares' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Antecedentes Familiares' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Datos del paciente - Hábitos de consumo
INSERT into hopes.sections (
       sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal)
VALUES (
       nextval('hopes.sections_sec_id_seq'),
       'Hábitos de consumo', 'Hábitos de consumo de alcohol y tabaco',
       true,
       (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1)), null), 1)) ,
       (select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1),
       '',
       '/hopes/pathology/patients/consumption-habits',
       false);

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Hábitos de consumo' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Hábitos de consumo' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Hábitos de consumo' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Cuadro de Mando Paciente - Diagnóstico
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Diagnósticos',
        'Sección que contiene los diagnosticos del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Diagnósticos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Diagnósticos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Diagnósticos - Diagnóstico Principal
INSERT into hopes.sections (
       sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal)
VALUES (
       nextval('hopes.sections_sec_id_seq'),
       'Diagnóstico Principal', 'Diagnóstico principal',
       true,
       (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Diagnósticos' LIMIT 1)), null), 1)) ,
       (select sec_id from hopes.sections where sec_title ='Diagnósticos' LIMIT 1),
       '',
       '/hopes/pathology/patients/diagnosis/principal-diagnosis',
       false);

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Diagnóstico Principal' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Diagnóstico Principal' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Diagnóstico Principal' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Diagnósticos - Diagnósticos Secundarios
INSERT into hopes.sections (
       sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal)
VALUES (
       nextval('hopes.sections_sec_id_seq'),
       'Diagnósticos Secundarios', 'Diagnósticos secundarios',
       true,
       (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Diagnósticos' LIMIT 1)), null), 1)) ,
       (select sec_id from hopes.sections where sec_title ='Diagnósticos' LIMIT 1),
       '',
       '/hopes/pathology/patients/diagnosis/secundary-diagnosis',
       false);

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Diagnósticos Secundarios' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Diagnósticos Secundarios' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Diagnósticos Secundarios' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Diagnósticos - Comorbilidades
INSERT into hopes.sections (
       sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal)
VALUES (
       nextval('hopes.sections_sec_id_seq'),
       'Comorbilidades', 'Comorbilidades',
       true,
       (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Diagnósticos' LIMIT 1)), null), 1)) ,
       (select sec_id from hopes.sections where sec_title ='Diagnósticos' LIMIT 1),
       '',
       '/hopes/pathology/patients/diagnosis/comorbidities',
       false);

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Comorbilidades' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Comorbilidades' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Comorbilidades' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Cuadro de Mando Paciente - Seguimiento
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Seguimiento',
        'Sección que contiene el Seguimiento del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '/hopes/pathology/patients/tracing');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Seguimiento' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Seguimiento' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Cuadro de Mando Paciente - Tratamientos
INSERT into hopes.sections (
       sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal)
VALUES (
       nextval('hopes.sections_sec_id_seq'),
       'Tratamientos', 'Tratamientos',
       true,
       (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
       (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
       '',
       '#',
       false);

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Tratamientos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Tratamientos' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Tratamientos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Tratamientos - Farmacológicos
INSERT into hopes.sections (
       sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal)
VALUES (
       nextval('hopes.sections_sec_id_seq'),
       'Farmacológicos', 'Tratamiento principal',
       true,
       (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Tratamientos' LIMIT 1)), null), 1)) ,
       (select sec_id from hopes.sections where sec_title ='Tratamientos' LIMIT 1),
       '',
       '/hopes/pathology/patients/principal-treatment',
       false);

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Farmacológicos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Farmacológicos' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Farmacológicos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Tratamientos - No farmacológico (fototerapia)
INSERT into hopes.sections (
       sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal)
VALUES (
       nextval('hopes.sections_sec_id_seq'),
       'No farmacológico (fototerapia)', 'Fototerapia',
       true,
       (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Tratamientos' LIMIT 1)), null), 1)) ,
       (select sec_id from hopes.sections where sec_title ='Tratamientos' LIMIT 1),
       '',
       '/hopes/pathology/patients/phototherapy',
       false);

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='No farmacológico (fototerapia)' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='No farmacológico (fototerapia)' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='No farmacológico (fototerapia)' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Cuadro de Mando Paciente - Evolución de índices clínicos
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Evolución de índices clínicos',
        'Sección que contiene la Evolución de índices clínicos del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Evolución de índices clínicos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Evolución de índices clínicos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Evolución de índices clínicos - PASI, BSA Y PGA
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'PASI, BSA Y PGA',
        'Sección que contiene los índices clínicos PASI, BSA Y PGA del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Evolución de índices clínicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Evolución de índices clínicos' LIMIT 1),
        null,
        '/hopes/pathology/patients/evolution-clinical-indices/pasi-bsa-pga');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='PASI, BSA Y PGA' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='PASI, BSA Y PGA' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Evolución de índices clínicos - DLQI
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'DLQI',
        'Sección que contiene el indice DLQI del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Evolución de índices clínicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Evolución de índices clínicos' LIMIT 1),
        null,
        '/hopes/pathology/patients/evolution-clinical-indices/dlqi');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='DLQI' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='DLQI' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));


--##################### Section: Evolución de índices clínicos - EAV Y PASE
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'EAV Y PASE',
        'Sección que contiene el indice EAV Y PASE del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Evolución de índices clínicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Evolución de índices clínicos' LIMIT 1),
        null,
        '/hopes/pathology/patients/evolution-clinical-indices/eav-pase');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='EAV Y PASE' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='EAV Y PASE' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Evolución de índices clínicos - Otros índices: NAPSI
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Otros índices: NAPSI',
        'Sección que contiene el indice Otros índices: NAPSI  del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Evolución de índices clínicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Evolución de índices clínicos' LIMIT 1),
        null,
        '/hopes/pathology/patients/evolution-clinical-indices/napsi');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Otros índices: NAPSI' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Otros índices: NAPSI' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Paciente - Evaluación de análisis clínicos
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Evaluación de análisis clínicos',
        'Sección que contiene la Evaluación de análisis clínicos del paciente ',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '/hopes/pathology/patients/physical-condition');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Evaluación de análisis clínicos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Evaluación de análisis clínicos' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Evaluación de análisis clínicos - Hemograma
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Hemograma',
        'Sección que contiene los hemogramas del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Evaluación de análisis clínicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Evaluación de análisis clínicos' LIMIT 1),
        null,
        '/hopes/pathology/patients/blood-count');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Hemograma' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Hemograma' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Evaluación de análisis clínicos - Perfil metabólico
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Perfil metabólico',
        'Sección que contiene el perfil metabólico del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Evaluación de análisis clínicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Evaluación de análisis clínicos' LIMIT 1),
        null,
        '/hopes/pathology/patients/metabolic-profile');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Perfil metabólico' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Perfil metabólico' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Evaluación de análisis clínicos - Bioquímica hepática y renal
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Bioquímica hepática y renal',
        'Sección que contiene la Bioquímica hepática y renal del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Evaluación de análisis clínicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Evaluación de análisis clínicos' LIMIT 1),
        null,
        '/hopes/pathology/patients/kidney-liver-biochemistry');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Bioquímica hepática y renal' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Bioquímica hepática y renal' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Evaluación de análisis clínicos - Serología (VHC, VHB, otras infecciones y vacunas)
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Serología (VHC, VHB, otras infecciones y vacunas)',
        'Sección que contiene las Serología (VHC, VHB, otras infecciones y vacunas) del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Evaluación de análisis clínicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Evaluación de análisis clínicos' LIMIT 1),
        null,
        '/hopes/pathology/patients/serology');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Serología (VHC, VHB, otras infecciones y vacunas)' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Serología (VHC, VHB, otras infecciones y vacunas)' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Evaluación de análisis clínicos - Anticuerpo y antígeno leucocitario
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Anticuerpo y antígeno leucocitario',
        'Sección que contiene la información de los Anticuerpo y antígeno leucocitario del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Evaluación de análisis clínicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Evaluación de análisis clínicos' LIMIT 1),
        null,
        '/hopes/pathology/patients/leukocyte-antibody-antigen');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Anticuerpo y antígeno leucocitario' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Anticuerpo y antígeno leucocitario' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Evaluación de análisis clínicos - Monitorización de fármacos biológicos
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Monitorización de fármacos biológicos',
        'Sección que contiene la Monitorización de fármacos biológicos del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Evaluación de análisis clínicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Evaluación de análisis clínicos' LIMIT 1),
        null,
        '/hopes/pathology/patients/biological-drug-monitoring');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Monitorización de fármacos biológicos' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Monitorización de fármacos biológicos' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Cuadro de Mando Paciente - Galería de fotos
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Galería de fotos',
        'Sección que contiene la Galería de fotos del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '/hopes/pathology/patients/gallery');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Galería de fotos' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Galería de fotos' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Cuadro de Mando Paciente - Exploraciones complementarias de imagen
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Exploraciones complementarias de imagen',
        'Sección que contiene las Exploraciones complementarias de imagen del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '/hopes/pathology/patients/complementary-imaging-scans');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Exploraciones complementarias de imagen' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Exploraciones complementarias de imagen' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));


-- Section: Cuadro de Mando Paciente - Paciente compartido
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Paciente compartido',
        'Sección que contiene informacion del Paciente compartido',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '/hopes/pathology/patients/shared-patients');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Paciente compartido' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Paciente compartido' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Cuadro de Mando Paciente - Adherencia al tratamiento
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Adherencia al tratamiento',
        'Sección que contiene la Adherencia al tratamiento del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Adherencia al tratamiento' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Adherencia al tratamiento' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Adherencia al tratamiento - Test de Morisky Green
INSERT into hopes.sections (
       sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal)
VALUES (
       nextval('hopes.sections_sec_id_seq'),
       'Test de Morisky Green', 'Test de Morisky Green',
       true,
       (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Adherencia al tratamiento' LIMIT 1)), null), 1)) ,
       (select sec_id from hopes.sections where sec_title ='Adherencia al tratamiento' LIMIT 1),
       '',
       '/hopes/pathology/patients/morisky-green',
       false);

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Test de Morisky Green' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Test de Morisky Green' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Test de Morisky Green' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

--##################### Section: Adherencia al tratamiento - Test de Haynes Sackett
INSERT into hopes.sections (
       sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal)
VALUES (
       nextval('hopes.sections_sec_id_seq'),
       'Test de Haynes Sackett', 'Test de Haynes Sackett',
       true,
       (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Adherencia al tratamiento' LIMIT 1)), null), 1)) ,
       (select sec_id from hopes.sections where sec_title ='Adherencia al tratamiento' LIMIT 1),
       '',
       '/hopes/pathology/patients/haynes-sackett',
       false);

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Test de Haynes Sackett' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Test de Haynes Sackett' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Test de Haynes Sackett' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Cuadro de Mando Paciente - Consentimiento
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Consentimiento',
        'Sección que contiene el Consentimiento del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '/hopes/pathology/patients/consent');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Consentimiento' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Consentimiento' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Diagnóstico - Pacientes por indicación
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Pacientes por indicación',
        'Sección que contiene informacion de diagnostico de Pacientes por indicación',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1),
        null,
        '/hopes/dashboard/diagnosis/patients-indication');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes por indicación' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes por indicación' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Diagnóstico - Pacientes por diagnóstico CIE9
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Pacientes por diagnóstico CIE',
        'Sección que contiene  informacion de diagnostico de Pacientes por diagnóstico CIE',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1),
        null,
        '/hopes/dashboard/diagnosis/cie');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes por diagnóstico CIE' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes por diagnóstico CIE' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Diagnóstico - Pacientes por tipo de tratamiento
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Pacientes por tipo de tratamiento',
        'Sección que contiene informacion de diagnostico de Pacientes por tipo de tratamiento',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1),
        null,
        '/hopes/dashboard/diagnosis/patients-treatment');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes por tipo de tratamiento' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes por tipo de tratamiento' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Diagnóstico - Pacientes por tratamientos combinados
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Pacientes por tratamientos combinados',
        'Sección que contiene  informacion de diagnostico de Pacientes por tratamientos combinados',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1),
        null,
        '/hopes/dashboard/diagnosis/patients-combined-treatments');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes por tratamientos combinados' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes por tratamientos combinados' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Diagnóstico - Motivo del último cambio de tratamiento biológico
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Motivo del cambio/suspensión de tratamiento biológico',
        'Motivo del cambio/suspensión de tratamiento biológico',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1),
        null,
        '/hopes/dashboard/diagnosis/reasons');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Motivo del cambio/suspensión de tratamiento biológico' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Motivo del cambio/suspensión de tratamiento biológico' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Diagnóstico - Número de cambios de tratamiento biológico
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Número de cambios de tratamiento biológico',
        'Sección que contiene informacion de diagnostico del Número de cambios de tratamiento biológico',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1),
        null,
        '/hopes/dashboard/diagnosis/number-changes-biological-treatment');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Número de cambios de tratamiento biológico' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Número de cambios de tratamiento biológico' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Tratamientos - Número de cambios de tratamiento biológico
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Pacientes en tratamiento según agentes',
        'Sección que contiene informacion de tratamiento de Pacientes en tratamiento según tipos de agentes',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1),
        null,
        '/hopes/dashboard/treatments/treatments-agents');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes en tratamiento según agentes' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes en tratamiento según agentes' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Tratamientos - Tratamiento biológico en pacientes con psoriasis en placas
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Tratamiento biológico en pacientes',
        'Sección que contiene informacion de tratamiento de Tratamiento biológico en pacientes',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1),
        null,
        '/hopes/dashboard/treatments/treatments-patients');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Tratamiento biológico en pacientes' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Tratamiento biológico en pacientes' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Pacientes/Dosis - Pacientes según dosis/frecuencia del tratamiento biológico
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Pacientes según dosis/frecuencia del tratamiento biológico',
        'Sección que contiene informacion de Pacientes según dosis/frecuencia del tratamiento biológico',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Pacientes/Dosis' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Pacientes/Dosis' LIMIT 1),
        null,
        '/hopes/dashboard/patient-dose/biological-treatment-frequency');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes según dosis/frecuencia del tratamiento biológico' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes según dosis/frecuencia del tratamiento biológico' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Farmaeconómicos - Consumo de tratamientos biológicos
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Consumo de tratamientos biológicos',
        'Sección que contiene la Información Farmaeconómicos de Consumo de tratamientos biológicos',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Farmaeconómicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Farmaeconómicos' LIMIT 1),
        null,
        '/hopes/dashboard/pharmacoeconomic/consumption-biological-treatment');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Consumo de tratamientos biológicos' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Consumo de tratamientos biológicos' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Farmaeconómicos - Coste por tratamientos biológicos
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Coste por tratamientos biológicos',
        'Sección que contiene el Coste por tratamiento biológico',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Farmaeconómicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Farmaeconómicos' LIMIT 1),
        null,
        '/hopes/dashboard/pharmacoeconomic/total-expenses-biological-treatment');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Coste por tratamientos biológicos' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Coste por tratamientos biológicos' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));