-- Create customer db

CREATE DATABASE customer;
CREATE USER customer_user WITH PASSWORD 'customer_password';

\connect customer;

CREATE TABLE customer (
   id SERIAL PRIMARY KEY,
   name VARCHAR(255),
   favorite_genre VARCHAR(100)
);

INSERT INTO customer (name, favorite_genre)
VALUES
    ('Sam', 'ACTION'),
    ('Sara', 'COMEDY');

 GRANT ALL PRIVILEGES ON ALL TABLES in SCHEMA public TO customer_user;

-- Create movie db

 CREATE DATABASE movie;
 CREATE USER movie_user WITH PASSWORD 'movie_password';

\connect movie;

 CREATE TABLE movie (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    release_year INTEGER,
    genre VARCHAR(100)
 );

INSERT INTO movie (title, release_year, genre)
VALUES
    ('Inception', 2010, 'ACTION'),
    ('The Godfather', 1972, 'CRIME'),
    ('Monty Python and the Holy Grail', 1975, 'COMEDY'),
    ('The Shawshank Redemption', 1994, 'DRAMA'),
    ('Mad Max: Fury Road', 2015, 'ACTION'),
    ('Die Hard', 1988, 'ACTION'),
    ('Pulp Fiction', 1994, 'CRIME'),
    ('Superbad', 2007, 'COMEDY'),
    ('Forrest Gump', 1994, 'DRAMA');

 GRANT ALL PRIVILEGES ON ALL TABLES in SCHEMA public TO movie_user;