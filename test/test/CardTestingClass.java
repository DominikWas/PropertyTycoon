package test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import propertytycoon.Card;
import propertytycoon.Player;

/**
 *
 * @author Group 22
 */
public class CardTestingClass 
{
    
    public CardTestingClass() 
    {
    }
    
    @Test
    public void test() {
        Card c = new Card();
        Player p = new Player(1, 1);
        ArrayList testOpportunityKnocks = new ArrayList();
        ArrayList testPotLuck = new ArrayList();
        ArrayList<Integer> expectedAL = new ArrayList<Integer>();
        ArrayList<Integer> expectedTPL = new ArrayList<Integer>();


        for (int i = 0; i < 16; i++) {
            testOpportunityKnocks.add(i);
            testPotLuck.add(i);
            expectedAL.add(i);
            expectedTPL.add(i);
        }
        assertEquals(testOpportunityKnocks, expectedAL);
        assertEquals(testPotLuck, expectedTPL);
    }
    
    @Test
    public void test2() {
        ArrayList testOpportunityKnocks = new ArrayList();
        testOpportunityKnocks.add(10);
        int cardTakenTest = (Integer) testOpportunityKnocks.get(0);
        System.out.println(cardTakenTest);
        assertEquals(cardTakenTest, 10);
       
    }

    @Test
    public void test3() {
        ArrayList testOpportunityKnocks = new ArrayList();
        testOpportunityKnocks.add(10);
        int cardTakenTest = (Integer) testOpportunityKnocks.get(0);
        System.out.println(testOpportunityKnocks.get(testOpportunityKnocks.size()-1));
        
    }
    
    @Test 
    public void test4() { //testing potLuckEffects
        Player p = new Player(1, 1);
        Card c = new Card();
        c.potLuckEffects(p, 1);
        System.out.println(c);
        assertEquals(c, "propertytycoon.Card@1175e2db");
    }
    
    @Test 
    public void test5() { //testing potLuckEffects
        Player p = new Player(1, 1);
        Card c = new Card();
        c.potLuckEffects(p, 2);
        System.out.println(c);
        assertEquals(c, ""); 
    }
    
        @Test 
    public void test6() { //testing potLuckEffects
        Player p = new Player(1, 1);
        Card c = new Card();
        c.opportunityKnocksEffects(p, 1);
        System.out.println(c);
        assertEquals(c, ""); 
    }
    
        @Test 
    public void test7() { //testing potLuckEffects
        Player p = new Player(1, 1);
        Card c = new Card();
        c.opportunityKnocksEffects(p, 2);
        System.out.println(c);
        assertEquals(c, ""); 
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
