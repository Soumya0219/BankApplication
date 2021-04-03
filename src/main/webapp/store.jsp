<%@page import="java.sql.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
    Class.forName("com.mysql.jdbc.Driver");
    String url="jdbc:mysql://localhost:3306/transaction";
    String user="root";
    String pass="root";
    Connection con=DriverManager.getConnection(url ,user ,pass);
    String Account_No=request.getParameter("Account_No");
    String name=request.getParameter("name");
    String cash = request.getParameter("cash");
    String Amount = request.getParameter("Amount");
    int amt = Integer.parseInt(Amount);
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    java.util.Date date = new Date();
    PreparedStatement ps=con.prepareStatement("insert into trans (Account_No,name,cash,Amount) values(?,?,?,?,?)");
    ps.setString(1,Account_No);
    ps.setString(2,name);
    ps.setString(3,cash);
    ps.setInt(4,amt);
    ps.setString(5,formatter.format(date));


    int x=ps.executeUpdate();
    if(x!=0)
        response.sendRedirect("transaction.html?msg='Applied Successfully'");
%>