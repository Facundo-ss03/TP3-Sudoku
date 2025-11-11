package view;

import javax.swing.*;
import java.awt.*;

public class CeldaView extends JTextField {
	private boolean prefijada = false;
	private static final Color DORADO_NEGRO = new Color(181, 148, 16);
	private static final Color GRIS_OSCURO = new Color(20, 20, 20);
	
	public CeldaView() {
		super();
		inicializarCelda();
	}
	
	private void inicializarCelda() {
		setHorizontalAlignment(JTextField.CENTER);
		setFont(new Font("Monospaced", Font.BOLD, 28));
		setBackground(Color.WHITE);
		
		addKeyListener(new java.awt.event.KeyAdapter() {
	        @Override
	        public void keyTyped(java.awt.event.KeyEvent e) {
	            char c = e.getKeyChar();

	            if (c < '1' || c > '9' || getText().length() >= 1) {
	                e.consume(); 
	            }
	        }
	    });
	}
	
	public void setValor(int valor, boolean esPrefijada) {
		if (valor == 0) {
			setText("");
		} else {
			setText(String.valueOf(valor));
		}
		this.prefijada = esPrefijada;
		setEditable(!esPrefijada);
		setBackground(esPrefijada ? GRIS_OSCURO : Color.WHITE);
		setForeground(esPrefijada ? DORADO_NEGRO : DORADO_NEGRO);
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