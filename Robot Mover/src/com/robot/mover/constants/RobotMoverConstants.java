package com.robot.mover.constants;

/**
 * 
 * Java constants file holding all constants used within the application 
 * 
 * @author Ramprakash
 * @version 1.1     
 * 
 */
public interface RobotMoverConstants 
{	
	final String VALID_MOVES = "VALID_MOVES"; //Indicates the valid moves that are allowed
	final String PLACE_COMMAND = "PLACE"; // Constant for PLACE command
	final String MOVE = "MOVE"; // Constant for MOVE command
	final String LEFT = "LEFT"; // Constant for LEFT command
	final String RIGHT = "RIGHT"; // Constant for RIGHT command
	final String REPORT = "REPORT"; // Constant for REPORT command
	final String  NORTH = "NORTH"; // Constant for direction - NORTH
	final String  SOUTH = "SOUTH"; // Constant for direction - SOUTH
	final String  EAST = "EAST"; // Constant for direction - EAST
	final String  WEST = "WEST"; // Constant for direction - WEST
	final String APPLICATION_PROPERTY_FILE = "C:\\Softwares\\eclipse\\workspace\\RobotMover\\src\\com\\robot\\mover\\propertyfiles\\RobotMover.properties"; // Application property file name and path
	final String COMMAND_INPUT_FILE = "COMMAND_INPUT_FILE"; // Constant for Input file containing the commands
	final String FAIL_SAFE_COMMAND_INPUT_FILE_NAME = "C:\\Softwares\\eclipse\\workspace\\RobotMover\\src\\com\\robot\\mover\\propertyfiles\\RobotMoverInputCommands.properties"; //Fail safe commands property file name and path
	final String LOG_FILE_NAME = "LOG_FILE_NAME"; // Constant for Log File Name
}
