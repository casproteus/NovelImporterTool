package model;

import java.sql.PreparedStatement;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import util.DBUtil;

@Ignore
public class AuthorTest {
	
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
	
	private void cleanNewAddedAuthorRecords() {
		try {
			PreparedStatement pstmt = DBUtil.getConnection().prepareStatement("delete from Author ");
			pstmt.executeQuery();
		}catch(Exception e) {
			System.out.println("Exception when clean novel table.");
		}
	}
	
	//search------------------------------------------
	@Test
	public void testSearchByName() {
		//TDODO:
	}
	
	//insert--------------------------------------------
	@Test
	public void testInsertRecord() {
		cleanNewAddedAuthorRecords();
		//TDODO:
	}
	
}
