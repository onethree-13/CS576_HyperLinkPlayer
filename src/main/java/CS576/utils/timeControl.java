package CS576.utils;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.*;
import java.lang.*;

public class timeControl extends JPanel {
	private static String start = new String("Start");
	private static String stop = new String("Stop ");
	private int frame = 0;
	private static int totalFrames = 9000;
	private JButton control = new JButton(start);
	private JButton nextTenFrame = new JButton("next 10f");
	private JButton prevTenFrame = new JButton("prev 10f");
	private JTextField frameNum = new JTextField(6);
	private JButton goTo = new JButton("Go to..");
	private Boolean startOrStop = new Boolean(false);

	public timeControl() {
		setPreferredSize(new Dimension(352, 100));

		control.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startOrStop = !startOrStop;
				control.setText(startOrStop ? stop : start);
			}
		});
		nextTenFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame += 10;
				frame %= totalFrames;
			}
		});
		prevTenFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame += totalFrames - 10;
				frame %= totalFrames;
			}
		});
		goTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int f = Integer.parseInt(frameNum.getText());
					frame = f;
				} catch (NumberFormatException err) {
					System.out.println("error: Not a number.");
				}
			}
		});

		add(control);
		add(nextTenFrame);
		add(prevTenFrame);
		add(frameNum);
		add(goTo);
	}

}