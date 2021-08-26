package projeto;

import java.util.List;
import corejava.Console;

public class Principal
{	public static void main (String[] args) 
	{	
		String nome;
		String email;
		String endereco;
		String telefone;
		//double lanceMinimo;
		//String dataCadastro;
		
		Cliente umCliente;

		ClienteDAO ClienteDAO = FabricaDeDAOs.getDAO(ClienteDAO.class);

		boolean continua = true;
		while (continua)
		{	System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um cliente");
			System.out.println("2. Alterar um cliente");
			System.out.println("3. Remover um cliente");
			System.out.println("4. Listar todos os clientes");
			System.out.println("5. Sair");
						
			int opcao = Console.readInt('\n' + 
							"Digite um número entre 1 e 5:");
					
			switch (opcao)
			{	case 1:
				{
					nome = Console.readLine('\n' + 
						"Informe o nome do cliente: ");
					email = Console.readLine('\n' + 
							"Informe o email do cliente: ");
					endereco = Console.readLine('\n' + 
							"Informe o endereco do cliente: ");
					telefone = Console.readLine('\n' + "Informe o Telefone do cliente: ");
						
					umCliente = new Cliente(nome, email, telefone, endereco);
					
					ClienteDAO.inclui(umCliente);
					
					System.out.println('\n' + "Cliente Matricula " + 
					    umCliente.getId() + " incluído com sucesso!");	

					break;
				}

				case 2:
				{	
					
					int resposta = Console.readInt('\n' + 
						"Digite o número do Cliente que você deseja alterar: ");
										
					try{
						umCliente = ClienteDAO.recuperaUmCliente(resposta);
					}
					catch(ClienteNaoEncontradoException e){
						System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
							"Matrícula = " 	+ umCliente.getId() +
							"  Nome = " 	+ umCliente.getNome() +
							"  Email = " 	+ umCliente.getEmail() +
							"  Telefone = " + umCliente.getTelefone() +
							"  Endereço = " + umCliente.getEndereco() +
							"  Versão = "	+ umCliente.getVersao());
												
					System.out.println('\n' + "O que você deseja alterar?");
					System.out.println('\n' + "1. Nome");
					System.out.println("2. E-mail");
					System.out.println("3. Telefone");
					System.out.println("4. Endereço");

					int opcaoAlteracao = Console.readInt('\n' + 
											"Digite um número de 1 a 4:");
					
					switch (opcaoAlteracao){
						
						case 1:
							String novoNome = Console.readLine("Digite o novo nome: ");
							umCliente.setNome(novoNome);
							try{
								ClienteDAO.altera(umCliente);
								System.out.println('\n' + 
									"Alteração de nome efetuada com sucesso!");
							}
							catch(ClienteNaoEncontradoException e){
								System.out.println('\n' + e.getMessage());
							}
							catch(EstadoDeObjetoObsoletoException e) {
								System.out.println('\n' +
										"A Operação não foi efetuada: "+
										"Os dados que tentou salvar foram " +
										"modificados por outro usuário.");
							}
							break;
						case 2:
							String novoEmail = Console.readLine('\n'+"Digite o novo E-mail: ");
							umCliente.setEmail(novoEmail);
							try{
								ClienteDAO.altera(umCliente);
								System.out.println('\n' + 
									"Alteração de E-mail efetuada com sucesso!");						
							}
							catch(ClienteNaoEncontradoException e){
								System.out.println('\n' + e.getMessage());
							}
							catch(EstadoDeObjetoObsoletoException e) {
								System.out.println('\n' +
										"A Operação não foi efetuada: "+
										"Os dados que tentou salvar foram " +
										"modificados por outro usuário.");
							}	
							break;
						case 3:
							String novoTel = Console.readLine('\n'+"Digite o novo Telefone: ");
							umCliente.setTelefone(novoTel);
							try{
								ClienteDAO.altera(umCliente);
								System.out.println('\n' + "Alteração de Telefone efetuada com sucesso!");						
							}
							catch(ClienteNaoEncontradoException e){
								System.out.println('\n' + e.getMessage());
							}
							catch(EstadoDeObjetoObsoletoException e) {
								System.out.println('\n' +
										"A Operação não foi efetuada: "+
										"Os dados que tentou salvar foram " +
										"modificados por outro usuário.");
							}	
							break;
						case 4:
							String novoEnd = Console.readLine('\n'+"Digite o novo Endereço: ");
							umCliente.setEndereco(novoEnd);
							try{
								ClienteDAO.altera(umCliente);
								System.out.println('\n' + "Alteração de Endereço efetuada com sucesso!");						
							}
							catch(ClienteNaoEncontradoException e){
								System.out.println('\n' + e.getMessage());
							}
							catch(EstadoDeObjetoObsoletoException e) {
								System.out.println('\n' +
										"A Operação não foi efetuada: "+
										"Os dados que tentou salvar foram " +
										"modificados por outro usuário.");
							}	
							break;
						default:
							System.out.println('\n' + "Opção inválida! Tente Novamente");
					}

					break;
				}

				case 3:
				{	int resposta = Console.readInt('\n' + 
						"Digite a Matrícula do Cliente que você deseja remover: ");
									
					try
					{	umCliente = ClienteDAO.
										recuperaUmCliente(resposta);
					}
					catch(ClienteNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Número = " 		+ umCliente.getId() + 
						"\n  Nome = " 		+ umCliente.getNome()+
						"\n  Email = " 		+ umCliente.getEmail() +
						"\n  Telefone = " 	+ umCliente.getTelefone() +
						"\n  Endereço = " 	+ umCliente.getEndereco());
														
					String resp = Console.readLine('\n' + 
						"Confirma a remoção do Cliente?");

					if(resp.equals("s")){	
						try{
							ClienteDAO.exclui (umCliente.getId());
							System.out.println('\n' + "Produto removido com sucesso!");
						}
						catch(ClienteNaoEncontradoException e){
							System.out.println('\n' + e.getMessage());
						}
					}
					else{
						System.out.println('\n' + "Produto não removido.");
					}
					
					break;
				}

				case 4:
				{	
					List<Cliente> clientes = ClienteDAO.recuperaClientes();

					for (Cliente cliente : clientes)
					{	
						System.out.println('\n' + 
							"Matrícula = " 	+ cliente.getId() +
							"  Nome = " 	+ cliente.getNome() +
							"  Email = " 	+ cliente.getEmail() +
							"  Telefone = " + cliente.getTelefone() +
							"  Endereço = " + cliente.getEndereco() +
							"  Versão = " 	+ cliente.getVersao());
					}
					
					break;
				}

				case 5:
				{	continua = false;
					break;
				}

				default:
					System.out.println('\n' + "Opção inválida! Tente novamente..");
			}
		}		
	}
}
