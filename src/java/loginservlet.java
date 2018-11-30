import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

 
public class loginservlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
    request.getRequestDispatcher("index.html").include(request, response); 
    String user_name=request.getParameter("username");  
    String user_pass=request.getParameter("pass");  
    String s1="";
    String s2="";   
   
            Class.forName("com.mysql.jdbc.Driver");
            Connection  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root" ,"");
            Statement  st =   con.createStatement();
              ResultSet rs =  st.executeQuery("Select * from login");
              
              int flag=0;
              while(rs.next())
              {          
                  s1=rs.getString("username");               
                  s2=rs.getString("pass");
               

      if(user_name.equals(s1)&&user_pass.equals(s2))
                  {
                    out.print("Welcome "+ s1+"\n"); flag=1; 
                    HttpSession session=request.getSession();  
                    session.setAttribute("s_id",user_pass);
                    response.sendRedirect("apartment1.html");
                     
                     break;
                  }
              }
              
//              ResultSet rs2 =  st.executeQuery("Select * from details");
//              while(rs2.next())
//              {          
//                  s1=rs2.getString("name");               
//                  s2=rs2.getString("pass");
//
//      if(user_name.equals(s1)&&user_pass.equals(s2))
//                  {
//                     out.print("Welcome "+ s1+"\n");
//                     HttpSession session=request.getSession();  
//                     session.setAttribute("s_id",user_pass);
//                     response.sendRedirect("MAIN.html");
//                     
//             flag=1;
//                     break;
//                  }
//              }
               if (flag==0)
              { 
                  //response.sendRedirect("error.html");
                //  request.getRequestDispatcher("indexerror.html").include(request, response);  
              out.println("Incorrect Details");
              }
        } catch (Exception e) {
            out.println("Not found" +e);
        }
        finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}