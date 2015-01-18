package dao.instance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ActiviteModelBean;

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

	// Crée l'activité dans la base de données
	public void addActivite(ActiviteModelBean activite) {

		// Création de la requête
		java.sql.Statement query;

		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'élément de requète
			query = connection.createStatement();

			// Executer puis parcourir les résultats
			String sql = "INSERT INTO nowwhattodo.activite (NomActivite, Description, NomLieu, Adresse, Ville, CodePostal, SiteWeb, Tel, Email, Domaine, LienPhoto, Importance) VALUES ('"
					+ activite.getNomActivite().replace("'","\\'")
					+ "', '"
					+ activite.getDescription().replace("'","\\'")
					+ "', '"
					+ activite.getNomLieu().replace("'","\\'")
					+ "', '"
					+ activite.getAdresse().replace("'","\\'")
					+ "', '"
					+ activite.getVille().replace("'","\\'")
					+ "', '"
					+ activite.getCodePostal().replace("'","\\'")
					+ "', '"
					+ activite.getSiteWeb().replace("'","\\'")
					+ "', '"
					+ activite.getTelephone()
					+ "', '"
					+ activite.getEmail()
					+ "', '"
					+ activite.getDomaine().replace("'","\\'")
					+ "', '"
					+ activite.getLienPhoto().replace("'","\\'")
					+ "', '"
					+ activite.getImportance() + "')";

			query.executeUpdate(sql);
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Met à jour l'activité "activité" : remplace par l'activité
	// "updateActivite"
	public void updateActivite(int idActivite,
			ActiviteModelBean updateActivite) {
		java.sql.Statement query;
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			query = connection.createStatement();
			query.executeUpdate("UPDATE Activite SET NomActivite = '"
					+ updateActivite.getNomActivite().replace("'","\\'") + "', Description = '"
					+ updateActivite.getDescription().replace("'","\\'") + "', NomLieu = '"
					+ updateActivite.getNomLieu().replace("'","\\'") + "', Adresse = '"
					+ updateActivite.getAdresse().replace("'","\\'") + "', Ville = '"
					+ updateActivite.getVille().replace("'","\\'") + "', CodePostal = '"
					+ updateActivite.getCodePostal() + "', SiteWeb = '"
					+ updateActivite.getSiteWeb().replace("'","\\'") + "', Tel = '"
					+ updateActivite.getTelephone() + "', Email = '"
					+ updateActivite.getEmail() + "', Domaine = '"
					+ updateActivite.getDomaine().replace("'","\\'") + "', LienPhoto = '"
					+ updateActivite.getLienPhoto().replace("'","\\'") + "', Importance = '"
					+ updateActivite.getImportance() + "'" + "WHERE id= '" + idActivite +"'");

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Supprime une activité voulue par ID
	public void deleteActivite(int id) {
		java.sql.Statement query;
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			query = connection.createStatement();
			query.executeUpdate("DELETE FROM Activite WHERE Id = '" + id + "'");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Récupère toutes les activités en base de données
	public ArrayList<ActiviteModelBean> getAllActivite() {
		// return value
		ArrayList<ActiviteModelBean> activiteList = new ArrayList<ActiviteModelBean>();
		// Création de la requête
		java.sql.Statement query;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			query = connection.createStatement();
			ResultSet resultat = query.executeQuery("SELECT * FROM Activite ORDER BY Domaine");
			// Extraction des données
			while (resultat.next()) {
				ActiviteModelBean model = new ActiviteModelBean();

				model.setId(resultat.getInt("Id"));
				model.setNomActivite(resultat.getString("NomActivite"));
				model.setDescription(resultat.getString("Description"));
				model.setNomLieu(resultat.getString("NomLieu"));
				model.setAdresse(resultat.getString("Adresse"));
				model.setVille(resultat.getString("Ville"));
				model.setCodePostal(resultat.getString("CodePostal"));
				model.setSiteWeb(resultat.getString("SiteWeb"));
				model.setTelephone(resultat.getString("Tel"));
				model.setEmail(resultat.getString("Email"));
				model.setDomaine(resultat.getString("Domaine"));
				model.setLienPhoto(resultat.getString("LienPhoto"));
				model.setImportance(resultat.getInt("Importance"));

				activiteList.add(model);
			}
			resultat.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return activiteList;
	}

	public ArrayList<ActiviteModelBean> getActivitebyDomaine(String domaine) {
		// return value
		ArrayList<ActiviteModelBean> activiteList = new ArrayList<ActiviteModelBean>();
		// Création de la requête
		java.sql.Statement query;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			query = connection.createStatement();
			ResultSet resultat = query
					.executeQuery("SELECT * FROM Activite WHERE Domaine = '"
							+ domaine + "'");
			// Extraction des données
			while (resultat.next()) {
				ActiviteModelBean model = new ActiviteModelBean();

				model.setId(resultat.getInt("Id"));
				model.setNomActivite(resultat.getString("NomActivite"));
				model.setDescription(resultat.getString("Description"));
				model.setNomLieu(resultat.getString("NomLieu"));
				model.setAdresse(resultat.getString("Adresse"));
				model.setVille(resultat.getString("Ville"));
				model.setCodePostal(resultat.getString("CodePostal"));
				model.setSiteWeb(resultat.getString("SiteWeb"));
				model.setTelephone(resultat.getString("Tel"));
				model.setEmail(resultat.getString("Email"));
				model.setDomaine(resultat.getString("Domaine"));
				model.setLienPhoto(resultat.getString("LienPhoto"));
				model.setImportance(resultat.getInt("Importance"));

				activiteList.add(model);
			}
			resultat.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return activiteList;
	}

	public ActiviteModelBean getActiviteById(int id) {
		ActiviteModelBean activite = new ActiviteModelBean();
		// Création de la requête
		java.sql.Statement query;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			query = connection.createStatement();
			ResultSet resultat = query
					.executeQuery("SELECT * FROM Activite WHERE Id = '" + id
							+ "'");
			// Extraction des données
			while (resultat.next()) {

				activite.setId(resultat.getInt("Id"));
				activite.setNomActivite(resultat.getString("NomActivite"));
				activite.setDescription(resultat.getString("Description"));
				activite.setNomLieu(resultat.getString("NomLieu"));
				activite.setAdresse(resultat.getString("Adresse"));
				activite.setVille(resultat.getString("Ville"));
				activite.setCodePostal(resultat.getString("CodePostal"));
				activite.setSiteWeb(resultat.getString("SiteWeb"));
				activite.setTelephone(resultat.getString("Tel"));
				activite.setEmail(resultat.getString("Email"));
				activite.setDomaine(resultat.getString("Domaine"));
				activite.setLienPhoto(resultat.getString("LienPhoto"));
				activite.setImportance(resultat.getInt("Importance"));

			}
			resultat.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return activite;

	}

	//Fonction recherche
	public ArrayList<ActiviteModelBean> RechercheParMotCle(String motCle)
	{
		ArrayList<ActiviteModelBean> listeRetour = new ArrayList<ActiviteModelBean>();
		
		// Création de la requête
		java.sql.Statement query;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			query = connection.createStatement();
			ResultSet resultat = query
					.executeQuery("SELECT * FROM Activite WHERE NomActivite LIKE '" + "%"+ motCle +"%' OR Domaine LIKE '" + "%"+ motCle +"%'");
			// Extraction des données '%motCle%'
			while (resultat.next()) {
				ActiviteModelBean model = new ActiviteModelBean();

				model.setId(resultat.getInt("Id"));
				model.setNomActivite(resultat.getString("NomActivite"));
				model.setDescription(resultat.getString("Description"));
				model.setNomLieu(resultat.getString("NomLieu"));
				model.setAdresse(resultat.getString("Adresse"));
				model.setVille(resultat.getString("Ville"));
				model.setCodePostal(resultat.getString("CodePostal"));
				model.setSiteWeb(resultat.getString("SiteWeb"));
				model.setTelephone(resultat.getString("Tel"));
				model.setEmail(resultat.getString("Email"));
				model.setDomaine(resultat.getString("Domaine"));
				model.setLienPhoto(resultat.getString("LienPhoto"));
				model.setImportance(resultat.getInt("Importance"));

				listeRetour.add(model);
			}
			resultat.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listeRetour;
	}
	
	//Renvoie toutes les activites obligatoires
	public ArrayList<ActiviteModelBean> getAllActiviteObligatoires()
	{
		ArrayList<ActiviteModelBean> listeRetour = new ArrayList<ActiviteModelBean>();
		
		java.sql.Statement query;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			query = connection.createStatement();
			ResultSet resultat = query
					.executeQuery("SELECT * FROM Activite WHERE Importance = 1");
			while (resultat.next()) {
				ActiviteModelBean model = new ActiviteModelBean();

				model.setId(resultat.getInt("Id"));
				model.setNomActivite(resultat.getString("NomActivite"));
				model.setDescription(resultat.getString("Description"));
				model.setNomLieu(resultat.getString("NomLieu"));
				model.setAdresse(resultat.getString("Adresse"));
				model.setVille(resultat.getString("Ville"));
				model.setCodePostal(resultat.getString("CodePostal"));
				model.setSiteWeb(resultat.getString("SiteWeb"));
				model.setTelephone(resultat.getString("Tel"));
				model.setEmail(resultat.getString("Email"));
				model.setDomaine(resultat.getString("Domaine"));
				model.setLienPhoto(resultat.getString("LienPhoto"));
				model.setImportance(resultat.getInt("Importance"));

				listeRetour.add(model);
			}
			resultat.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listeRetour;
	}
}
