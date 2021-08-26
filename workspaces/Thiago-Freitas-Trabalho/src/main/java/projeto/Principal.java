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
		{	System.out.println('\n' + "O que voc� deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um cliente");
			System.out.println("2. Alterar um cliente");
			System.out.println("3. Remover um cliente");
			System.out.println("4. Listar todos os clientes");
			System.out.println("5. Sair");
						
			int opcao = Console.readInt('\n' + 
							"Digite um n�mero entre 1 e 5:");
					
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
					    umCliente.getId() + " inclu�do com sucesso!");	

					break;
				}

				case 2:
				{	
					
					int resposta = Console.readInt('\n' + 
						"Digite o n�mero do Cliente que voc� deseja alterar: ");
										
					try{
						umCliente = ClienteDAO.recuperaUmCliente(resposta);
					}
					catch(ClienteNaoEncontradoException e){
						System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
							"Matr�cula = " 	+ umCliente.getId() +
							"  Nome = " 	+ umCliente.getNome() +
							"  Email = " 	+ umCliente.getEmail() +
							"  Telefone = " + umCliente.getTelefone() +
							"  Endere�o = " + umCliente.getEndereco() +
							"  Vers�o = "	+ umCliente.getVersao());
												
					System.out.println('\n' + "O que voc� deseja alterar?");
					System.out.println('\n' + "1. Nome");
					System.out.println("2. E-mail");
					System.out.println("3. Telefone");
					System.out.println("4. Endere�o");

					int opcaoAlteracao = Console.readInt('\n' + 
											"Digite um n�mero de 1 a 4:");
					
					switch (opcaoAlteracao){
						
						case 1:
							String novoNome = Console.readLine("Digite o novo nome: ");
							umCliente.setNome(novoNome);
							try{
								ClienteDAO.altera(umCliente);
								System.out.println('\n' + 
									"Altera��o de nome efetuada com sucesso!");
							}
							catch(ClienteNaoEncontradoException e){
								System.out.println('\n' + e.getMessage());
							}
							catch(EstadoDeObjetoObsoletoException e) {
								System.out.println('\n' +
										"A Opera��o n�o foi efetuada: "+
										"Os dados que tentou salvar foram " +
										"modificados por outro usu�rio.");
							}
							break;
						case 2:
							String novoEmail = Console.readLine('\n'+"Digite o novo E-mail: ");
							umCliente.setEmail(novoEmail);
							try{
								ClienteDAO.altera(umCliente);
								System.out.println('\n' + 
									"Altera��o de E-mail efetuada com sucesso!");						
							}
							catch(ClienteNaoEncontradoException e){
								System.out.println('\n' + e.getMessage());
							}
							catch(EstadoDeObjetoObsoletoException e) {
								System.out.println('\n' +
										"A Opera��o n�o foi efetuada: "+
										"Os dados que tentou salvar foram " +
										"modificados por outro usu�rio.");
							}	
							break;
						case 3:
							String novoTel = Console.readLine('\n'+"Digite o novo Telefone: ");
							umCliente.setTelefone(novoTel);
							try{
								ClienteDAO.altera(umCliente);
								System.out.println('\n' + "Altera��o de Telefone efetuada com sucesso!");						
							}
							catch(ClienteNaoEncontradoException e){
								System.out.println('\n' + e.getMessage());
							}
							catch(EstadoDeObjetoObsoletoException e) {
								System.out.println('\n' +
										"A Opera��o n�o foi efetuada: "+
										"Os dados que tentou salvar foram " +
										"modificados por outro usu�rio.");
							}	
							break;
						case 4:
							String novoEnd = Console.readLine('\n'+"Digite o novo Endere�o: ");
							umCliente.setEndereco(novoEnd);
							try{
								ClienteDAO.altera(umCliente);
								System.out.println('\n' + "Altera��o de Endere�o efetuada com sucesso!");						
							}
							catch(ClienteNaoEncontradoException e){
								System.out.println('\n' + e.getMessage());
							}
							catch(EstadoDeObjetoObsoletoException e) {
								System.out.println('\n' +
										"A Opera��o n�o foi efetuada: "+
										"Os dados que tentou salvar foram " +
										"modificados por outro usu�rio.");
							}	
							break;
						default:
							System.out.println('\n' + "Op��o inv�lida! Tente Novamente");
					}

					break;
				}

				case 3:
				{	int resposta = Console.readInt('\n' + 
						"Digite a Matr�cula do Cliente que voc� deseja remover: ");
									
					try
					{	umCliente = ClienteDAO.
										recuperaUmCliente(resposta);
					}
					catch(ClienteNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"N�mero = " 		+ umCliente.getId() + 
						"\n  Nome = " 		+ umCliente.getNome()+
						"\n  Email = " 		+ umCliente.getEmail() +
						"\n  Telefone = " 	+ umCliente.getTelefone() +
						"\n  Endere�o = " 	+ umCliente.getEndereco());
														
					String resp = Console.readLine('\n' + 
						"Confirma a remo��o do Cliente?");

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
						System.out.println('\n' + "Produto n�o removido.");
					}
					
					break;
				}

				case 4:
				{	
					List<Cliente> clientes = ClienteDAO.recuperaClientes();

					for (Cliente cliente : clientes)
					{	
						System.out.println('\n' + 
							"Matr�cula = " 	+ cliente.getId() +
							"  Nome = " 	+ cliente.getNome() +
							"  Email = " 	+ cliente.getEmail() +
							"  Telefone = " + cliente.getTelefone() +
							"  Endere�o = " + cliente.getEndereco() +
							"  Vers�o = " 	+ cliente.getVersao());
					}
					
					break;
				}

				case 5:
				{	continua = false;
					break;
				}

				default:
					System.out.println('\n' + "Op��o inv�lida! Tente novamente..");
			}
		}		
	}
}
