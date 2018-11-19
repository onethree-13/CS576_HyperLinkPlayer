package CS576.finalProject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import CS576.utils.ImagePanel;
import CS576.utils.FrameController;

public class HyperLinkAuthor extends JFrame implements ChangeListener {

    private JPanel contentPane;
    
    private FrameController primaryCtl;
    private FrameController secondaryCtl;

    private JButton btnImportPrimary;
    private JButton btnImportSecondary;
    private JButton btnCreateNewHyperlink;

    private JList list;
    private JButton btnConnectVideo;
    private JButton btnSaveFile;

    private ImagePanel panelPrimary;
    private ImagePanel panelSecondary;

    private JSlider sliderPrimary;
    private JSlider sliderSecondary;


	/**
	 * Create the frame.
	 */
	public HyperLinkAuthor() {
		contentPane = new JPanel();
		
        JLabel lblNewLabel = new JLabel("Action : ");
        lblNewLabel.setFont(new Font("Gulim", Font.BOLD, 15));
		lblNewLabel.setBounds(12, 10, 70, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select Link : ");
		lblNewLabel_1.setFont(new Font("Gulim", Font.BOLD, 15));
		lblNewLabel_1.setBounds(310, 10, 110, 15);
        contentPane.add(lblNewLabel_1);
        
        btnImportPrimary = new JButton();
		btnImportSecondary = new JButton();
        btnCreateNewHyperlink = new JButton();
        
        list = new JList();
        
		btnConnectVideo = new JButton();
        btnSaveFile = new JButton();
        
		panelPrimary = new ImagePanel();
        panelSecondary = new ImagePanel();

		sliderPrimary = new JSlider();
        sliderSecondary = new JSlider();
        
        InitializeFrame();
    }

    protected void InitializeFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 996, 623);
        
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        btnImportPrimary.setText("Import Primary video");
		btnImportPrimary.setBounds(90, 6, 170, 23);
        contentPane.add(btnImportPrimary);
        
        btnImportSecondary.setText("Import Secondary video");
        btnImportSecondary.setBounds(90, 39, 170, 23);
        contentPane.add(btnImportSecondary);
        
        btnCreateNewHyperlink.setText("Create new hyperlink");
        btnCreateNewHyperlink.setBounds(90, 72, 170, 23);
        contentPane.add(btnCreateNewHyperlink);
        
        list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(415, 9, 170, 86);
        contentPane.add(list);
        
        btnConnectVideo.setText("Connect Video");
        btnConnectVideo.setBounds(680, 6, 115, 75);
        contentPane.add(btnConnectVideo);
        
        btnSaveFile.setText("Save File");
        btnSaveFile.setBounds(830, 6, 115, 75);
        contentPane.add(btnSaveFile);
        
        panelPrimary.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPrimary.setBounds(100, 125, 352, 288);
        contentPane.add(panelPrimary);

        panelSecondary.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelSecondary.setBounds(540, 125, 352, 288);
        contentPane.add(panelSecondary);
        
        sliderPrimary.setBounds(100, 419, 352, 26);
		sliderPrimary.setPaintLabels(true);
        sliderPrimary.setPaintTicks(true);
        sliderPrimary.addChangeListener(this);
        contentPane.add(sliderPrimary);

        sliderSecondary.setPaintTicks(true);
		sliderSecondary.setPaintLabels(true);
        sliderSecondary.setBounds(540, 419, 352, 26);
        sliderSecondary.addChangeListener(this);
        contentPane.add(sliderSecondary);

        // ActionListener
        btnImportPrimary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = fc.showOpenDialog(contentPane);

                if (JFileChooser.APPROVE_OPTION == returnVal) {
                    try {
                        primaryCtl = new FrameController(fc.getSelectedFile().toString());                    
                        panelPrimary.setImage(primaryCtl.getFrameImage(0));

                        sliderPrimary.setMinimum(0);
                        sliderPrimary.setMaximum(primaryCtl.getTotalFrameCnt() - 1);                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                }
			}
        });
        
        btnImportSecondary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = fc.showOpenDialog(contentPane);

                if (JFileChooser.APPROVE_OPTION == returnVal) {
                    try {
                        secondaryCtl = new FrameController(fc.getSelectedFile().toString());                    
                        panelSecondary.setImage(secondaryCtl.getFrameImage(0));

                        sliderSecondary.setMinimum(0);
                        sliderSecondary.setMaximum(secondaryCtl.getTotalFrameCnt() - 1);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                }
			}
        });
        
        btnCreateNewHyperlink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
        });
        
        btnConnectVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
        });
        
        btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
    }

	public void stateChanged(ChangeEvent e) {
        if ((JSlider) e.getSource() == sliderPrimary) {
            if (null == primaryCtl) return;
            try {
                BufferedImage image = primaryCtl.getFrameImage(sliderPrimary.getValue());
                panelPrimary.setImage(image);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        } else if ((JSlider) e.getSource() == sliderSecondary) {
            if (null == secondaryCtl) return;
            try {
                BufferedImage image = secondaryCtl.getFrameImage(sliderSecondary.getValue());
                panelSecondary.setImage(image);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
    
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HyperLinkAuthor frame = new HyperLinkAuthor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
