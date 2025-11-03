package interfaces;

public interface ISudokuView {
	int getValorEnCelda(int fila, int columna);
	void setValorEnCelda(int valor, int fila, int columna, boolean esPrefijada);
	void mostrarMensaje(String mensaje);
}
