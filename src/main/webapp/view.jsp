<!DOCTYPE html>
<html>
<body bgcolor="wheat">
<br><br><br><br>
<div style="text-align: center;"></div>
<br><br>
<div style="text-align: center;">
        <div style="margin-left: 800px;">
    <h1> TRANSACTION DETAILS</h1>

    <div style="text-align: center;"/>
    <%@page import="java.sql.*"%>
    <%
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/transaction";
        String user="root";
        String pass="root";
        Connection con=DriverManager.getConnection(url ,user ,pass);%>
    <div style="text-align: center;">
        <div style="margin-left: 800px;">
            <a href="welcome.html" style="font-size: 20px; text-decoration: none;">Home</a>&nbsp;&nbsp;
            <a href="account.html" style="font-size: 20px; text-decoration: none;">My Account</a>&nbsp;&nbsp;&nbsp;
            <a href="index.html" style="font-size: 20px; text-decoration: none;">Logout</a>
        </div>
        <table align="center" border="2" cellpadding="10">
            <tr><td>Account_No</td><td>name</td><td>cash</td><td>Amount</td><td>registration_time</td></tr>
            <%
                PreparedStatement ps=con.prepareStatement("select * from trans");
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
            %>
            <tr><td><%=rs.getString(1) %></td>
                <td><%=rs.getString(2) %></td>
                <td><%=rs.getString(3) %></td>
                <td><%=rs.getInt(4) %></td>
                <td><%=rs.getString(5) %></td>
            </tr>
            <%} %>
        </table>
    </div>

</div>
</body>
</html>