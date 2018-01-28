package lib.joystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XboxController extends Joystick{
	
	/*****************
	 * INSTANCE DATA *
	 *****************/
	// Buttons
	public JoystickButton a;
	public JoystickButton b;
	public JoystickButton x;
	public JoystickButton y;
	public JoystickButton select;
	public JoystickButton start;
	// Thumb-stick buttons
	public JoystickButton leftJoystickButton;
	public JoystickButton rightJoystickButton;
	// Bumpers
	public JoystickButton leftBumper;
	public JoystickButton rightBumper;
	// Triggers
	public Button leftTrigger;
	public Button rightTrigger;

	
	/**
	 * @param port of the controller.
	 */
	public XboxController(int port) {
		super(port);
		a = new JoystickButton(this, 1);
		b = new JoystickButton(this, 2);
		x = new JoystickButton(this, 3);
		y = new JoystickButton(this, 4);
		leftBumper = new JoystickButton(this, 5);
		rightBumper = new JoystickButton(this, 6);
		select = new JoystickButton(this, 7);
		start = new JoystickButton(this, 8);
		leftJoystickButton = new JoystickButton(this, 9);
		rightJoystickButton = new JoystickButton(this, 10);
		leftTrigger = new AnalogButton(this, 2, 0.1);
		rightTrigger = new AnalogButton(this, 3, 0.1);
	}
	
	/**
	 * Gets the X-axis of the left-thumbstick.
	 * @return Value of the X-axis.
	 */
	public double getMainX(){
		return super.getRawAxis(0);
	}
	/**
	 * Gets the Y-axis of the left-thumbstick.
	 * @return Value of the Y-axis.
	 */
	public double getMainY(){
		return super.getRawAxis(1);
	}
	/**
	 * Gets the X-axis of the right-thumbstick.
	 * @return Value of the X-axis.
	 */
	public double getAltX(){
		return super.getRawAxis(4);
	}
	/**
	 * Gets the Y-axis of the right-thumbstick.
	 * @return Value of the Y-axis.
	 */
	public double getAltY(){
		return super.getRawAxis(5);
	}
	
	/**
	 * Gets the X-axis of the left-thumbstick, and smoothens it.
	 * @return Cubed value of the X-axis.
	 */
	public double getSmoothedMainX() {
		//return Math.pow(super.getRawAxis(0), 3) * -1;
		return -Math.sin(Math.PI/2 * super.getRawAxis(0));
	}
	
	/**
	 * Gets the Y-axis of the left-thumbstick, and smoothens it.
	 * @return Cubed value of the Y-axis.
	 */
	public double getSmoothedMainY() {
		//return Math.pow(super.getRawAxis(1), 5);
		return Math.sin(Math.PI/2 * super.getRawAxis(1));
	}
	
	/**
	 * Gets the X-axis of the right thumbstick, and smoothens it.
	 * @return Cubed value of the alternate X-axis.
	 */
	public double getSmoothedAltX() {
		//return Math.pow(super.getRawAxis(4), 3) * -1;
		return -Math.sin(Math.PI/2 * super.getRawAxis(4));
	}
	
	/**
	 * Gets the Y-axis of the right thumbstick, and smoothens it.
	 * @return Cubed value of the alternate Y-axis.
	 */
	public double getSmoothedAltY() {
		//return Math.pow(super.getRawAxis(5), 3);
		return Math.sin(Math.PI/2 * super.getRawAxis(5));
	}
		

}
