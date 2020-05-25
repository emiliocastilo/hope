-- object: hopes.medicines | type: TABLE --
ALTER TABLE hopes.medicines ADD COLUMN med_biologic boolean NOT NULL DEFAULT false;
COMMENT ON COLUMN hopes.medicines.med_biologic IS 'Columna indicador de si un medicamentoes biológico.';

-- ddl-end --