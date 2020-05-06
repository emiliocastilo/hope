----------------------------------------------------- CONFIGURACIÓN ----------------------------------------------------
-- Section: Sections
INSERT INTO hopes.sections(
	sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('hopes.sections_sec_id_seq'),
        'SECTIONS',
        'Secciones',
        true,
        (select max (sec_order)+1 from hopes.sections where sec_section_root = (select sec_id from hopes.sections where sec_title ='HOPES' LIMIT 1)),
        (select sec_id from hopes.sections where sec_title ='HOPES' LIMIT 1),
        'assets/img/modules/secciones.png',
        '#');

-- Role_Admin
INSERT INTO hopes.sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('hopes.sections_roles_scr_id_seq'),
		(select sec_id from hopes.sections where sec_title ='SECTIONS' LIMIT 1),
		(select rol_id from hopes.roles where rol_name = 'ROLE_ADMIN' LIMIT 1));
