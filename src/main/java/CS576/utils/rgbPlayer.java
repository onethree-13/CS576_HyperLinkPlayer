package CS576.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;

public class RGBPlayer extends JPanel implements MouseMotionListener, MouseListener {

	private BufferedImage bi = new BufferedImage(352, 288, BufferedImage.TYPE_INT_RGB);
	private String pathname = null;
	private DisplayInfo info;
	private Point point = new Point(0, 0);
	private LinkInfoMap map;
	private int num = 0;
	private LinkInfo linkInfo = null;
	private FileControl fctrl;
	private TimeControl tctrl;
	private Boolean hideOrShow = true;

	public RGBPlayer() {
		setPreferredSize(new Dimension(352, 288));
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	public void PanelLink(LinkInfoMap map, DisplayInfo info, FileControl fctrl, TimeControl tctrl) {
		this.map = map;
		this.info = info;
		this.fctrl = fctrl;
		this.tctrl = tctrl;
	}

	public void mouseMoved(MouseEvent e) {
		point.x = e.getX();
		point.y = e.getY();
		info.setMouseX(e.getX());
		info.setMouseY(e.getY());
		for (LinkInfo linkInfo : map.getLinkInfo(num + 1)) {
			if (LinkInfo2Rect(linkInfo).contains(point)) {
				this.linkInfo = linkInfo;
				info.setLinkInfo(linkInfo);
				return;
			}
		}
		this.linkInfo = null;
		info.setLinkInfo(new LinkInfo());
	}

	public void mouseDragged(MouseEvent e) {
	}

	public Rectangle LinkInfo2Rect(LinkInfo linkInfo) {
		return new Rectangle(linkInfo.getBoundaryX(), linkInfo.getBoundaryY(), linkInfo.getBoundaryWidth(),
				linkInfo.getBoundaryHeight());
	}

	LinkInfo getLinkInfo() {
		return new LinkInfo();
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		if (linkInfo != null && hideOrShow) {
			tctrl.stop();
			fctrl.openFile(linkInfo.getDestPathName());
			tctrl.set(linkInfo.getDestFrameNum());
			tctrl.play();
		}
	}

	public void openFile(String pathname) {
		this.pathname = pathname;
		play(num);
	}

	public void drawHyperBoxes() {
		List<LinkInfo> list;
		try {
			list = map.getLinkInfo(num + 1);
			Graphics graph = bi.getGraphics();
			graph.setColor(Color.MAGENTA);
			for (LinkInfo linkInfo : list) {
				graph.drawRect(linkInfo.getBoundaryX(), linkInfo.getBoundaryY(), linkInfo.getBoundaryWidth(),
						linkInfo.getBoundaryHeight());
			}
			graph.dispose();
		} catch (Exception e) {
			System.out.println("No hyperlink file: " + pathname + ".json");
		}
	}

	public void drawImage() {
		byte[] data = new byte[288 * 352 * 3];
		try {
			FileInputStream fis = new FileInputStream(pathname + String.format("%04d", num + 1) + ".rgb");
			fis.read(data);
			fis.close();
		} catch (IOException e) {
			System.out.println("No rgb file:" + pathname + String.format("%04d", num + 1) + ".rgb");
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
	}

	public void play(int num) {
		this.num = num;
		drawImage();
		if (hideOrShow)
			drawHyperBoxes();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bi, 0, 0, this);
	}

	public void setHideOrShow(Boolean hideOrShow) {
		this.hideOrShow = hideOrShow;
	}
}
