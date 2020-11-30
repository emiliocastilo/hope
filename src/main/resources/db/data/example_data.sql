-- Pacientes
INSERT INTO hopes.patients
(pac_hos_id, pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date, pac_city, pac_province, pac_cp, pac_origin_country)
VALUES( 1, 'JOAN', 'GUERRERO', 'FREIRE', 'NHC02497366', 'HC02497366', '02497366A', null, null, null, 'M', '1983-01-31 00:00:00.000', NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients
(pac_hos_id, pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date, pac_city, pac_province, pac_cp, pac_origin_country)
VALUES( 1, 'JAVIER', 'VILLANUEVA', 'VALLEJO', 'NHC52706819', 'HC52706819', '52706819L', null, null, null, 'M', '1983-10-18 00:00:00.000', NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients
(pac_hos_id, pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date, pac_city, pac_province, pac_cp, pac_origin_country)
VALUES( 1, 'MARIO', 'PADILLA', 'ORDOÑEZ', 'NHC35199021', 'HC35199021', '35199021M', null, null, null, 'M', '1944-03-25 00:00:00.000', NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients
(pac_hos_id, pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date, pac_city, pac_province, pac_cp, pac_origin_country)
VALUES( 1, 'ALFONSO', 'FRANCO', 'GALVAN', 'NHC92697325', 'HC92697325', '92697325B', null, null, null, 'M', '1991-11-11 00:00:00.000', NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients
(pac_hos_id, pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date, pac_city, pac_province, pac_cp, pac_origin_country)
VALUES( 1, 'ALICIA', 'SANTAMARIA', 'PEREZ', 'NHC24081311', 'HC24081311', '24081311N', null, null, null, 'F', '1961-05-02 00:00:00.000', NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients
(pac_hos_id, pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date, pac_city, pac_province, pac_cp, pac_origin_country)
VALUES( 1, 'ISIDORO', 'GOMEZ', 'CARRERA', 'NHC32304348', 'HC32304348', '32304348C', null, null, null, 'M', '1997-06-02 00:00:00.000', NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients
(pac_hos_id, pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date, pac_city, pac_province, pac_cp, pac_origin_country)
VALUES( 1, 'MIGUEL', 'SALVADOR', 'CARDENAS', 'NHC89774533', 'HC89774533', '89774533J', null, null, null, 'M', '1992-11-03 00:00:00.000', NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients
(pac_hos_id, pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date, pac_city, pac_province, pac_cp, pac_origin_country)
VALUES( 1, 'SEBASTIAN', 'VARGAS', 'PACHECO', 'NHC46180494', 'HC46180494', '46180494J', null, null, null, 'M', '1995-07-22 00:00:00.000', NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients
(pac_hos_id, pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date, pac_city, pac_province, pac_cp, pac_origin_country)
VALUES( 1, 'CONSTANTINO', 'DEL VALLE', 'CASANOVA', 'NHC40487696', 'HC40487696', '40487696Z', null, null, null, 'M', '1976-01-04 00:00:00.000', NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients
(pac_hos_id, pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date, pac_city, pac_province, pac_cp, pac_origin_country)
VALUES( 1, 'JONATHAN', 'QUINTERO', 'ROCA', 'NHC14560439', 'HC14560439', '14560439J', null, null, null, 'M', '1973-12-04 00:00:00.000', NULL, NULL, NULL, NULL);

--Datos del paciente
INSERT INTO hopes.patients_data
(pdt_pac_id, pdt_weight, pdt_height, pdt_imc, pdt_pat, pdt_pas, pdt_abdominal_perimeter, pdt_body_surface, pdt_psoriatric, pdt_date)
VALUES((select pac_id FROM   hopes.patients where pac_dni = '02497366A'), 83 ,1.83,24.784, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients_data
(pdt_pac_id, pdt_weight, pdt_height, pdt_imc, pdt_pat, pdt_pas, pdt_abdominal_perimeter, pdt_body_surface, pdt_psoriatric, pdt_date)
VALUES((select pac_id FROM   hopes.patients where pac_dni = '52706819L'), 100,1.83,29.861, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients_data
(pdt_pac_id, pdt_weight, pdt_height, pdt_imc, pdt_pat, pdt_pas, pdt_abdominal_perimeter, pdt_body_surface, pdt_psoriatric, pdt_date)
VALUES((select pac_id FROM   hopes.patients where pac_dni = '35199021M'), 67,1.54,28.251, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients_data
(pdt_pac_id, pdt_weight, pdt_height, pdt_imc, pdt_pat, pdt_pas, pdt_abdominal_perimeter, pdt_body_surface, pdt_psoriatric, pdt_date)
VALUES((select pac_id FROM   hopes.patients where pac_dni = '92697325B'), 88,1.87,25.165, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients_data
(pdt_pac_id, pdt_weight, pdt_height, pdt_imc, pdt_pat, pdt_pas, pdt_abdominal_perimeter, pdt_body_surface, pdt_psoriatric, pdt_date)
VALUES((select pac_id FROM   hopes.patients where pac_dni = '24081311N'), 34.5,1.35,18.93, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients_data
(pdt_pac_id, pdt_weight, pdt_height, pdt_imc, pdt_pat, pdt_pas, pdt_abdominal_perimeter, pdt_body_surface, pdt_psoriatric, pdt_date)
VALUES((select pac_id FROM   hopes.patients where pac_dni = '32304348C'), 87,1.85,25.42, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients_data
(pdt_pac_id, pdt_weight, pdt_height, pdt_imc, pdt_pat, pdt_pas, pdt_abdominal_perimeter, pdt_body_surface, pdt_psoriatric, pdt_date)
VALUES((select pac_id FROM   hopes.patients where pac_dni = '89774533J'), 93,1.81,28.387, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients_data
(pdt_pac_id, pdt_weight, pdt_height, pdt_imc, pdt_pat, pdt_pas, pdt_abdominal_perimeter, pdt_body_surface, pdt_psoriatric, pdt_date)
VALUES((select pac_id FROM   hopes.patients where pac_dni = '46180494J'), 130,1.79,40.573, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients_data
(pdt_pac_id, pdt_weight, pdt_height, pdt_imc, pdt_pat, pdt_pas, pdt_abdominal_perimeter, pdt_body_surface, pdt_psoriatric, pdt_date)
VALUES((select pac_id FROM   hopes.patients where pac_dni = '40487696Z'), 85,1.69,29.761, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO hopes.patients_data
(pdt_pac_id, pdt_weight, pdt_height, pdt_imc, pdt_pat, pdt_pas, pdt_abdominal_perimeter, pdt_body_surface, pdt_psoriatric, pdt_date)
VALUES((select pac_id FROM   hopes.patients where pac_dni = '14560439J'), 72,1.65,26.446, NULL, NULL, NULL, NULL, NULL, NULL);


-- Patologias del paciente
INSERT INTO hopes.patients_pathologies 
            (pcp_pac_id, 
             pcp_pth_id) 
SELECT pac_id, (select pth_id from hopes.pathologies where pth_name = 'Dermatología')
 FROM   hopes.patients
 WHERE  pac_dni IN ( '02497366A', '52706819L', '35199021M', '92697325B', '24081311N', '32304348C', '89774533J', '46180494J', '40487696Z', '14560439J'); 
					 
