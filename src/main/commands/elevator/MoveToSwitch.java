package main.commands.elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.Constants;
import main.HardwareAdapter;
import main.Robot;

// TODO are we using this
public class MoveToSwitch extends CommandGroup implements Constants, HardwareAdapter {
	public final double switchHeight = 24; //set this in encoder units today...

	public void MoveToSwich() {
		requires(Robot.el);
	}
	
    // Called just before this Command runs the first time
    public void initialize() {
    	Robot.el.moveToPosPID(switchHeight);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.el.isIntakeAtPos(switchHeight);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}