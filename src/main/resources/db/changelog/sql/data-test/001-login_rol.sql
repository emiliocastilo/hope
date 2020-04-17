-- INITIALIZE A DATABASE --

INSERT INTO public.hospitals (hos_id, hos_name) VALUES (nextval('public.hospitals_hos_id_seq'), 'Hospital Perpetuo Confinamiento');
INSERT INTO public.pathologies (pth_id, pth_name, pth_description) VALUES (nextval('public.PATHOLOGIES_pth_id_seq'), 'Reumatología', 'Patologia Reumatología');

-- admin/password
INSERT INTO public.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, usr_date_crea, usr_date_mod)
VALUES (nextval('public.users_usr_id_seq'), 'admin', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'jordi.ripoll@plexus.es', 1, current_timestamp, current_timestamp );

-- 1 role
INSERT INTO public.users_roles (uro_id, uro_user_id, uro_rol_id) VALUES (nextval('public.users_roles_uro_id_seq'), 1, 1);


-- doctor/password
INSERT INTO public.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, usr_date_crea, usr_date_mod)
VALUES (nextval('public.users_usr_id_seq'), 'doctor', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'angel.broncanorodriguez@plexus.es', 1, current_timestamp, current_timestamp );

INSERT INTO public.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, usr_date_crea, usr_date_mod)
VALUES (nextval('public.users_usr_id_seq'), 'farmaceutico', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'victor.gomeztejada@plexus.es', 1, current_timestamp, current_timestamp );

-- 2 roles
INSERT INTO public.users_ROLES (uro_id, uro_user_id, uro_rol_id) VALUES (nextval('public.users_roles_uro_id_seq'), 2, 4);
INSERT INTO public.users_ROLES (uro_id, uro_user_id, uro_rol_id) VALUES (nextval('public.users_roles_uro_id_seq'), 2, 5);


-- menu
INSERT INTO public.sections (sec_pth_id,sec_title,sec_description,sec_menu,sec_order,sec_section_root,sec_icon,sec_url) VALUES (1,'HOPES','root',true,1,NULL,NULL,'#');

INSERT INTO public.sections (sec_pth_id,sec_title,sec_description,sec_menu,sec_order,sec_section_root,sec_icon,sec_url) VALUES
(1,'CALENDAR','Calendario',true,1,1,'assets/img/modules/calendario.png','#')
,(1,'PATIENTS','Pacientes',true,2,1,'assets/img/modules/planes-atencion.png','#')
,(1,'CONTROL_PANEL','Cuadro de mandos',true,3,1,'assets/img/modules/cuadro-de-mando.png','#')
,(1,'ALERTS','Alertas',true,4,1,'assets/img/modules/alertas.png','#');
