package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = "Funcionario.recuperaListaDeFuncionarios", query = "select f from Funcionario f order by f.id"),
	@NamedQuery(name = "Funcionario.recuperaUmFuncionarioEProjetos", query = "select f from Funcionario f left outer join fetch f.projetos where f.id = ?1"),
	@NamedQuery(name = "Funcionario.recuperaListaDeFuncionariosEProjetos", query = "select distinct f from Funcionario f left outer join fetch f.projetos order by f.id asc"),
	})

@Entity
@Table(name = "funcionarios")

public class Funcionario {
	private Long id;
	private String nome;
	private String usuario;
	private String senha;
	
	//Um Funcionario está associado a Projetos
	private List<Projeto> projetos = new ArrayList<Projeto>();

	// ********* Construtores *********

	public Funcionario(){
	}

	public Funcionario(String nome, String usuario, String senha){	
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
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
		
	@Column(name="usuario")
	public String getUsuario(){	
		return usuario;
	}
		
	@Column(name="senha")
	public String getSenha(){	
		return senha;
	}

	// ********* Métodos do Tipo Set *********

	@SuppressWarnings("unused")
	private void setId(Long id){	
		this.id = id;
	}

	public void setNome(String nome){	
		this.nome = nome;
	}
		
	public void setUsuario(String usuario){	
		this.usuario = usuario;
	}
		
	public void setSenha(String senha){	
		this.senha = senha;	
	}
	//Métodos para Associações
	//Um cliente pode estar associado a muitos projetos, um projeto esta associado a um cliente
	@OneToMany(mappedBy = "funcionario")
	public List <Projeto> getProjetos() {
		return projetos;
	}
	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
	
}
