package exemple;

import java.sql.*;

public class Exemplul1
{
	public static void main(String[]args) throws SQLException
	{
		// url-ul de conectare la baza de date
		String url = "jdbc:mysql://localhost:3306/test";
		
		Statement sql;
		ResultSet rs;
		// conexiunea cu (url, username, password)
		Connection con = DriverManager.getConnection (url, "carameea", "cafeaculapte");
		
		// cu un obiect statement se pot realiza comenzile SQL
		sql = con.createStatement();
		
		// rezultatul interogarii
		rs = sql.executeQuery("select * from persoane");
		
		while (rs.next())
			System.out.println("id="+rs.getInt("Id")+", nume= " + rs.getString("nume") + ",varsta="+rs.getInt(3));
			
		con.close();
		sql.close();
		rs.close();
	}
}
