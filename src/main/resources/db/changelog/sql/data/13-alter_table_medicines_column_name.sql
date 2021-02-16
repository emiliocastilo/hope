-- Corregir nombre columnas tabla nedicines
ALTER TABLE hopes.medicines RENAME COLUMN med_code_act TO med_code_atc;
ALTER TABLE hopes.medicines RENAME COLUMN med_code_act_type TO med_code_atc_type;
