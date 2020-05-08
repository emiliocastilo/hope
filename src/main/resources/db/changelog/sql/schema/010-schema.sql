-- object: hopes.localized_roles | type: TABLE --
DROP TABLE IF EXISTS hopes.localized_roles CASCADE;
CREATE TABLE hopes.localized_roles (
	lcr_id serial,
	lcr_locale varchar(2) NOT NULL,
	lcr_name varchar(60) NOT NULL,
	lcr_description varchar(200) NOT NULL,
	lcr_rol_id smallint,
	CONSTRAINT lcr_id_pk PRIMARY KEY (lcr_id),
	CONSTRAINT lcr_rol_id_fk FOREIGN KEY (lcr_rol_id) REFERENCES hopes.roles(rol_id)
	
);
-- COMENTARIOS
COMMENT ON TABLE hopes.localized_roles IS 'Tabla de traducciones de roles';
COMMENT ON COLUMN hopes.localized_roles.lcr_name IS 'Columna que contiene el nombre del rol';
COMMENT ON COLUMN hopes.localized_roles.lcr_description IS 'Columna que contiene la descripcion del rol.';
COMMENT ON CONSTRAINT lcr_id_pk ON hopes.localized_roles IS 'pk de la tabla localized_roles';
COMMENT ON CONSTRAINT lcr_rol_id_fk ON hopes.localized_roles IS 'fk  Relacion con la tabla ROLES';

-- ddl-end --