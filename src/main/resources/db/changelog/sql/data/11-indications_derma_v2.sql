
delete from hopes.patients_diagnoses pt;
delete from hopes.indications i;

INSERT INTO hopes.indications (ind_pth_id, ind_description)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'psoriasisPlacas');
INSERT INTO hopes.indications (ind_pth_id, ind_description)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'psoriasisPalmoplantar');
INSERT INTO hopes.indications (ind_pth_id, ind_description)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'eritrodermia');
INSERT INTO hopes.indications (ind_pth_id, ind_description)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'psoriasisPustulosa');
INSERT INTO hopes.indications (ind_pth_id, ind_description)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'atopicDermatitis');
INSERT INTO hopes.indications (ind_pth_id, ind_description)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'chronicUrticaria');
INSERT INTO hopes.indications (ind_pth_id, ind_description)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'hidradenitisSuppurativa');
INSERT INTO hopes.indications (ind_pth_id, ind_description)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'basalCarcinoma');
INSERT INTO hopes.indications (ind_pth_id, ind_description)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'squamousCarcinoma');
INSERT INTO hopes.indications (ind_pth_id, ind_description)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'melanoma');
INSERT INTO hopes.indications (ind_pth_id, ind_description)
VALUES((select p.pth_id from hopes.pathologies p where p.pth_name = 'Dermatología') ,
'others');
