package CS576.utils;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class fileControl extends JPanel {
    private JTextField path;
    private JLabel text;
    private JButton open;
    private String pathname = new String("../AIFilm/AIFilmOne/AIFilmOne");

    private rgbPlayer rgb;
    private wavPlayer wav;

    private void openFile() {
        rgb.openFile(pathname);
        wav.openFile(pathname + ".wav");
    }

    public fileControl(rgbPlayer rgb, wavPlayer wav) {
        this.wav = wav;
        this.rgb = rgb;
        openFile();

        setPreferredSize(new Dimension(800, 80));
        path = new JTextField(16);
        open = new JButton("Open..");
        text = new JLabel(pathname);
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pathname = path.getText();
                text.setText("file path:" + pathname);
                path.setText("");
                openFile();
            }
        });
        path.setPreferredSize(new Dimension(300, 30));
        open.setPreferredSize(new Dimension(80, 30));
        text.setPreferredSize(new Dimension(300, 30));
        add(path);
        add(open);
        add(text);
    }

}