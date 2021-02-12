ALTER TABLE hopes.pathologies ADD COLUMN pth_code VARCHAR(100);

ALTER TABLE hopes.patients_treatments ALTER COLUMN  ptr_dose TYPE VARCHAR(255);