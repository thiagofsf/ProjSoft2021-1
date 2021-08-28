package dao;

import java.io.Serializable;

import excecao.ObjetoNaoEncontradoException;

/**
 * A interface GenericDao b�sica com os m�todos CRUD. Os m�todos de busca s�o
 * adicionados por heran�a de interface.
 */
public interface DaoGenerico<T, PK extends Serializable> {
	T inclui(T obj);

	void altera(T obj);

	void exclui(T obj);

	T getPorId(PK id) throws ObjetoNaoEncontradoException;

	T getPorIdComLock(PK id) throws ObjetoNaoEncontradoException;
}
