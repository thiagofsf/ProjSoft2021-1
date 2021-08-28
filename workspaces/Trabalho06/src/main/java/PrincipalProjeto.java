
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.ClienteNaoEncontradoException;
import excecao.FuncionarioNaoEncontradoException;
import excecao.ProjetoNaoEncontradoException;
import modelo.Cliente;
import modelo.Funcionario;
import modelo.Projeto;
import modelo.Tarefa;
import servico.ClienteAppService;
import servico.FuncionarioAppService;
import servico.ProjetoAppService;
import util.Util;

public class PrincipalProjeto {
	public void projetos(String[] args) {
		String nome;
		String dataini;
		String datafin;
		double valor;
		
		Cliente umCliente;
		Funcionario umFuncionario;
		Projeto umProjeto;

		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");
		
		ClienteAppService clienteAppService = (ClienteAppService)fabrica.getBean("clienteAppService");
		FuncionarioAppService funcionarioAppService = (FuncionarioAppService)fabrica.getBean("funcionarioAppService");
		ProjetoAppService projetoAppService = (ProjetoAppService)fabrica.getBean("projetoAppService");

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar Projeto");
			System.out.println("2. Editar Projeto");
			System.out.println("3. Remover Projeto");
			System.out.println("4. Listar Projetos");
			System.out.println("5. Recuperar Projeto Completo com Cliente e Responsavel");
			System.out.println("6. Recuperar Projeto com Tarefas");
			System.out.println("7. Listar todos os Projeto com Tarefas");
			System.out.println("0. Sair");

			int opcao = Console.readInt('\n' + "Digite o numero da opção:");

			switch (opcao) {
			case 1: {
				nome = Console.readLine('\n' + "Informe o nome do projeto: ");
				dataini = Console.readLine('\n' + "Informe a data inicio do projeto: ");
				datafin = Console.readLine('\n' + "Informe a data de entrega do projeto: ");
				valor = Console.readDouble('\n' + "Informe o valor do projeto: ");
				long idCliente = Console.readInt('\n' + "Informe o id do cliente: ");
				long idFuncionario = Console.readInt('\n' + "Informe o id do Funcionario Responsavel: ");
				
				try {
					umCliente = clienteAppService.recuperaUmCliente(idCliente);
				}catch(ClienteNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}
				
				try {
					umFuncionario = funcionarioAppService.recuperaUmFuncionario(idFuncionario);
				}catch(FuncionarioNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				umProjeto = new Projeto(nome, Util.strToCalendar(dataini), Util.strToCalendar(datafin), valor, umCliente, umFuncionario);

				try{
					long numero = projetoAppService.inclui(umProjeto);
					System.out.println('\n' + "Projeto número " + numero + " incluído com sucesso!");
				}catch(ClienteNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}catch(FuncionarioNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				break; 
			}

			case 2: {
				int resposta = Console.readInt('\n' + "Digite o número do projeto que você deseja alterar: ");
				
				try {
					umProjeto = projetoAppService.recuperaProjetoCompleto(resposta);
				} catch (ProjetoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + 
						"N° = " 				+ umProjeto.getId() +
						"  Nome = " 			+ umProjeto.getNome() +
						"  Data de Inicio = " 	+ umProjeto.getDataIniMasc() +
						"  Data de Entrega = " 	+ umProjeto.getDataFinMasc() +
						"  Valor do Projeto = "	+ umProjeto.getValor() +
						"  Cliente = "			+ umProjeto.getCliente().getNome() +
						"  Responsavel = "		+ umProjeto.getFuncionario().getNome());

				System.out.println('\n' + "O que você deseja alterar?");
				System.out.println('\n' + "1. Nome");
				System.out.println("2. Data de Entrega");
				System.out.println("3. Valor do Projeto");

				int opcaoAlteracao = Console.readInt('\n' + 
										"Digite um número de 1 a 3:");

				switch (opcaoAlteracao) {
				
				case 1:
					String novoNome = Console.readLine("Digite o novo nome: ");
					umProjeto.setNome(novoNome);
					try {
						projetoAppService.altera(umProjeto);
						System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
					} catch (ProjetoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
					break;
					
				case 2:
					String novaDatafin = Console.readLine("Digite a nova Data: ");
					umProjeto.setDatafin(Util.strToCalendar(novaDatafin));
					try {
						projetoAppService.altera(umProjeto);
						System.out.println('\n' + "Alteração de data de entrega efetuada com sucesso!");
					} catch (ProjetoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
					break;
					
				case 3:
					double novoValor = Console.readDouble("Digite o novo valor: ");
					umProjeto.setValor(novoValor);
					try {
						projetoAppService.altera(umProjeto);
						System.out.println('\n' + "Alteração de valor efetuada com sucesso!");
					} catch (ProjetoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
					break;

				default:
					System.out.println('\n' + "Opção inválida!");
				}

				break;
			}

			case 3: {
				int resposta = Console.readInt('\n' + "Digite o número do Projeto que você deseja remover: ");

				try {
					umProjeto = projetoAppService.recuperaUmProjeto(resposta);
				} catch (ProjetoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umProjeto.getId() + "    Nome = " + umProjeto.getNome());
				String resp = Console.readLine('\n' + "Confirma a remoção do Projeto?");

				if (resp.equals("s")) {
					try {

						projetoAppService.exclui(umProjeto);
						System.out.println('\n' + "Projeto removido com sucesso!");
					} catch (ProjetoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
				} else {
					System.out.println('\n' + "Projeto não removido.");
				}

				break;
			}

			case 4: {
				List<Projeto> projetos = projetoAppService.recuperaProjetos();
				
				for (Projeto projeto : projetos) {
					System.out.println('\n' + 
							"N° = " 				+ projeto.getId() +
							"  Nome = " 			+ projeto.getNome() +
							"  Data de Inicio = " 	+ projeto.getDataIniMasc() +
							"  Data de Entrega = " 	+ projeto.getDataFinMasc() +
							"  Valor do Projeto = "	+ projeto.getValor());
				}
				break;
			}
			
			case 5: {
				
				int numeroprojeto = Console.readInt('\n' + 
						"Digite o Numero do Projeto que deseja consultar:");
				
				try{
					Projeto projetoCompleto = projetoAppService.recuperaProjetoCompleto(numeroprojeto);
					if(projetoCompleto!=null) {
						System.out.println('\n' + 
								"N° = " 				+ projetoCompleto.getId() +
								"  Nome = " 			+ projetoCompleto.getNome() +
								"  Data de Inicio = " 	+ projetoCompleto.getDataIniMasc() +
								"  Data de Entrega = " 	+ projetoCompleto.getDataFinMasc() +
								"  Valor do Projeto = "	+ projetoCompleto.getValor() +
								"  Cliente = "			+ projetoCompleto.getCliente().getNome() +
								"  Responsável = "		+ projetoCompleto.getFuncionario().getNome()
								);
					}
				}catch(ProjetoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
				}
				
				break;
			}
			
			case 6: {
				
				int numeroprojeto = Console.readInt('\n' + 
						"Digite o Numero do Projeto que deseja consultar:");
				try{
					Projeto projeto = projetoAppService.recuperaUmProjetoETarefas(numeroprojeto);
					
					System.out.println('\n' + 
							"PROJETO N° = " 				+ projeto.getId() +
							"  Nome = " 			+ projeto.getNome() +
							"  Data de Inicio = " 	+ projeto.getDataIniMasc() +
							"  Data de Entrega = " 	+ projeto.getDataFinMasc() +
							"  Valor do Projeto = "	+ projeto.getValor()
							);
					List<Tarefa> tarefas = projeto.getTarefas();
					for(Tarefa tarefa : tarefas) {
						System.out.println( 
								"- Tarefa N° = " 			+ tarefa.getId() +
								"  Nome = " 			+ tarefa.getNome() +
								"  Descricao = " 		+ tarefa.getDescricao() +
								"  Data de Entrega = " 	+ tarefa.getPrazoMasc() +
								"  Status = "			+ tarefa.getStatusTxt()
								);
					}
				}catch(ProjetoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}
				
				break;
			}
			
			case 7: {
				List<Projeto> projetos = projetoAppService.recuperaProjetosETarefas();

				if (projetos.size() != 0) {
					System.out.println("");

					for (Projeto projeto : projetos) {
						System.out.println('\n' + 
								"PROJETO N° = " 				+ projeto.getId() +
								"  Nome = " 			+ projeto.getNome() +
								"  Data de Inicio = " 	+ projeto.getDataIniMasc() +
								"  Data de Entrega = " 	+ projeto.getDataFinMasc() +
								"  Valor do Projeto = "	+ projeto.getValor()
								);

						List<Tarefa> tarefas = projeto.getTarefas();
						for(Tarefa tarefa : tarefas) {
							System.out.println( 
									"- Tarefa N° = " 			+ tarefa.getId() +
									"  Nome = " 			+ tarefa.getNome() +
									"  Descricao = " 		+ tarefa.getDescricao() +
									"  Data de Entrega = " 	+ tarefa.getPrazoMasc() +
									"  Status = "			+ tarefa.getStatusTxt()
									);
						}
					}
				} else {
					System.out.println('\n' + "Não há projetos cadastrados com esta descrição.");
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
