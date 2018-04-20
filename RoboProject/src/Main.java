import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.*;
import lejos.hardware.sensor.*;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;


public class Main {
	public static void main(String[] args) {
		int i = 0;
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S2);
		EV3MediumRegulatedMotor m1 = new EV3MediumRegulatedMotor(MotorPort.A); //KOURA (MEDIUM MOTOR)
		EV3MediumRegulatedMotor m2 = new EV3MediumRegulatedMotor(MotorPort.C); //OHJAUS (MEDIUM MOTOR)
		EV3LargeRegulatedMotor m3 = new EV3LargeRegulatedMotor(MotorPort.B); //AJO (LARGE MOTOR)
		
		//OHJAUS JA KOURA LUONTI
		LCD.drawString("Ohjelma päällä", 0, 1);
		Drive mBmCIrSensor = new Drive(m3, m2);
		Drive mA = new Drive(m1);
		//Värisensorin käyttö
		Colour colourSensor = new Colour(cs); 
		IrsChecker irsChecker = new IrsChecker(irSensor);
		
		while(true) {
			//MODEN VAIHTO
			if(irsChecker.getRemComValue(0) != i && irsChecker.getRemComValue(0) != 0) {
				i = irsChecker.getRemComValue(0);
				LCD.drawString("value " + irsChecker.getRemComValue(0), 0, 1);
			}
			//OHJAUS
			//mBmCIrSensor.driveWithController(sensorValue);
			mBmCIrSensor.driveWithController(irsChecker.getRemComValue(0));
			//VÄRIN MÄÄRITYS
			if(irsChecker.getRemComValue(1) != 9 && irsChecker.getRemComValue(1) != 0){
				colourSensor.setColour(irsChecker.getRemComValue(1),m1);
			}
			//VÄRIN TUNNISTUS
			colourSensor.findColour(m3,m1);
			//EXIT
			if(Button.readButtons()==2) {
				mA.rotateClaw(100);
				irsChecker.close();
				colourSensor.closeColour();
				mA.close();
				break;
			}
		}
	}
}