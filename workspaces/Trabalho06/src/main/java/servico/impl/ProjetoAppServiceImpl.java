package servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.ProjetoDAO;
import excecao.ClienteNaoEncontradoException;
import excecao.FuncionarioNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import excecao.ProjetoNaoEncontradoException;
import modelo.Cliente;
import modelo.Funcionario;
import modelo.Projeto;
import servico.ProjetoAppService;

public class ProjetoAppServiceImpl implements ProjetoAppService {

	@Autowired
	private ProjetoDAO projetoDAO;
	@Autowired
	private ClienteDAO clienteDAO;
	@Autowired
	private FuncionarioDAO funcionarioDAO;

	@Transactional
	public long inclui(Projeto umProjeto) throws ClienteNaoEncontradoException, FuncionarioNaoEncontradoException {
		
		Cliente umCliente = umProjeto.getCliente();
		try {
			umCliente = clienteDAO.getPorIdComLock(umCliente.getId());
		}catch(ObjetoNaoEncontradoException e) {
			throw new ClienteNaoEncontradoException("Cliente não Encontrado");
		}
		
		Funcionario umFuncionario = umProjeto.getFuncionario();
		try {
			umFuncionario = funcionarioDAO.getPorIdComLock(umFuncionario.getId());
		}catch(ObjetoNaoEncontradoException e) {
			throw new FuncionarioNaoEncontradoException("Funcionário não Encontrado");
		}
		
		projetoDAO.inclui(umProjeto);
		return umProjeto.getId();
	}

	@Transactional
	public void altera(Projeto umProjeto) throws ProjetoNaoEncontradoException {
		try {
			projetoDAO.getPorIdComLock(umProjeto.getId());
			projetoDAO.altera(umProjeto);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ProjetoNaoEncontradoException("Projeto não encontrado");
		}
	}

	@Transactional
	public void exclui(Projeto umProjeto) throws ProjetoNaoEncontradoException {
		try {
			Projeto projeto = projetoDAO.recuperaUmProjetoETarefas(umProjeto.getId());
			if(projeto.getTarefas().size()>0) {
				throw new ProjetoNaoEncontradoException("Este projeto possui tarefas associadas e não pode ser removido");
			}
			projetoDAO.exclui(umProjeto);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ProjetoNaoEncontradoException("Projeto não encontrado");
		}
	}

	public Projeto recuperaUmProjeto(long numero) throws ProjetoNaoEncontradoException {
		try {
			return projetoDAO.getPorId(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ProjetoNaoEncontradoException("Projeto não encontrado");
		}
	}

	public List<Projeto> recuperaProjetos() {
		return projetoDAO.recuperaListaDeProjetos();
	}
	
	public Projeto recuperaProjetoCompleto(long numero) throws ProjetoNaoEncontradoException {
		try {
			Projeto projeto = projetoDAO.getPorId(numero);
			return projetoDAO.recuperaProjetoCompleto(projeto.getId());
		} catch (ObjetoNaoEncontradoException e) {
			throw new ProjetoNaoEncontradoException("Projeto não encontrado");
		}
	}
	
	public Projeto recuperaUmProjetoETarefas(long numero) throws ProjetoNaoEncontradoException {
		try {
			return projetoDAO.recuperaUmProjetoETarefas(numero);
		}catch(ObjetoNaoEncontradoException e) {
			throw new ProjetoNaoEncontradoException("Projeto não Encontrado");
		}
	}

	public List<Projeto> recuperaProjetosETarefas(){
		return projetoDAO.recuperaListaDeProjetosETarefas();
	}
}