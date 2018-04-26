import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.*;
import lejos.hardware.sensor.*;


public class Main {
	public static void main(String[] args) {
		int i = 0;
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S2);
		EV3MediumRegulatedMotor m1 = new EV3MediumRegulatedMotor(MotorPort.A); //KOURA (MEDIUM MOTOR)
		EV3MediumRegulatedMotor m2 = new EV3MediumRegulatedMotor(MotorPort.C); //OHJAUS (MEDIUM MOTOR)
		EV3LargeRegulatedMotor m3 = new EV3LargeRegulatedMotor(MotorPort.B); //AJO (LARGE MOTOR)
		
		//OHJAUS JA KOURA LUONTI
		LCD.drawString("Ohjelma paalla", 0, 1);
		Drive mBmC = new Drive(m3, m2);
		Drive mA = new Drive(m1);
		//VÄRISENSORIN KÄYTTÖ
		Colour colourSensor = new Colour(cs); 
		IrsChecker irsChecker = new IrsChecker(irSensor);
		
		while(true) {
			//MODEN VAIHTO
			if(irsChecker.getRemComValue(0) != i && irsChecker.getRemComValue(0) != 0) {
				i = irsChecker.getRemComValue(0);
				LCD.drawString("value " + irsChecker.getRemComValue(0), 0, 1);
			}
			//OHJAUS
			mBmC.driveWithController(irsChecker.getRemComValue(0));
			//VÄRIN MÄÄRITYS
			if(irsChecker.getRemComValue(1) != 9 && irsChecker.getRemComValue(1) != 0){
				colourSensor.setColour(irsChecker.getRemComValue(1),mA);
			}
			//VÄRIN TUNNISTUS
			colourSensor.findColour(mBmC, mA);
			//EXIT
			if(Button.readButtons()==2) {
				mA.rotateClaw(false);
				irsChecker.close();
				colourSensor.closeColour();
				mBmC.closeDrivingMotors();
				mA.closeClawMotor();
				break;
			}
		}
	}
}