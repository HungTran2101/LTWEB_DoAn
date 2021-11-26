package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.Constants;
import model.ContentModel;
import mydb.DB;


public class ContentDAO {
	
	//add content
	public static boolean addContent(String title, String brief, String content)
	{
		boolean tableUpdate = false;
		
		String query = "INSERT INTO CONTENT (TITLE, BRIEF, CONTENT,CREATEDATE, UPDATETIME, AUTHORID) VALUES (?,?,?,NOW(), NOW(),?)";
		
		try
		{
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			ps.setString(1, title);
			ps.setString(2, brief);
			ps.setString(3, content);
			ps.setInt(4,Constants.idMember);
			
			//return 0: not update table
			tableUpdate = ps.executeUpdate()>0;
			
			ps.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return tableUpdate;
	}
	
	//delete content
	public static boolean deleteContent (int id)
	{
		boolean tableUpdate = false;
		
		String query = "DELETE FROM CONTENT WHERE ID = ?";
		
		try
		{
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			ps.setInt(1, id);
			
			tableUpdate = ps.executeUpdate() > 0;
			
			ps.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return tableUpdate;
	}
	
	
	//modify content
	public static boolean modifyContent(int id, String title, String brief, String content)
	{
		boolean tableUpdate = false;
		
		String query = "UPDATE CONTENT SET TITLE = ?, BRIEF = ?, CONTENT = ?, UPDATETIME = NOW() WHERE ID = ?";
		
		try {
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			ps.setString(1, title);
			ps.setString(2, brief);
			ps.setString(3, content);
			ps.setInt(4, id);
			
			tableUpdate = ps.executeUpdate()>0;
			
			ps.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return tableUpdate;
	}

	
	//for editContent.jsp
	public static ContentModel getContentById(int id)
	{
		ContentModel content = new ContentModel();
		
		String query= "SELECT * FROM CONTENT WHERE ID = ?";
		
		try 
		{
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				content.setId(rs.getInt(1));
				content.setTitle(rs.getString(2));
				System.out.println(content.getTitle());
				content.setBrief(rs.getString(3));
				content.setContent(rs.getString(4));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return content;
	}
	
	
	
	//get content to  page
	public static List<ContentModel> getContentsByPage(int pagination)
	{
		List<ContentModel> lstContents = new ArrayList<ContentModel>();
		
		String query = "SELECT ID, TITLE, BRIEF, CREATEDATE FROM CONTENT WHERE AUTHORID = ? ORDER BY ID DESC LIMIT 10 OFFSET ?";
		
		try {
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			ps.setInt(1, Constants.idMember);
			ps.setInt(2, pagination);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				ContentModel newContent = new ContentModel();
				newContent.setId(rs.getInt(1));
				newContent.setTitle(rs.getString(2));
				newContent.setBrief(rs.getString(3));
				newContent.setCreateDate(rs.getTimestamp(4));
				lstContents.add(newContent);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return lstContents;
	}



	
	

	
	//return number of contents to calculate pages
	public static int numberOfContents(String search) {
		
		int n=0;
		
		String query = "SELECT COUNT(*) FROM MEMBER, CONTENT WHERE MEMBER.ID = CONTENT.AUTHORID AND (TITLE LIKE ? OR BRIEF LIKE ?)";
		
		try {
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			ps.setString(1, "%"+search+"%");
			ps.setString(2, "%"+search+"%");
			
			ResultSet rs = ps.executeQuery();
			
		
			
			while(rs.next())
			{
				n = rs.getInt(1);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	
	public static int numberOfContentsMember(String search) {
		
		int n=0;
		
		String query = "SELECT COUNT(*) FROM CONTENT WHERE AUTHORID = ? AND (TITLE LIKE ? OR BRIEF LIKE ?)";
		
		try {
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			ps.setInt(1, Constants.idMember);
			ps.setString(2, "%"+search+"%");
			ps.setString(3, "%"+search+"%");
			
			ResultSet rs = ps.executeQuery();
			
		
			
			while(rs.next())
			{
				n = rs.getInt(1);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	
	}

	
	
	//show contents for member's view
	public static List<ContentModel> getSearchContentsByMember (String search, int page)
	{
		List<ContentModel> lst = new ArrayList<ContentModel>();
		String query = "SELECT ID, TITLE, BRIEF, CREATEDATE FROM CONTENT WHERE (ID LIKE ? OR TITLE LIKE ? OR BRIEF LIKE ?) AND AUTHORID = ? ORDER BY ID DESC LIMIT 10 OFFSET ?";
			
		try 
		{
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			ps.setString(1, "%"+search+"%");
			ps.setString(2, "%"+search+"%");
			ps.setString(3, "%"+search+"%");
			ps.setInt(4, Constants.idMember);
			ps.setInt(5, page);
			ResultSet rs = ps.executeQuery();
				
			while(rs.next())
			{
				ContentModel content = new ContentModel();
				content.setId(rs.getInt(1));
				content.setTitle(rs.getString(2));
				content.setBrief(rs.getString(3));
				content.setCreateDate(rs.getTimestamp(4));
					
					
				lst.add(content);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
			
		return lst;
	}
		
	
	
	
	//show contents for admin's view
	public static List<ContentModel> getSearchContentsByAdmin (String search, int page){
		List<ContentModel> listContents = new ArrayList<ContentModel>();
		String query = "SELECT CONTENT.ID, CONTENT.TITLE, CONTENT.BRIEF, CONTENT.CREATEDATE, USERNAME FROM CONTENT, MEMBER WHERE CONTENT.AUTHORID = MEMBER.ID AND (CONTENT.ID LIKE ? OR TITLE LIKE ? OR BRIEF LIKE ? OR USERNAME LIKE ?) ORDER BY ID DESC LIMIT 10 OFFSET ?" ;
		
		try {
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			ps.setString(1, "%"+search+"%");
			ps.setString(2, "%"+search+"%");
			ps.setString(3, "%"+search+"%");
			ps.setString(4, "%"+search+"%");
			ps.setInt(5, page);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ContentModel content = new ContentModel();
				content.setId(rs.getInt(1));
				content.setTitle(rs.getString(2));
				content.setBrief(rs.getString(3));
				content.setCreateDate(rs.getTimestamp(4));
				content.setUsername(rs.getString(5));
				listContents.add(content);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return listContents;
	}
	
	


}
