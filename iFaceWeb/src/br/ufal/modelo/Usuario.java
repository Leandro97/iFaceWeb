package br.ufal.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

	private String nome;
	@Id
	@Column(unique = true)
	private String username;
	private String senha;

	@OneToMany(mappedBy = "receptor", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<MensagemUsuario> mensagensURecebidas;

	@OneToMany(mappedBy = "emissor", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<MensagemUsuario> mensagensUEnviadas;

	@OneToMany(mappedBy = "emissor", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<MensagemComunidade> mensagensCEnviadas;

	@OneToMany(mappedBy = "dono", cascade = CascadeType.REMOVE)
	private List<Comunidade> comunidadesQuePossuo;

	@OneToMany(mappedBy = "participante", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<ComunidadeUsuario> comunidadesQueParticipo;

	@OneToMany(mappedBy = "solicitante", cascade = CascadeType.REMOVE)
	private List<Amizade> pedidosEnviados;

	@OneToMany(mappedBy = "solicitado", cascade = CascadeType.REMOVE)
	private List<Amizade> pedidosRecebidos;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Atributo> atributos;

	public Usuario() {
	}

	public Usuario(String nome, String username, String senha) {
		super();
		this.nome = nome;
		this.username = username;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<MensagemUsuario> getMensagensURecebidas() {
		return mensagensURecebidas;
	}

	public void setMensagensURecebidas(List<MensagemUsuario> mensagensURecebidas) {
		this.mensagensURecebidas = mensagensURecebidas;
	}

	public List<MensagemUsuario> getMensagensUEnviadas() {
		return mensagensUEnviadas;
	}

	public void setMensagensUEnviadas(List<MensagemUsuario> mensagensUEnviadas) {
		this.mensagensUEnviadas = mensagensUEnviadas;
	}

	public List<MensagemComunidade> getMensagensCEnviadas() {
		return mensagensCEnviadas;
	}

	public void setMensagensCEnviadas(
			List<MensagemComunidade> mensagensCEnviadas) {
		this.mensagensCEnviadas = mensagensCEnviadas;
	}

	public List<Comunidade> getComunidadesQuePossuo() {
		return comunidadesQuePossuo;
	}

	public void setComunidadesQuePossuo(List<Comunidade> comunidadesQuePossuo) {
		this.comunidadesQuePossuo = comunidadesQuePossuo;
	}

	public List<ComunidadeUsuario> getComunidadesQueParticipo() {
		return comunidadesQueParticipo;
	}

	public void setComunidadesQueParticipo(
			List<ComunidadeUsuario> comunidadesQueParticipo) {
		this.comunidadesQueParticipo = comunidadesQueParticipo;
	}

	public List<Amizade> getPedidosEnviados() {
		return pedidosEnviados;
	}

	public void setPedidosEnviados(List<Amizade> pedidosEnviados) {
		this.pedidosEnviados = pedidosEnviados;
	}

	public List<Amizade> getPedidosRecebidos() {
		return pedidosRecebidos;
	}

	public void setPedidosRecebidos(List<Amizade> pedidosRecebidos) {
		this.pedidosRecebidos = pedidosRecebidos;
	}

	public List<Atributo> getAtributos() {
		return atributos;
	}

	public void setAtributos(List<Atributo> atributos) {
		this.atributos = atributos;
	}
}
