package dao.instance;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoUtilisateur {

		private Connection connection;
		private String dB_HOST;
		private String dB_PORT;
		private String dB_NAME;
		private String dB_USER;
		private String dB_PWD;

		public DaoUtilisateur(String DB_HOST, String DB_PORT, String DB_NAME,
				String DB_USER, String DB_PWD) {
			dB_HOST = DB_HOST;
			dB_PORT = DB_PORT;
			dB_NAME = DB_NAME;
			dB_USER = DB_USER;
			dB_PWD = DB_PWD;
		}

		/*public void addUser(UserModelBean user) {
			// Cr�ation de la requ�te
			java.sql.Statement query;
			try {
				// create connection
				connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
						+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

				// Creation de l'�l�ment de requ�te
				query = connection.createStatement();

				// Executer puis parcourir les r�sultats
				String sql = "INSERT INTO stepbystep1.user (surname, lastname, age, login, pwd, email) VALUES ('"
						+ user.getSurname()
						+ "', '"
						+ user.getLastname()
						+ "', '"
						+ user.getAge()
						+ "', '"
						+ user.getLogin()
						+ "', '"
						+ user.getPwd()
						+ "', '"
						+ user.getEmail() + "');";
				int rs = query.executeUpdate(sql);
				query.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/

}

