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

        // OPCION PARA CARGAR CON UN SUDOKU QUE ES VALIDO, ES PARA TESTEAR
//        int[][] sudokuEjemplo = {
//            {5,3,0,0,7,0,0,0,0},
//            {6,0,0,1,9,5,0,0,0},
//            {0,9,8,0,0,0,0,6,0},
//            {8,0,0,0,6,0,0,0,3},
//            {4,0,0,8,0,3,0,0,1},
//            {7,0,0,0,2,0,0,0,6},
//            {0,6,0,0,0,0,2,8,0},
//            {0,0,0,4,1,9,0,0,5},
//            {0,0,0,0,8,0,0,7,9}
//        };

//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                int val = sudokuEjemplo[i][j];
//                if (val != 0)
//                    model.setValor(val, i, j, true); // true = celda prefijada
//            }
//        }

        // ACTUALIZAR LA VISTA CON EL SUDOKU CARGADO
        controller.actualizarViewDesdeModel();

        // MOSTRAR VENTANA
        view.setVisible(true);

        // PARA GENERAR UNO ALEATORIO, DESCOMENTAR LO SIGUIENTE:
        
         model.generarSudoku(17);
         controller.actualizarViewDesdeModel();
    }
}
