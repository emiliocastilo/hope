--indications
INSERT INTO hopes.indications (ind_pth_id, ind_description) (select pth_id, 'EN PLACAS' from hopes.pathologies p where p.pth_name = 'Dermatología');
INSERT INTO hopes.indications (ind_pth_id, ind_description) (select pth_id, 'PALMOPLANTAR' from hopes.pathologies p where p.pth_name = 'Dermatología');
INSERT INTO hopes.indications (ind_pth_id, ind_description) (select pth_id, 'ERITRODERMIA' from hopes.pathologies p where p.pth_name = 'Dermatología');
INSERT INTO hopes.indications (ind_pth_id, ind_description) (select pth_id, 'PUSTULOSA' from hopes.pathologies p where p.pth_name = 'Dermatología');
INSERT INTO hopes.indications (ind_pth_id, ind_description) (select pth_id, 'OTRAS' from hopes.pathologies p where p.pth_name = 'Dermatología');

--hopes.patients
INSERT INTO hopes.patients
( pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date)
VALUES( 'Prueba1', 'Primer1', 'Segun2', 'PCM001', 'PCMTARJ1', null, null, null, null, 'M', null);
INSERT INTO hopes.patients
( pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date)
VALUES( 'Prueba2', 'Primer2', 'Segun2', 'PCM002', 'PCMTARJ2', null, null, null, null, 'F', null);
INSERT INTO hopes.patients
( pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date)
VALUES( 'Prueba3', 'Primer3', 'Segun3', 'PCM003', 'PCMTARJ3', null, null, null, null, 'M', null);
INSERT INTO hopes.patients
( pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date)
VALUES( 'Prueba4', 'Primer4', 'Segun4', 'PCM004', 'PCMTARJ4', null, null, null, null, 'F', null);
INSERT INTO hopes.patients
( pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date)
VALUES( 'Prueba5', 'Primer5', 'Segun5', 'PCM005', 'PCMTARJ5', null, null, null, null, 'M', null);
INSERT INTO hopes.patients
( pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date)
VALUES( 'Prueba6', 'Primer6', 'Segun6', 'PCM006', 'PCMTARJ6', null, null, null, null, 'F', null);
INSERT INTO hopes.patients
( pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date)
VALUES( 'Prueba7', 'Primer7', 'Segun7', 'PCM007', 'PCMTARJ7', null, null, null, null, 'M', null);
INSERT INTO hopes.patients
( pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date)
VALUES( 'Prueba8', 'Primer8', 'Segun8', 'PCM008', 'PCMTARJ8', null, null, null, null, 'F', null);
INSERT INTO hopes.patients
( pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date)
VALUES( 'Prueba9', 'Primer9', 'Segun9', 'PCM009', 'PCMTARJ9', null, null, null, null, 'M', null);
INSERT INTO hopes.patients
( pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date)
VALUES( 'Prueba10', 'Primer10', 'Segun10', 'PCM010', 'PCMTARJ10', null, null, null, null, 'M', null);
INSERT INTO hopes.patients
( pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date)
VALUES( 'Prueba11', 'Primer11', 'Segun11', 'PCM011', 'PCMTARJ11', null, null, null, null, 'M', null);


