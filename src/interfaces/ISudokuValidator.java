package interfaces;

public interface ISudokuValidator {
	boolean esValido(int[][] tablero, int numero, int fila, int columna);
	boolean tableroValido(int[][] tablero);
}
