create table cliente (
    id bigint not null auto_increment,
    nome varchar(55) not null,
    email varchar(100) not null,
    telefone varchar(20) not null,
    data_nascimento DATE not null,
    primary key (id)
);