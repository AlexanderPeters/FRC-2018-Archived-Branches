package main.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import main.Constants;
import main.HardwareAdapter;
import main.ImprovedSubsystem;

public class Pneumatics extends ImprovedSubsystem implements Constants, HardwareAdapter {
	public static Pneumatics instance;
	private boolean down = false;
	/**
	 * Constructor
	 */
	public Pneumatics() {
		comp.setClosedLoopControl(true);
		shifter.set(EXT);
		shifter.set(OFF);


	}
	

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

	public void initDefaultCommand() {
		setDefaultCommand(null);
	}


	@Override
	public void check() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void zeroSensors() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Pneumatics newInstance() {
		if (instance == null) {
			instance = new Pneumatics();
		}
		return instance;
	}
}
