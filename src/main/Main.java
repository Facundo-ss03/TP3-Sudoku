package main;

import model.SudokuModel;
import view.SudokuView;
import controller.SudokuController;

public class Main {
    public static void main(String[] args) {
        // CREAR INSTANCIAS DE MODEL, VIEW Y CONTROLLER
        SudokuModel model = new SudokuModel();
        SudokuView view = new SudokuView();
        SudokuController controller = new SudokuController(model, view);


        // ACTUALIZAR LA VISTA CON EL SUDOKU CARGADO
        controller.actualizarViewDesdeModel();

        // MOSTRAR VENTANA
        view.setVisible(true);

        // PARA GENERAR UNO ALEATORIO, DESCOMENTAR LO SIGUIENTE:
        
         model.generarSudoku(17);
         controller.actualizarViewDesdeModel();
    }
}
