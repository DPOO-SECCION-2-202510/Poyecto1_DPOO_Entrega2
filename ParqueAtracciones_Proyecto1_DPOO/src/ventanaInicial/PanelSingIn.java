package ventanaInicial;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import exceptions.ExceptionConstrasenaIncorrecta;
import exceptions.ExceptionInfoNotFound;
import exceptions.ExceptionInputIncorrecto;
import exceptions.ExceptionUsuarioNoExiste;
import exceptions.ExceptionUsuarioYaExiste;

public class PanelSingIn extends JPanel implements ActionListener{
    
    private JLabel usuarioLabel;
    private JLabel passwordLabel;
    private JLabel rolLabel;
    private JTextField usuarioTextField;
    private JPasswordField passwordField;
    private JComboBox<String> rolComboBox;
    private JButton loginButton;
    private JLabel mensajeLabel;
    
    private VentanaBasica principal;

    public PanelSingIn(VentanaBasica principal) {
        this.principal = principal;
        
        setBackground(new Color(240, 248, 255)); 
        setBorder(new EmptyBorder(20, 20, 20, 20)); 
        setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        rolLabel = new JLabel("Rol:");
        String[] roles = {"Cliente", "Administrador", "Empleado"};
        rolComboBox = new JComboBox<>(roles);
        rolComboBox.addActionListener(this);
        usuarioLabel = new JLabel("Usuario:");
        passwordLabel = new JLabel("Contraseña:");
        usuarioTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Iniciar Sesión");
        mensajeLabel = new JLabel("", JLabel.CENTER);
        mensajeLabel.setForeground(new Color(220, 20, 60)); 
        mensajeLabel.setFont(new Font("Arial", Font.BOLD, 12));

        usuarioTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        rolComboBox.setBackground(Color.WHITE);
        rolComboBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        loginButton.setBackground(new Color(100, 149, 237)); 
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));

        loginButton.addActionListener(this);

        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.anchor = GridBagConstraints.EAST;
        add(rolLabel, gbc); 

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(rolComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(usuarioLabel, gbc);

        // Cuadrito texto Usuario
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(usuarioTextField, gbc);

        // Contraseña Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(passwordLabel, gbc);

        // cuadrito texto contraseña
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passwordField, gbc);

        // Boton
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(loginButton, gbc);

        // Mensaje Label
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(mensajeLabel, gbc);

        setName("singin");
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton){
            String usuario = usuarioTextField.getText();
            String contra = String.valueOf(passwordField.getPassword());
            String op = (String)rolComboBox.getSelectedItem();
            int cual = 0;
            if (op.equals("Cliente")) {
                cual = 1;
            } else if (op.equals("Administrador")) {
                cual = 2;
            } else if (op.equals("Empleado")) {
                cual = 3;
            }
            try {
                principal.iniciarSecion(cual, usuario, contra, "");
                mensajeLabel.setText("Inicio de sesión exitoso!");
                mensajeLabel.setForeground(new Color(34, 139, 34)); // Verde para éxito
            } catch (ExceptionUsuarioYaExiste | ExceptionUsuarioNoExiste | ExceptionInputIncorrecto | IOException
                    | ExceptionInfoNotFound | ExceptionConstrasenaIncorrecta e1) {
                mensajeLabel.setText("Error al iniciar sesión: " + e1.getMessage());
                mensajeLabel.setForeground(new Color(220, 20, 60)); // Rojo para error
            }
        }
    }
}