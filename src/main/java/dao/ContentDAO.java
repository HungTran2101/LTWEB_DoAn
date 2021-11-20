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

	
	public static ContentModel getContentById(int id)
	{
		ContentModel content = new ContentModel();
		
		String query= "SELECT * FROM CONTENT WHERE ID = ? AND AUTHORID = ?";
		
		try 
		{
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			ps.setInt(1, id);
			ps.setInt(2, Constants.idMember);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				content.setId(rs.getInt(1));
				content.setTitle(rs.getString(2));
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
	
	
	
	//get all content to make number of pages
	public static int getAllContentsById()
	{
		
		String query = "SELECT ID, TITLE, BRIEF, CREATEDATE FROM CONTENT WHERE AUTHORID = ?";
		int count = 0;
		try {
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			ps.setInt(1, Constants.idMember);		
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next())
			{
				count++;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return count;
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



	public static List<ContentModel> searchContents (String search, int page)
	{
		//Constants.idMember = 1;
		List<ContentModel> lst = new ArrayList<ContentModel>();
		String query = "SELECT ID, TITLE, BRIEF, CREATEDATE FROM CONTENT WHERE (TITLE LIKE '%" + search + "%' OR BRIEF LIKE '%" + search + "%') AND AUTHORID =" + Constants.idMember +" ORDER BY ID DESC LIMIT 10 OFFSET " + page;
		
		try 
		{
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			
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
	
	public static List<ContentModel> getAllContentsBySearch(String search)
	{
		List<ContentModel> listContents = new ArrayList<ContentModel>();
		
		String query = "SELECT ID, TITLE, BRIEF, CREATEDATE FROM CONTENT WHERE (TITLE LIKE '%" + search + "%' OR BRIEF LIKE '%" + search + "%') AND AUTHORID =" + Constants.idMember;
		
		
		
		try 
		{
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				ContentModel content = new ContentModel();
				content.setId(rs.getInt(1));
				content.setTitle(rs.getString(2));
				content.setBrief(rs.getString(3));
				
				listContents.add(content);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return listContents;
	}

}
