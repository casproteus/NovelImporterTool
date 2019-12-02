package ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import i18n.DlgConst;
import model.Novel;
import util.DBUtil;
import util.XMLUtil;

public class MainFrame extends JFrame implements WindowListener, ComponentListener, ActionListener, FocusListener, KeyListener, ItemListener {

	private static final long serialVersionUID = 1L;

	public static MainFrame instance;

	private int limit = 30;
	private int offset = 0;
	private String sortField = DlgConst.ID;
	private List<Novel> curList;
    
	public static void main(String[] args) {
        instance = new MainFrame();
        DBUtil.checkDataBase();
        
        instance.initComponent();
        instance.setVisible(true);
	}
	
	private void initComponent(){
    	getContentPane().removeAll();
    	setTitle(DlgConst.MAIN_FRAME_TITLE);
    	
    	setBounds((UICons.size.width - 1024) / 2, (UICons.size.height - 768) / 2, 1024, 768);
    	setMinimumSize(new Dimension(900, 300));
        getContentPane().setLayout(null);
        setResizable(true);

        lblSelectFile = new JLabel("Input File Path:");
        btnSelectFile = new JButton("...");
        valFilePath = new JTextField("c:/novels.xml");
        btnImport = new JButton("Import");
        jTable = new JTable();
        jScrollPane = new JScrollPane(jTable);

        tfdLimit = new JTextField(String.valueOf(limit));
        lblLimit = new JLabel(DlgConst.RECORD_PER_PAGE);
        btnPageUp = new JButton("<<");
        lblPage = new JLabel(DlgConst.PAGE);
        tfdPage = new JTextField(String.valueOf(offset/limit));
        btnPageDown = new JButton(">>");
        lblSorBy = new JLabel(DlgConst.SORYBY);
        cmbSortBy = new JComboBox<String>(DlgConst.fields);
        
        btnCreate = new JButton(DlgConst.CREATE);
        btnDelete = new JButton(DlgConst.DELETE);
        btnModify = new JButton(DlgConst.MODIFY);
        
        lblStatus = new JLabel();
        lblVersion = new JLabel("--Wrote by Tao Jiang.  Version:0.1-20191124");
        
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable.setAutoscrolls(true);
        jTable.setRowHeight(20);
        jTable.setBorder(new JTextField().getBorder());
        jTable.setFocusable(false);

        JLabel cornerTable = new JLabel();
        cornerTable.setOpaque(true);
        cornerTable.setBackground(Color.WHITE);
        jScrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, cornerTable);
        
        lblStatus.setBackground(Color.WHITE);
        lblStatus.setOpaque(true);
        lblVersion.setBackground(Color.WHITE);
        lblVersion.setOpaque(true);
        
        add(lblSelectFile);
        add(btnSelectFile);
        add(valFilePath);
        add(btnImport);
        add(jScrollPane);
        add(tfdLimit);
        add(lblLimit);
        add(btnPageUp);
        add(lblPage);
        add(tfdPage);
        add(btnPageDown);
        add(lblSorBy);
        add(cmbSortBy);
        add(btnCreate);
        add(btnDelete);
        add(btnModify);
        add(lblStatus);
        add(lblVersion);

        initTable();
        
