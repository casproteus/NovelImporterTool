# NovelImporterTool
a demo application of swing, derby and xml

INFORMATION 
---------------------------------------------------------------------------------------------  
This is a tool for importing novel info from text file into derby database. 
The derby is launched in server mode, so you can use a db client to monitor the variation when tool is running.
Considering that it's a more UI based application and back end operations are all db related operations, manual test is more efficient for quality assurance.
With more functions added, we can consider use robot for automated UI test and add more unit tests.

Database for product use will be generated at :NovelImportTool\books
Database for unit test will be generated at :NovelImportTool\books_test

HOW TO USE 
---------------------------------------------------------------------------------------------       

1.Use Eclipse/File/Import navigate to NovelImprotTool folder to import exiting maven project into Eclipse.
2.In ui folder, right click on MainFrame.java, run as Java application
3.NOVEL IMPORT SYSTEM is displayed
4.Click ... to trigger Open dialog box, select the file :*\TAO Jiang\novels.xml or Input File Path *\TAO Jiang\novels.xml , or enter the right file path.
5.Click import ,all records in novels.xml will be imported.
6.User can create,modify and delete records.
Note:Only TITLE is a mandatory field.
  
DB DESIGN
----------------------------------------------------------------------------------
CREATE TABLE APP.AUTHOR (
	ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	NAME VARCHAR(100) NOT NULL,
	Gender INTEGER DEFAULT 0,
	BORN INTEGER,
	DEATH INTEGER,
	COUNTRY VARCHAR(100),
	INTRODUCTION VARCHAR(100),
	CONSTRAINT AUTHOR_PK PRIMARY KEY (ID)
);	
	
CREATE TABLE APP.NOVEL (
	ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	TITLE VARCHAR(100) NOT NULL,
	AUTHORID INTEGER,
	PUBLISHTIME DATE,
	FOREWORD VARCHAR(100),
	CONSTRAINT NOVEL_PK PRIMARY KEY (ID)
);

TEST CASE DESIGN 
JUnit Test
1 NovelTest.java: This unit test is used as a demo for doing unit test in a separate database(NovelImportTool\books_test).
-------------------------------------------------------------------------------
Below manual test cases are executed and passed.
===================================================================================
Test Case 1:Import a correct format xml file with 2 novel node inside(Positive case)  (e.g. C:\NovelImportTool\src\test\resource\1_normal_success.txt)
Test Steps:Import *\NovelImportTool\TestData\1_normal_success.txt
Expected result:
  1.2 new records are added into db.
  2.The 2 novel records should also appear on screen in the table component.
===================================================================================
Test Case 2:Create/update/delete the novel records(Positive case)
Test Steps:Create a new novel, update and delete the novel info
1.Clicking create button on mainframe
2.Novel info dialog show up correctly
Note :Only TITLE is a mandatory field.
      if the author name is ambiguous, then pop up a dialog, ask user to choose one.
	  if user didn't choose any one when dialog closed, then create a new record in author table.
	  if author name is not in author table yet, then create a new author record automatically.
3.When clicking modification on MainFrame.
  Note:if user didn't select any record, warning message appear:Please select at least one record,then try again.
	   if user selected more than 1 record,warning message appear:Please select at least one record,then try again.
4.When novel info dialog show up, the content of selected record should be displayed.
5.User can select one or more records to delete.
6.When click the close button on frame, user will exist the system.
			
Expected result:User can create,modify and delete the records
===================================================================================
Test Case 3:Records display(Positive case)
Test Steps:
	1.User can also adjust the offset with the page up and page down button.
	2.Select the value of order by, all records will be displayed by author, ID, etc.
	3.By default, it should display maximum 30 records ordered by id field desc(lasted added 30 novels).
Expected result:User can sort and switch between different pages.
===================================================================================
Test Case 4:Import a wrong format XML file(Negative case) (e.g. C:\NovelImportTool\src\test\resource\2_totally_wrong_format.txt)
Test Steps:Import *\NovelImportTool\TestData\2_totally_wrong_format.txt
Expected result:
   1.Error message will appear.
   2.should see the error dialog on screen.
   3.import progress is stopped.
===================================================================================
Test Case 5:Import a file path with wrong format date in the content (Negative case)(means in the author table, there are more than one author with same name)(Positive case)
Test Steps:Import *\NovelImportTool\TestData\3_wrong_format_date.txt
Expected result: Error message is appeared:Unexpected file format. Please specify a file with right format.
===================================================================================
Test Case 6:Input a file path with ambiguous author name(Positive case) (means in the author table, there are more than one author with same name)
Test Steps:Import *\NovelImportTool\TestData\4_ambiguous_author.txt
Expected result: Author list dialog is displayed to let user choose the right author to connect.
===================================================================================
Test Case 7:Import a file path with new author name(Positive case)
Test Steps:Import *\NovelImportTool\TestData\5_nonexist_author.txt
Expected result:New author will be added automatically.
===================================================================================



		
	  
