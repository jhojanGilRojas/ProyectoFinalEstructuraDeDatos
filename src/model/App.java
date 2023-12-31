package model;

import resources.Cola;
import resources.ListaDoble;
import resources.ListaSimple;
import resources.Nodo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class App extends Thread {

    public static final String RUTA_ARCHIVO_CSV = "C://td//";

    private ListaSimple <Proceso>listaProcesos;

    Scanner leer = new Scanner(System.in);
    Usuario usuarioLogueado;

    private ListaSimple<Usuario> listaUsuarios;


    public App() {
        this.listaProcesos = new ListaSimple<>();
        this.listaUsuarios = new ListaSimple<>();
        inicializarDatos();
    }

    private void inicializarDatos() {
        listaUsuarios.agregarInicio(new Usuario("jhojan","123",Rol.ADMINISTRADOR));
        Proceso proceso = new Proceso("12","Desayunos");
        Actividad actividad = new Actividad("Prepara Cafe","hacer el cafe",true);
        //actividad.crearTarea(new Tarea("TAREA 1", "una tarea fácil", false));
        proceso.crearActividad(actividad.getNombre(), actividad.getDescripcion(), actividad.isObligatoriedad());
        listaProcesos.agregarInicio(proceso);
    }

    public Usuario iniciarSesion(Usuario usuario) throws Exception {

        Nodo<Usuario> nodoUsuario = listaUsuarios.buscarNodo(usuario);

        if (nodoUsuario != null) {
            Usuario usuarioEnLista = nodoUsuario.getValorNodo();

            if (usuarioEnLista != null &&
                    usuarioEnLista.getUserId().equals(usuario.getUserId()) &&
                    usuarioEnLista.getPassword().equals(usuario.getPassword())) {
                System.out.println("Sesion iniciada con exito");
                return usuarioEnLista;

            }
        }
        return null;
    }
    public Usuario crearCuentasDeUsuario (String userId,String password,Rol rol){

        Usuario usuarioEncontrado = null;
        for (int i = 0; i < listaUsuarios.getTamano(); i++) {
            Usuario usuario = listaUsuarios.obtenerNodo(i).getValorNodo();

            if(usuario.getUserId().equals(userId)){
                usuarioEncontrado = usuario;
            }
        }

        if (usuarioEncontrado != null) {
            return null;
        }

        Usuario usuario = new Usuario(userId,password,rol);
        listaUsuarios.agregarInicio(usuario);
        return usuario;
    }



    public Proceso crearProceso(String id, String nombre) {
        Proceso procesoEncontrado = null;
        for (int i = 0; i < listaProcesos.getTamano(); i++) {
            Proceso proceso = listaProcesos.obtenerNodo(i).getValorNodo();

            if(proceso.getId().equals(id)){
                procesoEncontrado = proceso;
            }
        }
        if (procesoEncontrado != null) {
            return null;
        }

        Proceso nuevoProceso = new Proceso(id, nombre);
        listaProcesos.agregarInicio(nuevoProceso);
        //System.out.println("Proceso creado con éxito");
        return nuevoProceso;
    }



    public boolean eliminarProceso(Proceso proceso) throws Exception {

        if(!listaProcesos.estaVacia()){
            listaProcesos.eliminar(proceso);
            return true;
        }

        else {
            return false;
        }

    }

    public Proceso actualizarProceso(Proceso proceso, String id) {

        for (int i = 0; i < listaProcesos.getTamano(); i++) {
            Proceso proceso1 = listaProcesos.obtenerNodo(i).getValorNodo();
            if(proceso1.getId().equals(id)){
                proceso1.setNombre(proceso.getNombre());
                return proceso1;
            }
        }
        return null;
    }



    private Proceso configurarProceso(){
        return null;
    }

    public void insertarTareaAlFinal(Proceso proceso, Actividad actividad, Tarea tarea) {
        Nodo<Proceso> nodoProceso = listaProcesos.buscarNodo(proceso);

        if (nodoProceso != null) {
            Proceso procesoEncontrado = (Proceso) nodoProceso.getValorNodo();

            if (procesoEncontrado != null) {
                ListaDoble<Actividad> listaActividades = procesoEncontrado.getListaActividades();

                if (listaActividades != null) {
                    Actividad actividadEncontrada = listaActividades.buscarNodo(actividad).getValorNodo();

                    if (actividadEncontrada != null) {
                        actividadEncontrada.getTareas().encolar(tarea);
                    }
                }
            }
        }
    }

    public double consultarTiempoDuracionProceso(Proceso proceso) {
        if (proceso != null) {
            return (proceso.getTiempoMaximo() + proceso.getTiempoMinimo()) / 2.0;
        }

        return 0;
    }

   /* public Usuario crearCuentasDeUsuario(String userId, String password, Rol rol) {
        // Verificar si la lista de usuarios es null
        if (listaUsuarios == null) {
            System.out.println("Error: la lista de usuarios es null");
            return null;
        }

        // Definir una clase interna para la comparación
        class UsuarioComparator implements Predicate<Usuario> {
            @Override
            public boolean test(Usuario usuario) {
                return usuario != null && usuario.getUserId().equals(userId);
            }
        }

        // Crear un objeto de la clase interna
        UsuarioComparator usuarioComparator = new UsuarioComparator();

        // Buscar si ya existe un usuario con el mismo userId
        Nodo<Usuario> nodoUsuario = listaUsuarios.buscarNodo(usuarioComparator);

        if (nodoUsuario != null) {
            // Ya existe un usuario con el mismo userId
            System.out.println("Ya existe un usuario con el mismo userId: " + nodoUsuario.getValorNodo().getUserId() + ". No se puede crear la cuenta.");
            return null;
        }

        // No existe un usuario con el mismo userId, crear y agregar a la lista
        Usuario nuevoUsuario = new Usuario(userId, password, rol);
        listaUsuarios.agregarFinal(nuevoUsuario);
        System.out.println("Usuario creado con éxito: " + nuevoUsuario.getUserId());
        return nuevoUsuario;
    }*/


    private String notificarRecordatorios(){
        return null;
    }
    private String importarYExportarInformacion(){
        return null;
    }

    public ListaSimple getListaProcesos() {
        return listaProcesos;
    }

    public void setListaProcesos(ListaSimple listaProcesos) {
        this.listaProcesos = listaProcesos;
    }

    public ListaSimple getListaUsuarios() {
        return listaUsuarios;
    }
    public ArrayList<Usuario> getListaUsuariosArray(){
        ArrayList <Usuario> usuariosData = new ArrayList<>();


        for (int i = 0; i < listaUsuarios.getTamano(); i++) {
            Usuario usuario = (Usuario) listaUsuarios.obtenerNodo(i).getValorNodo();

            usuariosData.add(usuario);
        }

        return usuariosData;
    }

    public void setListaUsuarios(ListaSimple listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }


    public ArrayList <Proceso> getListaProcesosArray() {
        ArrayList <Proceso> procesosData = new ArrayList<>();
        for (int i = 0; i < listaProcesos.getTamano(); i++) {
            Proceso proceso = (Proceso) listaProcesos.obtenerNodo(i).getValorNodo();
            procesosData.add(proceso);
        }
        return procesosData;
    }

    public Usuario actualizarUsuario(Usuario usuarioNuevo,String id) {

        for (int i = 0; i < listaUsuarios.getTamano(); i++) {
            Usuario usuario = listaUsuarios.obtenerNodo(i).getValorNodo();
            if (usuario.getUserId().equals(id)){
                for (int j = 0; j < listaUsuarios.getTamano(); j++) {
                    Usuario usuario2 = listaUsuarios.obtenerNodo(j).getValorNodo();
                    if (usuario2.getUserId().equals(usuarioNuevo.getUserId())){
                        return null;
                    }

                }
                usuario.setUserId(usuarioNuevo.getUserId());
                usuario.setPassword(usuarioNuevo.getPassword());
                usuario.setRol(usuarioNuevo.getRol());
                return usuario;
            }

        }
        return null;
    }

    public void eliminarUsuario(String userId) {
        for (int i = 0; i < listaUsuarios.getTamano(); i++) {
            Usuario usuario = listaUsuarios.obtenerValorNodo(i);
            if (usuario.getUserId().equals(userId)){
                listaUsuarios.eliminar(usuario);
            }
        }
    }


    public Proceso getProcesoSelecionado(Proceso procesoSelecionado) {
        return procesoSelecionado;
    }

    public Usuario buscarUsuario(String email,String password) {
        for (int i = 0; i < listaUsuarios.getTamano(); i++) {
            Usuario usuario = listaUsuarios.obtenerNodo(i).getValorNodo();
            if (usuario.getUserId().equals(email)&&usuario.getPassword().equals(password)){
                return usuario;
            }
        }
        return null;
    }
    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public Object buscar(String filtro) {
        Cola<Tarea> colaAux = new Cola<>();

        for (int i = 0; i < listaProcesos.getTamano(); i++) {
            Proceso proceso = listaProcesos.obtenerNodo(i).getValorNodo();
            for (int j = 0; j < proceso.getListaActividades().getTamano(); j++) {
                Actividad actividad = proceso.getListaActividades().obtenerNodo(j).getValorNodo();

                if(actividad.getNombre().equals(filtro)){
                    return actividad;
                }
                while (!actividad.getTareas().estaVacia()){
                    Tarea tarea = actividad.getTareas().desencolar();
                    colaAux.encolar(tarea);
                    actividad.setTareas(colaAux);
                    if(tarea.getNombre().equals(filtro)){
                        return tarea;
                    }
                }
            }
        }
        return null;
    }

    public void iniciarProceso(Proceso proceso) throws InterruptedException {
        Cola<Tarea> colaAux = new Cola<>();
        for (int i = 0; i < proceso.getListaActividades().getTamano(); i++) {

            Actividad actividad = proceso.getListaActividades().obtenerNodo(i).getValorNodo();

            while (!actividad.getTareas().estaVacia()){
                Tarea tarea = actividad.getTareas().desencolar();
                colaAux.encolar(tarea);
                sleep(tarea.getTiempo()*1000);
                tarea.setCompletada(true);
            }
            actividad.setTareas(colaAux);
        }
    }

    public void exportarProcesosACSV(String nombreArchivo) {
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO_CSV+nombreArchivo)) {
            writer.write("ID Proceso,Nombre Proceso,Nombre Actividad,Descripción Actividad,Nombre Tarea,Descripción Tarea,Tiempo Tarea\n");
            for (int i = 0; i < listaProcesos.getTamano(); i++) {
                Proceso proceso = listaProcesos.obtenerNodo(i).getValorNodo();
                for (int j = 0; j < proceso.getListaActividades().getTamano(); j++) {
                    Actividad actividad = proceso.getListaActividades().obtenerValorNodo(j);
                    // Obtener el tamaño actual de la cola de tareas
                    int tamanoTareas = actividad.getTareas().getTamano();
                    // Iterar y procesar cada tarea en la cola
                    for (int k = 0; k < tamanoTareas; k++) {
                        Tarea tarea = actividad.getTareas().desencolar(); // Extraer la tarea de la cola
                        writer.write(proceso.getId() + "," + proceso.getNombre() + "," +
                                actividad.getNombre() + "," + actividad.getDescripcion() + "," +
                                tarea.getNombre() + "," + tarea.getDescripcion() + "," + tarea.getTiempo() + "\n");
                        // Volver a encolar la tarea para mantener la estructura original
                        actividad.getTareas().encolar(tarea);
                    }
                }
            }
            System.out.println("Datos exportados correctamente a " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
