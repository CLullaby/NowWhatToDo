package model;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AvancementActiviteModelBean implements Serializable {
	
	private int idAvancement;
	private int avancement;
	private String dateDebut;
	private String dateFin;
	private int ceCompte;
	private int ceActivite;
	
	
	
	public AvancementActiviteModelBean() {
		super();
		// TODO Auto-generated constructor stub
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



	@Override
	public String toString() {
		return "AvancementTacheModelBean [idAvancement=" + idAvancement
				+ ", avancement=" + avancement + ", dateDebut=" + dateDebut
				+ ", dateFin=" + dateFin + ", ceCompte=" + ceCompte
				+ ", ceActivite=" + ceActivite + "]";
	}
	

}
