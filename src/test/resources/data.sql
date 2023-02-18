INSERT INTO world.city (id, name, country_id, district, population)
VALUES (1,'Kabul',2,'Kabol',1780000),
       (2,'Qandahar',2,'Qandahar',237500),
       (3,'Herat',2,'Herat',186800),
       (4,'Mazar-e-Sharif',2,'Balkh',127800),
       (5,'Amsterdam',159,'Noord-Holland',731200);

INSERT INTO world.country
VALUES (1,'ABW','AW','Aruba',2,'Caribbean',193.00,NULL,103000,78.4,828.00,793.00,'Aruba','Nonmetropolitan Territory of The Netherlands','Beatrix',3),
       (2,'AFG','AF','Afghanistan',0,'Southern and Central Asia',652090.00,1919,22720000,45.9,5976.00,NULL,'Afganistan/Afqanestan','Islamic Emirate','Mohammad Omar',1);

INSERT INTO world.country_language
VALUES (1,1,'Dutch',1,5.3),
       (2,1,'English',0,9.5),
       (3,1,'Papiamento',0,76.7),
       (4,1,'Spanish',0,7.4),
       (5,2,'Balochi',0,0.9),
       (6,2,'Dari',1,32.1),
       (7,2,'Pashto',1,52.4),
       (8,2,'Turkmenian',0,1.9),
       (9,2,'Uzbek',0,8.8),
       (10,3,'Ambo',0,2.4);
