package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionServlet extends HttpServlet {
    Connection con=null;
    public void init(ServletConfig config){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/transaction";
            String user="root";
            String password="root";
            con= DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Account_No=request.getParameter("Account_No");
        String name=request.getParameter("name");
        String cash=request.getParameter("cash");
        String Amount=request.getParameter("Amount");
        int amt=Integer.parseInt(Amount);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        PrintWriter pw=response.getWriter();
        String sql="insert into trans(Account_No,name,cash,amount,registration_time) values(?,?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,Account_No);
            ps.setString(2,name);
            ps.setString(3,cash);
            ps.setInt(4,amt);
            ps.setString(5,formatter.format(date));

            int x=ps.executeUpdate();
            if(x!=0){
                pw.println("<html><h2>Transaction successful</h2></html>");
            }
            else{
                response.sendRedirect("./transaction.html");
            }
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }



