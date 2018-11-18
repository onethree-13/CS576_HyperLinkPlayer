package CS576.utils;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;;

public class timeControl extends JPanel {
	private final int FRAME_RATE = 30;
	private final int TOTAL_FRAME = 9000;
	private int frame = 0;
	private JButton control = new JButton("start");
	private JButton prevTenFrame = new JButton("prev 10f");
	private JButton nextTenFrame = new JButton("next 10f");
	private JTextField frameNum = new JTextField(6);
	private JButton goTo = new JButton("Go to..");
	private Boolean startOrStop = new Boolean(false);
	private JLabel frameDisplay = new JLabel("frame: 0001");
	private Timer timer;

	private rgbPlayer rgb;
	private wavPlayer wav;

	private void play() {
		wav.play();
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				frame++;
				frame %= TOTAL_FRAME;
				frameDisplay.setText("frame: " + String.format("%04d", frame + 1));
				rgb.play(frame);
			}
		}, (int) (1000 / FRAME_RATE), (int) (1000 / FRAME_RATE));
	}

	private void stop() {
		timer.cancel();
		wav.stop();
	}

	private void setFrame(int frame) {
		rgb.play(frame);
		wav.set(frame * 1000000 / FRAME_RATE);
	}

	public timeControl(rgbPlayer rgb, wavPlayer wav) {
		setPreferredSize(new Dimension(800, 80));
		this.wav = wav;
		this.rgb = rgb;
		control.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startOrStop = !startOrStop;
				if (startOrStop) {
					control.setText("stop");
					play();
				} else {
					control.setText("start");
					stop();
				}
			}
		});
		prevTenFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame += TOTAL_FRAME - 10;
				frame %= TOTAL_FRAME;
				frameDisplay.setText("frame: " + String.format("%04d", frame + 1));
				setFrame(frame);

			}
		});
		nextTenFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame += 10;
				frame %= TOTAL_FRAME;
				frameDisplay.setText("frame: " + String.format("%04d", frame + 1));
				setFrame(frame);
			}
		});
		goTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int f = Integer.parseInt(frameNum.getText());
					if (f <= 0 || f > TOTAL_FRAME)
						System.out.println("error: Out of range.");
					frame = f;
					frameDisplay.setText("frame: " + String.format("%04d", frame));
					setFrame(frame);
				} catch (NumberFormatException err) {
					System.out.println("error: Not a number.");
				}
			}
		});
		control.setPreferredSize(new Dimension(80, 30));
		prevTenFrame.setPreferredSize(new Dimension(80, 30));
		nextTenFrame.setPreferredSize(new Dimension(80, 30));
		frameNum.setPreferredSize(new Dimension(80, 30));
		goTo.setPreferredSize(new Dimension(80, 30));
		frameDisplay.setPreferredSize(new Dimension(80, 30));
		add(control);
		add(prevTenFrame);
		add(nextTenFrame);
		add(frameNum);
		add(goTo);
		add(frameDisplay);
	}

	public int getFrameNum() {
		return frame + 1;
	}
}