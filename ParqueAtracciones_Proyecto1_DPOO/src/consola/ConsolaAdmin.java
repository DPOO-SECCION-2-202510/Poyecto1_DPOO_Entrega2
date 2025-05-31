package consola;
import parque.ParqueAtraccion;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import administrador.Admin;
import empleado.Empleado;
import empleado.Trabajo;
import empleado.TrabajoAtraccion;
import empleado.TrabajoEspectaculo;
import exceptions.ExceptionInputIncorrecto;

public class ConsolaAdmin extends ConsolaMain{
	
	private static Admin admin;
	
	private static ParqueAtraccion parque;
	
	private static String usuario;
	
	private static Scanner input = new Scanner(System.in);;
	
	public ConsolaAdmin(Admin admin, ParqueAtraccion parque, String usuario, String contra) {
		super();
		this.admin = admin;
		this.parque = parque;
		this.usuario = usuario;
	}
	
	public static void empezarConsola() throws ExceptionInputIncorrecto {
		System.out.print("Bienvenido "+admin.getNombre()+"!!!\n");
		System.out.print("Que deseas hacer hoy?\n");
		boolean correr = true;
		while (correr ==true) {
			int opcion = menu();
			if (opcion ==1) {
				verInfo();
			} else if (opcion ==2) {
				cargarTrabajadores();
			} else if (opcion ==3) {
				anadirTrabajador();
			}else if (opcion ==4) {
				verTurnosApertura();
			}else if (opcion ==5) {
				verTurnosCierre();
			}else if (opcion ==6) {
				verInfoEmpleado();
			}else if (opcion ==7) {
				verInfoTrabajo();
			}else if (opcion ==8) {
				asignarTurnoManual();
			}else if (opcion ==9) {
				asignarTurnos();
			}else if (opcion ==10) {
				verTrabajosSolos();
			}else if (opcion ==11) {
				verUtilidad();
			}else if (opcion ==12) {
				cambiarSenha();
			}else if (opcion ==13) {
				salvarTurnos();
			}else if (opcion ==14) {
				correr = false;
			}
		}
	}
	
	
	public List<JPanel> getInput(){
		List<JPanel> input = new ArrayList<JPanel>();

		if (opcion ==3) {
			input = inputanadirTrabajador();
		}else if (opcion ==6) {
			input=inputverInfoEmpleado();
		}else if (opcion ==7) {
			input=inputverInfoTrabajo();
		}else if (opcion ==12) {
			input=inputcambiarSenha();
		}else if (opcion ==8) {
			input = inputasignarTurnoManual();
		}
		return input;
	}
	
	
	
	
	public List<JLabel> getInfo(List<String> inputs){
		List<JLabel> info = new ArrayList<JLabel>();
		if (opcion ==1) {
			info =infoverInfo();
		} else if (opcion ==2) {
			info =infocargarTrabajadores();
		} else if (opcion ==3) {
			info =infoanadirTrabajador(inputs);
		}else if (opcion ==4) {
			info =infoverTurnosApertura();
		}else if (opcion ==5) {
			info =infoverTurnosCierre();
		}else if (opcion ==6) {
			info =infoverInfoEmpleado(inputs);
		}else if (opcion ==7) {
			info =infoverInfoTrabajo(inputs);
		}else if (opcion ==8) {
			info =infoasignarTurnoManual(inputs);
		}else if (opcion ==9) {
			info =infoasignarTurnos();
		}else if (opcion ==10) {
			info =infoverTrabajosSolos();
		}else if (opcion ==11) {
			info =infoverUtilidad();
		}else if (opcion ==12) {
			info =infocambiarSenha(inputs);
		}else if (opcion ==13) {
			info =infosalvarTurnos();
		}
		return info;
	}
	
	
	public String[] getMenu() {
		String opciones = "01 - Ver tu informacion;02 - Cargar trabajadores;03 - Añadir un trabajador"
				+ ";04 - Ver turnos de apertura;05 - Ver turnos de cierre"
				+ ";06 - Ver la informacion de un empleado;07 - Ver la informacion de un trabajo"
				+ ";08 - Asignar un turno manualmente;09 - Usar auxiliar para asignar todos los turnos;10 - Ver trabajos sin empleado"
				+ ";11 - Ver la utilidad del parque;12 - Cambiar tu contraseña"
				+ ";13 - Guardar turnos asignados para fechas siguientes;14 - Cerrar sesion";
				
		String[] menu = opciones.split(";");
		return menu;
	}
	private static int menu() {
		System.out.print("1 - Ver tu informacion\n");
		System.out.print("2 - Cargar trabajadores\n");
		System.out.print("3 - Añadir un trabajador\n");
		System.out.print("4 - Ver turnos de apertura\n");
		System.out.print("5 - Ver turnos de cierre\n");
		System.out.print("6 - Ver la informacion de un empleado\n");
		System.out.print("7 - Ver la informacion de un trabajo\n");
		System.out.print("8 - Asignar un turno manualmente\n");
		System.out.print("9 - Usar auxiliar para asignar todos los turnos\n");
		System.out.print("10 - Ver trabajos sin empleado\n");
		System.out.print("11 - Ver la utilidad del parque\n");
		System.out.print("12 - Cambiar tu contraseña\n");
		System.out.print("13 - Guardar turnos asignados para fechas siguientes\n");
		System.out.print("14 - Cerrar sesion\n");
		input = new Scanner(System.in);
		int numero = input.nextInt();
		if (numero <0 || numero>14) {
			numero = menu();
		}
		return numero;
	}
	
	
	private static void verInfo() {
		String nombre = admin.getNombre();
		int empleados = admin.getNumEmpleados();
		int id = admin.getId();
		int turnosAsignados = admin.getTurnosAsignados();
		HashMap<String, Integer> trabajos = admin.getNumTrabajos();
		System.out.print("Nombre: "+ nombre+"\n");
		System.out.print("Identificacion: "+id+"\n");
		System.out.print("Numbre de usuario: "+usuario+"\n");
		System.out.print("Numero de empleados: "+empleados+"\n");
		System.out.print("Numero de turnos asignados: "+turnosAsignados+"\n");
		for (String nivel: trabajos.keySet()) {
			System.out.print("Numero de trabajos con nivel de capacitacion "+nivel+" : "+trabajos.get(nivel)+"\n");
		}
	}
	
