ALTER TABLE hopes.sections_roles
drop constraint scr_role_id_fk,
ADD CONSTRAINT scr_role_id_fk
FOREIGN KEY (scr_role_id)
REFERENCES hopes.roles(rol_id)
on delete cascade;

ALTER TABLE hopes.users_roles
drop constraint uro_rol_id_fk,
ADD CONSTRAINT uro_rol_id_fk
FOREIGN KEY (uro_rol_id)
REFERENCES hopes.roles(rol_id)
on delete cascade;