package shoppingCart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import shoppingCart.model.LoginBean;
import shoppingCart.model.User;
import shoppingCart.utils.JDBCUtils;

public class LoginDao {
	Connection connection = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	Statement st = null;

	public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
		boolean status = false;

		Class.forName("com.mysql.jdbc.Driver");

		try {
			connection = JDBCUtils.getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement ps = connection
					.prepareStatement("select * from user where username = ? and password = ? ");
			ps.setString(1, loginBean.getUsername());
			ps.setString(2, loginBean.getPassword());

			System.out.println(ps);
			rs = ps.executeQuery();
			status = rs.next();

		} catch (SQLException e) {
			// process sql exception
			JDBCUtils.printSQLException(e);
		} finally {
			JDBCUtils.cleanUp(connection,rs, ps);
		}
			return status;
	}

	public String createUser(User user) throws ClassNotFoundException {
		String status = "{response:404, message:record not inserted}";
		int response=0;
		Class.forName("com.mysql.jdbc.Driver");

		try {
			connection = JDBCUtils.getConnection();
			// Step 2:Create a statement using connection object
			ps = connection.prepareStatement(
					"INSERT INTO user (firstname, lastname, email, mobilenumber,username,password) VALUES (?, ?, ?, ?,?,?)");
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getemail());
			ps.setString(4, user.getMobileNumber());
			ps.setString(5, user.getUsername());
			ps.setString(6, user.getPassword());

			response = ps.executeUpdate();
			if (response>=1) {
			return "{response:201, message: user record inserted Successfully }";
			}
			System.out.println("res "+response);

		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		} finally {
			JDBCUtils.cleanUp(connection,rs, ps);
		}
		return status;
	}
	
	
	
}
