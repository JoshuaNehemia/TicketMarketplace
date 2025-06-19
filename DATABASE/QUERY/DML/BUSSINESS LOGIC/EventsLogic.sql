-- User looking for events
SELECT * FROM `ticketmarketplace`.`events`
WHERE seller = (SELECT username FROM sellers WHERE companyName like "%MilesDavis%");

SELECT * FROM `ticketmarketplace`.`events` WHERE seller = (SELECT username FROM sellers WHERE companyName like "%MilesDavis%");

-- Seller looking for his own events
SELECT * FROM `ticketmarketplace`.`events`
WHERE seller = "milesdavis";