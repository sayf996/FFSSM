/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFSSM;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 *
 * @author saifmohanad
 */
public class Tests {
    
    LocalDate dateNeSaif, dateNeJack, delivrance, datePlongee;
    Site albi;   
    Licence licenceSaif;
    Plongeur jack;
    Plongee plongee1;
    Moniteur saif, presidentAlbi;
    Club albiClub;
    
    
    
    
  
    
    
    
    
    @BeforeEach
    public void setUp() {
        jack = new Plongeur("78125", "JOHNS", "Jack", "74 Rue Croix Verte", "0634058379", dateNeJack, GroupeSanguin.APLUS, 3);
        
        saif = new Moniteur("1234", "MAJID","Saif", "18 Rue Lavedan", "0774476821", dateNeSaif, GroupeSanguin.AMOINS, 2, 33);
       
        
        datePlongee = LocalDate.of(2020, 11, 28);
        albiClub = new Club(presidentAlbi, "Albi Dive Club", "077234823");
        
        plongee1 = new Plongee(albi, saif, datePlongee, 10, 3);
        
        
        delivrance = LocalDate.of(2020, 1, 26);
        
    }
    
    
    @Test
    public void testEstConforme() {
            
        jack.ajouteLicence("87612", delivrance, albiClub);
                        
        assertFalse(plongee1.estConforme());
        
        jack.ajouteLicence("87612", delivrance.plusYears(-1), albiClub);
                        
        assertFalse(plongee1.estConforme());
            
    }
     @Test 
    public void testOrganisePlongee() {
        
        assertEquals(0, albiClub.getMyPlongees().size());
        
        albiClub.organisePlongee(plongee1);
        
        assertEquals(1, albiClub.getMyPlongees().size());
    }
    @Test
    public void testPlongeesNonConformes1() {
        
        
        albiClub.organisePlongee(plongee1);
        
        assertEquals(1, albiClub.plongeesNonConformes().size()); // non conformes because we dont have a valid licence
                
        
    }
    @Test
    public void testPlongeesNonConformes2() {
        
        saif.ajouteLicence("876234", delivrance, albiClub);
        
        assertEquals(0, albiClub.plongeesNonConformes().size()); // here the plongee is confirmed so the number of plongees non conformes is 0
    }
    @Test
    public void testAjouteParticipant() {
        
        assertEquals(0, plongee1.getPlongeurs().size());
        
        plongee1.ajouteParticipant(jack);
        
        assertEquals(1, plongee1.getPlongeurs().size());
        
       
    }
    @Test
    public void testEmbauche() {
        
        
        assertTrue(saif.emplois().isEmpty());
        assertTrue(saif.employeurActuel().isEmpty()); //we make sure saif is not in employeractuel
        
        
        saif.nouvelleEmbauche(albiClub, delivrance);
        Embauche job = saif.emplois().get(saif.emplois().size() - 1); // we make a new job
        
        assertFalse(job.estTerminee());   
        job.terminer(LocalDate.now());
        assertTrue(job.estTerminee());
        assertEquals(LocalDate.now(), job.getFin());
    }
    @Test
    public void testEstValide() {
         licenceSaif = new Licence(saif, "87611", delivrance, 3, albiClub);
         assertTrue(licenceSaif.estValide(delivrance.plusWeeks(0))); 
         assertFalse(licenceSaif.estValide(delivrance.plusMonths(12))); //After one year Licence expired
    }


 
}

