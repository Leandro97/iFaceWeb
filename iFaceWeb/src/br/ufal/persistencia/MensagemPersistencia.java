package br.ufal.persistencia;

import br.ufal.modelo.Comunidade;
import br.ufal.modelo.MensagemComunidade;
import br.ufal.modelo.MensagemUsuario;
import br.ufal.modelo.Usuario;

public class MensagemPersistencia extends Persistencia {

	private static MensagemPersistencia instance;

	private MensagemPersistencia() {

	}

	public static MensagemPersistencia getInstance() {
		if (instance == null) {
			instance = new MensagemPersistencia();
		}

		return instance;
	}

	// Envia mensagens de usuário para usuário
	public void mensagemParaUsuario(Usuario emissor, Usuario receptor,
			String conteudo) {
		MensagemUsuario msg = new MensagemUsuario(conteudo, emissor, receptor);
		manager = factory.createEntityManager();
		try {
			manager.getTransaction().begin();
			manager.persist(msg);
			manager.getTransaction().commit();
		} finally {
			manager.close();
		}
	}

	// Envia mensagens de usuário para usuário
	public void mensagemParaComunidade(Usuario emissor, Comunidade receptor,
			String conteudo) {
		MensagemComunidade msg = new MensagemComunidade(emissor, receptor,
				conteudo);
		manager = factory.createEntityManager();
		try {
			manager.getTransaction().begin();
			manager.persist(msg);
			manager.getTransaction().commit();
		} finally {
			manager.close();
		}
	}
}
