package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;
import main.HardwareAdapter;
import main.commands.intake.SpinIn;
import main.commands.pneumatics.arm.ArmClose;
import main.commands.pneumatics.arm.ArmOpen;
import main.commands.pneumatics.tilt.TiltDown;;

public class Dance extends CommandGroup implements Constants, HardwareAdapter {
	public Dance() {
		addSequential(new ArmOpen());
		addSequential(new WaitCommand(.2));
		addSequential(new ArmClose());
		addSequential(new WaitCommand(.2));
		addSequential(new ArmOpen());
		addSequential(new WaitCommand(.2));
		addSequential(new ArmClose());
		addSequential(new WaitCommand(.2));
		for (int i=0; i<4; ++i) {
			addSequential(new TiltDown());
			addSequential(new WaitCommand(.2));
		}
		addSequential(new SpinIn());
	}
}

