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
import javax.swing.BorderFactory; 
import javax.swing.border.EmptyBorder; 
import java.awt.Font; 


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
        
        setBackground(new Color(240, 248, 255)); 
        setBorder(new EmptyBorder(20, 20, 20, 20)); 
        setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        rolLabel = new JLabel("Rol:");
        String[] roles = {"Cliente", "Administrador", "Empleado"};
        rolComboBox = new JComboBox<>(roles);
        usuarioLabel = new JLabel("Usuario:");
        passwordLabel = new JLabel("Contraseña:");
        usuarioTextField = new JTextField(20); 
        passwordField = new JPasswordField(20); 
        loginButton = new JButton("Crear Cuenta"); 
        mensajeLabel = new JLabel("", JLabel.CENTER); 
        mensajeLabel.setForeground(new Color(220, 20, 60)); 
        mensajeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nombreLabel = new JLabel("Nombre:");
        nombreTextField = new JTextField(20);
        capaLabel = new JLabel("Capacitación:");
        capaLabel.setVisible(false);
        capaTextField = new JTextField(20);
        capaTextField.setVisible(false);

        usuarioTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        nombreTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        capaTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        rolComboBox.setBackground(Color.WHITE);
        rolComboBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        loginButton.setBackground(new Color(60, 179, 113)); 
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false); 
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));

        loginButton.addActionListener(this);
        rolComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Empleado".equals(rolComboBox.getSelectedItem())) {
                    capaLabel.setVisible(true);
                    capaTextField.setVisible(true);
                } else {
                    capaLabel.setVisible(false);
                    capaTextField.setVisible(false);
                }
                principal.pack(); 
            }
        });

        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.anchor = GridBagConstraints.EAST;
        add(rolLabel, gbc); 

        // Desplegable
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(rolComboBox, gbc);

        // Usuario Label
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
        
        // Nombre Label
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(nombreLabel, gbc);

        // Cuadrito texto nombre
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(nombreTextField, gbc);
        
        // Capacitacion Label
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        add(capaLabel, gbc);

        // Cuadrito texto capacitacion
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(capaTextField, gbc);

        // Boton
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE; // No estirar el botón
        add(loginButton, gbc);

        // Mensaje Label
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(mensajeLabel, gbc);

        setName("login");
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton){
            String usuario = usuarioTextField.getText();
            String nombre = nombreTextField.getText();
            String contra = String.valueOf(passwordField.getPassword());
            String op = (String)rolComboBox.getSelectedItem();
            String capacitacion = capaTextField.getText();
            int cual = 0;
            if (op.equals("Cliente")) {
                cual = 1;
            } else if (op.equals("Administrador")) {
                cual = 2;
            } else if (op.equals("Empleado")) {
                if (capacitacion.isEmpty()) { // Si está vacío, se asume "null"
                    capacitacion = "null";
                }
                cual = 3;
            }
            try {
                principal.crearSecion(cual, usuario, contra, nombre, cual, capacitacion);
                mensajeLabel.setText("Cuenta creada exitosamente!");
                mensajeLabel.setForeground(new Color(34, 139, 34)); // Verde para éxito
            } catch (ExceptionUsuarioYaExiste | ExceptionUsuarioNoExiste | ExceptionInputIncorrecto | IOException
                    | ExceptionInfoNotFound e1) {
                mensajeLabel.setText("Error al crear cuenta: " + e1.getMessage());
                mensajeLabel.setForeground(new Color(220, 20, 60)); // Rojo para error
            }
        }
    }
}