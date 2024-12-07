create database crud-api-web2;

create table tb_aluno
(
    id_aluno    integer generated always as identity
        primary key,
    nome_aluno  varchar(100) not null,
    email_aluno varchar(150) not null,
    cpf_aluno   varchar(14)  not null,
    data_aluno  date         not null
);

create table tb_curso
(
    id_curso   integer generated always as identity
        primary key,
    nome_curso varchar(150) not null,
    link_curso varchar(150) not null
);

create table tb_matricula
(
    id_matricula       integer generated always as identity
        primary key,
    id_aluno_matricula integer not null
        constraint fk_aluno
            references tb_aluno,
    id_curso_matricula integer not null
        constraint fk_curso
            references tb_curso,
    data_matricula     date    not null,
    nota_matricula     integer not null
);


