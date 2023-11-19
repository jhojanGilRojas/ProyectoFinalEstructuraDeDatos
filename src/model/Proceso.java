package model;

import resources.*;

import java.util.Objects;

public class Proceso {

    private String id;
    private String nombre;

    ListaDoble<Actividad> listaActividades = new ListaDoble<>();
    private int tiempoMinimo;
    private int tiempoMaximo;

    public Proceso(String id, String nombre, int tiempoMinimo, int tiempoMaximo) {
        this.id = id;
        this.nombre = nombre;
        this.listaActividades = listaActividades;
        this.tiempoMinimo = tiempoMinimo;
        this.tiempoMaximo = tiempoMaximo;
    }

    public Actividad buscarActividad(Actividad actividad) {

        if (listaActividades.buscarNodo(actividad) != null) {
            return actividad;
        }
        return null;
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
    public Actividad crearActividad (String nomnre, String descripcion, boolean obligatoriedad, Cola<Tarea> tareas){

        NodoDoble<Actividad> nodoActividad = listaActividades.buscarNodo(new Actividad(nombre, descripcion, obligatoriedad, tareas));

        if(nodoActividad != null){
            throw new RuntimeException("La actividad ya existe en la lista");
        }

        Actividad nuevaActividad = new Actividad(nombre, descripcion, obligatoriedad, tareas);
        listaActividades.agregarInicio(nuevaActividad);
        System.out.println("Actividad creada con éxito");
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





    public Actividad crearActividadAlFinal(String nombre, String descripcion, boolean obligatoriedad, Cola<Tarea> tareas) {
        // Crear una instancia de Actividad con la información proporcionada
        Actividad nuevaActividad = new Actividad(nombre, descripcion, obligatoriedad, tareas);

        // Verificar si la actividad ya existe en la lista usando buscarNodo
        NodoDoble<Actividad> nodoExistente = listaActividades.buscarNodo(nuevaActividad);

        // Si ya existe, lanzar una excepción
        if (nodoExistente != null) {
            throw new RuntimeException("La actividad ya existe en la lista.");
        }

        // Si no existe, agregar la nueva actividad al final de la lista
        listaActividades.agregarFinal(nuevaActividad);

        // Imprimir mensaje de éxito
        System.out.println("Actividad creada con éxito");

        // Devolver la nueva actividad
        return nuevaActividad;
    }

        public void crearDespuesDeUltimo(Actividad actividadExistente, Actividad nuevaActividad) {
            NodoDoble<Actividad> nodoActual = listaActividades.getNodoPrimero();
            NodoDoble<Actividad> ultimoNodoConActividad = null;

            // Iterar sobre la lista para encontrar la última ocurrencia de la actividad existente
            while (nodoActual != null) {
                if (nodoActual.getValorNodo().equals(actividadExistente)) {
                    ultimoNodoConActividad = nodoActual;
                }
                nodoActual = nodoActual.getSiguienteNodo();
            }

            // Verificar si se encontró alguna ocurrencia de la actividad existente
            if (ultimoNodoConActividad != null) {
                // Crear el nuevo nodo y asignarle la nueva actividad
                NodoDoble<Actividad> nuevoNodo = new NodoDoble<>(nuevaActividad);

                // Insertar el nuevo nodo después del último nodo con la actividad existente
                nuevoNodo.setSiguienteNodo(ultimoNodoConActividad.getSiguienteNodo());
                ultimoNodoConActividad.setSiguienteNodo(nuevoNodo);
            } else {
                // La actividad existente no se encontró en la lista
                System.out.println("La actividad existente no se encuentra en la lista.");
            }
        }

        // Otros métodos de la clase Proceso


    public Actividad crearActividadPosicionDeterminada(Actividad actividad, int posicion) {
        NodoDoble<Actividad> nodoActividad = listaActividades.buscarNodo(actividad);

        if (nodoActividad != null) {
            throw new RuntimeException("La actividad ya existe en la lista");
        }

        // Verificar si la posición proporcionada es válida
        if (posicion < 0 || posicion > listaActividades.getTamano()) {
            throw new IllegalArgumentException("Posición inválida");
        }

        // Insertar la actividad en la posición determinada
        listaActividades.insertarEnPosicion(actividad, posicion);
        System.out.println("Actividad creada con exito en posición determinada");
        return actividad;
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
