package ventanaBoleta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory; 
import javax.swing.border.EmptyBorder; 
import java.util.List;

@SuppressWarnings("serial")
public class PanelQR extends JPanel{


    public PanelQR(VentanaBoleta origen, String cual, List<String> info) {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255)); 

        String data = "Tiquete: "+info.get(4)+"\nID: " +info.get(0)+ "\nFecha: "+info.get(2);
        creadorQR creador = new creadorQR();
        String donde = creador.crearQr(data);
        
        JLabel imagen = new JLabel();
        if (donde != null) {
            ImageIcon originalIcon = new ImageIcon(donde);
            Image originalImage = originalIcon.getImage();
            int desiredWidth = 150; 
            int desiredHeight = 150; 
            Image scaledImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            imagen.setIcon(scaledIcon);
        } else {
            imagen.setText("Error al generar QR");
        }
        imagen.setHorizontalAlignment(JLabel.CENTER); 

        JPanel qrPanel = new JPanel(new BorderLayout()); 
        qrPanel.setBackground(new Color(224, 255, 255));
        qrPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), "Código QR"), 
            new EmptyBorder(10, 10, 10, 10))); 
        qrPanel.add(imagen, BorderLayout.CENTER);

        add(qrPanel, BorderLayout.CENTER);
        setBorder(new EmptyBorder(10, 10, 10, 10)); 
        setVisible(true);
    }
}