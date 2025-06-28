use concenssionaria;
create table cliente(
	nome varchar (100) not null,
	cpf varchar (14) not null primary key,
    endereco longtext,
    rg varchar (12),
    primeiraCompra boolean not null,
    email varchar(100) not null,
    celular varchar (16) not null,
    telefone varchar(15)
);

drop table cliente;
select * from cliente;