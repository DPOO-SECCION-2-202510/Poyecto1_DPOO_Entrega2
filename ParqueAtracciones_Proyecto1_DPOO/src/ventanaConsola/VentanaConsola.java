package ventanaConsola;

import java.awt.BorderLayout;
import java.awt.Color; 
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JFrame;

import consola.ConsolaMain;
import exceptions.ExceptionInputIncorrecto;
import parque.ParqueAtraccion;
import ventanaBoleta.VentanaBoleta;

@SuppressWarnings("serial")
public class VentanaConsola extends JFrame{
    
    private Panelinfo panelInfo;
    private Panelinput panelInput;
    private PanelOrden panelOrden;
    private ConsolaMain consola;

    public VentanaConsola(ParqueAtraccion parque, ConsolaMain consol) {
        this.consola = consol;
        this.panelInfo = new Panelinfo(this.consola);
        this.panelInput = new Panelinput(this.consola, this);
        this.panelOrden = new PanelOrden(this, this.consola);
        
        setLayout( new BorderLayout(10, 10) ); 
        setBackground(new Color(240, 248, 255)); 
        
        add(panelOrden, BorderLayout.NORTH);
        add(panelInput, BorderLayout.WEST);
        add(panelInfo, BorderLayout.EAST);
        
        setTitle( "Parque de Atracciones - Consola" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 800, 600 ); 
        setLocationRelativeTo( null );
        setVisible( true );
    }
    
    public void cambiarOpcion(int opcion) {
        consola.canbiarOpcion(opcion);
        this.panelInput.mostrarInputs();
        panelInfo.limpiar();
        pack(); 
        repaint(); 
    }

    public void mostrarTodo() {
        List<String> inputs;
        try {
            inputs = panelInput.getInputs();
            this.panelInfo.mostrarInfo(inputs);
            pack();
            repaint();
        } catch (ExceptionInputIncorrecto e) {
            this.panelInfo.falsoInput();
            pack();
            repaint();
        }
    }
    
    public void mostrarBoleta(String cual, List<String> info, LocalDateTime dia) {
        VentanaBoleta hola = new VentanaBoleta(cual, info, dia);
    }
    
    public void cerrarSesion() {
        dispose();
    }
}