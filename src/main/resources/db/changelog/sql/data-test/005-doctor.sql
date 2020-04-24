WITH userId AS (
    INSERT INTO public.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, date_created, date_updated)
        VALUES (nextval('public.users_roles_uro_id_seq'),
                'antonio.garcia',
                '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.',
                'antonio.garcia@plexus.es', 1, current_timestamp, current_timestamp) RETURNING usr_id
),
     doctor_one AS (
         INSERT
             INTO public.doctors (dct_id, dct_name, dct_surname, dct_phone, dct_dni, dct_college_number, dct_service_id,
                                  dct_active, dct_user_id, date_created, date_updated)
                 VALUES (nextval('public.doctors_dct_id_seq'), 'Antonio', 'Garcia', '123456789', '12345678Z', 123456, 1,
                         true,
                         (SELECT usr_id FROM userId), current_timestamp, current_timestamp))
INSERT
INTO public.users_ROLES (uro_id, uro_user_id, uro_rol_id)
VALUES (nextval('public.users_roles_uro_id_seq'), (SELECT usr_id FROM userId), 7);

WITH userId AS (
    INSERT INTO public.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, date_created,
                              date_updated
        )
        VALUES (nextval('public.users_roles_uro_id_seq'),
                'jose.martinez',
                '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.',
                'jose.martinez@plexus.es', 1, current_timestamp, current_timestamp) RETURNING usr_id
),
     doctor_two AS (
         INSERT
             INTO public.doctors (dct_id, dct_name, dct_surname, dct_phone, dct_dni, dct_college_number, dct_service_id,
                                  dct_active, dct_user_id, date_created, date_updated)
                 VALUES (nextval('public.doctors_dct_id_seq'), 'Jose', 'Martinez', '123456789', '12345678Z', 123456, 1,
                         true,
                         (SELECT usr_id FROM userId), current_timestamp, current_timestamp))
INSERT
INTO public.users_ROLES (uro_id, uro_user_id, uro_rol_id)
VALUES (nextval('public.users_roles_uro_id_seq'), (SELECT usr_id FROM userId), 7);

WITH userId AS (
    INSERT INTO public.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, date_created,
                              date_updated
        )
        VALUES (nextval('public.users_roles_uro_id_seq'),
                'francisco.lopez',
                '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.',
                'francisco.lopez@plexus.es', 1, current_timestamp, current_timestamp) RETURNING usr_id
),
     doctor_three AS (
         INSERT
             INTO public.doctors (dct_id, dct_name, dct_surname, dct_phone, dct_dni, dct_college_number, dct_service_id,
                                  dct_active, dct_user_id, date_created, date_updated)
                 VALUES (nextval('public.doctors_dct_id_seq'), 'Francisco', 'Lopez', '123456789', '12345678Z', 123456,
                         1, true,
                         (SELECT usr_id FROM userId), current_timestamp, current_timestamp))
INSERT
INTO public.users_ROLES (uro_id, uro_user_id, uro_rol_id)
VALUES (nextval('public.users_roles_uro_id_seq'), (SELECT usr_id FROM userId), 7);

WITH userId AS (
    INSERT INTO public.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, date_created,
                              date_updated
        )
        VALUES (nextval('public.users_roles_uro_id_seq'),
                'juan.sanchez',
                '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.',
                'juan.sanchez@plexus.es', 1, current_timestamp, current_timestamp) RETURNING usr_id
),
     doctor_four AS (
         INSERT
             INTO public.doctors (dct_id, dct_name, dct_surname, dct_phone, dct_dni, dct_college_number, dct_service_id,
                                  dct_active, dct_user_id, date_created, date_updated)
                 VALUES (nextval('public.doctors_dct_id_seq'), 'Juan', 'Sanchez', '123456789', '12345678Z', 123456, 1,
                         true,
                         (SELECT usr_id FROM userId), current_timestamp, current_timestamp))
INSERT
INTO public.users_ROLES (uro_id, uro_user_id, uro_rol_id)
VALUES (nextval('public.users_roles_uro_id_seq'), (SELECT usr_id FROM userId), 8);

WITH userId AS (
    INSERT INTO public.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, date_created,
                              date_updated
        )
        VALUES (nextval('public.users_roles_uro_id_seq'),
                'manuel.gonzales',
                '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.',
                'manuel.gonzales@plexus.es', 1, current_timestamp, current_timestamp) RETURNING usr_id
),
     doctor_five AS (
         INSERT
             INTO public.doctors (dct_id, dct_name, dct_surname, dct_phone, dct_dni, dct_college_number, dct_service_id,
                                  dct_active, dct_user_id, date_created, date_updated)
                 VALUES (nextval('public.doctors_dct_id_seq'), 'Manuel', 'Gonzales', '123456789', '12345678Z', 123456,
                         1, true,
                         (SELECT usr_id FROM userId), current_timestamp, current_timestamp))
INSERT
INTO public.users_ROLES (uro_id, uro_user_id, uro_rol_id)
VALUES (nextval('public.users_roles_uro_id_seq'), (SELECT usr_id FROM userId), 8);

