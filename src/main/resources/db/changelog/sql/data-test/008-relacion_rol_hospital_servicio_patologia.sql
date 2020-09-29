
UPDATE hopes.roles
SET rol_hos_id = 1,
rol_srv_id = (select hss_srv_id from hopes.hospitals_services where hss_hos_id = 1 LIMIT 1),
rol_pth_id = (select srp_pth_id from hopes.services_pathologies where srp_srv_id = (select hss_srv_id from hopes.hospitals_services where hss_hos_id = 1 LIMIT 1) LIMIT 1)
WHERE rol_name = 'Administrador';
