package administrador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import empleado.Empleado;
import persistencia.PersistenciaParqueAtracciones;

public class MoverEmpleado {

	public HashMap<String, ArrayList<Empleado>> empleados;
	
	private String nombre;
	
	
	public MoverEmpleado(HashMap<String, ArrayList<Empleado>> empleados, String nombre) {
		this.empleados = empleados;
		this.nombre = nombre;
	}
	
	public void cargarEmpleados() {
		PersistenciaParqueAtracciones cargador = new PersistenciaParqueAtracciones();
		try {
			empleados = cargador.cargarEmpleados(empleados, nombre);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public Empleado anadirEmpleado(String usuario, String contra, String nombreE, int codigo, String capacitacion) {
		Empleado empleado = new Empleado(usuario,contra,nombreE, codigo, capacitacion, nombre);
		if(empleados.containsKey(capacitacion)) {
			empleados.get(capacitacion).add(empleado);
		}else {
			ArrayList<Empleado> nueva = new ArrayList<Empleado>();
			nueva.add(empleado);
			empleados.put(capacitacion, nueva);
		}
		return empleado;
	}
	
	public void eliminarEmpleado(Empleado empleado) {
		for (ArrayList<Empleado> porNivel: empleados.values()) {
			if (porNivel.contains(empleado)) {
				porNivel.remove(empleado);
			}
		}
	}

}
