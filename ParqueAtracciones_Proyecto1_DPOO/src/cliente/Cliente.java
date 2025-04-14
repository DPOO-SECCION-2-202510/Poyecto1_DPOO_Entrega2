package cliente;
import java.util.LinkedList;
import java.util.ArrayList;
import financiero.Entrada;
import financiero.Tiquete;
import financiero.FastPass;

public class Cliente {

	private String nombre;
	
	private int identificacion;
	
	private LinkedList<Tiquete> tiquetes;
	
	private LinkedList<Entrada> entradas;
	
	private LinkedList<FastPass> fastPass;
	
	
	
	public Cliente(String nombre, int id){
		this.nombre = nombre ;
		this.identificacion = id;
		this.tiquetes = new LinkedList<Tiquete>() ;
		this.entradas = new LinkedList<Entrada>();
		this.fastPass = new LinkedList<FastPass>();
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	public int getId() {
		return identificacion;
	}
	
	public void anadirTiq(Tiquete tiq) {
		tiquetes.add(tiq);
	}
	
	public void anadirEn(Entrada en) {
		entradas.add(en);
	}
	
	public void anadirFastPass(FastPass fast) {
		fastPass.add(fast);
	}
	
	public ArrayList<Tiquete> getTiquetesSinUsar(){
		ArrayList<Tiquete> sinUso = new ArrayList<Tiquete>();
		for (Tiquete tiq : tiquetes) {
			if (tiq.getUsado()==false) {
				sinUso.add(tiq);
			}
		}
		return sinUso;
	}
	
	public ArrayList<Tiquete> getTiquetesUsados(){
		ArrayList<Tiquete> uso = new ArrayList<Tiquete>();
		for (Tiquete tiq : tiquetes) {
			if (tiq.getUsado()) {
				uso.add(tiq);
			}
		}
		return uso;
	}
	public ArrayList<Entrada> getEntradasSinUsar(){
		ArrayList<Entrada> sinUso = new ArrayList<Entrada>();
		for (Entrada en : entradas) {
			if (en.getUsado()==false) {
				sinUso.add(en);
			}
		}
		return sinUso;
	}
	public ArrayList<Entrada> getEntradasUsadas(){
		ArrayList<Entrada> uso = new ArrayList<Entrada>();
		for (Entrada en : entradas) {
			if (en.getUsado()) {
				uso.add(en);
			}
		}
		return uso;
	}
	public ArrayList<FastPass> getFastPassSinUsar(){
		ArrayList<FastPass> sinUso = new ArrayList<FastPass>();
		for (FastPass fast : fastPass) {
			if (fast.getUsado()==false) {
				sinUso.add(fast);
			}
		}
		return sinUso;
	}
	public ArrayList<FastPass> getFastPassUsados(){
		ArrayList<FastPass> uso = new ArrayList<FastPass>();
		for (FastPass fast : fastPass) {
			if (fast.getUsado()) {
				uso.add(fast);
			}
		}
		return uso;
	}
	
	

}
