package juegos;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDateTime;
import parque.Espacio;
import empleado.Empleado;

public class Atraccion implements IGetJuego{
	
	protected String nombre;

	private int cupoMax;
	
	private HashMap<String, Empleado> empleados;
	
	protected String exclusividad;
	
	protected Espacio espacio;
	
	private boolean funcionando;
	
	private ArrayList<LocalDateTime> temporada;
	
	private int minEmpleados;
	
	private String clima;
	
	
	public Atraccion(String nombre, int cupoMax, String exclusividad, Espacio espacio, ArrayList<LocalDateTime> temporada, int minEmpleados, String clima) {
		this.clima = clima;
		this.nombre = nombre;
		this.cupoMax= cupoMax;
		this.exclusividad =exclusividad;
		this.espacio = espacio;
		this.temporada = temporada;
		this.minEmpleados = minEmpleados;
		this.funcionando = false;
		this.empleados = new HashMap<String, Empleado>();
	}
	
	public String getInfo() {
		return null;
	}
	
	
	
	public int getCupoMax() {
		return cupoMax;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	public String getExclusividad() {
		return exclusividad;
	}
	
	public Espacio getEspacio() {
		return espacio;
	}
	
	public boolean getFuncionando(String climaA , LocalDateTime dia) {
		LocalDateTime ini = temporada.getFirst();
		LocalDateTime fin = temporada.getLast();
;		if (empleados.size() >= minEmpleados && climaA != clima && (dia.isAfter(ini)|| dia.isEqual(ini)) && (dia.isBefore(fin)||dia.isEqual(fin))) {
			funcionando = true;
		}
		return funcionando;
	}
	
	public boolean getFuncionando(String climaA) {
		funcionando = false;
		if (empleados.size() >= minEmpleados && climaA.contentEquals(clima)==false) {
			funcionando = true;
		}
		return funcionando;
	}
	
	
	public boolean getFuncionando(LocalDateTime dia) {
		funcionando = false;
		LocalDateTime ini = temporada.getFirst();
		LocalDateTime fin = temporada.getLast();
		if (empleados.size() >= minEmpleados && (dia.isAfter(ini)|| dia.isEqual(ini)) && (dia.isBefore(fin)||dia.isEqual(fin))) {
			funcionando = true;
		}
		return funcionando;
	}
	
	public boolean getFuncionando() {
		funcionando = false;
		if (empleados.size()>= minEmpleados ) {
			funcionando = true;
		}
		return funcionando;
	}

	
	public void masEmpleado(Empleado empl) {
		empleados.put(empl.getNombre(), empl);
	}
	
}
