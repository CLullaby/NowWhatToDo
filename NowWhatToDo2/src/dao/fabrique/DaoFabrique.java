package dao.fabrique;

import dao.instance.DaoAvancement;
import dao.instance.DaoCompte;
import dao.instance.DaoActivite;

public class DaoFabrique {

	private static volatile DaoFabrique instance = null;

	private String DB_HOST = "localhost";
	private String DB_PORT = "3306";
	private String DB_NAME = "NowWhatToDo";
	private String DB_USER = "root";
	private String DB_PWD = "";

	private DaoFabrique() {
		super();
		try {
			// Chargement du Driver, puis établissement de la connexion
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode permettant de renvoyer une instance de la classe Singleton
	 * 
	 * @return Retourne l'instance du singleton.
	 */
	public final static DaoFabrique getInstance() {
		// Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
		// d'éviter un appel coûteux à synchronized,
		// une fois que l'instanciation est faite.
		if (DaoFabrique.instance == null) {
			// Le mot-clé synchronized sur ce bloc empêche toute instanciation
			// multiple même par différents "threads".
			synchronized (DaoFabrique.class) {
				if (DaoFabrique.instance == null) {
					DaoFabrique.instance = new DaoFabrique();
				}
			}
		}
		return DaoFabrique.instance;
	}

	public DaoCompte createUserDao() {
		DaoCompte userDao = new DaoCompte(this.DB_HOST,this.DB_PORT,this.DB_NAME,this.DB_USER,this.DB_PWD);
		return userDao;
	}
	
	public DaoActivite createActiviteDao(){
		DaoActivite activiteDao = new DaoActivite(this.DB_HOST,this.DB_PORT,this.DB_NAME,this.DB_USER,this.DB_PWD);
		return activiteDao;
}
	public DaoAvancement createAvancementDao() {
		DaoAvancement avancementDao = new DaoAvancement(this.DB_HOST,this.DB_PORT,this.DB_NAME,this.DB_USER,this.DB_PWD);
		return avancementDao;

	}

}

