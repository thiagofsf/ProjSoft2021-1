
import corejava.Console;

public class Principal {
	public static void main(String[] args) {

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Gerenciamento de Projetos");
			System.out.println("2. Gerenciamento de Tarefas");
			System.out.println("3. Gerenciar Clientes");
			System.out.println("4. Gerenciamento de Funcionarios");
			System.out.println("0. Sair");

			int opcao = Console.readInt('\n' + "Digite um número da opção desejada:");

			switch (opcao) {
			case 1: {
				PrincipalProjeto programa = new PrincipalProjeto();
				programa.projetos(args);
				break;
			}

			case 2: {
				PrincipalTarefa programa = new PrincipalTarefa();
				programa.tarefas(args);
				break;
			}

			case 3: {
				PrincipalCliente programa = new PrincipalCliente();
				programa.clientes(args);
				break;
			}

			case 4: {
				PrincipalFuncionario programa = new PrincipalFuncionario();
				programa.funcionarios(args);
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
