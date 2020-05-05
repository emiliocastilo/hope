---------------------------------- AÑADIR COLUMNA DE TRADUCIONES A ROL ----------------------------
ALTER TABLE public.roles ADD COLUMN rol_traduction VARCHAR(100);
COMMENT ON COLUMN public.roles.rol_traduction IS 'Columna que contiene la traduccion del nombre del rol';
