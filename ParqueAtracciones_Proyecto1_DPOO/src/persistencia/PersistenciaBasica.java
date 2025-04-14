package persistencia;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;
import empleado.Trabajo;
import empleado.TrabajoAtraccion;
import empleado.TrabajoEspectaculo;
import juegos.Atraccion;
import juegos.AtraccionCultural;
import juegos.AtraccionMecanica;
import juegos.Espectaculo;
import parque.Caja;
import parque.ParqueAtraccion;
import parque.Espacio;


public class PersistenciaBasica {
	
	public PersistenciaBasica() {
	}

	public static ArrayList<HashMap<String, Integer>> cargarPrecios(ArrayList<HashMap<String, Integer>> precios) throws IOException {

		
		try (Scanner scanner = new Scanner(Paths.get("data/precios.txt"))) {
			String linea = scanner.nextLine();
			linea = scanner.nextLine();
			linea = scanner.nextLine();
			boolean tiquetesGuardados = false;
			boolean cargar = true;
		    while (cargar == true) {
				if (tiquetesGuardados == false) {
					String[] info = linea.split(";");
					if (info.length ==2) {
						int precioF = Integer.parseInt(Arrays.asList(info).get(1));
						precios.get(0).put("Basico", precioF);
						linea = scanner.nextLine();
					}else {
						String nombre = Arrays.asList(info).get(0);
						int precioDia = Integer.parseInt(Arrays.asList(info).get(1));
						int precioSem = Integer.parseInt(Arrays.asList(info).get(2));
						int precioMes = Integer.parseInt(Arrays.asList(info).get(3));
						int precioAnio = Integer.parseInt(Arrays.asList(info).get(4));
						precios.get(0).put(nombre, precioDia);
						precios.get(0).put(nombre+"Semana", precioSem);
						precios.get(0).put(nombre+"Mes", precioMes);
						precios.get(0).put(nombre+"Anio", precioAnio);
						linea = scanner.nextLine();
						if (linea.compareTo("Entradas")==0) {
							linea = scanner.nextLine();
							linea = scanner.nextLine();
							tiquetesGuardados = true;
						}}
				}else {
					String[] info = linea.split(";");
					String nombre = Arrays.asList(info).get(0);
					int precio = Integer.parseInt(Arrays.asList(info).get(1));
					precios.get(1).put(nombre, precio);
					linea = scanner.nextLine();
					if (linea.compareTo("FastPass")==0) {
						linea = scanner.nextLine();
						int precioF = Integer.parseInt(linea);
						precios.get(2).put("FastPass", precioF);
						cargar = false;
					}
				}
				
		    }
		} catch (Exception e) {
		    System.out.println("Error: " + e.getMessage());
		}
		
		return precios;

    }
	
	public static ArrayList<Trabajo> cargarTrabajos(ParqueAtraccion parque) throws IOException {
	
			ArrayList<Trabajo> cargados = new ArrayList<Trabajo>();
			try (Scanner scanner = new Scanner(Paths.get("data/trabajos.txt"))) {
				String linea = scanner.nextLine();
				while (scanner.hasNextLine()) {
					String[] info = linea.split(";");
					String servicio = Arrays.asList(info).get(0);
					String descripcion = Arrays.asList(info).get(2);
					String capacitacion = Arrays.asList(info).get(3);
					int idEspacio =Integer.parseInt(Arrays.asList(info).get(1));
					Espacio espacio = parque.getEspacio(idEspacio);
					HashMap<String, Integer> inventario = new HashMap<String, Integer>();
					String[]productos = Arrays.asList(info).get(4).split(",");
					String[]precios = Arrays.asList(info).get(5).split(",");
					for (int i =0 ; i < productos.length ; i++) {
						String precio = Arrays.asList(precios).get(i);
						int price = 0;
						if (precio.contentEquals("null")==false) {
							price = Integer.parseInt(precio);
						}
						inventario.put(Arrays.asList(precios).get(i), price);
					}
					Caja caja = new Caja(0, inventario);
					Trabajo trabajo = new Trabajo(servicio, espacio, descripcion, caja, capacitacion);
					cargados.add(trabajo);
					linea = scanner.nextLine();
				}
			} catch (Exception e) {
			    System.out.println("Error: " + e.getMessage());
			}
			
			return cargados;
	
	    }
	
