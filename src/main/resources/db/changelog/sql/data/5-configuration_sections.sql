DELETE FROM hopes.sections;
----------------------- Configuration Menú  ----------------------------------------
-- Section: Hopes
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


-- Section: Administración
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

-- Section: Cuadro de Mando
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

-- Section: Calendario
INSERT INTO hopes.sections(
	sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Calendario',
        'Sección que contiene el Calendario de citas de la patología',
        true,
        true,
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


-- Section: Alertas
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Alertas',
        'Sección que contiene las Alertas de la patología',
        true,
        true,
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

-- Section: Administración 	Secciones
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Secciones',
        'Sección que contiene la gestión de secciones de la aplicación',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
        'assets/img/modules/alertas.png',
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Secciones' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Section: Administración 	Gestión de Médicos
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Gestión de Médicos',
        'Sección que contiene la gestión de los médicos de la aplicación',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
        'assets/img/modules/medicos.png',
        '/hopes/management/medics');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Gestión de Médicos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Gestión de Médicos' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Section: Administración 	Gestión de Usuarios
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Gestión de Usuarios',
        'Sección que contiene la gestión de los usuarios de la aplicación',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Gestión de Usuarios' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Section: Management - Gestión de Pacientes
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

-- Section: Administración 	Gestión de Roles
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Gestión de Roles',
        'Sección que contiene la gestión de los roles de la aplicación',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Gestión de Roles' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Section: Administración 	Dispensaciones
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Dispensaciones',
        'Sección que contiene la carga de dispensaciones',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
        'assets/img/modules/dispensaciones.png',
        '#');

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

-- Section: Administración 	Gestión de Medicamentos
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
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Informes' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Section: Administración 	Soporte
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Soporte',
        'Sección que contiene la configuración de las opciones de soporte',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
        null,
        '#');

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


-- Section: Cuadro de Mando - Información Diagnóstico
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


-- Section: Cuadro de Mando - Información Tratamientos
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

-- Section: Cuadro de Mando - Resultados en Salud
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Información Resultados en Salud',
        'Sección que contiene los Resultados en salud de la patologia',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Cuadro de Mando' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Cuadro de Mando' LIMIT 1),
        null,
        '#');

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

-- Section: Cuadro de Mando - Información Pacientes/Dosis
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

-- Section: Cuadro de Mando - Información Farmaeconómicos
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

-- Section: Paciente - Paciente
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

-- Section: Cuadro de Mando Paciente - Cuadro de Mando Paciente
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

-- Section: Cuadro de Mando Paciente - Datos sociodemográficos
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Datos sociodemográficos',
        'Sección que contiene los datos sociodemograficos de un paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '#');

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

-- Section: Cuadro de Mando Paciente - Datos generales del paciente
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Datos generales del paciente',
        'Sección que contiene los datos generales del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Datos generales del paciente' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Datos generales del paciente' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Cuadro de Mando Paciente - Antecedentes Familiares
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Antecedentes Familiares',
        'Sección que contiene los antecedentes del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '#');

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

-- Section: Cuadro de Mando Paciente - Diagnóstico
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Diagnóstico',
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
		(select sec_id from hopes.sections where sec_title ='Diagnóstico' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Diagnóstico' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));


-- Section: Cuadro de Mando Paciente - Seguimiento
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Seguimiento',
        'Sección que contiene el Seguimiento del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '#');

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

-- Section: Cuadro de Mando Paciente - Tratamiento actual
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Tratamiento actual',
        'Sección que contiene el Tratamiento actual del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Tratamiento actual' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Tratamiento actual' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Cuadro de Mando Paciente - Tratamiento anteriores
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Tratamiento anteriores',
        'Sección que contiene los Tratamientos anteriores del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Tratamiento anteriores' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Tratamiento anteriores' LIMIT 1),
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

-- Section: Evolución de índices clínicos - PASI, BSA Y PGA
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'PASI, BSA Y PGA',
        'Sección que contiene los índices clínicos PASI, BSA Y PGA del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Evolución de índices clínicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Evolución de índices clínicos' LIMIT 1),
        null,
        '#');

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
        '#');

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


-- Section: Evolución de índices clínicos - EAV Y PASE
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'EAV Y PASE',
        'Sección que contiene el indice EAV Y PASE del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Evolución de índices clínicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Evolución de índices clínicos' LIMIT 1),
        null,
        '#');

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

-- Section: Evolución de índices clínicos - Otros índices: NAPSI
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Otros índices: NAPSI',
        'Sección que contiene el indice Otros índices: NAPSI  del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Evolución de índices clínicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Evolución de índices clínicos' LIMIT 1),
        null,
        '#');

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

-- Section: Cuadro de Mando Paciente - Evaluación de análisis clínicos
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Evaluación de análisis clínicos',
        'Sección que contiene la Evaluación de análisis clínicos del paciente ',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '#');

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

