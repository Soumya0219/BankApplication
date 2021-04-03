package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    Connection con=null;
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
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        String sql="select * from app where name=? and password=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            PrintWriter pw= response.getWriter();

            if(rs.next()){

                response.sendRedirect("./welcome.html");
            }
            else{
                response.sendRedirect("./index.html");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    }



