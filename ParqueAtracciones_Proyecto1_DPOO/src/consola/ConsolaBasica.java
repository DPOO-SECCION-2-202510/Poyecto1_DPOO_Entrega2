package consola;
import parque.ParqueAtraccion;
import persistencia.PersistenciaParqueAtracciones;
import persistencia.PersistenciaBasica;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import administrador.Admin;
import cliente.Cliente;
import empleado.Empleado;
import empleado.Trabajo;
import empleado.TrabajoAtraccion;
import empleado.TrabajoEspectaculo;
import exceptions.ExceptionConstrasenaIncorrecta;
import exceptions.ExceptionInfoNotFound;
import exceptions.ExceptionUsuarioNoExiste;
import exceptions.ExceptionUsuarioYaExiste;
import exceptions.ExceptionInputIncorrecto;
import juegos.AtraccionCultural;
import juegos.AtraccionMecanica;
import juegos.Espectaculo;


public class ConsolaBasica {

	private static ParqueAtraccion parque;


	private static Scanner input = new Scanner(System.in);
   
	
	
	
    public static void correrAplicacion(int cual, int opcion) throws IOException, ExceptionConstrasenaIncorrecta, ExceptionUsuarioNoExiste, ExceptionUsuarioYaExiste, ExceptionInputIncorrecto, ExceptionInfoNotFound
    {
    	boolean correr = true;
    	while(correr == true) {
	    	input = new Scanner(System.in);
	    	System.out.print("Ingrese su usuario: ");
			String usuario = input.next();
			input = new Scanner(System.in);
			System.out.print("Ingrese su contrasenha: ");
			String contra = input.next();
	    	if (cual == 1) {
		    	if (opcion == 1) {
		    		Cliente cliente = parque.ingresarCliente(usuario, contra);
		    		ConsolaCliente consola = new ConsolaCliente(cliente, parque);
	    			consola.empezarConsola();
		    	} else if (opcion == 2) {
		    		Admin admin = parque.ingresarAdmin(usuario, contra);
		    		ConsolaAdmin consola = new ConsolaAdmin(admin, parque, usuario, contra);
		    		consola.empezarConsola();
		    	} else if (opcion == 3) {
		    		input = new Scanner(System.in);
	    			System.out.print("Ingrese el nombre de su administrador encargado: ");
	        		String admin = input.next();
		    		Empleado emple = parque.ingresarEmpleado(usuario, contra, admin);
		    		ConsolaEmpleado consola = new ConsolaEmpleado(emple);	
	    			consola.empezarConsola();
		    	}
		    	cual = pedirOpcion(1, 3, "Ingrese 1 para iniciar sesion, 2 para crear sesion, 3 para parar la aplicacion");
		    	opcion = pedirOpcion(1, 3, "Ingrese tipo de entrada: (1 - como cliente, 2 - como administrador, 3 - como empleado");
		    	if (cual == 3) {
		    		correr = false;
		    	}
	    	}else if (cual==2){
	    		System.out.print("Informacion personal \n");
	    		input = new Scanner(System.in);
	    		System.out.print("Ingrese su nombre: ");
	    		String nombre = input.next();
	    		int id = pedirOpcion(100000, 999999999, "Ingrese su numero de identificacion (cedula): ");
	    		if (opcion ==1) {
	    			System.out.print("Desea ingresar su peso: ");
	    			String peso1 = input.next();
	    	    	int peso = 0;
	    	    	if (peso1=="si") {
	    	    		System.out.print("Ingrese su peso: ");
	    	    		peso = input.nextInt();
	    	    	}
	    	    	System.out.print("Desea ingresar su altura: ");
	    	    	String alt1 = input.next();
	    	    	int alt = 0;
	    	    	if (alt1=="si") {
	    	    		System.out.print("Ingrese su altura: ");
	    	    		alt = input.nextInt();
	    	    	}
	    	    	System.out.print("Desea ingresar su edad: ");
	    	    	String edad1 = input.next();
	    	    	int edad = 0;
	    	    	if (edad1=="si") {
	    	    		System.out.print("Ingrese su edad: ");
	    	    		edad = input.nextInt();
	    	    	}
	    	    	System.out.print("Desea ingresar datos de salud: ");
	    	    	String salud1 = input.next();
	    	    	String salud = null;
	    	    	if (salud1=="si") {
	    	    		System.out.print("Ingrese sus datos de salud separados por una , : ");
	    	    		salud = input.next();
	    	    	}
	    			Cliente cliente = parque.crearCliente(usuario, contra, nombre, id, peso, alt, edad, salud);
	    			ConsolaCliente consola = new ConsolaCliente(cliente, parque);
	    			consola.empezarConsola();
	    		} else if (opcion ==2) {
	    			ArrayList<Trabajo> trabajosGeneral = PersistenciaBasica.cargarTrabajos(parque);
	    			ArrayList<TrabajoAtraccion> tatracciones = PersistenciaBasica.cargarTrabajosA(parque);
	    			ArrayList<TrabajoEspectaculo> espectaculos = PersistenciaBasica.cargarTrabajosE(parque);
	    			Admin admin = parque.crearAdmin(usuario, contra, nombre, id, trabajosGeneral, tatracciones, espectaculos);
	    			ConsolaAdmin consola = new ConsolaAdmin(admin, parque, usuario, contra);
		    		consola.empezarConsola();
	    		} else if (opcion ==3) {
	    			input = new Scanner(System.in);
	    			System.out.print("Ingrese su nivel de capacitacion: ");
	        		String capacitacion = input.next();
	    			Empleado emple = parque.crearEmpleado(usuario, contra, nombre, id, capacitacion);
	    			ConsolaEmpleado consola = new ConsolaEmpleado(emple);	
	    			consola.empezarConsola();
	    		}
	    		cual = pedirOpcion(1, 3, "Ingrese 1 para iniciar sesion, 2 para crear sesion, 3 para parar la aplicacion");
		    	opcion = pedirOpcion(1, 3, "Ingrese tipo de entrada: (1 - como cliente, 2 - como administrador, 3 - como empleado");
		    	if (cual == 3) {
		    		correr = false;
		    	}
	    	}else {
	    		correr = false;
	    	}
	    	
	    	
    	}
    }
    
