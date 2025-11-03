package controller;

import java.util.List;

import javax.swing.JOptionPane;

import interfaces.ISudokuController;
import model.SudokuModel;
import view.SudokuView;

public class SudokuController implements ISudokuController {
	private final SudokuModel model;
	private final SudokuView view;
	
	// CONSTRUCTOR DEL CONTROLLER
	public SudokuController(SudokuModel model, SudokuView view) {
		this.model = model;
		this.view = view;
		
		view.setController(this);
	}
	
	// METODO PARA RESOLVER EL SUDOKU
	
	@Override
	public void resolverSudoku() {
	    actualizarModelDesdeView();

	    // Verifica si el tablero es válido
	    if (!model.tableroValido()) {
	        JOptionPane.showMessageDialog(view,
	            "El Sudoku ingresado tiene valores repetidos.\nRevisá filas, columnas o subcuadrículas.",
	            "Error",
	            JOptionPane.ERROR_MESSAGE
	        );
	        return;
	    }

	    // Obtenemos todas las soluciones válidas
	    List<int[][]> soluciones = model.getTodasLasSolucionesValidas();

	    if (soluciones.isEmpty()) {
	        JOptionPane.showMessageDialog(view, "El Sudoku no tiene solución.");
	        return;
	    } else if (soluciones.size() > 1) {
	        JOptionPane.showMessageDialog(view, "El Sudoku tiene más de una solución posible.");
	        // Abrimos ventana con todas las soluciones
	        int[][] primerSolucion = soluciones.get(0);
		    for (int i = 0; i < 9; i++) {
		        for (int j = 0; j < 9; j++) {
		            model.setValor(primerSolucion[i][j], i, j, model.esPrefijada(i, j));
		        }
		    }	  
		    actualizarViewDesdeModel();
	        view.mostrarVentanaSoluciones(soluciones);
	        return;
	    }

	    // Si solo hay una solución, aplicamos al model y actualizamos la vista
	    int[][] primerSolucion = soluciones.get(0);
	    for (int i = 0; i < 9; i++) {
	        for (int j = 0; j < 9; j++) {
	            model.setValor(primerSolucion[i][j], i, j, model.esPrefijada(i, j));
	        }
	    }
	    actualizarViewDesdeModel();
	    JOptionPane.showMessageDialog(view, "El sudoku resuelto solo tiene una única solución.");
	}

	// GENERA UN SUDOKU ALEATORIO
	@Override
	public void generarSudokuAleatorio(int cantidadPrefijados) {
		// LLAMA A LA FUNCION "generarSudoku" DEL MODEL
        model.generarSudoku(cantidadPrefijados);
        // ACTUALIZA LA VISTA DESDE EL MODEL
        actualizarViewDesdeModel();
        JOptionPane.showMessageDialog(view, "Se generó un Sudoku aleatorio con " + cantidadPrefijados + " valores prefijados.");
        if (!model.tableroValido()) {
        	view.mostrarMensaje("Imposible resolver el sudoku, es inválido.");
        }
        
    }
	
	@Override
	public void limpiarSudoku() {
		// LIMPIA EL TABLERO CON LA FUNCION "limpiarTablero" DEL MODEL
        model.limpiarTablero();
        actualizarViewDesdeModel();
    }
	
	// METODO PARA ACTUALIZAR EL MODEL DESDE LA VISTA
	/*
	 ESTE MÉTODO SE ENCARGA DE SINCRONIZAR LOS DATOS QUE EL USUARIO VE O EDITA
	 EN LA INTERFAZ (VIEW) HACIA EL MODEL DEL PROGRAMA.
	 
	 - TOMA VALORES QUE EL USUARIO ESCRIBIÓ EN LAS CELDAS DE LA INTERFAZ
	 - LOS COPIA DENTRO DEL OBJETO "model"
	 */
	@Override
	public void actualizarModelDesdeView() {
        for (int fila = 0; fila < 9; fila++) {									// RECORRE TODAS LAS FILAS
            for (int col = 0; col < 9; col++) {									// RECORRE TODAS LAS COLUMNAS
                int valor = view.getValorEnCelda(fila, col);					// OBTIENE EL VALOR CONTENIDO EN CADA CELDA
                model.setValor(valor, fila, col, model.esPrefijada(fila, col));	// ACTUALIZA EL MODEL CON ESE VALOR
            }
        }
    }
	
	// METODO PARA ACTUALIZAR LA VISTA DESDE EL MODEL
	/*
	 ESTE MÉTODO HACE LO CONTRARIO A LO DEL ANTERIOR MÉTODO
	 
	 - TOMA LOS VALORES ACTUALES ALMACENADOS EN EL MODEL
	 - LOS MUESTRA EN LOS CAMPOS DE TEXTO DE LA INTERFAZ (VIEW)
	 */
	@Override
	public void actualizarViewDesdeModel() {
        for (int fila = 0; fila < 9; fila++) {									// RECORRE TODAS LAS FILAS
            for (int col = 0; col < 9; col++) {									// RECORRE TODAS LAS COLUMNAS
                int valor = model.getValor(fila, col);							// OBTIENE EL VALOR CONTENIDO EN CADA CELDA
                boolean prefijada = model.esPrefijada(fila, col);				// VERIFICA SI ES UNA CELDA PREFIJADA
                view.setValorEnCelda(valor, fila, col, prefijada);				// SETEA EL VALOR, DICIENDO SI ES PREFIJADA O NO
            }
        }
    }
}
