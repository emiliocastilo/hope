ALTER TABLE hopes.doses ADD CONSTRAINT dos_codeAtc_description_doseIndicated_unique UNIQUE (dos_code_atc,dos_description,dos_dose_indicated);

-- COMENTARIOS
COMMENT ON CONSTRAINT dos_codeAtc_description_doseIndicated_unique ON hopes.doses IS 'Las columnas codeATC, description y dosis en conjunto no deben repetirse';