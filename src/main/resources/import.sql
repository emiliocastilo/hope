-- INITIALIZE A DATABASE --

-- Roles
INSERT INTO public.role (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO public.role (id, name) VALUES (2, 'ROLE_MANAGER_CAU');
INSERT INTO public.role (id, name) VALUES (3, 'ROLE_MANAGER');
INSERT INTO public.role (id, name) VALUES (4, 'ROLE_DOCTOR');
INSERT INTO public.role (id, name) VALUES (5, 'ROLE_PHARMACIST');
INSERT INTO public.role (id, name) VALUES (6, 'ROLE_NURSING');
INSERT INTO public.role (id, name) VALUES (7, 'ROLE_PATIENT');

-- admin/password
INSERT INTO public.user_ (id, username, "password") VALUES (1, 'admin', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.');
-- 1 role
INSERT INTO public.user_roles (user_id, role_id) VALUES (1, 1);




-- ONLY FOR DEVELOPER -- //TODO borrar o gestionarlo por el perfil dev
-- doctor/password
INSERT INTO public.user_ (id, username, "password") VALUES (2, 'doctor', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.');
-- 2 roles
INSERT INTO public.user_roles (user_id, role_id) VALUES (2, 3);
INSERT INTO public.user_roles (user_id, role_id) VALUES (2, 4);
