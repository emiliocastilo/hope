-- Eliminar seccion Farmacia de VIH con sus roles relacionados
delete from hopes.sections_roles where scr_id in (
	select sr.scr_id from hopes.sections_roles sr where sr.scr_section_id in (
		select s.sec_id from hopes.sections s
		join hopes.sections_roles sr on sr.scr_section_id = s.sec_id
		join hopes.roles r on r.rol_id = sr.scr_role_id
		and s.sec_title ='Farmacia'
		group by s.sec_id
	)
);

delete from hopes.sections where sec_id in (
	select s.sec_id from hopes.sections s
	left join hopes.sections_roles sr on sr.scr_section_id = s.sec_id
	where sr.scr_id is null and s.sec_title='Farmacia'
	group by s.sec_id
);

-- SECCION FARMACIA
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Farmacia', 'Farmacia', true, 5, (select s.sec_id from hopes.sections s where s.sec_section_root is null), '', '#', false, true);

-- RELACIÓN SECCIONES-ROLES
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
VALUES((select s.sec_id from hopes.sections s where s.sec_title = 'Farmacia'),
(select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
VALUES((select s.sec_id from hopes.sections s where s.sec_title = 'Farmacia'),
(select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
VALUES((select s.sec_id from hopes.sections s where s.sec_title = 'Farmacia'),
(select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Farmacéutico')));