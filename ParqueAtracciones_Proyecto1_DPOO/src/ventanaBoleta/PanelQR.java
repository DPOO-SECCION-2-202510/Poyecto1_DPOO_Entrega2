package ventanaBoleta;


import java.awt.BorderLayout;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class PanelQR extends JPanel{
	
	
	private VentanaBoleta principal;
	

	public PanelQR(VentanaBoleta origen, String cual, List<String> info) {
		setLayout(new BorderLayout());
		this.principal = origen;
		String data = "Tiquete: "+info.get(4)+"\nID: " +info.get(0)+ "\nFecha: "+info.get(2);
		creadorQR creador = new creadorQR();
		String donde = creador.crearQr(data);
		JLabel imagen = new JLabel(new ImageIcon( donde));
		add(imagen, BorderLayout.CENTER);
		setOpaque(true);
		setVisible(true);
	}
	

}
