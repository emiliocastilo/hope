-- object: public.hospitals_services | type: TABLE --
DROP TABLE IF EXISTS public.hospitals_services CASCADE;
CREATE TABLE public.hospitals_services
(
    hss_id     serial,
    hss_hos_id smallint NOT NULL,
    hss_srv_id smallint NOT NULL,
    CONSTRAINT hss_id_pk PRIMARY KEY (hss_id),
    CONSTRAINT hss_hos_id_fk FOREIGN KEY (hss_hos_id) REFERENCES public.hospitals (hos_id),
    CONSTRAINT hss_srv_id_fk FOREIGN KEY (hss_srv_id) REFERENCES public.services (srv_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.hospitals_services IS 'Tabla intermedia que representa la relación entre las entidades HOSPITALS y SERVICES';
COMMENT ON COLUMN public.hospitals_services.hss_id IS 'Columna con el identificador de base de datos de la relación entre las tablas HOSPITALS y SERVICES(pk)';
COMMENT ON COLUMN public.hospitals_services.hss_hos_id IS 'Columna con el id del hospital';
COMMENT ON COLUMN public.hospitals_services.hss_srv_id IS 'Columna con el id del servicio';
COMMENT ON CONSTRAINT hss_id_pk ON public.hospitals_services IS 'pk de la tabla HOSPITALS_SERVICES';
COMMENT ON CONSTRAINT hss_hos_id_fk ON public.hospitals_services IS 'fk Relacion con la tabla HOSPITALS';
COMMENT ON CONSTRAINT hss_srv_id_fk ON public.hospitals_services IS 'fk Relacion con la tabla SERVICES';
-- ddl-end --