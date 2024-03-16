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
public class Feedback extends HttpServlet {
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String a1 = request.getParameter("name");
        String a2 = request.getParameter("email");
        String a3 = request.getParameter("phone");
        String a4 = request.getParameter("desc");
        
        try 
        {
          Class.forName("oracle.jdbc.driver.OracleDriver");
          Connection cn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","tiger");
          PreparedStatement pst = cn.prepareStatement("insert into feedback values(?,?,?,?)");
          
          pst.setString(1,a1);
          pst.setString(2,a2);
          pst.setString(3,a3);
          pst.setString(4,a4);
          
          int i=pst.executeUpdate();
          
          if(i>0)
          {
              out.println("<center> Thankyou for providing your feedback. <br> Our team will shortly contact you within 48hrs for your concern.</center>");
          }
          else
          {
            RequestDispatcher rd=request.getRequestDispatcher("Registration.html");  
            rd.include(request, response);  
            out.println("<center>You are not registered.<BR> Kindly register and provide your feedback.</center>");
          }
        } 
        catch(Exception e)
        {
            
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
