-- INITIALIZE A DATABASE --

-- Configuration Initial - Hospitals
INSERT INTO hopes.hospitals (hos_id, hos_name) VALUES (nextval('hopes.hospitals_hos_id_seq'), 'Hopes - Servicios de Salud');
INSERT INTO hopes.hospitals (hos_id, hos_name) VALUES (nextval('hopes.hospitals_hos_id_seq'), 'Hospital Perpetuo Confinamiento');

-- Configuration Initial - Pathologies
INSERT INTO hopes.pathologies (pth_id, pth_name, pth_description) VALUES (nextval('hopes.pathologies_pth_id_seq'), 'Dermatología', 'Patologia Dermatología');
INSERT INTO hopes.pathologies (pth_id, pth_name, pth_description) VALUES (nextval('hopes.pathologies_pth_id_seq'), 'Reumatología', 'Patologia Reumatología');
INSERT INTO hopes.pathologies (pth_id, pth_name, pth_description) VALUES (nextval('hopes.pathologies_pth_id_seq'), 'VIH', 'Patologia VIH');

