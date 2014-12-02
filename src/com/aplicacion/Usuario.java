package com.aplicacion;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Usuario implements Serializable {
	String user;
	

	public void prueba() {

		Usuario p = new Usuario();
		// p.addParameter("TEST", "VALUE");

		try {
			p.user="Joge";
			URL gwtServlet = null;
			gwtServlet = new URL("http://localhost:8080/ListaCorreoServlet/ListaCorreosServlet");
			HttpURLConnection servletConnection = (HttpURLConnection) gwtServlet
					.openConnection();
			servletConnection.setRequestMethod("POST");
			servletConnection.setDoOutput(true);

			ObjectOutputStream objOut = new ObjectOutputStream(
					servletConnection.getOutputStream());
			objOut.writeObject(p);
			objOut.flush();
			objOut.close();
			
			System.out.println(servletConnection.getContentType());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
