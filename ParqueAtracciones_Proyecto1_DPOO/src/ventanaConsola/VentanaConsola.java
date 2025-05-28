package ventanaConsola;

import java.awt.BorderLayout;
import java.util.List;

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
		this.panelInfo = new Panelinfo(this.consola);
		this.panelInput = new Panelinput(this.consola);
		this.panelOrden = new PanelOrden(this, this.consola);
		this.parque = parque;
		setLayout( new BorderLayout( ) );
		add(panelOrden, BorderLayout.NORTH);
		add(panelInput, BorderLayout.WEST);
		add(panelInfo, BorderLayout.EAST);
		
		
		setTitle( "Parque de Atracciones" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 600 );
        pack( );
        setLocationRelativeTo( null );
        setVisible( true );
	}
	
	public void cambiarOpcion(int opcion) {
		if(consola instanceof ConsolaAdmin) {
			ConsolaAdmin conso = (ConsolaAdmin) consola;
			conso.canbiarOpcion(opcion);
			panelInput.mostrarInputs();
			List<String> inputs = panelInput.getInputs();
			panelInfo.mostrarInfo(inputs);
		}else if(consola instanceof ConsolaCliente) {
			ConsolaCliente conso = (ConsolaCliente) consola;
			conso.canbiarOpcion(opcion);
			panelInput.mostrarInputs();
			List<String> inputs = panelInput.getInputs();
			panelInfo.mostrarInfo(inputs);
		}else if(consola instanceof ConsolaEmpleado) {
			ConsolaEmpleado conso = (ConsolaEmpleado) consola;
			conso.canbiarOpcion(opcion);
			panelInput.mostrarInputs();
			List<String> inputs = panelInput.getInputs();
			panelInfo.mostrarInfo(inputs);
		}
	}
	

}
