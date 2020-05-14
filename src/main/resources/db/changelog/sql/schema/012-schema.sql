-- object: hopes.patients_diagnoses | type: TABLE --
DROP TABLE IF EXISTS hopes.health_outcomes CASCADE;
CREATE TABLE hopes.health_outcomes (
	hou_id serial  NOT NULL,
	hou_pac_id smallint REFERENCES hopes.patients (pac_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT,
	hou_index_type VARCHAR(50),
	hou_value VARCHAR(50),
	hou_result VARCHAR(50),
	hou_date TIMESTAMP,		
	CONSTRAINT hou_id_pk PRIMARY KEY(hou_id),
	CONSTRAINT hou_pac_id_fk FOREIGN KEY (hou_pac_id) REFERENCES hopes.patients(pac_id)
);
CREATE UNIQUE INDEX pki_hou_id ON hopes.health_outcomes (hou_id);
CREATE INDEX fki_hou_pac_id ON hopes.health_outcomes (hou_pac_id);

-- COMENTARIOS
COMMENT ON TABLE hopes.health_outcomes IS 'Tabla de resultados';
COMMENT ON COLUMN hopes.health_outcomes.hou_pac_id IS 'Columna que contiene el paciente al que pertenece el resultado';
COMMENT ON COLUMN hopes.health_outcomes.hou_index_type IS 'Columna que contiene la tipo indice del resultado.';
COMMENT ON COLUMN hopes.health_outcomes.hou_value IS 'Columna que contiene valor del resultado.';
COMMENT ON COLUMN hopes.health_outcomes.hou_result IS 'Columna que contiene el resultado.';

COMMENT ON CONSTRAINT hou_id_pk ON hopes.health_outcomes IS 'pk de la tabla health_outcomes';
COMMENT ON CONSTRAINT hou_pac_id_fk ON hopes.health_outcomes IS 'fk  Relacion con la tabla SECCIONS';

-- ddl-end --

ALTER TABLE hopes.patients_treatments DROP COLUMN IF EXISTS ptr_active;
ALTER TABLE hopes.patients_treatments ADD COLUMN ptr_active boolean NOT NULL DEFAULT true;