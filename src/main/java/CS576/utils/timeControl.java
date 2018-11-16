package CS576.utils;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.*;
import java.lang.*;
import java.util.Timer;
import java.util.TimerTask;;

public class timeControl extends JPanel {
	private static String start = new String("Start");
	private static String stop = new String("Stop ");
	private static int frameRate = 30;
	private int frame = 0;
	private static int totalFrames = 9000;
	private JButton control = new JButton(start);
	private JButton prevTenFrame = new JButton("prev 10f");
	private JButton nextTenFrame = new JButton("next 10f");
	private JTextField frameNum = new JTextField(6);
	private JButton goTo = new JButton("Go to..");
	private Boolean startOrStop = new Boolean(false);
	private JLabel frameDisplay = new JLabel("frame: 0001");
	private Timer timer;

	public timeControl(rgbPlayer player) {
		setPreferredSize(new Dimension(800, 80));

		control.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startOrStop = !startOrStop;
				if (startOrStop) {
					control.setText(stop);
					timer = new Timer();
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							frame++;
							frame %= totalFrames;
							frameDisplay.setText("frame: " + String.format("%04d", frame + 1));
							player.load(frame);
							player.repaint();
						}
					}, (int) (1000 / frameRate), (int) (1000 / frameRate));
				} else {
					control.setText(start);
					timer.cancel();
				}
			}
		});
		prevTenFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame += totalFrames - 10;
				frame %= totalFrames;
				frameDisplay.setText("frame: " + String.format("%04d", frame + 1));
				player.load(frame);
				player.repaint();

			}
		});
		nextTenFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame += 10;
				frame %= totalFrames;
				frameDisplay.setText("frame: " + String.format("%04d", frame + 1));
				player.load(frame);
				player.repaint();
			}
		});
		goTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int f = Integer.parseInt(frameNum.getText());
					if (f <= 0 || f > totalFrames)
						System.out.println("error: Out of range.");
					frame = f;
					frameDisplay.setText("frame: " + String.format("%04d", frame));
					player.load(frame);
					player.repaint();
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