ALTER TABLE hopes.medicines DROP COLUMN IF EXISTS med_unit_dose;
ALTER TABLE hopes.medicines DROP COLUMN IF EXISTS med_treatment_type;
ALTER TABLE hopes.medicines DROP COLUMN IF EXISTS med_pathology;
ALTER TABLE hopes.medicines DROP COLUMN IF EXISTS med_code_act_type;

ALTER TABLE hopes.medicines ADD COLUMN med_unit_dose bigint;
COMMENT ON COLUMN hopes.medicines.med_unit_dose IS 'MG de cada dosis del medicamento';

ALTER TABLE hopes.medicines ADD COLUMN med_treatment_type varchar(100);
COMMENT ON COLUMN hopes.medicines.med_treatment_type IS 'Tipo de tratamiento asociado al medicamento';

ALTER TABLE hopes.medicines ADD COLUMN med_code_act_type varchar(100);
COMMENT ON COLUMN hopes.medicines.med_code_act_type IS 'Tipo de código act';

-- object: hopes.services_pathologies | type: TABLE --
DROP TABLE IF EXISTS hopes.medicines_pathologies CASCADE;
CREATE TABLE hopes.medicines_pathologies
(
    mdp_id     serial,
    mdp_med_id smallint NOT NULL,
    mdp_pth_id smallint NOT NULL,
    CONSTRAINT mdp_id_pk PRIMARY KEY (mdp_id),
    CONSTRAINT mdp_med_pth_unique UNIQUE (mdp_med_id, mdp_pth_id),
    CONSTRAINT mdp_med_id_fk FOREIGN KEY (mdp_med_id) REFERENCES hopes.medicines (med_id),
    CONSTRAINT mdp_pth_id_fk FOREIGN KEY (mdp_pth_id) REFERENCES hopes.pathologies (pth_id)
);
-- COMENTARIOS
COMMENT ON TABLE hopes.medicines_pathologies IS 'Tabla intermedia que representa la relación entre las entidades MEDICINES y PATHOLOGIES';
COMMENT ON COLUMN hopes.medicines_pathologies.mdp_id IS 'Columna con el id de la tabla';
COMMENT ON COLUMN hopes.medicines_pathologies.mdp_med_id IS 'Columna que contiene el ID del medicamento';
COMMENT ON COLUMN hopes.medicines_pathologies.mdp_pth_id IS 'Columna que contiene el ID del patologia';
COMMENT ON CONSTRAINT mdp_id_pk ON hopes.medicines_pathologies IS 'pk de la tabla MEDICINES_PATHOLOGIES';
COMMENT ON CONSTRAINT mdp_pth_id_fk ON hopes.medicines_pathologies IS 'fk Relacion con la tabla PATHOLOGIES';
COMMENT ON CONSTRAINT mdp_med_id_fk ON hopes.medicines_pathologies IS 'fk Relacion con la tabla MEDICINES';
-- ddl-end --

ALTER TABLE hopes.doses DROP COLUMN IF EXISTS dos_code_atc_type;

ALTER TABLE hopes.doses ADD COLUMN dos_code_atc_type varchar(100);
COMMENT ON COLUMN hopes.doses.dos_code_atc_type IS 'Tipo de código atc';