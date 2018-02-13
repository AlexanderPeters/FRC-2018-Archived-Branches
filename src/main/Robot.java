/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package main;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.commands.autonomous.Baseline;
import main.commands.autonomous.DoNothing;
import main.commands.autonomous.ScoreCube;
import main.commands.joystickselector.JoyStick1;
import main.commands.joystickselector.JoyStick2;
import main.commands.startposition.StartLeft;
import main.commands.startposition.StartMiddle;
import main.commands.startposition.StartRight;
import main.subsystems.DriverAlerts;
import main.subsystems.Drivetrain;
import main.subsystems.Elevator;
import main.subsystems.Intake;
import main.subsystems.Pneumatics;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot implements Constants, HardwareAdapter {
	public static enum RobotState {
		Driving, Climbing, Neither
	}
	//robot modes
	Command teleopCommand;
	SendableChooser teleopChooser, autoChooser, startPos;
	
	//SendableChooser teleopChooser;
	public static Drivetrain dt;
	public static Pneumatics pn;
	public static Intake it;
	public static Elevator el;
	public static DriverAlerts da;
	//public static TfMini mini;
	//public static SmartDashboardInteractions sdb;

	public static String startpos;
	
	// auto modes
	Command autoCommand;

	@Override
	public void robotInit() {
		//create auto command
		//autonomousCommand = new SodaDelivery()
		//mini = new TfMini();
		
		//camera
		CameraServer.getInstance().startAutomaticCapture();
		//OI must be at end
		dt = new Drivetrain();
		pn = new Pneumatics();
		it = new Intake();
		//CameraServer.getInstance().startAutomaticCapture();
		el = new Elevator();
		//da = new DriverAlerts();
		//sdb = new SmartDashboardInteractions();
		//robotState = 
		
		//teleop modes
		teleopChooser = new SendableChooser();
		//SmartDashboard.putBoolean("alert light", Robot.da.getAlertLightState());
		teleopChooser.addDefault("2 joysticks", new JoyStick2());
		teleopChooser.addObject("1 joystick", new JoyStick1());
		SmartDashboard.putData("teleop mode chooser", teleopChooser);
		
		//auto modes
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Baseline", new Baseline());
		autoChooser.addObject("Score Cube", new ScoreCube());
		autoChooser.addObject("Do Nothing", new DoNothing());
		SmartDashboard.putData("auto", autoChooser);
		
		//Starting Pos
		startPos = new SendableChooser();
		startPos.addDefault("Left", new StartLeft());
		startPos.addObject("Middle", new StartMiddle());
		startPos.addObject("Right", new StartRight());
		SmartDashboard.putData("Starting Pos", startPos);
	}

	
	@Override
	public void disabledInit() {

	}
	
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		autoCommand = (Command) autoChooser.getSelected();
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0)
        {
        	if(gameData.charAt(0) == 'L' && startpos != "middle")
			{
	        	//Put left auto code here
				if (startpos == "left" && autoCommand.toString() == "ScoreCube()")
				autoCommand.start();
				else if (autoCommand.toString() == "DoNothing()") {
					autoCommand = new Baseline();
					autoCommand.start();
				}
				else 
					autoCommand.start();	
				
					  
			} else {
				//Put right auto code here
				if (startpos == "right" && autoCommand.toString() == "ScoreCube()")
				autoCommand.start();
				else if (autoCommand == new DoNothing()) {
					autoCommand = new Baseline();
					autoCommand.start();
				}
				else 
					autoCommand.start();
			}
        	if (gameData.charAt(0) == 'L' && startpos == "middle")
        	{
        		//Put middle auto here
        		autoCommand = new DoNothing();
				autoCommand.start();
        	}
    	}
        System.out.println(autoCommand.toString());
    }
		
        /*if(autoCommand != null) autoCommand.start();
		autoCommand = (Command) autoChooser.getSelected();
		autoCommand.start();*/


	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		//el.check();
	}

	@Override
	public void teleopInit() {
		if (autoCommand != null) autoCommand.cancel();
		teleopCommand = (Command) teleopChooser.getSelected();
		teleopCommand.start();
	} 
	@Override
	public void teleopPeriodic() {
		// smartdashboard stuff goes here
		OI.check();
		Scheduler.getInstance().run();
		//SmartDashboard.putNumber("Lazers ;)", mini.getValue());
		/*SmartDashboard.putNumber("Elevator Encoder Revs", leftElevatorMaster.getSensorCollection().getQuadraturePosition() / countsPerRev);
		SmartDashboard.putBoolean("Is arm at bottom: ", el.isArmAtBottom());
		SmartDashboard.putBoolean("Is arm at top: ", el.isArmAtTop());*/
		//el.check();
		//SmartDashboard.putNumber("Ultrasonic sensor distance (mm): ", HardwareAdapter.ultra.getRangeMM());
		/*SmartDashboard.putNumber("Elevator velocity:", el.getElevatorVelocity());
		SmartDashboard.putNumber("Elevator Distance:", el.getTicksTravelled());*/
		SmartDashboard.putNumber("Pressure: ", HardwareAdapter.analogPressureSensor1.value());
		SmartDashboard.putBoolean("Cube Detected: ", cubeSensor1.get());
	}

	@Override
	public void testPeriodic() {
		
	}
}
