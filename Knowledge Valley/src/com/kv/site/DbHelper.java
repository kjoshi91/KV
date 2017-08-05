package com.kv.site;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;



public class DbHelper
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/kvproject";
	Connection con=null;
	PreparedStatement pstmt=null;
	String username,password;
	Properties prop;
	public DbHelper()
	{
		fetchProperties();		
	}
	
	private void fetchProperties()
	{
		try
		{
			File propertiesFile=new File(System.getenv("TOMCAT_PATH")+File.separator+"lib"+File.separator+"site.properties");
			prop=new Properties();
			prop.load(new FileInputStream(propertiesFile));
			username=prop.getProperty("DBUSER");
			password=prop.getProperty("DBPASS");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean fetchUser(UserClass uc)
	{
		String query="select * from user_table where user_email='"+uc.emailId+"'";
		String email="",pass="";
		try
		{
			Class.forName(JDBC_DRIVER);
			con=DriverManager.getConnection(DB_URL, username, password);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			if(rs!=null)
			while(rs.next())
			{
				email=rs.getString("user_email");
				pass=rs.getString("user_pass");
			}
			if(uc.emailId.equals(email) && uc.passwd.equals(pass))
			{
				System.out.println("Login success!");
				return true;
			}
			else
				return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
/*
	public boolean insertText(TextWrapper tw)
	{
		String query="insert into scroll_details (scroll_text,text_visible) values (?,?)";
		String deleQuery="delete from scroll_details";
		try
		{
			Class.forName(JDBC_DRIVER);
			con=DriverManager.getConnection(DB_URL, username, password);
			pstmt=con.prepareStatement(deleQuery);
			pstmt.executeUpdate();
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, tw.text);
			if(tw.isVisible)
				pstmt.setInt(2,1);
			else
				pstmt.setInt(2,0);
			pstmt.execute();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean fetchTextVisible()
	{
		String query="select text_visible from scroll_details";
		boolean isVisible=false;
		try
		{
			Class.forName(JDBC_DRIVER);
			con=DriverManager.getConnection(DB_URL, username, password);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			if(rs!=null)
			{
				while(rs.next())
				{
					if(rs.getInt("text_visible")==1)
						isVisible=true;
					else
						isVisible=false;
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return isVisible;
	}

	public String fetchText() 
	{
		String query="select scroll_text from scroll_details";
		String text="";
		try
		{
			Class.forName(JDBC_DRIVER);
			con=DriverManager.getConnection(DB_URL, username, password);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			if(rs!=null)
			{
				while(rs.next())
				{
					text=rs.getString("scroll_text");	
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return text;
	}*/

	public Contacts fetchContacts() 
	{
		String query="select * from contact_table";
		try
		{
			Class.forName(JDBC_DRIVER);
			con=DriverManager.getConnection(DB_URL, username, password);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			Contacts con;
			String[] cons= new String[2];
			int i=0;
			while(rs.next())
			{
				cons[i]=rs.getString("contact_text");
				i++;
			}
			con=new Contacts(cons[0], cons[1], "fetched");
			return con;
		}
		catch (Exception e) {
			e.printStackTrace();
			return new Contacts("", "", "error");
		}
	}
}
