package excecao;

public class FuncionarioNaoEncontradoException extends Exception {
	private final static long serialVersionUID = 1;

	public FuncionarioNaoEncontradoException() {
		super();
	}

	public FuncionarioNaoEncontradoException(String msg) {
		super(msg);
	}
}