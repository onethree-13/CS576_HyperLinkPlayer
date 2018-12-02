package CS576.utils;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FileControl extends JPanel {
    private JLabel path = new JLabel();
    private JButton open = new JButton("Open");
    private String pathname = new String("E:" + File.separator + "CS576" + File.separator + "project" + File.separator
            + "AIFilm" + File.separator + "AIFilmOne");
    final JFrame frame = new JFrame("JFileChooser Demo");
    // private JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
    private JFileChooser fc = new JFileChooser("E:" + File.separator + "CS576" + File.separator + "project"
            + File.separator + "AIFilm" + File.separator + "AIFilmOne");

    private RGBPlayer rgb;
    private WAVPlayer wav;
    private LinkInfoMap map;
    private TimeControl tctrl;

    public void openFile(String pathname) {
        System.out.println("pathname: " + this.pathname);
        String arr[] = pathname.split("\\" + File.separator);
        this.pathname = pathname + File.separator + arr[arr.length - 1];
        path.setText(this.pathname);
        map.openFile(this.pathname + ".json");
        rgb.openFile(this.pathname);
        wav.openFile(this.pathname + ".wav");
    }

    public FileControl(RGBPlayer rgb, WAVPlayer wav, LinkInfoMap map, TimeControl tctrl) {
        this.wav = wav;
        this.rgb = rgb;
        this.map = map;
        this.tctrl = tctrl;
        // openFile(pathname);

        setPreferredSize(new Dimension(352, 30));
        setLayout(null);

        path.setPreferredSize(new Dimension(262, 30));
        path.setBounds(0, 0, 262, 30);
        add(path);
        open.setPreferredSize(new Dimension(82, 30));
        open.setBounds(270, 0, 82, 30);
        add(open);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int retVal = fc.showOpenDialog(frame);
                if (retVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        tctrl.stop();
                        openFile(fc.getSelectedFile().toString());
                        tctrl.set(0);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}