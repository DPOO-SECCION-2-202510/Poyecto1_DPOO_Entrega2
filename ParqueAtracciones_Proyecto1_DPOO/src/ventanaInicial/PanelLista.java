package ventanaInicial;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class PanelLista extends JPanel implements ActionListener{

	

    private VentanaBasica principal;
    
    public PanelLista( VentanaBasica ventanaPrincipal )
    {
        this.principal = ventanaPrincipal;
        setLayout( new BorderLayout( ) );

        
        String[] op = {"", "Iniciar Sesión","Crear cuenta"};


        JComboBox scroll = new JComboBox(op);
        scroll.addActionListener(this);
        setName("lista");
        add( scroll );
        setVisible(true);
        
    }
    
    

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
        String opcion = (String)cb.getSelectedItem();
        principal.cambiarTipoEntrada(opcion);
	}

}
