package main.commands.pneumatics.tilt;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import interfacesAndAbstracts.ImprovedCommand;
import main.Constants;
import main.HardwareAdapter;
import main.Robot;

public class Tilt extends ImprovedCommand implements Constants, HardwareAdapter {
	public DoubleSolenoid.Value v;
	
	public Tilt(DoubleSolenoid.Value v) {
		requires(Robot.pn);
		this.v = v;
	}
	
	public void execute(DoubleSolenoid.Value v) {
		Robot.pn.tilt(v);
	}

	@Override
	protected boolean isFinished() {
		// FIXME need to return true when finished
		return false;
	}
}
