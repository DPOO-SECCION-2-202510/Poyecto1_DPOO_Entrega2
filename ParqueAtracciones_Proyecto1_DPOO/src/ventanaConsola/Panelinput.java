package ventanaConsola;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import consola.ConsolaMain;


@SuppressWarnings("serial")
public class Panelinput extends JPanel implements ActionListener{
	
	private ConsolaMain consola;
	
	private List<JTextField> inputs;
	
	private JButton boton;

	private VentanaConsola parque;
	
	public Panelinput(ConsolaMain consola, VentanaConsola parque) {
		this.consola = consola;
		this.parque = parque;
		this.inputs = new ArrayList<JTextField>();
		this.boton = new JButton("Enter");
		boton.addActionListener(this);
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(java.awt.FlowLayout.LEFT);
		add(boton);
		setLayout( flowLayout );
		setVisible(true);
		setSize(200, 500);
		
	}

	
	public void mostrarInputs() {
		List<JPanel> ordenes = consola.getInput();
		for (JPanel op : ordenes) {
			add(op);
			JTextField input = (JTextField) op.getComponent(1);
			inputs.add(input);
		}
	}
	
	public List<String> getInputs(){
		List<String> usuarioInput = new ArrayList<String>();
		for (JTextField in : inputs) {
			String op = in.getText();
			usuarioInput.add(op);
		}
		return usuarioInput;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		parque.mostrarTodo();
		
	}

}
