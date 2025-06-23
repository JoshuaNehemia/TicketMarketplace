-- SQL query to insert major cities of Indonesia and link them to their respective provinces.
-- This script needs the 'provinces' table has been populated.
-- For each city, a subquery is used to find the corresponding 'province_id'.

INSERT INTO `ticket`.`cities` (`name`, `province_id`) VALUES
-- Aceh
('Banda Aceh', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Aceh')),
('Langsa', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Aceh')),
('Lhokseumawe', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Aceh')),
('Sabang', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Aceh')),
('Subulussalam', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Aceh')),

-- Bali
('Denpasar', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Bali')),

-- Bangka Belitung Islands
('Pangkal Pinang', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Bangka Belitung Islands')),

-- Banten
('Cilegon', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Banten')),
('Serang', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Banten')),
('Tangerang', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Banten')),
('South Tangerang', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Banten')),

-- Bengkulu
('Bengkulu', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Bengkulu')),

-- Central Java
('Magelang', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Central Java')),
('Pekalongan', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Central Java')),
('Salatiga', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Central Java')),
('Semarang', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Central Java')),
('Surakarta', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Central Java')),
('Tegal', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Central Java')),

-- Central Kalimantan
('Palangka Raya', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Central Kalimantan')),

-- Central Sulawesi
('Palu', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Central Sulawesi')),

-- East Java
('Batu', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'East Java')),
('Blitar', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'East Java')),
('Kediri', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'East Java')),
('Madiun', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'East Java')),
('Malang', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'East Java')),
('Mojokerto', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'East Java')),
('Pasuruan', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'East Java')),
('Probolinggo', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'East Java')),
('Surabaya', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'East Java')),

-- East Kalimantan
('Balikpapan', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'East Kalimantan')),
('Bontang', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'East Kalimantan')),
('Samarinda', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'East Kalimantan')),

-- East Nusa Tenggara
('Kupang', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'East Nusa Tenggara')),

-- Gorontalo
('Gorontalo', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Gorontalo')),

-- Jakarta
('West Jakarta', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Jakarta')),
('Central Jakarta', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Jakarta')),
('South Jakarta', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Jakarta')),
('East Jakarta', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Jakarta')),
('North Jakarta', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Jakarta')),

-- Jambi
('Jambi', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Jambi')),
('Sungai Penuh', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Jambi')),

-- Lampung
('Bandar Lampung', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Lampung')),
('Metro', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Lampung')),

-- Maluku
('Ambon', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Maluku')),
('Tual', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Maluku')),

-- North Kalimantan
('Tarakan', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'North Kalimantan')),

-- North Maluku
('Ternate', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'North Maluku')),
('Tidore', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'North Maluku')),

-- North Sulawesi
('Bitung', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'North Sulawesi')),
('Kotamobagu', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'North Sulawesi')),
('Manado', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'North Sulawesi')),
('Tomohon', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'North Sulawesi')),

-- North Sumatra
('Binjai', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'North Sumatra')),
('Gunungsitoli', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'North Sumatra')),
('Medan', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'North Sumatra')),
('Padangsidimpuan', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'North Sumatra')),
('Pematangsiantar', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'North Sumatra')),
('Sibolga', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'North Sumatra')),
('Tanjungbalai', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'North Sumatra')),
('Tebing Tinggi', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'North Sumatra')),

-- Papua
('Jayapura', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Papua')),

-- Riau
('Dumai', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Riau')),
('Pekanbaru', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Riau')),

-- Riau Islands
('Batam', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Riau Islands')),
('Tanjungpinang', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Riau Islands')),

-- South Kalimantan
('Banjarbaru', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'South Kalimantan')),
('Banjarmasin', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'South Kalimantan')),

-- South Sulawesi
('Makassar', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'South Sulawesi')),
('Palopo', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'South Sulawesi')),
('Parepare', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'South Sulawesi')),

-- South Sumatra
('Lubuklinggau', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'South Sumatra')),
('Pagar Alam', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'South Sumatra')),
('Palembang', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'South Sumatra')),
('Prabumulih', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'South Sumatra')),

-- Southeast Sulawesi
('Baubau', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Southeast Sulawesi')),
('Kendari', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Southeast Sulawesi')),

-- Southwest Papua
('Sorong', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Southwest Papua')),

-- West Java
('Bandung', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Java')),
('Banjar', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Java')),
('Bekasi', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Java')),
('Bogor', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Java')),
('Cimahi', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Java')),
('Cirebon', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Java')),
('Depok', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Java')),
('Sukabumi', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Java')),
('Tasikmalaya', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Java')),

-- West Kalimantan
('Pontianak', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Kalimantan')),
('Singkawang', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Kalimantan')),

-- West Nusa Tenggara
('Bima', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Nusa Tenggara')),
('Mataram', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Nusa Tenggara')),

-- West Sumatra
('Bukittinggi', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Sumatra')),
('Padang', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Sumatra')),
('Padang Panjang', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Sumatra')),
('Pariaman', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Sumatra')),
('Payakumbuh', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Sumatra')),
('Sawahlunto', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Sumatra')),
('Solok', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'West Sumatra')),

-- Special Region of Yogyakarta
('Yogyakarta', (SELECT id FROM `ticket`.`provinces` WHERE `name` = 'Special Region of Yogyakarta'));
