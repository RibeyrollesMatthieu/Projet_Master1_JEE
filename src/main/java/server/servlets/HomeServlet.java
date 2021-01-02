package server.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ribeyrolles Matthieu
 * 02/01/2021, 23:38
 */
public class HomeServlet extends HttpServlet {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (! Boolean.parseBoolean(req.getSession().getAttribute("logged").toString())) resp.sendRedirect(req.getRequestURI().replace("register", ""));
    else req.getRequestDispatcher("resources/views/pages/home.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPost(req, resp);
  }

   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
