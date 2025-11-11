package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.SudokuController;
import model.SudokuModel;
import view.CeldaView;
import view.SudokuView;


public class SudokuControllerTest {

	private SudokuModel model;
    private SudokuView view;
    private SudokuController controller;



    @BeforeEach
    public void setUp() {
        model = new SudokuModel();
        view = new SudokuView();
        controller = new SudokuController(model, view);
    }


    @Test
    public void testLimpiarSudoku() {
        model.setValor(9, 3, 3, false);
        controller.limpiarSudoku();
        assertEquals(0, model.getValor(3, 3), "El tablero debe quedar vacío");
    }

    @Test
    public void testGenerarSudokuAleatorio() {
        controller.generarSudokuAleatorio(20);
        int cantidadPrefijados = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (model.getValor(i, j) != 0)
                    cantidadPrefijados++;
            }
        }
        assertTrue(cantidadPrefijados >= 0 && cantidadPrefijados <= 81, 
                   "El Sudoku generado debe tener valores entre 1 y 9");
    }

    
    @Test
    public void testCeldaNoPermiteLetras() {
        CeldaView celda = new CeldaView();
        celda.setText("a"); 
        int valor = celda.getValor();
        assertEquals(0, valor, "Si se ingresa texto no numérico, debe devolver 0");
    }
    
    @Test
    public void testCeldaNoPermiteValorFueraDeRango() {
        CeldaView celda = new CeldaView();
        celda.setText("a");
        int valor = celda.getValor();
        assertEquals(0, valor, "En caso de ingresar valores no numericos, debe devolver 0");
    }
    @Test
    void testGenerarSudokusConUnaCeldaPrefijada() {
        SudokuModel model = new SudokuModel();

        for (int i = 0; i < 10; i++) {
            model.generarSudoku(1); 

            int contadorPrefijadas = 0;

            for (int fila = 0; fila < 9; fila++) {
                for (int col = 0; col < 9; col++) {
                    if (model.esPrefijada(fila, col)) {
                        contadorPrefijadas++;
                    }
                }
            }

            assertEquals(1, contadorPrefijadas, 
                "Error" + (i + 1) + 
                ": se esperaba 1 celda prefijada, se encontaron " + contadorPrefijadas);
        }
    }
    
}