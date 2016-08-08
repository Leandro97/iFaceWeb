package br.ufal.persistencia;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;

import br.ufal.modelo.Comunidade;
import br.ufal.modelo.ComunidadeUsuario;
import br.ufal.modelo.MensagemComunidade;
import br.ufal.modelo.Usuario;

public class ComunidadePersistencia extends Persistencia {

	private static ComunidadePersistencia instance;

	private ComunidadePersistencia() {

	}

	public static ComunidadePersistencia getInstance() {
		if (instance == null) {
			instance = new ComunidadePersistencia();
		}
		return instance;
	}

	// Retorna lista de todas as comunidades cadastradas
	public List<Comunidade> getAllComunidades() {
		manager = factory.createEntityManager();
		List<Comunidade> coms = null;
		try {
			manager.getTransaction().begin();
			coms = (List<Comunidade>) manager.createQuery("SELECT c FROM Comunidade c").getResultList();
			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
		return coms;
	}

	// Persiste uma comunidade no banco
	public void salvarComunidade(Comunidade com) throws PersistenceException {
		manager = factory.createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.persist(com);
			manager.getTransaction().commit();
			enviarPedidoComunidade(com, com.getDono(), true);
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
	}

	// Retorna um usuário ao receber seu id
	public Comunidade getComunidadeById(String nome) {
		Comunidade com = null;
		manager = factory.createEntityManager();

		try {
			com = manager.find(Comunidade.class, nome);
			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}

		return com;
	}

	// Inclui usuário em uma comunidade
	public boolean enviarPedidoComunidade(Comunidade com, Usuario user, boolean confirmado) {
		manager = factory.createEntityManager();
		boolean pode = false;

		try {
			List<ComunidadeUsuario> pedido = (List<ComunidadeUsuario>) manager
					.createQuery("SELECT cu FROM ComunidadeUsuario cu WHERE "
							+ "cu.comunidade = :com AND cu.participante = :user")
					.setParameter("com", com).setParameter("user", user).getResultList();

			ComunidadeUsuario uc = new ComunidadeUsuario(com, user, confirmado);

			if (pedido.size() == 0) {
				manager.getTransaction().begin();
				manager.persist(uc);
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

	// Retorna lista de usuários que ainda não foram aceitos em uma comunidade
	public List<ComunidadeUsuario> getPedidos(Comunidade com) {
		List<ComunidadeUsuario> pendencias = null;
		manager = factory.createEntityManager();

		try {
			pendencias = (List<ComunidadeUsuario>) manager
					.createQuery("SELECT cu FROM ComunidadeUsuario cu"
							+ " WHERE cu.comunidade = :comunidade AND cu.confirmado = 0")
					.setParameter("comunidade", com).getResultList();
			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}

		return pendencias;
	}

	// Aceita pedido de amizade de um determinado usuário
	public void aceitarPedido(ComunidadeUsuario cu) {
		manager = factory.createEntityManager();
		try {
			cu.setConfirmado(true);
			manager.getTransaction().begin();
			manager.merge(cu);
			manager.getTransaction().commit();
			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
	}

	// Retorna lista de membros de uma comunidade
	public List<Usuario> getMembros(Comunidade com) {
		List<Usuario> membros = null;
		manager = factory.createEntityManager();

		try {
			membros = (List<Usuario>) manager
					.createQuery(
							"SELECT cu.participante FROM ComunidadeUsuario cu " + "WHERE cu.comunidade = :comunidade "
									+ "AND cu.participante != cu.comunidade.dono AND cu.confirmado = 1")
					.setParameter("comunidade", com).getResultList();
			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}

		return membros;
	}

	// Atualiza uma comunidade
	public void atualizarComunidade(Comunidade comunidade) {
		manager = factory.createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.merge(comunidade);
			manager.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}

	// Retorna lista de mensagens trocadas entre receptor e emissor
	public List<MensagemComunidade> getMensagensComunidade(Comunidade com) {
		List<MensagemComunidade> msgs = null;
		manager = factory.createEntityManager();

		try {
			msgs = (List<MensagemComunidade>) manager
					.createQuery("SELECT mc FROM MensagemComunidade mc WHERE mc.receptor = :com")
					.setParameter("com", com).getResultList();
			manager.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}

		return msgs;
	}
}
