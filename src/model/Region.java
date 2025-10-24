package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Region {

	private CellsGrid cells;
	Random generator;
	List<Integer> alreadyAdded;
	
	public Region() 
	{
	
		cells = new CellsGrid();
		generator = new Random();
		alreadyAdded = new ArrayList<Integer>();
		generateCells();
		
	}
	
	private void generateCells() 
	{
		
		boolean decision = generator.nextInt() < 0.60;
		
		boolean[][] map = generateBasicStruct();

		for(int i = 0; i < 3; i++) 
		{
		
			for(int j = 0; j < 3; j++) 
			{

				System.out.print(map[i][j]);
				
			}
		}
		
	}
	
	private boolean[][] generateBasicStruct() 
	{
		
		boolean[][] struct = new boolean[3][3];
		
		for(int i = 0; i < 3; i++) {
		
			for(int j = 0; j < 3; j++) {
				
				struct[i][j] = generator.nextInt() < 0.40; 
				
			}
		}
		
		return struct;
		
	}
	
	private void generateCells(int random, int row, int col, boolean[][] struct) {
		
		if(struct[row][col] == true) {
			
			
			
		}
		
	}
	
	public String toString() {

		return cells.toString();
		
	}

	public static void main(String args[]) {

		Region r = new Region();
		System.out.print(r.toString());
		
	}
	
}
