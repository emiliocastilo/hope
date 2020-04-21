----------------------------------------------------------------- PATHOLOGIES -------------------------------------------------------------------------------

----------------------------------------------------------------- ROLES -------------------------------------------------------------------------------
INSERT INTO public.roles VALUES (1,'ROLE_ADMIN', 'Rol administrador. Tiene le máximo nivel de acceso a la aplicación');
INSERT INTO public.roles VALUES (2,'ROLE_MANAGER_CAU', 'Rol gestor del CAU. Permite la gestión de las credenciales de acceso de los diferentes usuarios de la aplicación, recuperación y asignación de perfiles');
INSERT INTO public.roles VALUES (3,'ROLE_MANAGER', 'Rol gestor. Personaliza las características de los formularios, encuestas, tramtamientos, medicamentos, etc a la aplicación');
INSERT INTO public.roles VALUES (4,'ROLE_PHARMACIST', 'Rol farmacéutico. Posee acceso al cuadro de mandos, calendario, listado de pacientes. Posibilidad de personalización de formularios relativos a su alcance');
INSERT INTO public.roles VALUES (5,'ROLE_NURSING', 'Rol enfermero. Posee acceso al cuadro de mandos, calendario, listado de pacientes. Posibilidad de personalización de formularios relativos a su alcance');
INSERT INTO public.roles VALUES (6,'ROLE_PATIENT', 'Rol gestor. Posee acceso a la información que los roles de seguimiento le concedan, ademas de a encuestas, recordatorios de consulta');
INSERT INTO public.roles VALUES (7,'ROLE_DOCTOR_DERMATOLOGY', 'Rol médico de la patología dermatología');
INSERT INTO public.roles VALUES (8,'ROLE_DOCTOR_VIH', 'Rol médico de la patología VIH');
----------------------------------------------------------------- ROLES -------------------------------------------------------------------------------

----------------------------------------------------------------- GENDERS -------------------------------------------------------------------------------
INSERT INTO public.genders VALUES ('M', 'Masculino');
INSERT INTO public.genders VALUES ('F', 'Femenino');
----------------------------------------------------------------- GENDERS -------------------------------------------------------------------------------