	public static ArrayList<TrabajoAtraccion> cargarTrabajosA(ParqueAtraccion parque) throws IOException {
		
		ArrayList<TrabajoAtraccion> cargados = new ArrayList<TrabajoAtraccion>();
		try (Scanner scanner = new Scanner(Paths.get("data/trabajoAtraccion.txt"))) {
			String linea = scanner.nextLine();
			while (scanner.hasNextLine()) {
				String[] info = linea.split(";");
				String servicio = Arrays.asList(info).get(0);
				String descripcion = Arrays.asList(info).get(2);
				String capacitacion = Arrays.asList(info).get(3);
				int idEspacio =Integer.parseInt(Arrays.asList(info).get(1));
				Espacio espacio = parque.getEspacio(idEspacio);
				String nombre = Arrays.asList(servicio.split(",")).get(0);
				Atraccion at = parque.getAtraccion(nombre);
				HashMap<String, Integer> inventario = new HashMap<String, Integer>();
				String[]productos = Arrays.asList(info).get(4).split(",");
				String[]precios = Arrays.asList(info).get(5).split(",");
				for (int i =0 ; i < productos.length ; i++) {
					String precio = Arrays.asList(precios).get(i);
					int price = 0;
					if (precio.contentEquals("null")==false) {
						price = Integer.parseInt(precio);
					}
					inventario.put(productos[i], price);
				}
				Caja caja = new Caja(0, inventario);
				TrabajoAtraccion trabajo = new TrabajoAtraccion(servicio, espacio, descripcion, caja, capacitacion, at);
				cargados.add(trabajo);
				linea = scanner.nextLine();
			}
		} catch (Exception e) {
		    System.out.println("Error: " + e.getMessage());
		}
		
		return cargados;

    }
	
	public static ArrayList<TrabajoEspectaculo> cargarTrabajosE(ParqueAtraccion parque) throws IOException {
		
		ArrayList<TrabajoEspectaculo> cargados = new ArrayList<TrabajoEspectaculo>();
		try (Scanner scanner = new Scanner(Paths.get("data/trabajosEspectaculo.txt"))) {
			String linea = scanner.nextLine();
			while (scanner.hasNextLine()) {
				String[] info = linea.split(";");
				String servicio = Arrays.asList(info).get(0);
				String descripcion = Arrays.asList(info).get(2);
				String capacitacion = Arrays.asList(info).get(3);
				int idEspacio =Integer.parseInt(Arrays.asList(info).get(1));
				Espacio espacio = parque.getEspacio(idEspacio);
				Espectaculo es = parque.getEspectaculo(Arrays.asList(servicio.split(",")).get(0));
				HashMap<String, Integer> inventario = new HashMap<String, Integer>();
				String[]productos = Arrays.asList(info).get(4).split(",");
				String[]precios = Arrays.asList(info).get(5).split(",");
				for (int i =0 ; i < productos.length ; i++) {
					String precio = Arrays.asList(precios).get(i);
					int price = 0;
					if (precio.contentEquals("null")==false) {
						price = Integer.parseInt(precio);
					}
					inventario.put(productos[i], price);
				}
				Caja caja = new Caja(0, inventario);
				TrabajoEspectaculo trabajo = new TrabajoEspectaculo(servicio, espacio, descripcion, caja, capacitacion, es);
				cargados.add(trabajo);
				linea = scanner.nextLine();
			}
		} catch (Exception e) {
		    System.out.println("Error: " + e.getMessage());
		}
		
		return cargados;

    }
	
	public static ArrayList<Espacio> cargarEspacios(){
		ArrayList<Espacio> espacios = new ArrayList<Espacio>();
		try (Scanner scanner = new Scanner(Paths.get("data/espacios.txt"))) {
			String linea = scanner.nextLine();
			while (scanner.hasNextLine()) {
				String[] info = linea.split(";");
				String area = Arrays.asList(info).get(0);
				int id =Integer.parseInt(Arrays.asList(info).get(1));
				Espacio es = new Espacio(area, id);
				espacios.add(es);
				linea = scanner.nextLine();
				}
		} catch (Exception e) {
		    System.out.println("Error: " + e.getMessage());
		}
		return espacios;
	}
	
