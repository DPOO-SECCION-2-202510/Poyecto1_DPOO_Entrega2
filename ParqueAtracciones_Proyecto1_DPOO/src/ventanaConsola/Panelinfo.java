package ventanaConsola;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory; 
import javax.swing.border.EmptyBorder; 
import java.awt.Color;
import java.awt.Component; 
import java.awt.Dimension; 

import consola.ConsolaCliente;
import consola.ConsolaMain;
import ventanaBoleta.VentanaBoleta;

@SuppressWarnings("serial")
public class Panelinfo extends JPanel implements ActionListener{

    private ConsolaMain consola;
    private List<String> datosBoleta;
    private JButton boletaBoton;

    public Panelinfo(ConsolaMain consola) {
        this.datosBoleta = new ArrayList<String>();
        this.consola = consola;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 248, 255)); 
        setBorder(new EmptyBorder(10, 10, 10, 10)); 
        
        boletaBoton = new JButton("Ver Boleta");
        boletaBoton.addActionListener(this);
        boletaBoton.setAlignmentX(Component.CENTER_ALIGNMENT); 
        boletaBoton.setBackground(new Color(100, 149, 237)); 
        boletaBoton.setForeground(Color.WHITE); 
        boletaBoton.setFocusPainted(false); 
        
        mostrarInfo(new ArrayList<String>()); 
        setVisible(true);
    }

    public void mostrarInfo(List<String> inputs) {
        removeAll();
        List<JLabel> info = consola.getInfo(inputs);
        if (consola instanceof ConsolaCliente ) {
            if (consola.getOpcion()==15 || consola.getOpcion()==16 || consola.getOpcion()==17) {
                JLabel data = info.get(0);
                data.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1), 
                    new EmptyBorder(5, 5, 5, 5)));
                data.setBackground(Color.WHITE);
                data.setOpaque(true);
                data.setAlignmentX(Component.CENTER_ALIGNMENT);
                add(data);
                
                String[] datos  = info.get(1).getText().split(";");
                datosBoleta.clear();
                for (String d : datos) {
                    datosBoleta.add(d);
                }
                add(boletaBoton);
            } else {
                for (JLabel data: info) {
                    data.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1), 
                        new EmptyBorder(5, 5, 5, 5)));
                    data.setBackground(Color.WHITE);
                    data.setOpaque(true);
                    data.setAlignmentX(Component.CENTER_ALIGNMENT);
                    add(data);
                }
            }
        } else {
            for (JLabel data: info) {
                data.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1), 
                    new EmptyBorder(5, 5, 5, 5)));
                data.setBackground(Color.WHITE);
                data.setOpaque(true);
                data.setAlignmentX(Component.CENTER_ALIGNMENT);
                add(data);
            }
        }
        revalidate(); 
        repaint(); 
        setVisible(true);
    }

    public void falsoInput() {
        removeAll();
        JLabel no = new JLabel("Por favor ingrese los datos", JLabel.CENTER);
        no.setForeground(Color.RED);
        no.setFont(new Font("Arial", Font.BOLD, 14));
        no.setBorder(new EmptyBorder(20, 0, 20, 0)); 
        add(no);
        revalidate();
        repaint();
        setVisible(true);
    }

    public void limpiar() {
        removeAll();
        revalidate();
        repaint();
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