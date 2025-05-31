package administrador;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDateTime;
import empleado.Trabajo;
import empleado.TrabajoAtraccion;
import empleado.TrabajoEspectaculo;
import empleado.Empleado;


public class Admin {
	
	private String nombre;
	
	private LocalDateTime hoy;
	
	private int id;
	
	private HashMap<String, ArrayList<Empleado>> empleados;
	
	
	private HashMap<String, ArrayList<Trabajo>>  trabajosGenerales;
	
	private HashMap<String, ArrayList<TrabajoAtraccion>> trabajosAtraccion;
	
	private HashMap<String, ArrayList<TrabajoEspectaculo>> trabajosEspectaculo;
	
	private HashMap<String, Trabajo> turnosApertura;
	
	private HashMap<String, Trabajo> turnosCierre;
	
	public AsignarTrabajo asignar;
	
	public MoverEmpleado mover;
	
	public Diario diario;
	
	
	public Admin(String nombre, int id, ArrayList<Trabajo> trabajosGeneral, ArrayList<TrabajoAtraccion> atracciones, ArrayList<TrabajoEspectaculo> espectaculos) {
		this.empleados = new HashMap<String, ArrayList<Empleado>>();
		//poner una lista por nivel de capacitacion;
		HashMap<String, ArrayList<Trabajo>> trabajosGenerales = getMapaCapacitacion(trabajosGeneral);
		HashMap<String, ArrayList<TrabajoAtraccion>> atraccion = getMapaCapacitacionA(atracciones);
		HashMap<String, ArrayList<TrabajoEspectaculo>> espectaculo = getMapaCapacitacionB(espectaculos);
		this.trabajosAtraccion = atraccion;
		this.trabajosEspectaculo = espectaculo;
		this.trabajosGenerales = trabajosGenerales;
		this.nombre = nombre;
		this.id = id;
		this.hoy = LocalDateTime.now();
		this.asignar = new AsignarTrabajo(this, empleados, atraccion, espectaculo, trabajosGenerales, hoy);
		this.mover = new MoverEmpleado(empleados, nombre);
		this.turnosApertura = asignar.turnosApertura;
		this.turnosCierre = asignar.turnosCierre;
		this.diario = asignar.guardarTurnos();
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	public int getId() {
		return id;
	}
	
	public void setApertura(HashMap<String, Trabajo> apertura) {
		this.turnosApertura = apertura;
	}
	
	public void setCierre(HashMap<String, Trabajo> cierre) {
		this.turnosCierre = cierre;
	}
	
	public int getNumEmpleados() {
		int numEmpleados = 0;
		for (ArrayList<Empleado> niv: empleados.values()) {
			numEmpleados = numEmpleados+niv.size();
		}
		return numEmpleados;
	}
	
	public HashMap<String, Trabajo> getTurnosApertura(){
		return turnosApertura;
	}
	
	public HashMap<String, Trabajo> getTurnosCierre(){
		return turnosCierre;
	}
	
	public int getTurnosAsignados() {
		return turnosApertura.size()+turnosCierre.size();
	}
	
	public ArrayList<Empleado> getEmpleados(){
		ArrayList<Empleado> todos = new ArrayList<Empleado>();
		for (ArrayList<Empleado> porNivel: empleados.values()) {
			todos.addAll(porNivel);
		}
		return todos;
	}
	
	public Empleado getEmpleado(String nombre) {
		Empleado este= null;
		for (ArrayList<Empleado> porNivel: empleados.values()) {
			for (Empleado empleado : porNivel) {
				if (este == null && nombre.contentEquals(empleado.getNombre())) {
					este = empleado;
				}
			}
		}
		return este;
	}
	
	
	public HashMap<String, Integer> getNumTrabajos() {
		HashMap<String, Integer> numero = new HashMap<String, Integer>();
		for (String nivel: trabajosGenerales.keySet()) {
			numero.put(nivel, trabajosGenerales.get(nivel).size());
		}
		for (String nivel: trabajosAtraccion.keySet()) {
			if (numero.containsKey(nivel)) {
				int num = numero.get(nivel) + trabajosAtraccion.get(nivel).size();
				numero.replace(nivel, num);
			}else{
				numero.put(nivel, trabajosAtraccion.get(nivel).size());
			}
		}
		for (String nivel: trabajosEspectaculo.keySet()) {
			if (numero.containsKey(nivel)) {
				int num = numero.get(nivel) + trabajosEspectaculo.get(nivel).size();
				numero.replace(nivel, num);
			}else{
				numero.put(nivel, trabajosEspectaculo.get(nivel).size());
			}
		}
		return numero;
	}
	
	public Trabajo getTrabajoGen(String nombre) {
		Trabajo cual = null;
		for (String niv: trabajosGenerales.keySet()) {
			for(Trabajo tr: trabajosGenerales.get(niv)) {
				if (tr.getServicio()== nombre) {
					cual = tr;
				}
			}
		}
		return cual;
	}
	
	public TrabajoAtraccion getTrabajoA(String nombre) {
		TrabajoAtraccion cual = null;
		for (String niv: trabajosAtraccion.keySet()) {
			for(TrabajoAtraccion tr: trabajosAtraccion.get(niv)) {
				if (tr.getServicio()== nombre) {
					cual = tr;
				}
			}
		}
		return cual;
	}
	
	public TrabajoEspectaculo getTrabajoEsp(String nombre) {
		TrabajoEspectaculo cual = null;
		for (String niv: trabajosEspectaculo.keySet()) {
			for(TrabajoEspectaculo tr: trabajosEspectaculo.get(niv)) {
				if (tr.getServicio()== nombre) {
					cual = tr;
				}
			}
		}
		return cual;
	}
	
	public ArrayList<Trabajo> getTrabajosSinAsignar(){
		ArrayList<Trabajo> sinAsignar = new ArrayList<Trabajo>();
		for (ArrayList<Trabajo> niv : trabajosGenerales.values()) {
			for(Trabajo tr: niv) {
				if (turnosApertura.containsValue(tr)==false && turnosCierre.containsValue(tr)==false) {
					sinAsignar.add(tr);
				}
			}
		}
		for (ArrayList<TrabajoAtraccion> niv : trabajosAtraccion.values()) {
			for(Trabajo tr: niv) {
				if (turnosApertura.containsValue(tr)==false && turnosCierre.containsValue(tr)==false) {
					sinAsignar.add(tr);
				}
			}
		}
		for (ArrayList<TrabajoEspectaculo> niv : trabajosEspectaculo.values()) {
			for(Trabajo tr: niv) {
				if (turnosApertura.containsValue(tr)==false && turnosCierre.containsValue(tr)==false) {
					sinAsignar.add(tr);
				}
			}
		}
		return sinAsignar;
	}
	
	public int getUtilidad() {
		int util = 0;
		for (String niv : trabajosGenerales.keySet()) {
			for(Trabajo tr: trabajosGenerales.get(niv)) {
				util=util + tr.getUtilidad();
			}
		}
		for (String niv : trabajosAtraccion.keySet()) {
			for(Trabajo tr: trabajosAtraccion.get(niv)) {
				util=util + tr.getUtilidad();
			}
		}
		for (String niv : trabajosEspectaculo.keySet()) {
			for(Trabajo tr: trabajosEspectaculo.get(niv)) {
				util=util + tr.getUtilidad();
			}
		}
		return util;
	}
	
	
	
	
	
	
	
	private HashMap<String, ArrayList<Trabajo>>  getMapaCapacitacion(ArrayList<Trabajo> listaTrabajos){
		HashMap<String, ArrayList<Trabajo>> trabajos = new HashMap<String, ArrayList<Trabajo>> ();
		for (Trabajo trabajo: listaTrabajos) {
			String nivel = trabajo.getCapaciacion();
			ArrayList<Trabajo> listaTrabajo = new ArrayList<Trabajo>();
			if (trabajos.containsKey(nivel)){
				listaTrabajo = trabajos.get(nivel);
			}
			listaTrabajo.add(trabajo);
			trabajos.put(nivel, listaTrabajo);
		}
		return trabajos;
	}
	
	private HashMap<String, ArrayList<TrabajoAtraccion>>  getMapaCapacitacionA(ArrayList<TrabajoAtraccion> listaTrabajos){
		HashMap<String, ArrayList<TrabajoAtraccion>> trabajos = new HashMap<String, ArrayList<TrabajoAtraccion>> ();
		for (TrabajoAtraccion trabajo: listaTrabajos) {
			String nivel = trabajo.getCapaciacion();
			ArrayList<TrabajoAtraccion> listaTrabajo = new ArrayList<TrabajoAtraccion>();
			if (trabajos.containsKey(nivel)){
				listaTrabajo=trabajos.get(nivel);
			}
			listaTrabajo.add(trabajo);
			trabajos.put(nivel, listaTrabajo);
		}
		return trabajos;
	}
	
	private HashMap<String, ArrayList<TrabajoEspectaculo>>  getMapaCapacitacionB(ArrayList<TrabajoEspectaculo> listaTrabajos){
		HashMap<String, ArrayList<TrabajoEspectaculo>> trabajos = new HashMap<String, ArrayList<TrabajoEspectaculo>> ();
		for (TrabajoEspectaculo trabajo: listaTrabajos) {
			String nivel = trabajo.getCapaciacion();
			ArrayList<TrabajoEspectaculo> listaTrabajo = new ArrayList<TrabajoEspectaculo>();
			if (trabajos.containsKey(nivel)){
				listaTrabajo = trabajos.get(nivel);
			}
			listaTrabajo.add(trabajo);
			trabajos.put(nivel, listaTrabajo);
		}
		return trabajos;
	}
	
	
	public String revisarTrabajo(String nombre) {
		String cual = null;
		for (String niv: trabajosGenerales.keySet()) {
			for(Trabajo tr: trabajosGenerales.get(niv)) {
				if (tr.getServicio()== nombre) {
					cual = "general";
				}
			}
		}
		for (String niv: trabajosAtraccion.keySet()) {
			for(Trabajo tr: trabajosAtraccion.get(niv)) {
				if (tr.getServicio()== nombre) {
					cual = "atraccion";
				}
			}
		}
		for (String niv: trabajosEspectaculo.keySet()) {
			for(Trabajo tr: trabajosEspectaculo.get(niv)) {
				if (tr.getServicio()== nombre) {
					cual = "espectaculo";
				}
			}
		}
		return cual;
	}
	
	
	

}
