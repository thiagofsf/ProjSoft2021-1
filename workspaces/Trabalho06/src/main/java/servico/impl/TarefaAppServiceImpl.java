package servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.ProjetoDAO;
import dao.TarefaDAO;
import excecao.ObjetoNaoEncontradoException;
import excecao.ProjetoNaoEncontradoException;
import excecao.TarefaNaoEncontradaException;
import modelo.Projeto;
import modelo.Tarefa;
import servico.TarefaAppService;

public class TarefaAppServiceImpl implements TarefaAppService {

	@Autowired
	private TarefaDAO tarefaDAO;
	@Autowired
	private ProjetoDAO projetoDAO;

	@Transactional
	public long inclui(Tarefa umaTarefa) throws ProjetoNaoEncontradoException {

		Projeto umProjeto = umaTarefa.getProjeto();
		try {
			umProjeto = projetoDAO.getPorIdComLock(umProjeto.getId());
		} catch (ObjetoNaoEncontradoException e) {
			throw new ProjetoNaoEncontradoException("Projeto não Encontrado");
		}

		tarefaDAO.inclui(umaTarefa);
		return umaTarefa.getId();
	}

	@Transactional
	public void altera(Tarefa umaTarefa) throws TarefaNaoEncontradaException {
		try {
			tarefaDAO.getPorIdComLock(umaTarefa.getId());
			tarefaDAO.altera(umaTarefa);
		} catch (ObjetoNaoEncontradoException e) {
			throw new TarefaNaoEncontradaException("Tarefa não encontrada");
		}
	}

	@Transactional
	public void exclui(Tarefa umaTarefa) throws TarefaNaoEncontradaException {
		try {
			tarefaDAO.getPorIdComLock(umaTarefa.getId());
			tarefaDAO.exclui(umaTarefa);
		} catch (ObjetoNaoEncontradoException e) {
			throw new TarefaNaoEncontradaException("Tarefa não encontrada");
		}
	}

	public Tarefa recuperaUmaTarefa(long numero) throws TarefaNaoEncontradaException {
		try {
			return tarefaDAO.getPorId(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new TarefaNaoEncontradaException("Tarefa não encontrada");
		}
	}

	public List<Tarefa> recuperaTarefas() {
		return tarefaDAO.recuperaListaDeTarefas();
	}

	public Tarefa recuperaTarefaCompleta(long numero) throws TarefaNaoEncontradaException {
		try {
			Tarefa tarefa = tarefaDAO.getPorId(numero);
			return tarefaDAO.recuperaTarefaCompleta(tarefa.getId());
		} catch (ObjetoNaoEncontradoException e) {
			throw new TarefaNaoEncontradaException("Tarefa não encontrada");
		}
	}
}