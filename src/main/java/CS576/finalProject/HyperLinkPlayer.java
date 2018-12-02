package CS576.finalProject;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import CS576.utils.DisplayInfo;
import CS576.utils.FileControl;
import CS576.utils.LinkInfoMap;
import CS576.utils.RGBPlayer;
import CS576.utils.SystemOutRedirect;
import CS576.utils.TimeControl;
import CS576.utils.WAVPlayer;

public class HyperLinkPlayer {

    public static class MediaPanel extends JPanel {
        public MediaPanel() {
            setLayout(null);

            SystemOutRedirect redirect = new SystemOutRedirect();
            LinkInfoMap map = new LinkInfoMap();
            DisplayInfo info = new DisplayInfo();
            RGBPlayer rgb = new RGBPlayer();
            WAVPlayer wav = new WAVPlayer();
            TimeControl tctrl = new TimeControl(rgb, wav, info);
            FileControl fctrl = new FileControl(rgb, wav, map, tctrl);
            rgb.PanelLink(map, info, fctrl, tctrl);

            rgb.setBounds(15, 55, 352, 288);
            add(rgb);

            fctrl.setBounds(15, 15, 352, 60);
            fctrl.setSize(new Dimension(352, 30));
            add(fctrl);

            tctrl.setBounds(15, 350, 352, 70);
            tctrl.setSize(new Dimension(352, 60));
            add(tctrl);

            redirect.setBounds(375, 15, 500, 200);
            redirect.setSize(new Dimension(500, 200));
            add(redirect);

            info.setBounds(375, 213, 500, 210);
            info.setSize(new Dimension(500, 210));
            add(info);
        }
    }

    static void displayJFrame(String[] args) {
        JFrame frame = new JFrame("HyperLinkPlayer");
        frame.setSize(new Dimension(1000, 550));
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
