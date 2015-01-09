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
	private String telephone;
	private String email;
	private String domaine;
	private String lienPhoto;
	private int importance;
	
	
	
	public ActiviteModelBean(String nomActivite, String description,
			String nomLieu, String adresse, String ville, String codePostal,
			String siteWeb, String telephone, String email, String domaine,
			String lienPhoto, int importance) {
		super();
		this.nomActivite = nomActivite;
		Description = description;
		this.nomLieu = nomLieu;
		this.adresse = adresse;
		this.ville = ville;
		this.codePostal = codePostal;
		this.siteWeb = siteWeb;
		this.telephone = telephone;
		this.email = email;
		this.domaine = domaine;
		this.lienPhoto = lienPhoto;
		this.importance = importance;
	}
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
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