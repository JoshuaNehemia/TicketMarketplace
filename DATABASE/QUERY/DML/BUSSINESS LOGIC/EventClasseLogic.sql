SELECT * FROM  `eventclasses` WHERE `event_id` = 1;

UPDATE eventclasses SET availableStock = availableStock - 1 WHERE id = 1;