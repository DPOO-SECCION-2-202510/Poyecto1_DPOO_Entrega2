package ventanaConsola;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JLabel;

import consola.ConsolaMain;

@SuppressWarnings("serial")
public class Panelinfo extends JPanel{

	private ConsolaMain consola;
	
	public Panelinfo(ConsolaMain consola) {
		this.consola = consola;
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(java.awt.FlowLayout.LEFT);
		setLayout( flowLayout );
		setSize(200, 500);
		setVisible(true);
	}
	
	public void mostrarInfo(List<String> inputs) {
		List<JLabel> info = consola.getInfo(inputs);
		for (JLabel data: info) {
			add(data);
		}
		
	}
	
	public void cambiarConsola(ConsolaMain nueva) {
		this.consola = nueva;
	}

}
