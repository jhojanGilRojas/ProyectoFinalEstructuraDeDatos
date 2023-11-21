package model;

import java.util.Iterator;
import java.util.Objects;

import resources.Cola;
import resources.Nodo;

public class Tarea {
    private String nombre;
    private String descripcion;
    private boolean obligatoriedad;
    private int tiempo;

    Cola<Tarea> cola = new Cola<>();

    public Tarea(String nombre, String descripcion, boolean obligatoriedad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.obligatoriedad = obligatoriedad;
    }

    public Tarea insertarEnPosicionDeterminada(Tarea tarea, int posicion) {
        // Verificar si la posición es válida
        if (posicion < 0 || posicion > cola.tamanio) {
            throw new IllegalArgumentException("Posición inválida");
        }

        // Desencolar hasta la posición deseada
        int i = 0;
        Cola<Tarea> colaAuxiliar = new Cola<>(); // Crear una cola auxiliar

        while (i < posicion) {
            Tarea tareaDesencolada = cola.desencolar(); // Cambiar por el método real de desencolar Tarea
            colaAuxiliar.encolar(tareaDesencolada); // Encolar en la cola auxiliar
            i++;
        }

        // Encolar la nueva tarea en la posición deseada
        colaAuxiliar.encolar(tarea); // Encolar la nueva tarea

        // Desencolar y volver a encolar las tareas restantes
        i = posicion + 1;
        while (i < cola.tamanio) {
            Tarea tareaDesencolada = cola.desencolar(); // Cambiar por el método real de desencolar Tarea
            colaAuxiliar.encolar(tareaDesencolada); // Encolar en la cola auxiliar
            i++;
        }

        // Volver a encolar las tareas desde la cola auxiliar a la cola original
        while (!colaAuxiliar.estaVacia()) {
            cola.encolar(colaAuxiliar.desencolar()); // Cambiar por el método real de encolar Tarea
        }

        return tarea;
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

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", obligatoriedad=" + obligatoriedad +
                ", cola=" + cola +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarea tarea = (Tarea) o;
        return obligatoriedad == tarea.obligatoriedad && Objects.equals(nombre, tarea.nombre) && Objects.equals(descripcion, tarea.descripcion) && Objects.equals(cola, tarea.cola);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion, obligatoriedad, cola);
    }


}