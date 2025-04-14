package consola;

import java.util.Scanner;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import empleado.Trabajo;
import exceptions.ExceptionInputIncorrecto;
import empleado.Empleado;

public class ConsolaEmpleado {

	private static Empleado emple;
	
	
	private static Scanner input;
	
	public ConsolaEmpleado(Empleado emple) {
		this.emple = emple;
		
		Scanner input = new Scanner(System.in);
	}
	
	public static void empezarConsola() throws ExceptionInputIncorrecto {
		System.out.print("Bienvenido "+emple.getNombre()+"!!!\n");
		System.out.print("Que deseas hacer hoy?\n");
		boolean correr = true;
		while (correr ==true) {
			int opcion = menu();
			if (opcion ==1) {
				verInfo();
			} else if (opcion ==2) {
				verHorario();
			} else if (opcion ==3) {
				verInfoTrabajo();
			}else if (opcion ==4) {
				cambiarSenha();
			}else if (opcion ==6) {
				revisarTurno();
			}else if (opcion ==5) {
				correr = false;
			}
		}
		
	}
	
	private static int menu() throws ExceptionInputIncorrecto {
		System.out.print("1 - Ver tu informacion \n");
		System.out.print("2 - Ver tu horario\n");
		System.out.print("3 - Ver informacion de un trabajo\n");
		System.out.print("4 - Cambiar tu contraseña\n");
		System.out.print("5 - Revisar si tiene turno a cierta hora\n");
		System.out.print("6 - Cerrar sesion\n");
		input = new Scanner(System.in);
		if (input.hasNextInt()==false) {
			throw new ExceptionInputIncorrecto("Por favor poner la fecha en formato (aaaa/mm/dd).");
		}
		int numero = input.nextInt();
		if (numero <0 || numero>7) {
			numero = menu();
		}
		return numero;
	}
	
	private static void verInfo() {
		String nombre = emple.getNombre();
		int id = emple.getCodigo();
		String usuario = emple.getUsuario();
		String contra = emple.getContra();
		String nivel = emple.getCapacitacion();
		int turnos = emple.geHorarios().size();
		System.out.print("Nombre: "+ nombre+"\n");
		System.out.print("Identificacion: "+id+"\n");
		System.out.print("Nombre de usuario: "+usuario+"\n");
		System.out.print("contraseña: "+contra+"\n");
		System.out.print("Nivel de capacitacion: "+nivel+"\n");
		System.out.print("Turnos Asignados: "+turnos+"\n");
		System.out.print("Administrador asignado: "+emple.getAdmin()+"\n");
	}
	
	private static void verHorario() {
		HashMap<LocalDateTime, Trabajo> horario = emple.geHorarios();
		for(LocalDateTime hora: horario.keySet()) {
			Trabajo tr = horario.get(hora);
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
			System.out.print("A las "+ hora.format( formatter)+" tiene trabajo en "+ tr.getServicio()+"\n");
		}
	}
	
	
	private static void verInfoTrabajo() {
		System.out.print("Ingrese el nombre del trabajo: ");
		String nombre = input.next();
		Trabajo tr = emple.getTrabajo(nombre);
		if (tr==null) {
			System.out.print("No tiene asignado ningun trabajo con este nombre: ");
		}else {
			System.out.print("Trabajo en  "+tr.getServicio()+" donde el empleado tiene que "+tr.getDescripcion()+". \nNecesita nivel de capacitacion "+tr.getCapaciacion());
		}
	}
	
	
	@SuppressWarnings("null")
	private static void revisarTurno() {
		System.out.print("Ingrese fecha (aaaa/mm/dd/hh/mn): ");
		String horaS = input.next();
		String[] horaA = horaS.split("/");
		LocalDateTime hora =  LocalDateTime.of(Integer.parseInt(horaA[0]), Integer.parseInt(horaA[1]), Integer.parseInt(horaA[2]), Integer.parseInt(horaA[3]), Integer.parseInt(horaA[4]));
		Trabajo tr = emple.trabajoEnHora(hora);
		if (tr!=null) {
			System.out.print("Tiene asignado un turno dentro de ese horario.\n ");
		}else {
			System.out.print("Trabajo en  "+tr.getServicio()+" donde el empleado tiene que "+tr.getDescripcion()+". \nNecesita nivel de capacitacion "+tr.getCapaciacion());
		}
	}
	
	private static void cambiarSenha() {
		System.out.print("Ingrese nueva contraseña: ");
		String senha = input.next();
		emple.cambiarContra(senha);
	}
	
}
