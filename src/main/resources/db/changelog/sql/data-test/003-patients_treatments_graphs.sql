delete from hopes.patients_treatments;
delete from hopes.medicines;
delete from hopes.patients_diagnoses;
delete from hopes.patients;

----------------------------------------------------------------- PATIENTS -------------------------------------------------------------------------------
INSERT INTO hopes.patients(
	pac_id,
	pac_hos_id,
	pac_name,
	pac_first_surname,
	pac_last_surname,
	pac_nhc,
	pac_health_card,
	pac_dni,
	pac_address,
	pac_phone,
	pac_email,
	pac_gender_code,
	pac_birth_date)
VALUES (nextval('hopes.patients_pac_id_seq'),
		(select MIN(hos_id) from hopes.hospitals),
		'Albert',
		'Suarez',
		'Pons',
		'NHC0000001',
		'HC00000001',
		'99662829B',
		'Plaza Cataluña, 5',
		'924874522',
		'albert.suarez@hopes.es',
		'M',
	    '02-05-1985');
INSERT INTO hopes.patients(
	pac_id,
	pac_hos_id,
	pac_name,
	pac_first_surname,
	pac_last_surname,
	pac_nhc,
	pac_health_card,
	pac_dni,
	pac_address,
	pac_phone,
	pac_email,
	pac_gender_code,
	pac_birth_date)
VALUES (nextval('hopes.patients_pac_id_seq'),
		(select MIN(hos_id) from hopes.hospitals),
		'Jose',
		'Hernandez',
		'Romero',
		'NHC0000002',
		'HC00000002',
		'04347584D',
		'Avenida Andalucia, 1',
		'687445599',
		'jose.hernandez@hopes.es',
		'M',
	    '02-07-1978');
INSERT INTO hopes.patients(
	pac_id,
	pac_hos_id,
	pac_name,
	pac_first_surname,
	pac_last_surname,
	pac_nhc,
	pac_health_card,
	pac_dni,
	pac_address,
	pac_phone,
	pac_email,
	pac_gender_code,
	pac_birth_date)
VALUES (nextval('hopes.patients_pac_id_seq'),
		(select MIN(hos_id) from hopes.hospitals),
		'Silvia',
		'Bermudez',
		'Cerrato',
		'NHC0000003',
		'HC00000003',
		'30207799J',
		'Plaza Mayor, 13',
		'698556633',
		'silvia.bermudez@hopes.es',
		'F',
	    '02-07-1978');
INSERT INTO hopes.patients(
	pac_id,
	pac_hos_id,
	pac_name,
	pac_first_surname,
	pac_last_surname,
	pac_nhc,
	pac_health_card,
	pac_dni,
	pac_address,
	pac_phone,
	pac_email,
	pac_gender_code,
	pac_birth_date)
VALUES (nextval('hopes.patients_pac_id_seq'),
		(select MIN(hos_id) from hopes.hospitals),
		'Almudena',
		'Derecha',
		'Mana',
		'NHC0000004',
		'HC00000004',
		'93611731Y',
		'Avenida Extremadura, 31',
		'663587741',
		'almudena.derecha@hopes.es',
		'F',
	    '02-07-1967');

INSERT INTO hopes.patients(
	pac_id,
	pac_hos_id,
	pac_name,
	pac_first_surname,
	pac_last_surname,
	pac_nhc,
	pac_health_card,
	pac_dni,
	pac_address,
	pac_phone,
	pac_email,
	pac_gender_code,
	pac_birth_date)
VALUES (nextval('hopes.patients_pac_id_seq'),
		(select MIN(hos_id) from hopes.hospitals),
		'Roger',
		'Gonzalez',
		'Pons',
		'NHC0000005',
		'HC00000005',
		'99662829B',
		'Plaza Cataluña, 5',
		'924874522',
		'roger.gonzalez@hopes.es',
		'M',
	    '02-05-1985');
INSERT INTO hopes.patients(
	pac_id,
	pac_hos_id,
	pac_name,
	pac_first_surname,
	pac_last_surname,
	pac_nhc,
	pac_health_card,
	pac_dni,
	pac_address,
	pac_phone,
	pac_email,
	pac_gender_code,
	pac_birth_date)
