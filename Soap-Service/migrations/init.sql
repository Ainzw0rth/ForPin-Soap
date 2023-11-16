USE forpin-soap;

CREATE TABLE IF NOT EXISTS log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description varchar(255) NOT NULL,
    IP char(16) NOT NULL,
    endpoint varchar(256) NOT NULL,
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS subscription (
    creator_id INT NOT NULL,
    subscriber_id INT NOT NULL,
    status SET('ACCEPTED', 'REJECTED', 'PENDING') DEFAULT 'PENDING'
);

CREATE TABLE IF NOT EXISTS premium (
    creator_id INT NOT NULL,
    status SET('ACCEPTED', 'REJECTED', 'PENDING') DEFAULT 'PENDING'
);
INSERT INTO premium (creator_id, status) VALUES (1, 'PENDING');
INSERT INTO premium (creator_id, status) VALUES (2, 'PENDING');
INSERT INTO premium (creator_id, status) VALUES (3, 'PENDING');
INSERT INTO premium (creator_id, status) VALUES (4, 'PENDING');
INSERT INTO subscription (creator_id, subscriber_id, status) VALUES (1, 2, 'PENDING');
