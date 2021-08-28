package modelo;

import java.util.Calendar;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import util.Util;

@NamedQueries({ @NamedQuery(name = "Tarefa.recuperaListaDeTarefas", query = "select t from Tarefa t order by t.id"),
				@NamedQuery(name = "Tarefa.recuperaTarefaCompleta", query = "select t from Tarefa t left outer join fetch t.projeto where t.id = ?1"),
			})

@Entity
@Table(name = "tarefas")

public class Tarefa {
	private Long id;
	private String nome;
	private String descricao;
	private Calendar prazo;
	private int status;
	
	//Tarefa possui chave externa para projeto
	private Projeto projeto;

	// ********* Construtores *********

	public Tarefa(){
	}

	public Tarefa(String nome, String descricao, Calendar prazo, int status){	
		this.nome = nome;
		this.descricao = descricao;
		this.prazo = prazo;
		this.status = status;
	}
	
	public Tarefa(String nome, String descricao, Calendar prazo, int status, Projeto projeto){	
		this.nome = nome;
		this.descricao = descricao;
		this.prazo = prazo;
		this.status = status;
		this.projeto = projeto;
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
		
	@Column(name="descricao")
	public String getDescricao(){	
		return descricao;
	}
		
	@Column(name="prazo")
	@Temporal(TemporalType.DATE)
	public Calendar getPrazo(){	
		return prazo;
	}
	
	@Transient
	public String getPrazoMasc() {
		return Util.calendarToStr(prazo);
	}

	@Column(name="status")
	public int getStatus(){
		return status;
	}
	
	@Transient
	public String getStatusTxt() {
		switch (status) {
		case 0:
			return "A concluir";
		case 1:
			return "Concluido";
		default:
			return "Status Indefinido";
		}
		
	}

	// ********* Métodos do Tipo Set *********

	@SuppressWarnings("unused")
	private void setId(Long id){	
		this.id = id;
	}

	public void setNome(String nome){	
		this.nome = nome;
	}
		
	public void setDescricao(String descricao){	
		this.descricao = descricao;
	}
		
	public void setPrazo(Calendar prazo){	
		this.prazo = prazo;
	}
		
	public void setStatus(int status){	
		this.status = status;
	}
	
	//Métodos para Associações
	//Um projeto pode estar associado a muitas tarefas, uma tarefa esta associado a um projeto
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projeto_id")
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
}
