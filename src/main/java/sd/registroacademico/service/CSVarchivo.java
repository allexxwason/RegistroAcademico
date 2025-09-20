/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.academico.service;

import com.mycompany.academico.domain.Estudiante;
import com.mycompany.academico.domain.Nota;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Coder
 */
public class CSVarchivo {

    // Guardar estudiantes en un archivo CSV
    public void guardarCSV(List<Estudiante> estudiantes, File archivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {

            // Encabezado actualizado
            writer.write("id,nombre,edad,email,nota1,nota2,nota3");
            writer.newLine();

            for (Estudiante est : estudiantes) {
                // Usamos Locale.US para forzar punto decimal (.) en los números
                String linea = String.format(Locale.US, "%s,\"%s\",%d,%s,%.2f,%.2f,%.2f",
                        est.getId(),
                        est.getNombre(),
                        est.getEdad(),
                        est.getEmail(),
                        est.getNotas().get(0).getValor(),
                        est.getNotas().get(1).getValor(),
                        est.getNotas().get(2).getValor()
                );
                writer.write(linea);
                writer.newLine();
            }
        }
    }

    // Método para cargar estudiantes desde un archivo CSV
    public List<Estudiante> cargarCSV(File archivo) throws IOException {
        List<Estudiante> estudiantes = new ArrayList<>();

        // Usamos try-with-resources para asegurar que el BufferedReader se cierre automáticamente
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

            String linea;
            // Leer y descartar el encabezado
            reader.readLine();

            // Leer cada línea del archivo
            while ((linea = reader.readLine()) != null) {
                // Separar los valores por la coma
                String[] datos = linea.split(",");

                // Asegurarse de que hay suficientes datos
                if (datos.length == 7) {
                    try {
                        // Crear un nuevo objeto Estudiante
                        String id = datos[0];
                        String nombre = datos[1].replace("\"", ""); // Remover comillas dobles
                        int edad = Integer.parseInt(datos[2]);
                        String email = datos[3];

                        // Crear las notas
                        List<Nota> notas = new ArrayList<>();
                        notas.add(new Nota(Double.parseDouble(datos[4])));
                        notas.add(new Nota(Double.parseDouble(datos[5])));
                        notas.add(new Nota(Double.parseDouble(datos[6])));

                        // Crear y añadir el estudiante a la lista
                        Estudiante estudiante = new Estudiante(id, nombre, edad, notas);
                        estudiantes.add(estudiante);

                    } catch (NumberFormatException e) {
                        System.err.println("⚠ Error al parsear un número en la línea: " + linea);
                        System.out.println("Sáltandose línea corrupta...");
                    }
                }
            }
        }
        return estudiantes;
    }
}
