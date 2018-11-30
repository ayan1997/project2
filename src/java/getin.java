import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

 
public class getin extends HttpServlet {
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
    String s1=request.getParameter("name");  
    String s2=request.getParameter("email");
    String s3=request.getParameter("message");

    
    Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root" ,"");
            Statement  st =  con.createStatement();
String query = " insert into getin (name,email,message)"
        + " values (?, ?, ?)";

      // create the mysql insert preparedstatement
      PreparedStatement ps = con.prepareStatement(query);
      ps.setString (1, s1);
      ps.setString (2, s2);
      ps.setString (3, s3);
    
     out.print(s1+" "+s2+ " "+s3);
      ps.execute();
      
      response.sendRedirect("index.html");  
      con.close();       
          
        } catch (Exception e) {
            e.printStackTrace();
            out.println("\nUsername already exists, choose a unqiue one!\n");
        }
        finally {
            out.close();
        }
    }
}