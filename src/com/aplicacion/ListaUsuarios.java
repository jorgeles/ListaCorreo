package com.aplicacion;

import java.awt.BorderLayout;
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
	private BDUsuario datos = new BDUsuario();
	private List<Usuario> users;
	JPanel p;

	/**
	 * Create the frame.
	 */
	public ListaUsuarios() {
		super("Lista Usuarios");
		pintar();

	}
	
	public void pintar(){
		setSize(300, 200);
		String titulos[] = { "Nombre", "Apellidos", "Email"," " };
		users = datos.mostrarUsuarios();
		p = new JPanel();
		p.setSize(600, 400);
		p.setLayout(new GridLayout(users.size() + 2, 4, 10, 10));
		for (int row = 0; row < users.size(); row++) {
			if (row == 0) {
				for (int col = 0; col < 4; col++) {
					p.add(new JLabel(titulos[col]));

				}
			}
			
			p.add(new JTextField(users.get(row).getNombre()));
			p.add(new JTextField(users.get(row).getApellidos()));
			p.add(new JTextField(users.get(row).getEmail()));
			JButton j = new JButton("Eliminar");
			j.addActionListener(this);
			j.setActionCommand(Integer.toString(row));
			p.add(j);

		}
		JButton j = new JButton("Modificar");
		j.addActionListener(this);
		j.setActionCommand("Modificar");
		
		p.add(j);
		scrollpane = new JScrollPane(p);
		getContentPane().add(scrollpane, BorderLayout.CENTER);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().toString().equals("Modificar")){
			
		}
		else{
			datos.eliminar(users.get(Integer.parseInt(e.getActionCommand().toString())));
			repaint();
		}
	}
}
