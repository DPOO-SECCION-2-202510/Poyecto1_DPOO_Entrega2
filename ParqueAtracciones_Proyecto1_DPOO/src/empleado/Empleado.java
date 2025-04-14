package empleado;
import java.util.HashMap;
import java.time.LocalDateTime;
import exceptions.ExceptionConstrasenaIncorrecta;

public class Empleado {
	
	private String nombre;
	
	private String usuario;
	
	private String contra;
	
	private int codigo;
	
	private HashMap<LocalDateTime, Trabajo> trabajos;
	
	private String nivCapacitacion;
	
	private String admin;
	
	
	
	public Empleado(String usuario, String contra, String nombre, int codigo, String capacitacion, String admin) {
		this.contra = contra;
		this.usuario = usuario;
		this.nombre = nombre;
		this.codigo = codigo;
		this.nivCapacitacion = capacitacion;
		this.trabajos = new HashMap<LocalDateTime, Trabajo>();
		this.admin = admin;
	}
	
	
	
	public String getNombre() {
		return nombre;
	}
	
	public String getAdmin() {
		return admin;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public String getContra() {
		return contra;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getCapacitacion() {
		return nivCapacitacion;
	}
	
	public void anadirTrabajo(LocalDateTime horario, Trabajo trabajo) {
		trabajos.put(horario, trabajo);
	}
	
	public Trabajo getTrabajo(String nombre) {
		Trabajo cual = null;
		for (Trabajo tr : trabajos.values()) {
			if(nombre.contentEquals(tr.getServicio())) {
				cual = tr;
			}
		}
		return cual;
	}
	
	public Trabajo trabajoEnHora(LocalDateTime hora) {
		Trabajo tiene = null;
		for (LocalDateTime trabajo: trabajos.keySet()) {
			if (trabajo.isEqual(hora)|| trabajo.isBefore(hora)) {
				LocalDateTime fin = trabajo.plusHours(5);
				if(fin.isEqual(hora)|| fin.isAfter(hora)) {
					tiene = trabajos.get(trabajo);
				}
			}
		}
		return tiene;
	}
	
	
	public HashMap<LocalDateTime, Trabajo> geHorarios() {
		return trabajos;
	}
	
	
	
	public void cambiarContra(String nuevo) {
		this.contra = nuevo;
	}

	
	public boolean contraCorrecta(String usuarioA, String contraA) throws ExceptionConstrasenaIncorrecta{
		boolean es = false;
		if (usuarioA==usuario) {
			if (contraA==contra) {
				es = true;
			}else {
				throw new ExceptionConstrasenaIncorrecta("La contraseña es incorrecta, intente de nuevo.");
			}
		}
		return es;
	}

}
