package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import i18n.DlgConst;
import model.Author;

public class AuthorListDlg extends JDialog implements ActionListener, ComponentListener{

	private static final long serialVersionUID = 1L;

	public int selectedAuthorID = -1;
    
    public AuthorListDlg(JFrame pParent, List<Author> list) {
        super(pParent, true);
        initDialog();
        initTable(list);
    }
    
    private void initDialog() {
        setTitle(DlgConst.AUTHOR_SELECT_DLG_TITLE);

        jTable = new JTable();
        jScrollPane = new JScrollPane(jTable);
        btnOK = new JButton(DlgConst.OK);
        
        // properties
        btnOK.setMnemonic('O');
        btnOK.setMargin(btnOK.getMargin());

        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable.setAutoscrolls(true);
        jTable.setRowHeight(20);
        jTable.setBorder(new JTextField().getBorder());
        jTable.setFocusable(false);

        JLabel cornerTable = new JLabel();
        cornerTable.setOpaque(true);
        cornerTable.setBackground(Color.GRAY);
        jScrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, cornerTable);
        
        getRootPane().setDefaultButton(btnOK);

        setBounds((UICons.size.width - 540) / 2, (UICons.size.height - 320) / 2, 540, 320); // 对话框的默认尺寸。
        getContentPane().setLayout(null);

        getContentPane().add(jScrollPane);
        getContentPane().add(btnOK);

        btnOK.addActionListener(this);
        getContentPane().addComponentListener(this);
    }

    private void reLayout() {
        jScrollPane.setBounds(UICons.HOR_GAP, 
        		UICons.VER_GAP, 
        		getWidth() - UICons.SIZE_EDGE * 2 - UICons.HOR_GAP  * 3, 
                getHeight() - UICons.SIZE_TITLE - UICons.SIZE_EDGE - UICons.VER_GAP * 3  - UICons.BTN_HEIGHT);

        btnOK.setBounds(getWidth() - UICons.HOR_GAP * 3 - UICons.BTN_WIDTH,
                getHeight() - UICons.SIZE_TITLE - UICons.BTN_HEIGHT - UICons.VER_GAP, 
                UICons.BTN_WIDTH, 
                UICons.BTN_HEIGHT);// 关闭
        
        TableColumnModel tTCM = jTable.getColumnModel();
        tTCM.getColumn(0).setPreferredWidth(120);
        tTCM.getColumn(1).setPreferredWidth(40);
        tTCM.getColumn(2).setPreferredWidth(60);
        tTCM.getColumn(3).setPreferredWidth(60);
        tTCM.getColumn(4).setPreferredWidth(80);
        tTCM.getColumn(5).setPreferredWidth(190);
        tTCM.getColumn(6).setPreferredWidth(40);

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
        	this.selectedAuthorID = jTable.getSelectedRow() > -1 ? (int)jTable.getValueAt(jTable.getSelectedRow(), 6) : -1;
            dispose();
        }
    }

    private void initTable(List<Author> list) {
        Object[][] tableModel = new Object[list.size()][7];
        
        for(int i = 0; i < list.size(); i++) {
        	Author author = list.get(i);
            tableModel[i][0] = author.getName();
            tableModel[i][1] = author.getGender();
            tableModel[i][2] = author.getBorn();
            tableModel[i][3] = author.getDeath();
            tableModel[i][4] = author.getCountry();
            tableModel[i][5] = author.getIntroduction();
            tableModel[i][6] = author.getId();
        }
        
        DefaultTableModel model = new DefaultTableModel();
        model.setDataVector(tableModel, header);
        jTable.setModel(model);
    }

    private String[] header = new String[] { 
    		DlgConst.AUTHOR,
            DlgConst.GENDER,
            DlgConst.BORN,
            DlgConst.DEATH,
            DlgConst.COUNTRY,
            DlgConst.INTRODUCTION,
            DlgConst.ID
    };

    private JTable jTable;
    private JScrollPane jScrollPane;
    private JButton btnOK;
}
