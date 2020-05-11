# Projeto Optimum API

Projeto desenvolvido com Spring Boot, para cadastro de pessoas. 

A versão do projeto em produção pode ser vista em [Projeto Optimum API Heroku](https://optimum-api-back.herokuapp.com/pessoas) 

## Instalação

Para instalar o projeto em sua máquina clone esse repositório do github em uma pasta local em seguida execute o arquivo OptimumApplication.java.

## Arquivos de configuração

No arquivo `application.properties` estão algumas configurações do banco de dados para desenvolvimento, e no arquivo `application-test.properties` estão as configuração de banco de dados para o ambiente de teste.

## Banco de Dados

A API foi configurada para realizar requisições ao banco de dados Postgres. Antes de iniciar o servidor será necessário realizar as seguintes operações no seu banco de dados local.

```
CREATE TABLE pessoa (
    id serial PRIMARY KEY,
    nome character varying(50),
    cpf character varying(20),
    nascimento character varying(20),
    endereco character varying(50),
    bairro character varying(20),
    cidade character varying(20),
    estado character varying(20),
  	cep character varying(20),
  	created_at timestamp,
  	updated_at timestamp
);


CREATE TABLE contato (
    id serial PRIMARY KEY,
    tipo character varying(20) CHECK ( tipo in ('EMAIL', 'TELEFONE', 'SKYPE') ) NOT NULL,
  	valor character varying(50),
    created_at timestamp,
  	updated_at timestamp
);


CREATE TABLE pessoa_contato (
 pessoa_id int NOT NULL, 
 contato_id int NOT NULL,
 PRIMARY KEY (pessoa_id, contato_id)
);


CREATE SEQUENCE hibernate_sequence START 1;
```

## Testes

Para realização dos testes foi utilizado JUnit5 e o banco de dados em memória H2. Os testes podem ser excutados rodando os arquivos do pacote de testes. 

## REST

Para realizar requisões via HTTP utilizar os endereços abaixo.

```
url_base = https://optimum-api-back.herokuapp.com
```

Listar Pessoas:

`GET url_base/pessoas`

Adicionar uma Pessoa

`POST url_base/pessoa`

```
{
	"nome":"Jonatas Oliveira Silva",
	"cpf":"04454360340",
	"nascimento":"2000-05-28T17:39:44.937",
	"endereco":"rua dor as",
	"bairro":"centro",
	"cidade":"teresina",
	"estado":"piaui",
	"cep":"cep",
	"contatos":[
		{
			"tipo":"SKYPE",
			"valor":"jonatas"
		},
		{
			"tipo":"EMAIL",
			"valor":"jonatas.iw@gmail.com"
		}
	]
}
```

Busca por pessoa

`GET url_base/pessoas/busca?nome=valor_da_busca`

Query utilizada para busca

```
"SELECT * FROM pessoa "
			+ "WHERE nome ILIKE %:nome% "
			+ "or endereco ILIKE %:nome% "
			+ "or cidade ILIKE %:nome% "
			+ "or bairro ILIKE %:nome% "
			+ "or estado ILIKE %:nome% "
			+ "or cep ILIKE %:nome% "
			+ "or cpf ILIKE %:nome%"
```

## Bugs e Issues abertas

Visualiar Issues abertas no gibhub para verificar bugs a resolver.
