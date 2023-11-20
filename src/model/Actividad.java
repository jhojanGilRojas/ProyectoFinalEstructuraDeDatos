package model;

import resources.Cola;

import java.util.Iterator;
import java.util.Objects;

public class Actividad {

    private String nombre;
    private String descripcion;
    private boolean obligatoriedad;

    Cola<Tarea> tareas;

    public Actividad(String nombre, String descripcion, boolean obligatoriedad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.obligatoriedad = obligatoriedad;
        this.tareas = new Cola<>();
    }

    public Actividad() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isObligatoriedad() {
        return obligatoriedad;
    }

    public void setObligatoriedad(boolean obligatoriedad) {
        this.obligatoriedad = obligatoriedad;
    }

    public Cola<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(Cola<Tarea> tareas) {
        this.tareas = tareas;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", obligatoriedad=" + obligatoriedad +
                ", tareas=" + tareas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actividad actividad = (Actividad) o;
        return obligatoriedad == actividad.obligatoriedad && Objects.equals(nombre, actividad.nombre) && Objects.equals(descripcion, actividad.descripcion) && Objects.equals(tareas, actividad.tareas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion, obligatoriedad, tareas);
    }
}

