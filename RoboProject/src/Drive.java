import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;

public class Drive {
	private RegulatedMotor mA; //KOURA (MEDIUM MOOTTORI)
	private RegulatedMotor mB; //AJAJA (SUURI MOOTTORI)
	private RegulatedMotor mC; //KÄÄNTÄJÄ (MEDIUM MOOTTORI)
	private int turn = 0;
	private int forward = 0;
	private EV3IRSensor irSensor;
	
	//KONSTRUKTORI OHJAUKSELLE
	public Drive(RegulatedMotor mB, RegulatedMotor mC, EV3IRSensor irSensor) { //SUURI, TAKA, IRSENROR
		this.mB = mB;
		this.mB.setSpeed(500);
		this.mC = mC;
		this.irSensor = irSensor;
	}
	//KONSTRUKTORI KOURALLE
	public Drive(RegulatedMotor mA) {
		this.mA = mA;
		this.mA.rotate(-100);
	}
	
	
	//KOURA METODI
	public void handClose() {
		mA.rotate(100);
	}
	public void rotateClaw (int angle) {
		mA.rotate(angle);
	}
	public void stop () {
		mB.stop(true);
	}
	//OHJAUS METODI
	public void driveWithController(int sensorValue) {
		if(irSensor.getRemoteCommand(0)!= 0){
			if(irSensor.getRemoteCommand(0) == 1 && turn < 4) {
				mC.rotate(15);
				turn++;
			}
			if(irSensor.getRemoteCommand(0) == 2 && turn > -4) {
				mC.rotate(-15);
				turn--;
			}
			if(irSensor.getRemoteCommand(0) == 3) {
				if(forward<0) {
					mB.stop();
					forward=0;
				}
				else {
					mB.forward();
					forward=1;
				}
			}
			if(irSensor.getRemoteCommand(0) == 4) {
				if(forward>0) {
					mB.stop();
					forward=0;
				}
				else {
					mB.backward();
					forward=-1;
				}
			}
			if(irSensor.getRemoteCommand(0) == 9) {
				turn *= 10;
				mC.rotate(-turn);
				turn = 0;
			}
		}
	}
}
