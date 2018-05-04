/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertytycoon;

import java.util.Random;

/**
 *
 * @author Group 22
 */
public class Dice 
{
    private int firstVal;
    private int secVal;
    
    /**
     * Create blank spaces for future dice values
     */
    public Dice()
    {
        firstVal = 0;
        secVal = 0;
    }

    /**
     * Retrieve the value shown by the first dice
     * @return firstVal return the value shown by the first dice
     */
    public int getFirstVal() 
    {
        return firstVal;
    }

    /**
     * Retrieve the value shown by the second dice
     * @return secVal return the value shown by the second dice
     */
    public int getSecVal() 
    {
        return secVal;
    }
    
    /**
     * Roll the dice to obtain two values between 1 and 6
     */
    public void rollDice() //assigns dice rolling values to die
    {
        Random r = new Random(); //creating the random values
        int randomInt = r.nextInt(6) + 1; //1-6
        int randomInt2 = r.nextInt(6) + 1;
        
        firstVal = randomInt; //assigning the values here
        secVal = randomInt2;
    }
}
