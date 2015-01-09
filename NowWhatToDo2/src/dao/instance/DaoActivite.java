package dao.instance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ActiviteModelBean;
import model.CompteModelBean;



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
	
	
	
	//Crée l'activité dans la base de données
	public void addActivite(ActiviteModelBean activite){

		// Création de la requête
					java.sql.Statement query;

					try {
						// create connection
						connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
								+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

						// Creation de l'élément de requète
						query = connection.createStatement();
						
						// Executer puis parcourir les résultats				
						String sql = "INSERT INTO nowwhattodo.activite (NomActivite, Description, NomLieu, Adresse, Ville, CodePostal, SiteWeb, Tel, Email, Domaine, LienPhoto, Importance) VALUES ('" +activite.getNomActivite()+"', '"+activite.getDescription()+"', '"+activite.getNomLieu()+"', '"+activite.getAdresse()+"', '"+activite.getVille()+"', '"+activite.getCodePostal()+"', '"+activite.getSiteWeb()+"', '"+activite.getTelephone()+"', '"+activite.getEmail()+"', '"+activite.getDomaine()+"', '"+activite.getLienPhoto()+"', '"+activite.getImportance()+"')";

						int rs = query.executeUpdate(sql);
						query.close();
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
	}
	
	//Met à jour l'activité "activité" : remplace par l'activité "updateActivite"
	public void updateActivite(ActiviteModelBean activite, ActiviteModelBean updateActivite)
	{
		java.sql.Statement query; 
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement(); 
			query.executeUpdate("UPDATE Activite SET NomActivite = '" +updateActivite.getNomActivite() + "', Description = '" +updateActivite.getDescription() + "', NomLieu = '" +updateActivite.getNomLieu() + "', Adresse = '" +updateActivite.getAdresse() + "', Ville = '" +updateActivite.getVille() +"', CodePostal = '" +updateActivite.getCodePostal()+"', SiteWeb = '" +updateActivite.getSiteWeb()+"', Tel = '" +updateActivite.getTelephone()+"', Email = '" +updateActivite.getEmail()+ "', Domaine = '" +updateActivite.getDomaine()+ "', LienPhoto = '" +updateActivite.getLienPhoto()+"', Importance = '" +updateActivite.getImportance()+"'");
					
			connection.close();
		} catch (SQLException e) 
		{
			e.printStackTrace(); 
		}
	}
	
	//Supprime une activité voulue par ID
	public void deleteActivite(int id)
	{
		java.sql.Statement query; 
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement(); 
			query.executeUpdate("DELETE FROM Activite WHERE Id = '" +id+  "'");
			connection.close();
		} catch (SQLException e) 
		{
			e.printStackTrace(); 
		}
	}
	
	
	//Récupère toutes les activités en base de données
	public ArrayList<ActiviteModelBean> getAllActivite()
	{
		//return value 
		ArrayList<ActiviteModelBean> activiteList = new ArrayList<ActiviteModelBean>();
		// Création de la requête 
		java.sql.Statement query; 
		try { 
			// create connection 
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD); 
			query = connection.createStatement();
			ResultSet resultat = query.executeQuery("SELECT * FROM Activite");
			//Extraction des données	
			while (resultat.next())
			{
				ActiviteModelBean model = new ActiviteModelBean();
				
				model.setNomActivite(resultat.getString("NomActivite"));
				model.setDescription(resultat.getString("Description"));
				model.setNomLieu(resultat.getString("NomLieu"));
				model.setAdresse(resultat.getString("Adresse"));
				model.setVille(resultat.getString("Ville"));
				model.setCodePostal(resultat.getString("CodePostal"));
				model.setSiteWeb(resultat.getString("SiteWeb"));
				model.setTelephone(resultat.getInt("Tel"));
				model.setEmail(resultat.getString("Email"));
				model.setDomaine(resultat.getString("Domaine"));
				model.setLienPhoto(resultat.getString("LienPhoto"));
				model.setImportance(resultat.getInt("Importance"));
				
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

}
