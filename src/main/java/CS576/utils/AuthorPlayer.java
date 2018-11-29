package CS576.utils;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect2d;

public class AuthorPlayer extends ImagePlayer implements MouseMotionListener, ImagePanelEventListener {

    private MotionTracker tracker;
    
    private Point pt;
    private Rectangle rect;

    private Boolean bDragged;

    private HashMap<Integer, Rectangle> hm;
    
    /**
	 * Create the panel.
	 */
	public AuthorPlayer() {
        super();

        tracker = new MotionTracker();
        pt = new Point();
        rect = new Rectangle();

        Initialize();
    }

    @Override
    public void Initialize() {
        super.Initialize();

        setMouseMotionListener(this);
        getPanel().setImagePanelEventListener(this);

        tracker.createTracker(MotionTracker.TRACKER_CSRT);
        
        bDragged = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("Mouse Moved"
            + " (" + e.getX() + "," + e.getY() + ")"
            + " detected on "
            + e.getComponent().getClass().getName());
            
        if ((ImagePanel)e.getSource() == getPanel() && bDragged) {
            mouseDragged(e);
            bDragged = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse Dragged"
            + " (" + e.getX() + "," + e.getY() + ")"
            + " detected on "
            + e.getComponent().getClass().getName());

        if ((ImagePanel)e.getSource() == getPanel()) {
            if (!isLoaded()) return;

            try {
                Point ptFrom = pt;
                Point ptTo = new Point(e.getX(), e.getY());
                
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

                updateImage();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void beforeDrawImage(BufferedImage image) {
        if (0 == rect.getWidth() || 0 == rect.getHeight()) return;

        if (null != hm && hm.containsKey(getCurFrameNum())) {
            drawRectangle(image, hm.get(getCurFrameNum()), Color.MAGENTA);
        }

        drawRectangle(image, rect, Color.cyan);
    }

    public Rectangle getDraggedRectangle() {
        return rect;
    }

    public BufferedImage drawRectangle(BufferedImage image, Rectangle rect, Color color) {
        Graphics graph = image.getGraphics();
        graph.setColor(color);
        graph.drawRect(rect.x, rect.y, rect.width, rect.height);
        graph.dispose();

        return image;
    }

    public HashMap<Integer, Rectangle> trackMotion(final Rectangle rect) {
        HashMap<Integer, Rectangle> hm = new HashMap<Integer, Rectangle>();
        
        Rect2d boundingBox = new Rect2d(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        FrameController frameCtl = getFrameController();
        int nbCurFrame = getCurFrameNum();

        try {
            Mat mat = frameCtl.getFrameMat(nbCurFrame);
            tracker.createTracker(MotionTracker.TRACKER_MEDIANFLOW);
            tracker.initialize(mat, boundingBox);

            for (int i = nbCurFrame + 1; i < frameCtl.getTotalFrameCnt(); i++) {
                Rectangle rt = new Rectangle((int)boundingBox.x(), (int)boundingBox.y(), (int)boundingBox.width(), (int)boundingBox.height());
                hm.put(i, rt);

                mat = frameCtl.getFrameMat(i);
                if (!tracker.updateTracker(mat, boundingBox)) {
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            tracker.closeTracker();
        }

        this.hm = hm;

        return hm;
        
    }
}