package financiero;
import java.time.LocalDateTime;

public class Entrada {

	private int codigo;
	
	private String atraccion;
	
	private boolean usado;
	
	private LocalDateTime cuando;
	
	private int precio;
	
	
	public Entrada(int codigo, String atraccion, int precio) {
		this.atraccion = atraccion;
		this.codigo = codigo;
		this.usado = false;
		this.cuando = null;
		this.precio = precio;
	}
	
	public int getPrecio() {
		return precio;
	}
	
	public LocalDateTime getFechaUso() {
		return cuando;
	}
	
	public String getAtraccion() {
		return atraccion;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public boolean getUsado() {
		return usado;
	}
	
	public void usar() {
		usado = true;
		cuando = LocalDateTime.now();
	}
}
