package br.ufal.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Mensagem_usuario")
public class MensagemUsuario {

	@Id
	@GeneratedValue
	private int id;
	private String conteudo;

	@ManyToOne
	private Usuario emissor;

	@ManyToOne
	private Usuario receptor;

	public MensagemUsuario() {
	}

	public MensagemUsuario(String conteudo, Usuario emissor, Usuario receptor) {
		this.emissor = emissor;
		this.conteudo = conteudo;
		this.receptor = receptor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Usuario getReceptor() {
		return receptor;
	}

	public void setReceptor(Usuario receptor) {
		this.receptor = receptor;
	}

	public Usuario getEmissor() {
		return emissor;
	}

	public void setEmissor(Usuario emissor) {
		this.emissor = emissor;
	}

}
