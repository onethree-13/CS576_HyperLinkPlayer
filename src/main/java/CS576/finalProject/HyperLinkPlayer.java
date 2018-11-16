package CS576.finalProject;

import CS576.utils.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.*;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class HyperLinkPlayer {

    public static class MediaPanel extends JPanel {
        public MediaPanel() {
            rgbPlayer player1 = new rgbPlayer("../AIFilm/AIFilmOne/AIFilmOne");
            timeControl tctrl = new timeControl(player1);
            fileControl fctrl = new fileControl();
            player1.load(1);
            SpringLayout layout = new SpringLayout();
            setLayout(layout);
            layout.putConstraint(SpringLayout.WEST, fctrl, +40, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, fctrl, +20, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, tctrl, +40, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, tctrl, +100, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, player1, +40, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, player1, +180, SpringLayout.NORTH, this);
            add(fctrl);
            add(tctrl);
            add(player1);
        }
    }

    static void displayJFrame(String[] args) {
        JFrame frame = new JFrame("HyperLinkPlayer");
        frame.setSize(new Dimension(1600, 800));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MediaPanel());
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                displayJFrame(args);
            }
        });
    }
}
