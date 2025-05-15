package cliente;

import java.util.ArrayList;
import java.util.Scanner;

public interface IBuilder {

	public void setName(String nombre);
	
	public void setId(int id);
	
	public void setPeso(int peso);
	
	public void setAltura(int alt);
	
	public void setEdad(int edad);
	
	public void setSalud(String salud);
	
}
