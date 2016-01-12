package res;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import me.cameronwitcher.upsilon.utils.Sound;

public class Audio {
	
	public static synchronized void playSound(final Sound sound) {
		new Thread(new Runnable() {
			
			// The wrapper thread is unnecessary, unless it blocks on the
			// Clip finishing; see comments.
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(Audio.class.getResourceAsStream("/res/sounds/" + sound.getSoundString()));
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}
	
	
}
