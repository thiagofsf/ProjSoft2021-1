package servico;

import java.util.List;

import excecao.ClienteNaoEncontradoException;
import excecao.FuncionarioNaoEncontradoException;
import excecao.ProjetoNaoEncontradoException;
import modelo.Projeto;

public interface ProjetoAppService {
	long inclui(Projeto umProjeto) throws ClienteNaoEncontradoException, FuncionarioNaoEncontradoException;

	void altera(Projeto umProjeto) throws ProjetoNaoEncontradoException;

	void exclui(Projeto umProjeto) throws ProjetoNaoEncontradoException;

	Projeto recuperaUmProjeto(long numero) throws ProjetoNaoEncontradoException;

	List<Projeto> recuperaProjetos();
	
	Projeto recuperaProjetoCompleto(long numero) throws ProjetoNaoEncontradoException;
	
	Projeto recuperaUmProjetoETarefas(long id) throws ProjetoNaoEncontradoException;

	List<Projeto> recuperaProjetosETarefas();
}