ALTER TABLE hopes.users RENAME COLUMN usr_name TO usr_username;
ALTER TABLE hopes.users ADD COLUMN usr_name varchar(50); --NOT NULL;
ALTER TABLE hopes.users ADD COLUMN usr_surname varchar(100); --NOT NULL;
ALTER TABLE hopes.users ADD COLUMN usr_phone varchar(11); --NOT NULL;
ALTER TABLE hopes.users ADD COLUMN usr_dni varchar(9); --NOT NULL;
ALTER TABLE hopes.users ADD COLUMN usr_college_number bigint; --NOT NULL
ALTER TABLE hopes.users DROP COLUMN usr_hos_id;

COMMENT ON COLUMN hopes.users.usr_name IS 'Columna que contiene el nombre del usuario';
COMMENT ON COLUMN hopes.users.usr_surname IS 'Columna que contiene los apellidos del usuario';
COMMENT ON COLUMN hopes.users.usr_phone IS 'Columna que contiene el número de teléfono del usuario';
COMMENT ON COLUMN hopes.users.usr_dni IS 'Columna que contiene el dni del usuario';
COMMENT ON COLUMN hopes.users.usr_college_number IS 'Columna que contiene el número de colegiado del médico';

-- object: hopes.services_pathologies | type: TABLE --
DROP TABLE IF EXISTS hopes.services_pathologies CASCADE;
CREATE TABLE hopes.services_pathologies
(
    srp_id     serial,
    srp_srv_id smallint NOT NULL,
    srp_pth_id smallint NOT NULL,
    CONSTRAINT srp_id_pk PRIMARY KEY (srp_id),
    CONSTRAINT srp_srv_pth_unique UNIQUE (srp_srv_id, srp_pth_id),
    CONSTRAINT srp_srv_id_fk FOREIGN KEY (srp_srv_id) REFERENCES hopes.services (srv_id),
    CONSTRAINT srp_pth_id_fk FOREIGN KEY (srp_pth_id) REFERENCES hopes.pathologies (pth_id)
);
-- COMENTARIOS
COMMENT ON TABLE hopes.services_pathologies IS 'Tabla intermedia que representa la relación entre las entidades SERVICES y PATHOLOGIES';
COMMENT ON COLUMN hopes.services_pathologies.srp_id IS 'Columna con el id de la tabla';
COMMENT ON COLUMN hopes.services_pathologies.srp_srv_id IS 'Columna que contiene el ID del servicio';
COMMENT ON COLUMN hopes.services_pathologies.srp_pth_id IS 'Columna que contiene el ID del patologia';
COMMENT ON CONSTRAINT srp_id_pk ON hopes.services_pathologies IS 'pk de la tabla SERVICES_PATHOLOGIES';
COMMENT ON CONSTRAINT srp_pth_id_fk ON hopes.services_pathologies IS 'fk Relacion con la tabla PATHOLOGIES';
COMMENT ON CONSTRAINT srp_srv_id_fk ON hopes.services_pathologies IS 'fk Relacion con la tabla SERVICES';
-- ddl-end --

-- Eliminar las tablas de doctor
DROP TABLE IF EXISTS hopes.doctors CASCADE;

-- Eliminar tabla HOSPITALS_PATHOLOGIES
DROP TABLE IF EXISTS hopes.hospitals_pathologies;

-- Añadir columnas nuevas en la tabla ROLES
ALTER TABLE hopes.roles ADD COLUMN rol_hos_id bigint; --NOT NULL;
ALTER TABLE hopes.roles ADD COLUMN rol_srv_id bigint; --NOT NULL;
ALTER TABLE hopes.roles ADD COLUMN rol_pth_id bigint; --NOT NULL;

COMMENT ON COLUMN hopes.roles.rol_hos_id IS 'Columna que contiene el identificador del hospital asociado al rol';
COMMENT ON COLUMN hopes.roles.rol_srv_id IS 'Columna que contiene el identificador del servicio asociado al rol';
COMMENT ON COLUMN hopes.roles.rol_pth_id IS 'Columna que contiene el identificador de la patología asociada al rol';

ALTER TABLE hopes.roles ADD CONSTRAINT rol_hos_id_fk FOREIGN KEY (rol_hos_id) REFERENCES hopes.hospitals(hos_id);
ALTER TABLE hopes.roles ADD CONSTRAINT rol_srv_id_fk FOREIGN KEY (rol_srv_id) REFERENCES hopes.services(srv_id);
ALTER TABLE hopes.roles ADD CONSTRAINT rol_pth_id_fk FOREIGN KEY (rol_pth_id) REFERENCES hopes.pathologies(pth_id);

-- La columna CODE de ROLES debe ser única.
ALTER TABLE hopes.roles ADD CONSTRAINT rol_code_unique UNIQUE (rol_code);
