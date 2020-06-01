drop table if exists patients_treatments CASCADE;
drop table if exists patients_diagnoses CASCADE;

drop table if exists hopes.indications;
--indications
CREATE TABLE hopes.indications (
	ind_id serial NOT NULL,
	ind_pth_id int8,	
	ind_description varchar(20) NOT NULL,
	CONSTRAINT ind_id_pk PRIMARY KEY (ind_id),
	CONSTRAINT ind_pth_id_fk FOREIGN KEY (ind_pth_id) REFERENCES hopes.pathologies (pth_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT
);
CREATE UNIQUE INDEX pki_ind_id ON hopes.indications (ind_id);
CREATE INDEX fki_ind_pth_id ON hopes.indications (ind_pth_id);

--cienueve
drop table if exists hopes.cies_nine;
CREATE TABLE hopes.cies_nine (
	cin_id serial NOT NULL,	
	cin_code varchar(20) NOT null,
	cin_description varchar(200) NOT NULL,
	CONSTRAINT cin_id_pk PRIMARY KEY (cin_id)
);
CREATE UNIQUE INDEX pki_cin_id ON hopes.cies_nine (cin_id);

--ciediez
drop table if exists hopes.cies_ten;
CREATE TABLE hopes.cies_ten (
	cid_id serial NOT NULL,	
	cid_code varchar(20) NOT null,
	cid_description varchar(200) NOT NULL,
	CONSTRAINT cid_id_pk PRIMARY KEY (cid_id)
);
CREATE UNIQUE INDEX pki_cid_id ON hopes.cies_ten (cid_id);

--patients_data
drop table if exists hopes.patients_data; 
create table hopes.patients_data(
	pdt_id serial not null,
	pdt_pac_id int8 not null,
	pdt_weight decimal(5,2) NULL,
	pdt_height decimal(3,2) NULL,
	pdt_imc decimal(4,2) NULL,
	pdt_pat int8 NULL,
	pdt_pas int8 NULL,
	pdt_abdominal_perimeter int8 NULL,
	pdt_body_surface decimal(4,2) NULL,
	pdt_psoriatric boolean NULL,
	pdt_date timestamp null,
	CONSTRAINT pdt_id_pk PRIMARY key (pdt_id),
	CONSTRAINT pdt_pac_id_fk FOREIGN KEY (pdt_pac_id) REFERENCES hopes.patients (pac_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT	
);
CREATE UNIQUE INDEX pki_pdt_id ON hopes.patients_data (pdt_id);
CREATE INDEX fki_pdt_pac_id ON hopes.patients_data (pdt_pac_id);

--health_outcomes
drop table if exists hopes.health_outcomes; 
CREATE TABLE hopes.health_outcomes (
	hou_id serial  NOT NULL,
	hou_pac_id int8,
	hou_index_type VARCHAR(50),
	hou_value int4,
	hou_result VARCHAR(50),
	hou_date TIMESTAMP,		
	CONSTRAINT hou_id_pk PRIMARY KEY(hou_id),
	CONSTRAINT hou_pac_id_fk FOREIGN KEY (hou_pac_id) REFERENCES hopes.patients (pac_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT	
);
CREATE UNIQUE INDEX pki_hou_id ON hopes.health_outcomes (hou_id);
CREATE INDEX fki_hou_pac_id ON hopes.health_outcomes (hou_pac_id);
--patients_treatments
drop table if exists hopes.patients_treatments CASCADE;
CREATE TABLE hopes.patients_treatments(
  ptr_id serial  NOT NULL,
  ptr_pdg_id int8 REFERENCES hopes.patients_diagnoses (pdg_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT,
  ptr_active boolean,
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
  CONSTRAINT ptr_pdg_id_fk FOREIGN KEY (ptr_pdg_id) REFERENCES hopes.patients_diagnoses (pdg_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT ptr_med_id_fk FOREIGN KEY (ptr_med_id) REFERENCES hopes.medicines (med_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT
);
CREATE UNIQUE INDEX pki_ptr_id ON hopes.patients_treatments (ptr_id);
CREATE INDEX fki_ptr_pdg_id ON hopes.patients_treatments (ptr_pdg_id);
CREATE INDEX fki_ptr_med_id ON hopes.patients_treatments (ptr_med_id);

--patients_diagnoses
drop table if exists hopes.patients_diagnoses CASCADE;
CREATE TABLE hopes.patients_diagnoses(
	pdg_id serial  NOT NULL,
	pdg_pac_id int8 NOT NULL,
	pdg_ind_id int8,
	pdg_cin_id int8,
	pdg_cid_id int8,
	pdg_others_indications VARCHAR(50),
	pdg_init_date TIMESTAMP,
	pdg_symptoms_date TIMESTAMP,
	pdg_derivation_date TIMESTAMP,
	CONSTRAINT pdg_id_pk PRIMARY KEY(pdg_id),
	CONSTRAINT pdg_pac_id_fk FOREIGN KEY (pdg_pac_id) REFERENCES hopes.patients (pac_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE restrict,
	CONSTRAINT pdg_ind_id_fk FOREIGN KEY (pdg_ind_id) REFERENCES hopes.indications (ind_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE restrict,
	CONSTRAINT pdg_cin_id_fk FOREIGN KEY (pdg_cin_id) REFERENCES hopes.cies_nine (cin_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE restrict,
	CONSTRAINT pdg_cid_id_fk FOREIGN KEY (pdg_cid_id) REFERENCES hopes.cies_ten (cid_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT
);
CREATE UNIQUE INDEX pki_pdg_id ON hopes.patients_diagnoses (pdg_id);
CREATE INDEX fki_pdg_pac_id ON hopes.patients_diagnoses (pdg_pac_id);
CREATE INDEX fki_pdg_ind_id ON hopes.patients_diagnoses (pdg_ind_id);
CREATE INDEX fki_pdg_cin_id ON hopes.patients_diagnoses (pdg_cin_id);
CREATE INDEX fki_pdg_cid_id ON hopes.patients_diagnoses (pdg_cid_id);



--medicines: Añadimos campo para indicar si un medicamento es biológico
alter table hopes.medicines add column IF NOT exists med_biologic boolean default false;

