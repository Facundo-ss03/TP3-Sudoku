package interfaces;

public interface ISudokuController {
	void resolverSudoku();
	void generarSudokuAleatorio(int cantidadPrefijados);
	void limpiarSudoku();
	void actualizarModelDesdeView();
	void actualizarViewDesdeModel();
}
