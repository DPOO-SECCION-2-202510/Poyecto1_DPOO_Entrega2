package ventanaConsola;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;

import consola.ConsolaMain;

@SuppressWarnings("serial")
public class Panelinput extends JPanel{
	
	private ConsolaMain consola;
	
	private List<JTextField> inputs;
	

	public Panelinput(ConsolaMain consola) {
		this.consola = consola;
		this.inputs = new ArrayList<JTextField>();
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(java.awt.FlowLayout.LEFT);
		setLayout( flowLayout );
		setVisible(true);
	}

	
	public void mostrarInputs() {
		List<JPanel> ordenes = consola.getInput();
		for (JPanel op : ordenes) {
			add(op, null);
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

}
