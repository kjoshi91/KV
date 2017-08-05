package com.kv.site;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/ContactsServlet")
public class ContactsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Contacts cont;
       
    public ContactsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
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
			cont=gson.fromJson(objParam, Contacts.class);
			if(cont.action.equals("fetch"))
			{
				cont=fetchContacts();
				PrintWriter out=response.getWriter();
				out.write("{\"con1\":"+cont.cont1+",\"con2\":"+cont.cont2+",\"action\":"+cont.action+"}");
			}
			else if(cont.action.equals("insert"))
			{
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Contacts fetchContacts() 
	{
		DbHelper dh=new DbHelper();
		Contacts c=dh.fetchContacts();
		return c;
	}

}
