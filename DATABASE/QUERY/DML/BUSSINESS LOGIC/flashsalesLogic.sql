SELECT * FROM flashsales WHERE eventClass_id = 0;

SELECT 
	ecl.* 
FROM 
	`eventClasses` AS ecl
INNER JOIN
	`events` AS eve
ON
	ecl.`event_id` = eve.`id`
INNER JOIN
	`flashsales` AS fls
ON
	ecl.`id` = fls.`id`;
