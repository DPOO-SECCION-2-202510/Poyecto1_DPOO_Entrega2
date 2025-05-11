package parque;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDateTime;
import cliente.Cliente;
import financiero.GenTiquetes;
import empleado.Empleado;
import empleado.Trabajo;
import empleado.TrabajoAtraccion;
import empleado.TrabajoEspectaculo;
import juegos.Atraccion;
import juegos.AtraccionCultural;
import juegos.AtraccionMecanica;
import juegos.Espectaculo;
import exceptions.ExceptionConstrasenaIncorrecta;
import exceptions.ExceptionUsuarioNoExiste;
import exceptions.ExceptionUsuarioYaExiste;
import exceptions.ExceptionInfoNotFound;


public class ParqueAtraccion {

	private HashMap<String, Admin> administradores;
	
	private ArrayList<Espacio> espacios;
	
	private HashMap<String, Cliente> clientes;
	
	private int utilidad;
	
	private GenTiquetes taquilla;
	
	private GenTiquetes cajeroVirtual;
	
	private ArrayList<AtraccionCultural> cultural;
	
	private ArrayList<AtraccionMecanica> mecanicas;
	
	private ArrayList<Espectaculo> espectaculos;

	
	public ParqueAtraccion(ArrayList<Espacio> espacios, HashMap<String, Integer> tiquetes, HashMap<String, Integer> entradas, int fastPass) {
		this.administradores = new HashMap<String, Admin>();
		this.clientes = new HashMap<String, Cliente>();
		this.utilidad = 0;
		this.taquilla = new GenTiquetes(tiquetes, entradas,  fastPass, 0);
		this.cajeroVirtual = new GenTiquetes(tiquetes, entradas,  fastPass, 0);
		this.espacios = espacios;
	}
	
	public void añadirAtracciones(ArrayList<AtraccionCultural> cult, ArrayList<AtraccionMecanica> meca, ArrayList<Espectaculo> espe) {
		this.cultural=cult;
		this.espectaculos = espe;
		this.mecanicas = meca;
	}
	
	
	
	public GenTiquetes getCajaVirtual() {
		return cajeroVirtual;
	}
	
	public int getUtilidad() {
		cargarUtilidadTaquilla();
		return utilidad;
	}
	
	public int getUtilidadVirtual() {
		return cajeroVirtual.getCaja();
		}
	
	
	public Espacio getEspacio(int id){
		Espacio cual = null;
		for (Espacio esacio: espacios) {
			if(esacio.getIdentificador()==id) {
				cual = esacio;
			}
		}
		return cual;
	}
	
	public HashMap<String, String> getInfoAtracciones(){
		HashMap<String, String > info = new HashMap<String, String>();
		for (AtraccionMecanica meca: mecanicas) {
			if (meca.getFuncionando()) {
				String data = meca.getNombre()+" accesible para personas con nivel "+ meca.getExclusividad();
				info.put(meca.getNombre(), data);
			}
		}
		for (AtraccionCultural cult: cultural) {
			if (cult.getFuncionando()) {
				String data = cult.getNombre()+" accesible para personas con nivel "+ cult.getExclusividad();
				info.put(cult.getNombre(), data);
			}
		}
		return info;
	}
	
	public String getInfoAtraccion(String nombre) {
		String info = "Atraccion no encontrada";
		for(AtraccionCultural cult: cultural) {
			if (cult.getNombre()==nombre) {
				info = cult.getInfo();
			}
		}
		for(AtraccionMecanica meca: mecanicas) {
			if (meca.getNombre()==nombre) {
				info = meca.getInfo();
			}
		}
		return info;
	}
	
	public HashMap<String, String> getInfoEspectaculos(LocalDateTime ahora, String clima){
		HashMap<String, String > info = new HashMap<String, String>();
		for (Espectaculo esp: espectaculos) {
			if (esp.enFuncionamiento(ahora, clima)) {
				String data = esp.getDescripcion()+" cuenta con una duracion de "+ esp.getDuracion() +" minutos";
				info.put(esp.getNombre(), data);
			}
		}
		return info;
	}
	
	public String getInfoEspectaculo(String nombre) {
		String info = "Espectaculo no encontrado";
		for(Espectaculo esp: espectaculos) {
			if (esp.getNombre()==nombre) {
				info = esp.getInfo();
			}
		}
		return info;
	}
	
	
	public Cliente ingresarCliente(String nombreUsuario, String contrasena) throws ExceptionConstrasenaIncorrecta, ExceptionUsuarioNoExiste {
		Cliente cliente = null;
		String usuario = nombreUsuario+";"+contrasena;
		if (clientes.containsKey(usuario)) {
			cliente = clientes.get(usuario);
		}else {
			for(String usuarios: clientes.keySet()) {
				if (usuarios.contains(nombreUsuario)) {
					throw new ExceptionConstrasenaIncorrecta("Contraseña incorretca, intente de nuevo.");
				}
			}
			throw new ExceptionUsuarioNoExiste("El usuario "+usuario+" no esta registrado. Crear usuario o intetar de nuevo.");
		}
		return cliente;
	}
	
	public Admin ingresarAdmin(String nombreUsuario, String contrasena) throws ExceptionConstrasenaIncorrecta, ExceptionUsuarioNoExiste {
		Admin admin = null;
		String usuario = nombreUsuario+";"+contrasena;
		if (administradores.containsKey(usuario)) {
			admin = administradores.get(usuario);
		}else {
			for(String usuarios: administradores.keySet()) {
				if (usuarios.contains(nombreUsuario)) {
					throw new ExceptionConstrasenaIncorrecta("Contraseña incorretca, intente de nuevo.");
				}
			}
			throw new ExceptionUsuarioNoExiste("El usuario "+usuario+" no esta registrado. Crear usuario o intetar de nuevo.");
		}
		return admin;
	}
	
