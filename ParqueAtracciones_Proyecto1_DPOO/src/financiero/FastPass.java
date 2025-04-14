package financiero;
import java.time.LocalDateTime;


public class FastPass {
	private int codigo;
	
	private LocalDateTime fecha;
	
	private boolean usado;
	
	private int precio;
	
	
	public FastPass(int codigo,  LocalDateTime fecha, int precio) {
		this.codigo = codigo;
		this.usado = false;
		this.fecha = fecha;
		this.precio = precio;
	}
	
	public int getPrecio() {
		return precio;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public boolean getUsado() {
		return usado;
	}
	
	public void usar() {
		usado = true;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}

}
