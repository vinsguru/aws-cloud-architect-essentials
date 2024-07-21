CREATE TABLE customer (
   id SERIAL PRIMARY KEY,
   name VARCHAR(255),
   favorite_genre VARCHAR(100)
);

INSERT INTO customer (name, favorite_genre)
    values
    ('sam', 'ACTION'),
    ('sara', 'CRIME');