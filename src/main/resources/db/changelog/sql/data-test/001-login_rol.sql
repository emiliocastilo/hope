-- INITIALIZE A DATABASE --
INSERT INTO "public"."HOSPITALES" (hos_id, hos_nombre) VALUES (nextval('"public"."HOSPITALES_hos_id_seq"'), 'Hospital Perpetuo Confinamiento');
INSERT INTO "public"."PATOLOGIAS" (pat_id, pat_nombre, pat_descripcion) VALUES (nextval('"public"."PATOLOGIAS_pat_id_seq"'), 'Reumatología', 'Patologia Reumatología');

-- admin/password
INSERT INTO "public"."USUARIOS" (usu_id, usu_nombre, usu_password, usu_email, usu_hos_id, usu_fecha_crea, usu_fecha_mod)
VALUES (nextval('"public"."USUARIOS_usu_id_seq"'), 'admin', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'angel.broncanorodriguez@plexus.es', 1, current_timestamp, current_timestamp );

-- 1 role
INSERT INTO "public"."USUARIOS_ROLES" (uro_id, uro_usuario_id, uro_rol_id) VALUES (nextval('"public"."USUARIOS_ROLES_uro_id_seq"'), 1, 1);




-- ONLY FOR DEVELOPER -- //TODO borrar o gestionarlo por el perfil dev
-- doctor/password
INSERT INTO "public"."USUARIOS" (usu_id, usu_nombre, usu_password, usu_email, usu_hos_id, usu_fecha_crea, usu_fecha_mod)
VALUES (nextval('"public"."USUARIOS_usu_id_seq"'), 'doctor', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'angel.broncanorodriguez@plexus.es', 1, current_timestamp, current_timestamp );

INSERT INTO "public"."USUARIOS" (usu_id, usu_nombre, usu_password, usu_email, usu_hos_id, usu_fecha_crea, usu_fecha_mod)
VALUES (nextval('"public"."USUARIOS_usu_id_seq"'), 'farmaceutico', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.', 'angel.broncanorodriguez@plexus.es', 1, current_timestamp, current_timestamp );

-- 2 roles
INSERT INTO "public"."USUARIOS_ROLES" (uro_id, uro_usuario_id, uro_rol_id) VALUES (nextval('"public"."USUARIOS_ROLES_uro_id_seq"'), 2, 4);
INSERT INTO "public"."USUARIOS_ROLES" (uro_id, uro_usuario_id, uro_rol_id) VALUES (nextval('"public"."USUARIOS_ROLES_uro_id_seq"'), 2, 5);
