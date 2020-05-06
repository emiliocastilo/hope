-- object: public.medicines | type: COLUMN --
ALTER TABLE hopes.medicines ADD COLUMN med_acronym varchar(15) not null;
ALTER TABLE hopes.medicines ADD COLUMN med_national_code varchar(7) unique;
ALTER TABLE hopes.medicines ADD COLUMN med_description varchar(150);
ALTER TABLE hopes.medicines ADD COLUMN med_presentation varchar(500);
ALTER TABLE hopes.medicines ADD COLUMN med_commercialization boolean default false;
ALTER TABLE hopes.medicines ADD COLUMN date_created timestamp NOT NULL;
ALTER TABLE hopes.medicines ADD COLUMN date_updated timestamp NOT NULL;

COMMENT ON COLUMN hopes.medicines.med_acronym IS 'Columna que contiene el acrónimo del medicamento';
COMMENT ON COLUMN hopes.medicines.med_national_code IS 'Columna que contiene el código nacional del medicamento';
COMMENT ON COLUMN hopes.medicines.med_description IS 'Columna que contiene la descripción del medicamento';
COMMENT ON COLUMN hopes.medicines.med_presentation IS 'Columna que contiene la presentación del medicamento';
COMMENT ON COLUMN hopes.medicines.med_commercialization IS 'Columna que indica si el medicamento se está comercializando';
COMMENT ON COLUMN hopes.medicines.date_created IS 'Columna que contiene la fecha de alta del medicamento en el sistema';
COMMENT ON COLUMN hopes.medicines.date_updated IS 'Columna que contiene la fecha de la última modificación sufrida en los datos del medicamento';

-- object: public.dispensations | type: COLUMN --
ALTER TABLE hopes.dispensations ADD COLUMN date_created timestamp NOT NULL default CURRENT_TIMESTAMP;
ALTER TABLE hopes.dispensations ADD COLUMN date_updated timestamp NOT NULL default CURRENT_TIMESTAMP;
COMMENT ON COLUMN hopes.dispensations.date_created IS 'Columna que contiene la fecha de alta del medicamento en el sistema';
COMMENT ON COLUMN hopes.dispensations.date_updated IS 'Columna que contiene la fecha de la última modificación sufrida en los datos del medicamento';

-- object: public.dispensation_details | type: COLUMN --
ALTER TABLE hopes.dispensation_details ADD COLUMN date_created timestamp NOT NULL default CURRENT_TIMESTAMP;
ALTER TABLE hopes.dispensation_details ADD COLUMN date_updated timestamp NOT NULL default CURRENT_TIMESTAMP;
COMMENT ON COLUMN hopes.dispensation_details.date_created IS 'Columna que contiene la fecha de alta del medicamento en el sistema';
COMMENT ON COLUMN hopes.dispensation_details.date_updated IS 'Columna que contiene la fecha de la última modificación sufrida en los datos del medicamento';