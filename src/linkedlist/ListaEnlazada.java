package linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ListaEnlazada<T> implements IList<T>, Iterable<T> {

    protected NodoSimple<T> inicio;
    protected NodoSimple<T> fin;
    protected NodoSimple<T> actual;
    protected int nElementos;

    private class NodoSimple<E> {
        private E dato;
        private NodoSimple<E> sig;
        private NodoSimple<E> ant;

        public NodoSimple(E dato) {
            this.dato = dato;
        }
    }

    public ListaEnlazada() {
        inicio = null;
        fin = null;
        actual = null;
        nElementos = 0;
    }

    @Override
    public void append(T elemento) {
        NodoSimple<T> nuevo = new NodoSimple<>(elemento);

        if (empty()) {
            inicio = nuevo;
            fin = nuevo;
            nuevo.sig = nuevo;
            nuevo.ant = nuevo;
        } else {
            nuevo.ant = fin;
            nuevo.sig = inicio;
            fin.sig = nuevo;
            inicio.ant = nuevo;
            fin = nuevo;
        }

        nElementos++;
    }

    @Override
    public void insert(T elemento, int index) {
        if (index < 0 || index > nElementos) {
            throw new ListException("Indice fuera de limites");
        }

        if (index == nElementos) {
            append(elemento);
            return;
        }

        NodoSimple<T> nuevo = new NodoSimple<>(elemento);

        if (index == 0) {
            nuevo.sig = inicio;
            nuevo.ant = fin;
            inicio.ant = nuevo;
            fin.sig = nuevo;
            inicio = nuevo;
        } else {
            NodoSimple<T> nodo = obtenerNodo(index);

            nuevo.sig = nodo;
            nuevo.ant = nodo.ant;
            nodo.ant.sig = nuevo;
            nodo.ant = nuevo;
        }

        nElementos++;
    }

    @Override
    public T remove(int index) {
        if (empty()) {
            throw new ListException("Lista vacia");
        }

        if (index < 0 || index >= nElementos) {
            throw new ListException("Indice fuera de limites");
        }

        NodoSimple<T> nodo = obtenerNodo(index);
        T dato = nodo.dato;

        if (nElementos == 1) {
            inicio = null;
            fin = null;
            actual = null;
        } else {
            nodo.ant.sig = nodo.sig;
            nodo.sig.ant = nodo.ant;

            if (nodo == inicio) {
                inicio = nodo.sig;
            }

            if (nodo == fin) {
                fin = nodo.ant;
            }

            if (actual == nodo) {
                actual = nodo.sig;
            }
        }

        nElementos--;
        return dato;
    }

    @Override
    public boolean removeObj(T elemento) {
        if (empty()) {
            return false;
        }

        NodoSimple<T> nodo = inicio;

        for (int i = 0; i < nElementos; i++) {
            if (Objects.equals(nodo.dato, elemento)) {
                remove(i);
                return true;
            }
            nodo = nodo.sig;
        }

        return false;
    }

    @Override
    public int indexOf(T elemento) {
        NodoSimple<T> nodo = inicio;

        for (int i = 0; i < nElementos; i++) {
            if (Objects.equals(nodo.dato, elemento)) {
                return i;
            }
            nodo = nodo.sig;
        }

        return -1;
    }

    @Override
    public T get(int index) {
        return obtenerNodo(index).dato;
    }

    @Override
    public void set(T elemento, int index) {
        obtenerNodo(index).dato = elemento;
    }

    private NodoSimple<T> obtenerNodo(int index) {
        if (index < 0 || index >= nElementos) {
            throw new ListException("Indice fuera de limites");
        }

        NodoSimple<T> nodo;

        if (index < nElementos / 2) {
            nodo = inicio;

            for (int i = 0; i < index; i++) {
                nodo = nodo.sig;
            }
        } else {
            nodo = fin;

            for (int i = nElementos - 1; i > index; i--) {
                nodo = nodo.ant;
            }
        }

        return nodo;
    }

    public void invertir() {
        if (nElementos < 2) {
            return;
        }

        NodoSimple<T> nodo = inicio;

        for (int i = 0; i < nElementos; i++) {
            NodoSimple<T> temporal = nodo.sig;
            nodo.sig = nodo.ant;
            nodo.ant = temporal;
            nodo = temporal;
        }

        NodoSimple<T> temporal = inicio;
        inicio = fin;
        fin = temporal;
    }

    public void concatenar(ListaEnlazada<T> otraLista) {
        if (otraLista == this) {
            throw new ListException("No puedes concatenar la lista consigo misma");
        }

        NodoSimple<T> nodo = otraLista.inicio;

        for (int i = 0; i < otraLista.nElementos; i++) {
            append(nodo.dato);
            nodo = nodo.sig;
        }
    }

    @Override
    public void clear() {
        inicio = null;
        fin = null;
        actual = null;
        nElementos = 0;
    }

    @Override
    public boolean empty() {
        return nElementos == 0;
    }

    @Override
    public int size() {
        return nElementos;
    }

    @Override
    public void irInicio() {
        actual = inicio;
    }

    @Override
    public void irFinal() {
        actual = fin;
    }

    public T siguiente() {
        if (actual == null) {
            throw new ListException("Lista vacia");
        }

        T dato = actual.dato;
        actual = actual.sig;
        return dato;
    }

    @Override
    public void previous() {
        if (actual == null) {
            throw new ListException("Lista vacia");
        }

        actual = actual.ant;
    }

    public T actual() {
        if (actual == null) {
            throw new ListException("Lista vacia");
        }

        return actual.dato;
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder("[");
        NodoSimple<T> nodo = inicio;

        for (int i = 0; i < nElementos; i++) {
            texto.append(nodo.dato);

            if (i < nElementos - 1) {
                texto.append(", ");
            }

            nodo = nodo.sig;
        }

        texto.append("]");
        return texto.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoSimple<T> nodo = inicio;
            private int recorridos = 0;

            @Override
            public boolean hasNext() {
                return recorridos < nElementos;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                T dato = nodo.dato;
                nodo = nodo.sig;
                recorridos++;
                return dato;
            }
        };
    }
}