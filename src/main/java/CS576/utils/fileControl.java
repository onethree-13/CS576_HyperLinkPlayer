package CS576.utils;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;
import javax.swing.border.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileControl extends JPanel {
    private JTextField path;
    private JLabel text;
    private JButton open;
    private String pathname = new String("../AIFilm/AIFilmOne/AIFilmOne");

    private RGBPlayer rgb;
    private WAVPlayer wav;
    private LinkInfoMap map;

    private void openFile() {
        rgb.openFile(pathname);
        wav.openFile(pathname + ".wav");
        map.openFile(pathname + ".json");
    }

    public void openFile(String pathname) {
        String pattern = "(^\\)*(\\){2}$";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(pathname);
        System.out.println(m.group(0));
        this.pathname = pathname + m.group(0);
        openFile();
    }

    public FileControl(RGBPlayer rgb, WAVPlayer wav, LinkInfoMap map) {
        this.wav = wav;
        this.rgb = rgb;
        this.map = map;
        openFile();

        setPreferredSize(new Dimension(352, 30));
        setLayout(null);

        path = new JTextField(16);
        path.setPreferredSize(new Dimension(262, 30));
        path.setBounds(0, 0, 262, 30);
        add(path);
        open = new JButton("Open");
        open.setPreferredSize(new Dimension(82, 30));
        open.setBounds(270, 0, 82, 30);
        add(open);

        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pathname = path.getText();
                text.setText("file path:" + pathname);
                openFile();
            }
        });

    }

}