    public static int pedirOpcion(int menor,int mayor, String mensaje) {
    	input = new Scanner(System.in);
    	System.out.print(mensaje);
    	int number = input.nextInt();
    	if (number > mayor || number<menor) {
    		System.out.print("Tan chistoso...\n");
    		number = pedirOpcion(menor,mayor, mensaje);
    	}
    	return number;
    }

    public static void main( String[] args ) throws IOException, ExceptionConstrasenaIncorrecta, ExceptionUsuarioNoExiste, ExceptionUsuarioYaExiste, ExceptionInputIncorrecto, ExceptionInfoNotFound
    {
    	
    	System.out.print("Bienvenidos al parque de diversiones!!");
    	int cual = pedirOpcion(1, 2, "Ingrese 1 para iniciar sesion, 2 para crear sesion");
    	int number = pedirOpcion(1, 3, "Ingrese tipo de entrada: (1 - como cliente, 2 - como administrador, 3 - como empleado");
    	ArrayList<HashMap<String, Integer>> precios = new ArrayList<HashMap<String, Integer>>();
		HashMap<String, Integer> tiquete = new HashMap<String, Integer>();
		HashMap<String, Integer> entrada = new HashMap<String, Integer>();
		HashMap<String, Integer> fastPass = new HashMap<String, Integer>();
		precios.add(tiquete);
		precios.add(entrada);
		precios.add(fastPass);
    	precios = PersistenciaBasica.cargarPrecios(precios);
    	parque = new ParqueAtraccion(PersistenciaBasica.cargarEspacios(), precios.get(0), precios.get(1), precios.get(2).get("FastPass"));
    	HashMap<ArrayList<AtraccionCultural>,ArrayList<AtraccionMecanica>> atracciones = PersistenciaBasica.cargarAtraccion(parque);
    	ArrayList<AtraccionCultural> cultural = null;
    	ArrayList<AtraccionMecanica> meca=null;
    	for(ArrayList<AtraccionCultural> cult: atracciones.keySet()) {
    		cultural = cult;
    		meca = atracciones.get(cult);
    	}
    	ArrayList<Espectaculo> espe = PersistenciaBasica.cargarEspectaculo(parque);
    	parque.añadirAtracciones(cultural, meca, espe);
        correrAplicacion(cual, number);
        
    }
}
