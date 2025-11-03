package view;

import controller.SudokuController;
import interfaces.ISudokuView;

import javax.swing.*;
import java.awt.*;

public class SudokuView extends JFrame implements ISudokuView {
	private final CeldaView[][] celdas = new CeldaView[9][9];
	private SudokuController controller;
	private final JTextField campoPrefijado;
	private Color grisOscuro = new Color(20, 20, 20);
	
	private final JButton btnResolver;
	private final JButton btnGenerar;
	private final JButton btnLimpiar;
	
	public SudokuView() {
		inicializarVentana();
        
        // PANEL PRINCIPAL
        JPanel panelTablero = new JPanel(new GridLayout(9, 9));
        panelTablero.setBorder(BorderFactory.createLineBorder(grisOscuro));
        
        // RECORRER LA MATRIZ PARA CREAR UNA CELDAVIEW
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
            	// CREA LA CELDA VIEW
                CeldaView celda = new CeldaView();

                // BORDES GRUESOS PARA EL 3x3
                if (fila % 3 == 0 && fila != 0) {
                	// 3 1 1 1
                    celda.setBorder(BorderFactory.createMatteBorder(3, 1, 1, 1, Color.BLACK));
                }
                if (columna % 3 == 0 && columna != 0) {
                	// 1 3 1 1
                    celda.setBorder(BorderFactory.createMatteBorder(1, 3, 1, 1, Color.BLACK));
                }
                
                // ASIGNA LA CELDA Y LA AGREGA AL TABLERO
                celdas[fila][columna] = celda;
                panelTablero.add(celda);
            }
        }
        
        // PANEL PARA BOTONES
        JPanel panelBotones = new JPanel();
        
        // BOTONES
        btnResolver = new JButton("Resolver");
        btnGenerar = new JButton("Generar Sudoku Aleatorio");
        btnLimpiar = new JButton("Limpiar");
        
        // CAMPO DE TEXTO PARA LA CANTIDAD DE PREFIJADOS
        campoPrefijado = new JTextField("17", 3);
        
        // CONFIGURAR EL PANEL DE LOS BOTONES
        panelBotones.add(new JLabel("Prefijados:"));
        panelBotones.add(campoPrefijado);
        panelBotones.add(btnGenerar);
        panelBotones.add(btnResolver);
        panelBotones.add(btnLimpiar);
        
        // CONFIGURAR ESTILO DE LOS BOTONES:
        btnResolver.setForeground(new Color(181, 148, 16));
        btnGenerar.setForeground(new Color(181, 148, 16));
        btnLimpiar.setForeground(new Color(181, 148, 16));

        // ASIGNAR UBICACIONES DE LOS PANELES
        add(panelTablero, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
	}
	
	private void inicializarVentana() {
		setTitle("Sudoku con Backtracking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
	}
	
	// SETTEAR EL CONTROLLER Y LOS LISTENERS DE LOS BOTONES
	public void setController(SudokuController controller) {
		this.controller = controller;
		
		// BOTON RESOLVER:
		btnResolver.addActionListener(e -> controller.resolverSudoku());
		
		// BOTON GENERAR:
		// HAY QUE PONER UNA EXCEPCION DE QUE SI EL USUARIO PONE UN VALOR > 81, SALTE UN ERROR
		btnGenerar.addActionListener(e -> {
			try {
				// TOMA EL NUMERO QUE ESTA DENTRO DEL CAMPO DE TEXTO "campoPrefijado"
				int cantidad = Integer.parseInt(campoPrefijado.getText());
				// GENERA UN SUDOKU ALEATORIO CON ESE NÚMERO
				controller.generarSudokuAleatorio(cantidad);
			} catch (NumberFormatException excepcion) {
				mostrarMensaje("Ingrese un número válido de celdas");
			}
		});
		
		// BOTON LIMPIAR:
		btnLimpiar.addActionListener(e -> controller.limpiarSudoku());
	}

	@Override
	public int getValorEnCelda(int fila, int columna) {
        return celdas[fila][columna].getValor();
    }

    // ESTABLECE UN VALOR EN LA CELDA PARA VERLO VISUALMENTE
	@Override
    public void setValorEnCelda(int valor, int fila, int columna, boolean esPrefijada) {
        celdas[fila][columna].setValor(valor, esPrefijada);
    }

    // MUESTRA LOS MENSAJES EMERGENTES
	@Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}