package main.commands.pneumatics.tilt;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;
import main.HardwareAdapter;

public class TiltUp extends CommandGroup implements Constants, HardwareAdapter{
    public TiltUp() {
    	addSequential(new Tilt(RET));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new Tilt(OFF));
    }
}