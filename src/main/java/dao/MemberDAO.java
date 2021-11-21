package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import common.Constants;
import model.MemberModel;
import mydb.DB;

public class MemberDAO {
	//login account
	public static int loginMember(String email, String password)
	{	
		String query = "SELECT * FROM MEMBER WHERE EMAIL = ? AND PASSWORD = ?";
		
		int id = 0;
		int count = 0;
		
		try
		{
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				count++;
				id = rs.getInt(1);
			}
			
			ps.close();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		if (count == 1)
			return id;
		
		return -1;
	}
	
	
	//register member
	public static boolean registerMember(String username, String email, String password)
	{
		boolean tableUpdate = false;
		
		String query = "INSERT INTO MEMBER (USERNAME, EMAIL, PASSWORD, CREATEDATE, UPDATETIME) VALUES (?,?,?, NOW(), NOW())";
		
		try 
		{
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, password);
			
			
			tableUpdate = ps.executeUpdate() > 0;
			
			ps.close();
			
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return tableUpdate;
	}
	
	
	public static boolean findExistedEmail(String email)
	{
		String query = "SELECT email FROM MEMBER WHERE EMAIL = ?";
		int count = 0;
		
		try 
		{
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			ps.setString(1,email);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				count++;
			}
			
			ps.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		if(count == 0)
			return true;
		return false;
	}
	
	public static MemberModel getMemberById(int id)
	{
		String query = "SELECT FIRSTNAME, LASTNAME, EMAIL, PHONE, DESCRIPTION FROM MEMBER WHERE ID = ?";
		
		MemberModel member = new MemberModel();
		
		try 
		{
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				if (rs.getString(1) == null)
					member.setFirstName("");
				else 
					member.setFirstName(rs.getString(1));
				
				if (rs.getString(2) == null)
					member.setLastName("");
				else 
					member.setLastName(rs.getString(2));
				
				
				member.setEmail(rs.getString(3));
				
				
				if (rs.getString(4) == null)
					member.setPhone("");
				else 
					member.setPhone(rs.getString(4));
				
				if (rs.getString(5) == null)
					member.setDescription("");
				else 
					member.setDescription(rs.getString(5));
				//count++;
			}
			
			ps.close();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return member;
	}

	public static boolean editProfile (String firstName, String lastName, String phone, String description)
	{
		boolean tableUpdate = false;
		
		String query = "UPDATE MEMBER SET FIRSTNAME=?, LASTNAME=?,PHONE=?,DESCRIPTION=?, UPDATETIME = NOW() WHERE ID = ?";
		
		try
		{
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, phone);
			ps.setString(4, description);
			ps.setInt(5, Constants.idMember);
			
			tableUpdate = ps.executeUpdate()>0;
			
			ps.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return tableUpdate;
	}

	public static String getPasswordByEmail(String email)
	{
		String password = "";
		
		String query = "SELECT PASSWORD FROM MEMBER WHERE EMAIL = ?";
		
		try {
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				password = rs.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return password;
	}

	
	public static void forgotPasswordHandler(String email)
	{
		String getPassword = getPasswordByEmail(email);
		System.out.print(getPassword);
		
		Properties properties = new Properties();

	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.port", "587");
	    Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Constants.myEmail, Constants.myPassword);
            }
        };
        
        Session session = Session.getInstance(properties, auth);
		//session.setDebug(true);
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Constants.myEmail));
			message.addRecipient(
				Message.RecipientType.TO,
				new InternetAddress(email)
			);
			message.setSubject("Get back your password");
			message.setText(getPassword);
			Transport.send(message);
		}
		catch(MessagingException e)
		{
			e.printStackTrace();
		}
	}
	
	public static boolean getRoleById() {
		boolean role = false;
		String query = "SELECT ROLE FROM MEMBER WHERE ID = ?";
		
		try {
			PreparedStatement ps = DB.getJDBCConnection().prepareStatement(query);
			ps.setInt(1, Constants.idMember);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				role = rs.getBoolean(1);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return role;
	}
	
}
