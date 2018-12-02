package CS576.finalProject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.DefaultListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import java.util.ArrayList;
import java.util.HashMap;

import CS576.utils.AuthorPlayer;
import CS576.utils.AuthorPlayerEventListener;
import CS576.utils.ImagePlayer;
import CS576.utils.LinkInfoVO;

public class HyperLinkAuthor extends JFrame implements AuthorPlayerEventListener {

    private static final String FILE_EXTENSION = "json";

    private JPanel contentPane;
    
    private JButton btnImportPrimary;
    private JButton btnImportSecondary;
    private JButton btnCreateNewHyperlink;

    private JList<String> list;
    private JButton btnConnectVideo;
    private JButton btnSaveFile;

    private AuthorPlayer primaryPlayer;
    private AuthorPlayer secondaryPlayer;
    
    JSpinner spinnerX;
    JSpinner spinnerY;
    JSpinner spinnerWidth;
    JSpinner spinnerHeight;
    
    JButton btnUpdate;

    JButton btnSetFrom;
    JButton btnSetTo;
    
    JSpinner spinnerFrom;
    JSpinner spinnerTo;

    JButton btnAddFrames;
    JButton btnRemoveFrames;

    JButton btnSetFromSecondary;
    JSpinner spinnerFromSecondary;

    HashMap<String, HashMap<Integer, Rectangle>> linkFrames;
    HashMap<String, LinkInfoVO> links;
    
    String curLinkName;
        
    /**
	 * Create the frame.
	 */
	public HyperLinkAuthor() {
        contentPane = new JPanel();
		
        JLabel lblNewLabel = new JLabel("Action : ");
        lblNewLabel.setFont(new Font("Gulim", Font.BOLD, 15));
		lblNewLabel.setBounds(90, 25, 70, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select Link : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Gulim", Font.BOLD, 15));
		lblNewLabel_1.setBounds(395, 25, 110, 15);
        contentPane.add(lblNewLabel_1);
        
        btnImportPrimary = new JButton();
		btnImportSecondary = new JButton();
        btnCreateNewHyperlink = new JButton();
        
        list = new JList<String>();
        
		btnConnectVideo = new JButton();
        btnSaveFile = new JButton();
        
		primaryPlayer = new AuthorPlayer();
        secondaryPlayer = new AuthorPlayer();
        
        JLabel lblBoxBounding = new JLabel("Box Bounding");
        lblBoxBounding.setHorizontalAlignment(SwingConstants.CENTER);
        lblBoxBounding.setBounds(430, 130, 100, 16);
        contentPane.add(lblBoxBounding);
        
        JLabel lblX = new JLabel("X : ");
        lblX.setHorizontalAlignment(SwingConstants.RIGHT);
        lblX.setBounds(430, 155, 20, 16);
        contentPane.add(lblX);
        
        JLabel lblY = new JLabel("Y : ");
        lblY.setHorizontalAlignment(SwingConstants.RIGHT);
        lblY.setBounds(430, 185, 20, 16);
        contentPane.add(lblY);
        
        JLabel lblWidth = new JLabel("WIDTH :");
        lblWidth.setHorizontalAlignment(SwingConstants.RIGHT);
        lblWidth.setBounds(525, 155, 65, 16);
        contentPane.add(lblWidth);
        
        JLabel lblHeight = new JLabel("HEIGHT :");
        lblHeight.setHorizontalAlignment(SwingConstants.RIGHT);
        lblHeight.setBounds(525, 185, 65, 16);
        contentPane.add(lblHeight);
        
        spinnerX = new JSpinner();
        spinnerY = new JSpinner();
        spinnerWidth = new JSpinner();
        spinnerHeight = new JSpinner();
        
        JLabel lblUpdateBoxBounding = new JLabel("Update Box Bounding :");
        lblUpdateBoxBounding.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUpdateBoxBounding.setBounds(430, 215, 145, 16);
        contentPane.add(lblUpdateBoxBounding);
        
        btnUpdate = new JButton();

        JLabel lblFrameFrom = new JLabel("Tracking From");
        lblFrameFrom.setHorizontalAlignment(SwingConstants.CENTER);
        lblFrameFrom.setBounds(440, 265, 100, 16);
        contentPane.add(lblFrameFrom);

        JLabel lblFrameTo = new JLabel("Tracking To");
        lblFrameTo.setHorizontalAlignment(SwingConstants.CENTER);
        lblFrameTo.setBounds(570, 265, 80, 16);

        contentPane.add(lblFrameTo);
        btnSetFrom = new JButton();
        btnSetTo = new JButton();

        spinnerFrom = new JSpinner();
        spinnerTo = new JSpinner();

        btnAddFrames = new JButton();
        btnRemoveFrames = new JButton();

        JLabel lblFrameFrom_2 = new JLabel("Frame From");
        lblFrameFrom_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblFrameFrom_2.setBounds(1070, 130, 80, 16);
        contentPane.add(lblFrameFrom_2);

        btnSetFromSecondary = new JButton();
        spinnerFromSecondary = new JSpinner();

        linkFrames = new HashMap<String, HashMap<Integer, Rectangle>>();
        links = new HashMap<String, LinkInfoVO>();
    
		InitializeFrame();
    }

    protected void InitializeFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1175, 560);
        
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        btnImportPrimary.setText("Import Primary video");
		btnImportPrimary.setBounds(155, 25, 175, 23);
        contentPane.add(btnImportPrimary);
        
