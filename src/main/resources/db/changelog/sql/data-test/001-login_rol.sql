-- INITIALIZE A DATABASE --

INSERT INTO public.hospitals (hos_id, hos_name) VALUES (nextval('public.hospitals_hos_id_seq'), 'Hospital Perpetuo Confinamiento');
INSERT INTO public.pathologies (pth_id, pth_name, pth_description) VALUES (nextval('public.PATHOLOGIES_pth_id_seq'), 'Reumatología', 'Patologia Reumatología');

-- admin/password
INSERT INTO public.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, usr_date_crea, usr_date_mod)
VALUES (nextval('public.users_usr_id_seq'), 'admin', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'jordi.ripoll@plexus.es', 1, current_timestamp, current_timestamp );

-- 1 role
INSERT INTO public.users_roles (uro_id, uro_user_id, uro_rol_id) VALUES (nextval('public.users_roles_uro_id_seq'), 1, 1);




-- ONLY FOR DEVELOPER -- //TODO borrar o gestionarlo por el perfil dev
-- doctor/password
INSERT INTO public.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, usr_date_crea, usr_date_mod)
VALUES (nextval('public.users_usr_id_seq'), 'doctor', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'angel.broncanorodriguez@plexus.es', 1, current_timestamp, current_timestamp );

INSERT INTO public.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, usr_date_crea, usr_date_mod)
VALUES (nextval('public.users_usr_id_seq'), 'farmaceutico', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'victor.gomeztejada@plexus.es', 1, current_timestamp, current_timestamp );

-- 2 roles
INSERT INTO public.users_ROLES (uro_id, uro_user_id, uro_rol_id) VALUES (nextval('public.users_roles_uro_id_seq'), 2, 4);
INSERT INTO public.users_ROLES (uro_id, uro_user_id, uro_rol_id) VALUES (nextval('public.users_roles_uro_id_seq'), 2, 5);
