package tron;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Database {

	Connection connection;
	public String str = "";

	public Database() {
		try {
		String dbURL = "jdbc:mysql://localhost:3306/scores?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		connection = DriverManager.getConnection(dbURL,"root", "root");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getTop10() {
		ArrayList<String> array = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from scores order by score desc");
			int i = 0;
			while (rs.next() && i < 10) {
				array.add(rs.getString(2) + " " + rs.getInt(3) + " " + rs.getDate(1).toString());
				i++;
			}
			rs.close();
			statement.close();
			
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return array;
	}
	

	public boolean saveUser(String name) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select name from scores where name = '" + name + "'");
			while(rs.next()) {
				if(rs.getString(1) == null) {
					return false;
				}else {
					return true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void insertPlayer(String name) {
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO SCORES (TIMESTAMP, NAME, SCORE) VALUES ('2020.12.10', '" + name +"', 0)" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateScore(String name) {
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate("update scores set score = score + 1 where name = '" + name + "'" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
