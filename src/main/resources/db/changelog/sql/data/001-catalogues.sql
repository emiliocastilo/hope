----------------------------------------------------------------- ROLES -------------------------------------------------------------------------------
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Administrador', 'Rol administrador. Tiene le máximo nivel de acceso a la aplicación');
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Gestor CAU', 'Rol gestor del CAU. Permite la gestión de las credenciales de acceso de los diferentes usuarios de la aplicación, recuperación y asignación de perfiles');
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Gestor', 'Rol gestor. Personaliza las características de los formularios, encuestas, tramtamientos, medicamentos, etc a la aplicación');
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Farmacéutico', 'Rol farmacéutico. Posee acceso al cuadro de mandos, calendario, listado de pacientes. Posibilidad de personalización de formularios relativos a su alcance');
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Médico Dermatología', 'Rol médico de la patología dermatología');
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Médico VIH', 'Rol médico de la patología VIH');
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Médica Reumatología', 'Rol médico de la patología reumatología');
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Enfermero', 'Rol enfermero. Posee acceso al cuadro de mandos, calendario, listado de pacientes. Posibilidad de personalización de formularios relativos a su alcance');
INSERT INTO hopes.roles VALUES (nextval('hopes.users_usr_id_seq'),'Paciente', 'Rol gestor. Posee acceso a la información que los roles de seguimiento le concedan, ademas de a encuestas, recordatorios de consulta');
----------------------------------------------------------------- ROLES -------------------------------------------------------------------------------

----------------------------------------------------------------- GENDERS -------------------------------------------------------------------------------
INSERT INTO hopes.genders VALUES ('M', 'Masculino');
INSERT INTO hopes.genders VALUES ('F', 'Femenino');
----------------------------------------------------------------- GENDERS -------------------------------------------------------------------------------

