package com.service;

import java.util.List;

import com.session.AuthentificationServiceLocal;
import com.users.Administration;
import com.users.Etudiant;
import com.users.Utilisateur;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Produces;


@Path("/authentification")
@Stateless
public class AuthentificationServ {
	private AuthentificationServiceLocal metier;

	public void addEtudiant( String nom, String prenom,  String email,
			String password,  boolean active) {
		Etudiant etd = new Etudiant(nom, prenom, email, password, active);
		metier.sauvegarderUtilisateur(etd);
	} 

	public void addAdministrateur( String email, String password,  boolean active) {
		Utilisateur admin = new Administration(email, password, active);
		metier.sauvegarderUtilisateur(admin);
	}

	public void activerCompte(String nom, String prenom) {
		Etudiant etd = metier.recherherEtudiant(nom, prenom);
		Administration admin = new Administration("z", "z", true);
		admin.activerCompte(etd);
	}

	public void desactiverCompte(String nom, String prenom) {
		Etudiant etd = metier.recherherEtudiant(nom, prenom);
		Administration admin = new Administration("z", "z", true);
		admin.desactiverCompte(etd);
	}
	
	
	public Etudiant findEtd(String nom, String prenom) {
		return metier.recherherEtudiant(nom, prenom);
	}
	
	@GET
	@Path("/listetd")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Etudiant> ConsulterEtundiants()
	{
		return metier.listerEtudiants();
	}
	
	
	
}
