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
import propertytycoon.Station;

/**
 *
 * @author Group 22
 */
public class StationTestingClass {
    
    public StationTestingClass() {
    }
    
    @Test
    public void test1() {
        Station s = new Station("testName", true);
        System.out.println(s.stationName);
        assertEquals("testName", s.stationName);
    }
    

}
