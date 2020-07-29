-- object: hopes.roles_hospitals | type: TABLE --
DROP TABLE IF EXISTS hopes.roles_hospitals CASCADE;
CREATE TABLE hopes.roles_hospitals
(
    rhs_id     serial,
    rhs_hos_id smallint NOT NULL,
    rhs_rol_id smallint NOT NULL,
    CONSTRAINT rhs_id_pk PRIMARY KEY (rhs_id),
    CONSTRAINT rhs_hos_id_fk FOREIGN KEY (rhs_hos_id) REFERENCES hopes.hospitals (hos_id),
    CONSTRAINT rhs_rol_id_fk FOREIGN KEY (rhs_rol_id) REFERENCES hopes.roles (rol_id)
);
-- COMENTARIOS
COMMENT ON TABLE hopes.roles_hospitals IS 'Tabla intermedia que representa la relación entre las entidades HOSPITALS y ROLES';
COMMENT ON COLUMN hopes.roles_hospitals.rhs_id IS 'Columna con el id de la tabla';
COMMENT ON COLUMN hopes.roles_hospitals.rhs_hos_id IS 'Columna que contiene el ID del hospital';
COMMENT ON COLUMN hopes.roles_hospitals.rhs_rol_id IS 'Columna que contiene el ID del rol';
COMMENT ON CONSTRAINT rhs_id_pk ON hopes.roles_hospitals IS 'pk de la tabla ROLES_HOSPITALS';
COMMENT ON CONSTRAINT rhs_hos_id_fk ON hopes.roles_hospitals IS 'fk Relacion con la tabla HOSPITALS';
COMMENT ON CONSTRAINT rhs_rol_id_fk ON hopes.roles_hospitals IS 'fk Relacion con la tabla ROLES';
-- ddl-end --
-- object: hopes.roles_hospitals | type: TABLE --
DROP TABLE IF EXISTS hopes.services_pathologies CASCADE;
CREATE TABLE hopes.services_pathologies
(
    srp_id     serial,
    srp_srv_id smallint NOT NULL,
    srp_pth_id smallint NOT NULL,
    CONSTRAINT srp_id_pk PRIMARY KEY (srp_id),
    CONSTRAINT srp_srv_id_fk FOREIGN KEY (srp_srv_id) REFERENCES hopes.services (srv_id),
    CONSTRAINT srp_pth_id_fk FOREIGN KEY (srp_pth_id) REFERENCES hopes.pathologies (pth_id)
);
-- COMENTARIOS
COMMENT ON TABLE hopes.services_pathologies IS 'Tabla intermedia que representa la relación entre las entidades HOSPITALS y ROLES';
COMMENT ON COLUMN hopes.services_pathologies.srp_id IS 'Columna con el id de la tabla';
COMMENT ON COLUMN hopes.services_pathologies.srp_srv_id IS 'Columna que contiene el ID del hospital';
COMMENT ON COLUMN hopes.services_pathologies.srp_pth_id IS 'Columna que contiene el ID del rol';
COMMENT ON CONSTRAINT srp_id_pk ON hopes.services_pathologies IS 'pk de la tabla SERVICES_PATHOLOGIES';
COMMENT ON CONSTRAINT srp_pth_id_fk ON hopes.services_pathologies IS 'fk Relacion con la tabla PATHOLOGIES';
COMMENT ON CONSTRAINT srp_srv_id_fk ON hopes.services_pathologies IS 'fk Relacion con la tabla SERVICES';
-- ddl-end --
