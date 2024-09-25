CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

-- Inserindo os valores padrão da enumeração
INSERT INTO roles (name) VALUES ('ADMIN'), ('USER');

