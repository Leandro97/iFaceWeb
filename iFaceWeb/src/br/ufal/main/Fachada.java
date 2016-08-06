package br.ufal.main;

import java.util.List;

import br.ufal.modelo.Amizade;
import br.ufal.modelo.Atributo;
import br.ufal.modelo.Comunidade;
import br.ufal.modelo.ComunidadeUsuario;
import br.ufal.modelo.Usuario;
import br.ufal.persistencia.AtributoPersistencia;
import br.ufal.persistencia.ComunidadePersistencia;
import br.ufal.persistencia.MensagemPersistencia;
import br.ufal.persistencia.UsuarioPersistencia;

public class Fachada {

	private static Fachada instance;

	private Fachada() {

	}

	public static Fachada getInstance() {
		if (instance == null) {
			instance = new Fachada();
		}
		return instance;
	}

	// Bloco de Usuário

	// Persiste um usuário no banco
	public void salvarUsuario(Usuario user) {
		UsuarioPersistencia.getInstance().salvarUsuario(user);
	}

	// Atualiza um usuário
	public void atualizarUsuario(Usuario user) {
		UsuarioPersistencia.getInstance().atualizarUsuario(user);
	}

	// Retorna um usuário ao receber seu id (seu username)
	public Usuario getUsuarioById(String username) {
		return UsuarioPersistencia.getInstance().getUsuarioById(username);
	}

	// Retorna um usuário se a combinação de username e senha estiver cadastrada
	public Usuario login(String username, String senha) {
		return UsuarioPersistencia.getInstance().login(username, senha);
	}

	// Envia pedido de amizade para um usuário
	public void enviarPedidoAmizade(Usuario user1, Usuario user2,
			boolean confirmado) {
		UsuarioPersistencia.getInstance().enviarPedidoAmizade(user1, user2,
				confirmado);
	}

	// Retorna lista de pedidos que ainda não foram aceitos
	public List<Amizade> getPedidosAmizade(Usuario user) {
		return UsuarioPersistencia.getInstance().getPedidosAmizade(user);
	}

	// Aceita pedido de amizade de um determinado usuário
	public void aceitarPedidoAmizade(Amizade am) {
		UsuarioPersistencia.getInstance().aceitarPedido(am);
	}

	// Retorna lista de amigos de usuário
	public List<Usuario> getAmigos(Usuario user) {
		return UsuarioPersistencia.getInstance().getAmigos(user);
	}

	// Retorna lista de amigos de usuário
	public List<Comunidade> getComunidades(Usuario user) {
		return UsuarioPersistencia.getInstance().getComunidades(user);
	}

	// Deleta instância de usuário no banco (Tem que implementar direitinho)
	public void deletarUsuario(Usuario user) {
		UsuarioPersistencia.getInstance().deletarUsuario(user);
	}

	// Fim do bloco de Usuário

	// Bloco de Atributo
	// Persiste um atributo no banco
	public void salvarAtributo(Atributo atributo) {
		AtributoPersistencia.getInstance().salvarAtributo(atributo);
	}

	// Atualiza um atributo
	public void atualizarAtributo(Atributo atributo) {
		AtributoPersistencia.getInstance().atualizarAtributo(atributo);
	}

	// Fim do bloco de Atributo

	// Bloco de Comunidade

	// Persiste uma comunidade no banco
	public void salvarComunidade(Comunidade com) {
		ComunidadePersistencia.getInstance().salvarComunidade(com);
	}

	// Retorna um usuário ao receber seu id (seu nome)
	public Comunidade getComunidadeById(String nome) {
		return ComunidadePersistencia.getInstance().getComunidadeById(nome);
	}

	// Inclui usuário em uma comunidade
	public void incluirMembro(Comunidade com, Usuario user) {
		ComunidadePersistencia.getInstance().incluirMembro(com, user, false);
	}

	// Retorna lista de usuários que ainda não foram aceitos em uma comunidade
	public List<ComunidadeUsuario> getPedidosComunidade(Comunidade com) {
		return ComunidadePersistencia.getInstance().getPedidos(com);
	}

	// Aceita pedido de inclusão na comunidade de um determinado usuário
	public void aceitarPedidoComunidade(ComunidadeUsuario cu) {
		ComunidadePersistencia.getInstance().aceitarPedido(cu);
	}

	// Retorna lista de membros de uma comunidade
	public List<Usuario> getMembros(Comunidade com) {
		return ComunidadePersistencia.getInstance().getMembros(com);
	}

	// Fim do bloco de Comunidade

	// Bloco de Mensagem

	// Envia mensagens de usuário para usuário
	public void mensagemParaComunidade(Usuario emissor, Comunidade receptor,
			String conteudo) {
		MensagemPersistencia.getInstance().mensagemParaComunidade(emissor,
				receptor, conteudo);
	}

	// Envia mensagens de usuário para usuário
	public void mensagemParaUsuario(Usuario emissor, Usuario receptor,
			String conteudo) {
		MensagemPersistencia.getInstance().mensagemParaUsuario(emissor,
				receptor, conteudo);
	}
	// Fim do bloco de Mensagem

}
