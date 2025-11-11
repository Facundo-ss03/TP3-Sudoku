package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import view.CeldaView;

public class CeldaViewTest {

    @Test
    void testGetValorConNumeroValido() {
        CeldaView celda = new CeldaView();
        celda.setText("5");
        assertEquals(5, celda.getValor(), "Se tiene que retornar el num 5");
    }

    @Test
    void testGetValorConLetraDevuelveCero() {
        CeldaView celda = new CeldaView();
        celda.setText("a");
        assertEquals(0, celda.getValor(), "Si se ingresa texto retorna 0");
    }

    @Test
    void testGetValorConNumeroInvalidoRetornaCero() {
        CeldaView celda = new CeldaView();
        celda.setText("12");
        assertEquals(0, celda.getValor(), "Si se ingresa un numero >9 o <0 debe devolver 0");
    }

    @Test
    void testSetValorActualizaTextoCorrectamente() {
        CeldaView celda = new CeldaView();
        celda.setValor(7, false);
        assertEquals("7", celda.getText(), "Se tiene que mostrar el número ingresado");
        assertFalse(celda.esPrefijada(), "Si no es prefijada, tiene que quedar editable");
    }

    @Test
    void testSetValorPrefijadaNoEditable() {
        CeldaView celda = new CeldaView();
        celda.setValor(9, true);
        assertTrue(celda.esPrefijada(), "Se debe marcar como prefijada");
        assertFalse(celda.isEditable(), "Las celdas prefijadas no se debenpoder editar");
    }

    @Test
    void testGetValorVacioDevuelveCero() {
        CeldaView celda = new CeldaView();
        celda.setText("");
        assertEquals(0, celda.getValor(), "Si está vacíaretorna 0");
    }
}
