package com.donations.controllers;

import com.donations.model.FileUpload;
import com.donations.model.User;
import com.donations.services.LoginService;
import com.donations.services.SessionService;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author user
 */
public class FileUploadServlet extends HttpServlet {

    static FileItemStream item;
    static FileItemStream itemTemp;
    static SimpleDateFormat sdf=new SimpleDateFormat("yyyymmddhhmmss");
    SessionService sessionService;
    LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
        ServletContext context = getServletContext();
        String path = context.getInitParameter("file-upload");


        User loggedUser = null;
        sessionService = new SessionService();
        sessionService.init(request);
        loginService = new LoginService();
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");
            sessionService.setAttribute("user", loggedUser);
            // call a method to upload file.

            // FileUpload.processFile(path, item);
            // response.sendRedirect(request.getHeader("Referer"));
        }

        if (isMultiPart) {
            Date date=new Date();
            ServletFileUpload upload = new ServletFileUpload();
            try {
                FileItemIterator itr = upload.getItemIterator(request);
                while (itr.hasNext()) {

                    item = itr.next();
                    if (item.isFormField()) {
                        //do field specific process
                        String fieldName = item.getFieldName();

                        InputStream is = item.openStream();
                        byte[] b = new byte[is.available()];
                        is.read(b);
                        String value = new String(b);
                        sessionService.setAttribute(item.getFieldName(), value);
                        // response.sendRedirect(request.getHeader("Referer"));
                    } else {
                        // do file upload specific process
                        FileUpload.processFile(path, item,sdf.format(date));
                        sessionService.setAttribute("imageLocation", "/data/images/"+sdf.format(date) + item.getName());
                    }
                }

                response.sendRedirect(request.getHeader("Referer"));
            } catch (FileUploadException fule) {
                fule.printStackTrace();
            }
        }
        // else do nothing

    }

    public static String getItem() {
        return item.getName();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
