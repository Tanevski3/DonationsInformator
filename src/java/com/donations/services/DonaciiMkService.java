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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author user
 */
public class DonaciiMkService {

    public static StringBuffer sendGetRequest(String targetUrl) {

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

    public static String sendPostRequest(String targetURL, String urlParameters) {
        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            //connection.setRequestProperty("Content-Language", "mk-MK");  

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            //Get Response	
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    //returns links to detail page
    private static String[] getPageHrefs(String content) {
        return StringUtils.substringsBetween(content, "<div class=\"entry-content\">		" + "<a href=\"", "\" class=\"thumb\" title=\"\">");
    }

    //returns all the links for the required date
    public static List<String> getPageHrefsForDate(Integer year, Integer month) {

        List<String> hrefs = new ArrayList<String>();
        Integer pageNumber = getPageNumber(sendGetRequest("http://www.donacii.mk/").toString(), year, month);
         String[] pages = new String[pageNumber];
         int d=0;
        for (int i = 1; i <= pageNumber; i++) {
            for(String href : getPageHrefs(sendGetRequest("http://www.donacii.mk/" + year.toString() + "/" + month.toString() + "/page/" + i + "/").toString()))
            {
                hrefs.add(href);
            }
           // hrefs.add(getPageHrefs(sendGetRequest("http://www.donacii.mk/" + year.toString() + "/" + month.toString() + "/page/" + i + "/").toString()).toString());
            d++;
        }

        return hrefs;
    }
    // returns the content beetwen entry-intro aka title and startDate
    public static String getEntryIntro(String href)
    {
        return StringUtils.substringBetween(href, "<div class=\"entry-intro\">", "</div>");
    }
    public static String getTitleForEntryIntro(String entryIntro)
    {
        return StringUtils.substringBetween(entryIntro, "<h1>", "</h1>");
    }
     public static String getStartDateForEntryIntro(String entryIntro)
    {
        return StringUtils.substringBetween(StringUtils.substringBetween(entryIntro, "<span class=\"entry-meta\">", "</span>"),"<time datetime=\"","\">");
    }
     
      public static String getEntryImage(String href)
    {
        return StringUtils.substringBetween(href, "<figure class=\"entry-image\">", "</figure>");
    }
       public static String getEntryImageRefForImage(String entryImage)
    {
        return StringUtils.substringBetween(entryImage, "<a href=\"", "\" class=\"thumb\"");
    }
     public static String getEntryContent(String href)
     {
         return StringUtils.substringBetween(href, "<div class=\"entry-content group\">", "</div>");
     }
    public static Integer getPageNumber(String content, Integer year, Integer month) {
        if (month < 1 || month > 12) {
            return 0;
        }
        if (year < 1 || year == null) {
            return 0;
        }
        String s = StringUtils.substringBetween(content, "<option value=\'http://www.donacii.mk/" + year + "/" + String.format("%02d", month) + "/\'>", "</option>");

        String numberOfPages = StringUtils.substringBetween(s, "(", ")");
        try {
            Integer nop = Integer.parseInt(numberOfPages);
            if (nop % 3 == 0) {
                return nop / 3;
            } else {
                return (nop / 3) + 1;
            }
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public static String getPageDonationImageUrl(String content) {
        Date dt = new Date();
        dt.getYear();
        dt.getMonth();
        return "";
    }

    public static String getPageDonationDescription(String content) {
        return "";
    }

    public static String getPageDonationTitle(String content) {
        return "";
    }
}
