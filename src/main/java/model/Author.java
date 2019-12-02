package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ui.MainFrame;
import util.DBUtil;

public class Author {
	private int id;
	private String name;
	private int gender;
	private int born;
	private int death;
	private String country;
	private String introduction;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getBorn() {
		return born;
	}
	public void setBorn(int born) {
		this.born = born;
	}
	public int getDeath() {
		return death;
	}
	public void setDeath(int death) {
		this.death = death;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	//search------------------------------------------
	public static List<Author> searchByName(String authorName) {
		List<Author> authorList = new ArrayList<Author>();
		String query_sql = new StringBuilder("select * from author where name = ?").toString();
		try {					
			PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(query_sql);
			pstmt.setString(1, authorName);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Author author = new Author();
				author.setId(rs.getInt("id"));
				author.setName(rs.getString("name"));
				author.setGender(rs.getInt("gender"));
				author.setBorn(rs.getInt("born"));
				author.setDeath(rs.getInt("death"));
				author.setCountry(rs.getString("country"));
				author.setIntroduction(rs.getString("introduction"));
				
				authorList.add(author);
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(MainFrame.instance, "exception when excuting query:" + e);
		}
		return authorList;
	}
	
	//insert--------------------------------------------
	public static int insertRecord(String authorName) {
		String query_sql = new StringBuilder("insert into author (name) values (?)").toString();
		try {					
			PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(query_sql);
			pstmt.setString(1, authorName);
			pstmt.executeUpdate();
			return findLatestRecordIdByName(authorName);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(MainFrame.instance, "exception when excuting insert:" + e);
		}
		return -1;
	}
	
	private static int findLatestRecordIdByName(String authorName) {
		String query_sql = new StringBuilder("select id from author where name = ? order by id desc FETCH FIRST ROW ONLY").toString();
		try {					
			PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(query_sql);
			pstmt.setString(1, authorName);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("id");
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(MainFrame.instance, "exception when excuting query:" + e);
		}
		return -1;
	}
	
//	======================================following not used for now, will be open next step.====================================
//	public static void insertRecord(Author author) {
//		String query_sql = new StringBuilder("insert into author (name, gender, born, death, country, introduction) values (?, ?, ?, ?, ?, ?)").toString();
//		try {					
//			PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(query_sql);
//			pstmt.setString(1, author.getName());
//			pstmt.setInt(2, author.getGender());
//			pstmt.setInt(3, author.getBorn());
//			pstmt.setInt(4, author.getDeath());
//			pstmt.setString(5, author.getCountry());
//			pstmt.setString(6, author.getIntroduction());
//			pstmt.executeUpdate();
//		}catch(Exception e) {
//			JOptionPane.showMessageDialog(MainFrame.instance, "exception when excuting insert:" + e);
//		}
//	}
//	
//	//delete---------------------------------------------
//	public static void deleteRecord(int id) {
//		String query_sql = new StringBuilder("delete from author where id = ?").toString();
//		try {					
//			PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(query_sql);
//			pstmt.setInt(1, id);
//			pstmt.executeUpdate();
//		}catch(Exception e) {
//			JOptionPane.showMessageDialog(MainFrame.instance, "exception when excuting insert:" + e);
//		}
//	}
//	
//	//update---------------------------------------------
//	public static void updateRecord(Author author) {
//		String query_sql = new StringBuilder("update author set name = ?, gender = ?, born = ?, death = ?, country = ?, introduction = ? where id = ?").toString();
//		try {					
//			PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(query_sql);
//			pstmt.setString(1, author.getName());
//			pstmt.setInt(2, author.getGender());
//			pstmt.setInt(3, author.getBorn());
//			pstmt.setInt(4, author.getDeath());
//			pstmt.setString(5, author.getCountry());
//			pstmt.setString(6, author.getIntroduction());
//			
//			pstmt.setInt(6, author.getId());
//			
//			pstmt.executeUpdate();
//		}catch(Exception e) {
//			JOptionPane.showMessageDialog(MainFrame.instance, "exception when excuting insert:" + e);
//		}
//	}
}
