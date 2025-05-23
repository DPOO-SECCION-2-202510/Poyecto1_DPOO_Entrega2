package ventanaBoleta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VentanaBoleta extends JFrame{
	
	private PanelInfoBoleta infoP;
	
	private PanelQR qrP;

	public VentanaBoleta(String cual, List<String> info, LocalDateTime dia) {
        setLayout( new BorderLayout() );
        infoP = new PanelInfoBoleta( this, cual, info);
        add(infoP, BorderLayout.WEST );
        qrP = new PanelQR( this, cual, info);
        add(qrP, BorderLayout.EAST);
        
        JPanel general = new JPanel();
        general.setBackground(Color.CYAN);
        general.add(new JLabel("Parque de Atracciones"), BorderLayout.NORTH);
        general.add(new JLabel(new ImageIcon( "./data/imagen.png" )), BorderLayout.CENTER);
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String diaS = dia.format(formatter);
        general.add(new JLabel(diaS), BorderLayout.SOUTH);

        add(general,  BorderLayout.CENTER);
        setTitle( cual );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 700, 300 );
        setBackground(Color.CYAN);
        setLocationRelativeTo( null );
        setVisible( true );
        
        
	}
	
	
	
	public static void main( String[] args ) {
		String cual = "";
		List<String> info = new ArrayList<String>();
		info.add("112");
		info.add("kmkml");
		info.add("1pokphgiu12");
		info.add("ddddd");
		LocalDateTime dia = LocalDateTime.now();
		new VentanaBoleta(cual, info, dia);
	}

}
