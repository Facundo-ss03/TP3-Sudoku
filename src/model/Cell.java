package model;

public class Cell 
{

	private int value;

	public int getValue() { return value; }
	
	public void setValue(int newValue) 
	{ 
	
		if(newValue < 1)
			throw new IllegalArgumentException("Error al crear la celda: no se puede asignar un valor menor que 1. El valor ingresado fue: " + newValue);
		if(newValue > 9)
			throw new IllegalArgumentException("Error al crear la celda: no se puede asignar un valor mayor que 9. El valor ingresado fue: " + newValue);
		
		value = newValue; 
		
	}
	
}
