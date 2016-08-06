package br.ufal.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Comunidade_Usuario")
public class ComunidadeUsuario implements Serializable {

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "comunidade_id")
	@Id
	private Comunidade comunidade;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "participante_id")
	@Id
	private Usuario participante;

	private boolean confirmado;

	public ComunidadeUsuario() {

	}

	public ComunidadeUsuario(Comunidade comunidade, Usuario participante,
			boolean confirmado) {
		this.comunidade = comunidade;
		this.participante = participante;
		this.confirmado = confirmado;
	}

	public Comunidade getComunidade() {
		return comunidade;
	}

	public void setComunidade(Comunidade comunidade) {
		this.comunidade = comunidade;
	}

	public Usuario getParticipante() {
		return participante;
	}

	public void setParticipante(Usuario participante) {
		this.participante = participante;
	}

	public boolean isConfirmado() {
		return confirmado;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}

}
