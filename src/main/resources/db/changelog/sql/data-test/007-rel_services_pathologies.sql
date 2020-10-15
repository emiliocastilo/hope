INSERT INTO hopes.services_pathologies (srp_srv_id, srp_pth_id)
    VALUES ( (select srv_id from hopes.services where srv_name = 'Cuidados paliativos'), (select pth_id from hopes.pathologies where pth_name = 'Dermatología'));
INSERT INTO hopes.services_pathologies (srp_srv_id, srp_pth_id)
    VALUES ( (select srv_id from hopes.services where srv_name = 'Cuidados paliativos'), (select pth_id from hopes.pathologies where pth_name = 'Reumatología'));
INSERT INTO hopes.services_pathologies (srp_srv_id, srp_pth_id)
    VALUES ( (select srv_id from hopes.services where srv_name = 'Cuidados paliativos'), (select pth_id from hopes.pathologies where pth_name = 'VIH'));