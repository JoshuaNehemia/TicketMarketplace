-- Event Classes for: Surabaya International Jazz Festival
INSERT INTO `ticketmarketplace`.`eventClasses` (`event_id`, `name`, `price`, `description`, `stock`, `availableStock`) VALUES
(
    (SELECT id FROM `events` WHERE name = 'Surabaya International Jazz Festival'),
    'VIP Pass',
    1800000.00,
    'Includes front-row seating, access to the VIP lounge, and one complimentary beverage.',
    150,
    150
),
(
    (SELECT id FROM `events` WHERE name = 'Surabaya International Jazz Festival'),
    'Regular Pass',
    850000.00,
    'Standard admission to the festival grounds.',
    1000,
    1000
),
(
    (SELECT id FROM `events` WHERE name = 'Surabaya International Jazz Festival'),
    'Early Bird Pass',
    500000.00,
    'Discounted admission for fans of yhe great Miles Davis',
    300,
    300
);

-- Event Classes for: Eminem: Live in Surabaya 2025
INSERT INTO `ticketmarketplace`.`eventClasses` (`event_id`, `name`, `price`, `description`, `stock`, `availableStock`) VALUES
(
    (SELECT id FROM `events` WHERE name = 'Eminem: Live in Surabaya 2025'),
    'Golden Circle (Standing)',
    2500000.00,
    'Front of stage standing area. Get closest to the action!',
    2000,
    2000
),
(
    (SELECT id FROM `events` WHERE name = 'Eminem: Live in Surabaya 2025'),
    'Festival (Standing)',
    1200000.00,
    'General admission standing area behind the Golden Circle.',
    10000,
    10000
),
(
    (SELECT id FROM `events` WHERE name = 'Eminem: Live in Surabaya 2025'),
    'Tribune A (Seated)',
    1750000.00,
    'Numbered seating with a direct view of the stage.',
    5000,
    5000
);

-- Event Classes for: Head In The Clouds Surabaya
INSERT INTO `ticketmarketplace`.`eventClasses` (`event_id`, `name`, `price`, `description`, `stock`, `availableStock`) VALUES
(
    (SELECT id FROM `events` WHERE name = 'Head In The Clouds Surabaya'),
    'VIP GA',
    2800000.00,
    'VIP General Admission. Includes dedicated entry lane, access to VIP-only areas, and an exclusive merch package.',
    1500,
    1500
),
(
    (SELECT id FROM `events` WHERE name = 'Head In The Clouds Surabaya'),
    'GA - Phase 1',
    1500000.00,
    'General Admission, early bird pricing. Limited availability.',
    3000,
    3000
),
(
    (SELECT id FROM `events` WHERE name = 'Head In The Clouds Surabaya'),
    'GA - Phase 2',
    1900000.00,
    'General Admission, standard pricing.',
    8000,
    8000
);

-- Event Classes for: JYP NATION: ONE MIC in Surabaya
INSERT INTO `ticketmarketplace`.`eventClasses` (`event_id`, `name`, `price`, `description`, `stock`, `availableStock`) VALUES
(
    (SELECT id FROM `events` WHERE name = 'JYP NATION: ONE MIC in Surabaya'),
    'MCP Package (Soundcheck)',
    3500000.00,
    'Numbered seating in the best section (Pink), plus access to the soundcheck rehearsal and a laminate with lanyard.',
    500,
    500
),
(
    (SELECT id FROM `events` WHERE name = 'JYP NATION: ONE MIC in Surabaya'),
    'Pink (Seated)',
    2900000.00,
    'Numbered seating in the section closest to the stage.',
    1500,
    1500
),
(
    (SELECT id FROM `events` WHERE name = 'JYP NATION: ONE MIC in Surabaya'),
    'Blue (Seated)',
    2200000.00,
    'Numbered seating in the sections behind Pink.',
    2500,
    2500
),
(
    (SELECT id FROM `events` WHERE name = 'JYP NATION: ONE MIC in Surabaya'),
    'Green (Seated)',
    1600000.00,
    'Numbered seating in the sections furthest from the stage.',
    3000,
    3000
);

-- Event Classes for: SMTOWN LIVE 2025: SMCU PALACE @ SURABAYA
INSERT INTO `ticketmarketplace`.`eventClasses` (`event_id`, `name`, `price`, `description`, `stock`, `availableStock`) VALUES
(
    (SELECT id FROM `events` WHERE name = 'SMTOWN LIVE 2025: SMCU PALACE @ SURABAYA'),
    'CAT 1 (Seated)',
    3000000.00,
    'Best seating category with a central view.',
    2000,
    2000
),
(
    (SELECT id FROM `events` WHERE name = 'SMTOWN LIVE 2025: SMCU PALACE @ SURABAYA'),
    'CAT 2 (Seated)',
    2400000.00,
    'Good seating category to the left and right of CAT 1.',
    3000,
    3000
),
(
    (SELECT id FROM `events` WHERE name = 'SMTOWN LIVE 2025: SMCU PALACE @ SURABAYA'),
    'CAT 3 (Standing)',
    1900000.00,
    'Free-standing area on the festival floor.',
    4000,
    4000
),
(
    (SELECT id FROM `events` WHERE name = 'SMTOWN LIVE 2025: SMCU PALACE @ SURABAYA'),
    'CAT 4 (Restricted View)',
    1300000.00,
    'Seating with a partially obstructed view of the stage.',
    1000,
    1000
);
