-- MODIFICAR NOMBRE Y TIPO DE LAS COLUMNAS CIE
ALTER TABLE hopes.patients_diagnoses DROP COLUMN pdg_cin_id;
ALTER TABLE hopes.patients_diagnoses DROP COLUMN pdg_cid_id;

ALTER TABLE hopes.patients_diagnoses ADD COLUMN pdg_cie_code VARCHAR(10);
ALTER TABLE hopes.patients_diagnoses ADD COLUMN pdg_cie_description VARCHAR(200);

-- COMENTARIOS
COMMENT ON COLUMN hopes.patients_diagnoses.pdg_cie_code IS 'Código CIE';
COMMENT ON COLUMN hopes.patients_diagnoses.pdg_cie_description IS 'Descripción código CIE';