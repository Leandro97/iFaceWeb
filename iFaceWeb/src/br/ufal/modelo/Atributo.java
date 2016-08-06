package br.ufal.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Atributo {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	private Usuario usuario;
	private String nome;
	private String conteudo;

	public Atributo(Usuario usuario, String conteudo) {
		super();
		this.usuario = usuario;
		this.conteudo = conteudo;
	}

	public Atributo() {

	}

	public Atributo(Usuario usuario, String nome, String conteudo) {
		super();
		this.usuario = usuario;
		this.nome = nome;
		this.conteudo = conteudo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
}
