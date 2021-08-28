
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.ClienteNaoEncontradoException;
import modelo.Cliente;
import modelo.Projeto;
import servico.ClienteAppService;

public class PrincipalCliente {
	public void clientes(String[] args) {
		String nome;
		String email;
		String telefone;
		String endereco;
		
		Cliente umCliente;

		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");
		
		ClienteAppService clienteAppService = (ClienteAppService)fabrica.getBean("clienteAppService");

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar Cliente");
			System.out.println("2. Editar Cliente");
			System.out.println("3. Remover Cliente");
			System.out.println("4. Listar Clientes");
			System.out.println("5. Recuperar um Cliente e Projetos");
			System.out.println("6. Listar todos os Clientes e Projetos");
			System.out.println("0. Sair");

			int opcao = Console.readInt('\n' + "Digite o numero da opção:");

			switch (opcao) {
			case 1: {
				nome = Console.readLine('\n' + "Informe o nome do Cliente: ");
				email = Console.readLine('\n' + "Informe o e-mail do Cliente: ");
				telefone = Console.readLine('\n' + "Informe o telefone do Cliente: ");
				endereco = Console.readLine('\n' + "Informe o endereco do Cliente: ");

				umCliente = new Cliente(nome, email, telefone, endereco);

				long numero = clienteAppService.inclui(umCliente);

				System.out.println('\n' + "Cliente número " + numero + " incluído com sucesso!");

				break;
			}

			case 2: {
				int resposta = Console.readInt('\n' + "Digite o número do cliente que você deseja alterar: ");
				
				try {
					umCliente = clienteAppService.recuperaUmCliente(resposta);
				} catch (ClienteNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + 
						"Matrícula = " 	+ umCliente.getId() +
						"  Nome = " 	+ umCliente.getNome() +
						"  Email = " 	+ umCliente.getEmail() +
						"  Telefone = " + umCliente.getTelefone() +
						"  Endereço = " + umCliente.getEndereco());

				System.out.println('\n' + "O que você deseja alterar?");
				System.out.println('\n' + "1. Nome");
				System.out.println("2. E-mail");
				System.out.println("3. Telefone");
				System.out.println("4. Endereço");

				int opcaoAlteracao = Console.readInt('\n' + 
										"Digite um número de 1 a 4:");

				switch (opcaoAlteracao) {
				
				case 1:
					String novoNome = Console.readLine("Digite o novo nome: ");
					umCliente.setNome(novoNome);
					try {
						clienteAppService.altera(umCliente);
						System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
					} catch (ClienteNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
					break;
					
				case 2:
					String novoEmail = Console.readLine("Digite o novo E-mail: ");
					umCliente.setEmail(novoEmail);
					try {
						clienteAppService.altera(umCliente);
						System.out.println('\n' + "Alteração de email efetuada com sucesso!");
					} catch (ClienteNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
					break;
					
				case 3:
					String novoTelefone = Console.readLine("Digite o novo telefone: ");
					umCliente.setNome(novoTelefone);
					try {
						clienteAppService.altera(umCliente);
						System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
					} catch (ClienteNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
					break;
					
				case 4:
					String novoEndereco = Console.readLine("Digite o novo endereço: ");
					umCliente.setNome(novoEndereco);
					try {
						clienteAppService.altera(umCliente);
						System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
					} catch (ClienteNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
					break;

				default:
					System.out.println('\n' + "Opção inválida!");
				}

				break;
			}

			case 3: {
				int resposta = Console.readInt('\n' + "Digite o número do cliente que você deseja remover: ");

				try {
					umCliente = clienteAppService.recuperaUmCliente(resposta);
				} catch (ClienteNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umCliente.getId() + "    Nome = " + umCliente.getNome());
				String resp = Console.readLine('\n' + "Confirma a remoção do Cliente?");

				if (resp.equals("s")) {
					try {

						clienteAppService.exclui(umCliente);
						System.out.println('\n' + "Cliente removido com sucesso!");
					} catch (ClienteNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
				} else {
					System.out.println('\n' + "Cliente não removido.");
				}

				break;
			}

			case 4: {
				List<Cliente> clientes = clienteAppService.recuperaClientes();

				for (Cliente cliente : clientes) {
					System.out.println('\n' + 
							"Matrícula = " 	+ cliente.getId() +
							"  Nome = " 	+ cliente.getNome() +
							"  Email = " 	+ cliente.getEmail() +
							"  Telefone = " + cliente.getTelefone() +
							"  Endereço = " + cliente.getEndereco());
				}

				break;
			}
			
			case 5: {
				
				int numerocliente = Console.readInt('\n' + 
						"Digite a Matricula do Cliente que deseja consultar:");
				try{
					Cliente cliente = clienteAppService.recuperaUmClienteEProjetos(numerocliente);
					
					System.out.println('\n' + 
							"Matrícula = " 	+ cliente.getId() +
							"  Nome = " 	+ cliente.getNome() +
							"  Email = " 	+ cliente.getEmail() +
							"  Telefone = " + cliente.getTelefone() +
							"  Endereço = " + cliente.getEndereco());
					List<Projeto> projetos = cliente.getProjetos();
					for(Projeto projeto : projetos) {
						System.out.println(
								"- Projeto = " 		+ projeto.getId() +
								"  Nome = " 			+ projeto.getNome() +
								"  Data de Inicio = " 	+ projeto.getDataIniMasc() +
								"  Data de Entrega = " 	+ projeto.getDataFinMasc() +
								"  Valor do Projeto = "	+ projeto.getValor()
								);
					}
				}catch(ClienteNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}
				
				break;
			}
			
			case 6: {
				List<Cliente> clientes = clienteAppService.recuperaClientesEProjetos();

				if (clientes.size() != 0) {
					System.out.println("");

					for (Cliente cliente : clientes) {
						System.out.println('\n' + 
								"Matrícula = " 	+ cliente.getId() +
								"  Nome = " 	+ cliente.getNome() +
								"  Email = " 	+ cliente.getEmail() +
								"  Telefone = " + cliente.getTelefone() +
								"  Endereço = " + cliente.getEndereco());
						List<Projeto> projetos = cliente.getProjetos();
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
