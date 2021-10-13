CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    logradouro  varchar (150),
    DataCriacao date,
    bairro varchar (50),
    cidade varchar (50),
    estado varchar (50),
    cep integer,
    numero integer,
    complemento varchar (150)
);
