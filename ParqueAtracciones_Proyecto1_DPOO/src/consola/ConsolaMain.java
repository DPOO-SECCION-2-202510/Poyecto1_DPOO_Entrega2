package consola;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ConsolaMain {
	
	protected int opcion;
	

	public ConsolaMain() {
		this.opcion = 1;
	}
	
	public void canbiarOpcion(int op) {
		this.opcion = op;
	}
	
	public String[] getMenu() {
		String[] menu = null;
		return menu;
	}
	
	public List<JPanel> getInput(){
		List<JPanel> input = new ArrayList<JPanel>();
		return input;
	}
	
	public List<JLabel> getInfo(List<String> inputs){
		List<JLabel> info = new ArrayList<JLabel>();
		return info;
	}
	

}