	private List<JLabel> infoverInfo() {
		List<JLabel> info  = new ArrayList<JLabel>();
		String nombre = admin.getNombre();
		int empleados = admin.getNumEmpleados();
		int id = admin.getId();
		int turnosAsignados = admin.getTurnosAsignados();
		HashMap<String, Integer> trabajos = admin.getNumTrabajos();
		JLabel s1 = new JLabel(("Nombre: "+ nombre));
		JLabel s2 = new JLabel(("Identificacion: "+id));
		JLabel s3 = new JLabel(("Numbre de usuario: "+usuario));
		JLabel s4 = new JLabel(("Numero de empleados: "+empleados));
		JLabel s5 = new JLabel(("Numero de turnos asignados: "+turnosAsignados));
		info.add(s1);
		info.add(s2);
		info.add(s3);
		info.add(s4);
		info.add(s5);
		for (String nivel: trabajos.keySet()) {
			JLabel s = new JLabel(("Numero de trabajos con nivel de capacitacion "+nivel+" : "+trabajos.get(nivel)));
			info.add(s);
		}
		return info;
	}
	
	
	private static void cargarTrabajadores() {
		admin.mover.cargarEmpleados();
		System.out.print("Se han cargado los empleados exitosamente.\n");
	}
	
	private List<JLabel> infocargarTrabajadores() {
		admin.mover.cargarEmpleados();
		List<JLabel> info  = new ArrayList<JLabel>();
		JLabel s = new JLabel("Se han cargado los empleados exitosamente.\n");
		info.add(s);
		return info;
	}
	
