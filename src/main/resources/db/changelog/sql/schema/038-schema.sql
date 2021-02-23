CREATE TABLE hopes.patients_treatments_lines (
	ptl_id serial NOT NULL,
	ptl_ptr_id int8 not null REFERENCES hopes.patients_treatments(ptr_id),
	ptl_modification_count int8 not NULL,
	ptl_med_id int8 null references hopes.medicines(med_id),
	ptl_dose varchar(500) null,
	ptl_master_formula varchar(500) null,
	ptl_master_formula_dose varchar(500) null,
	ptl_regimen varchar(200) null,
	ptl_reason varchar(200) NULL,
	ptl_type varchar(50) not NULL,
	ptl_med_changed boolean null,
	ptl_active boolean null,
	ptl_suspension_date timestamp null,
	ptl_deleted boolean null,
	ptl_deletion_date timestamp null
);