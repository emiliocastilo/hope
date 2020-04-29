ALTER TABLE hopes.patients ALTER COLUMN pac_hos_id TYPE bigint;

ALTER TABLE hopes.patients_pathologies ALTER COLUMN pcp_pth_id TYPE bigint;

ALTER TABLE hopes.hospitals_pathologies ALTER COLUMN hsp_hos_id TYPE bigint;
ALTER TABLE hopes.hospitals_pathologies ALTER COLUMN hsp_pth_id TYPE bigint;

ALTER TABLE hopes.sections ALTER COLUMN sec_order TYPE bigint;
ALTER TABLE hopes.sections ALTER COLUMN sec_section_root TYPE bigint;

ALTER TABLE hopes.sections_forms ALTER COLUMN scf_section_id TYPE bigint;
ALTER TABLE hopes.sections_forms ALTER COLUMN scf_form_id TYPE bigint;
ALTER TABLE hopes.sections_forms ALTER COLUMN scf_order TYPE bigint;

ALTER TABLE hopes.values_aux_tables ALTER COLUMN vat_aux_table_id TYPE bigint;
ALTER TABLE hopes.values_aux_tables ALTER COLUMN vat_value_id TYPE bigint;
ALTER TABLE hopes.values_aux_tables ALTER COLUMN vat_order TYPE bigint;

ALTER TABLE hopes.users ALTER COLUMN usr_hos_id TYPE bigint;

ALTER TABLE hopes.users_roles ALTER COLUMN uro_user_id TYPE bigint;
ALTER TABLE hopes.users_roles ALTER COLUMN uro_rol_id TYPE bigint;

ALTER TABLE hopes.patients_forms ALTER COLUMN paf_form_id TYPE bigint;
ALTER TABLE hopes.patients_forms ALTER COLUMN paf_user_id TYPE bigint;

ALTER TABLE hopes.patients_forms_fields ALTER COLUMN pff_form_id TYPE bigint;
ALTER TABLE hopes.patients_forms_fields ALTER COLUMN pff_value_num TYPE bigint;

ALTER TABLE hopes.doctors ALTER COLUMN dct_college_number TYPE bigint;
ALTER TABLE hopes.doctors ALTER COLUMN dct_service_id TYPE bigint;
ALTER TABLE hopes.doctors ALTER COLUMN dct_user_id TYPE bigint;

ALTER TABLE hopes.medicines ALTER COLUMN med_recommendation_id TYPE bigint;

ALTER TABLE hopes.patients ALTER COLUMN pac_hos_id TYPE bigint;
