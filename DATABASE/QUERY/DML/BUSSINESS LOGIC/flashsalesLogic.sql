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

-- Check if EventClass is flashsaled
SELECT fs.price AS 'flashsale_price'
FROM
	`flashsales` AS fs
INNER JOIN 
	`eventclasses` AS ec
ON 
ec.`id` = fs.`eventclass_id`
WHERE 
	STR_TO_DATE(fs.`startTime`,'%Y-%m-%d %H:%i:%s') < NOW()
AND
	STR_TO_DATE(fs.`endTime`,'%Y-%m-%d %H:%i:%s') > NOW()
AND
	fs.`stock` > 0
;