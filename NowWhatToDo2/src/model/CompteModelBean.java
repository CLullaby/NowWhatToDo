package model;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CompteModelBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String nom;
	private String prenom;
	private String identifiant;
	private String motDePasse;
	private String email;
	private int age;
	private String lienPhoto;
	private String adresse;
	private String codePostal;
	private String tel;
	private String role;
	public CompteModelBean() {
		super();
	}

	public CompteModelBean(String nom, String prenom, String identifiant,
			String motDePasse, String email, int age, String lienPhoto,
			String adresse, String codePostal, String tel, String role) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
		this.email = email;
		this.age = age;
		this.lienPhoto = lienPhoto;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.tel = tel;
		this.role = role;
	}

	@Override
	public String toString() {
		return "CompteModelBean [nom=" + nom + ", prenom=" + prenom
				+ ", identifiant=" + identifiant + ", motDePasse=" + motDePasse
				+ ", email=" + email + ", age=" + age + ", lienPhoto="
				+ lienPhoto + ", adresse=" + adresse + ", codePostal="
				+ codePostal + ", tel=" + tel + ", role=" + role + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getLienPhoto() {
		return lienPhoto;
	}
	public void setLienPhoto(String lienAvatar) {
		this.lienPhoto = lienAvatar;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
