package ventanaConsola;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
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
	
	private boolean ya;
	
	public Panelinput(ConsolaMain consola, VentanaConsola parque) {
		this.consola = consola;
		this.parque = parque;
		this.inputs = new ArrayList<JTextField>();
		this.ya = false;
		mostrarInputs();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setVisible(true);
		setSize(200, 500);
		ya = true;
	}

	
	public void mostrarInputs() {
		removeAll();
		List<JPanel> ordenes = consola.getInput();
		for (JPanel op : ordenes) {
			add(op);
			op.setVisible(true);
			JTextField input = (JTextField) op.getComponent(1);
			inputs.add(input);
		}
		if (ya) {
			this.boton = new JButton("Enter");
			add(boton);
			boton.addActionListener(this);
		}
		setVisible(true);
	}
	
	
	public List<String> getInputs(){
		List<String> usuarioInput = new ArrayList<String>();
		for (JTextField in : inputs) {
			String op = in.getText();
			usuarioInput.add(op);
		}
		inputs.clear();
		return usuarioInput;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		parque.mostrarTodo();
		
	}

}
