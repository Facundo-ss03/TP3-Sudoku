package view;

import controller.SudokuController;
import interfaces.ISudokuView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SudokuView extends JFrame implements ISudokuView {
	private static final Color DORADO_NEGRO = new Color(181, 148, 16);
	private static final Color GRIS_OSCURO = new Color(20, 20, 20);
	private static final Font FUENTE_MONO = new Font("Monospaced", Font.PLAIN, 14);
	
	private final CeldaView[][] celdas = new CeldaView[9][9];
	private SudokuController controller;
	private final JTextField campoPrefijado;
	
	private final JButton btnResolver;
	private final JButton btnGenerar;
	private final JButton btnLimpiar;
	
	public SudokuView() {
		inicializarVentana();
		
		JPanel panelTablero = crearPanelTablero();
        JPanel panelBotones = new JPanel();
        
        // BOTONES
        btnResolver = new JButton("Resolver");
        btnGenerar = new JButton("Generar Sudoku Aleatorio");
        btnLimpiar = new JButton("Limpiar");
        
        // CAMPO DE TEXTO PARA LA CANTIDAD DE PREFIJADOS
        campoPrefijado = new JTextField("17", 3);
        
        // CONFIGURAR EL PANEL DE LOS BOTONES
        
        // CONFIGURAR ESTILO DE LOS BOTONES:
        configurarBotones(panelBotones);

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
	
	private JPanel crearPanelTablero() {
		JPanel panel = new JPanel(new GridLayout(9, 9));
		panel.setBorder(BorderFactory.createLineBorder(GRIS_OSCURO));
		
		for (int fila = 0; fila < 9; fila++) {
			for (int columna = 0; columna < 9; columna++) {
				CeldaView celda = new CeldaView();
				
				int arriba = (fila % 3 == 0 && fila != 0) ? 3 : 1;
				int izquierda = (columna % 3 == 0 && columna != 0) ? 3 : 1;
				celda.setBorder(BorderFactory.createMatteBorder(arriba, izquierda, 1, 1, Color.BLACK));
				
				celdas[fila][columna] = celda;
				panel.add(celda);
			}
		}
		return panel;
	}
	
	private void configurarBotones(JPanel panel) {
		panel.add(new JLabel("Prefijados:"));
        panel.add(campoPrefijado);
        panel.add(btnGenerar);
        panel.add(btnResolver);
        panel.add(btnLimpiar);
		
		btnResolver.setForeground(DORADO_NEGRO);
        btnGenerar.setForeground(DORADO_NEGRO);
        btnLimpiar.setForeground(DORADO_NEGRO);
	}
	
	// SETTEAR EL CONTROLLER Y LOS LISTENERS DE LOS BOTONES
	public void setController(SudokuController controller) {
		this.controller = controller;
		
		// BOTON RESOLVER:
		btnResolver.addActionListener(e -> controller.resolverSudoku());
		
		// BOTON GENERAR:
		btnGenerar.addActionListener(e -> {
			try {
				// TOMA EL NUMERO QUE ESTA DENTRO DEL CAMPO DE TEXTO "campoPrefijado"
				int cantidad = Integer.parseInt(campoPrefijado.getText());
				if (cantidad < 0 || cantidad > 81) {
					mostrarMensaje("Ingrese un valor entre 0 y 81");
					return;
				}
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
	public void mostrarVentanaSoluciones(List<int[][]> soluciones) {
	    JTextArea areaTexto = new JTextArea(20, 30);
	    areaTexto.setFont(FUENTE_MONO);
	    areaTexto.setEditable(false);

	    StringBuilder sb = new StringBuilder();
	    for (int k = 0; k < soluciones.size(); k++) {
	        sb.append("Solución ").append(k + 1).append(":\n");
	        int[][] tablero = soluciones.get(k);
	        for (int i = 0; i < 9; i++) {
	            for (int j = 0; j < 9; j++) {
	                sb.append(tablero[i][j]).append(" ");
	                if ((j+1)%3==0 && j<8) sb.append("| ");
	            }
	            sb.append("\n");
	            if ((i+1)%3==0 && i<8) sb.append("------+-------+------\n");
	        }
	        sb.append("\n");
	    }

	    areaTexto.setText(sb.toString());
	    JOptionPane.showMessageDialog(this, new JScrollPane(areaTexto), "Soluciones del Sudoku", JOptionPane.INFORMATION_MESSAGE);
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