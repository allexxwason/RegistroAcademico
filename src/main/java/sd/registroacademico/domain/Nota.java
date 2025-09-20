/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.academico.domain;

/**
 *
 * @author Coder
 */
public final class Nota {
    private final double calificacion;

    public Nota(double calificacion) {
        if (calificacion < 0.0 || calificacion > 5.0) {
            throw new IllegalArgumentException("La calificación debe estar en el rango 0.0 a 5.0");
        }
        this.calificacion = calificacion;
    }

    public double getValor() { 
        return calificacion; 
    }

    public boolean esAprobada() {
        return calificacion >= 3.0;
    }
}
