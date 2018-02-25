package main.commands.driveAlerts;

import interfacesAndAbstracts.ImprovedCommand;
import main.Robot;

// TODO are we using this?
public class AlertLightOn extends ImprovedCommand {
	public AlertLightOn() {
		requires(Robot.da);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//Robot.da.setAlertLightState(true);
		
	}
	

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}	
}
