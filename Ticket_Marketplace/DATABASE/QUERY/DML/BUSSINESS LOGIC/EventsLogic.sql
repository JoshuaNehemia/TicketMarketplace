
SELECT * FROM `ticketmarketplace`.`events`;

-- User looking for events
SELECT * FROM `ticketmarketplace`.`events` WHERE seller = (SELECT username FROM sellers WHERE companyName like "%MilesDavis%");

SELECT 
	eve.*,
    ven.`name` AS 'venue_name',
    cit.`name` AS 'city_name',
    ven.`address` AS 'venue_address',
    sel.`companyName` AS 'seller_companyName',
    sel.`email` AS 'seller_email',
    sel.`phoneNumber` AS 'seller_phoneNumber'
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
INNER JOIN
	`cities` AS cit
ON 
	ven.`city_id` = cit.`id`
WHERE
	sel.`companyName` LIKE '%Miles Davis%';




-- Seller looking for his own events
SELECT * FROM `ticketmarketplace`.`events`
WHERE seller = "milesdavis";

SELECT * FROM `ticketmarketplace`.`events` WHERE seller = "milesdavis";