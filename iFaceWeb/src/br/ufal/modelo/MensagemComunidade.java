package br.ufal.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Mensagem_comunidade")
public class MensagemComunidade implements Serializable {

	private String conteudo;

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	private Usuario emissor;

	@ManyToOne
	private Comunidade receptor;

	public MensagemComunidade() {
	}

	public MensagemComunidade(Usuario emissor, Comunidade receptor,
			String conteudo) {
		this.emissor = emissor;
		this.conteudo = conteudo;
		this.receptor = receptor;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Comunidade getReceptor() {
		return receptor;
	}

	public void setReceptor(Comunidade receptor) {
		this.receptor = receptor;
	}

	public Usuario getEmissor() {
		return emissor;
	}

	public void setEmissor(Usuario emissor) {
		this.emissor = emissor;
	}

}
