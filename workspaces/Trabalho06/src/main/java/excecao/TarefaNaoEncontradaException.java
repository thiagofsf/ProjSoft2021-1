package excecao;

public class TarefaNaoEncontradaException extends Exception {
	private final static long serialVersionUID = 1;

	public TarefaNaoEncontradaException() {
		super();
	}

	public TarefaNaoEncontradaException(String msg) {
		super(msg);
	}
}