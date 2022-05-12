create table cargo (
idcargo serial primary key ,
nomecargo varchar(20) not null);

insert into cargo  (nomecargo) values ('Enfermeiro')

select * from cargo


create table categoria(
idCategoria serial primary key ,
nomeCategoria varchar(40) not null);

insert into categoria(nomeCategoria) values ('Antialérgico');
select * from categoria


create table funcionario (
idfuncionario serial primary key ,
nomefuncionario varchar (40) not null,
cpf varchar (14) not null,
turno varchar (20) not null,
imagemFuncionario text,
idcargo int not null,
constraint fk_cargo foreign key (idcargo) references cargo(idcargo));

insert into funcionario (nomefuncionario,cpf,turno,idcargo) values ('Roberto','22233344455','Tarde',1)
select * from funcionario


create table materialHospitalar(
idMaterialHospitalar serial primary key ,
nomeMaterial varchar(40) not null,
Qtde int not null,
idCategoria int not null,
constraint fk_Categoria foreign key (idCategoria) references categoria(idCategoria));

insert into materialHospitalar(nomeMaterial, qtde, idCategoria) values ('Loratamed', 13,1);
select * from MaterialHospitalar

create table unidadeHospitalar(
idunidadeHospitalar serial primary key,
nomeUnidade varchar (40) not null,
cnpj varchar (14) not null,
);
insert into unidadeHospitalar(nomeUnidade, cnpj) values ('Nucleo Central',22222222222222);

insert into materialHospitalar(nomeMaterial, qtde, idCategoria) values ('Loratamed', 13,1);
select * from MaterialHospitalar