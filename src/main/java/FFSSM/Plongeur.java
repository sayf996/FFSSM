package FFSSM;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Plongeur extends Personne
{
	public int niveau;
        public Licence licence;
        public GroupeSanguin groupeSanguin;
        public List<Licence> myLicences = new ArrayList<>();
        
        
       
        public Plongeur (String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, GroupeSanguin groupeSanguin, int niveau) {
            super (numeroINSEE, nom, prenom, adresse, telephone, naissance);
           this.niveau = niveau;
           this.groupeSanguin = groupeSanguin;
        }
    
	
	public void ajouteLicence( String numero, LocalDate delivrance, Club club)
	{
            this.licence = new Licence(this,numero,delivrance,niveau, club);
        }
		
                public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
public Licence getLicence(LocalDate date) {
        
        Licence currentLicence = null;
        
        for (Licence licence : myLicences) {
            
            Period period = Period.between(licence.getDelivrance(), date);
            
            if (period.toTotalMonths() >= 0 && period.toTotalMonths() < 12) {
                
                return licence;
            }
                
        }
        // Si le plongeur n'a pas une licence valide à la LocalDate date, on retourne null
        return currentLicence;
    }

}
