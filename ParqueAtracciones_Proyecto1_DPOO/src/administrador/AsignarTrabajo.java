package administrador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import empleado.Empleado;
import empleado.Trabajo;
import empleado.TrabajoAtraccion;
import empleado.TrabajoEspectaculo;

public class AsignarTrabajo {
	
	
	public Admin dueno;
	
	public LocalDateTime hoy;
	
	public HashMap<String, ArrayList<Empleado>> empleados;
	
	private HashMap<String, ArrayList<Trabajo>>  trabajosGenerales;
	
	private HashMap<String, ArrayList<TrabajoAtraccion>> trabajosAtraccion;
	
	private HashMap<String, ArrayList<TrabajoEspectaculo>> trabajosEspectaculo;
	
	public HashMap<String, Trabajo> turnosApertura;
	
	public HashMap<String, Trabajo> turnosCierre;
	

	public AsignarTrabajo(Admin dueno, HashMap<String, ArrayList<Empleado>> empleados, HashMap<String, ArrayList<TrabajoAtraccion>> ta, HashMap<String, ArrayList<TrabajoEspectaculo>> te, HashMap<String, ArrayList<Trabajo>> tg, LocalDateTime hoy) {
		this.empleados = empleados;
		this.dueno = dueno;
		this.trabajosAtraccion = ta;
		this.trabajosEspectaculo = te;
		this.trabajosGenerales = tg;
		this.turnosApertura = new HashMap<String, Trabajo>();
		this.turnosCierre = new HashMap<String, Trabajo>();
		this.hoy = hoy;
	}
	
	
	
	public void asignarTrabajo(Trabajo trabajo, Empleado empleado, LocalDateTime turno) {
		if (turno.getHour()==16) {
			turnosCierre.put(empleado.getNombre(), trabajo);
		} else {
			turnosApertura.put(empleado.getNombre(), trabajo);
		}
		empleado.anadirTrabajo(turno, trabajo);
	}
	
	public void asignarTodos() {
		asignarTrabajosAtraccion();
		asignarTrabajosGenerales();
		asignarTrabajosEspectaculo();
	}
	
	
	private void asignarTrabajosGenerales() {
		for (String nivel: empleados.keySet()) {
			if (trabajosGenerales.containsKey(nivel)) {
				ArrayList<Trabajo> trabajosDisponibles = trabajosGenerales.get(nivel);
				for (Trabajo trabajo: trabajosDisponibles) {
					int n = 0;
					LocalDateTime ini = LocalDateTime.of(hoy.getYear(),hoy.getMonth(),hoy.getDayOfMonth(),11,00);
					LocalDateTime fin = LocalDateTime.of(hoy.getYear(),hoy.getMonth(),hoy.getDayOfMonth(),16,00);
					boolean encontradoA =false;
					boolean encontradoB =false;
					while (encontradoA == false && n< empleados.get(nivel).size()) {
						Empleado empleado = empleados.get(nivel).get(n);
						if (empleado.getHorarios().keySet().contains(ini) == false) {
							asignarTrabajo(trabajo, empleado, ini);
							encontradoA = true;
						} 
						n=n+1;
					}
					n=0;
					while (encontradoB == false && n< empleados.get(nivel).size()) {
						Empleado empleado = empleados.get(nivel).get(n);
						if (empleado.getHorarios().keySet().contains(fin) == false) {
							asignarTrabajo(trabajo, empleado, fin);
							encontradoB = true;
						}
						n=n+1;
					}
				}
			}
		}
	}
	
	
	private void asignarTrabajosEspectaculo() {
		for (String nivel: empleados.keySet()) {
			if (trabajosEspectaculo.containsKey(nivel)) {
				ArrayList<TrabajoEspectaculo> trabajosDisponibles = trabajosEspectaculo.get(nivel);
				for (TrabajoEspectaculo trabajo: trabajosDisponibles) {
					int n = 0;
					LocalDateTime ini = LocalDateTime.of(hoy.getYear(),hoy.getMonth(),hoy.getDayOfMonth(),11,00);
					LocalDateTime fin = LocalDateTime.of(hoy.getYear(),hoy.getMonth(),hoy.getDayOfMonth(),16,00);
					boolean encontradoA =false;
					boolean encontradoB =false;
					while (encontradoA == false && n< empleados.get(nivel).size()) {
						Empleado empleado = empleados.get(nivel).get(n);
						if (empleado.getHorarios().keySet().contains(ini) == false) {
							asignarTrabajo(trabajo, empleado, ini);
							encontradoA = true;
						}
						n=n+1;
					}
					n=0;
					while (encontradoB == false && n< empleados.get(nivel).size()) {
						Empleado empleado = empleados.get(nivel).get(n);
						if (empleado.getHorarios().keySet().contains(fin) == false) {
							asignarTrabajo(trabajo, empleado, fin);
							encontradoB = true;
						} 
						n=n+1;
					}
				}
			}
		}
	}
	
	
	private void asignarTrabajosAtraccion() {
		for (String nivel: empleados.keySet()) {
			if (trabajosAtraccion.containsKey(nivel)) {
				ArrayList<TrabajoAtraccion> trabajosDisponibles = trabajosAtraccion.get(nivel);
				for (TrabajoAtraccion trabajo: trabajosDisponibles) {
					int n = 0;
					LocalDateTime ini = LocalDateTime.of(hoy.getYear(),hoy.getMonth(),hoy.getDayOfMonth(),11,00);
					LocalDateTime fin = LocalDateTime.of(hoy.getYear(),hoy.getMonth(),hoy.getDayOfMonth(),16,00);
					boolean encontradoA =false;
					boolean encontradoB =false;
					while (encontradoA == false && n< empleados.get(nivel).size()) {
						Empleado empleado = empleados.get(nivel).get(n);
						if (empleado.getHorarios().keySet().contains(ini) == false) {
							asignarTrabajo(trabajo, empleado, ini);
							trabajo.getAtraccion().masEmpleado(empleado);
							encontradoA = true;
						}
						n=n+1;
					}
					n=0;
					while (encontradoB == false && n< empleados.get(nivel).size()) {
						Empleado empleado = empleados.get(nivel).get(n);
						if (empleado.getHorarios().keySet().contains(fin) == false) {
							asignarTrabajo(trabajo, empleado, fin);
							trabajo.getAtraccion().masEmpleado(empleado);
							encontradoB = true;
						}
						n=n+1;
					}
				}
			}
		}
	}
	
	public Diario guardarTurnos() {
		return new Diario(this, turnosApertura, turnosCierre);
	}

}
