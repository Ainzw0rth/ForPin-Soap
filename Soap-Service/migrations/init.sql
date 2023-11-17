USE forpin-soap;

CREATE TABLE IF NOT EXISTS log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description varchar(255) NOT NULL,
    IP char(16) NOT NULL,
    endpoint varchar(256) NOT NULL,
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS subscription (
    creator_username VARCHAR(50) NOT NULL,
    subscriber_username VARCHAR(50) NOT NULL,
    status SET('ACCEPTED', 'REJECTED', 'PENDING') DEFAULT 'PENDING'
);

CREATE TABLE IF NOT EXISTS premium (
    creator_username VARCHAR(50) NOT NULL,
    status SET('ACCEPTED', 'REJECTED', 'PENDING') DEFAULT 'PENDING'
);
-- INSERT INTO premium (creator_username, status) VALUES ('userone', 'PENDING');
-- INSERT INTO premium (creator_username, status) VALUES ('usertwo', 'PENDING');
-- INSERT INTO premium (creator_username, status) VALUES ('userthree', 'PENDING');
-- INSERT INTO premium (creator_username, status) VALUES ('userfour', 'PENDING');
-- INSERT INTO subscription (creator_username, subscriber_username, status) VALUES ('userone', 'usertwo', 'PENDING');
