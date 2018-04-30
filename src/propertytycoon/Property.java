/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertytycoon;

/**
 *
 * @author dw294
 */
public class Property 
{
    private final String propertyName;
    private final String group;
    private boolean buyable;
    private final int[] rentLevels; //level 0 (default), level 1 (all of a kind), 
    //level 2 (1 houses), level 3 (2 houses), level 4 (3 houses),
    //level 5 (4 houses), level 6 (hotel)
    private int currentRentLevel;
    private final int mortgage;
    private final int cost;
    
    public Property(String propertyName, int cost, String group, boolean buyable, 
            int rent0, int rent1, int rent2, int rent3, 
            int rent4, int rent5, int mortgage)
    {
        this.propertyName = propertyName;
        this.group = group;
        this.buyable = buyable;
        rentLevels = new int[6];        
        rentLevels[0] = rent0;
        rentLevels[1] = rent1;
        rentLevels[2] = rent2;
        rentLevels[3] = rent3;
        rentLevels[4] = rent4;
        rentLevels[5] = rent5;
        currentRentLevel = 0;
        this.mortgage = mortgage;
        this.cost = cost;
    }
    
    public String getName()
    {
        return propertyName;
    }
    
    public int getRentLevel()
    {
        return currentRentLevel;
    }
    
    public int getRent()
    {
        return rentLevels[getRentLevel()];
    }

    public boolean isBuyable() 
    {
        return buyable;
    }
    
    public void setBuyable(boolean b) 
    {
        buyable = b;
    }    

    public String getPropertyName() 
    {
        return propertyName;
    }

    public String getGroup() 
    {
        return group;
    }

    public int[] getRentLevels() 
    {
        return rentLevels;
    }

    public int getCurrentRentLevel() 
    {
        return currentRentLevel;
    }

    public int getMortgage() 
    {
        return mortgage;
    }

    public int getCost() 
    {
        return cost;
    }
}
