
-- actualizar iconos
UPDATE hopes.sections SET sec_icon='assets/img/modules/i-dispensaciones.svg' WHERE  LTRIM(RTRIM(upper(sec_description))) = upper('Sección que contiene la carga de dispensaciones');
UPDATE hopes.sections SET sec_icon='assets/img/modules/i-farmacia.svg' WHERE LTRIM(RTRIM(upper(sec_description))) = upper('Farmacia');
UPDATE hopes.sections SET sec_icon='assets/img/modules/i-gestion-pacientes.svg' WHERE LTRIM(RTRIM(upper(sec_description))) = upper('Sección que contiene la Gestión de Pacientes');
UPDATE hopes.sections SET sec_icon='assets/img/modules/i-gestion-usuarios.svg' WHERE LTRIM(RTRIM(upper(sec_description))) = upper('Sección que contiene la gestión de los usuarios de la aplicación');
UPDATE hopes.sections SET sec_icon='assets/img/modules/i-gestion-medicamentos.svg' WHERE LTRIM(RTRIM(upper(sec_description))) = upper('Sección que contiene la gestión de Medicamentos');
UPDATE hopes.sections SET sec_icon='assets/img/modules/i-gestion-roles.svg' WHERE LTRIM(RTRIM(upper(sec_description))) = upper('Sección que contiene la gestión de los roles de la aplicación');
UPDATE hopes.sections SET sec_icon='assets/img/modules/i-secciones.svg' WHERE LTRIM(RTRIM(upper(sec_description))) = upper('Sección que contiene la gestión de secciones de la aplicación');
