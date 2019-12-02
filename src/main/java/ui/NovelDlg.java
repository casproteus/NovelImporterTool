package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import i18n.DlgConst;
import model.Author;
import model.Novel;

public class NovelDlg extends JDialog implements ActionListener, ComponentListener{

	private static final long serialVersionUID = 1L;
	private Novel novel;
	
    public NovelDlg(JFrame pParent, Novel novel) {
        super(pParent, true);
        this.novel = novel;
        initDialog();
        initContent(novel);
    }

	private void initDialog() {
        setTitle(DlgConst.NOVEL_DLG_TITLE);
        
        lblTitle = new JLabel(DlgConst.TITLE);
        valTitle = new JTextField();
        lblAuthor = new JLabel(DlgConst.AUTHOR);
        valAuthor = new JTextField();
        lblPublishTime = new JLabel(DlgConst.PUBLISH_DATA);
        valPublishTime = new JTextField();
        lblForeWord = new JLabel(DlgConst.FOREWORD);
        valForeWord = new JTextArea();
        btnOK = new JButton(DlgConst.OK);
        
        // properties
        btnOK.setMnemonic('O');
        btnOK.setMargin(btnOK.getMargin());
        valForeWord.setLineWrap(true);
        getRootPane().setDefaultButton(btnOK);

        setBounds((UICons.size.width - 540) / 2, (UICons.size.height - 320) / 2, 540, 320); // 对话框的默认尺寸。
        getContentPane().setLayout(null);

        getContentPane().add(lblTitle);
        getContentPane().add(valTitle);
        getContentPane().add(lblAuthor);
        getContentPane().add(valAuthor);
        getContentPane().add(lblPublishTime);
        getContentPane().add(valPublishTime);
        getContentPane().add(lblForeWord);
        getContentPane().add(valForeWord);
        getContentPane().add(btnOK);

        btnOK.addActionListener(this);
        getContentPane().addComponentListener(this);
    }
    
    private void initContent(Novel novel) {
		if(novel == null) {
			return;
		}
		valTitle.setText(novel.getTitle());
		valAuthor.setText(novel.getAuthorName());
		valPublishTime.setText(novel.getPublishTime().toString());
		valForeWord.setText(novel.getForeword());
	}
    
    public void reLayout() {
    	lblTitle.setBounds(UICons.HOR_GAP, UICons.VER_GAP, 80, UICons.BTN_HEIGHT);
        valTitle.setBounds(lblTitle.getX() + lblTitle.getWidth() + UICons.HOR_GAP, lblTitle.getY(), 250,  UICons.BTN_HEIGHT);
        
        lblAuthor.setBounds(lblTitle.getX(), lblTitle.getY() + lblTitle.getHeight() + UICons.VER_GAP, 80,  UICons.BTN_HEIGHT);
        valAuthor.setBounds(lblAuthor.getX() + lblAuthor.getWidth() + UICons.HOR_GAP, lblAuthor.getY(), 200,  UICons.BTN_HEIGHT);
        
        lblPublishTime.setBounds(lblAuthor.getX(), lblAuthor.getY() + lblAuthor.getHeight() + UICons.VER_GAP, 80,  UICons.BTN_HEIGHT);
        valPublishTime.setBounds(lblPublishTime.getX() + lblPublishTime.getWidth() + UICons.HOR_GAP, lblPublishTime.getY(), 150,  UICons.BTN_HEIGHT);
        
        lblForeWord.setBounds(lblPublishTime.getX(), lblPublishTime.getY() + lblPublishTime.getHeight() + UICons.VER_GAP, 80,  UICons.BTN_HEIGHT);
        valForeWord.setBounds(lblForeWord.getX() + lblForeWord.getWidth() + UICons.HOR_GAP, lblForeWord.getY(), getWidth() - 130,  getHeight() - lblForeWord.getY() - 90);
        
        btnOK.setBounds(getWidth() - UICons.SIZE_EDGE * 4 - UICons.HOR_GAP * 2 - UICons.BTN_WIDTH, 
        		getHeight() - 80, UICons.BTN_WIDTH, UICons.BTN_HEIGHT);

        validate();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        reLayout();
    };

    @Override
    public void componentMoved(ComponentEvent e) {};

    @Override
    public void componentShown(ComponentEvent e) {};

