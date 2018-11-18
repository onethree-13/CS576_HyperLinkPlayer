package CS576.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JPanel;

public class rgbPlayer extends JPanel {

	private BufferedImage bi = new BufferedImage(352, 288, BufferedImage.TYPE_INT_RGB);
	private String pathname;

	public rgbPlayer(String filename) {
		openFile(filename);
		setPreferredSize(new Dimension(352, 288));
	}

	public rgbPlayer() {
		setPreferredSize(new Dimension(352, 288));
	}

	public void openFile(String pathname) {
		this.pathname = pathname;
		play(1);
	}

	public void play(int num) {
		byte[] data = new byte[288 * 352 * 3];
		try {
			FileInputStream fis = new FileInputStream(pathname + String.format("%04d", num) + ".rgb");
			fis.read(data);
			fis.close();
		} catch (IOException e) {
			System.out.println("cannot play rgb file:" + pathname + num);
		}

		for (int y = 0; y < 288; y++) {
			for (int x = 0; x < 352; x++) {
				try {
					Color col = new Color((int) data[x + 352 * y] & 0xFF, (int) data[x + 352 * y + 288 * 352] & 0xFF,
							(int) data[x + 352 * y + 288 * 352 * 2] & 0xFF);
					bi.setRGB(x, y, col.getRGB());
				} catch (IllegalArgumentException e) {
					System.out.println(e);
				}
			}
		}
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bi, 0, 0, this);
	}

}
