package empleado;
import java.util.Set;
import java.util.HashMap;
import parque.Caja;
import parque.Espacio;

public class Trabajo {
	
	private String servicio;
	
	private Espacio espacio;
	
	private String descripcion;
	
	private Caja caja;
	
	private String capacitacion;
	
	private HashMap<String, Integer> ventas;
	
	
	public Trabajo(String servicio, Espacio espacio, String descripcion, Caja caja, String capacitacion) {
		this.caja = caja;
		this.descripcion = descripcion;
		this.espacio = espacio;
		this.servicio = servicio;
		this.ventas = new HashMap<String, Integer>();
		this.capacitacion = capacitacion;
		
	}
	
	public String getCapaciacion() {
		return capacitacion;
	}
	
	public String getServicio() {
		return servicio;
	}
	
	public Espacio getEspacio() {
		return espacio;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	
	public Set<String> getInventario(){
		return caja.getInventario();
	}
	
	public HashMap<String, Integer> getVentas(){
		return ventas;
	}
	
	public int getUtilidad() {
		int util = 0;
		for(int venta: ventas.values()) {
			util= util+venta;
		}
		return util;
	}
	
	public void vender(String item, int cantidad) {
		if (caja.getInventario().contains(item)){
			caja.vender(item, cantidad);
		}
	}
	
	
	
	
	
	
	
	
	

}
