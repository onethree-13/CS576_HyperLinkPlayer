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
	private Boolean isOpened = false;

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
			isOpened = true;
		} catch (Exception e) {
			isOpened = false;
			System.out.println("NO wav file:" + filename);
		}
	}

	public void play() {
		if (isOpened) {
			clip.start();
		} else
			System.out.println("NO wav file.");
	}

	public void stop() {
		if (isOpened)
			clip.stop();
	}

	public void set(long us) {
		if (isOpened) {
			clip.stop();
			clip.setMicrosecondPosition(us);
		} else
			System.out.println("NO wav file.");
	}
}
