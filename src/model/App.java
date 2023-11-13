package model;

import resources.ListaSimple;
import resources.Nodo;

import java.util.Scanner;
import java.util.function.Predicate;

public class App {

    private ListaSimple listaProcesos;

    Scanner leer = new Scanner(System.in);

    private ListaSimple listaUsuarios;

    public App() {
        listaProcesos = new ListaSimple<>();
        listaUsuarios = new ListaSimple<>();
        inicializarDatos();
    }

    private void inicializarDatos() {
        Usuario usuario = new Usuario("jhojan","1234",Rol.USUARIO_REGULAR);
        listaUsuarios.agregarInicio( usuario);
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


    public Proceso crearProceso(String id, String nombre, ListaSimple<Actividad> listaActividades, int tiempoMinimo, int tiempoMaximo) {
        Nodo<Proceso> nodoProceso = listaProcesos.buscarNodo(new Proceso(id, nombre, listaActividades, tiempoMinimo, tiempoMaximo));

        if (nodoProceso != null) {
            throw new RuntimeException("Ya existe un proceso con ese ID");
        }

        Proceso nuevoProceso = new Proceso(id, nombre, listaActividades, tiempoMinimo, tiempoMaximo);
        listaProcesos.agregarFinal(nuevoProceso);
        System.out.println("Proceso creado con éxito");
        return nuevoProceso;
    }


    private Proceso configurarProceso(){
        return null;
    }

    public void insertarTareaAlFinal(Proceso proceso, Actividad actividad, Tarea tarea) {
        Nodo<Proceso> nodoProceso = listaProcesos.buscarNodo(proceso);

        if (nodoProceso != null) {
            Proceso procesoEncontrado = (Proceso) nodoProceso.getValorNodo();

            if (procesoEncontrado != null) {
                ListaSimple<Actividad> listaActividades = procesoEncontrado.getListaActividades();

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


/*    public Usuario crearCuentasDeUsuario(String userId, String password, Rol rol) {
        // Verificar si la lista de usuarios es null
        if (listaUsuarios == null) {
            System.out.println("Error: la lista de usuarios es null");
            return null;
        }

        // Buscar si ya existe un usuario con el mismo userId
        Nodo<Usuario> nodoUsuario = listaUsuarios.buscarNodo(new Usuario(userId, null, null));

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
    }
*/

    public Usuario crearCuentasDeUsuario(String userId, String password, Rol rol) {
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
    }


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

    public void setListaUsuarios(ListaSimple listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }


}