	private static void cambiarSenha(){
		input = new Scanner(System.in);
		System.out.print("Ingrese nueva contraseña: ");
		String senha = input.next();
		parque.cambiarSenha(admin, senha);
	}
	
	private List<JPanel> inputcambiarSenha(){
		List<JPanel> info = new ArrayList<JPanel>();
		JTextField op= new JTextField(16);
		op.setHorizontalAlignment(0);
    	JLabel labNombre = new JLabel( "Ingrese nueva contraseña: " );
    	JPanel nom = new JPanel();
    	nom.add(labNombre);
    	nom.add(op);
    	info.add(nom);
    	return info;
	}
	
	private List<JLabel> infocambiarSenha(List<String> inputs) {
		List<JLabel> info  = new ArrayList<JLabel>();
		String m = "";
		if (inputs.isEmpty()) {
			m = "Por favor digite la contraseña";
		}else {
			String senha = inputs.getFirst();
			parque.cambiarSenha(admin, senha);
			m="Se ha cambiado la contraseña exitosamente";
		}
		JLabel s = new JLabel(m);
		info.add(s);
		return info;
	}
	
	private static void anadirTrabajador() throws ExceptionInputIncorrecto {
		input = new Scanner(System.in);
		System.out.print("Ingrese el nombre de usuario del empleado: ");
		String usua = input.next();
		System.out.print("Ingrese la contraseña del empleado: ");
		input = new Scanner(System.in);
		String contra = input.next();
		System.out.print("Ingrese el nombre del empleado: ");
		input = new Scanner(System.in);
		String nombre = input.next();
		System.out.print("Ingrese el numero de identificacion del empleado.\n");
		input = new Scanner(System.in);
		if (input.hasNextInt()==false) {
			throw new ExceptionInputIncorrecto("Por favor poner la fecha en formato (aaaa/mm/dd).");
		}
		int id = input.nextInt();
		System.out.print("Ingrese el nivel de capacitacion del empleado: ");
		input = new Scanner(System.in);
		String nivel = input.next();
		admin.mover.anadirEmpleado(usua, contra, nombre, id, nivel);
	}
	
	
	private List<JLabel> infoanadirTrabajador(List<String> inputs) {
		String m = "Se ha cergado el trabajador exitosamente";
		List<JLabel> info  = new ArrayList<JLabel>();
		String usua = inputs.get(0);
		String contra = inputs.get(1);
		String nombre = inputs.get(2);
		String nivel = inputs.get(4);
		try {
			int id = Integer.parseInt(inputs.get(3));
			admin.mover.anadirEmpleado(usua, contra, nombre, id, nivel);
		} catch (NumberFormatException e) {
			m = "Porfavor ingrese un numero de identidad correcto";
		}
		JLabel a = new JLabel(m);
		info.add(a);
		return info;
	}
	
	private List<JPanel> inputanadirTrabajador(){
		List<JPanel> info = new ArrayList<JPanel>();
		JTextField op= new JTextField(16);
		op.setHorizontalAlignment(0);
    	JLabel labNombre = new JLabel( "Ingrese el nombre de usuario del empleado: " );
    	JPanel nom = new JPanel();
    	nom.add(labNombre);
    	nom.add(op);
    	info.add(nom);
    	JTextField op2= new JTextField(16);
		op2.setHorizontalAlignment(0);
    	JLabel labNombre2 = new JLabel( "Ingrese la contraseña del empleado: " );
    	JPanel nom2 = new JPanel();
    	nom2.add(labNombre2);
    	nom2.add(op2);
    	info.add(nom2);
    	JTextField op3= new JTextField(16);
		op3.setHorizontalAlignment(0);
    	JLabel labNombre3 = new JLabel("Ingrese el nombre del empleado: " );
    	JPanel nom3 = new JPanel();
    	nom3.add(labNombre3);
    	nom3.add(op3);
    	info.add(nom3);
    	JTextField op4= new JTextField(16);
		op4.setHorizontalAlignment(0);
    	JLabel labNombre4 = new JLabel("Ingrese el numero de identificacion del empleado.\n" );
    	JPanel nom4 = new JPanel();
    	nom4.add(labNombre4);
    	nom4.add(op4);
    	info.add(nom4);
    	JTextField op5= new JTextField(16);
		op5.setHorizontalAlignment(0);
    	JLabel labNombre5 = new JLabel("Ingrese el nivel de capacitacion del empleado: ");
    	JPanel nom5 = new JPanel();
    	nom5.add(labNombre5);
    	nom5.add(op5);
    	info.add(nom5);
		return info;
	}
	
	
	private static void verTurnosApertura() {
		HashMap<String, Trabajo> turnosApertura = admin.getTurnosApertura();
		if (turnosApertura.isEmpty()) {
			System.out.print("No hay ningun turno asignado para la apertura.\n");
		}else {
			for(String empleado: turnosApertura.keySet()) {
				System.out.print(empleado+" tiene turno en el "+turnosApertura.get(empleado).getServicio()+" donde tendra que "+turnosApertura.get(empleado).getDescripcion()+"\n");
			}
		}
	}
	
