package model;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AvancementActiviteModelBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idAvancement;
	private int avancement;
	private String dateDebut;
	private String dateFin;
	private int ceCompte;
	private int ceActivite;
	//Pour afficher dans compte
	private String nomDomaine;
	private String nomActivite;
	private String descriptionActivite;
	
	public AvancementActiviteModelBean() {
		super();
	}
	
	public AvancementActiviteModelBean(int avancement, String dateDebut, String dateFin, int ceCompte, int ceActivite) {
		this.avancement = avancement;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.ceCompte = ceCompte;
		this.ceActivite = ceActivite;
	}
	
	public AvancementActiviteModelBean(int avancement, String dateDebut, String dateFin, int ceCompte, int ceActivite, String nomDomaine, String nomActivite, String descriptionActivite) {
		this.avancement = avancement;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.ceCompte = ceCompte;
		this.ceActivite = ceActivite;
		this.nomDomaine = nomDomaine;
		this.nomActivite = nomActivite;
		this.descriptionActivite = descriptionActivite;
	}

	public int getIdAvancement() {
		return idAvancement;
	}
	
	public void setIdAvancement(int idAvancement) {
		this.idAvancement = idAvancement;
	}
	
	public int getAvancement() {
		return avancement;
	}

	public void setAvancement(int avancement) {
		this.avancement = avancement;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public int getCeCompte() {
		return ceCompte;
	}

	public void setCeCompte(int ceCompte) {
		this.ceCompte = ceCompte;
	}

	public int getCeActivite() {
		return ceActivite;
	}

	public void setCeActivite(int ceActivite) {
		this.ceActivite = ceActivite;
	}

	public String getNomDomaine() {
		return nomDomaine;
	}

	public void setNomDomaine(String nomDomaine) {
		this.nomDomaine = nomDomaine;
	}

	public String getNomActivite() {
		return nomActivite;
	}

	public void setNomActivite(String nomActivite) {
		this.nomActivite = nomActivite;
	}

	public String getDescriptionActivite() {
		return descriptionActivite;
	}

	public void setDescriptionActivite(String descriptionActivite) {
		this.descriptionActivite = descriptionActivite;
	}
	
	@Override
	public String toString() {
		return "AvancementTacheModelBean [idAvancement=" + idAvancement
				+ ", avancement=" + avancement + ", dateDebut=" + dateDebut
				+ ", dateFin=" + dateFin + ", ceCompte=" + ceCompte
				+ ", ceActivite=" + ceActivite + "]";
	}
	

}
