package ventanaBoleta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelInfoBoleta extends JPanel{
	
	private VentanaBoleta princial;

	
	public PanelInfoBoleta(VentanaBoleta ventana, String cual, List<String> info) {
		this.princial= ventana;
		setLayout(new BorderLayout());
		JLabel nom = new JLabel(info.get(0));
		JPanel nomm = new JPanel();
		nomm.add(nom, BorderLayout.CENTER);
		nom.setOpaque(true);
		
		JLabel tiqn = new JLabel("Atraccion: ");
		if (cual.equals("tiquete")) {
			tiqn = new JLabel("Tiquete: ");
		}else if (cual.equals("fastPass")) {
			tiqn = new JLabel("FastPass: ");
		}
		
		
		JPanel datos = new JPanel();
		datos.setLayout(new GridLayout(0, 2));
		JLabel tiq = new JLabel(info.get(1));
		JLabel fecn = new JLabel("Fecha de Expiracion: ");
		JLabel fec = new JLabel(info.get(2));
		JLabel valn = new JLabel("Valor: ");
		JLabel val = new JLabel(info.get(3));
		datos.add(tiqn);
		datos.add(tiq);
		datos.add(fecn);
		datos.add(fec);
		datos.add(valn);
		datos.add(val);
		datos.setOpaque(true);
		
		
		String exclusividad = info.get(4);
		JLabel imagen = new JLabel(new ImageIcon( "./data/imagenDiamante.png" ));
		if (exclusividad.equals("Oro")) {
			imagen = new JLabel(new ImageIcon( "./data/imagenOro.png" ));
		}else if (exclusividad.equals("Familiar")) {
			imagen = new JLabel(new ImageIcon( "./data/imagenFamilia.png" ));
		}
		imagen.setOpaque(true);
		
		
		
		add(nom, BorderLayout.NORTH);
		add(datos, BorderLayout.CENTER);
		add(imagen, BorderLayout.SOUTH);
		setOpaque(true);
		setVisible(true);
	}

}
