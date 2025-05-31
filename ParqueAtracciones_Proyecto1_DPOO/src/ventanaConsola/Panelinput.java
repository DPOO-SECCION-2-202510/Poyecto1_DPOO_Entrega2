package ventanaConsola;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory; 
import javax.swing.border.EmptyBorder; 
import java.awt.Color;
import java.awt.Font; 
import java.awt.Dimension; 

import consola.ConsolaMain;
import exceptions.ExceptionInputIncorrecto;

@SuppressWarnings("serial")
public class Panelinput extends JPanel implements ActionListener{
    
    private ConsolaMain consola;
    private List<JTextField> inputs;
    private JButton boton;
    private VentanaConsola parque;
    private boolean ya;
    
    public Panelinput(ConsolaMain consola, VentanaConsola parque) {
        this.consola = consola;
        this.parque = parque;
        this.inputs = new ArrayList<JTextField>();
        this.ya = false;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 248, 255)); 
        setBorder(new EmptyBorder(10, 10, 10, 10)); 
        
        mostrarInputs();
        this.boton = new JButton("Enter"); 
        this.boton.addActionListener(this);
        this.boton.setBackground(new Color(60, 179, 113)); 
        this.boton.setForeground(Color.WHITE);
        this.boton.setFocusPainted(false);
        this.boton.setAlignmentX(CENTER_ALIGNMENT); 
        
        if (ya) { 
             add(boton);
        }
        setVisible(true);
        ya = true;
    }

    public void mostrarInputs() {
        removeAll();
        inputs.clear(); 
        List<JPanel> ordenes = consola.getInput();
        for (JPanel op : ordenes) {
            op.setBackground(new Color(224, 255, 255));
            op.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1), 
                new EmptyBorder(5, 5, 5, 5)));
            op.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); 

            JTextField input = (JTextField) op.getComponent(1);
            input.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            input.setFont(new Font("Arial", Font.PLAIN, 12));
            input.setPreferredSize(new Dimension(150, 25)); 
            inputs.add(input);
            add(op);
        }
        if (ya) { 
            add(boton);
        }
        revalidate(); 
        repaint(); 
        setVisible(true);
    }
    
    public List<String> getInputs() throws ExceptionInputIncorrecto{
        List<String> usuarioInput = new ArrayList<String>();
        for (JTextField in : inputs) {
            String op = in.getText();
            if (op.isBlank()) {
                throw new ExceptionInputIncorrecto();
            }
            usuarioInput.add(op);
        }
        return usuarioInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        parque.mostrarTodo();
    }
}
