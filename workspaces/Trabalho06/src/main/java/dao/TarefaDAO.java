package dao;

import java.util.List;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import modelo.Tarefa;

public interface TarefaDAO extends DaoGenerico<Tarefa, Long> {
	@RecuperaLista
	List<Tarefa> recuperaListaDeTarefas();
	@RecuperaObjeto
	Tarefa recuperaTarefaCompleta(long numero);
}
