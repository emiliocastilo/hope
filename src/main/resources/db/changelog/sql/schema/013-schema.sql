-- object: hopes.patients_diagnoses | type: TABLE --
DROP TABLE IF EXISTS hopes.indications CASCADE;
CREATE TABLE hopes.indications (
	ind_code varchar(3) NOT NULL,
	ind_description varchar(50) NOT NULL,
	CONSTRAINT ind_code_pk PRIMARY KEY (ind_code)
);

-- COMENTARIOS
COMMENT ON TABLE hopes.indications IS 'Tabla de indicaciones';
COMMENT ON COLUMN hopes.indications.ind_code IS 'Columna que contiene elcódigo de indicación';
COMMENT ON COLUMN hopes.indications.ind_description IS 'Columna que contiene la descripción de la indicación.';

COMMENT ON CONSTRAINT ind_code_pk ON hopes.indications IS 'pk de la tabla indications';

-- ddl-end --

ALTER TABLE hopes.patients_diagnoses DROP COLUMN IF EXISTS pdg_indication;
ALTER TABLE hopes.patients_diagnoses ADD COLUMN pdg_ind_code VARCHAR(3);
ALTER TABLE hopes.patients_diagnoses ADD CONSTRAINT pdg_ind_code_fk FOREIGN KEY (pdg_ind_code) REFERENCES hopes.indications (ind_code);

COMMENT ON CONSTRAINT pdg_ind_code_fk ON hopes.patients_diagnoses IS 'fk  Relacion con la tabla INDICATIONS';