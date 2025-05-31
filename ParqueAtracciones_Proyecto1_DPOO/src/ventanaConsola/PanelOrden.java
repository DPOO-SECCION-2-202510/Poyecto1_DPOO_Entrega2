package ventanaConsola;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color; 
import java.awt.Font; 
import javax.swing.BorderFactory; 

import consola.ConsolaAdmin;
import consola.ConsolaCliente;
import consola.ConsolaEmpleado;
import consola.ConsolaMain;

@SuppressWarnings("serial")
public class PanelOrden extends JPanel implements ActionListener{
    
    private VentanaConsola ventana;
    private ConsolaMain consola;
    private JComboBox<String> opciones; 
    
    public PanelOrden(VentanaConsola ventana, ConsolaMain consola) {
        this.ventana = ventana;
        this.consola = consola;
        setBorder( BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), "Opciones", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.DARK_GRAY) ); // Borde con título y estilo
        setLayout( new BorderLayout( ) );
        setBackground(new Color(230, 230, 250)); 
        
        String[] ops = consola.getMenu();
        opciones = new JComboBox<>(ops);
        opciones.addActionListener(this);
        opciones.setBackground(Color.WHITE); 
        opciones.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JScrollPane scroll = new JScrollPane( opciones );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.setBorder(BorderFactory.createEmptyBorder());

        add( scroll, BorderLayout.CENTER );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<?> cb = (JComboBox<?>)e.getSource(); 
        String opcion = (String)cb.getSelectedItem();
        int op = Integer.parseInt(opcion.substring(0, 2).trim()); 
        
        if(consola instanceof ConsolaAdmin) {
            if(op==14) {
                ventana.cerrarSesion();
            } else {
                ventana.cambiarOpcion(op);
            }
        } else if(consola instanceof ConsolaCliente) {
            if(op==18) {
                ventana.cerrarSesion();
            } else {
                ventana.cambiarOpcion(op);
            }
        } else if(consola instanceof ConsolaEmpleado) {
            if(op==7) {
                ventana.cerrarSesion();
            } else {
                ventana.cambiarOpcion(op);
            }
        }
    }
}
