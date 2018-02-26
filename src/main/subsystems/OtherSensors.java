package main.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;
import main.HardwareAdapter;
import main.commands.driveAlerts.AlertDriver;

public class OtherSensors extends Subsystem implements Constants, HardwareAdapter {
	private boolean elevatorLastState;
	private boolean elevatorCurrentState;
	private boolean IntakeLastState;
	private boolean IntakeCurrentState;
	private Command flashLights = new AlertDriver();

	public OtherSensors() {
	}

	public void check() {
		Elevator();
	}

	private void Elevator() {
		if (elevatorLastState != elevatorCurrentState)
			flashLights.start();
		elevatorLastState = elevatorCurrentState;
	}

	@Override
	protected void initDefaultCommand() {
	}
	
	public boolean isIntakeLastState() {
		return elevatorLastState;
	}

	public void setIntakeLastState(boolean elevatorLastState) {
		this.elevatorLastState = elevatorLastState;
	}

	public void check1() {
		Intake();
	}

	private void Intake() {
		// TODO do we need this pringln
		System.out.println(IntakeCurrentState);
		if (IntakeLastState != IntakeCurrentState)
			flashLights.start();
		IntakeLastState = IntakeCurrentState;
	}
	
	protected void initDefaultCommand1() {
	}

	public boolean isElevatorLastState() {
		return IntakeLastState;
	}

	public void setElevatorLastState(boolean elevatorLastState) {
		this.IntakeLastState = elevatorLastState;
	}
}
