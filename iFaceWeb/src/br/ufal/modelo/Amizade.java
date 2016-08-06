package br.ufal.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Amizade implements Serializable {

	@Id
	@ManyToOne
	private Usuario solicitante;

	@Id
	@ManyToOne
	private Usuario solicitado;
	boolean confirmado;

	public Amizade() {
	}

	public Amizade(Usuario solicitante, Usuario solicitado, boolean confirmado) {
		this.solicitante = solicitante;
		this.solicitado = solicitado;
		this.confirmado = confirmado;
	}

	public Usuario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}

	public Usuario getSolicitado() {
		return solicitado;
	}

	public void setSolicitado(Usuario solicitado) {
		this.solicitado = solicitado;
	}

	public boolean isConfirmado() {
		return confirmado;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}
}
