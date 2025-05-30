package ventanaConsola;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import consola.ConsolaMain;

@SuppressWarnings("serial")
public class Panelinfo extends JPanel{

	private ConsolaMain consola;
	
	public Panelinfo(ConsolaMain consola) {
		this.consola = consola;
		mostrarInfo(new ArrayList<String>());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setSize(200, 500);
		setVisible(true);
	}
	
	public void mostrarInfo(List<String> inputs) {
		List<JLabel> info = consola.getInfo(inputs);
		for (JLabel data: info) {
			add(data);
			data.setVisible(true);
		}
		setVisible(true);
		
	}
	
	public void limpiar() {
		removeAll();
	}
	
	public void cambiarConsola(ConsolaMain nueva) {
		this.consola = nueva;
	}

}
