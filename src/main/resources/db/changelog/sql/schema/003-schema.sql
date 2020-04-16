-- object: public.genders | type: TABLE --
DROP TABLE IF EXISTS public.genders CASCADE;
CREATE TABLE public.genders (
	gnd_code varchar(1) NOT NULL,
	gnd_name varchar(9) NOT NULL,
	CONSTRAINT gnd_code_pk PRIMARY KEY (gnd_code)
);
-- COMENTARIOS
COMMENT ON TABLE public.genders IS 'Tabla catálogo con todos los sexos';
COMMENT ON COLUMN public.genders.gnd_code IS 'Columna que contiene el código identificativo del sexo de los pacientes (pk)';
COMMENT ON COLUMN public.genders.gnd_name IS 'Columna con el nombre de los distintos sexos';
COMMENT ON CONSTRAINT gnd_code_pk ON public.genders IS 'pk de la tabla GENDERS';
-- ddl-end --

-- object: public.patients | type: TABLE --
DROP TABLE IF EXISTS public.patients CASCADE;
CREATE TABLE public.patients (
	pac_id serial,
	pac_hos_id smallint,
	pac_name varchar(50) NOT NULL,
	pac_first_surname varchar(50) NOT NULL,
	pac_last_surname varchar(50),
	pac_nhc varchar(50) NOT NULL,
	pac_health_card varchar(50) NOT NULL,
	pac_dni varchar(9),
	pac_address varchar(200),
	pac_phone varchar(15),
	pac_email varchar(50),
	pac_gender_code varchar(1),
	pac_birth_date timestamp,
	CONSTRAINT pac_id_pk PRIMARY KEY (pac_id),
	CONSTRAINT pac_nhc_unique UNIQUE (pac_nhc),
	CONSTRAINT pac_health_card_unique UNIQUE (pac_health_card),
    CONSTRAINT pac_hos_id_fk FOREIGN KEY (pac_hos_id) REFERENCES public.hospitals(hos_id),
	CONSTRAINT pac_gender_code_fk FOREIGN KEY (pac_gender_code) REFERENCES public.genders(gnd_code)
);
-- COMENTARIOS
COMMENT ON TABLE public.patients IS 'Tabla que representa la entidad PATIENTS';
COMMENT ON COLUMN public.patients.pac_id IS 'Columna que contiene el identificador del paciente en la bbdd de la aplicación. (pk)';
COMMENT ON COLUMN public.patients.pac_hos_id IS 'Columna que contiene el identificador del hospital en el que está dado de alta el paciente en la bbdd de la aplicación.';
COMMENT ON COLUMN public.patients.pac_name IS 'Columna que contiene el nombre del paciente';
COMMENT ON COLUMN public.patients.pac_first_surname IS 'Columna que contiene el primer apellido del paciente';
COMMENT ON COLUMN public.patients.pac_last_surname IS 'Columna que contiene el segundo apellido del paciente';
COMMENT ON COLUMN public.patients.pac_health_card IS  'Columna que contiene el número de tarjeta sanitaria del paciente';
COMMENT ON COLUMN public.patients.pac_dni IS 'Columna que contiene el documento nacional de identidad del paciente';
COMMENT ON COLUMN public.patients.pac_address IS 'Columna que contiene la direccion  del domicilio del paciente';
COMMENT ON COLUMN public.patients.pac_phone IS 'Columna que contiene el número de teléfono fijo facilitado por el paciente';
COMMENT ON COLUMN public.patients.pac_email IS  'Columna que contiene el email facilitado por el paciente';
COMMENT ON COLUMN public.patients.pac_gender_code IS  'Columna que contiene el código del sexo del paciente';
COMMENT ON COLUMN public.patients.pac_birth_date IS 'Columna que contiene la fecha de nacimiento del paciente';
COMMENT ON CONSTRAINT pac_id_pk ON public.patients IS 'pk de la tabla PATIENTS';
COMMENT ON CONSTRAINT pac_hos_id_fk ON public.patients IS 'fk Relacion con la tabla HOSPITALS';
COMMENT ON CONSTRAINT pac_gender_code_fk ON public.patients IS 'fk Relacion con la tabla GENDERS';
-- ddl-end --

-- object: public.sections_forms | type: TABLE --
DROP TABLE IF EXISTS public.sections_roles CASCADE;
CREATE TABLE public.sections_roles (
	scr_id serial,
	scr_section_id smallint NOT NULL,
	scr_form_id smallint NOT NULL,
	CONSTRAINT scr_id_pk PRIMARY KEY (scr_id),
	CONSTRAINT scr_section_id_fk FOREIGN KEY (scr_section_id) REFERENCES public.sections(sec_id),
	CONSTRAINT scr_form_id_fk FOREIGN KEY (scr_form_id) REFERENCES public.roles(rol_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.sections_roles IS 'Tabla intermedia que representa la relación entre las entidades SECTIONS y FORMS';
COMMENT ON COLUMN public.sections_roles.scr_id IS 'Columna con el identificador entre un formulario y un apartado';
COMMENT ON COLUMN public.sections_roles.scr_section_id IS 'Columna que contiene el identificador del apartado de la patologia';
COMMENT ON COLUMN public.sections_roles.scr_form_id IS 'Columna que contiene el identificador del formulario dentro del apartado';
COMMENT ON CONSTRAINT scr_id_pk ON public.sections_roles IS 'pk de la tabla SECTIONS_FORMS';
COMMENT ON CONSTRAINT scr_section_id_fk ON public.sections_roles IS 'fk Relacion con la tabla SECTIONS';
COMMENT ON CONSTRAINT scr_form_id_fk ON public.sections_roles IS 'fk Relacion con la tabla ROLES';
-- ddl-end --
