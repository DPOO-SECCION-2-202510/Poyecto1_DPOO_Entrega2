package consola;
import parque.ParqueAtraccion;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Scanner;

import administrador.Admin;
import empleado.Empleado;
import empleado.Trabajo;
import empleado.TrabajoAtraccion;
import empleado.TrabajoEspectaculo;
import exceptions.ExceptionInputIncorrecto;

public class ConsolaAdmin {
	
	private static Admin admin;
	
	private static ParqueAtraccion parque;
	
	private static String usuario;
	
	private String contra;
	
	private static Scanner input = new Scanner(System.in);;
	
	public ConsolaAdmin(Admin admin, ParqueAtraccion parque, String usuario, String contra) {
		this.admin = admin;
		this.parque = parque;
		this.contra = contra;
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
	
	
	private static void cargarTrabajadores() {
		admin.mover.cargarEmpleados();
		System.out.print("Se han cargado los empleados exitosamente.\n");
	}
	
	private static void cambiarSenha(){
		input = new Scanner(System.in);
		System.out.print("Ingrese nueva contraseña: ");
		String senha = input.next();
		parque.cambiarSenha(admin, senha);
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
	
	private static void asignarTurnos() {
		int antes = admin.getTurnosAsignados();
		admin.asignar.asignarTodos();
		int nuevo= admin.getTurnosAsignados();
		nuevo = nuevo-antes;
		System.out.print("Se han asignado "+nuevo+" tunos.\n");
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
	
	private static void salvarTurnos() {
		admin.asignar.guardarTurnos();
	}

}
