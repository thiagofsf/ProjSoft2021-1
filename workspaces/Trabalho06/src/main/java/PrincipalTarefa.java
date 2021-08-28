
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.ProjetoNaoEncontradoException;
import excecao.TarefaNaoEncontradaException;
import modelo.Projeto;
import modelo.Tarefa;
import servico.ProjetoAppService;
import servico.TarefaAppService;
import util.Util;

public class PrincipalTarefa {
	public void tarefas(String[] args) {
		String nome;
		String descricao;
		String prazo;
		int status;

		Projeto umProjeto;
		Tarefa umaTarefa;

		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");
		
		ProjetoAppService projetoAppService = (ProjetoAppService)fabrica.getBean("projetoAppService");
		TarefaAppService tarefaAppService = (TarefaAppService)fabrica.getBean("tarefaAppService");

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar Tarefa");
			System.out.println("2. Concluir Tarefa");
			System.out.println("3. Editar Tarefa");
			System.out.println("4. Remover Tarefa");
			System.out.println("5. Listar Tarefas");
			System.out.println("0. Sair");

			int opcao = Console.readInt('\n' + "Digite o numero da opção:");

			switch (opcao) {
			case 1: {
				nome = Console.readLine('\n' + "Informe o nome da tarefa: ");
				descricao = Console.readLine('\n' + "Descreva a tarefa: ");
				prazo = Console.readLine('\n' + "Informe a data prazo para a tarefa: ");
				status = 0; 	//toda a tarefa é inicializada com status não concluida, ou seja, 0
				
				long idProjeto = Console.readInt('\n' + "Informe o id do projeto onde a tarefa será add: ");
				
				try {
					umProjeto = projetoAppService.recuperaUmProjeto(idProjeto);
				}catch(ProjetoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				umaTarefa = new Tarefa(nome,descricao, Util.strToCalendar(prazo), status, umProjeto);

				try{
					long numero = tarefaAppService.inclui(umaTarefa);
					System.out.println('\n' + "Tarefa número " + numero + " incluída com sucesso!");
				}catch(ProjetoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				break; 
			}
			
			case 2: {
				int resposta = Console.readInt('\n' + "Digite o número da tarefa que você deseja Concluir: ");
				
				try {
					umaTarefa = tarefaAppService.recuperaTarefaCompleta(resposta);
				} catch (TarefaNaoEncontradaException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + 
						"N° = " 				+ umaTarefa.getId() +
						"  Nome = " 			+ umaTarefa.getNome() +
						"  Descricao = " 		+ umaTarefa.getDescricao() +
						"  Data de Entrega = " 	+ umaTarefa.getPrazoMasc() +
						"  Status = "			+ umaTarefa.getStatusTxt()
						);

				String conclusao = Console.readLine('\n' + "Confirmar Conclusão? (s/n): ");
				
				if(conclusao.contentEquals("s")) {
					int novostatus = 1;
					umaTarefa.setStatus(novostatus);
					try {
						tarefaAppService.altera(umaTarefa);
						System.out.println('\n' + "Tarefa agora marcada como concluida!");
					} catch (TarefaNaoEncontradaException e) {
						System.out.println('\n' + e.getMessage());
					}
				}
				else {
					System.out.println('\n' + "Conclusão Cancelada");
				}
				break;
			}

			case 3: {
				int resposta = Console.readInt('\n' + "Digite o número da tarefa que você deseja alterar: ");
				
				try {
					umaTarefa = tarefaAppService.recuperaTarefaCompleta(resposta);
				} catch (TarefaNaoEncontradaException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + 
						"N° = " 				+ umaTarefa.getId() +
						"  Nome = " 			+ umaTarefa.getNome() +
						"  Descricao = " 		+ umaTarefa.getDescricao() +
						"  Data de Entrega = " 	+ umaTarefa.getPrazoMasc() +
						"  Status = "			+ umaTarefa.getStatusTxt()
						);

				System.out.println('\n' + "O que você deseja alterar?");
				System.out.println('\n' + "1. Nome");
				System.out.println("2. Descricao");
				System.out.println("3. Prazo");
				System.out.println("0. Cancelar");
				System.out.println("\n obs: Para concluir a tarefa use o comando Concluir tarefa do menu principal");

				int opcaoAlteracao = Console.readInt('\n' + 
										"Digite a opção desejada:");

				switch (opcaoAlteracao) {
				
				case 1:
					String novoNome = Console.readLine("Digite o novo nome: ");
					umaTarefa.setNome(novoNome);
					try {
						tarefaAppService.altera(umaTarefa);
						System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
					} catch (TarefaNaoEncontradaException e) {
						System.out.println('\n' + e.getMessage());
					}
					break;
					
				case 2:
					String novaDescricao = Console.readLine("Digite a nova Descrição: ");
					umaTarefa.setDescricao(novaDescricao);
					try {
						tarefaAppService.altera(umaTarefa);
						System.out.println('\n' + "Alteração de descrição efetuada com sucesso!");
					} catch (TarefaNaoEncontradaException e) {
						System.out.println('\n' + e.getMessage());
					}
					break;
					
				case 3:
					String novaDataPrazo = Console.readLine("Digite a nova Data: ");
					umaTarefa.setPrazo(Util.strToCalendar(novaDataPrazo));
					try {
						tarefaAppService.altera(umaTarefa);
						System.out.println('\n' + "Alteração de tarefa efetuada com sucesso!");
					} catch (TarefaNaoEncontradaException e) {
						System.out.println('\n' + e.getMessage());
					}
					break;
				case 0:
					System.out.println('\n' + "Alteração Cancelada!");
					break;
				default:
					System.out.println('\n' + "Opção inválida!");
				}

				break;
			}

			case 4: {
				int resposta = Console.readInt('\n' + "Digite o número da Tarefa que você deseja remover: ");

				try {
					umaTarefa = tarefaAppService.recuperaTarefaCompleta(resposta);
				} catch (TarefaNaoEncontradaException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umaTarefa.getId() + "    Nome = " + umaTarefa.getNome());
				String resp = Console.readLine('\n' + "Confirma a remoção da Tarefa?");

				if (resp.equals("s")) {
					try {

						tarefaAppService.exclui(umaTarefa);
						System.out.println('\n' + "Tarefa removida com sucesso!");
					} catch (TarefaNaoEncontradaException e) {
						System.out.println('\n' + e.getMessage());
					}
				} else {
					System.out.println('\n' + "Tarefa não removida.");
				}

				break;
			}

			case 5: {
				List<Tarefa> tarefas = tarefaAppService.recuperaTarefas();
				
				for (Tarefa tarefa : tarefas) {
					System.out.println('\n' + 
							"N° = " 				+ tarefa.getId() +
							"  Nome = " 			+ tarefa.getNome() +
							"  Descricao = " 		+ tarefa.getDescricao() +
							"  Data de Entrega = " 	+ tarefa.getPrazoMasc() +
							"  Status = "			+ tarefa.getStatusTxt()
							);
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
