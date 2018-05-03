import lejos.hardware.motor.EV3MediumRegulatedMotor;

public class Claw {
	private EV3MediumRegulatedMotor mA; //KOURA (MEDIUM MOOTTORI)
	private boolean isClawOpen = false;
	
	public Claw(EV3MediumRegulatedMotor mA) {
		this.mA = mA;
		this.mA.rotate(-100); //AUKI
		this.isClawOpen = true;
	}
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
	public void closeClawMotor () {
		mA.close();
	}
}
