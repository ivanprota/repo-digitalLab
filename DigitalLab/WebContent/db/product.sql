USE digital_lab;

/* CASE */
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (9, 139.90, 'Kediers', 'C590', 'Case',
'9 ventole ARGB da 120 mm preinstallate\n
2 vetri temperati\n
Pannello I/O\n
Gestione dei cavi\n
Espansione notevole');
INSERT INTO picture VALUES (1, 'case2_f1.jpg', 'case2_f1.jpg');
INSERT INTO picture VALUES (1, 'case2_f2.jpg', 'case2_f2.jpg');

/* SCHEDE MADRI */
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (61, 259.99, 'Asus', 'Z690-A', 'Schede Madri', 'Personalizza la tua build come un vero professionista con ASUS Aura Sync, sincronizza i dispositivi compatibili e mostra a tutti lo stile da vero gamer grazie al connettore A-RBG Gen 2');
INSERT INTO picture VALUES (2, 'motherboard1_f1.jpg', 'motherboard1_f1.jpg');
INSERT INTO picture VALUES (2, 'motherboard1_f2.jpg', 'motherboard1_f2.jpg');
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (10, 249.99, 'Gigabyte', 'Z-790', 'Schede Madri', 
"Processore: Intel Socket LGA 1700\n
DDR5 a doppio canale: 4 DIMM con supporto per moduli di memoria XMP 3.0\n
Design termico efficace e protezione termica M.2: per assicurare la stabilità dell'alimentazione VRM e le prestazioni SSD M.2");
INSERT INTO picture VALUES (3, 'motherboard2_f1.jpg', 'motherboard2_f1.jpg');
INSERT INTO picture VALUES (3, 'motherboard2_f2.jpg', 'motherboard2_f2.jpg');

/* CPU */
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (60, 150.00, 'Intel', 'i5-12400F', 'CPU',
'Intel Core i5-12400 - Processore desktop di 12a generazione (velocità di base: 2,5 GHz, 6 core, LGA1700, RAM DDR4 e DDR5 fino a 128 GB) BX8071512400');
INSERT INTO picture VALUES (4, 'cpu1_f1.jpg', 'cpu1_f1.jpg');
INSERT INTO picture VALUES (4, 'cpu1_f2.jpg', 'cpu1_f2.jpg');
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (43, 170.00, 'Amd', 'Ryzen 7 5700G', 'CPU',
'Tipo di ram di sistema: ddr4_sdram
Piattaforma: Processore Boxed
Specificazioni di Memoria di Sistema: sino a 3200 MHz
Tipo di Sistema di Memoria: DDR4');
INSERT INTO picture VALUES (5, 'cpu2_f1.jpg', 'cpu2_f1.jpg');
INSERT INTO picture VALUES (5, 'cpu2_f2.jpg', 'cpu2_f2.jpg');

/* GPU */
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (16, 450.00, 'MSI', 'GeForce RTX 4060 Ti', 'GPU',
"GPU NVIDIA GEFORCE RTX 4060 Ti- MSI 4060 Ti è dotata dell'architettura premium Ada Lovelace per incredibili capacità di ray-tracing RTX di 3\n 
generazione (4K/UHD). Offre ben 8GB di memoria GDDR6 (18 Gbps).");
INSERT INTO picture VALUES (6, 'gpu1_f1.jpg', 'gpu1_f1.jpg');
INSERT INTO picture VALUES (6, 'gpu1_f2.jpg', 'gpu1_f2.jpg');
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (16, 376.86, 'Gigabyte', 'GeForce RTX 3060', 'GPU',
"Multiprocessori NVIDIA Ampere Streaming Elementi costitutivi per le GPU più veloci ed efficienti al mondo,\n 
il nuovissimo Ampere SM offre il doppio del throughput FP32 e una maggiore efficienza energetica");
INSERT INTO picture VALUES (7, 'gpu2_f1.jpg', 'gpu2_f1.jpg');
INSERT INTO picture VALUES (7, 'gpu2_f2.jpg', 'gpu2_f2.jpg');

/* RAM */
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (60, 95.20, 'Corsair', 'DDR4 32GB', 'RAM',
'I moduli di memoria DDR4 con overclocking CORSAIR serie VENGEANCE RGB PRO illumineranno il tuo PC grazie alla illuminazione RGB multizona');
INSERT INTO picture VALUES (8, 'ram1_f1.jpg', 'ram1_f1.jpg');
INSERT INTO picture VALUES (8, 'ram1_f2.jpg', 'ram1_f2.jpg');
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (60, 47.20, 'Corsair', 'DDR4 16GB', 'RAM',
'Supporta la tecnologia XMP 2.0 per offrire un semplice overclock automatico.\n
Progettate per elevate prestazioni ed overclock con pcb a otto strati');
INSERT INTO picture VALUES (9, 'ram2_f1.jpg', 'ram2_f1.jpg');
INSERT INTO picture VALUES (9, 'ram2_f2.jpg', 'ram2_f2.jpg');

/* STORAGE */
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (23, 56.13, 'Seagate', 'BarraCuda 2TB', 'Storage',
'Una velocità di 7200 giri/min e la tecnologia di caching di lettura e scrittura ottimizzata consentono di tuffarsi nei giochi su PC o montare musica, video e foto senza problema');
INSERT INTO picture VALUES (10, 'storage1_f1.jpg', 'storage1_f1.jpg');
INSERT INTO picture VALUES (10, 'storage1_f2.jpg', 'storage1_f2.jpg');
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (41, 17.80, 'Kingston', 'A400 240GB', 'Storage',
'Questo drive SSD risulta essere 10 volte più veloce di un hard drive tradizionale e assicura elevate prestazioni, elevata reattività dei processi multi-tasking');
INSERT INTO picture VALUES (11, 'storage2_f1.jpg', 'storage2_f1.jpg');
INSERT INTO picture VALUES (11, 'storage2_f2.jpg', 'storage2_f2.jpg');

/* COOLING */
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (17, 122.13, 'Thermaltake', 'EX12 RGB', 'Cooling',
'SWAFAN EX è dotato di un nuovo design a forza magnetica che consente un collegamento rapido tra le ventole tramite il cavo da 12 V');
INSERT INTO picture VALUES (12, 'cooling1_f1.jpg', 'cooling1_f1.jpg');
INSERT INTO picture VALUES (12, 'cooling1_f2.jpg', 'cooling1_f2.jpg');
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (39, 15.90, 'AeroCool', 'ASTRO12', 'Cooling',
'Cuscinetti antivibranti per assorbire e ridurre i movimenti causati dalla rotazione della ventola\n
Prodotto di alta qualità');
INSERT INTO picture VALUES (13, 'cooling2_f1.jpg', 'cooling2_f1.jpg');
INSERT INTO picture VALUES (13, 'cooling2_f2.jpg', 'cooling2_f2.jpg');

/* PSU */
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (7, 78.38, 'Thermaltake', 'TR2 S 700W', 'PSU', "La nuova serie TR2 risparmia molta energia grazie all'elevata efficienza, che raggiunge l'86%, ed è in grado di alimentare qualsiasi PC mainstream.");
INSERT INTO picture VALUES (14, 'psu1_f1.jpg', 'psu1_f1.jpg');
INSERT INTO picture VALUES (14, 'psu1_f2.jpg', 'psu1_f2.jpg');
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description)
VALUES (11, 82.76, 'Seasonic', 'G12 GC 750W', 'PSU', "Seasonic Alimentatore di rete per PC, G12 GC-750 80 PLUS Gold, ATX 12 V, 750 Watt, alto grado di efficienza, raffreddamento ottimale, risparmio energetico e silenzioso "); 
INSERT INTO picture VALUES (15, 'psu2_f1.jpg', 'psu2_f1.jpg');
INSERT INTO picture VALUES (15, 'psu2_f2.jpg', 'psu2_f2.jpg');

/* MONITOR */
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (31, 78.38, 'Samsung', 'S36C', 'Monitor', "Immergiti a pieno nel lavoro o nel gioco. Lo schermo curvo da 1800R ti avvolgerà, facendoti immergere nei contenuti e mantenendo alta la tua concentrazione.");
INSERT INTO picture VALUES (16, 'monitor1_f1.jpg', 'monitor1_f1.jpg');
INSERT INTO picture VALUES (16, 'monitor1_f2.jpg', 'monitor1_f2.jpg');
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description)
VALUES (48, 78.38, 'Philips', '241V8L', 'Monitor', "Monitor con pannello VA per angoli di visione 178/178. Tecnologia Adaptive Sync per giocare in modo fluido a 75Hz");
INSERT INTO picture VALUES (17, 'monitor2_f1.jpg', 'monitor2_f1.jpg');
INSERT INTO picture VALUES (17, 'monitor2_f2.jpg', 'monitor2_f2.jpg');

/* SISTEMI OPERATIVI */
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (7, 78.38, 'Microsoft', 'Windows 11 Home Edition', 'Sistemi Operativi', "Goditi una nuova prospettiva; Meno caos, più calma. Il design rinnovato di Windows 11 ti permette di fare ciò che vuoi senza sforzo.");
INSERT INTO picture VALUES (18, 'os1_f1.jpg', 'os1_f1.jpg');
INSERT INTO picture VALUES (18, 'os1_f2.jpg', 'os1_f2.jpg');
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (7, 78.38, 'Microsoft', 'Windows 10 Pro 64 Bit', 'Sistemi Operativi', "Windows 10 Pro Versione italiana OEM. Puoi anche scegliere altre lingue: inglese, tedesco, francese, spagnolo ecc.");
INSERT INTO picture VALUES (19, 'os2_f1.jpg', 'os2_f1.jpg');
INSERT INTO picture VALUES (19, 'os2_f2.jpg', 'os2_f2.jpg'); 

/* ACCESSORI */
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (39, 29.99, 'TMKB', 'T68SE', 'Accessori',
"Il cavo staccabile e il design compatto senza tastierino numerico lo rendono facile da trasportare e forniscono spazio extra per il movimento del mouse sul tavolo,\n 
offrendo un input efficiente durante il lavoro o il gioco.");
INSERT INTO picture VALUES (20, 'accessori1_f1.jpg', 'accessori1_f1.jpg');
INSERT INTO picture VALUES (20, 'accessori1_f2.jpg', 'accessori1_f2.jpg');
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (86, 4.99, 'Ewent', 'EW3300', 'Accessori',
'Mouse USB cablato a 3 pulsanti con tracciamento ottico in nero, compatibile con la maggior parte dei computer desktop, MacBook e laptop con una garanzia hardware di 5 anni');
INSERT INTO picture VALUES (21, 'accessori2_f1.jpg', 'accessori2_f1.jpg');
INSERT INTO picture VALUES (21, 'accessori2_f2.jpg', 'accessori2_f2.jpg');

INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (15, 49.99, 'Mars', 'MC-U3', 'Case', 
'3 x 120mm ventole argb\n
Pannello laterale in vetro temperato cristallino edge-to-edge\n
Supporto ventole e radiatori\n
Tante possibilità\n
Spazio componenti principali');
INSERT INTO picture VALUES (22, 'case1_f1.jpg', 'case1_f1.jpg');
INSERT INTO picture VALUES (22, 'case1_f2.jpg', 'case1_f2.jpg');

/* PRE-ASSEMBLATI */
INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (3, 989.90, 'Greed', 'Mk2', 'PC Gaming',
'Componenti che convincono La scheda grafica NVIDIA GeForce RTX3060 da 8 GB, la CPU Intel Core i7 – 10700 e la memoria RAM DDR4 da 32 GB offrono grafica/grafica impressionante e prestazioni veloci per gamer/streamer e zocker.'); 
INSERT INTO picture VALUES (23, 'pc_gaming1_f1.jpg', 'pc_gaming1_f1.jpg');
INSERT INTO picture VALUES (23, 'pc_gaming1_f2.jpg', 'pc_gaming1_f2.jpg');

INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (6, 1869, 'Megaport', 'Monument', 'PC Gaming', 
'Intel Core i9 11900KF con 8 core, scheda video GeForce RTX 4070 e con 32GB di memoria super veloce (3200 MHz) combinata con la CPU e un enorme SSD M.2 da 1TB, sperimenterai una velocità senza precedenti nell\'esecuzione di tutte le tue applicazioni. ');
INSERT INTO picture VALUES (24, 'pc_gaming2_f1.jpg', 'pc_gaming2_f1.jpg');
INSERT INTO picture VALUES (24, 'pc_gaming2_f2.jpg', 'pc_gaming2_f2.jpg');

INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (10, 339.99, 'MINIS', 'UM560', 'PC Streaming', 
'Alimentato dal processore AMD Ryzen 5 5600H con scheda grafica AMD Radeon (frequenza grafica 1800 MHz).');
INSERT INTO picture VALUES (25, 'pc_streaming1_f1.jpg', 'pc_streaming1_f1.jpg');
INSERT INTO picture VALUES (25, 'pc_streaming1_f2.jpg', 'pc_streaming1_f2.jpg');

INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description)
VALUES (10, 1549.00, 'Romagna', 'Computer', 'PC Workstation',
'Intel Core i9-11900 - Ram 64 GB DDR4
M2 NVME 1 TB - Scheda Video Nvidia Quadro T400 4 GB GDDR6
Masterizzatore DVD - WiFi Interno 2,4 GHz
Windows 10 Pro Originale ');
INSERT INTO picture VALUES (26, 'pc_workstation1_f1.jpg', 'pc_workstation1_f1.jpg');
INSERT INTO picture VALUES (26, 'pc_workstation1_f2.jpg', 'pc_workstation1_f2.jpg');

INSERT INTO product (product_quantity, product_price, product_brand, product_model, product_category, product_description) 
VALUES (12, 1299.99, 'Dell', 'Precision Tower 7810', 'PC Workstation', "Dimensioni della confezione dell'articolo - 19,68503935L x 15,74803148 W x 7,87401574H pollici");
INSERT INTO picture VALUES (27, 'pc_workstation2_f1.jpg', 'pc_workstation2_f1.jpg');
INSERT INTO picture VALUES (27, 'pc_workstation2_f2.jpg', 'pc_workstation2_f2.jpg');