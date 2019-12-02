package i18n;

public interface DlgConst {

	//button on main frame
	String MAIN_FRAME_TITLE = "NOVEL IMPORT SYSTEM";
	String PAGE = "Page";
	String SORYBY = "ORDER BY:";
	String CREATE = "CREATE";
	String DELETE = "DELETE";
	String MODIFY = "MODIFY";
	String RECORD_PER_PAGE = " records per page";
	
	//text on dialogs
	String NOVEL_DLG_TITLE = "Novel Info";
	String OK = "OK";
	String ID = "ID";
	String TITLE = "TITLE";
	String PUBLISH_DATA = "PUBLISH TIME";
	String FOREWORD = "FOREWORD";
	String AUTHOR = "AUTHOR";
	String GENDER = "GENDER";
    String BORN = "BORN";
    String DEATH = "DEATH";
    String COUNTRY = "COUNTRY";
    String INTRODUCTION = "INTRODUCTION";

	String[] fields = new String[] {ID, AUTHOR, TITLE, PUBLISH_DATA, FOREWORD};
	
	//text for messages
	Object UNNORMALCLOSED = "The database was not closed properly. please fix it and try again.";
	String AUTHOR_SELECT_DLG_TITLE = "Select an author from the list or leave no one selected if none of them match. ";
	String FILE_IMPORTED_SUCCESSFULLY = " novels have been imported successfully!";
	String PLEASE_SELECT_FIRST = "Please select at least one record then try again.";
	String PLEASE_SELECT_ONE = "Please select only one record then try again.";
	String RECORD_DELETED = " records deleted successfully.";

	Object PLEASE_INPUT_A_NUMBER = "Please input a valid number.";
	Object PLEASE_INPUT_A_DATE = "Please input a valid DATE.";

	Object FIELD_IS_MANDATORY = "Mandatory field can not be empty.";
	String NEW_NOVEL_CREATED = "A new novel record added.";
	String NOVEL_UPDATED = "Novel record updated successfully.";
	String NO_FILE_SELECTED = "No file has been selected.";
	Object CAN_NOT_FIND_FILE = "System can not find the file specified.";

}
