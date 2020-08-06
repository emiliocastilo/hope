ALTER TABLE hopes.users RENAME COLUMN usr_name TO usr_username;
ALTER TABLE hopes.users ADD COLUMN usr_name varchar(50); --NOT NULL;
ALTER TABLE hopes.users ADD COLUMN usr_surname varchar(100); --NOT NULL;
ALTER TABLE hopes.users ADD COLUMN usr_phone varchar(11); --NOT NULL;
ALTER TABLE hopes.users ADD COLUMN usr_dni varchar(9); --NOT NULL;
ALTER TABLE hopes.users ADD COLUMN usr_college_number bigint; --NOT NULL
ALTER TABLE hopes.users ADD COLUMN usr_srv_id bigint; --NOT NULL

COMMENT ON COLUMN hopes.users.usr_name IS 'Columna que contiene el nombre del usuario';
COMMENT ON COLUMN hopes.users.usr_surname IS 'Columna que contiene los apellidos del usuario';
COMMENT ON COLUMN hopes.users.usr_phone IS 'Columna que contiene el número de teléfono del usuario';
COMMENT ON COLUMN hopes.users.usr_dni IS 'Columna que contiene el dni del usuario';
COMMENT ON COLUMN hopes.users.usr_college_number IS 'Columna que contiene el número de colegiado del médico';
COMMENT ON COLUMN hopes.users.usr_srv_id IS 'Columna que contiene el identificador del servicio al que pertenece el médico';

ALTER TABLE hopes.users ADD CONSTRAINT usr_srv_id_fk FOREIGN KEY (usr_srv_id) REFERENCES hopes.services(srv_id);
