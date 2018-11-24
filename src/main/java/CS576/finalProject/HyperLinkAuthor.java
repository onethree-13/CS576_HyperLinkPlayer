package CS576.finalProject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.DefaultListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

import CS576.utils.AuthorPlayer;
import CS576.utils.ImagePlayer;
import CS576.utils.LinkInfoVO;

public class HyperLinkAuthor extends JFrame {

    private JPanel contentPane;
    
    private JButton btnImportPrimary;
    private JButton btnImportSecondary;
    private JButton btnCreateNewHyperlink;

    private JList<String> list;
    private JButton btnConnectVideo;
    private JButton btnSaveFile;

    private AuthorPlayer primaryPlayer;
    private AuthorPlayer secondaryPlayer;

    JButton btnSetFrom;
    JButton btnSetTo;
    
    JSpinner spinnerFrom;
    JSpinner spinnerTo;

    ArrayList<LinkInfoVO> links;

        

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
        
        list = new JList<String>();
        
		btnConnectVideo = new JButton();
        btnSaveFile = new JButton();
        
		primaryPlayer = new AuthorPlayer();
        secondaryPlayer = new AuthorPlayer();

        JLabel lblFrameFrom = new JLabel("Frame From");
        lblFrameFrom.setHorizontalAlignment(SwingConstants.CENTER);
        lblFrameFrom.setBounds(878, 125, 80, 16);
        contentPane.add(lblFrameFrom);

        JLabel lblFrameTo = new JLabel("Frame To");
        lblFrameTo.setHorizontalAlignment(SwingConstants.CENTER);
        lblFrameTo.setBounds(878, 250, 80, 16);

        contentPane.add(lblFrameTo);
        btnSetFrom = new JButton();
        btnSetTo = new JButton();

        spinnerFrom = new JSpinner();
        spinnerTo = new JSpinner();

        links = new ArrayList<LinkInfoVO>();
    
		InitializeFrame();
    }

    protected void InitializeFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1024, 623);
        
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
        
        DefaultListModel<String> dlm = new DefaultListModel<String>();
        list.setModel(dlm);
        list.setBorder(new LineBorder(new Color(0, 0, 0)));
        list.setBounds(415, 9, 170, 86);
        contentPane.add(list);
        
        btnConnectVideo.setText("Connect Video");
        btnConnectVideo.setBounds(680, 6, 115, 75);
        contentPane.add(btnConnectVideo);
        
        btnSaveFile.setText("Save File");
        btnSaveFile.setBounds(830, 6, 115, 75);
        contentPane.add(btnSaveFile);
        
        primaryPlayer.setBounds(100, 125, ImagePlayer.PANEL_DEFAULT_WIDTH, ImagePlayer.PANEL_DEFAULT_HEIGHT);
        contentPane.add(primaryPlayer);

        secondaryPlayer.setBounds(500, 125, ImagePlayer.PANEL_DEFAULT_WIDTH, ImagePlayer.PANEL_DEFAULT_HEIGHT);
        contentPane.add(secondaryPlayer);

        btnSetFrom.setText("Set");
        btnSetFrom.setBounds(878, 150, 80, 29);
        contentPane.add(btnSetFrom);

        btnSetTo.setText("Set");
        btnSetTo.setBounds(878, 275, 80, 29);
        contentPane.add(btnSetTo);

        spinnerFrom.setBounds(878, 180, 80, 26);
        contentPane.add(spinnerFrom);
        
        spinnerTo.setBounds(878, 305, 80, 26);
        contentPane.add(spinnerTo);

        links.clear();
        
        // ActionListener
        btnImportPrimary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                
                if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(contentPane)) {
                    try {
                        primaryPlayer.loadImages(fc.getSelectedFile().toString());
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

                if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(contentPane)) {
                    try {
                        int nbFrams = secondaryPlayer.loadImages(fc.getSelectedFile().toString());
                        
                        SpinnerNumberModel modelFrom = new SpinnerNumberModel(1, 1, nbFrams, 1);
                        spinnerFrom.setModel(modelFrom);

                        SpinnerNumberModel modelTo = new SpinnerNumberModel(1, 1, nbFrams, 1);
                        spinnerTo.setModel(modelTo);
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
                if (!primaryPlayer.isLoaded()) {
                    JOptionPane.showMessageDialog(null, "The primary video isn't loaded.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!secondaryPlayer.isLoaded()) {
                    JOptionPane.showMessageDialog(null, "The secondary video isn't loaded.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Rectangle rect = primaryPlayer.getDraggedRectangle();
                if (0 == rect.width || 0 == rect.height) {
                    JOptionPane.showMessageDialog(null, "Drag link area before create a hyperlink.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                HashMap<Integer, Rectangle> hm = primaryPlayer.trackMotion(rect);
                if (0 < hm.size())
                    return;

                String linkName = JOptionPane.showInputDialog("Link name");
                if (null == linkName || "" == linkName) return;

                
                
                try {
                    LinkInfoVO linkInfo = genLinkInfo(linkName);
                    links.add(linkInfo);

                    DefaultListModel<String> dlm = (DefaultListModel<String>)list.getModel();
                    dlm.addElement(linkName);
                } catch (Exception ev) {
                    JOptionPane.showMessageDialog(null, ev.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
			}
        });
        
        btnConnectVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                try {
                    String linkName = list.getSelectedValue();
                    LinkInfoVO linkInfo = genLinkInfo(linkName);

                    int index = list.getSelectedIndex();
                    links.set(index, linkInfo);

                    JOptionPane.showMessageDialog(null, "Saved.", "Info", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ev) {
                    JOptionPane.showMessageDialog(null, ev.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
			}
        });
        
        btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                if (0 < links.size()) {
                    Gson gson = new Gson();
                    String data = gson.toJson(links);
                }
			}
        });

        btnSetFrom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int nbFrame = secondaryPlayer.getCurFrameNum();
                if (nbFrame < 0) nbFrame = 0;

                spinnerFrom.setValue(nbFrame);
			}
        });

        btnSetTo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int nbFrame = secondaryPlayer.getCurFrameNum();
                if (nbFrame < 0) nbFrame = 0;

                spinnerTo.setValue(nbFrame);
			}
        });
    }

    public LinkInfoVO genLinkInfo(String linkName) throws Exception {
        String srcPathName = primaryPlayer.getPathName();
        int nbSrcFrame = primaryPlayer.getCurFrameNum();
        Rectangle rect = primaryPlayer.getDraggedRectangle();

        if (0 == rect.width || 0 == rect.height) {
            throw new Exception("A dragged area should be greater than 0.");
        }
        
        String linkPathName = secondaryPlayer.getPathName();
        int nbLinkFrameFrom = (Integer)spinnerFrom.getValue();
        int nbLinkFrameTo = (Integer)spinnerTo.getValue();

        if (nbLinkFrameTo < nbLinkFrameFrom) {
            throw new Exception("The linked video's beginning frame should be smaller or equal than end frame.");
        }

        LinkInfoVO linkInfo = new LinkInfoVO();
        linkInfo.setLinkName(linkName);
        linkInfo.setOriginPathName(srcPathName);
        linkInfo.setFrame(nbSrcFrame);
        linkInfo.setBoundaryX(rect.x);
        linkInfo.setBoundaryY(rect.y);
        linkInfo.setBoundaryWidth(rect.width);
        linkInfo.setBoundaryHeight(rect.height);
        linkInfo.setLinkPathName(linkPathName);
        linkInfo.setLinkFrameFrom(nbLinkFrameFrom);
        linkInfo.setLinkFrameTo(nbLinkFrameTo);

        return linkInfo;
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
