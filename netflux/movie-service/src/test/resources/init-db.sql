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
    ('Mad Max: Fury Road', 2015, 'ACTION'),
    ('Die Hard', 1988, 'ACTION'),
    ('Pulp Fiction', 1994, 'CRIME'),
    ('Superbad', 2007, 'COMEDY');