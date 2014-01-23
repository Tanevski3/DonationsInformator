package com.donations.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author user
 */
public class ScraperService {
    
     public static  StringBuffer sendGetRequest(String targetUrl) {
        
        //Build parameter string
        String query = "width=50&height=100";
        StringBuffer answer = new StringBuffer();
        String body;
        try {
            
            // Send the request
            URL url = new URL(targetUrl);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8"); 
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            //write parameters
            writer.write(query);
            writer.flush();
            
            // Get the response
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                answer.append(line);
            }
            writer.close();
            reader.close();
            
            //Output the response
            System.out.println(answer.toString());
            
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
         return answer;
    }
    
public static String sendPostRequest(String targetURL, String urlParameters)
  {
    URL url;
    HttpURLConnection connection = null;  
    try {
      //Create connection
      url = new URL(targetURL);
      connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
      //connection.setRequestProperty("Content-Language", "mk-MK");  
			
      connection.setUseCaches (false);
      connection.setDoInput(true);
      connection.setDoOutput(true);

      //Send request
      DataOutputStream wr = new DataOutputStream (
                  connection.getOutputStream ());
      wr.writeBytes (urlParameters);
      wr.flush ();
      wr.close ();

      //Get Response	
      InputStream is = connection.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      String line;
      StringBuffer response = new StringBuffer(); 
      while((line = rd.readLine()) != null) {
        response.append(line);
        response.append('\r');
      }
      rd.close();
      return response.toString();

    } catch (Exception e) {

      e.printStackTrace();
      return null;

    } finally {

      if(connection != null) {
        connection.disconnect(); 
      }
    }
  }
     
public static String getInsideHtmlForGoogleFirstResult(String content)
{
         String li=StringUtils.substringBetween(content, "<div class=\"entry-content\">", "</div>");
            String pto=StringUtils.substringBetween(li.toString(), "<p>", "</p>");
                //String[] a=StringUtils.substringsBetween(h3.toString(), "<a href=\"", "/a>");
                //String em=StringUtils.substringBetween(a.toString(), "<em>", "</em>");
                //String[] finalen=StringUtils.substringsBetween(a.toString(),"</em>","<");
                return pto;
}
     
}
