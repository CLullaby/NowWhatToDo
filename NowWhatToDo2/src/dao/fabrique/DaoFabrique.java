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
			// Chargement du Driver, puis �tablissement de la connexion
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�thode permettant de renvoyer une instance de la classe Singleton
	 * 
	 * @return Retourne l'instance du singleton.
	 */
	public final static DaoFabrique getInstance() {
		// Le "Double-Checked Singleton"/"Singleton doublement v�rifi�" permet
		// d'�viter un appel co�teux � synchronized,
		// une fois que l'instanciation est faite.
		if (DaoFabrique.instance == null) {
			// Le mot-cl� synchronized sur ce bloc emp�che toute instanciation
			// multiple m�me par diff�rents "threads".
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

