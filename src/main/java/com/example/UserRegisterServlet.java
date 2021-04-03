package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UserRegisterServlet extends HttpServlet {
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
        String confirm_password=request.getParameter("confirm_password");
        String email=request.getParameter("email");
        String phone=request.getParameter("phone");
        String dateOfBirth=request.getParameter("dateOfBirth");
        String gender=request.getParameter("gender");
        String address=request.getParameter("address");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        PrintWriter pw=response.getWriter();
        String sql="insert into app(name,password,confirm_password,email,phone,dateOfBirth,gender,address,registeration_time) values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,password);
            ps.setString(3,confirm_password);
            ps.setString(4,email);
            ps.setString(5,phone);
            ps.setString(6,dateOfBirth);
            ps.setString(7,gender);
            ps.setString(8,address);
            ps.setString(9,formatter.format(date));
            int x= ps.executeUpdate();
            if(x!=0)
            {
                response.sendRedirect("./login.html");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
