--update administrador a administrador dermatología
update hopes.roles set rol_name = 'Administrador Dermatología' where rol_name = 'Administrador';

-- Borrar el vínculo del servicio Farmacia
UPDATE hopes.roles r
SET rol_srv_id= null,
rol_pth_id= null
WHERE r.rol_srv_id in (select s.srv_id from hopes.services s where s.srv_name = 'Farmacia');


INSERT INTO hopes.pathologies (pth_name, pth_description)  values ( 'Fix Sin patología','Fix Sin patología');

update hopes.services_pathologies  set srp_pth_id = (select pth_id from hopes.pathologies p where pth_name = 'Fix Sin patología') where srp_pth_id  = (select pth_id from hopes.pathologies p where pth_name = 'Sin patología');

update hopes.roles set rol_pth_id  = (select pth_id from hopes.pathologies p where pth_name = 'Fix Sin patología') where rol_pth_id  = (select pth_id from hopes.pathologies p where pth_name = 'Sin patología');

delete from hopes.services_pathologies sph
where srp_srv_id  = ( select s2.srv_id from hopes.services s2 where s2.srv_name = 'Farmacia');

delete from hopes.services s
where s.srv_id = ( select s2.srv_id from hopes.services s2 where s2.srv_name = 'Farmacia');

INSERT INTO hopes.services (srv_name, srv_description) VALUES('Farmacia', 'Servicio de farmacia');

delete from hopes.pathologies p
where p.pth_id = (select p2.pth_id from hopes.pathologies p2 where p2.pth_name = 'Sin patología');

INSERT INTO hopes.pathologies (pth_name, pth_description) VALUES('Sin patología', 'Sin patología');


-- Vincular al rol de farmacia el hospital, servicio y patologia

UPDATE hopes.roles r
SET rol_hos_id= (select h.hos_id from hopes.hospitals h where h.hos_name = 'Hopes - Servicios de Salud'),
rol_srv_id= (select s.srv_id from hopes.services s where s.srv_name = 'Farmacia'),
rol_pth_id= (select p.pth_id from hopes.pathologies p where p.pth_name = 'Sin patología')
WHERE r.rol_name ='Farmacéutico';

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
VALUES('Farmacia', 'Farmacia', true, 5, (select s.sec_id from hopes.sections s where s.sec_section_root is null), '', '/hopes/pharmacy', false, true);



-- RELACIÓN SECCIONES-ROLES
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
VALUES((select s.sec_id from hopes.sections s where s.sec_title = 'Farmacia'),
(select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
VALUES((select s.sec_id from hopes.sections s where s.sec_title = 'Farmacia'),
(select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
VALUES((select s.sec_id from hopes.sections s where s.sec_title = 'Farmacia'),
(select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Farmacéutico')));

update hopes.services_pathologies  set srp_pth_id = (select pth_id from hopes.pathologies p where pth_name = 'Sin patología') where srp_pth_id  = (select pth_id from hopes.pathologies p where pth_name = 'Fix Sin patología');

update hopes.roles set rol_pth_id  = (select pth_id from hopes.pathologies p where pth_name = 'Sin patología') where rol_pth_id  = (select pth_id from hopes.pathologies p where pth_name = 'Fix Sin patología');

delete from hopes.pathologies where pth_name = 'Fix Sin patología';