        btnImportSecondary.setText("Import Secondary video");
        btnImportSecondary.setBounds(155, 50, 175, 23);
        contentPane.add(btnImportSecondary);
        
        btnCreateNewHyperlink.setText("Create new hyperlink");
        btnCreateNewHyperlink.setBounds(155, 75, 175, 23);
        contentPane.add(btnCreateNewHyperlink);
        
        DefaultListModel<String> dlm = new DefaultListModel<String>();
        list.setModel(dlm);
        list.setBorder(new LineBorder(new Color(0, 0, 0)));
        list.setBounds(510, 20, 170, 86);
        contentPane.add(list);
        
        btnConnectVideo.setText("Connect Video");
        btnConnectVideo.setBounds(760, 20, 115, 75);
        contentPane.add(btnConnectVideo);
        
        btnSaveFile.setText("Save File");
        btnSaveFile.setBounds(920, 20, 115, 75);
        contentPane.add(btnSaveFile);
        
        primaryPlayer.setBounds(50, 125, ImagePlayer.PANEL_DEFAULT_WIDTH, ImagePlayer.PANEL_DEFAULT_HEIGHT);
        contentPane.add(primaryPlayer);

        secondaryPlayer.setBounds(685, 125, ImagePlayer.PANEL_DEFAULT_WIDTH, ImagePlayer.PANEL_DEFAULT_HEIGHT);
        contentPane.add(secondaryPlayer);
        
        spinnerX.setBounds(445, 150, 75, 26);
        contentPane.add(spinnerX);
        
        spinnerY.setBounds(445, 180, 75, 26);
        contentPane.add(spinnerY);
        
        spinnerWidth.setBounds(590, 150, 75, 26);
        contentPane.add(spinnerWidth);
        
        spinnerHeight.setBounds(590, 180, 75, 26);
        contentPane.add(spinnerHeight);
        
        btnUpdate.setText("Update");
        btnUpdate.setBounds(585, 210, 80, 29);
        contentPane.add(btnUpdate);

        btnSetFrom.setText("Set");
        btnSetFrom.setBounds(440, 285, 80, 29);
        contentPane.add(btnSetFrom);

        btnSetTo.setText("Set");
        btnSetTo.setBounds(570, 285, 80, 29);
        contentPane.add(btnSetTo);

        spinnerFrom.setBounds(443, 310, 75, 26);
        contentPane.add(spinnerFrom);
        
        spinnerTo.setBounds(573, 310, 75, 26);
        contentPane.add(spinnerTo);

        btnAddFrames.setText("Add");
        btnAddFrames.setBounds(440, 335, 80, 29);
        contentPane.add(btnAddFrames);

        btnRemoveFrames.setText("Del");
        btnRemoveFrames.setBounds(570, 335, 80, 29);
        contentPane.add(btnRemoveFrames);

        btnSetFromSecondary.setText("Set");
        btnSetFromSecondary.setBounds(1070, 150, 80, 29);
        contentPane.add(btnSetFromSecondary);

        spinnerFromSecondary.setBounds(1073, 180, 80, 26);
        contentPane.add(spinnerFromSecondary);
        
        linkFrames.clear();
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

