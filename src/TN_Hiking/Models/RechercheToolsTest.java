package TN_Hiking.Models;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RechercheToolsTest {


    //28 rue de mulhouse nancy
    //48.684357299999995
    //      6.178623099999999

    // 30 route marcel proust strasbourg
    //48.59107971191406
    //7.718184471130371

    //113.66 km

    Etape e1 = new Etape("Nancy",48.40107,6.09201);
    Etape e2 = new Etape("Strasbourg",48.41369,6.10586);
    Double Ecart = 0.05; // ecart a 5%
    Double realDist = 3.43; // calculee via google map

    Parcours parcours = new Parcours("",0,"");

    @Test
    public void testEcart()
    {
        e1.etapeShow();
        parcours.addEtape(e1);
        parcours.addEtape(e2);
        Double ecartRelatif = Math.abs((parcours.getDistance() - realDist) / realDist);
        Boolean bool = (ecartRelatif <= Ecart);
        System.out.println(parcours.getDistance()+"");
        System.out.println(ecartRelatif+"");
        //assertEquals(true,true);
        assertEquals("abc","abc");
        assertEquals(true,true);
        assertEquals(true,bool);
    }

}
