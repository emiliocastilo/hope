----------------------------------------------------------------- SOPORTE -------------------------------------------------------------------------
-- La sección Soporte está insertada 2 veces una principal y otra no principal en otra ubicación.
-- Elimino la sección Soporte que no es principal.
delete from hopes.sections_roles sr where sr.scr_section_id = (select s.sec_id from hopes.sections s where sec_title ='Soporte'
and sec_section_root = (select s1.sec_id from hopes.sections s1 where sec_title='Administración'));
DELETE FROM hopes.sections WHERE sec_title = 'Soporte' AND sec_section_root = (select s1.sec_id from hopes.sections s1 where sec_title='Administración');

-- Doy visibilidad de la sección Soporte para todos los roles por defecto
-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Soporte' AND sec_section_root = (select s1.sec_id from hopes.sections s1 where sec_title='Hopes')),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1))
ON CONFLICT ON CONSTRAINT sections_roles_unique
DO NOTHING;

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Soporte' AND sec_section_root = (select s1.sec_id from hopes.sections s1 where sec_title='Hopes')),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1))
ON CONFLICT ON CONSTRAINT sections_roles_unique
DO NOTHING;

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Soporte' AND sec_section_root = (select s1.sec_id from hopes.sections s1 where sec_title='Hopes')),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1))
ON CONFLICT ON CONSTRAINT sections_roles_unique
DO NOTHING;

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Soporte' AND sec_section_root = (select s1.sec_id from hopes.sections s1 where sec_title='Hopes')),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1))
ON CONFLICT ON CONSTRAINT sections_roles_unique
DO NOTHING;
----------------------------------------------------------------- SOPORTE -------------------------------------------------------------------------
----------------------------------------------------------------- ADHERENCIA AL TRATAMIENTO -------------------------------------------------------
-- Esta sección debe tener 2 subsecciones "Test de Morisky Green" y "Test de Haynes Sackett"
DELETE FROM hopes.sections_roles WHERE scr_section_id in (SELECT sec_id FROM hopes.sections WHERE sec_title in ('Test de Morisky Green', 'Test de Haynes Sackett'));
DELETE FROM hopes.sections WHERE sec_title = 'Test de Morisky Green' or sec_title = 'Test de Haynes Sackett';

-- Crear las secciones desde cero
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

-- Doy visibilidad de las subsecciones anteriores para todos los roles por defecto
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

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Test de Morisky Green' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

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

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Test de Haynes Sackett' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

----------------------------------------------------------------- ADHERENCIA AL TRATAMIENTO -------------------------------------------------------
----------------------------------------------------------------- TRATAMIENTOS --------------------------------------------------------------------
-- Eliminar  las secciones
DELETE FROM hopes.sections_roles WHERE scr_section_id in (SELECT sec_id FROM hopes.sections WHERE sec_title in ('Tratamiento actual', 'Tratamiento anteriores', 'Tratamientos', 'Farmacológicos', 'No farmacológico (fototerapia)'));
DELETE FROM hopes.sections WHERE sec_title in ('Tratamiento actual', 'Tratamiento anteriores', 'Tratamientos', 'Farmacológicos', 'No farmacológico (fototerapia)');

-- Crear las secciones desde cero
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

-- Doy visibilidad de las subsecciones anteriores para todos los roles por defecto
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

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Tratamientos' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

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

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Farmacológicos' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

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

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='No farmacológico (fototerapia)' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

----------------------------------------------------------------- TRATAMIENTOS --------------------------------------------------------------------
----------------------------------------------------------------- DIAGNÓSTICOS --------------------------------------------------------------------
-- Eliminar secciones
DELETE FROM hopes.sections_roles WHERE scr_section_id in (SELECT sec_id FROM hopes.sections WHERE sec_title in ('Diagnóstico Principal', 'Diagnósticos Secundarios', 'Comorbilidades', 'Diagnóstico', 'Diagnósticos'));
DELETE FROM hopes.sections WHERE sec_title in ('Diagnóstico Principal', 'Diagnósticos Secundarios', 'Comorbilidades', 'Diagnóstico');
delete from hopes.sections where sec_title = 'Diagnósticos';

-- Crear las secciones desde cero
INSERT into hopes.sections (
       sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal)
VALUES (
       nextval('hopes.sections_sec_id_seq'),
       'Diagnósticos', 'Sección que contiene los diagnosticos del paciente',
       true,
       (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1)), null), 1)) ,
       (select sec_id from hopes.sections where sec_title ='Paciente' LIMIT 1),
       '',
       '#',
       false);
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

-- Doy visibilidad de las subsecciones anteriores para todos los roles por defecto
-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Diagnósticos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Diagnósticos' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Diagnósticos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Diagnósticos' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

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

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Diagnóstico Principal' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

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

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Diagnósticos Secundarios' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

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

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Comorbilidades' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));
----------------------------------------------------------------- DIAGNÓSTICOS --------------------------------------------------------------------
----------------------------------------------------------------- DATOS DEL PACIENTE --------------------------------------------------------------
-- Eliminar secciones
DELETE FROM hopes.sections_roles WHERE scr_section_id in (SELECT sec_id FROM hopes.sections WHERE sec_title in ('Datos generales del paciente', 'Datos sociodemográficos', 'Antecedentes Familiares', 'Datos personales', 'Datos de ensayos clínicos', 'Evaluacion del estado físico', 'Evaluación del estado físico', 'Antecedentes familiares', 'Hábitos de consumo', 'Datos del paciente'));
DELETE FROM hopes.sections WHERE sec_title in ('Datos generales del paciente', 'Datos sociodemográficos', 'Antecedentes Familiares', 'Datos personales', 'Datos de ensayos clínicos', 'Evaluacion del estado físico', 'Evaluación del estado físico', 'Antecedentes familiares', 'Hábitos de consumo');
DELETE FROM hopes.sections WHERE sec_title = 'Datos del paciente';