----------------------------------------------------------------- COUNTRIES -------------------------------------------------------------------------------
INSERT INTO hopes.countries VALUES ('AFG', 'Afganistán');
INSERT INTO hopes.countries VALUES ('ALA', 'Islas Gland');
INSERT INTO hopes.countries VALUES ('ALB', 'Albania');
INSERT INTO hopes.countries VALUES ('DEU', 'Alemania');
INSERT INTO hopes.countries VALUES ('AND', 'Andorra');
INSERT INTO hopes.countries VALUES ('AGO', 'Angola');
INSERT INTO hopes.countries VALUES ('AIA', 'Anguilla');
INSERT INTO hopes.countries VALUES ('ATA', 'Antártida');
INSERT INTO hopes.countries VALUES ('ATG', 'Antigua y Barbuda');
INSERT INTO hopes.countries VALUES ('ANT', 'Antillas Holandesas');
INSERT INTO hopes.countries VALUES ('SAU', 'Arabia Saudí');
INSERT INTO hopes.countries VALUES ('DZA', 'Argelia');
INSERT INTO hopes.countries VALUES ('ARG', 'Argentina');
INSERT INTO hopes.countries VALUES ('ARM', 'Armenia');
INSERT INTO hopes.countries VALUES ('ABW', 'Aruba');
INSERT INTO hopes.countries VALUES ('AUS', 'Australia');
INSERT INTO hopes.countries VALUES ('AUT', 'Austria');
INSERT INTO hopes.countries VALUES ('AZE', 'Azerbaiyán');
INSERT INTO hopes.countries VALUES ('BHS', 'Bahamas');
INSERT INTO hopes.countries VALUES ('BHR', 'Bahréin');
INSERT INTO hopes.countries VALUES ('BGD', 'Bangladesh');
INSERT INTO hopes.countries VALUES ('BRB', 'Barbados');
INSERT INTO hopes.countries VALUES ('BLR', 'Bielorrusia');
INSERT INTO hopes.countries VALUES ('BEL', 'Bélgica');
INSERT INTO hopes.countries VALUES ('BLZ', 'Belice');
INSERT INTO hopes.countries VALUES ('BEN', 'Benin');
INSERT INTO hopes.countries VALUES ('BMU', 'Bermudas');
INSERT INTO hopes.countries VALUES ('BTN', 'Bhután');
INSERT INTO hopes.countries VALUES ('BOL', 'Bolivia');
INSERT INTO hopes.countries VALUES ('BIH', 'Bosnia y Herzegovina');
INSERT INTO hopes.countries VALUES ('BWA', 'Botsuana');
INSERT INTO hopes.countries VALUES ('BVT', 'Isla Bouvet');
INSERT INTO hopes.countries VALUES ('BRA', 'Brasil');
INSERT INTO hopes.countries VALUES ('BRN', 'Brunéi');
INSERT INTO hopes.countries VALUES ('BGR', 'Bulgaria');
INSERT INTO hopes.countries VALUES ('BFA', 'Burkina Faso');
INSERT INTO hopes.countries VALUES ('BDI', 'Burundi');
INSERT INTO hopes.countries VALUES ('CPV', 'Cabo Verde');
INSERT INTO hopes.countries VALUES ('CYM', 'Islas Caimán');
INSERT INTO hopes.countries VALUES ('KHM', 'Camboya');
INSERT INTO hopes.countries VALUES ('CMR', 'Camerún');
INSERT INTO hopes.countries VALUES ('CAN', 'Canadá');
INSERT INTO hopes.countries VALUES ('CAF', 'República Centroafricana');
INSERT INTO hopes.countries VALUES ('TCD', 'Chad');
INSERT INTO hopes.countries VALUES ('CZE', 'República Checa');
INSERT INTO hopes.countries VALUES ('CHL', 'Chile');
INSERT INTO hopes.countries VALUES ('CHN', 'China');
INSERT INTO hopes.countries VALUES ('CYP', 'Chipre');
INSERT INTO hopes.countries VALUES ('CXR', 'Isla de Navidad');
INSERT INTO hopes.countries VALUES ('VAT', 'Ciudad del Vaticano');
INSERT INTO hopes.countries VALUES ('CCK', 'Islas Cocos');
INSERT INTO hopes.countries VALUES ('COL', 'Colombia');
INSERT INTO hopes.countries VALUES ('COM', 'Comoras');
INSERT INTO hopes.countries VALUES ('COD', 'República Democrática del Congo');
INSERT INTO hopes.countries VALUES ('COG', 'Congo');
INSERT INTO hopes.countries VALUES ('COK', 'Islas Cook');
INSERT INTO hopes.countries VALUES ('PRK', 'Corea del Norte');
INSERT INTO hopes.countries VALUES ('KOR', 'Corea del Sur');
INSERT INTO hopes.countries VALUES ('CIV', 'Costa de Marfil');
INSERT INTO hopes.countries VALUES ('CRI', 'Costa Rica');
INSERT INTO hopes.countries VALUES ('HRV', 'Croacia');
INSERT INTO hopes.countries VALUES ('CUB', 'Cuba');
INSERT INTO hopes.countries VALUES ('DNK', 'Dinamarca');
INSERT INTO hopes.countries VALUES ('DMA', 'Dominica');
INSERT INTO hopes.countries VALUES ('DOM', 'República Dominicana');
INSERT INTO hopes.countries VALUES ('ECU', 'Ecuador');
INSERT INTO hopes.countries VALUES ('EGY', 'Egipto');
INSERT INTO hopes.countries VALUES ('SLV', 'El Salvador');
INSERT INTO hopes.countries VALUES ('ARE', 'Emiratos Árabes Unidos');
INSERT INTO hopes.countries VALUES ('ERI', 'Eritrea');
INSERT INTO hopes.countries VALUES ('SVK', 'Eslovaquia');
INSERT INTO hopes.countries VALUES ('SVN', 'Eslovenia');
INSERT INTO hopes.countries VALUES ('ESP', 'España');
INSERT INTO hopes.countries VALUES ('UMI', 'Islas ultramarinas de Estados Unidos');
INSERT INTO hopes.countries VALUES ('USA', 'Estados Unidos');
INSERT INTO hopes.countries VALUES ('EST', 'Estonia');
INSERT INTO hopes.countries VALUES ('ETH', 'Etiopía');
INSERT INTO hopes.countries VALUES ('FRO', 'Islas Feroe');
INSERT INTO hopes.countries VALUES ('PHL', 'Filipinas');
INSERT INTO hopes.countries VALUES ('FIN', 'Finlandia');
INSERT INTO hopes.countries VALUES ('FJI', 'Fiyi');
INSERT INTO hopes.countries VALUES ('FRA', 'Francia');
INSERT INTO hopes.countries VALUES ('GAB', 'Gabón');
INSERT INTO hopes.countries VALUES ('GMB', 'Gambia');
INSERT INTO hopes.countries VALUES ('GEO', 'Georgia');
INSERT INTO hopes.countries VALUES ('SGS', 'Islas Georgias del Sur y Sandwich del Sur');
INSERT INTO hopes.countries VALUES ('GHA', 'Ghana');
INSERT INTO hopes.countries VALUES ('GIB', 'Gibraltar');
INSERT INTO hopes.countries VALUES ('GRD', 'Granada');
INSERT INTO hopes.countries VALUES ('GRC', 'Grecia');
INSERT INTO hopes.countries VALUES ('GRL', 'Groenlandia');
INSERT INTO hopes.countries VALUES ('GLP', 'Guadalupe');
INSERT INTO hopes.countries VALUES ('GUM', 'Guam');
INSERT INTO hopes.countries VALUES ('GTM', 'Guatemala');
INSERT INTO hopes.countries VALUES ('GUF', 'Guayana Francesa');
INSERT INTO hopes.countries VALUES ('GIN', 'Guinea');
INSERT INTO hopes.countries VALUES ('GNQ', 'Guinea Ecuatorial');
INSERT INTO hopes.countries VALUES ('GNB', 'Guinea-Bissau');
INSERT INTO hopes.countries VALUES ('GUY', 'Guyana');
INSERT INTO hopes.countries VALUES ('HTI', 'Haití');
INSERT INTO hopes.countries VALUES ('HMD', 'Islas Heard y McDonald');
INSERT INTO hopes.countries VALUES ('HND', 'Honduras');
INSERT INTO hopes.countries VALUES ('HKG', 'Hong Kong');
INSERT INTO hopes.countries VALUES ('HUN', 'Hungría');
INSERT INTO hopes.countries VALUES ('IND', 'India');
INSERT INTO hopes.countries VALUES ('IDN', 'Indonesia');
INSERT INTO hopes.countries VALUES ('IRN', 'Irán');
INSERT INTO hopes.countries VALUES ('IRQ', 'Iraq');
INSERT INTO hopes.countries VALUES ('IRL', 'Irlanda');
INSERT INTO hopes.countries VALUES ('ISL', 'Islandia');
INSERT INTO hopes.countries VALUES ('ISR', 'Israel');
INSERT INTO hopes.countries VALUES ('ITA', 'Italia');
INSERT INTO hopes.countries VALUES ('JAM', 'Jamaica');
INSERT INTO hopes.countries VALUES ('JPN', 'Japón');
INSERT INTO hopes.countries VALUES ('JOR', 'Jordania');
INSERT INTO hopes.countries VALUES ('KAZ', 'Kazajstán');
INSERT INTO hopes.countries VALUES ('KEN', 'Kenia');
INSERT INTO hopes.countries VALUES ('KGZ', 'Kirguistán');
INSERT INTO hopes.countries VALUES ('KIR', 'Kiribati');
INSERT INTO hopes.countries VALUES ('KWT', 'Kuwait');
INSERT INTO hopes.countries VALUES ('LAO', 'Laos');
INSERT INTO hopes.countries VALUES ('LSO', 'Lesotho');
INSERT INTO hopes.countries VALUES ('LVA', 'Letonia');
INSERT INTO hopes.countries VALUES ('LBN', 'Líbano');
INSERT INTO hopes.countries VALUES ('LBR', 'Liberia');
INSERT INTO hopes.countries VALUES ('LBY', 'Libia');
INSERT INTO hopes.countries VALUES ('LIE', 'Liechtenstein');
INSERT INTO hopes.countries VALUES ('LTU', 'Lituania');
INSERT INTO hopes.countries VALUES ('LUX', 'Luxemburgo');
INSERT INTO hopes.countries VALUES ('MAC', 'Macao');
INSERT INTO hopes.countries VALUES ('MKD', 'ARY Macedonia');
INSERT INTO hopes.countries VALUES ('MDG', 'Madagascar');
INSERT INTO hopes.countries VALUES ('MYS', 'Malasia');
INSERT INTO hopes.countries VALUES ('MWI', 'Malawi');
INSERT INTO hopes.countries VALUES ('MDV', 'Maldivas');
INSERT INTO hopes.countries VALUES ('MLI', 'Malí');
INSERT INTO hopes.countries VALUES ('MLT', 'Malta');
INSERT INTO hopes.countries VALUES ('FLK', 'Islas Malvinas');
INSERT INTO hopes.countries VALUES ('MNP', 'Islas Marianas del Norte');
INSERT INTO hopes.countries VALUES ('MAR', 'Marruecos');
INSERT INTO hopes.countries VALUES ('MHL', 'Islas Marshall');
INSERT INTO hopes.countries VALUES ('MTQ', 'Martinica');
INSERT INTO hopes.countries VALUES ('MUS', 'Mauricio');
INSERT INTO hopes.countries VALUES ('MRT', 'Mauritania');
INSERT INTO hopes.countries VALUES ('MYT', 'Mayotte');
INSERT INTO hopes.countries VALUES ('MEX', 'México');
INSERT INTO hopes.countries VALUES ('FSM', 'Micronesia');
INSERT INTO hopes.countries VALUES ('MDA', 'Moldavia');
INSERT INTO hopes.countries VALUES ('MCO', 'Mónaco');
INSERT INTO hopes.countries VALUES ('MNG', 'Mongolia');
INSERT INTO hopes.countries VALUES ('MSR', 'Montserrat');
INSERT INTO hopes.countries VALUES ('MOZ', 'Mozambique');
INSERT INTO hopes.countries VALUES ('MMR', 'Myanmar');
INSERT INTO hopes.countries VALUES ('NAM', 'Namibia');
INSERT INTO hopes.countries VALUES ('NRU', 'Nauru');
INSERT INTO hopes.countries VALUES ('NPL', 'Nepal');
INSERT INTO hopes.countries VALUES ('NIC', 'Nicaragua');
INSERT INTO hopes.countries VALUES ('NER', 'Níger');
INSERT INTO hopes.countries VALUES ('NGA', 'Nigeria');
INSERT INTO hopes.countries VALUES ('NIU', 'Niue');
INSERT INTO hopes.countries VALUES ('NFK', 'Isla Norfolk');
INSERT INTO hopes.countries VALUES ('NOR', 'Noruega');
INSERT INTO hopes.countries VALUES ('NCL', 'Nueva Caledonia');
INSERT INTO hopes.countries VALUES ('NZL', 'Nueva Zelanda');
INSERT INTO hopes.countries VALUES ('OMN', 'Omán');
INSERT INTO hopes.countries VALUES ('NLD', 'Países Bajos');
INSERT INTO hopes.countries VALUES ('PAK', 'Pakistán');
INSERT INTO hopes.countries VALUES ('PLW', 'Palau');
INSERT INTO hopes.countries VALUES ('PSE', 'Palestina');
INSERT INTO hopes.countries VALUES ('PAN', 'Panamá');
INSERT INTO hopes.countries VALUES ('PNG', 'Papúa Nueva Guinea');
INSERT INTO hopes.countries VALUES ('PRY', 'Paraguay');
INSERT INTO hopes.countries VALUES ('PER', 'Perú');
INSERT INTO hopes.countries VALUES ('PCN', 'Islas Pitcairn');
INSERT INTO hopes.countries VALUES ('PYF', 'Polinesia Francesa');
INSERT INTO hopes.countries VALUES ('POL', 'Polonia');
INSERT INTO hopes.countries VALUES ('PRT', 'Portugal');
INSERT INTO hopes.countries VALUES ('PRI', 'Puerto Rico');
INSERT INTO hopes.countries VALUES ('QAT', 'Qatar');
INSERT INTO hopes.countries VALUES ('GBR', 'Reino Unido');
INSERT INTO hopes.countries VALUES ('REU', 'Reunión');
INSERT INTO hopes.countries VALUES ('RWA', 'Ruanda');
INSERT INTO hopes.countries VALUES ('ROU', 'Rumania');
INSERT INTO hopes.countries VALUES ('RUS', 'Rusia');
INSERT INTO hopes.countries VALUES ('ESH', 'Sahara Occidental');
INSERT INTO hopes.countries VALUES ('SLB', 'Islas Salomón');
INSERT INTO hopes.countries VALUES ('WSM', 'Samoa');
INSERT INTO hopes.countries VALUES ('ASM', 'Samoa Americana');
INSERT INTO hopes.countries VALUES ('KNA', 'San Cristóbal y Nevis');
INSERT INTO hopes.countries VALUES ('SMR', 'San Marino');
INSERT INTO hopes.countries VALUES ('SPM', 'San Pedro y Miquelón');
INSERT INTO hopes.countries VALUES ('VCT', 'San Vicente y las Granadinas');
INSERT INTO hopes.countries VALUES ('SHN', 'Santa Helena');
INSERT INTO hopes.countries VALUES ('LCA', 'Santa Lucía');
INSERT INTO hopes.countries VALUES ('STP', 'Santo Tomé y Príncipe');
INSERT INTO hopes.countries VALUES ('SEN', 'Senegal');
INSERT INTO hopes.countries VALUES ('SCG', 'Serbia y Montenegro');
INSERT INTO hopes.countries VALUES ('SYC', 'Seychelles');
INSERT INTO hopes.countries VALUES ('SLE', 'Sierra Leona');
INSERT INTO hopes.countries VALUES ('SGP', 'Singapur');
INSERT INTO hopes.countries VALUES ('SYR', 'Siria');
INSERT INTO hopes.countries VALUES ('SOM', 'Somalia');
INSERT INTO hopes.countries VALUES ('LKA', 'Sri Lanka');
INSERT INTO hopes.countries VALUES ('SWZ', 'Suazilandia');
INSERT INTO hopes.countries VALUES ('ZAF', 'Sudáfrica');
INSERT INTO hopes.countries VALUES ('SDN', 'Sudán');
INSERT INTO hopes.countries VALUES ('SWE', 'Suecia');
INSERT INTO hopes.countries VALUES ('CHE', 'Suiza');
INSERT INTO hopes.countries VALUES ('SUR', 'Surinam');
INSERT INTO hopes.countries VALUES ('SJM', 'Svalbard y Jan Mayen');
INSERT INTO hopes.countries VALUES ('THA', 'Tailandia');
INSERT INTO hopes.countries VALUES ('TWN', 'Taiwán');
INSERT INTO hopes.countries VALUES ('TZA', 'Tanzania');
INSERT INTO hopes.countries VALUES ('TJK', 'Tayikistán');
INSERT INTO hopes.countries VALUES ('IOT', 'Territorio Británico del Océano Índico');
INSERT INTO hopes.countries VALUES ('ATF', 'Territorios Australes Franceses');
INSERT INTO hopes.countries VALUES ('TLS', 'Timor Oriental');
INSERT INTO hopes.countries VALUES ('TGO', 'Togo');
INSERT INTO hopes.countries VALUES ('TKL', 'Tokelau');
INSERT INTO hopes.countries VALUES ('TON', 'Tonga');
INSERT INTO hopes.countries VALUES ('TTO', 'Trinidad y Tobago');
INSERT INTO hopes.countries VALUES ('TUN', 'Túnez');
INSERT INTO hopes.countries VALUES ('TCA', 'Islas Turcas y Caicos');
INSERT INTO hopes.countries VALUES ('TKM', 'Turkmenistán');
INSERT INTO hopes.countries VALUES ('TUR', 'Turquía');
INSERT INTO hopes.countries VALUES ('TUV', 'Tuvalu');
INSERT INTO hopes.countries VALUES ('UKR', 'Ucrania');
INSERT INTO hopes.countries VALUES ('UGA', 'Uganda');
INSERT INTO hopes.countries VALUES ('URY', 'Uruguay');
INSERT INTO hopes.countries VALUES ('UZB', 'Uzbekistán');
INSERT INTO hopes.countries VALUES ('VUT', 'Vanuatu');
INSERT INTO hopes.countries VALUES ('VEN', 'Venezuela');
INSERT INTO hopes.countries VALUES ('VNM', 'Vietnam');
INSERT INTO hopes.countries VALUES ('VGB', 'Islas Vírgenes Británicas');
INSERT INTO hopes.countries VALUES ('VIR', 'Islas Vírgenes de los Estados Unidos');
INSERT INTO hopes.countries VALUES ('WLF', 'Wallis y Futuna');
INSERT INTO hopes.countries VALUES ('YEM', 'Yemen');
INSERT INTO hopes.countries VALUES ('DJI', 'Yibuti');
INSERT INTO hopes.countries VALUES ('ZMB', 'Zambia');
INSERT INTO hopes.countries VALUES ('ZWE', 'Zimbabue');
----------------------------------------------------------------- COUNTRIES -------------------------------------------------------------------------------
