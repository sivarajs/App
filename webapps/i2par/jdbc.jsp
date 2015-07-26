<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>

<% 



InitialContext cxt = new InitialContext();
if ( cxt == null ) {
   throw new Exception("Uh oh -- no context!");
}

DataSource ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/i2parDB" );

if ( ds == null ) {
   throw new Exception("Data source not found!");
}

System.out.println("$$$$$$$ "+ds);

Class.forName("com.mysql.jdbc.Driver");
//Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/i2par?autoReconnect=true","i2par","i2par123");
Connection connection = ds.getConnection();
Statement statement = connection.createStatement();
ResultSet rs = statement.executeQuery("SELECT name from sec_user");

while (rs.next()) {
out.println(rs.getString(1)+ " " );

}
rs.close();
statement.close();
connection.close();
%>




