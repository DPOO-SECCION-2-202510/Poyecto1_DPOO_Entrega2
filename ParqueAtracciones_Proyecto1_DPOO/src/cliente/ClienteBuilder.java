package cliente;

import java.util.ArrayList;

public class ClienteBuilder implements IBuilder{
	
	private String nombre;
	
	private int id;
	
	private int peso;
	
	private int alt;
	
	private int edad;
	
	private ArrayList<String> salud;


	@Override
	public void setName(String nombre) {
		this.nombre = nombre;
		
	}

	@Override
	public void setId(int id) {
		this.id=id;
		
	}

	@Override
	public void setPeso(int peso) {
		this.peso=peso;
		
	}

	@Override
	public void setAltura(int alt) {
		this.alt=alt;
		
	}

	@Override
	public void setEdad(int edad) {
		this.edad = edad;
		
	}

	@Override
	public void setSalud(String saluds) {
		this.salud = new ArrayList<String>();
		String[] datos = saluds.split(", ");
		for (String data: datos) {
			salud.add(data);
		}
		
	}
	
	public Cliente getNuevo() {
		Cliente cliente = new Cliente(nombre, id, peso, alt, edad, salud);
		return cliente;
		
	}

}
