ALTER TABLE hopes.indications ALTER COLUMN ind_description TYPE varchar(100) USING ind_description::varchar;
COMMENT ON COLUMN hopes.indications.ind_description IS 'Descripción de la indicación con el name que hay en front';
