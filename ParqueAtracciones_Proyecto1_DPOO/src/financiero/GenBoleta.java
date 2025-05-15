package financiero;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class GenBoleta {
	
	
	private int caja;
	
	private HashMap<Integer, Tiquete> tiquetesVendidos;
	
	private HashMap<Integer, Entrada> entradasVendidas;
	
	private HashMap<Integer, FastPass> fastPassVendidos;
	
	private HashMap<String, Integer> precioTiquetes;
	
	private HashMap<String, Integer> precioEntradas;
	
	private int precioFastPass;
	
	
	
	
	
	public GenBoleta(HashMap<String, Integer> precioTiquetes, HashMap<String, Integer> precioEntradas, int precioFastPass, int caja) {
		this.caja = caja;
		this.precioEntradas = precioEntradas;
		this.precioTiquetes = precioTiquetes;
		this.precioFastPass = precioFastPass;
		this.entradasVendidas = new HashMap<Integer, Entrada>();
		this.tiquetesVendidos = new HashMap<Integer, Tiquete>();
		this.fastPassVendidos = new HashMap<Integer, FastPass>();
	}
	
	
	public int getCaja() {
		return caja;
	}
	
	
	
	public Tiquete venderTiquete(String nivel, LocalDateTime ini, LocalDateTime fin) {
		Set<Integer> codigos = tiquetesVendidos.keySet();
		int codigo = generarCodigo(codigos);
		int anio = fin.getYear()-ini.getYear();
		int mon = fin.getMonthValue()-ini.getMonthValue();
		int semb = fin.getDayOfMonth()-ini.getDayOfMonth();
		int sem = semb/7;
		int dia = semb-sem*7;
		int precioAnio = precioTiquetes.get(nivel+"Anio");
		int precioMon = precioTiquetes.get(nivel+"Mes");
		int precioSem=precioTiquetes.get(nivel+"Semana");
		int precioDia=precioTiquetes.get(nivel);
		int precio = (precioAnio*anio)+(precioMon*mon)+(precioSem*sem)+(precioDia*dia);
		caja = caja+precio;
		Tiquete tiq = new Tiquete(codigo, nivel, ini, fin, precio);
		tiquetesVendidos.put(codigo, tiq);
		return tiq;
	}
	
	public Tiquete venderTiquete(String nivel) {
		Set<Integer> codigos = tiquetesVendidos.keySet();
		int codigo = 10000;
		if (codigos.isEmpty()==false) {
			codigo = generarCodigo(codigos);
		}
		int precio = precioTiquetes.get(nivel);
		caja = caja+precio;
		Tiquete tiq = new Tiquete(codigo, nivel, precio);
		tiquetesVendidos.put(codigo, tiq);
		return tiq;
	}
	
	public Entrada venderEntrada(String atraccion) {
		Set<Integer> codigos = entradasVendidas.keySet();
		int codigo = 10000;
		if (codigos.isEmpty()==false) {
			codigo = generarCodigo(codigos);
		}
		int precio = precioEntradas.get(atraccion);
		Entrada en = new Entrada(codigo, atraccion, precio);
		entradasVendidas.put(codigo, en);
		caja = caja+precio;
		return en;
	}
	
	
	public FastPass venderFastPass(LocalDateTime fecha) {
		Set<Integer> codigos = fastPassVendidos.keySet();
		int codigo = 10000;
		if (codigos.isEmpty()==false) {
			codigo = generarCodigo(codigos);
		}
		caja = caja+precioFastPass;
		FastPass pass = new FastPass(codigo, fecha, precioFastPass);
		fastPassVendidos.put(codigo, pass);
		return pass;
	}
	
	
	
	private int generarCodigo(Set<Integer> codigos) {
		Random rand = new Random();
		int codigo = rand.nextInt(10001);
		if (codigos.contains(codigo)) {
			codigo = generarCodigo(codigos);
		}
		return codigo;
	}
}
