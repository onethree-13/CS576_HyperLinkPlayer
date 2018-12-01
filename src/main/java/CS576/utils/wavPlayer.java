package CS576.utils;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class WAVPlayer {
	private AudioInputStream ais;
	private AudioFormat format;
	private DataLine.Info info;
	private Clip clip;

	public WAVPlayer(String filename) {
		openFile(filename);
	}

	public WAVPlayer() {
	}

	public void openFile(String filename) {
		try {
			ais = AudioSystem.getAudioInputStream(new File(filename));
			format = ais.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
		} catch (Exception e) {
			System.out.println("cannot play wav file:" + filename);
		}
		// System.out.println("audio length: " + clip.getFrameLength() + ", " + clip.getMicrosecondLength());
	}

	public void play() {
		clip.start();
	}

	public void stop() {
		clip.stop();
	}

	public void set(long us) {
		clip.stop();
		clip.setMicrosecondPosition(us);
	}
}