DROP TABLE IF EXISTS hopes.patients_clinical_data CASCADE;
CREATE TABLE hopes.patients_clinical_data(
	pcd_id serial  NOT NULL,
	pcd_pac_id int8 REFERENCES hopes.patients (pac_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT,
	pcd_name VARCHAR(50),
	pcd_value VARCHAR(50),
	pcd_description VARCHAR(50),
	pcd_classification VARCHAR(50),
	CONSTRAINT pcd_id_pk PRIMARY KEY(pcd_id),
	CONSTRAINT pcd_pac_id_fk FOREIGN KEY (pcd_pac_id) REFERENCES hopes.patients (pac_id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT
);
CREATE UNIQUE INDEX pki_pct_id ON hopes.patients_clinical_data (pcd_id);
CREATE INDEX fki_pcd_pac_id ON hopes.patients_clinical_data (pcd_pac_id);