package model;

import java.util.List;
import java.util.Random;

public class CellsGrid 
{

	private int WIDTH;
	private int HEIGHT;
	private Cell[][] cells;
	
	public CellsGrid() {

		int WIDTH = 3;
		int HEIGHT = 3;
		
		initialize();
		
	}
	
	private boolean noRepeats(int number, List<Integer> alreadyAdded) 
	{
		if(alreadyAdded.contains(number)) return false;
		else return true;
	}
	
	private void initialize() 
	{
		
		cells = new Cell[WIDTH][HEIGHT];
	
		for(int i = 0; i < WIDTH; i++) 
		{
		
			for(int j = 0; j < WIDTH; j++) 
			{
			
				cells[i][j] = new Cell();
				
			}
		}
	}
	
	public void setCell(int row, int column, int random) 
	{
	
		cells[row][column].setValue(random);
		
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < WIDTH; i++) 
		{
		
			for(int j = 0; j < WIDTH; j++) 
			{
			
				sb.append(cells[i][j]);
				
			}
		}
		
		return sb.toString();
		
	}
	
}
