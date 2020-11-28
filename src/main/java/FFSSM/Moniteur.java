/**
 * @(#) Moniteur.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Moniteur extends Plongeur {

    public int numeroDiplome;
    
    private final List<Embauche> myJobs = new ArrayList<>();

    public Moniteur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, GroupeSanguin groupeSanguin, int niveau, int numeroDiplome) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance, groupeSanguin, niveau);
        this.numeroDiplome = numeroDiplome;
    }

    /**
     * Si ce moniteur n'a pas d'embauche, ou si sa dernière embauche est terminée,
     * ce moniteur n'a pas d'employeur.
     * @return l'employeur actuel de ce moniteur sous la forme d'un Optional
     */
    public List<Embauche> getMyJobs(){
            return myJobs;
    }
    public Optional<Club> employeurActuel() {
          Optional<Club> opt;
        
        if (!myJobs.isEmpty() && !myJobs.get(myJobs.size()).estTerminee()) {
            
            Club a = myJobs.get(myJobs.size()).getEmployeur();
            opt = Optional.ofNullable(a);
        }
        else {           
            Club a = null;
            opt = Optional.ofNullable(a);
        }        
        return opt;
    }
    
    /**
     * Enregistrer une nouvelle embauche pour cet employeur
     * @param employeur le club employeur
     * @param debutNouvelle la date de début de l'embauche
     */
    public void nouvelleEmbauche(Club employeur, LocalDate debutNouvelle) {   
        Embauche e = new Embauche(debutNouvelle, this, employeur); 	    
        myJobs.add(e);
    }

    public List<Embauche> emplois() {
         return myJobs;
    }

}
