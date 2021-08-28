package dao;

import java.util.List;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import excecao.ObjetoNaoEncontradoException;
import excecao.ProjetoNaoEncontradoException;
import modelo.Projeto;

public interface ProjetoDAO extends DaoGenerico<Projeto, Long> {
	@RecuperaLista
	List<Projeto> recuperaListaDeProjetos();
	@RecuperaObjeto
	Projeto recuperaProjetoCompleto(long id) throws ProjetoNaoEncontradoException;
	@RecuperaObjeto
	Projeto recuperaUmProjetoETarefas(long id) throws ObjetoNaoEncontradoException;
	@RecuperaLista
	List<Projeto> recuperaListaDeProjetosETarefas();
}
