package dao;

import java.util.List;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import excecao.ObjetoNaoEncontradoException;
import modelo.Cliente;

public interface ClienteDAO extends DaoGenerico<Cliente, Long> {
	@RecuperaLista
	List<Cliente> recuperaListaDeClientes();
	@RecuperaObjeto
	Cliente recuperaUmClienteEProjetos(long id) throws ObjetoNaoEncontradoException;
	@RecuperaLista
	List<Cliente> recuperaListaDeClientesEProjetos();
}
