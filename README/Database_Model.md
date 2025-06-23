# Ticket Marketplace Database Schema

## Sellers
| Column Name      | Data Type      |
|------------------|----------------|
| username         | VARCHAR(45)    |
| password         | VARCHAR(45)    |
| companyName      | VARCHAR(100)   |
| companyAddress   | VARCHAR(100)   |
| phoneNumber      | VARCHAR(14)    |
| email            | VARCHAR(100)   |

---

## Venues
| Column Name      | Data Type      |
|------------------|----------------|
| id               | INT            |
| name             | VARCHAR(100)   |
| address          | VARCHAR(100)   |
| maxCapacity      | INT            |
| area             | INT            |
| cities_id        | INT            |

---

## Cities
| Column Name      | Data Type      |
|------------------|----------------|
| id               | INT            |
| name             | VARCHAR(45)    |
| province_id      | INT            |

---

## Provinces
| Column Name      | Data Type      |
|------------------|----------------|
| id               | INT            |
| name             | VARCHAR(45)    |

---

## Users
| Column Name      | Data Type      |
|------------------|----------------|
| username         | VARCHAR(45)    |
| password         | VARCHAR(45)    |
| fullname         | VARCHAR(100)   |
| email            | VARCHAR(100)   |
| birthDate        | DATETIME       |

---

## Events
| Column Name      | Data Type      |
|------------------|----------------|
| id               | INT            |
| name             | VARCHAR(100)   |
| dateTime         | DATETIME       |
| maxBuy           | INT            |
| venues_id        | INT            |
| sellers_username | VARCHAR(45)    |
| events_categories_id | INT         |

---

## Events Categories
| Column Name      | Data Type      |
|------------------|----------------|
| id               | INT            |
| name             | VARCHAR(45)    |
| description      | TEXT           |

---

## Seats
| Column Name      | Data Type      |
|------------------|----------------|
| id               | INT            |
| rows             | VARCHAR(10)    |
| column           | INT            |
| events_class_id  | INT            |

---

## Events Class
| Column Name      | Data Type      |
|------------------|----------------|
| id               | INT            |
| name             | VARCHAR(45)    |
| description      | TEXT           |
| price            | DOUBLE         |

---

## Tickets
| Column Name      | Data Type      |
|------------------|----------------|
| id               | VARCHAR(100)   |
| seats_id         | INT            |
| users_username   | VARCHAR(45)    |
| paidDate         | DATETIME       |

