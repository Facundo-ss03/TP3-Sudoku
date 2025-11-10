package model;

import java.util.ArrayList;
import java.util.List;

import interfaces.ISudokuSolver;

public class SudokuSolver implements ISudokuSolver {
	private final SudokuValidator validador = new SudokuValidator();
	private static final int LIMITE = 10000;
	
	@Override
	public boolean resolverBacktracking(int[][] tablero) {
	// RECORRE TODAS LAS FILAS Y COLUMNAS DEL TABLERO
		for (int fila = 0; fila < 9; fila++) {
			for (int columna = 0; columna < 9; columna++) {
				// SI UNA CELDA ESTA VACÍA
				if (tablero[fila][columna] == 0) {
					// PRUEBA COLOCAR NUMEROS DEL 1 AL 9
					for (int numero = 1; numero <= 9; numero++) {
						// SI EL NUMERO CUMPLE LAS REGLAS DEL SUDOKU
						if (validador.esValido(tablero, numero, fila, columna)) {
							tablero[fila][columna] = numero;	// LO COLOCA TEMPORALMENTE
							// EL METODO SE LLAMA RECURSIVAMENTE PARA INTENTAR RESOLVER EL RESTO DEL TABLERO
							if (resolverBacktracking(tablero)) {
								return true; // SI LOGRA RESOLVERSE, TERMINA
							}
							// SI NO PUEDE RESOLVERSE, VUELVE HACIA ATRÁS
							tablero[fila][columna] = 0;
						}	
					}
					// SI NINGUN NUMERO ES VALIDO, RETROCEDE AL PASO ANTERIOR
					return false;
				}
			}
		}
		// SI NO QUEDAN CELDAS VACIAS, EL SUDOKU ESTA RESUELTO
		return true;
	}
	
	@Override
	public List<int[][]> getTodasLasSoluciones(int[][] tablero) {
		List<int[][]> soluciones = new ArrayList<>();
	    resolverGuardando(tablero, soluciones);
	    return soluciones;
	}
	
	private void resolverGuardando(int[][] tablero, List<int[][]> soluciones) {
		if (soluciones.size() >= LIMITE) return; // CORTAMSO SI LLEGAMOS AL LIMITE

	    for (int fila = 0; fila < 9; fila++) {
	        for (int col = 0; col < 9; col++) {
	            if (tablero[fila][col] == 0) {
	                for (int numero = 1; numero <= 9; numero++) {
	                    if (validador.esValido(tablero, numero, fila, col)) {
	                        tablero[fila][col] = numero;
	                        resolverGuardando(tablero, soluciones);
	                        tablero[fila][col] = 0;
	                        if (soluciones.size() >= LIMITE) return; // CORTA ANTES
	                    }
	                }
	                return;
	            }
	        }
	    }
	    // SI SE LLEGA HASTA ACÁ, TABLERO ESTA COMPLETO
	    int[][] copia = new int[9][9];
	    for (int i = 0; i < 9; i++) {
	        System.arraycopy(tablero[i], 0, copia[i], 0, 9);
	    }
	    soluciones.add(copia);
	}
}
