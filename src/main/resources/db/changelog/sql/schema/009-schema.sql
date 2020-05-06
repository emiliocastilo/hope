-- object: hopes.tokens | type: TABLE --
DROP TABLE IF EXISTS hopes.tokens CASCADE;
CREATE TABLE hopes.tokens (
	tok_id serial,
	tok_value varchar(200) NOT NULL,
	tok_type varchar(20) NOT NULL,
	tok_usr_id smallint,
	tok_token_creation_date timestamp NOT NULL,
	tok_token_expiration_date timestamp NOT NULL,
	CONSTRAINT tok_id_pk PRIMARY KEY (tok_id),
    CONSTRAINT tok_usr_id_fk FOREIGN KEY (tok_usr_id) REFERENCES hopes.users(usr_id)
	
);
-- COMENTARIOS
COMMENT ON TABLE hopes.tokens IS 'Tabla que representa la entidad tokens';
COMMENT ON COLUMN hopes.tokens.tok_id IS 'Columna que contiene el identificador del token. (pk)';
COMMENT ON COLUMN hopes.tokens.tok_value IS 'Columna que contiene el valor del token.';
COMMENT ON COLUMN hopes.tokens.tok_type IS 'Columna que contiene el tipo del token';
COMMENT ON COLUMN hopes.tokens.tok_token_creation_date IS 'Columna que contiene la fecha de creación del token';
COMMENT ON COLUMN hopes.tokens.tok_token_expiration_date IS 'Columna que contiene la fecha de experación deltoken';
COMMENT ON CONSTRAINT tok_id_pk ON hopes.tokens IS 'pk de la tabla tokens';
COMMENT ON CONSTRAINT tok_usr_id_fk ON hopes.tokens IS 'fk Relacion con la tabla User';

-- ddl-end --