        tfdLimit.addFocusListener(this);
        tfdPage.addFocusListener(this);
        tfdLimit.addKeyListener(this);
        tfdPage.addKeyListener(this);
        cmbSortBy.addItemListener(this);
        btnSelectFile.addActionListener(this);
        btnPageUp.addActionListener(this);
        btnPageDown.addActionListener(this);
        btnImport.addActionListener(this);
        btnCreate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnModify.addActionListener(this);
        addWindowListener(this);
        addComponentListener(this);
        
    }
    
	public static void setStatusMes(String pMes) {
        lblStatus.setText(pMes);
    }
	
    private void reLayout() {
    	lblSelectFile.setBounds(UICons.HOR_GAP, 
    			UICons.VER_GAP, 
    			lblSelectFile.getPreferredSize().width,
    			40);
    	btnSelectFile.setBounds(lblSelectFile.getX() + lblSelectFile.getWidth() + UICons.HOR_GAP/3, lblSelectFile.getY() + 5,
    			btnSelectFile.getPreferredSize().width,
    			30);
        btnImport.setBounds(getWidth() - btnImport.getPreferredSize().width - UICons.SIZE_EDGE * 2 - UICons.HOR_GAP * 2,
        		UICons.VER_GAP,
        		btnImport.getPreferredSize().width,
				40);
		valFilePath.setBounds(
				btnSelectFile.getX() + btnSelectFile.getWidth() + UICons.HOR_GAP / 3,
				UICons.VER_GAP,
				btnImport.getX() - lblSelectFile.getWidth() - btnSelectFile.getWidth() - UICons.HOR_GAP * 2 + UICons.SIZE_EDGE,
				40);
    	
    	jScrollPane.setBounds(UICons.HOR_GAP, 
    			lblSelectFile.getY() + lblSelectFile.getHeight() + UICons.VER_GAP, 
        		getWidth() - UICons.SIZE_EDGE * 2 - UICons.HOR_GAP  * 3, 
                getHeight() - UICons.SIZE_TITLE - UICons.SIZE_EDGE - UICons.VER_GAP * 7  - UICons.BTN_HEIGHT - UICons.BTN_HEIGHT);

    	//Pagination tools
    	tfdLimit.setBounds(UICons.HOR_GAP, 
    			jScrollPane.getY() + jScrollPane.getHeight() + UICons.VER_GAP,
    			40, UICons.BTN_HEIGHT);
        lblLimit.setBounds(tfdLimit.getX() + tfdLimit.getWidth(), 
        		tfdLimit.getY(),
        		lblLimit.getPreferredSize().width,
        		UICons.BTN_HEIGHT);
        
        btnPageUp.setBounds(lblLimit.getX() + lblLimit.getWidth() + UICons.HOR_GAP * 4,
        		lblLimit.getY(),
        		60, UICons.BTN_HEIGHT);
        lblPage.setBounds(btnPageUp.getX() + btnPageUp.getWidth() + UICons.HOR_GAP / 3,
        		btnPageUp.getY(),
        		lblPage.getPreferredSize().width,
        		UICons.BTN_HEIGHT);
        tfdPage.setBounds(lblPage.getX() + lblPage.getWidth(),
        		lblPage.getY(),
        		40, UICons.BTN_HEIGHT);
        btnPageDown.setBounds(tfdPage.getX() + tfdPage.getWidth() + UICons.HOR_GAP / 3,
        		tfdPage.getY(),
        		60, UICons.BTN_HEIGHT);
        
        lblSorBy.setBounds(btnPageDown.getX() + btnPageDown.getWidth() +  UICons.HOR_GAP * 4,
        		btnPageDown.getY(), 
        		lblSorBy.getPreferredSize().width, UICons.BTN_HEIGHT);
        cmbSortBy.setBounds(lblSorBy.getX() + lblSorBy.getWidth() + UICons.HOR_GAP / 3, 
        		lblSorBy.getY(),
        		80, UICons.BTN_HEIGHT);
        
        //function buttons
    	btnDelete.setBounds(jScrollPane.getX() + jScrollPane.getWidth() - UICons.BTN_WIDTH,
    			btnPageDown.getY(),
    			UICons.BTN_WIDTH,
    			UICons.BTN_HEIGHT);
    	btnModify.setBounds(btnDelete.getX() - UICons.HOR_GAP - UICons.BTN_WIDTH,
    			btnDelete.getY(),
    			UICons.BTN_WIDTH,
    			UICons.BTN_HEIGHT);
    	btnCreate.setBounds(btnModify.getX() - UICons.HOR_GAP - UICons.BTN_WIDTH,
    			btnModify.getY(),
    			UICons.BTN_WIDTH,
    			UICons.BTN_HEIGHT);
        // status---------
        lblStatus.setBounds(0, 
        		instance.getHeight() - UICons.LBL_HEIGHT - UICons.VER_GAP*4, 
        		instance.getWidth() - 275, 
        		UICons.LBL_HEIGHT);
        lblVersion.setBounds(lblStatus.getX() + lblStatus.getWidth(), 
        		lblStatus.getY(), 
        		275, 
        		UICons.LBL_HEIGHT);
        
        TableColumnModel tTCM = jTable.getColumnModel();
        tTCM.getColumn(0).setPreferredWidth(170);
        tTCM.getColumn(1).setPreferredWidth(130);
        tTCM.getColumn(2).setPreferredWidth(90);
        tTCM.getColumn(4).setPreferredWidth(60);
        tTCM.getColumn(3).setPreferredWidth(getWidth() - 487);
        validate();
    }
    
    @Override
	public void actionPerformed(ActionEvent e) {
    	Object source = e.getSource();
		if (source == btnSelectFile) {
			JFileChooser jfc = new JFileChooser();
			int status = jfc.showOpenDialog(this);
			if (status != JFileChooser.APPROVE_OPTION) {
				setStatusMes(DlgConst.NO_FILE_SELECTED);
			} else {
				File file = jfc.getSelectedFile();
				valFilePath.setText(file.getAbsolutePath());
			}
		} else if (source == btnImport) {
			XMLUtil.importNovels(valFilePath.getText());
    		initTable();
    		
    	}else if(source == btnPageUp) {
    		int curPage = Integer.valueOf(tfdPage.getText());
    		curPage = curPage - 1 > 0 ? curPage - 1 : 0;
    		tfdPage.setText(String.valueOf(curPage));
    		offset = curPage * limit;
    		initTable();
    		
    	}else if(source == btnPageDown) {
    		int curPage = Integer.valueOf(tfdPage.getText());
    		curPage++;
    		tfdPage.setText(String.valueOf(curPage));
    		offset = curPage * limit;
    		initTable();
    		
    	}else if(source == btnCreate) {
    		NovelDlg novelDlg = new NovelDlg(this, null);
    		novelDlg.setVisible(true);
    		
    	}else if(source == btnDelete) {
    		int[] selectedRow = jTable.getSelectedRows();
    		if(selectedRow.length == 0) {
    			JOptionPane.showMessageDialog(this, DlgConst.PLEASE_SELECT_FIRST);
    			return;
    		}
    		for(int i = 0; i < selectedRow.length; i++) {
	    		int id = (int) jTable.getValueAt(selectedRow[i], 4);
	    		Novel.deleteRecord(id);
    		}

			setStatusMes(selectedRow.length + DlgConst.RECORD_DELETED);
    		initTable();
    		
    	}else if(source == btnModify) {
    		int[] selectedRow = jTable.getSelectedRows();
    		if(selectedRow.length == 0) {
    			JOptionPane.showMessageDialog(this, DlgConst.PLEASE_SELECT_FIRST);
    			return;
    		}else if(selectedRow.length > 1) {
    			JOptionPane.showMessageDialog(this, DlgConst.PLEASE_SELECT_ONE);
    			return;
    		}
    		
    		NovelDlg novelDlg = new NovelDlg(this, curList.get(selectedRow[0]));
    		novelDlg.setVisible(true);
    	}
	}

	@Override
	public void focusGained(FocusEvent e) {
		JTextField tfd = (JTextField)e.getSource();
		tfd.selectAll();
	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField tfd = (JTextField)e.getSource();
		String content = tfd.getText();
		if(content.length() == 0) {
			JOptionPane.showMessageDialog(this, DlgConst.PLEASE_INPUT_A_NUMBER);
			tfd.grabFocus();
		}else {
			int num = -1;
			try {
				num = Integer.valueOf(content);
				if(num < 0 || (num == 0 && tfd == tfdLimit)) {
					JOptionPane.showMessageDialog(this, DlgConst.PLEASE_INPUT_A_NUMBER);
					tfd.grabFocus();
					return;
				}
			}catch(Exception exp) {
				JOptionPane.showMessageDialog(this, DlgConst.PLEASE_INPUT_A_NUMBER);
				tfd.grabFocus();
				return;
			}
			if(tfd == tfdLimit) {
				limit = num;
			}else if(tfd == tfdPage) {
				offset = limit * num;
			}

	        initTable();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_ENTER ) {  
			btnCreate.grabFocus();
        }  
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
    /** Invoked when the component's size changes. */
    @Override
    public void componentResized(ComponentEvent e) {
        reLayout();
    };

	@Override
	public void itemStateChanged(ItemEvent e) {
		sortField = cmbSortBy.getSelectedItem().toString();
		initTable();
	}
	
    /** Invoked when the component's position changes. */
    @Override
    public void componentMoved(ComponentEvent e) {};

    /** Invoked when the component has been made visible. */
    @Override
    public void componentShown(ComponentEvent e) {};

    /** Invoked when the component has been made invisible. */
    @Override
    public void componentHidden(ComponentEvent e) {};

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {}

	@Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowDeactivated(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowOpened(WindowEvent e) {}

    void initTable() {
    	initTable(Novel.listAllNovels(limit, offset, sortField));
    }
    
    private void initTable(List<Novel> list) {
    	this.curList = list;
        Object[][] tableModel = new Object[list.size()][5];
        
        for(int i = 0; i < list.size(); i++) {
        	Novel novel = list.get(i);
            tableModel[i][0] = novel.getTitle();
            tableModel[i][1] = novel.getAuthorName();
            tableModel[i][2] = novel.getPublishTime();
            tableModel[i][3] = novel.getForeword();
            tableModel[i][4] = novel.getId();
        }
        
        DefaultTableModel model = new DefaultTableModel();
        model.setDataVector(tableModel, header);
        jTable.setModel(model);
        reLayout();
    }

    private String[] header = new String[] { 
    		DlgConst.TITLE,
    		DlgConst.AUTHOR,
            DlgConst.PUBLISH_DATA,
            DlgConst.FOREWORD,
            DlgConst.ID
    };

    private JTable jTable;
    private JScrollPane jScrollPane;
    private JLabel lblSelectFile;
    private JButton btnSelectFile;
    private JTextField valFilePath;
    private JButton btnImport;
    private static JLabel lblStatus;

    private JTextField tfdLimit;
    private JLabel lblLimit;
    private JButton btnPageUp;
    private JLabel lblPage;
    private JTextField tfdPage;
    private JButton btnPageDown;
    private JLabel lblSorBy;
    private JComboBox<String> cmbSortBy;
    
    private JButton btnCreate;
    private JButton btnDelete;
    private JButton btnModify;
    
    private JLabel lblVersion;

}
