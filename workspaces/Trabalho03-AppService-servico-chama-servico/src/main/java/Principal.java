
import java.util.GregorianCalendar;
import java.util.List;

import corejava.Console;
import excecao.ContaNaoEncontradaException;
import modelo.Conta;
import modelo.Movimento;
import servico.ContaAppService;
import servico.MovimentoAppService;
import util.Util;

public class Principal {
	public static void main(String[] args) {
		double saldo;
		String dataCadastro;
		Conta umaConta;

		ContaAppService contaAppService = new ContaAppService();
		MovimentoAppService movimentoAppService = new MovimentoAppService();

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que voc� deseja fazer?");
			System.out.println('\n' + "1. Cadastrar uma conta");
			System.out.println("2. Alterar uma conta");
			System.out.println("3. Remover uma conta");
			System.out.println("4. Listar todas as contas");
			System.out.println("5. Debitar valor de conta");
			System.out.println("6. Creditar valor em conta");
			System.out.println("7. Transferir valor entre contas");
			System.out.println("8. Listar todos os movimentos");
			System.out.println("9. Sair");

			int opcao = Console.readInt('\n' + "Digite um n�mero entre 1 e 9:");

			switch (opcao) {
			case 1: {
				saldo = Console.readDouble("Informe o saldo da conta: ");

				dataCadastro = Console.readLine("Informe a data de cadastramento do conta: ");

				umaConta = new Conta(saldo, Util.strToDate(dataCadastro));

				contaAppService.inclui(umaConta);

				break;
			}

			case 2: {
				int resposta = Console.readInt('\n' + "Digite o n�mero do conta que voc� deseja alterar: ");

				try {
					umaConta = contaAppService.recuperaUmaConta(resposta);
				} catch (ContaNaoEncontradaException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "N�mero = " + umaConta.getId() + 
						              "    Saldo = " + umaConta.getSaldo());

				System.out.println('\n' + "O que voc� deseja alterar?");

				System.out.println('\n' + "1. Saldo");

				int opcaoAlteracao = Console.readInt('\n' + "Digite o n�mero de 1:");

				switch (opcaoAlteracao) {
				case 1:
					double novoSaldo = Console.readDouble("Digite o novo saldo: ");

					umaConta.setSaldo(novoSaldo);

					try {
						contaAppService.altera(umaConta);

						System.out.println('\n' + "Altera��o de nome efetuada com sucesso!");
					} catch (ContaNaoEncontradaException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				default:
					System.out.println('\n' + "Op��o inv�lida!");
				}

				break;
			}

			case 3: {
				int resposta = Console.readInt('\n' + "Digite o n�mero da conta que voc� deseja remover: ");

				try {
					umaConta = contaAppService.recuperaUmaConta(resposta);
				} catch (ContaNaoEncontradaException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "N�mero = " + umaConta.getId() + 
						              "    Saldo = " + umaConta.getSaldo());

				String resp = Console.readLine('\n' + "Confirma a remo��o da conta?");

				if (resp.equals("s")) {
					try {
						contaAppService.exclui(umaConta.getId());

						System.out.println('\n' + "Conta removida com sucesso!");
					} catch (ContaNaoEncontradaException e) {
						System.out.println('\n' + e.getMessage());
					}
				} else {
					System.out.println('\n' + "Conta n�o removida.");
				}

				break;
			}

			case 4: {
				List<Conta> contas = contaAppService.recuperaContas();

				for (Conta conta : contas) {
					System.out.println('\n' + "Id = " + conta.getId() + 
							                "  Saldo = " + conta.getSaldo() + 
							                "  Data Cadastro = " + conta.getDataCadastroMasc());
				}

				break;
			}

			case 5: {
				int resposta = Console.readInt('\n' + "Digite o n�mero do conta que voc� deseja debitar: ");

				try {
					umaConta = contaAppService.recuperaUmaConta(resposta);
				} catch (ContaNaoEncontradaException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "N�mero = " + umaConta.getId() + 
						              "    Saldo = " + umaConta.getSaldo());

				double valor = Console.readDouble("Digite o valor a ser debitado: ");

				try {
					contaAppService.debita(umaConta, valor, new GregorianCalendar());
				} catch (ContaNaoEncontradaException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Conta debitada com sucesso!");

				break;
			}

			case 6: {
				int resposta = Console.readInt('\n' + "Digite o n�mero do conta que voc� deseja creditar: ");

				try {
					umaConta = contaAppService.recuperaUmaConta(resposta);
				} catch (ContaNaoEncontradaException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "N�mero = " + umaConta.getId() + 
						              "    Saldo = " + umaConta.getSaldo());

				double valor = Console.readDouble("Digite o valor a ser creditado: ");

				try {
					contaAppService.credita(umaConta, valor, new GregorianCalendar());
				} catch (ContaNaoEncontradaException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Conta creditada com sucesso!");

				break;
			}

			case 7: {
				int numContaDebitada = Console.readInt('\n' + "Digite o n�mero do conta que voc� deseja debitar: ");

				int numContaCreditada = Console.readInt('\n' + "Digite o n�mero do conta que voc� deseja creditar: ");

				Conta contaDebitada;
				Conta contaCreditada;

				try {
					contaDebitada = contaAppService.recuperaUmaConta(numContaDebitada);
					contaCreditada = contaAppService.recuperaUmaConta(numContaCreditada);
				} catch (ContaNaoEncontradaException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "N�mero da conta debitada = " + contaDebitada.getId() + 
						              "    Saldo = " + contaDebitada.getSaldo());

				System.out.println('\n' + "N�mero da conta creditada = " + contaCreditada.getId() + 
						              "    Saldo = " + contaCreditada.getSaldo());

				double valor = Console.readDouble("Digite o valor a ser transferido: ");

				try {
					contaAppService.transfereValor(contaDebitada, contaCreditada, valor, new GregorianCalendar());
				} catch (ContaNaoEncontradaException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Contas atualizadas com sucesso!");

				break;
			}

			case 8: {
				List<Movimento> movimentos = movimentoAppService.recuperaMovimentos();

				for (Movimento movimento : movimentos) {
					System.out.println('\n' + "Id = " + movimento.getId() + 
							"  Conta = " + movimento.getConta().getId() +
							"  Valor = " + movimento.getValorMasc() +
							"  Data = " + movimento.getDataCriacaoMasc() +
							"  Tipo = " + movimento.getTipo());
				}

				break;
			}

			case 9: {
				continua = false;
				break;
			}

			default:
				System.out.println('\n' + "Op��o inv�lida!");
			}
		}
	}
}
