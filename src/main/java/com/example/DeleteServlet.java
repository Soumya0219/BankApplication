package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class DeleteServlet extends HttpServlet {
    Connection con=null;
    PreparedStatement ps=null;
    public void init(ServletConfig config){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/bank1";
            String user="root";
            String password="root";
            con= DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql="delete from app where name='urmi'";
        try {
            ps= con.prepareStatement(sql);
            int x=ps.executeUpdate();
            PrintWriter pw=response.getWriter();

            if(x!=0){
                pw.println("one row deleted from table");
            }
            else{
                response.sendRedirect("./index.html");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
