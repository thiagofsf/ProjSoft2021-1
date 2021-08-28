package dao;

import java.util.List;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionario;

public interface FuncionarioDAO extends DaoGenerico<Funcionario, Long> {
	@RecuperaLista
	List<Funcionario> recuperaListaDeFuncionarios();
	@RecuperaObjeto
	Funcionario recuperaUmFuncionarioEProjetos(long id) throws ObjetoNaoEncontradoException;
	@RecuperaLista
	List<Funcionario> recuperaListaDeFuncionariosEProjetos();
}
