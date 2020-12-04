-- Actualizar el rol Medico VIH
UPDATE hopes.roles
SET rol_hos_id=(select hos_id from hopes.hospitals where upper(hos_name)=upper('Hopes - Servicios de Salud')),
rol_srv_id=(select srv_id from hopes.services where upper(srv_name)=upper('Cuidados paliativos')),
rol_pth_id=(select pth_id from hopes.pathologies where upper(pth_name)=upper('VIH'))
WHERE upper(rol_name)=upper('Médico VIH');

-- Insertar rol Administrador VIH
INSERT INTO hopes.roles (rol_name, rol_description, rol_code, rol_hos_id, rol_srv_id, rol_pth_id)
select 'Administrador VIH', 'Rol administrador. Tiene el máximo nivel de acceso a la aplicación con patología VIH', 'ROLE_ADMINISTRADORVIH_1HO-SEDESA_3VI',
(select hos_id from hopes.hospitals where upper(hos_name)=upper('Hopes - Servicios de Salud')),
(select srv_id from hopes.services where upper(srv_name)=upper('Cuidados paliativos')),
(select pth_id from hopes.pathologies where upper(pth_name)=upper('VIH'))
where not exists (select r.rol_id from hopes.roles r where r.rol_name = 'Administrador VIH');

-- Relacionar médico VIH, administrador VIH con usuario admin
INSERT INTO hopes.users_roles (uro_user_id, uro_rol_id)
select (select u.usr_id from hopes.users u where upper(u.usr_name)=upper('admin')),
(select r.rol_id from hopes.roles r where upper(r.rol_name)=upper('Médico VIH'))
where not exists (select ur.uro_id from hopes.users_roles ur
where ur.uro_user_id = (select u.usr_id from hopes.users u where upper(u.usr_name)=upper('admin'))
and ur.uro_rol_id = (select r.rol_id from hopes.roles r where upper(r.rol_name)=upper('Médico VIH')));

INSERT INTO hopes.users_roles (uro_user_id, uro_rol_id)
select (select u.usr_id from hopes.users u where upper(u.usr_name)=upper('admin')),
(select r.rol_id from hopes.roles r where upper(r.rol_name)=upper('Administrador VIH'))
where not exists (select ur.uro_id from hopes.users_roles ur
where ur.uro_user_id = (select u.usr_id from hopes.users u where upper(u.usr_name)=upper('admin'))
and ur.uro_rol_id = (select r.rol_id from hopes.roles r where upper(r.rol_name)=upper('Administrador VIH')));

-- Eliminar secciones de VIH con sus roles relacionados
delete from hopes.sections_roles where scr_id in (
	select sr.scr_id from hopes.sections_roles sr where sr.scr_section_id in (
		select s.sec_id from hopes.sections s
		join hopes.sections_roles sr on sr.scr_section_id = s.sec_id
		join hopes.roles r on r.rol_id = sr.scr_role_id
		where r.rol_pth_id = (select p.pth_id from hopes.pathologies p where p.pth_name = 'VIH')
		and s.sec_id not in (select s.sec_id from hopes.sections s
			join hopes.sections_roles sr on sr.scr_section_id = s.sec_id
			join hopes.roles r on r.rol_id = sr.scr_role_id
			where r.rol_pth_id in (select p.pth_id from hopes.pathologies p where p.pth_name != 'VIH')
			group by s.sec_id)
		group by s.sec_id
	)
);

delete from hopes.sections where sec_id in (
	select s.sec_id from hopes.sections s
	left join hopes.sections_roles sr on sr.scr_section_id = s.sec_id
	where sr.scr_id is null
	group by s.sec_id
);

