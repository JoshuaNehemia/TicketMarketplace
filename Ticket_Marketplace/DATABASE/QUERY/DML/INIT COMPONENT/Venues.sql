-- SQL query to insert major event venues in Indonesia into the 'venues' table.
-- This script needs that the 'cities' table has already been populated.
-- For each venue, a subquery is used to find the corresponding 'cities_id'.

INSERT INTO `ticket`.`venues` (`name`, `address`, `maxCapacity`, `area`, `cities_id`) VALUES
-- Jakarta
('Gelora Bung Karno Main Stadium', 'Jl. Pintu Satu Senayan, Gelora, Tanah Abang', 77193, 76000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Central Jakarta')),
('Jakarta Convention Center', 'Jl. Gatot Subroto No.1, Gelora, Tanah Abang', 15000, 120000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Central Jakarta')),
('JIExpo Kemayoran', 'Jl. H. Benyamin Sueb, Pademangan Timur', 20000, 440000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'North Jakarta')),
('Taman Mini Indonesia Indah', 'Jl. Raya Taman Mini, Ceger, Cipayung', 10000, 1500000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'East Jakarta')),

-- Surabaya
('Grand City Convention and Exhibition', 'Jl. Walikota Mustajab No.1, Ketabang, Genteng', 5000, 21000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Surabaya')),
('Jatim International Expo (JIE)', 'Jl. Ahmad Yani No.99, Jemur Wonosari, Wonocolo', 10000, 50000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Surabaya')),
('Gelora Bung Tomo Stadium', 'Jl. Jawar, Benowo, Pakal', 45134, 150000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Surabaya')),

-- Bandung
('Trans Convention Centre', 'Jl. Gatot Subroto No.289, Cibangkong, Batununggal', 4000, 6300, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Bandung')),
('Gedung Sate', 'Jl. Diponegoro No.22, Citarum, Bandung Wetan', 1000, 27000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Bandung')),
('Sabuga (Sasana Budaya Ganesha)', 'Jl. Tamansari No.73, Lb. Siliwangi, Coblong', 2500, 12000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Bandung')),

-- Bali
('Bali Nusa Dua Convention Center', 'Kawasan Pariwisata Nusa Dua, Benoa', 10000, 50000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Denpasar')),
('Garuda Wisnu Kencana (GWK) Cultural Park', 'Jl. Raya Uluwatu, Ungasan, Kuta Selatan', 25000, 600000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Denpasar')),

-- Yogyakarta
('Jogja Expo Center (JEC)', 'Jl. Janti, Wonocatur, Banguntapan', 8000, 14000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Yogyakarta')),

-- Medan
('Santika Premiere Dyandra Hotel & Convention', 'Jl. Kapten Maulana Lubis No.7', 3000, 4000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Medan')),

-- Kalimantan
('Balikpapan International Stadium Batakan', 'Jl. Mulawarman, Manggar, Balikpapan Timur', 40000, 100000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Balikpapan')),
('Pontianak Convention Center', 'Jl. Sultan Syarif Abdurrahman No.7', 3000, 5000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Pontianak')),
('Gedung Sultan Suriansyah', 'Jl. Brigjend H. Hasan Basri, Kayu Tangi', 2000, 4000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Banjarmasin')),
('Palaran Stadium', 'Jl. AM. Alimuddin, Simpang Tiga, Palaran', 35000, 80000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Samarinda')),

-- Sulawesi
('Celebes Convention Center', 'Jl. Metro Tanjung Bunga, Maccini Sombala', 5000, 10000, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Makassar')),
('Manado Convention Center', 'Jl. Piere Tendean, Sario Tumpaan', 2000, 4500, (SELECT id FROM `ticket`.`cities` WHERE `name` = 'Manado'));
