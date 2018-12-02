package CS576.utils;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TimeControl extends JPanel {
	private final int FRAME_RATE = 30;
	private final int TOTAL_FRAME = 9000;
	private int frame = 0;
	private JButton control = new JButton("start");
	private JTextField frameNum = new JTextField(6);
	private JButton goTo = new JButton("Go to..");
	private Boolean startOrStop = new Boolean(false);
	private JSlider slider = new JSlider();
	private Boolean sliderSetValue = false;
	private JButton hide = new JButton("hide");
	private Boolean hideOrShow = true;
	private Timer timer = new Timer();

	private RGBPlayer rgb;
	private WAVPlayer wav;
	private DisplayInfo info;

	public void play() {
		startOrStop = false;
		wav.set((long) ((long) frame * 1000000L / (long) FRAME_RATE));
		timer.cancel();
		timer = new Timer();
		control.setText("stop");
		wav.play();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				frame++;
				if (frame == TOTAL_FRAME) {
					frame = 0;
					setFrame(frame);
					stop();
					return;
				}
				rgb.play(frame);
				sliderSetValue = true;
				slider.setValue(frame);
				sliderSetValue = false;
				info.setFrame(frame);
			}
		}, (int) (1000 / FRAME_RATE), (int) (1000 / FRAME_RATE));
	}

	public void stop() {
		startOrStop = true;
		control.setText("start");
		timer.cancel();
		wav.stop();
	}

	private void setFrame(int frame) {
		rgb.play(frame);
		if (!sliderSetValue) {
			sliderSetValue = true;
			slider.setValue(frame);
			sliderSetValue = false;
		}
		info.setFrame(frame);
	}

	public void set(int frame) {
		this.frame = frame;
		wav.set((long) ((long) frame * 1000000L / (long) FRAME_RATE));
		setFrame(frame);
	}

	public TimeControl(RGBPlayer rgb, WAVPlayer wav, DisplayInfo info) {
		this.wav = wav;
		this.rgb = rgb;
		this.info = info;

		setPreferredSize(new Dimension(352, 60));
		setLayout(null);

		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setBounds(0, 0, 352, 30);
		slider.setValue(frame);
		slider.setMinimum(0);
		slider.setMaximum(TOTAL_FRAME - 1);
		add(slider);
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!sliderSetValue) {
					startOrStop = false;
					stop();
					frame = slider.getValue();
					setFrame(frame);
				}
			}
		});

		control.setBounds(0, 30, 82, 30);
		control.setPreferredSize(new Dimension(82, 30));
		add(control);
		control.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (startOrStop)
					play();
				else
					stop();
			}
		});

		frameNum.setBounds(90, 30, 82, 30);
		frameNum.setPreferredSize(new Dimension(82, 30));
		add(frameNum);

		goTo.setBounds(180, 30, 82, 30);
		goTo.setPreferredSize(new Dimension(82, 30));
		add(goTo);
		goTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int f = Integer.parseInt(frameNum.getText());
					if (f <= 0 || f > TOTAL_FRAME)
						System.out.println("error: Out of range.");
					frame = f - 1;
					setFrame(frame);
				} catch (NumberFormatException err) {
					System.out.println("error: Not a number.");
				}
			}
		});

		hide.setBounds(270, 30, 82, 30);
		hide.setPreferredSize(new Dimension(82, 30));
		add(hide);
		hide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideOrShow = !hideOrShow;
				rgb.setHideOrShow(hideOrShow);
				if (hideOrShow) {
					hide.setText("hide");
					System.out.println("Showing hyperlinks.");
				} else {
					hide.setText("show");
					System.out.println("Hiding hyperlinks.");
				}
				setFrame(frame);
			}
		});
	}

	public int getFrameNum() {
		return frame + 1;
	}
}
