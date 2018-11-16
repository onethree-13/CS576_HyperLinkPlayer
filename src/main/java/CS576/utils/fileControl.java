package CS576.utils;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.*;

public class fileControl extends JPanel {
    private JTextField path;
    private JLabel text;
    private JButton open;
    private String filename;

    public fileControl() {
        setPreferredSize(new Dimension(800, 80));
        path = new JTextField(16);
        open = new JButton("Open..");
        filename = new String("../AIFilm/AIFilmOne/AIFilmOne");
        text = new JLabel(filename);
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                text.setText("file path:" + path.getText());
                path.setText("");
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