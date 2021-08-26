package projeto;

//import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
//import javax.persistence.Transient;


@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="clientes")

public class Cliente{	
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String endereco;
	
	private int versao;

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
	
	@Version
	public int getVersao() {
		return versao;
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
	
	public void setVersao(int versao) {
		this.versao = versao;
	}
}

