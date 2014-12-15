package com.aplicacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Ventana del programa que nos permite enviar un correo a todos los usuarios existentes en la base de datos
 */
public class Enviar extends JFrame {

	private JPanel contentPane;
	private JTextField tfCorreo;
	private JTextField tfSMTP;

	private JPasswordField passwordField;
	private JTextField tfAsunto;
	private JTextField tfTexto;

	private JLabel lblTexto;
	private JLabel lblCorreoElectronico;
	private JLabel lblSmtp;
	private JLabel lblPassword;
	private JLabel lblAsunto;
	private JLabel lblIntroducir;
	private JLabel lblIntroducir_1;
	private JLabel lblIntroducir_2;

	/*
	 * String que contiene la estrucutra de un correo electronico
	 */
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Create the frame.
	 */
	public Enviar() {
		final Pattern pattern = Pattern.compile(PATTERN_EMAIL);

		setBounds(100, 100, 617, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		lblCorreoElectronico = new JLabel("Correo Electronico");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblCorreoElectronico,
				6, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblCorreoElectronico,
				0, SpringLayout.WEST, contentPane);
		contentPane.add(lblCorreoElectronico);

		tfCorreo = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, tfCorreo, -6,
				SpringLayout.NORTH, lblCorreoElectronico);
		sl_contentPane.putConstraint(SpringLayout.WEST, tfCorreo, 6,
				SpringLayout.EAST, lblCorreoElectronico);
		sl_contentPane.putConstraint(SpringLayout.EAST, tfCorreo, 212,
				SpringLayout.EAST, lblCorreoElectronico);
		contentPane.add(tfCorreo);
		tfCorreo.setColumns(10);

