package model;
import java.util.List;

import interfaces.ISudokuModel;

public class SudokuModel implements ISudokuModel {
	private final SudokuGenerator generador = new SudokuGenerator();
    private final SudokuSolver resolvente = new SudokuSolver();
    private final SudokuValidator validador = new SudokuValidator();
	
    private final int[][] tablero = new int[9][9];
    private final boolean[][] prefijadas = new boolean[9][9];

    @Override
    public int getValor(int fila, int columna) { return tablero[fila][columna]; }

    @Override
    public void setValor(int valor, int fila, int columna, boolean esPrefijada) {
        tablero[fila][columna] = valor;
        prefijadas[fila][columna] = esPrefijada;
    }

    @Override
    public void limpiarTablero() { generador.generarSudoku(tablero, prefijadas, 0); }

    @Override
    public boolean esValido(int numero, int fila, int columna) {
        return validador.esValido(tablero, numero, fila, columna);
    }

    @Override
    public boolean resolverBacktracking() { return resolvente.resolverBacktracking(tablero); }

    @Override
    public void generarSudoku(int cantidadPrefijados) {
        generador.generarSudoku(tablero, prefijadas, cantidadPrefijados);
    }

    @Override
    public boolean tableroValido() { return validador.tableroValido(tablero); }

    @Override
    public boolean esPrefijada(int fila, int col) { return prefijadas[fila][col]; }

    @Override
    public List<int[][]> getTodasLasSolucionesValidas() {
        int[][] copia = new int[9][9];
        for (int i = 0; i < 9; i++) System.arraycopy(tablero[i], 0, copia[i], 0, 9);
        return resolvente.getTodasLasSoluciones(copia);
    }
}