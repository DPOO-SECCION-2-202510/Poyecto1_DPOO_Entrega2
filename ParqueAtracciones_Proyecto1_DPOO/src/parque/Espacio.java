package parque;

public class Espacio {

	private String area;
	
	private int identificador;
	
	public Espacio(String area,int id) {
		this.area=area;
		this.identificador = id;
	}
	
	public String getArea() {
		return area;
	}
	
	public int getIdentificador() {
		return identificador;
	}
}
