----------------------------------------------------------------- LOCALIZED SECTIONS -------------------------------------------------------------------------------
INSERT INTO hopes.localized_sections (lcs_id, lcs_locale, lcs_title, lcs_description, lcs_sec_id) VALUES (nextval('hopes.localized_sections_lcs_id_seq'), 'en', 'Hopes', 'Home', (select sec_id from hopes.sections where sec_title ='HOPES' LIMIT 1));
INSERT INTO hopes.localized_sections (lcs_id, lcs_locale, lcs_title, lcs_description, lcs_sec_id) VALUES (nextval('hopes.localized_sections_lcs_id_seq'), 'es', 'Hopes', 'Home', (select sec_id from hopes.sections where sec_title ='HOPES' LIMIT 1));

INSERT INTO hopes.localized_sections (lcs_id, lcs_locale, lcs_title, lcs_description, lcs_sec_id) VALUES (nextval('hopes.localized_sections_lcs_id_seq'), 'en', 'Calendar', 'Section containing the Pathology Appointment Calendar', (select sec_id from hopes.sections where sec_title ='CALENDAR' LIMIT 1));
INSERT INTO hopes.localized_sections (lcs_id, lcs_locale, lcs_title, lcs_description, lcs_sec_id) VALUES (nextval('hopes.localized_sections_lcs_id_seq'), 'es', 'Calendario', 'Sección que contiene el Calendario de citas de la patología', (select sec_id from hopes.sections where sec_title ='CALENDAR' LIMIT 1));

INSERT INTO hopes.localized_sections (lcs_id, lcs_locale, lcs_title, lcs_description, lcs_sec_id) VALUES (nextval('hopes.localized_sections_lcs_id_seq'), 'en', 'Patient', 'Section containing the complete Menu after selecting a Patient', (select sec_id from hopes.sections where sec_title ='PATIENTS' LIMIT 1));
INSERT INTO hopes.localized_sections (lcs_id, lcs_locale, lcs_title, lcs_description, lcs_sec_id) VALUES (nextval('hopes.localized_sections_lcs_id_seq'), 'es', 'Paciente', 'Sección que contiene el Menú completo tras la seleccion de un Paciente', (select sec_id from hopes.sections where sec_title ='PATIENTS' LIMIT 1));

INSERT INTO hopes.localized_sections (lcs_id, lcs_locale, lcs_title, lcs_description, lcs_sec_id) VALUES (nextval('hopes.localized_sections_lcs_id_seq'), 'en', 'Control Panel', 'Section containing the Pathology Scorecard', (select sec_id from hopes.sections where sec_title ='CONTROL_PANEL' LIMIT 1));
INSERT INTO hopes.localized_sections (lcs_id, lcs_locale, lcs_title, lcs_description, lcs_sec_id) VALUES (nextval('hopes.localized_sections_lcs_id_seq'), 'es', 'Cuadro de Mando', 'Sección que contiene el Cuadro de Mando de la patología', (select sec_id from hopes.sections where sec_title ='CONTROL_PANEL' LIMIT 1));

INSERT INTO hopes.localized_sections (lcs_id, lcs_locale, lcs_title, lcs_description, lcs_sec_id) VALUES (nextval('hopes.localized_sections_lcs_id_seq'), 'en', 'Alerts', 'Section containing Pathology Alerts', (select sec_id from hopes.sections where sec_title ='ALERTS' LIMIT 1));
INSERT INTO hopes.localized_sections (lcs_id, lcs_locale, lcs_title, lcs_description, lcs_sec_id) VALUES (nextval('hopes.localized_sections_lcs_id_seq'), 'es', 'Alertas', 'Sección que contiene las Alertas de la patología', (select sec_id from hopes.sections where sec_title ='ALERTS' LIMIT 1));

INSERT INTO hopes.localized_sections (lcs_id, lcs_locale, lcs_title, lcs_description, lcs_sec_id) VALUES (nextval('hopes.localized_sections_lcs_id_seq'), 'en', 'Dispensations', 'Section containing the dispensation charge', (select sec_id from hopes.sections where sec_title ='DISPENSATIONS' LIMIT 1));
INSERT INTO hopes.localized_sections (lcs_id, lcs_locale, lcs_title, lcs_description, lcs_sec_id) VALUES (nextval('hopes.localized_sections_lcs_id_seq'), 'es', 'Dispensaciones', 'Sección que contiene la carga de dispensaciones', (select sec_id from hopes.sections where sec_title ='DISPENSATIONS' LIMIT 1));

INSERT INTO hopes.localized_sections (lcs_id, lcs_locale, lcs_title, lcs_description, lcs_sec_id) VALUES (nextval('hopes.localized_sections_lcs_id_seq'), 'en', 'Medicines Manager', 'Section containing Medicines Management', (select sec_id from hopes.sections where sec_title ='MEDICINES' LIMIT 1));
INSERT INTO hopes.localized_sections (lcs_id, lcs_locale, lcs_title, lcs_description, lcs_sec_id) VALUES (nextval('hopes.localized_sections_lcs_id_seq'), 'es', 'Gestión de Medicamentos', 'Sección que contiene la Gestión de Medicamentos', (select sec_id from hopes.sections where sec_title ='MEDICINES' LIMIT 1));

INSERT INTO hopes.localized_sections (lcs_id, lcs_locale, lcs_title, lcs_description, lcs_sec_id) VALUES (nextval('hopes.localized_sections_lcs_id_seq'), 'en', 'Sections', 'Sections', (select sec_id from hopes.sections where sec_title ='SECTIONS' LIMIT 1));
INSERT INTO hopes.localized_sections (lcs_id, lcs_locale, lcs_title, lcs_description, lcs_sec_id) VALUES (nextval('hopes.localized_sections_lcs_id_seq'), 'es', 'Secciones', 'Secciones', (select sec_id from hopes.sections where sec_title ='SECTIONS' LIMIT 1));
----------------------------------------------------------------- LOCALIZED SECTIONS -------------------------------------------------------------------------------
