package model;

import interfaces.ISudokuValidator;

public class SudokuValidator implements ISudokuValidator {
	
	@Override
	public boolean esValido(int[][] tablero, int numero, int fila, int columna) {
		// VERIFICA LAS FILAS
		for (int j = 0; j < 9; j++) {
			if (tablero[fila][j] == numero) return false;
		}
		// VERIFICA LAS COLUMNAS
		for (int i = 0; i < 9; i++) {
			if (tablero[i][columna] == numero) return false;
		}
		// CALCULA EL INICIO DE UN CUADRO 3x3
		int inicioFila = (fila / 3) * 3;
		int inicioColumna = (columna / 3) * 3;
		
		// RECORRE EL BLOQUE 3x3
		for (int i = inicioFila; i < inicioFila + 3; i++) {
            for (int j = inicioColumna; j < inicioColumna + 3; j++) {
                if (tablero[i][j] == numero) return false;
            }
        }
		// SI PASA TODAS ESTAS COMPROBACIONES, ES VÁLIDO PONER EL NÚMERO
        return true;
	}
	
	// VERIFICA SI UN TABLERO ES VÁLIDO
	@Override
	public boolean tableroValido(int[][] tablero) {
		for (int fila = 0; fila < 9; fila++) {
			for (int columna = 0; columna < 9; columna++) {
				int valor = tablero[fila][columna];
				if (valor != 0) {
					tablero[fila][columna] = 0; // EVITA PREGUNTAR POR EL MISMO VALOR
					if (!esValido(tablero, valor, fila, columna)) {
						tablero[fila][columna] = valor;
						return false;
					}
					tablero[fila][columna] = valor;
				}
			}
		}
		return true;
	}
}
