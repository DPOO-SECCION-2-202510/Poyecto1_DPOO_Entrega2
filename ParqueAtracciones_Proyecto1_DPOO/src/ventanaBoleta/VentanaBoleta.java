package ventanaBoleta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font; 
import java.awt.GridLayout; 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder; 

public class VentanaBoleta extends JFrame{

    private PanelInfoBoleta infoP;
    private PanelQR qrP;

    public VentanaBoleta(String cual, List<String> info, LocalDateTime dia) {
        setLayout( new BorderLayout(10, 10) ); 
        setBackground(new Color(240, 248, 255)); 

        infoP = new PanelInfoBoleta( this, cual, info);
        add(infoP, BorderLayout.WEST );

        qrP = new PanelQR( this, cual, info);
        add(qrP, BorderLayout.EAST);

        JPanel general = new JPanel();
        general.setLayout(new GridLayout(3, 1, 0, 5)); 
        general.setBackground(new Color(173, 216, 230)); 
        general.setBorder(new EmptyBorder(10, 10, 10, 10)); 

        JLabel tituloParque = new JLabel("Parque de Atracciones", JLabel.CENTER);
        tituloParque.setFont(new Font("Serif", Font.BOLD, 24)); 
        tituloParque.setForeground(Color.WHITE); 

        JLabel imagenParque = new JLabel(new ImageIcon("./data/imagen.png"), JLabel.CENTER); 

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String diaS = dia.format(formatter);
        JLabel fechaActual = new JLabel(diaS, JLabel.CENTER);
        fechaActual.setFont(new Font("Arial", Font.PLAIN, 14));
        fechaActual.setForeground(Color.WHITE);

        general.add(tituloParque);
        general.add(imagenParque);
        general.add(fechaActual);

        add(general, BorderLayout.CENTER);

        setTitle( cual );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 800, 400 ); 
        setLocationRelativeTo( null );
        setVisible( true );
    }
}