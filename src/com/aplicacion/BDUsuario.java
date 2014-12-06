package com.aplicacion;

import javax.persistence.EntityManagerFactory;

public class BDUsuario {
	
	private static final String PERSISTENCE_UNIT_NAME = "people";
	private EntityManagerFactory factory;

	public void existeEmail(String email) {

	}

	public static void insertar(Usuario usuario) {
		// ...
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
