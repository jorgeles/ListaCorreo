package com.aplicacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SpringLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JTextField tfNombre;
	private JTextField tfApellidos;
	private JTextField tfCorreoElectronico;

	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblCorreoElectronico;
	private JLabel lblWarningCorreoElectronico;

	private JButton btnAniadir;

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
					Usuario user = new Usuario();
					user.user="Jorge";
					frame.AniadirUsuario(user);
					//frame.setVisible(true);
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
		setBounds(100, 100, 450, 300);
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
		btnAniadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Matcher matcher = pattern.matcher(tfCorreoElectronico.getText());
				if (!matcher.matches()) {
					lblWarningCorreoElectronico.setVisible(true);
				} else {
					lblWarningCorreoElectronico.setVisible(false);
				}

			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btnAniadir, 115,
				SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAniadir, -10,
				SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnAniadir, -128,
				SpringLayout.EAST, contentPane);
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
		lblWarningCorreoElectronico.setVisible(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH,
				lblWarningCorreoElectronico, 0, SpringLayout.NORTH,
				lblCorreoElectronico);
		sl_contentPane.putConstraint(SpringLayout.EAST,
				lblWarningCorreoElectronico, -16, SpringLayout.WEST,
				lblCorreoElectronico);
		contentPane.add(lblWarningCorreoElectronico);
	}
	
	public boolean AniadirUsuario(Usuario user) {

		String answer;
		try {
			URL gwtServlet = null;
			gwtServlet = new URL(
					"http://localhost:8080/ListaCorreoServlet/ListaCorreosServlet");
			HttpURLConnection servletConnection = (HttpURLConnection) gwtServlet
					.openConnection();
			servletConnection.setRequestMethod("POST");
			servletConnection.setDoOutput(true);
			ObjectOutputStream objOut = new ObjectOutputStream(
					servletConnection.getOutputStream());
			
			objOut.writeInt(1);
			objOut.writeObject(user);
			int jorge = 1;
			objOut.flush();
			objOut.close();

			answer = servletConnection.getContentType();
			
			if(answer.equalsIgnoreCase("1")){
				return true;
			}
			else if(answer.equalsIgnoreCase("0")){
				return false;
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
