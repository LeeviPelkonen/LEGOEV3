import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public class Colour {
	private EV3ColorSensor cs; 
	private int colour;
	
	private Thread t;
	public Colour (EV3ColorSensor cs) {
		this.cs = cs;
	}
	public void setColour (int colour, Claw mA) {
		//V�RIN M��RITYS
		this.colour = colour;
			LCD.drawInt(cs.getColorID(), 0, 5);
			if(colour == 1) {
				LCD.drawString("Green", 0, 3);
				Sound.playTone(500, 500);
				Button.LEDPattern(1);
			}
			if(colour == 2) {
				LCD.drawString("Blue", 0, 3);
				Sound.playTone(500, 500);
				Button.LEDPattern(3);
			}
			if(colour == 3) {
				LCD.drawString("Red", 0, 3);
				Sound.playTone(500, 500);
				Button.LEDPattern(2);
			}
			if(colour == 4) {
				mA.rotateClaw(true);
			}
	}
	public int getColour () {
		return colour;
	}
	//V�RIN TUNNISTUS
	public void findColour (Drive motors ,Claw mA) { //SUURI, KOURA
		switch(cs.getColorID()) {
		case Color.BLUE:
			if (colour == 2) {
				colour = 0;
				mA.rotateClaw(false); //sulje koura & pys�yt� moottori
				motors.stop();
				LCD.clear(4);
				LCD.drawString("BLUE", 0, 4);
				Button.LEDPattern(0);
				t = new Thread(new MusicPlayer());
				t.start();
				
			}
			break;
		case Color.GREEN:
			if (colour == 1) {
				colour = 0;
				mA.rotateClaw(false); //sulje koura & pys�yt� moottori
				motors.stop();
				LCD.clear(4);
				LCD.drawString("GREEN", 0, 4);
				Button.LEDPattern(0);
				t = new Thread(new MusicPlayer());
				t.start();
			}
			break;
		case Color.RED:
			if (colour == 3) {
				colour = 0;
				mA.rotateClaw(false); //sulje koura & pys�yt� moottori
				motors.stop();
				LCD.clear(4);
				LCD.drawString("Red", 0, 4);
				Button.LEDPattern(0);
				t = new Thread(new MusicPlayer());
				t.start();
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