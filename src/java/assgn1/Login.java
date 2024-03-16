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
import java.sql.PreparedStatement;
import javax.servlet.ServletException;

/**
 *
 * @author HP
 */
public class Login extends HttpServlet {
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        int k = 0;
        String username = request.getParameter("un");
        String password = request.getParameter("pwd"); 
        
        try 
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection cn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","tiger");
            PreparedStatement ps = cn.prepareStatement("select username, password from studentform");
            ResultSet rs = ps.executeQuery();
            
        while(rs.next())
        {
            String un = rs.getString(1);
            String pwd = rs.getString(2);
            
            if(username.equals(un) && password.equals(pwd))
            {  
            out.println("<center>Login Successful.<br>Redirecting to information Page of the Customer Registration Portal in 2 Seconds.</center>");
            response.setHeader("Refresh", "2;url=ShowDetails");
            k=1;   
            break;  
            }
        }
            if(k==0)
        {    
            RequestDispatcher rd=request.getRequestDispatcher("Login.html");  
            rd.include(request, response);  
            out.println("<center>Login Unsuccessful.<BR> Invalid Credentials </center>");
       
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
