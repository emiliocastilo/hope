-- object: hopes.tokens | type: TABLE --
DROP TABLE IF EXISTS hopes.photos CASCADE;

CREATE TABLE hopes.photos
(
    pho_id          SERIAL,
    pho_name        VARCHAR,
    pho_type_photo  VARCHAR,
    pho_photo_bytes TEXT,
    pho_title       VARCHAR,
    pho_description VARCHAR,
    pho_pth_id      BIGINT,
    pho_pac_id      BIGINT,
    user_updated    VARCHAR,
    date_created    TIMESTAMP,
    date_updated    TIMESTAMP,
    CONSTRAINT pho_id_pk PRIMARY KEY (pho_id),
    CONSTRAINT pho_sec_id_pk FOREIGN KEY (pho_pth_id) REFERENCES hopes.pathologies (pth_id),
    CONSTRAINT pho_pac_id_pk FOREIGN KEY (pho_pac_id) REFERENCES hopes.patients (pac_id)
);

-- COMENTARIOS
COMMENT ON TABLE hopes.photos IS 'Tabla que representa la entidad foto';
COMMENT ON COLUMN hopes.photos.pho_id IS 'Columna que contiene el identificador de la foto (pk)';
COMMENT ON COLUMN hopes.photos.pho_name IS 'Columna que contiene el valor nombre de la photo';
COMMENT ON COLUMN hopes.photos.pho_type_photo IS 'Columna que contiene el tipo de la foto';
COMMENT ON COLUMN hopes.photos.pho_photo_bytes IS 'Columna que contiene la photo en bytes';
COMMENT ON COLUMN hopes.photos.pho_title IS 'Columna que contiene el tiulo de la foto';
COMMENT ON COLUMN hopes.photos.pho_description IS 'Columna que contiene la descripcion de la foto';
COMMENT ON COLUMN hopes.photos.pho_pth_id IS 'Columna que contiene la seccion de la foto';
COMMENT ON COLUMN hopes.photos.pho_pac_id IS 'Columna que contiene el paciente de la foto';
COMMENT ON COLUMN hopes.photos.user_updated IS 'Columna que contiene el nombre de la ultima persona que ha modificado la foto';
COMMENT ON COLUMN hopes.photos.date_created IS 'Columna que contiene la fecha de creación de la foto';
COMMENT ON COLUMN hopes.photos.date_updated IS 'Columna que contiene la fecha de la última modificación sufrida en las fotos';
COMMENT ON CONSTRAINT pho_id_pk ON hopes.photos IS 'pk de la tabla photo';
COMMENT ON CONSTRAINT pho_sec_id_pk ON hopes.photos IS 'pk Relacion con la tabla sections';
COMMENT ON CONSTRAINT pho_pac_id_pk ON hopes.photos IS 'pk Relacion con la tabla patients';

-- ddl-end --