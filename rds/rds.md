
## RDS

- Connect to RDS
```
psql -U postgres -h [endpoint]
```

- To list the databases
```
SELECT datname FROM pg_database;
```
- To create a database
```
create database mydb;
```
- To connect to a database
```
\c mydb;
```
- To create table
```
CREATE TABLE customer (
 id SERIAL PRIMARY KEY,
 name VARCHAR(50)
);
```
- To insert records
```
INSERT INTO customer (name)
VALUES
  ('Sam'),
  ('Mike');
```