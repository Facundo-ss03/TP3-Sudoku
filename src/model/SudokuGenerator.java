package model;

import java.util.Random;

import interfaces.ISudokuGenerator;

public class SudokuGenerator implements ISudokuGenerator {
	private final SudokuValidator validador = new SudokuValidator();
	private final Random random = new Random();
	
	@Override
	public void generarSudoku(int[][] tablero, boolean[][] prefijadas, int cantidadPrefijados) {
		limpiarTablero(tablero, prefijadas);
		generarSudokuCompleto(tablero);
		
		// CREAMOS UNA VARIABLE PARA SABER LA CANTIDAD DE CELDAS QUE DEBEMOS REMOVER
		int celdasARemover = 81 - cantidadPrefijados;
		
		// MIENTRAS QUE LA CANTIDAD DE CELDAS A REMOVER SEA MAYOR A 0:
		while (celdasARemover > 0) {
			// ELIGE UNA POSICION ALEATORIA
			int fila = random.nextInt(9);
			int columna = random.nextInt(9);
			
			// SI LA CELDA NO ESTÁ VACÍA, LA BORRA
			if (tablero[fila][columna] != 0) {
				tablero[fila][columna] = 0;
				prefijadas[fila][columna] = false;
				celdasARemover--;
			}
		}
		
		// 50% QUE NO SE PUEDA RESOLVER
		agregarChance(tablero, prefijadas, 0.5, cantidadPrefijados);
	}
	
	private void agregarChance(int[][] tablero, boolean[][] prefijadas, double chance, int cantidadPrefijados) {
		if (cantidadPrefijados > 0 && random.nextDouble() < chance) {
			int fila = random.nextInt(9);
			int columna1 = random.nextInt(9);
			int columna2 = random.nextInt(9);
					
			if (columna1 != columna2) {
				tablero[fila][columna1] = random.nextInt(9);
				tablero[fila][columna2] = random.nextInt(9);
				prefijadas[fila][columna1] = prefijadas[fila][columna2];
			}
		}
				
		// MARCA LAS CELDAS RESTANTES COMO PREFIJADAS
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (tablero[i][j] != 0) {
					prefijadas[i][j] = true;
				}
			}
		}
	}
	
	private boolean generarSudokuCompleto(int[][] tablero) {
		// RECORRE TODAS LAS CELDAS
		for (int fila = 0; fila < 9; fila++) {
			for (int col = 0; col < 9; col++) {
				// SI ENCUENTRA UNA CELDA VACÍA
				if (tablero[fila][col] == 0) {
					// GENERA NÚMEROS DEL 1 AL 9
					int[] numeros = generarNumerosAleatorios();
					// PRUEBA LOS NUMEROS EN ORDEN
					for (int numero : numeros) {
						if (validador.esValido(tablero, numero, fila, col)) {
							// COLOCA TEMPORTALMENTE EL NUMERO
							tablero[fila][col] = numero;
							// SE LLAMA RECURSIVAMENTE PARA LLENAR EL RESTO DEL TABLERO
							if (generarSudokuCompleto(tablero)) return true;
							// SI FALLA, DESHACE EL CAMBIO
							tablero[fila][col] = 0;
						}
					}
					// SI NO HAY NÚMERO, RETROCEDE
					return false;
				}
			}
		}
		// SI NO QUEDAN CELDAS VACÍAS, EL TABLERO COMPLETO ESTÁ RESUELTO
		return true;
	}
	
	private int[] generarNumerosAleatorios() {
		int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		Random random = new Random();
		// ALGORITMO DE FISHER-YATES:
		// AL MEZCLAR ALEATORIAMENTE LA SECUENCIA DE NÚMEROS, GARANTIZA QUE CADA PERMUTACIÓN TENGA LA MISMA PROBABILIDAD DE OCURRIR
		for (int i = 0; i < numeros.length; i++) {
			int j = random.nextInt(numeros.length);
			int temporal = numeros[i];
			numeros[i] = numeros[j];
			numeros[j] = temporal;
		}
		return numeros;
	}
	
	private void limpiarTablero(int[][] tablero, boolean[][] prefijadas) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				tablero[i][j] = 0;
				prefijadas[i][j] = false;
			}
		}
	}
}
