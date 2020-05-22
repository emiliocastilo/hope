-- object: hopes.medicines | type: TABLE --
ALTER TABLE hopes.medicines ADD COLUMN med_biologic boolean NOT NULL DEFAULT false;
-- ddl-end --