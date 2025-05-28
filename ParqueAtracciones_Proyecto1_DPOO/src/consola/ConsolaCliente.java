package consola;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import parque.ParqueAtraccion;
import cliente.Cliente;
import exceptions.ExceptionInfoNotFound;
import exceptions.ExceptionInputIncorrecto;
import financiero.Entrada;
import financiero.FastPass;
import financiero.Tiquete;
import juegos.Atraccion;
import juegos.AtraccionCultural;
import juegos.AtraccionMecanica;


public class ConsolaCliente extends ConsolaMain{

	private static Cliente cliente;
	
	private static ParqueAtraccion parque;
	
	
	private static Scanner input;
	
	
	public ConsolaCliente(Cliente cliente, ParqueAtraccion parque) {
		super();
		this.cliente = cliente;
		this.parque = parque;
		Scanner input = new Scanner(System.in);
	}
	
	
	public static void empezarConsola() throws ExceptionInputIncorrecto, ExceptionInfoNotFound {
		System.out.print("Bienvenido "+cliente.getNombre()+"!!!\n");
		boolean correr = true;
		while (correr ==true) {
			System.out.print("Que deseas hacer hoy?\n");
			int opcion = menu();
			if (opcion ==1) {
				verInfo();
			} else if (opcion ==2) {
				anadirInfoSalud();
			} else if (opcion ==3) {
				cambiarSenha();
			}else if (opcion ==4) {
				verAtracciones();
			}else if (opcion ==5) {
				verInfoAtraccion();
			}else if (opcion ==6) {
				puedeEntrar();
			}else if (opcion ==7) {
				verEspectaculos();
			}else if (opcion ==8) {
				verInfoEspectaculo();
			}else if (opcion ==9) {
				verTiquetesUsados();
			}else if (opcion ==10) {
				verEntradasUsadas();
			}else if (opcion ==11) {
				verFastPassUsados();
			}else if (opcion ==12) {
				verTiquetesSinUsar();
			}else if (opcion ==13) {
				verEntradasNoUsadas();
			}else if (opcion ==14) {
				verFastPassNoUsados();
			}else if (opcion ==15) {
				comprarTiquete();
			}else if (opcion ==16) {
				comprarEntrada();
			}else if (opcion ==17) {
				comprarFastPass();
			}else if (opcion ==18) {
				correr=false;
			}
		}
		
	}
	
	
	public List<JLabel> getInfo(List<String> inputs){
		List<JLabel> info = new ArrayList<JLabel>();
		if (opcion ==1) {
			info = infoverInfo();
		} else if (opcion ==2) {
			info =infoanadirInfoSalud(inputs);
		} else if (opcion ==3) {
			info =infocambiarSenha(inputs);
		}else if (opcion ==4) {
			info =infoverAtracciones();
		}else if (opcion ==5) {
			info =infoverInfoAtraccion(inputs);
		}else if (opcion ==6) {
			try {
				info =infopuedeEntrar(inputs);
			} catch (ExceptionInfoNotFound e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (opcion ==7) {
			info =infoverEspectaculos();
		}else if (opcion ==8) {
			info =infoverInfoEspectaculo(inputs);
		}else if (opcion ==9) {
			info =infoverTiquetesUsados();
		}else if (opcion ==10) {
			info =infoverEntradasUsadas();
		}else if (opcion ==11) {
			info =infoverFastPassUsados();
		}else if (opcion ==12) {
			info =infoverTiquetesSinUsar();
		}else if (opcion ==13) {
			info =infoverEntradasNoUsadas();
		}else if (opcion ==14) {
			info =infoverFastPassNoUsados();
		}else if (opcion ==15) {
			info =infocomprarTiquete(inputs);
		}else if (opcion ==16) {
			info =infocomprarEntrada(inputs);
		}else if (opcion ==17) {
			info =infocomprarFastPass(inputs);
		}
		return info;
	}
	
	
	public List<JPanel> getInput(){
		List<JPanel> input = new ArrayList<JPanel>();
		if (opcion ==2) {
			input = inputanadirInfoSalud();
		} else if (opcion ==3) {
			input =inputcambiarSenha();
		}else if (opcion ==5) {
			input =inputverInfoAtraccion();
		}else if (opcion ==8) {
			input =inputverInfoEspectaculo();
		}else if (opcion ==15) {
			input =inputcomprarTiquete();
		}else if (opcion ==16) {
			input =inputcomprarEntrada();
		}else if (opcion ==17) {
			input =inputcomprarFastPass();
		}
		return input;
	}
		
	
	
	public String[] getMenu() {
		String opciones = "1 - Ver tu informacion;2 - Anadir informacion de salud;3 - Cambiar contraseña;4 - Ver atracciones disonibles;5 - Ver informcion de una atraccion;6 - Revisar si podria usar una atraccion"
				+";7 - Ver espectaculos disonibles;8 - Ver informcion de un espectaculo;9 - Ver tiquetes usados;10 - Ver entradas usadas"
				+"11 - Ver fast pass usados;12 - Ver tiquetes comprados;13 - Ver entradas compradas;14 - Ver fast pass comprados;15 - Compar tiquetes;16 - Comprar entradas;17 - Comprar fast pass;18 - Cerrar sesion";
		String[] menu = opciones.split(";");
		return menu;
	}
	
	private static int menu() throws ExceptionInputIncorrecto {
		System.out.print("1 - Ver tu informacion\n");
		System.out.print("2 - Anadir informacion de salud\n");
		System.out.print("3 - Cambiar contraseña\n");
		System.out.print("4 - Ver atracciones disonibles\n");
		System.out.print("5 - Ver informcion de una atraccion\n");
		System.out.print("6 - Revisar si podria usar una atraccion\n");
		System.out.print("7 - Ver espectaculos disonibles\n");
		System.out.print("8 - Ver informcion de un espectaculo\n");
		System.out.print("9 - Ver tiquetes usados\n");
		System.out.print("10 - Ver entradas usadas\n");
		System.out.print("11 - Ver fast pass usados\n");
		System.out.print("12 - Ver tiquetes comprados\n");
		System.out.print("13 - Ver entradas compradas\n");
		System.out.print("14 - Ver fast pass comprados\n");
		System.out.print("15 - Compar tiquetes\n");
		System.out.print("16 - Comprar entradas\n");
		System.out.print("17 - Comprar fast pass\n");
		System.out.print("18 - Cerrar sesion\n");
		input = new Scanner(System.in);
		if (input.hasNextInt()==false) {
			throw new ExceptionInputIncorrecto("Por favor ingresar un numero");
		}
		int numero = input.nextInt();
		if (numero <0 || numero>19) {
			numero = menu();
		}
		return numero;
	}
	
	
	private static void verInfo() {
		String nombre = cliente.getNombre();
		int id = cliente.getId();
		int tiqUsados = cliente.getTiquetesUsados().size();
		int tiqSinUsar = cliente.getTiquetesSinUsar().size();
		int enUsados = cliente.getEntradasUsadas().size();
		int enSinUsar = cliente.getEntradasSinUsar().size();
		int fastUsados = cliente.getFastPassUsados().size();
		int fastSinUsar = cliente.getFastPassSinUsar().size();
		System.out.print("Nombre: "+ nombre+"\n");
		System.out.print("Identificacion: "+id+"\n");
		System.out.print("Numero de tiquetes usados: "+tiqUsados+"\n");
		System.out.print("Numero de tiquetes sin usar: "+tiqSinUsar+"\n");
		System.out.print("Numero de entradas usadas: "+enUsados+"\n");
		System.out.print("Numero de entradas sin usar: "+enSinUsar+"\n");
		System.out.print("Numero de fastPass usados: "+fastUsados+"\n");
		System.out.print("Numero de fastPass sin usar: "+fastSinUsar+"\n");
	}
	
	private List<JLabel> infoverInfo() {
		List<JLabel> info  = new ArrayList<JLabel>();
		String nombre = cliente.getNombre();
		int id = cliente.getId();
		int tiqUsados = cliente.getTiquetesUsados().size();
		int tiqSinUsar = cliente.getTiquetesSinUsar().size();
		int enUsados = cliente.getEntradasUsadas().size();
		int enSinUsar = cliente.getEntradasSinUsar().size();
		int fastUsados = cliente.getFastPassUsados().size();
		int fastSinUsar = cliente.getFastPassSinUsar().size();
		JLabel s1 = new JLabel(("Nombre: "+ nombre));
		JLabel s2 = new JLabel(("Identificacion: "+id));
		JLabel s3 = new JLabel(("Numero de tiquetes usados: "+tiqUsados));
		JLabel s4 = new JLabel(("Numero de tiquetes sin usar: "+tiqSinUsar));
		JLabel s5 = new JLabel(("Numero de entradas usadas: "+enUsados));
		JLabel s6 = new JLabel(("Numero de entradas sin usar: "+enSinUsar));
		JLabel s7 = new JLabel(("Numero de fastPass usados: "+fastUsados));
		JLabel s8 = new JLabel(("Numero de fastPass sin usar: "+fastSinUsar));
		info.add(s1);
		info.add(s2);
		info.add(s3);
		info.add(s4);
		info.add(s5);
		info.add(s6);
		info.add(s7);
		info.add(s8);
		return info;
	}
	
	public static void anadirInfoSalud() throws ExceptionInputIncorrecto {
		input = new Scanner(System.in);
		System.out.print("Ingrese contraindicaciones: ");
		String contraindicacion = input.nextLine();
		input = new Scanner(System.in);
		System.out.print("Desea cambiar su altura, edad y peso? ");
		String cambiar = input.next();
		if(cambiar.contentEquals("si") || cambiar.contentEquals("Si") || cambiar.contentEquals("1")) {
			System.out.print("Ingrese su altura: ");
			if (input.hasNextInt()==false) {
				throw new ExceptionInputIncorrecto("La altura en centimetros debe ser un numero.");
			}
			int alt = input.nextInt();
			System.out.print("Ingrese su peso: ");
			if (input.hasNextInt()==false) {
				throw new ExceptionInputIncorrecto("El peso en kilos debe ser un numero.");
			}
			int pesoA = input.nextInt();
			System.out.print("Ingrese su edad: ");
			if (input.hasNextInt()==false) {
				throw new ExceptionInputIncorrecto("La edad debe ser un numero.");
			}
			int edadA = input.nextInt();
			cliente.actualizarPeso(pesoA);
			cliente.actualizarEdad(edadA);
			cliente.actualizarAlt(alt);
			cliente.actualizarSalud(contraindicacion);
		}
	}
	
	public List<JLabel> infoanadirInfoSalud(List<String> inputs){
		String contraindicacion = inputs.get(0);
		int alt = Integer.parseInt(inputs.get(1));
		int pesoA = Integer.parseInt(inputs.get(2));
		int edadA = Integer.parseInt(inputs.get(3));
		cliente.actualizarPeso(pesoA);
		cliente.actualizarEdad(edadA);
		cliente.actualizarAlt(alt);
		cliente.actualizarSalud(contraindicacion);
		List<JLabel> info  = new ArrayList<JLabel>();
		JLabel s = new JLabel("Se ha cambiado la informacion de salud exitosamente");
		info.add(s);
		return info;
	}
	
	
	
	
	public List<JPanel> inputanadirInfoSalud() {
		List<JPanel> info = new ArrayList<JPanel>();
		JTextField op= new JTextField(16);
		op.setHorizontalAlignment(0);
    	JLabel labNombre = new JLabel("Ingrese contraindicaciones: ");
    	JPanel nom = new JPanel();
    	nom.add(labNombre);
    	nom.add(op);
    	info.add(nom);
    	JTextField op2= new JTextField(16);
		op2.setHorizontalAlignment(0);
    	JLabel labNombre2 = new JLabel("Ingrese su altura: ");
    	JPanel nom2 = new JPanel();
    	nom2.add(labNombre2);
    	nom2.add(op2);
    	info.add(nom2);
    	JTextField op3= new JTextField(16);
		op3.setHorizontalAlignment(0);
    	JLabel labNombre3 = new JLabel("Ingrese su peso: ");
    	JPanel nom3 = new JPanel();
    	nom3.add(labNombre3);
    	nom3.add(op3);
    	info.add(nom3);
    	JTextField op4= new JTextField(16);
		op4.setHorizontalAlignment(0);
    	JLabel labNombre4 = new JLabel("Ingrese su edad: ");
    	JPanel nom4 = new JPanel();
    	nom4.add(labNombre4);
    	nom4.add(op4);
    	info.add(nom4);
		return info;
	}
	
	private static void cambiarSenha(){
		input = new Scanner(System.in);
		System.out.print("Ingrese nueva contraseña: ");
		String senha = input.next();
		parque.cambiarSenha(cliente, senha);
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
		String senha = inputs.getFirst();
		parque.cambiarSenha(cliente, senha);
		JLabel s = new JLabel("Se ha cambiado la contraseña exitosamente");
		info.add(s);
		return info;
	}
	
	
	
	private static void verAtracciones() {
		HashMap<String, String> atracciones = parque.getInfoAtracciones();
		if (atracciones.isEmpty()) {
			System.out.print("No hay atracciones disponibles.\n ");
		}
		for (String atraccion: atracciones.keySet()) {
			System.out.print(atracciones.get(atraccion)+"\n");
		}
	}
	
	private List<JLabel> infoverAtracciones() {
		List<JLabel> info  = new ArrayList<JLabel>();
		HashMap<String, String> atracciones = parque.getInfoAtracciones();
		if (atracciones.isEmpty()) {
			JLabel s = new JLabel("No hay atracciones disponibles.");
			info.add(s);
		}
		for (String atraccion: atracciones.keySet()) {
			String a = atracciones.get(atraccion);
			JLabel s = new JLabel(a);
			info.add(s);
		}
		return info;
	}
	
	
	
	private static void verInfoAtraccion() {
		input = new Scanner(System.in);
		System.out.print("Ingrese el nombre de la atraccion: ");
		String nombre = input.nextLine();
		String[] info = parque.getInfoAtraccion(nombre).split(";");
		for(int i=0;i<info.length;i++) {
			System.out.print(info[i]+"\n");
		}
	}
	
	private List<JLabel> infoverInfoAtraccion(List<String> inputs) {
		List<JLabel> info  = new ArrayList<JLabel>();
		String nombre = inputs.getFirst();
		String[] data = parque.getInfoAtraccion(nombre).split(";");
		for(int i=0;i<data.length;i++) {
			JLabel s = new JLabel(data[i]);
			info.add(s);
		}
		return info;
	}
	
	private List<JPanel> inputverInfoAtraccion() {
		List<JPanel> info = new ArrayList<JPanel>();
		JTextField op= new JTextField(16);
		op.setHorizontalAlignment(0);
    	JLabel labNombre = new JLabel( "Ingrese el nombre de la atraccion: " );
    	JPanel nom = new JPanel();
    	nom.add(labNombre);
    	nom.add(op);
    	info.add(nom);
    	return info;
	}
	
	private static void verEspectaculos() {
		LocalDateTime ahora = LocalDateTime.now();
		input = new Scanner(System.in);
		System.out.print("Esta soleado (si o no): ");
		String clima = input.next();
		HashMap<String, String> espectaculos = parque.getInfoEspectaculos(ahora, clima);
		if (espectaculos.isEmpty()) {
			System.out.print("No hay espectaculos disponibles.\n ");
		}
		for (String esp: espectaculos.keySet()) {
			System.out.print(esp + ": "+espectaculos.get(esp)+"\n");
		}
	}
	
	private List<JLabel> infoverEspectaculos() {
		List<JLabel> info  = new ArrayList<JLabel>();
		LocalDateTime ahora = LocalDateTime.now();
		HashMap<String, String> espectaculos = parque.getInfoEspectaculos(ahora, "");
		if (espectaculos.isEmpty()) {
			JLabel a = new JLabel("No hay espectaculos disponibles");
			info.add(a);
		}
		for (String esp: espectaculos.keySet()) {
			String s = esp + ": "+espectaculos.get(esp);
			JLabel a = new JLabel(s);
			info.add(a);
		}
		return info;
	}
	
	
	
	
	private static void verInfoEspectaculo() {
		input = new Scanner(System.in);
		System.out.print("Ingrese el nombre del espectaculo: ");
		String nombre = input.nextLine();
		String[] info = parque.getInfoEspectaculo(nombre).split(";");
		for(int i=0;i<info.length;i++) {
			System.out.print(info[i]+"\n");
		}
	}
	
	
	private List<JLabel> infoverInfoEspectaculo(List<String> inputs) {
		List<JLabel> info  = new ArrayList<JLabel>();
		String nombre = inputs.getFirst();
		String[] data = parque.getInfoEspectaculo(nombre).split(";");
		for(int i=0;i<data.length;i++) {
			JLabel s = new JLabel(data[i]);
			info.add(s);
		}
		return info;
	}
	
	private List<JPanel>inputverInfoEspectaculo() {
		List<JPanel> info = new ArrayList<JPanel>();
		JTextField op= new JTextField(16);
		op.setHorizontalAlignment(0);
    	JLabel labNombre = new JLabel( "Ingrese el nombre del espectaculo: ");
    	JPanel nom = new JPanel();
    	nom.add(labNombre);
    	nom.add(op);
    	info.add(nom);
    	return info;
	}
	
	private static void verTiquetesUsados() {
		ArrayList<Tiquete> tiqUsados = cliente.getTiquetesUsados();
		if (tiqUsados.isEmpty()) {
			System.out.print("No se ha usado ningun tiquete. ");
		}
		for (Tiquete tiq: tiqUsados) {
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
			System.out.print("Tiquete numero "+ tiq.getCodigo()+" usado el "+tiq.getFechaUso().format(formatter)+"\n");
		}
	}
	
	private List<JLabel> infoverTiquetesUsados() {
		List<JLabel> info  = new ArrayList<JLabel>();
		ArrayList<Tiquete> tiqUsados = cliente.getTiquetesUsados();
		if (tiqUsados.isEmpty()) {
			JLabel s = new JLabel("No se ha usado ningun tiquete. ");
			info.add(s);
		}
		for (Tiquete tiq: tiqUsados) {
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
			String a = "Tiquete numero "+ tiq.getCodigo()+" usado el "+tiq.getFechaUso().format(formatter);
			JLabel s = new JLabel(a);
			info.add(s);
		}
		return info;
	}
	
	private static void verTiquetesSinUsar() {
		ArrayList<Tiquete> tiqNoUsados = cliente.getTiquetesSinUsar();
		if (tiqNoUsados.isEmpty()) {
			System.out.print("No hay tiquetes por usar. ");
		}
		for (Tiquete tiq: tiqNoUsados) {
			System.out.print("Tiquete numero "+ tiq.getCodigo()+"\n");
		}
	}
	
	private List<JLabel> infoverTiquetesSinUsar() {
		List<JLabel> info  = new ArrayList<JLabel>();
		ArrayList<Tiquete> tiqNoUsados = cliente.getTiquetesSinUsar();
		if (tiqNoUsados.isEmpty()) {
			JLabel s = new JLabel("No hay tiquetes por usar. ");
			info.add(s);
		}
		for (Tiquete tiq: tiqNoUsados) {
			String a = "Tiquete numero "+ tiq.getCodigo();
			JLabel s = new JLabel(a);
			info.add(s);
		}
		return info;
	}
	
	private static void verEntradasUsadas() {
		ArrayList<Entrada> enUsadas = cliente.getEntradasUsadas();
		if (enUsadas.isEmpty()) {
			System.out.print("No se ha usado ninguna entrada. ");
		}
		for (Entrada en: enUsadas) {
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
			System.out.print("Tiquete numero "+ en.getCodigo()+" usado el "+en.getFechaUso().format(formatter)+"\n");
		}
	}
	
	private List<JLabel> infoverEntradasUsadas() {
		List<JLabel> info  = new ArrayList<JLabel>();
		ArrayList<Entrada> enUsadas = cliente.getEntradasUsadas();
		if (enUsadas.isEmpty()) {
			JLabel s = new JLabel("No se ha usado ninguna entrada. ");
		}
		for (Entrada en: enUsadas) {
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
			String a = "Tiquete numero "+ en.getCodigo()+" usado el "+en.getFechaUso().format(formatter);
			JLabel s = new JLabel(a);
			info.add(s);
		}
		return info;
	}
	
	private static void verEntradasNoUsadas() {
		ArrayList<Entrada> enNoUsadas = cliente.getEntradasSinUsar();
		if (enNoUsadas.isEmpty()) {
			System.out.print("No hay entradas por usar. ");
		}
		for (Entrada en: enNoUsadas) {
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
			System.out.print("Tiquete numero "+ en.getCodigo()+" para usar el "+en.getFechaUso().format(formatter)+"\n");
		}
	}
	
	private List<JLabel> infoverEntradasNoUsadas() {
		List<JLabel> info  = new ArrayList<JLabel>();
		ArrayList<Entrada> enNoUsadas = cliente.getEntradasSinUsar();
		if (enNoUsadas.isEmpty()) {
			JLabel s = new JLabel("No hay entradas por usar. ");
			info.add(s);
		}
		for (Entrada en: enNoUsadas) {
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
			String a = "Tiquete numero "+ en.getCodigo()+" para usar el "+en.getFechaUso().format(formatter);
			JLabel s = new JLabel(a);
			info.add(s);
		}
		return info;
	}
	
	private static void verFastPassUsados() {
		ArrayList<FastPass> fastUsados = cliente.getFastPassUsados();
		if (fastUsados.isEmpty()) {
			System.out.print("No se ha usado ningun tiquete. ");
		}
		for (FastPass fast: fastUsados) {
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
			System.out.print("Tiquete numero "+ fast.getCodigo()+" usado el "+fast.getFecha().format(formatter)+"\n");
		}
	}
	
	private List<JLabel> infoverFastPassUsados() {
		List<JLabel> info  = new ArrayList<JLabel>();
		ArrayList<FastPass> fastUsados = cliente.getFastPassUsados();
		if (fastUsados.isEmpty()) {
			JLabel s = new JLabel("No se ha usado ningun tiquete. ");
			info.add(s);
		}
		for (FastPass fast: fastUsados) {
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
			String a = "Tiquete numero "+ fast.getCodigo()+" usado el "+fast.getFecha().format(formatter);
			JLabel s = new JLabel(a);
			info.add(s);
		}
		return info;
	}
	
	private static void verFastPassNoUsados() {
		ArrayList<FastPass> fastNoUsados = cliente.getFastPassSinUsar();
		if (fastNoUsados.isEmpty()) {
			System.out.print("No hay FastPass por usar. ");
		}
		for (FastPass fast: fastNoUsados) {
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
			System.out.print("Tiquete numero "+ fast.getCodigo()+" para usar el "+fast.getFecha().format(formatter)+"\n");
		}
	}
	
	private List<JLabel> infoverFastPassNoUsados() {
		List<JLabel> info  = new ArrayList<JLabel>();
		ArrayList<FastPass> fastNoUsados = cliente.getFastPassSinUsar();
		if (fastNoUsados.isEmpty()) {
			JLabel s = new JLabel("No se ha usado ningun tiquete. ");
			info.add(s);
		}
		for (FastPass fast: fastNoUsados) {
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
			String a = "Tiquete numero "+ fast.getCodigo()+" usado el "+fast.getFecha().format(formatter);
			JLabel s = new JLabel(a);
			info.add(s);
		}
		return info;
	}
	
	private static void comprarTiquete() throws ExceptionInputIncorrecto {
		input = new Scanner(System.in);
		System.out.print("Desea comprar para un rango de fecha: ");
		String fechas = input.next();
		input = new Scanner(System.in);
		System.out.print("Ingrese el nivel de exclusividad que dese comprar: ");
		String nivel = input.next();
		if (fechas.contentEquals("si")) {
			input = new Scanner(System.in);
			System.out.print("Ingrese fecha inicio (aaaa/mm/dd): ");
			String iniS = input.next();
			String[] iniA = iniS.split("/");
			if (iniA.length<3) {
				throw new ExceptionInputIncorrecto("Por favor poner la fecha en formato (aaaa/mm/dd).");
			}
			input = new Scanner(System.in);
			System.out.print("Ingrese fecha final (aaaa/mm/dd): ");
			String finS = input.next();
			String[] finA = finS.split("/");
			if (finA.length<3) {
				throw new ExceptionInputIncorrecto("Por favor poner la fecha en formato (aaaa/mm/dd).");
			}
			Tiquete tiq = parque.getCajaVirtual().venderTiquete(nivel, LocalDateTime.of(Integer.parseInt(iniA[0]), Integer.parseInt(iniA[1]), Integer.parseInt(iniA[2]), 11, 0), LocalDateTime.of(Integer.parseInt(finA[0]), Integer.parseInt(finA[1]), Integer.parseInt(finA[2]), 21, 0));
			cliente.anadirTiq(tiq);
			System.out.print("Comprado el tiquete numero "+ tiq.getCodigo()+" con un valor de "+tiq.getPrecio()+"\n");
		}else {
			Tiquete tiq = parque.getCajaVirtual().venderTiquete(nivel);
			cliente.anadirTiq(tiq);
			System.out.print("Comprado el tiquete numero "+ tiq.getCodigo()+" con un valor de "+tiq.getPrecio()+"\n");
		}
	}
	
	
	private List<JLabel> infocomprarTiquete(List<String> inputs){
		List<JLabel> info  = new ArrayList<JLabel>();
		String nivel = inputs.get(0);
		String ini = inputs.get(0);
		String fin = inputs.get(1);
		if (ini.isBlank()==false){
			String[] iniA = ini.split("/");
			String[] finA = fin.split("/");
			Tiquete tiq = parque.getCajaVirtual().venderTiquete(nivel, LocalDateTime.of(Integer.parseInt(iniA[0]), Integer.parseInt(iniA[1]), Integer.parseInt(iniA[2]), 11, 0), LocalDateTime.of(Integer.parseInt(finA[0]), Integer.parseInt(finA[1]), Integer.parseInt(finA[2]), 21, 0));
			cliente.anadirTiq(tiq);
			String a = "Comprado el tiquete numero "+ tiq.getCodigo()+" con un valor de "+tiq.getPrecio();
			JLabel s = new JLabel(a);
			info.add(s);
		}else {
			Tiquete tiq = parque.getCajaVirtual().venderTiquete(nivel);
			cliente.anadirTiq(tiq);
			String a = "Comprado el tiquete numero "+ tiq.getCodigo()+" con un valor de "+tiq.getPrecio();
			JLabel s = new JLabel(a);
			info.add(s);
		}
		return info;
	}
	
	private List<JPanel> inputcomprarTiquete(){
		List<JPanel> info = new ArrayList<JPanel>();
		JTextField op= new JTextField(16);
		op.setHorizontalAlignment(0);
    	JLabel labNombre = new JLabel("Ingrese el nivel de exclusividad que dese comprar: ");
    	JPanel nom = new JPanel();
    	nom.add(labNombre);
    	nom.add(op);
    	info.add(nom);
    	JTextField op2= new JTextField(16);
		op2.setHorizontalAlignment(0);
    	JLabel labNombre2 = new JLabel("Ingrese fecha inicio (aaaa/mm/dd): ");
    	JPanel nom2 = new JPanel();
    	nom2.add(labNombre2);
    	nom2.add(op2);
    	info.add(nom2);
    	JTextField op3= new JTextField(16);
		op3.setHorizontalAlignment(0);
    	JLabel labNombre3 = new JLabel("Ingrese fecha final (aaaa/mm/dd): ");
    	JPanel nom3 = new JPanel();
    	nom3.add(labNombre3);
    	nom3.add(op3);
    	info.add(nom3);
		return info;
	}
	
	private static void comprarEntrada() {
		input = new Scanner(System.in);
		System.out.print("Ingrese el nombre de la atraccion: ");
		String atraccion = input.next();
		Entrada en = parque.getCajaVirtual().venderEntrada(atraccion);
		cliente.anadirEn(en);
		System.out.print("Comprada la entrada numero "+ en.getCodigo()+" con un valor de "+en.getPrecio()+"\n");
	}
	
	private List<JLabel> infocomprarEntrada(List<String> inputs) {
		List<JLabel> info  = new ArrayList<JLabel>();
		String atraccion = inputs.getFirst();
		Entrada en = parque.getCajaVirtual().venderEntrada(atraccion);
		cliente.anadirEn(en);
		String a = "Comprada la entrada numero "+ en.getCodigo()+" con un valor de "+en.getPrecio();
		JLabel s = new JLabel(a);
		info.add(s);
		return info;
	}
	
	private List<JPanel> inputcomprarEntrada() {
		List<JPanel> info = new ArrayList<JPanel>();
		JTextField op= new JTextField(16);
		op.setHorizontalAlignment(0);
    	JLabel labNombre = new JLabel("Ingrese el nombre de la atraccion: ");
    	JPanel nom = new JPanel();
    	nom.add(labNombre);
    	nom.add(op);
    	info.add(nom);
    	return info;
	}
	
	private static void comprarFastPass() throws ExceptionInputIncorrecto {
		input = new Scanner(System.in);
		System.out.print("Ingrese fecha de uso: ");
		String iniS = input.next();
		String[] iniA = iniS.split("/");
		if (iniA.length<3) {
			throw new ExceptionInputIncorrecto("Por favor poner la fecha en formato (aaaa/mm/dd).");
		}
		FastPass fast = parque.getCajaVirtual().venderFastPass(LocalDateTime.of(Integer.parseInt(iniA[0]), Integer.parseInt(iniA[1]), Integer.parseInt(iniA[2]), 11, 0));
		cliente.anadirFastPass(fast);
		System.out.print("Comprado el FastPass numero "+ fast.getCodigo()+" con un valor de "+fast.getPrecio()+"\n");
	}
	
	private List<JLabel> infocomprarFastPass(List<String> inputs){
		List<JLabel> info  = new ArrayList<JLabel>();
		String iniS = inputs.get(0);
		String[] iniA = iniS.split("/");
		FastPass fast = parque.getCajaVirtual().venderFastPass(LocalDateTime.of(Integer.parseInt(iniA[0]), Integer.parseInt(iniA[1]), Integer.parseInt(iniA[2]), 11, 0));
		String a = "Comprado el FastPass numero "+ fast.getCodigo()+" con un valor de "+fast.getPrecio();
		JLabel s = new JLabel(a);
		info.add(s);
		return info;
	}
	
	private List<JPanel> inputcomprarFastPass() {
		List<JPanel> info = new ArrayList<JPanel>();
		JTextField op= new JTextField(16);
		op.setHorizontalAlignment(0);
    	JLabel labNombre = new JLabel("Ingrese fecha de uso: ");
    	JPanel nom = new JPanel();
    	nom.add(labNombre);
    	nom.add(op);
    	info.add(nom);
    	return info;
	}
	
	private static void puedeEntrar() throws ExceptionInfoNotFound {
		if(cliente.getEdad()==0 || cliente.getSalud().isEmpty() || cliente.getPeso()==0 || cliente.getAlt()==0) {
			System.out.print("Por favor ingrese primero su informacion de salud");
		}else {
			input = new Scanner(System.in);
			System.out.print("Ingrese el nombre de la atraccion: ");
			String nombre = input.nextLine();
			Atraccion at = parque.getAtraccion(nombre);
			boolean puede = false;
			if(at instanceof AtraccionMecanica) {
				AtraccionMecanica atm = (AtraccionMecanica) at;
				puede = atm.puedeEntrar(cliente.getPeso(), cliente.getAlt(), cliente.getSalud());
			}
			if(at instanceof AtraccionCultural) {
				AtraccionCultural atc = (AtraccionCultural) at;
				puede = atc.puedeEntrar(cliente.getEdad());
			}
			if(puede) {
				System.out.print("En base a su informacion de salud, usted puede usar la atraccion!!! ");
			}else {
				System.out.print("En base a su informacion de salud, usted no puede usar la atraccion.");
			}
		}
	}
	
	private List<JLabel> infopuedeEntrar(List<String> inputs) throws ExceptionInfoNotFound {
		List<JLabel> info  = new ArrayList<JLabel>();
		if(cliente.getEdad()==0 || cliente.getSalud().isEmpty() || cliente.getPeso()==0 || cliente.getAlt()==0) {
			JLabel s = new JLabel("Por favor ingrese primero su informacion de salud");
			info.add(s);
		}else {
			String nombre = inputs.getFirst();
			Atraccion at = parque.getAtraccion(nombre);
			boolean puede = false;
			if(at instanceof AtraccionMecanica) {
				AtraccionMecanica atm = (AtraccionMecanica) at;
				puede = atm.puedeEntrar(cliente.getPeso(), cliente.getAlt(), cliente.getSalud());
			}
			if(at instanceof AtraccionCultural) {
				AtraccionCultural atc = (AtraccionCultural) at;
				puede = atc.puedeEntrar(cliente.getEdad());
			}
			if(puede) {
				JLabel s = new JLabel("En base a su informacion de salud, usted puede usar la atraccion!!! ");
				info.add(s);
			}else {
				JLabel s = new JLabel("En base a su informacion de salud, usted no puede usar la atraccion.");
				info.add(s);
			}
		}
		return info;
	}
	
	
	
}
