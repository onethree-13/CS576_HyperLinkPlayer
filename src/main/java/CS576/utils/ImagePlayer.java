package CS576.utils;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ImagePlayer extends JPanel implements ChangeListener, MouseMotionListener {

    public static final int PANEL_DEFAULT_WIDTH = 366;
    public static final int PANEL_DEFAULT_HEIGHT = 385;

    ImagePanel panel;
    JSlider slider;
    JLabel lblFrameStr;
    
    JButton btnPlay;
    JButton btnStop;

    String pathName;
    
    private FrameController frameCtl;
    private Boolean bDragged;
    private Point pt;
    private Rectangle rect;
    
	/**
	 * Create the panel.
	 */
	public ImagePlayer() {
		setLayout(null);
		
		panel = new ImagePanel();
        slider = new JSlider();
		lblFrameStr = new JLabel();
        
        btnPlay = new JButton();
        btnStop = new JButton();

        pathName = null;
        frameCtl = null;
        pt = new Point();
        rect = new Rectangle();

        Initialize();
    }
    
    void Initialize() {
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(6, 6, 352, 288);
        panel.addMouseMotionListener(this);
        add(panel);
        
        slider.setValue(0);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
        slider.setBounds(6, 300, 352, 29);
        slider.addChangeListener(this);
        slider.setValue(0);
        add(slider);

        lblFrameStr.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrameStr.setBounds(6, 326, 352, 16);
        add(lblFrameStr);
        
        btnPlay.setText("Play");
		btnPlay.setBounds(40, 354, 117, 29);
        add(btnPlay);
        
        btnStop.setText("Stop");
		btnStop.setBounds(200, 354, 117, 29);
        add(btnStop);
        
        panel.addMouseMotionListener(this);

        bDragged = false;
    }

    public void stateChanged(ChangeEvent e) {
        if ((JSlider) e.getSource() == slider) {
            if (null == frameCtl) return;
            try {
                lblFrameStr.setText((slider.getValue() + 1) + " th / " + frameCtl.getTotalFrameCnt() + " Total");
                BufferedImage image = frameCtl.getFrameImage(slider.getValue(), panel.getWidth(), panel.getHeight());
                panel.setImage(image);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        System.out.println("Mouse Moved"
            + " (" + e.getX() + "," + e.getY() + ")"
            + " detected on "
            + e.getComponent().getClass().getName());

        if ((ImagePanel)e.getSource() == panel && bDragged) {
            mouseDragged(e);
            bDragged = false;
        }
    }

    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse Dragged"
            + " (" + e.getX() + "," + e.getY() + ")"
            + " detected on "
            + e.getComponent().getClass().getName());

        if ((ImagePanel)e.getSource() == panel) {
            if (null == frameCtl) return;

            try {
                Point ptFrom = pt;
                Point ptTo = new Point(e.getX(), e.getY());
                
                BufferedImage img = frameCtl.getFrameImage(frameCtl.getCurFrameNum(), panel.getWidth(), panel.getHeight());
                if (!bDragged) {
                    pt.setLocation(e.getX(), e.getY());
                    bDragged = true;
                }

                if (ptFrom.x < ptTo.x) {
                    if (ptFrom.y < ptTo.y) {
                        rect.setLocation(ptFrom);
                        rect.setSize(ptTo.x - ptFrom.x, ptTo.y - ptFrom.y);
                    } else {
                        rect.setLocation(ptFrom.x, ptTo.y);
                        rect.setSize(ptTo.x - ptFrom.x, ptFrom.y - ptTo.y);
                    }
                } else {
                    if (ptFrom.y < ptTo.y) {
                        rect.setLocation(ptTo.x, ptFrom.y);
                        rect.setSize(ptFrom.x - ptTo.x, ptTo.y - ptFrom.y);
                    } else {
                        rect.setLocation(ptTo);
                        rect.setSize(ptFrom.x - ptTo.x, ptFrom.y - ptTo.y);
                    }
                }

                Graphics graph = img.getGraphics();
                graph.setColor(Color.CYAN);
                graph.drawRect(rect.x, rect.y, rect.width, rect.height);
                graph.dispose();

                panel.setImage(img);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public int loadImages(String pathName) throws Exception {
        this.pathName = pathName;

        int nbTotalFrame = 0;

        try {
            frameCtl = new FrameController(pathName); 
            nbTotalFrame = frameCtl.getTotalFrameCnt();
            
            panel.setImage(frameCtl.getFrameImage(0, panel.getWidth(), panel.getHeight()));

            slider.setMinimum(0);
            slider.setMaximum(nbTotalFrame - 1);
            lblFrameStr.setText("1 th / " + nbTotalFrame + " Total");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

            throw e;
        }

        return nbTotalFrame;
    }

    public boolean isLoaded() {
        return null != frameCtl;
    }

    public String getPathName() {
        return pathName;
    }

    public Rectangle getDraggedRectangle() {
        return rect;
    }

    public int getCurFrameNum() {
        return frameCtl.getCurFrameNum();
    }
}