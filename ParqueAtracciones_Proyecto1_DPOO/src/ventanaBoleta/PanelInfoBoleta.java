package ventanaBoleta;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image; 
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;

@SuppressWarnings("serial")
public class PanelInfoBoleta extends JPanel {

    private VentanaBoleta princial;

    public PanelInfoBoleta(VentanaBoleta ventana, String cual, List<String> info) {
        this.princial = ventana;
        setLayout(null); 
        setBackground(new Color(245, 222, 179)); 
        setBorder(new EmptyBorder(10, 10, 10, 10)); 

        
        JLabel noLabel = new JLabel("No.");
        noLabel.setFont(new Font("Arial", Font.BOLD, 14));
        noLabel.setBounds(20, 20, 50, 20); 
        add(noLabel);

        JLabel numeroBoletaLabel = new JLabel(info.get(1));
        numeroBoletaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        numeroBoletaLabel.setBounds(60, 20, 150, 20); 
        add(numeroBoletaLabel);

        JLabel tipoTiqueteTitulo = new JLabel("Tiquete:");
        tipoTiqueteTitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        tipoTiqueteTitulo.setBounds(20, 50, 80, 20); 
        add(tipoTiqueteTitulo);

        JLabel tipoTiqueteValor = new JLabel(info.get(0)); 
        tipoTiqueteValor.setFont(new Font("Arial", Font.BOLD, 12));
        tipoTiqueteValor.setBounds(110, 50, 150, 20); 
        add(tipoTiqueteValor);

        JLabel fechaExpTitulo = new JLabel("Fecha Exp.:");
        fechaExpTitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        fechaExpTitulo.setBounds(20, 75, 80, 20); 
        add(fechaExpTitulo);

        JLabel fechaExpValor = new JLabel(info.get(2)); 
        fechaExpValor.setFont(new Font("Arial", Font.BOLD, 12));
        fechaExpValor.setBounds(110, 75, 150, 20); 
        add(fechaExpValor);

        JLabel valorTitulo = new JLabel("Valor:");
        valorTitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        valorTitulo.setBounds(20, 100, 80, 20);
        add(valorTitulo);

        JLabel valorValor = new JLabel(info.get(3)); 
        valorValor.setFont(new Font("Arial", Font.BOLD, 12));
        valorValor.setBounds(110, 100, 150, 20); 
        add(valorValor);

        String exclusividad = info.get(4); 
        ImageIcon originalIcon = null;
        String imagePath = null;

        if (exclusividad.equals("Oro")) {
            imagePath = "./data/imagenOro.png"; 
        } else if (exclusividad.equals("Familiar")) {
            imagePath = "./data/familia.png"; 
        } else { 
            imagePath = "./data/imagenDiamante.png"; 
        }

        if (imagePath != null) {
            originalIcon = new ImageIcon(imagePath);
            if (originalIcon.getImageLoadStatus() == java.awt.MediaTracker.COMPLETE) {
                int desiredWidth = 50;
                int desiredHeight = 50; 
                Image scaledImage = originalIcon.getImage().getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                JLabel imagenExclusividad = new JLabel(scaledIcon);
                imagenExclusividad.setBounds(20, 180, desiredWidth, desiredHeight); 
                add(imagenExclusividad);
            } else {
                JLabel errorLabel = new JLabel("Img no cargada");
                errorLabel.setForeground(Color.RED);
                errorLabel.setBounds(20, 180, 100, 20);
                add(errorLabel);
            }
        }

        setPreferredSize(new java.awt.Dimension(250, 250)); 
        setVisible(true);
    }
}
