package linkedlist;

import java.util.Iterator;


public class LinkedList {

    public static void main(String[] args) {
        
ListaEnlazada<Alumnito> lista = new ListaEnlazada<>();

        Alumnito a1 = new Alumnito("alumno1", 20, "001", 9.0);
        Alumnito a2 = new Alumnito("alumno2", 21, "002", 8.5);
        Alumnito a3 = new Alumnito("alumno3", 19, "003", 9.5);
        Alumnito a4 = new Alumnito("alumno4", 22, "004", 8.0);

        lista.append(a1);
        lista.append(a2);
        lista.append(a3);

        System.out.println("Lista original:");
        System.out.println(lista);

        lista.insert(a4, 1);

        System.out.println("\nDespues de insertar a alumno4:");
        System.out.println(lista);

        System.out.println("\nRecorrer hacia adelante:");

        lista.irInicio();

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.siguiente());
        }

        System.out.println("\nIr al inicio y retroceder:");

        lista.irInicio();
        lista.previous();

        System.out.println("Anterior al primero: " + lista.actual());

        System.out.println("\nRecorrer hacia atras:");

        lista.irFinal();

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.actual());
            lista.previous();
        }

        System.out.println("\nUsar for-each:");

        for (Alumnito alumno : lista) {
            System.out.println(alumno);
        }

        System.out.println("\nEliminar el elemento en indice 1:");
        System.out.println("Eliminado: " + lista.remove(1));
        System.out.println(lista);

        System.out.println("\nCantidad de elementos:");
        System.out.println(lista.size());
        
        
    }
    
}
