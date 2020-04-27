WITH hospitalOne AS (
    INSERT INTO public.hospitals (hos_id, hos_name) VALUES (nextval('public.hospitals_hos_id_seq'),
                                                            'Complejo Universitario La Paz')
        RETURNING hos_id
),
     hospitalTwo AS (
         INSERT INTO public.hospitals (hos_id, hos_name) VALUES (nextval('public.hospitals_hos_id_seq'),
                                                                 'Hospital Universitario Fundación Jiménez Díaz')
             RETURNING hos_id
     ),
     hospitalThree AS (
         INSERT INTO public.hospitals (hos_id, hos_name) VALUES (nextval('public.hospitals_hos_id_seq'),
                                                                 'Hospital Clínico San Carlos')
             RETURNING hos_id
     ),
     hospitalFour AS (
         INSERT INTO public.hospitals (hos_id, hos_name) VALUES (nextval('public.hospitals_hos_id_seq'),
                                                                 'Hospital Ramón y Cajal')
             RETURNING hos_id
     ),
     hospitalFive AS (
         INSERT INTO public.hospitals (hos_id, hos_name) VALUES (nextval('public.hospitals_hos_id_seq'),
                                                                 'Hospital Universitario Rey Juan Carlos')
             RETURNING hos_id
     ),
     serviceOne AS (
         INSERT INTO public.services (srv_id, srv_name, srv_description) VALUES (nextval('public.services_srv_id_seq'), 'Servicio 1', 'Servicio 1')
             RETURNING srv_id
     ),
     serviceTwo AS (
         INSERT INTO public.services (srv_id, srv_name, srv_description) VALUES (nextval('public.services_srv_id_seq'), 'Servicio 2', 'Servicio 2')
             RETURNING srv_id
     ),
     serviceThree AS (
         INSERT INTO public.services (srv_id, srv_name, srv_description) VALUES (nextval('public.services_srv_id_seq'), 'Servicio 3', 'Servicio 3')
             RETURNING srv_id
     ),
     serviceFour AS (
         INSERT INTO public.services (srv_id, srv_name, srv_description) VALUES (nextval('public.services_srv_id_seq'), 'Servicio 4', 'Servicio 4')
             RETURNING srv_id
     ),
     hospital_services_one AS (
         INSERT INTO public.hospitals_services (hss_id, hss_hos_id, hss_srv_id)
             VALUES (nextval('public.hospitals_services_hss_id_seq'), (SELECT hos_id FROM hospitalOne),
                     (SELECT srv_id FROM serviceOne))
     ),
     hospital_services_two AS (
         INSERT INTO public.hospitals_services (hss_id, hss_hos_id, hss_srv_id)
             VALUES (nextval('public.hospitals_services_hss_id_seq'), (SELECT hos_id FROM hospitalOne),
                     (SELECT srv_id FROM serviceTwo))
     ),
     hospital_services_three AS (
         INSERT INTO public.hospitals_services (hss_id, hss_hos_id, hss_srv_id)
             VALUES (nextval('public.hospitals_services_hss_id_seq'), (SELECT hos_id FROM hospitalTwo),
                     (SELECT srv_id FROM serviceOne))
     ),
     hospital_services_four AS (
         INSERT INTO public.hospitals_services (hss_id, hss_hos_id, hss_srv_id)
             VALUES (nextval('public.hospitals_services_hss_id_seq'), (SELECT hos_id FROM hospitalThree),
                     (SELECT srv_id FROM serviceThree))
     ),
     hospital_services_five AS (
         INSERT INTO public.hospitals_services (hss_id, hss_hos_id, hss_srv_id)
             VALUES (nextval('public.hospitals_services_hss_id_seq'), (SELECT hos_id FROM hospitalFour),
                     (SELECT srv_id FROM serviceFour))
     ),
     hospital_services_six AS (
         INSERT INTO public.hospitals_services (hss_id, hss_hos_id, hss_srv_id)
             VALUES (nextval('public.hospitals_services_hss_id_seq'), (SELECT hos_id FROM hospitalFive),
                     (SELECT srv_id FROM serviceOne))
     )
INSERT
INTO public.hospitals_services (hss_id, hss_hos_id, hss_srv_id)
VALUES (nextval('public.hospitals_services_hss_id_seq'), (SELECT hos_id FROM hospitalFive),
        (SELECT srv_id FROM serviceThree));