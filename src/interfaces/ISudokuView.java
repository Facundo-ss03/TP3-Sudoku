package interfaces;

import java.util.List;

public interface ISudokuView {
	int getValorEnCelda(int fila, int columna);
	void setValorEnCelda(int valor, int fila, int columna, boolean esPrefijada);
	void mostrarMensaje(String mensaje);
	void mostrarVentanaSoluciones(List<int[][]> soluciones);
}
