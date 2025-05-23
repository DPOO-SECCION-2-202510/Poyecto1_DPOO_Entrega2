package consola;

import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import empleado.Trabajo;
import exceptions.ExceptionInputIncorrecto;
import empleado.Empleado;

public class ConsolaEmpleado extends ConsolaMain{

	private static Empleado emple;
	
	
	private static Scanner input;
	
	public ConsolaEmpleado(Empleado emple) {
		super();
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
			}else if (opcion ==5) {
				revisarTurno();
			}else if (opcion ==6) {
				registrarVenta();
			}else if(opcion ==7) {
				correr = false;
			}
		}
		
	}
	
	public List<JLabel> getInfo(List<String> inputs) {
		List<JLabel> info = new ArrayList<JLabel>();
		if (opcion ==1) {
			infoverInfo(inputs);
		} else if (opcion ==2) {
			infoverHorario(inputs);
		} else if (opcion ==3) {
			infoverInfoTrabajo(inputs);
		}else if (opcion ==4) {
			infocambiarSenha(inputs);
		}else if (opcion ==5) {
			inforevisarTurno(inputs);
		}else if (opcion ==6) {
			inforegistrarVenta (inputs);
		}
		return info;
	}
	
	public List<JPanel> getInput(){
		List<JPanel> input = new ArrayList<JPanel>();
		if (opcion ==3) {
			input=inputverInfoTrabajo();
		}else if (opcion ==4) {
			input=inputcambiarSenha();
		}else if (opcion ==5) {
			input=inputrevisarTurno();
		}else if (opcion ==6) {
			input=inputregistrarVenta();
		}
		return input;
	}
	
	public String[] getMenu(){
		String opciones = "1 - Ver tu informacion ;2 - Ver tu horario;3 - Ver informacion de un trabajo;4 - Cambiar tu contraseña"
				+";5 - Revisar si tiene turno a cierta hora;6 - Registrar venta;7 - Cerrar sesion";
		String [] menu = opciones.split(";");
		return menu;
	}
	
	private static int menu() throws ExceptionInputIncorrecto {
		System.out.print("1 - Ver tu informacion \n");
		System.out.print("2 - Ver tu horario\n");
		System.out.print("3 - Ver informacion de un trabajo\n");
		System.out.print("4 - Cambiar tu contraseña\n");
		System.out.print("5 - Revisar si tiene turno a cierta hora\n");
		System.out.print("6 - Registrar venta\n");
		System.out.print("7 - Cerrar sesion\n");
		input = new Scanner(System.in);
		if (input.hasNextInt()==false) {
			throw new ExceptionInputIncorrecto("Por favor poner la fecha en formato (aaaa/mm/dd).");
		}
		int numero = input.nextInt();
		if (numero <0 || numero>8) {
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
		int turnos = emple.getHorarios().size();
		System.out.print("Nombre: "+ nombre+"\n");
		System.out.print("Identificacion: "+id+"\n");
		System.out.print("Nombre de usuario: "+usuario+"\n");
		System.out.print("contraseña: "+contra+"\n");
		System.out.print("Nivel de capacitacion: "+nivel+"\n");
		System.out.print("Turnos Asignados: "+turnos+"\n");
		System.out.print("Administrador asignado: "+emple.getAdmin()+"\n");
	}
	
	
	private List<JLabel> infoverInfo(List<String> inputs) {
		List<JLabel> info  = new ArrayList<JLabel>();
		String nombre = emple.getNombre();
		int id = emple.getCodigo();
		String usuario = emple.getUsuario();
		String contra = emple.getContra();
		String nivel = emple.getCapacitacion();
		int turnos = emple.getHorarios().size();
		JLabel s1 = new JLabel(("Nombre: "+ nombre));
		JLabel s2 = new JLabel(("Identificacion: "+id));
		JLabel s3 = new JLabel(("Nombre de usuario: "+usuario));
		JLabel s4 = new JLabel(("contraseña: "+contra));
		JLabel s5 = new JLabel(("Nivel de capacitacion: "+nivel));
		JLabel s6 = new JLabel(("Turnos Asignados: "+turnos));
		JLabel s7 = new JLabel(("Administrador asignado: "+emple.getAdmin()));
		info.add(s1);
		info.add(s2);
		info.add(s3);
		info.add(s4);
		info.add(s5);
		info.add(s6);
		info.add(s7);
		return info;
	}
	
	private static void verHorario() {
		HashMap<LocalDateTime, Trabajo> horario = emple.getHorarios();
		for(LocalDateTime hora: horario.keySet()) {
			Trabajo tr = horario.get(hora);
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
			System.out.print("A las "+ hora.format( formatter)+" tiene trabajo en "+ tr.getServicio()+"\n");
		}
		
	}
	
	
	private List<JLabel> infoverHorario(List<String> inputs) {
		List<JLabel> info  = new ArrayList<JLabel>();
		HashMap<LocalDateTime, Trabajo> horario = emple.getHorarios();
		for(LocalDateTime hora: horario.keySet()) {
			Trabajo tr = horario.get(hora);
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
			String s = "A las "+ hora.format( formatter)+" tiene trabajo en "+ tr.getServicio();
			JLabel s1 = new JLabel(s);
			info.add(s1);
		}
		return info;
	}
	
	
	private static void verInfoTrabajo() {
		System.out.print("Los trabajos disponibles son: \n");
		for (Trabajo tr: emple.getHorarios().values()) {
			System.out.print(tr.getServicio()+"\n");
		}
		System.out.print("Ingrese el nombre del trabajo: ");
		String nombre = input.next();
		Trabajo tar = emple.getTrabajo(nombre);
		if (tar==null) {
			System.out.print("No tiene asignado ningun trabajo con este nombre: ");
		}else {
			System.out.print("Trabajo en  "+tar.getServicio()+" donde el empleado tiene que "+tar.getDescripcion()+". \n Necesita nivel de capacitacion "+tar.getCapaciacion());
		}
	}
	
	private List<JLabel> infoverInfoTrabajo(List<String> inputs) {
		List<JLabel> info  = new ArrayList<JLabel>();
		String nombre = inputs.getFirst();
		Trabajo tar = emple.getTrabajo(nombre);
		if (tar==null) {
			JLabel s = new JLabel("No tiene asignado ningun trabajo con este nombre: ");
			info.add(s);
		}else {
			JLabel s = new JLabel("Trabajo en  "+tar.getServicio()+" donde el empleado tiene que "+tar.getDescripcion()+". \n Necesita nivel de capacitacion "+tar.getCapaciacion());
			info.add(s);
		}
		return info;
	}
	
	private List<JPanel> inputverInfoTrabajo() {
		List<JPanel> info = new ArrayList<JPanel>();
		JTextField op= new JTextField(16);
		op.setHorizontalAlignment(0);
    	JLabel labNombre = new JLabel("Ingrese el nombre del trabajo: ");
    	JPanel nom = new JPanel();
    	nom.add(labNombre);
    	nom.add(op);
    	info.add(nom);
		return info;
	}
	
	
	private static void revisarTurno() {
		System.out.print("Ingrese fecha (aaaa/mm/dd/hh/mn): ");
		String horaS = input.next();
		String[] horaA = horaS.split("/");
		LocalDateTime hora =  LocalDateTime.of(Integer.parseInt(horaA[0]), Integer.parseInt(horaA[1]), Integer.parseInt(horaA[2]), Integer.parseInt(horaA[3]), Integer.parseInt(horaA[4]));
		Trabajo tr = emple.trabajoEnHora(hora);
		if (tr==null) {
			System.out.print("No tiene asignado un turno dentro de ese horario.\n ");
		}else {
			System.out.print("Trabajo en  "+tr.getServicio()+" donde el empleado tiene que "+tr.getDescripcion()+". \nNecesita nivel de capacitacion "+tr.getCapaciacion());
		}
	}
	private List<JLabel> inforevisarTurno(List<String> inputs) {
		List<JLabel> info  = new ArrayList<JLabel>();
		String horaS = inputs.getFirst();
		String[] horaA = horaS.split("/");
		LocalDateTime hora =  LocalDateTime.of(Integer.parseInt(horaA[0]), Integer.parseInt(horaA[1]), Integer.parseInt(horaA[2]), Integer.parseInt(horaA[3]), Integer.parseInt(horaA[4]));
		Trabajo tr = emple.trabajoEnHora(hora);
		if (tr==null) {
			JLabel s = new JLabel("No tiene asignado un turno dentro de ese horario.\n ");
			info.add(s);
		}else {
			String a = ("Trabajo en  "+tr.getServicio()+" donde el empleado tiene que "+tr.getDescripcion()+". \nNecesita nivel de capacitacion "+tr.getCapaciacion());
			JLabel s = new JLabel(a);
			info.add(s);
		}
		return info;
	}
	
	private List<JPanel> inputrevisarTurno() {
		List<JPanel> info = new ArrayList<JPanel>();
		JTextField op= new JTextField(16);
		op.setHorizontalAlignment(0);
    	JLabel labNombre = new JLabel("Ingrese fecha (aaaa/mm/dd/hh/mn): ");
    	JPanel nom = new JPanel();
    	nom.add(labNombre);
    	nom.add(op);
    	info.add(nom);
		return info;
	}
	
	
	
	private static void registrarVenta() {
		Trabajo tr = emple.trabajoEnHora(LocalDateTime.now());
		if (tr != null) {
			if (tr.getInventario().isEmpty()) {
				System.out.print("Su trabajo no le permite vender ningun producto \n");
			}else {
				System.out.print("Los productos que existen son: \n");
				for (String prod: tr.getInventario()) {
					System.out.print(prod+"\n");
				}
				System.out.print("Ingrese el nombre del producto: ");
				String producto = input.next();
				System.out.print("Ingrese las cantidades: ");
				int cantidad = input.nextInt();
				tr.vender(producto, cantidad);
			}
		}else {
			System.out.print("No puede vender ningun producto porque no se encuentra trabajando. \n");
		}
		
	}
	
	private List<JLabel> inforegistrarVenta(List<String> inputs) {
		List<JLabel> info  = new ArrayList<JLabel>();
		Trabajo tr = emple.trabajoEnHora(LocalDateTime.now());
		if (tr != null) {
			if (tr.getInventario().isEmpty()) {
				JLabel s = new JLabel("Su trabajo no le permite vender ningun producto \n");
				info.add(s);
			}else {
				String producto = inputs.getFirst();
				int cantidad = Integer.parseInt(inputs.getLast());
				tr.vender(producto, cantidad);
				JLabel s = new JLabel("Se han vendido los productos exitosamente");
				info.add(s);
			}
		}else {
			JLabel s = new JLabel("No puede vender ningun producto porque no se encuentra trabajando. \n");
			info.add(s);
		}
		return info;
	}
	
	private List<JPanel> inputregistrarVenta() {
		List<JPanel> info = new ArrayList<JPanel>();
		Trabajo tr = emple.trabajoEnHora(LocalDateTime.now());
		if (tr != null) {
			if (tr.getInventario().isEmpty()) {
				JLabel labNombre = new JLabel("Su trabajo no le permite vender ningun producto");
		    	JPanel nom = new JPanel();
		    	nom.add(labNombre);
			}else {
				JTextField op= new JTextField(16);
				op.setHorizontalAlignment(0);
		    	JLabel labNombre = new JLabel("Ingrese el nombre del producto: ");
		    	JPanel nom = new JPanel();
		    	nom.add(labNombre);
		    	nom.add(op);
		    	info.add(nom);
		    	JTextField op2= new JTextField(16);
				op2.setHorizontalAlignment(0);
		    	JLabel labNombre2 = new JLabel("Ingrese las cantidades: ");
		    	JPanel nom2 = new JPanel();
		    	nom2.add(labNombre2);
		    	nom2.add(op2);
		    	info.add(nom2);
			}
		}else {
			JLabel labNombre = new JLabel("No puede vender ningun producto porque no se encuentra trabajando ");
	    	JPanel nom = new JPanel();
	    	nom.add(labNombre);
		}
		return info;
		
	}
	
	private static void cambiarSenha() {
		System.out.print("Ingrese nueva contraseña: ");
		String senha = input.next();
		emple.cambiarContra(senha);
	}
	
	private static void infocambiarSenha(List<String> inputs) {
		List<JLabel> info  = new ArrayList<JLabel>();
		String senha = inputs.getFirst();
		emple.cambiarContra(senha);
		JLabel s = new JLabel("Se ha cambiado la contraseña exitosamente");
		info.add(s);
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
	
}
