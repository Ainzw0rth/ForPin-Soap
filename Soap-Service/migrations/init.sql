USE forpin-soap;

CREATE TABLE IF NOT EXISTS log (
    id INT NOT NULL,
    description varchar(255) NOT NULL
);

-- CREATE TABLE IF NOT EXISTS subscription (
--     creator_id INT NOT NULL,
--     subscriber_id INT NOT NULL,
-- status ENUM NOT NULL DEFAULT 'PENDING'
-- );

INSERT INTO log (id, description) VALUES (1, 'tes');