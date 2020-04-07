-- INITIALIZE A DATABASE --
INSERT INTO "public"."HOSPITALS" (hos_id, hos_name) VALUES (nextval('"public"."HOSPITALS_hos_id_seq"'), 'Hospital Perpetuo Confinamiento');
INSERT INTO "public"."PATHOLOGIES" (pth_id, pth_name, pth_description) VALUES (nextval('"public"."PATHOLOGIES_pth_id_seq"'), 'Reumatología', 'Patologia Reumatología');

-- admin/password
INSERT INTO "public"."USERS" (USR_id, USR_name, USR_password, USR_email, USR_hos_id, USR_date_crea, USR_date_mod)
VALUES (nextval('"public"."USERS_usr_id_seq"'), 'admin', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'jordi.ripoll@plexus.es', 1, current_timestamp, current_timestamp );

-- 1 role
INSERT INTO "public"."USERS_ROLES" (uro_id, uro_user_id, uro_rol_id) VALUES (nextval('"public"."USERS_ROLES_uro_id_seq"'), 1, 1);




-- ONLY FOR DEVELOPER -- //TODO borrar o gestionarlo por el perfil dev
-- doctor/password
INSERT INTO "public"."USERS" (USR_id, USR_name, USR_password, USR_email, USR_hos_id, USR_date_crea, USR_date_mod)
VALUES (nextval('"public"."USERS_usr_id_seq"'), 'doctor', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'angel.broncanorodriguez@plexus.es', 1, current_timestamp, current_timestamp );

INSERT INTO "public"."USERS" (USR_id, USR_name, USR_password, USR_email, USR_hos_id, USR_date_crea, USR_date_mod)
VALUES (nextval('"public"."USERS_usr_id_seq"'), 'farmaceutico', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'victor.gomeztejada@plexus.es', 1, current_timestamp, current_timestamp );

-- 2 roles
INSERT INTO "public"."USERS_ROLES" (uro_id, uro_user_id, uro_rol_id) VALUES (nextval('"public"."USERS_ROLES_uro_id_seq"'), 2, 4);
INSERT INTO "public"."USERS_ROLES" (uro_id, uro_user_id, uro_rol_id) VALUES (nextval('"public"."USERS_ROLES_uro_id_seq"'), 2, 5);
