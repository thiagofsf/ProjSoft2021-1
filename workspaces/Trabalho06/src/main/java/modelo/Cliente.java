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

@NamedQueries({ @NamedQuery(name = "Cliente.recuperaListaDeClientes", query = "select c from Cliente c order by c.id"),
	@NamedQuery(name = "Cliente.recuperaUmClienteEProjetos", query = "select c from Cliente c left outer join fetch c.projetos where c.id = ?1"),
	@NamedQuery(name = "Cliente.recuperaListaDeClientesEProjetos", query = "select distinct c from Cliente c left outer join fetch c.projetos order by c.id asc"),
	})

@Entity
@Table(name = "clientes")

public class Cliente {
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String endereco;

	//Um Cliente está associado a Projetos
	private List<Projeto> projetos = new ArrayList<Projeto>();
	
	// ********* Construtores *********

	public Cliente(){
	}

	public Cliente(String nome, String email, String telefone, String endereco){	
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
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
		
	@Column(name="email")
	public String getEmail(){	
		return email;
	}
		
	@Column(name="telefone")
	public String getTelefone(){	
		return telefone;
	}

	@Column(name="endereco")
	public String getEndereco(){
		return endereco;
	}

	// ********* Métodos do Tipo Set *********

	@SuppressWarnings("unused")
	private void setId(Long id){	
		this.id = id;
	}

	public void setNome(String nome){	
		this.nome = nome;
	}
		
	public void setEmail(String email){	
		this.email = email;
	}
		
	public void setTelefone(String telefone){	
		this.telefone = telefone;	
	}
		
	public void setEndereco(String endereco){	
		this.endereco = endereco;
	}
	
	//Métodos para Associações
	//Um cliente pode estar associado a muitos projetos, um projeto esta associado a um cliente
	@OneToMany(mappedBy = "cliente")
	public List <Projeto> getProjetos() {
		return projetos;
	}
	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
}
