package main.commands.controllerCommands;

import controllers.Play;
import interfacesAndAbstracts.ImprovedCommand;

public class StartPlay extends ImprovedCommand {
	public StartPlay() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Play.okToPlay(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Play.isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Play.okToPlay(false);
    	Play.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
