package br.com.nozinho.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
@NamedQueries({
	@NamedQuery(name = "Usuario.findByLoginAndSenha", query = "SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha"),
	@NamedQuery(name = "Usuario.findAllExceptAdm", query = "SELECT u FROM Usuario u WHERE u.login <> 'admin'"),
	@NamedQuery(name = "Usuario.findByNomeContatoLike", query = "SELECT u FROM Usuario u JOIN u.contato c WHERE c.nome LIKE :nome")
})
public class Usuario extends Entidade{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5712827972185391777L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idusuario")
	private Long id;
	
	@Column
	private String login;
	
	@Column
	private String senha;
	
	@Column
	private boolean status;
	
	@OneToOne
	@JoinColumn(name = "idcontato")
	private Contato contato;
	
//	@ManyToOne
//	@JoinColumn(columnDefinition = "idempresa")
//	private Empresa empresa;
//	
	@ManyToOne
	@JoinColumn(name = "idperfil")
	private Perfil perfil;
	
	@ManyToOne
	@JoinColumn(name = "idcargo", referencedColumnName = "idcargo")
	private Cargo cargo;
	
	@Lob
	private byte[] foto;

	@Override
	public Serializable getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

//	public Empresa getEmpresa() {
//		return empresa;
//	}
//
//	public void setEmpresa(Empresa empresa) {
//		this.empresa = empresa;
//	}
//
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
//		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		Usuario other = (Usuario) obj;
//		if (empresa == null) {
//			if (other.empresa != null)
//				return false;
//		} else if (!empresa.equals(other.empresa))
//			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha
				+ ", status=" + status + ", contato=" + contato + ", empresa=";
//				+ empresa + ", perfil=" + perfil + ", cargo=" + cargo + "]";
	}

}
