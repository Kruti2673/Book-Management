/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class RegisterServlet extends HttpServlet {

   private static final String query="INSERT INTO bookdata(BOOKNAME,BOOKEDITION,BOOKPRICE)VALUES(?,?,?)";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw=resp.getWriter();
        resp.setContentType("text/html");
        
        //String bookId=req.getParameter("bookId");
        String bookName=req.getParameter("bookName");
        String bookEdition=req.getParameter("bookEdition");
        String  bookPrice= (String) req.getParameter("bookPrice");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","");
            PreparedStatement ps=conn.prepareStatement(query);
            //ps.setString(1,bookId);
            ps.setString(1,bookName);
            ps.setString(2,bookEdition);
            ps.setString(3,bookPrice);
            
            int count=ps.executeUpdate();
            if(count==1)
                pw.println("<h2> Record is registered successfully </h2>");
            else
                 pw.println("<h2> Record not registered successfully </h2>");
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
        pw.println("<br>");
        pw.println("<a href=\"BooklistServlet\">Book List</a>");
     
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    

}
