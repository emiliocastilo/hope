ALTER TABLE forms ADD for_template varchar(100) NULL;
COMMENT ON COLUMN forms.for_template IS 'Columna que relaciona el formulario con una plantilla de la bbdd no relacional';
