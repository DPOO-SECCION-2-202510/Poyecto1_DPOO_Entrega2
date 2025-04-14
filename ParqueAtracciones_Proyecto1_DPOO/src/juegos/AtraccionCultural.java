package juegos;

import java.time.LocalDateTime;
import java.util.ArrayList;

import parque.Espacio;

public class AtraccionCultural extends Atraccion{

	private int edad;
	
	public AtraccionCultural(String nombre,int cupoMax, String exclusividad, Espacio espacio, ArrayList<LocalDateTime> temporada, int minEmpleados, String clima, int edad) {
		super(nombre,cupoMax,  exclusividad,  espacio,  temporada,  minEmpleados,  clima);
		this.edad = edad;
	}
	
	public String getInfo() {
		String info = "nombre: "+nombre+";Atraccion de tipo: Cultural;Exclusividad: "+exclusividad+";Espacio: "+espacio.getIdentificador()+";Apto para mayores de: "+edad;
		return info;
	}
	
	public boolean puedeEntrar(int edadA) {
		boolean puede = false;
		if (edadA>=edad){
			puede = true;
		}
		return puede;
	}
	
}
