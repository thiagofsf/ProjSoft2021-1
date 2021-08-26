https://dev.mysql.com/downloads/

Download MySQL Community Server

Últimas versões:
- 5.5
- 5.6
- 5.7
- 8.0.12 (atual)

DROP TABLE clientes;

#banco de dados criado: banco
#Código que gera a tabela clientes

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefone` bigint(30) NOT NULL,
  `endereco` varchar(200) NOT NULL,
  `versao` int(50) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#Inserir alguns clientes para testes
INSERT INTO clientes(nome, email, telefone, endereco)
VALUES('Cliente 1', 'cliente1@gmail.com', 21000000000, 'Rua da Silva, 100');

INSERT INTO clientes(nome, email, telefone, endereco)
VALUES('Cliente 2', 'cliente2@gmail.com', 21000000000, 'Rua da Silva, 100');
