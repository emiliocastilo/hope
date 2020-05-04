----------------------------------------------------- CONFIGURACIÓN ----------------------------------------------------
-- Section: Sections
INSERT INTO sections(
	sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url)
VALUES (nextval('sections_sec_id_seq'),
        'SECTIONS',
        'Secciones',
        true,
        (select max (sec_order)+1 from sections where sec_section_root = (select sec_id from sections where sec_title ='HOPES' LIMIT 1)),
        (select sec_id from sections where sec_title ='HOPES' LIMIT 1),
        'assets/img/modules/secciones.png',
        '#');

-- Role_Admin
INSERT INTO sections_roles(scr_id, scr_section_id, scr_role_id)
VALUES (nextval('sections_roles_scr_id_seq'),
		(select sec_id from sections where sec_title ='SECTIONS' LIMIT 1),
		(select rol_id from roles where rol_name = 'ROLE_ADMIN' LIMIT 1));
