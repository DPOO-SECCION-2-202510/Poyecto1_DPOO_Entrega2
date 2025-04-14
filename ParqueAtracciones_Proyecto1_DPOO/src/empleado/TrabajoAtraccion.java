package empleado;
import java.util.LinkedList;
import java.util.ArrayList;
import juegos.Atraccion;
import juegos.AtraccionMecanica;
import juegos.AtraccionCultural;
import parque.Caja;
import parque.Espacio;
import financiero.Tiquete;
import exceptions.ExceptionInputIncorrecto;

public class TrabajoAtraccion extends Trabajo{

	private Atraccion atraccion;
	
	private LinkedList<Tiquete> tiquetesUsados;
	
	
	public TrabajoAtraccion(String servicio, Espacio espacio, String descripcion, Caja caja,   String capacitacion,  Atraccion atraccion) {
		super(servicio, espacio,  descripcion,  caja, capacitacion);
		this.atraccion = atraccion;
		this.tiquetesUsados = new LinkedList<Tiquete>();
	}
	
	public Atraccion getAtraccion() {
		return atraccion;
	}
	
	
	public void registrarTiquete(Tiquete tiquete, int altura, int peso, int edad, ArrayList<String> contraindicaciones) throws ExceptionInputIncorrecto {
		if (tiquete.getUsado()==false) {
			if (atraccion instanceof AtraccionMecanica) {  
			    AtraccionMecanica mecanica = (AtraccionMecanica) atraccion;  
			    if (mecanica.puedeEntrar(peso, altura, contraindicaciones)) {
			    	tiquetesUsados.add(tiquete);
			    }
			}
			if (atraccion instanceof AtraccionCultural) {  
			    AtraccionCultural cultural = (AtraccionCultural) atraccion;  
			    if (cultural.puedeEntrar(edad)) {
			    	tiquetesUsados.add(tiquete);
			    }}
			tiquete.usar();
		}else {
			throw new ExceptionInputIncorrecto("El tiquete ya fue usado");
		}
	}
	
	
}
