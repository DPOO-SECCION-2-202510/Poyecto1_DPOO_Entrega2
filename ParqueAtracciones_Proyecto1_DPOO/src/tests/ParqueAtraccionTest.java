package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import administrador.Admin;
import cliente.Cliente;
import cliente.ClienteBuilder;
import empleado.Empleado;
import exceptions.ExceptionInfoNotFound;
import exceptions.ExceptionUsuarioNoExiste;
import exceptions.ExceptionUsuarioYaExiste;
import financiero.Entrada;
import financiero.FastPass;
import financiero.Tiquete;
import juegos.Atraccion;
import juegos.AtraccionCultural;
import juegos.AtraccionMecanica;
import juegos.Espectaculo;
import parque.ParqueAtraccion;
import persistencia.PersistenciaBasica;

class ParqueAtraccionTest {
	
	private static ParqueAtraccion parque;
	
	private static Admin admin;
	
	@BeforeAll
	public static void cargarTodo() throws IOException, ExceptionUsuarioYaExiste {
		ArrayList<HashMap<String, Integer>> precios = new ArrayList<HashMap<String, Integer>>();
		HashMap<String, Integer> tiquete = new HashMap<String, Integer>();
		HashMap<String, Integer> entrada = new HashMap<String, Integer>();
		HashMap<String, Integer> fastPass = new HashMap<String, Integer>();
		precios.add(tiquete);
		precios.add(entrada);
		precios.add(fastPass);
    	precios = PersistenciaBasica.cargarPrecios(precios);
    	parque = ParqueAtraccion.getInstance(PersistenciaBasica.cargarEspacios(), precios.get(0), precios.get(1), precios.get(2).get("FastPass"));
    	HashMap<ArrayList<AtraccionCultural>,ArrayList<AtraccionMecanica>> atracciones = PersistenciaBasica.cargarAtraccion(parque);
    	ArrayList<AtraccionCultural> cultural = null;
    	ArrayList<AtraccionMecanica> meca=null;
    	for(ArrayList<AtraccionCultural> cult: atracciones.keySet()) {
    		cultural = cult;
    		meca = atracciones.get(cult);
    	}
    	ArrayList<Espectaculo> espe = PersistenciaBasica.cargarEspectaculo(parque);
    	parque.añadirAtracciones(cultural, meca, espe);
    	admin = parque.crearAdmin("usuario", "contra", "juan", 333271, PersistenciaBasica.cargarTrabajos(parque), PersistenciaBasica.cargarTrabajosA(parque), PersistenciaBasica.cargarTrabajosE(parque));
	}
	
	
	
	

	@Test
	void testAdmin() throws IOException {
		admin.mover.cargarEmpleados();
		assertEquals(admin.getNumEmpleados(), 30,"No se cargaron los trabajos correctamente");
		assertEquals(admin.getEmpleado("Sandra").getCodigo(), 39842783,"No se encuentra el empleado con la informacion correcta");
		admin.asignar.asignarTodos();
		assertEquals(admin.getTrabajosSinAsignar().size(), 6,"No se asignaron los trabajos correctamente");
		assertEquals(admin.getTurnosApertura().size(), admin.getTurnosCierre().size(), "no se asignaron los turnos por hora correctamnete");
	}
	
	@Test
	void testInfoCargo() throws ExceptionInfoNotFound, ExceptionUsuarioYaExiste, ExceptionUsuarioNoExiste {
		Atraccion meca = parque.getAtraccion("The Big Red Dragon");
		AtraccionMecanica mecan= (AtraccionMecanica) meca;
		Espectaculo esp = parque.getEspectaculo("Concierto Jazz");
		assertEquals(mecan.getExclusividad(),"Diamante","No se encuentran los datos correctos");
		assertEquals(esp.getDescripcion(),"Concierto con las mejores obras de Jazz en vivo","No se encuentran los datos correctos");
		admin.asignar.asignarTodos();
		assertEquals(admin.getTrabajosSinAsignar().size(), 6, "No reconoze la cantidad de trabajos sin personal.");
		assertEquals(mecan.getFuncionando(),true,"No reconoce los trabajores por puesto.");
		assertEquals(mecan.getFuncionando("Lluvia"),false,"No reconoce las restricciones por clima.");
		parque.crearEmpleado("nuevo", "Contra", "nuevo", 2222222, "null");
		assertEquals(admin.getEmpleados().size(), 31 ,"No reconoce los empleados nuevos.");
		assertEquals(admin.getTrabajosSinAsignar().size(), 5, "No asigna trabajos a los empleados nuevos.");
		
	}
	
	
	@Test
	void funcionaminetoAtraccionSalud() {
		ArrayList<LocalDateTime> temporada = new ArrayList<LocalDateTime>();
		LocalDateTime ini = LocalDateTime.of(2025, 5, 1, 1, 0);
		LocalDateTime fin = LocalDateTime.of(2025, 7, 30, 12, 0);
		temporada.add(ini);
		temporada.add(fin);
		ArrayList<String> contra = new ArrayList<String>();
		contra.add("Corazon");
		ArrayList<String> contra2 = new ArrayList<String>();
		AtraccionMecanica meca = new AtraccionMecanica("ejeple", 23, "Oro", parque.getEspacio(32),temporada, 2,"Lluvia", 200, 100, 200,100, contra, "Alto");
		AtraccionCultural cult = new AtraccionCultural("Ejemplo",20, "Oro", parque.getEspacio(32), temporada, 2, "Lluvia", 10);
		assertEquals(meca.puedeEntrar(150, 150, contra2), true, "No reconoce restricciones de salud");
		assertEquals(cult.puedeEntrar(15), true, "No reconoce restricciones de salud");
		assertEquals(meca.puedeEntrar(350, 350, contra), false, "No reconoce restricciones de salud");
		assertEquals(cult.puedeEntrar(5), false, "No reconoce restricciones de salud");
	}
	
