package sd.registroacademico.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import javax.swing.*;

public class LoginFrame extends JFrame {

    private static final Logger logger = Logger.getLogger(LoginFrame.class.getName());

    private static final String USERNAME = "User";
    private static final int PASSWORD_HASH = "riwi941".hashCode();
    private int intentosFallidos = 0;
    private static final int MAX_INTENTOS = 3;

    // Componentes
    private JLabel lblUsuario;
    private JLabel lblPassword;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar;

    public LoginFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Login - Registro Académico");
        setSize(350, 200);
        setLocationRelativeTo(null); // Centrar ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Labels
        lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(30, 30, 80, 25);
        add(lblUsuario);

        lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(30, 70, 80, 25);
        add(lblPassword);

        // Inputs
        txtUsuario = new JTextField();
        txtUsuario.setBounds(120, 30, 150, 25);
        add(txtUsuario);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 70, 150, 25);
        add(txtPassword);

        // Botón
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(120, 110, 150, 30);
        btnIngresar.addActionListener(evt -> btnIngresarActionPerformed(evt));
        add(btnIngresar);
    }

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {
        String usuario = txtUsuario.getText();
        String password = new String(txtPassword.getPassword());

        if (USERNAME.equals(usuario) && password.hashCode() == PASSWORD_HASH) {
            String fecha = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

            JOptionPane.showMessageDialog(
                null,
                "✅ Bienvenido " + USERNAME + "\nFecha de ingreso: " + fecha
            );

        logger.info("Usuario " + usuario + " inició sesión correctamente a las " + fecha);

        // 🔹 Aquí abrimos el frame de RegistroEstudiante
        RegistroEstudianteFrame registroFrame = new RegistroEstudianteFrame();
        registroFrame.setVisible(true);

        this.dispose(); // Cerramos login
    } else {
        intentosFallidos++;
        JOptionPane.showMessageDialog(
                null,
                "❌ Usuario o contraseña incorrectos. Intento " + intentosFallidos + " de " + MAX_INTENTOS
        );
        logger.warning("Intento fallido de login: " + usuario);

        if (intentosFallidos >= MAX_INTENTOS) {
            JOptionPane.showMessageDialog(null, "Demasiados intentos fallidos. Cerrando sistema.");
            System.exit(0);
        }
    }
}


    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
