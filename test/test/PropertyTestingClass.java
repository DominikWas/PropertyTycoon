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
import propertytycoon.Property;

/**
 *
 * @author Group 22
 */
public class PropertyTestingClass {
    
    public PropertyTestingClass() {
        
        
    }
    
    @Test 
    public void test1() {
      Property p = new Property("testName", 100, "red", true, 0,0,0,0,0,0,1000);
      p.setBuyable(false);
      assertEquals(false, p.isBuyable());
    }
    
    @Test 
    public void test2() {
      Property p = new Property("testName", 100, "red", true, 0,0,0,0,0,0,1000);
      p.getGroup();
      assertEquals(p.getGroup(), "red");
    }
    
    @Test 
    public void test3() {
      Property p = new Property("testName", 100, "red", true, 0,0,0,0,0,0,1000);
      assertEquals(p.getRentLevel(), 0);
    }
    
    @Test 
    public void test4() {
      Property p = new Property("testName", 100, "red", true, 0,0,0,0,0,0,1000);
      assertEquals(p.getMortgage(), 1000);
    }
    
    @Test 
    public void test5() {
      Property p = new Property("testName", 100, "red", true, 0,0,0,0,0,0,1000);
      assertEquals(p.getCost(), 100);
    }
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
