package dao.instance;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.CompteModelBean;

import enumerations.Roles;

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
			// Cr�ation de la requ�te
			java.sql.Statement query;

			try {
				// create connection
				connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
						+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

				// Creation de l'�l�ment de requ�te
				query = connection.createStatement();
				
				// Executer puis parcourir les r�sultats				
				String sql = "INSERT INTO nowwhattodo.compte (Nom, Prenom, Identifiant, MotDePasse, Email, Age, LienPhoto, Adresse, CodePostal, Tel, Role) VALUES ('" +user.getNom()+"', '"+user.getPrenom()+"', '"+user.getIdentifiant()+"', '"+user.getMotDePasse()+"', '"+user.getEmail()+"', '"+user.getAge()+"', '"+user.getLienPhoto()+"', '"+user.getAdresse()+"', '"+user.getCodePostal()+"', '"+user.getTel()+"', '"+user.getRole()+"')";

				query.executeUpdate(sql);
				query.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/*
		public void updateUtilisateur(CompteModelBean user, CompteModelBean updateUser)
		{
			java.sql.Statement query; 
			try {
				connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
				query = connection.createStatement(); 
				query.executeUpdate("UPDATE Compte SET Nom = '" +updateUser.getNom() + "', Prenom = '" +updateUser.getPrenom() + "', Identifiant = '" +updateUser.getIdentifiant() + "', MotDePasse = '" +updateUser.getMotDePasse() + "', Email = '" +updateUser.getEmail() + "', Age = '" +updateUser.getAge() + "', LienPhoto = '" +updateUser.getLienPhoto() + "', Adresse = '" +updateUser.getAdresse() + "', CodePostal = '" +updateUser.getCodePostal() + "', Tel = '" +updateUser.getTel() + "', Role = '" +updateUser.getRole() + "' WHERE Nom = '" +user.getNom() + "' AND Prenom = '" +user.getPrenom() + "' AND Identifiant = '" +user.getIdentifiant() + "' AND MotDePasse = '" +user.getMotDePasse() + "' AND Email = '" +user.getEmail() + "' AND Age = '" +user.getAge() + "' AND LienPhoto = '" +user.getLienPhoto() + "' AND Adresse = '" +user.getAdresse() + "' AND CodePostal = '" +user.getCodePostal() + "' AND Tel = '" +user.getTel() + "' AND Role = '" +user.getRole() + "'");
						
				connection.close();
			} catch (SQLException e) 
			{
				e.printStackTrace(); 
			}
		}*/
		
		//Update sans le mot de passe
		public void updateUtilisateur(CompteModelBean user, CompteModelBean updateUser)
		{
			java.sql.Statement query; 
			try {
				connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
				query = connection.createStatement(); 
				query.executeUpdate("UPDATE Compte SET Nom = '" +updateUser.getNom().replace("'","\\'") + "', Prenom = '" +updateUser.getPrenom().replace("'","\\'") + "', Email = '" +updateUser.getEmail() + "', Age = '" +updateUser.getAge() + "', LienPhoto = '" +updateUser.getLienPhoto().replace("'","\\'") + "', Adresse = '" +updateUser.getAdresse().replace("'","\\'") + "', CodePostal = '" +updateUser.getCodePostal() + "', Tel = '" +updateUser.getTel() + "', Role = '" +updateUser.getRole().replace("'","\\'") + "' WHERE Nom = '" +user.getNom().replace("'","\\'") + "' AND Prenom = '" +user.getPrenom().replace("'","\\'") + "' AND Email = '" +user.getEmail() + "' AND Age = '" +user.getAge() + "' AND LienPhoto = '" +user.getLienPhoto().replace("'","\\'") + "' AND Adresse = '" +user.getAdresse().replace("'","\\'") + "' AND CodePostal = '" +user.getCodePostal() + "' AND Tel = '" +user.getTel() + "' AND Role = '" +user.getRole().replace("'","\\'") + "'");
						
				connection.close();
			} catch (SQLException e) 
			{
				e.printStackTrace(); 
			}
		}
		
		//Update que du mot de passe
		public void updateMotDePasse(String login, String updateMdp)
		{
			java.sql.Statement query; 
			try {
				connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
				query = connection.createStatement(); 
				query.executeUpdate("UPDATE Compte SET MotDePasse = '" +updateMdp + "' WHERE Identifiant = '" +login +"'");
						
				connection.close();
			} catch (SQLException e) 
			{
				e.printStackTrace(); 
			}
		}
		
		public void deleteUtilisateur(int idUser)
		{
			java.sql.Statement query; 
			try {
				connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
				query = connection.createStatement(); 
				query.executeUpdate("DELETE FROM Compte WHERE Id = '" + idUser + "'");
				connection.close();
			} catch (SQLException e) 
			{
				e.printStackTrace(); 
			}
		}
		
		public ArrayList<CompteModelBean> getAllUser()
		{
			//return tout les utilisateurs 
			ArrayList<CompteModelBean> userList = new ArrayList<CompteModelBean>();
			// Cr�ation de la requ�te 
			java.sql.Statement query; 
			try { 
				// create connection 
				connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
				query = connection.createStatement();
				ResultSet resultat = query.executeQuery("SELECT * FROM Compte");
				//Extraction des donn�es	
				while (resultat.next())
				{
					CompteModelBean model = new CompteModelBean();
					model.setId(resultat.getInt("Id"));
					model.setNom(resultat.getString("Nom"));
					model.setPrenom(resultat.getString("Prenom"));
					model.setIdentifiant(resultat.getString("Identifiant"));
					model.setMotDePasse(resultat.getString("MotDePasse"));
					model.setEmail(resultat.getString("Email"));
					model.setAge(resultat.getInt("Age"));
					model.setLienPhoto(resultat.getString("LienPhoto"));
					model.setAdresse(resultat.getString("Adresse"));
					model.setCodePostal(resultat.getString("CodePostal"));
					model.setTel(resultat.getString("Tel"));
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
		public ArrayList<CompteModelBean> getAllUserAdmin()
		{
			//return tout les admins 
			ArrayList<CompteModelBean> userList = new ArrayList<CompteModelBean>();
			// Cr�ation de la requ�te 
			java.sql.Statement query; 
			try { 
				// create connection 
				connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
				query = connection.createStatement();
				ResultSet resultat = query.executeQuery("SELECT * FROM Compte WHERE Role = '" + Roles.Administrateur.returnValue() + "'");
				//Extraction des donn�es	
				while (resultat.next())
				{
					CompteModelBean model = new CompteModelBean();
					model.setId(resultat.getInt("Id"));
					model.setNom(resultat.getString("Nom"));
					model.setPrenom(resultat.getString("Prenom"));
					model.setIdentifiant(resultat.getString("Identifiant"));
					model.setMotDePasse(resultat.getString("MotDePasse"));
					model.setEmail(resultat.getString("Email"));
					model.setAge(resultat.getInt("Age"));
					model.setLienPhoto(resultat.getString("LienPhoto"));
					model.setAdresse(resultat.getString("Adresse"));
					model.setCodePostal(resultat.getString("CodePostal"));
					model.setTel(resultat.getString("Tel"));
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
		
		public CompteModelBean getUserLogin(String login)
		{
			//return l'utilisateur ayant ce login 
			CompteModelBean userReturn = new CompteModelBean();
			ArrayList<CompteModelBean> userList=new ArrayList<CompteModelBean>();
			// Cr�ation de la requ�te 
			java.sql.Statement query; 
			try { 
				// create connection 
				connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
				query = connection.createStatement();
				ResultSet resultat = query.executeQuery("SELECT * FROM Compte WHERE Identifiant = '" + login + "'");
				//Extraction des donn�es	
				while (resultat.next())
				{
					CompteModelBean model = new CompteModelBean();
					model.setId(resultat.getInt("Id"));
					model.setNom(resultat.getString("Nom"));
					model.setPrenom(resultat.getString("Prenom"));
					model.setIdentifiant(resultat.getString("Identifiant"));
					model.setMotDePasse(resultat.getString("MotDePasse"));
					model.setEmail(resultat.getString("Email"));
					model.setAge(resultat.getInt("Age"));
					model.setLienPhoto(resultat.getString("LienPhoto"));
					model.setAdresse(resultat.getString("Adresse"));
					model.setCodePostal(resultat.getString("CodePostal"));
					model.setTel(resultat.getString("Tel"));
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
			//Affectation de la valeur de retour
			if(!userList.isEmpty())
			{
				userReturn = userList.get(0);
			}
			return userReturn;
		}
		
		//checke utilisateur pour connexion banale du site
		public boolean checkUtilisateur(String login, String password)
		{
			boolean value = false;
			
			//Recherche dans la BD
			ArrayList<CompteModelBean> listeUser = getAllUser();
			for(CompteModelBean userTemp : listeUser)
			{
				if(userTemp.getIdentifiant().equals(login) && userTemp.getMotDePasse().equals(password))
				{
					value = true;
					//break;
				}
			}
			return value;
		}
		//checke utilisateur administrateur pour connexion � la partie admin -> Plus de protection en pr�triant par les role
		public boolean checkUtilisateurAdmin(String login, String password)
		{
			boolean value = false;			
			//Recherche dans la BD
			ArrayList<CompteModelBean> listeUser = getAllUserAdmin();
			for(CompteModelBean userTemp : listeUser)
			{
				if(userTemp.getIdentifiant().equals(login) && userTemp.getMotDePasse().equals(password))
				{
					value = true;
					//break;
				}
			}
			return value;
		}
		
		//permet de hash les mdp en SHA-256
		public String hasher(String toHash) {
			byte[] hash = null;
			 
		    try {
		        hash = MessageDigest.getInstance("SHA-256").digest(toHash.getBytes());
		    } catch (NoSuchAlgorithmException ex) {
		        Logger.getLogger(HashSet.class.getName()).log(Level.SEVERE, null, ex);
		    }
		    StringBuilder stringBuilder = new StringBuilder();
		    for (byte byt : hash) {
		        String hex = Integer.toHexString(byt);
		        if (hex.length() == 1) {
		            stringBuilder.append(0);
		            stringBuilder.append(hex.charAt(hex.length() - 1));
		        } else {
		            stringBuilder.append(hex.substring(hex.length() - 2));
		        }
		    }
		    return stringBuilder.toString();
		}
		
		public boolean checkLoginExiste(String login)
		{
			// recherche si le login existe 
			boolean returnValue = false;
			ArrayList<CompteModelBean> listeUser = getAllUser();
			for (CompteModelBean user : listeUser)
			{
				if(user.getIdentifiant().equals(login))
				{
					returnValue = true;
					break;
				}
			}
			return returnValue;
		}
}

