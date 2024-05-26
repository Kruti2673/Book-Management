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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kruti Vadaliya
 */

public class EditScreenServlet extends HttpServlet {
    private static final String query="SELECT BOOKNAME,BOOKEDITION,BOOKPRICE FROM bookdata where id=?";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw=resp.getWriter();
        resp.setContentType("text/html");
        int id=Integer.parseInt(req.getParameter("id"));
      
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","");
            PreparedStatement ps=conn.prepareStatement(query);
            ps.setInt(4,id);
            ResultSet rs=ps.executeQuery();
            rs.next();
            pw.println("<form action='EditScreen?id="+id+"' method='post'>");
            pw.println("<table align='center'>");
            pw.println("<tr>");
            pw.println("<td>Book Name</td>");
            pw.println("<td><input type='text' name='bookName' value='"+rs.getString(1)+"'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>Book Edition</td>");
            pw.println("<td><input type='text' name='bookEdition' value='"+rs.getString(2)+"'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>Book Price</td>");
            pw.println("<td><input type='text' name='bookPrice' value='"+rs.getString(3)+"'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td><input type='submit' value='Edit'></td>");
            pw.println("<td><input type='reset' value='cancel'></td>");
            pw.println("</tr>");
            pw.println("</table>");
            pw.println("</form>");
        }
        catch (SQLException | ClassNotFoundException se) {
          pw.println("<h1>"+se.getMessage()+"</h2>");
       }
      pw.println("<a href=\"index.html\">Home</a>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
