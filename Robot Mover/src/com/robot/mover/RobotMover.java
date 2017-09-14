package com.robot.mover;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.robot.mover.bean.RobotMoverBean;
import com.robot.mover.constants.RobotMoverConstants;
/**
 * 
 * This is the main class holding key logic for Robot move requirement.
 * 
 * @author Ramprakash
 * @version 1.1     
 * 
 */
 
 /**
 * 			
 * 	Based on the assertion (0,0) is to be considered as the SOUTH WEST corner of a 5 units X 5 units table 
 *  Below is the layout of how coordinates would look on the table
 *  
 * 									NORTH					
 *		NORTH WEST	(4,0)	(4,1)	(4,2)	(4,3)	(4,4)	NORTH EAST
 *					(3,0)	(3,1)	(3,2)	(3,3)	(3,4)	
 *			WEST	(2,0)	(2,1)	(2,2)	(2,3)	(2,4)	EAST
 *					(1,0)	(1,1)	(1,2)	(1,3)	(1,4)	
 *		SOUTH WEST	(0,0)	(0,1)	(0,2)	(0,3)	(0,4)	SOUTH EAST
 *									SOUTH					
 * 
 */

public class RobotMover implements RobotMoverConstants
{
	static Properties prop = new Properties();
	InputStream input = null;
	static BufferedReader br;
	static RobotMoverBean rmBean = null;
    static Logger logger;  
    FileHandler fh = null;
	
	public RobotMover() 
	{
		// Default constructor calling init() method to load property file
		init();
	}
	
	//Initialize and load property files
	public void init()
	{
		try
		{
			// Loading Application property file
			input = new FileInputStream(APPLICATION_PROPERTY_FILE);
			prop.load(input);
			//instantiate RobotMoverBean object
			rmBean = new RobotMoverBean();
			fh = new FileHandler(prop.getProperty(LOG_FILE_NAME));
			logger = Logger.getLogger(prop.getProperty(LOG_FILE_NAME));
			logger.addHandler(fh);
			//Set format for logger
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
			// Loading input file containing commands
            if(prop.getProperty(COMMAND_INPUT_FILE) !=null)
            {
            	//Read file name and path for Command Input to be given to Robot
    			br = new BufferedReader(new FileReader(prop.getProperty(COMMAND_INPUT_FILE)));
            }
            else
            {
            	//Fail safe constant hardcoded with File Name - if in case something goes wrong with Property file entry
    			br = new BufferedReader(new FileReader(FAIL_SAFE_COMMAND_INPUT_FILE_NAME));
            }
			logger.info("RobotMover --> init() - Initializing Prop file , RobotMoverBean, logger instance");
		}
		catch(Exception ex)
		{
			logger.info("RobotMover --> Exception"+ex.getMessage());
			ex.printStackTrace();
		}
	}

	
	public static void main(String[] args) 
    {
		//Contructor invoked by creating a new object for the class - to invoke init() method
		new RobotMover();
		String line = null;
		String validMovesAllowed = null;
		String[] commas = null;
		try
		{
			//Real all valid moves allowed from Property File
			validMovesAllowed = prop.getProperty(VALID_MOVES);
			// While there are more lines in RobotMoverInputCommands.properties
			while((line = br.readLine()) != null)
			{
				// Check if the line read from input file has the word PLACE. Also check if the length is more than 5 - To make sure there are coordinates after the word PLACE
				// If the PLACE command is issued without coordinates, this code will ignore that PLACE command
				commas = line.split(",");
				if(line.indexOf(PLACE_COMMAND) != -1 && commas.length-1 == 2)
				{
					logger.info("RobotMoverUtil - executePLACECommand() - ENTRY");
					logger.info("Command Requested - "+line);
					//Set coordinates once PLACE command is read
					RobotMoverUtil.executePLACECommand(line.trim(),rmBean);
					if(rmBean.isPLACECommandExecuted())
					{
						logger.info("Robot placed successfully - "+line);
					}
					else
					{
						logger.info("PLACE command NOT executed - "+line);

					}
					logger.info("RobotMoverUtil - executePLACECommand() - EXIT");
				}
				else if (validMovesAllowed!=null && validMovesAllowed.indexOf(line.trim()) != -1 && rmBean.isPLACECommandExecuted())  
				// If the entry read from RobotMoverInputCommands.properties is a valid move & if PLACE command has already been exeuted
				{
					if(line.trim().indexOf(MOVE) != -1)
					{
						logger.info("RobotMoverUtil - executeMOVECommand() - ENTRY");
						//Execute MOVE command
						RobotMoverUtil.executeMOVECommand(rmBean);
						logger.info("RobotMoverUtil - executeMOVECommand() - EXIT");

						logger.info("Command Requested - "+line);
						logger.info("After executing command - "+line+" - "+"("+rmBean.getRow()+","+rmBean.getColumn()+","+rmBean.getDirection()+")");
					}
					else if(line.indexOf(LEFT) != -1 || line.indexOf(RIGHT) != -1)
					{
						logger.info("RobotMoverUtil - executeLeftRightCommand() - ENTRY");
						//Execute LEFT or RIGHT command
						RobotMoverUtil.executeLeftRightCommand(line.trim(),rmBean);
						logger.info("RobotMoverUtil - executeLeftRightCommand() - EXIT");
						logger.info("Command Requested - "+line);
						logger.info("After executing command - "+line+" - "+"("+rmBean.getRow()+","+rmBean.getColumn()+","+rmBean.getDirection()+")");
					}
					else if(line.indexOf(REPORT) != -1)
					{
						//REPORT coordinates of the Robot
						logger.info("RobotMover - REPORT Command requested");
						logger.info("("+rmBean.getRow()+","+rmBean.getColumn()+","+rmBean.getDirection()+")");
					}
				}
				else
				{
					// Invalid command catching area
					logger.info("Command Invalid at this time - "+line);
				}
			}
			
		}
		catch(Exception ex)
		{
			logger.info(ex.getMessage());
			ex.printStackTrace();
		}
    }
 }