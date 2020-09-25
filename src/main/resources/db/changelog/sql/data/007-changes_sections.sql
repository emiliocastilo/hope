-- Insertar nuevas secciones para agrupar

INSERT into hopes.sections (
       sec_id, sec_title, sec_description, sec_principal, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (
       nextval('hopes.sections_sec_id_seq'),
       'Gestión', 'Sección que contiene todas las secciones de gestión del usuario logado',
       false,
       true,
       (SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1)), null), 1)) ,
       (select sec_id from hopes.sections where sec_title ='Administración' LIMIT 1),
       null,
       '#' );

-- Actualizar secciones que deben ir agrupadas en las nuevas anteriormente insertadas
UPDATE hopes.sections SET sec_url = '/hopes/management/sections' WHERE sec_title ='Secciones';

UPDATE hopes.sections SET sec_order = (
    SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root =
        (select sec_id from hopes.sections where sec_title ='Gestión' LIMIT 1)), null), 1)),
    sec_section_root = (select sec_id from hopes.sections where sec_title ='Gestión' LIMIT 1)
 WHERE sec_title='Gestión de Médicos';

UPDATE hopes.sections SET sec_order = (
    SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root =
        (select sec_id from hopes.sections where sec_title ='Gestión' LIMIT 1)), null), 1)),
    sec_section_root = (select sec_id from hopes.sections where sec_title ='Gestión' LIMIT 1)
 WHERE sec_title='Gestión de Usuarios';

UPDATE hopes.sections SET sec_order = (
    SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root =
        (select sec_id from hopes.sections where sec_title ='Gestión' LIMIT 1)), null), 1)),
    sec_section_root = (select sec_id from hopes.sections where sec_title ='Gestión' LIMIT 1)
 WHERE sec_title='Gestión de Pacientes';

UPDATE hopes.sections SET sec_order = (
    SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root =
        (select sec_id from hopes.sections where sec_title ='Gestión' LIMIT 1)), null), 1)),
    sec_section_root = (select sec_id from hopes.sections where sec_title ='Gestión' LIMIT 1)
 WHERE sec_title='Gestión de Roles';

UPDATE hopes.sections SET sec_order = (
    SELECT COALESCE( NULLIF((select max(sec_order)+1 from hopes.sections where sec_section_root =
        (select sec_id from hopes.sections where sec_title ='Gestión' LIMIT 1)), null), 1)),
    sec_section_root = (select sec_id from hopes.sections where sec_title ='Gestión' LIMIT 1)
 WHERE sec_title='Gestión de Medicamentos';

UPDATE hopes.sections SET sec_title = 'Motivo del cambio/suspensión de tratamiento biológico'
WHERE sec_title ='Motivo del último cambio de tratamiento biológico';

UPDATE hopes.sections SET sec_title = 'Pacientes en tratamiento según agentes'
WHERE sec_title ='Pacientes en tratamiento según agentes químicos';

UPDATE hopes.sections SET sec_title = 'Tratamiento biológico en pacientes'
WHERE sec_title ='Tratamiento biológico en pacientes con psoriasis en placas';

UPDATE hopes.sections SET sec_title = 'Tratamiento biológico en pacientes'
WHERE sec_title ='Tratamiento biológico en pacientes con psoriasis en placas';

UPDATE hopes.sections SET sec_title = 'Pacientes según tipo de resultado'
WHERE sec_title ='Pacientes según PASI';

UPDATE hopes.sections SET sec_title = 'Consumo de tratamientos biológicos'
WHERE sec_title ='Consumo anual de tratamientos biológicos';

UPDATE hopes.sections SET sec_title = 'Coste por tratamientos biológicos'
WHERE sec_title ='Coste total por tratamiento biológico';

-- Insertar que el los distintos roles puedan ver las nuevas secciones
-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Gestión' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Administrador' LIMIT 1));

-- Role_Manager
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Gestión' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Gestor' LIMIT 1));

-- Role_Doctor
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='Gestión' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'Médico Dermatología' LIMIT 1));

-- Role_Pharmaceutist
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
	  	(select sec_id from hopes.sections where sec_title ='Gestión' LIMIT 1),
	  	(select rol_id from hopes.roles where rol_name = 'Farmacéutico' LIMIT 1));

-- Eliminar secciones
delete from hopes.sections_roles sr where sr.scr_section_id = (select s.sec_id from hopes.sections s where sec_title ='Motivo de la suspensión de tratamiento biológico');
delete from hopes.sections where sec_title ='Motivo de la suspensión de tratamiento biológico';

