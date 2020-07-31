WITH serviceOne AS (
         INSERT INTO hopes.services (srv_id, srv_name, srv_description) VALUES (nextval('hopes.services_srv_id_seq'), 'Dermatología', 'Servicio de dermatologia')
             RETURNING srv_id
     ),
     serviceTwo AS (
         INSERT INTO hopes.services (srv_id, srv_name, srv_description) VALUES (nextval('hopes.services_srv_id_seq'), 'Reumatología', 'Servicio de Reumatología')
             RETURNING srv_id
     ),
     serviceThree AS (
         INSERT INTO hopes.services (srv_id, srv_name, srv_description) VALUES (nextval('hopes.services_srv_id_seq'), 'Cardiología', 'Servicio de cardiología')
             RETURNING srv_id
     ),
     serviceFour AS (
         INSERT INTO hopes.services (srv_id, srv_name, srv_description) VALUES (nextval('hopes.services_srv_id_seq'), 'Cuidados paliativos', 'Servicio de Cuidados paliativos')
             RETURNING srv_id
     ),
     hospital_services_one AS (
         INSERT INTO hopes.hospitals_services (hss_id, hss_hos_id, hss_srv_id)
             VALUES (nextval('hopes.hospitals_services_hss_id_seq'), (SELECT hos_id FROM hopes.hospitals WHERE hos_name = 'Hopes - Servicios de Salud'),
                     (SELECT srv_id FROM serviceOne))
     ),
     hospital_services_two AS (
         INSERT INTO hopes.hospitals_services (hss_id, hss_hos_id, hss_srv_id)
             VALUES (nextval('hopes.hospitals_services_hss_id_seq'), (SELECT hos_id FROM hopes.hospitals WHERE hos_name = 'Hopes - Servicios de Salud'),
                     (SELECT srv_id FROM serviceTwo))
     ),
     hospital_services_three AS (
         INSERT INTO hopes.hospitals_services (hss_id, hss_hos_id, hss_srv_id)
             VALUES (nextval('hopes.hospitals_services_hss_id_seq'), (SELECT hos_id FROM hopes.hospitals WHERE hos_name = 'Hopes - Servicios de Salud'),
                     (SELECT srv_id FROM serviceThree))
     ) INSERT INTO hopes.hospitals_services (hss_id, hss_hos_id, hss_srv_id)
     VALUES (nextval('hopes.hospitals_services_hss_id_seq'), (SELECT hos_id FROM hopes.hospitals WHERE hos_name = 'Hopes - Servicios de Salud'),
                     (SELECT srv_id FROM serviceFour));