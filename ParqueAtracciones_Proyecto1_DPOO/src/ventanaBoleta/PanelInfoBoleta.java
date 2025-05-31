package ventanaBoleta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font; 
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder; 
import javax.swing.BorderFactory; 

public class PanelInfoBoleta extends JPanel{

    private VentanaBoleta princial;

    public PanelInfoBoleta(VentanaBoleta ventana, String cual, List<String> info) {
        this.princial = ventana;
        setLayout(new BorderLayout(10, 10)); 
        setBackground(new Color(240, 248, 255)); 

      
        JLabel nom = new JLabel("<html><b>" + info.get(0) + "</b></html>", JLabel.CENTER);
        nom.setFont(new Font("Arial", Font.BOLD, 18)); 
        JPanel nomm = new JPanel();
        nomm.setBackground(new Color(224, 255, 255)); 
        nomm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        nomm.add(nom, BorderLayout.CENTER);

       
        JPanel datos = new JPanel();
        datos.setLayout(new GridLayout(0, 2, 5, 5)); 
        datos.setBackground(new Color(224, 255, 255)); 
        datos.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), "Detalles de la Boleta"), 
            new EmptyBorder(5, 5, 5, 5))); 

        JLabel tiqn = new JLabel("Tipo:");
        if (cual.equals("tiquete")) {
            tiqn = new JLabel("Tiquete:");
        } else if (cual.equals("fastPass")) {
            tiqn = new JLabel("FastPass:");
        }
        JLabel tiq = new JLabel("<html><b>" + info.get(1) + "</b></html>");
        JLabel fecn = new JLabel("Fecha de Expiracion:");
        JLabel fec = new JLabel("<html><b>" + info.get(2) + "</b></html>");
        JLabel valn = new JLabel("Valor:");
        JLabel val = new JLabel("<html><b>" + info.get(3) + "</b></html>");

        datos.add(tiqn);
        datos.add(tiq);
        datos.add(fecn);
        datos.add(fec);
        datos.add(valn);
        datos.add(val);

        String exclusividad = info.get(4);
        JLabel imagen = new JLabel();
        if (exclusividad.equals("Oro")) {
            imagen.setIcon(new ImageIcon("./data/imagenOro.png"));
        } else if (exclusividad.equals("Familiar")) {
            imagen.setIcon(new ImageIcon("./data/imagenFamilia.png"));
        } else { 
            imagen.setIcon(new ImageIcon("./data/imagenDiamante.png"));
        }
        imagen.setHorizontalAlignment(JLabel.CENTER);
        JPanel imagenPanel = new JPanel();
        imagenPanel.setBackground(new Color(224, 255, 255)); 
        imagenPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), "Nivel de Exclusividad"), 
            new EmptyBorder(5, 5, 5, 5))); 
        imagenPanel.add(imagen);

        add(nomm, BorderLayout.NORTH);
        add(datos, BorderLayout.CENTER);
        add(imagenPanel, BorderLayout.SOUTH);
        setBorder(new EmptyBorder(10, 10, 10, 10)); 
        setVisible(true);
    }
}
