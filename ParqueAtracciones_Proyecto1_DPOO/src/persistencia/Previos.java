package persistencia;
import java.util.ArrayList;

import administrador.Admin;
import empleado.Empleado;
import empleado.Trabajo;
import empleado.TrabajoAtraccion;
import empleado.TrabajoEspectaculo;
import exceptions.ExceptionUsuarioNoExiste;
import exceptions.ExceptionUsuarioYaExiste;
import parque.ParqueAtraccion;

public class Previos {

	public Previos() {
		
	}
	
	public static void cargarEmpleadoPrevio(ParqueAtraccion parque) throws ExceptionUsuarioYaExiste, ExceptionUsuarioNoExiste {
		Empleado emple = parque.crearEmpleado("juan_Empleado", "Contrasena", "Juan", 1234567, "seguridad alto");
	}

	public static void cargarAdminPrevio(ParqueAtraccion parque, ArrayList<Trabajo> trabajosGeneral, ArrayList<TrabajoAtraccion> atracciones, ArrayList<TrabajoEspectaculo> espectaculos) throws ExceptionUsuarioYaExiste {
		Admin admin = parque.crearAdmin("Camilo_admin", "Contrasena", "Camilo", 1234567, trabajosGeneral, atracciones, espectaculos);
	}
}
