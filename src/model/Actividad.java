package model;

import resources.Cola;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Actividad {

    private String nombre;
    private String descripcion;
    private boolean obligatoriedad;

    private int tiempoMin;
    private int tiempoMax;


    Cola<Tarea> tareas;

    public Actividad(String nombre, String descripcion, boolean obligatoriedad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.obligatoriedad = obligatoriedad;
        this.tareas = new Cola<>();
        this.tiempoMin = 0;
        this.tiempoMax = 0;
    }

    public Actividad() {
        this.tareas = new Cola<>();
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
        return this.tareas;
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

    public Tarea crearTarea(Tarea tarea){
        getTareas().encolar(tarea);
        return tarea;
    }

    public ArrayList<Tarea> convertirCola(Cola<Tarea> tareas) {
        ArrayList<Tarea> listaTareas = new ArrayList<>();
        while (!tareas.estaVacia()) {
            listaTareas.add(tareas.desencolar());
        }
        for (int j = 0; j < listaTareas.size(); j++) {
            getTareas().encolar(listaTareas.get(j));
        }
        return listaTareas;
    }

    public Tarea actualizarTarea(Tarea tarea, String nombreTareaVieja) {
        Cola colaAux = new Cola();
        while (!tareas.estaVacia()){
            Tarea tareaI = tareas.desencolar();
            colaAux.encolar(tareaI);

            if(tareaI.getNombre().equals(nombreTareaVieja)){
                tareaI.setNombre(tarea.getNombre());
                tareaI.setDescripcion(tarea.getNombre());
                tareaI.setObligatoriedad(tarea.isObligatoriedad());
                tareaI.setTiempo(tarea.getTiempo());
                return tareaI;
            }
        }
        tareas = colaAux;
        return null;
    }

    public void calcularTiempoMax(){
        Cola colaAux = new Cola();
        int tiempo = 0;
        while (!tareas.estaVacia()){
            Tarea tareaI = tareas.desencolar();
            tiempo += tareaI.getTiempo();
            colaAux.encolar(tareaI);
        }
        tareas = colaAux;
        this.tiempoMax = tiempo;
    }

    public int getTiempoMax() {
        return tiempoMax;
    }

    public void setTiempoMax(int tiempoMax) {
        this.tiempoMax = tiempoMax;
    }
}

