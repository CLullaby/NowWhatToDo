package dao.instance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ActiviteModelBean;
import model.CompteModelBean;
import model.DomaineModelBean;

public class DaoActivite {

	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;
	
	public DaoActivite(String DB_HOST, String DB_PORT, String DB_NAME,
			String DB_USER, String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}
	
	
	
	//Cr�e l'activit� dans la base de donn�es
	public void addActivite(ActiviteModelBean activite){

		// Cr�ation de la requ�te
					java.sql.Statement query;

					try {
						// create connection
						connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
								+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

						// Creation de l'�l�ment de requ�te
						query = connection.createStatement();
						
						// Executer puis parcourir les r�sultats				
						String sql = "INSERT INTO nowwhattodo.activite (Nom, SiteWeb, Description, IdDomaine) VALUES ('" +activite.getNom()+"', '"+activite.getSiteWeb()+"', '"+activite.getDescription()+"', '"+activite.getIdDomaine()+"')";

						int rs = query.executeUpdate(sql);
						query.close();
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
	}
	
	//Met � jour l'activit� "activit�" : remplace par l'activit� "updateActivite"
	public void updateActivite(ActiviteModelBean activite, ActiviteModelBean updateActivite)
	{
		java.sql.Statement query; 
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement(); 
			query.executeUpdate("UPDATE Activite SET Nom = '" +updateActivite.getNom() + "', SiteWeb = '" +updateActivite.getSiteWeb() + "', Description = '" +updateActivite.getDescription() + "', IdDomaine = '" +updateActivite.getIdDomaine() + "'");
					
			connection.close();
		} catch (SQLException e) 
		{
			e.printStackTrace(); 
		}
	}
	
	//Supprime une activit� voulue
	public void deleteActivite(ActiviteModelBean activite)
	{
		java.sql.Statement query; 
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement(); 
			query.executeUpdate("DELETE FROM Activite WHERE Nom = '" +activite.getNom() + "' AND SiteWeb = '" +activite.getSiteWeb() + "' AND Description = '" +activite.getDescription() + "' AND IdDomaine = '" +activite.getIdDomaine() + "'");
			connection.close();
		} catch (SQLException e) 
		{
			e.printStackTrace(); 
		}
	}
	
	
	//R�cup�re toutes les activit�s en base de donn�es
	public ArrayList<ActiviteModelBean> getAllActivite()
	{
		//return value 
		ArrayList<ActiviteModelBean> activiteList = new ArrayList<ActiviteModelBean>();
		// Cr�ation de la requ�te 
		java.sql.Statement query; 
		try { 
			// create connection 
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement();
			ResultSet resultat = query.executeQuery("SELECT * FROM Activite");
			//Extraction des donn�es	
			while (resultat.next())
			{
				ActiviteModelBean model = new ActiviteModelBean();
				model.setNom(resultat.getString("Nom"));
				model.setSiteWeb(resultat.getString("SiteWeb"));
				model.setDescription(resultat.getString("Description"));
				model.setIdDomaine(resultat.getInt("IdDomaine"));
				activiteList.add(model);
			}
			resultat.close();
			connection.close(); 
			}
		catch (SQLException e) 
		{
			e.printStackTrace(); 
		}
		return activiteList; 
	}	
	
	
	//Cr�e un domaine d'activit�s
	public void addDomaine(DomaineModelBean domaine){

		// Cr�ation de la requ�te
		java.sql.Statement query;

		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();
			
			// Executer puis parcourir les r�sultats				
			String sql = "INSERT INTO nowwhattodo.domaine (Nom) VALUES ('" +domaine.getNom()+"')";

			int rs = query.executeUpdate(sql);
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Met � jour un domaine d'activit�s 
	public void updateDomaine(DomaineModelBean domaine, DomaineModelBean updateModel){
		java.sql.Statement query;
		try{
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			query = connection.createStatement();
			query.executeUpdate("UPDATE Domaine SET Nom = '" + updateModel.getNom() + "'");
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	
	//Supprime un domaine voulu
	public void deleteDomaine(DomaineModelBean domaine)
	{
		java.sql.Statement query; 
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement(); 
			query.executeUpdate("DELETE FROM Domaine WHERE Nom = '" +domaine.getNom() + "'");
			connection.close();
		} catch (SQLException e) 
		{
			e.printStackTrace(); 
		}
	}
	
	//R�cup�re tous les domaines 
	public ArrayList<DomaineModelBean> getAllDomaine()
	{
		//return value 
		ArrayList<DomaineModelBean> domaineList = new ArrayList<DomaineModelBean>();
		// Cr�ation de la requ�te 
		java.sql.Statement query; 
		try { 
			// create connection 
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement();
			ResultSet resultat = query.executeQuery("SELECT * FROM Domaine");
			//Extraction des donn�es	
			while (resultat.next())
			{
				DomaineModelBean model = new DomaineModelBean();
				model.setNom(resultat.getString("Nom"));
				domaineList.add(model);
			}
			resultat.close();
			connection.close(); 
			}
		catch (SQLException e) 
		{
			e.printStackTrace(); 
		}
		return domaineList; 
	}	
	
	
}
