ALTER TABLE hopes.medicines ADD COLUMN med_content VARCHAR(150);
ALTER TABLE hopes.medicines ADD COLUMN med_authorization_date DATE;
ALTER TABLE hopes.medicines ADD COLUMN med_authorized BOOLEAN DEFAULT FALSE NOT NULL;
ALTER TABLE hopes.medicines ADD COLUMN med_end_date_authorization DATE;
ALTER TABLE hopes.medicines ADD COLUMN med_commercialization_date DATE;
ALTER TABLE hopes.medicines ADD COLUMN med_end_date_commercialization DATE;
ALTER TABLE hopes.medicines ADD COLUMN med_via_administration VARCHAR(150);
ALTER TABLE hopes.medicines ADD COLUMN med_brand VARCHAR(50);
ALTER TABLE hopes.medicines ADD COLUMN med_units INT;
ALTER TABLE hopes.medicines ADD COLUMN med_pvl decimal(13,2);
ALTER TABLE hopes.medicines ADD COLUMN med_pvl_unitary decimal(13,2);
ALTER TABLE hopes.medicines ADD COLUMN med_pvp decimal(13,2);
ALTER TABLE hopes.medicines ADD COLUMN med_pathology VARCHAR(150);
ALTER TABLE hopes.medicines ADD COLUMN med_family VARCHAR(150);
ALTER TABLE hopes.medicines ADD COLUMN med_subfamily VARCHAR(150);

ALTER TABLE hopes.medicines DROP COLUMN med_recommendation_id;
ALTER TABLE hopes.medicines DROP COLUMN med_recommended;

-- object: hopes.doses | type: TABLE --
DROP TABLE IF EXISTS hopes.doses CASCADE;
CREATE TABLE hopes.doses (
	dos_id serial,
	dos_code_atc varchar(7) NOT NULL,
	dos_description varchar(150),
	dos_dose_indicated varchar(150),
	dos_recommendation varchar(200),
    date_created timestamp,
    date_updated timestamp,
	CONSTRAINT dos_id_pk PRIMARY KEY (dos_id)
);
-- COMENTARIOS
COMMENT ON TABLE hopes.doses IS 'Tabla que representa la entidad hospitales';
COMMENT ON COLUMN hopes.doses.dos_id IS 'Columna con el id de base de datos de la dosis(pk)';
COMMENT ON COLUMN hopes.doses.dos_code_atc IS 'Columna que contiene el código ATC';
COMMENT ON COLUMN hopes.doses.dos_description IS 'Columna que contiene el nombre del hospital';
COMMENT ON COLUMN hopes.doses.dos_dose_indicated IS 'Columna que contiene el nombre del hospital';
COMMENT ON COLUMN hopes.doses.dos_recommendation IS 'Columna que contiene valor de la recomendación';
COMMENT ON CONSTRAINT dos_id_pk ON hopes.doses IS 'pk de la tabla DOSES';