package test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import propertytycoon.Player;

/**
 *
 * @author Group 22
 */
public class PlayerTestingClass {
    
    public PlayerTestingClass() {
        
    }
    
    @Test
    public void test1() {
        Player p = new Player(1, 10);
        assertEquals(p.getPlayerID(), 1);
    }
    @Test
    public void test2() {
        Player p = new Player(1, 10);
        assertEquals(p.getTokenID(), 10);
    }
    @Test
    public void test3() {
        Player p = new Player(1, 10);
        p.setCash(100);
        assertEquals(p.getCash(), 100);
    }
    @Test
    public void test4() {
        Player p = new Player(1, 10);
        p.getCurrentPos();
        assertEquals(p.getCurrentPos(), 0);
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
