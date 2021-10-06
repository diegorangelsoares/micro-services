CREATE TABLE book (
    id SERIAL PRIMARY KEY,
  author varchar (100),
  launch_date date NOT NULL,
  price decimal ,
  title varchar (100)
);
