package servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.FuncionarioDAO;
import excecao.FuncionarioNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionario;
import servico.FuncionarioAppService;

public class FuncionarioAppServiceImpl implements FuncionarioAppService {

	@Autowired
	private FuncionarioDAO funcionarioDAO;

	@Transactional
	public long inclui(Funcionario umFuncionario) {
		funcionarioDAO.inclui(umFuncionario);
		return umFuncionario.getId();
	}

	@Transactional
	public void altera(Funcionario umFuncionario) throws FuncionarioNaoEncontradoException {
		try {
			funcionarioDAO.getPorIdComLock(umFuncionario.getId());
			funcionarioDAO.altera(umFuncionario);
		} catch (ObjetoNaoEncontradoException e) {
			throw new FuncionarioNaoEncontradoException("Funcion�rio n�o encontrado");
		}
	}

	@Transactional
	public void exclui(Funcionario umFuncionario) throws FuncionarioNaoEncontradoException {
		try {
			Funcionario funcionario = funcionarioDAO.recuperaUmFuncionarioEProjetos(umFuncionario.getId());
			if(funcionario.getProjetos().size()>0) {
				throw new FuncionarioNaoEncontradoException("Este funcionario � respons�vel por um ou mais projetos e n�o pode ser removido");
			}
			funcionarioDAO.exclui(umFuncionario);
		} catch (ObjetoNaoEncontradoException e) {
			throw new FuncionarioNaoEncontradoException("Funcion�rio n�o encontrado");
		}
	}

	public Funcionario recuperaUmFuncionario(long numero) throws FuncionarioNaoEncontradoException {
		try {
			return funcionarioDAO.getPorId(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new FuncionarioNaoEncontradoException("Funcion�rio n�o encontrado");
		}
	}

	public List<Funcionario> recuperaFuncionarios() {
		return funcionarioDAO.recuperaListaDeFuncionarios();
	}
	
	public Funcionario recuperaUmFuncionarioEProjetos(long numero) throws FuncionarioNaoEncontradoException {
		try {
			return funcionarioDAO.recuperaUmFuncionarioEProjetos(numero);
		}catch(ObjetoNaoEncontradoException e) {
			throw new FuncionarioNaoEncontradoException("Funcionario n�o Encontrado");
		}
	}

	public List<Funcionario> recuperaFuncionariosEProjetos(){
		return funcionarioDAO.recuperaListaDeFuncionariosEProjetos();
	}
}