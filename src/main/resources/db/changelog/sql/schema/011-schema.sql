-- object: hopes.patients_diagnoses | type: TABLE --
DROP TABLE IF EXISTS hopes.patients_diagnoses CASCADE;
CREATE TABLE hopes.patients_diagnoses(
	pdg_id serial  NOT NULL,
	pdg_pac_id int8 REFERENCES hopes.patients (pac_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT,
	pdg_indication VARCHAR(50),
	pdg_cie9_code VARCHAR(50),
	pdg_cie9_desc VARCHAR(50),
	pdg_cie10_code VARCHAR(50),
	pdg_cie10_desc VARCHAR(50),
	pdg_others_indications VARCHAR(50),
	pdg_init_date TIMESTAMP,
	pdg_symptoms_date TIMESTAMP,
	pdg_derivation_date TIMESTAMP,
	CONSTRAINT pdg_id_pk PRIMARY KEY(pdg_id),
	CONSTRAINT pdg_pac_id_fk FOREIGN KEY (pdg_pac_id) REFERENCES hopes.patients (pac_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT	
);
CREATE UNIQUE INDEX pki_pdg_id ON hopes.patients_diagnoses (pdg_id);
CREATE INDEX fki_pdg_pac_id ON hopes.patients_diagnoses (pdg_pac_id);

DROP TABLE IF EXISTS hopes.patients_treatments CASCADE;
CREATE TABLE hopes.patients_treatments	(
	ptr_id serial  NOT NULL,
	ptr_pac_id int8 REFERENCES hopes.patients (pac_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT,
	ptr_pdg_id int8 REFERENCES hopes.patients_diagnoses (pdg_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT,
	ptr_active boolean,
	ptr_indication VARCHAR(50),
	ptr_type VARCHAR(50),
	ptr_med_id int8 REFERENCES hopes.medicines (med_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT,	
	ptr_dose VARCHAR(50),
	ptr_master_formula VARCHAR(50),
	ptr_master_formula_dose VARCHAR(50),
	ptr_regimen VARCHAR(50),
	ptr_init_date TIMESTAMP,
	ptr_final_date TIMESTAMP,
	ptr_end_cause VARCHAR(50),
	ptr_reason VARCHAR(50),
	CONSTRAINT ptr_id_pk PRIMARY KEY(ptr_id),
	CONSTRAINT ptr_pac_id_fk FOREIGN KEY (ptr_pac_id) REFERENCES hopes.patients (pac_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT,
	CONSTRAINT ptr_pdg_id_fk FOREIGN KEY (ptr_pdg_id) REFERENCES hopes.patients_diagnoses (pdg_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT
		
);
CREATE UNIQUE INDEX pki_ptr_id ON hopes.patients_treatments (ptr_id);
CREATE INDEX fki_ptr_pac_id ON hopes.patients_treatments (ptr_pac_id);	
CREATE INDEX fki_ptr_pdg_id ON hopes.patients_treatments (ptr_pdg_id);
CREATE INDEX fki_ptr_med_id ON hopes.patients_treatments (ptr_med_id);
-- ddl-end --