	private List<JLabel> infoverTurnosApertura() {
		List<JLabel> info  = new ArrayList<JLabel>();
		HashMap<String, Trabajo> turnosApertura = admin.getTurnosApertura();
		if (turnosApertura.isEmpty()) {
			JLabel s = new JLabel("No hay ningun turno asignado para la apertura");
			info.add(s);
		}else {
			for(String empleado: turnosApertura.keySet()) {
				String a = empleado+" tiene turno en el "+turnosApertura.get(empleado).getServicio()+" donde tendra que "+turnosApertura.get(empleado).getDescripcion();
				JLabel s = new JLabel(a);
				info.add(s);
			}
		}
		return info;
	}
	
	private static void verTurnosCierre() {
		HashMap<String, Trabajo> turnosCierre = admin.getTurnosCierre();
		if (turnosCierre.isEmpty()) {
			System.out.print("No hay ningun turno asignado para la apertura.\n");
		}else {
			for(String empleado: turnosCierre.keySet()) {
				System.out.print(empleado+" tiene turno en el "+turnosCierre.get(empleado).getServicio()+" donde tendra que "+turnosCierre.get(empleado).getDescripcion()+"\n");
			}
		}
	}
	
	private List<JLabel> infoverTurnosCierre() {
		List<JLabel> info  = new ArrayList<JLabel>();
		HashMap<String, Trabajo> turnosCierre = admin.getTurnosCierre();
		if (turnosCierre.isEmpty()) {
			JLabel s = new JLabel("No hay ningun turno asignado para la apertura.\n");
			info.add(s);
		}else {
			for(String empleado: turnosCierre.keySet()) {
				String a = empleado+" tiene turno en el "+turnosCierre.get(empleado).getServicio()+" donde tendra que "+turnosCierre.get(empleado).getDescripcion();
				JLabel s = new JLabel(a);
				info.add(s);
			}
		}
		return info;
	}
	
	
	private static void verInfoEmpleado() {
		input = new Scanner(System.in);
		System.out.print("Ingrese el nombre del empleado: ");
		String nombre = input.next();
		Empleado emple = admin.getEmpleado(nombre);
		if (emple==null) {
			System.out.print("No se ha encontrado un empleado con ese nombre\n");
		}else {
			System.out.print(emple.getNombre()+" identificado con el CC "+emple.getCodigo()+" tiene un nivel de caacitacion "+emple.getCapacitacion()+". En el momento tiene "+emple.getHorarios().size()+" turnos asignados."+"\n");
	
		}
	}
	
	
	private List<JLabel> infoverInfoEmpleado(List<String> inputs) {
		List<JLabel> info  = new ArrayList<JLabel>();
		String nombre = inputs.get(0);
		Empleado emple = admin.getEmpleado(nombre);
		if (emple==null) {
			JLabel s = new JLabel("No se ha encontrado un empleado con ese nombre");
			info.add(s);
		}else {
			String a =emple.getNombre()+" identificado con el CC "+emple.getCodigo()+" tiene un nivel de caacitacion "+emple.getCapacitacion()+". En el momento tiene "+emple.getHorarios().size()+" turnos asignados";
			JLabel s = new JLabel(a);
			info.add(s);
		}
		return info;
	}
	
	
	private List<JPanel> inputverInfoEmpleado() {
		List<JPanel> info = new ArrayList<JPanel>();
		JTextField op= new JTextField(16);
		op.setHorizontalAlignment(0);
    	JLabel labNombre = new JLabel( "Ingrese el nombre del empleado: ");
    	JPanel nom = new JPanel();
    	nom.add(labNombre);
    	nom.add(op);
    	info.add(nom);
		return info;
	}
	
	
	private static void verInfoTrabajo() {
		input = new Scanner(System.in);
		System.out.print("Ingrese el servicio del trabajo: ");
		String nombre = input.next();
		String cual = admin.revisarTrabajo(nombre);
		if (cual==null) {
			System.out.print("No se ha encontrado un trabajo con ese servicio\n");
		}else if (cual=="general") {
			Trabajo tr = admin.getTrabajoGen(nombre);
			System.out.print("Trabajo en "+tr.getServicio()+" donde el empleado tiene que "+tr.getDescripcion()+". necesita nivel de capacitacion "+tr.getCapaciacion()+". En el momento tiene una utilidad de "+tr.getUtilidad()+"\n");
		}else if (cual=="atraccion") {
			TrabajoAtraccion tr = admin.getTrabajoA(nombre);
			System.out.print("Trabajo en la atraccion  "+tr.getServicio()+" donde el empleado tiene que "+tr.getDescripcion()+". necesita nivel de capacitacion "+tr.getCapaciacion()+". En el momento tiene una utilidad de "+tr.getUtilidad()+"\n");
		}else {
			TrabajoEspectaculo tr = admin.getTrabajoEsp(nombre);
			System.out.print("Trabajo en el espectaculo  "+tr.getServicio()+" donde el empleado tiene que "+tr.getDescripcion()+". necesita nivel de capacitacion "+tr.getCapaciacion()+". En el momento tiene una utilidad de "+tr.getUtilidad()+"\n");
		}
	}
	
