package com.aplicacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SpringLayout;

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

import javax.swing.JLabel;

import java.awt.Color;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JTextField tfNombre;
	private JTextField tfApellidos;
	private JTextField tfCorreoElectronico;

	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblCorreoElectronico;
	private JLabel lblWarningCorreoElectronico;
	private JLabel lblWarningNombre;
	private JLabel lblWarningApellidos;

	private JButton btnAniadir;
	private JButton JBEnviar;
	private JButton btnMostrarTodos;

	private BDUsuario datos = new BDUsuario();

	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		final Pattern pattern = Pattern.compile(PATTERN_EMAIL);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		tfNombre = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, tfNombre, 56,
				SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, tfNombre, -182,
				SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, tfNombre, -5,
				SpringLayout.EAST, contentPane);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);

		tfApellidos = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, tfApellidos, 19,
				SpringLayout.SOUTH, tfNombre);
		sl_contentPane.putConstraint(SpringLayout.WEST, tfApellidos, -177,
				SpringLayout.EAST, tfNombre);
		sl_contentPane.putConstraint(SpringLayout.EAST, tfApellidos, 0,
				SpringLayout.EAST, tfNombre);
		contentPane.add(tfApellidos);
		tfApellidos.setColumns(10);

		tfCorreoElectronico = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, tfCorreoElectronico,
				21, SpringLayout.SOUTH, tfApellidos);
		sl_contentPane.putConstraint(SpringLayout.WEST, tfCorreoElectronico, 0,
				SpringLayout.WEST, tfNombre);
		sl_contentPane.putConstraint(SpringLayout.EAST, tfCorreoElectronico,
				-5, SpringLayout.EAST, contentPane);
		contentPane.add(tfCorreoElectronico);
		tfCorreoElectronico.setColumns(10);

		btnAniadir = new JButton("Registrar");
		sl_contentPane.putConstraint(SpringLayout.EAST, btnAniadir, -66,
				SpringLayout.EAST, contentPane);
		btnAniadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int correcto = 0;
				Matcher matcher = pattern.matcher(tfCorreoElectronico.getText());
				if (!matcher.matches()) {
					lblWarningCorreoElectronico.setVisible(true);

				} else {
					lblWarningCorreoElectronico.setVisible(false);
					correcto++;
				}
				if (tfNombre.getText().isEmpty()) {
					lblWarningNombre.setVisible(true);
				} else {
					lblWarningNombre.setVisible(false);
					correcto++;
				}
				if (tfApellidos.getText().isEmpty()) {
					lblWarningApellidos.setVisible(true);
				} else {
					lblWarningApellidos.setVisible(false);
					correcto++;
				}
				if (correcto >= 3) {
					Usuario user = new Usuario();
					user.setApellidos(tfApellidos.getText());
					user.setNombre(tfNombre.getText());
					user.setEmail(tfCorreoElectronico.getText());
					if (AniadirUsuario(user)) {
						JOptionPane.showMessageDialog(null, "Usuario Creado");
					} else {
						JOptionPane
								.showMessageDialog(null,
										"¡El Correo Ya Existe! Introduzca otro Por Favor");
					}
				}

			}
		});
		contentPane.add(btnAniadir);

		lblNombre = new JLabel("Nombre");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNombre, 3,
				SpringLayout.NORTH, tfNombre);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNombre, 81,
				SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNombre, -11,
				SpringLayout.WEST, tfNombre);
		contentPane.add(lblNombre);

		lblApellidos = new JLabel("Apellidos");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblApellidos, 6,
				SpringLayout.NORTH, tfApellidos);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblApellidos, -6,
				SpringLayout.SOUTH, tfApellidos);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblApellidos, -6,
				SpringLayout.WEST, tfApellidos);
		contentPane.add(lblApellidos);

		lblCorreoElectronico = new JLabel("Correo Electronico");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblCorreoElectronico,
				6, SpringLayout.NORTH, tfCorreoElectronico);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblCorreoElectronico,
				-6, SpringLayout.SOUTH, tfCorreoElectronico);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblCorreoElectronico,
				-6, SpringLayout.WEST, tfCorreoElectronico);
		contentPane.add(lblCorreoElectronico);

		lblWarningCorreoElectronico = new JLabel("Correo Electronico No Válido");
		lblWarningCorreoElectronico.setForeground(Color.RED);
		lblWarningCorreoElectronico.setVisible(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH,
				lblWarningCorreoElectronico, 0, SpringLayout.NORTH,
				lblCorreoElectronico);
		sl_contentPane.putConstraint(SpringLayout.EAST,
				lblWarningCorreoElectronico, -16, SpringLayout.WEST,
				lblCorreoElectronico);
		contentPane.add(lblWarningCorreoElectronico);

		lblWarningNombre = new JLabel("Introduzca un Nombre");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblWarningNombre, 6,
				SpringLayout.NORTH, tfNombre);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblWarningNombre, -24,
				SpringLayout.WEST, lblNombre);
		lblWarningNombre.setForeground(Color.RED);
		lblWarningNombre.setVisible(false);
		contentPane.add(lblWarningNombre);

		lblWarningApellidos = new JLabel("Introduzca Apellidos");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblWarningApellidos,
				6, SpringLayout.NORTH, tfApellidos);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblWarningApellidos,
				-20, SpringLayout.WEST, lblApellidos);
		lblWarningApellidos.setForeground(Color.RED);
		lblWarningApellidos.setVisible(false);
		contentPane.add(lblWarningApellidos);

		btnMostrarTodos = new JButton("Mostrar Todos");
		btnMostrarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarUsuarios();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnMostrarTodos, -10,
				SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnAniadir, 0,
				SpringLayout.NORTH, btnMostrarTodos);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnAniadir, 42,
				SpringLayout.EAST, btnMostrarTodos);
		contentPane.add(btnMostrarTodos);

		JBEnviar = new JButton("Enviar a Todos");
		JBEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enviar frame = new Enviar();
				frame.setVisible(true);
			}
		});
		contentPane.add(JBEnviar);
	}

	public boolean AniadirUsuario(Usuario user) {
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

			objOut.writeInt(1);
			objOut.writeObject(user);
			objOut.flush();
			objOut.close();

			String answer = servletConnection.getContentType();

			if (answer.equalsIgnoreCase("Correcto")) {
				System.out.println(answer);
				return true;
			} else if (answer.equalsIgnoreCase("Existe")) {
				System.out.println(answer);
				return false;
			}
			servletConnection.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void MostrarUsuarios() {
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

			objOut.writeInt(2);
			objOut.flush();
			objOut.close();

			ObjectInputStream objIn = new ObjectInputStream(
					servletConnection.getInputStream());
			List<Usuario> user;
			
			try {
				user = (List<Usuario>) objIn.readObject();
				System.out.println("J"+user);
				ListaUsuarios frame = new ListaUsuarios(user);
				frame.setVisible(true);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