-- SECCIONES CUADRO DE MANDOS VIH
insert into hopes.sections (sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
values('Información clínica', 'Información clínica VIH', true, 1, (select s.sec_id from hopes.sections s where s.sec_title = 'Cuadro de Mando'), '', '#', false, true);
insert into hopes.sections (sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
values('Pacientes', 'Información clínica por pacientes VIH', true, 1, (select s.sec_id from hopes.sections s where s.sec_description = 'Información clínica VIH'),
'', '#', false, true);
insert into hopes.sections (sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
values('Tratamientos de paciente', 'Información clínica por tratamientos de pacientes VIH', true, 2, (select s.sec_id from hopes.sections s where s.sec_description = 'Información clínica VIH'),
'', '#', false, true);

insert into hopes.sections (sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
values('Información tratamiento', 'Información tratamiento VIH', true, 2, (select s.sec_id from hopes.sections s where s.sec_title = 'Cuadro de Mando'), '', '#', false, true);

insert into hopes.sections (sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
values('Coste total/medio del paciente', 'Coste total/medio del paciente VIH', true, 1, (select s.sec_id from hopes.sections s where s.sec_title = 'Información Farmaeconómicos'),
'', '#', false, true);
insert into hopes.sections (sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
values('Coste por pautas', 'Coste por pautas VIH', true, 2, (select s.sec_id from hopes.sections s where s.sec_title = 'Información Farmaeconómicos'), '', '#', false, true);

-- SECCIÓN PACIENTE y SECCIÓN CUADRO DE MANDOS se utiliza la misma que usa Derma

-- RESTO DE SECCIONES NUEVAS
INSERT INTO hopes.sections
(  sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES( 'Datos sociodemográficos', 'Datos sociodemográficos VIH', true, 2, (select s.sec_id from hopes.sections s where s.sec_title = 'Datos del paciente'),
'', '/hopes/pathology/patients/sociodemographic-data-vih', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Tratamiento datos personales', 'Tratamiento datos personales VIH', true, 3, (select s.sec_id from hopes.sections s where s.sec_title = 'Datos del paciente'), '', '#', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES( 'Ensayos clínicos y grupos de trabajo', 'Ensayos clínicos y grupos de trabajo VIH', true, 4, (select s.sec_id from hopes.sections s where s.sec_title = 'Datos del paciente'),
'', '/hopes/pathology/patients/work-groups-vih', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES( 'Situación del paciente', 'Situación del paciente VIH', true, 5, (select s.sec_id from hopes.sections s where s.sec_title = 'Datos del paciente'),
'', '/hopes/pathology/patients/patient-situation-vih', false, true);

INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Datos generales', 'Datos generales VIH', true, 3, (select s.sec_id from hopes.sections s where s.sec_title = 'Paciente'), '', '#', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Evaluación del estado físico', 'Evaluación del estado físico VIH', true, 1, (select s.sec_id from hopes.sections s where s.sec_description = 'Datos generales VIH'),
'', '/hopes/pathology/patients/physical-condition-vih', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Riesgo cardiovascular', 'Riesgo cardiovascular VIH', true, 2, (select s.sec_id from hopes.sections s where s.sec_description = 'Datos generales VIH'),
'', '/hopes/pathology/patients/cardiovascular-risk', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Ginecología: Embarazo y parto', 'Ginecología: Embarazo y parto VIH', true, 3, (select s.sec_id from hopes.sections s where s.sec_description = 'Datos generales VIH'),
'', '/hopes/pathology/patients/gynecology', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Factores de riesgo', 'Factores de riesgo VIH', true, 4, (select s.sec_id from hopes.sections s where s.sec_description = 'Datos generales VIH'),
'', '/hopes/pathology/patients/risk-factors', false, true);

INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Diagnóstico principal', 'Diagnóstico principal VIH', true, 1, (select s.sec_id from hopes.sections s where s.sec_title = 'Diagnósticos'),
'', '/hopes/pathology/patients/diagnosis/principal-diagnosis-vih', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Diagnósticos Secundarios', 'Diagnósticos Secundarios VIH', true, 2, (select s.sec_id from hopes.sections s where s.sec_title = 'Diagnósticos'),
'', '/hopes/pathology/patients/diagnosis/secundary-diagnosis-vih', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Comorbilidades y coinfecciones', 'Comorbilidades y coinfecciones VIH', true, 3, (select s.sec_id from hopes.sections s where s.sec_title = 'Diagnósticos'),
'', '/hopes/pathology/patients/comorbidities-coinfections-vih', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Enfermedades de transmisión sexual', 'Enfermedades de transmisión sexual VIH', true, 4, (select s.sec_id from hopes.sections s where s.sec_title = 'Diagnósticos'),
'', '/hopes/pathology/patients/diagnosis/sexually-transmitted-diseases-vih', false, true);

INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Tratamientos actuales', 'Tratamientos actuales VIH', true, 1, (select s.sec_id from hopes.sections s where s.sec_title = 'Tratamientos'), '', '#', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Tratamientos concomitantes', 'Tratamientos concomitantes VIH', true, 2, (select s.sec_id from hopes.sections s where s.sec_title = 'Tratamientos'), '', '#', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Tratamientos anteriores', 'Tratamientos anteriores VIH', true, 1, (select s.sec_id from hopes.sections s where s.sec_title = 'Tratamientos'), '', '#', false, true);

INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Seguimiento', 'Seguimiento VIH', true, 6, (select s.sec_id from hopes.sections s where s.sec_title = 'Paciente'),
'', '/hopes/pathology/patients/tracing-vih', false, true);

INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Análisis clínicos', 'Análisis clínicos VIH', true, 7, (select s.sec_id from hopes.sections s where s.sec_title = 'Paciente'), '', '#', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Hemograma', 'Hemograma VIH', true, 1, (select s.sec_id from hopes.sections s where s.sec_description = 'Análisis clínicos VIH'),
'', '/hopes/pathology/patients/blood-count-vih', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Bioquímica hepática y renal', 'Bioquímica hepática y renal VIH', true, 2, (select s.sec_id from hopes.sections s where s.sec_description = 'Análisis clínicos VIH'),
'', '/hopes/pathology/patients/kidney-liver-biochemistry-vih', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Perfil metabólico', 'Perfil metabólico VIH', true, 3, (select s.sec_id from hopes.sections s where s.sec_description = 'Análisis clínicos VIH'),
'', '/hopes/pathology/patients/metabolic-profile-vih', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Análisis básico de orina', 'Análisis básico de orina VIH', true, 4, (select s.sec_id from hopes.sections s where s.sec_description = 'Análisis clínicos VIH'),
'', '/hopes/pathology/patients/basic-urinalysis-vih', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Otras pruebas analíticas', 'Otras pruebas analíticas VIH', true, 5, (select s.sec_id from hopes.sections s where s.sec_description = 'Análisis clínicos VIH'),
'', '/hopes/pathology/patients/other-analysis-vih', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Serología', 'Serología VIH', true, 6, (select s.sec_id from hopes.sections s where s.sec_description = 'Análisis clínicos VIH'),
'', '/hopes/pathology/patients/serology-vih', false, true);

INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Fenotipo viral: tropismo y mutaciones', 'Fenotipo viral: tropismo y mutaciones VIH', true, 8, (select s.sec_id from hopes.sections s where s.sec_title = 'Paciente'),
'', '#', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Tropismo viral', 'Tropismo viral VIH', true, 1, (select s.sec_id from hopes.sections s where s.sec_description = 'Fenotipo viral: tropismo y mutaciones VIH'),
'', '/hopes/pathology/patients/viral-tropism-vih', false, true);
INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Genotipado/ Resistencias', 'Genotipado/ Resistencias VIH', true, 2, (select s.sec_id from hopes.sections s where s.sec_description = 'Fenotipo viral: tropismo y mutaciones VIH'),
'', '/hopes/pathology/patients/genotyping-resistances-vih', false, true);

INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Exploraciones complementarias de imagen', 'Exploraciones complementarias de imagen VIH', true, 9, (select s.sec_id from hopes.sections s where s.sec_title = 'Paciente'),
'', '/hopes/pathology/patients/complementary-imaging-scans-vih', false, true);

INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Farmacia', 'Farmacia VIH', true, 10, (select s.sec_id from hopes.sections s where s.sec_title = 'Paciente'), '', '#', false, true);

INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Cuestionario SMAQ', 'Cuestionario SMAQ VIH', true, 1, (select s.sec_id from hopes.sections s where s.sec_title = 'Adherencia al tratamiento'),
'', '/hopes/pathology/patients/adherence-to-treatment-SMAQ', false, true);

INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Paciente compartido', 'Paciente compartido VIH', true, 12, (select s.sec_id from hopes.sections s where s.sec_title = 'Paciente'),
'', '/hopes/pathology/patients/shared-patients-vih', false, true);

INSERT INTO hopes.sections
( sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES('Exportar', 'Exportar VIH', true, 13, (select s.sec_id from hopes.sections s where s.sec_title = 'Paciente'), '', '#', false, true);

-- RELACIÓN SECCIONES-ROLES

-- Relación con las secciones base compartidas para Administrador VIH
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_section_root isnull), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_section_root isnull));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Administración'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Administración'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Cuadro de Mando'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Cuadro de Mando'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Calendario'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Calendario'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Alertas'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Alertas'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Secciones'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Secciones'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Gestión de Usuarios'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Gestión de Usuarios'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Gestión de Pacientes'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Gestión de Pacientes'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Gestión de Roles'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Gestión de Roles'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Dispensaciones'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Dispensaciones'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Gestión de Medicamentos'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Gestión de Medicamentos'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Informes'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Informes'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Soporte'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Soporte'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Información Farmaeconómicos'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Información Farmaeconómicos'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Paciente'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Paciente'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Cuadro de Mando Paciente'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Cuadro de Mando Paciente'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Datos del paciente'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Datos del paciente'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Datos personales'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Datos personales'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Adherencia al tratamiento'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Adherencia al tratamiento'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Tratamientos'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Tratamientos'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Diagnósticos'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Diagnósticos'));