	public static HashMap<ArrayList<AtraccionCultural>,ArrayList<AtraccionMecanica>> cargarAtraccion(ParqueAtraccion parque){
		ArrayList<AtraccionCultural> culturales = new ArrayList<AtraccionCultural>();
		ArrayList<AtraccionMecanica> mecanicas = new ArrayList<AtraccionMecanica>();
		try (Scanner scanner = new Scanner(Paths.get("data/atracciones.txt"))) {
			String linea = scanner.nextLine();
			boolean cargo = false;
			while (scanner.hasNextLine()) {
				if(linea.contentEquals("Mecanica")) {
					cargo=true;
				}else if(cargo==false && linea.contentEquals("Cultural")==false) {
					String[] info = linea.split(";");
					String nombre = Arrays.asList(info).get(0);
					String exclusividad = Arrays.asList(info).get(2);
					String tempo = Arrays.asList(info).get(4);
					ArrayList<LocalDateTime> temporada = new ArrayList<LocalDateTime>();
					if (tempo == "Verano") {
						LocalDateTime ini = LocalDateTime.of(2025, 5, 1, 1, 0);
						LocalDateTime fin = LocalDateTime.of(2025, 7, 30, 12, 0);
						temporada.add(ini);
						temporada.add(fin);
					}else {
						LocalDateTime ini = LocalDateTime.of(2025, 1, 1, 1, 0);
						LocalDateTime fin = LocalDateTime.of(2025, 12, 30, 12, 0);
						temporada.add(ini);
						temporada.add(fin);
					}
					String clima = Arrays.asList(info).get(6);
					int cupo =Integer.parseInt(Arrays.asList(info).get(1));
					int minEmple =Integer.parseInt(Arrays.asList(info).get(5));
					int edad =Integer.parseInt(Arrays.asList(info).get(7));
					Espacio es = parque.getEspacio(Integer.parseInt(Arrays.asList(info).get(3)));
					AtraccionCultural trCult = new AtraccionCultural(nombre, cupo,  exclusividad,  es, temporada, minEmple, clima, edad);
					culturales.add(trCult);
				}else if(cargo==true) {
					String[] info = linea.split(";");
					String nombre = Arrays.asList(info).get(0);
					String exclusividad = Arrays.asList(info).get(2);
					String tempo = Arrays.asList(info).get(4);
					ArrayList<LocalDateTime> temporada = new ArrayList<LocalDateTime>();
					if (tempo == "Verano") {
						LocalDateTime ini = LocalDateTime.of(2025, 5, 1, 1, 0);
						LocalDateTime fin = LocalDateTime.of(2025, 7, 30, 12, 0);
						temporada.add(ini);
						temporada.add(fin);
					}else {
						LocalDateTime ini = LocalDateTime.of(2025, 1, 1, 1, 0);
						LocalDateTime fin = LocalDateTime.of(2025, 12, 30, 12, 0);
						temporada.add(ini);
						temporada.add(fin);
					}
					String clima = Arrays.asList(info).get(6);
					int cupo =Integer.parseInt(Arrays.asList(info).get(1));
					int minEmple =Integer.parseInt(Arrays.asList(info).get(5));
					int altMin =Integer.parseInt(Arrays.asList(info).get(7));
					int altMax =Integer.parseInt(Arrays.asList(info).get(8));
					int pesoMin =Integer.parseInt(Arrays.asList(info).get(9));
					int pesoMax =Integer.parseInt(Arrays.asList(info).get(10));
					String contra = Arrays.asList(info).get(12);
					String[] midriezgo = Arrays.asList(info).get(11).split(",");
					ArrayList<String> riezgo = new ArrayList<String>();
					for(int i=0 ;i < midriezgo.length ;i++) {
						riezgo.add(Arrays.asList(midriezgo).get(i));
					}
					Espacio es = parque.getEspacio(Integer.parseInt(Arrays.asList(info).get(3)));
					AtraccionMecanica trmec = new AtraccionMecanica(nombre, cupo, exclusividad, es, temporada, minEmple, clima, altMax, altMin, pesoMax, pesoMin, riezgo, contra);
					mecanicas.add(trmec);
				}
				linea = scanner.nextLine();
			}
		} catch (Exception e) {
		    System.out.println("Error: " + e.getMessage());
		}
		HashMap<ArrayList<AtraccionCultural>,ArrayList<AtraccionMecanica>> esta = new HashMap<ArrayList<AtraccionCultural>,ArrayList<AtraccionMecanica>>();
		esta.put(culturales, mecanicas);
		return esta;
	}
	
	public static ArrayList<Espectaculo> cargarEspectaculo(ParqueAtraccion parque){
		ArrayList<Espectaculo> espectaculos = new ArrayList<Espectaculo>() ;
		try (Scanner scanner = new Scanner(Paths.get("data/espectaculos.txt"))) {
			String linea = scanner.nextLine();
			while (scanner.hasNextLine()) {
				String[] info = linea.split(";");
				String nombre = Arrays.asList(info).get(0);
				String descripcion = Arrays.asList(info).get(1);
				String clima = Arrays.asList(info).get(2);
				String temporada = Arrays.asList(info).get(3);
				int duracion =Integer.parseInt(Arrays.asList(info).get(4));
				String[]horas = (Arrays.asList(info).get(5)).split(",");
				String[]espacios = (Arrays.asList(info).get(6)).split(",");
				HashMap<LocalDateTime, Espacio> horarios = new HashMap<LocalDateTime, Espacio>();
				for (int i =0 ; i < horas.length ; i++) {
					Espacio es = parque.getEspacio(Integer.parseInt(Arrays.asList(espacios).get(i)));
					String[] data = (Arrays.asList(horas).get(i)).split(":");
					LocalDateTime hora = LocalDateTime.of(Integer.parseInt(Arrays.asList(data).get(0)), Integer.parseInt(Arrays.asList(data).get(1)), Integer.parseInt(Arrays.asList(data).get(2)), Integer.parseInt(Arrays.asList(data).get(3)), Integer.parseInt(Arrays.asList(data).get(4)));
					horarios.put(hora, es);
				}
				Espectaculo espe = new Espectaculo(horarios, nombre, descripcion, temporada, clima, duracion);
				espectaculos.add(espe);
				linea = scanner.nextLine();
			}
		} catch (Exception e) {
		    System.out.println("Error: " + e.getMessage());
		}
		
		return espectaculos;
		
	}
	


}
