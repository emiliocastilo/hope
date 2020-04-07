------------------------------------------------- INICIO TABLAS --------------------------------------------------------------------------------------
-- object: "public"."HOSPITALES" | type: TABLE --
DROP TABLE IF EXISTS "public"."HOSPITALES" CASCADE;
CREATE TABLE "public"."HOSPITALES" (
	HOS_ID serial,
	HOS_NOMBRE varchar(100) NOT NULL,
	CONSTRAINT HOS_ID_PK PRIMARY KEY (HOS_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."HOSPITALES" IS 'Tabla que representa la entidad hospitales';
COMMENT ON COLUMN "public"."HOSPITALES".HOS_ID IS 'Columna con el id de base de datos del hospital(PK)';
COMMENT ON COLUMN "public"."HOSPITALES".HOS_NOMBRE IS 'Columna que contiene el nombre del hospital';
COMMENT ON CONSTRAINT HOS_ID_PK ON "public"."HOSPITALES" IS 'PK de la tabla HOSPITALES';
-- ddl-end --

-- object: "public"."SEXOS" | type: TABLE --
DROP TABLE IF EXISTS "public"."SEXOS" CASCADE;
CREATE TABLE "public"."SEXOS" (
	SEX_CODIGO varchar(1) NOT NULL,
	SEX_NOMBRE varchar(9) NOT NULL,
	CONSTRAINT SEX_CODIGO_PK PRIMARY KEY (SEX_CODIGO)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."SEXOS" IS 'Tabla catálogo con todos los sexos';
COMMENT ON COLUMN "public"."SEXOS".SEX_CODIGO IS 'Columna que contiene el código identificativo del sexo de los pacientes (PK)';
COMMENT ON COLUMN "public"."SEXOS".SEX_NOMBRE IS 'Columna con el nombre de los distintos sexos';
COMMENT ON CONSTRAINT SEX_CODIGO_PK ON "public"."SEXOS" IS 'PK de la tabla SEXOS';
-- ddl-end --

-- object: "public"."PAISES" | type: TABLE --
DROP TABLE IF EXISTS "public"."PAISES" CASCADE;
CREATE TABLE "public"."PAISES" (
	PAI_CODIGO varchar(3) NOT NULL,
	PAI_NOMBRE varchar(60) NOT NULL,
	CONSTRAINT PAI_CODIGO_PK PRIMARY KEY (PAI_CODIGO)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."PAISES" IS 'Tabla catálogo con todos los países';
COMMENT ON COLUMN "public"."PAISES".PAI_CODIGO IS 'Columna que contiene el código ISO de 3 letras asignado a cada país o territorio. (PK)';
COMMENT ON COLUMN "public"."PAISES".PAI_NOMBRE IS 'Columna con el nombre del país o territorio';
COMMENT ON CONSTRAINT PAI_CODIGO_PK ON "public"."PAISES" IS 'PK de la tabla PAISES';
-- ddl-end --

-- object: "public"."PACIENTES" | type: TABLE --
DROP TABLE IF EXISTS "public"."PACIENTES" CASCADE;
CREATE TABLE "public"."PACIENTES" (
	PAC_ID serial,
	PAC_HOS_ID smallint,
	PAC_NOMBRE varchar(50) NOT NULL,
	PAC_APELLIDO1 varchar(50) NOT NULL,
	PAC_APELLIDO2 varchar(50),
	PAC_NHC varchar(50) NOT NULL,
	PAC_TARJETA_SAN varchar(50) NOT NULL,
	PAC_CIP varchar(50),
	PAC_DNI varchar(9),
	PAC_DIRECCION varchar(100),
	PAC_LOCALIDAD varchar(50),
	PAC_PROVINCIA varchar(50),
	PAC_COD_POST varchar(50),
	PAC_TELEFONO varchar(15),
	PAC_MOVIL varchar(15),
	PAC_EMAIL varchar(50),
	PAC_COD_SEXO varchar(1),
	PAC_FEC_NACIM timestamp,
	PAC_COD_PAIS varchar(3),
	PAC_FEC_PRO_VIS timestamp,
	PAC_FEC_ULT_VIS timestamp,
	PAC_FEC_ULT_ACC timestamp,
	CONSTRAINT PAC_ID_PK PRIMARY KEY (PAC_ID), 
	CONSTRAINT PAC_NHC_UNIQUE UNIQUE (PAC_NHC), 
	CONSTRAINT PAC_CIP_UNIQUE UNIQUE (PAC_CIP),
	CONSTRAINT PAC_TARJETA_SAN_UNIQUE UNIQUE (PAC_TARJETA_SAN), 
  CONSTRAINT PAC_HOS_ID_FK FOREIGN KEY (PAC_HOS_ID) REFERENCES "public"."HOSPITALES"(HOS_ID),
	CONSTRAINT PAC_COD_SEXO_FK FOREIGN KEY (PAC_COD_SEXO) REFERENCES "public"."SEXOS"(SEX_CODIGO),
	CONSTRAINT PAC_COD_PAIS_FK FOREIGN KEY (PAC_COD_PAIS) REFERENCES "public"."PAISES"(PAI_CODIGO)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."PACIENTES" IS 'Tabla que representa la entidad PACIENTES';
COMMENT ON COLUMN "public"."PACIENTES".PAC_ID IS 'Columna que contiene el identificador del paciente en la bbdd de la aplicación. (PK)';
COMMENT ON COLUMN "public"."PACIENTES".PAC_HOS_ID IS 'Columna que contiene el identificador del hospital en el que está dado de alta el paciente en la bbdd de la aplicación.';
COMMENT ON COLUMN "public"."PACIENTES".PAC_NOMBRE IS 'Columna que contiene el nombre del paciente';
COMMENT ON COLUMN "public"."PACIENTES".PAC_APELLIDO1 IS 'Columna que contiene el primer apellido del paciente';
COMMENT ON COLUMN "public"."PACIENTES".PAC_APELLIDO2 IS 'Columna que contiene el segundo apellido del paciente'; 
COMMENT ON COLUMN "public"."PACIENTES".PAC_TARJETA_SAN IS  'Columna que contiene el número de tarjeta sanitaria del paciente'; 
COMMENT ON COLUMN "public"."PACIENTES".PAC_CIP IS  'Columna que contiene código de identificación personal en el Sistema de Información de Tarjeta Sanitaria'; 
COMMENT ON COLUMN "public"."PACIENTES".PAC_DNI IS 'Columna que contiene el documento nacional de identidad del paciente';
COMMENT ON COLUMN "public"."PACIENTES".PAC_DIRECCION IS 'Columna que contiene la direccion  del domicilio del paciente';
COMMENT ON COLUMN "public"."PACIENTES".PAC_LOCALIDAD IS  'Columna que contiene la localidad de la dirección del domicilio del paciente';
COMMENT ON COLUMN "public"."PACIENTES".PAC_PROVINCIA IS 'Columna que contiene la provincia de la dirección del domicilio del paciente';
COMMENT ON COLUMN "public"."PACIENTES".PAC_COD_POST IS 'Columna que contiene el código postal de la localidad de la dirección del domicilio del paciente';
COMMENT ON COLUMN "public"."PACIENTES".PAC_TELEFONO IS 'Columna que contiene el número de teléfono fijo facilitado por el paciente';
COMMENT ON COLUMN "public"."PACIENTES".PAC_MOVIL IS 'Columna que contiene el número de teléfono móvile facilitado por el paciente';
COMMENT ON COLUMN "public"."PACIENTES".PAC_EMAIL IS  'Columna que contiene el email facilitado por el paciente';
COMMENT ON COLUMN "public"."PACIENTES".PAC_COD_SEXO IS  'Columna que contiene el código del sexo del paciente';
COMMENT ON COLUMN "public"."PACIENTES".PAC_FEC_NACIM IS 'Columna que contiene la fecha de nacimiento del paciente';
COMMENT ON COLUMN "public"."PACIENTES".PAC_COD_PAIS IS 'Columna que contiene el código del país de nacimiento del paciente';
COMMENT ON COLUMN "public"."PACIENTES".PAC_FEC_PRO_VIS IS 'Columna que contiene la fecha de la próxima visita del paciente';
COMMENT ON COLUMN "public"."PACIENTES".PAC_FEC_ULT_VIS IS 'Columna que contiene la fecha de la última visita del paciente';
COMMENT ON COLUMN "public"."PACIENTES".PAC_FEC_ULT_ACC IS 'Columna que contiene la fecha de la última vez que fue seleccionado el paciente para cualquier tipo de consulta en el sistema';
COMMENT ON CONSTRAINT PAC_ID_PK ON "public"."PACIENTES" IS 'PK de la tabla PACIENTES';
COMMENT ON CONSTRAINT PAC_HOS_ID_FK ON "public"."PACIENTES" IS 'FK Relacion con la tabla HOSPITALES';
COMMENT ON CONSTRAINT PAC_COD_SEXO_FK ON "public"."PACIENTES" IS 'FK Relacion con la tabla SEXOS';
COMMENT ON CONSTRAINT PAC_COD_PAIS_FK ON "public"."PACIENTES" IS 'FK  Relacion con la tabla PAISES';
-- ddl-end --


-- object: "public"."PATOLOGIAS" | type: TABLE --
DROP TABLE IF EXISTS "public"."PATOLOGIAS" CASCADE;
CREATE TABLE "public"."PATOLOGIAS" (
	PAT_ID serial,
	PAT_NOMBRE varchar(100) NOT NULL,
	PAT_DESCRIPCION varchar(500),
	CONSTRAINT PAT_ID_PK PRIMARY KEY (PAT_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."PATOLOGIAS" IS 'Tabla que representa la entidad PATOLOGIA';
COMMENT ON COLUMN "public"."PATOLOGIAS".PAT_ID IS 'Columna con el id de base de datos del patologia(PK)';
COMMENT ON COLUMN "public"."PATOLOGIAS".PAT_NOMBRE IS 'Columna que contiene el nombre de la patologia';
COMMENT ON COLUMN "public"."PATOLOGIAS".PAT_DESCRIPCION IS 'Columna que contiene la descripcion detallada de la patologia';
COMMENT ON CONSTRAINT PAT_ID_PK ON "public"."PATOLOGIAS" IS 'PK de la tabla PATOLOGIAS';
-- ddl-end --


-- object: "public"."PACIENTES_PATOLOGIAS" | type: TABLE --
DROP TABLE IF EXISTS "public"."PACIENTES_PATOLOGIAS" CASCADE;
CREATE TABLE "public"."PACIENTES_PATOLOGIAS" (
	PCP_ID serial,
	PCP_PAC_ID bigint NOT NULL,
	PCP_PAT_ID smallint NOT NULL,
	CONSTRAINT PCP_ID_PK PRIMARY KEY (PCP_ID),
	CONSTRAINT PCP_PAC_ID_FK FOREIGN KEY (PCP_PAC_ID) REFERENCES "public"."PACIENTES"(PAC_ID),
	CONSTRAINT PCP_PAT_ID_FK FOREIGN KEY (PCP_PAT_ID) REFERENCES "public"."PATOLOGIAS"(PAT_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."PACIENTES_PATOLOGIAS" IS 'Tabla intermedia que representa la relación entre las entidades PACIENTES y PATOLOGIAS';
COMMENT ON COLUMN "public"."PACIENTES_PATOLOGIAS".PCP_ID IS 'Columna con el id de base de datos de la relación entre las tablas pacientes y patologias(PK)';
COMMENT ON COLUMN "public"."PACIENTES_PATOLOGIAS".PCP_PAC_ID IS 'Columna con el id del paciente';
COMMENT ON COLUMN "public"."PACIENTES_PATOLOGIAS".PCP_PAT_ID IS 'Columna con el id de la patologia';
COMMENT ON CONSTRAINT PCP_ID_PK ON "public"."PACIENTES_PATOLOGIAS" IS 'PK de la tabla PACIENTES_PATOLOGIAS';
COMMENT ON CONSTRAINT PCP_PAC_ID_FK ON "public"."PACIENTES_PATOLOGIAS" IS 'FK Relacion con la tabla PACIENTES';
COMMENT ON CONSTRAINT PCP_PAT_ID_FK ON "public"."PACIENTES_PATOLOGIAS" IS 'FK Relacion con la tabla PATOLOGIAS';
-- ddl-end --

-- object: "public"."HOSPITALES_PATOLOGIAS" | type: TABLE --
DROP TABLE IF EXISTS "public"."HOSPITALES_PATOLOGIAS" CASCADE;
CREATE TABLE "public"."HOSPITALES_PATOLOGIAS" (
	HSP_ID serial,
	HSP_HOS_ID smallint NOT NULL,
	HSP_PAT_ID smallint NOT NULL,
	CONSTRAINT HSP_ID_PK PRIMARY KEY (HSP_ID),
	CONSTRAINT HSP_HOS_ID_FK FOREIGN KEY (HSP_HOS_ID) REFERENCES "public"."HOSPITALES"(HOS_ID),
	CONSTRAINT HSP_PAT_ID_FK FOREIGN KEY (HSP_PAT_ID) REFERENCES "public"."PATOLOGIAS"(PAT_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."HOSPITALES_PATOLOGIAS" IS 'Tabla intermedia que representa la relación entre las entidades HOSPITALES y PATOLOGIAS';
COMMENT ON COLUMN "public"."HOSPITALES_PATOLOGIAS".HSP_ID IS 'Columna con el identificador de base de datos de la relación entre las tablas HOSPITALES y PATOLOGIAS(PK)';
COMMENT ON COLUMN "public"."HOSPITALES_PATOLOGIAS".HSP_HOS_ID IS 'Columna con el id del hospital';
COMMENT ON COLUMN "public"."HOSPITALES_PATOLOGIAS".HSP_PAT_ID IS 'Columna con el id de la patologia';
COMMENT ON CONSTRAINT HSP_ID_PK ON "public"."HOSPITALES_PATOLOGIAS" IS 'PK de la tabla HOSPITALES_PATOLOGIAS';
COMMENT ON CONSTRAINT HSP_HOS_ID_FK ON "public"."HOSPITALES_PATOLOGIAS" IS 'FK Relacion con la tabla HOSPITALES';
COMMENT ON CONSTRAINT HSP_PAT_ID_FK ON "public"."HOSPITALES_PATOLOGIAS" IS 'FK Relacion con la tabla PATOLOGIAS';
-- ddl-end --

-- object: "public"."APARTADOS" | type: TABLE --
DROP TABLE IF EXISTS "public"."APARTADOS" CASCADE;
CREATE TABLE "public"."APARTADOS" (
	APA_ID serial,
	APA_PAT_ID smallint NOT NULL,
	APA_TITULO varchar(50) NOT NULL,
	APA_DESCRIPCION varchar(500),
	APA_MENU boolean,
	APA_ORDEN smallint NOT NULL,
	APA_APART_PADRE smallint,
	CONSTRAINT APA_ID_PK PRIMARY KEY (APA_ID),
	CONSTRAINT APA_PAT_ID_FK FOREIGN KEY (APA_PAT_ID) REFERENCES "public"."PATOLOGIAS"(PAT_ID),
	CONSTRAINT APA_APART_PADRE_FK FOREIGN KEY (APA_APART_PADRE) REFERENCES "public"."APARTADOS"(APA_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."APARTADOS" IS 'Tabla que representa la entidad APARTADOS. Se consideran apartados tanto las opciones de menu como cualquier elemento hijo que cuelgue de él';
COMMENT ON COLUMN "public"."APARTADOS".APA_ID IS 'Columna con el id de base de datos del patologia(PK)';
COMMENT ON COLUMN "public"."APARTADOS".APA_PAT_ID IS 'Columna que contiene el identificador de la patologia a la que el apartado hace referencia';
COMMENT ON COLUMN "public"."APARTADOS".APA_TITULO IS 'Columna que contiene el titulo del apartado del menu de la patologia';
COMMENT ON COLUMN "public"."APARTADOS".APA_DESCRIPCION IS 'Columna que contiene la descripcion detallada del apartado del menu de la patologia';
COMMENT ON COLUMN "public"."APARTADOS".APA_MENU IS 'Columna que indica sí este apartado forma parte del menu de la patologia';
COMMENT ON COLUMN "public"."APARTADOS".APA_ORDEN IS 'Columna que indica el orden del apartado dentro del menu/listado de apartados de la patologia';
COMMENT ON COLUMN "public"."APARTADOS".APA_APART_PADRE IS 'Columna que contiene el identificador del apartado padre en el que estaría incluido creando un arbol de apartados dentro del menú de la patologia';
COMMENT ON CONSTRAINT APA_ID_PK ON "public"."APARTADOS" IS 'PK de la tabla APARTADOS';
COMMENT ON CONSTRAINT APA_PAT_ID_FK ON "public"."APARTADOS" IS 'FK Relacion con la tabla PATOLOGIAS';
COMMENT ON CONSTRAINT APA_APART_PADRE_FK ON "public"."APARTADOS" IS 'FK Relacion con la tabla APARTADOS';
-- ddl-end --

-- object: "public"."FORMULARIOS" | type: TABLE --
DROP TABLE IF EXISTS "public"."FORMULARIOS" CASCADE;
CREATE TABLE "public"."FORMULARIOS" (
	FOR_ID serial,
	FOR_TITULO varchar(50) NOT NULL,
	FOR_DESCRIPCION varchar(500),
	CONSTRAINT FOR_ID_PK PRIMARY KEY (FOR_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."FORMULARIOS" IS 'Tabla intermedia que representa la relación entre las entidades APARTADOS y FORMULARIOS';
COMMENT ON COLUMN "public"."FORMULARIOS".FOR_ID IS 'Columna que contiene el título del formulario';
COMMENT ON COLUMN "public"."FORMULARIOS".FOR_TITULO IS 'Columna que contiene la descripcion detallada del formulario';
COMMENT ON COLUMN "public"."FORMULARIOS".FOR_DESCRIPCION IS 'Columna que contiene el identificador del formulario dentro del apartado';
COMMENT ON CONSTRAINT FOR_ID_PK ON "public"."FORMULARIOS" IS 'PK de la tabla FORMULARIOS';
-- ddl-end --

-- object: "public"."APARTADOS_FORMULARIOS" | type: TABLE --
DROP TABLE IF EXISTS "public"."APARTADOS_FORMULARIOS" CASCADE;
CREATE TABLE "public"."APARTADOS_FORMULARIOS" (
	APF_ID serial,
	APF_APARTADO_ID smallint NOT NULL,
	APF_FORM_ID smallint NOT NULL,
	APF_ORDEN smallint,
	CONSTRAINT APF_ID_PK PRIMARY KEY (APF_ID),
	CONSTRAINT APF_APARTADO_ID_FK FOREIGN KEY (APF_APARTADO_ID) REFERENCES "public"."APARTADOS"(APA_ID),
	CONSTRAINT APF_FORM_ID_FK FOREIGN KEY (APF_FORM_ID) REFERENCES "public"."FORMULARIOS"(FOR_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."APARTADOS_FORMULARIOS" IS 'Tabla intermedia que representa la relación entre las entidades APARTADOS y FORMULARIOS';
COMMENT ON COLUMN "public"."APARTADOS_FORMULARIOS".APF_ID IS 'Columna con el identificador entre un formulario y un apartado';
COMMENT ON COLUMN "public"."APARTADOS_FORMULARIOS".APF_APARTADO_ID IS 'Columna que contiene el identificador del apartado de la patologia';
COMMENT ON COLUMN "public"."APARTADOS_FORMULARIOS".APF_FORM_ID IS 'Columna que contiene el identificador del formulario dentro del apartado';
COMMENT ON COLUMN "public"."APARTADOS_FORMULARIOS".APF_ORDEN IS 'Columna que indica el orden';
COMMENT ON CONSTRAINT APF_ID_PK ON "public"."APARTADOS_FORMULARIOS" IS 'PK de la tabla APARTADOS_FORMULARIOS';
COMMENT ON CONSTRAINT APF_APARTADO_ID_FK ON "public"."APARTADOS_FORMULARIOS" IS 'FK Relacion con la tabla APARTADOS';
COMMENT ON CONSTRAINT APF_FORM_ID_FK ON "public"."APARTADOS_FORMULARIOS" IS 'FK Relacion con la tabla FORMULARIOS';
-- ddl-end --

-- object: "public"."VALORES_TAB_AUX" | type: TABLE --
DROP TABLE IF EXISTS "public"."VALORES_TAB_AUX" CASCADE;
CREATE TABLE "public"."VALORES_TAB_AUX" (
	VTA_ID serial,
	VTA_TABLA_AUX_ID smallint NOT NULL,
	VTA_NOM_TAB_AUX varchar(50) NOT NULL,
	VTA_DESC_TAB_AUX varchar(150),
	VTA_VALOR_ID smallint NOT NULL,
	VTA_VALOR varchar(2000),
	VTA_ORDEN smallint,
	VTA_ACTIVO boolean,
	CONSTRAINT VTA_ID_PK PRIMARY KEY (VTA_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."VALORES_TAB_AUX" IS 'Tabla que representa la entidad VALORES_TABLAS_AUXILIARES';
COMMENT ON COLUMN "public"."VALORES_TAB_AUX".VTA_ID IS 'Columna que contiene el identificador de la relacion de la tabla auxiliar con su valor(PK)';
COMMENT ON COLUMN "public"."VALORES_TAB_AUX".VTA_TABLA_AUX_ID IS 'Columna que contiene el identificador de la tabla auxiliar';
COMMENT ON COLUMN "public"."VALORES_TAB_AUX".VTA_NOM_TAB_AUX IS 'Columna que contiene el nombre de la tabla auxiliar';
COMMENT ON COLUMN "public"."VALORES_TAB_AUX".VTA_DESC_TAB_AUX IS 'Columna que contiene la descripcion detallada de la tabla auxiliar';
COMMENT ON COLUMN "public"."VALORES_TAB_AUX".VTA_VALOR_ID IS 'Columna que contiene el identificador del valor';
COMMENT ON COLUMN "public"."VALORES_TAB_AUX".VTA_VALOR IS 'Columna que contiene el valor de la tabla';
COMMENT ON COLUMN "public"."VALORES_TAB_AUX".VTA_ORDEN IS 'Columna que indica el orden del valor dentro de la tabla a la que hace referencia';
COMMENT ON COLUMN "public"."VALORES_TAB_AUX".VTA_ACTIVO IS 'Columna que indica si la tabla está o no activa';
COMMENT ON CONSTRAINT VTA_ID_PK ON "public"."VALORES_TAB_AUX" IS 'PK de la tabla VALORES_TAB_AUX';
-- ddl-end --

-- object: "public"."ROLES" | type: TABLE --
DROP TABLE IF EXISTS "public"."ROLES" CASCADE;
CREATE TABLE "public"."ROLES" (
	ROL_ID serial,
	ROL_NOMBRE varchar(50) NOT NULL,
	ROL_DESCRIPCION varchar(500),
	CONSTRAINT ROL_ID_PK PRIMARY KEY (ROL_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."ROLES" IS 'Tabla que representa la entidad ROLES';
COMMENT ON COLUMN "public"."ROLES".ROL_ID IS 'Columna que contiene el identificador del ROL';
COMMENT ON COLUMN "public"."ROLES".ROL_NOMBRE IS 'Columna que contiene el nombre por el que se conoce al rol';
COMMENT ON COLUMN "public"."ROLES".ROL_DESCRIPCION IS 'Columna que contiene una descripcion detallada del rol';
COMMENT ON CONSTRAINT ROL_ID_PK ON "public"."ROLES" IS 'PK de la tabla ROLES';
-- ddl-end --

-- object: "public"."USUARIOS" | type: TABLE --
DROP TABLE IF EXISTS "public"."USUARIOS" CASCADE;
CREATE TABLE "public"."USUARIOS" (
	USU_ID serial,
	USU_NOMBRE varchar(50) NOT NULL,
	USU_PASSWORD varchar(200) NOT NULL,
	USU_EMAIL varchar(50),
	USU_HOS_ID smallint NOT NULL,
	USU_FECHA_CREA timestamp NOT NULL,
	USU_FECHA_MOD timestamp NOT NULL,
	CONSTRAINT USU_ID_PK PRIMARY KEY (USU_ID),
	CONSTRAINT USU_HOS_ID_FK FOREIGN KEY (USU_HOS_ID) REFERENCES "public"."HOSPITALES"(HOS_ID)
	
);
-- COMENTARIOS
COMMENT ON TABLE "public"."USUARIOS" IS 'Tabla que representa la entidad USUARIOS';
COMMENT ON COLUMN "public"."USUARIOS".USU_ID IS 'Columna que contiene el identificador del usuario en la base de datos de la aplicación';
COMMENT ON COLUMN "public"."USUARIOS".USU_NOMBRE IS 'Columna que contiene el nombre de acceso del usuario en el sistema';
COMMENT ON COLUMN "public"."USUARIOS".USU_PASSWORD IS 'Columna que contiene la contraseña de acceso del usuario';
COMMENT ON COLUMN "public"."USUARIOS".USU_EMAIL IS 'Columna que contiene el email del usuario al que enviar información desde el sistema';
COMMENT ON COLUMN "public"."USUARIOS".USU_HOS_ID IS 'Columna que contiene el identificador del hospital al que pertenece el usuario';
COMMENT ON COLUMN "public"."USUARIOS".USU_FECHA_CREA IS 'Columna que contiene la fecha de creación del usuario';
COMMENT ON COLUMN "public"."USUARIOS".USU_FECHA_MOD IS 'Columna que contiene la fecha de la última modificación sufrida en el usuario';
COMMENT ON CONSTRAINT USU_ID_PK ON "public"."USUARIOS" IS 'PK de la tabla USUARIOS';
COMMENT ON CONSTRAINT USU_HOS_ID_FK ON "public"."USUARIOS" IS 'FK Relacion con la tabla HOSPITALES';
-- ddl-end --

-- object: "public"."ROLES" | type: TABLE --
DROP TABLE IF EXISTS "public"."USUARIOS_ROLES" CASCADE;
CREATE TABLE "public"."USUARIOS_ROLES" (
	URO_ID serial,
	URO_USUARIO_ID smallint NOT NULL,
	URO_ROL_ID smallint NOT NULL,
	CONSTRAINT URO_ID_PK PRIMARY KEY (URO_ID),
	CONSTRAINT URO_USUARIO_ID_FK FOREIGN KEY (URO_USUARIO_ID) REFERENCES "public"."USUARIOS"(USU_ID),
	CONSTRAINT URO_ROL_ID_FK FOREIGN KEY (URO_ROL_ID) REFERENCES "public"."ROLES"(ROL_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."USUARIOS_ROLES" IS 'Tabla intermedia que representa la relación entre las entidades USUARIOS Y ROLES';
COMMENT ON COLUMN "public"."USUARIOS_ROLES".URO_ID IS 'Columna que contiene el identificador de la relacion rol-usuario';
COMMENT ON COLUMN "public"."USUARIOS_ROLES".URO_USUARIO_ID IS 'Columna que contiene el identificador del usuario relacionado con el rol';
COMMENT ON COLUMN "public"."USUARIOS_ROLES".URO_ROL_ID IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON CONSTRAINT URO_ID_PK ON "public"."USUARIOS_ROLES" IS 'PK de la tabla USUARIOS_ROLES';
COMMENT ON CONSTRAINT URO_USUARIO_ID_FK ON "public"."USUARIOS_ROLES" IS 'FK Relacion con la tabla USUARIOS';
COMMENT ON CONSTRAINT URO_ROL_ID_FK ON "public"."USUARIOS_ROLES" IS 'FK Relacion con la tabla ROLES';
-- ddl-end --

-- object: "public"."PACIENTES_FORMULARIOS" | type: TABLE --
DROP TABLE IF EXISTS "public"."PACIENTES_FORMULARIOS" CASCADE;
CREATE TABLE "public"."PACIENTES_FORMULARIOS" (
	PAF_ID serial,
	PAF_PAC_ID bigint NOT NULL,
	PAF_FORM_ID smallint NOT NULL,
	PAC_USUARIO_ID smallint NOT NULL,
	PAF_FECHA_CREA timestamp,
	CONSTRAINT PAF_ID_PK PRIMARY KEY (PAF_ID),
	CONSTRAINT PAF_PAC_ID_FK FOREIGN KEY (PAF_PAC_ID) REFERENCES "public"."PACIENTES"(PAC_ID),
	CONSTRAINT PAF_FORM_ID_FK FOREIGN KEY (PAF_FORM_ID) REFERENCES "public"."FORMULARIOS"(FOR_ID),
	CONSTRAINT PAC_USUARIO_ID_FK FOREIGN KEY (PAC_USUARIO_ID) REFERENCES "public"."USUARIOS"(USU_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."PACIENTES_FORMULARIOS" IS 'Tabla intermedia que representa la relación entre las entidades PACIENTES y FORMULARIOS';
COMMENT ON COLUMN "public"."PACIENTES_FORMULARIOS".PAF_ID IS 'Columna que contiene el identificador de la relacion paciente-formulario-usuario';
COMMENT ON COLUMN "public"."PACIENTES_FORMULARIOS".PAF_PAC_ID IS 'Columna que contiene el identificador del usuario relacionado con el rol';
COMMENT ON COLUMN "public"."PACIENTES_FORMULARIOS".PAF_FORM_ID IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON COLUMN "public"."PACIENTES_FORMULARIOS".PAC_USUARIO_ID IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON COLUMN "public"."PACIENTES_FORMULARIOS".PAF_FECHA_CREA IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON CONSTRAINT PAF_ID_PK ON "public"."PACIENTES_FORMULARIOS" IS 'PK de la tabla PACIENTES_FORMULARIOS';
COMMENT ON CONSTRAINT PAF_PAC_ID_FK ON "public"."PACIENTES_FORMULARIOS" IS 'FK Relacion con la tabla PACIENTES';
COMMENT ON CONSTRAINT PAF_FORM_ID_FK ON "public"."PACIENTES_FORMULARIOS" IS 'FK Relacion con la tabla FORMULARIOS';
COMMENT ON CONSTRAINT PAC_USUARIO_ID_FK ON "public"."PACIENTES_FORMULARIOS" IS 'FK Relacion con la tabla USUARIOS';
-- ddl-end --

-- object: "public"."PACIENTES_FORMULARIOS" | type: TABLE --
DROP TABLE IF EXISTS "public"."PACIENTES_FORMULARIOS" CASCADE;
CREATE TABLE "public"."PACIENTES_FORMULARIOS" (
	PAF_ID serial,
	PAF_PAC_ID bigint NOT NULL,
	PAF_FORM_ID smallint NOT NULL,
	PAC_USUARIO_ID smallint NOT NULL,
	PAF_FECHA_CREA timestamp,
	CONSTRAINT PAF_ID_PK PRIMARY KEY (PAF_ID),
	CONSTRAINT PAF_PAC_ID_FK FOREIGN KEY (PAF_PAC_ID) REFERENCES "public"."PACIENTES"(PAC_ID),
	CONSTRAINT PAF_FORM_ID_FK FOREIGN KEY (PAF_FORM_ID) REFERENCES "public"."FORMULARIOS"(FOR_ID),
	CONSTRAINT PAC_USUARIO_ID_FK FOREIGN KEY (PAC_USUARIO_ID) REFERENCES "public"."USUARIOS"(USU_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."PACIENTES_FORMULARIOS" IS 'Tabla intermedia que representa la relación entre las entidades PACIENTES y FORMULARIOS';
COMMENT ON COLUMN "public"."PACIENTES_FORMULARIOS".PAF_ID IS 'Columna que contiene el identificador de la relacion paciente-formulario-usuario';
COMMENT ON COLUMN "public"."PACIENTES_FORMULARIOS".PAF_PAC_ID IS 'Columna que contiene el identificador del usuario relacionado con el rol';
COMMENT ON COLUMN "public"."PACIENTES_FORMULARIOS".PAF_FORM_ID IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON COLUMN "public"."PACIENTES_FORMULARIOS".PAC_USUARIO_ID IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON COLUMN "public"."PACIENTES_FORMULARIOS".PAF_FECHA_CREA IS 'Columna que contiene el identificador del rol relacion con el usuario';
COMMENT ON CONSTRAINT PAF_ID_PK ON "public"."PACIENTES_FORMULARIOS" IS 'PK de la tabla PACIENTES_FORMULARIOS';
COMMENT ON CONSTRAINT PAF_PAC_ID_FK ON "public"."PACIENTES_FORMULARIOS" IS 'FK Relacion con la tabla PACIENTES';
COMMENT ON CONSTRAINT PAF_FORM_ID_FK ON "public"."PACIENTES_FORMULARIOS" IS 'FK Relacion con la tabla FORMULARIOS';
COMMENT ON CONSTRAINT PAC_USUARIO_ID_FK ON "public"."PACIENTES_FORMULARIOS" IS 'FK Relacion con la tabla USUARIOS';
-- ddl-end --

-- object: "public"."PAC_FORM_CAMPOS" | type: TABLE --
DROP TABLE IF EXISTS "public"."PAC_FORM_CAMPOS" CASCADE;
CREATE TABLE "public"."PAC_FORM_CAMPOS" (
	PFC_ID serial,
	PFC_PAF_ID bigint NOT NULL,
	PFC_FORM_ID smallint NOT NULL,
	PFC_PAC_ID bigint NOT NULL,
	PFC_CAMPO_ID bigint NOT NULL,
	PFC_VALOR_TXT VARCHAR(2000),
	PFC_VALOR_NUM smallint,
	PFC_VALOR_BOOL boolean,
	PFC_VALOR_DATA bytea,
	CONSTRAINT PFC_ID_PK PRIMARY KEY (PFC_ID),
	CONSTRAINT PFC_PAF_ID_FK FOREIGN KEY (PFC_PAF_ID) REFERENCES "public"."PACIENTES_FORMULARIOS"(PAF_ID),
	CONSTRAINT PFC_FORM_ID_FK FOREIGN KEY (PFC_FORM_ID) REFERENCES "public"."FORMULARIOS"(FOR_ID),
	CONSTRAINT PFC_PAC_ID_FK FOREIGN KEY (PFC_PAC_ID) REFERENCES "public"."PACIENTES"(PAC_ID)
);
-- COMENTARIOS
COMMENT ON TABLE "public"."PAC_FORM_CAMPOS" IS 'Tabla intermedia que representa la relación entre las entidades PACIENTES FORMULARIOS CAMPOS';
COMMENT ON COLUMN "public"."PAC_FORM_CAMPOS".PFC_ID IS 'Columna que contiene el identificador de la relacion paciente-formulario-usuario';
COMMENT ON COLUMN "public"."PAC_FORM_CAMPOS".PFC_PAF_ID IS 'Columna que contiene el identificador de la relacion paciente formulario';
COMMENT ON COLUMN "public"."PAC_FORM_CAMPOS".PFC_FORM_ID IS 'Columna que contiene el identificador del formulario';
COMMENT ON COLUMN "public"."PAC_FORM_CAMPOS".PFC_PAC_ID IS 'Columna que contiene el identificador del paciente';
COMMENT ON COLUMN "public"."PAC_FORM_CAMPOS".PFC_CAMPO_ID IS 'Columna que contiene el identificador del campo';
COMMENT ON COLUMN "public"."PAC_FORM_CAMPOS".PFC_VALOR_TXT IS 'Columna que contiene el valor del campo cuando es de tipo texto';
COMMENT ON COLUMN "public"."PAC_FORM_CAMPOS".PFC_VALOR_NUM IS 'Columna que contiene el valor del campo cuando es de tipo numerico';
COMMENT ON COLUMN "public"."PAC_FORM_CAMPOS".PFC_VALOR_BOOL IS 'Columna que contiene el valor del campo cuando es de tipo booleano';
COMMENT ON COLUMN "public"."PAC_FORM_CAMPOS".PFC_VALOR_DATA IS 'Columna que contiene el valor del campo cuando es de tipo data';
COMMENT ON CONSTRAINT PFC_ID_PK ON "public"."PAC_FORM_CAMPOS" IS 'PK de la tabla PAC_FORM_CAMPOS';
COMMENT ON CONSTRAINT PFC_PAF_ID_FK ON "public"."PAC_FORM_CAMPOS" IS 'FK Relacion con la tabla PACIENTES_FORMULARIOS';
COMMENT ON CONSTRAINT PFC_FORM_ID_FK ON "public"."PAC_FORM_CAMPOS" IS 'FK Relacion con la tabla FORMULARIOS';
COMMENT ON CONSTRAINT PFC_PAC_ID_FK ON "public"."PAC_FORM_CAMPOS" IS 'FK Relacion con la tabla PACIENTES';
-- ddl-end --

------------------------------------------------- FIN TABLAS --------------------------------------------------------------------------------------