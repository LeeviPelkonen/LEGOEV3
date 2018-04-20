import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.*;
import lejos.hardware.sensor.*;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;


public class Colour {
	public static void main(String[] args) {
		int i = 0, colour = 0, turn = 0, forward = 0;
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S2);
		RegulatedMotor m1 = new EV3MediumRegulatedMotor(MotorPort.A);
		RegulatedMotor m2 = new EV3MediumRegulatedMotor(MotorPort.C);
		RegulatedMotor m3 = new EV3LargeRegulatedMotor(MotorPort.B);
		m3.setSpeed(500);
		m1.rotate(-100);

		
		while(true) {
			//MODEN VAIHTO
			if(irSensor.getRemoteCommand(0) != i && irSensor.getRemoteCommand(0) != 0) {
				i = irSensor.getRemoteCommand(0);
				LCD.drawString("value " + irSensor.getRemoteCommand(0), 0, 1);
			}
			//OHJAUS
			if(irSensor.getRemoteCommand(0)!= 0){
				if(irSensor.getRemoteCommand(0) == 1 && turn < 4) {
					m2.rotate(15);
					turn++;
				}
				if(irSensor.getRemoteCommand(0) == 2 && turn > -4) {
					m2.rotate(-15);
					turn--;
				}
				if(irSensor.getRemoteCommand(0) == 3) {
					if(forward<0) {
						m3.stop();
						forward=0;
					}
					else {
						m3.forward();
						forward=1;
					}
				}
				if(irSensor.getRemoteCommand(0) == 4) {
					if(forward>0) {
						m3.stop();
						forward=0;
					}
					else {
						m3.backward();
						forward=-1;
					}
				}
				if(irSensor.getRemoteCommand(0) == 9) {
					turn *= 10;
					m2.rotate(-turn);
					turn = 0;
				}
			}
			//VÄRIN MÄÄRITYS
			if(irSensor.getRemoteCommand(1) != 9 && irSensor.getRemoteCommand(1) != 0){
				colour = irSensor.getRemoteCommand(1);
				LCD.drawInt(cs.getColorID(), 0, 5);
				if(colour == 1) {
					LCD.drawString("Green", 0, 3);
				}
				if(colour == 2) {
					LCD.drawString("Blue", 0, 3);
				}
				if(colour == 3) {
					LCD.drawString("Red", 0, 3);
				}
				if(colour == 4) {
					m1.rotate(-100);
				}
			}
			//VÄRIN TUNNISTUS
			switch(cs.getColorID()) {
    		case Color.BLUE:
    			if (colour == 2) {
    				colour = 0;
    				m1.rotate(100);
    				m3.stop();
    				LCD.clear(4);
    				LCD.drawString("BLUE", 0, 4);
    			}
    			break;
    		case Color.GREEN:
    			if (colour == 1) {
    				colour = 0;
    				m1.rotate(100);
    				m3.stop();
    				LCD.clear(4);
    				LCD.drawString("GREEN", 0, 4);
    			}
    			break;
    		case Color.RED:
    			if (colour == 3) {
    				colour = 0;
    				m1.rotate(100);
    				m3.stop();
    				LCD.clear(4);
    				LCD.drawString("Red", 0, 4);
    			}
    			break;
    		default:
    			LCD.clear(4);
			}
			//EXIT
			if(Button.readButtons()==2) {
				m1.rotate(100);
				irSensor.close();
				cs.close();
				m1.close();
				m2.close();
				m3.close();
				break;
			}
		}
	}
}
