package financiero;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Tiquete {

	private int codigo;
	
	private String nivel;
	
	private ArrayList<LocalDateTime> temporada;
	
	private boolean usado;
	
	private LocalDateTime cuando;
	
	private int precio;
	
	public Tiquete(int codigo, String nivel, int precio) {
		this.nivel = nivel;
		this.codigo = codigo;
		this.usado = false;
		LocalDateTime ini = LocalDateTime.of(2000, 1, 1,1,1);
		LocalDateTime fin = LocalDateTime.of(2000, 1, 1,1,1);
		this.temporada = new ArrayList<LocalDateTime>();
		temporada.add(ini);
		temporada.add(fin);
		this.cuando=null;
		this.precio = precio;
	}
	
	public Tiquete(int codigo, String nivel, LocalDateTime localDateTime, LocalDateTime localDateTime2,int precio ) {
		this.nivel = nivel;
		this.codigo = codigo;
		this.usado = false;
		this.temporada = new ArrayList<LocalDateTime>();
		temporada.add(localDateTime);
		temporada.add(localDateTime2);
		this.cuando = null;
		this.precio = precio;
	}
	
	
	public int getPrecio() {
		return precio;
	}
	
	public LocalDateTime getFechaUso() {
		return cuando;
	}
	
	public String getNivel() {
		return nivel;
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
	
	public ArrayList<LocalDateTime> getTemporada(){
		return temporada;
	}
}
