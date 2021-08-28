package excecao;

public class ProjetoNaoEncontradoException extends Exception {
	private final static long serialVersionUID = 1;

	public ProjetoNaoEncontradoException() {
		super();
	}

	public ProjetoNaoEncontradoException(String msg) {
		super(msg);
	}
}