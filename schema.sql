CREATE TABLE IF NOT EXISTS characters (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    external_id BIGINT NOT NULL UNIQUE,
    name VARCHAR(255),
    status VARCHAR(50),
    gender VARCHAR(50)
);
