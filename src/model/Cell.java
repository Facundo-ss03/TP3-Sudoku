package model;

public class Cell {

	private int value;
	
	Cell(int staticValue){

		if(staticValue < 1)
			throw new IllegalArgumentException("Error al crear la celda: no se puede asignar un valor menor que 1. El valor ingresado fue: " + staticValue);
		if(staticValue > 9)
			throw new IllegalArgumentException("Error al crear la celda: no se puede asignar un valor mayor que 9. El valor ingresado fue: " + staticValue);
		
		this.value = staticValue;
		
	}	
}
