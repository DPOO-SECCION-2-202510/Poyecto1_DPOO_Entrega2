package ventanaInicial;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import exceptions.ExceptionConstrasenaIncorrecta;
import exceptions.ExceptionInfoNotFound;
import exceptions.ExceptionInputIncorrecto;
import exceptions.ExceptionUsuarioNoExiste;
import exceptions.ExceptionUsuarioYaExiste;

@SuppressWarnings("serial")
public class PanelLogIn extends JPanel implements ActionListener{

	private JLabel usuarioLabel;
    private JLabel passwordLabel;
    private JLabel rolLabel;
    private JTextField usuarioTextField;
    private JLabel nombreLabel;
    private JTextField nombreTextField;
    private JLabel capaLabel;
    private JTextField capaTextField;
    private JPasswordField passwordField;
    private JComboBox<String> rolComboBox;
    private JButton loginButton;
    private JLabel mensajeLabel;
    
    private VentanaBasica principal;

	public PanelLogIn(VentanaBasica principal) {
		this.principal = principal;
		
	    setSize(350, 200);
	    setLayout(new GridBagLayout()); 
	    GridBagConstraints gbc = new GridBagConstraints(); 
	    gbc.insets = new Insets(7, 5, 5, 5); 

	    rolLabel = new JLabel("Rol:");
	    String[] roles = {"Cliente", "Administrador", "Empleado"};
	    rolComboBox = new JComboBox<>(roles);
	    usuarioLabel = new JLabel("Usuario:");
	    passwordLabel = new JLabel("Contraseña:");
	    usuarioTextField = new JTextField(15);
	    passwordField = new JPasswordField(15);
	    loginButton = new JButton("Iniciar Sesión");
	    mensajeLabel = new JLabel("");
	    mensajeLabel.setForeground(Color.RED);
	    nombreLabel = new JLabel("Nombre:");
	    nombreTextField = new JTextField(15);
	    capaLabel = new JLabel("Capacitacion:");
	    capaLabel.setVisible(false);
	    capaTextField = new JTextField(15);
	    capaTextField.setVisible(false);


	        // No se algo necesario pal boton
	    loginButton.addActionListener(this);

	   

	        //  Label del desplegableee
	    gbc.gridx = 0; 
	    gbc.gridy = 0; 
	    gbc.anchor = GridBagConstraints.LINE_END;
	    add(rolLabel, gbc); 

	        // Desplegable
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    gbc.fill = GridBagConstraints.HORIZONTAL; 
	    gbc.weightx = 1.0; 
	    add(rolComboBox, gbc);
	    gbc.weightx = 0.0;

	        // Usuario Label
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.anchor = GridBagConstraints.LINE_END;
	    add(usuarioLabel, gbc);

	        // Cuadrito texto
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    add(usuarioTextField, gbc);

	        // Contraseña Label
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.anchor = GridBagConstraints.LINE_END;
	    add(passwordLabel, gbc);

	        // cuadrito texto contraseña
	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    add(passwordField, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    gbc.anchor = GridBagConstraints.LINE_END;
	    add(nombreLabel, gbc);

	        // cuadrito texto contraseña
	    gbc.gridx = 1;
	    gbc.gridy = 3;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    add(nombreTextField, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 4;
	    gbc.anchor = GridBagConstraints.LINE_END;
	    add(capaLabel, gbc);

	        // cuadrito texto contraseña
	    gbc.gridx = 1;
	    gbc.gridy = 4;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    add(capaTextField, gbc);

	        // Botoncitooo
	    gbc.gridx = 0;
	    gbc.gridy = 5;
	    gbc.gridwidth = 2; 
	    gbc.fill = GridBagConstraints.NONE;
	    gbc.anchor = GridBagConstraints.CENTER;
	    add(loginButton, gbc);
	    gbc.gridwidth = 1; 

	    gbc.gridx = 0;
	    gbc.gridy = 6;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.anchor = GridBagConstraints.CENTER;
	    add(mensajeLabel, gbc);

	    setName("login");
	    setVisible(false);
	}

	    
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==loginButton){
			String usuario = usuarioTextField.getText();
			String nombre = nombreTextField.getText();
			String contra = String.valueOf(passwordField.getPassword());
	        String op = (String)rolComboBox.getSelectedItem();
	        String capacitacion = "";
			int cual = 0;
			if (op.equals("Cliente")) {
				cual = 1;
			}else if (op.equals("Administrador")) {
				cual = 2;
			}else if (op.equals("Empleado")) {
				capacitacion = capaTextField.getText();
				if (capacitacion.equals("")) {
					capacitacion = "null";
				}
				cual = 3;
			}
			try {
				principal.crearSecion(cual, usuario, contra, nombre, cual, capacitacion);
			} catch (ExceptionUsuarioYaExiste | ExceptionUsuarioNoExiste | ExceptionInputIncorrecto | IOException
					| ExceptionInfoNotFound e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }else if(e.getSource()==rolComboBox){
	        String op = (String)rolComboBox.getSelectedItem();
	        if (op.equals("Empleado")){
	        	
	        }
        }

			
	}

}
