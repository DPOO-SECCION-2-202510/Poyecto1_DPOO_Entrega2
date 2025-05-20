package juegos;
import java.time.LocalDateTime;
import java.util.ArrayList;

import parque.Espacio;

public class AtraccionMecanica extends Atraccion{

	private int altMin;
	
	private int altMax;
	
	private int pesoMin;
	
	private int pesoMax;
	
	private ArrayList<String> contraindicaciones;
	
	private String nivRiesgo;
	
	
	public AtraccionMecanica(String nombre, int cupoMax, String exclusividad, Espacio espacio, ArrayList<LocalDateTime>  temporada, int minEmpleados, String clima, int altMax, int altMin, int pesoMax, int pesoMin, ArrayList<String> contra, String nivRiesgo) {
		super(nombre,cupoMax, exclusividad, espacio, temporada, minEmpleados, clima);
		this.altMax = altMax;
		this.altMin = altMin;
		this.pesoMax = pesoMax;
		this.pesoMin = pesoMin;
		this.nivRiesgo = nivRiesgo;
		this.contraindicaciones = contra;
	}
	
	public String getNivRiesgo() {
		return nivRiesgo;
	}
	
	public String getInfo() {
		String contra=null;
		String info = "nombre: "+nombre+";Atraccion de tipo: Mecanica;"+";Exclusividad: "+exclusividad+";Espacio: "+espacio.getIdentificador()+";Nivel de riezgo: "+nivRiesgo+";Apto para personas con un peso entre: "+pesoMax+" - "+pesoMin+";Apto para pesonas con una altura entre: "+altMax+" - "+altMin;
		for(String indi:contraindicaciones) {
			contra = contra+", "+indi;
		}
		if (contra!= null) {
			info = info+";No apto para personas con: "+contra;
		}
		return info;
	}
	
	public boolean puedeEntrar(int peso, int alt, ArrayList<String> salud) {
		boolean puede = false;
		boolean puedeSalud = true;
		for (String indicacion: salud) {
			System.out.print(indicacion+"\n");
			for (String contra: contraindicaciones) {
				System.out.print(contra+"\n");
			}
			if (contraindicaciones.contains(indicacion)) {
				puedeSalud = false;
			}
		}
		if (peso>=pesoMin && peso<= pesoMax && alt>=altMin && alt<= altMax && puedeSalud) {
			puede = true;
		}
		return puede;
	}
	
	
}
