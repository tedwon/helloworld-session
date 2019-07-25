package io.undertow.clusteringdemo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Stuart Douglas
 */
@WebServlet(urlPatterns = "/simple/*")
public class SimpleServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("UTF-8");
      String response = "SimpleServlet Server " + System.getProperty("server.name") + " ";
      resp.getWriter().write(response);
      System.out.println(response);
      System.out.println("aaa=" + req.getParameter("aaa"));
      
      if (req.getParameter("stateful") != null) {
         HttpSession session = req.getSession(true);
         long start = System.currentTimeMillis();
         Integer count = (Integer) session.getAttribute("count");
         System.out.println("SimpleServlet session.getAttribute: " + (System.currentTimeMillis() - start) + "ms");
         if (count == null) {
            count = 1;
         }
         String statefulResponse = "Request Count " + count;
         resp.getWriter().write(statefulResponse);
         resp.getWriter().write("\n");
         System.out.println(statefulResponse);
         start = System.currentTimeMillis();
         session.setAttribute("count", ++count);
         System.out.println("SimpleServlet session.setAttribute: " + (System.currentTimeMillis() - start) + "ms");

         final String sleep = req.getParameter("sleep");
         if (sleep != null) {
            try {
               System.out.println("start sleep during " + sleep + "ms");
               Thread.sleep(Integer.parseInt(sleep));
               System.out.println("finish sleep after " + sleep + "ms");
            } catch (InterruptedException e) {
            }
         }

         resp.getWriter().write("Session ID: " + session.getId() + " ");
      }
   }
}