----------------------------------------------------------------- COUNTRIES -------------------------------------------------------------------------------
INSERT INTO public.countries VALUES ('AFG', 'Afganistán');
INSERT INTO public.countries VALUES ('ALA', 'Islas Gland');
INSERT INTO public.countries VALUES ('ALB', 'Albania');
INSERT INTO public.countries VALUES ('DEU', 'Alemania');
INSERT INTO public.countries VALUES ('AND', 'Andorra');
INSERT INTO public.countries VALUES ('AGO', 'Angola');
INSERT INTO public.countries VALUES ('AIA', 'Anguilla');
INSERT INTO public.countries VALUES ('ATA', 'Antártida');
INSERT INTO public.countries VALUES ('ATG', 'Antigua y Barbuda');
INSERT INTO public.countries VALUES ('ANT', 'Antillas Holandesas');
INSERT INTO public.countries VALUES ('SAU', 'Arabia Saudí');
INSERT INTO public.countries VALUES ('DZA', 'Argelia');
INSERT INTO public.countries VALUES ('ARG', 'Argentina');
INSERT INTO public.countries VALUES ('ARM', 'Armenia');
INSERT INTO public.countries VALUES ('ABW', 'Aruba');
INSERT INTO public.countries VALUES ('AUS', 'Australia');
INSERT INTO public.countries VALUES ('AUT', 'Austria');
INSERT INTO public.countries VALUES ('AZE', 'Azerbaiyán');
INSERT INTO public.countries VALUES ('BHS', 'Bahamas');
INSERT INTO public.countries VALUES ('BHR', 'Bahréin');
INSERT INTO public.countries VALUES ('BGD', 'Bangladesh');
INSERT INTO public.countries VALUES ('BRB', 'Barbados');
INSERT INTO public.countries VALUES ('BLR', 'Bielorrusia');
INSERT INTO public.countries VALUES ('BEL', 'Bélgica');
INSERT INTO public.countries VALUES ('BLZ', 'Belice');
INSERT INTO public.countries VALUES ('BEN', 'Benin');
INSERT INTO public.countries VALUES ('BMU', 'Bermudas');
INSERT INTO public.countries VALUES ('BTN', 'Bhután');
INSERT INTO public.countries VALUES ('BOL', 'Bolivia');
INSERT INTO public.countries VALUES ('BIH', 'Bosnia y Herzegovina');
INSERT INTO public.countries VALUES ('BWA', 'Botsuana');
INSERT INTO public.countries VALUES ('BVT', 'Isla Bouvet');
INSERT INTO public.countries VALUES ('BRA', 'Brasil');
INSERT INTO public.countries VALUES ('BRN', 'Brunéi');
INSERT INTO public.countries VALUES ('BGR', 'Bulgaria');
INSERT INTO public.countries VALUES ('BFA', 'Burkina Faso');
INSERT INTO public.countries VALUES ('BDI', 'Burundi');
INSERT INTO public.countries VALUES ('CPV', 'Cabo Verde');
INSERT INTO public.countries VALUES ('CYM', 'Islas Caimán');
INSERT INTO public.countries VALUES ('KHM', 'Camboya');
INSERT INTO public.countries VALUES ('CMR', 'Camerún');
INSERT INTO public.countries VALUES ('CAN', 'Canadá');
INSERT INTO public.countries VALUES ('CAF', 'República Centroafricana');
INSERT INTO public.countries VALUES ('TCD', 'Chad');
INSERT INTO public.countries VALUES ('CZE', 'República Checa');
INSERT INTO public.countries VALUES ('CHL', 'Chile');
INSERT INTO public.countries VALUES ('CHN', 'China');
INSERT INTO public.countries VALUES ('CYP', 'Chipre');
INSERT INTO public.countries VALUES ('CXR', 'Isla de Navidad');
INSERT INTO public.countries VALUES ('VAT', 'Ciudad del Vaticano');
INSERT INTO public.countries VALUES ('CCK', 'Islas Cocos');
INSERT INTO public.countries VALUES ('COL', 'Colombia');
INSERT INTO public.countries VALUES ('COM', 'Comoras');
INSERT INTO public.countries VALUES ('COD', 'República Democrática del Congo');
INSERT INTO public.countries VALUES ('COG', 'Congo');
INSERT INTO public.countries VALUES ('COK', 'Islas Cook');
INSERT INTO public.countries VALUES ('PRK', 'Corea del Norte');
INSERT INTO public.countries VALUES ('KOR', 'Corea del Sur');
INSERT INTO public.countries VALUES ('CIV', 'Costa de Marfil');
INSERT INTO public.countries VALUES ('CRI', 'Costa Rica');
INSERT INTO public.countries VALUES ('HRV', 'Croacia');
INSERT INTO public.countries VALUES ('CUB', 'Cuba');
INSERT INTO public.countries VALUES ('DNK', 'Dinamarca');
INSERT INTO public.countries VALUES ('DMA', 'Dominica');
INSERT INTO public.countries VALUES ('DOM', 'República Dominicana');
INSERT INTO public.countries VALUES ('ECU', 'Ecuador');
INSERT INTO public.countries VALUES ('EGY', 'Egipto');
INSERT INTO public.countries VALUES ('SLV', 'El Salvador');
INSERT INTO public.countries VALUES ('ARE', 'Emiratos Árabes Unidos');
INSERT INTO public.countries VALUES ('ERI', 'Eritrea');
INSERT INTO public.countries VALUES ('SVK', 'Eslovaquia');
INSERT INTO public.countries VALUES ('SVN', 'Eslovenia');
INSERT INTO public.countries VALUES ('ESP', 'España');
INSERT INTO public.countries VALUES ('UMI', 'Islas ultramarinas de Estados Unidos');
INSERT INTO public.countries VALUES ('USA', 'Estados Unidos');
INSERT INTO public.countries VALUES ('EST', 'Estonia');
INSERT INTO public.countries VALUES ('ETH', 'Etiopía');
INSERT INTO public.countries VALUES ('FRO', 'Islas Feroe');
INSERT INTO public.countries VALUES ('PHL', 'Filipinas');
INSERT INTO public.countries VALUES ('FIN', 'Finlandia');
INSERT INTO public.countries VALUES ('FJI', 'Fiyi');
INSERT INTO public.countries VALUES ('FRA', 'Francia');
INSERT INTO public.countries VALUES ('GAB', 'Gabón');
INSERT INTO public.countries VALUES ('GMB', 'Gambia');
INSERT INTO public.countries VALUES ('GEO', 'Georgia');
INSERT INTO public.countries VALUES ('SGS', 'Islas Georgias del Sur y Sandwich del Sur');
INSERT INTO public.countries VALUES ('GHA', 'Ghana');
INSERT INTO public.countries VALUES ('GIB', 'Gibraltar');
INSERT INTO public.countries VALUES ('GRD', 'Granada');
INSERT INTO public.countries VALUES ('GRC', 'Grecia');
INSERT INTO public.countries VALUES ('GRL', 'Groenlandia');
INSERT INTO public.countries VALUES ('GLP', 'Guadalupe');
INSERT INTO public.countries VALUES ('GUM', 'Guam');
INSERT INTO public.countries VALUES ('GTM', 'Guatemala');
INSERT INTO public.countries VALUES ('GUF', 'Guayana Francesa');
INSERT INTO public.countries VALUES ('GIN', 'Guinea');
INSERT INTO public.countries VALUES ('GNQ', 'Guinea Ecuatorial');
INSERT INTO public.countries VALUES ('GNB', 'Guinea-Bissau');
INSERT INTO public.countries VALUES ('GUY', 'Guyana');
INSERT INTO public.countries VALUES ('HTI', 'Haití');
INSERT INTO public.countries VALUES ('HMD', 'Islas Heard y McDonald');
INSERT INTO public.countries VALUES ('HND', 'Honduras');
INSERT INTO public.countries VALUES ('HKG', 'Hong Kong');
INSERT INTO public.countries VALUES ('HUN', 'Hungría');
INSERT INTO public.countries VALUES ('IND', 'India');
INSERT INTO public.countries VALUES ('IDN', 'Indonesia');
INSERT INTO public.countries VALUES ('IRN', 'Irán');
INSERT INTO public.countries VALUES ('IRQ', 'Iraq');
INSERT INTO public.countries VALUES ('IRL', 'Irlanda');
INSERT INTO public.countries VALUES ('ISL', 'Islandia');
INSERT INTO public.countries VALUES ('ISR', 'Israel');
INSERT INTO public.countries VALUES ('ITA', 'Italia');
INSERT INTO public.countries VALUES ('JAM', 'Jamaica');
INSERT INTO public.countries VALUES ('JPN', 'Japón');
INSERT INTO public.countries VALUES ('JOR', 'Jordania');
INSERT INTO public.countries VALUES ('KAZ', 'Kazajstán');
INSERT INTO public.countries VALUES ('KEN', 'Kenia');
INSERT INTO public.countries VALUES ('KGZ', 'Kirguistán');
INSERT INTO public.countries VALUES ('KIR', 'Kiribati');
INSERT INTO public.countries VALUES ('KWT', 'Kuwait');
INSERT INTO public.countries VALUES ('LAO', 'Laos');
INSERT INTO public.countries VALUES ('LSO', 'Lesotho');
INSERT INTO public.countries VALUES ('LVA', 'Letonia');
INSERT INTO public.countries VALUES ('LBN', 'Líbano');
INSERT INTO public.countries VALUES ('LBR', 'Liberia');
INSERT INTO public.countries VALUES ('LBY', 'Libia');
INSERT INTO public.countries VALUES ('LIE', 'Liechtenstein');
INSERT INTO public.countries VALUES ('LTU', 'Lituania');
INSERT INTO public.countries VALUES ('LUX', 'Luxemburgo');
INSERT INTO public.countries VALUES ('MAC', 'Macao');
INSERT INTO public.countries VALUES ('MKD', 'ARY Macedonia');
INSERT INTO public.countries VALUES ('MDG', 'Madagascar');
INSERT INTO public.countries VALUES ('MYS', 'Malasia');
INSERT INTO public.countries VALUES ('MWI', 'Malawi');
INSERT INTO public.countries VALUES ('MDV', 'Maldivas');
INSERT INTO public.countries VALUES ('MLI', 'Malí');
INSERT INTO public.countries VALUES ('MLT', 'Malta');
INSERT INTO public.countries VALUES ('FLK', 'Islas Malvinas');
INSERT INTO public.countries VALUES ('MNP', 'Islas Marianas del Norte');
INSERT INTO public.countries VALUES ('MAR', 'Marruecos');
INSERT INTO public.countries VALUES ('MHL', 'Islas Marshall');
INSERT INTO public.countries VALUES ('MTQ', 'Martinica');
INSERT INTO public.countries VALUES ('MUS', 'Mauricio');
INSERT INTO public.countries VALUES ('MRT', 'Mauritania');
INSERT INTO public.countries VALUES ('MYT', 'Mayotte');
INSERT INTO public.countries VALUES ('MEX', 'México');
INSERT INTO public.countries VALUES ('FSM', 'Micronesia');
INSERT INTO public.countries VALUES ('MDA', 'Moldavia');
INSERT INTO public.countries VALUES ('MCO', 'Mónaco');
INSERT INTO public.countries VALUES ('MNG', 'Mongolia');
INSERT INTO public.countries VALUES ('MSR', 'Montserrat');
INSERT INTO public.countries VALUES ('MOZ', 'Mozambique');
INSERT INTO public.countries VALUES ('MMR', 'Myanmar');
INSERT INTO public.countries VALUES ('NAM', 'Namibia');
INSERT INTO public.countries VALUES ('NRU', 'Nauru');
INSERT INTO public.countries VALUES ('NPL', 'Nepal');
INSERT INTO public.countries VALUES ('NIC', 'Nicaragua');
INSERT INTO public.countries VALUES ('NER', 'Níger');
INSERT INTO public.countries VALUES ('NGA', 'Nigeria');
INSERT INTO public.countries VALUES ('NIU', 'Niue');
INSERT INTO public.countries VALUES ('NFK', 'Isla Norfolk');
INSERT INTO public.countries VALUES ('NOR', 'Noruega');
INSERT INTO public.countries VALUES ('NCL', 'Nueva Caledonia');
INSERT INTO public.countries VALUES ('NZL', 'Nueva Zelanda');
INSERT INTO public.countries VALUES ('OMN', 'Omán');
INSERT INTO public.countries VALUES ('NLD', 'Países Bajos');
INSERT INTO public.countries VALUES ('PAK', 'Pakistán');
INSERT INTO public.countries VALUES ('PLW', 'Palau');
INSERT INTO public.countries VALUES ('PSE', 'Palestina');
INSERT INTO public.countries VALUES ('PAN', 'Panamá');
INSERT INTO public.countries VALUES ('PNG', 'Papúa Nueva Guinea');
INSERT INTO public.countries VALUES ('PRY', 'Paraguay');
INSERT INTO public.countries VALUES ('PER', 'Perú');
INSERT INTO public.countries VALUES ('PCN', 'Islas Pitcairn');
INSERT INTO public.countries VALUES ('PYF', 'Polinesia Francesa');
INSERT INTO public.countries VALUES ('POL', 'Polonia');
INSERT INTO public.countries VALUES ('PRT', 'Portugal');
INSERT INTO public.countries VALUES ('PRI', 'Puerto Rico');
INSERT INTO public.countries VALUES ('QAT', 'Qatar');
INSERT INTO public.countries VALUES ('GBR', 'Reino Unido');
INSERT INTO public.countries VALUES ('REU', 'Reunión');
INSERT INTO public.countries VALUES ('RWA', 'Ruanda');
INSERT INTO public.countries VALUES ('ROU', 'Rumania');
INSERT INTO public.countries VALUES ('RUS', 'Rusia');
INSERT INTO public.countries VALUES ('ESH', 'Sahara Occidental');
INSERT INTO public.countries VALUES ('SLB', 'Islas Salomón');
INSERT INTO public.countries VALUES ('WSM', 'Samoa');
INSERT INTO public.countries VALUES ('ASM', 'Samoa Americana');
INSERT INTO public.countries VALUES ('KNA', 'San Cristóbal y Nevis');
INSERT INTO public.countries VALUES ('SMR', 'San Marino');
INSERT INTO public.countries VALUES ('SPM', 'San Pedro y Miquelón');
INSERT INTO public.countries VALUES ('VCT', 'San Vicente y las Granadinas');
INSERT INTO public.countries VALUES ('SHN', 'Santa Helena');
INSERT INTO public.countries VALUES ('LCA', 'Santa Lucía');
INSERT INTO public.countries VALUES ('STP', 'Santo Tomé y Príncipe');
INSERT INTO public.countries VALUES ('SEN', 'Senegal');
INSERT INTO public.countries VALUES ('SCG', 'Serbia y Montenegro');
INSERT INTO public.countries VALUES ('SYC', 'Seychelles');
INSERT INTO public.countries VALUES ('SLE', 'Sierra Leona');
INSERT INTO public.countries VALUES ('SGP', 'Singapur');
INSERT INTO public.countries VALUES ('SYR', 'Siria');
INSERT INTO public.countries VALUES ('SOM', 'Somalia');
INSERT INTO public.countries VALUES ('LKA', 'Sri Lanka');
INSERT INTO public.countries VALUES ('SWZ', 'Suazilandia');
INSERT INTO public.countries VALUES ('ZAF', 'Sudáfrica');
INSERT INTO public.countries VALUES ('SDN', 'Sudán');
INSERT INTO public.countries VALUES ('SWE', 'Suecia');
INSERT INTO public.countries VALUES ('CHE', 'Suiza');
INSERT INTO public.countries VALUES ('SUR', 'Surinam');
INSERT INTO public.countries VALUES ('SJM', 'Svalbard y Jan Mayen');
INSERT INTO public.countries VALUES ('THA', 'Tailandia');
INSERT INTO public.countries VALUES ('TWN', 'Taiwán');
INSERT INTO public.countries VALUES ('TZA', 'Tanzania');
INSERT INTO public.countries VALUES ('TJK', 'Tayikistán');
INSERT INTO public.countries VALUES ('IOT', 'Territorio Británico del Océano Índico');
INSERT INTO public.countries VALUES ('ATF', 'Territorios Australes Franceses');
INSERT INTO public.countries VALUES ('TLS', 'Timor Oriental');
INSERT INTO public.countries VALUES ('TGO', 'Togo');
INSERT INTO public.countries VALUES ('TKL', 'Tokelau');
INSERT INTO public.countries VALUES ('TON', 'Tonga');
INSERT INTO public.countries VALUES ('TTO', 'Trinidad y Tobago');
INSERT INTO public.countries VALUES ('TUN', 'Túnez');
INSERT INTO public.countries VALUES ('TCA', 'Islas Turcas y Caicos');
INSERT INTO public.countries VALUES ('TKM', 'Turkmenistán');
INSERT INTO public.countries VALUES ('TUR', 'Turquía');
INSERT INTO public.countries VALUES ('TUV', 'Tuvalu');
INSERT INTO public.countries VALUES ('UKR', 'Ucrania');
INSERT INTO public.countries VALUES ('UGA', 'Uganda');
INSERT INTO public.countries VALUES ('URY', 'Uruguay');
INSERT INTO public.countries VALUES ('UZB', 'Uzbekistán');
INSERT INTO public.countries VALUES ('VUT', 'Vanuatu');
INSERT INTO public.countries VALUES ('VEN', 'Venezuela');
INSERT INTO public.countries VALUES ('VNM', 'Vietnam');
INSERT INTO public.countries VALUES ('VGB', 'Islas Vírgenes Británicas');
INSERT INTO public.countries VALUES ('VIR', 'Islas Vírgenes de los Estados Unidos');
INSERT INTO public.countries VALUES ('WLF', 'Wallis y Futuna');
INSERT INTO public.countries VALUES ('YEM', 'Yemen');
INSERT INTO public.countries VALUES ('DJI', 'Yibuti');
INSERT INTO public.countries VALUES ('ZMB', 'Zambia');
INSERT INTO public.countries VALUES ('ZWE', 'Zimbabue');
----------------------------------------------------------------- COUNTRIES -------------------------------------------------------------------------------
