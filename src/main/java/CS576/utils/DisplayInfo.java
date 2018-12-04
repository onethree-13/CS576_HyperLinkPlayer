package CS576.utils;

import java.awt.Dimension;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayInfo extends JPanel {
	private JLabel frame = new JLabel("frame: 0000");
	private JLabel time = new JLabel("time: 00:00");
	private JLabel mouseX = new JLabel("mouse X: ");
	private JLabel mouseY = new JLabel("mouse Y: ");
	private JLabel object = new JLabel("object: ");
	private JLabel destination = new JLabel("destination: ");
	private int mouseXpos = 0;
	private int mouseYpos = 0;

	public DisplayInfo() {
		setLayout(null);
		setPreferredSize(new Dimension(300, 210));
		frame.setBounds(0, 0, 300, 30);
		frame.setSize(new Dimension(300, 30));
		add(frame);
		time.setBounds(0, 30, 300, 30);
		time.setSize(new Dimension(300, 30));
		add(time);
		mouseX.setBounds(0, 60, 300, 30);
		mouseX.setSize(new Dimension(300, 30));
		add(mouseX);
		mouseY.setBounds(0, 90, 300, 30);
		mouseY.setSize(new Dimension(300, 30));
		add(mouseY);
		object.setBounds(0, 120, 300, 30);
		object.setSize(new Dimension(300, 30));
		add(object);
		destination.setBounds(0, 150, 300, 30);
		destination.setSize(new Dimension(300, 30));
		add(destination);
	}

	public void setMouseX(int mouseXpos) {
		mouseX.setText("mouse X: " + mouseXpos);
	}

	public void setMouseY(int mouseYpos) {
		mouseY.setText("mouse Y: " + mouseYpos);
	}

	public void setFrame(int frameNum) {
		frame.setText("frame: " + String.format("%04d", frameNum + 1));
		long millis = (long) frameNum * 1000L / 30L;
		String hms = String.format("%02d:%02d",
				TimeUnit.MILLISECONDS.toMinutes(millis)
						- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
				TimeUnit.MILLISECONDS.toSeconds(millis)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
		time.setText("time: " + hms);
	}

	public void setLinkInfo(LinkInfo linkInfo) {
		object.setText("object: " + linkInfo.getObject());
		destination.setText("destination: " + linkInfo.getDestPathName());
	}

}