package com.diy.xMyBank;

import java.util.ResourceBundle;

public class AuthenticationController
{
  public static void main(String[] args)
  {
    try
    {
      AuthenticationController ac = new AuthenticationController();
      ac.authenticate("svinchon", "141271");
      ac.authenticate("setberg", "007007");
      ac.authenticate("setberg", "00707");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public String authenticate(String user, String password)
    throws Exception
  {
    String ret = null;
    
    ResourceBundle rb = ResourceBundle.getBundle("xBank");
    String users = rb.getString("users");
    int userStartIndex = users.indexOf(user + ":" + password);
    Log("user: '" + user + "'");
    Log("password:'" + password + "'");
    if ((user != "") && (userStartIndex >= 0))
    {
      String accountNumber = users.substring(userStartIndex + (user + ":" + password + ":").length());
      int userEndIndex = accountNumber.indexOf(";");
      if (userEndIndex < 0) {
        userEndIndex = accountNumber.length();
      }
      accountNumber = accountNumber.substring(0, userEndIndex);
      Log("User OK and Account Found:'" + accountNumber + "'");
      ret = accountNumber;
    }
    else
    {
      Log("Account Not Found");
      Exception e = new Exception("Account Not Found Exception");
      throw e;
    }
    return ret;
  }
  
  private void Log(String string)
  {
    System.out.println(getClass().getCanonicalName() + " => " + string);
  }
}