-- Section: Evaluación de análisis clínicos - Hemograma
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Hemograma',
        'Sección que contiene los hemogramas del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Evaluación de análisis clínicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Evaluación de análisis clínicos' LIMIT 1),
        null,
        '#');

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
        '#');

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
        '#');

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
        '#');

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
        '#');

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
        '#');

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
        '#');

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
        '#');

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
        '#');

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

-- Section: Cuadro de Mando Paciente - Incidencias
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Incidencias',
        'Sección que contiene Incidencias del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Incidencias' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Incidencias' LIMIT 1),
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
        '#');

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

-- Section: Cuadro de Mando Paciente - Exportar
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Exportar',
        'Sección que contiene las opciones de exportacion de la informacion del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Exportar' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Exportar' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Cuadro de Mando Paciente - Registro Visitas
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Registro Visitas',
        'Sección que contiene los Registro Visitas del paciente',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Registro Visitas' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Registro Visitas' LIMIT 1),
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
        '#');

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
        'Pacientes por diagnóstico CIE9',
        'Sección que contiene  informacion de diagnostico de Pacientes por diagnóstico CIE9',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes por diagnóstico CIE9' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes por diagnóstico CIE9' LIMIT 1),
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
        '#');

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
        '#');

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
        'Motivo del último cambio de tratamiento biológico',
        'Sección que contiene informacion de diagnostico del Motivo del cambio de tratamiento biológico de los últimos',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Motivo del último cambio de tratamiento biológico' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Motivo del último cambio de tratamiento biológico' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Diagnóstico - Motivo del último cambio de tratamiento biológico
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Motivo de la suspensión de tratamiento biológico',
        'Sección que contiene informacion de diagnostico del Motivo de la suspensión de tratamiento biológico',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Motivo de la suspensión de tratamiento biológico' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Motivo de la suspensión de tratamiento biológico' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Diagnóstico - Motivo del cambio de tratamiento biológico de los últimos 5 años
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Motivo del cambio de tratamiento biológico de los últimos 5 años',
        'Sección que contiene informacion de diagnostico del Motivo de la suspensión de tratamiento biológico',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Diagnóstico' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Motivo del cambio de tratamiento biológico de los últimos 5 años' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Motivo del cambio de tratamiento biológico de los últimos 5 años' LIMIT 1),
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
        '#');

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
        'Pacientes en tratamiento según agentes químicos',
        'Sección que contiene informacion de tratamiento de Pacientes en tratamiento según agentes químicos',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes en tratamiento según agentes químicos' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes en tratamiento según agentes químicos' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Tratamientos - Pacientes en tratamiento según agentes biológicos
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Pacientes en tratamiento según agentes biológicos',
        'Sección que contiene informacion de tratamiento de Pacientes en tratamiento según agentes biológicos',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes en tratamiento según agentes biológicos' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes en tratamiento según agentes biológicos' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Tratamientos - Tratamiento biológico en pacientes con psoriasis en placas
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Tratamiento biológico en pacientes con psoriasis en placas',
        'Sección que contiene informacion de tratamiento de Tratamiento biológico en pacientes con psoriasis en placas',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Tratamiento biológico en pacientes con psoriasis en placas' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Tratamiento biológico en pacientes con psoriasis en placas' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Tratamientos - Tratamiento biológico en pacientes con psoriasis palmo-plantar
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Tratamiento biológico en pacientes con psoriasis palmo-plantar',
        'Sección que contiene informacion de tratamiento de Tratamiento biológico en pacientes con psoriasis palmo-plantar',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Tratamiento biológico en pacientes con psoriasis palmo-plantar' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Tratamiento biológico en pacientes con psoriasis palmo-plantar' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Tratamientos - Tratamiento biológico en pacientes con eritrodermia
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Tratamiento biológico en pacientes con eritrodermia',
        'Sección que contiene informacion de tratamiento de Tratamiento biológico en pacientes con eritrodermia',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Tratamiento biológico en pacientes con eritrodermia' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Tratamiento biológico en pacientes con eritrodermia' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Tratamientos - Tratamiento biológico en pacientes con psoriasis pustulosa
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Tratamiento biológico en pacientes con psoriasis pustulosa',
        'Sección que contiene informacion de tratamiento de Tratamiento biológico en pacientes con psoriasis pustulosa',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información de Tratamientos' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Tratamiento biológico en pacientes con psoriasis pustulosa' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Tratamiento biológico en pacientes con psoriasis pustulosa' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Resultados en Salud - Pacientes según PASI
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Pacientes según PASI',
        'Sección que contiene la información de resultados en salud de Pacientes según PASI de la patologia',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Resultados en Salud' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Resultados en Salud' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes según PASI' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes según PASI' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Resultados en Salud - Pacientes según BSA
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Pacientes según BSA',
        'Sección que contiene  la información de resultados en salud de Pacientes según BSA de la patologia',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Resultados en Salud' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Resultados en Salud' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes según BSA' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes según BSA' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));


