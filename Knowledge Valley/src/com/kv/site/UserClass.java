package com.kv.site;

public class UserClass
{
	public String emailId;
	public String passwd;
	public String action;
	
	
	
	public UserClass(String email, String pass, String action) 
	{
		this.emailId = email;
		this.passwd = pass;
		this.action = action;
	}
	
	public void printUser()
	{
		System.out.println("User email: "+emailId);
		System.out.println("User pass: "+passwd);
		System.out.println("Action: "+action);
	}
}
