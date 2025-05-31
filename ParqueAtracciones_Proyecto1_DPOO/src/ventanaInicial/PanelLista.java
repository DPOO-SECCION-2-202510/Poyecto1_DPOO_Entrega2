package ventanaInicial;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder; 
import java.awt.Color;
import java.awt.Font; 

@SuppressWarnings("serial")
public class PanelLista extends JPanel implements ActionListener{

    private VentanaBasica principal;
    
    public PanelLista( VentanaBasica ventanaPrincipal ) {
        this.principal = ventanaPrincipal;
        setLayout( new BorderLayout() );
        setBackground(new Color(230, 230, 250)); 
        setBorder(new EmptyBorder(20, 50, 20, 50)); 

        String[] op = {"Seleccione una opción", "Iniciar Sesión","Crear cuenta"}; 
        JComboBox<String> scroll = new JComboBox<>(op);
        scroll.addActionListener(this);
        scroll.setName("lista");
        scroll.setBackground(Color.WHITE);
        scroll.setFont(new Font("Arial", Font.PLAIN, 14));
        scroll.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); 

        add( scroll, BorderLayout.CENTER );
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<?> cb = (JComboBox<?>)e.getSource();
        String opcion = (String)cb.getSelectedItem();
        principal.cambiarTipoEntrada(opcion);
    }
}
