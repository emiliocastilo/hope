------------------------------------------------- INICIO TABLAS --------------------------------------------------------------------------------------
-- object: "public"."HOSPITALS" | type: TABLE --
DROP TABLE IF EXISTS "public"."HOSPITALS" CASCADE;
CREATE TABLE "public"."HOSPITALS" (
	HOS_ID serial,
	HOS_NAME varchar(100) NOT NULL,
	CONSTRAINT HOS_ID_PK PRIMARY KEY (HOS_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."HOSPITALS" IS 'Tabla que representa la entidad hospitales';
COMMENT ON COLUMN "public"."HOSPITALS".HOS_ID IS 'Columna con el id de base de datos del hospital(PK)';
COMMENT ON COLUMN "public"."HOSPITALS".HOS_NAME IS 'Columna que contiene el nombre del hospital';
COMMENT ON CONSTRAINT HOS_ID_PK ON "public"."HOSPITALS" IS 'PK de la tabla HOSPITALS';
-- ddl-end --

-- object: "public"."GENDERS" | type: TABLE --
DROP TABLE IF EXISTS "public"."GENDERS" CASCADE;
CREATE TABLE "public"."GENDERS" (
	SEX_CODE varchar(1) NOT NULL,
	SEX_NAME varchar(9) NOT NULL,
	CONSTRAINT SEX_CODE_PK PRIMARY KEY (SEX_CODE)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."GENDERS" IS 'Tabla catálogo con todos los sexos';
COMMENT ON COLUMN "public"."GENDERS".SEX_CODE IS 'Columna que contiene el código identificativo del sexo de los pacientes (PK)';
COMMENT ON COLUMN "public"."GENDERS".SEX_NAME IS 'Columna con el nombre de los distintos sexos';
COMMENT ON CONSTRAINT SEX_CODE_PK ON "public"."GENDERS" IS 'PK de la tabla GENDERS';
-- ddl-end --

-- object: "public"."COUNTRIES" | type: TABLE --
DROP TABLE IF EXISTS "public"."COUNTRIES" CASCADE;
CREATE TABLE "public"."COUNTRIES" (
	PAI_CODE varchar(3) NOT NULL,
	PAI_NAME varchar(60) NOT NULL,
	CONSTRAINT PAI_CODE_PK PRIMARY KEY (PAI_CODE)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."COUNTRIES" IS 'Tabla catálogo con todos los países';
COMMENT ON COLUMN "public"."COUNTRIES".PAI_CODE IS 'Columna que contiene el código ISO de 3 letras asignado a cada país o territorio. (PK)';
COMMENT ON COLUMN "public"."COUNTRIES".PAI_NAME IS 'Columna con el nombre del país o territorio';
COMMENT ON CONSTRAINT PAI_CODE_PK ON "public"."COUNTRIES" IS 'PK de la tabla COUNTRIES';
-- ddl-end --

-- object: "public"."PATIENTS" | type: TABLE --
DROP TABLE IF EXISTS "public"."PATIENTS" CASCADE;
CREATE TABLE "public"."PATIENTS" (
	PAC_ID serial,
	PAC_HOS_ID smallint,
	PAC_NAME varchar(50) NOT NULL,
	PAC_SURNAME1 varchar(50) NOT NULL,
	PAC_SURNAME2 varchar(50),
	PAC_NHC varchar(50) NOT NULL,
	PAC_HEALTH_CARD varchar(50) NOT NULL,
	PAC_CIP varchar(50),
	PAC_DNI varchar(9),
	PAC_ADDRESS varchar(100),
	PAC_CITY varchar(50),
	PAC_STATE varchar(50),
	PAC_CP varchar(50),
	PAC_PHONE varchar(15),
	PAC_MOBILE varchar(15),
	PAC_EMAIL varchar(50),
	PAC_CODE_GENDER varchar(1),
	PAC_DATE_NACIM timestamp,
	PAC_CODE_COUNTRY varchar(3),
	PAC_DATE_NEXT_VIS timestamp,
	PAC_DATE_LAST_VIS timestamp,
	PAC_DATE_LAST_ACC timestamp,
	CONSTRAINT PAC_ID_PK PRIMARY KEY (PAC_ID),
	CONSTRAINT PAC_NHC_UNIQUE UNIQUE (PAC_NHC),
	CONSTRAINT PAC_CIP_UNIQUE UNIQUE (PAC_CIP),
	CONSTRAINT PAC_HEALTH_CARD_UNIQUE UNIQUE (PAC_HEALTH_CARD),
    CONSTRAINT PAC_HOS_ID_FK FOREIGN KEY (PAC_HOS_ID) REFERENCES "public"."HOSPITALS"(HOS_ID),
	CONSTRAINT PAC_CODE_GENDER_FK FOREIGN KEY (PAC_CODE_GENDER) REFERENCES "public"."GENDERS"(SEX_CODE),
	CONSTRAINT PAC_CODE_COUNTRY_FK FOREIGN KEY (PAC_CODE_COUNTRY) REFERENCES "public"."COUNTRIES"(PAI_CODE)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."PATIENTS" IS 'Tabla que representa la entidad PATIENTS';
COMMENT ON COLUMN "public"."PATIENTS".PAC_ID IS 'Columna que contiene el identificador del paciente en la bbdd de la aplicación. (PK)';
COMMENT ON COLUMN "public"."PATIENTS".PAC_HOS_ID IS 'Columna que contiene el identificador del hospital en el que está dado de alta el paciente en la bbdd de la aplicación.';
COMMENT ON COLUMN "public"."PATIENTS".PAC_NAME IS 'Columna que contiene el nombre del paciente';
COMMENT ON COLUMN "public"."PATIENTS".PAC_SURNAME1 IS 'Columna que contiene el primer apellido del paciente';
COMMENT ON COLUMN "public"."PATIENTS".PAC_SURNAME2 IS 'Columna que contiene el segundo apellido del paciente';
COMMENT ON COLUMN "public"."PATIENTS".PAC_HEALTH_CARD IS  'Columna que contiene el número de tarjeta sanitaria del paciente';
COMMENT ON COLUMN "public"."PATIENTS".PAC_CIP IS  'Columna que contiene código de identificación personal en el Sistema de Información de Tarjeta Sanitaria';
COMMENT ON COLUMN "public"."PATIENTS".PAC_DNI IS 'Columna que contiene el documento nacional de identidad del paciente';
COMMENT ON COLUMN "public"."PATIENTS".PAC_ADDRESS IS 'Columna que contiene la direccion  del domicilio del paciente';
COMMENT ON COLUMN "public"."PATIENTS".PAC_CITY IS  'Columna que contiene la localidad de la dirección del domicilio del paciente';
COMMENT ON COLUMN "public"."PATIENTS".PAC_STATE IS 'Columna que contiene la provincia de la dirección del domicilio del paciente';
COMMENT ON COLUMN "public"."PATIENTS".PAC_CP IS 'Columna que contiene el código postal de la localidad de la dirección del domicilio del paciente';
COMMENT ON COLUMN "public"."PATIENTS".PAC_PHONE IS 'Columna que contiene el número de teléfono fijo facilitado por el paciente';
COMMENT ON COLUMN "public"."PATIENTS".PAC_MOBILE IS 'Columna que contiene el número de teléfono móvile facilitado por el paciente';
COMMENT ON COLUMN "public"."PATIENTS".PAC_EMAIL IS  'Columna que contiene el email facilitado por el paciente';
COMMENT ON COLUMN "public"."PATIENTS".PAC_CODE_GENDER IS  'Columna que contiene el código del sexo del paciente';
COMMENT ON COLUMN "public"."PATIENTS".PAC_DATE_NACIM IS 'Columna que contiene la fecha de nacimiento del paciente';
COMMENT ON COLUMN "public"."PATIENTS".PAC_CODE_COUNTRY IS 'Columna que contiene el código del país de nacimiento del paciente';
COMMENT ON COLUMN "public"."PATIENTS".PAC_DATE_NEXT_VIS IS 'Columna que contiene la fecha de la próxima visita del paciente';
COMMENT ON COLUMN "public"."PATIENTS".PAC_DATE_LAST_VIS IS 'Columna que contiene la fecha de la última visita del paciente';
COMMENT ON COLUMN "public"."PATIENTS".PAC_DATE_LAST_ACC IS 'Columna que contiene la fecha de la última vez que fue seleccionado el paciente para cualquier tipo de consulta en el sistema';
COMMENT ON CONSTRAINT PAC_ID_PK ON "public"."PATIENTS" IS 'PK de la tabla PATIENTS';
COMMENT ON CONSTRAINT PAC_HOS_ID_FK ON "public"."PATIENTS" IS 'FK Relacion con la tabla HOSPITALS';
COMMENT ON CONSTRAINT PAC_CODE_GENDER_FK ON "public"."PATIENTS" IS 'FK Relacion con la tabla GENDERS';
COMMENT ON CONSTRAINT PAC_CODE_COUNTRY_FK ON "public"."PATIENTS" IS 'FK  Relacion con la tabla COUNTRIES';
-- ddl-end --


-- object: "public"."PATHOLOGIES" | type: TABLE --
DROP TABLE IF EXISTS "public"."PATHOLOGIES" CASCADE;
CREATE TABLE "public"."PATHOLOGIES" (
	PTH_ID serial,
	PTH_NAME varchar(100) NOT NULL,
	PTH_DESCRIPTION varchar(500),
	CONSTRAINT PTH_ID_PK PRIMARY KEY (PTH_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."PATHOLOGIES" IS 'Tabla que representa la entidad PATHOLOGY';
COMMENT ON COLUMN "public"."PATHOLOGIES".PTH_ID IS 'Columna con el id de base de datos del patologia(PK)';
COMMENT ON COLUMN "public"."PATHOLOGIES".PTH_NAME IS 'Columna que contiene el nombre de la patologia';
COMMENT ON COLUMN "public"."PATHOLOGIES".PTH_DESCRIPTION IS 'Columna que contiene la descripcion detallada de la patologia';
COMMENT ON CONSTRAINT PTH_ID_PK ON "public"."PATHOLOGIES" IS 'PK de la tabla PATHOLOGIES';
-- ddl-end --


-- object: "public"."PATIENTS_PATHOLOGIES" | type: TABLE --
DROP TABLE IF EXISTS "public"."PATIENTS_PATHOLOGIES" CASCADE;
CREATE TABLE "public"."PATIENTS_PATHOLOGIES" (
	PCP_ID serial,
	PCP_PAC_ID bigint NOT NULL,
	PCP_PTH_ID smallint NOT NULL,
	CONSTRAINT PCP_ID_PK PRIMARY KEY (PCP_ID),
	CONSTRAINT PCP_PAC_ID_FK FOREIGN KEY (PCP_PAC_ID) REFERENCES "public"."PATIENTS"(PAC_ID),
	CONSTRAINT PCP_PTH_ID_FK FOREIGN KEY (PCP_PTH_ID) REFERENCES "public"."PATHOLOGIES"(PTH_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."PATIENTS_PATHOLOGIES" IS 'Tabla intermedia que representa la relación entre las entidades PATIENTS y PATHOLOGIES';
COMMENT ON COLUMN "public"."PATIENTS_PATHOLOGIES".PCP_ID IS 'Columna con el id de base de datos de la relación entre las tablas pacientes y patologias(PK)';
COMMENT ON COLUMN "public"."PATIENTS_PATHOLOGIES".PCP_PAC_ID IS 'Columna con el id del paciente';
COMMENT ON COLUMN "public"."PATIENTS_PATHOLOGIES".PCP_PTH_ID IS 'Columna con el id de la patologia';
COMMENT ON CONSTRAINT PCP_ID_PK ON "public"."PATIENTS_PATHOLOGIES" IS 'PK de la tabla PATIENTS_PATHOLOGIES';
COMMENT ON CONSTRAINT PCP_PAC_ID_FK ON "public"."PATIENTS_PATHOLOGIES" IS 'FK Relacion con la tabla PATIENTS';
COMMENT ON CONSTRAINT PCP_PTH_ID_FK ON "public"."PATIENTS_PATHOLOGIES" IS 'FK Relacion con la tabla PATHOLOGIES';
-- ddl-end --

-- object: "public"."HOSPITALS_PATHOLOGIES" | type: TABLE --
DROP TABLE IF EXISTS "public"."HOSPITALS_PATHOLOGIES" CASCADE;
CREATE TABLE "public"."HOSPITALS_PATHOLOGIES" (
	HSP_ID serial,
	HSP_HOS_ID smallint NOT NULL,
	HSP_PTH_ID smallint NOT NULL,
	CONSTRAINT HSP_ID_PK PRIMARY KEY (HSP_ID),
	CONSTRAINT HSP_HOS_ID_FK FOREIGN KEY (HSP_HOS_ID) REFERENCES "public"."HOSPITALS"(HOS_ID),
	CONSTRAINT HSP_PTH_ID_FK FOREIGN KEY (HSP_PTH_ID) REFERENCES "public"."PATHOLOGIES"(PTH_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."HOSPITALS_PATHOLOGIES" IS 'Tabla intermedia que representa la relación entre las entidades HOSPITALS y PATHOLOGIES';
COMMENT ON COLUMN "public"."HOSPITALS_PATHOLOGIES".HSP_ID IS 'Columna con el identificador de base de datos de la relación entre las tablas HOSPITALS y PATHOLOGIES(PK)';
COMMENT ON COLUMN "public"."HOSPITALS_PATHOLOGIES".HSP_HOS_ID IS 'Columna con el id del hospital';
COMMENT ON COLUMN "public"."HOSPITALS_PATHOLOGIES".HSP_PTH_ID IS 'Columna con el id de la patologia';
COMMENT ON CONSTRAINT HSP_ID_PK ON "public"."HOSPITALS_PATHOLOGIES" IS 'PK de la tabla HOSPITALS_PATHOLOGIES';
COMMENT ON CONSTRAINT HSP_HOS_ID_FK ON "public"."HOSPITALS_PATHOLOGIES" IS 'FK Relacion con la tabla HOSPITALS';
COMMENT ON CONSTRAINT HSP_PTH_ID_FK ON "public"."HOSPITALS_PATHOLOGIES" IS 'FK Relacion con la tabla PATHOLOGIES';
-- ddl-end --

-- object: "public"."SECTIONS" | type: TABLE --
DROP TABLE IF EXISTS "public"."SECTIONS" CASCADE;
CREATE TABLE "public"."SECTIONS" (
	SEC_ID serial,
	SEC_PTH_ID smallint NOT NULL,
	SEC_TITLE varchar(50) NOT NULL,
	SEC_DESCRIPTION varchar(500),
	SEC_MENU boolean,
	SEC_ORDER smallint NOT NULL,
	SEC_SEC_ROOT smallint,
	CONSTRAINT SEC_ID_PK PRIMARY KEY (SEC_ID),
	CONSTRAINT SEC_PTH_ID_FK FOREIGN KEY (SEC_PTH_ID) REFERENCES "public"."PATHOLOGIES"(PTH_ID),
	CONSTRAINT SEC_SEC_ROOT_FK FOREIGN KEY (SEC_SEC_ROOT) REFERENCES "public"."SECTIONS"(SEC_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."SECTIONS" IS 'Tabla que representa la entidad SECTIONS. Se consideran apartados tanto las opciones de menu como cualquier elemento hijo que cuelgue de él';
COMMENT ON COLUMN "public"."SECTIONS".SEC_ID IS 'Columna con el id de base de datos del patologia(PK)';
COMMENT ON COLUMN "public"."SECTIONS".SEC_PTH_ID IS 'Columna que contiene el identificador de la patologia a la que el apartado hace referencia';
COMMENT ON COLUMN "public"."SECTIONS".SEC_TITLE IS 'Columna que contiene el titulo del apartado del menu de la patologia';
COMMENT ON COLUMN "public"."SECTIONS".SEC_DESCRIPTION IS 'Columna que contiene la descripcion detallada del apartado del menu de la patologia';
COMMENT ON COLUMN "public"."SECTIONS".SEC_MENU IS 'Columna que indica sí este apartado forma parte del menu de la patologia';
COMMENT ON COLUMN "public"."SECTIONS".SEC_ORDER IS 'Columna que indica el orden del apartado dentro del menu/listado de apartados de la patologia';
COMMENT ON COLUMN "public"."SECTIONS".SEC_SEC_ROOT IS 'Columna que contiene el identificador del apartado padre en el que estaría incluido creando un arbol de apartados dentro del menú de la patologia';
COMMENT ON CONSTRAINT SEC_ID_PK ON "public"."SECTIONS" IS 'PK de la tabla SECTIONS';
COMMENT ON CONSTRAINT SEC_PTH_ID_FK ON "public"."SECTIONS" IS 'FK Relacion con la tabla PATHOLOGIES';
COMMENT ON CONSTRAINT SEC_SEC_ROOT_FK ON "public"."SECTIONS" IS 'FK Relacion con la tabla SECTIONS';
-- ddl-end --

-- object: "public"."FORMS" | type: TABLE --
DROP TABLE IF EXISTS "public"."FORMS" CASCADE;
CREATE TABLE "public"."FORMS" (
	FOR_ID serial,
	FOR_TITLE varchar(50) NOT NULL,
	FOR_DESCRIPTION varchar(500),
	CONSTRAINT FOR_ID_PK PRIMARY KEY (FOR_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."FORMS" IS 'Tabla intermedia que representa la relación entre las entidades SECTIONS y FORMS';
COMMENT ON COLUMN "public"."FORMS".FOR_ID IS 'Columna que contiene el título del formulario';
COMMENT ON COLUMN "public"."FORMS".FOR_TITLE IS 'Columna que contiene la descripcion detallada del formulario';
COMMENT ON COLUMN "public"."FORMS".FOR_DESCRIPTION IS 'Columna que contiene el identificador del formulario dentro del apartado';
COMMENT ON CONSTRAINT FOR_ID_PK ON "public"."FORMS" IS 'PK de la tabla FORMS';
-- ddl-end --

-- object: "public"."SECTIONS_FORMS" | type: TABLE --
DROP TABLE IF EXISTS "public"."SECTIONS_FORMS" CASCADE;
CREATE TABLE "public"."SECTIONS_FORMS" (
	APF_ID serial,
	APF_SECTION_ID smallint NOT NULL,
	APF_FORM_ID smallint NOT NULL,
	APF_ORDER smallint,
	CONSTRAINT APF_ID_PK PRIMARY KEY (APF_ID),
	CONSTRAINT APF_SECTION_ID_FK FOREIGN KEY (APF_SECTION_ID) REFERENCES "public"."SECTIONS"(SEC_ID),
	CONSTRAINT APF_FORM_ID_FK FOREIGN KEY (APF_FORM_ID) REFERENCES "public"."FORMS"(FOR_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."SECTIONS_FORMS" IS 'Tabla intermedia que representa la relación entre las entidades SECTIONS y FORMS';
COMMENT ON COLUMN "public"."SECTIONS_FORMS".APF_ID IS 'Columna con el identificador entre un formulario y un apartado';
COMMENT ON COLUMN "public"."SECTIONS_FORMS".APF_SECTION_ID IS 'Columna que contiene el identificador del apartado de la patologia';
COMMENT ON COLUMN "public"."SECTIONS_FORMS".APF_FORM_ID IS 'Columna que contiene el identificador del formulario dentro del apartado';
COMMENT ON COLUMN "public"."SECTIONS_FORMS".APF_ORDER IS 'Columna que indica el orden';
COMMENT ON CONSTRAINT APF_ID_PK ON "public"."SECTIONS_FORMS" IS 'PK de la tabla SECTIONS_FORMS';
COMMENT ON CONSTRAINT APF_SECTION_ID_FK ON "public"."SECTIONS_FORMS" IS 'FK Relacion con la tabla SECTIONS';
COMMENT ON CONSTRAINT APF_FORM_ID_FK ON "public"."SECTIONS_FORMS" IS 'FK Relacion con la tabla FORMS';
-- ddl-end --

-- object: "public"."VALUES_AUX_TAB" | type: TABLE --
DROP TABLE IF EXISTS "public"."VALUES_AUX_TAB" CASCADE;
CREATE TABLE "public"."VALUES_AUX_TAB" (
	VTA_ID serial,
	VTA_TABLA_AUX_ID smallint NOT NULL,
	VTA_NOM_TAB_AUX varchar(50) NOT NULL,
	VTA_DESC_TAB_AUX varchar(150),
	VTA_VALUE_ID smallint NOT NULL,
	VTA_VALUE varchar(2000),
	VTA_ORDER smallint,
	VTA_ACTIVO boolean,
	CONSTRAINT VTA_ID_PK PRIMARY KEY (VTA_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."VALUES_AUX_TAB" IS 'Tabla que representa la entidad VALUEES_TABLAS_AUXILIARES';
COMMENT ON COLUMN "public"."VALUES_AUX_TAB".VTA_ID IS 'Columna que contiene el identificador de la relacion de la tabla auxiliar con su valor(PK)';
COMMENT ON COLUMN "public"."VALUES_AUX_TAB".VTA_TABLA_AUX_ID IS 'Columna que contiene el identificador de la tabla auxiliar';
COMMENT ON COLUMN "public"."VALUES_AUX_TAB".VTA_NOM_TAB_AUX IS 'Columna que contiene el nombre de la tabla auxiliar';
COMMENT ON COLUMN "public"."VALUES_AUX_TAB".VTA_DESC_TAB_AUX IS 'Columna que contiene la descripcion detallada de la tabla auxiliar';
COMMENT ON COLUMN "public"."VALUES_AUX_TAB".VTA_VALUE_ID IS 'Columna que contiene el identificador del valor';
COMMENT ON COLUMN "public"."VALUES_AUX_TAB".VTA_VALUE IS 'Columna que contiene el valor de la tabla';
COMMENT ON COLUMN "public"."VALUES_AUX_TAB".VTA_ORDER IS 'Columna que indica el orden del valor dentro de la tabla a la que hace referencia';
COMMENT ON COLUMN "public"."VALUES_AUX_TAB".VTA_ACTIVO IS 'Columna que indica si la tabla está o no activa';
COMMENT ON CONSTRAINT VTA_ID_PK ON "public"."VALUES_AUX_TAB" IS 'PK de la tabla VALUES_AUX_TAB';
-- ddl-end --

-- object: "public"."ROLES" | type: TABLE --
DROP TABLE IF EXISTS "public"."ROLES" CASCADE;
CREATE TABLE "public"."ROLES" (
	ROL_ID serial,
	ROL_NAME varchar(50) NOT NULL,
	ROL_DESCRIPTION varchar(500),
	CONSTRAINT ROL_ID_PK PRIMARY KEY (ROL_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."ROLES" IS 'Tabla que representa la entidad ROLES';
COMMENT ON COLUMN "public"."ROLES".ROL_ID IS 'Columna que contiene el identificador del ROL';
COMMENT ON COLUMN "public"."ROLES".ROL_NAME IS 'Columna que contiene el nombre por el que se conoce al rol';
COMMENT ON COLUMN "public"."ROLES".ROL_DESCRIPTION IS 'Columna que contiene una descripcion detallada del rol';
COMMENT ON CONSTRAINT ROL_ID_PK ON "public"."ROLES" IS 'PK de la tabla ROLES';
-- ddl-end --

-- object: "public"."USERS" | type: TABLE --
DROP TABLE IF EXISTS "public"."USERS" CASCADE;
CREATE TABLE "public"."USERS" (
	USR_ID serial,
	USR_NAME varchar(50) NOT NULL,
	USR_PASSWORD varchar(200) NOT NULL,
	USR_EMAIL varchar(50),
	USR_HOS_ID smallint NOT NULL,
	USR_DATE_CREA timestamp NOT NULL,
	USR_DATE_MOD timestamp NOT NULL,
	CONSTRAINT USR_ID_PK PRIMARY KEY (USR_ID),
	CONSTRAINT USR_NAME_UNIQUE UNIQUE (USR_NAME),
	CONSTRAINT USR_EMAIL_UNIQUE UNIQUE (USR_EMAIL),
	CONSTRAINT USR_HOS_ID_FK FOREIGN KEY (USR_HOS_ID) REFERENCES "public"."HOSPITALS"(HOS_ID)

);
-- COMENTARIOS
COMMENT ON TABLE "public"."USERS" IS 'Tabla que representa la entidad USERS';
COMMENT ON COLUMN "public"."USERS".USR_ID IS 'Columna que contiene el identificador del usuario en la base de datos de la aplicación';
COMMENT ON COLUMN "public"."USERS".USR_NAME IS 'Columna que contiene el nombre de acceso del usuario en el sistema';
COMMENT ON COLUMN "public"."USERS".USR_PASSWORD IS 'Columna que contiene la contraseña de acceso del usuario';
COMMENT ON COLUMN "public"."USERS".USR_EMAIL IS 'Columna que contiene el email del usuario al que enviar información desde el sistema';
COMMENT ON COLUMN "public"."USERS".USR_HOS_ID IS 'Columna que contiene el identificador del hospital al que pertenece el usuario';
COMMENT ON COLUMN "public"."USERS".USR_DATE_CREA IS 'Columna que contiene la fecha de creación del usuario';
COMMENT ON COLUMN "public"."USERS".USR_DATE_MOD IS 'Columna que contiene la fecha de la última modificación sufrida en el usuario';
COMMENT ON CONSTRAINT USR_ID_PK ON "public"."USERS" IS 'PK de la tabla USERS';
COMMENT ON CONSTRAINT USR_HOS_ID_FK ON "public"."USERS" IS 'FK Relacion con la tabla HOSPITALS';
-- ddl-end --

-- object: "public"."ROLES" | type: TABLE --
DROP TABLE IF EXISTS "public"."USERS_ROLES" CASCADE;
CREATE TABLE "public"."USERS_ROLES" (
	URO_ID serial,
	URO_USER_ID smallint NOT NULL,
	URO_ROL_ID smallint NOT NULL,
	CONSTRAINT URO_ID_PK PRIMARY KEY (URO_ID),
	CONSTRAINT URO_USER_ID_FK FOREIGN KEY (URO_USER_ID) REFERENCES "public"."USERS"(USR_ID),
	CONSTRAINT URO_ROL_ID_FK FOREIGN KEY (URO_ROL_ID) REFERENCES "public"."ROLES"(ROL_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."USERS_ROLES" IS 'Tabla intermedia que representa la relación entre las entidades USERS Y ROLES';
COMMENT ON COLUMN "public"."USERS_ROLES".URO_ID IS 'Columna que contiene el identificador de la relacion rol-usuario';
COMMENT ON COLUMN "public"."USERS_ROLES".URO_USER_ID IS 'Columna que contiene el identificador del usuario relacionado con el rol';
COMMENT ON COLUMN "public"."USERS_ROLES".URO_ROL_ID IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON CONSTRAINT URO_ID_PK ON "public"."USERS_ROLES" IS 'PK de la tabla USERS_ROLES';
COMMENT ON CONSTRAINT URO_USER_ID_FK ON "public"."USERS_ROLES" IS 'FK Relacion con la tabla USERS';
COMMENT ON CONSTRAINT URO_ROL_ID_FK ON "public"."USERS_ROLES" IS 'FK Relacion con la tabla ROLES';
-- ddl-end --

-- object: "public"."PATIENTS_FORMS" | type: TABLE --
DROP TABLE IF EXISTS "public"."PATIENTS_FORMS" CASCADE;
CREATE TABLE "public"."PATIENTS_FORMS" (
	PAF_ID serial,
	PAF_PAC_ID bigint NOT NULL,
	PAF_FORM_ID smallint NOT NULL,
	PAC_USER_ID smallint NOT NULL,
	PAF_DATE_CREA timestamp,
	CONSTRAINT PAF_ID_PK PRIMARY KEY (PAF_ID),
	CONSTRAINT PAF_PAC_ID_FK FOREIGN KEY (PAF_PAC_ID) REFERENCES "public"."PATIENTS"(PAC_ID),
	CONSTRAINT PAF_FORM_ID_FK FOREIGN KEY (PAF_FORM_ID) REFERENCES "public"."FORMS"(FOR_ID),
	CONSTRAINT PAC_USER_ID_FK FOREIGN KEY (PAC_USER_ID) REFERENCES "public"."USERS"(USR_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."PATIENTS_FORMS" IS 'Tabla intermedia que representa la relación entre las entidades PATIENTS y FORMS';
COMMENT ON COLUMN "public"."PATIENTS_FORMS".PAF_ID IS 'Columna que contiene el identificador de la relacion paciente-formulario-usuario';
COMMENT ON COLUMN "public"."PATIENTS_FORMS".PAF_PAC_ID IS 'Columna que contiene el identificador del usuario relacionado con el rol';
COMMENT ON COLUMN "public"."PATIENTS_FORMS".PAF_FORM_ID IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON COLUMN "public"."PATIENTS_FORMS".PAC_USER_ID IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON COLUMN "public"."PATIENTS_FORMS".PAF_DATE_CREA IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON CONSTRAINT PAF_ID_PK ON "public"."PATIENTS_FORMS" IS 'PK de la tabla PATIENTS_FORMS';
COMMENT ON CONSTRAINT PAF_PAC_ID_FK ON "public"."PATIENTS_FORMS" IS 'FK Relacion con la tabla PATIENTS';
COMMENT ON CONSTRAINT PAF_FORM_ID_FK ON "public"."PATIENTS_FORMS" IS 'FK Relacion con la tabla FORMS';
COMMENT ON CONSTRAINT PAC_USER_ID_FK ON "public"."PATIENTS_FORMS" IS 'FK Relacion con la tabla USERS';
-- ddl-end --

-- object: "public"."PATIENTS_FORMS" | type: TABLE --
DROP TABLE IF EXISTS "public"."PATIENTS_FORMS" CASCADE;
CREATE TABLE "public"."PATIENTS_FORMS" (
	PAF_ID serial,
	PAF_PAC_ID bigint NOT NULL,
	PAF_FORM_ID smallint NOT NULL,
	PAC_USER_ID smallint NOT NULL,
	PAF_DATE_CREA timestamp,
	CONSTRAINT PAF_ID_PK PRIMARY KEY (PAF_ID),
	CONSTRAINT PAF_PAC_ID_FK FOREIGN KEY (PAF_PAC_ID) REFERENCES "public"."PATIENTS"(PAC_ID),
	CONSTRAINT PAF_FORM_ID_FK FOREIGN KEY (PAF_FORM_ID) REFERENCES "public"."FORMS"(FOR_ID),
	CONSTRAINT PAC_USER_ID_FK FOREIGN KEY (PAC_USER_ID) REFERENCES "public"."USERS"(USR_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."PATIENTS_FORMS" IS 'Tabla intermedia que representa la relación entre las entidades PATIENTS y FORMS';
COMMENT ON COLUMN "public"."PATIENTS_FORMS".PAF_ID IS 'Columna que contiene el identificador de la relacion paciente-formulario-usuario';
COMMENT ON COLUMN "public"."PATIENTS_FORMS".PAF_PAC_ID IS 'Columna que contiene el identificador del usuario relacionado con el rol';
COMMENT ON COLUMN "public"."PATIENTS_FORMS".PAF_FORM_ID IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON COLUMN "public"."PATIENTS_FORMS".PAC_USER_ID IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON COLUMN "public"."PATIENTS_FORMS".PAF_DATE_CREA IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON CONSTRAINT PAF_ID_PK ON "public"."PATIENTS_FORMS" IS 'PK de la tabla PATIENTS_FORMS';
COMMENT ON CONSTRAINT PAF_PAC_ID_FK ON "public"."PATIENTS_FORMS" IS 'FK Relacion con la tabla PATIENTS';
COMMENT ON CONSTRAINT PAF_FORM_ID_FK ON "public"."PATIENTS_FORMS" IS 'FK Relacion con la tabla FORMS';
COMMENT ON CONSTRAINT PAC_USER_ID_FK ON "public"."PATIENTS_FORMS" IS 'FK Relacion con la tabla USERS';
-- ddl-end --

-- object: "public"."PAC_FORM_FIELDS" | type: TABLE --
DROP TABLE IF EXISTS "public"."PATIENTS_FORM_FIELDS" CASCADE;
CREATE TABLE "public"."PATIENTS_FORM_FIELDS" (
	PFF_ID serial,
	PFF_PAF_ID bigint NOT NULL,
	PFF_FORM_ID smallint NOT NULL,
	PFF_PAC_ID bigint NOT NULL,
	PFF_FIELD_ID bigint NOT NULL,
	PFF_VALUE_TXT VARCHAR(2000),
	PFF_VALUE_NUM smallint,
	PFF_VALUE_BOOL boolean,
	PFF_VALUE_DATA bytea,
	CONSTRAINT PFF_ID_PK PRIMARY KEY (PFF_ID),
	CONSTRAINT PFF_PAF_ID_FK FOREIGN KEY (PFF_PAF_ID) REFERENCES "public"."PATIENTS_FORMS"(PAF_ID),
	CONSTRAINT PFF_FORM_ID_FK FOREIGN KEY (PFF_FORM_ID) REFERENCES "public"."FORMS"(FOR_ID),
	CONSTRAINT PFF_PAC_ID_FK FOREIGN KEY (PFF_PAC_ID) REFERENCES "public"."PATIENTS"(PAC_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."PATIENTS_FORM_FIELDS" IS 'Tabla intermedia que representa la relación entre las entidades PATIENTS FORMS FIELDS';
COMMENT ON COLUMN "public"."PATIENTS_FORM_FIELDS".PFF_ID IS 'Columna que contiene el identificador de la relacion paciente-formulario-usuario';
COMMENT ON COLUMN "public"."PATIENTS_FORM_FIELDS".PFF_PAF_ID IS 'Columna que contiene el identificador de la relacion paciente formulario';
COMMENT ON COLUMN "public"."PATIENTS_FORM_FIELDS".PFF_FORM_ID IS 'Columna que contiene el identificador del formulario';
COMMENT ON COLUMN "public"."PATIENTS_FORM_FIELDS".PFF_PAC_ID IS 'Columna que contiene el identificador del paciente';
COMMENT ON COLUMN "public"."PATIENTS_FORM_FIELDS".PFF_FIELD_ID IS 'Columna que contiene el identificador del campo';
COMMENT ON COLUMN "public"."PATIENTS_FORM_FIELDS".PFF_VALUE_TXT IS 'Columna que contiene el valor del campo cuando es de tipo texto';
COMMENT ON COLUMN "public"."PATIENTS_FORM_FIELDS".PFF_VALUE_NUM IS 'Columna que contiene el valor del campo cuando es de tipo numerico';
COMMENT ON COLUMN "public"."PATIENTS_FORM_FIELDS".PFF_VALUE_BOOL IS 'Columna que contiene el valor del campo cuando es de tipo booleano';
COMMENT ON COLUMN "public"."PATIENTS_FORM_FIELDS".PFF_VALUE_DATA IS 'Columna que contiene el valor del campo cuando es de tipo data';
COMMENT ON CONSTRAINT PFF_ID_PK ON "public"."PATIENTS_FORM_FIELDS" IS 'PK de la tabla PATIENTS_FORM_FIELDS';
COMMENT ON CONSTRAINT PFF_PAF_ID_FK ON "public"."PATIENTS_FORM_FIELDS" IS 'FK Relacion con la tabla PATIENTS_FORMS';
COMMENT ON CONSTRAINT PFF_FORM_ID_FK ON "public"."PATIENTS_FORM_FIELDS" IS 'FK Relacion con la tabla FORMS';
COMMENT ON CONSTRAINT PFF_PAC_ID_FK ON "public"."PATIENTS_FORM_FIELDS" IS 'FK Relacion con la tabla PATIENTS';
-- ddl-end --

------------------------------------------------- FIN TABLAS --------------------------------------------------------------------------------------