-- INITIALIZE A DATABASE --

INSERT INTO public.hospitals (hos_id, hos_name) VALUES (nextval('public.hospitals_hos_id_seq'), 'Hospital De la casa');
INSERT INTO public.pathologies (pth_id, pth_name, pth_description) VALUES (nextval('public.pathologies_pth_id_seq'), 'Dermatología', 'Patologia Dermatología');

INSERT INTO public.patients(
	pac_id, pac_hos_id, pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date)
	VALUES (nextval('public.patients_pac_id_seq'), 1, 'Paciente', 'Numero', 'Uno',
	    'MDMD610604911012', '1080775698', '94773462F',
	    'Avenida de La alegría de la huerta, 24, Segovia, Segovia, España CP: 28080', '+34668795263', 'patient.numero.uno@hopes.com',
	    'F', current_timestamp);

INSERT INTO public.patients_pathologies(
	pcp_id, pcp_pac_id, pcp_pth_id)
	VALUES (nextval('public.patients_pathologies_pcp_id_seq'),1, 1);

INSERT INTO public.patients(
	pac_id, pac_hos_id, pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date)
	VALUES (nextval('public.patients_pac_id_seq'), 1, 'Paciente', 'Numero', 'Dos',
	    'MDMD613604911012', '1280775698', '95773462F',
	    'Calle Salamanca, 2, Albacete, Sc, España CP: 28080', '+34668395263', 'patient.numero.dos@hopes.com',
	    'M', timestamp'1902-12-31 12:25:50');

INSERT INTO public.patients_pathologies(
	pcp_id, pcp_pac_id, pcp_pth_id)
	VALUES (nextval('public.patients_pathologies_pcp_id_seq'),2, 1);

INSERT INTO public.patients(
	pac_id, pac_hos_id, pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date)
	VALUES (nextval('public.patients_pac_id_seq'), 1, 'Seiya', 'De', 'Pegaso',
	    'MDMD310604911012', '0080775698', '14773462F',
	    'Partenon de Atenas, 23, Casa 11 CP: 28080', '+34668795263', 'seiya.de.pegaso@hopes.com',
	    'M', timestamp'2009-12-11 12:25:50');

INSERT INTO public.patients_pathologies(
	pcp_id, pcp_pac_id, pcp_pth_id)
	VALUES (nextval('public.patients_pathologies_pcp_id_seq'),3, 1);

INSERT INTO public.patients(
	pac_id, pac_hos_id, pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date)
	VALUES (nextval('public.patients_pac_id_seq'), 1, 'Sun', 'de', 'Andromeda',
	    'MDMD610604911013', '1080775688', '94773562F',
	    'Isla de Andromeda, 24', '+34668595263', 'sun.de.andromeda@hopes.com',
	    'F', timestamp'2009-12-31 12:25:50');

INSERT INTO public.patients_pathologies(
	pcp_id, pcp_pac_id, pcp_pth_id)
	VALUES (nextval('public.patients_pathologies_pcp_id_seq'),4, 1);

INSERT INTO public.patients(
	pac_id, pac_hos_id, pac_name, pac_first_surname, pac_last_surname, pac_nhc, pac_health_card, pac_dni, pac_address, pac_phone, pac_email, pac_gender_code, pac_birth_date)
	VALUES (nextval('public.patients_pac_id_seq'), 2, 'Sun', 'de', 'Andromeda',
	    'MDMD610604913013', '1080475688', '94773562F',
	    'Isla de Andromeda, 24', '+34668595263', 'sun.de.andromeda@hopes.com',
	    'F', timestamp'2009-12-31 12:25:50');

INSERT INTO public.patients_pathologies(
	pcp_id, pcp_pac_id, pcp_pth_id)
	VALUES (nextval('public.patients_pathologies_pcp_id_seq'),5, 2);