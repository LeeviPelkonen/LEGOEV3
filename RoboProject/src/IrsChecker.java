import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class IrsChecker extends Thread {
	private EV3IRSensor infraredSensor;
	private int i;
	public IrsChecker(EV3IRSensor sensor) {
		this.infraredSensor = sensor;
	}

	// THIS IS RUN WHEN THE THREAD IS STARTED
	public void run() {
		while (!Button.ENTER.isDown()) {
			int remoteCommand = infraredSensor.getRemoteCommand(0);
			if (remoteCommand != 0) {
				LCD.drawString("Painoit: " + remoteCommand, 0, 0);
				Delay.msDelay(500);
			}
		}
	}
	// METHOD TO GET REMOTECONTROLLER BUTTON VALUE WHEN PRESSED
	public int getRemComValue(int channel) {
		int remoteCommand = infraredSensor.getRemoteCommand(channel);
		return remoteCommand;
	}
	public void close () {
		infraredSensor.close();
	}
	// METHOD TO GET DISTANCE FROM OBJECTS NEAR SENSOR
	public float getDistance() {
		SampleProvider distance= infraredSensor.getDistanceMode();
		float[] distSample = new float[distance.sampleSize()];
		distance.fetchSample(distSample, 0);
		return distSample[0];
	}
}