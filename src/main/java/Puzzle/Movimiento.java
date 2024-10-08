package Puzzle;

import Pilas.ExcepcionDePilaLlena;
import Pilas.ExcepcionDePilaVacia;
import Pilas.PilaDinamica;

public class Movimiento {
    private int[][] tablero;
    private int[] posicionVacia;  
    private PilaDinamica<int[][]> pilaUndo;
    private PilaDinamica<int[][]> pilaRedo;
    private PilaDinamica<int[]> pilaPosicionUndo; 
    private PilaDinamica<int[]> pilaPosicionRedo;  

    public Movimiento(int[][] estadoInicial, int[] posicionInicialVacia) {
        this.tablero = copiarTablero(estadoInicial);
        this.posicionVacia = posicionInicialVacia.clone();
        this.pilaUndo = new PilaDinamica<>();
        this.pilaRedo = new PilaDinamica<>();
        this.pilaPosicionUndo = new PilaDinamica<>();
        this.pilaPosicionRedo = new PilaDinamica<>();
    }

    public int[][] getEstadoActual() {
        return copiarTablero(tablero);
    }

    public boolean esEstadoFinal(int[][] estadoFinal) {
        return java.util.Arrays.deepEquals(tablero, estadoFinal);
    }

    public boolean mover(int direccion) {
        int filaVacia = posicionVacia[0];
        int columnaVacia = posicionVacia[1];
        int nuevaFila = filaVacia;
        int nuevaColumna = columnaVacia;

        switch (direccion) {
            case 0: 
                nuevaFila = filaVacia - 1;
                break;
            case 1: 
                nuevaFila = filaVacia + 1;
                break;
            case 2: 
                nuevaColumna = columnaVacia - 1;
                break;
            case 3:
                nuevaColumna = columnaVacia + 1;
                break;
            default:
                return false; 
        }
        
        if (nuevaFila < 0 || nuevaFila >= 3 || nuevaColumna < 0 || nuevaColumna >= 3) {
            return false; 
        } try {
            pilaUndo.push(copiarTablero(tablero));
            pilaPosicionUndo.push(posicionVacia.clone());
        } catch (ExcepcionDePilaLlena e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

        tablero[filaVacia][columnaVacia] = tablero[nuevaFila][nuevaColumna];
        tablero[nuevaFila][nuevaColumna] = 0;
        posicionVacia[0] = nuevaFila;
        posicionVacia[1] = nuevaColumna;
        pilaRedo.clear(); 
        pilaPosicionRedo.clear();

        return true; 
    }

    public void deshacerMovimiento() {
        try {
            if (!pilaUndo.isEmpty()) {
                pilaRedo.push(copiarTablero(tablero));
                pilaPosicionRedo.push(posicionVacia.clone());

                tablero = pilaUndo.pop();
                posicionVacia = pilaPosicionUndo.pop();
            }
        } catch (ExcepcionDePilaVacia e) {
            System.out.println("No hay movimientos para deshacer");
        } catch (ExcepcionDePilaLlena e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void rehacerMovimiento() {
        try {
            if (!pilaRedo.isEmpty()) {
                pilaUndo.push(copiarTablero(tablero));
                pilaPosicionUndo.push(posicionVacia.clone());

                tablero = pilaRedo.pop();
                posicionVacia = pilaPosicionRedo.pop();
            }
        } catch (ExcepcionDePilaVacia e) {
            System.out.println("No hay movimientos para rehacer");
        } catch (ExcepcionDePilaLlena e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static int[][] copiarTablero(int[][] original) {
        int[][] copia = new int[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copia[i], 0, original[i].length);
        }
        return copia;
    }
}
