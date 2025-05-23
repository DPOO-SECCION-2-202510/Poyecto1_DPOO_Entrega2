package ventanaConsola;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;

import consola.ConsolaMain;


@SuppressWarnings("serial")
public class PanelOrden extends JPanel implements ListSelectionListener{
	
	private VentanaConsola ventana;
	
	private ConsolaMain consola;
	
	private JList<String> opciones;
	
	private DefaultListModel<String> dataModel;

	public PanelOrden(VentanaConsola ventana, ConsolaMain consola) {
		this.ventana = ventana;
		this.consola = consola;
		setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new BorderLayout( ) );
        
        dataModel = new DefaultListModel<>( );
        String[] ops = consola.getMenu();
        for (String op: ops) {
        	dataModel.addElement(op);
        }
        opciones = new JList<>( dataModel );
        opciones.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        opciones.addListSelectionListener( this );
        JScrollPane scroll = new JScrollPane( opciones );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );

        add( scroll );
 
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String opcion = opciones.getSelectedValue();
		int op = Integer.parseInt(opcion.substring(0, 1));
		consola.canbiarOpcion(op);
	
	}
	
	public void seleccionarOpcion( String opcion )
    {
        opciones.setSelectedValue( opcion, true );
        
    }

}
