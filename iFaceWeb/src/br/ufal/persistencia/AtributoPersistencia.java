package br.ufal.persistencia;

import org.hibernate.HibernateException;

import br.ufal.modelo.Atributo;

public class AtributoPersistencia extends Persistencia {

	private static AtributoPersistencia instance;

	private AtributoPersistencia() {

	}

	public static AtributoPersistencia getInstance() {
		if (instance == null) {
			instance = new AtributoPersistencia();
		}
		return instance;
	}

	// Persiste um atributo no banco
	public void salvarAtributo(Atributo atributo) {
		manager = factory.createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.persist(atributo);
			manager.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}

	// Atualiza um atributo
	public void atualizarAtributo(Atributo atributo) {
		manager = factory.createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.merge(atributo);
			manager.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}
}
