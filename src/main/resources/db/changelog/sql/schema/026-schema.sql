ALTER TABLE hopes.sections ADD sec_visible boolean NOT NULL DEFAULT true;
COMMENT ON COLUMN hopes.sections.sec_visible IS 'Columna que indica si una sección es visible o no';
