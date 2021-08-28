package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import util.Util;

@NamedQueries({ @NamedQuery(name = "Projeto.recuperaListaDeProjetos", query = "select p from Projeto p order by p.id"),
				@NamedQuery(name = "Projeto.recuperaUmProjetoComCliente", query = "select p from Projeto p left outer join fetch p.cliente where p.id = ?1"),
				@NamedQuery(name = "Projeto.recuperaProjetoscomCliente", query = "select p from Projeto p left outer join fetch p.funcionario where p.id = ?1"),
				@NamedQuery(name = "Projeto.recuperaProjetoCompleto", query = "select p from Projeto p left outer join fetch p.cliente left outer join fetch p.funcionario where p.id = ?1"),
				@NamedQuery(name = "Projeto.recuperaUmProjetoETarefas", query = "select p from Projeto p left outer join fetch p.tarefas where p.id = ?1"),
				@NamedQuery(name = "Projeto.recuperaListaDeProjetosETarefas", query = "select distinct p from Projeto p left outer join fetch p.tarefas order by p.id asc"),
			})

@Entity
@Table(name = "projetos")

public class Projeto {
	private Long id;
	private String nome;
	private Calendar dataini;
	private Calendar datafin;
	private double valor;
	
	//Projeto possui chave externa para cliente
	private Cliente cliente;
	//Projeto possui chave externa para Funcionario responsavel
	private Funcionario funcionario;
	//Um projeto possui varias tarefas
	List<Tarefa> tarefas = new ArrayList<Tarefa>();

	// ********* Construtores *********

	public Projeto(){
	}

	public Projeto(String nome, Calendar dataini, Calendar datafin, double valor){	
		this.nome = nome;
		this.dataini = dataini;
		this.datafin = datafin;
		this.valor = valor;
	}
	
	public Projeto(String nome, Calendar dataini, Calendar datafin, double valor, Cliente cliente, Funcionario funcionario){	
		this.nome = nome;
		this.dataini = dataini;
		this.datafin = datafin;
		this.valor = valor;
		this.cliente = cliente;
		this.funcionario = funcionario;
	}
	
	// ********* Métodos do Tipo Get *********

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	public Long getId(){	
		return id;
	}
		
	@Column(name="nome")
	public String getNome(){	
		return nome;
	}
		
	@Column(name="dataini")
	@Temporal(TemporalType.DATE)
	public Calendar getDataini(){	
		return dataini;
	}
	
	@Transient
	public String getDataIniMasc() {
		return Util.calendarToStr(dataini);
	}
		
	@Column(name="datafin")
	@Temporal(TemporalType.DATE)
	public Calendar getDatafin(){	
		return datafin;
	}
	
	@Transient
	public String getDataFinMasc() {
		return Util.calendarToStr(datafin);
	}

	@Column(name="valor")
	public double getValor(){
		return valor;
	}

	// ********* Métodos do Tipo Set *********

	@SuppressWarnings("unused")
	private void setId(Long id){	
		this.id = id;
	}

	public void setNome(String nome){	
		this.nome = nome;
	}
		
	public void setDataini(Calendar dataini){	
		this.dataini = dataini;
	}
		
	public void setDatafin(Calendar datafin){	
		this.datafin = datafin;
	}
		
	public void setValor(double valor){	
		this.valor = valor;
	}
	
	//Métodos para Associações
	//Um cliente pode estar associado a muitos projetos, um projeto esta associado a um cliente
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	//Um Funcionario pode estar associado a muitos projetos, um projeto esta associado a um funcionario
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "funcionario_id")
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	//Um cliente pode estar associado a muitos projetos, um projeto esta associado a um cliente
	@OneToMany(mappedBy = "projeto")
	public List <Tarefa> getTarefas() {
		return tarefas;
	}
	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
}
