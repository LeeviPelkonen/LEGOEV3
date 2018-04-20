
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;

public class Colour {
	private EV3ColorSensor cs; 
	private int colour;
	//private Drive clawMotor = new Drive(new EV3MediumRegulatedMotor(MotorPort.A));
	//private Drive driveMotor = new Drive(new EV3LargeRegulatedMotor(MotorPort.B), new EV3MediumRegulatedMotor(MotorPort.C));
	public Colour (EV3ColorSensor cs) {
		this.cs = cs;
	}
	public void setColour (int colour, RegulatedMotor mA) {
		//VÄRIN MÄÄRITYS
		this.colour = colour;
			LCD.drawInt(cs.getColorID(), 0, 5);
			if(colour == 1) {
				LCD.drawString("Green", 0, 3);
				Sound.playTone(500, 500);
			}
			if(colour == 2) {
				LCD.drawString("Blue", 0, 3);
				Sound.playTone(500, 500);
			}
			if(colour == 3) {
				LCD.drawString("Red", 0, 3);
				Sound.playTone(500, 500);
			}
			if(colour == 4) {
				mA.rotate(-100);
			}
	}
	public int getColour () {
		return colour;
	}
	//VÄRIN TUNNISTUS
	public void findColour (RegulatedMotor mB, RegulatedMotor mA) { //SUURI, KOURA
		switch(cs.getColorID()) {
		case Color.BLUE:
			if (colour == 2) {
				colour = 0;
				mA.rotate(-100); //sulje koura & pysäytä moottori
				mB.stop();
				LCD.clear(4);
				LCD.drawString("BLUE", 0, 4);
				Sound.playTone(500, 100);
				Sound.playTone(400, 100);
				Sound.playTone(600, 100);
				
			}
			break;
		case Color.GREEN:
			if (colour == 1) {
				colour = 0;
				mA.rotate(-100); //sulje koura & pysäytä moottori
				mB.stop();
				LCD.clear(4);
				LCD.drawString("GREEN", 0, 4);
				Sound.playTone(500, 100);
				Sound.playTone(400, 100);
				Sound.playTone(600, 100);
			}
			break;
		case Color.RED:
			if (colour == 3) {
				colour = 0;
				mA.rotate(-100); //sulje koura & pysäytä moottori
				mB.stop();
				LCD.clear(4);
				LCD.drawString("Red", 0, 4);
				Sound.playTone(500, 100);
				Sound.playTone(400, 100);
				Sound.playTone(600, 100);
			}
			break;
		default:
			LCD.clear(4);
		}
	}
	public void closeColour () {
		cs.close();
		Sound.playTone(500, 100);
		Sound.playTone(400, 100);
		Sound.playTone(300, 100);
		Sound.playTone(200, 100);
		Sound.playTone(100, 100);
	}
	
}

