INSERT INTO public.sections (sec_id, sec_title,sec_description,sec_menu,sec_order,sec_section_root,sec_icon,sec_url) VALUES (1, 'HOPES','root',true,1,NULL,NULL,'#');

INSERT INTO public.sections (sec_id, sec_title,sec_description,sec_menu,sec_order,sec_section_root,sec_icon,sec_url) VALUES
(2 ,'CALENDAR','Calendario',true,1,1,'assets/img/modules/calendario.png','#')
,(3, 'PATIENTS','Pacientes',true,2,1,'assets/img/modules/planes-atencion.png','#')
,(4, 'CONTROL_PANEL','Cuadro de mandos',true,3,1,'assets/img/modules/cuadro-de-mando.png','#')
,(5, 'ALERTS','Alertas',true,4,1,'assets/img/modules/alertas.png','#')
,(6, 'CONTROL_PANEL','Cuadro de mandos',true,4,3,'assets/img/modules/cuadro-de-mando.png','#')
,(7, 'FORM_DERMA','Formulario dermatología',true,4,3,'assets/img/modules/form.png','#')
;
