package dao.impl;

import org.springframework.stereotype.Repository;

import dao.TarefaDAO;
import modelo.Tarefa;

@Repository
public abstract class TarefaDAOImpl extends JPADaoGenerico<Tarefa, Long> implements TarefaDAO {
	public TarefaDAOImpl() {
		super(Tarefa.class); 
	}
}