	private List<JLabel> infoverInfoTrabajo(List<String> inputs) {
		List<JLabel> info  = new ArrayList<JLabel>();
		String nombre = inputs.getFirst();
		String cual = admin.revisarTrabajo(nombre);
		if (cual==null) {
			JLabel s = new JLabel("No se ha encontrado un trabajo con ese servicio\n");
			info.add(s);
		}else if (cual=="general") {
			Trabajo tr = admin.getTrabajoGen(nombre);
			String a = "Trabajo en "+tr.getServicio()+" donde el empleado tiene que "+tr.getDescripcion()+". necesita nivel de capacitacion "+tr.getCapaciacion()+". En el momento tiene una utilidad de "+tr.getUtilidad();
			JLabel s = new JLabel(a);
			info.add(s);
		}else if (cual=="atraccion") {
			TrabajoAtraccion tr = admin.getTrabajoA(nombre);
			String a = "Trabajo en la atraccion  "+tr.getServicio()+" donde el empleado tiene que "+tr.getDescripcion()+". necesita nivel de capacitacion "+tr.getCapaciacion()+". En el momento tiene una utilidad de "+tr.getUtilidad();
			JLabel s = new JLabel(a);
			info.add(s);
		}else {
			TrabajoEspectaculo tr = admin.getTrabajoEsp(nombre);
			String s = "Trabajo en el espectaculo  "+tr.getServicio()+" donde el empleado tiene que "+tr.getDescripcion()+". necesita nivel de capacitacion "+tr.getCapaciacion()+". En el momento tiene una utilidad de "+tr.getUtilidad();
			JLabel a = new JLabel(s);
			info.add(a);
		}
		return info;
	}
	
