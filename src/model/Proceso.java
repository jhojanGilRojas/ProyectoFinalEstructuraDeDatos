package model;

import resources.*;

import java.util.ArrayList;
import java.util.Objects;

public class Proceso {

    private String id;
    private String nombre;
    private int indice = 0;

    ListaDoble<Actividad> listaActividades = new ListaDoble<>();
    private int tiempoMinimo;
    private int tiempoMaximo;

    public Proceso(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.listaActividades = listaActividades;
        tiempoMinimo = 0;
        tiempoMaximo = 0;
    }
    public Proceso(String id, String nombre,  int tiempoMin,int tiempoMax) {
        this.id = id;
        this.nombre = nombre;
        this.listaActividades = listaActividades;
        tiempoMinimo = tiempoMin;
        tiempoMaximo = tiempoMax;
    }

    public Actividad buscarActividad(Actividad actividad) {

        if (listaActividades.buscarNodo(actividad) != null) {
            return actividad;
        }
        return null;
    }
    public int buscarActividadIndice(Actividad actividad) {

        for (int i = 0; i < listaActividades.getTamano(); i++) {
            Actividad actividad1 = listaActividades.obtenerValorNodo(i);
            if (actividad1.getNombre().equals(actividad.getNombre())){
                return i;
            }
        }
        return 0;
    }

    public Tarea buscarTarea(Actividad actividad, Tarea tarea) {
        NodoDoble<Actividad> nodoActividad = listaActividades.buscarNodo(actividad);

        if (nodoActividad != null) {
            Cola<Tarea> colaTareas = nodoActividad.getValorNodo().getTareas();
            NodoCola<Tarea> nodoTarea = colaTareas.buscarNodoCola(tarea);

            if (nodoTarea != null) {
                return nodoTarea.getValorNodo();
            }
        }

        return null;
    }


    public void intercambiarActividadesSinSusTareas(Actividad actividad1, Actividad actividad2) {
        NodoDoble<Actividad> nodoActividad1 = listaActividades.buscarNodo(actividad1);
        NodoDoble<Actividad> nodoActividad2 = listaActividades.buscarNodo(actividad2);

        if (nodoActividad1 != null && nodoActividad2 != null) {
            // Intercambiar actividades solo si ambas actividades existen en la lista
            Actividad actividadAuxiliar = nodoActividad1.getValorNodo();
            nodoActividad1.setValorNodo(nodoActividad2.getValorNodo());
            nodoActividad2.setValorNodo(actividadAuxiliar);
        } else {
            System.out.println("Una o ambas actividades no existen en la lista.");
        }
    }


    public void intercambiarActividadesConSusListasDeTareas(Actividad actividad1, Actividad actividad2) {
        NodoDoble<Actividad> nodoActividad1 = listaActividades.buscarNodo(actividad1);
        NodoDoble<Actividad> nodoActividad2 = listaActividades.buscarNodo(actividad2);

        if (nodoActividad1 != null && nodoActividad2 != null) {
            // Intercambiar actividades solo si ambas actividades existen en la lista
            Actividad valorActividad1 = nodoActividad1.getValorNodo();
            Actividad valorActividad2 = nodoActividad2.getValorNodo();

            // Intercambiar colas de tareas
            Cola<Tarea> colaTareasAuxiliar = valorActividad1.getTareas();
            valorActividad1.setTareas(valorActividad2.getTareas());
            valorActividad2.setTareas(colaTareasAuxiliar);

            // Intercambiar posiciones en la lista
            nodoActividad1.setValorNodo(valorActividad2);
            nodoActividad2.setValorNodo(valorActividad1);
        } else {
            System.out.println("Una o ambas actividades no existen en la lista.");
        }
    }




    public void eliminarActividad(Actividad actividad) {
        // Busco la actividad en la lista
        NodoDoble<Actividad> nodoActividad = listaActividades.buscarNodo(actividad);

        if (nodoActividad==null){
            throw new RuntimeException("La actividad no existe en la lista");
        }
        listaActividades.eliminar(actividad);
    }
    public Actividad crearActividad (String nombre, String descripcion, boolean obligatoriedad){

        Actividad actividadEncontrada = null;

        for (int i =0; i<listaActividades.getTamano();i++){
            Actividad actividad = listaActividades.obtenerValorNodo(i);

            if(actividad.getNombre().equals(nombre)){
                actividadEncontrada = actividad;
            }
        }

        if (actividadEncontrada != null){
            return null;}

        Actividad nuevaActividad = new Actividad(nombre, descripcion, obligatoriedad);
        listaActividades.agregarInicio(nuevaActividad);
        //System.out.println("Actividad creada con éxito");
        indice = listaActividades.obtenerPosicionNodo(nuevaActividad);
        return nuevaActividad;
    }

