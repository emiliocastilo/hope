-- object: hopes.localized_sections | type: TABLE --
DROP TABLE IF EXISTS hopes.localized_sections CASCADE;
CREATE TABLE hopes.localized_sections (
	lcs_id serial,
	lcs_locale varchar(2) NOT NULL,
	lcs_title varchar(60) NOT NULL,
	lcs_description varchar(200) NOT NULL,
	lcs_sec_id smallint,
	CONSTRAINT lcs_id_pk PRIMARY KEY (lcs_id),
	CONSTRAINT lcs_id_id_fk FOREIGN KEY (lcs_sec_id) REFERENCES hopes.sections(sec_id)
	
);
-- COMENTARIOS
COMMENT ON TABLE hopes.localized_sections IS 'Tabla de traducciones de secciones';
COMMENT ON COLUMN hopes.localized_sections.lcs_title IS 'Columna que contiene el titulo del seccion';
COMMENT ON COLUMN hopes.localized_sections.lcs_description IS 'Columna que contiene la descripcion de seccion.';
COMMENT ON CONSTRAINT lcs_id_pk ON hopes.localized_sections IS 'pk de la tabla localized_sections';
COMMENT ON CONSTRAINT lcs_id_id_fk ON hopes.localized_sections IS 'fk  Relacion con la tabla SECCIONS';

-- ddl-end --