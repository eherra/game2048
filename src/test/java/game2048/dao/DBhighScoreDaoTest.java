/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2048.dao;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DBhighScoreDaoTest {
    private DBhighScoreDao testDb;
    
    @Before
    public void setUp() {
        testDb = new DBhighScoreDao(true);
        testDb.addScore(9999, 4, "test");
        testDb.addScore(66, 4, "test");
        testDb.addScore(99, 4, "test");
        testDb.addScore(88888, 4, "test");
        testDb.addScore(777, 4, "test");
        
        testDb.addScore(9999, 3, "test");
        testDb.addScore(66, 3, "test");
        testDb.addScore(99, 3, "test");
        testDb.addScore(131231231, 3, "test");
        
        testDb.addScore(9999, 5, "test");
        testDb.addScore(22222, 5, "test");
        testDb.addScore(777, 5, "test");    
    }

    @After
    public void deleteDatabase() {
        testDb.deleteDatabase();
    }    

    @Test
    public void testAddScore() {
        assertEquals(4, testDb.getTopFiveScores(3).size());
        assertEquals(5, testDb.getTopFiveScores(4).size());
        assertEquals(3, testDb.getTopFiveScores(5).size());
    }

    @Test
    public void testGetTopFiveScores() {
        List<String> topFive = testDb.getTopFiveScores(5);
        String testString1 ="1. 22222p, test";
        String testString2 ="2. 9999p, test";
        String testString3 ="3. 777p, test";

        assertEquals(testString1, topFive.get(0));
        assertEquals(testString2, topFive.get(1));
        assertEquals(testString3, topFive.get(2));
        
        List<String> topFour = testDb.getTopFiveScores(4);
        testString1 ="1. 88888p, test";
        testString2 ="2. 9999p, test";
        testString3 ="3. 777p, test";
        String testString4 ="4. 99p, test";
        String testString5 ="5. 66p, test";

        assertEquals(testString1, topFour.get(0));
        assertEquals(testString2, topFour.get(1));
        assertEquals(testString3, topFour.get(2));
        assertEquals(testString4, topFour.get(3));
        assertEquals(testString5, topFour.get(4));
    }

    @Test
    public void testGetTopScore() {
        assertEquals(131231231, testDb.getTopScore(3));  
        assertEquals(88888, testDb.getTopScore(4));   
        assertEquals(22222, testDb.getTopScore(5)); 
    }

    @Test
    public void testGetFifthScore() {
        assertEquals(0, testDb.getFifthScore(3));  
        assertEquals(66, testDb.getFifthScore(4));   
        assertEquals(0, testDb.getFifthScore(5)); 
        
        testDb.addScore(777, 5, "");    
        testDb.addScore(44, 5, "");    
        assertEquals(44, testDb.getFifthScore(5)); 

        testDb.addScore(22, 5, "");    
        testDb.addScore(21, 5, "");            
        testDb.addScore(45, 5, "");    
        testDb.addScore(19, 5, "");    
        assertEquals(45, testDb.getFifthScore(5)); 
    }
}
