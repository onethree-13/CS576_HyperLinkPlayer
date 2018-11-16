package CS576.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JPanel;

public class rgbPlayer extends JPanel {

	private BufferedImage bi;
	private String filename;

	public rgbPlayer(String filename) {
		this.filename = filename;
		bi = new BufferedImage(352, 288, BufferedImage.TYPE_INT_RGB);
		setPreferredSize(new Dimension(352, 288));
	}

	public void openFile(String filename) {
		this.filename = filename;
	}

	public void load(int num) {
		int[][][] img = new int[288][352][3];
		try {
			System.out.println(filename + String.format("%04d", num) + ".rgb");
			FileInputStream fis = new FileInputStream(filename + String.format("%04d", num) + ".rgb");
			for (int rgb = 0; rgb < 3; rgb++)
				for (int y = 0; y < 288; y++)
					for (int x = 0; x < 352; x++)
						img[y][x][rgb] = fis.read() & 0xff;
			fis.close();
		} catch (IOException e) {
			System.out.println("error: No such file.");
		}

		for (int y = 0; y < 288; y++) {
			for (int x = 0; x < 352; x++) {
				Color col = new Color(img[y][x][0], img[y][x][1], img[y][x][2]);
				bi.setRGB(x, y, col.getRGB());
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bi, 0, 0, this);
	}
}
