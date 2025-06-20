-- User looking for events
SELECT * FROM `ticketmarketplace`.`events`
WHERE seller = (SELECT username FROM sellers WHERE companyName like "%MilesDavis%");

SELECT * FROM `ticketmarketplace`.`events` WHERE seller = (SELECT username FROM sellers WHERE companyName like "%MilesDavis%");

SELECT 
	eve.*,
    ven.`id` AS 'venue_id',
    ven.`name` AS 'venue_name'
FROM
	`events` AS eve
INNER JOIN
	`venues` AS ven
ON 
	eve.`venue_id` = ven.`id`
INNER JOIN
	`sellers` AS sel
ON 
	eve.`seller` = sel.`username`
WHERE
	sel.`companyName` LIKE '%Miles Davis%';




-- Seller looking for his own events
SELECT * FROM `ticketmarketplace`.`events`
WHERE seller = "milesdavis";

SELECT * FROM `ticketmarketplace`.`events` WHERE seller = "milesdavis";