package util;

import java.io.File;
import java.sql.Date;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import i18n.DlgConst;
import model.Novel;
import ui.MainFrame;


public class XMLUtil {
	
	public static void importNovels(String filePath) {
		File file = new File(filePath);
		if(!file.exists()) {
			JOptionPane.showMessageDialog(MainFrame.instance, "Please input an valid full file path.");
			return;
		}
		
		Document document = null;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
		} catch (Exception exp) {
			JOptionPane.showMessageDialog(MainFrame.instance, "Unexpected file format. Please specify a file with right format.\n" + exp);
			return;
		}
		
		NodeList tableNodes = document.getElementsByTagName("novel");
		for(int i = 0; i < tableNodes.getLength(); i++) {

			Node novelNode = tableNodes.item(i);
			
			Novel novel = new Novel();
			//title
			novel.setTitle(novelNode.getAttributes().item(0).getNodeValue());
			//author
			String authorName = ((Element)novelNode).getElementsByTagName("author").item(0).getTextContent();
			novel.setAuthorName(authorName);
			//publish time
			String publishTime = ((Element)novelNode).getElementsByTagName("publishTime").item(0).getTextContent();
			try {
				novel.setPublishTime(Date.valueOf(publishTime));
			}catch(IllegalArgumentException e) {
				JOptionPane.showMessageDialog(MainFrame.instance, "Found invalid date info which will be ignored:" + publishTime);
			}
			//fore word
			novel.setForeword(((Element)novelNode).getElementsByTagName("foreword").item(0).getTextContent());
			
			Novel.insertRecord(novel);
		}
		MainFrame.setStatusMes(tableNodes.getLength() + DlgConst.FILE_IMPORTED_SUCCESSFULLY);
	}

}
