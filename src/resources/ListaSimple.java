package resources;


import java.util.Iterator;

public class ListaSimple<T> implements Iterable<T>{

    private Nodo <T> nodoPrimero;
    private Nodo <T> nodoUltimo;
    int tamano;

    public ListaSimple() {
        nodoPrimero = null;
        tamano = 0;
    }

    public void agregarInicio(T valorNodo) {

        Nodo <T>nuevoNodo = new Nodo<>(valorNodo);

        if (estaVacia()) {
            nodoPrimero = nuevoNodo;
        } else {
            nuevoNodo.setSiguienteNodo(nodoPrimero);
            nodoPrimero = nuevoNodo;
        }
        tamano++;
    }

    public void agregarFinal(T valorNodo){

        Nodo<T> nodo = new Nodo<>(valorNodo);

        if (estaVacia()){
            nodoPrimero = nodoUltimo = nodo;
        } else {
            nodoUltimo.setSiguienteNodo(nodo);
            nodoUltimo = nodo;
        }
        tamano++;
    }

    public void imprimirLista() {

        Nodo<T> aux = nodoPrimero;

        while(aux!=null) {
            System.out.print( aux.getValorNodo()+"\t" );
            aux = aux.getSiguienteNodo();
        }

        System.out.println();
    }

    public T obtenerValorNodo(int indice) {

        Nodo<T> nodoTemporal = null;
        int contador = 0;

        if(indiceValido(indice))
        {
            nodoTemporal = nodoPrimero;

            while (contador < indice) {

                nodoTemporal = nodoTemporal.getSiguienteNodo();
                contador++;
            }
        }

        if(nodoTemporal != null)
            return nodoTemporal.getValorNodo();
        else
            return null;
    }

    public int obtenerPosicionNodo(T dato) {

        int i = 0;

        for( Nodo<T> aux = nodoPrimero ; aux!=null ; aux = aux.getSiguienteNodo() ) {
            if( aux.getValorNodo().equals(dato) ) {
                return i;
            }
            i++;
        }

        return -1;
    }

    //Verificar si indice es valido
    private boolean indiceValido(int indice) {
        if( indice>=0 && indice<tamano ) {
            return true;
        }
        throw new RuntimeException("�ndice no v�lido");
    }

    private Nodo<T> obtenerNodo(int indice) {

        if(indice>=0 && indice<tamano) {

            Nodo<T> nodo = nodoPrimero;

            for (int i = 0; i < indice; i++) {
                nodo = nodo.getSiguienteNodo();
            }

            return nodo;
        }

        return null;
    }

    public void insertarEnPosicion(T dato, int posicion) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);

        if (nodoPrimero == null) {
            // Si la lista está vacía, el nuevo nodo se convierte en el inicio.
            nodoPrimero = nuevoNodo;
        } else if (posicion <= 0) {
            // Insertar al principio (posición 0)
            nuevoNodo.setSiguienteNodo(nodoPrimero);
            nodoPrimero = nuevoNodo;
        } else {
            Nodo<T> nodoActual = nodoPrimero;
            for (int i = 0; i < posicion - 1 && nodoActual.getSiguienteNodo() != null; i++) {
                nodoActual = nodoActual.getSiguienteNodo();
            }
            nuevoNodo.setSiguienteNodo(nodoActual.getSiguienteNodo());
            nodoActual.setSiguienteNodo(nuevoNodo);
        }
        tamano++;
    }

    private boolean estaVacia() { return (nodoPrimero == null)? true:false;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorListaSimple (nodoPrimero);
    }


    public Nodo<T> buscarNodo(T dato){

        Nodo<T> aux = nodoPrimero;

        while(aux!=null) {
            if(aux.getValorNodo().equals(dato)) {
                return aux;
            }
            aux = aux.getSiguienteNodo();
        }

        return null;
    }

    public class IteradorListaSimple implements Iterator<T>{

        private Nodo<T> nodo;
        private int posicion;

        /**
         * Constructor de la clase Iterador
       //  * @param aux Primer ejercicio1.Nodo de la lista
         */
        public IteradorListaSimple(Nodo<T> nodo) {
            this.nodo = nodo;
            this.posicion = 0;
        }

        @Override
        public boolean hasNext() {
            return nodo!=null;
        }

        @Override
        public T next() {
            T valor = nodo.getValorNodo();
            nodo = nodo.getSiguienteNodo();
            posicion++;
            return valor;
        }

        /**
         * Posici�n actual de la lista
         * @return posici�n
         */
        public int getPosicion() {
            return posicion;
        }
    }

    public Nodo getNodoPrimero() {
        return nodoPrimero;
    }

    public void setNodoPrimero(Nodo nodoPrimero) {
        this.nodoPrimero = nodoPrimero;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamanio(int tamano) {
        this.tamano = tamano;
    }

    @Override
    public String toString() {
        return "ListaSimple{" +
                nodoPrimero +
                nodoUltimo + tamano +
                '}';
    }
}

