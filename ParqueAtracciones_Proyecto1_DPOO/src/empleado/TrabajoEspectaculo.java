package empleado;
import juegos.Espectaculo;
import parque.Caja;
import parque.Espacio;

public class TrabajoEspectaculo extends Trabajo{

	private Espectaculo espectaculo;
	
	public TrabajoEspectaculo(String servicio, Espacio espacio, String descripcion, Caja caja,  String capacitacion, Espectaculo espectaculo){
		super(servicio, espacio, descripcion, caja, capacitacion);
		this.espectaculo = espectaculo;
		
	}
	
	public Espectaculo getEspectaculo(){
		return espectaculo;
	}
}
