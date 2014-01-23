package com.donations.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.http.fileupload.FileItemStream;

/**
 *
 * @author user
 */
public class FileUpload {

    public static boolean processFile(String path, FileItemStream item,String date) throws FileNotFoundException, IOException {
        try {
            File f = new File(path + File.separator);//+"images"+File.separator
            if (!f.exists()) {
                f.mkdir();
            }
            File savedFile = new File(f.getAbsolutePath() + File.separator+date+item.getName());
            FileOutputStream fos = new FileOutputStream(savedFile);

            InputStream is = item.openStream();
            int x = 0;
            byte[] b = new byte[1024];
            while ((x = is.read(b)) != -1) {
                fos.write(b, 0, x);
            }
            fos.flush();
            fos.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