-- Crear las secciones desde cero
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
INSERT into hopes.sections (
       sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal)
VALUES (
       nextval('hopes.sections_sec_id_seq'),
       'Datos sociodemográficos', 'Datos sociodemográficos',
       true,
       (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1)), null), 1)) ,
       (select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1),
       '',
       '/hopes/pathology/patients/sociodemographic-data',
       false);
INSERT into hopes.sections (
       sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal)
VALUES (
       nextval('hopes.sections_sec_id_seq'),
       'Antecedentes familiares', 'Antecedentes familiares',
       true,
       (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1)), null), 1)) ,
       (select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1),
       '',
       '/hopes/pathology/patients/family-history',
       false);
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

-- Doy visibilidad de las subsecciones anteriores para todos los roles por defecto
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

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Datos del paciente' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

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

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Datos personales' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Datos sociodemográficos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Datos sociodemográficos' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Datos sociodemográficos' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Datos sociodemográficos' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Antecedentes familiares' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Antecedentes familiares' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Antecedentes familiares' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Antecedentes familiares' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

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

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Evaluación del estado físico' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

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

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Datos de ensayos clínicos' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

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

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Hábitos de consumo' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));
----------------------------------------------------------------- DATOS DEL PACIENTE --------------------------------------------------------------
----------------------------------------------------------------- SECCIONES NO NECESARIAS ---------------------------------------------------------
DELETE FROM hopes.sections_roles WHERE scr_section_id in (SELECT sec_id FROM hopes.sections WHERE sec_title in ('Registro Visitas', 'Exportar', 'Incidencias'));
DELETE FROM hopes.sections WHERE sec_title in ('Registro Visitas', 'Exportar', 'Incidencias');
----------------------------------------------------------------- SECCIONES NO NECESARIAS ---------------------------------------------------------
----------------------------------------------------------------- ACTUALIZAR SECCIONES ------------------------------------------------------------
UPDATE hopes.sections
SET sec_url='/hopes/pathology/patients/tracing'
WHERE sec_title='Seguimiento';

UPDATE hopes.sections
SET sec_url='/hopes/pathology/patients/gallery'
WHERE sec_title='Galería de fotos';

UPDATE hopes.sections
SET sec_url='/hopes/pathology/patients/complementary-imaging-scans'
WHERE sec_title='Exploraciones complementarias de imagen';

UPDATE hopes.sections
SET sec_url='/hopes/pathology/patients/consent'
WHERE sec_title='Consentimiento';

UPDATE hopes.sections
SET sec_url='/hopes/pathology/patients/shared-patients'
WHERE sec_title='Paciente compartido';

UPDATE hopes.sections
SET sec_url='/hopes/pathology/patients/evolution-clinical-indices/eav-pase'
WHERE sec_title='EAV Y PASE';

UPDATE hopes.sections
SET sec_url='/hopes/pathology/patients/evolution-clinical-indices/pasi-bsa-pga'
WHERE sec_title='PASI, BSA Y PGA';

UPDATE hopes.sections
SET sec_url='/hopes/pathology/patients/evolution-clinical-indices/dlqi'
WHERE sec_title='DLQI';

UPDATE hopes.sections
SET sec_url='/hopes/pathology/patients/evolution-clinical-indices/napsi'
WHERE sec_title='Otros índices: NAPSI';

UPDATE hopes.sections
SET sec_url='/hopes/pathology/patients/blood-count'
WHERE sec_title='Hemograma';

UPDATE hopes.sections
SET sec_url='/hopes/pathology/patients/metabolic-profile'
WHERE sec_title='Perfil metabólico';

UPDATE hopes.sections
SET sec_url='/hopes/pathology/patients/kidney-liver-biochemistry'
WHERE sec_title='Bioquímica hepática y renal';

UPDATE hopes.sections
SET sec_url='/hopes/pathology/patients/biological-drug-monitoring'
WHERE sec_title='Monitorización de fármacos biológicos';

UPDATE hopes.sections
SET sec_url='/hopes/pathology/patients/leukocyte-antibody-antigen'
WHERE sec_title='Anticuerpo y antígeno leucocitario';

UPDATE hopes.sections
SET sec_url='/hopes/pathology/patients/serology'
WHERE sec_title='Serología (VHC, VHB, otras infecciones y vacunas)';

UPDATE hopes.sections
SET sec_url='/hopes/management/dispensations'
WHERE sec_title='Dispensaciones';

UPDATE hopes.sections
SET sec_url='/hopes/pathology/patients/evolution-clinical-indices'
WHERE sec_title='Evolución de índices clínicos';

----------------------------------------------------------------- ACTUALIZAR SECCIONES ------------------------------------------------------------