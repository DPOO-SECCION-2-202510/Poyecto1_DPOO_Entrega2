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

import consola.ConsolaAdmin;
import consola.ConsolaCliente;
import consola.ConsolaEmpleado;
import consola.ConsolaMain;


@SuppressWarnings("serial")
public class PanelOrden extends JPanel implements ActionListener{
	
	private VentanaConsola ventana;
	
	private ConsolaMain consola;
	
	private JComboBox opciones;
	

	public PanelOrden(VentanaConsola ventana, ConsolaMain consola) {
		this.ventana = ventana;
		this.consola = consola;
		setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new BorderLayout( ) );
        
        String[] ops = consola.getMenu();
        opciones = new JComboBox(ops);
        opciones.addActionListener(this);
        JScrollPane scroll = new JScrollPane( opciones );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );

        add( scroll );
 
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
        String opcion = (String)cb.getSelectedItem();
		int op = Integer.parseInt(opcion.substring(0, 2));
		if(consola instanceof ConsolaAdmin) {
			if(op==14) {
				ventana.cerrarSesion();
			}else {
				ventana.cambiarOpcion(op);
			}
		}else if(consola instanceof ConsolaCliente) {
			if(op==18) {
				ventana.cerrarSesion();
			}else {
				ventana.cambiarOpcion(op);
			}
		}else if(consola instanceof ConsolaEmpleado) {
			if(op==7) {
				ventana.cerrarSesion();
			}else {
				ventana.cambiarOpcion(op);
			}
		}
		
	}
	
	

}
