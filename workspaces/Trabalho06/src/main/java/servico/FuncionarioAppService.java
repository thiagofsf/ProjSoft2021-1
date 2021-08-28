package servico;

import java.util.List;

import excecao.FuncionarioNaoEncontradoException;
import modelo.Funcionario;

public interface FuncionarioAppService {
	long inclui(Funcionario umFuncionario);

	void altera(Funcionario umFuncionario) throws FuncionarioNaoEncontradoException;

	void exclui(Funcionario umFuncionario) throws FuncionarioNaoEncontradoException;

	Funcionario recuperaUmFuncionario(long numero) throws FuncionarioNaoEncontradoException;

	List<Funcionario> recuperaFuncionarios();
	
	Funcionario recuperaUmFuncionarioEProjetos(long id) throws FuncionarioNaoEncontradoException;

	List<Funcionario> recuperaFuncionariosEProjetos();
}