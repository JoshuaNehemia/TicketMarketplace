SELECT
    nf.id AS nf_id,
    nf.message,
    ti.*,
    ec.id AS eventclass_id,
    ec.event_id,
    ec.name AS eventclass_name,
    ev.name AS event_name,
    ev.startDateTime AS event_startDateTime,
    ev.venue_id,
    v.name AS venue_name,
    v.address AS venue_address,
    v.city_id,
    c.name AS city_name,
    pm.id AS paymentMethod_id,
    pm.name AS paymentmethod_name
FROM
    notifications AS nf
INNER JOIN
    tickets AS ti ON ti.id = nf.ticket_id
INNER JOIN
    eventclasses AS ec ON ti.eventClass_id = ec.id
INNER JOIN
    events AS ev ON ec.event_id = ev.id
INNER JOIN
    venues AS v ON ev.venue_id = v.id
INNER JOIN
    cities AS c ON v.city_id = c.id
INNER JOIN
    paymentmethods AS pm ON ti.paymentMethod_id = pm.id
WHERE
    nf.user_username;
    
INSERT INTO notifications (message, user_username, ticket_id) VALUES (?, ?, ?);