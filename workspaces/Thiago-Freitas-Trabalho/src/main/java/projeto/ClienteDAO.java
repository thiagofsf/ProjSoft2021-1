package projeto;

import java.util.List;


public interface ClienteDAO
{	
	long inclui(Cliente umCliente); 

	void altera(Cliente umCliente)
		throws ClienteNaoEncontradoException; 
	
	void exclui(long id) 
		throws ClienteNaoEncontradoException; 
	
	Cliente recuperaUmCliente(long numero) 
		throws ClienteNaoEncontradoException; 
	
	List<Cliente> recuperaClientes();
}