package servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.ProdutoDAO;
import excecao.ObjetoNaoEncontradoException;
import excecao.ProdutoNaoEncontradoException;
import modelo.Produto;
import servico.ProdutoAppService;

public class ProdutoAppServiceImpl implements ProdutoAppService {

	@Autowired
	private ProdutoDAO produtoDAO;

	@Transactional
	public long inclui(Produto umProduto) {
		return produtoDAO.inclui(umProduto);
	}

	@Transactional(rollbackFor = { ProdutoNaoEncontradoException.class })
	public void altera(Produto umProduto) throws ProdutoNaoEncontradoException {
		try {
			produtoDAO.altera(umProduto);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ProdutoNaoEncontradoException("Produto n�o encontrado");
		}
	}

	@Transactional
	public void exclui(long numero) throws ProdutoNaoEncontradoException {
		try {
			produtoDAO.exclui(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ProdutoNaoEncontradoException("Produto n�o encontrado");
		}
	}

	public Produto recuperaUmProduto(long numero) throws ProdutoNaoEncontradoException {
		try {
			return produtoDAO.recuperaUmProduto(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ProdutoNaoEncontradoException("Produto n�o encontrado");
		}
	}

	public List<Produto> recuperaProdutos() {
		List<Produto> produtos = produtoDAO.recuperaProdutos();

		return produtos;
	}
}