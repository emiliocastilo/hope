-- Actualizar url de secciones
update hopes.sections set sec_url = '/hopes/pathology/derma/adherence-to-treatment-morisky' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Test de Morisky Green'));
update hopes.sections set sec_url = '/hopes/pathology/derma/biological-drug-monitoring' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene la Monitorización de fármacos biológicos del paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/blood-count' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene los hemogramas del paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/complementary-imaging-scans' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene las Exploraciones complementarias de imagen del paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/consent' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene el Consentimiento del paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/consumption-habits' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Hábitos de consumo de alcohol y tabaco'));
update hopes.sections set sec_url = '/hopes/pathology/derma/dashboard' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene la Cuadro de Mando Paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/diagnosis/comorbidities' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Comorbilidades'));
update hopes.sections set sec_url = '/hopes/pathology/derma/diagnosis/principal-diagnosis' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Diagnóstico principal'));
update hopes.sections set sec_url = '/hopes/pathology/derma/diagnosis/secundary-diagnosis' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Diagnósticos secundarios'));
update hopes.sections set sec_url = '/hopes/pathology/derma/evolution-clinical-indices/dlqi' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene el indice DLQI del paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/evolution-clinical-indices/eav-pase' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene el indice EAV Y PASE del paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/evolution-clinical-indices/napsi' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene el indice Otros índices: NAPSI  del paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/evolution-clinical-indices/pasi-bsa-pga' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene los índices clínicos PASI, BSA Y PGA del paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/family-history' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene los antecedentes del paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/gallery' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene la Galería de fotos del paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/adherence-to-treatment-haynes' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Test de Haynes Sackett'));
update hopes.sections set sec_url = '/hopes/pathology/derma/kidney-liver-biochemistry' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene la Bioquímica hepática y renal del paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/leukocyte-antibody-antigen' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene la información de los Anticuerpo y antígeno leucocitario del paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/metabolic-profile' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene el perfil metabólico del paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/personal-information' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Datos personales del paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/phototherapy' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Fototerapia'));
update hopes.sections set sec_url = '#' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene la Evaluación de análisis clínicos del paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/physical-condition' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Evaluación del estado físico'));
update hopes.sections set sec_url = '/hopes/pathology/derma/principal-treatment' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Tratamiento principal'));
update hopes.sections set sec_url = '/hopes/pathology/derma/serology' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene las Serología (VHC, VHB, otras infecciones y vacunas) del paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/shared-patients' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene informacion del Paciente compartido'));
update hopes.sections set sec_url = '/hopes/pathology/derma/sociodemographic-data' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene los datos sociodemograficos de un paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/tracing' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene el Seguimiento del paciente'));
update hopes.sections set sec_url = '/hopes/pathology/derma/work-groups' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Datos sobre ensayos clínicos'));
update hopes.sections set sec_url = '/hopes/pathology/patients' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Sección que contiene el Menú completo tras la seleccion de un Paciente'));
update hopes.sections set sec_url = '/hopes/pathology/vih/adherence-to-treatment-SMAQ' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Cuestionario SMAQ VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/basic-urinalysis-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Análisis básico de orina VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/blood-count-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Hemograma VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/cardiovascular-risk' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Riesgo cardiovascular VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/comorbidities-coinfections-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Comorbilidades y coinfecciones VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/complementary-imaging-scans-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Exploraciones complementarias de imagen VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/dashboard' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Cuandro de mando'));
update hopes.sections set sec_url = '/hopes/pathology/vih/diagnosis/principal-diagnosis-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Diagnóstico principal VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/diagnosis/secundary-diagnosis-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Diagnósticos Secundarios VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/diagnosis/sexually-transmitted-diseases-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Enfermedades de transmisión sexual VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/genotyping-resistances-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Genotipado/ Resistencias VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/gynecology' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Ginecología: Embarazo y parto VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/kidney-liver-biochemistry-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Bioquímica hepática y renal VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/metabolic-profile-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Perfil metabólico VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/other-analysis-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Otras pruebas analíticas VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/patient-situation-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Situación del paciente VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/personal-information' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Datos personales VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/physical-condition-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Evaluación del estado físico VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/risk-factors' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Factores de riesgo VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/serology-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Serología VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/shared-patients-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Paciente compartido VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/sociodemographic-data-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Datos sociodemográficos VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/tracing-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Seguimiento VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/treatments/current' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Tratamientos actuales VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/viral-tropism-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Tropismo viral VIH'));
update hopes.sections set sec_url = '/hopes/pathology/vih/work-groups-vih' where LTRIM(RTRIM(sec_description)) = LTRIM(RTRIM('Ensayos clínicos y grupos de trabajo VIH'));

