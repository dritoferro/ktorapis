CREATE TABLE products
(
    id          UUID             NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    name        VARCHAR          NOT NULL,
    value       DOUBLE PRECISION NOT NULL,
    description VARCHAR,
    arrived_at  DATE                                  DEFAULT NOW()
)