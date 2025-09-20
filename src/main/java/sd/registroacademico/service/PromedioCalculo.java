/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.academico.service;

import com.mycompany.academico.domain.Nota;
import java.util.List;

/**
 *
 * @author Coder
 */
public class PromedioCalculo {
    
    // Calcula el promedio de notas con redondeo a 2 decimales
    public double promedio(List<Nota> notas) {
        double suma = 0.0;
        for (Nota nota : notas) {
            suma += nota.getValor();
        }
        if (notas.isEmpty()) return 0.0;
        
        double resultado = suma / notas.size();
        return Math.round(resultado * 100.0) / 100.0; // redondea a 2 decimales
    }
    
    // Encuentra la nota máxima
    public Nota notaMaxima(List<Nota> notas) {
        if (notas.isEmpty()) {
            return null;
        }
        Nota maxNota = notas.get(0);
        for (Nota nota : notas) {
            if (nota.getValor() > maxNota.getValor()) {
                maxNota = nota;
            }
        }
        return maxNota;
    }

    // Encuentra la nota mínima
    public Nota notaMinima(List<Nota> notas) {
        if (notas.isEmpty()) {
            return null;
        }
        Nota minNota = notas.get(0);
        for (Nota nota : notas) {
            if (nota.getValor() < minNota.getValor()) {
                minNota = nota;
            }
        }
        return minNota;
    }
    
    // Verifica si está aprobado (>= 3.0) 
    public String aprobado(double promedio) {
        return promedio >= 3.0 ? "✅ Aprobado" : "❌ Reprobado";
    }
}
