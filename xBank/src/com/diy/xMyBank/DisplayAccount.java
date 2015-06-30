package com.diy.xMyBank;

import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({"/DisplayAccount"})
public class DisplayAccount
  extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    doPost(request, response);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    try
    {
      HttpSession session = request.getSession();
      session.invalidate();
      String login = request.getParameter("login");
      String password = request.getParameter("password");
      Log("login: '" + login + "'");
      Log("password: '" + password + "'");
      AuthenticationController ac = new AuthenticationController();
      String accountNumber = ac.authenticate(login, password);
      Log("accountNumber: '" + accountNumber + "'");
      session = request.getSession(true);
      session.setAttribute("accountNumber", accountNumber);
      response.sendRedirect("DisplayAccount.jsp");
    }
    catch (Exception e)
    {
      response.sendRedirect("Error.html");
    }
  }
  
  private void Log(String string)
  {
    System.out.println(getClass().getCanonicalName() + " => " + string);
  }
}