--patients_diagnoses
INSERT INTO hopes.patients_diagnoses
(pdg_pac_id, pdg_ind_id, pdg_cin_id, pdg_cid_id, pdg_others_indications, pdg_init_date, pdg_symptoms_date, pdg_derivation_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM001'), (select ind_id from hopes.indications where ind_description ='EN PLACAS'),  null, null, '', null, null, null);
INSERT INTO hopes.patients_diagnoses
(pdg_pac_id, pdg_ind_id, pdg_cin_id, pdg_cid_id, pdg_others_indications, pdg_init_date, pdg_symptoms_date, pdg_derivation_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM002'), (select ind_id from hopes.indications where ind_description ='EN PLACAS'),  null, null, '', null, null, null);
INSERT INTO hopes.patients_diagnoses
(pdg_pac_id, pdg_ind_id, pdg_cin_id, pdg_cid_id, pdg_others_indications, pdg_init_date, pdg_symptoms_date, pdg_derivation_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM003'), (select ind_id from hopes.indications where ind_description ='PALMOPLANTAR'),  null, null, '', null, null, null);
INSERT INTO hopes.patients_diagnoses
(pdg_pac_id, pdg_ind_id, pdg_cin_id, pdg_cid_id, pdg_others_indications, pdg_init_date, pdg_symptoms_date, pdg_derivation_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM004'), (select ind_id from hopes.indications where ind_description ='ERITRODERMIA'),  null, null, '', null, null, null);
INSERT INTO hopes.patients_diagnoses
(pdg_pac_id, pdg_ind_id, pdg_cin_id, pdg_cid_id, pdg_others_indications, pdg_init_date, pdg_symptoms_date, pdg_derivation_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM005'), (select ind_id from hopes.indications where ind_description ='PUSTULOSA'),  null, null, '', null, null, null);
INSERT INTO hopes.patients_diagnoses
(pdg_pac_id, pdg_ind_id, pdg_cin_id, pdg_cid_id, pdg_others_indications, pdg_init_date, pdg_symptoms_date, pdg_derivation_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM006'), (select ind_id from hopes.indications where ind_description ='OTRAS'),  null, null, 'Otras indicaciones', null, null, null);
INSERT INTO hopes.patients_diagnoses
(pdg_pac_id, pdg_ind_id, pdg_cin_id, pdg_cid_id, pdg_others_indications, pdg_init_date, pdg_symptoms_date, pdg_derivation_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM007'), (select ind_id from hopes.indications where ind_description ='PALMOPLANTAR'),  null, null, '', null, null, null);
INSERT INTO hopes.patients_diagnoses
(pdg_pac_id, pdg_ind_id, pdg_cin_id, pdg_cid_id, pdg_others_indications, pdg_init_date, pdg_symptoms_date, pdg_derivation_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM008'), (select ind_id from hopes.indications where ind_description ='ERITRODERMIA'),  null, null, '', null, null, null);
INSERT INTO hopes.patients_diagnoses
(pdg_pac_id, pdg_ind_id, pdg_cin_id, pdg_cid_id, pdg_others_indications, pdg_init_date, pdg_symptoms_date, pdg_derivation_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM009'), (select ind_id from hopes.indications where ind_description ='PALMOPLANTAR'),  null, null, '', null, null, null);

--patients_treatments
INSERT INTO hopes.patients_treatments
(ptr_pdg_id, ptr_type, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_end_cause, ptr_reason, ptr_active)
VALUES((select pd.pdg_id from hopes.patients_diagnoses pd where pd.pdg_pac_id in (select pac_id from hopes.patients where pac_nhc = 'PCM001')), 'BIOLOGICO', '', '', '', 'INTENSIFICADA', '', '', true);
INSERT INTO hopes.patients_treatments
(ptr_pdg_id, ptr_type, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_end_cause, ptr_reason, ptr_active, ptr_init_date)
VALUES((select pd.pdg_id from hopes.patients_diagnoses pd where pd.pdg_pac_id in (select pac_id from hopes.patients where pac_nhc = 'PCM001')), 'BIOLOGICO', '', '', '', '', 'Cambio', 'Ineficacia', false, current_timestamp - interval '1 year');
INSERT INTO hopes.patients_treatments
(ptr_pdg_id, ptr_type, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_end_cause, ptr_reason, ptr_active, ptr_init_date)
VALUES((select pd.pdg_id from hopes.patients_diagnoses pd where pd.pdg_pac_id in (select pac_id from hopes.patients where pac_nhc = 'PCM001')), 'BIOLOGICO', '', '', '', '', 'Cambio', 'Ineficacia', false, current_timestamp - interval '2 year');
INSERT INTO hopes.patients_treatments
(ptr_pdg_id, ptr_type, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_end_cause, ptr_reason, ptr_active, ptr_init_date)
VALUES((select pd.pdg_id from hopes.patients_diagnoses pd where pd.pdg_pac_id in (select pac_id from hopes.patients where pac_nhc = 'PCM002')), 'BIOLOGICO', '', '', '', '', 'Cambio', 'Blanqueamiento', false, current_timestamp - interval '2 year');
INSERT INTO hopes.patients_treatments
(ptr_pdg_id, ptr_type, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_end_cause, ptr_reason, ptr_active)
VALUES((select pd.pdg_id from hopes.patients_diagnoses pd where pd.pdg_pac_id in (select pac_id from hopes.patients where pac_nhc = 'PCM002')), 'BIOLOGICO', '', '', '', '', '', '', true);
INSERT INTO hopes.patients_treatments
(ptr_pdg_id, ptr_type, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_end_cause, ptr_reason, ptr_active)
VALUES((select pd.pdg_id from hopes.patients_diagnoses pd where pd.pdg_pac_id in (select pac_id from hopes.patients where pac_nhc = 'PCM003')), 'BIOLOGICO', '', '', '', '', '', '', true);
INSERT INTO hopes.patients_treatments
(ptr_pdg_id, ptr_type, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_end_cause, ptr_reason, ptr_active, ptr_init_date)
VALUES((select pd.pdg_id from hopes.patients_diagnoses pd where pd.pdg_pac_id in (select pac_id from hopes.patients where pac_nhc = 'PCM004')), 'QUIMICO', '', '', '', '', '', '', true, current_timestamp - interval '2 month');
INSERT INTO hopes.patients_treatments
(ptr_pdg_id, ptr_type, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_end_cause, ptr_reason, ptr_active, ptr_init_date)
VALUES((select pd.pdg_id from hopes.patients_diagnoses pd where pd.pdg_pac_id in (select pac_id from hopes.patients where pac_nhc = 'PCM004')), 'BIOLOGICO', '', '', '', '', 'Suspension', 'Blanqueamiento', false, current_timestamp - interval '6 month');
INSERT INTO hopes.patients_treatments
(ptr_pdg_id, ptr_type, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_end_cause, ptr_reason, ptr_active, ptr_init_date)
VALUES((select pd.pdg_id from hopes.patients_diagnoses pd where pd.pdg_pac_id in (select pac_id from hopes.patients where pac_nhc = 'PCM004')), 'BIOLOGICO', '', '', '', '', 'Suspension', 'Blanqueamiento', false, current_timestamp - interval '1 year');
INSERT INTO hopes.patients_treatments
(ptr_pdg_id, ptr_type, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_end_cause, ptr_reason, ptr_active, ptr_init_date)
VALUES((select pd.pdg_id from hopes.patients_diagnoses pd where pd.pdg_pac_id in (select pac_id from hopes.patients where pac_nhc = 'PCM005')), 'BIOLOGICO', '', '', '', '', 'Suspension', 'Blanqueamiento', false, current_timestamp - interval '1 year');
INSERT INTO hopes.patients_treatments
(ptr_pdg_id, ptr_type, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_end_cause, ptr_reason, ptr_active, ptr_init_date)
VALUES((select pd.pdg_id from hopes.patients_diagnoses pd where pd.pdg_pac_id in (select pac_id from hopes.patients where pac_nhc = 'PCM005')), 'BIOLOGICO', '', '', '', '', 'Suspension', 'Blanqueamiento', false, current_timestamp - interval '2 year');
INSERT INTO hopes.patients_treatments
(ptr_pdg_id, ptr_type, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_end_cause, ptr_reason, ptr_active, ptr_init_date)
VALUES((select pd.pdg_id from hopes.patients_diagnoses pd where pd.pdg_pac_id in (select pac_id from hopes.patients where pac_nhc = 'PCM005')), 'BIOLOGICO', '', '', '', '', 'Suspension', 'Blanqueamiento', false, current_timestamp - interval '3 year');

--health_outcomes
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM001'), 'PASI', 0, '', current_timestamp - interval '1 year');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM001'), 'PASI', 0, '', current_timestamp - interval '6 month');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM001'), 'PASI', 0, '', current_timestamp - interval '4 month');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM001'), 'PASI', 1, 'Prueba PASI', current_timestamp - interval '2 month');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM001'), 'DLQI', 0, '', current_timestamp - interval '1 year');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM001'), 'DLQI', 0, '', current_timestamp - interval '6 month');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM001'), 'DLQI', 0, '', current_timestamp - interval '4 month');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM001'), 'DLQI', 2, 'Prueba DLQI', current_timestamp - interval '2 month');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM002'), 'PASI', 0, '', current_timestamp - interval '1 year');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM002'), 'PASI', 0, '', current_timestamp - interval '6 month');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM002'), 'PASI', 0, '', current_timestamp - interval '4 month');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM002'), 'PASI', 1, 'Prueba PASI', current_timestamp - interval '2 month');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM002'), 'DLQI', 0, '', current_timestamp - interval '1 year');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM003'), 'DLQI', 0, '', current_timestamp - interval '6 month');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM003'), 'DLQI', 0, '', current_timestamp - interval '4 month');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM003'), 'DLQI', 2, 'Prueba DLQI', current_timestamp - interval '2 month');

INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM004'), 'PASI', 0, '', current_timestamp - interval '1 year');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM004'), 'PASI', 0, '', current_timestamp - interval '6 month');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM004'), 'PASI', 0, '', current_timestamp - interval '4 month');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM004'), 'PASI', 6, 'Prueba PASI 6', current_timestamp - interval '2 month');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM004'), 'DLQI', 0, '', current_timestamp - interval '1 year');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM004'), 'DLQI', 0, '', current_timestamp - interval '6 month');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM004'), 'DLQI', 0, '', current_timestamp - interval '4 month');
INSERT INTO hopes.health_outcomes (hou_pac_id, hou_index_type, hou_value, hou_result, hou_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM004'), 'DLQI', 6, 'Prueba DLQI 6', current_timestamp - interval '2 month');



--patients_data
INSERT INTO hopes.patients_data (pdt_pac_id, pdt_weight, pdt_height, pdt_imc, pdt_pat, pdt_pas, pdt_abdominal_perimeter, pdt_body_surface, pdt_psoriatric, pdt_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM001'), 80.258, 1.752, 20, 0, 0, 30, 2.54, false, current_timestamp );
INSERT INTO hopes.patients_data (pdt_pac_id, pdt_weight, pdt_height, pdt_imc, pdt_pat, pdt_pas, pdt_abdominal_perimeter, pdt_body_surface, pdt_psoriatric, pdt_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM001'), 82, 1.752, 20, 0, 0, 30, 2.54, false, current_timestamp - interval '2 month' );
INSERT INTO hopes.patients_data (pdt_pac_id, pdt_weight, pdt_height, pdt_imc, pdt_pat, pdt_pas, pdt_abdominal_perimeter, pdt_body_surface, pdt_psoriatric, pdt_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM001'), 81.5, 1.752, 20, 0, 0, 30, 2.54, false, current_timestamp - interval '4 month');
INSERT INTO hopes.patients_data (pdt_pac_id, pdt_weight, pdt_height, pdt_imc, pdt_pat, pdt_pas, pdt_abdominal_perimeter, pdt_body_surface, pdt_psoriatric, pdt_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM001'), 79, 1.752, 20, 0, 0, 30, 2.54, false, current_timestamp - interval '6 month');
INSERT INTO hopes.patients_data (pdt_pac_id, pdt_weight, pdt_height, pdt_imc, pdt_pat, pdt_pas, pdt_abdominal_perimeter, pdt_body_surface, pdt_psoriatric, pdt_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM001'), 80.258, 1.752, 20, 0, 0, 30, 2.54, false, current_timestamp - interval '8 month');
INSERT INTO hopes.patients_data (pdt_pac_id, pdt_weight, pdt_height, pdt_imc, pdt_pat, pdt_pas, pdt_abdominal_perimeter, pdt_body_surface, pdt_psoriatric, pdt_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM002'), 65, 1.64, 19, 0, 0, 30, 2.54, false, current_timestamp );
INSERT INTO hopes.patients_data (pdt_pac_id, pdt_weight, pdt_height, pdt_imc, pdt_pat, pdt_pas, pdt_abdominal_perimeter, pdt_body_surface, pdt_psoriatric, pdt_date)
VALUES((select pac_id from hopes.patients where pac_nhc = 'PCM003'), 66, 1.64, 19, 0, 0, 30, 2.54, false, current_timestamp );
