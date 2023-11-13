package resources;

public class Nodo<T> {

    private Nodo siguienteNodo; //Puntero enlace
    T valorNodo;

    public Nodo(T valorNodo) {
        this.valorNodo = valorNodo;
        siguienteNodo = null;
    }
    public Nodo getSiguienteNodo() {
        return siguienteNodo;
    }

    public void setSiguienteNodo(Nodo siguienteNodo) {
        this.siguienteNodo = siguienteNodo;
    }

    public T getValorNodo() {
        return valorNodo;
    }

    public void setValorNodo(T valorNodo) {
        this.valorNodo = valorNodo;
    }

    }