VALUES (nextval('hopes.patients_pac_id_seq'),
		(select MIN(hos_id) from hopes.hospitals),
		'Alfonso',
		'Gallego',
		'Gordo',
		'NHC0000006',
		'HC00000006',
		'04347584D',
		'Avenida Andalucia, 1',
		'687445599',
		'alfonso.gallego@hopes.es',
		'M',
	    '02-07-1978');
INSERT INTO hopes.patients(
	pac_id,
	pac_hos_id,
	pac_name,
	pac_first_surname,
	pac_last_surname,
	pac_nhc,
	pac_health_card,
	pac_dni,
	pac_address,
	pac_phone,
	pac_email,
	pac_gender_code,
	pac_birth_date)
VALUES (nextval('hopes.patients_pac_id_seq'),
		(select MIN(hos_id) from hopes.hospitals),
		'Beatriz',
		'Ramirez',
		'Gordo',
		'NHC0000007',
		'HC00000007',
		'30207799J',
		'Plaza Mayor, 13',
		'698556633',
		'beatriz.ramirez@hopes.es',
		'F',
	    '02-07-1978');
INSERT INTO hopes.patients(
	pac_id,
	pac_hos_id,
	pac_name,
	pac_first_surname,
	pac_last_surname,
	pac_nhc,
	pac_health_card,
	pac_dni,
	pac_address,
	pac_phone,
	pac_email,
	pac_gender_code,
	pac_birth_date)
VALUES (nextval('hopes.patients_pac_id_seq'),
		(select MIN(hos_id) from hopes.hospitals),
		'Dorotea',
		'Izquierdo',
		'Sanchez',
		'NHC0000008',
		'HC00000008',
		'93611731Y',
		'Avenida Extremadura, 31',
		'663587741',
		'dorotea.izquierdo@hopes.es',
		'F',
	    '02-07-1967');
INSERT INTO hopes.patients(
	pac_id,
	pac_hos_id,
	pac_name,
	pac_first_surname,
	pac_last_surname,
	pac_nhc,
	pac_health_card,
	pac_dni,
	pac_address,
	pac_phone,
	pac_email,
	pac_gender_code,
	pac_birth_date)
VALUES (nextval('hopes.patients_pac_id_seq'),
		(select MIN(hos_id) from hopes.hospitals),
		'Jose',
		'Barcelo',
		'Roig',
		'NHC0000009',
		'HC00000009',
		'08025750S',
		'Plaza España, 13',
		'698556633',
		'jose.barcelo@hopes.es',
		'M',
	    '12-11-1939');
INSERT INTO hopes.patients(
	pac_id,
	pac_hos_id,
	pac_name,
	pac_first_surname,
	pac_last_surname,
	pac_nhc,
	pac_health_card,
	pac_dni,
	pac_address,
	pac_phone,
	pac_email,
	pac_gender_code,
	pac_birth_date)
VALUES (nextval('hopes.patients_pac_id_seq'),
		(select MIN(hos_id) from hopes.hospitals),
		'Pedro',
		'Pica',
		'Piedra',
		'NHC0000010',
		'HC00000010',
		'08025750S',
		'Plaza España, 13',
		'76888320X',
		'pedro.pica@hopes.es',
		'M',
	    '11-01-1933');
