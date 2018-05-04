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
import propertytycoon.Dice;

/**
 *
 * @author Group 22
 */
public class DiceTestingClass {
    
    public DiceTestingClass() {
    }
    

    @Test
    public void test1() {
        Dice d = new Dice();
        d.getFirstVal();
        assertEquals(d.getFirstVal(), 0);
    }
    
    @Test
    public void test2() {
        Dice d = new Dice();
        d.getSecVal();
        System.out.println(d.getSecVal());
        assertEquals(d.getFirstVal(), 0);
    }
    
    @Test
    public void test3() {
        Dice d = new Dice();
        Dice d2 = new Dice();
        d.rollDice();
        d2.rollDice();
        System.out.println(d.getFirstVal());
        assertNotEquals(d.getFirstVal(), d2.getFirstVal());
    }
    
    @Test
    public void test4() {
        Dice d = new Dice();
        Dice d2 = new Dice();
        d.rollDice();
        d2.rollDice();
        System.out.println(d.getSecVal());
        assertNotEquals(d.getSecVal(), d2.getSecVal());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
