package test;

import model.SudokuModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SudokuModelTest {

	private SudokuModel model;
	private int[][] tableroEj = {
			{0,0,5, 3,0,0, 0,0,0},
			{8,0,0, 0,0,0, 0,2,0},
			{0,7,0, 0,1,0, 5,0,0},
			{4,0,0, 0,0,5, 3,0,0},
			{0,1,0, 0,7,0, 0,0,6},
			{0,0,3, 2,0,0, 0,8,0},
			{0,6,0, 5,0,0, 0,0,9},
			{0,0,4, 0,0,0, 0,3,0},
			{0,0,0, 0,0,9, 7,0,0}
	};

	@BeforeEach
	void setUp() {
		model = new SudokuModel();
	}

	@Test
	void testSetYGetValor() {
		model.setValor(5, 0, 0, true);
		assertEquals(5, model.getValor(0, 0));
		assertTrue(model.esPrefijada(0, 0));
	}

	@Test
	void testLimpiarTablero() {
		model.setValor(5, 0, 0, true);
		model.limpiarTablero();
		assertEquals(0, model.getValor(0, 0));
		assertFalse(model.esPrefijada(0, 0));
	}

	@Test
	void testFilaDuplicada() {
		model.setValor(5, 0, 0, true);
		assertFalse(model.esValido(5, 0, 1));
	}

	@Test
	void testColumnaDuplicada() {
		model.setValor(7, 0, 0, true);
		assertFalse(model.esValido(7, 1, 0));
	}

	@Test
	void testSubcuadroDuplicado() {
		model.setValor(3, 0, 0, true);
		assertFalse(model.esValido(3, 1, 1));
	}

	@Test
	void testNumeroNuevo() {
		model.setValor(3, 0, 0, true);
		assertTrue(model.esValido(4, 0, 1));
	}



	@Test
	void testTableroInvalido() {
		model.setValor(5, 0, 0, true);
		model.setValor(5, 0, 1, true);
		assertFalse(model.tableroValido());
	}



	@Test
	void testGenerarSudokuConPrefijados() {
		model.generarSudoku(25);
		int count = 0;
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
				if (model.getValor(i,j) != 0) count++;

		assertTrue(count <= 81 && count >= 25,
				"Genera la cantidad pedida de valores prefijados");
	}

	@Test
	void testTableroValido1() {
		model.setValor(5, 0, 0, true);
		model.setValor(3, 1, 1, true);
		assertTrue(model.tableroValido());
	}

	@Test
	void testGetTodasLasSolucionesValidasVerificacionDeLimite() {
		var soluciones = model.getTodasLasSolucionesValidas();
		assertTrue(soluciones.size() <= 10000);
	}


	@Test
	void testCantidadSolucionesParaSudokuConMuchasSoluciones() {
		int[][] tableroVacio = new int[9][9];
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
				model.setValor(tableroVacio[i][j], i, j, false);
		var soluciones = model.getTodasLasSolucionesValidas();
		assertTrue(soluciones.size() > 1, "Debe encontrar más de una solución");
	}

	@Test
	void testResolverBacktrackingDevuelveTrueParaSudokuSolucionable() {
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
				model.setValor(tableroEj[i][j], i, j, tableroEj[i][j]!=0);
		assertTrue(model.resolverBacktracking());
	}

	@Test
	void testGenerarSudokuConTableroVacio() {
		model.generarSudoku(0);
		int suma = 0;
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
				suma += model.getValor(i, j);
		assertEquals(0, suma, "Debe generar un tablero vacío sin prefijados");
	}

	@Test
	void testGenerarSudokuConMaximoPrefijadosNoRompe() {
		assertDoesNotThrow(() -> model.generarSudoku(81));
	}

	@Test
	void testDeSetYGetValor() {
		model.setValor(5, 0, 0, true);
		assertEquals(5, model.getValor(0, 0));
	}

	@Test
	void testNumeroValido() {
		model.setValor(5, 0, 0, true);
		assertFalse(model.esValido(5, 0, 1));
		assertFalse(model.esValido(5, 1, 0));
	}

	@Test
	void testTableroValido() {
		model.setValor(5, 0, 0, true);
		assertTrue(model.tableroValido());
	}

	@Test
	void generarSudoku() {
		model.generarSudoku(25);
		model.tableroValido();
	}

}
