ALTER TABLE hopes.patients_treatments ADD COLUMN ptr_special_indication BOOLEAN DEFAULT false;

alter table hopes.patients_treatments_lines  add column ptl_init_date timestamp null;
alter table hopes.patients_treatments_lines  add column ptl_final_date timestamp null;
alter table hopes.patients_treatments_lines  add column ptl_date_prescription timestamp null;
alter table hopes.patients_treatments_lines  add column ptl_expected_end_date timestamp null;
alter table hopes.patients_treatments_lines  add column ptl_observations varchar(255) null;
alter table hopes.patients_treatments_lines  add column ptl_other varchar(255) null;