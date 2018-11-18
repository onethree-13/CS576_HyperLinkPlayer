package CS576.finalProject;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import CS576.utils.fileControl;
import CS576.utils.rgbPlayer;
import CS576.utils.timeControl;
import CS576.utils.wavPlayer;

public class HyperLinkPlayer {

    public static class MediaPanel extends JPanel {
        public MediaPanel() {
            rgbPlayer rgb = new rgbPlayer();
            wavPlayer wav = new wavPlayer();
            fileControl fctrl = new fileControl(rgb, wav);
            timeControl tctrl = new timeControl(rgb, wav);
            rgb.play(1);
            SpringLayout layout = new SpringLayout();
            setLayout(layout);
            layout.putConstraint(SpringLayout.WEST, fctrl, +40, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, fctrl, +20, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, tctrl, +40, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, tctrl, +100, SpringLayout.NORTH, this);
            layout.putConstraint(SpringLayout.WEST, rgb, +40, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, rgb, +180, SpringLayout.NORTH, this);
            add(fctrl);
            add(tctrl);
            add(rgb);
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
