package juegos;
import java.util.HashMap;
import java.time.LocalDateTime;
import parque.Espacio;

public class Espectaculo {

	private HashMap<LocalDateTime, Espacio> horarios;
	
	private String nombre;
	
	private String descripcion;
	
	private String clima;
	
	private String temporada;
	
	private boolean funcionando;
	
	private int duracionMin;
	
	
	public Espectaculo (HashMap<LocalDateTime, Espacio> horarios, String nombre, String descripcion, String temporada, String clima, int duracionMin) {
		this.clima = clima;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.horarios = horarios;
		this.temporada = temporada;
		this.funcionando = false;
		this.duracionMin = duracionMin;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getInfo() {
		LocalDateTime ahora = LocalDateTime.now();
		String info = "nombre: "+nombre+";Descripcion:"+descripcion+";Disponible en: "+temporada+";Dura: "+duracionMin+";Siguente funcion: "+getSiguenteFuncion(ahora);
		return info;
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}
	
	
	public int getDuracion() {
		return duracionMin;
	}
	
	
	public HashMap<LocalDateTime, Espacio> getHorarios(){
		return horarios;
	}
	
	public Espacio getEspacio(LocalDateTime fecha) {
		return horarios.get(fecha);
	}
	
	public boolean enFuncionamiento(LocalDateTime hora, String climaA) {
		funcionando = false;
		if (clima!= climaA ) {
			for (LocalDateTime fecha : horarios.keySet()) {
				if (fecha.equals(hora)) {
					funcionando = true;
				} else if (hora.isAfter(fecha) && fecha.minusMinutes(duracionMin).isAfter(hora)) {
					funcionando = true;
				}
			}
			
		}
		return funcionando;
	}
	
	public boolean enFuncionamiento(LocalDateTime hora, String climaA, String temporadaA) {
		funcionando = false;
		if (clima!= climaA && temporadaA==temporada) {
			for (LocalDateTime fecha : horarios.keySet()) {
				if (fecha.equals(hora)) {
					funcionando = true;
				} else if (hora.isAfter(fecha) && fecha.minusMinutes(duracionMin).isAfter(hora)) {
					funcionando = true;
				}
			}
			
		}
		return funcionando;
	}
	
	public LocalDateTime getSiguenteFuncion(LocalDateTime hora) {
		LocalDateTime siguiente = null;
		for (LocalDateTime fecha : horarios.keySet()) {
			if (siguiente == null && fecha.isAfter(hora)) {
				siguiente = fecha;
			}
		}
		return siguiente;
	}
	
	
}
