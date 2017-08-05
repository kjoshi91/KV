package com.kv.site;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kv.site.DbHelper;
import com.google.gson.Gson;
import com.kv.site.UserClass;

@WebServlet("/LoginRequest")
public class LoginRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserClass uc;
	Properties prop;
	InputStream input=null;
	String logLocation;
	
    public LoginRequest() {
        super();
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		File propertiesFile=new File(System.getenv("TOMCAT_PATH")+File.separator+"lib"+File.separator+"site.properties");
		System.out.println("Properties file: "+propertiesFile.toString());
		prop=new Properties();
		input=new FileInputStream(propertiesFile);
		prop.load(input);
		logLocation=prop.getProperty("LOGS");
		System.out.println("Servlet called");
		BufferedReader br=new BufferedReader(new InputStreamReader(request.getInputStream()));
		String objParam="";
		if(br!=null)
		{
			objParam=br.readLine();
		}
		System.out.println("Json Received: "+objParam);
		try
		{
			Gson gson=new Gson();
			uc=gson.fromJson(objParam, UserClass.class);
			System.out.println("Printing parsed Json below");
			uc.printUser();
			if(uc.action.equals("login")) 
			{
				response.setContentType("application/json");
				PrintWriter out=response.getWriter();
				boolean loginResult=loginUser(uc);
				if(loginResult)
				{
					System.out.println("User logged in.");
					out.write("{\"result\":\"success\"}");
					out.flush();
				}
				else
				{
					System.out.println("User login failed.");
					out.write("{\"result\":\"failure\"}");
					out.flush();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private boolean loginUser(UserClass uc2)
	{
		DbHelper dh=new DbHelper();
		boolean res=dh.fetchUser(uc2);
		return res;
	}
}