	private List<JPanel> inputverInfoTrabajo() {
		List<JPanel> info = new ArrayList<JPanel>();
		JTextField op= new JTextField(16);
		op.setHorizontalAlignment(0);
    	JLabel labNombre = new JLabel( "Ingrese el servicio del trabajo: ");
    	JPanel nom = new JPanel();
    	nom.add(labNombre);
    	nom.add(op);
    	info.add(nom);
    	return info;
	}
	
	private static void asignarTurnoManual() {
		input = new Scanner(System.in);
		System.out.print("Ingrese el servicio del trabajo: ");
		String trabajo = input.next();
		input = new Scanner(System.in);
		System.out.print("Ingrese el nombre del empleado: ");
		String empleado = input.next();
		Empleado emple = admin.getEmpleado(empleado);
		input = new Scanner(System.in);
		System.out.print("Asignar en el turno de apertura(1) o de cierre(2): ");
		String turno = input.next();
		LocalDateTime hora = null;
		if (turno=="1"||turno=="apertura") {
			hora = LocalDateTime.of(2025,4,14,11,00);
		}else {
			hora = LocalDateTime.of(2025,4,14,16,00);
		}
		String cual = admin.revisarTrabajo(trabajo);
		if (cual==null) {
			System.out.print("No se ha encontrado un trabajo con ese servicio\n");
		}else if (cual=="general") {
			Trabajo tr = admin.getTrabajoGen(trabajo);
			admin.asignar.asignarTrabajo(tr, emple, hora);
		}else if (cual=="atraccion") {
			TrabajoAtraccion tr = admin.getTrabajoA(trabajo);
			admin.asignar.asignarTrabajo(tr, emple, hora);
		}else {
			TrabajoEspectaculo tr = admin.getTrabajoEsp(trabajo);
			admin.asignar.asignarTrabajo(tr, emple, hora);
		}
	}
	
	
	private List<JLabel> infoasignarTurnoManual(List<String> inputs) {
		List<JLabel> info  = new ArrayList<JLabel>();
		String trabajo = inputs.get(0);
		String empleado = inputs.get(1);
		Empleado emple = admin.getEmpleado(empleado);
		if (emple.equals(null)) {
			JLabel s = new JLabel("No se ha encontrado el empleado.");
			info.add(s);
		}else {
			String turno = inputs.get(2);
			LocalDateTime hora = null;
			if (turno=="1"||turno=="apertura") {
				hora = LocalDateTime.of(2025,4,14,11,00);
			}else {
				hora = LocalDateTime.of(2025,4,14,16,00);
			}
			String cual = admin.revisarTrabajo(trabajo);
			if (cual==null) {
				JLabel s = new JLabel("No se ha encontrado un trabajo con ese servicio\n");
				info.add(s);
			}else if (cual=="general") {
				Trabajo tr = admin.getTrabajoGen(trabajo);
				admin.asignar.asignarTrabajo(tr, emple, hora);
				JLabel s = new JLabel("Se ha asignado correctamente el turno");
				info.add(s);
			}else if (cual=="atraccion") {
				TrabajoAtraccion tr = admin.getTrabajoA(trabajo);
				admin.asignar.asignarTrabajo(tr, emple, hora);
				JLabel s = new JLabel("Se ha asignado correctamente el turno");
				info.add(s);
			}else {
				TrabajoEspectaculo tr = admin.getTrabajoEsp(trabajo);
				admin.asignar.asignarTrabajo(tr, emple, hora);
				JLabel s = new JLabel("Se ha asignado correctamente el turno");
				info.add(s);
			}
		}
		return info;
	}
	
