import lejos.hardware.Sound;

public class MusicPlayer implements Runnable {
	   
	   private int c;
	   private int d;
	   private int e;
	   private int f;
	   private int g;
	   private int a;
	   private int b;
	   private boolean playing;
	   
	   public MusicPlayer() {
		   this.c = 261;
		   this.d = 294;
		   this.e = 330;
		   this.f = 349;
		   this.g = 392;
		   this.a = 440;
		   this.b = 494;
		   this.playing = true;
		   
	   }

	public void run() {
		Sound.setVolume(15);
		while (playing) {
			Sound.playNote(Sound.PIANO, c, 200);   
			Sound.playNote(Sound.PIANO, c, 200);
			Sound.playNote(Sound.PIANO, c, 200);
			Sound.playNote(Sound.PIANO, d, 200);
			Sound.playNote(Sound.PIANO, e, 400);
			Sound.pause(50);
			Sound.playNote(Sound.PIANO, e, 400);
			Sound.pause(200);
			   
			Sound.playNote(Sound.PIANO, d, 200);
		    Sound.playNote(Sound.PIANO, c, 200);
		    Sound.playNote(Sound.PIANO, d, 200);
		    Sound.playNote(Sound.PIANO, e, 200);   
		    Sound.playNote(Sound.PIANO, c, 400);
		   
		   
		   
		   
		   
		   Sound.playNote(Sound.PIANO, e, 150);
		   Sound.playNote(Sound.PIANO, e, 150);
		   Sound.playNote(Sound.PIANO, e, 150);
		   Sound.playNote(Sound.PIANO, f, 150);
		   Sound.playNote(Sound.PIANO, g, 300);
		   Sound.playNote(Sound.PIANO, g, 300);
		   Sound.pause(100);
		   
		   Sound.playNote(Sound.PIANO, f, 150);   
		   Sound.playNote(Sound.PIANO, e, 150);
		   Sound.playNote(Sound.PIANO, f, 150);
		   Sound.playNote(Sound.PIANO, g, 150);
		   Sound.playNote(Sound.PIANO, e, 400);  
		   
		 
		   
		   
		   
		   //Verse 2
		   Sound.playNote(Sound.PIANO, c, 300);
		   Sound.playNote(Sound.PIANO, c, 150);
		   Sound.playNote(Sound.PIANO, c, 150);   
		   Sound.playNote(Sound.PIANO, b, 300);
		   Sound.playNote(Sound.PIANO, b, 300);
		   Sound.pause(150);
		   Sound.playNote(Sound.PIANO, a, 150);
		   Sound.playNote(Sound.PIANO, a, 150);   
		   Sound.playNote(Sound.PIANO, a, 150);
		   Sound.playNote(Sound.PIANO, a, 150);
		   Sound.playNote(Sound.PIANO, g, 300);
		   
		   Sound.playNote(Sound.PIANO, c, 150);   
		   Sound.playNote(Sound.PIANO, c, 150);
		   Sound.playNote(Sound.PIANO, c, 150);
		   Sound.playNote(Sound.PIANO, d, 150);
		   Sound.playNote(Sound.PIANO, e, 300);
		   Sound.pause(50);
		   Sound.playNote(Sound.PIANO, e, 300);
		   Sound.pause(200);
		   
		   Sound.playNote(Sound.PIANO, d, 150);
		   Sound.playNote(Sound.PIANO, c, 150);
		   Sound.playNote(Sound.PIANO, d, 150);
		   Sound.playNote(Sound.PIANO, e, 150);   
		   Sound.playNote(Sound.PIANO, c, 400);
		   return;
		}
	}
	public void stopPlaying() {
		playing = false;
	}
	
	public void continuePlaying() {
		playing = true;
	}
}