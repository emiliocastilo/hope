UPDATE hopes.sections SET sec_icon='assets/img/modules/paciente.png' WHERE sec_id= (select sec_id from hopes.sections s where sec_title like 'Paciente');
UPDATE hopes.sections SET sec_icon='assets/img/modules/gestion-paciente.png' WHERE sec_id= (select sec_id from hopes.sections s where sec_title like 'Gestión de Pacientes');