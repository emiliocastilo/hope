INSERT INTO hopes.sections (sec_title,sec_description,sec_active,sec_order,sec_section_root,sec_icon,sec_url) VALUES
('HOPES','root',true,1,NULL,NULL,'#')
,('CALENDAR','Calendario',true,1,1,'assets/img/modules/calendario.png','#')
,('PATIENTS','Pacientes',true,2,1,'assets/img/modules/planes-atencion.png','#')
,('CONTROL_PANEL','Cuadro de mandos',true,3,1,'assets/img/modules/cuadro-de-mando.png','#')
,('ALERTS','Alertas',true,4,1,'assets/img/modules/alertas.png','#')
,('FORM_DERMA','Formulario dermatología',true,2,3,'assets/img/modules/form.png','#')
,('CONTROL_PANEL','Cuadro de mandos',true,1,3,'assets/img/modules/cuadro-de-mando.png','#')
,('FORM_VIH','Formulario VIH',true,2,3,'assets/img/modules/form.png','#')
;

INSERT INTO hopes.sections_roles (scr_section_id,scr_role_id) VALUES
(1,7)
,(2,7)
,(3,7)
,(4,7)
,(5,7)
,(6,7)
,(7,7)
,(1,8)
,(2,8)
,(3,8)
,(4,8)
,(5,8)
,(6,8)
,(8,8)
,(1,1)
;
