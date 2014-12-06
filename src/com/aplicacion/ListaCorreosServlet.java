package com.aplicacion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListaCorreosServlet
 */
@WebServlet("/ListaCorreosServlet")
public class ListaCorreosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// String url = "/index.html";

		// Obtener la accion a partir de "peticion" (getParameter("action");

		// Realizar la accion y asignar el URL a la pagina apropiada
		// almacenar los datos en el objeto de Usuario

		// validar los parametros utilizando los metodos de BDUsuario; si existe
		// la direccion de email en la base de datos, mostrar un mensaje y pedir
		// otra direccion
		// Insertar los datos del usuario
		// request.setAttribute("user", user);
		// request.setAttribute("message", message);

		// getServletContext().getRequestDispatcher(url).forward(peticion,
		// respuesta);

		System.out.println("doPost");
		ObjectInputStream objIn = new ObjectInputStream(
				request.getInputStream());
		
		if (objIn.readInt() == 1) {
			Usuario user = null;
			try {
				user = (Usuario) objIn.readObject();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			//System.out.println(user.user);
		}

		/*
		 * if(p.user.equalsIgnoreCase("Jorge")){
		 * response.setContentType("JUJUJUJU"); } else{
		 * response.setContentType("JAJAJAJAJ"); }
		 */
	}

}
