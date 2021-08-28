
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.FuncionarioNaoEncontradoException;
import modelo.Funcionario;
import modelo.Projeto;
import servico.FuncionarioAppService;

public class PrincipalFuncionario {
	public void funcionarios(String[] args) {
		String nome;
		String usuario;
		String senha;
		
		Funcionario umFuncionario;

		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");
		
		FuncionarioAppService funcionarioAppService = (FuncionarioAppService)fabrica.getBean("funcionarioAppService");

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar Funcionario");
			System.out.println("2. Editar Funcionario");
			System.out.println("3. Remover Funcionario");
			System.out.println("4. Listar Funcionarios");
			System.out.println("5. Recuperar um Funcionario e Projetos");
			System.out.println("6. Listar todos Funcionarios e Projetos");
			System.out.println("0. Sair");

			int opcao = Console.readInt('\n' + "Digite o numero da opção:");

			switch (opcao) {
			case 1: {
				nome = Console.readLine('\n' + "Informe o nome do funcionario: ");
				usuario = Console.readLine('\n' + "Informe o usuario do funcionário: ");
				senha = Console.readLine('\n' + "Informe a senha do Funcionario: ");

				umFuncionario = new Funcionario(nome, usuario, senha);
				
				System.out.println('\n' + 
						"Matrícula = " 	+ umFuncionario.getId() +
						"  Nome = " 	+ umFuncionario.getNome() +
						"  Usuário = " 	+ umFuncionario.getUsuario() +
						"  Senha = " 	+ umFuncionario.getSenha());


				long numero = funcionarioAppService.inclui(umFuncionario);

				System.out.println('\n' + "Funcionario número " + numero + " incluído com sucesso!");

				break;
			}

			case 2: {
				int resposta = Console.readInt('\n' + "Digite o número do funcionario que você deseja alterar: ");
				
				try {
					umFuncionario = funcionarioAppService.recuperaUmFuncionario(resposta);
				} catch (FuncionarioNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + 
						"Matrícula = " 	+ umFuncionario.getId() +
						"  Nome = " 	+ umFuncionario.getNome() +
						"  Usuário = " 	+ umFuncionario.getUsuario() +
						"  Senha = " 	+ umFuncionario.getSenha());

				System.out.println('\n' + "O que você deseja alterar?");
				System.out.println('\n' + "1. Nome");
				System.out.println("2. Usuario");
				System.out.println("3. Senha");

				int opcaoAlteracao = Console.readInt('\n' + 
										"Digite um número de 1 a 3:");

				switch (opcaoAlteracao) {
				
				case 1:
					String novoNome = Console.readLine("Digite o novo nome: ");
					umFuncionario.setNome(novoNome);
					try {
						funcionarioAppService.altera(umFuncionario);
						System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
					} catch (FuncionarioNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
					break;
					
				case 2:
					String novoUsuario = Console.readLine("Digite o novo nome de Usuário: ");
					umFuncionario.setUsuario(novoUsuario);
					try {
						funcionarioAppService.altera(umFuncionario);
						System.out.println('\n' + "Alteração de nome de Usuário efetuada com sucesso!");
					} catch (FuncionarioNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
					break;
					
				case 3:
					String novaSenha = Console.readLine("Digite a nova Senha: ");
					umFuncionario.setSenha(novaSenha);
					try {
						funcionarioAppService.altera(umFuncionario);
						System.out.println('\n' + "Alteração de senha efetuada com sucesso!");
					} catch (FuncionarioNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
					break;

				default:
					System.out.println('\n' + "Opção inválida!");
				}

				break;
			}

			case 3: {
				int resposta = Console.readInt('\n' + "Digite o número do funcionario que você deseja remover: ");

				try {
					umFuncionario = funcionarioAppService.recuperaUmFuncionario(resposta);
				} catch (FuncionarioNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umFuncionario.getId() + "    Nome = " + umFuncionario.getNome());
				String resp = Console.readLine('\n' + "Confirma a remoção do Funcionario?");

				if (resp.equals("s")) {
					try {

						funcionarioAppService.exclui(umFuncionario);
						System.out.println('\n' + "Funcionario removido com sucesso!");
					} catch (FuncionarioNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
				} else {
					System.out.println('\n' + "Funcionario não removido.");
				}

				break;
			}

			case 4: {
				List<Funcionario> funcionarios = funcionarioAppService.recuperaFuncionarios();

				for (Funcionario funcionario : funcionarios) {
					System.out.println('\n' + 
							"Matrícula = " 	+ funcionario.getId() +
							"  Nome = " 	+ funcionario.getNome() +
							"  Usuario = " 	+ funcionario.getUsuario() +
							"  Senha = " 	+ funcionario.getSenha());
				}

				break;
			}
			
			case 5: {
				
				int numerofuncionario = Console.readInt('\n' + 
						"Digite o número do Funcionario que deseja consultar:");
				try{
					Funcionario funcionario = funcionarioAppService.recuperaUmFuncionarioEProjetos(numerofuncionario);
					
					System.out.println('\n' + 
							"Matrícula = " 	+ funcionario.getId() +
							"  Nome = " 	+ funcionario.getNome() +
							"  Usuario = " 	+ funcionario.getUsuario() +
							"  Senha = " 	+ funcionario.getSenha());
					List<Projeto> projetos = funcionario.getProjetos();
					for(Projeto projeto : projetos) {
						System.out.println( 
								"- Projeto = " 		+ projeto.getId() +
								"  Nome = " 			+ projeto.getNome() +
								"  Data de Inicio = " 	+ projeto.getDataIniMasc() +
								"  Data de Entrega = " 	+ projeto.getDataFinMasc() +
								"  Valor do Projeto = "	+ projeto.getValor()
								);
					}
				}catch(FuncionarioNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}
				
				break;
			}
			
			case 6: {
				List<Funcionario> funcionarios = funcionarioAppService.recuperaFuncionariosEProjetos();

				if (funcionarios.size() != 0) {
					System.out.println("");

					for (Funcionario funcionario : funcionarios) {
						System.out.println('\n' + 
								"Matrícula = " 	+ funcionario.getId() +
								"  Nome = " 	+ funcionario.getNome() +
								"  Usuario = " 	+ funcionario.getUsuario() +
								"  Senha = " 	+ funcionario.getSenha());
						List<Projeto> projetos = funcionario.getProjetos();
						for(Projeto projeto : projetos) {
							System.out.println( 
									"- Projeto = " 		+ projeto.getId() +
									"  Nome = " 			+ projeto.getNome() +
									"  Data de Inicio = " 	+ projeto.getDataIniMasc() +
									"  Data de Entrega = " 	+ projeto.getDataFinMasc() +
									"  Valor do Projeto = "	+ projeto.getValor()
									);
						}
					}
				} else {
					System.out.println('\n' + "Não há clientes cadastrados com esta descrição.");
				}

				break;
			}


			case 0: {
				continua = false;
				break;
			}

			default:
				System.out.println('\n' + "Opção inválida!");
			}
		}
	}
}
