package com.robot.mover;

import java.util.Arrays;
import java.util.List;

import com.robot.mover.bean.RobotMoverBean;
import com.robot.mover.constants.RobotMoverConstants;

/**
 * 
 * Utility file created to separate the decision making logic from the main Java file for this project 
 * 
 * @author Ramprakash
 * @version 1.1     
 * 
 */
public class RobotMoverUtil implements RobotMoverConstants
{

	/**
	 * @param RobotMoverBean
	 * This method taken RobotMoverBean object as parameter.
	 * This method is invoked when a MOVE command is requested.
	 * Depending on the current direction - NORTH, SOUTH, EAST, WEST - the MOVE command will have to move Robot on the table.
	 * This method also makes sure any invalid MOVE being requested is not executed
	 *
	 */
	public static void executeMOVECommand(RobotMoverBean rmBean)
	{
		// Logic to identify the coordinates after MOVE command is executed - based on direction
		switch(rmBean.getDirection())
		{
			case NORTH: //If current direction is NORTH
				if(rmBean.getRow()+1 >=0 && rmBean.getRow()+1 <4) //Checking to see if the MOVE is valid and if the Robot stays within the 5X5 table 
				{
					rmBean.setRow(rmBean.getRow()+1);
					// Move Robot towards NORTH direction
				}
				break;
			
			case SOUTH: //If current direction is SOUTH
				if(rmBean.getRow()-1 >=0 && rmBean.getRow()-1 <=4) //Checking to see if the MOVE is valid and if the Robot stays within the 5X5 table 
				{
					rmBean.setRow(rmBean.getRow()-1);
					// Move Robot towards SOUTH direction
				}								
				break;
			
			case(EAST): //If current direction is EAST
				if(rmBean.getColumn()+1 >=0 && rmBean.getColumn()+1 <=4) //Checking to see if the MOVE is valid and if the Robot stays within the 5X5 table 
				{
					rmBean.setColumn(rmBean.getColumn()+1);
					// Move Robot towards EAST direction
				}								
				break;
			
			case(WEST): //If current direction is WEST
				if(rmBean.getColumn()-1 >=0 && rmBean.getColumn()-1 <=4) //Checking to see if the MOVE is valid and if the Robot stays within the 5X5 table 
				{
					rmBean.setColumn(rmBean.getColumn()-1);
					// Move Robot towards WEST direction
				}									
				break;
			default:
				//Move cannot be made - hence ignore
				break;
		}
	}
	
	/**
	 * @param RobotMoverBean object
	 * @param Input Command String
	 * This method taken RobotMoverBean object and Input Command String as parameters.
	 * This method is invoked when a LEFT or RIGHT command is requested.
	 * Depending on the current direction - NORTH, SOUTH, EAST, WEST - the LEFT or RIGHT command will shift the direction in which Robot is facing
	 *
	 */
	public static void executeLeftRightCommand(String inputCommand, RobotMoverBean rmBean)
	{
		switch(rmBean.getDirection())
		{
			case NORTH:  //If current direction is NORTH
				rmBean.setDirection((inputCommand.equalsIgnoreCase(LEFT))?WEST:EAST);
				//if LEFT command is requested direction is set to WEST 
				//if RIGHT command is requested direction is set to EAST 
				break;
			case SOUTH:  //If current direction is SOUTH
				rmBean.setDirection((inputCommand.equalsIgnoreCase(LEFT))?EAST:WEST);
				//if LEFT command is requested direction is set to EAST 
				//if RIGHT command is requested direction is set to WEST 
				break;
			case EAST:  //If current direction is EAST
				rmBean.setDirection((inputCommand.equalsIgnoreCase(LEFT))?NORTH:SOUTH);
				//if LEFT command is requested direction is set to NORTH 
				//if RIGHT command is requested direction is set to SOUTH 
				break;
			case WEST:  //If current direction is WEST
				rmBean.setDirection((inputCommand.equalsIgnoreCase(LEFT))?SOUTH:NORTH);
				//if LEFT command is requested direction is set to SOUTH 
				//if RIGHT command is requested direction is set to NORTH 
				break;
		}	
	}
	
	/**
	 * @param RobotMoverBean object
	 * @param Input Command String
	 * This method taken RobotMoverBean object and Input Command String as parameters.
	 * This method is invoked when a PLACE command is requested.
	 * PLACE is the first command the Robot is supposed to act on. This command places the Robot on the requested coordinates on  the table.
	 *
	 */

	public static void executePLACECommand(String inputCommand, RobotMoverBean rmBean)
	{
		int tempRow = 0, tempColumn = 0;
		rmBean.setPLACECommandExecuted(true);
		//If the read text starts with "PLACE" - Proceed to parse the second half of the text - containing coordinates and direction
		List<String> items = Arrays.asList(inputCommand.trim().substring(PLACE_COMMAND.length()).split("\\s*,\\s*"));
		if(items.size() == 3) // Checking to see if all 3 coordinates exist X,Y, F (X,Y is table position and F is direction in which Robot should be facing)
		{
			tempRow = Integer.parseInt(items.get(0).toString().trim()); // Parse and convert X to integer
			tempColumn = Integer.parseInt(items.get(1).toString().trim()); // Parse and convert Y to integer
			
			if(tempRow >= 0 && tempRow <= 4 && tempColumn >=0 && tempColumn >=0) // Checking to make sure X and Y are within the limits of table. This will ensure the Robot does not fall off table
			{
				rmBean.setRow(tempRow); // Set value of X (Row)
				rmBean.setColumn(tempColumn); // Set value of Y (Column)
				rmBean.setDirection(items.get(2).toString());  // Set value of Y (direction)
			}
			else
			{
				//If coordinates requested are outside the area of the table
				rmBean.setPLACECommandExecuted(false);
			}
		}
	}
}
