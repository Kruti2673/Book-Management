/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kruti Vadaliya
 */
public class BooklistServlet extends HttpServlet {

   private static final String query="SELECT BOOKID,BOOKNAME,BOOKEDITION,BOOKPRICE FROM bookdata";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw=resp.getWriter();
        resp.setContentType("text/html");
      
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","");
            PreparedStatement ps=conn.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            pw.println("<table border='1' align='center'>");
            pw.println("<tr>");
            pw.println("<th>Book Id</th>");
            pw.println("<th>Book Name</th>");
            pw.println("<th>Book Edition</th>");
            pw.println("<th>Book Price</th>");
             pw.println("<th>Edit</th>");
              pw.println("<th>Delete</th>");
            pw.println("</tr>");
            while(rs.next())
            {
            pw.println("<tr>");
            pw.println("<td>"+rs.getInt(1)+"</td>");
            pw.println("<td>"+rs.getString(2)+"</td>");
            pw.println("<td>"+rs.getString(3)+"</td>");
            pw.println("<td>"+rs.getString(4)+"</td>");
            pw.println("<td><a href='EditScreen?id="+rs.getInt(1)+"'>Edit</a></td>");
            pw.println("<td><a href='DeleteServlet?id="+rs.getInt(1)+"'>Delete</a></td>");
            pw.println("</tr>");
            }
            pw.println("</table>");
        }
        catch (SQLException se) {
            se.printStackTrace();
          pw.println("<h1>"+se.getMessage()+"</h2>");
       }
        catch(Exception e){
            e.printStackTrace();
            pw.println("<h1>"+e.getMessage()+"</h2>");
        }
      pw.println("<a href=\"index.html\">Home</a>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
}
