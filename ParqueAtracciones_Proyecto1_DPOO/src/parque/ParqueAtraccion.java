package parque;
import java.util.ArrayList;
import java.util.HashMap;

import administrador.Admin;

import java.time.LocalDateTime;
import cliente.Cliente;
import financiero.GenBoleta;
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
import cliente.ClienteBuilder;


public class ParqueAtraccion {
	
	private static ParqueAtraccion instance;

	private HashMap<String, Admin> administradores;
	
	private ArrayList<Espacio> espacios;
	
	private HashMap<String, Cliente> clientes;
	
	private int utilidad;
	
	private GenBoleta taquilla;
	
	private GenBoleta cajeroVirtual;
	
	private ArrayList<AtraccionCultural> cultural;
	
	private ArrayList<AtraccionMecanica> mecanicas;
	
	private ArrayList<Espectaculo> espectaculos;
	
	public Ingreso ingreso;
	

	
	private ParqueAtraccion(ArrayList<Espacio> espacios, HashMap<String, Integer> tiquetes, HashMap<String, Integer> entradas, int fastPass) {
		this.administradores = new HashMap<String, Admin>();
		this.clientes = new HashMap<String, Cliente>();
		this.utilidad = 0;
		this.taquilla = new GenBoleta(tiquetes, entradas,  fastPass, 0);
		this.cajeroVirtual = new GenBoleta(tiquetes, entradas,  fastPass, 0);
		this.espacios = espacios;
		this.ingreso = new Ingreso();
	}
	
	public static ParqueAtraccion getInstance(ArrayList<Espacio> espacios, HashMap<String, Integer> tiquetes, HashMap<String, Integer> entradas, int fastPass) {
        if (instance == null) {
            instance = new ParqueAtraccion(espacios, tiquetes, entradas,  fastPass);
        }
        return instance;
    }
	
	public void añadirAtracciones(ArrayList<AtraccionCultural> cult, ArrayList<AtraccionMecanica> meca, ArrayList<Espectaculo> espe) {
		this.cultural=cult;
		this.espectaculos = espe;
		this.mecanicas = meca;
	}
	
	
	
	public GenBoleta getCajaVirtual() {
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
			if (cult.getNombre().contentEquals(nombre)) {
				info = cult.getInfo();
			}
		}
		for(AtraccionMecanica meca: mecanicas) {
			if (meca.getNombre().contentEquals(nombre)) {
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
		Admin admin = encontrarAdmin(adminN);
		if(admin != null) {
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
	
	
	public Cliente crearCliente(String usuario, String contra, String nombre, int id, int peso, int alt, int edad, String salud) throws ExceptionUsuarioYaExiste{
		for (String existentes: clientes.keySet()) {
			if (existentes.contains(usuario)) {
				throw new ExceptionUsuarioYaExiste("El nombre de usuario ya existe, intente de nuevo");
			}
		}
		ClienteBuilder nuevo = new ClienteBuilder();
		nuevo.setAltura(alt);
		nuevo.setEdad(edad);
		nuevo.setId(id);
		nuevo.setName(nombre);
		nuevo.setPeso(peso);
		nuevo.setSalud(salud);
		Cliente cliente = nuevo.getNuevo();
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
		Empleado empleado = menor.mover.anadirEmpleado(usuario, contra, nombre, codigo, capacitacion);
		ArrayList<Trabajo> trabajos = menor.getTrabajosSinAsignar();
		boolean asignado = false;
		for (Trabajo tr: trabajos) {
			if (tr.getCapaciacion().contentEquals(capacitacion)  & asignado == false) {
				LocalDateTime hoy = LocalDateTime.now();
				LocalDateTime ini = LocalDateTime.of(hoy.getYear(),hoy.getMonth(),hoy.getDayOfMonth(),11,00);
				LocalDateTime fin = LocalDateTime.of(hoy.getYear(),hoy.getMonth(),hoy.getDayOfMonth(),16,00);
				menor.asignar.asignarTrabajo(tr, empleado, ini);
				menor.asignar.asignarTrabajo(tr, empleado, fin);
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
	
	public void actualizarFecha() {
		for (Admin admin: administradores.values()) {
			admin.diario.restore();
		}
	}
	
	private Admin encontrarAdmin(String nombre) {
		Admin resultado = null;
		for (Admin admin: administradores.values()) {
			if (admin.getNombre().contentEquals(nombre)){
				resultado = admin;
			}
		}
		return resultado;
	}
	
}
