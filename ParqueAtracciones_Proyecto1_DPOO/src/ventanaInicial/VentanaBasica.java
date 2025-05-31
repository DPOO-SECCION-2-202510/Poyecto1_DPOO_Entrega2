package ventanaInicial;

import java.awt.BorderLayout;
import java.awt.Color; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.SwingUtilities; 

import administrador.Admin;
import cliente.Cliente;
import consola.ConsolaAdmin;
import consola.ConsolaCliente;
import consola.ConsolaEmpleado;
import empleado.Empleado;
import empleado.Trabajo;
import empleado.TrabajoAtraccion;
import empleado.TrabajoEspectaculo;
import exceptions.ExceptionConstrasenaIncorrecta;
import exceptions.ExceptionInfoNotFound;
import exceptions.ExceptionInputIncorrecto;
import exceptions.ExceptionUsuarioNoExiste;
import exceptions.ExceptionUsuarioYaExiste;
import juegos.AtraccionCultural;
import juegos.AtraccionMecanica;
import juegos.Espectaculo;
import parque.ParqueAtraccion;
import persistencia.PersistenciaBasica;
import persistencia.Previos;
import ventanaConsola.VentanaConsola;

public class VentanaBasica extends JFrame{
    private static final long serialVersionUID = 1L;

    private PanelLogIn logIn;
    private PanelSingIn singIn;
    private ParqueAtraccion parque;
    private PanelLista lista;
    private boolean existeSing;
    private boolean existeLog;

    public VentanaBasica() throws IOException, ExceptionUsuarioYaExiste, ExceptionUsuarioNoExiste {
        this.existeLog = false;
        this.existeSing = false;
        
        ArrayList<HashMap<String, Integer>> precios = new ArrayList<HashMap<String, Integer>>();
        HashMap<String, Integer> tiquete = new HashMap<String, Integer>();
        HashMap<String, Integer> entrada = new HashMap<String, Integer>();
        HashMap<String, Integer> fastPass = new HashMap<String, Integer>();
        precios.add(tiquete);
        precios.add(entrada);
        precios.add(fastPass);
        precios = PersistenciaBasica.cargarPrecios(precios);
        parque = ParqueAtraccion.getInstance(PersistenciaBasica.cargarEspacios(), precios.get(0), precios.get(1), precios.get(2).get("FastPass"));
        HashMap<ArrayList<AtraccionCultural>,ArrayList<AtraccionMecanica>> atracciones = PersistenciaBasica.cargarAtraccion(parque);
        ArrayList<AtraccionCultural> cultural = null;
        ArrayList<AtraccionMecanica> meca = null;
        for(ArrayList<AtraccionCultural> cult: atracciones.keySet()) {
            cultural = cult;
            meca = atracciones.get(cult);
        }
        ArrayList<Espectaculo> espe = PersistenciaBasica.cargarEspectaculo(parque);
        parque.añadirAtracciones(cultural, meca, espe);
        ArrayList<Trabajo> trabajosGeneral = PersistenciaBasica.cargarTrabajos(parque);
        ArrayList<TrabajoAtraccion> tatracciones = PersistenciaBasica.cargarTrabajosA(parque);
        ArrayList<TrabajoEspectaculo> espectaculos = PersistenciaBasica.cargarTrabajosE(parque);
        Previos.cargarAdminPrevio(parque, trabajosGeneral, tatracciones, espectaculos);
        Previos.cargarEmpleadoPrevio(parque);
        
        this.lista = new PanelLista(this);
        this.logIn = new PanelLogIn(this);
        this.singIn = new PanelSingIn(this);
        
        setLayout( new BorderLayout() );
        setBackground(new Color(240, 248, 255)); 
        
        add(lista, BorderLayout.NORTH);
        
        setTitle( "Parque de Atracciones - Inicio" ); 
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 450, 500 ); 
        setLocationRelativeTo( null );
        setVisible( true );
    }
    
    public void cambiarTipoEntrada(String opcion) {
        if(existeLog) {
            remove(logIn);
            existeLog = false;
        }
        if(existeSing) {
            remove(singIn);
            existeSing = false;
        }
        
        if (opcion.equals("Iniciar Sesión")) {
            add(this.singIn, BorderLayout.CENTER);
            this.singIn.setVisible(true);
            existeSing = true;
        }else if (opcion.equals("Crear cuenta")){
            add(this.logIn, BorderLayout.CENTER);
            this.logIn.setVisible(true);
            existeLog = true;
        }
        revalidate(); 
        repaint(); 
        pack(); 
        setLocationRelativeTo(null); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new VentanaBasica();
            } catch (IOException | ExceptionUsuarioYaExiste | ExceptionUsuarioNoExiste e) {
                e.printStackTrace();
            }
        });
    }
    
    public void iniciarSecion(int opcion, String usuario, String contra, String adminS) throws ExceptionUsuarioYaExiste, ExceptionUsuarioNoExiste, ExceptionInputIncorrecto, IOException, ExceptionInfoNotFound, ExceptionConstrasenaIncorrecta{
        if (opcion == 1) {
            Cliente cliente = parque.ingresarCliente(usuario, contra);
            ConsolaCliente consola = new ConsolaCliente(cliente, parque);
            VentanaConsola nueva = new VentanaConsola(parque, consola); 
        } else if (opcion == 2) {
            Admin admin = parque.ingresarAdmin(usuario, contra);
            ConsolaAdmin consola = new ConsolaAdmin(admin, parque, usuario, contra);
            VentanaConsola nueva = new VentanaConsola(parque, consola);
        } else if (opcion == 3) {
            Empleado emple = parque.ingresarEmpleado(usuario, contra, adminS);
            ConsolaEmpleado consola = new ConsolaEmpleado(emple);    
            VentanaConsola nueva = new VentanaConsola(parque, consola);
            dispose();
        }
    }
    
    public void crearSecion(int opcion, String usuario, String contra, String nombre, int id, String capacitacion) throws ExceptionUsuarioYaExiste, ExceptionUsuarioNoExiste, ExceptionInputIncorrecto, IOException, ExceptionInfoNotFound {
        if (opcion == 1) {
            Cliente cliente = parque.crearCliente(usuario, contra, nombre, id, 0, 0, 0, "");
            ConsolaCliente consola = new ConsolaCliente(cliente, parque);
            VentanaConsola nueva = new VentanaConsola(parque, consola);
        } else if (opcion == 2) {
            ArrayList<Trabajo> trabajosGeneral = PersistenciaBasica.cargarTrabajos(parque);
            ArrayList<TrabajoAtraccion> tatracciones = PersistenciaBasica.cargarTrabajosA(parque);
            ArrayList<TrabajoEspectaculo> espectaculos = PersistenciaBasica.cargarTrabajosE(parque);
            Admin admin = parque.crearAdmin(usuario, contra, nombre, id, trabajosGeneral, tatracciones, espectaculos);
            ConsolaAdmin consola = new ConsolaAdmin(admin, parque, usuario, contra);
            VentanaConsola nueva = new VentanaConsola(parque, consola);
        } else if (opcion == 3) {
            Empleado emple = parque.crearEmpleado(usuario, contra, nombre, id, capacitacion);
            ConsolaEmpleado consola = new ConsolaEmpleado(emple);    
            VentanaConsola nueva = new VentanaConsola(parque, consola);
        }
    }
}
