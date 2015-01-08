package model;

public class ActiviteModelBean {

	public ActiviteModelBean() {

	}
	private int id;
	private String nomActivite;
	private String Description;
	private String nomLieu;
	private String adresse;
	private String ville;
	private String codePostal;
	private String siteWeb;
	private int telephone;
	private String email;
	private String domaine;
	private String lienPhoto;
	private int importance;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomActivite() {
		return nomActivite;
	}
	public void setNomActivite(String nomActivite) {
		this.nomActivite = nomActivite;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getNomLieu() {
		return nomLieu;
	}
	public void setNomLieu(String nomLieu) {
		this.nomLieu = nomLieu;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getSiteWeb() {
		return siteWeb;
	}
	public void setSiteWeb(String siteWeb) {
		this.siteWeb = siteWeb;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDomaine() {
		return domaine;
	}
	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
	public String getLienPhoto() {
		return lienPhoto;
	}
	public void setLienPhoto(String lienPhoto) {
		this.lienPhoto = lienPhoto;
	}
	public int getImportance() {
		return importance;
	}
	public void setImportance(int importance) {
		this.importance = importance;
	}

}