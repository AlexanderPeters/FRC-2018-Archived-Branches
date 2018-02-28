package Util;

/**
 * Date: 1/17
 * <p>
 * This is a class that allows you to change the current state of the output
 * each time a button has been pressed.
 * 
 * @author Alexander Peters and Sol Kim
 */
public class LatchedBoolean {
	private boolean currentValue = false;
	private boolean output;

	// allows the user to input data
	public void setInput(boolean input) {
		output = currentValue ^ input;
	}

	// allows the user to get the current state of the output
	public boolean getOutput() {
		return output;
	}
}
