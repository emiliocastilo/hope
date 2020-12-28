
delete from hopes.patients_diagnoses pt;
delete from hopes.indications i;

INSERT INTO hopes.indications (ind_pth_id, ind_description, ind_code)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'psoriasisPlacas', 'psoriasisPlacas');
INSERT INTO hopes.indications (ind_pth_id, ind_description, ind_code)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'psoriasisPalmoplantar', 'psoriasisPalmoplantar');
INSERT INTO hopes.indications (ind_pth_id, ind_description, ind_code)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'eritrodermia','eritrodermia');
INSERT INTO hopes.indications (ind_pth_id, ind_description, ind_code)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'psoriasisPustulosa', 'psoriasisPustulosa');
INSERT INTO hopes.indications (ind_pth_id, ind_description, ind_code)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'atopicDermatitis', 'atopicDermatitis');
INSERT INTO hopes.indications (ind_pth_id, ind_description, ind_code)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'chronicUrticaria', 'chronicUrticaria');
INSERT INTO hopes.indications (ind_pth_id, ind_description, ind_code)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'hidradenitisSuppurativa', 'hidradenitisSuppurativa');
INSERT INTO hopes.indications (ind_pth_id, ind_description, ind_code)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'basalCarcinoma', 'basalCarcinoma');
INSERT INTO hopes.indications (ind_pth_id, ind_description, ind_code)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'squamousCarcinoma', 'squamousCarcinoma');
INSERT INTO hopes.indications (ind_pth_id, ind_description, ind_code)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'melanoma', 'melanoma');
INSERT INTO hopes.indications (ind_pth_id, ind_description, ind_code)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'others', 'others');
