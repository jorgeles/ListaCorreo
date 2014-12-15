package com.aplicacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BDUsuario {

	private static final String PERSISTENCE_UNIT_NAME = "ListaCorreo";
	private EntityManagerFactory factoria;

	/*
	 * Indica si un email:String existe ya en la base de datos
	 */
	public boolean existeEmail(String email) {
		factoria = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		Query q = em
				.createQuery("SELECT u from Usuario u WHERE u.email = :email");
		q.setParameter("email", email);

		List<Usuario> listaUsuario = q.getResultList();
		for (Usuario lista : listaUsuario) {
			if (lista.getEmail().equals(email)) {
				return true;
			}
		}
		return false;

	}
	/*
	 * Nos permite insertar un usuario:Usuario en la base de datos
	 */
	public void insertar(Usuario usuario) {
		factoria = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();
		Usuario user = usuario;
		// Posteriormente hay que decirle al gestor de entidad (em) que dicha
		// instancia va a ser persistente; conseguir la transaccion
		// ("em.getTransaction()") y hacerla definitiva ("commit()")
		em.persist(user);
		em.getTransaction().commit();
		// Por ultimo, hay que cerrar al gestor de entidad
		em.close();
	}

	/*
	 * Se utiliza para actualizar la base de datos.
	 * Se le pasa una lista con los datos antiguos y otra con los datos actualizados
	 */
	public void actualizar(List<Usuario> usuario, List<Usuario> newusuario) {
		factoria = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		for (int i = 0; i < newusuario.size(); i++) {
			String email = newusuario.get(i).getEmail();
			String nombre = newusuario.get(i).getNombre();
			String apellidos = newusuario.get(i).getApellidos();
			String oldemail = usuario.get(i).getEmail();

			EntityManager em = factoria.createEntityManager();
			EntityTransaction updateTransaction = em.getTransaction();
			updateTransaction.begin();
			Query q = em
					.createQuery("UPDATE Usuario u SET u.email = :email, u.apellidos = :apellidos, u.nombre= :nombre WHERE u.email = :oldemail");
			q.setParameter("email", email);
			q.setParameter("apellidos", apellidos);
			q.setParameter("nombre", nombre);
			q.setParameter("oldemail", oldemail);
			q.executeUpdate();
			updateTransaction.commit();
		}
	}
	
	/*
	 * Nos permite eliminar un usuario:Usuario de la base de datos
	 */
	public void eliminar(Usuario usuario) {
		factoria = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();
		String email = usuario.getEmail();
		Query q = em
				.createQuery("SELECT u FROM Usuario u WHERE u.email = :email ");
		q.setParameter("email", email);
		System.out.println("JAJAJ");
		List<Usuario> listaUsuario = q.getResultList();
		for (Usuario lista : listaUsuario) {
			if (lista.getEmail().equals(email)) {
				em.remove(lista);

			}
		}
		em.getTransaction().commit();
		em.close();
	}

	/*
	 * No se utiliza en este programa
	 */
	public static Usuario seleccionarUsuario(String email) {
		return null;
		// ...
	}

	/*
	 * Devuelve una lista con los datos de todos los usuarios existente en la base de datos
	 */
	public List<Usuario> mostrarUsuarios() {
		factoria = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		// leer las entradas existentes y escribir en la consola
		Query q = em.createQuery("SELECT u from Usuario u");
		List<Usuario> listaUsuario = q.getResultList();
		return listaUsuario;
	}

}