    @Override
    public void componentHidden(ComponentEvent e) {};

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == btnOK) {
        	if(novel == null) {	//creating
        		//title
        		String title = valTitle.getText();
        		if(title.length() == 0) {
        			JOptionPane.showMessageDialog(this, DlgConst.FIELD_IS_MANDATORY);
        			valTitle.grabFocus();
        			return;
        		}
        		//authorid
        		int authorID = -1;
        		String authorName = valAuthor.getText();
        		if(authorName.length() > 0) {
        			List<Author> list = Author.searchByName(authorName);
        			if(list.size() < 1) {
        				authorID = Author.insertRecord(authorName);
        			}else if(list.size() == 1) {
        				authorID = list.get(0).getId();
        			}else {
        				//there's more than one author exist. ask user to select one.
        				//show up a dialog with table inside. waiting for user to select one from it.
        				AuthorListDlg authorListDlg = new AuthorListDlg(MainFrame.instance, list);
        				authorListDlg.setVisible(true);
        				authorID = authorListDlg.selectedAuthorID;
        				if(authorID < 0) {
        					authorID = Author.insertRecord(authorName);
        				}
        			}
        		}
        		//check date
        		Date date = null;
        		String publishDate = valPublishTime.getText();
        		if(publishDate.length() > 0) {
	        		try {
	        			date = Date.valueOf(publishDate);
	        		}catch(Exception exp) {
	        			JOptionPane.showMessageDialog(this, DlgConst.PLEASE_INPUT_A_DATE);
	        			valPublishTime.selectAll();
	        			valPublishTime.grabFocus();
	        			return;
	        		}
        		}
        		//start to insert
        		Novel novel = new Novel();
        		novel.setTitle(title);
        		novel.setAuthorId(authorID);
        		novel.setAuthorName(authorName);
        		novel.setPublishTime(date);
        		novel.setForeword(valForeWord.getText());
        		Novel.insertRecord(novel);
        		//refresh ui
        		MainFrame.instance.initTable();
        		MainFrame.setStatusMes(DlgConst.NEW_NOVEL_CREATED);
        		
        	}else {	//updating------------------------------------------
        		//title
        		String title = valTitle.getText();
        		if(title.length() == 0) {
        			JOptionPane.showMessageDialog(this, DlgConst.FIELD_IS_MANDATORY);
        			valTitle.grabFocus();
        			return;
        		}
        		//authorid
        		int authorID = novel.getAuthorId();
        		String authorName = valAuthor.getText();
        		if(authorName.length() > 0 && !authorName.equals(novel.getAuthorName())) {
        			List<Author> list = Author.searchByName(authorName);
        			if(list.size() < 1) {
        				authorID = Author.insertRecord(authorName);
        			}else if(list.size() == 1) {
        				authorID = list.get(0).getId();
        			}else {
        				//there's more than one author exist. ask user to select one.
        				//show up a dialog with table inside. waiting for user to select one from it.
        				AuthorListDlg authorListDlg = new AuthorListDlg(MainFrame.instance, list);
        				authorListDlg.setVisible(true);
        				authorID = authorListDlg.selectedAuthorID;
        				if(authorID < 0) {
        					authorID = Author.insertRecord(authorName);
        				}
        			}
        		}else if(authorName.length() == 0) {
        			authorID = -1;
        		}
        		//check date
        		Date date = null;
        		String publishDate = valPublishTime.getText();
        		if(publishDate.length() > 0) {
	        		try {
	        			date = Date.valueOf(publishDate);
	        		}catch(Exception exp) {
	        			JOptionPane.showMessageDialog(this, DlgConst.PLEASE_INPUT_A_DATE);
	        			valPublishTime.selectAll();
	        			valPublishTime.grabFocus();
	        			return;
	        		}
        		}
        		//start to insert
        		Novel newNovel = new Novel();
        		newNovel.setId(novel.getId());
        		newNovel.setTitle(title);
        		newNovel.setAuthorId(authorID);
        		newNovel.setAuthorName(authorName);
        		newNovel.setPublishTime(date);
        		newNovel.setForeword(valForeWord.getText());
        		Novel.updateRecord(newNovel);
        		//refresh ui
        		MainFrame.instance.initTable();
        		MainFrame.setStatusMes(DlgConst.NOVEL_UPDATED);
        	}
        	
            dispose();
        }
    }

    private JLabel lblTitle;
    private JTextField valTitle;
    private JLabel lblAuthor;
    private JTextField valAuthor;
    private JLabel lblPublishTime;
    private JTextField valPublishTime;
    private JLabel lblForeWord;
    private JTextArea valForeWord;
    private JButton btnOK;
}
