package view;

import javax.swing.*;
import java.awt.*;

public class CeldaView extends JTextField {
	private boolean prefijada = false;
	private Color doradoNegro = new Color(181, 148, 16);
	private Color grisOscuro = new Color(20, 20, 20);
	
	public CeldaView() {
		super();
//		setHorizontalAlignment(JTextField.CENTER);
//		setFont(new Font("Monospaced", Font.BOLD, 28));
//		setBackground(Color.WHITE);
		inicializarCelda();
	}
	
	private void inicializarCelda() {
		setHorizontalAlignment(JTextField.CENTER);
		setFont(new Font("Monospaced", Font.BOLD, 28));
		setBackground(Color.WHITE);
	}
	
	public void setValor(int valor, boolean esPrefijada) {
		if (valor == 0) {
			setText("");
		} else {
			setText(String.valueOf(valor));
		}
		
		this.prefijada = esPrefijada;
		setEditable(!esPrefijada);
		// FONDO GRIS SI ES PREFIJADA
		setBackground(esPrefijada ? grisOscuro : Color.WHITE);
//		setBackground(esPrefijada ? new Color(20, 20, 20) : new Color(181, 148, 16));
		// COLOR "BLACK GOLD"
		setForeground(esPrefijada ? doradoNegro : doradoNegro);
//		setForeground(esPrefijada ? new Color(181, 148, 16) : new Color(20, 20, 20));
	}
	
	public int getValor() {
        String texto = getText().trim();
        if (texto.isEmpty()) return 0;
        try {
            int valor = Integer.parseInt(texto);
            return (valor >= 1 && valor <= 9) ? valor : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean esPrefijada() {
        return prefijada;
    }
}