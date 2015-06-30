package com.diy.xMyBank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class XML2JSONTest
{
  public static int PRETTY_PRINT_INDENT_FACTOR = 4;
  public static String TEST_XML_STRING = "<?xml version=\"1.0\" ?><list name=\"list1\"><item><name>name</name><value>value</value></item><item><name>name2</name><value>value2</value></item><item>Turn this to JSON</item></list>";
  
  public static void main(String[] args)
  {
    try
    {
      FileInputStream fis = new FileInputStream("/home/dmadmin/Desktop/test.xml");
      byte[] b = new byte[fis.available()];
      fis.read(b);
      String myXML = new String(b);
      fis.close();
      
      JSONObject xmlJSONObj = XML.toJSONObject(myXML);
      String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
      FileOutputStream fos = new FileOutputStream("/home/dmadmin/Desktop/test.xml.json");
      fos.write(jsonPrettyPrintString.getBytes());
      
      fos.close();
    }
    catch (FileNotFoundException e1)
    {
      e1.printStackTrace();
    }
    catch (IOException e1)
    {
      e1.printStackTrace();
    }
    catch (JSONException je)
    {
      System.out.println(je.toString());
    }
  }
  
  private void Log(String string)
  {
    System.out.println(getClass().getCanonicalName() + " => " + string);
  }
}
