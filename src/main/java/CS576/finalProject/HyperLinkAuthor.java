package CS576.finalProject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import CS576.utils.ImagePanel;
import CS576.utils.FrameController;

public class HyperLinkAuthor extends JFrame implements ChangeListener, MouseMotionListener {

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

    private JLabel lblPrimaryLabel;
    private JLabel lblSecondaryLabel;

    private Boolean bPrimaryDragged;
    private Boolean bSecondaryDragged;

    private Point ptPrimary;
    private Point ptSecondary;

    private Rectangle rectPrimary;
    private Rectangle rectSecondary;

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

        lblPrimaryLabel = new JLabel();
        lblSecondaryLabel = new JLabel();

        ptPrimary = new Point();
        ptSecondary = new Point();

        rectPrimary = new Rectangle();
        rectSecondary = new Rectangle();
                
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
        sliderPrimary.setValue(0);
        contentPane.add(sliderPrimary);

        sliderSecondary.setPaintTicks(true);
		sliderSecondary.setPaintLabels(true);
        sliderSecondary.setBounds(540, 419, 352, 26);
        sliderSecondary.addChangeListener(this);
        sliderSecondary.setValue(0);
        contentPane.add(sliderSecondary);

        lblPrimaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblPrimaryLabel.setBounds(100, 450, 352, 16);
        contentPane.add(lblPrimaryLabel);

        lblSecondaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblSecondaryLabel.setBounds(540, 450, 352, 16);
        contentPane.add(lblSecondaryLabel);

        // ActionListener
        btnImportPrimary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = fc.showOpenDialog(contentPane);

                if (JFileChooser.APPROVE_OPTION == returnVal) {
                    try {
                        primaryCtl = new FrameController(fc.getSelectedFile().toString());                    
                        panelPrimary.setImage(primaryCtl.getFrameImage(0, panelPrimary.getWidth(), panelSecondary.getHeight()));

                        sliderPrimary.setMinimum(0);
                        sliderPrimary.setMaximum(primaryCtl.getTotalFrameCnt() - 1);                        

                        lblPrimaryLabel.setText("1 th / " + primaryCtl.getTotalFrameCnt() + " Total");
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
                        panelSecondary.setImage(secondaryCtl.getFrameImage(0, panelSecondary.getWidth(), panelSecondary.getHeight()));

                        sliderSecondary.setMinimum(0);
                        sliderSecondary.setMaximum(secondaryCtl.getTotalFrameCnt() - 1);

                        lblSecondaryLabel.setText("1 th / " + secondaryCtl.getTotalFrameCnt() + " Total");
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
        
        panelPrimary.addMouseMotionListener(this);
        panelSecondary.addMouseMotionListener(this);

        bPrimaryDragged = false;
        bSecondaryDragged = false;
    }

	public void stateChanged(ChangeEvent e) {
        if ((JSlider) e.getSource() == sliderPrimary) {
            if (null == primaryCtl) return;
            try {
                lblPrimaryLabel.setText((sliderPrimary.getValue() + 1) + " th / " + primaryCtl.getTotalFrameCnt() + " Total");
                BufferedImage image = primaryCtl.getFrameImage(sliderPrimary.getValue(), panelPrimary.getWidth(), panelPrimary.getHeight());
                panelPrimary.setImage(image);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        } else if ((JSlider) e.getSource() == sliderSecondary) {
            if (null == secondaryCtl) return;
            try {
                lblSecondaryLabel.setText((sliderSecondary.getValue() + 1) + " th / " + secondaryCtl.getTotalFrameCnt() + " Total");
                BufferedImage image = secondaryCtl.getFrameImage(sliderSecondary.getValue(), panelSecondary.getWidth(), panelSecondary.getHeight());
                panelSecondary.setImage(image);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        if (e.getComponent().getClass().getName() == "CS576.utils.ImagePanel") {
            if ((ImagePanel)e.getSource() == panelPrimary && bPrimaryDragged) {
                mouseDragged(e);
                bPrimaryDragged = false;
            } else if ((ImagePanel)e.getSource() == panelSecondary && bSecondaryDragged) {
                mouseDragged(e);
                bSecondaryDragged = false;
            }
        }
        System.out.println("Mouse Moved"
        + " (" + e.getX() + "," + e.getY() + ")"
        + " detected on "
        + e.getComponent().getClass().getName());
    }

    public void mouseDragged(MouseEvent e) {
        if (e.getComponent().getClass().getName() == "CS576.utils.ImagePanel") {
            ImagePanel panel = null;
            BufferedImage img = null;
            Boolean bDragged = false;
            Rectangle rect = null;
            Point ptFrom = null;
            Point ptTo = new Point(e.getX(), e.getY());

            try {
                if ((ImagePanel)e.getSource() == panelPrimary) {
                    if (null == primaryCtl) return;

                    panel = panelPrimary;
                    img = primaryCtl.getFrameImage(primaryCtl.getCurFrameNum(), panelPrimary.getWidth(), panelPrimary.getHeight());
                    rect = rectPrimary;
                    ptFrom = ptPrimary;
                    if (!bPrimaryDragged) {
                        ptPrimary.setLocation(e.getX(), e.getY());
                        bPrimaryDragged = true;
                    }
                } else if ((ImagePanel)e.getSource() == panelSecondary) {
                    if (null != secondaryCtl) return;

                    panel = panelSecondary;
                    img = secondaryCtl.getFrameImage(secondaryCtl.getCurFrameNum(), panelSecondary.getWidth(), panelSecondary.getHeight());
                    rect = rectSecondary;
                    ptFrom = ptSecondary;
                    if (!bSecondaryDragged) {
                        ptSecondary.setLocation(e.getX(), e.getY());
                        bSecondaryDragged = true;
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }

            if (ptFrom.x < ptTo.x) {
                if (ptFrom.y < ptTo.y) {
                    rect.setLocation(ptFrom);
                    rect.setSize(ptTo.x - ptFrom.x, ptTo.y - ptFrom.y);
                } else {
                    rect.setLocation(ptFrom.x, ptTo.y);
                    rect.setSize(ptTo.x - ptFrom.x, ptFrom.y - ptTo.y);
                }
            } else {
                if (ptFrom.y < ptTo.y) {
                    rect.setLocation(ptTo.x, ptFrom.y);
                    rect.setSize(ptFrom.x - ptTo.x, ptTo.y - ptFrom.y);
                } else {
                    rect.setLocation(ptTo);
                    rect.setSize(ptFrom.x - ptTo.x, ptFrom.y - ptTo.y);
                }
            }

            Graphics graph = img.getGraphics();
            graph.setColor(Color.CYAN);
            graph.drawRect(rect.x, rect.y, rect.width, rect.height);
            graph.dispose();

            panel.setImage(img);
        }

        System.out.println("Mouse Dragged"
        + " (" + e.getX() + "," + e.getY() + ")"
        + " detected on "
        
        + e.getComponent().getClass().getName());
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
