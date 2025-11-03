package interfaces;

public interface ISudokuModel {
	int getValor(int fila, int columna);
	void setValor(int valor, int fila, int columna, boolean esPrefijada);
	void limpiarTablero();
	boolean esValido(int numero, int fila, int columna);
	boolean resolverBacktracking();
	boolean haySolucion();
	void generarSudoku(int cantidadPrefijados);
	boolean esPrefijada(int fila, int columna);
	boolean tableroValido();
}
