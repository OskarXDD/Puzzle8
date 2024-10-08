package Puzzle;

public class Puzzle8 {
    private Movimiento movimiento;

    public Puzzle8() {
        int[][] estadoInicial = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 0, 8}
        };
        movimiento = new Movimiento(estadoInicial, new int[] {2, 1}); 
    }
    
     public boolean esEstadoFinal() {
        int[][] estadoFinal = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        };
        return movimiento.esEstadoFinal(estadoFinal);
    }

    public int[][] getEstadoActual() {
        return movimiento.getEstadoActual();
    }

    public boolean mover(int direccion) {
        return movimiento.mover(direccion);
    }

    public void deshacerMovimiento() {
        movimiento.deshacerMovimiento();
    }

    public void rehacerMovimiento() {
        movimiento.rehacerMovimiento();
    }
}
