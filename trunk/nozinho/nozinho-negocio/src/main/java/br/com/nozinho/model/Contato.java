package br.com.nozinho.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "contato")
@NamedQueries({
	@NamedQuery(name = "Contato.findByNameLike", query = "SELECT c FROM Contato c WHERE c.nome like :nome"),
	@NamedQuery(name = "Contato.findByNameLikeAndType", query = "SELECT c FROM Contato c WHERE c.tipoPessoa = :tipoPessoa AND c.nome like :nome"),
	@NamedQuery(name = "Contato.findByIdFetchAll", query = "SELECT c FROM Contato c LEFT JOIN FETCH c.enderecos LEFT JOIN FETCH  c.contatosForma WHERE c.id = :id"),
	@NamedQuery(name = "Contato.findAllFetchAll", query = "SELECT DISTINCT c FROM Contato c LEFT JOIN FETCH c.enderecos LEFT JOIN FETCH  c.contatosForma")
})
public class Contato extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8770216331187973630L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcontato")
	private Long id;
	
	@Column
	private String nome;
	
	@Column(name = "nome_responsavel")
	private String nomeResponsavel;
	
	@Column(name = "nome_pai")
	private String nomePai;
	
	@Column(name = "nome_mae")
	private String nomeMae;
	
	@Column(name = "nome_conjuge")
	private String nomeConjuge;
	
	@Column(name = "cpf_cnpj")
	private String cpf;
	
	@Column(length = 10)
	private String identidade;
	
	@Column(name = "titulo_eleitor", length = 12)
	private String tituloEleitor;
	
	@Column(length = 4)
	private Long zona;
	
	@Column(length = 4)
	private Long secao;
	
	@Lob
	private byte[] foto;
	
	@Column(name = "data_nascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
	private SexoEnum sexo;
	
	@Column
	private String apelido;
	
	@Column(name = "obs")
	private String observacao;
	
	@Column(name = "tipo_pessoa")
	@Enumerated(EnumType.ORDINAL)
	private TipoPessoaEnum tipoPessoa = TipoPessoaEnum.PESSOA_FISICA;
	
	@ManyToOne
	@JoinColumn(name = "idprofissao")
	private Profissao profissao;

	@ManyToOne
	@JoinColumn(name = "idformacao_intelectual")
	private FormacaoIntelectual formacaoIntelectual;
	
	@ManyToOne
	@JoinColumn(name = "idtratamento")
	private Tratamento tratamento;
	
	@ManyToOne
	@JoinColumn(name = "idregional")
	private Regional regional;
	
	@ManyToOne
	@JoinColumn(name = "idpartido")
	private Partido partido;
	
	@ManyToOne
	@JoinColumn(name = "idcargo")
	private Cargo cargo;
	
	@OneToMany(mappedBy = "contato", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ContatoEndereco> enderecos = new HashSet<ContatoEndereco>();
	
	@OneToMany(mappedBy = "contato", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ContatoForma> contatosForma = new HashSet<ContatoForma>();
	
	public Set<ContatoEndereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<ContatoEndereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public Set<ContatoForma> getContatosForma() {
		return contatosForma;
	}

	public void setContatosForma(Set<ContatoForma> contatosForma) {
		this.contatosForma = contatosForma;
	}

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

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomeConjuge() {
		return nomeConjuge;
	}

	public void setNomeConjuge(String nomeConjuge) {
		this.nomeConjuge = nomeConjuge;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public String getTituloEleitor() {
		return tituloEleitor;
	}

	public void setTituloEleitor(String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}

	public Long getZona() {
		return zona;
	}

	public void setZona(Long zona) {
		this.zona = zona;
	}

	public Long getSecao() {
		return secao;
	}

	public void setSecao(Long secao) {
		this.secao = secao;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoPessoaEnum getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}


	public FormacaoIntelectual getFormacaoIntelectual() {
		return formacaoIntelectual;
	}

	public void setFormacaoIntelectual(FormacaoIntelectual formacaoIntelectual) {
		this.formacaoIntelectual = formacaoIntelectual;
	}

	public Tratamento getTratamento() {
		return tratamento;
	}

	public void setTratamento(Tratamento tratamento) {
		this.tratamento = tratamento;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	
	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public ContatoEndereco getEndereco(){
		ContatoEndereco contatoEndereco = new ContatoEndereco();
		contatoEndereco.setCidade(new Cidade());
		for(ContatoEndereco endereco : enderecos){
			if(endereco.getTipoEndereco().equals(TipoEnderecoEnum.PRINCIPAL)){
				contatoEndereco = endereco;
				break;
			}
			contatoEndereco = endereco;
		}
		return contatoEndereco;
	}

	public ContatoForma getEmail(){
		ContatoForma email = new ContatoForma();
		for(ContatoForma contato : contatosForma){
			if(contato.getTipoContato().equals(FormaContatoEnum.EMAIL)){
				email = contato;
				break;
			}
		}
		return email;
	}
	
	public ContatoForma getTelefone(){
		ContatoForma telefone = new ContatoForma();
		for(ContatoForma contato : contatosForma){
			if(contato.getTipoContato().equals(FormaContatoEnum.TELEFONE)){
				telefone = contato;
			}
		}
		return telefone;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result
				+ ((identidade == null) ? 0 : identidade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((tituloEleitor == null) ? 0 : tituloEleitor.hashCode());
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
		Contato other = (Contato) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (identidade == null) {
			if (other.identidade != null)
				return false;
		} else if (!identidade.equals(other.identidade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tituloEleitor == null) {
			if (other.tituloEleitor != null)
				return false;
		} else if (!tituloEleitor.equals(other.tituloEleitor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contato [id=" + id + ", nome=" + nome + ", cpf=" + cpf + "]";
	}
	
}
