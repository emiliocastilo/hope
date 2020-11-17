--Actualizar el rol Medico VIH
UPDATE hopes.roles
SET rol_hos_id=(select hos_id from hopes.hospitals where upper(hos_name)=upper('Hopes - Servicios de Salud')),
rol_srv_id=(select srv_id from hopes.services where upper(srv_name)=upper('Cuidados paliativos')),
rol_pth_id=(select pth_id from hopes.pathologies where upper(pth_name)=upper('VIH'))
WHERE upper(rol_name)=upper('Médico VIH');

-- Relacionar médico VIH con usuario admin
INSERT INTO hopes.users_roles (uro_user_id, uro_rol_id)
VALUES( (select u.usr_id from hopes.users u where upper(u.usr_name)=upper('admin')),
(select r.rol_id from hopes.roles r where upper(r.rol_name)=upper('Médico VIH')));

-- Eliminar los datos de las tablas localized_sections, sections, sections_forms y sections_roles que hacen referencia a secciones
DELETE FROM  hopes.localized_sections;
DELETE FROM  hopes.sections_forms;
DELETE FROM  hopes.sections_roles;
DELETE FROM  hopes.sections;

-- secciones base
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(1, 'Hopes', 'Home', true, 1, NULL, 'assets/img/modules/hopes-launch.png', '/hopes', true, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(2, 'Administración', 'Sección que contiene las gestiones del usuario logado', true, 1, 1, 'assets/img/modules/administracion.png', '/hopes/management', true, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(3, 'Cuadro de Mando', 'Sección que contiene el Cuadro de Mando de la patología', true, 2, 1, 'assets/img/modules/cuadro-de-mando.png', '#', true, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(4, 'Paciente', 'Sección que contiene el Menú completo tras la seleccion de un Paciente', true, 3, 1, 'assets/img/modules/planes-atencion.png', '/hopes/pathology/patients', true, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(5, 'Calendario', 'Sección que contiene el Calendario de citas de la patología', false, 5, 1, 'assets/img/modules/calendario.png', '#', true, false);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(6, 'Alertas', 'Sección que contiene las Alertas de la patología', false, 6, 1, 'assets/img/modules/alertas.png', '#', true, false);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(7, 'Secciones', 'Sección que contiene la gestión de secciones de la aplicación', true, 1, 2, 'assets/img/modules/alertas.png', '/hopes/management/sections', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(8, 'Gestión de Usuarios', 'Sección que contiene la gestión de los usuarios de la aplicación', true, 2, 2, NULL, '/hopes/management/users', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(9, 'Gestión de Pacientes', 'Sección que contiene la Gestión de Pacientes', true, 3, 2, 'assets/img/modules/planes-atencion.png', '/hopes/management/patients', true, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(10, 'Gestión de Roles', 'Sección que contiene la gestión de los roles de la aplicación', true, 4, 2, NULL, '/hopes/management/roles', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(11, 'Dispensaciones', 'Sección que contiene la carga de dispensaciones', true, 5, 2, 'assets/img/modules/dispensaciones.png', '/hopes/management/dispensations', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(12, 'Gestión de Medicamentos', 'Sección que contiene la Gestión de Medicamentos', true, 6, 2, 'assets/img/modules/medicamentos.png', '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(13, 'Informes', 'Sección que contiene la configuración de los Informes', false, 7, 2, NULL, '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(14, 'Soporte', 'Sección que contiene la configuración de las opciones de soporte', true, 7, 1, '', '/hopes/contact', false, false);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(15, 'Información Diagnóstico', 'Sección que contiene la información de diagnóstico de la patologia', true, 1, 3, NULL, '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(16, 'Información de Tratamientos', 'Sección que contiene la información de los tratamientos de la patologia', true, 2, 3, NULL, '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(17, 'Información Resultados en Salud', 'Sección que contiene los Resultados en salud de la patologia', true, 3, 3, NULL, '/hopes/dashboard/health-outcomes', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(18, 'Información Pacientes/Dosis', 'Sección que contiene la Información de Pacientes/Dosis de la patologia', true, 4, 3, NULL, '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(19, 'Información Farmaeconómicos', 'Sección que contiene la Información Farmaeconómica de la patologia', true, 5, 3, NULL, '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(20, 'Cuadro de Mando Paciente', 'Sección que contiene la Cuadro de Mando Paciente', true, 1, 4, '', '/hopes/pathology/patients/dashboard', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(21, 'Datos del paciente', 'Sección que contiene los datos generales del paciente', true, 2, 4, '', '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(22, 'Datos personales', 'Datos personales del paciente', true, 1, 21, '', '/hopes/pathology/patients/personal-information', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(23, 'Datos sociodemográficos', 'Sección que contiene los datos sociodemograficos de un paciente', true, 2, 21, '', '/hopes/pathology/patients/sociodemographic-data', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(24, 'Datos de ensayos clínicos', 'Datos sobre ensayos clínicos', true, 3, 21, '', '/hopes/pathology/patients/work-groups', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(25, 'Evaluación del estado físico', 'Evaluación del estado físico', true, 4, 21, '', '/hopes/pathology/patients/physical-condition', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(26, 'Antecedentes Familiares', 'Sección que contiene los antecedentes del paciente', true, 5, 21, NULL, '/hopes/pathology/patients/family-history', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(27, 'Hábitos de consumo', 'Hábitos de consumo de alcohol y tabaco', true, 6, 21, '', '/hopes/pathology/patients/consumption-habits', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(28, 'Diagnósticos', 'Sección que contiene los diagnosticos del paciente', true, 4, 4, '', '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(29, 'Diagnóstico Principal', 'Diagnóstico principal', true, 1, 28, '', '/hopes/pathology/patients/diagnosis/principal-diagnosis', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(30, 'Diagnósticos Secundarios', 'Diagnósticos secundarios', true, 2, 28, '', '/hopes/pathology/patients/diagnosis/secundary-diagnosis', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(31, 'Comorbilidades', 'Comorbilidades', true, 3, 28, '', '/hopes/pathology/patients/diagnosis/comorbidities', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(32, 'Seguimiento', 'Sección que contiene el Seguimiento del paciente', true, 6, 4, '', '/hopes/pathology/patients/tracing', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(33, 'Tratamientos', 'Tratamientos', true, 5, 4, '', '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(34, 'Farmacológicos', 'Tratamiento principal', true, 1, 33, '', '/hopes/pathology/patients/principal-treatment', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(35, 'No farmacológico (fototerapia)', 'Fototerapia', true, 2, 33, '', '/hopes/pathology/patients/phototherapy', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(36, 'Evolución de índices clínicos', 'Sección que contiene la Evolución de índices clínicos del paciente', true, 6, 4, NULL, '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(37, 'PASI, BSA Y PGA', 'Sección que contiene los índices clínicos PASI, BSA Y PGA del paciente', true, 1, 36, NULL, '/hopes/pathology/patients/evolution-clinical-indices/pasi-bsa-pga', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(38, 'DLQI', 'Sección que contiene el indice DLQI del paciente', true, 2, 36, NULL, '/hopes/pathology/patients/evolution-clinical-indices/dlqi', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(39, 'EAV Y PASE', 'Sección que contiene el indice EAV Y PASE del paciente', true, 3, 36, NULL, '/hopes/pathology/patients/evolution-clinical-indices/eav-pase', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(40, 'Otros índices: NAPSI', 'Sección que contiene el indice Otros índices: NAPSI  del paciente', true, 4, 36, NULL, '/hopes/pathology/patients/evolution-clinical-indices/napsi', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(41, 'Evaluación de análisis clínicos', 'Sección que contiene la Evaluación de análisis clínicos del paciente ', true, 7, 4, '', '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(42, 'Hemograma', 'Sección que contiene los hemogramas del paciente', true, 1, 41, '', '/hopes/pathology/patients/blood-count', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(43, 'Perfil metabólico', 'Sección que contiene el perfil metabólico del paciente', true, 2, 41, '', '/hopes/pathology/patients/metabolic-profile', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(44, 'Bioquímica hepática y renal', 'Sección que contiene la Bioquímica hepática y renal del paciente', true, 3, 41, '', '/hopes/pathology/patients/kidney-liver-biochemistry', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(45, 'Serología (VHC, VHB, otras infecciones y vacunas)', 'Sección que contiene las Serología (VHC, VHB, otras infecciones y vacunas) del paciente', true, 4, 41, '', '/hopes/pathology/patients/serology', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(46, 'Anticuerpo y antígeno leucocitario', 'Sección que contiene la información de los Anticuerpo y antígeno leucocitario del paciente', true, 5, 41, NULL, '/hopes/pathology/patients/leukocyte-antibody-antigen', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(47, 'Monitorización de fármacos biológicos', 'Sección que contiene la Monitorización de fármacos biológicos del paciente', true, 6, 41, NULL, '/hopes/pathology/patients/biological-drug-monitoring', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(48, 'Galería de fotos', 'Sección que contiene la Galería de fotos del paciente', true, 8, 4, NULL, '/hopes/pathology/patients/gallery', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(49, 'Exploraciones complementarias de imagen', 'Sección que contiene las Exploraciones complementarias de imagen del paciente', true, 9, 4, '', '/hopes/pathology/patients/complementary-imaging-scans', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(50, 'Paciente compartido', 'Sección que contiene informacion del Paciente compartido', true, 19, 4, '', '/hopes/pathology/patients/shared-patients', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(51, 'Adherencia al tratamiento', 'Sección que contiene la Adherencia al tratamiento del paciente', true, 11, 4, '', '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(52, 'Test de Morisky Green', 'Test de Morisky Green', true, 1, 51, '', '/hopes/pathology/patients/adherence-to-treatment-morisky', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(53, 'Test de Haynes Sackett', 'Test de Haynes Sackett', true, 2, 51, '', '/hopes/pathology/patients/adherence-to-treatment-haynes', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(54, 'Consentimiento', 'Sección que contiene el Consentimiento del paciente', true, 12, 4, NULL, '/hopes/pathology/patients/consent', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(55, 'Pacientes por indicación', 'Sección que contiene informacion de diagnostico de Pacientes por indicación', true, 1, 15, NULL, '/hopes/dashboard/diagnosis/patients-indication', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(56, 'Pacientes por diagnóstico CIE', 'Sección que contiene  informacion de diagnostico de Pacientes por diagnóstico CIE', true, 2, 15, NULL, '/hopes/dashboard/diagnosis/cie', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(57, 'Pacientes por tipo de tratamiento', 'Sección que contiene informacion de diagnostico de Pacientes por tipo de tratamiento', true, 3, 15, NULL, '/hopes/dashboard/diagnosis/patients-treatment', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(58, 'Pacientes por tratamientos combinados', 'Sección que contiene  informacion de diagnostico de Pacientes por tratamientos combinados', true, 4, 15, NULL, '/hopes/dashboard/diagnosis/patients-combined-treatments', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(59, 'Motivo del cambio/suspensión de tratamiento biológico', 'Motivo del cambio/suspensión de tratamiento biológico', true, 5, 15, NULL, '/hopes/dashboard/diagnosis/reasons', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(60, 'Número de cambios de tratamiento biológico', 'Sección que contiene informacion de diagnostico del Número de cambios de tratamiento biológico', true, 6, 15, NULL, '/hopes/dashboard/diagnosis/number-changes-biological-treatment', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(61, 'Pacientes en tratamiento según agentes', 'Sección que contiene informacion de tratamiento de Pacientes en tratamiento según tipos de agentes', true, 1, 16, NULL, '/hopes/dashboard/treatments/treatments-agents', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(62, 'Tratamiento biológico en pacientes', 'Sección que contiene informacion de tratamiento de Tratamiento biológico en pacientes', true, 2, 16, NULL, '/hopes/dashboard/treatments/treatments-patients', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(63, 'Pacientes según dosis/frecuencia del tratamiento biológico', 'Sección que contiene informacion de Pacientes según dosis/frecuencia del tratamiento biológico', true, 1, 18, NULL, '/hopes/dashboard/patient-dose/biological-treatment-frequency', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(64, 'Consumo de tratamientos biológicos', 'Sección que contiene la Información Farmaeconómicos de Consumo de tratamientos biológicos', true, 1, 19, NULL, '/hopes/dashboard/pharmacoeconomic/consumption-biological-treatment', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(65, 'Coste por tratamientos biológicos', 'Sección que contiene el Coste por tratamiento biológico', true, 2, 19, NULL, '/hopes/dashboard/pharmacoeconomic/total-expenses-biological-treatment', false, true);

-- seccion patología VIH
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(66, 'VIH', 'VIH', true, 4, 1, '', '/hopes/pathology/patients/genotyping-resistances-vih', false, false);

-- sección Paciente para VIH
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(67, 'Paciente', 'Sección que contiene el Menú completo tras la seleccion de un Paciente', true, 1, 66, 'assets/img/modules/planes-atencion.png', '/hopes/pathology/patients', false, true);


-- Resto de secciones nuevas
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(68, 'Cuadro de Mando Paciente', 'Sección que contiene la Cuadro de Mando Paciente', true, 1, 67, '', '/hopes/pathology/patients/dashboard', false, true);

INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(69, 'Datos del paciente', 'Sección que contiene los datos generales del paciente', true, 2, 67, '', '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(70, 'Datos personales', 'Datos personales del paciente', true, 1, 69, '', '/hopes/pathology/patients/personal-information', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(71, 'Datos sociodemográficos', 'Datos sociodemográficos VIH', true, 2, 69, '', '/hopes/pathology/patients/sociodemographic-data-vih', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(72, 'Tratamiento datos personales', 'Tratamiento datos personales', true, 3, 69, '', '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(73, 'Ensayos clínicos y grupos de trabajo', 'Ensayos clínicos y grupos de trabajo', true, 4, 69, '', '/hopes/pathology/patients/work-groups-vih', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(74, 'Situación del paciente', 'Situación del paciente', true, 5, 69, '', '/hopes/pathology/patients/patient-situation-vih', false, true);

INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(75, 'Datos generales', 'Datos generales', true, 3, 67, '', '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(76, 'Evaluación del estado físico', 'Evaluación del estado físico', true, 1, 75, '', '/hopes/pathology/patients/physical-condition-vih', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(77, 'Riesgo cardiovascular', 'Riesgo cardiovascular', true, 2, 75, '', '/hopes/pathology/patients/cardiovascular-risk', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(78, 'Ginecología: Embarazo y parto', 'Ginecología: Embarazo y parto', true, 3, 75, '', '/hopes/pathology/patients/gynecology', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(79, 'Factores de riesgo', 'Factores de riesgo', true, 4, 75, '', '/hopes/pathology/patients/risk-factors', false, true);

INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(80, 'Diagnósticos', 'Sección que contiene los diagnosticos del paciente', true, 4, 67, '', '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(81, 'Diagnóstico principal', 'Diagnóstico principal', true, 1, 80, '', '/hopes/pathology/patients/diagnosis/principal-diagnosis-vih', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(82, 'Diagnósticos Secundarios', 'Diagnósticos secundarios', true, 2, 80, '', '/hopes/pathology/patients/diagnosis/secundary-diagnosis-vih', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(83, 'Comorbilidades y coinfecciones', 'Comorbilidades y coinfecciones', true, 3, 80, '', '/hopes/pathology/patients/diagnosis/comorbidities-coinfections-vih', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(84, 'Enfermedades de transmisión sexual', 'Enfermedades de transmisión sexual', true, 4, 80, '', '/hopes/pathology/patients/diagnosis/sexually-transmitted-diseases-vih', false, true);

INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(85, 'Tratamientos', 'Tratamientos', true, 5, 67, '', '#', false, true);

INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(86, 'Seguimiento', 'Sección que contiene el Seguimiento del paciente', true, 6, 67, '', '/hopes/pathology/patients/tracing-vih', false, true);

INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(87, 'Análisis clínicos', 'Sección que contiene los Análisis clínicos', true, 7, 67, '', '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(88, 'Hemograma', 'Sección que contiene los hemogramas del paciente', true, 1, 87, '', '/hopes/pathology/patients/blood-count-vih', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(89, 'Bioquímica hepática y renal', 'Sección que contiene la Bioquímica hepática y renal del paciente', true, 2, 87, '', '/hopes/pathology/patients/kidney-liver-biochemistry-vih', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(90, 'Perfil metabólico', 'Sección que contiene el perfil metabólico del paciente', true, 3, 87, '', '/hopes/pathology/patients/metabolic-profile-vih', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(91, 'Análisis básico de orina', 'Análisis básico de orina', true, 4, 87, '', '/hopes/pathology/patients/basic-urinalysis-vih', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(92, 'Otras pruebas analíticas', 'Otras pruebas analíticas', true, 5, 87, '', '/hopes/pathology/patients/other-analysis-vih', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(93, 'Serología', 'Serología', true, 6, 87, '', '/hopes/pathology/patients/serology-vih', false, true);

INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(94, 'Fenotipo viral: tropismo y mutaciones', 'Fenotipo viral', true, 8, 67, '', '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(95, 'Tropismo viral', 'Tropismo viral', true, 1, 94, '', '/hopes/pathology/patients/viral-tropism-vih', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(96, 'Genotipado/ Resistencias', 'Genotipado/ Resistencias', true, 2, 94, '', '/hopes/pathology/patients/genotyping-resistances-vih', false, true);

INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(97, 'Exploraciones complementarias de imagen', 'Sección que contiene las Exploraciones complementarias de imagen del paciente', true, 9, 67, '', '/hopes/pathology/patients/complementary-imaging-scans-vih', false, true);

INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(98, 'Farmacia', 'Farmacia', true, 10, 67, '', '#', false, true);

INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(99, 'Adherencia al tratamiento', 'Sección que contiene la Adherencia al tratamiento del paciente', true, 11, 67, '', '#', false, true);
INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(100, 'Cuestionario SMAQ', 'Cuestionario SMAQ', true, 1, 99, '', '/hopes/pathology/patients/adherence-to-treatment-SMAQ', false, true);

INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(101, 'Paciente compartido', 'Sección que contiene informacion del Paciente compartido', true, 12, 67, '', '/hopes/pathology/patients/shared-patients-vih', false, true);

INSERT INTO hopes.sections
(sec_id, sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
VALUES(102, 'Exportar', 'Exportar', true, 13, 67, '', '#', false, true);

-- Relación sección-rol

-- Relación base
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(1, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(1, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor CAU')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(1, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(1, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Farmacéutico')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(1, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(1, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(1, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médica Reumatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(2, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(2, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(2, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Farmacéutico')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(2, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(2, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(2, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médica Reumatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(3, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(3, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(3, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(3, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(3, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médica Reumatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(4, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(4, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(5, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(5, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(5, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(5, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(5, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médica Reumatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(6, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(6, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(6, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(6, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(6, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médica Reumatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(7, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(8, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(9, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(9, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(9, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(10, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(11, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(11, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Farmacéutico')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(12, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(12, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Farmacéutico')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(13, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(14, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(14, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor CAU')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(15, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(15, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(16, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(16, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(17, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(17, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(18, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(18, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(19, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(19, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(20, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(20, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(21, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(21, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(21, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(22, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(22, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(22, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(23, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(23, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(24, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(24, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(24, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(25, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(25, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(25, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(26, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(26, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(27, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(27, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(27, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(28, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(28, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(29, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(29, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(29, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(30, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(30, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(30, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(31, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(31, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(31, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(32, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(32, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(33, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(33, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(33, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(34, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(34, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(34, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(35, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(35, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(35, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(36, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(36, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(37, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(37, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(38, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(38, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(39, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(39, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(40, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(40, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(41, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(41, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(42, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(42, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(43, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(43, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(44, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(44, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(45, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(45, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(46, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(46, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(47, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(47, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(48, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(48, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(49, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(49, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(50, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(50, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(51, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(51, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(52, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(52, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(52, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(53, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(53, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Gestor')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(53, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(54, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(54, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(55, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(55, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(56, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(56, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(57, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(57, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(58, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(58, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(59, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(59, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(60, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(60, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(61, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(61, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(62, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(62, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(63, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(63, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(64, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(64, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(65, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(65, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico Dermatología')));

-- Relación con las nuevas secciones para Administrador
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(66, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(67, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(68, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(69, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(70, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(71, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(72, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(73, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(74, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(75, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(76, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(77, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(78, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(79, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(80, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(81, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(82, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(83, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(84, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(85, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(86, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(87, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(88, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(89, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(90, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(91, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(92, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(93, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(94, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(95, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(96, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(97, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(98, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(99, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(100, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(101, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(102, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador')));

-- Relación con las nuevas secciones para Médico VIH
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(66, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(67, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(68, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(69, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(70, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(71, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(72, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(73, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(74, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(75, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(76, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(77, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(78, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(79, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(80, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(81, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(82, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(83, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(84, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(85, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(86, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(87, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(88, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(89, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(90, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(91, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(92, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(93, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(94, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(95, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(96, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(97, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(98, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(99, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(100, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(101, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id) VALUES(102, (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));
