package com.aplicacion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListaCorreosServlet
 * Este el servidor que realiza todas la acciones de comunicación y hace de intermediario entre
 * la base de datos y el cliente,
 */
@WebServlet("/ListaCorreosServlet")
public class ListaCorreosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BDUsuario datos = new BDUsuario();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListaCorreosServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      Para comunicarse con el servlet primero hay que mandarle un int que indique que función
	 *      queremos hacer y luego los datos correspondientes. Si los datos no van en ese orden o no son
	 *      correctos el servlet devolvera un error 500
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		ObjectInputStream objIn = new ObjectInputStream(
				request.getInputStream());
		int codigo = objIn.readInt();
		/*
		 * Codigo para insertar el usuario
		 */
		if (codigo == 1) {
			Usuario user = null;
			try {
				user = (Usuario) objIn.readObject();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if (this.datos.existeEmail(user.getEmail())) {
				response.setContentType("Existe");
			} else {
				this.datos.insertar(user);
				response.setContentType("Correcto");
			}

		}
		/*
		 * Codigo para mostrar todos los usuarios
		 */
		else if (codigo == 2) {
			List<Usuario> user = this.datos.mostrarUsuarios();
			ObjectOutputStream objOut = new ObjectOutputStream(
					response.getOutputStream());
			objOut.writeObject(user);
			objOut.flush();
			objOut.close();
		}
		/*
		 * Codigo para eliminar usuario
		 */
		else if (codigo == 3) {
			Usuario user = null;
			try {
				user = (Usuario) objIn.readObject();
				datos.eliminar(user);
				response.setContentType("Done");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
		/*
		 * Codifo para actualizar usuarios
		 */
		else if (codigo == 4) {
			List<Usuario> newuser = null;
			List<Usuario> user = null;
			try {
				newuser = (List<Usuario>) objIn.readObject();
				user = (List<Usuario>) objIn.readObject();
				datos.actualizar(user, newuser);
				response.setContentType("Done");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		/*
		 * Codigo para enviar correo
		 */
		else if (codigo == 5) {
			String email;
			String SMTP;
			String password;
			String Asunto;
			String texto;
			try {
				email = (String) objIn.readObject();
				SMTP = (String) objIn.readObject();
				password = (String) objIn.readObject();
				Asunto = (String) objIn.readObject();
				texto = (String) objIn.readObject();

				List<Usuario> user = this.datos.mostrarUsuarios();
				EnviadorMail enviar = new EnviadorMail();
				for (int i = 0; i < user.size(); i++) {
					enviar.Enviar(email, SMTP, password, user.get(i).getEmail(), Asunto,
							texto);
				}
				response.setContentType("Done");
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}

	}
}
