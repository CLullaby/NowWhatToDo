package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class LoginBean implements Serializable{
	private String identifiant;
	private String motDePasse;
	private boolean connected;
	
	public LoginBean(String identifiant, String motDePasse, boolean connected) {
		super();
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
		this.connected = connected;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public LoginBean() {
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
	
}
