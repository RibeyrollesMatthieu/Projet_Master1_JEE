package server.servlets;

import server.SQLConnector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Ribeyrolles Matthieu
 * 29/12/2020, 23:16
 */
public class LoginServlet extends HttpServlet {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    new SQLConnector().connect("projet_master1_jee", "root", "");

    resp.setContentType("text/html");
    resp.setCharacterEncoding("UTF-8");

    PrintWriter pw = resp.getWriter();
    pw.println("coucuo");
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
