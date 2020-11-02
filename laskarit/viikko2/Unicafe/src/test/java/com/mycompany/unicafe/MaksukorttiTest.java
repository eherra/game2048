package com.mycompany.unicafe;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void kortinSaldoLisayksenJalkeenOikein() {
        kortti.lataaRahaa(10);
        assertEquals("saldo: 10.10", kortti.toString());

    }
    
    @Test
    public void saldoVaheneeOikein() {
        kortti.otaRahaa(500);
        assertEquals("saldo: 5.0", kortti.toString());
        kortti.otaRahaa(600);
        assertEquals("saldo: 5.0", kortti.toString());
        assertEquals(false, kortti.otaRahaa(600));
        assertEquals(true, kortti.otaRahaa(500));

    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoOikein() {
        assertEquals(1000, kortti.saldo());
    }
}
