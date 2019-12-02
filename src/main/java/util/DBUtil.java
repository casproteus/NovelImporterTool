package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.apache.derby.drda.NetworkServerControl;

import i18n.DlgConst;

public class DBUtil {

	//db connecting------------------------------------------
	private static Connection connection;
	private static String url = "jdbc:derby://localhost:1527/books;create=true";
	private static boolean isDriverLoaded;
	private static boolean dbValidation;
    private static final String defaultDriver = "org.apache.derby.jdbc.ClientDriver";  // private static final String defaultDriver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String defaultUserName = "App";
    private static final String defaultPassword = "App";
    
    //default db table creating---------------------------------------
	private static String[] SYSTEMTABLE_NAME_LIST = new String[] {"author", "novel"};
    private static String TYPE_ID = " INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "; // ID
    //author table
    private static  String[] AUTHOR_TABLE_FIELD = new String[] { "ID", "NAME", "GENDER", "BORN", "DEATH", "COUNTRY", "INTRODUCTION"};
    private static String[] AUTHOR_TABLE_FIELD_TYPE = new String[] { TYPE_ID, " VARCHAR(100) NOT NULL, ", " INTEGER DEFAULT 0, ", " INTEGER, ", " INTEGER, ", " VARCHAR(100), ", " VARCHAR(32672)"};
    //novel table
    private static String[] NOVEL_TABLE_FIELD = new String[] { "ID", "TITLE", "AUTHORID", "PUBLISHTIME", "FOREWORD" };
    private static String[] NOVEL_TABLE_FIELD_TYPE = new String[] { TYPE_ID, " VARCHAR(100) NOT NULL, ", " INTEGER, ", " DATE, ", " VARCHAR(32672)"};
    //all tables array
    private static String[][] TABLE_FIELD_LIST = new String[][] { AUTHOR_TABLE_FIELD, AUTHOR_TABLE_FIELD_TYPE, NOVEL_TABLE_FIELD, NOVEL_TABLE_FIELD_TYPE};
    
    //default author table content--------------------------------
	private static final String[] AUTHOR_TABLE_DEFAULT_RECORDS = new String[] {
		"INSERT INTO author (name, Gender, BORN, DEATH, COUNTRY, introduction) values('Agatha Christie', 1, 1947, 2000, 'English','Whodunits including the Miss Marple and Hercule Poirot series')",
		"INSERT INTO author (name, Gender, BORN, DEATH, COUNTRY, introduction) values('Barbara Cartland', 1, 1956, 2000, 'English','Romance')",
		"INSERT INTO author (name, Gender, BORN, DEATH, COUNTRY, introduction) values('Danielle Steel', 1, 1924, 2000, 'English','Adventure')",
		"INSERT INTO author (name, Gender, BORN, DEATH, COUNTRY, introduction) values('Harold Robbins', 1, 1906, 2000, 'French','Detectives, Maigret, romans dur')",
		"INSERT INTO author (name, Gender, BORN, DEATH, COUNTRY, introduction) values('NameOfTwoAuthors', 1, 1806, 2000, 'French','Detectives, Maigret, romans dur')",
		"INSERT INTO author (name, Gender, BORN, DEATH, COUNTRY, introduction) values('NameOfTwoAuthors', 2, 1928, 2000, 'English','A man with smill on face')"
	};
	   
	public static boolean checkDataBase() {
		makesureDBStarted();
		connection = buildConnection(defaultUserName, defaultPassword);
        if (connection == null) {
            connection = buildConnection(defaultUserName, defaultPassword);
            if(connection == null) {
                JOptionPane.showMessageDialog(null, DlgConst.UNNORMALCLOSED);
            }
        }

		try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM author");
            dbValidation = (rs != null && rs.next());
        } catch (Exception e) {
            dbValidation = false;
        }

        if (!dbValidation) {
            try {
                createDatabase();
                ResultSet rs = connection.createStatement().executeQuery("select * from author");
                dbValidation = (rs != null && rs.next());
            } catch (Exception e) {
                dbValidation = false;
                e.printStackTrace();
            }
            reConnectDb();
        }
        return dbValidation;
	}
	
	public static Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        }else if (connection != null) {
            connection.close();
            connection = null;
        }
        //=====================
        connection = buildConnection(defaultUserName, defaultPassword);
        if (!dbValidation) // It's somewhat funny! dose it offen need to connect twice?
        {
            System.out.println("God! You can't believe it, method getConnection() failed! now System is ready to connec again!");
            connection = buildConnection(defaultUserName, defaultPassword);
        }
        return dbValidation ? connection : null;
    }
    
	private static boolean createDatabase() throws SQLException {

        StringBuilder tmpSQL = new StringBuilder();
        Statement tmpStateMent = connection.createStatement();
        for (int i = SYSTEMTABLE_NAME_LIST.length - 1; i >= 0; i--) {
            tmpSQL.setLength(0);
            tmpSQL.append("CREATE TABLE ").append(SYSTEMTABLE_NAME_LIST[i]).append(" (");
            String[] fieldList = TABLE_FIELD_LIST[i * 2];
            String[] fieldTypeList = TABLE_FIELD_LIST[i * 2 + 1];
            int tmpLength = fieldList.length;
            for (int j = 0; j < tmpLength; j++)
                tmpSQL.append(fieldList[j]).append(fieldTypeList[j]);
            tmpSQL.append(")");
            try {
                tmpStateMent.executeUpdate(tmpSQL.toString());
            } catch (Exception e) {
                System.out.println(tmpSQL.toString() + e);
            }
        }
        
        for (int i = AUTHOR_TABLE_DEFAULT_RECORDS.length - 1; i >= 0; i--) {
            try {
                tmpStateMent.executeUpdate(AUTHOR_TABLE_DEFAULT_RECORDS[i]);
            } catch (Exception e) {
                System.out.println(tmpSQL.toString() + e);
            }
        }

        dbValidation = true;
        tmpStateMent.close();
        tmpStateMent = null;
        return true;
    }
	 
	private static Connection buildConnection(
            String user,
            String pwd) {
        if (!isDriverLoaded && !loadDriver()) {
            dbValidation = false;
            return null;
        } else {
            try {
                Connection tConn = DriverManager.getConnection(getUrl(), user, pwd); // 得到数据库的连接.derby的参数:(url.concat(";create=true"));
                dbValidation = true;
                return tConn;
            } catch (SQLException e) {
                dbValidation = false;
            }
            return null;
        }
    }
	
	private static void makesureDBStarted() {
    	try {
            NetworkServerControl server = new NetworkServerControl();
            server.start (null);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
    private static boolean loadDriver() {
        try {
            Class.forName(defaultDriver).newInstance();
            isDriverLoaded = true;
        } catch (Exception e) {
            isDriverLoaded = false;
        }
        return isDriverLoaded;
    }
    
    private static void reConnectDb() {
        // SwingUtilities.invokeLater(new Runnable() {
        // @Override
        // public void run() {
        if (connection != null)
            try {
                if (!connection.isClosed())
                    connection.close();
                connection = buildConnection(defaultUserName, defaultPassword);
            } catch (SQLException e) {
                connection = null;
                e.printStackTrace();
            }
        // }
        // });
    }

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		DBUtil.url = url;
	}
    
}
