/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertytycoon;

/**
 *
 * @author Group 22
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
    
    /**
     * Retrieve the name of a property
     * @return property's name
     */
    public String getName()
    {
        return propertyName;
    }
    
    /**
     * Retrieve the amount of houses or the amount of owned properties of the
     * same colour to possibly increase rent
     * @return currentRentLevel return property's rent level
     */
    public int getRentLevel()
    {
        return currentRentLevel;
    }
    
    /**
     * Retrieve a property's adapted rent, taking houses and owned properties of
     * the same colour into account
     * @return property's rent
     */
    public int getRent()
    {
        return rentLevels[getRentLevel()];
    }

    /**
     * Is a property available on the free market?
     * @return buyable return true or false depending on whether a property is owned or not
     */
    public boolean isBuyable() 
    {
        return buyable;
    }
    
    /**
     * Change a property's availability to being bought
     * @param b true if one can buy the property, false if not
     */
    public void setBuyable(boolean b) 
    {
        buyable = b;
    }    

    /**
     * Retrieve the name of a property
     * @return propertyName return property's name
     */
    public String getPropertyName() 
    {
        return propertyName;
    }

    /**
     * Retrieve the colour group of a property
     * @return group return property's colour group
     */
    public String getGroup() 
    {
        return group;
    }

    /**
     * Retrieve the cost of houses and hotels for a property
     * @return rentLevels return property's cost of accomodation
     */
    public int[] getRentLevels() 
    {
        return rentLevels;
    }

    /**
     * Retrieve the mortgage value of a property
     * @return mortgage return property's mortgage
     */
    public int getMortgage() 
    {
        return mortgage;
    }

    /**
     * Retrieve the base cost of a property
     * @return cost return property's price
     */
    public int getCost() 
    {
        return cost;
    }
}
