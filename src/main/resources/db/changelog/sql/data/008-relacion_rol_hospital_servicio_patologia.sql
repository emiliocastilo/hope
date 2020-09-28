
INSERT INTO hopes.roles_hospitals (rhs_hos_id, rhs_rol_id) VALUES((select hos_id from hopes.hospitals where hos_name ='Hopes - Servicios de Salud' LIMIT 1), (select rol_id from hopes.roles where rol_name ='Administrador' LIMIT 1));
INSERT INTO hopes.roles_hospitals (rhs_hos_id, rhs_rol_id) VALUES((select hos_id from hopes.hospitals where hos_name ='Hospital Perpetuo Confinamiento' LIMIT 1), (select rol_id from hopes.roles where rol_name ='Administrador' LIMIT 1));