-- Relación con las nuevas secciones para Administrador VIH
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Datos sociodemográficos VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Tratamiento datos personales VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Ensayos clínicos y grupos de trabajo VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Situación del paciente VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Datos generales VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Evaluación del estado físico VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Riesgo cardiovascular VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Ginecología: Embarazo y parto VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Factores de riesgo VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Diagnóstico principal VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Diagnósticos Secundarios VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Comorbilidades y coinfecciones VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Enfermedades de transmisión sexual VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Tratamientos actuales VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Tratamientos concomitantes VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Tratamientos anteriores VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Seguimiento VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Análisis clínicos VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Hemograma VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Bioquímica hepática y renal VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Perfil metabólico VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Análisis básico de orina VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Otras pruebas analíticas VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Serología VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Fenotipo viral: tropismo y mutaciones VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Tropismo viral VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Genotipado/ Resistencias VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Exploraciones complementarias de imagen VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Farmacia VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Cuestionario SMAQ VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Paciente compartido VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Exportar VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Información clínica VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Información clínica por pacientes VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Información clínica por tratamientos de pacientes VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Información tratamiento VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Coste total/medio del paciente VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Coste por pautas VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));

-- Relación con las secciones base para Médico VIH
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_section_root isnull), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_section_root isnull));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Administración'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Administración'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Cuadro de Mando'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Cuadro de Mando'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Calendario'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Calendario'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Alertas'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Alertas'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Gestión de Pacientes'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Gestión de Pacientes'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Soporte'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Soporte'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Información Farmaeconómicos'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Información Farmaeconómicos'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Paciente'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Paciente'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Cuadro de Mando Paciente'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Cuadro de Mando Paciente'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Datos del paciente'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Datos del paciente'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Datos personales'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Datos personales'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Adherencia al tratamiento'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Adherencia al tratamiento'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Tratamientos'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Tratamientos'));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
select (select s.sec_id from hopes.sections s where s.sec_title = 'Diagnósticos'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
where not exists (select sr.scr_id from hopes.sections_roles sr
where sr.scr_role_id = (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH'))
and sr.scr_section_id = (select s.sec_id from hopes.sections s where s.sec_title = 'Diagnósticos'));

-- Relación con las nuevas secciones para Médico VIH
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Datos sociodemográficos VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Tratamiento datos personales VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Ensayos clínicos y grupos de trabajo VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Situación del paciente VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Datos generales VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Evaluación del estado físico VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Riesgo cardiovascular VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Ginecología: Embarazo y parto VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Factores de riesgo VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Diagnóstico principal VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Diagnósticos Secundarios VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Comorbilidades y coinfecciones VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Enfermedades de transmisión sexual VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Tratamientos actuales VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Tratamientos concomitantes VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Tratamientos anteriores VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Seguimiento VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Análisis clínicos VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Hemograma VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Bioquímica hepática y renal VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Perfil metabólico VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Análisis básico de orina VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Otras pruebas analíticas VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Serología VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Fenotipo viral: tropismo y mutaciones VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Tropismo viral VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Genotipado/ Resistencias VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Exploraciones complementarias de imagen VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Farmacia VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Cuestionario SMAQ VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Paciente compartido VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Exportar VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Información clínica VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Información clínica por pacientes VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Información clínica por tratamientos de pacientes VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Información tratamiento VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Coste total/medio del paciente VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Coste por pautas VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
