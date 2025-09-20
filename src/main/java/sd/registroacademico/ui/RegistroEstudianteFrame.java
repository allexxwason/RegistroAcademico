package sd.registroacademico.ui;

import com.mycompany.academico.domain.Estudiante;
import com.mycompany.academico.domain.Nota;
import com.mycompany.academico.service.PromedioCalculo;
import com.mycompany.academico.service.CSVarchivo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RegistroEstudianteFrame extends JFrame {

    private static final Logger logger = Logger.getLogger(RegistroEstudianteFrame.class.getName());

    private final List<Estudiante> listaEstudiantes = new ArrayList<>();
    private final PromedioCalculo calculoService = new PromedioCalculo();
    private final CSVarchivo archivoService = new CSVarchivo();

    private JTextField txtNombre, txtNota1, txtNota2, txtNota3;
    private JSpinner txtEdad;
    private JLabel lblPromedio, lblNotaMax, lblNotaMin, lblResultado;
    private JTable tableEstudiantes;
    private DefaultTableModel tableModel;

    public RegistroEstudianteFrame() {
        setTitle("📘 Registro Académico");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        // Panel formulario
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Estudiante"));

        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Edad:"));
        txtEdad = new JSpinner(new SpinnerNumberModel(18, 1, 100, 1));
        panelForm.add(txtEdad);

        panelForm.add(new JLabel("Nota 1:"));
        txtNota1 = new JTextField();
        panelForm.add(txtNota1);

        panelForm.add(new JLabel("Nota 2:"));
        txtNota2 = new JTextField();
        panelForm.add(txtNota2);

        panelForm.add(new JLabel("Nota 3:"));
        txtNota3 = new JTextField();
        panelForm.add(txtNota3);

        // Panel botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnCalcular = new JButton("📊 Calcular");
        JButton btnGuardar = new JButton("💾 Guardar");
        JButton btnLimpiar = new JButton("🧹 Limpiar");
        JButton btnEliminar = new JButton("🗑 Eliminar");
        JButton btnGuardarCsv = new JButton("⬇ Guardar CSV");
        JButton btnCargarCsv = new JButton("⬆ Cargar CSV");
        JButton btnSalir = new JButton("🚪 Salir");

        panelBotones.add(btnCalcular);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnGuardarCsv);
        panelBotones.add(btnCargarCsv);
        panelBotones.add(btnSalir);

        // Panel resultados
        JPanel panelResultados = new JPanel(new GridLayout(4, 1));
        panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados"));
        lblPromedio = new JLabel("Promedio: ");
        lblNotaMax = new JLabel("Nota Máxima: ");
        lblNotaMin = new JLabel("Nota Mínima: ");
        lblResultado = new JLabel("Resultado: ");

        panelResultados.add(lblPromedio);
        panelResultados.add(lblNotaMax);
        panelResultados.add(lblNotaMin);
        panelResultados.add(lblResultado);

        // Tabla
        String[] columnNames = {"ID", "Nombre", "Edad", "Email", "Nota 1", "Nota 2", "Nota 3"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableEstudiantes = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableEstudiantes);

        // Layout principal con JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelForm, scrollPane);
        splitPane.setDividerLocation(200);

        add(splitPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        add(panelResultados, BorderLayout.EAST);

        // Acciones
        btnCalcular.addActionListener(this::calcularEstudiante);
        btnGuardar.addActionListener(this::guardarEstudiante);
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnEliminar.addActionListener(e -> eliminarEstudiante());
        btnGuardarCsv.addActionListener(this::guardarCSV);
        btnCargarCsv.addActionListener(this::cargarCSV);
        btnSalir.addActionListener(e -> dispose());
    }

    private void calcularEstudiante(ActionEvent evt) {
    try {
        String nombre = txtNombre.getText().trim();
        int edad = (Integer) txtEdad.getValue();
        double nota1 = Double.parseDouble(txtNota1.getText());
        double nota2 = Double.parseDouble(txtNota2.getText());
        double nota3 = Double.parseDouble(txtNota3.getText());

        // ✅ Validación de rango
        if (!esNotaValida(nota1) || !esNotaValida(nota2) || !esNotaValida(nota3)) {
            JOptionPane.showMessageDialog(
                this,
                "⚠ Las notas deben estar entre 0.0 y 5.0.",
                "Error de Validación",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Estudiante estudiante = new Estudiante(nombre, edad, nota1, nota2, nota3);
        double promedio = calculoService.promedio(estudiante.getNotas());
        Nota maxNota = calculoService.notaMaxima(estudiante.getNotas());
        Nota minNota = calculoService.notaMinima(estudiante.getNotas());
        String resultado = calculoService.aprobado(promedio);

        lblPromedio.setText("Promedio: " + promedio);
        lblNotaMax.setText("Nota Máxima: " + maxNota.getValor());
        lblNotaMin.setText("Nota Mínima: " + minNota.getValor());
        lblResultado.setText("Resultado: " + resultado);

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "⚠ Ingresa valores numéricos válidos para las notas.");
    }
}

// 🔹 Método auxiliar para validar notas
private boolean esNotaValida(double nota) {
    return nota >= 0.0 && nota <= 5.0;
}


    private void guardarEstudiante(ActionEvent evt) {
        try {
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠ El nombre no puede estar vacío.");
                return;
            }
            int edad = (Integer) txtEdad.getValue();
            if (edad < 1 || edad > 100) {
                JOptionPane.showMessageDialog(this, "⚠ Edad inválida (1-100).");
                return;
            }
            double nota1 = Double.parseDouble(txtNota1.getText());
            double nota2 = Double.parseDouble(txtNota2.getText());
            double nota3 = Double.parseDouble(txtNota3.getText());

            Estudiante estudiante = new Estudiante(nombre, edad, nota1, nota2, nota3);
            listaEstudiantes.add(estudiante);

            Object[] rowData = {
                estudiante.getId(),
                estudiante.getNombre(),
                estudiante.getEdad(),
                estudiante.getEmail(),
                estudiante.getNota1(),
                estudiante.getNota2(),
                estudiante.getNota3()
            };
            tableModel.addRow(rowData);

            JOptionPane.showMessageDialog(this, "✅ Estudiante guardado correctamente.");
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "❌ Error: Ingrese números válidos en las notas.");
        }
    }

    private void eliminarEstudiante() {
        int fila = tableEstudiantes.getSelectedRow();
        if (fila >= 0) {
            listaEstudiantes.remove(fila);
            tableModel.removeRow(fila);
            JOptionPane.showMessageDialog(this, "🗑 Estudiante eliminado.");
        } else {
            JOptionPane.showMessageDialog(this, "⚠ Selecciona un estudiante de la tabla para eliminar.");
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtEdad.setValue(18);
        txtNota1.setText("");
        txtNota2.setText("");
        txtNota3.setText("");
        lblPromedio.setText("Promedio: ");
        lblNotaMax.setText("Nota Máxima: ");
        lblNotaMin.setText("Nota Mínima: ");
        lblResultado.setText("Resultado: ");
    }

    private void guardarCSV(ActionEvent evt) {
        if (listaEstudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠ No hay estudiantes para guardar.");
            return;
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar archivo CSV");
        int opcion = chooser.showSaveDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                archivoService.guardarCSV(listaEstudiantes, file);
                JOptionPane.showMessageDialog(this, "✅ Archivo guardado: " + file.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "❌ Error al guardar: " + e.getMessage());
            }
        }
    }

    private void cargarCSV(ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Cargar archivo CSV");
        int opcion = chooser.showOpenDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                List<Estudiante> cargados = archivoService.cargarCSV(file);
                listaEstudiantes.clear();
                listaEstudiantes.addAll(cargados);
                actualizarTabla();
                JOptionPane.showMessageDialog(this, "✅ Archivo cargado con éxito.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "❌ Error al cargar: " + e.getMessage());
            }
        }
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        for (Estudiante est : listaEstudiantes) {
            Object[] rowData = {
                est.getId(),
                est.getNombre(),
                est.getEdad(),
                est.getEmail(),
                est.getNota1(),
                est.getNota2(),
                est.getNota3()
            };
            tableModel.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroEstudianteFrame().setVisible(true));
    }
}
