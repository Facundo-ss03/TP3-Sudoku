package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.Color;

public class Tablero {

	private JFrame frame;
	private JPanel panelDeJuego;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tablero window = new Tablero();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tablero() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(49, 49, 49));
		frame.setBounds(100, 100, 480, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panelDeJuego = new JPanel();
		cargarPanelDeJuego(panelDeJuego);
		panelDeJuego.setBackground(new Color(49, 49, 49));
		panelDeJuego.setBounds(10, 11, 444, 359);
		frame.getContentPane().add(panelDeJuego);
		panelDeJuego.setLayout(new GridLayout(9, 9, 0, 0));
		
	}
	
	public void cargarPanelDeJuego(JPanel panelDeJuego) {
		
		for(int i = 0; i < 9*9; i++)
			panelDeJuego.add(createTextButton());
			
	}
	
	private JButton createTextButton() {
		
		JButton txtButton = new JButton();
		txtButton.setHorizontalAlignment(JTextField.CENTER);
		txtButton.setText("");
		txtButton.addActionListener( e -> {

			if(txtButton.getText().equals("")) {
				txtButton.setText("1");
				return;
			}
			if(txtButton.getText().equals("9")) {
				txtButton.setText("");
				return;
			}
			if(Integer.parseInt(txtButton.getText()) <= 9) {
								
				int value = Integer.parseInt(txtButton.getText());
				value++;
				txtButton.setText("" + value);
			}
		});
		return txtButton;
	}
}
