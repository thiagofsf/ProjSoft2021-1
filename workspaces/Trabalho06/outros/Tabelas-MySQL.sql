https://dev.mysql.com/downloads/

Download MySQL Community Server

Últimas versões:
- 5.5
- 5.6
- 5.7
- 8.0.12 (atual)

/*Criando Tabela Clientes*/
CREATE TABLE `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefone` bigint(30) NOT NULL,
  `endereco` varchar(200) NOT NULL,
  `versao` int(50) NOT NULL DEFAULT 0
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*Inserindo dados de Exemplo na Tabela Clientes*/
INSERT INTO `clientes` (`id`, `nome`, `email`, `telefone`, `endereco`, `versao`) VALUES
(2, 'Carla Silva', 'cliente2@gmail.com', 2147483647, 'Rua da Silva, 100', 0),
(3, 'Adriella', 'adri@gmail.com', 2134567890, 'Rua Carlos Coco 100', 0),
(4, 'Carla', 'carla@hotmail.com', 219856435656, 'Rua Silvaninha 200', 1),
(5, 'Adriella', 'adriella@email.com', 212222222222, 'Rua da Silva 100', 0);

/*Criando Tabela Funcionarios (Responsavel)*/
CREATE TABLE `funcionarios` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `senha` varchar(10) NOT NULL
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*Inserindo dados de Exemplo na Tabela Funcionarios*/
INSERT INTO `funcionarios` (`id`, `nome`, `usuario`, `senha`) VALUES
(1, 'Jose', 'jose10', 'jose123'),
(2, 'Alan', 'alan1', 'alan123');

/*Criando Tabela Projetos*/
CREATE TABLE `projetos` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `dataini` date NOT NULL,
  `datafin` date NOT NULL,
  `valor` double NOT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `funcionario_id` int(11) DEFAULT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
ALTER TABLE `projetos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cliente` (`cliente_id`),
  ADD KEY `funcionario` (`funcionario_id`);
/*Inserindo dados exemplo na Tabela Projetos*/
INSERT INTO `projetos` (`id`, `nome`, `dataini`, `datafin`, `valor`, `cliente_id`, `funcionario_id`) VALUES
(1, 'Projeto 1 alt', '2021-08-27', '2021-11-27', 1500, 2, 1);

/*Criando Tabela Tarefas*/
CREATE TABLE `tarefas` (
  `id` int(200) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `descricao` varchar(2000) NOT NULL,
  `prazo` date NOT NULL,
  `status` int(4) NOT NULL,
  `projeto_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
ALTER TABLE `tarefas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `projeto` (`projeto_id`);
/*Inserindo dados exemplo na Tabela Tarefas*/
INSERT INTO `tarefas` (`id`, `nome`, `descricao`, `prazo`, `status`, `projeto_id`) VALUES
(1, 'Tarefa 1', 'Nova Descrição da Tarefa 1 (editada 1x)', '2021-08-30', 1, 1);




/*Codigos da tabela produto (os arquivos da classo permanecem no projeto)*/
DROP TABLE produto02;

CREATE TABLE banco.produto02 (
  id INT(11) NOT NULL AUTO_INCREMENT,
  nome VARCHAR(30) NOT NULL,
  lance_minimo DECIMAL(8, 2) NOT NULL,
  data_cadastro DATE NOT NULL,
  data_venda DATE DEFAULT NULL,
  versao INT(11) DEFAULT 0,
  PRIMARY KEY (id)
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

INSERT INTO produto02(NOME, LANCE_MINIMO, DATA_CADASTRO)
VALUES('TV SAMSUNG 20 POL', 2000, curdate());

INSERT INTO produto02(NOME, LANCE_MINIMO, DATA_CADASTRO)
VALUES('TV SAMSUNG 22 POL', 2500, curdate());

