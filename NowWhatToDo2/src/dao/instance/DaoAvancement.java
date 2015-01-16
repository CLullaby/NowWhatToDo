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

			// Creation de l'élément de requète
			query = connection.createStatement();
			
			// Executer puis parcourir les résultats				
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

			// Creation de l'élément de requète
			query = connection.createStatement();
			
			// Executer puis parcourir les résultats				
			String sql = "UPDATE Avancement SET Avancement = '" +updateActivite.getAvancement() + "', DateDebut = '" +updateActivite.getDateDebut() + "', DateFin = '" +updateActivite.getDateFin() + "', CECompte = '" +updateActivite.getCeCompte() + "', CEActivite = '" +updateActivite.getCeActivite() + "' WHERE Avancement = '" +activite.getAvancement() + "' AND DateDebut = '" +activite.getDateDebut() + "' AND DateFin = '" +activite.getDateFin() + "' AND CECompte = '" +activite.getCeCompte() + "' AND CEActivite = '" +activite.getCeActivite() + "'";
			
			query.executeUpdate(sql);
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateAvancement(int avancement, AvancementActiviteModelBean activite)
	{
		java.sql.Statement query;

		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'élément de requète
			query = connection.createStatement();
			
			// Executer puis parcourir les résultats				
			String sql = "UPDATE Avancement SET Avancement = '" +avancement + "' WHERE Avancement = '" +activite.getAvancement() + "'";
			
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
	
	public ArrayList<AvancementActiviteModelBean> getAllAvancementActivite()
	{
		//return value 
		ArrayList<AvancementActiviteModelBean> list = new ArrayList<AvancementActiviteModelBean>();
		// Création de la requête 
		java.sql.Statement query; 
		try { 
			// create connection 
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement();
			ResultSet resultat = query.executeQuery("SELECT * FROM Avancement");
			//Extraction des données	
			while (resultat.next())
			{
				AvancementActiviteModelBean model = new AvancementActiviteModelBean();
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
	
	public ArrayList<AvancementActiviteModelBean> getAvancementActiviteByCompte(int idCompte)
	{
		//return value 
		ArrayList<AvancementActiviteModelBean> list = new ArrayList<AvancementActiviteModelBean>();
		// Création de la requête 
		java.sql.Statement query; 
		try { 
			// create connection 
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement();
			ResultSet resultat = query.executeQuery("SELECT * FROM Avancement WHERE CECompte = '" + idCompte + "'");
			//Extraction des données	
			while (resultat.next())
			{
				AvancementActiviteModelBean model = new AvancementActiviteModelBean();
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
		//return value 
		ArrayList<AvancementActiviteModelBean> list = new ArrayList<AvancementActiviteModelBean>();
		// Création de la requête 
		java.sql.Statement query; 
		try { 
			// create connection 
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement();
			ResultSet resultat = query.executeQuery("SELECT * FROM Avancement WHERE CEActivite = '" + idActivite + "'");
			//Extraction des données	
			while (resultat.next())
			{
				AvancementActiviteModelBean model = new AvancementActiviteModelBean();
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
		//return value 
		ArrayList<AvancementActiviteModelBean> list = new ArrayList<AvancementActiviteModelBean>();
		// Création de la requête 
		java.sql.Statement query; 
		try { 
			// create connection 
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement();
			ResultSet resultat = query.executeQuery("SELECT * FROM Avancement WHERE Avancement = '" + avancement + "'");
			//Extraction des données	
			while (resultat.next())
			{
				AvancementActiviteModelBean model = new AvancementActiviteModelBean();
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
		//return value 
		ArrayList<AvancementActiviteModelBean> list = new ArrayList<AvancementActiviteModelBean>();
		// Création de la requête 
		java.sql.Statement query; 
		try { 
			// create connection 
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement();
			ResultSet resultat = query.executeQuery("SELECT * FROM Avancement WHERE DateDebut = '" + dateD + "'");
			//Extraction des données	
			while (resultat.next())
			{
				AvancementActiviteModelBean model = new AvancementActiviteModelBean();
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
		//return value 
		ArrayList<AvancementActiviteModelBean> list = new ArrayList<AvancementActiviteModelBean>();
		// Création de la requête 
		java.sql.Statement query; 
		try { 
			// create connection 
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement();
			ResultSet resultat = query.executeQuery("SELECT * FROM Avancement WHERE DateFin = '" + dateF + "'");
			//Extraction des données	
			while (resultat.next())
			{
				AvancementActiviteModelBean model = new AvancementActiviteModelBean();
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
