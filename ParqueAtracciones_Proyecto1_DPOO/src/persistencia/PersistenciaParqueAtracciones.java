package persistencia;
import empleado.Empleado;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class PersistenciaParqueAtracciones {

	public PersistenciaParqueAtracciones (){
		
	}

	public HashMap<String, ArrayList<Empleado>> cargarEmpleados(HashMap<String, ArrayList<Empleado>> empleados,  String admin) throws IOException {

		
		try (Scanner scanner = new Scanner(Paths.get("data/empleados.txt"))) {
			String linea = scanner.nextLine();
		    while (scanner.hasNextLine()) {
				String[] info = linea.split(";");
				String nombre = Arrays.asList(info).get(0);
				String nivel = Arrays.asList(info).get(2);
				int codigo =Integer.parseInt(Arrays.asList(info).get(1));
		        Empleado nuevo = new Empleado("usuario", "contrasena", nombre, codigo, nivel, admin);
		        if(empleados.containsKey(nivel)) {
		        	empleados.get(nivel).add(nuevo);
		        }else {
		        	ArrayList<Empleado> nueva = new ArrayList<Empleado>();
		        	nueva.add(nuevo);
		        	empleados.put(nivel, nueva);
		        }
		        linea = scanner.nextLine();
		    }
		} catch (Exception e) {
		    System.out.println("Error: " + e.getMessage());
		}
		
		return empleados;

    }
	
	
	

    
}
