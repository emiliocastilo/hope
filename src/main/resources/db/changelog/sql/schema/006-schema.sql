ALTER TABLE hopes.forms ADD for_template varchar(100) NULL;
COMMENT ON COLUMN hopes.forms.for_template IS 'Columna que relaciona el formulario con una plantilla de la bbdd no relacional';
