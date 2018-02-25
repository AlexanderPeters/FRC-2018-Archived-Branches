// Copyright (c) 2018 FIRST 3140. All Rights Reserved.

package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import Util.Logger;
import controllers.Play;
import controllers.Record;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import interfacesAndAbstracts.ImprovedRobot;
import loopController.Looper;
import main.commands.controllerCommands.DoNothing;
import main.commands.controllerCommands.FileCreator;
import main.commands.controllerCommands.FileDeletor;
import main.commands.controllerCommands.FilePicker;
import main.commands.controllerCommands.StartPlay;
import main.commands.controllerCommands.StartRecord;
import main.subsystems.DriverAlerts;
import main.subsystems.DriverCamera;
import main.subsystems.Drivetrain;
import main.subsystems.Elevator;
import main.subsystems.Intake;
import main.subsystems.Pneumatics;

public class Robot extends ImprovedRobot {
	private enum StartPos {LEFT, MIDDLE, RIGHT}
	public static Drivetrain dt;
	public static Pneumatics pn;
	public static Intake it;
	public static Elevator el;
	public static DriverCamera dc;
	public static DriverAlerts da;	
	public static OI oi;
	// PLAY AND RECORD
	public static Logger lg;
    private static Looper autoLooper;
    private static SendableChooser<Command> fileChooser;
    private static Command autoPlayCommand;
    private Command lastSelectedFile = new DoNothing();
    private static String newFileName = "";
    private static List<File> listOfFiles = new ArrayList<File>();
    private static int lastNumOfFiles = 0;
	// AUTO LOGIC
	public static StartPos start_pos = StartPos.LEFT;
	public static boolean auto_score = true;
	private static SendableChooser<Runnable> autoChooser, startPos;

	// auto modes
	//Command autoCommand;

	@Override
	public void robotInit() {
		// camera
		//CameraServer.getInstance().startAutomaticCapture();
		dc = new DriverCamera();
		// OI must be at end
		dt = new Drivetrain();
		pn = new Pneumatics();
		it = new Intake();
		el = new Elevator();
		oi = OI.newInstance();
		// da = new DriverAlerts();	
		lg = new Logger();
		autoLooper = new Looper(kLooperDt);
		autoLooper.register(new Record());
		autoLooper.register(new Play()); 

        //**************************************************SmartDashboard
		SmartDashboard.putString("NOTICE:", "Whenever you redeploy code you must restart shuffleboard; And whenever you "
								+ "delete a file you must restart robot code.");

		//FileSelector
    	fileChooser = new SendableChooser<>();
    	fileChooser.addDefault("", new DoNothing());
    	SmartDashboard.putData("File Selector", fileChooser);
    	
    	if(!isCompetition) {
    		SmartDashboard.putData("Record", new StartRecord());
			SmartDashboard.putData("Play", new StartPlay());
    		// File adder
    		SmartDashboard.putString("New File Name", "");
    		SmartDashboard.putData("Create a new file", new FileCreator()); 
    		// File deleter
    		SmartDashboard.putData("Delete a file", new FileDeletor());
    	}
		
		// auto modes
		autoChooser = new SendableChooser<>();
		autoChooser.addObject("Score Cube", () -> {
			auto_score=true;
		});
		autoChooser.addDefault("Baseline", () -> {
			auto_score=true;
		});
		SmartDashboard.putData("Auto Chooser", autoChooser);

		// Starting Pos
		startPos = new SendableChooser<>();
		startPos.addDefault("Left", () -> {
			start_pos=StartPos.LEFT;
		});
		startPos.addObject("Middle", () -> {
			start_pos=StartPos.MIDDLE;
		});
		startPos.addObject("Right", () -> {
			start_pos=StartPos.RIGHT;
		});
		SmartDashboard.putData("Starting Position", startPos);
	}
	