    public Actividad actualizarActividad(Actividad actividad) {

        NodoDoble<Actividad> nodoActividad = listaActividades.buscarNodo(actividad);

        if (nodoActividad != null) {
            Actividad actividadEncontrada = nodoActividad.getValorNodo();

            if (actividadEncontrada != null) {
                actividadEncontrada.setNombre(actividad.getNombre());
                actividadEncontrada.setDescripcion(actividad.getDescripcion());
                actividadEncontrada.setObligatoriedad(actividad.isObligatoriedad());
                actividadEncontrada.setTareas(actividad.getTareas());

            }
        }
            return actividad;
        }


    public Actividad crearActividadAlFinal (String nombre, String descripcion, boolean obligatoriedad){

        Actividad actividadEncontrada = null;

        for (int i =0; i<listaActividades.getTamano();i++){
            Actividad actividad = listaActividades.obtenerValorNodo(i);

            if(actividad.getNombre().equals(nombre)){
                actividadEncontrada = actividad;
            }
        }

        if (actividadEncontrada != null){
            return  null;}

        Actividad nuevaActividad = new Actividad(nombre, descripcion, obligatoriedad);
        listaActividades.agregarFinal(nuevaActividad);
        System.out.println("Actividad creada con éxito");
        indice = listaActividades.obtenerPosicionNodo(nuevaActividad);

        return nuevaActividad;
    }

    public Actividad crearActividadDespuesDeUltima(Actividad nuevaActividad) {


        for (int i = 0; i < listaActividades.getTamano(); i++) {
            if (listaActividades.obtener(i).equals(nuevaActividad)) {
                return null;
            }
        }

        if (indice != -1) {
            // Inserta la nueva actividad después de la última
            listaActividades.insertarEnPosicion(nuevaActividad, indice + 1);
            indice+=1;
            return nuevaActividad;
        } else {
            System.out.println("La actividad existente no se encuentra en la lista.");
            return null;
        }
    }

    public Actividad crearActividadPosicionDeterminada(Actividad nuevaActividad, int posicion) {
        // Verificar si la posición proporcionada es válida
        if (posicion < 0 || posicion > listaActividades.getTamano()) {
            return null;
        }

        // Verificar si la nueva actividad ya existe en la lista
        for (int i = 0; i < listaActividades.getTamano(); i++) {
            Actividad actividad = listaActividades.obtener(i);
            if (actividad.equals(nuevaActividad)) {
                return null;
            }
        }

        // Insertar la nueva actividad en la posición determinada
        listaActividades.insertarEnPosicion(nuevaActividad, posicion);
        System.out.println("Actividad creada con éxito en posición determinada");
        indice = posicion;
        return nuevaActividad;
    }


    public ArrayList<Actividad> getListaActividadesArray() {
        ArrayList <Actividad> actividadesData = new ArrayList<>();
        for (int i = 0; i < getListaActividades().getTamano(); i++) {
            Actividad actividad = (Actividad) listaActividades.obtenerNodo(i).getValorNodo();
            actividadesData.add(actividad);
        }
        return actividadesData;
    }
    public Actividad actualizarActividad(Actividad actividad, String nombre) {
        Actividad actividadActualizada = null;

        for (int i = 0; i < listaActividades.getTamano(); i++) {
            Actividad actividadExistente = listaActividades.obtenerValorNodo(i);

            if (actividadExistente.getNombre().equals(nombre)) {
                // Se encontró la actividad que coincide con el nombre proporcionado
                actividadActualizada = actividadExistente;
                actividadActualizada.setDescripcion(actividad.getDescripcion());
                actividadActualizada.setObligatoriedad(actividad.isObligatoriedad());
                actividadActualizada.setTareas(actividad.getTareas());
                break;  // Terminar el bucle después de encontrar la actividad
            }
        }

        if (actividadActualizada != null) {
            System.out.println("Actividad actualizada con éxito");
        } else {
            System.out.println("No se encontró ninguna actividad con el nombre proporcionado");
        }

        return actividadActualizada;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoMinimo() {
        return tiempoMinimo;
    }

    public void setTiempoMinimo(int tiempoMinimo) {
        this.tiempoMinimo = tiempoMinimo;
    }

    public int getTiempoMaximo() {
        return tiempoMaximo;
    }

    public void setTiempoMaximo(int tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }

    public ListaDoble<Actividad> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(ListaDoble<Actividad> listaActividades) {
        this.listaActividades = listaActividades;
    }

    @Override
    public String toString() {
        return "Proceso{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", listaActividades=" + listaActividades +
                ", tiempoMinimo=" + tiempoMinimo +
                ", tiempoMaximo=" + tiempoMaximo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proceso proceso = (Proceso) o;
        return tiempoMinimo == proceso.tiempoMinimo && tiempoMaximo == proceso.tiempoMaximo && Objects.equals(id, proceso.id) && Objects.equals(nombre, proceso.nombre) && Objects.equals(listaActividades, proceso.listaActividades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, listaActividades, tiempoMinimo, tiempoMaximo);
    }
}