----------------------------------------------------------------- PATIENTS DIAGNOSES -------------------------------------------------------------------------------
INSERT INTO hopes.patients_diagnoses
(pdg_id, pdg_pac_id, pdg_indication, pdg_cie9_code, pdg_cie9_desc, pdg_cie10_code, pdg_cie10_desc, pdg_others_indications, pdg_init_date, pdg_symptoms_date, pdg_derivation_date)
VALUES
(nextval('hopes.patients_diagnoses_pdg_id_seq'),(select pac_id from hopes.patients where pac_nhc = 'NHC0000001'),'Dermatitis atópica',' 696.4','turpis. In condimentum. ','L02.01','in magna. ','','30-09-2019 07:09:14','04-04-2120 10:04:56','26-12-2020 09:12:31'),
(nextval('hopes.patients_diagnoses_pdg_id_seq'),(select pac_id from hopes.patients where pac_nhc = 'NHC0000001'),'Hidradenitis supurativa',' 696.3','Quisque tincidunt ','L00.01','consectetuer','','26-06-2019 12:06:40','18-02-2020 10:02:03','28-07-2019 04:07:51'),
(nextval('hopes.patients_diagnoses_pdg_id_seq'),(select pac_id from hopes.patients where pac_nhc = 'NHC0000002'),'Psoriasis palmo-plantar',' 696.2','lorem, vehicula et, ','L06.01','dolor vitae dolor.','','13-12-2019 04:12:44','17-07-2019 08:07:25','07-06-2019 03:06:33'),
(nextval('hopes.patients_diagnoses_pdg_id_seq'),(select pac_id from hopes.patients where pac_nhc = 'NHC0000002'),'Psoriasis pustulosa',' 696.1','Phasellus elit pede, ','L08.01','enim. Mauris quis turpis ','','01-03-02121 01:03:02','05-012-2020 02:12:46','24-03-2020 07:03:35'),
(nextval('hopes.patients_diagnoses_pdg_id_seq'),(select pac_id from hopes.patients where pac_nhc = 'NHC0000002'),'Dermatitis atópica',' 696.2','ornare, elit ,','L05.01','egestas. ','','31-03-2020 12:03:20','16-03-2020 04:03:38','13-07-2020 02:07:27'),
(nextval('hopes.patients_diagnoses_pdg_id_seq'),(select pac_id from hopes.patients where pac_nhc = 'NHC0000002'),'Eritrodermia',' 696.2','leo. Cras vehicula aliquet libero. ','L02.01','nec, malesuada ut, ','','23-07-2020 03:07:04','14-07-2019 11:07:34','17-04-2020 05:04:25'),
(nextval('hopes.patients_diagnoses_pdg_id_seq'),(select pac_id from hopes.patients where pac_nhc = 'NHC0000003'),'Otros (Derma)',' 696.2','interdum enim non nisi. ','L05.01','Nulla dignissim. ','','12-01-2020 07:01:20','04-09-2019 06:09:55','06-01-2020 08:01:19'),
(nextval('hopes.patients_diagnoses_pdg_id_seq'),(select pac_id from hopes.patients where pac_nhc = 'NHC0000004'),'Psoriasis pustulosa',' 696.2','auctor','L03.01','tristique aliquet. ','','22-01-2020 07:01:20','03-02-2020 01:02:32','31-03-2121 05:03:05'),
(nextval('hopes.patients_diagnoses_pdg_id_seq'),(select pac_id from hopes.patients where pac_nhc = 'NHC0000005'),'Psoriasis pustulosa',' 696.3','ante lectus convallis est, ','L01.01','at, libero. Morbi','','26-12-2019 04:12:36','05-05-2020 02:05:54','09-08-2019 04:08:03'),
(nextval('hopes.patients_diagnoses_pdg_id_seq'),(select pac_id from hopes.patients where pac_nhc = 'NHC0000006'),'Psoriasis En Placas',' 696.4','venenatis a, magna. ','L01.01','vitae erat vel ','','24-12-2020 10:12:51','27-07-2020 05:07:22','31-03-2020 02:03:28'),
(nextval('hopes.patients_diagnoses_pdg_id_seq'),(select pac_id from hopes.patients where pac_nhc = 'NHC0000006'),'Psoriasis palmo-plantar',' 696.4','venenatis a, magna. ','L01.01','vitae erat vel ','','24-12-2020 10:12:51','27-07-2020 05:07:22','31-03-2020 02:03:28'),
(nextval('hopes.patients_diagnoses_pdg_id_seq'),(select pac_id from hopes.patients where pac_nhc = 'NHC0000006'),'Otras psoriasis',' 696.1','malesuada id, erat. Etiam ','L05.01','libero','','13-07-2020 05:07:04','26-12-2020 01:12:14','08-09-2019 09:09:30'),
(nextval('hopes.patients_diagnoses_pdg_id_seq'),(select pac_id from hopes.patients where pac_nhc = 'NHC0000007'),'Psoriasis En Placas',' 696.4','venenatis a, magna. ','L01.01','vitae erat vel ','','24-12-2020 10:12:51','27-07-2020 05:07:22','31-03-2020 02:03:28'),
(nextval('hopes.patients_diagnoses_pdg_id_seq'),(select pac_id from hopes.patients where pac_nhc = 'NHC0000008'),'Psoriasis palmo-plantar',' 696.4','venenatis a, magna. ','L01.01','vitae erat vel ','','24-12-2020 10:12:51','27-07-2020 05:07:22','31-03-2020 02:03:28'),
(nextval('hopes.patients_diagnoses_pdg_id_seq'),(select pac_id from hopes.patients where pac_nhc = 'NHC0000009'),'Psoriasis En Placas',' 696.4','venenatis a, magna. ','L01.01','vitae erat vel ','','24-12-2020 10:12:51','27-07-2020 05:07:22','31-03-2020 02:03:28'),
(nextval('hopes.patients_diagnoses_pdg_id_seq'),(select pac_id from hopes.patients where pac_nhc = 'NHC0000010'),'Psoriasis En Placas',' 696.4','venenatis a, magna. ','L01.01','vitae erat vel ','','24-12-2020 10:12:51','27-07-2020 05:07:22','31-03-2020 02:03:28');