-- Section: Información Resultados en Salud - Pacientes según PGA
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Pacientes según PGA',
        'Sección que contiene  la información de resultados en salud de Pacientes según PGA de la patologia',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Resultados en Salud' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Resultados en Salud' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes según PGA' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes según PGA' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Resultados en Salud - Pacientes según DLQI
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Pacientes según DLQI',
        'Sección que contiene  la información de resultados en salud de Pacientes según DLQI de la patologia',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Resultados en Salud' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Resultados en Salud' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes según DLQI' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Pacientes según DLQI' LIMIT 1),
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
        '#');

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


-- Section: Información Farmaeconómicos - Consumo anual de tratamientos biológicos
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Consumo anual de tratamientos biológicos',
        'Sección que contiene la Información Farmaeconómicos de Consumo anual de tratamientos biológicos',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Farmaeconómicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Farmaeconómicos' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Consumo anual de tratamientos biológicos' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Consumo anual de tratamientos biológicos' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Consumo anual de tratamientos biológicos - Consumo mensual en euros
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Consumo mensual en euros',
        'Sección que contiene la Información del Consumo anual de tratamientos biologicos por Consumo mensual en euros',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Consumo anual de tratamientos biológicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Consumo anual de tratamientos biológicos' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Consumo mensual en euros' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Consumo mensual en euros' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Consumo anual de tratamientos biológicos - Consumo mensual acumulado en euros
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Consumo mensual acumulado en euros',
        'Sección que contiene la informacion del Consumo anual de tratamientos biológicos por Consumo mensual acumulado en euros',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Consumo anual de tratamientos biológicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Consumo anual de tratamientos biológicos' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Consumo mensual acumulado en euros' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Consumo mensual acumulado en euros' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Farmaeconómicos - Consumo medio de tratamientos biológicos
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Consumo medio de tratamientos biológicos',
        'Sección que contiene la informacion del Consumo medio de tratamientos biológicos',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Farmaeconómicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Farmaeconómicos' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Consumo medio de tratamientos biológicos' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Consumo medio de tratamientos biológicos' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Consumo medio de tratamientos biológicos
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Consumo mensual medio en euros',
        'Sección que contiene la informacion del Consumo medio de tratamientos biológicos mensual medio en euros',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Consumo medio de tratamientos biológicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Consumo medio de tratamientos biológicos' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Consumo mensual medio en euros' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Consumo mensual medio en euros' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Consumo medio de tratamientos biológicos - Consumo mensual medio acumulado en euros
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Consumo mensual medio acumulado en euros',
        'Sección que contiene la informacion de Consumo medio de tratamientos biológicos mensual medio acumulado en euros',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Consumo medio de tratamientos biológicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Consumo medio de tratamientos biológicos' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Consumo mensual medio acumulado en euros' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Consumo mensual medio acumulado en euros' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Farmaeconómicos - Coste total por tratamiento biológico
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Coste total por tratamiento biológico',
        'Sección que contiene el Coste total por tratamiento biológico',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Farmaeconómicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Farmaeconómicos' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Coste total por tratamiento biológico' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Coste total por tratamiento biológico' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Coste total por tratamiento biológico - Costes totales
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Costes totales',
        'Sección que contiene el Costes totales por tratamiento biologico',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Coste total por tratamiento biológico' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Coste total por tratamiento biológico' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Costes totales' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Costes totales' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Coste total por tratamiento biológico - Costes acumulados
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Costes acumulados',
        'Sección que contiene los Costes total acumulados por tratamiento biologico ',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Coste total por tratamiento biológico' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Coste total por tratamiento biológico' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Costes acumulados' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Costes acumulados' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Información Farmaeconómicos - Coste medio por tratamiento biológico
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Coste medio por tratamiento biológico',
        'Sección que contiene el Coste medio por tratamiento biológico',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Información Farmaeconómicos' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Información Farmaeconómicos' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Coste medio por tratamiento biológico' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Coste medio por tratamiento biológico' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Coste medio por tratamiento biológico - Costes medios
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Costes medios',
        'Sección que contiene el Coste medio por tratamiento biológico: Costes medios',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Coste medio por tratamiento biológico' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Coste medio por tratamiento biológico' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Costes medios' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Costes medios' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Section: Coste medio por tratamiento biológico - Costes medios acumulados
INSERT INTO hopes.sections(sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'Costes medios acumulados',
        'Sección que contiene el Coste medio por tratamiento biológico: Costes medios acumulados',
        false,
        true,
        (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Coste medio por tratamiento biológico' LIMIT 1)), null), 1)) ,
        (select sec_id from hopes.sections where sec_title ='Coste medio por tratamiento biológico' LIMIT 1),
        null,
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Costes medios acumulados' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));


-- Role_Medico
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
        (select sec_id from hopes.sections where sec_title ='Costes medios acumulados' LIMIT 1),
        (select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));