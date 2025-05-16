package administrador;

import java.util.HashMap;

import empleado.Trabajo;

public class Diario {
	
	private AsignarTrabajo asignar;
	
	private HashMap<String, Trabajo> turnosApertura;
	
	private HashMap<String, Trabajo> turnosCierre;
	

	public Diario(AsignarTrabajo asignar, HashMap<String, Trabajo> turnosApertura, HashMap<String, Trabajo> turnosCierre) {
		this.asignar = asignar;
		this.turnosApertura = turnosApertura;
		this.turnosCierre = turnosCierre;
	}
	
	public void restore() {
		asignar.dueno.setApertura(turnosApertura);
		asignar.dueno.setCierre(turnosCierre);
	}

}