delete from hopes.sections_roles sr where sr.scr_section_id = (select s.sec_id from hopes.sections s where sec_title ='Motivo del cambio de tratamiento biológico de los últimos 5 años');
delete from hopes.sections where sec_title ='Motivo del cambio de tratamiento biológico de los últimos 5 años';

delete from hopes.sections_roles sr where sr.scr_section_id = (select s.sec_id from hopes.sections s where sec_title ='Número de cambios de tratamiento biológico');
delete from hopes.sections where sec_title ='Número de cambios de tratamiento biológico';

delete from hopes.sections_roles sr where sr.scr_section_id = (select s.sec_id from hopes.sections s where sec_title ='Pacientes en tratamiento según agentes biológicos');
delete from hopes.sections where sec_title ='Pacientes en tratamiento según agentes biológicos';

delete from hopes.sections_roles sr where sr.scr_section_id = (select s.sec_id from hopes.sections s where sec_title ='Tratamiento biológico en pacientes con psoriasis palmo-plantar');
delete from hopes.sections where sec_title ='Tratamiento biológico en pacientes con psoriasis palmo-plantar';

delete from hopes.sections_roles sr where sr.scr_section_id = (select s.sec_id from hopes.sections s where sec_title ='Tratamiento biológico en pacientes con eritrodermia');
delete from hopes.sections where sec_title ='Tratamiento biológico en pacientes con eritrodermia';

delete from hopes.sections_roles sr where sr.scr_section_id = (select s.sec_id from hopes.sections s where sec_title ='Tratamiento biológico en pacientes con psoriasis pustulosa');
delete from hopes.sections where sec_title ='Tratamiento biológico en pacientes con psoriasis pustulosa';

delete from hopes.sections_roles sr where sr.scr_section_id = (select s.sec_id from hopes.sections s where sec_title ='Pacientes según BSA');
delete from hopes.sections where sec_title ='Pacientes según BSA';

delete from hopes.sections_roles sr where sr.scr_section_id = (select s.sec_id from hopes.sections s where sec_title ='Pacientes según PGA');
delete from hopes.sections where sec_title ='Pacientes según PGA';

delete from hopes.sections_roles sr where sr.scr_section_id = (select s.sec_id from hopes.sections s where sec_title ='Pacientes según DLQI');
delete from hopes.sections where sec_title ='Pacientes según DLQI';

delete from hopes.sections_roles sr where sr.scr_section_id = (select s.sec_id from hopes.sections s where sec_title ='Consumo mensual medio en euros'
and sec_section_root = (select s1.sec_id from hopes.sections s1 where sec_title='Consumo medio de tratamientos biológicos'));
delete from hopes.sections where sec_title ='Consumo mensual medio en euros' and sec_section_root = (select s1.sec_id from hopes.sections s1 where sec_title='Consumo medio de tratamientos biológicos');

delete from hopes.sections_roles sr where sr.scr_section_id = (select s.sec_id from hopes.sections s where sec_title ='Consumo mensual medio acumulado en euros'
and sec_section_root = (select s1.sec_id from hopes.sections s1 where sec_title='Consumo medio de tratamientos biológicos'));
delete from hopes.sections where sec_title ='Consumo mensual medio acumulado en euros' and sec_section_root = (select s1.sec_id from hopes.sections s1 where sec_title='Consumo medio de tratamientos biológicos');

delete from hopes.sections_roles sr where sr.scr_section_id = (select s.sec_id from hopes.sections s where sec_title ='Consumo medio de tratamientos biológicos');
delete from hopes.sections where sec_title ='Consumo medio de tratamientos biológicos';

delete from hopes.sections_roles sr where sr.scr_section_id = (select s.sec_id from hopes.sections s where sec_title ='Costes medios'
and sec_section_root = (select s1.sec_id from hopes.sections s1 where sec_title='Coste medio por tratamiento biológico'));
delete from hopes.sections where sec_title ='Costes medios' and sec_section_root = (select s1.sec_id from hopes.sections s1 where sec_title='Coste medio por tratamiento biológico');

delete from hopes.sections_roles sr where sr.scr_section_id = (select s.sec_id from hopes.sections s where sec_title ='Costes medios acumulados'
and sec_section_root = (select s1.sec_id from hopes.sections s1 where sec_title='Coste medio por tratamiento biológico'));
delete from hopes.sections where sec_title ='Costes medios acumulados' and sec_section_root = (select s1.sec_id from hopes.sections s1 where sec_title='Coste medio por tratamiento biológico');

delete from hopes.sections_roles sr where sr.scr_section_id = (select s.sec_id from hopes.sections s where sec_title ='Coste medio por tratamiento biológico');
delete from hopes.sections where sec_title ='Coste medio por tratamiento biológico';