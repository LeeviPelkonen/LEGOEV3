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
		int i = 0, colour = 0, turn = 0, forward = 0;
		//EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S2);
		RegulatedMotor m1 = new EV3MediumRegulatedMotor(MotorPort.A); //KOURA
		RegulatedMotor m2 = new EV3MediumRegulatedMotor(MotorPort.C); //TAKA PYÖRÄ
		RegulatedMotor m3 = new EV3LargeRegulatedMotor(MotorPort.B); //ISO MOOTTORI
		
		//OHJAUS JA KOURA LUONTI
		Drive mBmCIrSensor = new Drive(m3, m2, irSensor);
		Drive mA = new Drive(m1);
		
		Colour colourSensor = new Colour(new EV3ColorSensor(SensorPort.S3)); 

		
		while(true) {
			//MODEN VAIHTO
			if(irSensor.getRemoteCommand(0) != i && irSensor.getRemoteCommand(0) != 0) {
				i = irSensor.getRemoteCommand(0);
				LCD.drawString("value " + irSensor.getRemoteCommand(0), 0, 1);
			}
			//OHJAUS
			//mBmCIrSensor.driveWithController(sensorValue);
			mBmCIrSensor.driveWithController(irSensor.getRemoteCommand(0));
			//VÄRIN MÄÄRITYS
			if(irSensor.getRemoteCommand(1) != 9 && irSensor.getRemoteCommand(1) != 0){
				colourSensor.setColour(irSensor.getRemoteCommand(0));
			}
			//VÄRIN TUNNISTUS
			colourSensor.findColour();
			//EXIT
			if(Button.readButtons()==2) {
				m1.rotate(100);
				irSensor.close();
				colourSensor.closeColour();
				m1.close();
				m2.close();
				m3.close();
				break;
			}
		}
	}
}