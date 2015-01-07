package model;

public class ActiviteModelBean {

	private String nom;
	private String siteWeb;
	private String description;
	private int idDomaine;
	
	public ActiviteModelBean(){
		
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getSiteWeb() {
		return siteWeb;
	}
	public void setSiteWeb(String siteWeb) {
		this.siteWeb = siteWeb;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIdDomaine() {
		return idDomaine;
	}
	public void setIdDomaine(int idDomaine) {
		this.idDomaine = idDomaine;
	}
	
	
}