	@Test
	void funcionaminetoAtraccionEmpleado() {
		Empleado emple = new Empleado("usuario", "senha", "nombre", 1234567, "capac", "juan");
		Empleado emple2 = new Empleado("usuario2", "senha2", "nombre2", 1234568, "capac2", "juan");
		ArrayList<LocalDateTime> temporada = new ArrayList<LocalDateTime>();
		LocalDateTime ini = LocalDateTime.of(2025, 5, 1, 1, 0);
		LocalDateTime fin = LocalDateTime.of(2025, 7, 30, 12, 0);
		temporada.add(ini);
		temporada.add(fin);
		ArrayList<String> contra = new ArrayList<String>();
		contra.add("Corazon");
		AtraccionMecanica meca = new AtraccionMecanica("ejeple", 23, "Oro", parque.getEspacio(32),temporada, 2,"Lluvia", 200, 100, 200,100, contra, "Alto");
		AtraccionCultural cult = new AtraccionCultural("Ejemplo",20, "Oro", parque.getEspacio(32), temporada, 2, "Lluvia", 10);
		assertEquals(meca.getFuncionando(), false, "No reconoce restricciones de empelados");
		assertEquals(cult.getFuncionando(), false, "No reconoce restricciones de salud");
		meca.masEmpleado(emple);
		meca.masEmpleado(emple2);
		cult.masEmpleado(emple);
		cult.masEmpleado(emple2);
		assertEquals(meca.getFuncionando(), true, "No reconoce restricciones de empelados");
		assertEquals(cult.getFuncionando(), true, "No reconoce restricciones de salud");
	}

	@Test
	void comprar() throws ExceptionInfoNotFound {
		ClienteBuilder nuevo = new ClienteBuilder();
		nuevo.setAltura(160);
		nuevo.setEdad(20);
		nuevo.setId(111234);
		nuevo.setName("Jaime");
		nuevo.setPeso(65);
		nuevo.setSalud("corazon, alma");
		Cliente cliente = nuevo.getNuevo();
		assertEquals(cliente.getEntradasSinUsar().size(),0,"No reconoce inventarios vacios");
		assertEquals(cliente.getTiquetesSinUsar().size(),0,"No reconoce inventarios vacios");
		assertEquals(cliente.getFastPassSinUsar().size(),0,"No reconoce inventarios vacios");
		Tiquete tiq = parque.getCajaVirtual().venderTiquete("Diamante");
		Entrada en = parque.getCajaVirtual().venderEntrada("Terremoto");
		FastPass fast = parque.getCajaVirtual().venderFastPass(LocalDateTime.of(2025, 7, 30, 12, 0));
		cliente.anadirEn(en);
		cliente.anadirFastPass(fast);
		cliente.anadirTiq(tiq);
		assertEquals(cliente.getEntradasSinUsar().size(),1,"No reconoce inventarios vacios");
		assertEquals(cliente.getTiquetesSinUsar().size(),1,"No reconoce inventarios vacios");
		assertEquals(cliente.getFastPassSinUsar().size(),1,"No reconoce inventarios vacios");
		cliente.getEntradasSinUsar().get(0).usar();
		cliente.getTiquetesSinUsar().get(0).usar();
		cliente.getFastPassSinUsar().get(0).usar();
		assertEquals(cliente.getEntradasSinUsar().size(),0,"No reconoce inventarios vacios");
		assertEquals(cliente.getTiquetesSinUsar().size(),0,"No reconoce inventarios vacios");
		assertEquals(cliente.getFastPassSinUsar().size(),0,"No reconoce inventarios vacios");
		assertEquals(cliente.getEntradasUsadas().size(),1,"No reconoce inventarios vacios");
		assertEquals(cliente.getTiquetesUsados().size(),1,"No reconoce inventarios vacios");
		assertEquals(cliente.getFastPassUsados().size(),1,"No reconoce inventarios vacios");
		}


	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
