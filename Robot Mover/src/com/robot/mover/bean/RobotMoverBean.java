package com.robot.mover.bean;

/**
 * 
 * Java bean which holds values for 
 * row - indicates the row number on the table where Robot is located
 * column - indicates the column number on the table where Robot is located
 * direction - indicates the direction in which Robot is to move
 * isPLACECommandExecuted - indicates if PLACE command has been executed or not
 * 
 * @author Ramprakash
 * @version 1.1     
 * 
 */

public class RobotMoverBean 
{

	private int row = 0;
	private int column = 0;
	private String direction;
	private boolean isPLACECommandExecuted = false;
	
	//get the existing value for row
	public int getRow() 
	{
		return row;
	}
	
	//Set value for row
	public void setRow(int row) 
	{
		this.row = row;
	}
	
	//get the existing value for column
	public int getColumn() 
	{
		return column;
	}
	
	//set value for column
	public void setColumn(int column) 
	{
		this.column = column;
	}
	
	//get the existing value for direction
	public String getDirection() 
	{
		return direction;
	}
	
	//Set the value for direction
	public void setDirection(String direction)
	{
		this.direction = direction;
	}

	//get the existing value for isPLACECommandExecuted
	public boolean isPLACECommandExecuted() 
	{
		return isPLACECommandExecuted;
	}

	//Set the value for isPLACECommandExecuted
	public void setPLACECommandExecuted(boolean isPLACECommandExecuted) 
	{
		this.isPLACECommandExecuted = isPLACECommandExecuted;
	}
}
