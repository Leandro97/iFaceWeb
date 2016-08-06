package br.ufal.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

//Não mapeada
@Entity
public class Comunidade {
	@Id
	@Column(unique = true)
	private String nome;
	private String descricao;

	@ManyToOne
	private Usuario dono;

	@OneToMany(mappedBy = "receptor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<MensagemComunidade> mensagensRecebidas;

	@OneToMany(mappedBy = "comunidade", cascade = CascadeType.ALL)
	private List<ComunidadeUsuario> participantes;

	public Comunidade() {
	}

	public Comunidade(String nome, String descricao, Usuario dono) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.dono = dono;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getDono() {
		return dono;
	}

	public void setDono(Usuario dono) {
		this.dono = dono;
	}

	public List<MensagemComunidade> getMensagensRecebidas() {
		return mensagensRecebidas;
	}

	public void setMensagensRecebidas(
			List<MensagemComunidade> mensagensRecebidas) {
		this.mensagensRecebidas = mensagensRecebidas;
	}

	public List<ComunidadeUsuario> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<ComunidadeUsuario> participantes) {
		this.participantes = participantes;
	}
}
