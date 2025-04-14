package parque;
import java.util.HashMap;
import java.util.Set;

public class Caja {
	
	private int caja;
	
	private HashMap<String, Integer> inventario;
	
	public Caja(int caja, HashMap<String, Integer> in) {
		this.caja=caja;
		this.inventario = in;
	}
	
	public void vender (String item, int cantidad) {
		int precio = inventario.get(item);
		caja = caja+(precio*cantidad);
	}
	
	public int getCaja() {
		return caja;
	}
	
	public int getPrecio(String item) {
		return inventario.get(item);
	}
	
	public Set<String> getInventario(){
		return inventario.keySet();
	}
	
	public boolean vende(String item) {
		return inventario.containsKey(item);
	}

}
