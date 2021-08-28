package servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.ClienteDAO;
import excecao.ClienteNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Cliente;
import servico.ClienteAppService;

public class ClienteAppServiceImpl implements ClienteAppService {

	@Autowired
	private ClienteDAO clienteDAO;

	@Transactional
	public long inclui(Cliente umCliente) {
		clienteDAO.inclui(umCliente);
		return umCliente.getId();
	}

	@Transactional
	public void altera(Cliente umCliente) throws ClienteNaoEncontradoException {
		try {
			clienteDAO.getPorIdComLock(umCliente.getId());
			clienteDAO.altera(umCliente);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ClienteNaoEncontradoException("Cliente não encontrado");
		}
	}

	@Transactional
	public void exclui(Cliente umCliente) throws ClienteNaoEncontradoException {
		try {
			Cliente cliente = clienteDAO.recuperaUmClienteEProjetos(umCliente.getId());
			if(cliente.getProjetos().size()>0) {
				throw new ClienteNaoEncontradoException("Este cliente possui projetos associados e não pode ser removido");
			}
			clienteDAO.exclui(umCliente);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ClienteNaoEncontradoException("Cliente não encontrado");
		}
	}

	public Cliente recuperaUmCliente(long numero) throws ClienteNaoEncontradoException {
		try {
			return clienteDAO.getPorId(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ClienteNaoEncontradoException("Cliente não encontrado");
		}
	}

	public List<Cliente> recuperaClientes() {
		return clienteDAO.recuperaListaDeClientes();
	}
	
	public Cliente recuperaUmClienteEProjetos(long numero) throws ClienteNaoEncontradoException {
		try {
			return clienteDAO.recuperaUmClienteEProjetos(numero);
		}catch(ObjetoNaoEncontradoException e) {
			throw new ClienteNaoEncontradoException("Cliente não Encontrado");
		}
	}

	public List<Cliente> recuperaClientesEProjetos(){
		return clienteDAO.recuperaListaDeClientesEProjetos();
	}
}