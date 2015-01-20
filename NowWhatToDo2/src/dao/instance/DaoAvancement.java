package dao.instance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.AvancementActiviteModelBean;

public class DaoAvancement {
	
	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;

	public DaoAvancement(String DB_HOST, String DB_PORT, String DB_NAME,
			String DB_USER, String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}
	
	public void addAvancementActivite(AvancementActiviteModelBean activite)
	{
		java.sql.Statement query;

		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();
			
			// Executer puis parcourir les r�sultats				
			String sql = "INSERT INTO nowwhattodo.avancement (Avancement, DateDebut, DateFin, CECompte, CEActivite) VALUES ('" +activite.getAvancement()+"', '"+activite.getDateDebut()+"', '"+activite.getDateFin()+"', '"+activite.getCeCompte()+"', '"+activite.getCeActivite()+"')";

			query.executeUpdate(sql);
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateAvancementActivite(AvancementActiviteModelBean activite, AvancementActiviteModelBean updateActivite)
	{
		java.sql.Statement query;

		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();
			
			// Executer puis parcourir les r�sultats				
			String sql = "UPDATE Avancement SET Avancement = '" +updateActivite.getAvancement() + "', DateDebut = '" +updateActivite.getDateDebut() + "', DateFin = '" +updateActivite.getDateFin() + "', CECompte = '" +updateActivite.getCeCompte() + "', CEActivite = '" +updateActivite.getCeActivite() + "' WHERE Avancement = '" +activite.getAvancement() + "' AND DateDebut = '" +activite.getDateDebut() + "' AND DateFin = '" +activite.getDateFin() + "' AND CECompte = '" +activite.getCeCompte() + "' AND CEActivite = '" +activite.getCeActivite() + "'";
			
			query.executeUpdate(sql);
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*Rajouter par rapport � l'id de l'activit� sinon cette requete updateAvancement() modifie plusieurs lignes.*/
	public void updateAvancement(int avancement, AvancementActiviteModelBean activite)
	{
		java.sql.Statement query;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();
			
			// Executer puis parcourir les r�sultats				
			String sql = "UPDATE Avancement SET Avancement = '" +avancement + "' WHERE IdAvancement = '" +activite.getIdAvancement() + "'";
			
			query.executeUpdate(sql);
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAvancementActivite(int id)
	{
		java.sql.Statement query; 
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement(); 
			query.executeUpdate("DELETE FROM Avancement WHERE IdAvancement = '" +id + "'");
			connection.close();
		} catch (SQLException e) 
		{
			e.printStackTrace(); 
		}
	}
	
	//Supprime les avancements li�s � une activit�
	public void deleteAvancementByActivite(int idActivite){
		java.sql.Statement query; 
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement(); 
			query.executeUpdate("DELETE FROM Avancement WHERE CEActivite = '" +idActivite + "'");
			connection.close();
		} catch (SQLException e) 
		{
			e.printStackTrace(); 
		}
	}
	
	public ArrayList<AvancementActiviteModelBean> getAllAvancementActivite()
	{
		//return tout les avancements par activites 
		ArrayList<AvancementActiviteModelBean> list = new ArrayList<AvancementActiviteModelBean>();
		// Cr�ation de la requ�te 
		java.sql.Statement query; 
		try { 
			// create connection 
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement();
			ResultSet resultat = query.executeQuery("SELECT * FROM Avancement");
			//Extraction des donn�es	
			while (resultat.next())
			{
				AvancementActiviteModelBean model = new AvancementActiviteModelBean();
				model.setIdAvancement(resultat.getInt("IdAvancement"));
				model.setAvancement(resultat.getInt("Avancement"));
				model.setDateDebut(resultat.getString("DateDebut"));
				model.setDateFin(resultat.getString("DateFin"));
				model.setCeCompte(resultat.getInt("CECompte"));
				model.setCeActivite(resultat.getInt("CEActivite"));
				list.add(model);
			}
			resultat.close();
			connection.close(); 
			}
		catch (SQLException e) 
		{
			e.printStackTrace(); 
		}
		return list; 
	}	
	
	public AvancementActiviteModelBean getAvancementActiviteById(int idAvancementActivite)
	{
		// return activit� s�lectionn� par l'id 
		AvancementActiviteModelBean model = new AvancementActiviteModelBean();
		// Cr�ation de la requ�te 
		java.sql.Statement query; 
		try { 
			// create connection 
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement();
			ResultSet resultat = query.executeQuery("SELECT * FROM Avancement WHERE idAvancement = '" + idAvancementActivite + "'");
			//Extraction des donn�es	
			while (resultat.next())
			{
				model.setIdAvancement(resultat.getInt("IdAvancement"));
				model.setAvancement(resultat.getInt("Avancement"));
				model.setDateDebut(resultat.getString("DateDebut"));
				model.setDateFin(resultat.getString("DateFin"));
				model.setCeCompte(resultat.getInt("CECompte"));
				model.setCeActivite(resultat.getInt("CEActivite"));
				
			}
			resultat.close();
			connection.close(); 
			}
		catch (SQLException e) 
		{
			e.printStackTrace(); 
		}
		return model; 
	}	
	
	public ArrayList<AvancementActiviteModelBean> getAvancementActiviteByCompte(int idCompte)
	{
		//return les avancements d'�ctivit� par utilisateur 
		ArrayList<AvancementActiviteModelBean> list = new ArrayList<AvancementActiviteModelBean>();
		// Cr�ation de la requ�te 
		java.sql.Statement query; 
		try { 
			// create connection 
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement();
			ResultSet resultat = query.executeQuery("SELECT * FROM Avancement WHERE CECompte = '" + idCompte + "'");
			//Extraction des donn�es	
			while (resultat.next())
			{
				AvancementActiviteModelBean model = new AvancementActiviteModelBean();
				model.setIdAvancement(resultat.getInt("IdAvancement"));
				model.setAvancement(resultat.getInt("Avancement"));
				model.setDateDebut(resultat.getString("DateDebut"));
				model.setDateFin(resultat.getString("DateFin"));
				model.setCeCompte(resultat.getInt("CECompte"));
				model.setCeActivite(resultat.getInt("CEActivite"));
				list.add(model);
			}
			resultat.close();
			connection.close(); 
			}
		catch (SQLException e) 
		{
			e.printStackTrace(); 
		}
		return list; 
	}
	
	public ArrayList<AvancementActiviteModelBean> getAvancementActiviteByActivite(int idActivite)
	{
		//return tout les avancements par activit� 
		ArrayList<AvancementActiviteModelBean> list = new ArrayList<AvancementActiviteModelBean>();
		// Cr�ation de la requ�te 
		java.sql.Statement query; 
		try { 
			// create connection 
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement();
			ResultSet resultat = query.executeQuery("SELECT * FROM Avancement WHERE CEActivite = '" + idActivite + "'");
			//Extraction des donn�es	
			while (resultat.next())
			{
				AvancementActiviteModelBean model = new AvancementActiviteModelBean();
				model.setIdAvancement(resultat.getInt("IdAvancement"));
				model.setAvancement(resultat.getInt("Avancement"));
				model.setDateDebut(resultat.getString("DateDebut"));
				model.setDateFin(resultat.getString("DateFin"));
				model.setCeCompte(resultat.getInt("CECompte"));
				model.setCeActivite(resultat.getInt("CEActivite"));
				list.add(model);
			}
			resultat.close();
			connection.close(); 
			}
		catch (SQLException e) 
		{
			e.printStackTrace(); 
		}
		return list; 
	}	
	
	public ArrayList<AvancementActiviteModelBean> getAvancementActiviteByAvancement(int avancement)
	{
		//return liste d'avancement d'ativit� par avancement 
		ArrayList<AvancementActiviteModelBean> list = new ArrayList<AvancementActiviteModelBean>();
		// Cr�ation de la requ�te 
		java.sql.Statement query; 
		try { 
			// create connection 
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement();
			ResultSet resultat = query.executeQuery("SELECT * FROM Avancement WHERE Avancement = '" + avancement + "'");
			//Extraction des donn�es	
			while (resultat.next())
			{
				AvancementActiviteModelBean model = new AvancementActiviteModelBean();
				model.setIdAvancement(resultat.getInt("IdAvancement"));
				model.setAvancement(resultat.getInt("Avancement"));
				model.setDateDebut(resultat.getString("DateDebut"));
				model.setDateFin(resultat.getString("DateFin"));
				model.setCeCompte(resultat.getInt("CECompte"));
				model.setCeActivite(resultat.getInt("CEActivite"));
				list.add(model);
			}
			resultat.close();
			connection.close(); 
			}
		catch (SQLException e) 
		{
			e.printStackTrace(); 
		}
		return list; 
	}	
	
	public ArrayList<AvancementActiviteModelBean> getAvancementActiviteByDateDebut(String dateD)
	{
		//return liste d'avancement d'ativit� par date de debut 
		ArrayList<AvancementActiviteModelBean> list = new ArrayList<AvancementActiviteModelBean>();
		// Cr�ation de la requ�te 
		java.sql.Statement query; 
		try { 
			// create connection 
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement();
			ResultSet resultat = query.executeQuery("SELECT * FROM Avancement WHERE DateDebut = '" + dateD + "'");
			//Extraction des donn�es	
			while (resultat.next())
			{
				AvancementActiviteModelBean model = new AvancementActiviteModelBean();
				model.setIdAvancement(resultat.getInt("IdAvancement"));
				model.setAvancement(resultat.getInt("Avancement"));
				model.setDateDebut(resultat.getString("DateDebut"));
				model.setDateFin(resultat.getString("DateFin"));
				model.setCeCompte(resultat.getInt("CECompte"));
				model.setCeActivite(resultat.getInt("CEActivite"));
				list.add(model);
			}
			resultat.close();
			connection.close(); 
			}
		catch (SQLException e) 
		{
			e.printStackTrace(); 
		}
		return list; 
	}	
	
	public ArrayList<AvancementActiviteModelBean> getAvancementActiviteByDateFin(String dateF)
	{
		//return liste d'avancement d'ativit� par date de fin 
		ArrayList<AvancementActiviteModelBean> list = new ArrayList<AvancementActiviteModelBean>();
		// Cr�ation de la requ�te 
		java.sql.Statement query; 
		try { 
			// create connection 
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement();
			ResultSet resultat = query.executeQuery("SELECT * FROM Avancement WHERE DateFin = '" + dateF + "'");
			//Extraction des donn�es	
			while (resultat.next())
			{
				AvancementActiviteModelBean model = new AvancementActiviteModelBean();
				model.setIdAvancement(resultat.getInt("IdAvancement"));
				model.setAvancement(resultat.getInt("Avancement"));
				model.setDateDebut(resultat.getString("DateDebut"));
				model.setDateFin(resultat.getString("DateFin"));
				model.setCeCompte(resultat.getInt("CECompte"));
				model.setCeActivite(resultat.getInt("CEActivite"));
				list.add(model);
			}
			resultat.close();
			connection.close(); 
			}
		catch (SQLException e) 
		{
			e.printStackTrace(); 
		}
		return list; 
	}	
}
