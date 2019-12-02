package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import junit.framework.Assert;
import util.DBUtil;

public class NovelTest {
		
	@Before
	public void startATestDB() {
		//create a db(books_test) for unit test only.
		DBUtil.setUrl("jdbc:derby://localhost:1527/books_test;create=true");
		DBUtil.checkDataBase();
	}

	@After
	public void closeTestDB() {
		try {
			DBUtil.getConnection().close();
		}catch(Exception e) {
			System.out.println("Exception happend when closing the connection to db.");
		}
	}
	
	//search------------------------------------------	
	@Test
	public void testListAllNovels() {
		cleanNovel();
		//prepare test record
		Novel novel = new Novel();
		novel.setTitle("zzzzzz");
		novel.setPublishTime(Date.valueOf("3020-12-12"));
		novel.setAuthorName("ZZZ");
		novel.setForeword("ZZZ");
		Novel.insertRecord(novel);
		
		novel.setTitle("zzzzzy");
		novel.setPublishTime(Date.valueOf("3020-12-12"));
		novel.setAuthorName("ZZy");
		novel.setForeword("ZZy");
		Novel.insertRecord(novel);
		
		List<Novel> lists = Novel.listAllNovels(100, 0, "ID");
		Assert.assertEquals(2, lists.size());
		
	}

	//insert--------------------------------------------
	@Test
	@Ignore
	public void testInsertRecord() {
		//TDODO:
	}

	//delete---------------------------------------------
	@Test
	@Ignore
	public void testDeleteRecord() {
		//TDODO:
	}
	
	//update---------------------------------------------
	@Test
	@Ignore
	public void testUpdateRecord() {
		//TDODO:
	}
	
	//db reset.
	private void cleanNovel() {
		try {
			PreparedStatement pstmt = DBUtil.getConnection().prepareStatement("delete from novel");
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("Exception when clean novel table.");
		}
	}
}
