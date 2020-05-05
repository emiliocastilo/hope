---------------------------------- RELLENAR DICHA COLUMNA Y CORREGIR ERRATAS ---------------------------
update public.roles set rol_traduction='Administrador' where rol_name ='ROLE_ADMIN';
update public.roles set rol_description='Rol administrador. Tiene el máximo nivel de acceso a la aplicación' where rol_name ='ROLE_ADMIN';

update public.roles set rol_traduction='Gestor del CAU' where rol_name ='ROLE_MANAGER_CAU';
update public.roles set rol_traduction='Gestor' where rol_name ='ROLE_MANAGER';
update public.roles set rol_traduction='Farmaceútico' where rol_name ='ROLE_PHARMACIST';
update public.roles set rol_traduction='Enfermero' where rol_name ='ROLE_NURSING';
update public.roles set rol_traduction='Gestor de pacientes' where rol_name ='ROLE_PATIENT';
update public.roles set rol_traduction='Médico de patología dermatología' where rol_name ='ROLE_DOCTOR_DERMATOLOGY';
update public.roles set rol_traduction='Médico de patología VIH' where rol_name ='ROLE_DOCTOR_VIH';

---------- Eliminamos las asociaciones previas de admin -------------------------
delete from public.sections_roles where scr_role_id = 1;
----------------------- ASOCIACIÓN ROL-MENÚ PARA ADMIN ---------------------------------------- 

INSERT INTO public.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('public.sections_roles_scr_id_seq'),
		(select sec_id from public.sections where sec_title ='HOPES' LIMIT 1),
		(select rol_id from public.roles where rol_name = 'ROLE_ADMIN' LIMIT 1));

INSERT INTO public.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('public.sections_roles_scr_id_seq'),
		(select sec_id from public.sections where sec_title ='CALENDAR' LIMIT 1),
		(select rol_id from public.roles where rol_name = 'ROLE_ADMIN' LIMIT 1));

INSERT INTO public.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('public.sections_roles_scr_id_seq'),
		(select sec_id from public.sections where sec_title ='PATIENTS' LIMIT 1),
		(select rol_id from public.roles where rol_name = 'ROLE_ADMIN' LIMIT 1));
		
INSERT INTO public.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('public.sections_roles_scr_id_seq'),
		(select sec_id from public.sections where sec_title ='CONTROL_PANEL' LIMIT 1),
		(select rol_id from public.roles where rol_name = 'ROLE_ADMIN' LIMIT 1));		

INSERT INTO public.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('public.sections_roles_scr_id_seq'),
		(select sec_id from public.sections where sec_title ='ALERTS' LIMIT 1),
		(select rol_id from public.roles where rol_name = 'ROLE_ADMIN' LIMIT 1));		
		
INSERT INTO public.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('public.sections_roles_scr_id_seq'),
		(select sec_id from public.sections where sec_title ='FORM_DERMA' LIMIT 1),
		(select rol_id from public.roles where rol_name = 'ROLE_ADMIN' LIMIT 1));	

INSERT INTO public.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('public.sections_roles_scr_id_seq'),
		(select sec_id from public.sections where sec_title ='FORM_VIH' LIMIT 1),
		(select rol_id from public.roles where rol_name = 'ROLE_ADMIN' LIMIT 1));	

INSERT INTO public.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('public.sections_roles_scr_id_seq'),
		(select sec_id from public.sections where sec_title ='DISPENSATIONS' LIMIT 1),
		(select rol_id from public.roles where rol_name = 'ROLE_ADMIN' LIMIT 1));	
		
INSERT INTO public.sections (sec_title,sec_description,sec_menu,sec_order,sec_section_root,sec_icon,sec_url) VALUES
('MEDICINES','Medicamentos',false,6,1,'assets/img/modules/medicamentos.png','#');

INSERT INTO public.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('public.sections_roles_scr_id_seq'),
		(select sec_id from public.sections where sec_title ='MEDICINES' LIMIT 1),
		(select rol_id from public.roles where rol_name = 'ROLE_ADMIN' LIMIT 1));	
		
		
------------------------------------ ACTUALIZACION URL------------------------------------------------------		
update public.sections set sec_url='/dermatology/patients' where sec_title ='PATIENTS';

------------------------------------- NUEVA OPCION DE MENÚ -----------------------------------------------

INSERT INTO public.sections (sec_title,sec_description,sec_menu,sec_order,sec_section_root,sec_icon,sec_url) VALUES
('DOCTORS','Médicos',true,7,1,'assets/img/modules/medicos.png','/management/medic');

------------------------------------ ASOCIAR OPCION A ADMIN ------------------------------------------------------
INSERT INTO public.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('public.sections_roles_scr_id_seq'),
		(select sec_id from public.sections where sec_title ='DOCTORS' LIMIT 1),
		(select rol_id from public.roles where rol_name = 'ROLE_ADMIN' LIMIT 1));
		
--------------------------------- DESACTIVAR OPCIONES INCOMPLETAS ----------------------------

update public.sections set sec_menu=false where sec_title ='CALENDAR';
update public.sections set sec_menu=false where sec_title ='ALERTS';
update public.sections set sec_menu=false where sec_title ='CONTROL_PANEL';
update public.sections set sec_menu=false where sec_title ='DISPENSATIONS';