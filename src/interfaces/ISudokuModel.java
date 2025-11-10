package interfaces;

import java.util.List;

public interface ISudokuModel {
	int getValor(int fila, int columna);
	void setValor(int valor, int fila, int columna, boolean esPrefijada);
	void limpiarTablero();
	boolean esValido(int numero, int fila, int columna);
	boolean resolverBacktracking();
	void generarSudoku(int cantidadPrefijados);
	boolean esPrefijada(int fila, int columna);
	boolean tableroValido();
	List<int[][]> getTodasLasSolucionesValidas();
}
