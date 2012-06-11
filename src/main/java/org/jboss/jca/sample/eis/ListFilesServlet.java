package org.jboss.jca.sample.eis;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/listfiles"})
public class ListFilesServlet extends HttpServlet
{
   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
      String dir = request.getParameter("dir");
      StringBuilder sb = new StringBuilder();
      if(dir != null && !dir.isEmpty()){
         File file = new File(dir);
         if(file.exists() && file.canRead() && file.isDirectory()){
            for(File f: file.listFiles()){
               if(f.isDirectory()){
                  sb.append("drwx------\t" + f.getName() +"<br />\n");
               }else if(f.isFile()){
                  sb.append("-rwx------\t" + f.getName() +"<br />\n");
               }
            }
         }
      }else{
         sb.append("Unkown Directory: " + dir);
      }
      request.setCharacterEncoding("UTF-8");
//      response.setContentType("text/plain");
      PrintWriter out = response.getWriter();
      out.println(sb.toString());
      out.flush();
   }

}