-- Eliminar secciones
delete from hopes.sections_roles where scr_id in (
    select sr.scr_id from hopes.sections_roles sr where sr.scr_section_id in (
		select s.sec_id from hopes.sections s
        join hopes.sections_roles sr on sr.scr_section_id = s.sec_id
        join hopes.roles r on r.rol_id = sr.scr_role_id
        and LTRIM(RTRIM(s.sec_description)) ='Tratamiento datos personales VIH'
        group by s.sec_id
	)
);
delete from hopes.sections where sec_id in (
	select s.sec_id from hopes.sections s
	left join hopes.sections_roles sr on sr.scr_section_id = s.sec_id
	where sr.scr_id is null and LTRIM(RTRIM(s.sec_description)) ='Tratamiento datos personales VIH'
	group by s.sec_id
);

delete from hopes.sections_roles where scr_id in (
    select sr.scr_id from hopes.sections_roles sr where sr.scr_section_id in (
		select s.sec_id from hopes.sections s
        join hopes.sections_roles sr on sr.scr_section_id = s.sec_id
        join hopes.roles r on r.rol_id = sr.scr_role_id
        and LTRIM(RTRIM(s.sec_description)) ='Tratamientos anteriores VIH'
        group by s.sec_id
	)
);
delete from hopes.sections where sec_id in (
	select s.sec_id from hopes.sections s
	left join hopes.sections_roles sr on sr.scr_section_id = s.sec_id
	where sr.scr_id is null and LTRIM(RTRIM(s.sec_description)) ='Tratamientos anteriores VIH'
	group by s.sec_id
);

delete from hopes.sections_roles where scr_id in (
    select sr.scr_id from hopes.sections_roles sr where sr.scr_section_id in (
		select s.sec_id from hopes.sections s
        join hopes.sections_roles sr on sr.scr_section_id = s.sec_id
        join hopes.roles r on r.rol_id = sr.scr_role_id
        and LTRIM(RTRIM(s.sec_description)) ='Tratamientos concomitantes VIH'
        group by s.sec_id
	)
);
delete from hopes.sections where sec_id in (
	select s.sec_id from hopes.sections s
	left join hopes.sections_roles sr on sr.scr_section_id = s.sec_id
	where sr.scr_id is null and LTRIM(RTRIM(s.sec_description)) ='Tratamientos concomitantes VIH'
	group by s.sec_id
);

delete from hopes.sections_roles where scr_id in (
    select sr.scr_id from hopes.sections_roles sr where sr.scr_section_id in (
		select s.sec_id from hopes.sections s
        join hopes.sections_roles sr on sr.scr_section_id = s.sec_id
        join hopes.roles r on r.rol_id = sr.scr_role_id
        and LTRIM(RTRIM(s.sec_description)) ='Exportar VIH'
        group by s.sec_id
	)
);
delete from hopes.sections where sec_id in (
	select s.sec_id from hopes.sections s
	left join hopes.sections_roles sr on sr.scr_section_id = s.sec_id
	where sr.scr_id is null and LTRIM(RTRIM(s.sec_description)) ='Exportar VIH'
	group by s.sec_id
);

-- Insertar secciones
INSERT INTO hopes.sections (sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
    VALUES( 'Datos personales', 'Datos personales del paciente VIH', true, 1, (select s.sec_id from hopes.sections s where s.sec_title = 'Datos del paciente'), '', '/hopes/pathology/vih/personal-information', false, true);
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
    VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Datos personales del paciente VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
    VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Datos personales del paciente VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));