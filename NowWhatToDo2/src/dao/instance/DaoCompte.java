package dao.instance;

import java.sql.Connection;
import java.sql.SQLException;

import model.CompteModelBean;

public class DaoCompte {

		private Connection connection;
		private String dB_HOST;
		private String dB_PORT;
		private String dB_NAME;
		private String dB_USER;
		private String dB_PWD;

		public DaoCompte(String DB_HOST, String DB_PORT, String DB_NAME,
				String DB_USER, String DB_PWD) {
			dB_HOST = DB_HOST;
			dB_PORT = DB_PORT;
			dB_NAME = DB_NAME;
			dB_USER = DB_USER;
			dB_PWD = DB_PWD;
		}

		public void addUtilisateur(CompteModelBean user) {
			// Création de la requête
			java.sql.Statement query;
			try {
				// create connection
				connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
						+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

				// Creation de l'élément de requète
				query = connection.createStatement();
				
				// Executer puis parcourir les résultats
				String sql = "INSERT INTO nowwhattodo.compte (test2) VALUES ('"
						+ user.getNom()
						+ "');";
				int rs = query.executeUpdate(sql);
				query.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

}

