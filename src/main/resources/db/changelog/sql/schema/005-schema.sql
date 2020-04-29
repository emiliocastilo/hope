-- object: hopes.hospitals_services | type: TABLE --
DROP TABLE IF EXISTS hopes.hospitals_services CASCADE;
CREATE TABLE hopes.hospitals_services
(
    hss_id     serial,
    hss_hos_id smallint NOT NULL,
    hss_srv_id smallint NOT NULL,
    CONSTRAINT hss_id_pk PRIMARY KEY (hss_id),
    CONSTRAINT hss_hos_id_fk FOREIGN KEY (hss_hos_id) REFERENCES hopes.hospitals (hos_id),
    CONSTRAINT hss_srv_id_fk FOREIGN KEY (hss_srv_id) REFERENCES hopes.services (srv_id)
);
-- COMENTARIOS
COMMENT ON TABLE hopes.hospitals_services IS 'Tabla intermedia que representa la relación entre las entidades HOSPITALS y SERVICES';
COMMENT ON COLUMN hopes.hospitals_services.hss_id IS 'Columna con el identificador de base de datos de la relación entre las tablas HOSPITALS y SERVICES(pk)';
COMMENT ON COLUMN hopes.hospitals_services.hss_hos_id IS 'Columna con el id del hospital';
COMMENT ON COLUMN hopes.hospitals_services.hss_srv_id IS 'Columna con el id del servicio';
COMMENT ON CONSTRAINT hss_id_pk ON hopes.hospitals_services IS 'pk de la tabla HOSPITALS_SERVICES';
COMMENT ON CONSTRAINT hss_hos_id_fk ON hopes.hospitals_services IS 'fk Relacion con la tabla HOSPITALS';
COMMENT ON CONSTRAINT hss_srv_id_fk ON hopes.hospitals_services IS 'fk Relacion con la tabla SERVICES';
-- ddl-end --