	@Override
	public void disabledInit() {
		if(isCompetition) {
			if(autoPlayCommand.isRunning()) autoPlayCommand.cancel();
		}
		autoLooper.stop();		
	}
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		allPeriodic();
	}

	@Override
	public void autonomousInit() { 
		//TODO: THIS NEEDS SOME SERIOUS WORK
		autoLooper.start();
		if(isCompetition) {
			fileChooser.getSelected().start();
			Command autoPlayCommand = new StartPlay();
			autoPlayCommand.start();

			String gmsg = DriverStation.getInstance().getGameSpecificMessage();

			if (gmsg != null && gmsg.length() == 3) {
				// game message is correct

				boolean left = gmsg.charAt(0) == 'L';

				if (auto_score) {
					switch (start_pos) {
					case LEFT:
						if (left)
							; // TODO: score cube to left from left
						else
							; // TODO: score cube to right from left
						break;
					case MIDDLE:
						if (left)
							; // TODO: score cube to left from middle
						else
							; // TODO: score cube to right from middle
						break;
					case RIGHT:
						if (left)
							; // TODO: score cube to left from right
						else
							; // TODO: score cube to right from right
					}
				} else {
					switch (start_pos) {
					case LEFT:
						if (left)
							; // TODO: cross baseline to left from left
						else
							; // TODO: cross baseline to right from left
						break;
					case MIDDLE:
						if (left)
							; // TODO: cross baseline to left from middle
						else
							; // TODO: cross baseline to right from middle
						break;
					case RIGHT:
						if (left)
							; // TODO: cross baseline to left from right
						else
							; // TODO: cross baseline to right from right
					}
				}
			}
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		allPeriodic();
	}

	@Override
	public void teleopInit() {
		if(isCompetition) {
			if(autoPlayCommand.isRunning())
				autoPlayCommand.cancel();
		}
		if(!isCompetition)
			autoLooper.start();
	}	

	@Override
	public void teleopPeriodic() {
		Runtime runtime = Runtime.getRuntime();

		// smartdashboard stuff goes here
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Free memory", runtime.freeMemory());
		SmartDashboard.putNumber("Total memory", runtime.totalMemory());
		SmartDashboard.putNumber("Pressure: ", HardwareAdapter.analogPressureSensor1.value());
		SmartDashboard.putBoolean("Cube Detected: ", cubeSensor1.get());
		allPeriodic();
	}
	
	@Override
	public void testPeriodic() {
		allPeriodic();
	}
	
	private void checkForSmartDashboardUpdates() {
		if (!isCompetition && !newFileName.equals(SmartDashboard.getString("New File Name", "")))
			newFileName = SmartDashboard.getString("New File Name", "");
		
		// TODO: what is fileChooser?
		if (fileChooser.getSelected() != lastSelectedFile && fileChooser.getSelected() != null) {
			fileChooser.getSelected().start();
			lastSelectedFile = fileChooser.getSelected();
		}
		
		if (lg.getFiles(outputPath).length != lastNumOfFiles) {
			for (File file : lg.getFiles(outputPath))
				if (!fileNameInListOfFiles(listOfFiles, file)) {
					fileChooser.addObject(file.getName(), new FilePicker(file.getPath()));
					listOfFiles.add(file);
				}
			lastNumOfFiles = lg.getFiles(outputPath).length;
		} 
	}
	
	// TODO what kind of list it is?
	private boolean fileNameInListOfFiles(List<File> l, File f) {
		// TODO: switch from File to Path?
		for (File file: l) {
			// TODO comparing with absolute path? file name? instance?
			if (file.getName().equalsIgnoreCase(f.getName())) return true;
		}
		
		return false;
	}
	
	public static SendableChooser<Command> getFileChooser() {
		return fileChooser;
	}
	
	public static Command getFile() {
		return fileChooser.getSelected();
	}
	
	public void allPeriodic() {
		SmartDashboard.updateValues();
		checkForSmartDashboardUpdates();
		autoLooper.outputToSmartDashboard();
		/*
		dt.check();
		pn.check();
		*/
		oi.check();
		// Knowing where you're at
		if(!isCompetition) {
			SmartDashboard.putString("Working File", lg.getWorkingFile());
			SmartDashboard.putString("Working Path", outputPath);
		}
	}
	
	public static String getNewFileName() {
		return newFileName;
	}
}