                Rectangle rect = primaryPlayer.getDraggedRectangle();
                if (0 == rect.width || 0 == rect.height) {
                    JOptionPane.showMessageDialog(null, "Drag link area before create a hyperlink.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int trackingFrom = (Integer)spinnerFrom.getValue();
                int trackingTo = (Integer)spinnerTo.getValue();

                if (0 == trackingFrom && 0 == trackingTo) {
                    trackingTo = primaryPlayer.getTotalFrameCnt() - 1;
                }

                if (!(trackingFrom < trackingTo)) {
                    JOptionPane.showMessageDialog(null, "The starting frame of tracking section should be less than end point.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                String linkName = JOptionPane.showInputDialog("Link name");
                if (null == linkName || "" == linkName) return;

                HashMap<Integer, Rectangle> hm = primaryPlayer.trackMotion(rect, trackingFrom, trackingTo);
                JOptionPane.showMessageDialog(null, hm.size() + " frames are detected.", "Info", JOptionPane.INFORMATION_MESSAGE);
                linkFrames.put(linkName, hm);
                
                try {
                    DefaultListModel<String> dlm = (DefaultListModel<String>)list.getModel();
                    dlm.addElement(linkName);
                    curLinkName = linkName;
                } catch (Exception ev) {
                    JOptionPane.showMessageDialog(null, ev.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    linkFrames.remove("linkName");
                }
			}
        });
        
        btnConnectVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                try {
                    if (!secondaryPlayer.isLoaded()) {
                        JOptionPane.showMessageDialog(null, "The secondary video isn't loaded.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    LinkInfoVO linkInfo = genLinkInfo(curLinkName);

                    links.put(curLinkName, linkInfo);

                    JOptionPane.showMessageDialog(null, "Connected.", "Info", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ev) {
                    JOptionPane.showMessageDialog(null, ev.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
			}
        });
        
        btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                if (0 < links.size()) {
                    final JFileChooser fc = new JFileChooser();
                    fc.setAcceptAllFileFilterUsed(false);
                    
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(FILE_EXTENSION.toUpperCase()+" (*."+FILE_EXTENSION+")", FILE_EXTENSION);
                    fc.addChoosableFileFilter(filter);

                    fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
                    if (JFileChooser.APPROVE_OPTION == fc.showSaveDialog(contentPane)) {
                        InputStream in = null;
                        FileOutputStream fos = null;
                        
                        BufferedInputStream bis = null;
                        BufferedOutputStream bos = null;

                        try {
                            String fileName = fc.getSelectedFile().toString();
                            if (!(fileName.toLowerCase().endsWith("."+FILE_EXTENSION))) {
                                fileName += "."+FILE_EXTENSION;
                            }

                            File file = new File(fileName);
                            if (file.exists()) {
                                if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null, fileName + " already exists. Do you want to overwrite?", "File exist", JOptionPane.YES_NO_OPTION)) return;
                            }
                                
                            ArrayList<LinkInfoVO> infos = new ArrayList<LinkInfoVO>();
            
                            ListModel<String> model = list.getModel();
                            for (int i = 0; i < model.getSize(); i++) {
                                model.getElementAt(i);
                                infos.add(links.get(model.getElementAt(i)));
                            }
        
                            String json = LinkInfoVO.toJson(infos);
                            
                            in = new ByteArrayInputStream(json.getBytes("UTF-8"));
                            fos = new FileOutputStream(file);

                            bis = new BufferedInputStream(in);
                            bos = new BufferedOutputStream(fos);

                            int nbOfByteRead = 0;
                            byte[] buf = new byte[1024];
                            while (-1 != (nbOfByteRead = bis.read(buf, 0, buf.length))) {
                                bos.write(buf, 0, nbOfByteRead);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            System.out.println(ex.getMessage());
                            ex.printStackTrace();
                        } finally {
                            try {
                                if (null != bos) bos.close();
                                if (null != bis) bis.close();
                                if (null != fos) fos.close();
                                if (null != in) in.close();
                            } catch (Exception ex) {
                                System.err.println(ex.getMessage());
                            }
                        }
                    }
                }
			}
        });

        btnUpdate.addActionListener(new ActionListener() {        
            @Override
            public void actionPerformed(ActionEvent e) {
                if (null == curLinkName || "" == curLinkName) return;
                if (!linkFrames.containsKey(curLinkName)) return;
                
                HashMap<Integer, Rectangle> hm = linkFrames.get(curLinkName);
                if (!hm.containsKey(primaryPlayer.getCurFrameNum())) return;

                Rectangle rect = hm.get(primaryPlayer.getCurFrameNum());
                rect.x = (int)spinnerX.getValue();
                rect.y = (int)spinnerY.getValue();
                rect.width = (int)spinnerWidth.getValue();
                rect.height = (int)spinnerHeight.getValue();
            }
        });

