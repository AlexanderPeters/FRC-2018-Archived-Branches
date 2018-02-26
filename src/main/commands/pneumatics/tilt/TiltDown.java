package main.commands.pneumatics.tilt;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;
import main.HardwareAdapter;

public class TiltDown extends CommandGroup implements Constants, HardwareAdapter{
    public TiltDown() {
    	addSequential(new Tilt(EXT));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new Tilt(OFF));
    }
}