package com.aplicacion;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class BDUsuario {

	private static final String PERSISTENCE_UNIT_NAME = "ListaCorreo";
	private EntityManagerFactory factoria;

	public boolean existeEmail(String email) {
		System.out.println(email);
		factoria = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		// leer las entradas existentes y escribir en la consola
		Query q = em.createQuery("SELECT u from Usuario u");

		List<Usuario> listaUsuario = q.getResultList();
		for (Usuario lista : listaUsuario) {
			if (lista.getEmail().equals(email)) {
				return true;
			}
		}
		return false;

	}

	public void insertar(Usuario usuario) {
		factoria = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();
		Usuario user = usuario;
	    //Posteriormente hay que decirle al gestor de entidad (em) que dicha instancia va a ser persistente; conseguir la transaccion ("em.getTransaction()") y hacerla definitiva ("commit()")
	    em.persist(user);
	    em.getTransaction().commit();
	    //Por ultimo, hay que cerrar al gestor de entidad
	    em.close();
	}

	public static void actualizar(Usuario usuario) {
		// ...
	}

	public static void eliminar(Usuario usuario) {
		// ...
	}

	public static Usuario seleccionarUsuario(String email) {
		return null;
		// ...
	}

}
