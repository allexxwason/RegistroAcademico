/*
 * Clase principal generada para el proyecto Registro Académico
 */

package sd.registroacademico;

import sd.registroacademico.ui.LoginFrame; // ✅ Importamos el login
import javax.swing.SwingUtilities;

/**
 * Punto de entrada del Sistema de Registro Académico
 * @author Coder
 */
public class RegistroAcademico {

    public static void main(String[] args) {
        System.out.println("🚀 Registro Académico iniciado correctamente");

        // ✅ Ejecutar la interfaz gráfica en el hilo de despacho de eventos (EDT)
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}
