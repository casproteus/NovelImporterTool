package model;

import java.security.InvalidParameterException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import i18n.DlgConst;
import ui.AuthorListDlg;
import ui.MainFrame;
import util.DBUtil;

public class Novel {
	private int id;
	private String title;
	private int authorId;
	private Date publishTime;
	private String foreword;
	
	//Transient
	private String authorName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public String getForeword() {
		return foreword;
	}
	public void setForeword(String foreword) {
		this.foreword = foreword;
	}
	//transient
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	//search------------------------------------------	
	public static List<Novel> listAllNovels(int limit, int offset, String sortField) {
		List<Novel> novelList = new ArrayList<Novel>();
		String query_sql = new StringBuilder("SELECT * FROM Novel left join Author on novel.authorid = author.ID ORDER BY ")
				.append(validate(sortField)).append(" desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY").toString();
		try {					
			PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(query_sql);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, limit);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Novel novel = new Novel();
				novel.setId(rs.getInt("id"));
				novel.setTitle(rs.getString("title"));
				novel.setAuthorId(rs.getInt("authorID"));
				novel.setAuthorName(rs.getString("name"));
				novel.setPublishTime(rs.getDate("publishtime"));
				novel.setForeword(rs.getString("foreword"));
				
				novelList.add(novel);
			}
		}catch(InvalidParameterException e) {
			JOptionPane.showMessageDialog(MainFrame.instance, "exception when validting parameter:" + e);
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(MainFrame.instance, "exception when excuting query:" + e);
		}
		return novelList;
	}
	
	private static String validate(String sortField) throws InvalidParameterException{
		switch (sortField) {
			case DlgConst.ID:
				return "novel.id";
			case DlgConst.AUTHOR:
				return "author.name";
			case DlgConst.TITLE:
				return "novel.title";
			case DlgConst.PUBLISH_DATA:
				return "novel.publishtime";
			case DlgConst.FOREWORD:
				return "novel.foreword";
			default:
				throw new InvalidParameterException();
		}
	}
	
	//insert--------------------------------------------
	public static void insertRecord(Novel novel) {
		int authorID = -1;
		String authorName = novel.getAuthorName();
		//check if there's no matching author, create a new one.
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
		
		String insert_sql = new StringBuilder("INSERT INTO NOVEL (TITLE, AUTHORID, PUBLISHTIME, FOREWORD) VALUES (?,?,?,?)").toString();
		try {					
			PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(insert_sql);
			//title
			pstmt.setString(1, novel.getTitle());
			//author
			pstmt.setInt(2, authorID);
			//publish time
			pstmt.setDate(3, novel.getPublishTime());
			//fore word
			pstmt.setString(4, novel.getForeword());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(" exception when restore table:" + e);
		}
	}

	//delete---------------------------------------------
	public static boolean deleteRecord(int id) {
		String query_sql = new StringBuilder("delete from novel where id = ?").toString();
		try {					
			PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(query_sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			return true;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(MainFrame.instance, "exception when excuting insert:" + e);
		}
		return false;
	}
	
	//update---------------------------------------------
	public static void updateRecord(Novel novel) {
		String query_sql = new StringBuilder("update novel set title = ?, AuthorId = ?, PublishTime = ?, Foreword = ? where id = ?").toString();
		try {					
			PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(query_sql);
			pstmt.setString(1, novel.getTitle());
			pstmt.setInt(2, novel.getAuthorId());
			pstmt.setDate(3, novel.getPublishTime());
			pstmt.setString(4, novel.getForeword());
			pstmt.setInt(5, novel.getId());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(MainFrame.instance, "exception when excuting insert:" + e);
		}
	}
}
