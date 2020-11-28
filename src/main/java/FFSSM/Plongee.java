/**
 * @(#) Plongee.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Plongee {

	public Site lieu;

	public Moniteur chefDePalanquee;

	public LocalDate date;

	public int profondeur;

	public int duree;
        
        private final Set<Plongeur> plongeurs = new HashSet<>();
        
        
       
        

	public Plongee(Site lieu, Moniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
        
		this.lieu = lieu;
		this.chefDePalanquee = chefDePalanquee;
		this.date = date;
		this.profondeur = profondeur;
		this.duree = duree;
                
	}
        
            
        

	public void ajouteParticipant(Plongeur participant) {
		plongeurs.add(participant);
	}

	public LocalDate getDate() {
		return date;
	}
        public Set<Plongeur> getPlongeurs() {
                return plongeurs;
        }

	/**
	 * Détermine si la plongée est conforme.Une plongée est conforme si tous les plongeurs de la palanquée ont une
 licence valide à la date de la plongée
	 * @return vrai si la plongée est conforme
	 */
	public boolean estConforme() {
        return plongeurs.stream().anyMatch(plongeur -> (plongeur.getLicence(date) == null));
        }
}
        
        

        
                
        
                       
                   

    


