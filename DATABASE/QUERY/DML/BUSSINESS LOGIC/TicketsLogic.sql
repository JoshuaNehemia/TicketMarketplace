SELECT 
ti.*,
ec.`name` AS 'eventclass_name',
ev.`id` AS 'event_id',
ev.`name` AS 'event_name',
pm.`name` AS 'paymentmethod_name'
FROM 
`tickets` AS ti
INNER JOIN 
`eventclasses` AS ec
ON 
ti.`eventClass_id` = ec.`id`
INNER JOIN
`events` AS ev
ON
ev.`id` = ec.`event_id`
INNER JOIN
`paymentmethods` AS pm
ON
ti.`paymentMethod_id` = pm.id
WHERE 
ti.`user` = "";