	private List<JPanel> inputasignarTurnoManual() {
		List<JPanel> info = new ArrayList<JPanel>();
		JTextField op= new JTextField(16);
		op.setHorizontalAlignment(0);
    	JLabel labNombre = new JLabel("Ingrese el servicio del trabajo: ");
    	JPanel nom = new JPanel();
    	nom.add(labNombre);
    	nom.add(op);
    	info.add(nom);
    	JTextField op2= new JTextField(16);
		op2.setHorizontalAlignment(0);
    	JLabel labNombre2 = new JLabel("Ingrese el nombre del empleado: ");
    	JPanel nom2 = new JPanel();
    	nom2.add(labNombre2);
    	nom2.add(op2);
    	info.add(nom2);
    	JTextField op3= new JTextField(16);
		op3.setHorizontalAlignment(0);
    	JLabel labNombre3 = new JLabel("Asignar en el turno de apertura(1) o de cierre(2): ");
    	JPanel nom3 = new JPanel();
    	nom3.add(labNombre3);
    	nom3.add(op3);
    	info.add(nom3);
		return info;
	}
	
	private static void asignarTurnos() {
		int antes = admin.getTurnosAsignados();
		admin.asignar.asignarTodos();
		int nuevo= admin.getTurnosAsignados();
		nuevo = nuevo-antes;
		System.out.print("Se han asignado "+nuevo+" tunos.\n");
	}
	
	private List<JLabel> infoasignarTurnos() {
		List<JLabel> info  = new ArrayList<JLabel>();
		int antes = admin.getTurnosAsignados();
		admin.asignar.asignarTodos();
		int nuevo= admin.getTurnosAsignados();
		nuevo = nuevo-antes;
		String a = "Se han asignado "+nuevo+" tunos";
		JLabel s = new JLabel(a);
		info.add(s);
		return info;
	}
	
	private List<JLabel> infoverTrabajosSolos() {
		List<JLabel> info  = new ArrayList<JLabel>();
		for(Trabajo tr: admin.getTrabajosSinAsignar()) {
			String a ="Trabajo en  "+tr.getServicio()+" donde el empleado tiene que "+tr.getDescripcion()+". necesita nivel de capacitacion "+tr.getCapaciacion();
			JLabel s = new JLabel(a);
			info.add(s);
		}
		if (info.isEmpty()) {
			JLabel s = new JLabel("No se han encontrado trabajos sin turnos");
			info.add(s);
		}
		return info;
	}
	
	private static void verTrabajosSolos() {
		for(Trabajo tr: admin.getTrabajosSinAsignar()) {
			System.out.print("Trabajo en  "+tr.getServicio()+" donde el empleado tiene que "+tr.getDescripcion()+". necesita nivel de capacitacion "+tr.getCapaciacion()+"\n");
		}
	}
	
	public static void verUtilidad() {
		int utilTaquilla =  parque.getUtilidad();
		int utilTrabajo = admin.getUtilidad();
		int utilVirtual = parque.getUtilidadVirtual();
		System.out.print("Utilidad de los tiquetes vendidos en taquilla: "+utilTaquilla+"\n");
		System.out.print("Utilidad de los tiquetes vendidos virtualmente: "+utilVirtual+"\n");
		System.out.print("Utilidad de los productos vendidos dentro del parque: "+utilTrabajo+"\n");
	}
	
	public List<JLabel> infoverUtilidad() {
		List<JLabel> info  = new ArrayList<JLabel>();
		int utilTaquilla =  parque.getUtilidad();
		int utilTrabajo = admin.getUtilidad();
		int utilVirtual = parque.getUtilidadVirtual();
		String s1 ="Utilidad de los tiquetes vendidos en taquilla: "+utilTaquilla;
		String s2 = "Utilidad de los tiquetes vendidos virtualmente: "+utilVirtual;
		String s3 = "Utilidad de los productos vendidos dentro del parque: "+utilTrabajo;
		JLabel a1 = new JLabel(s1);
		JLabel a2 = new JLabel(s2);
		JLabel a4 = new JLabel(s3);
		info.add(a4);
		info.add(a2);
		info.add(a1);
		return info;
	}
	
	private static void salvarTurnos() {
		admin.asignar.guardarTurnos();
	}
	
	private List<JLabel> infosalvarTurnos() {
		List<JLabel> info  = new ArrayList<JLabel>();
		admin.asignar.guardarTurnos();
		JLabel s = new JLabel("Se ha salvado el turno correctamente");
		info.add(s);
		return info;
	}

}