        btnSetFrom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int nbFrame = primaryPlayer.getCurFrameNum();
                if (nbFrame < 0) nbFrame = 0;

                spinnerFrom.setValue(nbFrame);
			}
        });

        btnSetTo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int nbFrame = primaryPlayer.getCurFrameNum();
                if (nbFrame < 0) nbFrame = 0;

                spinnerTo.setValue(nbFrame);
			}
        });

        btnAddFrames.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(null, "Please select a link on the list.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Rectangle rect = primaryPlayer.getDraggedRectangle();
                if (0 == rect.width || 0 == rect.height) {
                    JOptionPane.showMessageDialog(null, "Drag link area before create a hyperlink.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int trackingFrom = (Integer)spinnerFrom.getValue();
                int trackingTo = (Integer)spinnerTo.getValue();

                if (0 == trackingFrom && 0 == trackingTo) {
                    trackingTo = primaryPlayer.getTotalFrameCnt() - 1;
                }

                if (!(trackingFrom < trackingTo)) {
                    JOptionPane.showMessageDialog(null, "The starting frame of tracking section should be less than end point.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String linkName = list.getSelectedValue();
                HashMap<Integer, Rectangle> hm = primaryPlayer.trackMotion(rect, trackingFrom, trackingTo);
                linkFrames.get(linkName).putAll(hm);
                
                JOptionPane.showMessageDialog(null, hm.size() + " frames are detected.", "Info", JOptionPane.INFORMATION_MESSAGE);                
            }
        });

        btnRemoveFrames.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(null, "Please select a link on the list.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int trackingFrom = (Integer)spinnerFrom.getValue();
                int trackingTo = (Integer)spinnerTo.getValue();

                if (0 == trackingFrom && 0 == trackingTo) {
                    trackingTo = primaryPlayer.getTotalFrameCnt() - 1;
                }

                if (!(trackingFrom < trackingTo)) {
                    JOptionPane.showMessageDialog(null, "The starting frame of tracking section should be less than end point.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int nbRemoved = 0;
                String linkName = list.getSelectedValue();
                HashMap<Integer, Rectangle> hm = linkFrames.get(linkName);
                for (int i = trackingFrom; i <= trackingTo; i++) {
                    if (hm.containsKey(i)) {
                        hm.remove(i);
                        nbRemoved++;
                    }
                }

                JOptionPane.showMessageDialog(null, nbRemoved + " frames are detected.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnSetFromSecondary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int nbFrame = secondaryPlayer.getCurFrameNum();
                if (nbFrame < 0) nbFrame = 0;

                spinnerFromSecondary.setValue(nbFrame);
			}
        });
    }

    public LinkInfoVO genLinkInfo(String linkName) throws Exception {
        if (false == linkFrames.containsKey(linkName)) {
            throw new Exception("Can't find link bounding box from primary video.");
        }

        String srcPathName = primaryPlayer.getPathName();
        
        String linkPathName = secondaryPlayer.getPathName();
        int nbLinkFrameFrom = (Integer)spinnerFromSecondary.getValue();
        
        LinkInfoVO linkInfo = new LinkInfoVO();
        linkInfo.setLinkName(linkName);
        linkInfo.setOriginPathName(srcPathName);
        linkInfo.setFrame(linkFrames.get(linkName));
        linkInfo.setDestinationPathName(linkPathName);
        linkInfo.setDestinationFrameFrom(nbLinkFrameFrom);

        return linkInfo;
    }

    @Override
	public void mouseDragged(Rectangle rect) {		
    	spinnerX.setValue(rect.x);
    	spinnerY.setValue(rect.y);
    	spinnerWidth.setValue(rect.width);
    	spinnerHeight.setValue(rect.height);
	}
    
    @Override
	public void frameChanged(int nbFrame, BufferedImage image) {        
        HashMap<Integer, Rectangle> hm = linkFrames.get(curLinkName);
        if (hm.containsKey(nbFrame)) {
            Rectangle rect = hm.get(nbFrame);

            spinnerX.setValue(rect.x);
            spinnerY.setValue(rect.y);
            spinnerWidth.setValue(rect.width);
            spinnerHeight.setValue(rect.height);
        } else {
            spinnerX.setValue(0);
            spinnerY.setValue(0);
            spinnerWidth.setValue(0);
            spinnerHeight.setValue(0);
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
