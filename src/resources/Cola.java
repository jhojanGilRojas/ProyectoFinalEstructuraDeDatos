package resources;


/**
 *
 * @param <T>
 */
public class Cola<T> {

	public NodoCola<T> nodoPrimero, nodoUltimo;
	public int tamanio;


	/**
	 * Agrega un elemento en la Cola
	 *
	 * @param dato elemento a guardar en la Cola
	 */
	public void encolar(T dato) {

		NodoCola<T> nodo = new NodoCola<>(dato);

		if (estaVacia()) {
			nodoPrimero = nodoUltimo = nodo;
		} else {
			nodoUltimo.setSiguienteNodo(nodo);
			nodoUltimo = nodo;
		}

		tamanio++;
	}

	/**
	 * Retorna y elimina el elemento que est� al incio de la Cola
	 *
	 * @return Primer elemento de la Cola
	 */
	public T desencolar() {

		if (estaVacia()) {
			throw new RuntimeException("La Cola est� vac�a");
		}

		T dato = nodoPrimero.getValorNodo();
		nodoPrimero = nodoPrimero.getSiguienteNodo();

		if (nodoPrimero == null) {
			nodoUltimo = null;
		}

		tamanio--;
		return dato;
	}


	/**
	 * Verifica si la Cola est� vac�a
	 *
	 * @return true si est� vac�a
	 */
	public boolean estaVacia() {
		return nodoPrimero == null;
	}

	/**
	 * Busca y retorna el nodo con el valor especificado al desencolar la Cola
	 *
	 * @param dato Valor a buscar y desencolar
	 * @return Nodo con el valor especificado, o null si no se encuentra
	 */
	public NodoCola<T> buscarNodoCola(T dato) {
		if (estaVacia()) {
			throw new RuntimeException("La Cola está vacía");
		}

		NodoCola<T> aux = nodoPrimero;
		NodoCola<T> nodoAnterior = null;

		while (aux != null && !aux.getValorNodo().equals(dato)) {
			nodoAnterior = aux;
			aux = aux.getSiguienteNodo();
		}

		if (aux != null) {
			// Se encontró el nodo con el valor especificado
			if (nodoAnterior != null) {
				// El nodo no es el primero en la cola
				nodoAnterior.setSiguienteNodo(aux.getSiguienteNodo());
				if (nodoAnterior.getSiguienteNodo() == null) {
					// El nodo era el último en la cola
					nodoUltimo = nodoAnterior;
				}
			} else {
				// El nodo es el primero en la cola
				nodoPrimero = aux.getSiguienteNodo();
				if (nodoPrimero == null) {
					// El nodo era el único en la cola
					nodoUltimo = null;
				}
			}
		}

		// Encola nuevamente el nodo desencolado
		encolar(aux != null ? aux.getValorNodo() : null);

		return aux;
	}

	public void insertarEnPosicion(T dato, int posicion) {
		// Verificar si la posición es válida
		if (posicion < 0 || posicion > tamanio) {
			throw new IllegalArgumentException("Posición inválida");
		}

		// Desencolar hasta la posición deseada
		int i = 0;
		while (i < posicion) {
			T elementoDesencolado = desencolar();
			encolar(elementoDesencolado);
			i++;
		}

		// Encolar el nuevo dato en la posición deseada
		encolar(dato);

		// Desencolar y volver a encolar los elementos restantes
		i = posicion + 1;
		while (i < tamanio) {
			T elementoDesencolado = desencolar();
			encolar(elementoDesencolado);
			i++;
		}
	}


	/**
	 * Borra completamente la Cola
	 */
	public void borrarCola() {
		nodoPrimero = nodoUltimo = null;
		tamanio = 0;
	}

	/**
	 * @return the primero
	 */
	public NodoCola<T> getPrimero() {
		return nodoPrimero;
	}

	/**
	 * @return the ultimo
	 */
	public NodoCola<T> getUltimo() {
		return nodoUltimo;
	}

	/**
	 * @return the tamano
	 */
	public int getTamano() {
		return tamanio;
	}

	/**
	 * Verifica si la Cola es id�ntica a la actual
	 *
	 * @param cola Cola a comparar
	 * @return True si son iguales
	 */
	public boolean sonIdenticas(Cola<T> cola) {

		Cola<T> clon1 = clone();
		Cola<T> clon2 = cola.clone();

		if (clon1.getTamano() == clon2.getTamano()) {

			while (!clon1.estaVacia()) {
				if (!clon1.desencolar().equals(clon2.desencolar())) {
					return false;
				}
			}

		} else {
			return false;
		}

		return true;
	}

	/**
	 * Imprime una cola en consola
	 */
	public void imprimir() {
		NodoCola<T> aux = nodoPrimero;
		while (aux != null) {
			System.out.print(aux.getValorNodo() + "\t");
			aux = aux.getSiguienteNodo();
		}
		System.out.println();
	}

	@Override
	protected Cola<T> clone() {

		Cola<T> nueva = new Cola<>();
		NodoCola<T> aux = nodoPrimero;

		while (aux != null) {
			nueva.encolar(aux.getValorNodo());
			aux = aux.getSiguienteNodo();
		}

		return nueva;
	}

	/*public Nodo<T> buscarNodo(T tarea) {
		Nodo<T> aux = nodoPrimero;

		while(aux!=null) {
			if(aux.getValorNodo().equals(dato)) {
				return aux;
			}
			aux = aux.getSiguienteNodo();
		}

		return null;
	}*/


	@Override
	public String toString() {
		return "Cola{" +
				"nodoPrimero=" + nodoPrimero +
				", nodoUltimo=" + nodoUltimo +
				", tamanio=" + tamanio +
				'}';
	}
}



