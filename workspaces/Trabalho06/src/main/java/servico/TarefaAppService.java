package servico;

import java.util.List;

import excecao.ProjetoNaoEncontradoException;
import excecao.TarefaNaoEncontradaException;
import modelo.Tarefa;

public interface TarefaAppService {
	long inclui(Tarefa umaTarefa) throws ProjetoNaoEncontradoException;

	void altera(Tarefa umaTarefa) throws TarefaNaoEncontradaException;

	void exclui(Tarefa umaTarefa) throws TarefaNaoEncontradaException;

	Tarefa recuperaUmaTarefa(long numero) throws TarefaNaoEncontradaException;

	List<Tarefa> recuperaTarefas();
	
	Tarefa recuperaTarefaCompleta(long numero) throws TarefaNaoEncontradaException;

}