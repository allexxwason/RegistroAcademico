/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.academico.domain;

import java.util.ArrayList; 
import java.util.List;  
import java.util.UUID; // Para generar IDs únicos

/**
 *
 * @author Coder
 */
public class Estudiante {
    
    private final String id; 
    private String nombre;
    private int edad;
    private String email; 
    private List<Nota> notas = new ArrayList<>();
    
    
    public Estudiante(String nombre, int edad, double nota1, double nota2, double nota3) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.edad = edad;
        this.email = nombre.toLowerCase().replaceAll("\\s+", ".") + "@Riwi.com";
        this.notas = new ArrayList<>();
        this.notas.add(new Nota(nota1));
        this.notas.add(new Nota(nota2));
        this.notas.add(new Nota(nota3));
    }

    // Constructor que el método cargarCSV necesita
    public Estudiante(String id, String nombre, int edad, List<Nota> notas) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.notas = notas;
        this.email = nombre.toLowerCase().replaceAll("\\s+", ".") + "@Riwi.com";

    }
    
    // getters
    public String getId(){return id;}
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public List<Nota> getNotas() { return new ArrayList<>(notas); } 
    public String getEmail() { return email; } 
    
    // Métodos para acceder a notas individuales (simplificado para la tabla)
    public double getNota1() { return notas.get(0).getValor(); }
    public double getNota2() { return notas.get(1).getValor(); }
    public double getNota3() { return notas.get(2).getValor(); }

    @Override
    public String toString() {
        return String.format("Estudiante{id='%s', nombre='%s', edad=%d, email='%s', notas=%s}",
            id, nombre, edad, email, notas);
    }

}
