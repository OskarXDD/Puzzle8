package Puzzle;

import java.util.Scanner;


public class Jugar {

    public static void run() {
        Puzzle8 puzzle = new Puzzle8();
        Scanner scanner = new Scanner(System.in);

        boolean juegoTerminado = false;
        int direccion;

         while (!juegoTerminado) {
            System.out.println("Estado actual del tablero:");
            imprimirTablero(puzzle.getEstadoActual());

            System.out.println("Mueve la ficha (0 = arriba, 1 = abajo, 2 = "
                    + "izquierda, 3 = derecha, 4 = deshacer, 5 = rehacer, 6 = salir): ");
            direccion = scanner.nextInt();

            switch (direccion) {
                case 0, 1, 2, 3 -> {
                    boolean movimientoExitoso = puzzle.mover(direccion);
                    if (movimientoExitoso) {
                        System.out.println("Movimiento exitoso.");
                    } else {
                        System.out.println("Movimiento invalido, fuera de los limites");
                    }
                }
                case 4 -> {
                    puzzle.deshacerMovimiento();
                    System.out.println("Movimiento deshecho");
                }
                case 5 -> {
                    puzzle.rehacerMovimiento();
                    System.out.println("Movimiento rehecho");
                }
                case 6 -> {
                    juegoTerminado = true;
                    System.out.println("No pudiste?");
                }
                default -> System.out.println("Opción inválida");
            }
            if (puzzle.esEstadoFinal()) {
                System.out.println("Felicidades! Has resuelto el puzzle");
                imprimirTablero(puzzle.getEstadoActual());
                juegoTerminado = true;
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        run();
    }

    public static void imprimirTablero(int[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
