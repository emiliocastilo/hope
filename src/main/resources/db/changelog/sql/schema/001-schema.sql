------------------------------------------------- INICIO TABLAS --------------------------------------------------------------------------------------
-- object: public.hospitals | type: TABLE --
DROP TABLE IF EXISTS public.hospitals CASCADE;
CREATE TABLE public.hospitals (
	hos_id serial,
	hos_name varchar(100) NOT NULL,
	CONSTRAINT hos_id_pk PRIMARY KEY (hos_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.hospitals IS 'Tabla que representa la entidad hospitales';
COMMENT ON COLUMN public.hospitals.hos_id IS 'Columna con el id de base de datos del hospital(pk)';
COMMENT ON COLUMN public.hospitals.hos_name IS 'Columna que contiene el nombre del hospital';
COMMENT ON CONSTRAINT hos_id_pk ON public.hospitals IS 'pk de la tabla HOSPITALS';
-- ddl-end --

-- object: public.genders | type: TABLE --
DROP TABLE IF EXISTS public.genders CASCADE;
CREATE TABLE public.genders (
	gender_code varchar(1) NOT NULL,
	gender_name varchar(9) NOT NULL,
	CONSTRAINT gender_code_pk PRIMARY KEY (gender_code)
);
-- COMENTARIOS
COMMENT ON TABLE public.genders IS 'Tabla catálogo con todos los sexos';
COMMENT ON COLUMN public.genders.gender_code IS 'Columna que contiene el código identificativo del sexo de los pacientes (pk)';
COMMENT ON COLUMN public.genders.gender_name IS 'Columna con el nombre de los distintos sexos';
COMMENT ON CONSTRAINT gender_code_pk ON public.genders IS 'pk de la tabla GENDERS';
-- ddl-end --

-- object: public.countries | type: TABLE --
DROP TABLE IF EXISTS public.countries CASCADE;
CREATE TABLE public.countries (
	pai_code varchar(3) NOT NULL,
	pai_name varchar(60) NOT NULL,
	CONSTRAINT pai_code_pk PRIMARY KEY (pai_code)
);
-- COMENTARIOS
COMMENT ON TABLE public.countries IS 'Tabla catálogo con todos los países';
COMMENT ON COLUMN public.countries.pai_code IS 'Columna que contiene el código ISO de 3 letras asignado a cada país o territorio. (pk)';
COMMENT ON COLUMN public.countries.pai_name IS 'Columna con el nombre del país o territorio';
COMMENT ON CONSTRAINT pai_code_pk ON public.countries IS 'pk de la tabla COUNTRIES';
-- ddl-end --

-- object: public.patients | type: TABLE --
DROP TABLE IF EXISTS public.patients CASCADE;
CREATE TABLE public.patients (
	pac_id serial,
	pac_hos_id smallint,
	pac_name varchar(50) NOT NULL,
	pac_surname1 varchar(50) NOT NULL,
	pac_surname2 varchar(50),
	pac_nhc varchar(50) NOT NULL,
	pac_health_card varchar(50) NOT NULL,
	pac_cip varchar(50),
	pac_dni varchar(9),
	pac_address varchar(100),
	pac_city varchar(50),
	pac_state varchar(50),
	pac_cp varchar(50),
	pac_phone varchar(15),
	pac_mobile varchar(15),
	pac_email varchar(50),
	pac_code_gender varchar(1),
	pac_date_birth timestamp,
	pac_code_country varchar(3),
	pac_date_next_vis timestamp,
	pac_date_last_vis timestamp,
	pac_date_last_acc timestamp,
	CONSTRAINT pac_id_pk PRIMARY KEY (pac_id),
	CONSTRAINT pac_nhc_unique UNIQUE (pac_nhc),
	CONSTRAINT pac_cip_unique UNIQUE (pac_cip),
	CONSTRAINT pac_health_card_unique UNIQUE (pac_health_card),
    CONSTRAINT pac_hos_id_fk FOREIGN KEY (pac_hos_id) REFERENCES public.hospitals(hos_id),
	CONSTRAINT pac_code_gender_fk FOREIGN KEY (pac_code_gender) REFERENCES public.genders(gender_code),
	CONSTRAINT pac_code_country_fk FOREIGN KEY (pac_code_country) REFERENCES public.countries(pai_code)
);
-- COMENTARIOS
COMMENT ON TABLE public.patients IS 'Tabla que representa la entidad PATIENTS';
COMMENT ON COLUMN public.patients.pac_id IS 'Columna que contiene el identificador del paciente en la bbdd de la aplicación. (pk)';
COMMENT ON COLUMN public.patients.pac_hos_id IS 'Columna que contiene el identificador del hospital en el que está dado de alta el paciente en la bbdd de la aplicación.';
COMMENT ON COLUMN public.patients.pac_name IS 'Columna que contiene el nombre del paciente';
COMMENT ON COLUMN public.patients.pac_surname1 IS 'Columna que contiene el primer apellido del paciente';
COMMENT ON COLUMN public.patients.pac_surname2 IS 'Columna que contiene el segundo apellido del paciente';
COMMENT ON COLUMN public.patients.pac_health_card IS  'Columna que contiene el número de tarjeta sanitaria del paciente';
COMMENT ON COLUMN public.patients.pac_cip IS  'Columna que contiene código de identificación personal en el Sistema de Información de Tarjeta Sanitaria';
COMMENT ON COLUMN public.patients.pac_dni IS 'Columna que contiene el documento nacional de identidad del paciente';
COMMENT ON COLUMN public.patients.pac_address IS 'Columna que contiene la direccion  del domicilio del paciente';
COMMENT ON COLUMN public.patients.pac_city IS  'Columna que contiene la localidad de la dirección del domicilio del paciente';
COMMENT ON COLUMN public.patients.pac_state IS 'Columna que contiene la provincia de la dirección del domicilio del paciente';
COMMENT ON COLUMN public.patients.pac_cp IS 'Columna que contiene el código postal de la localidad de la dirección del domicilio del paciente';
COMMENT ON COLUMN public.patients.pac_phone IS 'Columna que contiene el número de teléfono fijo facilitado por el paciente';
COMMENT ON COLUMN public.patients.pac_mobile IS 'Columna que contiene el número de teléfono móvile facilitado por el paciente';
COMMENT ON COLUMN public.patients.pac_email IS  'Columna que contiene el email facilitado por el paciente';
COMMENT ON COLUMN public.patients.pac_code_gender IS  'Columna que contiene el código del sexo del paciente';
COMMENT ON COLUMN public.patients.pac_date_birth IS 'Columna que contiene la fecha de nacimiento del paciente';
COMMENT ON COLUMN public.patients.pac_code_country IS 'Columna que contiene el código del país de nacimiento del paciente';
COMMENT ON COLUMN public.patients.pac_date_next_vis IS 'Columna que contiene la fecha de la próxima visita del paciente';
COMMENT ON COLUMN public.patients.pac_date_last_vis IS 'Columna que contiene la fecha de la última visita del paciente';
COMMENT ON COLUMN public.patients.pac_date_last_acc IS 'Columna que contiene la fecha de la última vez que fue seleccionado el paciente para cualquier tipo de consulta en el sistema';
COMMENT ON CONSTRAINT pac_id_pk ON public.patients IS 'pk de la tabla PATIENTS';
COMMENT ON CONSTRAINT pac_hos_id_fk ON public.patients IS 'fk Relacion con la tabla HOSPITALS';
COMMENT ON CONSTRAINT pac_code_gender_fk ON public.patients IS 'fk Relacion con la tabla GENDERS';
COMMENT ON CONSTRAINT pac_code_country_fk ON public.patients IS 'fk  Relacion con la tabla COUNTRIES';
-- ddl-end --


-- object: public.pathologies | type: TABLE --
DROP TABLE IF EXISTS public.pathologies CASCADE;
CREATE TABLE public.pathologies (
	pth_id serial,
	pth_name varchar(100) NOT NULL,
	pth_description varchar(500),
	CONSTRAINT pth_id_pk PRIMARY KEY (pth_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.pathologies IS 'Tabla que representa la entidad PATHOLOGY';
COMMENT ON COLUMN public.pathologies.pth_id IS 'Columna con el id de base de datos del patologia(pk)';
COMMENT ON COLUMN public.pathologies.pth_name IS 'Columna que contiene el nombre de la patologia';
COMMENT ON COLUMN public.pathologies.pth_description IS 'Columna que contiene la descripcion detallada de la patologia';
COMMENT ON CONSTRAINT pth_id_pk ON public.pathologies IS 'pk de la tabla PATHOLOGIES';
-- ddl-end --


-- object: public.patients_pathologies | type: TABLE --
DROP TABLE IF EXISTS public.patients_pathologies CASCADE;
CREATE TABLE public.patients_pathologies (
	pcp_id serial,
	pcp_pac_id bigint NOT NULL,
	pcp_pth_id smallint NOT NULL,
	CONSTRAINT pcp_id_pk PRIMARY KEY (pcp_id),
	CONSTRAINT pcp_pac_id_fk FOREIGN KEY (pcp_pac_id) REFERENCES public.patients(pac_id),
	CONSTRAINT pcp_pth_id_fk FOREIGN KEY (pcp_pth_id) REFERENCES public.pathologies(pth_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.patients_pathologies IS 'Tabla intermedia que representa la relación entre las entidades PATIENTS y PATHOLOGIES';
COMMENT ON COLUMN public.patients_pathologies.pcp_id IS 'Columna con el id de base de datos de la relación entre las tablas pacientes y patologias(pk)';
COMMENT ON COLUMN public.patients_pathologies.pcp_pac_id IS 'Columna con el id del paciente';
COMMENT ON COLUMN public.patients_pathologies.pcp_pth_id IS 'Columna con el id de la patologia';
COMMENT ON CONSTRAINT pcp_id_pk ON public.patients_pathologies IS 'pk de la tabla PATIENTS_PATHOLOGIES';
COMMENT ON CONSTRAINT pcp_pac_id_fk ON public.patients_pathologies IS 'fk Relacion con la tabla PATIENTS';
COMMENT ON CONSTRAINT pcp_pth_id_fk ON public.patients_pathologies IS 'fk Relacion con la tabla PATHOLOGIES';
-- ddl-end --

-- object: public.hospitals_pathologies | type: TABLE --
DROP TABLE IF EXISTS public.hospitals_pathologies CASCADE;
CREATE TABLE public.hospitals_pathologies (
	hsp_id serial,
	hsp_hos_id smallint NOT NULL,
	hsp_pth_id smallint NOT NULL,
	CONSTRAINT hsp_id_pk PRIMARY KEY (hsp_id),
	CONSTRAINT hsp_hos_id_fk FOREIGN KEY (hsp_hos_id) REFERENCES public.hospitals(hos_id),
	CONSTRAINT hsp_pth_id_fk FOREIGN KEY (hsp_pth_id) REFERENCES public.pathologies(pth_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.hospitals_pathologies IS 'Tabla intermedia que representa la relación entre las entidades HOSPITALS y PATHOLOGIES';
COMMENT ON COLUMN public.hospitals_pathologies.hsp_id IS 'Columna con el identificador de base de datos de la relación entre las tablas HOSPITALS y PATHOLOGIES(pk)';
COMMENT ON COLUMN public.hospitals_pathologies.hsp_hos_id IS 'Columna con el id del hospital';
COMMENT ON COLUMN public.hospitals_pathologies.hsp_pth_id IS 'Columna con el id de la patologia';
COMMENT ON CONSTRAINT hsp_id_pk ON public.hospitals_pathologies IS 'pk de la tabla HOSPITALS_PATHOLOGIES';
COMMENT ON CONSTRAINT hsp_hos_id_fk ON public.hospitals_pathologies IS 'fk Relacion con la tabla HOSPITALS';
COMMENT ON CONSTRAINT hsp_pth_id_fk ON public.hospitals_pathologies IS 'fk Relacion con la tabla PATHOLOGIES';
-- ddl-end --

-- object: public.sections | type: TABLE --
DROP TABLE IF EXISTS public.sections CASCADE;
CREATE TABLE public.sections (
	sec_id serial,
	sec_title varchar(50) NOT NULL,
	sec_description varchar(500),
	sec_menu boolean,
	sec_order smallint NOT NULL,
	sec_section_root smallint,
	CONSTRAINT sec_id_pk PRIMARY KEY (sec_id),
	CONSTRAINT sec_section_root_fk FOREIGN KEY (sec_section_root) REFERENCES public.sections(sec_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.sections IS 'Tabla que representa la entidad SECTIONS. Se consideran apartados tanto las opciones de menu como cualquier elemento hijo que cuelgue de él';
COMMENT ON COLUMN public.sections.sec_id IS 'Columna con el id de base de datos del patologia(pk)';
COMMENT ON COLUMN public.sections.sec_title IS 'Columna que contiene el titulo del apartado del menu de la patologia';
COMMENT ON COLUMN public.sections.sec_description IS 'Columna que contiene la descripcion detallada del apartado del menu de la patologia';
COMMENT ON COLUMN public.sections.sec_menu IS 'Columna que indica sí este apartado forma parte del menu de la patologia';
COMMENT ON COLUMN public.sections.sec_order IS 'Columna que indica el orden del apartado dentro del menu/listado de apartados de la patologia';
COMMENT ON COLUMN public.sections.sec_section_root IS 'Columna que contiene el identificador del apartado padre en el que estaría incluido creando un arbol de apartados dentro del menú de la patologia';
COMMENT ON CONSTRAINT sec_id_pk ON public.sections IS 'pk de la tabla SECTIONS';
COMMENT ON CONSTRAINT sec_section_root_fk ON public.sections IS 'fk Relacion con la tabla SECTIONS';
-- ddl-end --

-- object: public.forms | type: TABLE --
DROP TABLE IF EXISTS public.forms CASCADE;
CREATE TABLE public.forms (
	for_id serial,
	for_title varchar(50) NOT NULL,
	for_description varchar(500),
	CONSTRAINT for_id_pk PRIMARY KEY (for_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.forms IS 'Tabla intermedia que representa la relación entre las entidades SECTIONS y FORMS';
COMMENT ON COLUMN public.forms.for_id IS 'Columna que contiene el título del formulario';
COMMENT ON COLUMN public.forms.for_title IS 'Columna que contiene la descripcion detallada del formulario';
COMMENT ON COLUMN public.forms.for_description IS 'Columna que contiene el identificador del formulario dentro del apartado';
COMMENT ON CONSTRAINT for_id_pk ON public.forms IS 'pk de la tabla FORMS';
-- ddl-end --

-- object: public.sections_forms | type: TABLE --
DROP TABLE IF EXISTS public.sections_forms CASCADE;
CREATE TABLE public.sections_forms (
	scf_id serial,
	scf_section_id smallint NOT NULL,
	scf_form_id smallint NOT NULL,
	scf_order smallint,
	CONSTRAINT scf_id_pk PRIMARY KEY (scf_id),
	CONSTRAINT scf_section_id_fk FOREIGN KEY (scf_section_id) REFERENCES public.sections(sec_id),
	CONSTRAINT scf_form_id_fk FOREIGN KEY (scf_form_id) REFERENCES public.forms(for_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.sections_forms IS 'Tabla intermedia que representa la relación entre las entidades SECTIONS y FORMS';
COMMENT ON COLUMN public.sections_forms.scf_id IS 'Columna con el identificador entre un formulario y un apartado';
COMMENT ON COLUMN public.sections_forms.scf_section_id IS 'Columna que contiene el identificador del apartado de la patologia';
COMMENT ON COLUMN public.sections_forms.scf_form_id IS 'Columna que contiene el identificador del formulario dentro del apartado';
COMMENT ON COLUMN public.sections_forms.scf_order IS 'Columna que indica el orden';
COMMENT ON CONSTRAINT scf_id_pk ON public.sections_forms IS 'pk de la tabla SECTIONS_FORMS';
COMMENT ON CONSTRAINT scf_section_id_fk ON public.sections_forms IS 'fk Relacion con la tabla SECTIONS';
COMMENT ON CONSTRAINT scf_form_id_fk ON public.sections_forms IS 'fk Relacion con la tabla FORMS';
-- ddl-end --

-- object: public.values_aux_tables | type: TABLE --
DROP TABLE IF EXISTS public.values_aux_tables CASCADE;
CREATE TABLE public.values_aux_tables (
	vat_id serial,
	vat_aux_table_id smallint NOT NULL,
	vat_aux_table_name varchar(50) NOT NULL,
	vat_aux_table_desc varchar(150),
	vat_value_id smallint NOT NULL,
	vat_value varchar(2000),
	vat_order smallint,
	vat_active boolean,
	CONSTRAINT vat_id_pk PRIMARY KEY (vat_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.values_aux_tables IS 'Tabla que representa la entidad VALUEES_TABLAS_AUXILIARES';
COMMENT ON COLUMN public.values_aux_tables.vat_id IS 'Columna que contiene el identificador de la relacion de la tabla auxiliar con su valor(pk)';
COMMENT ON COLUMN public.values_aux_tables.vat_aux_table_id IS 'Columna que contiene el identificador de la tabla auxiliar';
COMMENT ON COLUMN public.values_aux_tables.vat_aux_table_name IS 'Columna que contiene el nombre de la tabla auxiliar';
COMMENT ON COLUMN public.values_aux_tables.vat_aux_table_desc IS 'Columna que contiene la descripcion detallada de la tabla auxiliar';
COMMENT ON COLUMN public.values_aux_tables.vat_value_id IS 'Columna que contiene el identificador del valor';
COMMENT ON COLUMN public.values_aux_tables.vat_value IS 'Columna que contiene el valor de la tabla';
COMMENT ON COLUMN public.values_aux_tables.vat_order IS 'Columna que indica el orden del valor dentro de la tabla a la que hace referencia';
COMMENT ON COLUMN public.values_aux_tables.vat_active IS 'Columna que indica si la tabla está o no activa';
COMMENT ON CONSTRAINT vat_id_pk ON public.values_aux_tables IS 'pk de la tabla VALUES_AUX_TAB';
-- ddl-end --

-- object: public.roles | type: TABLE --
DROP TABLE IF EXISTS public.roles CASCADE;
CREATE TABLE public.roles (
	rol_id serial,
	rol_name varchar(50) NOT NULL,
	rol_description varchar(500),
	CONSTRAINT rol_id_pk PRIMARY KEY (rol_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.roles IS 'Tabla que representa la entidad ROLES';
COMMENT ON COLUMN public.roles.rol_id IS 'Columna que contiene el identificador del ROL';
COMMENT ON COLUMN public.roles.rol_name IS 'Columna que contiene el nombre por el que se conoce al rol';
COMMENT ON COLUMN public.roles.rol_description IS 'Columna que contiene una descripcion detallada del rol';
COMMENT ON CONSTRAINT rol_id_pk ON public.roles IS 'pk de la tabla ROLES';
-- ddl-end --

-- object: public.users | type: TABLE --
DROP TABLE IF EXISTS public.users CASCADE;
CREATE TABLE public.users (
	usr_id serial,
	usr_name varchar(50) NOT NULL,
	usr_password varchar(200) NOT NULL,
	usr_email varchar(50),
	usr_hos_id smallint NOT NULL,
    date_created timestamp NOT NULL,
    date_updated timestamp NOT NULL,
	CONSTRAINT usr_id_pk PRIMARY KEY (usr_id),
	CONSTRAINT usr_name_unique UNIQUE (usr_name),
	CONSTRAINT usr_email_unique UNIQUE (usr_email),
	CONSTRAINT usr_hos_id_fk FOREIGN KEY (usr_hos_id) REFERENCES public.hospitals(hos_id)

);
-- COMENTARIOS
COMMENT ON TABLE public.users IS 'Tabla que representa la entidad USERS';
COMMENT ON COLUMN public.users.usr_id IS 'Columna que contiene el identificador del usuario en la base de datos de la aplicación';
COMMENT ON COLUMN public.users.usr_name IS 'Columna que contiene el nombre de acceso del usuario en el sistema';
COMMENT ON COLUMN public.users.usr_password IS 'Columna que contiene la contraseña de acceso del usuario';
COMMENT ON COLUMN public.users.usr_email IS 'Columna que contiene el email del usuario al que enviar información desde el sistema';
COMMENT ON COLUMN public.users.usr_hos_id IS 'Columna que contiene el identificador del hospital al que pertenece el usuario';
COMMENT ON COLUMN public.users.date_created IS 'Columna que contiene la fecha de creación del usuario';
COMMENT ON COLUMN public.users.date_updated IS 'Columna que contiene la fecha de la última modificación sufrida en el usuario';
COMMENT ON CONSTRAINT usr_id_pk ON public.users IS 'pk de la tabla USERS';
COMMENT ON CONSTRAINT usr_hos_id_fk ON public.users IS 'fk Relacion con la tabla HOSPITALS';
-- ddl-end --

-- object: public.roles | type: TABLE --
DROP TABLE IF EXISTS public.users_roles CASCADE;
CREATE TABLE public.users_roles (
	uro_id serial,
	uro_user_id smallint NOT NULL,
	uro_rol_id smallint NOT NULL,
	CONSTRAINT uro_id_pk PRIMARY KEY (uro_id),
	CONSTRAINT uro_user_id_fk FOREIGN KEY (uro_user_id) REFERENCES public.users(usr_id),
	CONSTRAINT uro_rol_id_fk FOREIGN KEY (uro_rol_id) REFERENCES public.roles(rol_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.users_roles IS 'Tabla intermedia que representa la relación entre las entidades USERS Y ROLES';
COMMENT ON COLUMN public.users_roles.uro_id IS 'Columna que contiene el identificador de la relacion rol-usuario';
COMMENT ON COLUMN public.users_roles.uro_user_id IS 'Columna que contiene el identificador del usuario relacionado con el rol';
COMMENT ON COLUMN public.users_roles.uro_rol_id IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON CONSTRAINT uro_id_pk ON public.users_roles IS 'pk de la tabla USERS_ROLES';
COMMENT ON CONSTRAINT uro_user_id_fk ON public.users_roles IS 'fk Relacion con la tabla USERS';
COMMENT ON CONSTRAINT uro_rol_id_fk ON public.users_roles IS 'fk Relacion con la tabla ROLES';
-- ddl-end --

-- object: public.patients_forms | type: TABLE --
DROP TABLE IF EXISTS public.patients_forms CASCADE;
CREATE TABLE public.patients_forms (
	paf_id serial,
	paf_patient_id bigint NOT NULL,
	paf_form_id smallint NOT NULL,
	paf_user_id smallint NOT NULL,
	paf_date_crea timestamp,
	CONSTRAINT paf_id_pk PRIMARY KEY (paf_id),
	CONSTRAINT paf_patient_id_fk FOREIGN KEY (paf_patient_id) REFERENCES public.patients(pac_id),
	CONSTRAINT paf_form_id_fk FOREIGN KEY (paf_form_id) REFERENCES public.forms(for_id),
	CONSTRAINT paf_user_id_fk FOREIGN KEY (paf_user_id) REFERENCES public.users(usr_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.patients_forms IS 'Tabla intermedia que representa la relación entre las entidades PATIENTS y FORMS';
COMMENT ON COLUMN public.patients_forms.paf_id IS 'Columna que contiene el identificador de la relacion paciente-formulario-usuario';
COMMENT ON COLUMN public.patients_forms.paf_patient_id IS 'Columna que contiene el identificador del usuario relacionado con el rol';
COMMENT ON COLUMN public.patients_forms.paf_form_id IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON COLUMN public.patients_forms.paf_user_id IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON COLUMN public.patients_forms.paf_date_crea IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON CONSTRAINT paf_id_pk ON public.patients_forms IS 'pk de la tabla PATIENTS_FORMS';
COMMENT ON CONSTRAINT paf_patient_id_fk ON public.patients_forms IS 'fk Relacion con la tabla PATIENTS';
COMMENT ON CONSTRAINT paf_form_id_fk ON public.patients_forms IS 'fk Relacion con la tabla FORMS';
COMMENT ON CONSTRAINT paf_user_id_fk ON public.patients_forms IS 'fk Relacion con la tabla USERS';
-- ddl-end --

-- object: public.pat_forms_fields | type: TABLE --
DROP TABLE IF EXISTS public.patients_forms_fields CASCADE;
CREATE TABLE public.patients_forms_fields (
	pff_id serial,
	pff_paf_id bigint NOT NULL,
	pff_form_id smallint NOT NULL,
	pff_pac_id bigint NOT NULL,
	pff_field_id bigint NOT NULL,
	pff_value_txt VARCHAR(2000),
	pff_value_num smallint,
	pff_value_bool boolean,
	pff_value_data bytea,
	CONSTRAINT pff_id_pk PRIMARY KEY (pff_id),
	CONSTRAINT pff_paf_id_fk FOREIGN KEY (pff_paf_id) REFERENCES public.patients_forms(paf_id),
	CONSTRAINT pff_form_id_fk FOREIGN KEY (pff_form_id) REFERENCES public.forms(for_id),
	CONSTRAINT pff_pac_id_fk FOREIGN KEY (pff_pac_id) REFERENCES public.patients(pac_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.patients_forms_fields IS 'Tabla intermedia que representa la relación entre las entidades PATIENTS FORMS FIELDS';
COMMENT ON COLUMN public.patients_forms_fields.pff_id IS 'Columna que contiene el identificador de la relacion paciente-formulario-usuario';
COMMENT ON COLUMN public.patients_forms_fields.pff_paf_id IS 'Columna que contiene el identificador de la relacion paciente formulario';
COMMENT ON COLUMN public.patients_forms_fields.pff_form_id IS 'Columna que contiene el identificador del formulario';
COMMENT ON COLUMN public.patients_forms_fields.pff_pac_id IS 'Columna que contiene el identificador del paciente';
COMMENT ON COLUMN public.patients_forms_fields.pff_field_id IS 'Columna que contiene el identificador del campo';
COMMENT ON COLUMN public.patients_forms_fields.pff_value_txt IS 'Columna que contiene el valor del campo cuando es de tipo texto';
COMMENT ON COLUMN public.patients_forms_fields.pff_value_num IS 'Columna que contiene el valor del campo cuando es de tipo numerico';
COMMENT ON COLUMN public.patients_forms_fields.pff_value_bool IS 'Columna que contiene el valor del campo cuando es de tipo booleano';
COMMENT ON COLUMN public.patients_forms_fields.pff_value_data IS 'Columna que contiene el valor del campo cuando es de tipo data';
COMMENT ON CONSTRAINT pff_id_pk ON public.patients_forms_fields IS 'pk de la tabla PATIENTS_FORM_FIELDS';
COMMENT ON CONSTRAINT pff_paf_id_fk ON public.patients_forms_fields IS 'fk Relacion con la tabla PATIENTS_FORMS';
COMMENT ON CONSTRAINT pff_form_id_fk ON public.patients_forms_fields IS 'fk Relacion con la tabla FORMS';
COMMENT ON CONSTRAINT pff_pac_id_fk ON public.patients_forms_fields IS 'fk Relacion con la tabla PATIENTS';
-- ddl-end --

------------------------------------------------- FIN TABLAS --------------------------------------------------------------------------------------
