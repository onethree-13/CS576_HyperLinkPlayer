package CS576.finalProject;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.border.EmptyBorder;
import CS576.utils.*;
import javax.swing.*;

public class HyperLinkPlayer {

    public static class MediaPanel extends JPanel {
        public MediaPanel() {
            setLayout(null);

            LinkInfoMap map = new LinkInfoMap();
            DisplayInfo info = new DisplayInfo();
            RGBPlayer rgb = new RGBPlayer();
            WAVPlayer wav = new WAVPlayer();
            FileControl fctrl = new FileControl(rgb, wav, map);
            TimeControl tctrl = new TimeControl(rgb, wav, map, info);
            rgb.PanelLink(map, info, fctrl, tctrl);
            SystemOutRedirect redirect = new SystemOutRedirect();

            rgb.play(0);
            rgb.setBounds(15, 55, 352, 288);
            add(rgb);

            fctrl.setBounds(15, 15, 352, 30);
            fctrl.setSize(new Dimension(352, 30));
            add(fctrl);

            tctrl.setBounds(15, 350, 352, 100);
            tctrl.setSize(new Dimension(352, 100));
            add(tctrl);

            redirect.setBounds(375, 15, 500, 200);
            redirect.setSize(new Dimension(500, 200));
            add(redirect);

            info.setBounds(375, 213, 300, 210);
            info.setSize(new Dimension(500, 210));
            add(info);
        }
    }

    static void displayJFrame(String[] args) {
        JFrame frame = new JFrame("HyperLinkPlayer");
        frame.setSize(new Dimension(1000, 600));
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
