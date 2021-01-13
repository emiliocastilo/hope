ALTER TABLE hopes.medicines DROP COLUMN IF EXISTS med_code_act;

ALTER TABLE hopes.medicines ADD COLUMN med_code_act varchar(50);
COMMENT ON COLUMN hopes.medicines.med_code_act IS 'Código actualizado medicamento';