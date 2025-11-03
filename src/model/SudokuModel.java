package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import interfaces.ISudokuModel;

public class SudokuModel implements ISudokuModel {
	private final int[][] tablero = new int[9][9];
	private final boolean[][] prefijadas = new boolean[9][9];
	
	private int limite = 10000;
	private boolean limiteAlcanzado = false;
	
	// GETTER DEL TABLERO PARA LA VISTA
	@Override
	public int getValor(int fila, int columna) {
		return tablero[fila][columna];
	}
	
	// SETTER
	@Override
	public void setValor(int valor, int fila, int columna, boolean esPrefijada) {
		tablero[fila][columna] = valor;
		prefijadas[fila][columna] = esPrefijada;
	}
	
	// LIMPIAR TABLERO
	@Override
	public void limpiarTablero() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				tablero[i][j] = 0;
				prefijadas[i][j] = false;
			}
		}
	}
	
	// FRENAR PROGRAMA
	
	
	// VERIFICA SI ES VALIDO COLOCAR UN NUMERO
	// ES DECIR, VERIFICA QUE NO SE REPITA EN UN 3x3 NI EN COLUMNAS NI FILAS.
	@Override
	public boolean esValido(int numero, int fila, int columna) {
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
	
	// RESOLVER CON BACKTRACKING
	@Override
	public boolean resolverBacktracking() {
		// RECORRE TODAS LAS FILAS Y COLUMNAS DEL TABLERO
		for (int fila = 0; fila < 9; fila++) {
			for (int columna = 0; columna < 9; columna++) {
				// SI UNA CELDA ESTA VACÍA (== 0)
				if (tablero[fila][columna] == 0) {
					// PRUEBA COLOCAR NUMEROS DEL 1 AL 9
					for (int numero = 1; numero <= 9; numero++) {
						// SI EL NUMERO CUMPLE LAS REGLAS DEL SUDOKU
						if (esValido(numero, fila, columna)) {
							tablero[fila][columna] = numero;	// LO COLOCA TEMPORALMENTE
							// EL METODO SE LLAMA RECURSIVAMENTE PARA INTENTAR RESOLVER EL RESTO DEL TABLERO
							if (resolverBacktracking()) {
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
	
	// VERIFICA SI HAY SOLUCION
	@Override
	public boolean haySolucion() {
		// CREA UNA COPIA DEL TABLERO
		int[][] tableroCopia = new int[9][9];
		// PEGA LOS VALORES DE "tablero" HACIA "tableroCopia"
		for (int i = 0; i < 9; i++) {
			System.arraycopy(tablero[i], 0, tableroCopia[i], 0, 9);
		}
		
		// CREA UN RESULTADO LLAMANDO A "resolverBacktracking"
		boolean resultado = resolverBacktracking();

		// RESTAURAR EL TABLERO ORIGINAL
		for (int i = 0; i < 9; i++) {
			System.arraycopy(tableroCopia[i], 0, tablero[i], 0, 9);
		}
		return resultado;
	}
	
	// GENERAR SUDOKU ALEATORIO
	@Override
	public void generarSudoku(int cantidadPrefijados) {
		limpiarTablero();
		generarSudokuCompleto();
		
		// CREAMOS UNA INSTANCIA RANDOM
		Random random = new Random();
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
		
		// AGREGAR UN 50% DE GENERAR UNO QUE NO SE PUEDA RESOLVER
		if (random.nextDouble() < 0.5) {
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
	
	// MÉTODO QUE GENERA UN SUDOKU CON UNA SOLUCIÓN COMPLETA VÁLIDA
	private boolean generarSudokuCompleto() {
		// RECORRE TODAS LAS CELDAS
		for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
            	// SI ENCUENTRA UNA CELDA VACÍA
                if (tablero[fila][col] == 0) {
                	// GENERA NÚMEROS DEL 1 AL 9
                    int[] numeros = generarNumerosAleatorios();
                    // PRUEBA LOS NUMEROS EN ORDEN
                    for (int numero : numeros) {
                        if (esValido(numero, fila, col)) {
                        	// COLOCA TEMPORTALMENTE EL NUMERO
                            tablero[fila][col] = numero;
                            // SE LLAMA RECURSIVAMENTE PARA LLENAR EL RESTO DEL TABLERO
                            if (generarSudokuCompleto()) return true;
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
	
	// DEVUELVE LOS NÚMEROS DEL 1 AL 9 EN ORDEN ALEATORIO
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
	
	// VERIFICA SI EL TABLERO ES VALIDO
	@Override
	public boolean tableroValido() {
		for (int fila = 0; fila < 9; fila++) {
			for (int columna = 0; columna < 9; columna++) {
				int valor = tablero[fila][columna];
				if (valor != 0) {
					tablero[fila][columna] = 0; // EVITA PREGUNTAR POR EL MISMO VALOR
					if (!esValido(valor, fila, columna)) {
						tablero[fila][columna] = valor;
						return false;
					}
					tablero[fila][columna] = valor;
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean esPrefijada(int fila, int col) {
        return prefijadas[fila][col];
    }
	

		
	private boolean esValidoEn(int[][] tablero, int valor, int fila, int columna) {
		for (int j = 0; j < 9; j++) {
			if (tablero[fila][j] == valor) {
				return false;
			}
		}
		for (int i = 0; i < 9; i++) {
			if (tablero[i][columna] == valor) {
				return false;
			}
		}
		
		int inicioFila = (fila / 3) * 3;
		int inicioColumna = (columna / 3) * 3;
		for (int i = inicioFila; i < inicioFila + 3; i++) {
	        for (int j = inicioColumna; j < inicioColumna + 3; j++) {
	            if (tablero[i][j] == valor) return false;
	        }
	    }
	    return true;
	}
	
	// MÉTODOS PARA VER CUANTAS SOLUCIONES HAY EN UN SUDOKU
	//--------------------ELIMINARLAS----------------------
	public List<int[][]> getTodasLasSolucionesValidas() {
	    List<int[][]> soluciones = new ArrayList<>();
	    int[][] copia = new int[9][9];
	    for (int i = 0; i < 9; i++) System.arraycopy(tablero[i], 0, copia[i], 0, 9);

	    resolverGuardando(copia, soluciones);
	    return soluciones;
	}
	private void resolverGuardando(int[][] tablero, List<int[][]> soluciones) {
	    if (soluciones.size() >= limite) return; // cortamos si llegamos al límite

	    for (int fila = 0; fila < 9; fila++) {
	        for (int col = 0; col < 9; col++) {
	            if (tablero[fila][col] == 0) {
	                for (int num = 1; num <= 9; num++) {
	                    if (esValidoEn(tablero, num, fila, col)) {
	                        tablero[fila][col] = num;
	                        resolverGuardando(tablero, soluciones);
	                        tablero[fila][col] = 0;
	                        if (soluciones.size() >= limite) return; // cortamos temprano
	                    }
	                }
	                return;
	            }
	        }
	    }
	    // Si llegamos acá, tablero completo
	    int[][] copia = new int[9][9];
	    for (int i = 0; i < 9; i++)
	        System.arraycopy(tablero[i], 0, copia[i], 0, 9);
	    soluciones.add(copia);
	}




}