package br.ufal.persistencia;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;

import br.ufal.modelo.Amizade;
import br.ufal.modelo.Comunidade;
import br.ufal.modelo.MensagemUsuario;
import br.ufal.modelo.Usuario;

public class UsuarioPersistencia extends Persistencia {

	private static UsuarioPersistencia instance;

	private UsuarioPersistencia() {

	}

	public static UsuarioPersistencia getInstance() {
		if (instance == null) {
			instance = new UsuarioPersistencia();
		}
		return instance;
	}

	// Retorna lista de todos os usu�rios cadastrados
	public List<Usuario> getAllUsers(Usuario user) {
		manager = factory.createEntityManager();
		List<Usuario> users = null;
		try {
			users = (List<Usuario>) manager.createQuery("SELECT u FROM Usuario u where u != :user")
					.setParameter("user", user).getResultList();
			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
		return users;
	}

	// Persiste um usu�rio no banco
	public void salvarUsuario(Usuario user) throws PersistenceException {
		manager = factory.createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.persist(user);
			manager.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}

	// Atualiza um usu�rio
	public void atualizarUsuario(Usuario user) {
		manager = factory.createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.merge(user);
			manager.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}

	// Retorna um usu�rio ao receber seu id (seu username)
	public Usuario getUsuarioById(String username) {
		Usuario user = null;
		manager = factory.createEntityManager();

		try {
			user = manager.find(Usuario.class, username);
			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}

		return user;
	}

	// Retorna um usu�rio se a combina��o de username e senha estiver cadastrada
	public Usuario login(String username, String senha) {
		manager = factory.createEntityManager();
		Usuario user = null;
		try {
			user = (Usuario) manager
					.createQuery("SELECT u FROM Usuario u WHERE u.username = :username AND u.senha = :senha")
					.setParameter("username", username).setParameter("senha", senha).getResultList().get(0);
			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} catch (IndexOutOfBoundsException e) {
			return null;
		}

		return user;
	}

	// Envia pedido de amizade para um usu�rio. Retorna true se o pedido pode
	// ser feito
	public boolean enviarPedidoAmizade(Usuario solicitante, Usuario solicitado, boolean confirmado) {
		manager = factory.createEntityManager();
		boolean pode = false;
		Amizade amizade = new Amizade(solicitante, solicitado, confirmado);
		try {
			List<Amizade> pedido = (List<Amizade>) manager
					.createQuery("SELECT am FROM Amizade am" + " WHERE (am.solicitado = :solicitado"
							+ " AND am.solicitante = :solicitante)" + " OR ((am.solicitado = :solicitante AND "
							+ "am.solicitante = :solicitado))")
					.setParameter("solicitado", solicitado).setParameter("solicitante", solicitante).getResultList();

			if (pedido.size() == 0) {
				manager.getTransaction().begin();
				manager.persist(amizade);
				manager.getTransaction().commit();
				manager.close();
				pode = true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
		return pode;
	}

	// Retorna lista de pedidos que ainda n�o foram aceitos
	public List<Amizade> getPedidosAmizade(Usuario solicitado) {
		List<Amizade> pendencias = null;
		manager = factory.createEntityManager();

		try {
			pendencias = (List<Amizade>) manager
					.createQuery("SELECT am FROM Amizade am" + " WHERE am.solicitado = :user AND am.confirmado = 0")
					.setParameter("user", solicitado).getResultList();
			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}

		return pendencias;
	}

	// Aceita pedido de amizade de um determinado usu�rio
	public void aceitarPedido(Amizade am) {
		manager = factory.createEntityManager();
		try {
			am.setConfirmado(true);
			manager.getTransaction().begin();
			manager.merge(am);
			manager.getTransaction().commit();
			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
	}

	// Retorna lista de amigos de usu�rio
	public List<Usuario> getAmigos(Usuario user) {
		List<Usuario> amigos1 = null;
		List<Usuario> amigos2 = null;
		manager = factory.createEntityManager();

		try {

			amigos1 = (List<Usuario>) manager
					.createQuery("SELECT am.solicitante FROM Amizade am"
							+ " WHERE am.solicitado = :user AND am.confirmado = 1")
					.setParameter("user", user).getResultList();

			amigos2 = (List<Usuario>) manager
					.createQuery("SELECT am.solicitado FROM Amizade am"
							+ " WHERE am.solicitante = :user AND am.confirmado = 1")
					.setParameter("user", user).getResultList();

			amigos1.addAll(amigos2);
			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}

		return amigos1;
	}

	// Retorna lista de comunidades que o usu�rio participa
	public List<Comunidade> getComunidadesQueParticipo(Usuario user) {
		List<Comunidade> coms = null;

		manager = factory.createEntityManager();

		try {

			coms = (List<Comunidade>) manager
					.createQuery("SELECT cu.comunidade FROM ComunidadeUsuario cu"
							+ " WHERE cu.participante = :user AND cu.confirmado = 1")
					.setParameter("user", user).getResultList();

			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}

		return coms;
	}

	// Retorna lista de comunidades que o usu�rio possui
	public List<Comunidade> getComunidadesQuePossuo(Usuario user) {
		List<Comunidade> coms = null;

		manager = factory.createEntityManager();

		try {

			coms = (List<Comunidade>) manager.createQuery("SELECT c FROM Comunidade c" + 
			" WHERE c.dono = :user")
					.setParameter("user", user).getResultList();

			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}

		return coms;
	}

	// Deleta inst�ncia de usu�rio no banco (Tem que implementar direitinho)
	public void deletarUsuario(Usuario user) {
		manager = factory.createEntityManager();
		try {
			manager.getTransaction().begin();
			manager.remove(manager.merge(user));
			manager.getTransaction().commit();
			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
	}

	public List<MensagemUsuario> getMensagens(Usuario receptor, Usuario emissor) {
		List<MensagemUsuario> msgs = null;
		manager = factory.createEntityManager();

		try {
			msgs = (List<MensagemUsuario>) manager
					.createQuery("SELECT mu FROM MensagemUsuario mu"
							+ " WHERE (mu.receptor = :receptor AND mu.emissor = :emissor)"
							+ "OR (mu.receptor = :emissor AND mu.emissor = :receptor)")
					.setParameter("receptor", receptor).setParameter("emissor", emissor).getResultList();
			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}

		return msgs;
	}
}
