package dao.impl;

import org.springframework.stereotype.Repository;

import dao.FuncionarioDAO;
import modelo.Funcionario;

@Repository
public abstract class FuncionarioDAOImpl extends JPADaoGenerico<Funcionario, Long> implements FuncionarioDAO {
	public FuncionarioDAOImpl() {
		super(Funcionario.class);
	}
}