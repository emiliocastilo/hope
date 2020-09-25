-- update duplicated
UPDATE hopes.hospitals_services SET hss_srv_id = (SELECT srv_id FROM hopes.hospitals_services right join hopes.services on hss_srv_id = srv_id where hss_id is null)
WHERE hss_id = (SELECT hss_id FROM hopes.hospitals_services a
join (SELECT hss_hos_id, hss_srv_id FROM hopes.hospitals_services GROUP BY (hss_hos_id, hss_srv_id) HAVING count(*) > 1) b
on a.hss_hos_id = b.hss_hos_id and a.hss_srv_id = b.hss_srv_id LIMIT 1);

-- Alter tables
ALTER TABLE hopes.hospitals_services
ADD CONSTRAINT hospitals_services_unique UNIQUE (hss_hos_id, hss_srv_id);

ALTER TABLE hopes.hospitals_pathologies
ADD CONSTRAINT hospitals_pathologies_unique UNIQUE (hsp_hos_id, hsp_pth_id);

ALTER TABLE hopes.patients_pathologies
ADD CONSTRAINT patients_pathologies_unique UNIQUE (pcp_pac_id, pcp_pth_id);

ALTER TABLE hopes.patients_forms
ADD CONSTRAINT patients_forms_unique UNIQUE (paf_form_id, paf_user_id);

ALTER TABLE hopes.patients_forms_fields
ADD CONSTRAINT patients_forms_fields_unique UNIQUE (pff_paf_id, pff_form_id);

ALTER TABLE hopes.sections_forms
ADD CONSTRAINT sections_forms_unique UNIQUE (scf_section_id, scf_form_id);

ALTER TABLE hopes.sections_roles
ADD CONSTRAINT sections_roles_unique UNIQUE (scr_section_id, scr_role_id);

ALTER TABLE hopes.users_roles
ADD CONSTRAINT users_roles_unique UNIQUE (uro_user_id, uro_rol_id);

-- Alter columns
ALTER TABLE hopes.patients 
ADD CONSTRAINT patients_pac_dni_unique UNIQUE (pac_dni);
ALTER TABLE hopes.patients 
ADD CONSTRAINT patients_pac_email_unique UNIQUE (pac_email);
