package com.aplicacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ListaUsuarios extends JFrame implements ActionListener {

	private JScrollPane scrollpane;
	private List<Usuario> users;
	private JPanel fondo;
	private SpringLayout sl_fondo;
	private JButton btnActualizar;
	private ArrayList<JTextField> textfield;
	private ArrayList<JButton> deletes;
	private JTextField textField;
	private JLabel Nombre;
	private JLabel Apellidos;
	private JLabel Email;
	private int Altura = 900;

	/**
	 * Create the frame.
	 */
	public ListaUsuarios(List<Usuario> user) {
		super("Lista Usuarios");
		getContentPane().setLayout(new SpringLayout());
		textfield = new ArrayList<JTextField>();
		deletes = new ArrayList<JButton>();
		pintar(user);

	}

	public void pintar(List<Usuario> user) {
		setSize(800, 300);
		users = user;
		getContentPane().setLayout(new BorderLayout(0, 0));
		fondo = new JPanel();
		fondo.setPreferredSize(new Dimension(200, Altura));
		fondo.setOpaque(false);
		scrollpane = new JScrollPane(fondo);
		sl_fondo = new SpringLayout();
		fondo.setLayout(sl_fondo);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setActionCommand("a");
		sl_fondo.putConstraint(SpringLayout.NORTH, btnActualizar, 10,
				SpringLayout.NORTH, fondo);
		sl_fondo.putConstraint(SpringLayout.WEST, btnActualizar, 10,
				SpringLayout.WEST, fondo);
		fondo.add(btnActualizar);

		Nombre = new JLabel("Nombre");
		sl_fondo.putConstraint(SpringLayout.NORTH, Nombre, 40,
				SpringLayout.NORTH, fondo);
		sl_fondo.putConstraint(SpringLayout.WEST, Nombre, 10,
				SpringLayout.WEST, fondo);
		fondo.add(Nombre);

		Apellidos = new JLabel("Apellidos");
		sl_fondo.putConstraint(SpringLayout.NORTH, Apellidos, 40,
				SpringLayout.NORTH, fondo);
		sl_fondo.putConstraint(SpringLayout.WEST, Apellidos, 200,
				SpringLayout.WEST, fondo);
		fondo.add(Apellidos);

		Email = new JLabel("Email");
		sl_fondo.putConstraint(SpringLayout.NORTH, Email, 40,
				SpringLayout.NORTH, fondo);
		sl_fondo.putConstraint(SpringLayout.WEST, Email, 400,
				SpringLayout.WEST, fondo);
		fondo.add(Email);
		int p = 2;
		for (Usuario usuario : users) {
			for (int i = 0; i < 3; i++) {
				JTextField jt = new JTextField();
				jt.setColumns(10);
				if (i == 0) {
					jt.setText(usuario.getNombre());
				} else if (i == 1) {
					jt.setText(usuario.getApellidos());
				} else {
					jt.setText(usuario.getEmail());
				}
				sl_fondo.putConstraint(SpringLayout.NORTH, jt, 40 * p,
						SpringLayout.NORTH, fondo);
				sl_fondo.putConstraint(SpringLayout.WEST, jt, 200 * i,
						SpringLayout.WEST, fondo);
				fondo.add(jt);
				textfield.add(jt);
			}
			JButton jb = new JButton("Eliminar");
			sl_fondo.putConstraint(SpringLayout.NORTH, jb, 40 * p,
					SpringLayout.NORTH, fondo);
			sl_fondo.putConstraint(SpringLayout.WEST, jb, 200 * 3,
					SpringLayout.WEST, fondo);
			jb.addActionListener(this);
			jb.setActionCommand(Integer.toString(p-1));
			fondo.add(jb);
			deletes.add(jb);
			p++;
			if(p%22==1){
				Altura = Altura*2;
				fondo.setPreferredSize(new Dimension(200, Altura));
			}
		}

		this.scrollpane.getViewport().setOpaque(false);
		this.scrollpane.setOpaque(false);
		getContentPane().add(scrollpane, BorderLayout.CENTER);

		/*
		 * setSize(300, 200); String titulos[] = { "Nombre", "Apellidos",
		 * "Email"," " }; users = user; p = new JPanel(); p.setSize(600, 400);
		 * p.setLayout(new GridLayout(users.size() + 2, 4, 10, 10)); for (int
		 * row = 0; row < users.size(); row++) { if (row == 0) { for (int col =
		 * 0; col < 4; col++) { p.add(new JLabel(titulos[col]));
		 * 
		 * } }
		 * 
		 * p.add(new JTextField(users.get(row).getNombre())); p.add(new
		 * JTextField(users.get(row).getApellidos())); p.add(new
		 * JTextField(users.get(row).getEmail())); JButton j = new
		 * JButton("Eliminar"); j.addActionListener(this);
		 * j.setActionCommand(Integer.toString(row)); p.add(j);
		 * 
		 * } JButton j = new JButton("Modificar"); j.addActionListener(this);
		 * j.setActionCommand("Modificar");
		 * 
		 * p.add(j); scrollpane = new JScrollPane(p);
		 * getContentPane().add(scrollpane, BorderLayout.CENTER);
		 */
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().toString().equals("Modificar")) {

		} else {
			// datos.eliminar(users.get(Integer.parseInt(e.getActionCommand().toString())));
			repaint();
			Component[] componentes = this.getComponents();

			for (int i = 0; i < componentes.length; i++) {
				System.out.println(componentes[i].getClass());
			}
		}
	}
}
