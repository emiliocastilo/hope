----------------------------------------------------- CONFIGURACIÓN ----------------------------------------------------
-- Section: Medicines
INSERT INTO sections(
	sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('sections_sec_id_seq'),
        'MEDICINES',
        'Medicamentos',
        true,
        (select max (sec_order)+1 from sections where sec_section_root = (select sec_id from sections where sec_title ='HOPES' LIMIT 1)),
        (select sec_id from sections where sec_title ='HOPES' LIMIT 1),
        'assets/img/modules/medicamentos.png',
        '#');

-- Role_Admin
INSERT INTO sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('sections_roles_scr_id_seq'),
		(select sec_id from sections where sec_title ='DISPENSATIONS' LIMIT 1),
		(select rol_id from roles where rol_name = 'ROLE_ADMIN' LIMIT 1));

-- Role_Pharmacist
INSERT INTO sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('sections_roles_scr_id_seq'),
	  	(select sec_id from sections where sec_title ='DISPENSATIONS' LIMIT 1),
	  	(select rol_id from roles where rol_name = 'ROLE_MANAGER' LIMIT 1));

---------------------------------------------------------------------- CATÁLOGOS ------------------------------------
INSERT INTO hopes.recommendations VALUES (1,'Recomendacion01');
INSERT INTO hopes.recommendations VALUES (2,'Recomendacion02');
INSERT INTO hopes.recommendations VALUES (3,'Recomendacion03');
INSERT INTO hopes.recommendations VALUES (4,'Recomendacion04');
