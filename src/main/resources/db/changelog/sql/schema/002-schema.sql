-- object: public.services | type: TABLE --
DROP TABLE IF EXISTS public.services CASCADE;
CREATE TABLE public.services (
	srv_id serial,
	srv_name varchar(50) NOT NULL,
	srv_description varchar(100) ,
	CONSTRAINT srv_id_pk PRIMARY KEY (srv_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.services IS 'Tabla catálogo con todos los servicios del hospital';
COMMENT ON COLUMN public.services.srv_id IS 'Columna que contiene el código identificativo del servicio';
COMMENT ON COLUMN public.services.srv_name IS 'Columna con el nombre de los distintos servicios del hospital';
COMMENT ON COLUMN public.services.srv_description IS 'Columna que contiene una descripción detallada del servicio del hospital';
COMMENT ON CONSTRAINT srv_id_pk ON public.services IS 'pk de la tabla SERVICES';
-- ddl-end --

-- object: public.doctors | type: TABLE --
DROP TABLE IF EXISTS public.doctors CASCADE;
CREATE TABLE public.doctors(
	dct_id serial,
	dct_name varchar(50) NOT NULL,
	dct_surname varchar(100) NOT NULL,
	dct_phone varchar(11) NOT NULL,
	dct_dni varchar(9) NOT NULL,
	dct_college_number smallint NOT NULL,
	dct_service_id smallint NOT NULL,
	dct_active boolean NOT NULL,
	dct_user_id smallint,
	dct_date_create timestamp,
	dct_date_modify timestamp,
	CONSTRAINT dct_id_pk PRIMARY KEY (dct_id),
	CONSTRAINT dct_srv_id_fk FOREIGN KEY (dct_service_id) REFERENCES public.services(srv_id),
    CONSTRAINT dct_usr_id_fk FOREIGN KEY (dct_user_id) REFERENCES public.users(usr_id)
);

-- COMENTARIOS
COMMENT ON TABLE public.doctors IS 'Tabla que representa la entidad doctors';
COMMENT ON COLUMN public.doctors.dct_id IS 'Columna con el id de los datos del médico(pk)';
COMMENT ON COLUMN public.doctors.dct_name IS 'Columna que contiene el nombre del médico';
COMMENT ON COLUMN public.doctors.dct_surname IS 'Columna que contiene los apellidos del médico';
COMMENT ON COLUMN public.doctors.dct_phone IS 'Columna que contiene el número de teléfono del médico';
COMMENT ON COLUMN public.doctors.dct_dni IS 'Columna que contiene el dni del médico';
COMMENT ON COLUMN public.doctors.dct_college_number IS 'Columna que contiene el número de colegiado del médico';
COMMENT ON COLUMN public.doctors.dct_service_id IS 'Columna que contiene el identificador del servicio al que pertenece el médico';
COMMENT ON COLUMN public.doctors.dct_active IS 'Columna que indica que el médico está dado de alta en el sistema';
COMMENT ON COLUMN public.doctors.dct_user_id IS 'Columna que contiene el identificador del usuario del médico';
COMMENT ON COLUMN public.doctors.dct_date_create IS 'Columna que contiene la fecha de alta del médico en el sistema';
COMMENT ON COLUMN public.doctors.dct_date_modify IS 'Columna que contiene la fecha de la última modificación sufrida en los datos del médico de la tabla doctors';
COMMENT ON CONSTRAINT dct_id_pk ON public.doctors IS 'pk de la tabla DOCTORS';
COMMENT ON CONSTRAINT dct_srv_id_fk ON public.doctors IS 'fk Relacion con la tabla SERVICES';
COMMENT ON CONSTRAINT dct_usr_id_fk ON public.doctors IS 'fk Relacion con la tabla USERS';
-- ddl-end --

-- USERS. Se agrega nuevo campo para deshabilitar el usuario.
ALTER TABLE public.users DROP COLUMN IF EXISTS usr_active;
ALTER TABLE public.users ADD COLUMN usr_active boolean NOT NULL DEFAULT true;
COMMENT ON COLUMN public.users.usr_active IS 'Columna que indica si el usuario está activo en el sistema';

DROP TABLE IF EXISTS public.recommendations CASCADE;
CREATE TABLE public.recommendations (
	rec_id serial,
	rec_value varchar(50) NOT NULL,
	CONSTRAINT rec_id_pk PRIMARY KEY (rec_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.recommendations IS 'Tabla catálogo recomendations, son las opciones de las recomendaciones de un medicamento';
COMMENT ON COLUMN public.recommendations.rec_id IS 'Columna que contiene el código identificativo de la recomendacion';
COMMENT ON COLUMN public.recommendations.rec_value IS 'Columna con los distintos valores de recomendaciones para un medicamento';
COMMENT ON CONSTRAINT rec_id_pk ON public.recommendations IS 'pk de la tabla recommendations';
-- ddl-end --

-- object: public.medicines | type: TABLE --
DROP TABLE IF EXISTS public.medicines CASCADE;
CREATE TABLE public.medicines(
	med_id serial,
	med_act_ingredients varchar(500) NOT NULL,
	med_code_act varchar(7) NOT NULL,
	med_recommended boolean DEFAULT false,
	med_recommendation_id smallint,
	CONSTRAINT med_id_pk PRIMARY KEY (med_id),
	CONSTRAINT med_recommendation_id_fk FOREIGN KEY (med_recommendation_id) REFERENCES public.recommendations(rec_id)
);

-- COMENTARIOS
COMMENT ON TABLE public.medicines IS 'Tabla que representa la entidad medicines';
COMMENT ON COLUMN public.medicines.med_id IS 'Columna con el id de los datos del medicamento(pk)';
COMMENT ON COLUMN public.medicines.med_act_ingredients IS 'Columna que contiene el principio activo del medicamento';
COMMENT ON COLUMN public.medicines.med_code_act IS 'Columna que contiene el código ACT';
COMMENT ON COLUMN public.medicines.med_recommended IS 'Columna que indica si es o no recomendado el medicamento';
COMMENT ON COLUMN public.medicines.med_recommendation_id IS 'Columna que contiene valor de la recomendación';
COMMENT ON CONSTRAINT med_id_pk ON public.medicines IS 'pk de la tabla medicines';
COMMENT ON CONSTRAINT med_recommendation_id_fk ON public.medicines IS 'fk Relacion con la tabla recommendations';
-- ddl-end --

-- ALTER TABLE public.sections DROP COLUMN IF EXISTS sec_role_id;
-- ALTER TABLE public.sections  ADD COLUMN sec_role_id bigint DEFAULT sec_role_id;
-- COMMENT ON COLUMN public.sections.sec_role_id IS 'Columna que contiene el identificador del rol para ver si tiene o no permisos';

ALTER TABLE public.sections DROP COLUMN IF EXISTS sec_icon;
ALTER TABLE public.sections ADD COLUMN sec_icon character varying(100);

COMMENT ON COLUMN public.sections.sec_icon IS 'Columna que incluye el nombre del icono';

ALTER TABLE public.sections DROP COLUMN IF EXISTS sec_url;
ALTER TABLE public.sections ADD COLUMN sec_url character varying(100);

COMMENT ON COLUMN public.sections.sec_url IS 'Columna que incluye el path dentro del árbol de secciones de menus';