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
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		ObjectInputStream objIn = new ObjectInputStream(
				request.getInputStream());
		System.out.println("Hola");
		if (objIn.readInt() == 1) {
			Usuario user = null;
			System.out.println("Hola2");
			try {
				user = (Usuario) objIn.readObject();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println("Hola3");
			if(this.datos.existeEmail(user.getEmail())){
				System.out.println("Hola4");
			}
			else{
				
			}
		}

		/*
		 * if(p.user.equalsIgnoreCase("Jorge")){
		 * response.setContentType("JUJUJUJU"); } else{
		 * response.setContentType("JAJAJAJAJ"); }
		 */
	}

}
