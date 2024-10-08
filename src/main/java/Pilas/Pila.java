/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pilas;

public interface Pila<E> {
	
     public void push(E item) throws ExcepcionDePilaLlena;
     public E pop() throws ExcepcionDePilaVacia;
     public boolean isEmpty();
     public boolean isFull();
     public E top() throws ExcepcionDePilaVacia;
     public int size();
     
}

