package CS576.utils;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.*;
import javax.swing.border.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.DefaultListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileSystemView;
import javax.swing.SwingConstants;

public class FileControl extends JPanel {
    private JTextField path = new JTextField(16);
    private JLabel text;
    private JButton open = new JButton("Open");
    private String pathname = new String("E:\\CS576\\project\\AIFilm\\AIFilmOne");
    final JFrame frame = new JFrame("JFileChooser Demo");
    private JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));

    private RGBPlayer rgb;
    private WAVPlayer wav;
    private LinkInfoMap map;

    public void openFile(String pathname) {
        System.out.println("pathname: " + pathname);
        String arr[] = pathname.split("\\\\");
        this.pathname = pathname + "\\" + arr[arr.length - 1];
        rgb.openFile(this.pathname);
        wav.openFile(this.pathname + ".wav");
        map.openFile(this.pathname + ".json");
    }

    public FileControl(RGBPlayer rgb, WAVPlayer wav, LinkInfoMap map) {
        this.wav = wav;
        this.rgb = rgb;
        this.map = map;
        openFile(pathname);

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
                        openFile(fc.getSelectedFile().toString());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
            // pathname = path.getText();
            // text.setText("file path:" + pathname);
            // openFile();
        });
    }
}