
## connect to RDS

```
psql -U postgres -h [endpoint]
```

### init.sql

```sql
-- Create customer db

CREATE DATABASE customer;
CREATE USER customer_user WITH PASSWORD 'customer_password_123';

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
 CREATE USER movie_user WITH PASSWORD 'movie_password_123';

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
    ('Forrest Gump', 1994, 'DRAMA'),
    ('Gladiator', 2000, 'ACTION'),
    ('The Dark Knight', 2008, 'ACTION'),
    ('Goodfellas', 1990, 'CRIME'),
    ('Anchorman: The Legend of Ron Burgundy', 2004, 'COMEDY'),
    ('Fight Club', 1999, 'DRAMA'),
    ('The Matrix', 1999, 'ACTION'),
    ('Scarface', 1983, 'CRIME'),
    ('Step Brothers', 2008, 'COMEDY'),
    ('Schindler''s List', 1993, 'DRAMA'),
    ('The Avengers', 2012, 'ACTION'),
    ('The Departed', 2006, 'CRIME');

 GRANT ALL PRIVILEGES ON ALL TABLES in SCHEMA public TO movie_user;
```