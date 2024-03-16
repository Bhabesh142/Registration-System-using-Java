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
public class Registration extends HttpServlet {
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String a1 = request.getParameter("un");
        String a2 = request.getParameter("pwd");
        String a3 = request.getParameter("an");
        String a4 = request.getParameter("bn");
        String a5 = request.getParameter("cn");
        String a6 = request.getParameter("name");
        String a7 = request.getParameter("ta");
        String a8 = request.getParameter("cb");
        String a9 = request.getParameter("r1");
        String a10 = request.getParameter("lb");
        
        try 
        {
          Class.forName("oracle.jdbc.driver.OracleDriver");
          Connection cn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","tiger");
          PreparedStatement pst = cn.prepareStatement("insert into studentform values(?,?,?,?,?,?,?,?,?,?)");
          
          pst.setString(1,a1);
          pst.setString(2,a2);
          pst.setString(3,a3);
          pst.setString(4,a4);
          pst.setString(5,a5);
          pst.setString(6,a6);
          pst.setString(7,a7);
          pst.setString(8,a8);
          pst.setString(9,a9);
          pst.setString(10,a10);
          
          int i=pst.executeUpdate();
          
          if(i>0)
          {
              out.println("<center> Thankyou for Registering. <br> Customer details has been entered successfully.<br> Redirecting to Login Page in 2 Seconds.</center>");
              response.setHeader("Refresh", "2;url=Login.html"); 
          }
          else
          {
              RequestDispatcher rd=request.getRequestDispatcher("Registration.html");  
            rd.include(request, response);  
            out.println("<center>Login Unsuccessful.<BR>Invalid Credentials.</center>");
          }
        } 
        catch(Exception e)
        {
            
        }
        finally 
        { 
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
