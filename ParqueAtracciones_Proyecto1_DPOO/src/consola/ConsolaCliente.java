package consola;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
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


public class ConsolaCliente {

	private static Cliente cliente;
	
	private static ParqueAtraccion parque;
	
	private static int peso;
	
	private static int altura;
	
	private static int edad;
	
	private static ArrayList<String> salud;
	
	private static Scanner input;
	
	
	public ConsolaCliente(Cliente cliente, ParqueAtraccion parque) {
		this.cliente = cliente;
		this.salud = new ArrayList<String>();
		this.parque = parque;
		peso=0;
		altura = 0;
		edad=0;
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
	
	private static int menu() throws ExceptionInputIncorrecto {
		System.out.print("1 - Ver tu informacion\n");
		System.out.print("2 - Anadir informacion de salud\n");
		System.out.print("3 - Cambiar contraseña\n");
		System.out.print("4 - Ver atracciones disonibles\n");
		System.out.print("5 - Ver informcion de una atraccion\n");
		System.out.print("6 - Revisar si podria usar una atraccion\n ");
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
		System.out.print("Nombre: "+ nombre+"/n");
		System.out.print("Identificacion: "+id+"/n");
		System.out.print("Numero de tiquetes usados: "+tiqUsados+"\n");
		System.out.print("Numero de tiquetes sin usar: "+tiqSinUsar+"\n");
		System.out.print("Numero de entradas usadas: "+enUsados+"\n");
		System.out.print("Numero de entradas sin usar: "+enSinUsar+"\n");
		System.out.print("Numero de fastPass usados: "+fastUsados+"\n");
		System.out.print("Numero de fastPass sin usar: "+fastSinUsar+"\n");
	}
	
	public static void anadirInfoSalud() throws ExceptionInputIncorrecto {
		input = new Scanner(System.in);
		System.out.print("Ingrese contraindicaciones: ");
		String contraindicacion = input.next();
		input = new Scanner(System.in);
		System.out.print("Desea cambiar su altura, edad y peso? ");
		String cambiar = input.next();
		if(cambiar == "si" || cambiar=="Si" || cambiar =="1") {
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
			salud.add(contraindicacion);
			altura = alt;
			peso = pesoA;
			edad = edadA;
		}
	}
	
	private static void cambiarSenha(){
		input = new Scanner(System.in);
		System.out.print("Ingrese nueva contraseña: ");
		String senha = input.next();
		parque.cambiarSenha(cliente, senha);
	}
	
	
	private static void verAtracciones() {
		HashMap<String, String> atracciones = parque.getInfoAtracciones();
		if (atracciones.isEmpty()) {
			System.out.print("No hay atracciones disponibles.\n ");
		}
		for (String atraccion: atracciones.keySet()) {
			System.out.print(atraccion + ": "+atracciones.get(atraccion)+"\n");
		}
	}
	
	private static void verInfoAtraccion() {
		input = new Scanner(System.in);
		System.out.print("Ingrese el nombre de la atraccion: ");
		String nombre = input.next();
		String[] info = parque.getInfoAtraccion(nombre).split(";");
		for(int i=0;i<info.length;i++) {
			System.out.print(info[i]+"/n");
		}
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
	
	private static void verInfoEspectaculo() {
		input = new Scanner(System.in);
		System.out.print("Ingrese el nombre del espectaculo: ");
		String nombre = input.next();
		String[] info = parque.getInfoEspectaculo(nombre).split(";");
		for(int i=0;i<info.length;i++) {
			System.out.print(info[i]+"/n");
		}
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
	
	private static void verTiquetesSinUsar() {
		ArrayList<Tiquete> tiqNoUsados = cliente.getTiquetesSinUsar();
		if (tiqNoUsados.isEmpty()) {
			System.out.print("No hay tiquetes por usar. ");
		}
		for (Tiquete tiq: tiqNoUsados) {
			System.out.print("Tiquete numero "+ tiq.getCodigo()+"\n");
		}
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
	
	private static void comprarEntrada() {
		input = new Scanner(System.in);
		System.out.print("Ingrese el nombre de la atraccion: ");
		String atraccion = input.next();
		Entrada en = parque.getCajaVirtual().venderEntrada(atraccion);
		cliente.anadirEn(en);
		System.out.print("Comprada la entrada numero "+ en.getCodigo()+" con un valor de "+en.getPrecio()+"\n");
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
	
	private static void puedeEntrar() throws ExceptionInfoNotFound {
		if(edad==0 || salud.isEmpty() || peso==0 || altura==0) {
			System.out.print("Por favor ingrese primero su informacion de salud");
		}else {
			input = new Scanner(System.in);
			System.out.print("Ingrese el nombre de la atraccion: ");
			String nombre = input.next();
			Atraccion at = parque.getAtraccion(nombre);
			boolean puede = false;
			if(at instanceof AtraccionMecanica) {
				AtraccionMecanica atm = (AtraccionMecanica) at;
				puede = atm.puedeEntrar(peso, altura, salud);
			}
			if(at instanceof AtraccionCultural) {
				AtraccionCultural atm = (AtraccionCultural) at;
				puede = atm.puedeEntrar(edad);
			}
			if(puede) {
				System.out.print("En base a su informacion de salud, usted puede usar la atraccion!!! ");
			}else {
				System.out.print("En base a su informacion de salud, usted no puede usar la atraccion.");
			}
		}
	}
	
	
	
}
