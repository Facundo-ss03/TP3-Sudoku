package interfaces;

import java.util.List;

public interface ISudokuSolver {
	boolean resolverBacktracking(int[][] tablero);
	List<int[][]> getTodasLasSoluciones(int[][] tablero);
}
