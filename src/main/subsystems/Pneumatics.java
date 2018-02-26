package main.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;
import main.HardwareAdapter;

public class Pneumatics extends Subsystem implements Constants, HardwareAdapter {
	private boolean down = false;
	// TODO make the code modify down or remove it entirely

	public Pneumatics() {
		shifter.set(EXT);
		shifter.set(OFF);
		tilter.set(EXT);
		tilter.set(OFF);
		arm.set(EXT);
		arm.set(OFF);
//		pto.set(RET);
//		pto.set(OFF);
//		forklift.set(EXT);
//		forklift.set(OFF);
//		hook.set(EXT);
//		hook.set(OFF);
	}
	
	public static enum ArmStates {
		Opened, Closed, Off
	}
	
	public static enum LiftStates {
		Up, Down, Off
	}
	
	public static ArmStates armStates = ArmStates.Off;
	public static LiftStates liftStates = LiftStates.Off;

	/*******************
	 * COMMAND METHODS *
	 *******************/

	/**
	 * Shifts the gearbox from the different gears
	 * 
	 * @param v - Desired shifting value (Uses default shifting values)
	 */
	public void shift(DoubleSolenoid.Value v) {
		shifter.set(v);
	}
	
	public boolean getDown() {
		return down;
	}
	
	// Toggles Arm to open or closed
	public void toggleArm(DoubleSolenoid.Value v) {
		if (v == EXT) armStates = ArmStates.Closed;
		else if (v == RET) armStates = ArmStates.Opened;
		else { armStates = ArmStates.Off; }
		arm.set(v);
	}
	
	// Changes the tilter to up or down
	public void tilt(DoubleSolenoid.Value v) {
		if (v == EXT) liftStates = LiftStates.Down;
		else if (v == RET) liftStates = LiftStates.Up;
		else { liftStates = LiftStates.Off; }
		tilter.set(v);
	}
//	
//	public void shiftPTO(DoubleSolenoid.Value v) {
//		pto.set(v);
//	}
//	
//	public void toggleForks(DoubleSolenoid.Value v) {
//		forklift.set(v);
//	}
//	
//	public void toggleHooks(DoubleSolenoid.Value v) {
//		hook.set(v);
//	}
//	
	/**
	 * Toggles the compressor (ON/OFF)
	 */
	public void toggleComp() {
		if (comp.enabled())
			comp.stop();
		else
			comp.start();
	}
	
	public void turnCompOff() {
		if (comp.enabled())
			comp.stop();
	}

	/*******************
	 * DEFAULT METHODS *
	 *******************/
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}
