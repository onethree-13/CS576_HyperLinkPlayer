package CS576.utils;

import org.json.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import javax.swing.border.LineBorder;

/**
 * This class extends from OutputStream to redirect output to a JTextArrea
 * 
 * @author www.codejava.net
 *
 */
public class SystemOutRedirect extends JPanel {
	private JTextArea textArea;

	private JLabel description;

	private PrintStream standardOut;

	public SystemOutRedirect() {
		setLayout(null);

		description = new JLabel("Log:");
		description.setBounds(0, 0, 500, 30);
		description.setPreferredSize(new Dimension(500, 30));
		add(description);

		textArea = new JTextArea(10, 40);
		JScrollPane log = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textArea.setEditable(false);
		log.setBounds(0, 30, 500, 170);
		log.setPreferredSize(new Dimension(500, 170));
		add(log);
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));

		standardOut = System.out;
		System.setOut(printStream);
		// System.setErr(printStream);
	}
}