WITH userId AS (
    INSERT INTO public.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, date_created,
                              date_updated
        )
        VALUES (nextval('public.users_roles_uro_id_seq'),
                'maria.garcia',
                '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.',
                'maria.garcia@plexus.es', 1, current_timestamp, current_timestamp) RETURNING usr_id
),
     doctor_six AS (
         INSERT
             INTO public.doctors (dct_id, dct_name, dct_surname, dct_phone, dct_dni, dct_college_number, dct_service_id,
                                  dct_active, dct_user_id, date_created, date_updated)
                 VALUES (nextval('public.doctors_dct_id_seq'), 'Maria', 'Garcia', '123456789', '12345678Z', 123456, 1,
                         true,
                         (SELECT usr_id FROM userId), current_timestamp, current_timestamp))
INSERT
INTO public.users_ROLES (uro_id, uro_user_id, uro_rol_id)
VALUES (nextval('public.users_roles_uro_id_seq'), (SELECT usr_id FROM userId), 8);

WITH userId AS (
    INSERT INTO public.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, date_created,
                              date_updated
        )
        VALUES (nextval('public.users_roles_uro_id_seq'),
                'mariacarmen.martinez',
                '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.',
                'mariacarmen.martinez@plexus.es', 1, current_timestamp, current_timestamp) RETURNING usr_id
),
     doctor_seven AS (
         INSERT
             INTO public.doctors (dct_id, dct_name, dct_surname, dct_phone, dct_dni, dct_college_number, dct_service_id,
                                  dct_active, dct_user_id, date_created, date_updated)
                 VALUES (nextval('public.doctors_dct_id_seq'), 'Maria Carmen', 'Martinez', '123456789', '12345678Z',
                         123456, 1, true,
                         (SELECT usr_id FROM userId), current_timestamp, current_timestamp))
INSERT
INTO public.users_ROLES (uro_id, uro_user_id, uro_rol_id)
VALUES (nextval('public.users_roles_uro_id_seq'), (SELECT usr_id FROM userId), 7);

WITH userId AS (
    INSERT INTO public.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, date_created,
                              date_updated
        )
        VALUES (nextval('public.users_roles_uro_id_seq'),
                'joseantonio.rodriguez',
                '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.',
                'joseantonio.rodriguez@plexus.es', 1, current_timestamp, current_timestamp) RETURNING usr_id
),
     doctor_eight AS (
         INSERT
             INTO public.doctors (dct_id, dct_name, dct_surname, dct_phone, dct_dni, dct_college_number, dct_service_id,
                                  dct_active, dct_user_id, date_created, date_updated)
                 VALUES (nextval('public.doctors_dct_id_seq'), 'Jose Antonio', 'Rodriguez', '123456789', '12345678Z',
                         123456, 1, true,
                         (SELECT usr_id FROM userId), current_timestamp, current_timestamp))
INSERT
INTO public.users_ROLES (uro_id, uro_user_id, uro_rol_id)
VALUES (nextval('public.users_roles_uro_id_seq'), (SELECT usr_id FROM userId), 7);

WITH userId AS (
    INSERT INTO public.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, date_created,
                              date_updated
        )
        VALUES (nextval('public.users_roles_uro_id_seq'),
                'carlos.ruiz',
                '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.',
                'carlos.ruiz@plexus.es', 1, current_timestamp, current_timestamp) RETURNING usr_id
),
     doctor_nine AS (
         INSERT
             INTO public.doctors (dct_id, dct_name, dct_surname, dct_phone, dct_dni, dct_college_number, dct_service_id,
                                  dct_active, dct_user_id, date_created, date_updated)
                 VALUES (nextval('public.doctors_dct_id_seq'), 'Carlos', 'Ruiz', '123456789', '12345678Z', 123456, 1,
                         true,
                         (SELECT usr_id FROM userId), current_timestamp, current_timestamp))
INSERT
INTO public.users_ROLES (uro_id, uro_user_id, uro_rol_id)
VALUES (nextval('public.users_roles_uro_id_seq'), (SELECT usr_id FROM userId), 7);

WITH userId AS (
    INSERT INTO public.users (usr_id, usr_name, usr_password, usr_email, usr_hos_id, date_created,
                              date_updated
        )
        VALUES (nextval('public.users_roles_uro_id_seq'),
                'mariaangeles.garcia',
                '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.',
                'mariaangeles.garcia@plexus.es', 1, current_timestamp, current_timestamp) RETURNING usr_id
),
     doctor_ten AS (
         INSERT
             INTO public.doctors (dct_id, dct_name, dct_surname, dct_phone, dct_dni, dct_college_number, dct_service_id,
                                  dct_active, dct_user_id, date_created, date_updated)
                 VALUES (nextval('public.doctors_dct_id_seq'), 'Maria Angeles', 'Garcia', '123456789', '12345678Z',
                         123456, 1, true,
                         (SELECT usr_id FROM userId), current_timestamp, current_timestamp))
INSERT
INTO public.users_ROLES (uro_id, uro_user_id, uro_rol_id)
VALUES (nextval('public.users_roles_uro_id_seq'), (SELECT usr_id FROM userId), 7);