	public Empleado ingresarEmpleado(String nombreUsuario, String contrasena, String adminN) throws ExceptionUsuarioNoExiste, ExceptionConstrasenaIncorrecta {
		Empleado este = null;
		if(administradores.containsKey(adminN)) {
			Admin admin = administradores.get(adminN);
			for(Empleado empleado: admin.getEmpleados()) {
				if(empleado.contraCorrecta(nombreUsuario, contrasena)) {
					este = empleado;
				}
			}
		}else {
			throw new ExceptionUsuarioNoExiste("El administrador no esta registrado");
		}
		if (este == null) {
			throw new ExceptionUsuarioNoExiste("El usuario "+nombreUsuario+" no esta registrado. Crear usuario o intetar de nuevo.");
		}
		return este;
	}
	
	
	public Cliente crearCliente(String usuario, String contra, String nombre, int id) throws ExceptionUsuarioYaExiste{
		for (String existentes: clientes.keySet()) {
			if (existentes.contains(usuario)) {
				throw new ExceptionUsuarioYaExiste("El nombre de usuario ya existe, intente de nuevo");
			}
		}
		Cliente cliente = new Cliente(nombre, id);
		String llave = usuario+";"+contra;
		clientes.put(llave, cliente);
		return cliente;
	}
	
	
	public Admin crearAdmin(String usuario, String contra, String nombre, int id, ArrayList<Trabajo> trabajosGeneral, ArrayList<TrabajoAtraccion> atracciones, ArrayList<TrabajoEspectaculo> espectaculos) throws ExceptionUsuarioYaExiste{
		for (String existentes: administradores.keySet()) {
			if (existentes.contains(usuario)) {
				throw new ExceptionUsuarioYaExiste("El nombre de usuario ya existe, intente de nuevo");
			}
		}
		Admin admin = new Admin(nombre, id, trabajosGeneral, atracciones, espectaculos);
		String llave = usuario+";"+contra;
		administradores.put(llave, admin);
		return admin;
	}
	
	public Empleado crearEmpleado(String usuario, String contra, String nombre, int codigo, String capacitacion) throws ExceptionUsuarioYaExiste, ExceptionUsuarioNoExiste{
		Admin menor = null;
		for (Admin admins: administradores.values()) {
			if (menor ==null) {
				menor = admins;
			} else if(menor.getNumEmpleados()>admins.getNumEmpleados()){
				menor = admins;
			}}
		if(menor==null) {
			throw new ExceptionUsuarioNoExiste("Debe haber un admnistrador registrado primero");
		}
		for (Empleado existentes: menor.getEmpleados()) {
			if (existentes.getUsuario().contentEquals(usuario)) {
				throw new ExceptionUsuarioYaExiste("El nombre de usuario ya existe, intente de nuevo");
			}
		}
		Empleado empleado = menor.anadirEmpleado(usuario, contra, nombre, codigo, capacitacion);
		ArrayList<Trabajo> trabajos = menor.getTrabajosSinAsignar();
		boolean asignado = false;
		for (Trabajo tr: trabajos) {
			if (tr.getCapaciacion() == capacitacion & asignado == false) {
				LocalDateTime ini = LocalDateTime.of(2025,4,14,11,00);
				LocalDateTime fin = LocalDateTime.of(2025,4,14,16,00);
				menor.asignarTrabajo(tr, empleado, ini);
				menor.asignarTrabajo(tr, empleado, fin);
			}
		}
	    return empleado;
	}
	
	
	public void cambiarSenha(Cliente cliente, String senha) {
		String nueva = null;
		String antigua = null;
		for (String usuarios: clientes.keySet()) {
			if(clientes.get(usuarios) == cliente) {
				String nombre = usuarios.split(";")[0];
				nueva = nombre+";"+senha;
				antigua = usuarios;
			}
		}
		clientes.remove(antigua);
		clientes.put(nueva, cliente);
	}
	
	public Atraccion getAtraccion(String nombre) throws ExceptionInfoNotFound {
		Atraccion at = null;
		for(AtraccionCultural cult: cultural) {
			if (cult.getNombre().contentEquals(nombre)) {
				at = cult;
			}
		}
		for(AtraccionMecanica meca: mecanicas) {
			if (meca.getNombre().contentEquals(nombre)) {
				at=meca;
			}
		}
		if (at==null) {
			throw new ExceptionInfoNotFound("La atraccion "+nombre+" no existe");
		}
		return at;
	}
	
	
	public Espectaculo getEspectaculo(String nombre) throws ExceptionInfoNotFound {
		Espectaculo es = null;
		for(Espectaculo esp: espectaculos) {
			if (esp.getNombre().contentEquals(nombre)) {
				es = esp;
			}
		}
		if (es==null) {
			throw new ExceptionInfoNotFound("El espectaculo "+nombre+" no existe");
		}
		return es;
	}
	
	
	
	public void cambiarSenha(Admin admin, String senha) {
		String nueva = null;
		String antigua = null;
		for (String usuarios: administradores.keySet()) {
			if(administradores.get(usuarios) == admin) {
				String nombre = usuarios.split(";")[0];
				nueva = nombre+";"+senha;
				antigua = usuarios;
			}
		}
		administradores.remove(antigua);
		administradores.put(nueva, admin);
	}
	
	public void cargarUtilidadTaquilla() {
		int util = taquilla.getCaja();
		utilidad = utilidad+util;
	}
	
	
	
}
