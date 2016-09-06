package br.com.nozinho.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "perfil")
@NamedQueries({
	@NamedQuery(name = "Perfil.findPerfisExceptAdm", query = "SELECT p FROM Perfil p WHERE p.nome <> 'ADMINISTRADOR'"),
	@NamedQuery(name = "Perfil.findByIdFetchPermissao", query = "SELECT p from Perfil p LEFT JOIN FETCH p.permissoes WHERE p.id = :id")
})
public class Perfil extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9145368379107176587L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idperfil")
	private Long id;

	@Column(name = "nome_perfil")
	private String nome;
	
	@Column(name = "status")
	private boolean status;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinTable(name = "perfil_permissao", joinColumns = {@JoinColumn(name = "idperfil")}, inverseJoinColumns = {@JoinColumn(name = "idpermissao")})
	private List<Permissao> permissoes;
	
	@Override
	public Serializable getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Permissao> getPermissoes() {
		if(permissoes == null){
			permissoes = new ArrayList<>(); 
		}
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Perfil [id=" + id + ", nome=" + nome + ", status=" + status
				+ "]";
	}
	
}