		tfSMTP = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, tfSMTP, 6,
				SpringLayout.SOUTH, tfCorreo);
		sl_contentPane.putConstraint(SpringLayout.WEST, tfSMTP, 0,
				SpringLayout.WEST, tfCorreo);
		sl_contentPane.putConstraint(SpringLayout.EAST, tfSMTP, 328,
				SpringLayout.WEST, contentPane);
		contentPane.add(tfSMTP);
		tfSMTP.setColumns(10);

		lblSmtp = new JLabel("SMTP");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSmtp, 6,
				SpringLayout.NORTH, tfSMTP);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblSmtp, 0,
				SpringLayout.EAST, lblCorreoElectronico);
		contentPane.add(lblSmtp);

		passwordField = new JPasswordField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, passwordField, 6,
				SpringLayout.SOUTH, tfSMTP);
		sl_contentPane.putConstraint(SpringLayout.WEST, passwordField, 0,
				SpringLayout.WEST, tfCorreo);
		sl_contentPane.putConstraint(SpringLayout.EAST, passwordField, 0,
				SpringLayout.EAST, tfCorreo);
		contentPane.add(passwordField);

		lblPassword = new JLabel("Password");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPassword, 6,
				SpringLayout.NORTH, passwordField);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblPassword, -8,
				SpringLayout.WEST, passwordField);
		contentPane.add(lblPassword);

		lblAsunto = new JLabel("Asunto");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblAsunto, 25,
				SpringLayout.SOUTH, lblPassword);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblAsunto, 0,
				SpringLayout.WEST, contentPane);
		contentPane.add(lblAsunto);

		tfAsunto = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, tfAsunto, 6,
				SpringLayout.SOUTH, lblAsunto);
		sl_contentPane.putConstraint(SpringLayout.WEST, tfAsunto, 0,
				SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, tfAsunto, -10,
				SpringLayout.EAST, contentPane);
		contentPane.add(tfAsunto);
		tfAsunto.setColumns(10);

		lblTexto = new JLabel("Texto");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblTexto, 0,
				SpringLayout.WEST, lblCorreoElectronico);
		contentPane.add(lblTexto);

		tfTexto = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblTexto, -11,
				SpringLayout.NORTH, tfTexto);
		sl_contentPane.putConstraint(SpringLayout.NORTH, tfTexto, 198,
				SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, tfTexto, -5,
				SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, tfTexto, 10,
				SpringLayout.WEST, contentPane);
		contentPane.add(tfTexto);
		tfTexto.setColumns(10);

		JButton btnEnviar = new JButton("Enviar");
		sl_contentPane.putConstraint(SpringLayout.EAST, tfTexto, 0,
				SpringLayout.EAST, btnEnviar);
		btnEnviar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				/*
				 * Comprobamos que la estructura del correo introducido tiene la estrctura de un correo real
				 * Ademas comprobamos que los field no estan vacios
				 */
				Matcher matcher = pattern.matcher(tfCorreo.getText());
				boolean correcto = true;
				if (tfCorreo.getText().isEmpty() || !matcher.matches()) {
					lblIntroducir.setVisible(true);
					correcto = false;
				} else {
					lblIntroducir.setVisible(false);
				}
				if (tfSMTP.getText().isEmpty()) {
					lblIntroducir_1.setVisible(true);
					correcto = false;
				} else {
					lblIntroducir_1.setVisible(false);
				}
				if (passwordField.getText().isEmpty()) {
					lblIntroducir_2.setVisible(true);
					correcto = false;
				} else {
					lblIntroducir_2.setVisible(false);
				}

				if (correcto) {
					EnviarCorreo(tfCorreo.getText(), tfSMTP.getText(),
							passwordField.getText(), tfAsunto.getText(),
							tfTexto.getText());
				}
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnEnviar, 0,
				SpringLayout.NORTH, lblCorreoElectronico);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnEnviar, -10,
				SpringLayout.EAST, contentPane);
		contentPane.add(btnEnviar);

		lblIntroducir = new JLabel("Introducir Correo Valido");
		lblIntroducir.setForeground(Color.RED);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblIntroducir, 0,
				SpringLayout.NORTH, lblCorreoElectronico);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblIntroducir, 6,
				SpringLayout.EAST, tfCorreo);
		lblIntroducir.setVisible(false);
		contentPane.add(lblIntroducir);

		lblIntroducir_1 = new JLabel("Introducir");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblIntroducir_1, 6,
				SpringLayout.NORTH, tfSMTP);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblIntroducir_1, 6,
				SpringLayout.EAST, tfSMTP);
		lblIntroducir_1.setForeground(Color.RED);
		lblIntroducir_1.setVisible(false);
		contentPane.add(lblIntroducir_1);

		lblIntroducir_2 = new JLabel("Introducir");
		lblIntroducir_2.setForeground(Color.RED);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblIntroducir_2, 6,
				SpringLayout.EAST, passwordField);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblIntroducir_2, 0,
				SpringLayout.SOUTH, lblPassword);
		lblIntroducir_2.setVisible(false);
		contentPane.add(lblIntroducir_2);
	}

	/*
	 * Función que llama al servidor indicandole que desea enviar un correo electronio.
	 * Además le pasa todos los datos del cliente necearios para enviar el correo.
	 */
	public void EnviarCorreo(String email, String SMTP, String password,
			String Asunto, String Texto) {
		try {
			URL gwtServlet = null;
			gwtServlet = new URL(
					"http://localhost:8080/ListaCorreoServlet/ListaCorreosServlet");
			HttpURLConnection servletConnection = (HttpURLConnection) gwtServlet
					.openConnection();
			servletConnection.setUseCaches(false);
			servletConnection.setRequestMethod("POST");
			servletConnection.setDoOutput(true);
			ObjectOutputStream objOut = new ObjectOutputStream(
					servletConnection.getOutputStream());

			objOut.writeInt(5);
			objOut.writeObject(email);
			objOut.writeObject(SMTP);
			objOut.writeObject(password);
			objOut.writeObject(Asunto);
			objOut.writeObject(Texto);
			objOut.flush();
			objOut.close();

			String answer = servletConnection.getContentType();

			if (answer.equalsIgnoreCase("Done")) {
				JOptionPane.showMessageDialog(null, "Mensaje Enviado Correctamente");

			} else {
				JOptionPane.showMessageDialog(null,"¡Error en el Envio! Pruebe de Nuevo");
			}

			servletConnection.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
