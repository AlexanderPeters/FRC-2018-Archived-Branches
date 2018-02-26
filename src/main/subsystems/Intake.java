package main.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;
import main.HardwareAdapter;

public class Intake extends Subsystem implements Constants, HardwareAdapter {
	public static enum WheelStates {
		In, Out, Off
	}
	
	// NOT SURE IF WE SHOULD USE AN ENUM- TESTING SWITCH METHODS FIRST
	// TODO do we need this
//	public static enum CubeInIntake {
//		HasCube, NoCube
//	}
	
	public static WheelStates wheelStates = WheelStates.Off;
	//public static CubeInIntake cubeInIntake = CubeInIntake.NoCube;
	
	public void spinIn() {
		leftIntakeMotor.set(-1.0);
    	rightIntakeMotor.set(1.0);
    	wheelStates = WheelStates.In;
	}
	
	public void spinOut() {
		leftIntakeMotor.set(1.0);
    	rightIntakeMotor.set(-1.0);
    	wheelStates = WheelStates.Out;
	}
	
	public void spinOff() {
    	leftIntakeMotor.set(0.0);
    	rightIntakeMotor.set(0.0);
    	wheelStates = WheelStates.Off;
	}
	
//	public boolean isCubeInIntake() {
//		return intakeSwitch.get();
//	}
	
	@Override
	protected void initDefaultCommand() {
	}
}
