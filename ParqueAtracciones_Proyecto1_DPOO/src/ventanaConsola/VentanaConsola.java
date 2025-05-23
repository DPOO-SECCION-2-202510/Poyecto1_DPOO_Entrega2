package ventanaConsola;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import consola.ConsolaAdmin;
import consola.ConsolaCliente;
import consola.ConsolaEmpleado;
import consola.ConsolaMain;
import parque.ParqueAtraccion;

@SuppressWarnings("serial")
public class VentanaConsola extends JFrame{
	
	private ParqueAtraccion parque;
	
	private Panelinfo panelInfo;
	
	private Panelinput panelInput;
	
	private PanelOrden panelOrden;
	
	private ConsolaMain consola;

	public VentanaConsola(ParqueAtraccion parque, ConsolaMain consol) {
	
			
		this.consola = consol;
		this.panelInfo = new Panelinfo(consola);
		this.panelInput = new Panelinput(consola);
		this.panelOrden = new PanelOrden(this, consola);
		this.parque = parque;
		setLayout( new BorderLayout( ) );
		add(panelOrden, BorderLayout.NORTH);
		add(panelInput, BorderLayout.WEST);
		add(panelInfo, BorderLayout.EAST);
		
		
		setTitle( "Parque de Atracciones" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 600 );
        setLocationRelativeTo( null );
        setVisible( true );
	}
	
	public void cambiarOpcion(int opcion) {
		if(consola instanceof ConsolaAdmin) {
			ConsolaAdmin conso = (ConsolaAdmin) consola;
		}else if(consola instanceof ConsolaCliente) {
			ConsolaCliente conso = (ConsolaCliente) consola;
		}else if(consola instanceof ConsolaEmpleado) {
			ConsolaEmpleado conso = (ConsolaEmpleado) consola;
		}
	}


}
