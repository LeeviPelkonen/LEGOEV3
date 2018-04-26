import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;

public class Drive {
	private EV3MediumRegulatedMotor mA; //KOURA (MEDIUM MOOTTORI)
	private EV3LargeRegulatedMotor mB; //AJAJA (SUURI MOOTTORI)
	private EV3MediumRegulatedMotor mC; //KÄÄNTÄJÄ (MEDIUM MOOTTORI)
	private int turn = 0;
	private int forward = 0;
	private boolean isClawOpen = false;
	//private EV3IRSensor irSensor;
	
	//KONSTRUKTORI OHJAUKSELLE
	public Drive(EV3LargeRegulatedMotor mB, EV3MediumRegulatedMotor mC) { //SUURI, TAKA, IRSENROR
		this.mB = mB;
		this.mB.setSpeed(500);
		this.mC = mC;
		//this.irSensor = irSensor;
	}
	//KONSTRUKTORI KOURALLE
	public Drive(EV3MediumRegulatedMotor mA) {
		this.mA = mA;
		this.mA.rotate(-100); //AUKI
		this.isClawOpen = true;
	}
	//KOURA METODI
	/*public void handClose() {
		mA.rotate(100);
	}*/
	public void rotateClaw (boolean isClawOpen) {
		
		if(isClawOpen == true && this.isClawOpen == false) {
		mA.rotate(-100); //AUKI
		this.isClawOpen = true;
		} 
		
		if (isClawOpen == false && this.isClawOpen == true) {
		mA.rotate(100); //KIINNI
		this.isClawOpen = false;
		}
	}
	public void stop () {
		mB.stop(true);
	}
	public void closeDrivingMotors () { //CLOSE AJAMIS MOOTTORIT
		mB.close();
		mC.close();
	}
	public void closeClawMotor () {
		mA.close();
	}
	//OHJAUS METODI
	public void driveWithController(int sensorValue) {
		if(sensorValue!= 0){
			if(sensorValue == 1 && turn < 4) {
				mC.rotate(15);
				turn++;
			}
			if(sensorValue == 2 && turn > -4) {
				mC.rotate(-15);
				turn--;
			}
			if(sensorValue == 3) {
				if(forward<0) {
					mB.stop();
					forward=0;
				}
				else {
					mB.forward();
					forward=1;
				}
			}
			if(sensorValue == 4) {
				if(forward>0) {
					mB.stop();
					forward=0;
				}
				else {
					mB.backward();
					forward=-1;
				}
			}
			if(sensorValue == 9) {
				mC.rotate((int) -mC.getPosition());
				turn = 0;
			}
		}
	}
	public void returnHome(IrsChecker getDirection){
		int i = 0;
		mC.rotate((int) -mC.getPosition());
		mB.backward();
		while(true) {
			LCD.drawString("DIREKTIO: "+getDirection.getDirection(), 0, 5);
			if(i<getDirection.getDirection()) {
				mC.rotate(-8);
				i=i+5;
			}
			if(i>getDirection.getDirection()) {
				mC.rotate(8);
				i=i-5;
			}
			if(getDirection.getDistance()<10) {
				mB.stop();
				break;
			}
		}
	}
}
