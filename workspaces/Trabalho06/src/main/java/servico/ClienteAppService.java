package servico;

import java.util.List;

import excecao.ClienteNaoEncontradoException;
import modelo.Cliente;

public interface ClienteAppService {
	long inclui(Cliente umCliente);

	void altera(Cliente umCliente) throws ClienteNaoEncontradoException;

	void exclui(Cliente umCliente) throws ClienteNaoEncontradoException;

	Cliente recuperaUmCliente(long numero) throws ClienteNaoEncontradoException;

	List<Cliente> recuperaClientes();
	
	Cliente recuperaUmClienteEProjetos(long id) throws ClienteNaoEncontradoException;

	List<Cliente> recuperaClientesEProjetos();
}