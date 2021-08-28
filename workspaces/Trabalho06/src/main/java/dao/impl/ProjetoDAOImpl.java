package dao.impl;

import org.springframework.stereotype.Repository;

import dao.ProjetoDAO;
import modelo.Projeto;

@Repository
public abstract class ProjetoDAOImpl extends JPADaoGenerico<Projeto, Long> implements ProjetoDAO {
	public ProjetoDAOImpl() {
		super(Projeto.class); 
	}
}