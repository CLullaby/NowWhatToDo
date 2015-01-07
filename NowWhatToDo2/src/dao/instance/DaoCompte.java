package dao.instance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
				String sql = "INSERT INTO nowwhattodo.compte (Nom, Prenom, Identifiant, MotDePasse, Email, Age, LienPhoto, Adresse, Role) VALUES ('" +user.getNom()+"', '"+user.getPrenom()+"', '"+user.getIdentifiant()+"', '"+user.getMotDePasse()+"', '"+user.getEmail()+"', '"+user.getAge()+"', '"+user.getLienPhoto()+"', '"+user.getAdresse()+"', '"+user.getRole()+"')";

				int rs = query.executeUpdate(sql);
				query.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void updateUtilisateur(CompteModelBean user, CompteModelBean updateUser)
		{
			java.sql.Statement query; 
			try {
				connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
				query = connection.createStatement(); 
				query.executeUpdate("UPDATE Compte SET Nom = '" +updateUser.getNom() + "', Prenom = '" +updateUser.getPrenom() + "', Identifiant = '" +updateUser.getIdentifiant() + "', MotDePasse = '" +updateUser.getMotDePasse() + "', Email = '" +updateUser.getEmail() + "', Age = '" +updateUser.getAge() + "', LienPhoto = '" +updateUser.getLienPhoto() + "', Adresse = '" +updateUser.getAdresse() + "', Role = '" +updateUser.getRole() + "' WHERE Nom = '" +user.getNom() + "' AND Prenom = '" +user.getPrenom() + "' AND Identifiant = '" +user.getIdentifiant() + "' AND MotDePasse = '" +user.getMotDePasse() + "' AND Email = '" +user.getEmail() + "' AND Age = '" +user.getAge() + "' AND LienPhoto = '" +user.getLienPhoto() + "' AND Adresse = '" +user.getAdresse() + "' AND Role = '" +user.getRole() + "'");
						
				connection.close();
			} catch (SQLException e) 
			{
				e.printStackTrace(); 
			}
		}
		
		public void deleteUtilisateur(CompteModelBean user)
		{
			java.sql.Statement query; 
			try {
				connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
				query = connection.createStatement(); 
				query.executeUpdate("DELETE FROM Compte WHERE Nom = '" +user.getNom() + "' AND Prenom = '" +user.getPrenom() + "' AND Identifiant = '" +user.getIdentifiant() + "' AND MotDePasse = '" +user.getMotDePasse() + "' AND Email = '" +user.getEmail() + "' AND Age = '" +user.getAge() + "' AND LienPhoto = '" +user.getLienPhoto() + "' AND Adresse = '" +user.getAdresse() + "' AND Role = '" +user.getRole() + "'");
				connection.close();
			} catch (SQLException e) 
			{
				e.printStackTrace(); 
			}
		}
		
		public ArrayList<CompteModelBean> getAllUser()
		{
			//return value 
			ArrayList<CompteModelBean> userList = new ArrayList<CompteModelBean>();
			// Création de la requête 
			java.sql.Statement query; 
			try { 
				// create connection 
				connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
				query = connection.createStatement();
				ResultSet resultat = query.executeQuery("SELECT * FROM Compte");
				//Extraction des données	
				while (resultat.next())
				{
					CompteModelBean model = new CompteModelBean();
					model.setNom(resultat.getString("Nom"));
					model.setPrenom(resultat.getString("Prenom"));
					model.setIdentifiant(resultat.getString("Identifiant"));
					model.setMotDePasse(resultat.getString("MotDePasse"));
					model.setEmail(resultat.getString("Email"));
					model.setAge(resultat.getInt("Age"));
					model.setLienPhoto(resultat.getString("LienPhoto"));
					model.setAdresse(resultat.getString("Adresse"));
					model.setRole(resultat.getString("Role"));
					userList.add(model);
				}
				resultat.close();
				connection.close(); 
				}
			catch (SQLException e) 
			{
				e.printStackTrace(); 
			}
			return userList; 
		}	
		

}

