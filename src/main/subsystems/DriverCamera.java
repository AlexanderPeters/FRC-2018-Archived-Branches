package main.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class DriverCamera {
	// TODO put private/public based on need, or remove both of them
	CameraServer camServer;
	UsbCamera cam;

	public DriverCamera() {
		camServer = CameraServer.getInstance();
		cam = camServer.startAutomaticCapture();
		cam.setFPS(30);
		cam.setResolution(320, 240);
	}
}
