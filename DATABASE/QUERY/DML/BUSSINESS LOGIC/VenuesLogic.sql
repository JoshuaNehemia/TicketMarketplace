SELECT * FROM `venues`;

SELECT * 
FROM `venues` 
WHERE city_id = (SELECT id FROM `cities` WHERE `name` = "Surabaya" LIMIT 1);

SELECT v.*, c.id AS 'city_id', c.`name` AS 'city_name' 
FROM `venues` AS v
JOIN `cities` AS c ON v.city_id = c.id
JOIN `provinces` AS p ON c.province_id = p.id
WHERE p.name like "%Jakarta%";

SELECT v.*,c.id as 'city_id',c.name as 'city_name' 
FROM `venues` AS v 
INNER JOIN `cities` AS c 
ON v.city_id = c.id 
WHERE v.`name` like "%Bung Tomo%";