----------------------------------------------------------------- MEDICINES -------------------------------------------------------------------------------
INSERT INTO hopes.medicines(
	med_id, med_act_ingredients, med_code_act, med_recommended, med_recommendation_id, med_acronym, med_national_code, med_description, med_presentation, med_commercialization, date_created, date_updated)
	VALUES (nextval('hopes.medicines_med_id_seq'), 'ABACAVIR, LAMIVUDINA', 'J05AR02', false, null, 'ABALAAM', '711415', 'ABACAVIR/LAMIVUDINA  DR. REDDYS 600 MG/300 MG COMPRIMIDOS RECUBIERTOS CON PELICULA EFG 30 comprimidos', 'COMPRIMIDO RECUBIERTO CON PELÍCULA', true, current_timestamp, current_timestamp);

INSERT INTO hopes.medicines(
	med_id, med_act_ingredients, med_code_act, med_recommended, med_recommendation_id, med_acronym, med_national_code, med_description, med_presentation, med_commercialization, date_created, date_updated)
	VALUES (nextval('hopes.medicines_med_id_seq'), 'ACARBOSA', 'A10BF01', false, null, 'ACA', '662258', 'ACARBOSA TECNIGEN 50 mg COMPRIMIDOS', 'COMPRIMIDO RECUBIERTO CON PELÍCULA', true, current_timestamp, current_timestamp);

----------------------------------------------------------------- PATIENTS TREATMENT -------------------------------------------------------------------------------
INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000001'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000001') and pd.pdg_indication = 'Hidradenitis supurativa'), true, 'Hidradenitis supurativa', 'Biologico', (select med_id from hopes.medicines med where med.med_national_code = '662258'), '', '', '', '', current_timestamp, null, '', '');

INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000001'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000001') and pd.pdg_indication = 'Dermatitis atópica'), true, 'Dermatitis atópica', 'Tópico', (select med_id from hopes.medicines med where med.med_national_code = '711415'), '', '', '', '', current_timestamp, null, '', '');

INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000002'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000002') and pd.pdg_indication = 'Psoriasis palmo-plantar'), true, 'Psoriasis palmo-plantar', 'Biologico', (select med_id from hopes.medicines med where med.med_national_code = '662258'), '', '', '', '', current_timestamp, null, '', '');

INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000002'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000002') and pd.pdg_indication = 'Psoriasis pustulosa'), true, 'Psoriasis pustulosa', 'Tópico', (select med_id from hopes.medicines med where med.med_national_code = '711415'), '', '', '', '', current_timestamp, null, '', '');

INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000002'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000002') and pd.pdg_indication = 'Dermatitis atópica'), true, 'Dermatitis atópica', 'Biologico', (select med_id from hopes.medicines med where med.med_national_code = '662258'), '', '', '', '', current_timestamp, null, '', '');

INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000002'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000002') and pd.pdg_indication = 'Eritrodermia'), true, 'Eritrodermia', 'Tópico', (select med_id from hopes.medicines med where med.med_national_code = '711415'), '', '', '', '', current_timestamp, null, '', '');

INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000004'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000004') and pd.pdg_indication = 'Psoriasis pustulosa'), false, 'Psoriasis pustulosa', 'Biologico', (select med_id from hopes.medicines med where med.med_national_code = '662258'), '', '', '', '', current_timestamp - INTERVAL '7 DAY', current_timestamp, 'Cambio', 'Blanqueamiento');

INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000005'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000005') and pd.pdg_indication = 'Psoriasis pustulosa'), false, 'Psoriasis pustulosa', 'Biologico', (select med_id from hopes.medicines med where med.med_national_code = '711415'), '', '', '', '', current_timestamp - INTERVAL '3 DAY', current_timestamp - INTERVAL '2 DAY', 'Cambio', 'Ineficacia');

INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000005'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000005') and pd.pdg_indication = 'Psoriasis pustulosa'), true, 'Psoriasis pustulosa', 'Biologico', (select med_id from hopes.medicines med where med.med_national_code = '662258'), '', '', '', '', current_timestamp - INTERVAL '2 DAY' , null, '', '');

INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000006'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000006') and pd.pdg_indication = 'Otras psoriasis'), true, 'Otras psoriasis', 'Biologico', (select med_id from hopes.medicines med where med.med_national_code = '711415'), '', '', '', '', current_timestamp, null, '', '');

INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000006'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000006') and pd.pdg_indication = 'Psoriasis palmo-plantar'), true, 'Psoriasis palmo-plantar', 'Quimico', (select med_id from hopes.medicines med where med.med_national_code = '711415'), '', '', '', '', current_timestamp - interval '3 month', null, '', '');

INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000006'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000006') and pd.pdg_indication = 'Psoriasis palmo-plantar'), false, 'Psoriasis palmo-plantar', 'Biologico', (select med_id from hopes.medicines med where med.med_national_code = '711415'), '', '', '', '', current_timestamp, null, 'Cambio', 'Blanqueamiento');

INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000006'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000006') and pd.pdg_indication = 'Psoriasis palmo-plantar'), false, 'Psoriasis palmo-plantar', 'Biologico', (select med_id from hopes.medicines med where med.med_national_code = '662258'), '', '', '', '', current_timestamp - interval '5 month', current_timestamp - interval '3 month', 'Cambio', 'Blanqueamiento');

INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000006'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000006') and pd.pdg_indication = 'Psoriasis palmo-plantar'), false, 'Psoriasis palmo-plantar', 'Biologico', (select med_id from hopes.medicines med where med.med_national_code = '711415'), '', '', '', '', current_timestamp - interval '1 year' , current_timestamp - interval '5 month', 'Cambio', 'Blanqueamiento');

INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000007'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000007') and pd.pdg_indication = 'Psoriasis En Placas'), false, 'Psoriasis En Placas', 'Quimico', (select med_id from hopes.medicines med where med.med_national_code = '662258'), '', '', '', '', current_timestamp  - interval '1 year' , current_timestamp - interval '5 month', '', '');

INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000007'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000007') and pd.pdg_indication = 'Psoriasis En Placas'), true, 'Psoriasis En Placas', 'Tópico', (select med_id from hopes.medicines med where med.med_national_code = '662258'), '', '', '', '', current_timestamp - interval '5 month', null, '', '') ;

INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000008'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000008') and pd.pdg_indication = 'Psoriasis palmo-plantar'), true, 'Psoriasis palmo-plantar', 'Quimico', (select med_id from hopes.medicines med where med.med_national_code = '711415'), '', '', '', '', current_timestamp, null, 'Suspension', 'Blanqueamiento');

INSERT INTO hopes.patients_treatments
(ptr_pac_id, ptr_pdg_id, ptr_active, ptr_indication, ptr_type, ptr_med_id, ptr_dose, ptr_master_formula, ptr_master_formula_dose, ptr_regimen, ptr_init_date, ptr_final_date, ptr_end_cause, ptr_reason)
VALUES((select pac_id from hopes.patients where pac_nhc = 'NHC0000008'), (select pdg_id from hopes.patients_diagnoses pd, hopes.patients pt where pt.pac_id=pd.pdg_pac_id and pt.pac_id = (select p.pac_id from hopes.patients p where pac_nhc = 'NHC0000008') and pd.pdg_indication = 'Psoriasis palmo-plantar'), false, 'Psoriasis palmo-plantar', 'Quimico', (select med_id from hopes.medicines med where med.med_national_code = '711415'), '', '', '', '', current_timestamp - INTERVAL '6 DAY', current_timestamp, 'Cambio', 'Blanqueamiento');
