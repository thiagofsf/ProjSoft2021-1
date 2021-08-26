package modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import util.Util;

@Entity
@Table(name = "conta")
//@SequenceGenerator(name="SEQUENCIA", 
//		           sequenceName="SEQ_PRODUTO",
//		           allocationSize=1)

public class Conta {
	private Long id;
	private double saldo;
	private Date dataCadastro;

	private List<Movimento> movimentos = new ArrayList<Movimento>();

	// ********* Construtores *********

	public Conta() {
	}

	public Conta(double saldo, Date dataCadastro) {
		this.saldo = saldo;
		this.dataCadastro = dataCadastro;
	}

	// ********* M�todos do Tipo Get *********

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public double getSaldo() {
		return saldo;
	}

	@Transient
	public String getSaldoMasc() {
		return Util.doubleToStr(saldo);
	}

	@Column(name = "DATA_CADASTRO")
	public Date getDataCadastro() {
		return dataCadastro;
	}

	@Transient
	public String getDataCadastroMasc() {
		return Util.dateToStr(dataCadastro);
	}

	// ********* M�todos do Tipo Set *********

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void debita(double valor) {
		this.saldo = this.saldo - valor;
	}
	
	public void credita(double valor) {
		this.saldo = this.saldo + valor;
	}
	
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	// ********************    Associa��es    ********************
	
	/*
	 * Sem a utiliza��o do atributo mappedBy a JPA ir� procurar pela tabela
	 * PRODUTO_LANCE. Com ele, ao se tentar recuperar um produto e todos os 
	 * seus  lances, o  join  de  PRODUTO e LANCE  ir� acontecer atrav�s da 
	 * chave estrangeira existente em LANCE. 
	 */
	@OneToMany(mappedBy = "conta", fetch=FetchType.LAZY)
	@OrderBy
	public List<Movimento> getMovimentos() {
		return movimentos;
	}

	public void setMovimentos(List<Movimento> movimentos) {
		this.movimentos = movimentos;
	}
	
}
