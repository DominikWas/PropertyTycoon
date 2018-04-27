/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertytycoon;

import java.util.Random;

/**
 *
 * @author dw294
 */
public class Dice 
{
    private int firstVal;
    private int secVal;
    
    public Dice()
    {
        firstVal = 0;
        secVal = 0;
    }

    public int getFirstVal() 
    {
        return firstVal;
    }

    public int getSecVal() 
    {
        return secVal;
    }
    
    public void rollDice() //assigns dice rolling values to die
    {
        Random r = new Random(); //creating the random values
        int randomInt = r.nextInt(6) + 1; //1-6
        int randomInt2 = r.nextInt(6) + 1;
        
        firstVal = randomInt; //assigning the values here
        secVal = randomInt2;
    }
}