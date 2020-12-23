ALTER TABLE hopes.indications DROP COLUMN IF EXISTS ind_code;

ALTER TABLE hopes.indications ADD COLUMN ind_code varchar(100);
COMMENT ON COLUMN hopes.indications.ind_code IS 'Código de la indicación. Será inmutable y único.';

ALTER TABLE hopes.indications ADD CONSTRAINT ind_code_unique UNIQUE (ind_code);
