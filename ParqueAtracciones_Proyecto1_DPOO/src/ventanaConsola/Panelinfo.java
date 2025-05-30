package ventanaConsola;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import consola.ConsolaCliente;
import consola.ConsolaMain;
import ventanaBoleta.VentanaBoleta;

@SuppressWarnings("serial")
public class Panelinfo extends JPanel implements ActionListener{

	private ConsolaMain consola;

	private List<String> datosBoleta;
	
	private JButton boletaBoton;
	
	public Panelinfo(ConsolaMain consola) {
		this.datosBoleta= new ArrayList<String>();
		this.consola = consola;
		boletaBoton = new JButton("Ver Boleta");
		boletaBoton.addActionListener(this);
		mostrarInfo(new ArrayList<String>());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setSize(200, 500);
		setVisible(true);
	}
	
	public void mostrarInfo(List<String> inputs) {
		removeAll();
		List<JLabel> info = consola.getInfo(inputs);
		if (consola instanceof ConsolaCliente ) {
			if (consola.getOpcion()==15 || consola.getOpcion()==16 || consola.getOpcion()==17) {
				JLabel data = info.get(0);
				add(data);
				data.setVisible(true);
				String[] datos  = info.get(1).getText().split(";");
				datosBoleta.clear();
				for (String d : datos) {
					datosBoleta.add(d);
				}
				add(boletaBoton);
			}else {
				for (JLabel data: info) {
					add(data);
					data.setVisible(true);
				}
			}
		}else {
			for (JLabel data: info) {
				add(data);
				data.setVisible(true);
			}
		}
		setVisible(true);
		
	}
	
	public void limpiar() {
		removeAll();
	}
	
	public void cambiarConsola(ConsolaMain nueva) {
		this.consola = nueva;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String iniA[] = datosBoleta.get(2).split("/");
		LocalDateTime dia = LocalDateTime.of(Integer.parseInt(iniA[0]), Integer.parseInt(iniA[1]), Integer.parseInt(iniA[2]), 11, 0);
		VentanaBoleta boleta = new VentanaBoleta(datosBoleta.getFirst(), datosBoleta, dia);
		
	}

}
