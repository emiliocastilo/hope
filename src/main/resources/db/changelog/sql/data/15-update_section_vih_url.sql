
-- Insertar secciones
INSERT INTO hopes.sections (sec_title, sec_description, sec_active, sec_order, sec_section_root, sec_icon, sec_url, sec_principal, sec_visible)
    VALUES( 'Cuadro de mando', 'Cuadro de mando VIH', true, 1, (select s.sec_id from hopes.sections s where s.sec_title = 'Paciente'), '', '/hopes/pathology/vih/dashboard', false, true);
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
    VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Cuadro de mando VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Administrador VIH')));
INSERT INTO hopes.sections_roles (scr_section_id, scr_role_id)
    VALUES((select s.sec_id from hopes.sections s where s.sec_description = 'Cuadro de mando VIH'), (select r.rol_id from hopes.roles r where upper(r.rol_name) = upper('Médico VIH')));

-- Eliminar section role
delete from hopes.sections_roles where scr_id in (
    select sr.scr_id from hopes.sections_roles sr
        where sr.scr_section_id in (select s.sec_id from hopes.sections s where s.sec_description = 'Sección que contiene la Cuadro de Mando Paciente')
			and sr.scr_role_id in (select r.rol_id from hopes.roles r where upper(r.rol_code) = upper('ROLE_MEVI') or upper(r.rol_code) = upper('ROLE_ADMINISTRADORVIH_1HO-SEDESA_3VI')));