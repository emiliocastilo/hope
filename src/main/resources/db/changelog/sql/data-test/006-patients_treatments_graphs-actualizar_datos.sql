-- Se actualizan datos para que tengan datos de prueba con CIE9 o CIE10

update hopes.hospitals set hos_cie = 'CIE9' where hos_id = 1;

update hopes.hospitals set hos_cie = 'CIE10' where hos_id = 2;

update hopes.patients_diagnoses
set pdg_cie_code = (select cn.cid_code from hopes.cies_ten cn limit 1),
pdg_cie_description =(select cn.cid_description from hopes.cies_ten cn limit 1) where pdg_id in (1,2,3);

update hopes.patients_diagnoses
set pdg_cie_code = (select cn.cid_code from hopes.cies_ten cn limit 1 offset 5),
pdg_cie_description =(select cn.cid_description from hopes.cies_ten cn limit 1 offset 5) where pdg_id = 4;

update hopes.patients_diagnoses
set pdg_cie_code = (select cn.cin_code from hopes.cies_nine cn limit 1 ),
pdg_cie_description =(select cn.cin_description from hopes.cies_nine cn limit 1 ) where pdg_id = 5;

update hopes.patients_diagnoses
set pdg_cie_code = (select cn.cin_code from hopes.cies_nine cn limit 1 offset 5),
pdg_cie_description =(select cn.cin_description from hopes.cies_nine cn limit 1 offset 5) where pdg_id in (6,7,8,9);

update hopes.patients_diagnoses
set pdg_cie_code = (select cn.cid_code from hopes.cies_ten cn limit 1 offset 10),
pdg_cie_description =(select cn.cid_description from hopes.cies_ten cn limit 1 offset 10) where pdg_id in (10,11);
