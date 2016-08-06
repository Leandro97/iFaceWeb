package br.ufal.persistencia;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;

import br.ufal.modelo.Amizade;
import br.ufal.modelo.Comunidade;
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

	// Persiste um usuário no banco
	public void salvarUsuario(Usuario user) {
		manager = factory.createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.persist(user);
			manager.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} catch (PersistenceException e) {
			System.out.println("Usuário já cadastrado!");
		} finally {
			manager.close();
		}
	}

	// Atualiza um usuário
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

	// Retorna um usuário ao receber seu id (seu username)
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

	// Retorna um usuário se a combinação de username e senha estiver cadastrada
	public Usuario login(String username, String senha) {
		manager = factory.createEntityManager();
		Usuario user = null;
		try {
			user = (Usuario) manager
					.createQuery(
							"SELECT u FROM Usuario u WHERE u.username = :username AND u.senha = :senha")
					.setParameter("username", username)
					.setParameter("senha", senha).getResultList().get(0);
			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}

		return user;
	}

	// Envia pedido de amizade para um usuário
	public void enviarPedidoAmizade(Usuario solicitante, Usuario solicitado,
			boolean confirmado) {
		manager = factory.createEntityManager();
		Amizade amizade = new Amizade(solicitante, solicitado, confirmado);
		try {
			manager.getTransaction().begin();
			manager.persist(amizade);
			manager.getTransaction().commit();
			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
	}

	// Retorna lista de pedidos que ainda não foram aceitos
	public List<Amizade> getPedidosAmizade(Usuario solicitado) {
		List<Amizade> pendencias = null;
		manager = factory.createEntityManager();

		try {
			pendencias = (List<Amizade>) manager
					.createQuery(
							"SELECT am FROM Amizade am"
									+ " WHERE am.solicitado = :user AND am.confirmado = 0")
					.setParameter("user", solicitado).getResultList();
			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}

		return pendencias;
	}

	// Aceita pedido de amizade de um determinado usuário
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

	// Retorna lista de amigos de usuário
	public List<Usuario> getAmigos(Usuario user) {
		List<Usuario> amigos1 = null;
		List<Usuario> amigos2 = null;
		manager = factory.createEntityManager();

		try {

			amigos1 = (List<Usuario>) manager
					.createQuery(
							"SELECT am.solicitante FROM Amizade am"
									+ " WHERE am.solicitado = :user AND am.confirmado = 1")
					.setParameter("user", user).getResultList();

			amigos2 = (List<Usuario>) manager
					.createQuery(
							"SELECT am.solicitado FROM Amizade am"
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

	// Retorna lista de amigos de usuário
	public List<Comunidade> getComunidades(Usuario user) {
		List<Comunidade> coms = null;

		manager = factory.createEntityManager();

		try {

			coms = (List<Comunidade>) manager
					.createQuery(
							"SELECT cu.comunidade FROM ComunidadeUsuario cu"
									+ " WHERE cu.participante = :user AND cu.confirmado = 1")
					.setParameter("user", user).getResultList();

			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}

		return coms;
	}

	// Deleta instância de usuário no banco (Tem que implementar direitinho)
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
}
