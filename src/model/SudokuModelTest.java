package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuModelTest {
	private SudokuModel model;

	@BeforeEach
	void setUp() {
		model = new SudokuModel();
	}
	
	@Test
	void testDeSetYGetValor() {
		model.setValor(5, 0, 0, true);
		assertEquals(5, model.getValor(0, 0));
	}
	
	@Test
	void testNumeroEsValido() {
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
