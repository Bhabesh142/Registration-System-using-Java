/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assgn1;

import java.io.*;
import java.net.*;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 *
 * @author HP
 */
public class ShowDetails extends HttpServlet {
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        String username = request.getParameter("un");
//        String password = request.getParameter("pwd"); 
        
        try 
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "tiger");
//            PreparedStatement ps=cn.prepareStatement("select * from studentform");
//              rs.setString(1,username);                  
//            ResultSet rs=ps.executeQuery();   
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from STUDENTFORM");
            
            while(rs.next())
                {
                out.println("<H1  style='color:BLUE;' align='center'><u>Your Registration Data </u></H1>");
                        
                out.println("<table border='1' cellspacing='5' cellpadding='5' align='center'> <tr><td align='right'> Username:</td><td> " + rs.getString(1) + "</td></tr> ");
                    out.println(" <tr><td align='right'>Password:</td><td> " + rs.getString(2) + "</td></tr>");
                    out.println(" <tr><td align='right'>Name:</td><td> " + rs.getString(3) + "</td></tr>");
                    out.println(" <tr><td align='right'>Father's Name:</td><td> " + rs.getString(4) + "</td></tr>");
                    out.println(" <tr><td align='right'>Mother's Name:</td><td> " + rs.getString(5) + "</td></tr>");
                    out.println(" <tr><td align='right'>Email:</td><td> " + rs.getString(6) + "</td></tr>");
                    out.println(" <tr><td align='right'>Address:</td><td> " + rs.getString(7) + "</td></tr>");
                    out.println(" <tr><td align='right'>Country:</td><td> " + rs.getString(8) + "</td></tr>");
                    out.println(" <tr><td align='right'>Gender:</td><td> " + rs.getString(9) + "</td></tr>");
                    out.println(" <tr><td align='right'>Qualification:</td><td> " + rs.getString(10) + "</td></tr></table>");
            }
        }
        catch (Exception e) 
        {
            out.println("Error" + e);
        }
        finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
