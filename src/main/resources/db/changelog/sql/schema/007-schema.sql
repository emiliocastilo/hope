-- object: public.dispensations | type: TABLE --
DROP TABLE IF EXISTS public.dispensations CASCADE;
CREATE TABLE public.dispensations
(
    dsp_id bigserial,
    dsp_date timestamp NOT NULL,
    dsp_start_period timestamp NOT NULL,
    dsp_end_period timestamp NOT NULL,
    dsp_num_records int NOT NULL,
    CONSTRAINT dsp_id_pk PRIMARY KEY (dsp_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.dispensations IS 'Tabla que representa la entidad DISPENSATIONS';
COMMENT ON COLUMN public.dispensations.dsp_id IS 'Columna que contiene el identificador de la carga de dispensaciones. (pk)';
COMMENT ON COLUMN public.dispensations.dsp_date IS 'Columna que indica la fecha de carga de las dispensaciones';
COMMENT ON COLUMN public.dispensations.dsp_start_period IS 'Columna que indica el inicio del periodo de las dispensaciones';
COMMENT ON COLUMN public.dispensations.dsp_end_period IS 'Columna que indica el fin del periodo de las dispensaciones';
COMMENT ON COLUMN public.dispensations.dsp_num_records IS 'Columna que indica el número de dispensaciones de la carga';
COMMENT ON CONSTRAINT dsp_id_pk ON public.dispensations IS 'pk de la tabla DISPENSATIONS';

-- object: public.dispensation_details | type: TABLE --
DROP TABLE IF EXISTS public.dispensation_details CASCADE;
CREATE TABLE public.dispensation_details
(
    dsd_id bigserial,
    dsd_date timestamp NOT NULL,
    dsd_nhc varchar(50),
    dsd_code varchar(9),
    dsd_national_code int,
    dsd_description varchar(100),
    dsd_quantity varchar(100),
    dsd_amount decimal(13,2),
    dsd_days_disp int,
    dsd_dsp_id bigint,
    CONSTRAINT dsd_id_pk PRIMARY KEY (dsd_id),
    CONSTRAINT dsd_dsp_id_fk FOREIGN KEY (dsd_dsp_id) REFERENCES public.dispensations(dsp_id)
);
-- COMENTARIOS
COMMENT ON TABLE public.dispensation_details IS 'Tabla que representa la entidad DISPENSATION_DETAILS';
COMMENT ON COLUMN public.dispensation_details.dsd_id IS 'Columna que contiene el identificador de la dispensación. (pk)';
COMMENT ON COLUMN public.dispensation_details.dsd_date IS 'Columna que indica la fecha de las dispensación';
COMMENT ON COLUMN public.dispensation_details.dsd_nhc IS 'Columna que contiene el número de historia clínica del paciente al cual se le ha prescito la medicación';
COMMENT ON COLUMN public.dispensation_details.dsd_code IS 'Columna que contiene el código del medicamento';
COMMENT ON COLUMN public.dispensation_details.dsd_national_code IS 'Columna que contiene el código nacional del medicamento';
COMMENT ON COLUMN public.dispensation_details.dsd_description IS 'Columna que contiene la descripción de la dispensación';
COMMENT ON COLUMN public.dispensation_details.dsd_quantity IS 'Columna que indica cantidad a tomar por el paciente';
COMMENT ON COLUMN public.dispensation_details.dsd_amount IS 'Columna que indica el precio de la medicación';
COMMENT ON COLUMN public.dispensation_details.dsd_days_disp IS 'Columna que indica el número de dias de la dispensación proporcionada';
COMMENT ON COLUMN public.dispensation_details.dsd_dsp_id IS 'Columna que indica el número de dispensaciones de la carga';
COMMENT ON CONSTRAINT dsd_id_pk ON public.dispensation_details IS 'pk de la tabla DISPENSATION_DETAILS';
COMMENT ON CONSTRAINT dsd_dsp_id_fk ON public.dispensation_details IS 'fk Relación con la tabla DISPENSATIONS';