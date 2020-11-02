/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author balooza
 */
public class KassapaateTest {
    private Kassapaate kassa;
        
    @Before
    public void setUp() {
        kassa  = new Kassapaate();
    }
    
    @Test 
    public void rahamaaraJaMyydytLounaatOikein() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void ostotKateiselleOnnistuuOikein() {
        assertEquals(60, kassa.syoEdullisesti(300));
        assertEquals(100240, kassa.kassassaRahaa());
        
        assertEquals(100, kassa.syoMaukkaasti(500));
        assertEquals(100640, kassa.kassassaRahaa());
        
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());

        assertEquals(200, kassa.syoEdullisesti(200));
        assertEquals(100640, kassa.kassassaRahaa());

        assertEquals(300, kassa.syoMaukkaasti(300));
        assertEquals(100640, kassa.kassassaRahaa());
        
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void korttiOstotToimiiOikein() {
        Maksukortti kortti = new Maksukortti(700);
        
        assertEquals(true, kassa.syoEdullisesti(kortti));
        assertEquals(true, kassa.syoMaukkaasti(kortti));
        
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());    
        
        assertEquals(false, kassa.syoEdullisesti(kortti));
        assertEquals(false, kassa.syoMaukkaasti(kortti));    
        
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(1, kassa.edullisiaLounaitaMyyty()); 
        
        assertEquals(100000, kassa.kassassaRahaa()); 
    }
    
    @Test
    public void kortilleLatausToimiiJaKortinSaldoOikea() {
        Maksukortti kortti = new Maksukortti(0);
        kassa.lataaRahaaKortille(kortti, 700);
        assertEquals(100700, kassa.kassassaRahaa());
        
        assertEquals(700 ,kortti.saldo());
    }
}
